/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.controller.usuario.signup;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import pe.partnertech.fenosys.controller.util.util_code.*;
import pe.partnertech.fenosys.dto.request.usuario.general.UtilityTokenRequest;
import pe.partnertech.fenosys.dto.request.usuario.signup.SignupAgricultorRequest;
import pe.partnertech.fenosys.dto.response.general.MessageResponse;
import pe.partnertech.fenosys.enums.RolNombre;
import pe.partnertech.fenosys.model.Distrito;
import pe.partnertech.fenosys.model.Rol;
import pe.partnertech.fenosys.model.Usuario;
import pe.partnertech.fenosys.model.UtilityToken;
import pe.partnertech.fenosys.service.*;
import pe.partnertech.fenosys.tools.UtilityFenosys;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SignupAgricultorController {

    final
    PasswordEncoder passwordEncoder;

    final
    IUsuarioService usuarioService;

    final
    IRolService rolService;

    final
    IImagenService imagenService;

    final
    IDistritoService distritoService;

    final
    JavaMailSender mailSender;

    final
    TemplateEngine templateEngine;

    final
    IUtilityTokenService utilityTokenService;

    @Value("${front.baseurl}")
    private String baseurl;

    @Value("${spring.mail.username}")
    private String system_mail;

    @Value("${image.logo.url}")
    private String img_logo;

    public SignupAgricultorController(PasswordEncoder passwordEncoder, IUsuarioService usuarioService,
                                      IRolService rolService, IImagenService imagenService,
                                      IDistritoService distritoService, JavaMailSender mailSender,
                                      TemplateEngine templateEngine, IUtilityTokenService utilityTokenService) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        this.imagenService = imagenService;
        this.distritoService = distritoService;
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.utilityTokenService = utilityTokenService;
    }

    @PostMapping("/agricultor/signup")
    public ResponseEntity<?> SignupAgricultor(@RequestBody SignupAgricultorRequest signupAgricultorRequest,
                                              HttpServletRequest request) {

        Optional<Usuario> usuario_data =
                usuarioService.BuscarUsuario_By_EmailUsuario(signupAgricultorRequest.getEmailUsuario());

        if (usuario_data.isPresent()) {
            return Code_SignupValidations.SignupValidationResponse(usuario_data);
        } else {
            Optional<Rol> rol_data = rolService.BuscarRol_Nombre(RolNombre.ROLE_AGRICULTOR);

            if (rol_data.isPresent()) {
                try {
                    Optional<Distrito> distrito_data =
                            distritoService.BuscarDistrito_By_IDDistrito(signupAgricultorRequest.getDistritoUsuario());

                    if (distrito_data.isPresent()) {
                        if (usuarioService.ValidarUsername(signupAgricultorRequest.getUsernameUsuario())) {
                            return new ResponseEntity<>(new MessageResponse("El Usuario ya se encuentra en uso."),
                                    HttpStatus.CONFLICT);
                        } else {
                            Usuario usuario =
                                    new Usuario(
                                            signupAgricultorRequest.getNombreUsuario(),
                                            signupAgricultorRequest.getApellidoUsuario(),
                                            signupAgricultorRequest.getEmailUsuario(),
                                            signupAgricultorRequest.getUsernameUsuario(),
                                            passwordEncoder.encode(signupAgricultorRequest.getPasswordUsuario())
                                    );

                            usuarioService.GuardarUsuario(usuario);

                            Optional<Usuario> agricultor_data =
                                    usuarioService.BuscarUsuario_By_EmailUsuario(signupAgricultorRequest.getEmailUsuario());

                            if (agricultor_data.isPresent()) {
                                Usuario agricultor = agricultor_data.get();

                                //Agregando Usuario al Distrito
                                Distrito distrito = distrito_data.get();
                                Code_AssignDistrito.AgregarUsuarioToDistrito(agricultor, distrito, usuarioService);

                                //Asignando Rol: Agricultor
                                Code_SetUserRol.SetUserRol(agricultor, rol_data);

                                //Asignando Fecha de Registro Actual
                                agricultor.setFecharegistroUsuario(LocalDate.now());

                                //Asignando Estado de Cuenta: PENDIENTE
                                agricultor.setEstadoUsuario("PENDIENTE");

                                //Asignando Foto por Defecto: Agricultor
                                InputStream fotoStream = getClass().getResourceAsStream("/static/img/AgroUser.png");
                                Code_UploadFoto.AssignFoto(agricultor, fotoStream, imagenService);

                                String token = RandomString.make(50);

                                //Generando Token: Verificación
                                UtilityToken utilityToken = new UtilityToken(
                                        token,
                                        "Signup Agricultor Verify",
                                        LocalDateTime.now().plusHours(72),
                                        agricultor
                                );
                                utilityTokenService.GuardarUtilityToken(utilityToken);

                                String url = UtilityFenosys.GenerarUrl(request) + "/api/agricultor_verify_gateway?token=" + token;

                                EnviarCorreo(signupAgricultorRequest.getEmailUsuario(), url);

                                usuarioService.GuardarUsuario(agricultor);

                                return new ResponseEntity<>(new MessageResponse("Se ha registrado satisfactoriamente. Revise su " +
                                        "bandeja de entrada para verificar su cuenta. Recuerde que dispone no más de 72 horas para " +
                                        "culminar dicho proceso. De lo contrario, deberá rellenar el formulario nuevamente."),
                                        HttpStatus.OK);

                            } else {
                                return new ResponseEntity<>(new MessageResponse("Ocurrió un error durante el proceso " +
                                        "de registro de datos."),
                                        HttpStatus.NOT_FOUND);
                            }
                        }
                    } else {
                        return new ResponseEntity<>(new MessageResponse("Ocurrió un error al buscar su Ubicación."),
                                HttpStatus.NOT_FOUND);
                    }
                } catch (Exception e) {
                    return new ResponseEntity<>(new MessageResponse("Ocurrió un error al asignar la foto de perfil " +
                            "por defecto." + e),
                            HttpStatus.EXPECTATION_FAILED);
                }
            } else {
                return new ResponseEntity<>(new MessageResponse("Ocurrió un error al otorgar sus permisos " +
                        "correspondientes."),
                        HttpStatus.NOT_FOUND);
            }
        }
    }

    @GetMapping("/agricultor_verify_gateway")
    void RedirectAgricultorVerify(HttpServletResponse response, @Param(value = "token") String token) throws IOException {

        Optional<UtilityToken> utilitytoken_data = utilityTokenService.BuscarUtilityToken_By_Token(token);

        if (utilitytoken_data.isPresent()) {
            response.sendRedirect(baseurl + "/signup/agricultor/verify/" + token);
        } else {
            response.sendRedirect(baseurl + "/error/403");
        }
    }

    @PutMapping("/agricultor/signup/verify")
    public ResponseEntity<?> SignupAgricultorVerify(@RequestBody UtilityTokenRequest utilityTokenRequest) {

        Optional<UtilityToken> utilitytoken_data =
                utilityTokenService.BuscarUtilityToken_By_Token(utilityTokenRequest.getUtilityToken());

        if (utilitytoken_data.isPresent()) {
            UtilityToken utilitytoken = utilitytoken_data.get();

            Optional<Usuario> agricultor_data =
                    usuarioService.BuscarUsuario_By_IDUtilityToken(utilitytoken.getIdUtilityToken());

            if (agricultor_data.isPresent()) {
                Usuario agricultor = agricultor_data.get();

                agricultor.setEstadoUsuario("ACTIVO");
                usuarioService.GuardarUsuario(agricultor);

                utilityTokenService.EliminarUtilityToken(utilitytoken.getIdUtilityToken());

                return new ResponseEntity<>(new MessageResponse("Se ha verificado el usuario satisfactoriamente."),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new MessageResponse("Ocurrió un error durante la búsqueda del usuario."),
                        HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(new MessageResponse("Ocurrió un error en el proceso de verificación."),
                    HttpStatus.NOT_FOUND);
        }
    }

    private void EnviarCorreo(String email, String url) throws MessagingException, UnsupportedEncodingException {

        Code_SendEmail.VerifyEmail(email, url, mailSender, system_mail, img_logo, templateEngine);
    }
}

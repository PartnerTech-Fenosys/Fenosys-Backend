package pe.partnertech.fenosys.controller.util.util_code;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pe.partnertech.fenosys.model.Usuario;
import pe.partnertech.fenosys.service.IUsuarioService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Code_SendEmail {

    public static void VerifyEmail(String email, String url, JavaMailSender mailSender, String system_mail,
                                   String img_logo, TemplateEngine templateEngine)
            throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(system_mail, "Kaizen Talent Support");
        helper.setTo(email);

        String asunto = "Verificación de Cuenta";

        Context context = new Context();

        context.setVariables(SetParameters(img_logo, url, system_mail));

        String html_template = templateEngine.process("agricultorverify-mailtemplate", context);

        helper.setSubject(asunto);
        helper.setText(html_template, true);

        mailSender.send(message);
    }

    public static void AdminRequestEmail(String email, String url, IUsuarioService usuarioService, JavaMailSender mailSender,
                                         String system_mail, String img_logo, TemplateEngine templateEngine)
            throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(system_mail, "Fenosys Support");
        helper.setTo(email);

        String asunto = "Solicitud de Registro de Administrador";

        Optional<Usuario> admin_data = usuarioService.BuscarUsuario_By_EmailUsuario(email);

        if (admin_data.isPresent()) {
            Usuario admin = admin_data.get();

            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            model.put("img_logo", img_logo);
            model.put("username", admin.getUsernameUsuario());
            model.put("url", url);
            model.put("system_mail", system_mail);

            context.setVariables(model);

            String html_template = templateEngine.process("adminrequest-mailtemplate", context);

            helper.setSubject(asunto);
            helper.setText(html_template, true);

            mailSender.send(message);
        }
    }

    public static void RestorePasswordEmail(String email, String url, JavaMailSender mailSender, String system_mail,
                                            String img_logo, TemplateEngine templateEngine)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(system_mail, "Kaizen Talent Support");
        helper.setTo(email);

        String asunto = "Restablecimiento de Contraseña";

        Context context = new Context();

        context.setVariables(SetParameters(img_logo, url, system_mail));

        String html_template = templateEngine.process("restorepassword-mailtemplate", context);

        helper.setSubject(asunto);
        helper.setText(html_template, true);

        mailSender.send(message);
    }

    private static Map<String, Object> SetParameters(String img_logo, String url, String system_mail) {

        Map<String, Object> model = new HashMap<>();

        model.put("img_logo", img_logo);
        model.put("url", url);
        model.put("system_mail", system_mail);

        return model;
    }
}

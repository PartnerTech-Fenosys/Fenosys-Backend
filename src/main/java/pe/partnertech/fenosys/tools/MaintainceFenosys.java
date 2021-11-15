/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.tools;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pe.partnertech.fenosys.model.Usuario;
import pe.partnertech.fenosys.model.UtilityToken;
import pe.partnertech.fenosys.service.IUsuarioService;
import pe.partnertech.fenosys.service.IUtilityTokenService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Component
@Transactional
public class MaintainceFenosys {

    final
    IUtilityTokenService utilityTokenService;

    final
    IUsuarioService usuarioService;

    public MaintainceFenosys(IUtilityTokenService utilityTokenService, IUsuarioService usuarioService) {
        this.utilityTokenService = utilityTokenService;
        this.usuarioService = usuarioService;
    }

    @Scheduled(fixedRate = 60000)
    public void EliminarAdminSignupTimedOut() {
        Set<UtilityToken> utilitytoken_list = utilityTokenService.BuscarUtilityToken_By_ExpiracionAndRazon(LocalDateTime.now(),
                "Signup Admin");

        EliminarUsuario_UtilityToken(utilitytoken_list);
    }

    @Scheduled(fixedRate = 60000)
    public void EliminarAgricultorVerifyTimedOut() {
        Set<UtilityToken> utilitytoken_list = utilityTokenService.BuscarUtilityToken_By_ExpiracionAndRazon(LocalDateTime.now(),
                "Signup Agricultor Verify");

        EliminarUsuario_UtilityToken(utilitytoken_list);
    }

    @Scheduled(fixedRate = 60000)
    public void EliminarRestorePasswordUtilityToken() {
        Set<UtilityToken> utilitytoken_list = utilityTokenService.BuscarUtilityToken_By_ExpiracionAndRazon(LocalDateTime.now(),
                "Restore Password");

        for (UtilityToken restoretoken : utilitytoken_list) {
            utilityTokenService.EliminarUtilityToken(restoretoken.getIdUtilityToken());
        }
    }

    private void EliminarUsuario_UtilityToken(Set<UtilityToken> utilitytoken_list) {
        for (UtilityToken utilitytoken : utilitytoken_list) {
            Optional<Usuario> usuario_timedout =
                    usuarioService.BuscarUsuario_By_IDUtilityToken(utilitytoken.getIdUtilityToken());

            if (usuario_timedout.isPresent()) {
                Usuario usuario = usuario_timedout.get();

                usuarioService.EliminarUsuario(usuario.getIdUsuario());
            }
        }
    }
}



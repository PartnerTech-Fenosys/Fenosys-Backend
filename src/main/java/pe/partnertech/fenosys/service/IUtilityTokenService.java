/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.service;

import pe.partnertech.fenosys.model.UtilityToken;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public interface IUtilityTokenService {

    void GuardarUtilityToken(UtilityToken utilitytoken);

    Optional<UtilityToken> BuscarUtilityToken_By_Token(String token_utilitytoken);

    Set<UtilityToken> BuscarUtilityToken_By_ExpiracionAndRazon(LocalDateTime expiracion_utilitytoken,
                                                               String razon_utilitytoken);

    Set<UtilityToken> BuscarUtilityToken_By_IDUsuarioAndRazonUtilityToken(Long id_usuario, String razon_utilitytoken);

    void EliminarUtilityToken(Long id_utilitytoken);
}

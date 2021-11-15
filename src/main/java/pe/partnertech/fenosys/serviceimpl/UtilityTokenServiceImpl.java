/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.serviceimpl;

import org.springframework.stereotype.Service;
import pe.partnertech.fenosys.model.UtilityToken;
import pe.partnertech.fenosys.repository.IUtilityTokenDAO;
import pe.partnertech.fenosys.service.IUtilityTokenService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UtilityTokenServiceImpl implements IUtilityTokenService {

    final
    IUtilityTokenDAO data;

    public UtilityTokenServiceImpl(IUtilityTokenDAO data) {
        this.data = data;
    }

    @Override
    public void GuardarUtilityToken(UtilityToken utilitytoken) {
        data.save(utilitytoken);
    }

    @Override
    public Optional<UtilityToken> BuscarUtilityToken_By_Token(String token_utilitytoken) {
        return data.findByTokenUtilityToken(token_utilitytoken);
    }

    @Override
    public Set<UtilityToken> BuscarUtilityToken_By_ExpiracionAndRazon(LocalDateTime expiracion_utilitytoken, String razon_utilitytoken) {
        return data.findUtilityTokenByExpiracionAndRazon(expiracion_utilitytoken, razon_utilitytoken);
    }

    @Override
    public Set<UtilityToken> BuscarUtilityToken_By_IDUsuarioAndRazonUtilityToken(Long id_usuario, String razon_utilitytoken) {
        return data.findUtilityTokensByIdUsuarioAndRazon(id_usuario, razon_utilitytoken);
    }

    @Override
    public void EliminarUtilityToken(Long id_utilitytoken) {
        data.deleteById(id_utilitytoken);
    }
}

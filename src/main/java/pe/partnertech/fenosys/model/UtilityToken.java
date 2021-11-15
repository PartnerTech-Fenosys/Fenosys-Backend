/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "utilitytoken")
public class UtilityToken implements Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilitytoken")
    private Long idUtilityToken;

    @Column(name = "token_utilitytoken")
    private String tokenUtilityToken;

    @Column(name = "razon_utilitytoken", length = 35)
    private String razonUtilityToken;

    @Column(name = "expiracion_utilitytoken")
    private LocalDateTime expiracionUtilityToken;

    @ManyToOne
    @JoinTable(name = "usuario_utilitytokens",
            joinColumns = @JoinColumn(name = "id_utilitytoken", referencedColumnName = "id_utilitytoken"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"))
    private Usuario usuarioUtilityToken;

    //Constructores
    public UtilityToken() {
    }

    public UtilityToken(String tokenUtilityToken, String razonUtilityToken, LocalDateTime expiracionUtilityToken,
                        Usuario usuarioUtilityToken) {
        this.tokenUtilityToken = tokenUtilityToken;
        this.razonUtilityToken = razonUtilityToken;
        this.expiracionUtilityToken = expiracionUtilityToken;
        this.usuarioUtilityToken = usuarioUtilityToken;
    }

    //Getters y Setters
    public Long getIdUtilityToken() {
        return idUtilityToken;
    }

    public void setIdUtilityToken(Long idUtilityToken) {
        this.idUtilityToken = idUtilityToken;
    }

    public String getTokenUtilityToken() {
        return tokenUtilityToken;
    }

    public void setTokenUtilityToken(String tokenUtilityToken) {
        this.tokenUtilityToken = tokenUtilityToken;
    }

    public String getRazonUtilityToken() {
        return razonUtilityToken;
    }

    public void setRazonUtilityToken(String razonUtilityToken) {
        this.razonUtilityToken = razonUtilityToken;
    }

    public LocalDateTime getExpiracionUtilityToken() {
        return expiracionUtilityToken;
    }

    public void setExpiracionUtilityToken(LocalDateTime expiracionUtilityToken) {
        this.expiracionUtilityToken = expiracionUtilityToken;
    }

    public Usuario getUsuarioUtilityToken() {
        return usuarioUtilityToken;
    }

    public void setUsuarioUtilityToken(Usuario usuarioUtilityToken) {
        this.usuarioUtilityToken = usuarioUtilityToken;
    }
}

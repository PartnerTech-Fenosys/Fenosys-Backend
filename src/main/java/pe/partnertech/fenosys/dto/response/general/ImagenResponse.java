/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.dto.response.general;

public class ImagenResponse {

    //Atributos
    private String nombreImagen;
    private String urlImagen;

    //Constructores
    public ImagenResponse() {
    }

    public ImagenResponse(String nombreImagen, String urlImagen) {
        this.nombreImagen = nombreImagen;
        this.urlImagen = urlImagen;
    }

    //Getters y Setters
    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}

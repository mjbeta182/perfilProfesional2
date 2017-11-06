package com.tecontrato.modelo;

/**
 * Nombre de la clase: Nivel de Experiencia
 * Versión: 1.0
 * Fecha: 21/09/2017
 * CopyRight: Programmer Group Services S.A de C.V
 * @author Alexis, Kevin, Luis, María José, Geofredo
 */
public class NivelExperiencia {
    private int idNivelExperiencia;
    private String nombreNivelExperiencia;

    public NivelExperiencia() {
    }

    public NivelExperiencia(int idNivelExperiencia, String nombreNivelExperiencia) {
        this.idNivelExperiencia = idNivelExperiencia;
        this.nombreNivelExperiencia = nombreNivelExperiencia;
    }

    public NivelExperiencia(int idNivelExperiencia) {
        this.idNivelExperiencia = idNivelExperiencia;
    }
    

    public int getIdNivelExperiencia() {
        return idNivelExperiencia;
    }

    public void setIdNivelExperiencia(int idNivelExperiencia) {
        this.idNivelExperiencia = idNivelExperiencia;
    }

    public String getNombreNivelExperiencia() {
        return nombreNivelExperiencia;
    }

    public void setNombreNivelExperiencia(String nombreNivelExperiencia) {
        this.nombreNivelExperiencia = nombreNivelExperiencia;
    }
    
    
}

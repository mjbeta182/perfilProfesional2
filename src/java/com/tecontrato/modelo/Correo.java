package com.tecontrato.modelo;

/**
 * Nombre de la clase: Correo
 * Versión: 1.0
 * Fecha: 21/09/2017
 * CopyRight: Programmer Group Services S.A de C.V
 * @author Alexis, Kevin, Luis, María José, Geofredo
 */
public class Correo {
    private Candidato candidato;
    private String correo;

    public Correo() {
    }

    public Correo(Candidato candidato, String correo) {
        this.candidato = candidato;
        this.correo = correo;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
}

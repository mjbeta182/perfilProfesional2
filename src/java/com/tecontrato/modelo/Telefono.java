package com.tecontrato.modelo;

/**
 * Nombre de la clase: Teléfono
 * Versión: 1.0
 * Fecha: 21/09/2017
 * CopyRight: Programmer Group Services S.A de C.V
 * @author Alexis, Kevin, Luis, María José, Geofredo
 */
public class Telefono {
    private Candidato candidato;
    private String telefono;

    public Telefono() {
    }

    public Telefono(Candidato candidato, String telefono) {
        this.candidato = candidato;
        this.telefono = telefono;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
}

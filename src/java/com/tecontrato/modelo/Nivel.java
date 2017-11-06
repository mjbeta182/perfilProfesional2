package com.tecontrato.modelo;

/**
 * Nombre de la clase: Nivel
 * Versión: 1.0
 * Fecha: 21/09/2017
 * CopyRight: Programmer Group Services S.A de C.V
 * @author Alexis, Kevin, Luis, María José, Geofredo
 */
public class Nivel {
    private int idNivel;
    private String nivel;

    public Nivel() {
    }

    public Nivel(int idNivel, String nivel) {
        this.idNivel = idNivel;
        this.nivel = nivel;
    }

    public Nivel(int idNivel) {
        this.idNivel = idNivel;
    }
    

    public int getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    
}

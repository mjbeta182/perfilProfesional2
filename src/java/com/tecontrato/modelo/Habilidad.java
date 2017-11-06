
package com.tecontrato.modelo;

/**
 * Nombre de la clase: Habilidad
 * Versión: 1.0
 * Fecha: 21/09/2017
 * CopyRight: Programmer Group Services S.A de C.V
 * @author Alexis, Kevin, Luis, María José, Geofredo
 */
public class Habilidad {
    
    private int idHabilidad;
    private String nombreHabilidad;

    public Habilidad() {
    }

    public Habilidad(int idHabilidad, String nombreHabilidad) {
        this.idHabilidad = idHabilidad;
        this.nombreHabilidad = nombreHabilidad;
    }

    public Habilidad(int idHabilidad) {
        this.idHabilidad = idHabilidad;
    }
    

    public int getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(int idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public String getNombreHabilidad() {
        return nombreHabilidad;
    }

    public void setNombreHabilidad(String nombreHabilidad) {
        this.nombreHabilidad = nombreHabilidad;
    }
    
    
    
}

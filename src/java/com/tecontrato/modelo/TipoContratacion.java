package com.tecontrato.modelo;

/**
 * Nombre de la clase: Tipo de Contratación
 * Versión: 1.0
 * Fecha: 21/09/2017
 * CopyRight: Programmer Group Services S.A de C.V
 * @author Alexis, Kevin, Luis, María José, Geofredo
 */
public class TipoContratacion {
    private int idTipoContratacion;
    private String nombreContratacion;

    public TipoContratacion() {
    }

    public TipoContratacion(int idTipoContratacion, String nombreContratacion) {
        this.idTipoContratacion = idTipoContratacion;
        this.nombreContratacion = nombreContratacion;
    }

    public TipoContratacion(int idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }
    

    public int getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(int idTipoContratacion) {
        this.idTipoContratacion = idTipoContratacion;
    }

    public String getNombreContratacion() {
        return nombreContratacion;
    }

    public void setNombreContratacion(String nombreContratacion) {
        this.nombreContratacion = nombreContratacion;
    }
    
    
}

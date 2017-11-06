package com.tecontrato.modelo;

/**
 * Nombre de la clase: Cargo
 * Versión: 1.0
 * Fecha: 21/09/2017
 * CopyRight: Programmer Group Services S.A de C.V
 * @author Alexis, Kevin, Luis, María José, Geofredo
 */
public class Cargo {
    private int idCargo;
    private String nombreCargo;

    public Cargo() {
    }

    public Cargo(int idCargo, String nombreCargo) {
        this.idCargo = idCargo;
        this.nombreCargo = nombreCargo;
    }

    public Cargo(int idCargo) {
        this.idCargo = idCargo;
    }
    

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }
    
    
}

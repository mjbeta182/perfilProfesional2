package com.tecontrato.modelo;

/**
 * Nombre de la clase: CV Cargo
 * Versión: 1.0
 * Fecha: 21/09/2017
 * CopyRight: Programmer Group Services S.A de C.V
 * @author Alexis, Kevin, Luis, María José, Geofredo
 */
public class CvCargo {
    private Curriculum curriculum;
    private Cargo cargo;

    public CvCargo() {
    }

    public CvCargo(Curriculum curriculum, Cargo cargo) {
        this.curriculum = curriculum;
        this.cargo = cargo;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    
}

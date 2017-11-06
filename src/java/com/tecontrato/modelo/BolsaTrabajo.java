package com.tecontrato.modelo;

/**
 * Nombre de la clase: Bolsa de Trabajo
 * Versión: 1.0
 * Fecha: 21/09/2017
 * CopyRight: Programmer Group Services S.A de C.V
 * @author Alexis, Kevin, Luis, María José, Geofredo
 */
public class BolsaTrabajo {
    private Empresa empresa;
    private Curriculum curriculum;

    public BolsaTrabajo() {
    }

    public BolsaTrabajo(Empresa empresa, Curriculum curriculum) {
        this.empresa = empresa;
        this.curriculum = curriculum;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }
    
    
}

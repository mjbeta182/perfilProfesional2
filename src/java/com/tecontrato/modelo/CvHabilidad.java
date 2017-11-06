package com.tecontrato.modelo;

/**
 * Nombre de la clase: CV Habilidad
 * Versión: 1.0
 * Fecha: 21/09/2017
 * CopyRight: Programmer Group Services S.A de C.V
 * @author Alexis, Kevin, Luis, María José, Geofredo
 */
public class CvHabilidad {
    private Curriculum curriculum;
    private Habilidad habilidad;
    private int valoracion;
    private Nivel nivel;

    public CvHabilidad() {
    }

    public CvHabilidad(Curriculum curriculum, Habilidad habilidad, int valoracion, Nivel nivel) {
        this.curriculum = curriculum;
        this.habilidad = habilidad;
        this.valoracion = valoracion;
        this.nivel = nivel;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
    
    
}

package com.tecontrato.modelo;

/**
 * Nombre de la clase: Educación
 * Versión: 1.0
 * Fecha: 21/09/2017
 * CopyRight: Programmer Group Services S.A de C.V
 * @author Alexis, Kevin, Luis, María José, Geofredo
 */
public class Educacion {
    private Curriculum curriculum;
    private String institucion;
    private String especialidad;
    private int anioFinalizacion;

    public Educacion() {
    }

    public Educacion(Curriculum curriculum, String institucion, String especialidad, int anioFinalizacion) {
        this.curriculum = curriculum;
        this.institucion = institucion;
        this.especialidad = especialidad;
        this.anioFinalizacion = anioFinalizacion;
    }
 

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getAnioFinalizacion() {
        return anioFinalizacion;
    }

    public void setAnioFinalizacion(int anioFinalizacion) {
        this.anioFinalizacion = anioFinalizacion;
    }
    
    
}

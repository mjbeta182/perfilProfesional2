package com.tecontrato.modelo;

/**
 * Nombre de la clase: Curso
 * Versión: 1.0
 * Fecha: 21/09/2017
 * CopyRight: Programmer Group Services S.A de C.V
 * @author Alexis, Kevin, Luis, María José, Geofredo
 */
public class Curso {
    private String titulo;
    private String institucion;
    private int anioFinalizacion;
    private Curriculum curriculum;

    public Curso() {
    }

    public Curso(String titulo, String institucion, int anioFinalizacion, Curriculum curriculum) {
        this.titulo = titulo;
        this.institucion = institucion;
        this.anioFinalizacion = anioFinalizacion;
        this.curriculum = curriculum;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public int getAnioFinalizacion() {
        return anioFinalizacion;
    }

    public void setAnioFinalizacion(int anioFinalizacion) {
        this.anioFinalizacion = anioFinalizacion;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }
    
    
}

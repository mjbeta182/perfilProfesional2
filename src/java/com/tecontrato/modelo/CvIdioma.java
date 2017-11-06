package com.tecontrato.modelo;

/**
 * Nombre de la clase: CV Idioma
 * Versión: 1.0
 * Fecha: 21/09/2017
 * CopyRight: Programmer Group Services S.A de C.V
 * @author Alexis, Kevin, Luis, María José, Geofredo
 */
public class CvIdioma {
    private Curriculum curriculum;
    private Idioma idioma;
    private Nivel nivel;

    public CvIdioma() {
    }

    public CvIdioma(Curriculum curriculum, Idioma idioma, Nivel nivel) {
        this.curriculum = curriculum;
        this.idioma = idioma;
        this.nivel = nivel;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
    
    
}

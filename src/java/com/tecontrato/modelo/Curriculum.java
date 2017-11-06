package com.tecontrato.modelo;

/**
 * Nombre de la clase: Curriculum
 * Versión: 1.0
 * Fecha: 21/09/2017
 * CopyRight: Programmer Group Services S.A de C.V
 * @author Alexis, Kevin, Luis, María José, Geofredo
 */
public class Curriculum {
    private int idCurriculum;
    private Candidato candidato;
    private NivelExperiencia nivelExperiencia;

    public Curriculum() {
    }

    public Curriculum(int idCurriculum, Candidato candidato, NivelExperiencia nivelExperiencia) {
        this.idCurriculum = idCurriculum;
        this.candidato = candidato;
        this.nivelExperiencia = nivelExperiencia;
    }

    public Curriculum(int idCurriculum) {
        this.idCurriculum = idCurriculum;
    }
    

    public int getIdCurriculum() {
        return idCurriculum;
    }

    public void setIdCurriculum(int idCurriculum) {
        this.idCurriculum = idCurriculum;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public NivelExperiencia getNivelExperiencia() {
        return nivelExperiencia;
    }

    public void setNivelExperiencia(NivelExperiencia nivelExperiencia) {
        this.nivelExperiencia = nivelExperiencia;
    }
    
    
}

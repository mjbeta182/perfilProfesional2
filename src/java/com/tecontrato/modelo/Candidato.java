package com.tecontrato.modelo;


/**
 * Nombre de la clase: Candidato
 * Versión: 1.0
 * Fecha: 21/09/2017
 * CopyRight: Programmer Group Services S.A de C.V
 * @author Alexis, Kevin, Luis, María José, Geofredo
 */

public class Candidato {
    private int idCandidato;
    private Departamento depto;
    private Genero genero;
    private String nombre;
    private String nacionalidad;
    private String fechaNacimiento;
    private String direccion;
    private String foto;
    private String profesion;
    
    public Candidato() {
    }

    public Candidato(int idCandidato, Departamento depto, Genero genero, String nombre, String nacionalidad, String fechaNacimiento, String direccion, String profesion) {
        this.idCandidato = idCandidato;
        this.depto = depto;
        this.genero = genero;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.profesion = profesion;
    }

    public Candidato(int idCandidato, String foto) {
        this.idCandidato = idCandidato;
        this.foto = foto;
    }

    public Candidato(int idCandidato, Departamento depto, Genero genero, String nombre, String nacionalidad, String fechaNacimiento, String direccion, String foto, String profesion) {
        this.idCandidato = idCandidato;
        this.depto = depto;
        this.genero = genero;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.foto = foto;
        this.profesion = profesion;
    }

    
    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
    
    public Candidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }
    

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public Departamento getDepto() {
        return depto;
    }

    public void setDepto(Departamento depto) {
        this.depto = depto;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    
}

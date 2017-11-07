
package com.tecontrato.modelo;

/**
 * Nombre de la clase: Empresa
 * Versión: 1.0
 * Fecha: 21/09/2017
 * CopyRight: Programmer Group Services S.A de C.V
 * @author Alexis, Kevin, Luis, María José, Geofredo
 */
public class Empresa {
    
    private int idEmpresa;
    private String actividad;
    private String descripcion;
    private String email;
    private String telefono;
    private String nombreEmpresa;
    private String foto;

    public Empresa() {
    }

    public Empresa(int idEmpresa, String actividad, String descripcion, String email, String telefono, String nombreEmpresa, String foto) {
        this.idEmpresa = idEmpresa;
        this.actividad = actividad;
        this.descripcion = descripcion;
        this.email = email;
        this.telefono = telefono;
        this.nombreEmpresa = nombreEmpresa;
        this.foto = foto;
    }

    public Empresa(int idEmpresa, String actividad, String descripcion, String email, String telefono, String nombreEmpresa) {
        this.idEmpresa = idEmpresa;
        this.actividad = actividad;
        this.descripcion = descripcion;
        this.email = email;
        this.telefono = telefono;
        this.nombreEmpresa = nombreEmpresa;
    }

    public Empresa(int idEmpresa, String foto) {
        this.idEmpresa = idEmpresa;
        this.foto = foto;
    }

    public Empresa(String foto) {
        this.foto = foto;
    }

    

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    
    public Empresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    
    
    
}

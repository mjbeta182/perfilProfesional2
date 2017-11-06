package com.tecontrato.modelo;

/**
 * Nombre de la clase:Usuario
 * Versión: 1.0
 * Fecha: 21/09/2017
 * CopyRight: Programmer Group Services S.A de C.V
 * @author Alexis, Kevin, Luis, María José, Geofredo
 */
public class Usuario {
    private int idUsuario;
    private Rol rol;
    private String usuario;
    private String clave;

    public Usuario() {
    }

    public Usuario(int idUsuario, Rol rol, String usuario, String clave) {
        this.idUsuario = idUsuario;
        this.rol = rol;
        this.usuario = usuario;
        this.clave = clave;
    }

    public Usuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

       
    
}

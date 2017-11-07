package com.tecontrato.modelo;

/**
 *
 * @author lguil
 */
public class Empleado {
     private int idEmpleado;
    private String nombreEmpleado;
    private String direccion;
    private String telefono;
    private String fechaNacimiento;
    private String foto;

    public Empleado() {
    }

    public Empleado(int idEmpleado, String nombreEmpleado, String direccion, String telefono, String fechaNacimiento, String foto) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.foto = foto;
    }

    public Empleado(int idEmpleado, String foto) {
        this.idEmpleado = idEmpleado;
        this.foto = foto;
    }

    public Empleado(int idEmpleado, String nombreEmpleado, String direccion, String telefono, String fechaNacimiento) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}


package com.tecontrato.modelo;

/**
 *
 * @author Alexis
 */
public class Valoraciones {
    private int iddetalle;
    private int valoracion;
    private int promedio;
    private String comentario;

    public Valoraciones() {
    }

    public Valoraciones(int iddetalle, int valoracion, int promedio, String comentario) {
        this.iddetalle = iddetalle;
        this.valoracion = valoracion;
        this.promedio = promedio;
        this.comentario = comentario;
    }

    public int getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public int getPromedio() {
        return promedio;
    }

    public void setPromedio(int promedio) {
        this.promedio = promedio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
}

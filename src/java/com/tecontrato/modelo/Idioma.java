
package com.tecontrato.modelo;

/**
 * Nombre de la clase: Idioma
 * Versión: 1.0
 * Fecha: 21/09/2017
 * CopyRight: Programmer Group Services S.A de C.V
 * @author Alexis, Kevin, Luis, María José, Geofredo
 */
public class Idioma {
    
    private int idIdioma;
    private String nombreIdioma;

    public Idioma() {
    }

    public Idioma(int idIdioma, String nombreIdioma) {
        this.idIdioma = idIdioma;
        this.nombreIdioma = nombreIdioma;
    }

    public Idioma(int idIdioma) {
        this.idIdioma = idIdioma;
    }
    

    public int getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(int idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getNombreIdioma() {
        return nombreIdioma;
    }

    public void setNombreIdioma(String nombreIdioma) {
        this.nombreIdioma = nombreIdioma;
    }

    
    
    
    
}

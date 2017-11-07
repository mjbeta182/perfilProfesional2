/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecontrato.beans;

import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;

/**
 *
 * @author Kevin López
 */
@Named(value = "candidatoBeans")
@ManagedBean
@ViewScoped
public class CandidatoBeans implements Serializable{
    
    public CandidatoBeans() {
    }

        
    public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        //Instancia hacia la clase reporteClientes        
        reporte reporte = new reporte();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/rptCandidato.jasper");
       
        reporte.getReporte(ruta);        
        FacesContext.getCurrentInstance().responseComplete();            
    }
    
    
}

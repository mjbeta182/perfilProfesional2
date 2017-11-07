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
@Named(value = "curriculumBeans")
@ManagedBean
@ViewScoped
public class CurriculumBeans implements Serializable{
    
    public CurriculumBeans() {
    }
    
    public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        //Instancia hacia la clase reporteClientes        
        reporte reporte = new reporte();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/rptCurriculum.jasper");
       
        reporte.getReporte(ruta);        
        FacesContext.getCurrentInstance().responseComplete();            
    }
}

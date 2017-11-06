
package com.tecontrato.controlador;

import com.tecontrato.modelo.Area;
import com.tecontrato.modelo.Cargo;
import com.tecontrato.modelo.CrudOferta;
import com.tecontrato.modelo.Departamento;
import com.tecontrato.modelo.Empresa;
import com.tecontrato.modelo.NivelExperiencia;
import com.tecontrato.modelo.Oferta;
import com.tecontrato.modelo.TipoContratacion;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Nombre      : ProcesarCargo
 * Versión     : 1.0
 * Fecha       : 06/10/2017
 * CopyRight   : Programmer Group Services S.A de C.V
 * @author     : Alexis, Kevin, Luis, María José, Geofredo
 */
public class ProcesarOferta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Oferta ofe = new Oferta();
        CrudOferta crof = new CrudOferta();
        String respuesta = null;
        
        try {
            
            //ofe.setIdOferta(Integer.parseInt(request.getParameter("txtIdOferta")));
            
            if(request.getParameter("btnGuardar")!=null)
            {
                
                ofe.setArea(new Area(Integer.parseInt(request.getParameter("cmbArea"))));
                ofe.setCargo(new Cargo(Integer.parseInt(request.getParameter("cmbCargo"))));
                ofe.setNivelExperiencia(new NivelExperiencia(Integer.parseInt(request.getParameter("cmbNivelExperiencia"))));
                ofe.setTipoContratacion(new TipoContratacion(Integer.parseInt(request.getParameter("cmbTipoContratacion"))));
                ofe.setDepartamento(new Departamento(Integer.parseInt(request.getParameter("cmbDepartamento"))));
                ofe.setEmpresa(new Empresa(Integer.parseInt(request.getParameter("cmbEmpresa"))));
                ofe.setNombre(request.getParameter("txtNombreOferta"));
                ofe.setVacantes(Integer.parseInt(request.getParameter("txtVacantes")));
                ofe.setDescripcion(request.getParameter("txtDescripcion"));
                ofe.setEdadMin(Integer.parseInt(request.getParameter("txtEdadMin")));
                ofe.setEdadMax(Integer.parseInt(request.getParameter("txtEdadMax")));

                crof.insertarOferta(ofe);
                respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro ingresado de forma exitosa.</div>";
            }else if(request.getParameter("btnModificar")!=null)
            {
                
                ofe.setIdOferta(Integer.parseInt(request.getParameter("txtIdOferta")));
                ofe.setArea(new Area(Integer.parseInt(request.getParameter("cmbArea"))));
                ofe.setCargo(new Cargo(Integer.parseInt(request.getParameter("cmbCargo"))));
                ofe.setNivelExperiencia(new NivelExperiencia(Integer.parseInt(request.getParameter("cmbNivelExperiencia"))));
                ofe.setTipoContratacion(new TipoContratacion(Integer.parseInt(request.getParameter("cmbTipoContratacion"))));
                ofe.setDepartamento(new Departamento(Integer.parseInt(request.getParameter("cmbDepartamento"))));
                ofe.setEmpresa(new Empresa(Integer.parseInt(request.getParameter("cmbEmpresa"))));
                ofe.setNombre(request.getParameter("txtNombreOferta"));
                ofe.setVacantes(Integer.parseInt(request.getParameter("txtVacantes")));
                ofe.setDescripcion(request.getParameter("txtDescripcion"));
                ofe.setEdadMin(Integer.parseInt(request.getParameter("txtEdadMin")));
                ofe.setEdadMax(Integer.parseInt(request.getParameter("txtEdadMax")));
                
                crof.modificarOferta(ofe);
                respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro modificado de forma exitosa.</div>";
            }else if(request.getParameter("btnEliminar")!=null)
            {
                ofe.setIdOferta(Integer.parseInt(request.getParameter("txtIdOferta")));
                crof.eliminarOferta(ofe);
                respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro eliminado de forma exitosa.</div>";
            }
            
            request.setAttribute("respuesta", respuesta);
            response.sendRedirect("ofertasempresa.jsp");
     } catch (Exception e) {
         request.setAttribute("ERROR", e.toString());
     }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

package com.tecontrato.controlador;

import com.tecontrato.modelo.CrudCvIdioma;
import com.tecontrato.modelo.Curriculum;
import com.tecontrato.modelo.CvIdioma;
import com.tecontrato.modelo.Idioma;
import com.tecontrato.modelo.Nivel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Nombre      : ProcesarCvIdioma
 * Versión     : 1.0
 * Fecha       : 07/10/2017
 * CopyRight   : Programmer Group Services S.A de C.V
 * @author     : Alexis, Kevin, Luis, María José, Geofredo
 */
public class ProcesarCvIdioma extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        CvIdioma cvi = new CvIdioma();
        CrudCvIdioma ccvi = new CrudCvIdioma();
        String respuesta = null;
        
        try {
            
            cvi.setCurriculum(new Curriculum(Integer.parseInt(request.getParameter("txtIdCurriculum"))));
            cvi.setIdioma(new Idioma(Integer.parseInt(request.getParameter("cmbIdioma"))));
            cvi.setNivel(new Nivel(Integer.parseInt(request.getParameter("nivelIdioma"))));
            
            if(request.getParameter("btnGuardar")!=null)
            {
                ccvi.insertarCvIdioma(cvi);
                respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro ingresado de forma exitosa.</div>";
            
            }else if(request.getParameter("btnModificar")!=null)
            {
                ccvi.modificarCvIdioma(cvi);
                respuesta="<div class='alert alert-success alert-dismissable alerta'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro modificado de forma exitosa.</div>";
            
            }else if(request.getParameter("btnEliminar")!=null)
            {
                ccvi.eliminarCvIdioma(cvi);
                respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro eliminado de forma exitosa.</div>";
            }
            
            request.setAttribute("respuesta", respuesta);
            request.getRequestDispatcher("perfil.jsp").forward(request, response);
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

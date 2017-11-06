package com.tecontrato.controlador;

import com.tecontrato.modelo.CrudHabilidad;
import com.tecontrato.modelo.Habilidad;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Nombre      : ProcesarHabilidad
 * Versión     : 2.0
 * Fecha       : 21/09/2017
 * CopyRight   : Programmer Group Services S.A de C.V
 * @author     : Alexis, Kevin, Luis, María José, Geofredo
 */

public class ProcesarHabilidad extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();   
        Habilidad hb = new Habilidad();
        CrudHabilidad cha = new CrudHabilidad();
        String respuesta = null;
        try{    
                if(request.getParameter("btnGuardar") != null){
                    //Solo se insertan en el objeto (hb) los atributos que se piden en el crud
                    hb.setNombreHabilidad(request.getParameter("nombrehabilidad"));
                    cha.insertarHabilidad(hb);
                    respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro ingresado de forma exitosa.</div>";
                }else{
                    //Aqui se guarda nombre y id en el objeto(hb) porque son los 
                    //atributos que se utilizan para modificar y eliminar en el crud
                    hb.setIdHabilidad(Integer.parseInt(request.getParameter("idhabilidad")));
                    hb.setNombreHabilidad(request.getParameter("nombrehabilidad"));
                    if (request.getParameter("btnModificar") != null){  
                        cha.modificarHabilidad(hb);;
                        respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro modificado de forma exitosa.</div>";
                    }else if(request.getParameter("btnEliminar") != null){
                        cha.eliminarHabilidad(hb);
                        respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro eliminado de forma exitosa.</div>";
                    }
                }       
                request.setAttribute("respuesta", respuesta);
                response.sendRedirect("habilidad.jsp");
            }catch(Exception e) {
                request.setAttribute("error", e.toString());
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

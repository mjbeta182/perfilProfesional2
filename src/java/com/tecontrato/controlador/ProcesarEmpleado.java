/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecontrato.controlador;

import com.tecontrato.modelo.CrudEmpleado;
import com.tecontrato.modelo.Empleado;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lguil
 */
public class ProcesarEmpleado extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            
        Empleado emp = new Empleado();
        CrudEmpleado cemp = new CrudEmpleado();
        String respuesta = null;
        try {
            emp.setIdEmpleado(Integer.parseInt(request.getParameter("txtIdEmpleado")));
            emp.setNombreEmpleado(request.getParameter("txtNombreEmpleado"));
            emp.setDireccion(request.getParameter("txtDireccion"));
            emp.setTelefono(request.getParameter("txtTelefono"));
            emp.setFechaNacimiento(request.getParameter("txtFechaNacimiento"));
            emp.setFoto(request.getParameter("foto"));
            
            String txtIdRol = request.getParameter("txtIdRol");
            HttpSession sessi = request.getSession(); 
            sessi.setAttribute("txtIdRol", txtIdRol);
            
            if(request.getParameter("btnGuardar")!=null){
                emp.setIdEmpleado(Integer.parseInt(request.getParameter("txtIdEmpleado")));
                cemp.insertarEmpleado(emp);
                respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro ingresado de forma exitosa.</div>";
            }else if(request.getParameter("btnModificar")!=null){
                emp.setIdEmpleado(Integer.parseInt(request.getParameter("txtIdEmpleado")));
                cemp.modificarEmpleado(emp);
                respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro modificado de forma exitosa.</div>";
            } else if(request.getParameter("btnEliminar")!=null){
                emp.setIdEmpleado(Integer.parseInt(request.getParameter("txtIdEmpleado")));
                cemp.eliminarEmpleado(emp);
                respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro eliminado de forma exitosa.</div>";
            }
            request.setAttribute("respuesta", respuesta);
            
            if(txtIdRol.equals("1")){
                request.getRequestDispatcher("empleadoAdmin.jsp").forward(request, response);
            }
            
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

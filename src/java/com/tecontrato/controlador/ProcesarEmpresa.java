
package com.tecontrato.controlador;

import com.tecontrato.modelo.CrudEmpresa;
import com.tecontrato.modelo.Empresa;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexis
 */
public class ProcesarEmpresa extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Empresa emp = new Empresa();
        CrudEmpresa crem = new CrudEmpresa();
        String respuesta = null;
        
        try {
            emp.setIdEmpresa(Integer.parseInt(request.getParameter("txtIdEmpresa")));
            emp.setActividad(request.getParameter("txtActividad"));
            emp.setDescripcion(request.getParameter("txtDescripcion"));
            emp.setEmail(request.getParameter("txtEmail"));
            emp.setTelefono(request.getParameter("txtTelefono"));
            emp.setNombreEmpresa(request.getParameter("txtNombreEmpresa"));
            
            String txtIdRol = request.getParameter("txtIdRol");
            HttpSession sess = request.getSession(); 
            sess.setAttribute("txtIdRol", txtIdRol);
            
            if(request.getParameter("btnGuardar")!=null)
            {
                emp.setIdEmpresa(Integer.parseInt(request.getParameter("txtIdEmpresa")));
                crem.insertarEmpresa(emp);
                respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro ingresado de forma exitosa.</div>";
            }else if(request.getParameter("btnModificar")!=null)
            {
                emp.setIdEmpresa(Integer.parseInt(request.getParameter("txtIdEmpresa")));
                crem.modificarEmpresa(emp);
                respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro modificado de forma exitosa.</div>";
            }else if(request.getParameter("btnEliminar")!=null)
            {
                emp.setIdEmpresa(Integer.parseInt(request.getParameter("txtIdEmpresa")));
                crem.eliminarEmpresa(emp);
                respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro eliminado de forma exitosa.</div>";
            }
            
            request.setAttribute("respuesta",respuesta);
            
            if(txtIdRol.equals("2"))
            {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            else if(txtIdRol.equals("1")){

                request.getRequestDispatcher("empresaAdmin.jsp").forward(request, response);
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

package com.tecontrato.controlador;

import com.tecontrato.modelo.CrudUsuario;
import com.tecontrato.modelo.Rol;
import com.tecontrato.modelo.Usuario;
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
public class ProcesarUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Usuario usu = new Usuario();
        CrudUsuario crudu = new CrudUsuario();
        String respuesta = null;
        
        
        try {
            
            usu.setRol(new Rol(Integer.parseInt(request.getParameter("txtIdRol"))));
            usu.setUsuario(request.getParameter("txtUsuario"));
            usu.setClave(request.getParameter("txtClave"));
            
            String txtIdUsuario = request.getParameter("txtIdUsuario");
            String txtIdRol = request.getParameter("txtIdRol");
            HttpSession sess = request.getSession(); 
            sess.setAttribute("txtIdUsuario", txtIdUsuario);
            
            if(request.getParameter("btnGuardar")!=null)
            {
                crudu.insertarUsuario(usu);
                respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro ingresado de forma exitosa.</div>";
            }else if(request.getParameter("btnModificar")!=null)
            {
                usu.setIdUsuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
                crudu.modificarUsuario(usu);
                respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro Modificado de forma exitosa.</div>";
            }else if(request.getParameter("btnEliminar")!=null)
            {
                usu.setIdUsuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
                crudu.eliminarUsuario(usu);
                respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro Eliminar de forma exitosa.</div>";
            }
            
            request.setAttribute("respuesta", respuesta);
            if(txtIdRol.equals("2")){
                request.getRequestDispatcher("empresa.jsp").forward(request, response);
            }
            else if(txtIdRol.equals("3")){
                
                request.getRequestDispatcher("candidato.jsp").forward(request, response);
                
            }else if(txtIdRol.equals("1")){
                request.getRequestDispatcher("empleado.jsp").forward(request, response);
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
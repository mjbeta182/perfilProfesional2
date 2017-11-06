package com.tecontrato.controlador;

import com.tecontrato.modelo.CrudCurriculum;
import com.tecontrato.modelo.CrudUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maria Jose
 */
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
          PrintWriter out = response.getWriter();
        String usuario  = request.getParameter("user");
        String password = request.getParameter("pass");
        CrudUsuario cu  = new CrudUsuario();
       
        int rol=0;
       
        
            if(cu.autenticarUsuario(usuario,password)==1){  
                HttpSession session = request.getSession(true); // reuse existing												// or create one
                session.setAttribute("user", usuario);
                rol=1;
                request.getSession().setAttribute("valor", rol);
                response.sendRedirect("index.jsp");
            }else if (cu.autenticarUsuario(usuario,password)==2){
                HttpSession session = request.getSession(true); // reuse existing												// or create one
                session.setAttribute("user", usuario);
                rol=2;
                request.getSession().setAttribute("valor", rol);
                response.sendRedirect("index.jsp");
            }else if (cu.autenticarUsuario(usuario,password)==3){
                HttpSession session = request.getSession(true); // reuse existing												// or create one
                session.setAttribute("user", usuario);
                rol=3;
                request.getSession().setAttribute("valor", rol);
                response.sendRedirect("index.jsp");
            }else {
                String message = "<div class='alert alert-danger alert-dismissable fade in'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color:#C0392B;'>Ha ocurrido un error!</strong> El nombre de usuario o contraseña son incorrectos.</div>";
                request.setAttribute("message", message); // This will be available as ${message}
                request.getRequestDispatcher("login.jsp").forward(request, response);   
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

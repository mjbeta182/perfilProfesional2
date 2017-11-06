/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecontrato.controlador;

import com.tecontrato.modelo.CrudOferta;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Maria Jose
 */
public class ProcesarSeguirUsuario extends HttpServlet {

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
        CrudOferta crof = new CrudOferta();       
          
         try {
           if(request.getParameter("txtBuscar")!=null)
            {
                String criterio = request.getParameter("txtBuscar");
                request.getSession().setAttribute("criterio", criterio);
                crof.seguirCriterio(criterio);
                response.sendRedirect("seguir.jsp");
            }else if(request.getParameter("btnDetalle")!=null)
            {
               
                String codigo = request.getParameter("codCandidato");
                request.getSession().setAttribute("codigo", codigo);
                response.sendRedirect("seguirusuario.jsp");
            }else if(request.getParameter("btnNewIn")!=null)
            {
                String idseguir = request.getParameter("txtIdCandidato");
                String idusuario = request.getParameter("txtIdUsuario");
                request.getSession().setAttribute("codigo", idseguir);//Posiblemente de error por estar repetida la variable global
                request.getSession().setAttribute("idusuario", idusuario);
                crof.insertarSeguir(idseguir,idusuario);
                response.sendRedirect("seguirusuario.jsp");
                
            }else if(request.getParameter("btnCanIn")!=null)
            {
                String idseguir = request.getParameter("txtIdCandidato");
                String idusuario = request.getParameter("txtIdUsuario");
                request.getSession().setAttribute("codigo", idseguir);//Posiblemente de error por estar repetida la variable global
                request.getSession().setAttribute("idusuario", idusuario);
                crof.eliminarSeguir(idseguir, idusuario);
                response.sendRedirect("seguirusuario.jsp");
            }else if(request.getParameter("btnCandidatos")!=null)
            {
                response.sendRedirect("ofertaaplicantes.jsp");
            } 
        } catch (Exception e) {
            out.println(e);
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

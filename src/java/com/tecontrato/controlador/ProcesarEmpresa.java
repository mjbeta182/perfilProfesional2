
package com.tecontrato.controlador;

import com.tecontrato.modelo.CrudEmpresa;
import com.tecontrato.modelo.Empresa;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Nombre      : ProcesarEmpresa
 * Versión     : 1.0
 * Fecha       : 07/10/2017
 * CopyRight   : Programmer Group Services S.A de C.V
 * @author     : Alexis, Kevin, Luis, María José, Geofredo
 */

public class ProcesarEmpresa extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Empresa emp = new Empresa();
        CrudEmpresa crem = new CrudEmpresa();
        String respuesta = null;
        String txtIdRol = request.getParameter("txtIdRol");
        HttpSession sess = request.getSession(); 
        sess.setAttribute("txtIdRol", txtIdRol);
        int resultado = 0;

            
            if(request.getParameter("btnGuardar")!=null)
            {
                try {
                        emp.setIdEmpresa(Integer.parseInt(request.getParameter("txtIdEmpresa")));
                        emp.setActividad(request.getParameter("txtActividad"));
                        emp.setDescripcion(request.getParameter("txtDescripcion"));
                        emp.setEmail(request.getParameter("txtEmail"));
                        emp.setTelefono(request.getParameter("txtTelefono"));
                        emp.setNombreEmpresa(request.getParameter("txtNombreEmpresa"));
                        
                        resultado = crem.insertarEmpresa(emp);
                        if(resultado==1){
                            respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong>Registro ingresado correctamente.</div>";
                        }else{
                            respuesta="<div class='alert alert-danger alert-dismissable alerta'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Oops!</strong> Ocurrió un error.</div>";
                        }
                        
                } catch (Exception e) {
                    out.println(e);
                }
            }
                
        //Proceso y validacion para boton modificar       
        if(request.getParameter("btnModificar")!=null){
            try {
                    emp.setIdEmpresa(Integer.parseInt(request.getParameter("txtIdEmpresa")));
                    emp.setActividad(request.getParameter("txtActividad"));
                    emp.setDescripcion(request.getParameter("txtDescripcion"));
                    emp.setEmail(request.getParameter("txtEmail"));
                    emp.setTelefono(request.getParameter("txtTelefono"));
                    emp.setNombreEmpresa(request.getParameter("txtNombreEmpresa"));
                        
                    resultado = crem.modificarEmpresa(emp);
                    if(resultado==1){
                        respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong>Registro ingresado correctamente.</div>";
                    }else{
                        respuesta="<div class='alert alert-danger alert-dismissable alerta'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Oops!</strong> Ocurrió un error.</div>";
                    } 
                } catch (Exception e) {
                    out.println(e);
                }
        }
        
        //Proceso y validacion para boton modificar foto
        if(request.getParameter("btnModFoto")!=null){

            try {
                    emp.setIdEmpresa(Integer.parseInt(request.getParameter("txtIdEmp")));

                    if (!request.getParameter("txtarchivo").equals("")) 
                    {    
                        String nombreImagen = request.getParameter("txtarchivo");  //Recupero nombre de la imagen del input file
                        emp.setFoto(nombreImagen);
                        Part archivo = request.getPart("foto");

                        if (nombreImagen.endsWith(".jpg") || nombreImagen.endsWith(".png")) 
                        {
                            Integer posicion = 0;
                            String direccion2 = request.getSession().getServletContext().getRealPath("imagenes");
                            posicion = direccion2.indexOf("\\build\\web");
                            direccion2 = direccion2.substring(0,posicion);
                            String direccion = direccion2+"/web/imagenes/fotos";
                            File imagenVieja = new File(direccion + "/" + request.getParameter("modViejaBandera"));

                            resultado = crem.modificarFoto(emp);
                            if (resultado == 1) 
                            {
                                respuesta="<div class='alert alert-success alert-dismissable alerta'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro modificado de forma exitosa.</div>";
                                posicion = 0;
                                direccion2 = request.getSession().getServletContext().getRealPath("imagenes");
                                posicion = direccion2.indexOf("\\build\\web");
                                direccion2 = direccion2.substring(0,posicion);
                                direccion = direccion2+"/web/imagenes/fotos";
                                InputStream entradaImagen = archivo.getInputStream();
                                File file = new File(direccion + "/" + nombreImagen);
                                FileOutputStream salidaImagen = new FileOutputStream(file);
                                int dato = entradaImagen.read();
                                while (dato != -1) 
                                {                
                                     salidaImagen.write(dato);
                                     dato = entradaImagen.read();
                                }
                                entradaImagen.close();
                                salidaImagen.close();
                            }else{
                                respuesta="<div class='alert alert-danger alert-dismissable alerta'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Oops!</strong> Ocurrió un error.</div>";
                            }
                        }else{
                            respuesta="<div class='alert alert-danger alert-dismissable alerta'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Oops!</strong> Ocurrió un error .</div>";
                        }

                    }else{
                        emp.setFoto("userdefault.png");
                         resultado = crem.modificarFoto(emp);
                        if (resultado == 1) 
                        {
                            respuesta="<div class='alert alert-success alert-dismissable alerta'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro modificado de forma exitosa.</div>";

                        }else{

                            respuesta="<div class='alert alert-danger alert-dismissable alerta'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Oops!</strong> Ocurrió un error.</div>";
                        }
                    }
            } catch (Exception e) {
                out.println(e);
            }
        }  
        
        //Proceso y validacion para boton eliminar 
        if(request.getParameter("btnEliminar")!=null)   
            try {
                emp.setIdEmpresa(Integer.parseInt(request.getParameter("txtIdEmpresa")));
                resultado = crem.eliminarEmpresa(emp);
                if (resultado == 1) 
                {
                    respuesta="<div class='alert alert-success alert-dismissable alerta'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro modificado de forma exitosa.</div>";

                }else{

                    respuesta="<div class='alert alert-danger alert-dismissable alerta'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Oops!</strong> Ocurrió un error.</div>";
                }
            } catch (Exception e) {
                 out.println(e);
            }
          
            if(request.getParameter("btnEliminarFoto")!=null){
                try {
                   emp.setIdEmpresa(Integer.parseInt(request.getParameter("txtIdEmp")));
                   emp.setFoto("userdefault.png");
                   resultado = crem.modificarFoto(emp);
                    if(resultado==1){
                       respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro eliminado de forma exitosa.</div>";
                    }else{
                       respuesta="<div class='alert alert-danger alert-dismissable alerta'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Oops!</strong> Ocurrió un error.</div>";
                    }
                } catch (Exception e) {
                    out.println(e);
                }
             }
            
            
            request.setAttribute("respuesta",respuesta);
            
            if(txtIdRol.equals("2"))
            {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            else if(txtIdRol.equals("1")){

                request.getRequestDispatcher("empresaAdmin.jsp").forward(request, response);
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
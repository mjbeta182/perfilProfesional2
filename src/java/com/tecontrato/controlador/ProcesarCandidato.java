package com.tecontrato.controlador;

import com.tecontrato.modelo.Candidato;
import com.tecontrato.modelo.CrudCandidato;
import com.tecontrato.modelo.CrudCurriculum;
import com.tecontrato.modelo.Departamento;
import com.tecontrato.modelo.Genero;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Nombre      : ProcesarCandidato
 * Versión     : 1.0
 * Fecha       : 06/10/2017
 * CopyRight   : Programmer Group Services S.A de C.V
 * @author     : Alexis, Kevin, Luis, María José, Geofredo
 */
public class ProcesarCandidato extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        CrudCurriculum ccv = new CrudCurriculum();
        Candidato can = new Candidato();
        CrudCandidato crca = new CrudCandidato();
        String respuesta = null;
        int resultado = 0;
        String txtIdRol = request.getParameter("txtIdRol");
        HttpSession sessi = request.getSession(); 
        sessi.setAttribute("txtIdRol", txtIdRol);
 
        //Proceso y validacion para boton guardar   
        if(request.getParameter("btnGuardar")!=null)
        {
            try {

                can.setIdCandidato(Integer.parseInt(request.getParameter("txtCodigo")));
                can.setDepto(new Departamento(Integer.parseInt(request.getParameter("cmbDepto"))));
                can.setGenero(new Genero(Integer.parseInt(request.getParameter("genero"))));
                can.setNombre(request.getParameter("txtNombreCandidato"));
                can.setNacionalidad(request.getParameter("txtNacionalidad"));
                can.setFechaNacimiento(request.getParameter("txtFechaNacimiento"));
                can.setDireccion(request.getParameter("txtDireccion"));
                can.setProfesion(request.getParameter("txtProfesion"));
                Part archivo = request.getPart("foto");
                String nombreImagen = request.getParameter("txtarchivo");  //Recupero nombre de la imagen del input file
                can.setFoto(nombreImagen);

                    if (nombreImagen.endsWith(".png") || nombreImagen.endsWith(".jpg")) 
                   {
                        resultado = crca.insertarCandidato(can); 
                        //crear curriculum automaticamente
                        ccv.insertarCurriculum();

                        if (resultado == 1) 
                        {
                            respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong>Comprueba tu usuario iniciando sesión</div>";
                            Integer posicion = 0;
                            String direccion2 = request.getSession().getServletContext().getRealPath("imagenes");
                            posicion = direccion2.indexOf("\\build\\web");
                            direccion2 = direccion2.substring(0,posicion);
                            String direccion = direccion2+"/web/imagenes/fotos";
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
                        respuesta="<div class='alert alert-danger alert-dismissable alerta'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Oops!</strong> Ocurrió un error.</div>";
                    }
            } catch (Exception e) {
                out.println(e);
            }
       }
                
        //Proceso y validacion para boton modificar       
        if(request.getParameter("btnModificar")!=null){

                try {
                        can.setIdCandidato(Integer.parseInt(request.getParameter("txtCodigo")));
                        can.setDepto(new Departamento(Integer.parseInt(request.getParameter("cmbDepto"))));
                        can.setGenero(new Genero(Integer.parseInt(request.getParameter("genero"))));
                        can.setNombre(request.getParameter("txtNombreCandidato"));
                        can.setNacionalidad(request.getParameter("txtNacionalidad"));
                        can.setFechaNacimiento(request.getParameter("txtFechaNacimiento"));
                        can.setDireccion(request.getParameter("txtDireccion"));
                        can.setProfesion(request.getParameter("txtProfesion"));

                                resultado = crca.modificarCandidato(can);
                                if (resultado == 1) 
                                {
                                    respuesta="<div class='alert alert-success alert-dismissable alerta'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro modificado de forma exitosa.</div>";
                                   
                                }else{
                                    respuesta="<div class='alert alert-danger alert-dismissable alerta'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Oops!</strong> Ocurrió un error.</div>";
                                }
                            
                } catch (Exception e) {
                    out.println(e);
                }
        } else{
                          
            respuesta="<div class='alert alert-danger alert-dismissable alerta'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Oops!</strong> Ocurrió un error.</div>";
                         
        }
        
        if(request.getParameter("btnModFoto")!=null){

                try {
                    
                        can.setIdCandidato(Integer.parseInt(request.getParameter("txtIdCandidato")));

                        if (!request.getParameter("txtarchivo").equals("")) 
                        {    
                            String nombreImagen = request.getParameter("txtarchivo");  //Recupero nombre de la imagen del input file
                            can.setFoto(nombreImagen);
                            Part archivo = request.getPart("foto");
                     
                            if (nombreImagen.endsWith(".jpg") || nombreImagen.endsWith(".png")) 
                            {
                                Integer posicion = 0;
                                String direccion2 = request.getSession().getServletContext().getRealPath("imagenes");
                                posicion = direccion2.indexOf("\\build\\web");
                                direccion2 = direccion2.substring(0,posicion);
                                String direccion = direccion2+"/web/imagenes/fotos";
                                File imagenVieja = new File(direccion + "/" + request.getParameter("modViejaBandera"));
                                

                                
                                resultado = crca.modificarFoto(can);
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
                            can.setFoto("userdefault.png");
                            resultado = crca.modificarFoto(can);
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
        if(request.getParameter("btnEliminar")!=null){
            try {
               can.setIdCandidato(Integer.parseInt(request.getParameter("txtCodigo"))); 
               resultado = crca.eliminarCandidato(can);
                if(resultado==1){
                   respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro eliminado de forma exitosa.</div>";
                }else{
                   respuesta="<div class='alert alert-danger alert-dismissable alerta'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Oops!</strong> Ocurrió un error.</div>";
                }
            } catch (Exception e) {
                out.println(e);
            }
         }
        if(request.getParameter("btnEliminarFoto")!=null){
            try {
               can.setIdCandidato(Integer.parseInt(request.getParameter("txtIdCandidato")));
               can.setFoto("userdefault.png");
               resultado = crca.modificarFoto(can);
                if(resultado==1){
                   respuesta="<div class='alert alert-success alert-dismissable'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Éxito!</strong> Registro eliminado de forma exitosa.</div>";
                }else{
                   respuesta="<div class='alert alert-danger alert-dismissable alerta'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color: black;'>¡Oops!</strong> Ocurrió un error.</div>";
                }
            } catch (Exception e) {
                out.println(e);
            }
         }
                
        //Se redirecciona dependiendo del rol de usuario
        request.setAttribute("respuesta", respuesta);
        if(txtIdRol.equals("3")){
            
            request.getRequestDispatcher("perfil.jsp").forward(request, response);
            
        }
        else if(txtIdRol.equals("1")){

            response.sendRedirect("candidatoAdmin.jsp");
            
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
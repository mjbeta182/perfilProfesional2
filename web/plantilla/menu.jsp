<%-- 
    Document   : menu
    Created on : 10-09-2017, 05:48:52 PM
    Author     : Maria Jose
--%>

<%@page import="com.tecontrato.modelo.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    CrudUsuario cu = new CrudUsuario();
    CrudCandidato ccan = new CrudCandidato();
    String iniciarsesion ="";
    String registrarse   ="";
    String usuario       ="";
    String gestionar     ="";
    String reportes      ="";
    String cuenta        ="";
    String ofertas       ="none";
    String seguir        ="none";
    String perfil        ="";
    String curriculum    ="";
    String dir           ="";
    String cerrar        ="";
    String close         ="none";
    String name          ="";
    String foto          ="userdefault.png";
    String nombre        ="";
    int idcan            = 0;
    int rol              = 0;     
    if (session != null) {
        if (session.getAttribute("user") != null) {
            close="";
            cerrar = "<form action='logoutcontroller' method='post'><button id='buttonL' class='btn btn-primary'><span class='glyphicon glyphicon-off'></span></button></form>";
            iniciarsesion="none";
            registrarse="none";
   
            name = (String) session.getAttribute("user");
            List<Candidato> lst = cu.obtenerDatosC(name);
            for(Candidato c:lst){
                foto = c.getFoto();
                nombre = c.getNombre();
                idcan = c.getIdCandidato();
             }
            rol =(Integer) request.getSession().getAttribute("valor");
            if(request.getSession().getAttribute("valor").equals(1)){
                ofertas    = "none";
                seguir     = "none";
                perfil     = "none";
                ofertas    = "none";
                seguir     = "none";
            }else if(request.getSession().getAttribute("valor").equals(2) ){
                usuario     = "none";
                gestionar   = "none";
                reportes    = "none";
            }else if(request.getSession().getAttribute("valor").equals(3)){ 
                usuario     = "none";
                gestionar   = "none";
                reportes    = "none";
                dir         = "perfil.jsp";
                ofertas     ="";
                seguir      ="";
               
            }else{
                response.sendRedirect("index.jsp");
                usuario     = "none";
                gestionar   = "none";
                reportes    = "none";
                cuenta      = "none";
                curriculum  = "none";
                close       = "none";
                registrarse = "";
                iniciarsesion= "";
            }
        } else {
            response.sendRedirect("index.jsp");
                usuario     = "none";
                gestionar   = "none";
                reportes    = "none";
                cuenta      = "none";
                curriculum  = "none";
                close       = "none";
                registrarse = "";
                iniciarsesion= "";
        }
    }
%>
<!--MENU-->
<div id='cssmenu' >
        <ul>
            <li class='active'><a href='index.jsp'><span>TeContrato.com</span></a></li>
           <li class='pull-right' style='display: <%=iniciarsesion%>;'><a href='login.jsp'><span>Iniciar Sesión</span></a></li>
            <li class='has-sub pull-right' style="z-index: 1;display: <%=registrarse%>;"><a href='#'><span>Registrarse</span></a>
               <ul>
                  <li><a href='usuarioCandidato.jsp'><span>Candidato</span></a></li>
                  <li><a href='usuarioEmpresa.jsp'><span>Empresa</span></a></li>
               </ul>
            </li>
            <li class='has-sub' style="z-index:2;display: <%=usuario %>;"><a href='#'><span>Usuarios</span></a>
               <ul>
                   <li><a href='empleadoAdmin.jsp'><span>Empleado</span></a></li>
                   <li class=""><a href='candidatoAdmin.jsp'><span>Candidato</span></a>
                      <!-- <ul>
                            <li><a href='#'><span>Curriculums</span></a></li>
                       </ul>-->
                   </li>
                  <li class=""><a href='empresaAdmin.jsp'><span>Empresa</span></a>
                        <!--<ul>
                            <li><a href='#'><span>Ofertas</span></a></li>
                        </ul>-->
                  </li>
               </ul>
            </li>
            <li class='has-sub' style="z-index: 1;display: <%=gestionar %>;"><a href='#'><span>Gestionar</span></a>
               <ul>
                   <li><a href='habilidad.jsp'><span>Habilidad</span></a></li>
                  <li><a href='idioma.jsp'><span>Idioma</span></a></li>
                  <li><a href='cargo.jsp'><span>cargo</span></a></li>
                  <li><a href='educacion.jsp'><span>Educación</span></a></li>
                  <li><a href='area.jsp'><span>Área</span></a></li>
               </ul>
            </li>
            <li class="has-sub" style="display:<%=reportes %>; "><a href='#' ><span>Reportes</span></a>
                <ul >
                  <li ><a href='#' >
                        <iframe style="width: 100%;margin: 0px;padding: 0px; border: none; " src="reportes/reportes.xhtml">
                        </iframe></a>
                  </li>
               </ul>
            </li>
            <li  style="z-index: 1;display: <%=curriculum %>;"><a href='<%=dir%>'><span><%=nombre %></span></a></li>
            <li style="display:<%=ofertas %>; "><a href='oferta.jsp'><span>Ofertas</span></a></li>
            <li style="display:<%=seguir %>; "><a href='seguir.jsp'><span>Candidatos</span></a></li>
            <li style="display: <%=close%>;  padding-right: 20px;" class="pull-right"><% out.print(cerrar); %></li>                      
        </ul>
         
    </div>

<!--Fin de menú-->

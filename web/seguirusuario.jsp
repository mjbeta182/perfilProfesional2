<%-- 
    Document   : oferta
    Created on : 10-07-2017, 10:04:19 AM
    Author     : Alexis
--%>

<%@page import="com.tecontrato.modelo.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="plantilla/libs.jsp"/>
        <title>Usuarios</title>
    </head>
    <body class="bodyFormulario">
    <%
        CrudOferta crof = new CrudOferta();
        
       //Este código es para obtener el id de la oferta que se mostrará
        String codigo="";
        int resp = 0;
        String varuno = "";
        String vardos = "";
        if(request.getSession().getAttribute("codigo")!=null)
        {
            codigo = (String) request.getSession().getAttribute("codigo");
        }
        String nombre = "";
        int codigod = 0;
    int rol = 0;
        //Código para obtener el id del usuario
        int idusuario = 0;
        
        //para validar que el usuario no esté vacío
        if (session.getAttribute("user").equals(null)){
            response.sendRedirect("index.jsp");
        }        
    %>    
    <!--Fin obtener idusuario-->
    <div class="container-fluid">
        <div class="row" style="width: 100%; position: fixed;z-index: 10;">
            <jsp:include page="plantilla/menu.jsp" />
        </div>
        <div style="margin-top: 0px;">
            ${respuesta}
             
        </div>
        <div class="row main">
        <div class="container">
        <div id="page-content-wrapper">
        <!-- Keep all page content within the page-content inset div! -->
        <div class="page-content inset">
          <div class="row">
              
                 <form action="procesarSeguirUsuario">
                  <div style="display: none;">
                        <%= nombre = (String) session.getAttribute("user") %>
                        <% codigod = crof.obtenerid(nombre);%>
                        <% rol = crof.obtenerrol(codigod);%>
                        <input type="text" name="codUsu" id="codUsu" value="<%= codigod%>">
                        <input type="text" name="idrol" id="idrol" value="<%= rol%>">
                    </div>
                  <table border="0">
                      <tbody>
                          <tr>
                              <td>
                                <div class="row " id="buscador" style="margin-top: 50px;"  >
                                <div class="col-md-11 " style="margin-left: 55px;"  > 
                                    <input style="display: none;" type="submit" name="btnBuscar" class="btn btn-primary btn-large pull-right" value="Buscar">
                                </div>
                                <div class="col-md-1"></div>
                                </div>
                              </td>
                              <td>
                                <div class="row " id="buscador" style="margin-top: 65px;"  >
                                <div class="col-md-11 " style="margin-left: 65px;"  > 
                                <input type="text" name="txtBuscar"  id="buscar" placeholder="Busqueda" style="margin-bottom: 15px;">
                                </div>
                                <div class="col-md-1"></div>
                                </div>
                              </td>
                          </tr>
                      </tbody>
                  </table>

              </form>
              
              <% CrudOferta co = new CrudOferta(); 
                  List<Candidato> lst= co.seguirdetalle(codigo);
                  for(Candidato c:lst){
                %>
                <!--Este código de acá es para obtener y llenar el id del usuario-->
                <div style="display: none;">
                    <%= nombre = (String) session.getAttribute("user") %>
                    <% idusuario = crof.obtenerid(nombre);%>
                    <% resp = crof.verRelacionSeguir(idusuario, codigo);%>
                    <%  
                        if(resp!=0)
                        {
                            varuno="none";
                            vardos="block";
                        }else{
                            varuno="block";
                            vardos="none";
                        }
                    %>
                </div>
                <div class="row main" style="margin-right: -20px;">
                <div class="main-tbl " style="padding-left: -10%;">
                    
                    <form action="procesarSeguirUsuario">
                        <div style="display: none;">
                        <%= nombre = (String) session.getAttribute("user") %>
                        <% codigod = crof.obtenerid(nombre);%>
                        <% rol = crof.obtenerrol(codigod);%>
                        <input type="text" name="codUsu" id="codUsu" value="<%= codigo%>">
                        <input type="text" name="idrol" id="idrol" value="<%= rol%>">
                    </div>
                        <table class="table active" id="tblMostrar" name="tblMostrar">
                            <tbody>
                                <tr id="headertbl">
                                    <td></td>
                                    <td> 
                                        <input name="txtIdCandidato" id="txtIdCandidato" type="text" value="<%= c.getIdCandidato() %>" style="display: none;">
                                        <input name="txtIdUsuario" id="txtIdUsuario" type="text" value="<%= idusuario %>" style="display: none;">
                                        <img src="imagenes/fotos/<%=c.getFoto() %>" title="Candidatos" width="115px">
                                    </td>
                                    <td><center>
                                        <h3><%=c.getNombre() %></h3>
                                        </center>
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td colspan="2"> Profesión :</td>
                                    <td colspan="2"><h5><%=c.getProfesion() %></h5>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"> Habilidades :</td>
                                    <td colspan="2"> <h5></h5>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4">                                    
                                        <center>
                                            <input style=" width: 25%; margin-left: 10%; display: <%= varuno%>;"  type="submit" name="btnNewIn" class="btn btn-success btn-large pull-right" value="Seguir">
                                            <input style=" width: 25%; margin-left: 10%; display: <%= vardos%>;"  type="submit" name="btnCanIn" class="btn btn-warning btn-large pull-right" value="Dejar de seguir">
                                        </center>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                                   
                    </form>
                </div>
                </div> 
                <%}%>
          
          </div>
        </div>
    </div>
                </div>
        </div>
        <jsp:include page="plantilla/footer.jsp"/> 
    </body>
</html>

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
        <title>Candidatos</title>
    </head>
    <body class="bodyFormulario">
    <%
       CrudOferta crof = new CrudOferta();
       String nombre = "";
       int codigo = 0;
       int rol = 0;
        String criterio="";
        if(request.getSession().getAttribute("criterio")!=null)
        {
            criterio = (String) request.getSession().getAttribute("criterio");
        }
        
    %>    
    <div class="container-fluid">
        <div class="row" style="width: 100%; position: fixed;z-index: 10;">
            <jsp:include page="plantilla/menu.jsp" />
        </div>
        <div style="margin-top: 60px;">
            ${respuesta}
            
        </div>
                <div class="row main">
                     <div class="container">
                    <div id="page-content-wrapper">
        <!-- Keep all page content within the page-content inset div! -->
        <div class="page-content inset">
          <div class="row">
              <div style="">
                  
              </div>
              
              <form action="procesarSeguirUsuario">
                  <div style="display: none;">
                        <%= nombre = (String) session.getAttribute("user") %>
                        <% codigo = crof.obtenerid(nombre);%>
                        <% rol = crof.obtenerrol(codigo);%>
                        <input type="text" name="codUsu" id="codUsu" value="<%= codigo%>">
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
              
              <%CrudOferta co = new CrudOferta(); 
                  List<Candidato> lst= co.seguirCriterio(criterio);
                  for(Candidato e:lst){
                %>
                
                <div class="media well" style="margin-left: 20px;">
                <div class="col-md-2">
                    <img src="imagenes/fotos/<%=e.getFoto() %>" title="Candidatos" width="120px" height="120px"  style="border-radius: 50%;">
                </div>
                <div class="col-md-10 " style="padding-left: 10%;">
                    <form action="procesarSeguirUsuario">
                        <div style="display: none;">
                            <%= nombre = (String) session.getAttribute("user") %>
                            <% codigo = crof.obtenerid(nombre);%>
                            <% rol = crof.obtenerrol(codigo);%>
                            <input type="text" name="codUsu" id="codUsu" value="<%= codigo%>">
                            <input type="text" name="idrol" id="idrol" value="<%= rol%>">
                        </div>
                    <h4><%=e.getNombre()%></h4>
                    <input value="<%=e.getIdCandidato() %>" name="codCandidato" id="codCandidato" style="display: none;"/>
                    <h5><%=e.getProfesion() %></h5>
                    <input type="submit" name="btnDetalle" class="btn btn-primary btn-large pull-right" value="Detalles">
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

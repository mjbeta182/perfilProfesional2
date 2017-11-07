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
        <title>Oferta</title>
    </head>
    <body class="bodyFormulario" id="home">
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
              
                 <form action="procesarOferta">
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
              
              <%CrudOferta co = new CrudOferta(); 
                  List<Oferta> lst= co.ofertadetalle(codigo);
                  for(Oferta e:lst){
                %>
                <!--Este código de acá es para obtener y llenar el id del usuario-->
                <div style="display: none;">
                    <%= nombre = (String) session.getAttribute("user") %>
                    <% idusuario = crof.obtenerid(nombre);%>
                    <% resp = crof.verRelacion(idusuario, codigo);%>
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
                    
                    <form action="procesarOferta">
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
                                        <input name="txtIdOferta" id="txtIdOferta" type="text" value="<%= e.getIdOferta()%>" style="display: none;">
                                        <input name="txtIdUsuario" id="txtIdUsuario" type="text" value="<%= idusuario %>" style="display: none;">
                                        <%
                                            CrudEmpresa cemp = new CrudEmpresa();
                                             List<Empresa> ls = cemp.obtenerEmpresas(e.getEmpresa().getIdEmpresa());
                                            for(Empresa emp:ls){
                                        %>
                                           <img src="imagenes/fotos/<%=emp.getFoto() %>" title="Empresa" width="120px" height="120px"  style="border-radius: 50%;">
                                         <%}%>
                                    </td>
                                    <td><center>
                                        <h3><%=e.getEmpresa().getNombreEmpresa()%></h3>
                                        <h5> <%=e.getEmpresa().getActividad()%></h5>
                                        <h5> <%=e.getEmpresa().getDescripcion()%></h5>
                                        </center>
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td colspan="4">
                                    <center>
                                        <h3><%=e.getNombre()%></h3>
                                    </center>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"> Descripción :</td>
                                    <td colspan="2"><h5><%=e.getDescripcion()%></h5>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"> Departamento :</td>
                                    <td colspan="2"> <h5><%=e.getDepartamento().getNombreDepto()%></h5>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"> Area :</td>
                                    <td colspan="2"> <h5><%=e.getArea().getNombreArea()%></h5>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"> Cargo :</td>
                                    <td colspan="2"><h5><%=e.getCargo().getNombreCargo()%></h5>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"> Edad mínima :</td>
                                    <td colspan="2"><h5><%=e.getEdadMin()%></h5>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"> Edad máxima :</td>
                                    <td colspan="2"><h5><%=e.getEdadMax()%></h5>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"> Experiencia :</td>
                                    <td colspan="2"><h5><%=e.getNivelExperiencia().getNombreNivelExperiencia()%></h5>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"> Contratación por :</td>
                                    <td colspan="2"><h5><%=e.getTipoContratacion().getNombreContratacion()%></h5>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">Número de vacantes :</td>
                                    <td colspan="2"><h5><%=e.getVacantes()%></h5>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4">                                    
                                        <center>
                                            <input style=" width: 25%; margin-left: 10%; display: <%= varuno%>;"  type="submit" name="btnNewIn" class="btn btn-success btn-large pull-right" value="Inscribir en oferta">
                                            <input style=" width: 25%; margin-left: 10%; display: <%= vardos%>;"  type="submit" name="btnCanIn" class="btn btn-warning btn-large pull-right" value="Cancelar inscripción">
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

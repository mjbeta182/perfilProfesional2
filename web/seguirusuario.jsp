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
        
        <script Language='JavaScript'>
          var iddetalle=0;
          function cargar(codigo, nombre, nivel)
          {
               //document.frmOferta.nombre.value=nombre;
               //document.frmOferta.nivel.value=nivel;
               var codigoId = codigo;
               iddetalle = codigoId;
               $('#txtIdDetalle').val(codigo);
               
          }
          
          $(document).ready(function(){
            $(" [data-toggle=confirmation]").confirmation({
            rootSelector: "[data-toggle=confirmation]",
            // other options
            popout:true,
            singleton:true
            });
          }); 
        </script>
        
    </head>
    <body class="bodyFormulario">
    <%
        CrudOferta crof = new CrudOferta();
        CrudCvHabilidad cvh = new CrudCvHabilidad();
        
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
            <div class="row">
    <!--Cambiar id modal-fade segun formulario con data-toggle="modal" data-target="#id" 
    con esto se manda a llamar el modal  en las etiquetas <a></a> o <button></button> -->
    
        <div class="modal fade" id="habilidad" tabindex="-1" role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
        <div class="modal-dialog"  id="modal-dialog">
            <div class="panel panel-primary" id="panel-primary">
                <div class="panel-heading" id="panel-heading" >
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="panel-title" id="contactLabel">TeContrato.com</h4>
                </div>
                 <!--Formulario-->
                 <form action="procesarSeguirUsuario" method="post" name="frmHabilidades" >
                <div class="modal-body" id="modalBody">               
                    <div class="main-center">
                        <h3><strong>VALORAR</strong></h3>
                        <div style="display: none;">
                            <%= nombre = (String) session.getAttribute("user") %>
                            <% codigod = crof.obtenerid(nombre);%>
                            <% rol = crof.obtenerrol(codigod);%>
                            <input type="text" name="codUsu" id="codUsu" value="<%= codigo%>">
                            <input type="text" name="idrol" id="idrol" value="<%= rol%>">
                        </div>
                        
                        <!--
                            <input type="text"  name="idhabilidad" id="idhabilidad"   class="form-control" placeholder="Código" style="display: none;" readonly="true"/>
                            <input type="text" name="nombrehabilidad" id="nombrehabilidad"  class="form-control" placeholder="Nombre"  required="true" />
                        -->
                       <input type="text" class="form-control" name="txtIdDetalle" id="txtIdDetalle" style="display: none;" placeholder="Codigo"/>
                       <label class="cols-sm-2 control-label" id="nombre" ></label>
                        <label class="cols-sm-2 control-label" id="nivel"></label>
                              
                            <div class="form-group">
                                <label for="valoracion" class="cols-sm-2 control-label">Valoración</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-star" aria-hidden="true"></i></span>
                                        <input type="number" class="form-control" name="spValor" id="spValor"  placeholder="Seleccione una valoración" required="true" min="1" max="5"/>
                                    </div>
                                </div>
                            </div>           
                       
                           <div class="form-group">
                                <label for="descripcion" class="cols-sm-2 control-label">Comentario</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-file" aria-hidden="true"></i></span>
                                        <textarea class="form-control" name="txtDescripcion" id="txtDescripcion"  placeholder="Escribe lo que piensas..."/></textarea>
                                    </div>
                                </div>
                            </div>
                       
                            <div id="botones">
                                <button type="submit" data-toggle="confirmation" name="btnGuardar" class="btn btn-warning fa fa-undo" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                                data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                                data-title="¿Está seguro de modificar el registro?" data-content="¿Seguro de guardar el comentario?"> Guardar</button>
                                <button type="submit" data-toggle="confirmation" name="btnEliminar" class="btn btn-danger fa fa-close" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                                data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                                data-title="¿Está seguro de eliminar el registro?" data-content="Esto podría ser peligroso"> Eliminar</button>
                                <button type="reset"  class="btn btn-primary fa fa-undo" title="Limpiar campos"></button>  
                            </div>
                            </div>
                            <div class="col-md-2" ></div>
                            
                            
                        </div>
                    </div>         
                </form> 
                <!--End Formulario-->
            </div>
        </div>
    </div><!--End modal-->
            
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
                                    <td colspan="2"> Género :</td>
                                    <td colspan="2"><h5><%=c.getGenero().getGenero() %></h5>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"> Nacionalidad :</td>
                                    <td colspan="2"><h5><%=c.getNacionalidad()%></h5>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"> Género :</td>
                                    <td colspan="2"><h5><%=c.getDepto().getNombreDepto() %></h5>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2"> Habilidades :</td>
                                    <td colspan="2"> 
                                      
    
            
        <table class="table active " id="tblMostrar" name="tblMostrar">
            <%
             List<CvHabilidad> lso = cvh.listaCvhId(codigo);
             for( CvHabilidad ch:lso){
           %>
           <tr>
               <td><%= ch.getHabilidad().getNombreHabilidad() %></td>
               <td><%= ch.getNivel().getNivel()%></td>
               <td id="colAccion" style="width: 35%;">
                   <form action="procesarSeguirUsuario">
                       <div style="display: none;">
                            <%= nombre = (String) session.getAttribute("user") %>
                            <% codigod = crof.obtenerid(nombre);%>
                            <% rol = crof.obtenerrol(codigod);%>
                            <input type="text" name="codUsu" id="codUsu" value="<%= codigo%>">
                            <input type="text" name="idrol" id="idrol" value="<%= rol%>">
                        </div>
                        <a   class="btn btn-primary" id="button" href="" data-toggle="modal" data-target="#habilidad" onclick="cargar(<%= ch.getValoracion()%>,'<%= ch.getHabilidad().getNombreHabilidad()%>','<%= ch.getNivel().getNivel()%>')" ><span class="glyphicon glyphicon-plus-sign"></span> Comentar</a>
                         </form>
               </td>
           </tr>
           <% } %>
       </table>
       
       

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
        <jsp:include page="plantilla/footer.jsp"/> 
    </body>
</html>

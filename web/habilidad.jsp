<%-- 
    Document   : habilidad
    Created on : 10-01-2017, 06:59:17 PM
    Author     : Maria Jose
--%>

<%@page import="com.tecontrato.modelo.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <jsp:include page="plantilla/libs.jsp" />
        <title>Habilidad</title>
        <script Language='JavaScript'>
          function cargar(codigo, nombreh)
          {
               $('#idhabilidad').val(codigo);
               $('#nombrehabilidad').val(nombreh);
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
<body class="bodyFormulario" id="home">
    <%
         CrudHabilidad cha = new CrudHabilidad();
        if (session.getAttribute("user") == null){
            response.sendRedirect("index.jsp");
        }
    %>    
    <div class="container-fluid">
        <div class="row" style="width: 100%; position: fixed;z-index: 10;">
            <jsp:include page="plantilla/menu.jsp" />
        </div>
        <div style="margin-top: 60px;">
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
                 <form action="procesarHabilidad" method="post" name="frmHabilidad" >
                <div class="modal-body" id="modalBody">               
                    <div class="main-center">
                        
                        <h3><strong>HABILIDAD</strong></h3>
                        <input type="text"  name="idhabilidad" id="idhabilidad"   class="form-control" placeholder="Código" style="display: none;" readonly="true"/>
                                <input type="text" name="nombrehabilidad" id="nombrehabilidad"  class="form-control" placeholder="Nombre"  required="true" />
                                <div id="botones">
                                    <button type="submit" name="btnGuardar" class="btn btn-primary glyphicon glyphicon-floppy-disk"> Guardar</button>
                                    <button type="submit" data-toggle="confirmation" name="btnModificar" class="btn btn-warning fa fa-undo" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                                    data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                                    data-title="¿Está seguro de modificar el registro?" data-content="Esto podría ser peligroso"> Modificar</button>
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
    
    

    <div class="row main" style="margin-top: 0;">
    <div class=" main-tbl">
         <h3><strong>HABILIDAD</strong></h3>
        <!--Tabla para mostrar registros-->  
        <div class="row ">
            <div class="col-md-10"><input type="text" name="search"  id="buscar" placeholder="Busqueda"></div><div class="col-md-2"> <a id="buttontbl" class="btn btn-primary pull-right" onclick="cargar()"  href="" data-toggle="modal" data-target="#habilidad">Nuevo</a></div>
        </div>
        <table class="table active " id="tblMostrar" >
            <thead >
            <tr id="headertbl">
               <th>Código</th>
               <th>Habilidad</th>
               <th style="width: 10%;">Acción</th>
             </tr>
           </thead>
            <%
             List<Habilidad> ls = cha.listahabilidades();
             for( Habilidad h:ls){
           %>
           <tr>
               <td><%= h.getIdHabilidad() %> </td>
               <td><%= h.getNombreHabilidad() %></td>
               <td id="colAccion" style="width: 50px;">
                   <a   class="btn btn-primary" id="button" href="" data-toggle="modal" data-target="#habilidad" onclick="cargar(<%= h.getIdHabilidad()%>,'<%=h.getNombreHabilidad() %>')" ><span class="glyphicon glyphicon-plus-sign"></span></a>
               </td>
           </tr>
           <% } %>
       </table>
    </div>
    
  
 </div><!--End Row-->
<!--Footer-->
<jsp:include page="plantilla/footer.jsp" />
</div><!--End Container-->
</body>
</html>

<%-- 
    Document   : empresaAdmin
    Created on : 10-10-2017, 10:59:18 AM
    Author     : Kevin López
--%>

<%@page import="java.util.List"%>
<%@page import="com.tecontrato.modelo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="plantilla/libs.jsp" />
        <title>JSP Page</title>
        <script Language='JavaScript'>
          
          $(document).ready(function(){
              $('.phone').mask('(000)0000-0000');
          }); 
          
          function cargar(codigo, nombreE, act, desc, email, tel)
          {
               $('#txtIdEmpresa').val(codigo);
               $('#txtNombreEmpresa').val(nombreE);
               $('#txtActividad').val(act);
               $('#txtDescripcion').val(desc);
               $('#txtEmail').val(email);
               $('#txtTelefono').val(tel);
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
            CrudEmpresa cemp = new CrudEmpresa();
            HttpSession sess = request.getSession(true); //use false to use the existing session
            String idu = (String) sess.getAttribute("txtIdUsuario");
            if (session.getAttribute("user") == null){
                response.sendRedirect("index.jsp");
            }
        %>
<div class="container-fluid">
        <div class="row" style="width: 100%; position: fixed;z-index: 10;">
            <jsp:include page="plantilla/menu.jsp" />
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
                 <form action="procesarEmpresa" method="post" name="frmEmpresaAdmin" >
                <div class="modal-body" id="modalBody">               
                    <div class="main-center">
                        <h3><strong>EMPRESA</strong></h3>
                                <input type="hidden" class="form-control" name="txtIdEmpresa" id="txtIdEmpresa"  placeholder="Código" readonly="true" value="<%=idu %>"/>
                                <!--Pendiente-->
                                <input type="hidden" class="form-control" name="txtIdRol" id="txtIdRol"  placeholder="" readonly="true" value="1"/>
                            <br>
                            <label for="txtNombre" class="cols-sm-2 control-label">Nombre</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
                                <input type="text" class="form-control" name="txtNombreEmpresa" id="txtNombreEmpresa"  placeholder="Nombre de la empresa" required="true"/>
                            <br>
                            </div>
                            <label for="txtActividad" class="cols-sm-2 control-label">Actividad</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-book" aria-hidden="true"></i></span>
                                <input type="text" class="form-control" name="txtActividad" id="txtActividad"  placeholder="Ingrese la actividad de la empresa" required="true"/>
                            </div>
                            <br>
                            <label for="txtDescripcion" class="cols-sm-2 control-label">Descripcion</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-edit" aria-hidden="true"></i></span>
                                <textarea class="form-control" rows="2" name="txtDescripcion" id="txtDescripcion" placeholder="Descripción" required="true"></textarea>
                            </div>
                            <br>
                            <label for="txtEmail" class="cols-sm-2 control-label">Email</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i></span>
                                <input type="email" class="form-control" name="txtEmail" id="txtEmail"  placeholder="Ingrese el correo electronico" required="true"/>
                            </div>
                            <br>
                            <label for="txtTelefono" class="cols-sm-2 control-label">Telefono</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-phone" aria-hidden="true"></i></span>
                                <input type="text" class="form-control phone" name="txtTelefono" id="txtTelefono" placeholder="(503)0000-0000" required="true"/>
                            </div>
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
    
    <div style="margin-top: 60px;">
        ${respuesta}
    </div>

    <div class="row main" style="margin-top: 25px;">
    <div class=" main-tbl">
         <h3><strong>EMPRESA</strong></h3>
        <!--Tabla para mostrar registros-->  
        <div class="row ">
            <div class="col-md-10"><input type="text" name="search"  id="buscar" placeholder="Busqueda"></div><div class="col-md-2"> <a id="buttontbl" class="btn btn-primary pull-right" onclick="cargar()"  href="" data-toggle="modal" data-target="#habilidad">Nuevo</a></div>
        </div>
        <table class="table active " id="tblMostrar" >
            <thead >
            <tr id="headertbl">
               <th>Código</th>
               <th>Nombre EMpresa</th>
               <th>Actividad</th>
               <th>Descripción</th>
               <th>Email</th>
               <th>Telefono</th>
               <th style="width: 10%;">Acción</th>
             </tr>
           </thead>
            <%
             List<Empresa> ls = cemp.mostrarEmpresa();
             for(Empresa emp:ls){
           %>
           <tr>
               <td><%= emp.getIdEmpresa() %> </td>
               <td><%= emp.getNombreEmpresa() %></td>
               <td><%= emp.getActividad() %></td>
               <td><%= emp.getDescripcion() %></td>
               <td><%= emp.getEmail() %></td>
               <td><%= emp.getTelefono() %></td>
               <td id="colAccion" style="width: 50px;">
                   <a   class="btn btn-primary" id="button" href="" data-toggle="modal" data-target="#habilidad" onclick="cargar(<%=emp.getIdEmpresa() %>,'<%=emp.getNombreEmpresa() %>','<%=emp.getActividad() %>','<%=emp.getDescripcion() %>','<%=emp.getEmail() %>','<%=emp.getTelefono() %>')" ><span class="glyphicon glyphicon-plus-sign"></span></a>
               </td>
           </tr>
           <% } %>
       </table>
    </div>
    <
  
 </div><!--End Row-->
<!--Footer-->
<jsp:include page="plantilla/footer.jsp" />
</div><!--End Container-->
</body>
</html>

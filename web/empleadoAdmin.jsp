<%-- 
    Document   : empleado
    Created on : 10-10-2017, 03:53:35 PM
    Author     : Kevin López
--%>

<%@page import="java.util.List"%>
<%@page import="com.tecontrato.modelo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="plantilla/libs.jsp"/>
        <title>Empleado</title>
        <script>
        jQuery(function(){
            $('.al-date').datepicker({
                format: 'yyyy-mm-dd',
                language: 'es',
                autoclose: true,
                startDate: "1927/01/01",
                endDate: "2100/12/31",
                startView: 2,
                todayBtn: "linked"
            });
          });
    </script>
        <script Language='JavaScript'>
          
          $(document).ready(function(){
              $('.phone').mask('(000)0000-0000');
          }); 
           function cargarFoto(foto,id)
            {
              $('#txtarchivo').val(foto); 
              $("#modViejaBandera").val(foto);
              $("#banderaActual").attr("src","imagenes/fotos/"+foto);
              $('#txtIdEmp').val(id); 

            }
          function cargar(codigo, nombreC, dir, tel, fechaNac, foto)
          {
               $('#txtIdEmpleado').val(codigo);
               $('#txtNombreEmpleado').val(nombreC);
               $('#txtDireccion').val(dir);
               $('#txtTelefono').val(tel);
               $('#txtFechaNacimiento').val(fechaNac);
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
            CrudEmpleado cemp=new CrudEmpleado(); 
            HttpSession sess = request.getSession(true); //use false to use the existing session
            String idu = (String) sess.getAttribute("txtIdUsuario");
            int rol = 0;
            if (session.getAttribute("user") == null){
                response.sendRedirect("index.jsp");
            }
             rol =(Integer) request.getSession().getAttribute("valor");
        %>
    <div class="container-fluid">
        <div class="row" style="width: 100%; position: fixed;z-index: 10;">
            <jsp:include page="plantilla/menu.jsp" />
        </div>
    <div class="row"> 
                 <!--Formulario Foto-->
    <div class="modal fade" id="foto" tabindex="-1" role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
        <div class="modal-dialog"  id="modal-dialog">
            <div class="panel panel-primary" id="panel-primary">
                <div class="panel-heading" id="panel-heading" >
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="panel-title" id="contactLabel">TeContrato.com</h4>
                </div>
                 <!--Formulario-->
                <form action="procesarEmpleado" method="POST" name="frmFoto" enctype="multipart/form-data" >
                <div class="modal-body" id="modalBody">               
                    <div class="main-center">
                        <h3><strong>SUBIR FOTO</strong></h3>
                        <input type="hidden" class="form-control" name="txtIdRol" id="txtIdRol"  placeholder="" readonly="true" value="<%=rol %>"/>

                        <input  name="txtIdEmp" id="txtIdEmp" style="display: none;">
                        <div class="form-group ">
                            <div id="profile-userpic" style="margin-bottom: 20px" >
                                <img src="imagenes/arena.png" alt="bandera" class="bandera img-responsive" id="banderaActual" style="margin-top: 15px;margin: auto;width: 120px;height: 120px; border-radius: 50%;">
                            </div>
                            <div class="cols-sm-10">    
                                <div class="input-group image-preview" style="text-align: right;" >
                                <!-- image-preview-filename -->
                                <span class="input-group-addon"><i class="glyphicon glyphicon-picture" aria-hidden="true"></i></span>
                                <input type="text" class="form-control image-preview-filename" name="txtarchivo" id="txtarchivo" >
                                <input type="hidden" id="modViejaBandera" name="modViejaBandera">
                                <span class="input-group-btn">
                                        <!-- image-preview-clear -->
                                        <button type="button" class="btn btn-default image-preview-clear" style="display:none;height: 36px;">
                                            <span class="glyphicon glyphicon-remove"></span>
                                        </button>
                                        <!-- image-preview-input -->
                                        <div class="btn btn-default image-preview-input">
                                            <span class="glyphicon glyphicon-folder-open"></span>
                                            <span class="image-preview-input-title">Subir Foto</span>
                                            <input type="file" name="foto" id="foto" accept="image/png, image/jpeg, image/gif" />
                                        </div>
                                    </span> <!-- /input-group-btn -->
                                </div><!-- /input-group [image preview] --> 
                            </div>
                         </div> 

                        <div id="botones">
                            <button type="submit" data-toggle="confirmation" name="btnModFoto" id="btnModificarFoto" class="btn btn-warning " data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                            data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                            data-title="¿Está seguro de modificar el registro?" ><span class="glyphicon glyphicon-pencil"></span>Guardar</button>
                            <button type="submit" data-toggle="confirmation" name="btnEliminarFoto" id="btnEliminarFoto" class="btn btn-danger" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                            data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                            data-title="¿Está seguro de eliminar el registro?" ><span class="glyphicon glyphicon-remove"></span>Eliminar</button>
                            <button class="btn btn-primary" data-dismiss="modal" >Cancelar</button>  
                        </div>    
                    </div>        
                </div>
                </form><!--End Formulario-->
            </div>
        </div>
    </div><!--End modal--> 

        
        <div class="modal fade" id="empleado" tabindex="-1" role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
        <div class="modal-dialog"  id="modal-dialog">
            <div class="panel panel-primary" id="panel-primary">
                <div class="panel-heading" id="panel-heading" >
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="panel-title" id="contactLabel">TeContrato.com</h4>
                </div>
                 <!--Formulario-->
                 <form action="procesarEmpleado" method="POST" name="frmEmpleado" enctype="multipart/form-data" >
                <div class="modal-body" id="modalBody">               
                    <div class="main-center">
                        <h3><strong>EMPLEADO</strong></h3>
                                <input type="hidden" class="form-control" name="txtIdEmpleado" id="txtIdEmpleado"  placeholder="Código" readonly="true" value="<%=idu %>"/>
                                <!--Pendiente-->
                                <input type="hidden" class="form-control" name="txtIdRol" id="txtIdRol"  placeholder="" readonly="true" value="1"/>
                            <br>
                            <label for="txtNombreEmpleado" class="cols-sm-2 control-label">Nombre</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
                                <input type="text" class="form-control" name="txtNombreEmpleado" id="txtNombreEmpleado"  placeholder="Nombre del Empleado"/>
                            </div>
                            <br>
                            <label for="txtDireccion" class="cols-sm-2 control-label">Dirección</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-map" aria-hidden="true"></i></span>
                                <input type="text" class="form-control" name="txtDireccion" id="txtDireccion"  placeholder="Dirección" required="true"/>
                            </div>
                            <br>
                            <label for="txtTelefono" class="cols-sm-2 control-label">Teléfono</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-phone" aria-hidden="true"></i></span>
                                <input type="text" class="form-control phone" name="txtTelefono" id="txtTelefono" placeholder="(503)0000-0000"  placeholder="Teléfono" required="true"/>
                            </div>
                            <br>
                            <label for="fechaNacimiento" class="cols-sm-2 control-label">Fecha Nacimiento</label>
                                <div class="input-group date al-date" data-provide="datepicker">
                                        <span class="input-group-addon"><i class="fa fa-calendar" aria-hidden="true"></i></span>
                                        <input type="text" class="form-control" name="txtFechaNacimiento" id="txtFechaNacimiento" placeholder="Fecha Nacimiento" required="true" readonly="" style="color: black;" />
                                </div>
                            <br>

                           <div id="botones">
                                <button type="submit" data-toggle="confirmation" name="btnModFoto" id="btnModificarFoto" class="btn btn-warning " data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                                data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                                data-title="¿Está seguro de modificar el registro?" ><span class="glyphicon glyphicon-pencil"></span>Guardar</button>
                                <button type="submit" data-toggle="confirmation" name="btnEliminarFoto" id="btnEliminarFoto" class="btn btn-danger" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                                data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                                data-title="¿Está seguro de eliminar el registro?" ><span class="glyphicon glyphicon-remove"></span>Eliminar</button>
                                <button class="btn btn-primary" data-dismiss="modal" >Cancelar</button>  
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
         <h3><strong>EMPLEADO</strong></h3>
        <!--Tabla para mostrar registros-->  
        <div class="row ">
            <div class="col-md-10"><input type="text" name="search"  id="buscar" placeholder="Busqueda"></div><div class="col-md-2"> <a id="buttontbl" class="btn btn-primary pull-right"  href="usuarioEmpleado.jsp" >Crear usuario</a></div>
        </div>
        <table class="table active " id="tblMostrar" >
            <thead >
            <tr id="headertbl">
               <th>Código</th>
               <th>Nombre Empleado</th>
               <th>Dirección</th>
               <th>Teléfono</th>
               <th>Fecha Nacionalidad</th>
               <th>Foto</th>
               <th style="width: 10%;">Acción</th>
             </tr>
           </thead>
            <%
             List<Empleado> ls2 = cemp.mostrarEmpleado();
             for(Empleado emp:ls2){
           %>
           <tr>
               <td><%= emp.getIdEmpleado() %> </td>
               <td><%= emp.getNombreEmpleado() %></td>
               <td><%= emp.getDireccion() %></td>
               <td><%= emp.getTelefono() %></td>
               <td><%= emp.getFechaNacimiento() %></td>
               <td>
                    <%
                       List<Empleado> lst = cemp.obtenerEmpleados(emp.getIdEmpleado());
                       for(Empleado em:lst){
                    %>
                    <a data-toggle="modal" data-target="#foto" onclick="cargarFoto('<%=em.getFoto()%>',<%=em.getIdEmpleado() %>)" >
                       <img  src="imagenes/fotos/<%=emp.getFoto() %>" alt="Foto" style="width:42px;height:42px;border:0;">
                    </a> 
                    <% } %>
                           
                </td>
               <td id="colAccion" style="width: 50px;">
                   <a class="btn btn-primary" id="button" href="" data-toggle="modal" data-target="#empleado" onclick="cargar(<%=emp.getIdEmpleado() %>,'<%=emp.getNombreEmpleado()%>','<%=emp.getDireccion() %>','<%=emp.getTelefono() %>','<%=emp.getFechaNacimiento() %>')" ><span class="glyphicon glyphicon-plus-sign"></span></a>
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

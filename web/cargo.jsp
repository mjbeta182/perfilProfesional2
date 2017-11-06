<%-- 
    Document   : cargo
    Created on : 10-09-2017, 04:55:17 PM
    Author     : Geofredo
--%>
<%@page import="java.util.List"%>
<%@page import="com.tecontrato.modelo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="plantilla/libs.jsp"/>
        <title>Cargo</title>
        <script Language='JavaScript'>
            function cargar(codigo, nombre)
            {
                $('#txtCodigo').val(codigo);
                $('#txtNombreCargo').val(nombre);
            }

            $(document).ready(function () {
                $(" [data-toggle=confirmation]").confirmation({
                    rootSelector: "[data-toggle=confirmation]",
                    // other options
                    popout: true,
                    singleton: true
                });
            });
        </script>
    </head>
    <body class="bodyFormulario" id="home">
    <%
        CrudCargo ccar = new CrudCargo();
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

                <div class="modal fade" id="cargo" tabindex="-1" role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
                    <div class="modal-dialog"  id="modal-dialog">
                        <div class="panel panel-primary" id="panel-primary">
                            <div class="panel-heading" id="panel-heading" >
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <h4 class="panel-title" id="contactLabel">TeContrato.com</h4>
                            </div>
                            <!--Formulario-->
                            <form action="procesarCargo" method="post" name="frmCargo" >
                                <div class="modal-body" id="modalBody">               
                                    <div class="main-center">

                                        <h3><strong>CARGO</strong></h3>

                                         
                                                <input type="text" class="form-control" name="txtCodigo" id="txtCodigo" style="display:none"  placeholder="Código" readonly="" />
                                          

                                        <br>
                                        <div class="form-group">
                                            <label for="name" class="cols-sm-2 control-label">Nombre del cargo</label>
                                            <div class="cols-sm-10">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-file" aria-hidden="true"></i></span>
                                                    <input type="text" class="form-control" name="txtNombreCargo" id="txtNombreCargo"  placeholder="Ingrese el cargo"/>
                                                </div>

                                             </div>
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

                                                <div class="col-md-2" ></div>
                                                     
                                        </form> 
                                        <!--End Formulario-->
                                    </div>
                                </div>
                        </div>
                        </div><!--End modal-->
                    </div><!--End Row-->


                          <div class="row main" style="margin-top: 25px;">
                <div class=" main-tbl">
                    <h3><strong>CARGO</strong></h3>
                    <!--Tabla para mostrar registros-->  
                    <div class="row ">
                        <div class="col-md-10"><input type="text" name="search"  id="buscar" placeholder="Busqueda"></div><div class="col-md-2"> <a id="buttontbl" class="btn btn-primary pull-right" onclick="cargar()"  href="" data-toggle="modal" data-target="#cargo">Nuevo</a></div>
                    </div>
                    <table class="table active " id="tblMostrar" >
                        <thead >
                            <tr id="headertbl">
                                <th>Código</th>
                                <th>Cargo</th>
                                <th style="width: 10%;">Acción</th>
                            </tr>
                        </thead>
                        <%
                            List<Cargo> ls = ccar.mostrarCargo();
                            for (Cargo c: ls) {
                        %>
                        <tr>
                            <td><%= c.getIdCargo()%> </td>
                            <td><%= c.getNombreCargo()%></td>
                            <td id="colAccion" style="width: 50px;">
                                <a   class="btn btn-primary" id="button" href="" data-toggle="modal" data-target="#cargo" onclick="cargar(<%= c.getIdCargo()%>, '<%=c.getNombreCargo()%>')" ><span class="glyphicon glyphicon-plus-sign"></span></a>
                            </td>
                        </tr>
                        <% }%>
                    </table>
                </div>


            </div>

                    
                    <jsp:include page="plantilla/footer.jsp"/> 
                    </body>
                    </html>

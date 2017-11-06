<%-- 
    Document   : candidatoAdmin
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
        <title>JSP Page</title>
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
          
          function cargar(codigo, nombreC, gen, depto, nac, fechaNac, direc, foto,prof)
          {
               $('#txtCodigo').val(codigo);
               $('#txtProfesion').val(prof);
               $('#txtNombreCandidato').val(nombreC);
               document.frmCandidatoAdmin.genero.value=gen;
               document.frmCandidatoAdmin.cmbDepto.value=depto;
               $('#txtNacionalidad').val(nac);
               $('#txtFechaNacimiento').val(fechaNac);
               $('#txtDireccion').val(direc);
               document.frmCandidatoAdmin.txtarchivo.value=foto;
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
            CrudCandidato ccan=new CrudCandidato(); 
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
        <div class="modal fade" id="candidato" tabindex="-1" role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
        <div class="modal-dialog"  id="modal-dialog">
            <div class="panel panel-primary" id="panel-primary">
                <div class="panel-heading" id="panel-heading" >
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="panel-title" id="contactLabel">TeContrato.com</h4>
                </div>
                 <!--Formulario-->
                 <form action="procesarCandidato" method="post" name="frmCandidatoAdmin" enctype="multipart/form-data">
                <div class="modal-body" id="modalBody">               
                    <div class="main-center">
                        <h3><strong>CANDIDATO</strong></h3>
                                <input type="hidden" class="form-control" name="txtCodigo" id="txtCodigo"  placeholder="Código" readonly="true" value="<%=idu %>"/>
                                <!--Pendiente-->
                                <input type="hidden" class="form-control" name="txtIdRol" id="txtIdRol"  placeholder="" readonly="true" value="1"/>
                            <br>
                            <label for="txtNombre" class="cols-sm-2 control-label">Nombre</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
                                <input type="text" class="form-control" name="txtNombreCandidato" id="txtNombreCandidato"  placeholder="Nombre del Candidato"/>
                            </div>
                            
                            <div class="form-group">
                                    <label for="profesion" class="cols-sm-2 control-label">Profesión</label>
                                    <div class="cols-sm-10">
                                        <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                                <input type="text" class="form-control" name="txtProfesion" id="txtProfesion"  placeholder="Profesión" />
                                        </div>
                                    </div>
                               </div>
  
                            <label for="txtGenero" class="cols-sm-2 control-label">Genero</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-venus-mars" aria-hidden="true"></i></span>
                                <%
                                    List<Genero> lsg=ccan.listaGenero();
                                    for(Genero ge:lsg){
                                %>
                                <input type="radio" id="<%=ge.getIdGenero() %>" name="genero" required="true" checked="" value="<%=ge.getIdGenero() %>" >
                                <label for="<%=ge.getIdGenero() %>"><%=ge.getGenero() %></label>
                                <%}%>
                            </div>
                            <br>
                            <label for="txtDepartamento" class="cols-sm-2 control-label">Departamento</label>
                            <div class="input-group" id="select-dark">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-globe" aria-hidden="true"></i></span>
                                <select name="cmbDepto" id="dark" required="true" class="form-control">
                                    <option value="">.::Seleccione Departamento::.</option>
                                    <%
                                        List<Departamento>ls=ccan.listaDepto();
                                        for(Departamento depto:ls){
                                    %>
                                    <option value="<%=depto.getIdDepto() %>"><%=depto.getNombreDepto() %></option>
                                    <%}%>
                                </select>
                            </div>
                            <br>
                            <label for="txtNacionalidad" class="cols-sm-2 control-label">Nacionalidad</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-map" aria-hidden="true"></i></span>
                                <input type="text" class="form-control" name="txtNacionalidad" id="txtNacionalidad"  placeholder="Nacionalidad" required="true"/>
                            </div>
                            <br>
                            <label for="fechaNacimiento" class="cols-sm-2 control-label">Fecha  Nacimiento</label>
                                <div class="input-group date al-date" data-provide="datepicker">
                                        <span class="input-group-addon"><i class="fa fa-calendar" aria-hidden="true"></i></span>
                                        <input type="text" class="form-control" name="txtFechaNacimiento" id="txtFechaNacimiento" placeholder="Fecha Nacimiento" required="true" readonly="" style="color: black;" />
                                </div>
                            <br>
                            <label for="descripcion" class="cols-sm-2 control-label">Dirección</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i></span>
                                <textarea class="form-control" rows="2" name="txtDireccion" id="txtDireccion" placeholder="Direccion..." required="true"></textarea>
                            </div>
                            <br>
                           <div class="form-group ">
                                <label for="foto" class="cols-sm-2 control-label">Foto</label>
                                <div class="cols-sm-10">    
                                    <div class="input-group image-preview" style="text-align: right;" >
                                    <!-- image-preview-filename -->
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-picture" aria-hidden="true"></i></span>
                                    <input type="text" class="form-control image-preview-filename" name="txtarchivo" id="txtarchivo" >
                                    <span class="input-group-btn">
                                            <!-- image-preview-clear -->
                                            <button type="button" class="btn btn-default image-preview-clear" style="display:none;height: 36px;">
                                                <span class="glyphicon glyphicon-remove"></span>
                                            </button>
                                            <!-- image-preview-input -->
                                            <div class="btn btn-default image-preview-input">
                                                <span class="glyphicon glyphicon-folder-open"></span>
                                                <span class="image-preview-input-title">Subir Foto</span>
                                                <input type="file" name="foto" id="foto" accept="image/png, image/jpeg, image/gif"/>
                                            </div>
                                        </span> <!-- /input-group-btn -->
                                    </div><!-- /input-group [image preview] --> 
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

    <div class="row main" style="margin-top: 100px;">
    <div class=" main-tbl">
         <h3><strong>CANDIDATO</strong></h3>
        <!--Tabla para mostrar registros-->  
        <div class="row ">
            <div class="col-md-10"><input type="text" name="search"  id="buscar" placeholder="Busqueda"></div><div class="col-md-2"> <a id="buttontbl" class="btn btn-primary pull-right" onclick="cargar()"  href="" data-toggle="modal" data-target="#habilidad">Nuevo</a></div>
        </div>
        <table class="table active " id="tblMostrar" >
            <thead >
            <tr id="headertbl">
               <th>Código</th>
               <th>Nombre Candidato</th>
               <th>Profesión</th>
               <th>Genero</th>
               <th>Departamento</th>
               <th>Nacionalidad</th>
               <th>Fecha de Nacimiento</th>
               <th>Direccion</th>
               <th>Foto</th>
               <th style="width: 10%;">Acción</th>
             </tr>
           </thead>
            <%
             List<Candidato> ls2 = ccan.mostrarCandidato();
             for(Candidato can:ls2){
           %>
           <tr>
               <td><%= can.getIdCandidato() %> </td>
               <td><%= can.getNombre() %></td>
               <td><%= can.getProfesion() %></td>
               <td><%= can.getGenero().getGenero() %></td>
               <td><%= can.getDepto().getNombreDepto() %></td>
               <td><%= can.getNacionalidad() %></td>
               <td><%= can.getFechaNacimiento() %></td>
               <td><%= can.getDireccion() %></td>
               <td><%= can.getFoto() %></td>
               <td id="colAccion" style="width: 50px;">
                   <a class="btn btn-primary" id="button" href="" data-toggle="modal" data-target="#candidato" onclick="cargar(<%=can.getIdCandidato() %>,'<%=can.getNombre() %>','<%=can.getGenero().getIdGenero() %>','<%=can.getDepto().getIdDepto() %>','<%=can.getNacionalidad() %>','<%=can.getFechaNacimiento() %>','<%=can.getDireccion() %>','<%=can.getFoto() %>',<%=can.getProfesion() %>)" ><span class="glyphicon glyphicon-plus-sign"></span></a>
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

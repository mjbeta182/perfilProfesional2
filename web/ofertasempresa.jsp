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
        <title>Oferta</title>
        <!--Esta es la función para buscar dentro de la tabla
            Necesitas el form donde esta el text de busquedacompleto para remplazar el normal
        -->
        <script language="javascript">
		function doSearch()
		{
			var tableReg = document.getElementById('tblMostrar');//Este es para saber en que tabla buscar
			var searchText = document.getElementById('buscar').value.toLowerCase();//Y el nombre del txt
			var cellsOfRow="";
			var found=false;
			var compareWith="";
 
			// Recorremos todas las filas con contenido de la tabla
			for (var i = 1; i < tableReg.rows.length; i++)
			{
				cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
				found = false;
				// Recorremos todas las celdas
				for (var j = 0; j < cellsOfRow.length && !found; j++)
				{
					compareWith = cellsOfRow[j].innerHTML.toLowerCase();
					// Buscamos el texto en el contenido de la celda
					if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
					{
						found = true;
					}
				}
				if(found)
				{
					tableReg.rows[i].style.display = '';
				} else {
					// si no ha encontrado ninguna coincidencia, esconde la
					// fila de la tabla
					tableReg.rows[i].style.display = 'none';
				}
			}
		}
	</script>
        
        <script Language='JavaScript'>
            
          function cargar(codigo, nombreo, area,cargo,experiencia,contra,departamento,empresa,vacantes,descripcion,edadmin,edadmax)
          {
               $('#txtIdOferta').val(codigo);
               $('#txtNombreOferta').val(nombreo);
               document.frmOferta.cmbArea.value=area;
               document.frmOferta.cmbCargo.value=cargo;
               document.frmOferta.cmbNivelExperiencia.value=experiencia;
               document.frmOferta.cmbTipoContratacion.value=contra;
               document.frmOferta.cmbDepartamento.value=departamento;
               document.frmOferta.cmbEmpresa.value=empresa;
               $('#txtVacantes').val(vacantes);
               $('#txtDescripcion').val(descripcion);
               $('#txtEdadMin').val(edadmin);
               $('#txtEdadMax').val(edadmax);
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
    CrudArea crar   = new CrudArea();
    CrudCargo crca  = new CrudCargo();
    CrudNivelExperiencia crne = new CrudNivelExperiencia();
    CrudTipoContratacion crtc = new CrudTipoContratacion();
    CrudCandidato crucand = new CrudCandidato();
    CrudEmpresa crempre = new CrudEmpresa();
    CrudOferta crof = new CrudOferta();
    if (session.getAttribute("user") != null){
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
                 <form action="procesarOferta" method="post" name="frmOferta" >
                <div class="modal-body" id="modalBody">               
                    <div class="main-center">
                        <h3><strong>OFERTA</strong></h3>
                        
                        
                        <!--
                            <input type="text"  name="idhabilidad" id="idhabilidad"   class="form-control" placeholder="Código" style="display: none;" readonly="true"/>
                            <input type="text" name="nombrehabilidad" id="nombrehabilidad"  class="form-control" placeholder="Nombre"  required="true" />
                        -->
                       <input type="text" class="form-control" name="txtIdOferta" id="txtIdOferta" style="display: none;" placeholder="Codigo"/>
                           
                        <div class="form-group">
                                <label for="area" class="cols-sm-2 control-label">Área</label>
                                <div class="cols-sm-10">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-crosshairs" aria-hidden="true"></i></span>
                                            <select name="cmbArea" id="dark" class="form-control" required="true">
                                                <option value="0">.:Seleccione un área:.</option>
                                                <%
                                                    List<Area> lstArea = crar.mostrarArea();
                                                    for(Area ar:lstArea){
                                                %>
                                                <option value="<%=ar.getIdArea()%>"><%=ar.getNombreArea()%></option>
                                                <%}%>
                                            </select>
                                        </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="cargo" class="cols-sm-2 control-label">Cargo</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="	fa fa-id-card-o" aria-hidden="true"></i></span>
                                        <select name="cmbCargo" id="dark" class="form-control" required="true" >
                                            <option value="0">.:Seleccione un cargo:.</option>
                                            <%
                                                List<Cargo> lstCargo = crca.mostrarCargo();
                                                for(Cargo ca:lstCargo){
                                            %>
                                            <option value="<%=ca.getIdCargo()%>"><%=ca.getNombreCargo()%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="nivelexperiencia" class="cols-sm-2 control-label">Nivel Experiencia</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-hourglass-2" aria-hidden="true"></i></span>
                                        <select name="cmbNivelExperiencia" id="dark" class="form-control" required="true">
                                            <option value="0">.:Seleccione un Nivel:.</option>
                                            <%
                                                List<NivelExperiencia> lstNivExp = crne.mostrarNivelExperiencia();
                                                for(NivelExperiencia nivexp:lstNivExp){
                                            %>
                                            <option value="<%=nivexp.getIdNivelExperiencia()%>"><%=nivexp.getNombreNivelExperiencia()%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="tipocontratacion" class="cols-sm-2 control-label">Tipo Contratación</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-navicon" aria-hidden="true"></i></span>
                                        <select name="cmbTipoContratacion" id="dark" class="form-control" required="true" >
                                            <option value="0">.:Seleccione un Tipo:.</option>
                                            <%
                                                List<TipoContratacion> lstTiCon = crtc.mostrarTipoContratacion();
                                                for(TipoContratacion ticon:lstTiCon){
                                            %>
                                            <option value="<%=ticon.getIdTipoContratacion()%>"><%=ticon.getNombreContratacion()%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                            <label for="departamento" class="cols-sm-2 control-label">Departamento</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-map" aria-hidden="true"></i></span>
                                        <select name="cmbDepartamento" id="dark" class="form-control" required="true">
                                            <option value="0">.:Seleccione un Departamento:.</option>
                                            <%
                                                List<Departamento> lstDepto = crucand.listaDepto();
                                                for(Departamento depto:lstDepto){
                                            %>
                                            <option value="<%=depto.getIdDepto()%>"><%=depto.getNombreDepto()%></option>
                                            <%}%>
                                        </select>
                                </div>
                            </div>
                            </div>
                            <div class="form-group">
                            <label for="empresa" class="cols-sm-2 control-label">Empresa</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-home" aria-hidden="true"></i></span>
                                        <select name="cmbEmpresa" id="dark" class="form-control" required="true">
                                            <option value="0">.:Empresa:.</option>
                                            <%
                                                List<Empresa> lstEmpre = crempre.mostrarEmpresa();
                                                for(Empresa empre:lstEmpre){
                                            %>
                                            <option value="<%=empre.getIdEmpresa()%>"><%=empre.getNombreEmpresa()%></option>
                                            <%}%>
                                        </select>
                                </div>
                            </div>
                            </div>

                            <div class="form-group">
                                <label for="nombreoferta" class="cols-sm-2 control-label">Nombre Oferta</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true" ></i></span>
                                        <input type="text" class="form-control" name="txtNombreOferta" id="txtNombreOferta"  placeholder="Título de Oferta" required="true"/>
                                    </div>
                                </div>
                            </div>
                                    
                            <div class="form-group">
                                <label for="vacantes" class="cols-sm-2 control-label">Vacantes</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                                        <input type="number" class="form-control" name="txtVacantes" id="txtVacantes"  placeholder="Ingrese el número de vacantes" required="true"/>
                                    </div>
                                </div>
                            </div>
                                    
                           <div class="form-group">
                                <label for="descripcion" class="cols-sm-2 control-label">Descripción</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-file" aria-hidden="true"></i></span>
                                        <textarea class="form-control" name="txtDescripcion" id="txtDescripcion"  placeholder="Descripción"/></textarea>
                                    </div>
                                </div>
                            </div>
                                    
                            <div class="form-group">
                                <label for="edadmin" class="cols-sm-2 control-label">Edad Mínima</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                                        <input type="number" class="form-control" name="txtEdadMin" id="txtEdadMin"  placeholder="Edad Mínima" required="true"/>
                                    </div>
                                </div>
                            </div>
                                        
                            <div class="form-group">
                                <label for="edadmax" class="cols-sm-2 control-label">Edad Máxima</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                                        <input type="number" class="form-control" name="txtEdadMax" id="txtEdadMax"  placeholder="Edad Máxima" required="true"/>
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
         <h3><strong>OFERTA</strong></h3>
        <!--Tabla para mostrar registros-->  
        <div class="row ">
            <div class="col-md-10">
            <form>
		<input id="buscar" type="text" placeholder="Busqueda" onkeyup="doSearch()" />
            </form>
            </div>
            <!--<input type="text" name="search"  id="buscar" placeholder="Busqueda">-->
            <div class="col-md-2"> <a id="buttontbl" class="btn btn-primary pull-right" onclick="cargar('','','0','0','0','0','0','0','0','','0','0')"  href="" data-toggle="modal" data-target="#habilidad">Nuevo</a></div>
        </div>
        <table class="table active " id="tblMostrar" name="tblMostrar">
            <thead >
            <tr id="headertbl">
               <th>Código</th>
               <th>Título</th>
               <th>Área</th>
               <th>Cargo</th>
               <th>Experiencia</th>
               <th>Contratación</th>
               <th>Departamento</th>
               <th>Empresa</th>
               <th>Vacantes</th>
               <th>Descripción</th>
               <th>Edad Mín.</th>
               <th>Edad Máx.</th>
               <th style="width: 10%;">Acción</th>
             </tr>
           </thead>
            <%
             List<Oferta> lso = crof.mostrarOferta();
             for( Oferta o:lso){
           %>
           <tr>
               <td><%= o.getIdOferta()%> </td>
               <td><%= o.getNombre()%></td>
               <td><%= o.getArea().getNombreArea()%></td>
               <td><%= o.getCargo().getNombreCargo()%></td>
               <td><%= o.getNivelExperiencia().getNombreNivelExperiencia()%></td>
               <td><%= o.getTipoContratacion().getNombreContratacion()%></td>
               <td><%= o.getDepartamento().getNombreDepto()%></td>
               <td><%= o.getEmpresa().getNombreEmpresa()%></td>
               <td><%= o.getVacantes()%></td>
               <td><%= o.getDescripcion()%></td>
               <td><%= o.getEdadMin()%></td>
               <td><%= o.getEdadMax()%></td>
               <td id="colAccion" style="width: 50px;">
                   <a   class="btn btn-primary" id="button" href="" data-toggle="modal" data-target="#habilidad" onclick="cargar(<%= o.getIdOferta()%>,'<%= o.getNombre()%>',<%= o.getArea().getIdArea()%>,'<%= o.getCargo().getIdCargo()%>','<%= o.getNivelExperiencia().getIdNivelExperiencia()%>','<%= o.getTipoContratacion().getIdTipoContratacion()%>','<%= o.getDepartamento().getIdDepto()%>','<%= o.getEmpresa().getIdEmpresa()%>','<%= o.getVacantes()%>','<%= o.getDescripcion()%>','<%= o.getEdadMin()%>','<%= o.getEdadMax()%>')" ><span class="glyphicon glyphicon-plus-sign"></span></a>
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

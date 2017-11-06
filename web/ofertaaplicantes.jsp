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
    String nombre = "";
    int codigo = 0;
    int rol =0;
    if (session.getAttribute("user").equals(null)){
        response.sendRedirect("index.jsp");
    }
%>    
<!--Este código de acá es para obtener y llenar el id del usuario-->
    <div style="display: none;">
        <%= nombre = (String) session.getAttribute("user") %>
        <% codigo = crof.obtenerid(nombre);%>
        <% rol = crof.obtenerrol(codigo);%>
        <input type="text" name="codUsu" id="codUsu" value="<%= codigo%>">
        <input type="text" name="idrol" id="idrol" value="<%= rol%>">
    </div>
            
    <div class="container-fluid">
        <div class="row" style="width: 100%; position: fixed;z-index: 10;">
            <jsp:include page="plantilla/menu.jsp" />
        </div>
        <div style="margin-top: 60px;">
            ${respuesta}
        </div>
    
    <div class="row main" style="margin-top: 0;">
    <div class=" main-tbl">
         <h3><strong>OFERTA</strong></h3>
        <!--Tabla para mostrar registros-->  
        <div class="row ">
            <div class="col-md-10">
            
            </div>
            <!--<input type="text" name="search"  id="buscar" placeholder="Busqueda">-->
            <div class="col-md-2"> <a id="buttontbl" class="btn btn-primary pull-right" onclick="cargar('','','0','0','0','0','0','0','0','','0','0')"  href="" data-toggle="modal" data-target="#habilidad">Nuevo</a></div>
        </div>
        <table class="table active " id="tblMostrar" name="tblMostrar">
            <thead >
            <tr id="headertbl">
               <th>Código</th>
               <th>Nombre</th>
               <th>Género</th>
               <th>Nacionalidad</th>
               <th>Fecha Nacimiento</th>
               <th>Dirección</th>
               <th>Profesion</th>
               <th>Correo</th>
               <th>Teléfono</th>
               <th style="width: 10%;">Acción</th>
             </tr>
           </thead>
            <%
             List<Oferta> lso = crof.ofertaEmpresa(codigo);
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
               <td id="colAccion" style="width: 15%;">
                   <form action="procesarOferta">
                       <div style="display: none;">
                            <%= nombre = (String) session.getAttribute("user") %>
                            <% codigo = crof.obtenerid(nombre);%>
                            <% rol = crof.obtenerrol(codigo);%>
                            <input type="text" name="codUsu" id="codUsu" value="<%= codigo%>">
                            <input type="text" name="idrol" id="idrol" value="<%= rol%>">
                        </div>
                        <a   class="btn btn-primary" id="button" href="" data-toggle="modal" data-target="#habilidad" onclick="cargar(<%= o.getIdOferta()%>,'<%= o.getNombre()%>',<%= o.getArea().getIdArea()%>,'<%= o.getCargo().getIdCargo()%>','<%= o.getNivelExperiencia().getIdNivelExperiencia()%>','<%= o.getTipoContratacion().getIdTipoContratacion()%>','<%= o.getDepartamento().getIdDepto()%>','<%= o.getEmpresa().getIdEmpresa()%>','<%= o.getVacantes()%>','<%= o.getDescripcion()%>','<%= o.getEdadMin()%>','<%= o.getEdadMax()%>')" ><span class="glyphicon glyphicon-plus-sign"></span></a>
                        <input type="submit" name="btnCandidatos" class="btn btn-primary" value="Candidatos">
                   </form>
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

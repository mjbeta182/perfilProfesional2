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
    <body class="bodyFormulario">
    <%
        CrudArea crar   = new CrudArea();
        CrudCargo crca  = new CrudCargo();
        CrudNivelExperiencia crne = new CrudNivelExperiencia();
        CrudTipoContratacion crtc = new CrudTipoContratacion();
        CrudCandidato crucand = new CrudCandidato();
        CrudEmpresa crempre = new CrudEmpresa();
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
                <div class="row main">
                    <div class="main-login main-center">
                    <h2>Oferta</h2>
                        <form class="" method="post" action="procesarOferta">

                            <div class="form-group">
                                <label for="name" class="cols-sm-2 control-label">Codigo</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                        <input type="text" class="form-control" name="txtIdOferta" id="txtIdOferta"  placeholder="Codigo"/>
                                    </div>
                                </div>
                            </div>

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
                            <div class="form-group ">
                                <input type="submit" id="button" name="btnGuardar" class="btn btn-primary btn-lg btn-block login-button" value="GUARDAR">
                                 <input type="submit" id="button" name="btnModificar" class="btn btn-primary btn-lg btn-block login-button" value="MODIFICAR">
                                 <input type="submit" id="button" name="btnEliminar" class="btn btn-primary btn-lg btn-block login-button" value="ELIMINAR">
                                 <input type="reset" id="button" name="btnCancelar" class="btn btn-primary btn-lg btn-block login-button" value="CANCELAR">
                            </div>

                            </form>
                    </div>
            </div>
        </div>
        <jsp:include page="plantilla/footer.jsp"/> 
    </body>
</html>

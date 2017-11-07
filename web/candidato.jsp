<%-- 
    Document   : candidato
    Created on : 10-06-2017, 10:03:44 PM
    Author     : Kevin López
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.tecontrato.modelo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
               
        <jsp:include page="plantilla/libs.jsp"/>
        <title>Candidato</title>
    </head>
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
    <body class="bodyFormulario">
        <%
            CrudCandidato ccan=new CrudCandidato(); 
            HttpSession sess = request.getSession(true); //use false to use the existing session
            String idu = (String) sess.getAttribute("txtIdUsuario");
            int rol = 0;
            if (session.getAttribute("user") != null){
                
                 rol =(Integer) request.getSession().getAttribute("valor");
                 if(rol==1){
                    
                }else {
                    response.sendRedirect("index.jsp");
                }
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
                    <div class="main-login main-center" style="margin-top: 25px;">
                    <h2>Candidato</h2>
                            <form method="post" action="procesarCandidato" enctype="multipart/form-data" >
                                    
                                    <div class="form-group">
                                        <label for="nombre" class="cols-sm-2 control-label">Nombre</label>
                                            <div class="cols-sm-10">
                                                    <div class="input-group">
                                                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                                            <input type="text" class="form-control" name="txtNombreCandidato" id="txtNombreCandidato"  placeholder="Nombre" required="true" autofocus="true" />
                                                    </div>
                                            </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="nombre" class="cols-sm-2 control-label">Profesión</label>
                                            <div class="cols-sm-10">
                                                    <div class="input-group">
                                                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                                            <input type="text" class="form-control" name="txtProfesion" id="txtProfesion"  placeholder="Profesión" />
                                                    </div>
                                            </div>
                                    </div>
                                
                                    <div class="form-group">
                                            <div class="cols-sm-10">
                                                    <div class="input-group">
                                                        <input type="hidden" class="form-control" name="txtCodigo" id="txtCodigo"  placeholder="Código" required="true" value="<%=idu %>"/>
                                                    </div>
                                            </div>
                                    </div>
                                                    
                                    <div class="form-group">
                                            <div class="cols-sm-10">
                                                    <div class="input-group">
                                                        <input type="hidden" class="form-control" name="txtIdRol" id="txtIdRol"  placeholder="Código" required="true" value="3"/>
                                                    </div>
                                            </div>
                                    </div>
                                
             
                                    <div class="form-group">
                                            <label for="genero" class="cols-sm-2 control-label">Genero</label>
                                            <div class="cols-sm-10">
                                                    <div class="input-group">
                                                            <span class="input-group-addon"><i class="fa fa-venus-mars" aria-hidden="true"></i></span>
                                                            <%
                                                                List<Genero>ls2=ccan.listaGenero();
                                                                for(Genero ge:ls2){
                                                            %>
                                                            <input type="radio" id="<%=ge.getIdGenero() %>" name="genero" required="true" value="<%=ge.getIdGenero() %>" checked>
                                                            <label for="<%=ge.getIdGenero() %>"><%=ge.getGenero() %></label>
                                                            <%}%>
                                                    </div>
                                            </div>
                                    </div> 

                                    <div class="form-group">
                                            <label for="depto" class="cols-sm-2 control-label">Departamento</label>
                                            <div class="cols-sm-10">
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
                                            </div>
                                    </div>                                                   
                                                                                                           
                                    <div class="form-group">
                                            <label for="nacionalidad" class="cols-sm-2 control-label">Nacionalidad</label>
                                            <div class="cols-sm-10">
                                                    <div class="input-group">
                                                        <span class="input-group-addon"><i class="fa fa-map" aria-hidden="true"></i></span>
                                                            <input type="text" class="form-control" name="txtNacionalidad" id="txtNacionalidad"  placeholder="Nacionalidad" required="true"/>
                                                    </div>
                                            </div>
                                    </div>
                                   
                                    <div class="form-group">
                                            <label for="fechaNacimiento" class="cols-sm-2 control-label">Fecha Nacimiento</label>
                                            <div class="cols-sm-10">
                                                    <div class="input-group date al-date" data-provide="datepicker">
                                                            <span class="input-group-addon"><i class="fa fa-calendar" aria-hidden="true"></i></span>
                                                            <input type="text" class="form-control" name="txtFechaNacimiento" id="txtFechaNacimiento" placeholder="Fecha Nacimiento" required="true" readonly="" style="color: #000;"/>
                                                    </div>
                                            </div>
                                    </div>                
                                                    
                                    <div class="form-group">
                                            <label for="descrpcion" class="cols-sm-2 control-label">Dirección</label>
                                            <div class="cols-sm-10">
                                                    <div class="input-group">
                                                            <span class="input-group-addon"><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i></span>
                                                            <textarea class="form-control" rows="2" name="txtDireccion" id="txtDireccion" placeholder="Direccion..." required="true"></textarea>
                                                    </div>
                                            </div>
                                    </div>
                                    
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
                                                                <input type="file" name="foto" id="foto" accept="image/png, image/jpeg, image/gif" required="true" />
                                                            </div>
                                                        </span> <!-- /input-group-btn -->
                                                    </div><!-- /input-group [image preview] --> 
                                                </div>
                                        </div>
                                                    
                                    <div id="botones">
                                        <button type="submit" class="btn btn-success" name="btnGuardar">
                                            <span class="glyphicon glyphicon-floppy-saved"></span> Guardar
                                        </button>
                                        <button type="reset" class="btn btn-primary">
                                            <span class="glyphicon glyphicon-share-alt"></span> Cancelar
                                        </button> 
                                    </div>

                            </form>
                    </div>
              </div>
        </div>      
        <jsp:include page="plantilla/footer.jsp"/> 
    </body>
</html>
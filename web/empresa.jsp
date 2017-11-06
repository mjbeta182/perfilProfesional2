<%-- 
    Document   : empresa
    Created on : 04-oct-2017, 21:44:18
    Author     : lguil
--%>

<%@page import="java.util.List"%>
<%@page import="com.tecontrato.modelo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="plantilla/libs.jsp"/>
        <title>Empresa</title>
        <script Language='JavaScript'>
          
          $(document).ready(function(){
              $('.phone').mask('(000)0000-0000');
          }); 
          
        </script>
    </head>
    <body class="bodyFormulario" id="home">
            <%
                CrudEmpresa cemp = new CrudEmpresa();
                HttpSession sess = request.getSession(true); //use false to use the existing session
                String idu = (String) sess.getAttribute("txtIdUsuario");
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
                    <div class="main-login main-center" style="margin-top: 25px;">
                            <h3><strong>EMPRESA</strong></h3>
                            <form class="" method="GET" action="procesarEmpresa">
                                    <div class="form-group">
                                            <label for="txtNombreEmpresa" class="cols-sm-2 control-label">Nombre</label>
                                            <div class="cols-sm-10">
                                                    <div class="input-group">
                                                            <span class="input-group-addon"><i class="glyphicon glyphicon-user" aria-hidden="true"></i></span>
                                                            <input type="text" class="form-control" name="txtNombreEmpresa" id="txtNombreEmpresa"  placeholder="Nombre de la empresa" required="true"/>
                                                    </div>
                                            </div>
                                    </div>
                                
                                    <div class="form-group">
                                            <div class="cols-sm-10">
                                                    <div class="input-group">
                                                            <input type="hidden" class="form-control" name="txtIdEmpresa" id="txtIdEmpresa"  placeholder="Código" readonly="true" value="<%=idu %>"/>
                                                    </div>
                                            </div>
                                    </div>
                                    
                                    <input type="hidden" class="form-control" name="txtIdRol" id="txtIdRol"  placeholder="" readonly="true" value="2"/>

                                    <div class="form-group">
                                            <label for="txtActividad" class="cols-sm-2 control-label">Actividad</label>
                                            <div class="cols-sm-10">
                                                    <div class="input-group">
                                                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                                                            <input type="text" class="form-control" name="txtActividad" id="txtActividad"  placeholder="Ingrese la actividad de la empresa" required="true"/>
                                                    </div>
                                            </div>
                                    </div>

                                    <div class="form-group">
                                            <label for="txtDescripcion" class="cols-sm-2 control-label">Descripción</label>
                                            <div class="cols-sm-10">
                                                    <div class="input-group">
                                                            <span class="input-group-addon"><i class="glyphicon glyphicon-file" aria-hidden="true"></i></span>
                                                            <textarea class="form-control" rows="2" name="txtDescripcion" id="txtDescripcion" placeholder="Descripción" required="true"></textarea>
                                                    </div>
                                            </div>
                                    </div>

                                    <div class="form-group">
                                            <label for="txtEmail" class="cols-sm-2 control-label">Email</label>
                                            <div class="cols-sm-10">
                                                    <div class="input-group">
                                                            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i></span>
                                                            <input type="email" class="form-control" name="txtEmail" id="txtEmail"  placeholder="Ingrese el correo electronico" required="true"/>
                                                    </div>
                                            </div>
                                    </div>

                                    <div class="form-group">
                                            <label for="txtTelefono" class="cols-sm-2 control-label">Teléfono</label>
                                            <div class="cols-sm-10">
                                                    <div class="input-group">
                                                            <span class="input-group-addon"><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i></span>
                                                            <input type="text" class="form-control phone" name="txtTelefono" id="txtTelefono" required="true"/>
                                                    </div>
                                            </div>
                                    </div>

                                    <div id="botones">
                                        <button type="submit" class="btn btn-success" name="btnGuardar" id="btnGuardar">
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

<%-- 
    Document   : usuarioCandidato
    Created on : 10-08-2017, 03:41:07 PM
    Author     : Kevin López
--%>

<%@page import="com.tecontrato.modelo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="plantilla/libs.jsp"/>
        <title>Usuario</title>
        <script Language='JavaScript'>
          
          $(document).ready(function(){
              $('.phone').mask('(000)0000-0000');
          });       
        </script>
        <script>
            function validarPass(){
                var p1 = $('#txtClave1').val();
                var p2 =  $('#txtClave').val();
                var msj;
                if (p1 != p2) {
                    msj = "<div class='alert alert-danger alert-dismissable fade in'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong style='color:#C0392B;'>Error!</strong> Las contraseñas no coinciden.</div>";
                    $('#mensaje').html(msj);
                    document.frmUsuario.btnGuardar.disabled=true;
                }else{ 
                    msj = "";
                    $('#mensaje').html(msj);
                    document.frmUsuario.btnGuardar.disabled=false;
                }
            }   
        </script>
        <title>Usuario Candidato</title>
    </head>
    <body id="home" class="bodyFormulario">
        <%
            CrudUsuario cus = new CrudUsuario();
            int idUsuario = cus.ultimoId();
            if (session.getAttribute("user")!= null){
                response.sendRedirect("index.jsp");
            }else{ }
        %>
        <div class="container-fluid">
        <div class="row" style="width: 100%; position: fixed;z-index: 10;">
            <jsp:include page="plantilla/menu.jsp" />
        </div> 
            <div class="row main">
                <div class="main-login main-center" style="margin-top: 50px ;">
                    <h3><strong>USUARIO CANDIDATO</strong></h3>
                    <div id="mensaje" class="col-md-12"></div>
                    <form class="" method="GET" action="procesarUsuario" name="frmUsuario">
                            <div class="form-group">
                                    <div class="cols-sm-10">
                                            <div class="input-group">
                                                    <input type="hidden" class="form-control" name="txtIdUsuario" id="txtIdUsuario"  placeholder="Código" value="<%=idUsuario+1 %>"/>
                                            </div>
                                    </div>
                            </div>

                            <div class="form-group">
                                    <label for="txtIdRol" class="cols-sm-2 control-label sr-only">Rol</label>
                                    <div class="cols-sm-10">
                                            <div class="input-group">
                                                <input type="hidden" class="form-control" name="txtIdRol" id="txtIdRol" value="3"/>
                                            </div>
                                    </div>
                            </div>

                            <div class="form-group">
                                    <label for="txtUsuario" class="cols-sm-2 control-label">Usuario</label>
                                    <div class="cols-sm-10">
                                            <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user" aria-hidden="true"></i></span>
                                                    <input type="text" class="form-control" name="txtUsuario" id="txtUsuario" placeholder="Nombre de usuario" required="true"/>
                                            </div>
                                    </div>
                            </div>

                            <div class="form-group">
                                    <label for="txtClave1" class="cols-sm-2 control-label">Contraseña</label>
                                    <div class="cols-sm-10">
                                            <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock" aria-hidden="true"></i></span>
                                                    <input type="password" class="form-control" name="txtClave1" id="txtClave1"  placeholder="Ingrese su contraseña" required="true"/>
                                            </div>
                                    </div>
                            </div>

                            <div class="form-group">
                                    <label for="txtClave" class="cols-sm-2 control-label">Confirmar contraseña</label>
                                    <div class="cols-sm-10">
                                            <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock" aria-hidden="true"></i></span>
                                                    <input type="password" class="form-control" name="txtClave" id="txtClave"  placeholder="Confirme su contraseña" required="true" onkeyup="validarPass()">
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
    </body>
</html>
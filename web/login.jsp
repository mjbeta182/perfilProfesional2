<%-- 
    Document   : login
    Created on : 10-05-2017, 04:55:49 PM
    Author     : Maria Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="plantilla/libs.jsp"/>
        <title>Iniciar Sesión</title>
        <script>
        function enfocar(){
            $('#register').focus();
        }
        </script>
    </head>
    <body class="bodyFormulario" id="home">
    <%
        if (session.getAttribute("user")!= null){
            response.sendRedirect("index.jsp");
        }else{
        
        }
    %>
        <div class="container-fluid">
        <div class="row" style="width: 100%; position: fixed;z-index: 10;">
            <jsp:include page="plantilla/menu.jsp" />
        </div> 

            <div class="col-md-4" ></div>  
              
            <div class="col-md-4" ></div>  
                <div class="row main">
                    <div class="main-login main-center" style="margin-top: 60px;">  
                    <h3><strong>INICIAR SESIÓN</strong></h3>
                    <div id="msj" class="col-md-12" style="margin-top: 20px;" >${message}</div>
                        <form action="logincontroller" class="" method="post"  >
                            <div class="form-group">
                                <label for="username" class="cols-sm-2 control-label">Usuario</label>
                                <div class="cols-sm-10">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-user" aria-hidden="true"></i></span>
                                            <input type="text" class="form-control"  name="user"  placeholder="Usuario">
                                        </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="cols-sm-2 control-label">Contraseña</label>
                                <div class="cols-sm-10">
                                        <div class="input-group">
                                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock" aria-hidden="true"></i></span>
                                                <input type="password" class="form-control" name="pass" placeholder="*******">
                                        </div>
                                </div>
                            </div>
                            
                            <div class="form-group ">   
                                 <input type="submit" id="button" class="btn btn-primary btn-lg btn-block login-button" value="Acceder">
                                 <center ><label style="margin-top: 10px;">¿Aún no tiene una cuenta en TeContrato.com?</label><br><a href="usuarioCandidato.jsp" style="color: #45B39D;" >&nbsp;Regístrese aquí como candidato</a> ó <a href="usuarioEmpresa.jsp" style="color: #45B39D;">&nbsp;aquí como empresa</a></center>
                            </div>
                        </form>
                     </div>
                </div>
        </div>      
        <jsp:include page="plantilla/footer.jsp"/> 
</body>
</html>

<%-- 
    Document   : index
    Created on : 09-21-2017, 03:08:38 PM
    Author     : Maria Jose
--%>
<%@page import="java.util.List"%>
<%@page import="com.tecontrato.modelo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="plantilla/libs.jsp" />
        <title>TeContrato.com</title>
        <style>
            nav.sidebar.navbar {
		border-radius: 0px;
	}

	nav.sidebar, .main{
		-webkit-transition: margin 200ms ease-out;
	    -moz-transition: margin 200ms ease-out;
	    -o-transition: margin 200ms ease-out;
	    transition: margin 200ms ease-out;
	}

	/* Add gap to nav and right windows.*/
	.main{
		padding: 10px 10px 0 10px;
	}

	/* .....NavBar: Icon only with coloring/layout.....*/

	/*small/medium side display*/
	@media (min-width: 768px) {

		/*Allow main to be next to Nav*/
		.main{
			position: absolute;
			width: calc(100% - 40px); /*keeps 100% minus nav size*/
			margin-left: 40px;
			float: right;
		}

		/*lets nav bar to be showed on mouseover*/
		nav.sidebar:hover + .main{
			margin-left: 200px;
		}

		/*Center Brand*/
		nav.sidebar.navbar.sidebar>.container .navbar-brand, .navbar>.container-fluid .navbar-brand {
			margin-left: 0px;
		}
		/*Center Brand*/
		nav.sidebar .navbar-brand, nav.sidebar .navbar-header{
			text-align: center;
			width: 100%;
			margin-left: 0px;
		}

		/*Center Icons*/
		nav.sidebar a{
			padding-right: 13px;
		}

		/*adds border top to first nav box
		nav.sidebar .navbar-nav > li:first-child{
			border-top: 1px #e5e5e5 solid;
		} */

		/*adds border to bottom nav boxes*/
		nav.sidebar .navbar-nav > li{
			border-bottom: 1px #45B39D solid;
		}

		/* Colors/style dropdown box*/
		nav.sidebar .navbar-nav .open .dropdown-menu {
			position: static;
			float: none;
			width: auto;
			margin-top: 0;
			background-color: transparent;
			border: 0;
			-webkit-box-shadow: none;
			box-shadow: none;
		}

		/*allows nav box to use 100% width*/
		nav.sidebar .navbar-collapse, nav.sidebar .container-fluid{
			padding: 0 0px 0 0px;
		}

		/*colors dropdown box text */
		.navbar-inverse .navbar-nav .open .dropdown-menu>li>a {
			color: whitesmoke;
		}

		/*gives sidebar width/height*/
		nav.sidebar{
			width: 300px;
			height: 100%;
			margin-left: -160px;
			float: left;
			z-index: 8000;
			margin-bottom: 0px;
		}

		/*give sidebar 100% width;*/
		nav.sidebar li {
			width: 100%;
		}

		/* Move nav to full on mouse over*/
		nav.sidebar:hover{
			margin-left: 0px;
		}
		/*for hiden things when navbar hidden*/
		.forAnimate{
			opacity: 0;
		}
	}

	/* .....NavBar: Fully showing nav bar..... */

	@media (min-width: 1330px) {

		/*Allow main to be next to Nav*/
		.main{
			width: calc(100% - 200px); /*keeps 100% minus nav size*/
			margin-left: 200px;
		}

		/*Show all nav*/
		nav.sidebar{
			margin-left: 0px;
			float: left;
		}
		/*Show hidden items on nav*/
		nav.sidebar .forAnimate{
			opacity: 1;
		}
	}

	nav.sidebar .navbar-nav .open .dropdown-menu>li>a:hover, nav.sidebar .navbar-nav .open .dropdown-menu>li>a:focus {
		color: #CCC;
	
	}

	nav:hover .forAnimate{
		opacity: 1;
	}
	section{
		padding-left: 15px;
	}
        </style>
        <script>
            function htmlbodyHeightUpdate(){
		var height3 = $( window ).height()
		var height1 = $('.nav').height()+50
		height2 = $('.main').height()
		if(height2 > height3){
			$('html').height(Math.max(height1,height3,height2)+10);
			$('body').height(Math.max(height1,height3,height2)+10);
		}
		else
		{
			$('html').height(Math.max(height1,height3,height2));
			$('body').height(Math.max(height1,height3,height2));
		}
		
	}
	$(document).ready(function () {
		htmlbodyHeightUpdate()
		$( window ).resize(function() {
			htmlbodyHeightUpdate()
		});
		$( window ).scroll(function() {
			height2 = $('.main').height()
  			htmlbodyHeightUpdate()
		});
	});
         </script>  
</head>
<body  class="bodyFormulario" id="home">
    <%
        CrudUsuario          cu  = new CrudUsuario();
        String name = (String) session.getAttribute("user");
        int idCandidato=0;
        if (session.getAttribute("user") != null){
            
            List<Candidato> lst = cu.obtenerDatosC(name);
            for(Candidato c : lst){ 
                idCandidato = c.getIdCandidato();
            }
        }
    %>
 <div class="container-fluid">
        <div class="row" style="width: 100%; position: fixed;z-index: 10;">
            <jsp:include page="plantilla/menu.jsp" />
        </div> 
        <!--Busqueda-->
        <div class="row " id="buscador" style="margin-top: 6%;"  >
  
        </div>
                    <div style="margin-top:10px;">
            ${respuesta}
        </div>
        
    <!-- Sidebar -->

        <div class="row">
            <div class="col-md-3">
                <nav class="navbar navbar-inverse sidebar" role="navigation" style="z-index: 0;">
                <div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-sidebar-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">BUSQUEDA</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
			<ul class="nav navbar-nav">
                            <li><a href="#">
                               <div class="row " id="buscador">
                                    <div class="col-md-12 " >  
                                       <input type="text" name="search"  id="buscar" placeholder="Empresa,Cargo...">
                                    </div>
                                    <div class="col-md-1"></div>
                                </div></a>
                            </li>
				<li ><a href="#">Profesión<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-user"></span></a></li>
				<li ><a href="#">Empresa<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-envelope"></span></a></li>
				<li><a href="#">Actividad<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
				<li ><a href="#">Departamento<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-user"></span></a></li>
				<li ><a href="#">Cargo<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-envelope"></span></a></li>
				<li ><a href="#">Candidatos<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-envelope"></span></a></li>  
			</ul>
		</div>
	</div>
</nav>

            </div>     
            <div class="col-md-6">
                <%CrudEmpresa ce = new CrudEmpresa(); 
                  List<Empresa> lst= ce.empresas();
                  for(Empresa e:lst){
                %>
                <div class="media well" style="margin-left: 20px;">
                <div class="col-md-2">
                    <img src="imagenes/fotos/<%=e.getFoto() %>" title="Empresas" width="150px" height="150px" style="border-radius: 50%;" >
                </div>
                <div class="col-md-10 " style="padding-left: 20%;">
                    <h4><%=e.getNombreEmpresa() %></h4>
                    <h5><%=e.getActividad() %></h5>
                    <h5><%=e.getDescripcion() %></h5>
                    <p><a id="button" class="btn btn-primary btn-large pull-right" href="#">Leer más</a></p>
                </div>
                </div>
                <%}%>
            </div>
            <div class="col-md-3">
                <% CrudCandidato cc = new CrudCandidato(); 
                  List<Candidato> lste= cc.candidatos(idCandidato);
                  for(Candidato c:lste){
                %>
                <div class="media well" style="margin-left: 20px;">
                <div class="col-md-2">
                    <img src="imagenes/fotos/<%=c.getFoto() %>" title="Candidatos" width="60px" height="60px"  style="border-radius: 50%;">
                </div>
                <div class="col-md-10 " style="padding-left: 25%;">
                  <h5><%=c.getNombre() %></h5>
                  <p><a id="button" class="btn btn-primary btn-large pull-right" href="#"><span class="glyphicon-plus">Seguir</span></a></p>
                </div>
            </div>
                <%}%>
        </div>

   
    
 <!-- <div class="col-md-12" id="contact" style="margin-top: 50px;">
    <div class="col-md-4">
        <p><img src="imagenes/logo.png" height="270px"></p>      
    </div>
      <div class="col-md-4" >
  
              <p>Sobre nosotros</p>
              <p>loremkasldjklaskjdlaskjdlaskjdlaskjdlaskjdlaskjdlasml</p>
              <p> askdlask saldks oskposapdis uwuwuwhk   ms </p>
 
      </div>
      <div class="col-md-4">
        <h3><strong>CONTÁCTANOS</strong></h3>
        <form action="#" target="_blank" >
        <p><input class=" form-control" type="text" placeholder="Nombre" required name="Nombre"></p>
        <p><input class="form-control" type="email" placeholder="Correo eléctronico" required name="Correo"></p>
        <p><textarea class="form-control" lang="140"  placeholder="Mensaje" id="txtDescripcion" required name="Mensaje"></textarea></p>
        <p>
            <button class="btn btn-primary" id="buttonL" style="width: 100%;" type="submit">
            <i class="fa fa-paper-plane"></i> ENVIAR
          </button>
        </p>
      </form>
    </div>-->

<!-- Footer -->
    <jsp:include page="plantilla/footer.jsp"/>
</div>
 </div>
</body>
</html>

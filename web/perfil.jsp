<%-- 
    Document   : perfil
    Created on : 10-10-2017, 12:33:30 AM
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
    <title>Curriculum</title>
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
    function cargarIdioma(idioma,nivel){
        $('#cmbIdioma').val(idioma);
        jQuery("input:radio[name=nivelIdioma][value="+nivel+"]").attr('checked',true);
    }
    function cargarCurso(titulo,ins,anio){
        $('#txtTitulo').val(titulo);
        $('#txtInstitucionC').val(ins);
       $('#txtAnioFinalizacion').val(anio);

    }
    function cargarEducacion(especialidad,institucion,aniofin){
        $('#txtEspecialidad').val(especialidad);
        $('#txtInstitucion').val(institucion);
        $('#txtAnioFinalizacion').val(aniofin);
    }
    function cargarExperiencia(nivel){
        $('#cmbNivelExperiencia').val(nivel);
    }
    function cargarExperienciaLaboral(puesto,empresa,inicio,fin){
        $('#txtPuesto').val(puesto);
        $('#txtEmpresa').val(empresa);
        $('#txtFechaInicio').val(inicio);
        $('#txtFechaFin').val(fin);

    }
    function cargarCorreo(correo){
        $('#txtCorreo').val(correo);
    }
    function cargarTelefono(tel){
        $('#txtTelefono').val(tel);
    }
    function cargarHabilidad(habilidad,nivel,valoracion){
        document.frmHabilidad.cmbHabilidad.value = habilidad;
        document.frmHabilidad.nivel.value        = nivel;
         $('#valoracion').val(valoracion);
    }
    $(document).ready(function(){
            $(" [data-toggle=confirmation]").confirmation({
            rootSelector: "[data-toggle=confirmation]",
            // other options
            popout:true,
            singleton:true
            });
          }); 
          
      function cargarCandidato(codigo, nombreC, gen, depto, nac, fechaNac, direc, prof)
      {
           $('#txtCodigo').val(codigo);
           $('#txtProfesion').val(prof);
           $('#txtNombreCandidato').val(nombreC);
           jQuery("input:radio[id="+gen+"][value="+gen+"]").attr('checked',true);
           $('#dark').val(depto);
           $('#txtNacionalidad').val(nac);
           $('#txtFechaNacimiento').val(fechaNac);
           $('#txtDireccion').val(direc);        
      }
       function cargarFoto(foto)
      {
        $('#txtarchivo').val(foto); 
        $("#modViejaBandera").val(foto);
        $("#banderaActual").attr("src","imagenes/fotos/"+foto);
            
      }
    </script>
    <%
        CrudCurriculum      crudcv = new CrudCurriculum();
        CrudExperienciaLaboral cel = new CrudExperienciaLaboral();
        CrudCurso           crudc  = new CrudCurso();
        CrudEducacion       cedu = new CrudEducacion();
        CrudNivelExperiencia cne = new CrudNivelExperiencia();
        CrudCvHabilidad     cch  = new CrudCvHabilidad();
        CrudCvIdioma         cvi = new CrudCvIdioma();
        CrudHabilidad        ch  = new CrudHabilidad();
        CrudCvHabilidad     cvh  = new CrudCvHabilidad();
        CrudIdioma           ci  = new CrudIdioma();
        CrudCandidato       ccan = new CrudCandidato();
        CrudUsuario          cu  = new CrudUsuario();
        CrudCorreo      ccorreo  = new CrudCorreo();
        CrudTelefono       ctel  = new CrudTelefono();
        String name = (String) session.getAttribute("user");
        String foto          ="userdefault.png";
        String nombre        ="";
        String profesion     ="";
        int idCandidato=0;
        Integer rol=0;
        
        if (session.getAttribute("user") != null){
            
            List<Candidato> lst = cu.obtenerDatosC(name);
            for(Candidato c : lst){ 
                idCandidato = c.getIdCandidato();
                foto = c.getFoto();
                nombre = c.getNombre();
                profesion = c.getProfesion();
            }
           
            rol =(Integer) request.getSession().getAttribute("valor");
        }else{
            response.sendRedirect("index.jsp");
        }
        int  idcv = crudcv.ultimoId(idCandidato);

    %>  
</head>
<div class="modal fade" id="candidato" tabindex="-1"  role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
            <div class="modal-dialog"  id="modal-dialog" style="z-index: 100;">
            <div class="panel panel-primary" id="panel-primary">
                <div class="panel-heading" id="panel-heading" >
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="panel-title" id="contactLabel">TeContrato.com</h4>
                </div>
                 <!--Formulario-->
                 <form action="procesarCandidato" method="POST" id="frmCandidatoAdmin" enctype="multipart/form-data" >
                <div class="modal-body" id="modalBody" >               
                    <div class="main-center">
                        <h3><strong>CANDIDATO</strong></h3>
                                <input type="hidden" class="form-control" name="txtCodigo" id="txtCodigo"  placeholder="Código" readonly="true" value="<%=idCandidato %>"/>
                                <!--Pendiente-->
                                <input type="hidden" class="form-control" name="txtIdRol" id="txtIdRol"  placeholder="" readonly="true" value="<%=rol %>"/>
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
                                <input type="radio" id="<%=ge.getIdGenero() %>" name="genero" required="true"  value="<%=ge.getIdGenero() %>" >
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
                   
                                <div id="botones">
                                    <button type="submit" data-toggle="confirmation" name="btnModificar" class="btn btn-warning fa fa-undo" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                                    data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                                    data-title="¿Está seguro de modificar el registro?" data-content="Esto podría ser peligroso"> Modificar</button>
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
                                
<body class="bodyFormulario" id="home">
<div class="container-fluid">  
<div class="row" style="width: 100%; position: fixed;z-index: 5;">
    <jsp:include page="plantilla/menu.jsp" />
</div>
<!--<div style="margin-top:60px;">
    <%--${respuesta}--%>
</div>-->
    <!--Cambiar id  segun formulario con data-toggle="modal" data-target="#id" 
    con esto se manda a llamar el modal  en las etiquetas <a></a> o <button></button> -->
                          
    <!--Nivel Experiencia-->
     <div class="modal fade" id="nivelExperiencia" tabindex="-1" role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
        <div class="modal-dialog"  id="modal-dialog">
            <div class="panel panel-primary" id="panel-primary">
                <div class="panel-heading" id="panel-heading" >
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="panel-title" id="contactLabel">TeContrato.com</h4>
                </div>
             <!--Formulario-->
            <form action="procesarNivelExperiencia" method="post" name="frmNivelExperiencia" >
            <div class="modal-body" id="modalBody">               
                <div class="main-center">              
                    <h3><strong>NIVEL DE EXPERIENCIA</strong></h3>
                    <input type="hidden" name="txtIdCurriculum" value="<%=idcv %>" >
                    <input type="hidden" name="txtIdCandidato" value="<%=idCandidato%>">
                     <div class="form-group ">
                        <div class="cols-sm-10">
                            <div class="input-group"  id="select-dark">
                                <select name="cmbNivelExperiencia" id="cmbNivelExperiencia" class="form-control" required="true">
                                    <option value="0">Seleccionar nivel de experiencia</option> 
                                    <% List<NivelExperiencia> lst7 = cne.mostrarNivelExperiencia() ; 
                                    for(NivelExperiencia ne: lst7){%>
                                    <option value="<%=ne.getIdNivelExperiencia() %>"><%=ne.getNombreNivelExperiencia() %></option> 
                                    <%}%>
                                </select><br>
                            </div>
                        </div>
                     </div>     
                        <div id="botones">
                            <button type="submit" data-toggle="confirmation" name="btnModificar" class="btn btn-warning fa fa-undo" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                            data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                            data-title="¿Está seguro de modificar el registro?" data-content="Esto podría ser peligroso"> Modificar</button>
                            <button data-dismiss="modal"   class="btn btn-primary" >Cancelar</button>  
                        </div>
                    </div>
                </div>       
            </form><!--End Formulario-->
        </div>
        </div>
    </div><!--End modal-->
    
    
    <!--Formulario Experiencia Laboral-->
     <div class="modal fade" id="experienciaLaboral" tabindex="-1" role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
        <div class="modal-dialog"  id="modal-dialog">
            <div class="panel panel-primary" id="panel-primary">
                <div class="panel-heading" id="panel-heading" >
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="panel-title" id="contactLabel">TeContrato.com</h4>
                </div>
             <!--Formulario-->
            <form action="procesarExperienciaLaboral" method="post" name="frmExperienciaLaboral" >
            <div class="modal-body" id="modalBody">               
                <div class="main-center">              
                    <h3><strong>EXPERIENCIA LABORAL</strong></h3>
                    <input type="hidden" name="txtIdCurriculum" value="<%=idcv %>" >
                    <input type="hidden" name="txtIdRol" value="<%=rol %>">
                        <div class="form-group">
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                     <input type="text" name="txtPuesto" id="txtPuesto" placeholder="Puesto" class="form-control"> 
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                     <input type="text" name="txtEmpresa" id="txtEmpresa" placeholder="Empresa" class="form-control"> 
                                </div>
                            </div>
                        </div>       
                         <div class="form-group">
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                     <input type="date" class="form-control" name="txtFechaInicio"  id="txtFechaInicio" placeholder="Fecha Nacimiento" required="true"/>
                                </div>
                            </div>
                        </div>        
                         <div class="form-group">
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                     <input type="date" class="form-control" name="txtFechaFin" id="txtFechaFin" placeholder="Fecha Nacimiento" required="true"/>
                                </div>
                            </div>
                        </div>                           
                        <div id="botones">
                            <button type="submit" name="btnGuardar" class="btn btn-primary "> <span class="glyphicon glyphicon-floppy-disk"></span>Guardar</button>
                            <button type="submit" data-toggle="confirmation" name="btnModificar" class="btn btn-warning " data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                            data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                            data-title="¿Está seguro de modificar el registro?" data-content="Esto podría ser peligroso"><span class="glyphicon glyphicon-pencil"></span>Modificar</button>
                            <button type="submit" data-toggle="confirmation" name="btnEliminar" class="btn btn-danger" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                            data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                            data-title="¿Está seguro de eliminar el registro?" data-content="Esto podría ser peligroso"><span class="glyphicon glyphicon-remove"></span>Eliminar</button>
                            <button data-dismiss="modal"   class="btn btn-primary" >Cancelar</button>  
                        </div>
                    </div>
                </div>       
            </form><!--End Formulario-->
        </div>
        </div>
    </div><!--End modal-->
    
    
    
    <!--Formulario Educacion Academica-->
    <div class="modal fade" id="educacion" tabindex="-1" role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
        <div class="modal-dialog"  id="modal-dialog">
            <div class="panel panel-primary" id="panel-primary">
                <div class="panel-heading" id="panel-heading" >
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="panel-title" id="contactLabel">TeContrato.com</h4>
                </div>
             <!--Formulario-->
            <form action="procesarEducacion" method="post" name="frmEducacion" >
            <div class="modal-body" id="modalBody">               
                <div class="main-center">              
                    <h3><strong>EDUCACION ACADÉMICA</strong></h3>
                    <input type="hidden" name="txtIdCurriculum" value="<%=idcv %>" >
                    <input type="hidden" name="txtIdRol" value="<%=rol %>">
                      <div class="form-group">
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                   <input type="text" name="txtEspecialidad" id="txtEspecialidad" placeholder="Especialidad" class="form-control"> 
                                </div>
                            </div>
                        </div>               
                        <div class="form-group">
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                    <input type="text" name="txtInstitucion" id="txtInstitucion" placeholder="Institución" class="form-control"> 
                                </div>
                            </div>
                        </div>         
                         <div class="form-group">
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                    <input type="number" name="txtAnioFinalizacion" id="txtAnioFinalizacion" placeholder="1990" min="1990" max="2017" class="form-control"> 
                                </div>
                            </div>
                        </div>        
                        <div id="botones">
                            <button type="submit" name="btnGuardar" class="btn btn-primary fa fa-save"> Guardar</button>
                            <button type="submit" data-toggle="confirmation" name="btnModificar" class="btn btn-warning fa fa-undo" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                            data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                            data-title="¿Está seguro de modificar el registro?" data-content="Esto podría ser peligroso"> Modificar</button>
                            <button type="submit" data-toggle="confirmation" name="btnEliminar" class="btn btn-danger fa fa-close" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                            data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                            data-title="¿Está seguro de eliminar el registro?" data-content="Esto podría ser peligroso"> Eliminar</button>
                            <button data-dismiss="modal"   class="btn btn-primary" >Cancelar</button>  
                        </div>
                    </div>
                </div>       
            </form><!--End Formulario-->
        </div>
        </div>
    </div><!--End modal-->
    
    
    
     <!--Formulario Cursos-->
    <div class="modal fade" id="cursos" tabindex="-1" role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
        <div class="modal-dialog"  id="modal-dialog">
            <div class="panel panel-primary" id="panel-primary">
                <div class="panel-heading" id="panel-heading" >
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="panel-title" id="contactLabel">TeContrato.com</h4>
                </div>
             <!--Formulario-->
            <form action="procesarCurso" method="post" name="frmCursos" >
            <div class="modal-body" id="modalBody">               
                <div class="main-center">              
                    <h3><strong>CURSOS</strong></h3>
                    <input type="hidden" name="txtIdCurriculum" value="<%=idcv %>" >
                    <input type="hidden" name="txtIdRol" value="<%=rol %>">
                        <div class="form-group">
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                    <input type="text" name="txtTitulo" id="txtTitulo" placeholder="Titulo" class="form-control"> 
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                    <input type="text" name="txtInstitucion" id="txtInstitucionC" placeholder="Institución" class="form-control">
                                </div>
                            </div>
                        </div>   
                        <div class="form-group">
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                    <input type="number" name="txtAnioFinalizacion" id="txtAnioFinalizacion" value="2017" min="1990" max="2017" class="form-control"> 
                                </div>
                            </div>
                        </div>   
                        <div id="botones">
                            <button type="submit" name="btnGuardar" class="btn btn-primary "> <span class="glyphicon glyphicon-floppy-disk"></span>Guardar</button>
                            <button type="submit" data-toggle="confirmation" name="btnEliminar" class="btn btn-danger" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                            data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                            data-title="¿Está seguro de eliminar el registro?" data-content="Esto podría ser peligroso"><span class="glyphicon glyphicon-remove"></span>Eliminar</button>
                            <button data-dismiss="modal"   class="btn btn-primary" >Cancelar</button>  
                        </div>
                </div>
            </div>       
            </form><!--End Formulario-->
        </div>
        </div>
    </div><!--End modal-->
    
    
    
     <!--Formulario Idioma-->  
    <div class="modal fade" id="idioma" tabindex="-1" role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
        <div class="modal-dialog"  id="modal-dialog">
            <div class="panel panel-primary" id="panel-primary">
                <div class="panel-heading" id="panel-heading" >
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="panel-title" id="contactLabel">TeContrato.com</h4>
                </div>
             <!--Formulario-->
            <form action="procesarCvIdioma" method="get" name="frmIdioma" >
            <div class="modal-body" id="modalBody">               
                <div class="main-center">              
                    <h3><strong>IDIOMA</strong></h3>
                    <input type="hidden" name="txtIdCurriculum"  value="<%=idcv %>" >
                    <div class="form-group ">
                        <div class="cols-sm-10">
                            <div class="input-group"  id="select-dark">
                                <select name="cmbIdioma" id="cmbIdioma"  class="form-control" required="true">
                                    <option value="0" >Seleccionar idioma</option> 
                                    <% List<Idioma> lst3 = ci.mostrarIdioma() ; for(Idioma i: lst3){%>
                                    <option value="<%=i.getIdIdioma() %>"><%=i.getNombreIdioma() %></option> 
                                    <%}%>
                                </select><br>
                            </div>
                        </div>
                     </div>          
                    <div class="form-group ">
                        <div class="cols-sm-10">
                            <div class="input-group" style="text-align: left;">
                                <p style="margin-left: 5%;">Nivel:</p>
                                <% List<Nivel> lst4 = cvh.listaNivel() ; for(Nivel n: lst4){%>
                                <input type="radio" id="<%=n.getNivel() %>"  name="nivelIdioma"   value="<%=n.getIdNivel() %>" name="radio-group">
                                <label for="<%=n.getNivel() %>">&nbsp;<%=n.getNivel() %></label><br>
                                 <%}%>
                            </div>
                        </div>
                     </div>                 
                    <div id="botones">
                        <button type="submit" name="btnGuardar" id="btnGuardar" class="btn btn-primary "> <span class="glyphicon glyphicon-floppy-disk"></span>Guardar</button>
                        <button type="submit" data-toggle="confirmation" name="btnModificar" class="btn btn-warning " data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                        data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                        data-title="¿Está seguro de modificar el registro?" ><span class="glyphicon glyphicon-pencil"></span>Modificar</button>
                        <button type="submit" data-toggle="confirmation" name="btnEliminar" class="btn btn-danger" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                        data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                        data-title="¿Está seguro de eliminar el registro?" ><span class="glyphicon glyphicon-remove"></span>Eliminar</button>
                        <button data-dismiss="modal"  class="btn btn-primary" >Cancelar</button>  
                    </div>
                </div>
            </div>       
            </form><!--End Formulario-->
        </div>
        </div>
    </div><!--End modal-->
    
    
    
    <!--Formulario Habilidad-->
    <div class="modal fade" id="telefono" tabindex="-1" role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
        <div class="modal-dialog"  id="modal-dialog">
            <div class="panel panel-primary" id="panel-primary">
                <div class="panel-heading" id="panel-heading" >
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="panel-title" id="contactLabel">TeContrato.com</h4>
                </div>
                 <!--Formulario-->
                <form action="procesarTelefono" method="get" name="frmTelefono" >
                <div class="modal-body" id="modalBody">               
                    <div class="main-center">
                        <h3><strong>TELÉFONO</strong></h3>
                        
                        <input type="hidden" name="txtIdCandidato" value="<%=idCandidato %>">

                        <div class="form-group">
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                    <input type="text" name="txtTelefono" id="txtTelefono" placeholder="####-####" class="form-control" required="true">
                                </div>
                            </div>
                        </div>

                        <div id="botones">
                            <button type="submit" name="btnGuardar" id="btnGuardar" class="btn btn-primary "> <span class="glyphicon glyphicon-floppy-disk"></span>Guardar</button>
                            <button type="submit" data-toggle="confirmation" name="btnEliminar" id="btnEliminar" class="btn btn-danger" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                            data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                            data-title="¿Está seguro de eliminar el registro?" ><span class="glyphicon glyphicon-remove"></span>Eliminar</button>
                            <button class="btn btn-primary" data-dismiss="modal" >Cancelar</button>  
                        </div>    
                    </div>        
                </div>
                </form><!--End Formulario-->
            </div>
        </div>
    </div><!--End modal-->
    
 <!--Formulario Habilidad-->
    <div class="modal fade" id="correo" tabindex="-1" role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
        <div class="modal-dialog"  id="modal-dialog">
            <div class="panel panel-primary" id="panel-primary">
                <div class="panel-heading" id="panel-heading" >
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="panel-title" id="contactLabel">TeContrato.com</h4>
                </div>
                 <!--Formulario-->
                <form action="procesarCorreo" method="post" name="frmCorreo" >
                <div class="modal-body" id="modalBody">               
                    <div class="main-center">
                        <h3><strong>CORREO</strong></h3>
                        
                        <input type="hidden" name="txtIdCandidato" value="<%=idCandidato %>"><!--Aqui idCurriculum-->
                        <input type="hidden" name="txtIdRol" value="<%=rol%>"><!--Aqui se debe modificar rol-->

                        <div class="form-group">
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"></span>
                                    <input type="email" name="txtCorreo" id="txtCorreo" placeholder="Correo" class="form-control" required="true">
                                </div>
                            </div>
                        </div>

                        <div id="botones">
                            <button type="submit" name="btnGuardar" id="btnGuardar" class="btn btn-primary "> <span class="glyphicon glyphicon-floppy-disk"></span>Guardar</button>
                            <button type="submit" data-toggle="confirmation" name="btnEliminar" id="btnEliminar" class="btn btn-danger" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                            data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                            data-title="¿Está seguro de eliminar el registro?" ><span class="glyphicon glyphicon-remove"></span>Eliminar</button>
                            <button class="btn btn-primary" data-dismiss="modal" >Cancelar</button>  
                        </div>    
                    </div>        
                </div>
                </form><!--End Formulario-->
            </div>
        </div>
    </div><!--End modal-->   
    
     <!--Formulario Foto-->
    <div class="modal fade" id="foto" tabindex="-1" role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
        <div class="modal-dialog"  id="modal-dialog">
            <div class="panel panel-primary" id="panel-primary">
                <div class="panel-heading" id="panel-heading" >
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="panel-title" id="contactLabel">TeContrato.com</h4>
                </div>
                 <!--Formulario-->
                <form action="procesarCandidato" method="POST" name="frmFoto" enctype="multipart/form-data" >
                <div class="modal-body" id="modalBody">               
                    <div class="main-center">
                        <h3><strong>SUBIR FOTO</strong></h3>
                        
                        <input type="hidden" name="txtIdCandidato" value="<%=idCandidato %>">
                        <input type="hidden" name="txtIdRol" value="<%=rol%>"><!--Aqui se debe modificar rol-->

                        <div class="form-group ">
                            <div class="cols-sm-10">  
                                <div id="profile-userpic" style="margin-bottom: 20px" >
                                    <img src="imagenes/arena.png" alt="bandera" class="bandera img-responsive" id="banderaActual" style="margin-top: 15px;margin: auto;width: 120px;height: 120px; border-radius: 50%;">
                                </div>
                                <div class="input-group image-preview" style="text-align: right;" >
                                <!-- image-preview-filename -->
                                <span class="input-group-addon"><i class="glyphicon glyphicon-picture" aria-hidden="true"></i></span>
                                <input type="text" class="form-control image-preview-filename" name="txtarchivo" id="txtarchivo" >
                                <input type="hidden" id="modViejaBandera" name="modViejaBandera">
                                <span class="input-group-btn">
                                        <!-- image-preview-clear -->
                                        <button type="button" class="btn btn-default image-preview-clear" style="display:none;height: 36px;">
                                            <span class="glyphicon glyphicon-remove"></span>
                                        </button>
                                        <!-- image-preview-input -->
                                        <div class="btn btn-default image-preview-input">
                                            <span class="glyphicon glyphicon-folder-open"></span>
                                            <span class="image-preview-input-title">Subir Foto</span>
                                            <input type="file" name="foto" id="foto" accept="image/png, image/jpeg, image/gif" />
                                        </div>
                                    </span> <!-- /input-group-btn -->
                                </div><!-- /input-group [image preview] --> 
                            </div>
                         </div> 

                        <div id="botones">
                            <button type="submit" data-toggle="confirmation" name="btnModFoto" id="btnModificar" class="btn btn-warning " data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                            data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                            data-title="¿Está seguro de modificar el registro?" ><span class="glyphicon glyphicon-pencil"></span>Guardar</button>
                            <button type="submit" data-toggle="confirmation" name="btnEliminarFoto" id="btnEliminar" class="btn btn-danger" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                            data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                            data-title="¿Está seguro de eliminar el registro?" ><span class="glyphicon glyphicon-remove"></span>Eliminar</button>
                            <button class="btn btn-primary" data-dismiss="modal" >Cancelar</button>  
                        </div>    
                    </div>        
                </div>
                </form><!--End Formulario-->
            </div>
        </div>
    </div><!--End modal-->  
    
     
    
<div class="row">
    <div class="col-md-4" style="margin-top: 100px;">
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
                   <a class="navbar-brand" href="perfil.jsp">Bienvenid@ <%=nombre %></a>
           </div>
           <!-- Collect the nav links, forms, and other content for toggling -->
           <div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
                   <ul class="nav navbar-nav">
                        <li style="border-style: none;">
                        <div id="profile-userpic">
                            <img id="profile" src="imagenes/fotos/<%=foto%>" class="img-responsive" style="margin-top: 15px;margin-bottom: 15px;">
                        </div>
                        </li>
                         <li style="border-style: none;">
                             <a class="navbar-brand"><h4 style="color: whitesmoke;"> <%=profesion %></h4></a>
                        </li>
                        <li>
                        <%
                        List<Candidato> ls2 = cu.obtenerDatosC(name);
                        for(Candidato can:ls2){
                           %>
                           <a  data-toggle="modal" data-target="#candidato" onclick="cargarCandidato(<%=can.getIdCandidato() %>,'<%=can.getNombre() %>','<%=can.getGenero().getIdGenero() %>','<%=can.getDepto().getIdDepto() %>','<%=can.getNacionalidad() %>','<%=can.getFechaNacimiento() %>','<%=can.getDireccion() %>','<%=can.getProfesion() %>')" >
                            Editar perfil<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-user"></span> </a>
                        </li>
                        <li><a data-toggle="modal" data-target="#foto" onclick="cargarFoto('<%=can.getFoto()%>')" >
                            Subir foto<span class=" pull-right hidden-xs showopacity glyphicon glyphicon-camera"></span></a></li>   
                        <% } %>
                        <li><a href="#">Aplicación de trabajo<span class="badge pull-right">3</span></a></li>
                        <li><a href="#">Siguiendo<span class="badge pull-right">5</span></a></li>
                        <li ><a href="perfil.jsp">Actualizar curriculum<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-edit"></span></a></li>
                        <li ><a href="curriculum.jsp">Ver Curriculum<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-list-alt"></span></a></li>
                        <li ><a href="#"><form action='logoutcontroller' method='post'>Cerrar Sesión<button id='buttonL'  style=" border-radius: 5px;margin-top: -4px;" class="pull-right" ><span class='glyphicon glyphicon-off ' style="color: whitesmoke" ></span></button></form></a></li>
                   </ul>
             </div>
            </div>
            </nav>
        </div>

    <div class="col-md-7 main-perfil" style="margin-top: 100px;">
        
      <!--Aqui se mostrara curriculum -->
        <h3><strong>CURRICULUM VITAE</strong> </h3>
        <p>Todos los datos del curriculum podran ser visualizados por toda empresa que lo consulte.</p>     
        <form action="" method="get" name="frmExperienciaLaboral" >
            <table class="table table-bordered" id="tblMostrar" >
            <thead >
            <tr id="headertbl">
             <% 
                List<Curriculum>  cvne = crudcv.mostrarCurriculum(idcv);
                for(Curriculum cv: cvne){
            %>
                <th colspan="3">NIVEL DE EXPERIENCIA <a id="button" class="btn btn-primary pull-right" href="" data-toggle="modal" data-target="#nivelExperiencia" onclick="cargarExperiencia(<%= cv.getNivelExperiencia().getIdNivelExperiencia() %> )" ><span class="glyphicon glyphicon-plus "></span> Modificar</a> </th>
             </tr>
           </thead>
           <tbody>
            <tr>
                <td  style="border-right:none;border-left: none; width: 250px;">
                    Nivel de experiencia   :
                </td>
                <td style="border-right:none;border-left: none;">
                    <%=cv.getNivelExperiencia().getNombreNivelExperiencia() %>
                </td>
               <td style="border-left:none;text-align: right;"><a href="" class="link" data-toggle="modal" data-target="#nivelExperiencia" onclick="cargarExperiencia(<%= cv.getNivelExperiencia().getIdNivelExperiencia() %> )">seleccionar</a></td>
            </tr>
            <%}%>
           </tbody>
        </table>
        </form>
           
            <form action="" method="get" name="" >
            <table class="table table-bordered" id="tblMostrar">
            <thead >
            <tr id="headertbl">
                <th colspan="2">TELÉFONO <a id="button" class="btn btn-primary pull-right" href="" data-toggle="modal" data-target="#telefono" onclick="cargarTelefono()" ><span class="glyphicon glyphicon-plus "></span> Agregar</a> </th>
             </tr>
           </thead>
           <tbody>
            
            <% 
                 List<Telefono>  lsttel = ctel.telefonos(idCandidato);
                 for(Telefono tel: lsttel ){
                    
            %> 
            <tr>
                <td  style="border-right:none;border-left: none; width: 250px;">
                  
                        +(503) <%=tel.getTelefono()%><br>
                    
                </td>
                <td style="border-left:none;text-align: right;"><a href="" class="link" data-toggle="modal" data-target="#telefono" onclick="cargarTelefono('<%=tel.getTelefono()%>')" >seleccionar</a></td>
                </tr>
            <%}%>
            
        </tbody>
        </table>
        </form>
           
           <form action="" method="get" name="" >
            <table class="table table-bordered" id="tblMostrar">
            <thead >
            <tr id="headertbl">
                <th colspan="3">CORREO <a id="button" class="btn btn-primary pull-right" href="" data-toggle="modal" data-target="#correo" onclick="cargarCorreo()" ><span class="glyphicon glyphicon-plus "></span> Agregar</a> </th>
             </tr>
           </thead>
           <tbody>
               
            <% 
                 List<Correo>  lstcorreo = ccorreo.correos(idCandidato) ;
                 int k = 0;
                 for(Correo correo: lstcorreo){
                    
            %> 
             <tr>
                <td  style="border-right:none;border-left: none; width: 250px;">
              
                        <%=correo.getCorreo()%><br>
                
                </td>
                <td style="border-left:none;text-align: right;"><a href="" class="link" data-toggle="modal" data-target="#correo" onclick="cargarCorreo('<%=correo.getCorreo()%>')" >seleccionar</a></td>
                 </tr>
            <%}%>

        </tbody>
        </table>
        </form>
        
        <form action="" method="get" name="frmExperienciaLaboral" >
            <table class="table table-bordered" id="tblMostrar">
            <thead >
            <tr id="headertbl">
                <th colspan="3">EXPERIENCIA LABORAL <a id="button" class="btn btn-primary pull-right" href="" data-toggle="modal" data-target="#experienciaLaboral" onclick="cargarExperienciaLaboral()" ><span class="glyphicon glyphicon-plus "></span> Agregar</a> </th>
             </tr>
           </thead>
           <tbody>
            <% 
                 List<ExperienciaLaboral>  cvel = cel.mostrarExperienciaLaboral(idcv);
                 for(ExperienciaLaboral elab: cvel){
            %>
            <tr>
                <td  style="border-right:none;border-left: none; width: 250px;">
                    Cargo   :<br>
                    Empresa    :<br>
                    Período    :
                </td>
                <td style="border-right:none;border-left: none;">
                    <%=elab.getPuesto() %><br>
                    <%=elab.getNombreEmpresa() %><br>
                    <%=elab.getFechaInicio() %> |  <%=elab.getFechaFin() %>
                </td>
                <td style="border-left:none;text-align: right;"><a href="" class="link" data-toggle="modal" data-target="#experienciaLaboral" onclick="cargarExperienciaLaboral('<%=elab.getPuesto() %>','<%=elab.getNombreEmpresa() %>','<%=elab.getFechaInicio() %>','<%=elab.getFechaFin() %>')" >seleccionar</a></td>
            </tr>
            <%}%>
        </tbody>
        </table>
        </form>
        
          <form action="" method="" name="frmEducacion" >
            <table class="table table-bordered" id="tblMostrar">
            <thead >
            <tr id="headertbl">
                <th colspan="3">EDUCACIÓN ACADÉMICA<a id="button" class="btn btn-primary pull-right" href="" data-toggle="modal" data-target="#educacion" onclick="cargarEducacion()" ><span class="glyphicon glyphicon-plus "></span> Agregar</a></th>
             </tr>
           </thead>
           <tbody>
            <% 
                List<Educacion>  cveducacion = cedu.mostrarEducacion(idcv);
                for(Educacion e: cveducacion){
            %>
            <tr>
                <td  style="border-right:none;border-left: none; width: 250px;">
                    Especialidad/Titulo   :<br>
                    Institución    :<br>
                    Año de finalización    :
                </td>
                <td style="border-right:none;border-left: none;">
                    <%=e.getEspecialidad() %><br>
                    <%=e.getInstitucion() %><br>
                    <%=e.getAnioFinalizacion() %>
                </td>
                <td style="border-left:none;text-align: right;" ><a href="" class="link" data-toggle="modal" data-target="#educacion" onclick="cargarEducacion('<%=e.getEspecialidad()%>','<%=e.getInstitucion()%>','<%=e.getAnioFinalizacion()%>')">seleccionar</a></td>
                </tr>
            <%}%>
            </tbody>
        </table>
        </form>
        
        <form action="" method="" name="frmCursos" >
            <table class="table table-bordered" id="tblMostrar">
            <thead >
            <tr id="headertbl">
                <th colspan="3">CURSOS <a id="button" class="btn btn-primary pull-right" href="" data-toggle="modal" data-target="#cursos" onclick="cargarCurso()" ><span class="glyphicon glyphicon-plus "></span> Agregar</a></th>
             </tr>
           </thead>
           <tbody>
            <%
                List<Curso> cvcurso = crudc.mostrarCurso(idcv);
                for(Curso c:cvcurso){
            %>
            <tr>
                <td  style="border-right:none;border-left: none; width: 250px;">
                    Titulo   :<br>
                    Institución :<br>
                    Año de finalización :
                </td>  
                <td style="border-right:none;border-left: none;">
                      <%=c.getTitulo() %><br>
                      <%=c.getInstitucion() %><br>
                      <%=c.getAnioFinalizacion() %>
                </td> 
                <td  style="border-left:none;text-align: right;" ><a  class="link"  href="" data-toggle="modal" data-target="#cursos" onclick="cargarCurso('<%=c.getTitulo()%>','<%=c.getInstitucion()%>','<%=c.getAnioFinalizacion()%>')">seleccionar</a></td>
            </tr>
           <%}%>
           </tbody>
        </table>
        </form>           

        <!--Tabla CvIdioma-->
        <form action="" method="" name="frmIdioma" >
            <table class="table table-bordered" id="tblMostrar">
                <thead >
                <tr id="headertbl">
                    <th colspan="3">IDIOMA <a id="button" class="btn btn-primary pull-right" href="" data-toggle="modal" data-target="#idioma" onclick="cargarIdioma()" ><span class="glyphicon glyphicon-plus "></span> Agregar</a></th>
                </tr>
               </thead>
               <tbody>
                <% 
                List<CvIdioma>  cvidioma = cvi.mostrarCvIdioma(idcv);
                for(CvIdioma i:cvidioma){
                %>      
                <tr>
                    <td  style="border-right:none;border-left: none; width: 250px;">
                        Idioma    :<br>
                        Nivel    :
                    </td>
                    <td style="border-right:none;border-left: none;">
                        <%=i.getIdioma().getNombreIdioma() %><br>
                        <%=i.getNivel().getNivel() %>
                    </td>
                    <td style="border-left:none;text-align: right;"><a href="" data-toggle="modal" data-target="#idioma"  class="link" onclick="cargarIdioma(<%=i.getIdioma().getIdIdioma()%>,<%=i.getNivel().getIdNivel()%>)" >seleccionar</a></td>
                </tr>
                <%}%>
               </tbody>
            </table>
        </form>
         
        <!--Tabla Habilidades-->
        <form action="" method="" name="frmHabilidades" >
            <table class="table table-bordered" id="tblMostrar">
                <thead >
                <tr id="headertbl">
                    <th colspan="3" style="border-right:none;">HABILIDADES <a id="button" class="btn btn-primary pull-right" href="" data-toggle="modal" data-target="#habilidad" onclick="cargarHabilidad()" ><span class="glyphicon glyphicon-plus "></span> Agregar</a></th>
                </tr>
               </thead>
               <tbody>
                <% 
                List<CvHabilidad>  cvhabilidad = cch.mostrarCvHabilidad(idcv) ;
                for(CvHabilidad h:cvhabilidad){
               %>    
               <tr>
                    <td style="border-right:none;border-left: none; width: 250px;">
                        Habilidad:<br>
                        Nivel    :
                    </td>
                    <td style="border-right:none;border-left: none;">    
                        <%=h.getHabilidad().getNombreHabilidad() %><br>
                        <%=h.getNivel().getNivel() %>  
                    </td>
                    <td style="border-left:none;text-align: right;"><a  href="" data-toggle="modal" data-target="#habilidad" class="link" onclick="cargarHabilidad(<%=h.getHabilidad().getIdHabilidad() %>,<%=h.getNivel().getIdNivel() %>,<%=h.getValoracion()%>)">seleccionar</a></td>
                </tr>
                <%}%>
               </tbody>
            </table>
        </form>
        
        <!--Boton Finalidar-->
        <center>
            <button type="submit" data-toggle="confirmation" name="btnModificar" id="buttonL"  class="btn btn-primary  btn-block " data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt"
                data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"
                data-title="¿Está seguro de finalizar la edición de su curriculum?"><span class="glyphicon glyphicon-saved "></span> Finalizar
            </button>
        </center>
    
</div><!--Fin de main-cv--> 

<div class="col-md-1"></div>
</div>
<!--Footer-->
<jsp:include page="plantilla/footer.jsp" />
</div>
</body>
</html>

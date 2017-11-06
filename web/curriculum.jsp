<%-- 
    Document   : curriculum
    Created on : 10-06-2017, 11:12:58 PM
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

</head>
<body class="bodyFormulario" id="home">
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
        CrudTelefono         ct  = new CrudTelefono();
        CrudCorreo       ccorreo = new CrudCorreo();
        String name = (String) session.getAttribute("user");
        String foto          ="userdefault.png";
        String nombre        ="";
        String direccion     ="";
        int edad             =0;
        int idCandidato      =0;
        Integer rol          =0;
        
        if (session.getAttribute("user") != null){
            
            List<Candidato> lst = cu.obtenerDatosC(name);
            for(Candidato c : lst){ 
                idCandidato = c.getIdCandidato();
                foto = c.getFoto();
                nombre = c.getNombre();
                direccion=c.getDireccion();
            }
            edad = ccan.edad(idCandidato);
            rol =(Integer) request.getSession().getAttribute("valor");
        }else{
            response.sendRedirect("index.jsp");
        }
        int  idcv = crudcv.ultimoId(idCandidato);
%>   
<style>
/* Header */
header {
  margin-top: 100px;
  width:100%;
  vertical-align: middle;
  text-align: center;
 
}

.header-content {
  display: inline-block;
}

.pic {
  margin: 0 auto;
  margin-top:-70px;
  border-radius:10em;
  height:4em;
  width:4em;
  background-image: url(imagenes/fotos/<%=foto%>);
  background-size: 100% auto;
  
  border: 2px solid #e0e0e0;
  transition : all 0.5s linear;
  
}

@-webkit-keyframes wiggle {
  0%   {
    transform: rotate(0deg);
  }
  25%  {
    transform: rotate(-30deg);
  }
  50%  {
    transform: rotate(15deg);
  }
  100% {
    transform: rotate(0deg);
  }
}

.pic:hover {
  /*Webkit fix*/
  -webkit-transform: translateZ(0);
  
  cursor:pointer;
  
  -webkit-animation: wiggle 0.7s cubic-bezier(0.6, -0.28, 0.735, 0.045);
}

.header-text {
  margin-top:0.5em;
  margin-left:1em;
}

header p {
  text-align:center;
  font-family: "Merriweather";
  
  margin:0;
  
  color: #212121;
}

.first-name {
   font-size: 1.05em;
}

.subtitle {
  font-family: "Merriweather";
  padding-top:.3em;
  font-size:0.75em;
  font-weight:500;
  color: #424242;
}

/* Information */

.paper {
  margin: 0 auto;
  margin-bottom:1em;
  
  background-color:white;
  width:60%;
  
  padding:0.5em 0 0.5em 0;
  
  border-top: 1px solid #e0e0e0;
  border-left: 1px solid #e0e0e0;
  border-right: 1px solid #e0e0e0;
  border-bottom: 3px solid #e0e0e0;
  border-radius:3px;
}

/* Content Part */

.content-wrapper {
  padding-right:7.25%;
}

.content {
  display:table;
  width:100%;
  
  padding-top:1.5em;
  
  /*border-top: 1px solid #eeeeee;*/
}

.content:last-child {
  padding-bottom:1.5em;
}

.row {
    display:table-row;
}
.row div {
    display:table-cell;
}

.content-cat {
  font-family: "Merriweather";
  font-variant:small-caps;
  font-weight:300;
  letter-spacing: 1px;
  
  color: #212121;
  
  font-size:1.1em;
  
  text-align:center;
  vertical-align:middle;

  padding-left:6.66%;
  width:36.66%;
}
#name{
   font-family: "Merriweather";
  font-variant:small-caps;
  font-weight:300;
  letter-spacing: 1px;
  
  color: #212121;
  
  font-size:1.5em;
  
  text-align:center;
  vertical-align:middle;   
}
.content-cat p {
  font-size:0.60em;
  font-weight:600;
  
  margin-bottom:0;
}

.content-text {
    
  border-left:1px solid #e0e0e0;
  transition: border 0.66s ease-in;
}

.content-text:hover {
  border-left:1px solid #bdbdbd;
}

.big-text {
  vertical-align:top;
  
  padding-top:1.25em;
}

.content-text ul {
  padding:0;
  margin:0;
  margin-top:0.15em;
  
  width:85%;
  margin-left:1.5em;
}

.content-text ul:last-child {
  margin-bottom:0.5em;
}

.content-text ul li {
  font-family: "Open Sans";
  font-size:0.75em;
  margin-left:1.5em;
  display:inline-block;
  
}

.content-text ul li:first-child {
  width:35%;
  
  color: #424242;
  
  font-weight:600;
  font-size:0.75;
}

.content-text ul li a {
  text-decoration: none;
  color: #0277bd;
}

.content-text ul li a:hover {
  color:#4fc3f7;
}

.work-listing p {
  font-family: "Open Sans";
  font-size:0.80em;
  font-weight:400;
  
  line-height:1.5;
  
  color: #424242;
  
  margin-left:2em;
  margin-top:0.25em;
  margin-bottom:0.75em;
}

.work-listing .highlight, .content-text.skills-listing > ul > li > .highlight {
  font-family: "Open Sans";
  font-size:0.80em;
  font-weight:400;
  
  color: #424242;
  
  margin-left:3.25em;
  padding-left:0.5em;
  
  border-left:1px solid #e0e0e0;
  
}

.education-listing > .heading {
  font-weight:600;
  
  margin-top:0.75em;
  margin-left:2em;
}

.education-listing > .highlight {
  margin-left:3.25em;
}

.content-text.skills-listing > ul {
  display:table-row;
}

.content-text.skills-listing > ul:last-child {
  margin-bottom:0;
}

.content-text.skills-listing > ul > li {
  display:table-cell;
  vertical-align:top;
  
  padding-left:2em;
  
  width:50%;
  font-weight:600;
}

.content-text.skills-listing > ul > li span {
  font-weight:400;
  color: #616161;
}

.content-text.skills-listing > ul > li > .highlight {
  
  font-size:1em;
  font-weight:400;
  
  margin-left:1.25em;
}

.highlight {
  transition: border .33s ease-in;
}

.highlight:hover, .content-text.skills-listing > ul > li > .highlight:hover {
  border-left:1px solid #9e9e9e;
}
#btns{
    margin-right: 25px;
    margin-bottom: 25;
}
</style>
 <div class="container-fluid">
    <div class="row" style="width: 100%; position: fixed;z-index: 10;">
        <jsp:include page="plantilla/menu.jsp" />
    </div> 

<div class="resume-wrapper" style="margin-top: 90px;">
  <article class="paper">
     <!-- <div class="btn-toolbar pull-right" role="toolbar" id="btns" >
            <div class="btn-group">
              <button type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-print"></span> Imprimir
              </button>

              <button type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-plus-sign"></span>
              </button>
            </div>
        </div>   -->
    <header>
       
      <div class="header-content">
        <div class="header-pic" title="Wiggle Wiggle">
            <div class="pic" style="width: 100px;height: 100px;">
            </div>
        </div>
        <div class="header-text">
            <div id="name">
            <%=nombre %>
          </div>
        </div>
      </div>
    </header>
    
    <div class="content-wrapper">
    
      <!-- CONTACT -->
      <section class="content">
        <div class="row">
          <div class="content-cat">
           Datos Personales
          </div>
          <div class="content-text">
            <ul>
              <li>Edad</li>
              <li><%=edad %></li>
            </ul>
            <ul>
               <li>Correo</li>
                <% 
                    List<Correo>  lstcorreo = ccorreo.correos(idCandidato) ;
                    for(Correo correo: lstcorreo){
                %> 
               <li><%=correo.getCorreo()%></li>
               <%}%>
            </ul>
            <ul>
              <li>Teléfono</li>
               <% 
                    List<Telefono>  lsttel = ct.telefonos(idCandidato) ;
                    for(Telefono tel: lsttel){
                %> 
              <li>+503 <%=tel.getTelefono()%></li>
              <%}%>
            </ul>
            <ul>
              <li>Dirección</li>
              <li><%=direccion %></li>
            </ul>
          </div>
        </div>
      </section>

        <section class="content">
            <div class="row">
                <div class="content-cat education-listing">
                    Nivel de Experiencia
                </div>
            <div class="content-text work-listing">
                <p class="highlight">2 años</p>
            </div>
            </div>
        </section>

      <section class="content">
        <div class="row">
          <div class="content-cat">
            Formación Acádemica
          </div>
            <% 
                List<Educacion>  cveducacion = cedu.mostrarEducacion(idcv);
                for(Educacion e: cveducacion){
            %>
                <div class="content-text work-listing education-listing">
                    <p class="highlight">
                        Titulo/Especialidad : <%=e.getEspecialidad() %><br>
                        Institución : <%=e.getInstitucion() %><br>
                        Año de finalización : <%=e.getAnioFinalizacion() %>
                    </p>
                </div>
          <%}%>
        </div>
      </section>
      
 
      <section class="content">
        <div class="row">
          <div class="content-cat ">
            Experiencia Laboral
          </div>
            <% 
                List<ExperienciaLaboral>  cvel = cel.mostrarExperienciaLaboral(idcv);
                for(ExperienciaLaboral elab: cvel){
            %>
                <div class="content-text work-listing education-listing">
                    <p class="highlight">
                        Cargo : <%=elab.getPuesto() %><br>
                        Empresa : <%=elab.getNombreEmpresa() %><br>
                        Período : <%=elab.getFechaInicio() %> || <%=elab.getFechaFin() %>
                    </p>
                </div>
          <%}%>
        </div>
      </section>
      
      
      <section class="content">
        <div class="row">
          <div class="content-cat">
            Cursos
          </div>
            <%
                List<Curso> cvcurso = crudc.mostrarCurso(idcv);
                for(Curso c:cvcurso){
            %>
                <div class="content-text work-listing education-listing">
                    <p class="highlight">
                        Titulo : <%=c.getTitulo() %><br>
                        Institución : <%=c.getInstitucion() %><br>
                        Año de finalización : <%=c.getAnioFinalizacion() %>
                    </p>
                </div>
            <%}%>
        </div>
      </section>
      
          <section class="content">
        <div class="row">
            <div class="content-cat">
              Idioma
            </div>
             <% 
                List<CvIdioma>  cvidioma = cvi.mostrarCvIdioma(idcv);
                for(CvIdioma i:cvidioma){
            %> 
                <div class="content-text work-listing education-listing">
                    <p class="highlight">
                        Idioma : <%=i.getIdioma().getNombreIdioma() %><br>
                        Nivel : <%=i.getNivel().getNivel() %><br>
                    </p>
                </div>
          <%}%>
        </div>
      </section>
      
      <section class="content">
        <div class="row">
          <div class="content-cat">
            Habilidades
          </div>
                 <% 
                List<CvHabilidad>  cvhabilidad = cch.mostrarCvHabilidad(idcv) ;
                for(CvHabilidad h:cvhabilidad){
                %>
                  <div class="content-text work-listing education-listing">
                      <p class="highlight">
                          <strong style="color: #212121;"><%=h.getHabilidad().getNombreHabilidad() %></strong>
                    <%=h.getNivel().getNivel() %></p>
                </div>
                  <%}%>  
        </div>
      </section>
    
    </div>
      
  </article>
  
</div>

<!--Footer-->
<jsp:include page="plantilla/footer.jsp" />
</div><!--End Container-->
</body>
</html>
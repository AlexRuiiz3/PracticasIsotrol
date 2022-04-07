<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <title><s:text name="title.otrasOpciones" /></title>
    <!-- Scripts -->
    <s:include value="templates/head_scripts.jsp" />
    <!-- CSS  -->
    <s:include value="templates/head_styles.jsp" />
  </head>
  <body>
    <div class="imagenCabecera mb-5"></div>
    <div class="container-fluid">
      <div class="justify-content-center row">
        <div id="messagesAndErrors" class="align-self-center col-sm-12 d-none"></div>
    </div>
    <ul class="nav nav-tabs">
      <li class="nav-item" id="tabGenerales"><a class="nav-link active" data-toggle="tab" href="#datosGenerales"><s:text name="tab.datosGenerales" /></a></li>
      <li class="nav-item" id="tabSubsanacion"><a class="nav-link" data-toggle="tab" href="#datosSubsanacion"><s:text name="tab.datosSubsanacion" /></a></li>
      <li class="nav-item" id="tabModificacion"><a class="nav-link" data-toggle="tab" href="#historicoModificaciones" onClick="initPestanaHistorico()"><s:text name="tab.histModificacion" /></a></li>
      <s:if test="mostrarTabComuInicio">
          <li class="nav-item" id="tabComunicacionInicio"><a class="nav-link" data-toggle="tab" href="#comunicacionInicio" onClick="initPestanaComunicacionInicio()"><s:text name="tab.comunicacionInicio" /></a></li>
      </s:if>
    </ul>
    <!-- Tab paneles -->
      <div class="tab-content">
        <div class="tab-pane container-fluid active" id="datosGenerales">
          <div id="bloqueDatosGenerales">
            <%@include file="datosGenerales/datosGenerales.jsp"%>
          </div>
        </div>
        <div class="tab-pane container-fluid fade" id="datosSubsanacion">
          <div id="bloqueDatosSubsanacion">
            <%@include file="datosSubsanacion/datosSubsanacion.jsp"%>
          </div>
        </div>
        <div class="tab-pane container-fluid fade" id="historicoModificaciones"> 
           <div id="bloqueDatosGenerales">
             <%@include file="historicoModificaciones/historicoModificaciones.jsp"%>
           </div> 
        </div>
        <div class="tab-pane container-fluid fade" id="comunicacionInicio"> 
           <div id="bloqueDatosGenerales">
             <%@include file="comunicacionInicio/comunicacionInicio.jsp"%>
           </div> 
        </div>
    </div>
    <div class="fondoOverlay d-none" />
  </body>
</html>
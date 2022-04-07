<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
  <head>
  <title><s:text name="title.valoracion"/></title>
  <!-- Scripts -->
  <s:include value="templates/head_scripts.jsp"/>
  <!-- CSS  -->
  <s:include value="templates/head_styles.jsp"/>
  <script type="text/javascript">
    window.esONGD = '<s:property value="valoracionSolicitudDTO.ongd"/>';
    window.urlActionInformeValoracion = '<s:url action="informeValoracion" namespace="/modulos/valoracion" includeParams="none"/>';
    window.urlActionInformeEvaluacion = '<s:url action="informeEvaluacion" namespace="/modulos/valoracion" includeParams="none"/>';
  </script>
  </head>
  <body>
    <div class="imagenCabecera mb-5"></div>
    <div class="container-fluid">
      <div class="justify-content-center row">
        <div id="messagesAndErrors" class="align-self-center col-sm-12 d-none"></div>
      </div>
      <s:hidden name="valoracionSolicitudDTO.codigoFinalidad"/>
      <s:hidden name="valoracionSolicitudDTO.idSolicitud"/>
      <s:hidden name="valoracionSolicitudDTO.ongd"/>
      <ul class="nav nav-tabs">
        <li class="nav-item" id="tabPertinenciaViabilidad"><a class="nav-link active" data-toggle="tab" href="#pertinenciaViabilidad"><s:text name="tab.pertinenciaViabilidad" /></a></li>
        <li class="nav-item" id="tabCoherenciaSostImp"><a class="nav-link" data-toggle="tab" href="#coherenciaSostImp">
          <s:if test ='%{"AH"==valoracionSolicitudDTO.codigoFinalidad}'>
            <s:text name="tab.coherenciaConectividadImp" />
          </s:if>
          <s:else>
            <s:text name="tab.coherenciaSostImp" />
          </s:else>
          
        </a></li>
        <s:if test ="valoracionSolicitudDTO.ongd">
          <li class="nav-item" id="tabConvergenciaCapGest"><a class="nav-link" data-toggle="tab" href="#convergenciaCapGest" onclick="recargarPestania3()"><s:text name="tab.convergenciaCapGest" /></a></li>
        </s:if>
        <li class="nav-item" id="tabIncrValTotal"><a class="nav-link" data-toggle="tab" href="#IncrValTotal" onclick="recargarPestanaValoracionTotal()">
        <s:if test="valoracionSolicitudDTO.ongd">
          <s:text name="tab.incremento.valoracionTotal" />
        </s:if>
        <s:else>
          <s:text name="tab.valoracionTotal" />
        </s:else>
        </a></li>
      </ul>
      <!-- Tab paneles -->
      <div class="tab-content">
        <div class="tab-pane container-fluid active" id="pertinenciaViabilidad">
          <div id="bloqueDatosPertinencia">
            <%@include file="datosPertinenciaViabilidad.jsp"%>
          </div>
        </div>
        <div class="tab-pane container-fluid fade" id="coherenciaSostImp">
          <div id="bloqueDatosCoherencia">
            <%@include file="datosCoherenciaSostImp.jsp"%>
          </div>
        </div>
        <s:if test ="valoracionSolicitudDTO.ongd">
          <div class="tab-pane container-fluid fade" id="convergenciaCapGest"> 
             <div id="bloqueDatosConvergencia">
               <%@include file="datosConvergenciaCapGest.jsp"%>
             </div> 
          </div>
        </s:if>
        <div class="tab-pane container-fluid fade" id="IncrValTotal">
          <div id="bloqueValoracionTotal">
            <%@include file="datosValoracionTotal.jsp"%>
          </div>
        </div>
      </div>
      <div class="float-right mt-4 mb-4">
        <input class="btn btn-success btn-space" type="button" id="btnGuardar" value="GUARDAR" onclick="guardarCriteriosSolicitud()" />
        <input class="btn btn-danger btn-space" type="button" id="btnCerrar" value="<s:text name="CERRAR" />" 
          onclick="if (confirm('Todos los cambios que no hayan guardado se perderán. \n¿Está seguro que desea salir?')){window.close();return false; } else {return false;}"></input>
      </div>
      <s:if test="valoracionSolicitudDTO.generacionInformes">
      <div class="float-left mt-4 mb-4">
        <input class="btn btn-info btn-space" type="button" id="btnInfVal" value="Informe Valoración" onclick="informeValoracion()">
      </div>
      </s:if>
    </div>  
    <div class="fondoOverlay d-none" />
  </body>
</html>
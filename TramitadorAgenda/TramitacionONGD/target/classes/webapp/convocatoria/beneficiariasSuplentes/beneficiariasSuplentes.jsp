<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
  <head>
  <title><s:text name="title.beneficiario.suplente"/></title>
  <!-- Scripts -->
  <s:include value="/modulos/tramitacionONGD/templates/head_scripts.jsp"/>
  <script type="text/javascript" src="<s:url value="/modulos/tramitacionONGD/convocatoria/beneficiariasSuplentes/js/beneficiariaSuplente.js"/>"></script>
  <!-- CSS  -->
  <s:include value="/modulos/tramitacionONGD/templates/head_styles.jsp" />
  <script type="text/javascript">
    window.urlActionCargaListadoExpedientesPorFinalidad = '<s:url action="cargaListadoExpedientesPorFinalidad" namespace="/agenda/tareas" includeParams="none"/>';
    window.urlActionGuardarBeneficiariaSuplente  = '<s:url action="guardarBeneficiarioSuplente" namespace="/agenda/tareas" includeParams="none"/>';
    initParametrizacionComun([
      '<s:text name="tabla.beneSuple.col.0.title"/>',
      '<s:text name="tabla.beneSuple.col.1.title"/>',
      '<s:text name="tabla.beneSuple.col.2.title"/>',
      '<s:text name="tabla.beneSuple.col.3.title"/>',
      '<s:text name="tabla.beneSuple.col.4.title"/>' ], [
      '<s:text name="tabla.beneSuple.col.0.width"/>',
      '<s:text name="tabla.beneSuple.col.1.width"/>',
      '<s:text name="tabla.beneSuple.col.2.width"/>',
      '<s:text name="tabla.beneSuple.col.3.width"/>',
      '<s:text name="tabla.beneSuple.col.4.width"/>' ],
      '<s:text name ="mensaje.mensajeSinRegistros"/>');
  </script>
  <!-- CSS  -->
  <s:include value="templates/head_styles.jsp"/>
  </head>
  <body>
    <div class="imagenCabecera mb-5"></div>
    <div class="container-fluid">
      <div class="justify-content-center row">
        <div id="messagesAndErrors" class="align-self-center col-sm-12 d-none"></div>
      </div>
      <ul class="nav nav-tabs">
        <li class="nav-item" id="tabFinalidadC"><a class="nav-link active" data-toggle="tab" href="#finalidadC"><s:text name="tab.finalidad.C" /></a></li>
        <li class="nav-item" id="tabFinalidadAH"><a class="nav-link" data-toggle="tab" href="#finalidadAH"><s:text name="tab.finalidad.AH" /></a></li>
        <li class="nav-item" id="tabFinalidadED"><a class="nav-link" data-toggle="tab" href="#finalidadED"><s:text name="tab.finalidad.ED" /></a></li>
        <li class="nav-item" id="tabFinalidadF"><a class="nav-link" data-toggle="tab" href="#finalidadF"><s:text name="tab.finalidad.F" /></a></li>
      </ul>
      <!-- Tab paneles -->
      <div class="tab-content">
        <div class="tab-pane container-fluid active" id="finalidadC">
          <div id="bloqueFinalidadC">
            <%@include file="expedientesFinalidadC.jsp"%>
          </div>
        </div>
        <div class="tab-pane container-fluid fade" id="finalidadAH">
          <div id="bloqueFinalidadAH">
            <%@include file="expedientesFinalidadAH.jsp"%>
          </div>
        </div>
        <div class="tab-pane container-fluid fade" id="finalidadED"> 
           <div id="bloqueFinalidadED">
             <%@include file="expedientesFinalidadED.jsp"%>
           </div> 
        </div>
        <div class="tab-pane container-fluid fade" id="finalidadF"> 
           <div id="bloqueFinalidadF">
             <%@include file="expedientesFinalidadF.jsp"%>
           </div> 
        </div>
      </div>
      <div class="float-right mt-4 mb-4" style="padding-right: 30px;">
        <input class="btn btn-success btn-space" type="button" id="btnGuardar" value="GUARDAR" onclick="guardar()" />
        <input class="btn btn-danger btn-space" type="button" id="btnCerrar" value="<s:text name="CERRAR" />" 
          onclick="if (confirm('Todos los cambios que no hayan guardado se perderán. \n¿Está seguro que desea salir?')){window.close();return false; } else {return false;}"></input>
      </div>
    </div>  
    <div class="fondoOverlay d-none" />
  </body>
</html>
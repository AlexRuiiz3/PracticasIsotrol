<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
  <head>
  <title><s:text name="title.beneficiario.suplente"/></title>
  <!-- Scripts -->
  <s:include value="/modulos/tramitacionUniv/templates/head_scripts.jsp"/>
  <script type="text/javascript" src="<s:url value="/modulos/tramitacionUniv/convocatoria/beneficiariasSuplentes/js/beneficiariaSuplente.js"/>"></script>
  <!-- CSS  -->
  <s:include value="/modulos/tramitacionUniv/templates/head_styles.jsp" />
  <script type="text/javascript">
    window.urlActionCargaListadoExpedientesPorFinalidad = '<s:url action="cargaListadoExpedientesPorFinalidadUniv" namespace="/agenda/tareas" includeParams="none"/>';
    window.urlActionGuardarBeneficiariaSuplente  = '<s:url action="guardarBeneficiarioSuplenteUniv" namespace="/agenda/tareas" includeParams="none"/>';
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
        <li class="nav-item" id="tabFinalidadCU"><a class="nav-link active" data-toggle="tab" href="#finalidadCU"><s:text name="tab.finalidad.CU" /></a></li>
        <li class="nav-item" id="tabFinalidadEDD"><a class="nav-link" data-toggle="tab" href="#finalidadEDD"><s:text name="tab.finalidad.EDD" /></a></li>
        <li class="nav-item" id="tabFinalidadFE"><a class="nav-link" data-toggle="tab" href="#finalidadFE"><s:text name="tab.finalidad.FE" /></a></li>
        <li class="nav-item" id="tabFinalidadINN"><a class="nav-link" data-toggle="tab" href="#finalidadINN"><s:text name="tab.finalidad.INN" /></a></li>
      </ul>
      <!-- Tab paneles -->
      <div class="tab-content">
        <div class="tab-pane container-fluid active" id="finalidadCU">
          <div id="bloqueFinalidadCU">
            <%@include file="expedientesFinalidadCU.jsp"%>
          </div>
        </div>
        <div class="tab-pane container-fluid fade" id="finalidadEDD">
          <div id="bloqueFinalidadEDD">
            <%@include file="expedientesFinalidadEDD.jsp"%>
          </div>
        </div>
        <div class="tab-pane container-fluid fade" id="finalidadFE"> 
           <div id="bloqueFinalidadEF">
             <%@include file="expedientesFinalidadFE.jsp"%>
           </div> 
        </div>
        <div class="tab-pane container-fluid fade" id="finalidadINN"> 
           <div id="bloqueFinalidadINN">
             <%@include file="expedientesFinalidadINN.jsp"%>
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
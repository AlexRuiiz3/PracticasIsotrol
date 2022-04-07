<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
  <title>Datos del expediente</title>
  <!-- JS Especificos pantalla -->
  <s:include value="/modulos/portletDatosExpediente/js/portletDatosExpediente_scripts.jsp" />
  <!-- CSS  -->
  <s:include value="/modulos/portletDatosExpediente/templates/head_styles.jsp" />
</head>
<body>
  <div id="divMensajeEspera"  style="display: none; position: absolute;top: 50%;left: 50%;color: #007934;z-index: 22002 !important;overflow: auto;">
    <div cssClass="mensajeEsperaImagen">
      <img src="../instalaciones/imagenes/images/imagenEspera.gif" alt="Capa de espera"/>
    </div>
  </div>
  <div id="divMensajeEsperaFondo" style="display: none; position: absolute; background: #000; z-index: 2000; opacity: 0.2;-moz-opacity: 0.2; filter: alpha(opacity = 20);"/>
  
  <div id="bloqueDatoExpediente" style="display: inline-table;">
    <s:hidden id="numExpediente" name="portletDatosExpedienteDTO.numeroExpediente" />
    <div id="formulario" style="float: left; width: 100% !important;">
      <div style="padding-right:1rem;font-size: 0.75em; font-weight: bold; float: left;" for="entidad">
        <s:i18n name="es.juntadeandalucia.aacid.portletdatosexpediente.action.PortletDatosExpedienteAction">
          <s:text name="expediente.entidad" />:
        </s:i18n>
      </div>
      <div style="text-align:left;font-size: 0.75em; float: left;">
        <span id="entidad"></span>
      </div>
    </div>
    <div id="formulario" style="float: left; width: 100% !important;">
      <div style="padding-right:1rem;font-size: 0.75em; font-weight: bold; float: left;" for="titulo">
        <s:i18n name="es.juntadeandalucia.aacid.portletdatosexpediente.action.PortletDatosExpedienteAction">
          <s:text name="expediente.titulo"/>:
        </s:i18n>
      </div>
      <div style="text-align:left;font-size: 0.75em; float: left;">
        <span id="titulo"></span>
      </div>
    </div>
    <div id="formulario" style="float: left; width: 100% !important;">
      <div style="padding-right:1rem;font-size: 0.75em; font-weight: bold; float: left;" for="numAACID">
        <s:i18n name="es.juntadeandalucia.aacid.portletdatosexpediente.action.PortletDatosExpedienteAction">
          <s:text name="expediente.numeroExpediente.aacid" />:
        </s:i18n>
      </div>
      <div style="text-align:left;font-size: 0.75em; float: left;padding-left: 10px;">
        <span id="numAACID" ></span>
      </div>
    </div>
    <div id="formulario" style="float: left; width: 100% !important;">
      <div style="padding-right:1rem;font-size: 0.75em; font-weight: bold; float: left;" for="fechaComputo">
        <s:i18n name="es.juntadeandalucia.aacid.portletdatosexpediente.action.PortletDatosExpedienteAction">
          <s:text name="expediente.fechaComputo" />:
        </s:i18n>
      </div>
      <div style="text-align:left;font-size: 0.75em; float: left;">
        <span id="fechaComputo" ></span>
      </div>
    </div>
    <div id="formulario" style="float: left; width: 100% !important;">
      <div style="padding-right:1rem;font-size: 0.75em; font-weight: bold; float: left;" for="numeroExpediente">
        <s:i18n name="es.juntadeandalucia.aacid.portletdatosexpediente.action.PortletDatosExpedienteAction">
          <s:text name="expediente.numeroExpediente" />:
        </s:i18n>
      </div>
      <div style="text-align:left;font-size: 0.75em; float: left;">
        <span id="numeroExpediente"></span>
      </div>
    </div>
<!--     <div id="formulario" style="float: left; width: 100% !important;"> -->
<!--       <div style="padding-right:1rem;font-size: 0.75em; font-weight: bold; float: left;" for="desProcedimiento"> -->
<%--         <s:i18n name="es.juntadeandalucia.aacid.portletdatosexpediente.action.PortletDatosExpedienteAction"> --%>
<%--           <s:text name="expediente.procedimiento" />: --%>
<%--         </s:i18n> --%>
<!--       </div> -->
<!--       <div style="text-align:left;font-size: 0.75em; float: left;"> -->
<%--         <span id="desProcedimiento"></span> --%>
<!--       </div> -->
<!--     </div> -->
    <div id="formulario" style="float: left; width: 100% !important;">
      <div style="padding-right:1rem;font-size: 0.75em; font-weight: bold; float: left;" for="desUnidadOrganica">
        <s:i18n name="es.juntadeandalucia.aacid.portletdatosexpediente.action.PortletDatosExpedienteAction">
          <s:text name="expediente.unidadOrganica" />:
        </s:i18n>
      </div>
      <div style="text-align:left;font-size: 0.75em; float: left;">
        <span id="desUnidadOrganica"></span>
      </div>
    </div>
    
    <div  id="formulario" style="float: left; width: 100% !important;">
      <span id="bloqueUsuarioBloqueo" style="width: 100%; font-size: 0.75em; font-weight: bold; float: left;display:none;">El usuario: <span id="nombreUsuarioBloqueo"></span> tiene <span id="tipoBloqueoE"> 
        <s:i18n name="es.juntadeandalucia.aacid.portletdatosexpediente.action.PortletDatosExpedienteAction">
          <s:text name="usuario.bloqueadoExpediente"/>
        </s:i18n>
      </span> 
      <span id="tipoBloqueoF">
        <s:i18n name="es.juntadeandalucia.aacid.portletdatosexpediente.action.PortletDatosExpedienteAction">
          <s:text name="usuario.bloqueadaFase"/></span>
        </s:i18n>
      </span>
      </span>
    </div>
    
    <div id="infoExpedienteCaducado" style="float: left; width: 100% !important;">
      <span style="width: 100%; font-size: 0.75em; font-weight: bold; float: left;display:none;"> 
        <strong>
          <s:i18n name="es.juntadeandalucia.aacid.portletdatosexpediente.action.PortletDatosExpedienteAction">
            <s:text name="expediente.estaCaducado"/>
          </s:i18n>
        </strong>
      </span>
    </div>
  </div>
</body>
</html>
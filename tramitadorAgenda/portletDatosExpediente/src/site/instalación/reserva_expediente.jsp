<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="es">
<head>
	<title>Reserva</title>
	<script type="text/javascript" src="../modulos/comun/js/tr_ajax.js"></script>
	
	<link href="../../favicon.ico" rel="shortcut icon" />
	<link rel="icon" href="../../favicon.ico" type="image/x-icon" />
	<link rel="apple-touch-icon" href="../../favicon.ico" />
	<script type="text/javascript" src="../../modulos/comun/js/tr_ajax.js" ></script> 
	<s:head theme="ajax"/>
</head>
<body>
<center>
<s:div id="contenedor-nuevo">
	<s:div id="contenido-nuevo-utilidades">
		<h2>Reservar expediente</h2>
		<s:form action="reservarExp"
			theme="simple">
			<input type="hidden" id="tokenUsuario" name="tokenUsuario" value="<%= ActionContext.getContext().getSession().get("tokenUsuario")%>" />
		
			<fieldset style="padding:5px !important;"><legend> <span>Reservar: </span> </legend> <s:if
				test="%{nombreUsuarioBloqueo != null}">
						<center><p class="info">El usuario: "<s:text name="%{nombreUsuarioBloqueo}" />" tiene bloqueado el expediente/fase.</p></center>
					</s:if>


			<center>
			<blockquote>
			<input type="radio" class="clase_input_radio" name="optSeleccion" value="0" checked="checked">
				<s:text name="Expediente" />
			<input type="radio" class="clase_input_radio" name="optSeleccion" value="1">
			 	<s:text name="Fase" /> 
			<input type="radio" class="clase_input_radio" name="optSeleccion" value="2">
				<s:text	name="Anular reserva" />
			</blockquote>
			</center>

			<s:submit type="submit" label="Reservar" value="Reservar"
				cssClass="clase_input" />
				</fieldset>

		</s:form>

		<h2>Reservar expediente para otro usuario</h2>
		<center><p class="info">Utilizar el caracter '%' en la b&uacute;squeda
			para no especificar la palabra completa. Ejemplo '%ANA%</p></center>
		<fieldset style="padding:5px !important;"><legend> Búsqueda de usuarios </legend> 
		<s:form id="buscarUsuarioParaReserva" method="post"	action="buscarUsuarioParaReserva" theme="simple">
			<input type="hidden" id="tokenUsuario" name="tokenUsuario" value="<%= ActionContext.getContext().getSession().get("tokenUsuario")%>" />
					
			<s:div id="formulario-in">
				
				<s:label for="identificadorReserva" value="Identif." ></s:label>
				
				
				<s:textfield id="identificadorReserva" name="identificadorReserva"
					 cssClass="ancho_generico" />
				
				<s:label for="nombreReserva" value="Nombre" ></s:label>
				
				<s:textfield id="nombreReserva" name="nombreReserva" 
						cssClass="ancho_generico" />
				
				<s:label for="apellido1Reserva" value="Primer apellido" ></s:label>
				
				<s:textfield id="apellido1Reserva" name="apellido1Reserva"
						 cssClass="ancho_generico" />
				
				<s:label for="apellido2Reserva" value="Segundo apellido" ></s:label>
				
				<s:textfield id="apellido2Reserva" name="apellido2Reserva"
						 cssClass="ancho_generico" />
				
			</s:div>
			
			<s:div>
				<s:submit type="button" theme="ajax" id="resultadosBusqueda" value="Buscar"
				targets="divResultadosBusqueda" cssClass="oculto" loadingText="Buscando..."/>

			<input type="submit" class="clase_input" value="Buscar"
				onclick="javascript:document.getElementById('resultadosBusqueda').click();return false;">
				
			</s:div>	
	</s:form>	</fieldset>
	<s:div id="divResultadosBusqueda" theme="simple"
		loadingText="Buscando..." name="divResultadosBusqueda" />
	<s:div id="divConfirmacionReserva" theme="simple"
		loadingText="Reservando..." name="divConfirmacionReserva" />

	<input type="submit" value="cerrar" class="clase_input" style="font-size: 1em;"
		onclick="javascript:opener.recargaPadre([['portletDatosExpediente', '../modulos/portletDatosExpediente/inicio.action']]);self.close();" />			
		</s:div>
		</s:div>
</center>
</body>
</html>
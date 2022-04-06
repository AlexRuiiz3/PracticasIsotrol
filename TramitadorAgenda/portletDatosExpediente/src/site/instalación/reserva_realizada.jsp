<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="es">
<head>
	<title>Reserva - Operación realizada</title>
	
	<s:head theme="ajax" debug="false" />
</head>

<body>
<center>
<s:div id="contenedor-nuevo">
	<s:div id="contenido-nuevo-utilidades">
		
		<h2>Reservar expediente</h2>
		<s:if test="%{mensajeError == null || mensajeError == ''}"> 
			<center><p class="info"><s:text name="La acción se ha realizado correctamente."/></p></center>
		</s:if>
		<s:else>
			<center>
			<s:div cssClass="error">
				<s:property value="mensajeError"/>
			</s:div>
			</center>
		</s:else>
		
		<s:div id="centrado">
			<input type="submit" value="cerrar" class="clase_input" style="font-size:1.0em;"
			onclick="javascript:opener.recargaPadre([['portletDatosExpediente', '../modulos/portletDatosExpediente/inicio.action']]);self.close();" />			
		</s:div>
		
	</s:div>
</s:div>
</center>
</body>
</html>
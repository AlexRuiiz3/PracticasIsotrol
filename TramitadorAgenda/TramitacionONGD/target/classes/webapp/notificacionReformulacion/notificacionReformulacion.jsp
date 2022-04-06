<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><s:text name="reformula.title" /></title>
<!-- Scripts -->
<s:include value="/modulos/tramitacionONGD/templates/head_scripts.jsp" />
<script type="text/javascript" src="<s:url value="/modulos/tramitacionONGD/notificacionReformulacion/js/notificacionReformulacion.js"/>"></script>
<script type="text/javascript" src="<s:url value="/modulos/tramitacionONGD/notificacionReformulacion/js/validacionesNotificacionReformulacion.js"/>" ></script>
<!-- CSS  -->
<s:include value="/modulos/tramitacionONGD/templates/head_styles.jsp" />
<script type="text/javascript">
    defineUrlsControladores([ '<s:url action="guardarReformula" namespace="/agenda/tareas" includeParams="none"/>', '<s:url action="incorporarNotificacionReformula" namespace="/agenda/tareas" includeParams="none"/>' ]);
</script>
<script type="text/javascript">
    $(document).ready(function() {
    	initPantallaNotificacionReformula();
    });
</script>
<s:head theme="ajax" />
</head>
<body>
	<div class="imagenCabecera mb-5"></div>
	<div class="container-fluid">
		<div class="justify-content-center row">
			<div id="messagesAndErrors" class="align-self-center col-sm-12 d-none"></div>
		</div>
		<s:form id="formReformula" cssClass="form-vertical" theme="simple">
			<s:hidden name="datosReformulaDTO.idSolicitud" />
			<div class="card">
				<div class="card-header">
					<h5>
						<s:text name="reformula.title" />
					</h5>
				</div>
				<div class="card-body">
					<div class="form-group row">
						<label class="col-sm-3 col-form-label obligatorio" for="maximoAACID"><s:text name="reformula.label.maximoAACID" />: </label>
						<div class="col-sm-3">
							<div class="input-group">
							  <s:textfield name="datosReformulaDTO.maximoAACID" id="maximoAACID" cssClass="form-control" maxlength="16" />
							  <div class="input-group-append">
							    <span class="input-group-text">€</span>
							  </div>
							</div>
						</div>
						<label class="col-sm-3 col-form-label obligatorio" for="minimoPresupuestoTotal"><s:text name="reformula.label.minimoPresupuestoTotal" />: </label>
						<div class="col-sm-3">
							<div class="input-group">
							  <s:textfield name="datosReformulaDTO.minimoPresupuestoTotal" id="minimoPresupuestoTotal" cssClass="form-control" maxlength="16" />
							  <div class="input-group-append">
							    <span class="input-group-text">€</span>
							  </div>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-3 col-form-label" for="tipoReformulacion"><s:text name="reformula.label.tipo" />:</label>
						<div class="col-sm-3">
							<s:select list="tiposReformulaDTO" name="datosReformulaDTO.tipoSeleccionado" multiple="false" id="tipoReformulacion" cssClass="form-control" />
						</div>
					</div>
				</div>
			</div>
			<div class="float-right mt-4 mb-4">
				<input class="btn btn-success btn-space" type="button"
					id="btnGuardar" value="GUARDAR"
					onclick="createOrUpdateReformula()" />
				<input class="btn btn-primary btn-space" type="button"
					id="btnGenerar" value="GENERAR/INCORPORAR NOTIFICACIÓN"
					onclick="incorporarNotificacionReformula()" />
				<input
					class="btn btn-danger btn-space" type="button" id="btnCerrar"
					value="<s:text name="CERRAR" />"
					onclick="if (confirm('¿Está seguro que desea salir?')){window.close();return false; } else {return false;}"></input>
			</div>
		</s:form>
	</div>
	<div class="fondoOverlay d-none" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<title><s:text name="title.exclusiones.causasInadmision" /></title>
<!-- Scripts -->
<s:include value="/modulos/tramitacionUniv/templates/head_scripts.jsp" />
<script type="text/javascript"
  src="<s:url value="/modulos/tramitacionUniv/exclusion/js/comunExclusion.js"/>"></script>
<!-- CSS  -->
<s:include value="/modulos/tramitacionUniv/templates/head_styles.jsp" />
<script type="text/javascript">
    defineUrlsControladores([ '<s:url action="guardarCausasInadmision" namespace="/agenda/tareas" includeParams="none"/>' ]);
</script>
</head>
<body>
  <div class="imagenCabecera mb-5"></div>
  <div class="container-fluid">
    <div class="justify-content-center row">
      <div id="messagesAndErrors" class="align-self-center col-sm-12 d-none"></div>
    </div>
    <s:form id="formCausasInadmision" cssClass="form-vertical" theme="simple">
      <s:hidden name="datosExclusionDTO.idSolicitud" />
      <div id="listadoExclusiones" class="card mb-5">
        <div class="card-header">
          <h5>
            <s:text name="fieldset.causasExclusion.causasInadmision" />
          </h5>
        </div>
        <div class="card-body">
          <div class="form-group">
            <div class="col-md-12">
              <div class="list-group checkbox-vertical">
                <s:checkboxlist cssClass="col-md-12 list-group-item"
                  id="exclusionesTipo2" listKey="idEditar"
                  listValue="descripcion"
                  list="listasExclusionesDTO.listaExclusionesComunes"
                  name="datosExclusionDTO.exclusionesComunesSeleccionadas"
                  theme="simple" />
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="card mb-5">
        <div class="card-header">
          <h5>
            <s:text name="grupo.exclusion.validacion.exclusiones" />
          </h5>
        </div>
        <div class="card-body">
          <div class="form-group">
            <div class="col-md-12">
              <div class="checkbox-vertical">
                <input type="checkbox" class="form-check-input" id="noTieneExclusiones" name="datosExclusionDTO.noTieneExclusiones" <c:if test="${datosExclusionDTO.noTieneExclusiones}">checked</c:if>/>
                <label for="noTieneExclusiones" class="checkboxLabel"><s:text name="noTieneExclusiones" /></label>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="float-right mt-4 mb-4">
        <input class="btn btn-success btn-space" type="button" id="btnGuardar"
          value="GUARDAR" onclick="createOrUpdateExclusion('formCausasInadmision')" />
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
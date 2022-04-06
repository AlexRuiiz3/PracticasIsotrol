<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<title><s:text name="title.exclusiones.admision" /></title>
<!-- Scripts -->
<s:include value="/modulos/tramitacionONGD/templates/head_scripts.jsp" />
<script type="text/javascript"
  src="<s:url value="/modulos/tramitacionONGD/exclusion/js/comunExclusion.js"/>"></script>
<!-- CSS  -->
<s:include value="/modulos/tramitacionONGD/templates/head_styles.jsp" />
<script type="text/javascript">
    defineUrlsControladores([ '<s:url action="guardarExclusionTramitacion" namespace="/agenda/tareas" includeParams="none"/>' ]);
</script>
</head>
<body>
  <div class="imagenCabecera mb-5"></div>
  <div class="container-fluid">
    <div class="justify-content-center row">
      <div id="messagesAndErrors" class="align-self-center col-sm-12 d-none"></div>
    </div>
    <s:form id="formExclusionAdmision" cssClass="form-vertical" theme="simple">
      <s:hidden name="datosExclusionDTO.idSolicitud" />
      <div id="listadoExclusiones" class="card mb-5">
        <div class="card-header">
          <h5>
            <s:text name="fieldset.causasExclusion.preSubsanacion" />
          </h5>
        </div>
        <div class="card-body">
          <div class="form-group">
            <h6 class="col-md-12 border-bottom" for="exclusionesComunes">
              <s:text name="grupo.exclusion.causas.comunes" />
            </h6>
            <div class="col-md-12">
              <div class="list-group checkbox-vertical">
                <s:checkboxlist cssClass="col-md-12 list-group-item"
                  id="exclusionesComunes" listKey="idEditar"
                  listValue="descripcion"
                  list="listasExclusionesDTO.listaExclusionesComunes"
                  name="datosExclusionDTO.exclusionesComunesSeleccionadas"
                  theme="simple" />
              </div>
            </div>
          </div>
          <div class="form-group">
            <h6 class="col-md-12 border-bottom" for="exclusionesCooperacion">
              <s:text name="grupo.exclusion.causas.cooperacion" />
            </h6>
            <div class="col-md-12">
              <div class="list-group checkbox-vertical">
                <s:checkboxlist cssClass="col-md-12 list-group-item"
                  id="exclusionesCooperacion" listKey="idEditar"
                  listValue="descripcion"
                  list="listasExclusionesDTO.listaExclusionesCooperacion"
                  name="datosExclusionDTO.exclusionesCooperacionSeleccionadas"
                  theme="simple" />
              </div>
            </div>
          </div>
          <div class="form-group">
            <h6 class="col-md-12 border-bottom" for="exclusionesHumanitaria">
              <s:text name="grupo.exclusion.causas.humanitaria" />
            </h6>
            <div class="col-md-12">
              <div class="list-group checkbox-vertical">
                <s:checkboxlist cssClass="col-md-12 list-group-item"
                  id="exclusionesHumanitaria" listKey="idEditar"
                  listValue="descripcion"
                  list="listasExclusionesDTO.ListaExclusionesHumanitaria"
                  name="datosExclusionDTO.exclusionesHumanitariaSeleccionadas"
                  theme="simple" />
              </div>
            </div>
          </div>
          <div class="form-group">
            <h6 class="col-md-12 border-bottom" for="exclusionesEducacion">
              <s:text name="grupo.exclusion.causas.educacion" />
            </h6>
            <div class="col-md-12">
              <div class="list-group checkbox-vertical">
                <s:checkboxlist cssClass="col-md-12 list-group-item"
                  id="exclusionesEducacion" listKey="idEditar"
                  listValue="descripcion"
                  list="listasExclusionesDTO.listaExclusionesEducacion"
                  name="datosExclusionDTO.exclusionesEducacionSeleccionadas"
                  theme="simple" />
              </div>
            </div>
          </div>
          <div class="form-group">
            <h6 class="col-md-12 border-bottom" for="exclusionesFormacion">
              <s:text name="grupo.exclusion.causas.formacion" />
            </h6>
            <div class="col-md-12">
              <div class="list-group checkbox-vertical">
                <s:checkboxlist cssClass="col-md-12 list-group-item"
                  id="exclusionesFormacion" listKey="idEditar"
                  listValue="descripcion"
                  list="listasExclusionesDTO.ListaExclusionesFormacion"
                  name="datosExclusionDTO.exclusionesFormacionSeleccionadas"
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
          value="GUARDAR" onclick="createOrUpdateExclusion('formExclusionAdmision')" /> <input
          class="btn btn-danger btn-space" type="button" id="btnCerrar"
          value="<s:text name="CERRAR" />"
          onclick="if (confirm('¿Está seguro que desea salir?')){window.close();return false; } else {return false;}"></input>
      </div>
    </s:form>
  </div>
  <div class="fondoOverlay d-none" />
</body>
</html>
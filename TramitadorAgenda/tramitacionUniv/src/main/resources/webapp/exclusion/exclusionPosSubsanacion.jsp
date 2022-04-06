<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<title><s:text name="title.exclusiones.subsanacion" /></title>
<!-- Scripts -->
<s:include value="/modulos/tramitacionUniv/templates/head_scripts.jsp" />
<script type="text/javascript"
  src="<s:url value="/modulos/tramitacionUniv/exclusion/js/comunExclusion.js"/>"></script>
<!-- CSS  -->
<s:include value="/modulos/tramitacionUniv/templates/head_styles.jsp" />
<script type="text/javascript">
    defineUrlsControladores([ '<s:url action="guardarExclusionPosSubsanacionUniv" namespace="/agenda/tareas" includeParams="none"/>' ]);
</script>
</head>
<body>
  <div class="imagenCabecera mb-5"></div>
  <div class="container-fluid">
    <div class="justify-content-center row">
      <div id="messagesAndErrors" class="align-self-center col-sm-12 d-none"></div>
    </div>
    <s:form id="formExclusionTrasSubsanacion" cssClass="form-vertical" theme="simple">
      <s:hidden name="datosExclusionDTO.idSolicitud" />
      <s:if test="listasExclusionesDTO.listaExclusionesPaisesPriorizados!=null && !listasExclusionesDTO.listaExclusionesPaisesPriorizados.isEmpty()">
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
              <h6 class="col-md-12 border-bottom" for="paisesPriorizados">
                <s:text name="grupo.exclusion.causas.paisesPriorizados" />
              </h6>
              <div class="col-md-12">
                <div class="list-group checkbox-vertical">
                  <s:checkboxlist cssClass="col-md-12 list-group-item"
                                  id="paisesPriorizados" listKey="idEditar"
                                  listValue="descripcion"
                                  list="listasExclusionesDTO.listaExclusionesPaisesPriorizados"
                                  name="datosExclusionDTO.exclusionesPaisesPriorizadosSeleccionadas"
                                  theme="simple" />
                </div>
              </div>
            </div>
            <div class="form-group">
              <h6 class="col-md-12 border-bottom" for="exclusionesAndalucia">
                <s:text name="grupo.exclusion.causas.andalucia" />
              </h6>
              <div class="col-md-12">
                <div class="list-group checkbox-vertical">
                  <s:checkboxlist cssClass="col-md-12 list-group-item"
                                  id="exclusionesAndalucia" listKey="idEditar"
                                  listValue="descripcion"
                                  list="listasExclusionesDTO.listaExclusionesAndalucia"
                                  name="datosExclusionDTO.exclusionesAndaluciaSeleccionadas"
                                  theme="simple" />
                </div>
              </div>
            </div>
          </div>
        </div>
      </s:if>
      <s:if test="listasExclusionesDTO.listaExclusionesProyectosDesarrolloInnov!=null && !listasExclusionesDTO.listaExclusionesProyectosDesarrolloInnov.isEmpty()">
        <div id="listadoExclusiones" class="card mb-5">
          <div class="card-header">
            <h5>
              <s:text name="fieldset.causasExclusion.impideValoracion" />
            </h5>
          </div>
          <div class="card-body">
            <div class="form-group">
              <h6 class="col-md-12 border-bottom" for="exclusionesCausasComunes">
                <s:text name="grupo.exclusion.causas.comunes.univ" />
              </h6>
              <div class="col-md-12">
                <div class="list-group checkbox-vertical">
                  <s:checkboxlist cssClass="col-md-12 list-group-item"
                    id="exclusionesCausasComunes" listKey="idEditar"
                    listValue="descripcion"
                    list="listasExclusionesDTO.listaExclusionesComunes"
                    name="datosExclusionDTO.exclusionesComunesSeleccionadas"
                    theme="simple" />
                </div>
              </div>
            </div>
            <div class="form-group">
              <h6 class="col-md-12 border-bottom" for="exclusionesProyCooperacion">
                <s:text name="grupo.exclusion.proyectos.cooperacion.univ" />
              </h6>
              <div class="col-md-12">
                <div class="list-group checkbox-vertical">
                  <s:checkboxlist cssClass="col-md-12 list-group-item"
                    id="exclusionesProyCooperacion" listKey="idEditar"
                    listValue="descripcion"
                    list="listasExclusionesDTO.listaExclusionesProyectosCooperacion"
                    name="datosExclusionDTO.exclusionesProyCooperacionSeleccionadas"
                    theme="simple" />
                </div>
              </div>
            </div>
            <div class="form-group">
              <h6 class="col-md-12 border-bottom" for="exclusionesProyInvestigacion">
                <s:text name="grupo.exclusion.proyectos.investigacion.univ" />
              </h6>
              <div class="col-md-12">
                <div class="list-group checkbox-vertical">
                  <s:checkboxlist cssClass="col-md-12 list-group-item"
                    id="exclusionesProyInvestigacion" listKey="idEditar"
                    listValue="descripcion"
                    list="listasExclusionesDTO.listaExclusionesProyectosDesarrolloInnov"
                    name="datosExclusionDTO.exclusionesProyDesarrolloSeleccionadas"
                    theme="simple" />
                </div>
              </div>
            </div>
          </div>
        </div>
      </s:if>
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
          value="GUARDAR" onclick="createOrUpdateExclusion('formExclusionTrasSubsanacion')" /> <input
          class="btn btn-danger btn-space" type="button" id="btnCerrar"
          value="<s:text name="CERRAR" />"
          onclick="if (confirm('¿Está seguro que desea salir?')){window.close();return false; } else {return false;}"></input>
      </div>
    </s:form>
  </div>
  <div class="fondoOverlay d-none" />
</body>
</html>
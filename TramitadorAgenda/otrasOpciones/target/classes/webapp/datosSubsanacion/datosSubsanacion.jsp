<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="!datosGeneralesDTO.tienePermisosGuardado">
	<script type="text/javascript">
		$(document).ready(function() {
		$(":input[type='text']").attr("disabled", true);
		$(":input[type='checkbox']").attr("disabled", true);
		$("#lugarRegistroSubsanacion").attr("disabled", true);
		$("#idSelecSubsanacion").css("display", "none");
		$("#idSelecGrupoSubsanacionSeleccionada").css("display", "none");
		$("#idSelecGrupoSubsanacion").css("display", "none");
		});
	</script>
</s:if>
      <div class="justify-content-center row">
        <div id="messagesAndErrorsSubsanacion" class="align-self-center col-sm-12 d-none"></div>
      </div>
      <s:form id="formDatosSubsanacion" action="/guardarSubsanacion.action" theme="simple">
        <s:hidden id="tipoProcedimiento" name="tipoProcedimiento"/>
        <div class="card mt-5">
          <div class="card-header">
            <s:text name="fieldset.subsanacion"/>
          </div>
          <div class="card-body">
            <div class="row">
              <div class="col-sm-12">
                <div class="form-group row">
                  <label class = "col-sm-2 col-form-label" for="fechaRequerimientoSubsanacion"><s:text name="label.fechaRequerimiento"/></label>
                  <div class="col-sm-2">
                    <div class="date input-group" id="fhRequerimientoSubsanacion">
                      <input id="fechaRequerimientoSubsanacion" type="text" name="fechaRequerimiento" class="form-control" disabled="true">
                    </div>
                  </div>
                  <div class="col-sm-4"></div>
                  <label class = "col-sm-2 col-form-label" for="fechaLimiteSubsanacion"><s:text name="label.fechaLimite"/></label>
                  <div class="col-sm-2">
                    <div class="date input-group" id="fhLimiteSubsanacion">
                      <input id="fechaLimiteSubsanacion" type="text" name="fechaLimite" class="form-control" disabled="true">
                    </div>
                  </div>
                  <div class="col-sm-4"></div>
                </div>
                <div class="form-group row">
                  <label class = "col-sm-2 col-form-label" for="fechaEntregaSubsanacion"><s:text name="label.fechaEntrega"/></label>
                  <div class="col-sm-2">
                    <div class="date input-group" id="fhEntregaSubsanacion">
                      <input id="fechaEntregaSubsanacion" type="text" name="fechaEntrega" class="form-control" onblur="validFormSubsanacion()">
                      <div class="input-group-append">
                        <span class="input-group-addon input-group-text"><em class="fa fa-calendar"></em></span>
                      </div>
                    </div>
                  </div>
                  <div class="col-sm-4"></div>
                  <label class = "col-sm-2 col-form-label" for="fechaRegistroSubsanacion"><s:text name="label.fechaRegistro"/></label>
                  <div class="col-sm-2">
                    <div class="date input-group" id="fhRegistroSubsanacion">
                      <input id="fechaRegistroSubsanacion" type="text" name="fechaRegistro" class="form-control" onblur="validFormSubsanacion()">
                      <div class="input-group-append">
                        <span class="input-group-addon input-group-text"><em class="fa fa-calendar"></em></span>
                      </div>
                    </div>
                  </div>
                  <div class="col-sm-4"></div>
                </div>
                <div class="form-group row">
                  <label class = "col-sm-2 col-form-label" for="lugarRegistroSubsanacion"><s:text name="label.lugarRegistro"/></label>
                  <div class="col-sm-10">
                    <s:textarea theme="simple" cssClass="form-control rounded-0" id="lugarRegistroSubsanacion" rows="6" tooltip="Lugar Registro" name="lugarRegistro" onkeyup="compruebaLimite(this, 4000)"/>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="card mb-5">
          <div class="card-header">
            <s:text name="fieldset.subsanacionSoliciada"/>
          </div>
          <div class="card-body scroll-400">
            <table id="tablaSubsanacionesSeleccionadas" class="d-none w-100">
              <thead>
              <tr>
                <th>
                  Subsanación
                </th>
              </tr>
              </thead>
            </table>
            <div id="mensajeInfoSubsanacionSeleccioanda" class="text-center">
              No tiene subsanaciones pendientes.
            </div>
          </div>
        </div>
        <div class="card mb-5">
          <div class="card-header">
            <s:text name="fieldset.aniadirSubsanacion"/>
          </div>
          <div class="card-body">
            <div class="row">
              <div class="col-sm-12">
                <div id="subsanacionSeleccionada" class="d-none">
                  <div class="form-group row">
                    <div class="col-sm-12">
                      <s:a id="idSelecGrupoSubsanacionSeleccionada" theme="simple" href="javascript:cargarSelecGrupoSubsanacion()"><s:text name="label.boton.cambiar.tipo"/> <i class="fas fa-search"></i></s:a>
                    </div>
                  </div>
                  <div class="form-group row">
                    <div id="nombreSubsanacion" class="col-sm-12">
                    </div>
                  </div>
                  <div class="form-group row">
                    <div class="col-sm-12">
                      <s:hidden id="grupoSubsanacionSeleccionado" value=""/>
                      <s:a id="idSelecSubsanacion" theme="simple" href="javascript:cargarSelecSubsanacion()"><s:text name="label.boton.seleccionar.subsanaciones"/> <i class="fas fa-search"></i></s:a>
                    </div>
                  </div>
                </div>
                <div id ="subsanacionNoSeleccionada">
                  <div class="form-group row">
                    <div class="col-sm-12">
                      <s:a id="idSelecGrupoSubsanacion" theme="simple" href="javascript:cargarSelecGrupoSubsanacion()"><s:text name="label.boton.mostrar.subsanacion"/> <i class="fas fa-search"></i></s:a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div id="modalBuscarGrupo" class="modal fade">
          <div class="modal-dialog modal-xl">
            <div class="modal-content">
              <div class="modal-header">
                <h4 class="modal-title">Grupo Subsanación</h4>
                <button type="button" class="close" data-dismiss="modal">×</button>
              </div>
              <div class="modal-body">
                <table id="tablaGrupoSubsanaciones"  class="w-100">
                  <thead>
                  <tr>
                    <th>
                      Grupo Subsanación
                    </th>
                    <th>
                      Seleccionar
                    </th>
                  </tr>
                  </thead>
                </table>
              </div>
            </div>
          </div>
        </div>
        <div id="modalBuscarSubsanacion" class="modal fade">
          <div class="modal-dialog modal-xl">
            <div class="modal-content">
              <div class="modal-header">
                <h4 class="modal-title">Subsanación</h4>
                <button type="button" class="close" data-dismiss="modal">×</button>
              </div>
              <div class="modal-body">
                <table id="tablaSubsanaciones" class="w-100">
                  <thead>
                  <tr>
                    <th>
                      Seleccionar
                    </th>
                    <th>
                      Subsanación
                    </th>
                  </tr>
                  </thead>
                </table>
                <div class="float-right mb-4 mt-4">
                  <input class="btn btn-success btn-space" type="button" id="btnSeleccionar" value="SELECCIONAR" onclick="seleccionarSubsanacion(false)" data-dismiss="modal"/>
                </div>
              </div>
            </div>
          </div>
        </div>
      </s:form>
      <div class="float-right mb-4 mt-4">
      	<s:if test="datosGeneralesDTO.tienePermisosGuardado">
        <input class="btn btn-success mr-3" type="button" id="btnGuardar" value="GUARDAR" onclick="guardarSubsanacion()"/>
        </s:if>
        <input class="btn btn-danger" type="button" id="btnCerrar" value="<s:text name="CERRAR" />"  onclick="if (confirm('¿Está seguro que desea salir?')){window.close();return false; } else {return false;}" />
      </div>
    
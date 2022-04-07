<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><s:text name="title.datosSubsanacion"/></title>
    <!-- Scripts -->
    <s:include value="/modulos/tramitacionUniv/templates/head_scripts.jsp" />
    <script type="text/javascript" src="<s:url value="/modulos/tramitacionUniv/subsanacion/js/creacionSubsanacion.js"/>" ></script>
    <!-- CSS  -->
    <s:include value="/modulos/tramitacionUniv/templates/head_styles.jsp" />
    <s:head theme="ajax" />
  </head>
  <body>
    <div class="imagenCabecera mb-5"></div>
    <div id="divContainerDatosSubsanacion" class="container-fluid">
      <div class="justify-content-center row">
        <div id="messagesAndErrors" class="align-self-center col-sm-12 d-none">
        </div>
      </div>
      <s:form action="/guardarSubsanacionUniv.action" theme="simple">
        <div class="card mb-5">
          <div class="card-header">
            <s:text name="fieldset.subsanacion"/>
          </div>
          <div class="card-body">
            <div class="row">
              <div class="col-sm-12">
                <div class="form-group row">
                  <label class = "col-sm-2 col-form-label" for="idExp"><s:text name="label.numExpediente"/></label>
                  <div class="col-sm-4">
                    <s:textfield cssClass="form-control" id="idExp" tooltip="Número de expediente" name="datos.idExp" readonly="true"/>
                  </div>
                  <label class = "col-sm-2 col-form-label" for="numExpInterno"><s:text name="label.numExpediente.interno"/></label>
                  <div class="col-sm-4">
                    <s:textfield cssClass="form-control" id="numExpInterno" tooltip="Número de expediente interno" name="datos.idExpInterno" readonly="true"/>
                  </div>
                </div>
                <div class="form-group row">
                  <label class = "col-sm-2 col-form-label" for="titulo"><s:text name="label.titulo"/></label>
                  <div class="col-sm-10">
                    <s:textfield cssClass="form-control" id="titulo" tooltip="Número de expediente interno" name="datos.tituloProy" readonly="true"/>
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
                      <s:a theme="simple" href="javascript:cargarSelecGrupoSubsanacion()"><s:text name="label.boton.cambiar.tipo"/> <i class="fas fa-search"></i></s:a>
                    </div>
                  </div>
                  <div class="form-group row">
                    <div id="nombreSubsanacion" class="col-sm-12">
                    </div>
                  </div>
                  <div class="form-group row">
                    <div class="col-sm-12">
                      <s:hidden id="grupoSubsanacionSeleccionado" value=""/>
                      <s:a theme="simple" href="javascript:cargarSelecSubsanacion()"><s:text name="label.boton.seleccionar.subsanaciones"/> <i class="fas fa-search"></i></s:a>
                    </div>
                  </div>
                </div>
                <div id ="subsanacionNoSeleccionada">
                  <div class="form-group row">
                    <div class="col-sm-12">
                      <s:a theme="simple" href="javascript:cargarSelecGrupoSubsanacion()"><s:text name="label.boton.mostrar.subsanacion"/> <i class="fas fa-search"></i></s:a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="card mb-5">
          <div class="card-header">
            <s:text name="fieldset.otrosDatos"/>
          </div>
          <div class="card-body">
            <div class="row">
              <div class="col-sm-12">
                <div class="form-group row">
                  <label class = "col-sm-2 col-form-label" for="observaciones"><s:text name="label.Observaciones"/></label>
                  <div class="col-sm-10">
                    <s:textarea theme="simple" cssClass="form-control rounded-0" id="observaciones" rows="6" tooltip="Observaciones" name="datos.observaciones" onkeyup="compruebaLimite(this, 4000)"/>
                  </div>
                </div>
                <div class="form-group row">
                  <label class = "col-sm-2 col-form-label" for="motivoDesestimacion"><s:text name="label.motivo.desestimacion"/></label>
                  <div class="col-sm-10">
                    <s:textarea theme="simple" cssClass="form-control rounded-0" id="motivoDesestimacion" rows="6" tooltip="Motivo de desestimación" name="datos.observacionesDes" onkeyup="compruebaLimite(this, 4000)"/>
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
                <table id="tablaGrupoSubsanaciones" class="w-100">
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
        <input class="btn btn-success btn-space" type="button" id="btnGuardar" value="GUARDAR" onclick="guardarSubsanacion()"/>
        <input class="btn btn-danger btn-space" type="button" id="btnCerrar" value="<s:text name="CERRAR" />"  onclick="if (confirm('¿Está seguro que desea salir?')){window.close();return false; } else {return false;}" />
      </div>
    </div>
    <div class="fondoOverlay d-none"/>
  </body>
</html>

    
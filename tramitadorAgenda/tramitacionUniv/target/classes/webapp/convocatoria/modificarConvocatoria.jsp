<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <title><s:text name="title.convocatoria.modificacion" /></title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Scripts -->
    <s:include value="/modulos/tramitacionUniv/templates/head_scripts.jsp" />
    <script type="text/javascript"
      src="<s:url value="/modulos/tramitacionUniv/convocatoria/js/modificarConvocatoria.js"/>"></script>
    <script type="text/javascript">
    window.idCamposFechas = ["fecha_Inicio", "fecha_Fin", "fechaIniValoracion", "fechaPropReslProv", "fechaLimResolDef",  "fechaResolConsec", "fechaIniRecSol", "fechaFinRecSol", "fechaIniRecSub", "fechaFinRecSub", "fechaIniRecAlega", "fechaFinRecAlega", "fechaIniRecSub2", "fechaFinRecSub2", "fechaIniRecAARPD", "fechaFinRecAARPD", "fechaIniRecSubDoc", "fechaFinRecSubDoc", "fechaIniRecComIni", "fechaFinRecComIni"];
    $(document).ready(function() {
      initPantalla();
    });
    </script>
    <!-- CSS  -->
    <s:include value="/modulos/tramitacionUniv/templates/head_styles.jsp" />
    <s:head theme="ajax" />
  </head>
  <body>
    <div class="imagenCabecera mb-5"></div>
    <div id="divContainerModifConvocatoria" class="container-fluid">
      <div class="justify-content-center row">
        <div id="messagesAndErrors" class="align-self-center col-sm-12 d-none"></div>
      </div>
      <s:form id="formDatosConvocatoria" cssClass="form-horizontal" theme="simple">
        <div class="card">
          <div class="card-header">
            <s:text name="fieldset.convocatorias" />
          </div>
          <div class="card-body">
            <s:hidden id="idConv" name="convocatoriaDTO.idConv" />
            <s:hidden id="idConvFH" name="convocatoriaDTOconvocatoriaFechasDTO.idConvFh" />
            <div class="row">
              <div class="col-sm-12">
                <div class="form-group row">
                  <label class="col-sm-4 col-form-label obligatorio" for="idTitulo">
                    <s:text name="label.titulo" />
                  </label>
                  <div class="col-sm-8">
                    <s:textfield name="convocatoriaDTO.titulo" id="idTitulo" cssClass="form-control" maxlength="250" />
                  </div>
                </div>
                <div class="form-group row">
                  <label class="col-sm-4 col-form-label" for="idDescripcion">
                    <s:text name="label.descripcion" />
                  </label>
                  <div class="col-sm-8">
                    <s:textarea cssClass="form-control rounded-0" id="idDescripcion" rows="3" name="convocatoriaDTO.descripcion" />
                  </div>
                </div>
                <div class="form-group row">
                  <label class="col-sm-4 col-form-label obligatorio" for="fecha_Inicio">
                    <s:text name="label.fechaInicio" />
                  </label>
                  <div class="col-sm-2">
                    <div class="date input-group" id="fecha_Inicio">
                      <s:date id="fechaInicioFormateada" name="convocatoriaDTO.fhInicio" format="dd/MM/yyyy" />
                      <s:textfield name="convocatoriaDTO.fhInicio" maxlength="10" cssClass="form-control" value="%{fechaInicioFormateada}" />
                      <div class="input-group-append">
                        <span class="input-group-addon input-group-text">
                          <em class="fa fa-calendar"></em>
                        </span>
                      </div>
                    </div>
                    <!-- Div para alojar el label de error -->
                    <div></div>
                  </div>
                  <label class="col-sm-2 col-form-label obligatorio" for="fecha_Fin">
                    <s:text name="label.fechaFin" />
                  </label>
                  <div class="col-sm-2">
                    <div class="date input-group" id="fecha_Fin">
                      <s:date id="fechaFinFormateada" name="convocatoriaDTO.fhFin" format="dd/MM/yyyy" />
                      <s:textfield name="convocatoriaDTO.fhFin" maxlength="10" cssClass="form-control" value="%{fechaFinFormateada}" />
                      <div class="input-group-append">
                        <span class="input-group-addon input-group-text">
                          <em class="fa fa-calendar"></em>
                        </span>
                      </div>
                    </div>
                    <!-- Div para alojar el label de error -->
                    <div></div>
                  </div>
                </div>
                <div class="form-group row">
                  <label class="col-sm-4 col-form-label" for="fechaIniValoracion">
                    <s:text name="label.fechaInicioValoracion" />
                  </label>
                  <div class="col-sm-2">
                    <div class="date input-group" id="fechaIniValoracion">
                      <s:date id="fechaIniValFormateada" name="convocatoriaDTO.fhInicioValoracion" format="dd/MM/yyyy" />
                      <s:textfield name="convocatoriaDTO.fhInicioValoracion" maxlength="10" cssClass="form-control" value="%{fechaIniValFormateada}" />
                      <div class="input-group-append">
                        <span class="input-group-addon input-group-text">
                          <em class="fa fa-calendar"></em>
                        </span>
                      </div>
                    </div>
                    <!-- Div para alojar el label de error -->
                    <div></div>
                  </div>
                </div>
                <div class="form-group row">
                  <label class="col-sm-4 col-form-label" for="fechaPropReslProv">
                    <s:text name="label.fechaPropResolProvisional" />
                  </label>
                  <div class="col-sm-2">
                    <div class="date input-group" id="fechaPropReslProv">
                      <s:date id="fechaPropResolProvFormateada" name="convocatoriaDTO.fhPropResolProv" format="dd/MM/yyyy" />
                      <s:textfield name="convocatoriaDTO.fhPropResolProv" maxlength="10" cssClass="form-control" value="%{fechaPropResolProvFormateada}" />
                      <div class="input-group-append">
                        <span class="input-group-addon input-group-text">
                          <em class="fa fa-calendar"></em>
                        </span>
                      </div>
                    </div>
                    <!-- Div para alojar el label de error -->
                    <div></div>
                  </div>
                </div>
                <div class="form-group row">
                  <label class="col-sm-4 col-form-label" for="fechaLimResolDef">
                    <s:text name="label.fechaLimEnDocResolDefinitiva" />
                  </label>
                  <div class="col-sm-2">
                    <div class="date input-group" id="fechaLimResolDef">
                      <s:date id="fechaLimResolDefFormateada" name="convocatoriaDTO.fhLimitDocResolDef" format="dd/MM/yyyy" />
                      <s:textfield name="convocatoriaDTO.fhLimitDocResolDef" maxlength="10" cssClass="form-control" value="%{fechaLimResolDefFormateada}" />
                      <div class="input-group-append">
                        <span class="input-group-addon input-group-text">
                          <em class="fa fa-calendar"></em>
                        </span>
                      </div>
                    </div>
                    <!-- Div para alojar el label de error -->
                    <div></div>
                  </div>
                </div>
                <div class="form-group row">
                  <label class="col-sm-4 col-form-label" for="fechaResolConsec">
                    <s:text name="label.fechaResolConcesion" />
                  </label>
                  <div class="col-sm-2">
                    <div class="date input-group" id="fechaResolConsec">
                      <s:date id="fechaResolConsecFormateada" name="convocatoriaDTO.fhResolConc" format="dd/MM/yyyy" />
                      <s:textfield name="convocatoriaDTO.fhResolConc" maxlength="10" cssClass="form-control" value="%{fechaResolConsecFormateada}" />
                      <div class="input-group-append">
                        <span class="input-group-addon input-group-text">
                          <em class="fa fa-calendar"></em>
                        </span>
                      </div>
                    </div>
                    <!-- Div para alojar el label de error -->
                    <div></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="card mt-5">
          <div class="card-header">
            <s:text name="fieldset.fechas.fases.recepcion" />
          </div>
          <div class="card-body">
            <!-- Recepción solicitudes -->
            <div class="form-group row">
              <label class="col-sm-4 col-form-label obligatorio" for="fechaIniRecSol">
                <s:text name="label.fase.recepcion.solicitudes" />
              </label>
              <label class="col-sm-1 col-form-label obligatorio text-right" for="fechaIniRecSol">
                <s:text name="label.apertura" />
              </label>
              <div class="col-sm-2">
                <div class="date input-group" id="fechaIniRecSol">
                  <s:date id="fechaInicioRecSolFormateada" name="convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecSol" format="dd/MM/yyyy" />
                  <s:textfield name="convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecSol" maxlength="10" cssClass="form-control" value="%{fechaInicioRecSolFormateada}" />
                  <div class="input-group-append">
                    <span class="input-group-addon input-group-text">
                      <em class="fa fa-calendar"></em>
                    </span>
                  </div>
                </div>
                <!-- Div para alojar el label de error -->
                <div></div>
              </div>
              <label class="col-sm-2 col-form-label obligatorio text-right" for="fechaFinRecSol">
                <s:text name="label.cierre" />
              </label>
              <div class="col-sm-2">
                <div class="date input-group" id="fechaFinRecSol">
                  <s:date id="fechaFinRecSolFormateada" name="convocatoriaDTO.convocatoriaFechasDTO.fhFinRecSol" format="dd/MM/yyyy" />
                  <s:textfield name="convocatoriaDTO.convocatoriaFechasDTO.fhFinRecSol" maxlength="10" cssClass="form-control" value="%{fechaFinRecSolFormateada}" />
                  <div class="input-group-append">
                    <span class="input-group-addon input-group-text">
                      <em class="fa fa-calendar"></em>
                    </span>
                  </div>
                </div>
                <!-- Div para alojar el label de error -->
                <div></div>
              </div>
              <div class="col-sm-1"></div>
            </div>
          <!-- Recepción de subsanación -->
          <div class="form-group row">
              <label class="col-sm-4 col-form-label obligatorio" for="fechaIniRecSub">
                <s:text name="label.fase.recepcion.subsanacion" />
              </label>
              <label class="col-sm-1 col-form-label obligatorio text-right" for="fechaIniRecSub">
                <s:text name="label.apertura" />
              </label>
              <div class="col-sm-2">
                <div class="date input-group" id="fechaIniRecSub">
                  <s:date id="fechaInicioRecSubFormateada" name="convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecSub" format="dd/MM/yyyy" />
                  <s:textfield name="convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecSub" maxlength="10" cssClass="form-control" value="%{fechaInicioRecSubFormateada}" />
                  <div class="input-group-append">
                    <span class="input-group-addon input-group-text">
                      <em class="fa fa-calendar"></em>
                    </span>
                  </div>
                </div>
                <!-- Div para alojar el label de error -->
                <div></div>
              </div>
              <label class="col-sm-2 col-form-label obligatorio text-right" for="fechaFinRecSub">
                <s:text name="label.cierre" />
              </label>
              <div class="col-sm-2">
                <div class="date input-group" id="fechaFinRecSub">
                  <s:date id="fechaFinRecSubFormateada" name="convocatoriaDTO.convocatoriaFechasDTO.fhFinRecSub" format="dd/MM/yyyy" />
                  <s:textfield name="convocatoriaDTO.convocatoriaFechasDTO.fhFinRecSub" maxlength="10" cssClass="form-control" value="%{fechaFinRecSubFormateada}" />
                  <div class="input-group-append">
                    <span class="input-group-addon input-group-text">
                      <em class="fa fa-calendar"></em>
                    </span>
                  </div>
                </div>
                <!-- Div para alojar el label de error -->
                <div></div>
              </div>
              <div class="col-sm-1"></div>
            </div>
            <!-- Recepción de alegación -->
            <div class="form-group row">
              <label class="col-sm-4 col-form-label obligatorio" for="fechaIniRecAlega">
                <s:text name="label.fase.recepcion.alegacion" />
              </label>
              <label class="col-sm-1 col-form-label obligatorio text-right" for="fechaIniRecAlega">
                <s:text name="label.apertura" />
              </label>
              <div class="col-sm-2">
                <div class="date input-group" id="fechaIniRecAlega">
                  <s:date id="fechaInicioRecAlegaFormateada" name="convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecAlega" format="dd/MM/yyyy" />
                  <s:textfield name="convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecAlega" maxlength="10" cssClass="form-control" value="%{fechaInicioRecAlegaFormateada}" />
                  <div class="input-group-append">
                    <span class="input-group-addon input-group-text">
                      <em class="fa fa-calendar"></em>
                    </span>
                  </div>
                </div>
                <!-- Div para alojar el label de error -->
                <div></div>
              </div>
              <label class="col-sm-2 col-form-label obligatorio text-right" for="fechaFinRecAlega">
                <s:text name="label.cierre" />
              </label>
              <div class="col-sm-2">
                <div class="date input-group" id="fechaFinRecAlega">
                  <s:date id="fechaFinRecAlegaFormateada" name="convocatoriaDTO.convocatoriaFechasDTO.fhFinRecAlega" format="dd/MM/yyyy" />
                  <s:textfield name="convocatoriaDTO.convocatoriaFechasDTO.fhFinRecAlega" maxlength="10" cssClass="form-control" value="%{fechaFinRecAlegaFormateada}" />
                  <div class="input-group-append">
                    <span class="input-group-addon input-group-text">
                      <em class="fa fa-calendar"></em>
                    </span>
                  </div>
                </div>
                <!-- Div para alojar el label de error -->
                <div></div>
              </div>
              <div class="col-sm-1"></div>
            </div>
            <!-- Recepción de Subsanación (2) -->
            <div class="form-group row">
              <label class="col-sm-4 col-form-label obligatorio" for="fechaIniRecSub2">
                <s:text name="label.fase.recepcion.subsanacion2" />
              </label>
              <label class="col-sm-1 col-form-label obligatorio text-right" for="fechaIniRecSub2">
                <s:text name="label.apertura" />
              </label>
              <div class="col-sm-2">
                <div class="date input-group" id="fechaIniRecSub2">
                  <s:date id="fechaInicioRecSub2Formateada" name="convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecSub2" format="dd/MM/yyyy" />
                  <s:textfield name="convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecSub2" maxlength="10" cssClass="form-control" value="%{fechaInicioRecSub2Formateada}" />
                  <div class="input-group-append">
                    <span class="input-group-addon input-group-text">
                      <em class="fa fa-calendar"></em>
                    </span>
                  </div>
                </div>
                <!-- Div para alojar el label de error -->
                <div></div>
              </div>
              <label class="col-sm-2 col-form-label obligatorio text-right" for="fechaFinRecSub2">
                <s:text name="label.cierre" />
              </label>
              <div class="col-sm-2">
                <div class="date input-group" id="fechaFinRecSub2">
                  <s:date id="fechaFinRecSub2Formateada" name="convocatoriaDTO.convocatoriaFechasDTO.fhFinRecSub2" format="dd/MM/yyyy" />
                  <s:textfield name="convocatoriaDTO.convocatoriaFechasDTO.fhFinRecSub2" maxlength="10" cssClass="form-control" value="%{fechaFinRecSub2Formateada}" />
                  <div class="input-group-append">
                    <span class="input-group-addon input-group-text">
                      <em class="fa fa-calendar"></em>
                    </span>
                  </div>
                </div>
                <!-- Div para alojar el label de error -->
                <div></div>
              </div>
              <div class="col-sm-1"></div>
            </div>
            <!-- Recepción de Alegación/Aceptación/Reformulación/Prese. Doc. -->
            <div class="form-group row">
              <label class="col-sm-4 col-form-label obligatorio" for="fechaIniRecAARPD">
                <s:text name="label.fase.recepcion.alegaAceptaReformulaPresentDocu" />
              </label>
              <label class="col-sm-1 col-form-label obligatorio text-right" for="fechaIniRecAARPD">
                <s:text name="label.apertura" />
              </label>
              <div class="col-sm-2">
                <div class="date input-group" id="fechaIniRecAARPD">
                  <s:date id="fechaInicioRecAARPDFormateada" name="convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecAARPD" format="dd/MM/yyyy" />
                  <s:textfield name="convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecAARPD" maxlength="10" cssClass="form-control" value="%{fechaInicioRecAARPDFormateada}" />
                  <div class="input-group-append">
                    <span class="input-group-addon input-group-text">
                      <em class="fa fa-calendar"></em>
                    </span>
                  </div>
                </div>
                <!-- Div para alojar el label de error -->
                <div></div>
              </div>
              <label class="col-sm-2 col-form-label obligatorio text-right" for="fechaFinRecAARPD">
                <s:text name="label.cierre" />
              </label>
              <div class="col-sm-2">
                <div class="date input-group" id="fechaFinRecAARPD">
                  <s:date id="fechaFinRecAARPDFormateada" name="convocatoriaDTO.convocatoriaFechasDTO.fhFinRecAARPD" format="dd/MM/yyyy" />
                  <s:textfield name="convocatoriaDTO.convocatoriaFechasDTO.fhFinRecAARPD" maxlength="10" cssClass="form-control" value="%{fechaFinRecAARPDFormateada}" />
                  <div class="input-group-append">
                    <span class="input-group-addon input-group-text">
                      <em class="fa fa-calendar"></em>
                    </span>
                  </div>
                </div>
                <!-- Div para alojar el label de error -->
                <div></div>
              </div>
              <div class="col-sm-1"></div>
            </div>
            <!-- Recepción de Subsanación Doc. -->
            <div class="form-group row">
              <label class="col-sm-4 col-form-label obligatorio" for="fechaIniRecSubDoc">
                <s:text name="label.fase.recepcion.subsanacion.documentacion" />
              </label>
              <label class="col-sm-1 col-form-label obligatorio text-right" for="fechaIniRecSubDoc">
                <s:text name="label.apertura" />
              </label>
              <div class="col-sm-2">
                <div class="date input-group" id="fechaIniRecSubDoc">
                  <s:date id="fechaInicioRecSubDocFormateada" name="convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecSubDoc" format="dd/MM/yyyy" />
                  <s:textfield name="convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecSubDoc" maxlength="10" cssClass="form-control" value="%{fechaInicioRecSubDocFormateada}" />
                  <div class="input-group-append">
                    <span class="input-group-addon input-group-text">
                      <em class="fa fa-calendar"></em>
                    </span>
                  </div>
                </div>
                <!-- Div para alojar el label de error -->
                <div></div>
              </div>
              <label class="col-sm-2 col-form-label obligatorio text-right" for="fechaFinRecSubDoc">
                <s:text name="label.cierre" />
              </label>
              <div class="col-sm-2">
                <div class="date input-group" id="fechaFinRecSubDoc">
                  <s:date id="fechaFinRecSubDocFormateada" name="convocatoriaDTO.convocatoriaFechasDTO.fhFinRecSubDoc" format="dd/MM/yyyy" />
                  <s:textfield name="convocatoriaDTO.convocatoriaFechasDTO.fhFinRecSubDoc" maxlength="10" cssClass="form-control" value="%{fechaFinRecSubDocFormateada}" />
                  <div class="input-group-append">
                    <span class="input-group-addon input-group-text">
                      <em class="fa fa-calendar"></em>
                    </span>
                  </div>
                </div>
                <!-- Div para alojar el label de error -->
                <div></div>
              </div>
              <div class="col-sm-1"></div>
            </div>
            <!-- Recepción de Comunicación Inicio -->
            <div class="form-group row">
              <label class="col-sm-4 col-form-label obligatorio" for="fechaIniRecComIni">
                <s:text name="label.fase.recepcion.comunicacionInicio" />
              </label>
              <label class="col-sm-1 col-form-label obligatorio text-right" for="fechaIniRecComIni">
                <s:text name="label.apertura" />
              </label>
              <div class="col-sm-2">
                <div class="date input-group" id="fechaIniRecComIni">
                  <s:date id="fechaInicioRecComIniFormateada" name="convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecComIni" format="dd/MM/yyyy" />
                  <s:textfield name="convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecComIni" maxlength="10" cssClass="form-control" value="%{fechaInicioRecComIniFormateada}" />
                  <div class="input-group-append">
                    <span class="input-group-addon input-group-text">
                      <em class="fa fa-calendar"></em>
                    </span>
                  </div>
                </div>
                <!-- Div para alojar el label de error -->
                <div></div>
              </div>
              <label class="col-sm-2 col-form-label obligatorio text-right" for="fechaFinRecComIni">
                <s:text name="label.cierre" />
              </label>
              <div class="col-sm-2">
                <div class="date input-group" id="fechaFinRecComIni">
                  <s:date id="fechaFinRecComIniFormateada" name="convocatoriaDTO.convocatoriaFechasDTO.fhFinRecComIni" format="dd/MM/yyyy" />
                  <s:textfield name="convocatoriaDTO.convocatoriaFechasDTO.fhFinRecComIni" maxlength="10" cssClass="form-control" value="%{fechaFinRecComIniFormateada}" />
                  <div class="input-group-append">
                    <span class="input-group-addon input-group-text">
                      <em class="fa fa-calendar"></em>
                    </span>
                  </div>
                </div>
                <!-- Div para alojar el label de error -->
                <div></div>
              </div>
              <div class="col-sm-1"></div>
            </div>
          </div>
        </div>
      </s:form>
      <div class="float-right mt-4 mb-4">
        <input class="btn btn-success btn-space" type="button"
              id="btnGuardar" value="GUARDAR"
              onclick="createOrUpdateConvocatoria()" /> <input
              class="btn btn-danger btn-space" type="button" id="btnCerrar"
              value="<s:text name="CERRAR" />"
              onclick="if (confirm('¿Está seguro que desea salir?')){window.close();return false; } else {return false;}"></input>
      </div>
    </div>
    <div class="fondoOverlay d-none"/>
  </body>
</html>
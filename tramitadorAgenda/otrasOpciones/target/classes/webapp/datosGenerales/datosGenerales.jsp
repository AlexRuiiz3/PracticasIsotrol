<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- Scripts -->
<script type="text/javascript">
    // Ids de los campos del formulario que contienen datepickers para formatear. /
    window.idCamposFechas = [ "fhEntrada", "fhRegistro", "fhPagoSubv", "fhIniPrev", "fhPostegracionComunInicio"];
    window.formatoDatePicker = "dd/mm/yyyy";
    window.tablaAgrupacion = "#tablaAgrupacion";
    window.urlGuardarDatosGenerales = '<s:url action="guardarDatosGenerales" namespace="/modulos/otrasOpciones" includeParams="none"/>';

    initParametrizacionAgrupacion(
            [ '<s:text name="tabla.agrupacion.col.0.title"/>',
              '<s:text name="tabla.agrupacion.col.1.title"/>' ],
            [ '<s:text name="tabla.agrupacion.col.0.width"/>',
              '<s:text name="tabla.agrupacion.col.1.width"/>' ],
            '<s:url action="cargarListaAgrupaciones" namespace="/modulos/otrasOpciones" includeParams="none"/>',
            '<s:text name ="mensaje.mensajeSinAgrupacion"/>');
    $(document).ready(function() {
        initPestanaDatosGenerales();
    });
</script>
<s:if test="!datosGeneralesDTO.tienePermisosGuardado">
  <script type="text/javascript">
            $(document).ready(function() {
                $(":input[type='text']").attr("disabled", true);
                $(":input[type='checkbox']").attr("disabled", true);
            });
  </script>
</s:if>
<div class="justify-content-center row">
  <div id="messagesAndErrorsDatosGenerales" class="align-self-center col-sm-12 d-none"></div>
</div>
<s:form id="formDatosGenerales" cssClass="form-vertical" theme="simple">
  <s:hidden name="datosGeneralesDTO.idSolicitud" />
  <s:hidden name="datosGeneralesDTO.idSubsanacion" />
  <s:hidden id="idFinalidad" name="datosGeneralesDTO.finalidad" />
  <!--       Datos Generales -->
  <div class="card mt-5 mb-5">
    <div class="card-header">
      <h5><s:text name="fieldset.datosGenerales" /></h5>
    </div>
    <div class="card-body">
      <div class="form-group row">
        <label class="col-sm-3 col-form-label" for="numExp"><s:text name="label.numExp" /></label>
        <div class="col-sm-2">
          <s:textfield name="datosGeneralesDTO.numExp" id="numExp" cssClass="form-control" disabled="true" />
        </div>
        <label class="col-sm-2 col-form-label" for="numExpInterno"><s:text name="label.numExp.interno" /></label>
        <div class="col-sm-2">
          <s:textfield name="datosGeneralesDTO.numExpInterno" id="numExpInterno" maxlength="19" cssClass="form-control" />
        </div>
        <label class="col-sm-1 col-form-label" for="numOV"><s:text name="label.numOV" /></label>
        <div class="col-sm-2">
          <s:textfield name="datosGeneralesDTO.numOV" id="numOV" maxlength="19" cssClass="form-control" />
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-3 col-form-label" for="persRespon"><s:text name="label.pers.respo" /></label>
        <div class="col-sm-9">
          <s:textfield name="datosGeneralesDTO.persRespon" id="persRespon" cssClass="form-control" disabled="true" />
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-3 col-form-label" for="unidRespon"><s:text name="label.unidad.responsable" /></label>
        <div class="col-sm-9">
          <s:textarea name="datosGeneralesDTO.unidRespon" id="unidRespon" cssClass="form-control" disabled="true" />
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-3 col-form-label" for="fhEntrada"><s:text name="label.fecha.entrada" /></label>
        <div class="col-sm-2">
          <div class="date input-group" id="fhEntrada">
            <s:date id="fechaEntradaFormateada" name="datosGeneralesDTO.fechaEntrada" format="dd/MM/yyyy" />
            <s:textfield name="datosGeneralesDTO.fechaEntrada" maxlength="10" cssClass="form-control" value="%{fechaEntradaFormateada}" />
            <div class="input-group-append">
              <span class="input-group-addon input-group-text"><em class="fa fa-calendar"></em></span>
            </div>
          </div>
          <!-- Div para alojar el label de error -->
          <div></div>
        </div>
        <div class="col-sm-2"></div>
        <label class="col-sm-2 col-form-label" for="fhRegistro"><s:text name="label.fecha.registro" /></label>
        <div class="col-sm-2">
          <div class="date input-group" id="fhRegistro">
            <s:date id="fechaRegistroFormateada" name="datosGeneralesDTO.fechaRegistro" format="dd/MM/yyyy" />
            <s:textfield name="datosGeneralesDTO.fechaRegistro" maxlength="10" cssClass="form-control" value="%{fechaRegistroFormateada}" />
            <div class="input-group-append">
              <span class="input-group-addon input-group-text"><em class="fa fa-calendar"></em></span>
            </div>
          </div>
          <!-- Div para alojar el label de error -->
          <div></div>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-3 col-form-label checkboxLabel form-text" for="tieneSub"><s:text name="label.tiene.subsnacion" /></label>
        <div class="col-sm-1">
          <s:textfield name="datosGeneralesDTO.tieneSub" id="tieneSub" cssClass="form-control" disabled="true" />
        </div>
      </div>
      <s:if test="datosGeneralesDTO.pasaAValoracion!=null">
      <div class="form-group row">
        <label class="col-sm-3 col-form-label checkboxLabel form-text" for="pasaAValoracion"><s:text name="label.pasaAValoracion" /></label>
        <div class="col-sm-1">
          <s:textfield name="datosGeneralesDTO.pasaAValoracion" id="pasaAValoracion" cssClass="form-control" disabled="true" />
        </div>
      </div>
      </s:if>
      <div class="form-group row">
        <label class="col-sm-3 col-form-label checkboxLabel form-text" for="presupReformu"><s:text name="label.presupuesto.reformulado" /></label>
        <div class="col-sm-1">
          <s:textfield name="datosGeneralesDTO.presupFormu" id="presupReformu" cssClass="form-control" disabled="true" />
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-3 col-form-label" for="fhPagoSubv"><s:text name="label.fecha.pago.subvencion" /></label>
        <div class="col-sm-2">
          <div class="date input-group" id="fhPagoSubv">
            <s:date id="fechaPagoFormateada" name="datosGeneralesDTO.fechaPagoSubvencion" format="dd/MM/yyyy" />
            <s:textfield name="datosGeneralesDTO.fechaPagoSubvencion" maxlength="10" cssClass="form-control" value="%{fechaPagoFormateada}" />
            <div class="input-group-append">
              <span class="input-group-addon input-group-text"><em class="fa fa-calendar"></em></span>
            </div>
          </div>
          <!-- Div para alojar el label de error -->
          <div></div>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-3 col-form-label" for="fhPostegracionComunInicio"><s:text name="label.fechaPostegracionComunInicio" /></label>
        <div class="col-sm-2">
          <div class="date input-group" id="fhPostegracionComunInicio">
            <s:date id="fhPostegracionComunInicioFormateada" name="datosGeneralesDTO.fechaPostegracionComunInicio" format="dd/MM/yyyy" />
            <s:textfield name="datosGeneralesDTO.fechaPostegracionComunInicio" maxlength="10" cssClass="form-control" value="%{fhPostegracionComunInicioFormateada}" />
            <div class="input-group-append">
              <span class="input-group-addon input-group-text"><em class="fa fa-calendar"></em></span>
            </div>
          </div>
          <!-- Div para alojar el label de error -->
          <div></div>
        </div>
      </div>
    </div>
  </div>
  
  <!--         Vistos Buenos -->
  <s:if test="datosGeneralesDTO.mostrarVB">
    <div class="card mb-5">
      <div class="card-header">
        <h5><s:text name="fieldset.vistosBuenos" /></h5>
      </div>
      <div class="card-body">
        <div class="form-group row">
          <label class="col-sm-2 col-form-label checkboxLabel form-text" for="vbTecnico"><s:text name="label.visto.bueno.tecnico" /></label>
          <div class="col-sm-4">
            <s:checkbox name="datosGeneralesDTO.vbTecnico" id="vbTecnico" cssClass="form-control-lg" disabled="%{!datosGeneralesDTO.editarVBTecnico}" theme="simple" />
          </div>
          <label class="col-sm-2 col-form-label checkboxLabel form-text" for="vbCoordinador"><s:text name="label.visto.bueno.coord" /></label>
          <div class="col-sm-4">
            <s:checkbox name="datosGeneralesDTO.vbCoord" id="vbCoordinador" cssClass="form-control-lg" disabled="%{!datosGeneralesDTO.editarVBCoordinador}" theme="simple" />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-sm-2 col-form-label" for="resumen"><s:text name="label.resumen" /></label>
          <div class="col-form-label-sm col-sm-10">
            <span id="resumen" cssClass="form-text"><s:property value="datosGeneralesDTO.resumen" /></span>
          </div>
        </div>
      </div>
    </div>
  </s:if>
  <!--         Proyecto -->
  <div class="card mb-5">
    <div class="card-header">
      <h5><s:text name="fieldset.proyecto" /></h5>
    </div>
    <div class="card-body">
      <div class="form-group row">
        <label class="col-sm-2 col-form-label" for="tituloProy"><s:text name="label.titulo" /></label>
        <div class="col-sm-10">
          <s:textfield name="datosGeneralesDTO.tituloProy" id="tituloProy" cssClass="form-control" disabled="true" />
        </div>
      </div>
      <s:if test="datosGeneralesDTO.accionHuman || datosGeneralesDTO.cooperacion">
        <div class="form-group row">
          <label class="col-sm-2 col-form-label" for="pais"><s:text name="label.pais" /></label>
          <div class="col-sm-6">
            <s:textfield name="datosGeneralesDTO.pais" id="pais" maxlength="72" readonly="true" cssClass="form-control" />
          </div>
        </div>
      </s:if>
      <div class="form-group row">
        <label class="col-sm-2 col-form-label" for="plazoEjec"><s:text name="label.plazoEjec" /></label>
        <div class="col-sm-4">
          <s:textfield name="datosGeneralesDTO.plazoEjec" id="plazoEjec" cssClass="form-control" disabled="true" />
        </div>
        <label class="col-sm-2 col-form-label" for="fhIniPrev"><s:text name="label.fhIniPrev" /></label>
        <div class="col-sm-2">
          <div class="date input-group" id="fhIniPrev">
            <s:date id="fechaInicioPrevistaFormateada" name="datosGeneralesDTO.fechaInicio" format="dd/MM/yyyy" />
            <s:textfield name="datosGeneralesDTO.fechaInicio" maxlength="10" cssClass="form-control" value="%{fechaInicioPrevistaFormateada}" />
            <div class="input-group-append">
              <span class="input-group-addon input-group-text"><em class="fa fa-calendar"></em></span>
            </div>
          </div>
          <!-- Div para alojar el label de error -->
          <div></div>
        </div>
        <div class="col-sm-2"></div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 col-form-label" for="descripcionProy"><s:text name="label.descripcionProy" /></label>
        <div class="col-sm-10">
          <s:textarea name="datosGeneralesDTO.descripcionProy" id="descripcionProy" cols="80" rows="6" cssClass="form-control" disabled="true" />
        </div>
      </div>
      <s:if test="datosGeneralesDTO.formacion || datosGeneralesDTO.educacion">
        <div class="form-group row">
          <label class="col-sm-2 col-form-label" for="impoSoli"><s:text name="label.impoSoli" /></label>
          <div class="col-sm-2">
            <s:textfield name="datosGeneralesDTO.impoSoli" id="impoSoli" cssClass="form-control" disabled="true" />
          </div>
        </div>
        <div class="col-sm-8"></div>
      </s:if>
      <s:if test="datosGeneralesDTO.accionHuman">
        <div class="form-group row">
          <label class="col-sm-2 col-form-label" for="localidadEjec"><s:text name="label.localidadEjec" /></label>
          <div class="col-sm-4">
            <s:textfield name="datosGeneralesDTO.localidadEjec" id="localidadEjec" cssClass="form-control" disabled="true" />
          </div>
          <label class="col-sm-2 col-form-label" for="impoSoli"><s:text name="label.impoSoli" /></label>
          <div class="col-sm-2">
            <s:textfield name="datosGeneralesDTO.impoSoli" id="impoSoli" cssClass="form-control" disabled="true" />
          </div>
          <div class="col-sm-2"></div>
        </div>
        <div class="form-group row">
          <label class="col-sm-2 col-form-label checkboxLabel form-text" for="prevRiesgos"><s:text name="label.prevRiesgos" /></label>
          <div class="col-sm-4">
            <s:checkbox name="datosGeneralesDTO.prevRiesgos" id="prevRiesgos" cssClass="form-control-lg" disabled="true" theme="simple" />
          </div>
        </div>
      </s:if>
      <s:if test="datosGeneralesDTO.cooperacion">
        <div class="form-group row">
          <label class="col-sm-2 col-form-label" for="localidadEjec"><s:text name="label.localidadEjec" /></label>
          <div class="col-sm-4">
            <s:textfield name="datosGeneralesDTO.localidadEjec" id="localidadEjec" cssClass="form-control" disabled="true" />
          </div>
          <label class="col-sm-2 col-form-label" for="impoSoli"><s:text name="label.impoSoli" /></label>
          <div class="col-sm-2">
            <s:textfield name="datosGeneralesDTO.impoSoli" id="impoSoli" cssClass="form-control" disabled="true" />
          </div>
          <div class="col-sm-2"></div>
        </div>
        <div class="form-group row">
          <label class="col-sm-2 col-form-label checkboxLabel form-text" for="africa"><s:text name="label.africa" /></label>
          <div class="col-sm-4">
            <s:checkbox name="datosGeneralesDTO.africa" id="africa" cssClass="form-control-lg" theme="simple" disabled="%{datosGeneralesDTO.tieneSub!='SI'}" />
          </div>
          <label class="col-sm-2 col-form-label checkboxLabel form-text" for="servSoc"><s:text name="label.servSoc" /></label>
          <div class="col-sm-4">
            <s:checkbox name="datosGeneralesDTO.servSoc" id="servSoc" cssClass="form-control-lg" theme="simple" disabled="%{datosGeneralesDTO.tieneSub!='SI'}" />
          </div>
        </div>
        <div class="form-group row">
          <label class="col-sm-2 col-form-label checkboxLabel form-text" for="otros"><s:text name="label.otros" /></label>
          <div class="col-sm-4">
            <s:checkbox name="datosGeneralesDTO.otros" id="otros" cssClass="form-control-lg" theme="simple" disabled="%{datosGeneralesDTO.tieneSub!='SI'}" />
          </div>
          <label class="col-sm-2 col-form-label checkboxLabel form-text" for="noEspec"><s:text name="label.noEspec" /></label>
          <div class="col-sm-4">
            <s:checkbox name="datosGeneralesDTO.noEspec" id="noEspec" cssClass="form-control-lg" theme="simple" disabled="%{datosGeneralesDTO.tieneSub!='SI'}" />
          </div>
        </div>
      </s:if>
      <s:if test="datosGeneralesDTO.formacion || datosGeneralesDTO.educacion">
        <div class="form-group row">
          <label class="col-sm-2 col-form-label checkboxLabel form-text" for="almeria"><s:text name="label.almeria" /></label>
          <div class="col-sm-4">
            <s:checkbox name="datosGeneralesDTO.almeria" id="almeria" cssClass="form-control-lg" theme="simple" disabled="%{datosGeneralesDTO.tieneSub!='SI'}" />
          </div>
          <label class="col-sm-2 col-form-label checkboxLabel form-text" for="cadiz"><s:text name="label.cadiz" /></label>
          <div class="col-sm-4">
            <s:checkbox name="datosGeneralesDTO.cadiz" id="cadiz" cssClass="form-control-lg" theme="simple" disabled="%{datosGeneralesDTO.tieneSub!='SI'}" />
          </div>
          <label class="col-sm-2 col-form-label checkboxLabel form-text" for="cordoba"><s:text name="label.cordoba" /></label>
          <div class="col-sm-4">
            <s:checkbox name="datosGeneralesDTO.cordoba" id="cordoba" cssClass="form-control-lg" theme="simple" disabled="%{datosGeneralesDTO.tieneSub!='SI'}" />
          </div>
          <label class="col-sm-2 col-form-label checkboxLabel form-text" for="granada"><s:text name="label.granada" /></label>
          <div class="col-sm-4">
            <s:checkbox name="datosGeneralesDTO.granada" id="granada" cssClass="form-control-lg" theme="simple" disabled="%{datosGeneralesDTO.tieneSub!='SI'}" />
          </div>
          <label class="col-sm-2 col-form-label checkboxLabel form-text" for="huelva"><s:text name="label.huelva" /></label>
          <div class="col-sm-4">
            <s:checkbox name="datosGeneralesDTO.huelva" id="huelva" cssClass="form-control-lg" theme="simple" disabled="%{datosGeneralesDTO.tieneSub!='SI'}" />
          </div>
          <label class="col-sm-2 col-form-label checkboxLabel form-text" for="jaen"><s:text name="label.jaen" /></label>
          <div class="col-sm-4">
            <s:checkbox name="datosGeneralesDTO.jaen" id="jaen" cssClass="form-control-lg" theme="simple" disabled="%{datosGeneralesDTO.tieneSub!='SI'}" />
          </div>
          <label class="col-sm-2 col-form-label checkboxLabel form-text" for="malaga"><s:text name="label.malaga" /></label>
          <div class="col-sm-4">
            <s:checkbox name="datosGeneralesDTO.malaga" id="malaga" cssClass="form-control-lg" theme="simple" disabled="%{datosGeneralesDTO.tieneSub!='SI'}" />
          </div>
          <label class="col-sm-2 col-form-label checkboxLabel form-text" for="sevilla"><s:text name="label.sevilla" /></label>
          <div class="col-sm-4">
            <s:checkbox name="datosGeneralesDTO.sevilla" id="sevilla" cssClass="form-control-lg" theme="simple" disabled="%{datosGeneralesDTO.tieneSub!='SI'}" />
          </div>
        </div>
      </s:if>
    </div>
  </div>
  <!--         Entidad Solicitante -->
  <div class="card mb-5">
    <div class="card-header">
      <h5><s:text name="fieldset.entidadSolicitante" /></h5>
    </div>
    <div class="card-body">
      <div class="form-group row">
        <label class="col-sm-2 col-form-label" for="nombreEntidad"><s:text name="label.nombreEntidad" /></label>
        <div class="col-sm-10">
          <s:textfield name="datosGeneralesDTO.entidadSolicitante.nombreEntidad" id="nombreEntidad" cssClass="form-control" disabled="true" />
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 col-form-label" for="cif"><s:text name="label.cif" /></label>
        <div class="col-sm-2">
          <s:textfield name="datosGeneralesDTO.entidadSolicitante.cif" id="cif" cssClass="form-control" disabled="true" />
        </div>
        <div class="col-sm-2"></div>
        <label class="col-sm-2 col-form-label" for="email"><s:text name="label.email" /></label>
        <div class="col-sm-4">
          <s:textfield name="datosGeneralesDTO.entidadSolicitante.email" id="email" cssClass="form-control" disabled="true" />
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 col-form-label" for="direccion"><s:text name="label.direccion" /></label>
        <div class="col-sm-10">
          <s:textfield name="datosGeneralesDTO.entidadSolicitante.direccion" id="direccion" cssClass="form-control" disabled="true" />
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 col-form-label" for="codPostal"><s:text name="label.codPostal" /></label>
        <div class="col-sm-1">
          <s:textfield name="datosGeneralesDTO.entidadSolicitante.codPostal" id="codPostal" cssClass="form-control" disabled="true" />
        </div>
        <div class="col-sm-3"></div>
        <label class="col-sm-2 col-form-label" for="localidad"><s:text name="label.localidad" /></label>
        <div class="col-sm-4">
          <s:textfield name="datosGeneralesDTO.entidadSolicitante.localidad" id="localidad" cssClass="form-control" disabled="true" />
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 col-form-label" for="provincia"><s:text name="label.provincia" /></label>
        <div class="col-sm-4">
          <s:textfield name="datosGeneralesDTO.entidadSolicitante.provincia" id="provincia" cssClass="form-control" disabled="true" />
        </div>
      </div>
    </div>
  </div>
  <!--         Agrupaciones -->
  <div class="card mb-5">
    <div class="card-header">
      <h5><s:text name="fieldset.agrupaciones" /></h5>
    </div>
    <div class="card-body">
      <table id="tablaAgrupacion" class="table table-bordered table-sm">
        <thead class="thead-dark">
          <tr>
            <th title='<s:text name="tabla.agrupacion.col.0.title"/>'><s:text name="tabla.agrupacion.col.0.title" /></th>
            <th title='<s:text name="tabla.agrupacion.col.1.title"/>'><s:text name="tabla.agrupacion.col.1.title" /></th>
          </tr>
        </thead>
        <tbody>
        </tbody>
      </table>
      <div class="form-group row mt-5">
        <label class="col-sm-2 col-form-label" for="apellidosSol"><s:text name="label.apellidosSol" /></label>
        <div class="col-sm-3">
          <s:textfield name="datosGeneralesDTO.apellidosSol" id="apellidosSol" maxlength="50" size="15" cssClass="form-control" />
        </div>
        <div class="col-sm-1"></div>
        <label class="col-sm-2 col-form-label" for="nombreSol"><s:text name="label.nombreSol" /></label>
        <div class="col-sm-3">
          <s:textfield name="datosGeneralesDTO.nombreSol" id="nombreSol" maxlength="50" size="15" cssClass="form-control" />
        </div>
        <div class="col-sm-1"></div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 col-form-label" for="nifSol"><s:text name="label.nifSol" /></label>
        <div class="col-sm-2">
          <s:textfield name="datosGeneralesDTO.nifSol" id="nifSol" maxlength="9" size="9" cssClass="form-control" />
        </div>
        <div class="col-sm-2"></div>
        <label class="col-sm-2 col-form-label" for="ostentaRepresentacion"><s:text name="label.ostentaRepresentacion" /></label>
        <div class="col-sm-4">
          <s:checkbox name="datosGeneralesDTO.ostentaRepresentacion" id="ostentaRepresentacion" cssClass="form-control-lg" theme="simple" />
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 col-form-label" for="observaciones"><s:text name="label.observaciones" /></label>
        <div class="col-sm-10">
          <s:textarea name="datosGeneralesDTO.observaciones" id="observaciones" onkeyup="compruebaLimite(this, 4000)" cssClass="form-control" disabled="%{datosGeneralesDTO.tieneSub!='SI'}" />
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 col-form-label" for="motivoDesestimacion"><s:text name="label.motivoDesestimacion" /></label>
        <div class="col-sm-10">
          <s:textarea name="datosGeneralesDTO.motivoDesestimacion" id="motivoDesestimacion" onkeyup="compruebaLimite(this, 4000)" cssClass="form-control" disabled="%{datosGeneralesDTO.tieneSub!='SI'}" />
        </div>
      </div>
    </div>
  </div>
</s:form>
<div class="float-right mt-4 mb-4">
  <s:if test="datosGeneralesDTO.tienePermisosGuardado">
    <button class="btn btn-success mr-3" type="button" id="btnGuardar" value="GUARDAR" onclick="guardarDatosGenerales()">GUARDAR</button>
  </s:if>
  <button class="btn btn-danger" type="button" id="btnCerrar" value="<s:text name="CERRAR" />" onclick="if (confirm('Se perderán los datos que no hayan sido guardados.\n¿Deseas continuar?')){window.close();return false; } else {return false;}">
    <s:text name="CERRAR" />
  </button>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:url value="/modulos/valoracion/js/totalesValoracion.js"/>"></script>
<script type="text/javascript" src="<s:url value="/modulos/valoracion/js/comun.js"/>"></script>
<script type="text/javascript">
  window.tablaIncrementos = "#tablaListadoIncremento";
  window.urlActionListadoIncrementos = '<s:url action="cargaListadoIncrementos" namespace="/modulos/valoracion" includeParams="none"/>';
  window.maximoPuntuacionIncremento = '<s:text name="incremento.puntuacion.max"/>';
  
  initParametrizacionIncrementos([
    '<s:text name="tabla.propiedades.comunes.col.0.title"/>',
    '<s:text name="tabla.propiedades.incrementos.col.title"/>',
    '<s:text name="tabla.propiedades.comunes.col.2.title"/>' ], [
    '<s:text name="tabla.propiedades.comunes.col.0.width"/>',
    '<s:text name="tabla.propiedades.comunes.col.1.width"/>',
    '<s:text name="tabla.propiedades.comunes.col.2.width"/>' ]);

  $(document).ready(function() {
    initPestanaIncrementos();
  });
</script>
<s:form id="formDatosPestania4" cssClass="form-vertical" theme="simple">
<input type="hidden" id="totalPuntuacionPertinenciaOculto" />
    <input type="hidden" id="totalPuntuacionViabilidadOculto" />
    <input type="hidden" id="totalPuntuacionCoherenciaOculto" />
    <input type="hidden" id="totalPuntuacionConectividadOculto" />
    <input type="hidden" id="totalPuntuacionSostenibilidadOculto" />
    <input type="hidden" id="totalPuntuacionImpactoOculto" />
    <input type="hidden" id="totalPuntuacionConvergenciaOculto" />
    <input type="hidden" id="totalPuntuacionFase1Oculto" />
    <input type="hidden" id="totalPuntuacionFase2Oculto" />
    <input type="hidden" id="totalPuntuacionFase12Oculto" />
    <input type="hidden" id="totalPuntuacionIncrementoOculto" />
    <input type="hidden" id="totalPuntuacionCapGestionOculto" />
  <s:hidden name='valoracionSolicitudDTO.permiteSumaIncrementos'/> 
  <s:if test ="valoracionSolicitudDTO.ongd">
    <div id="divContainerIncrementos" class="container-fluid">
      <div class="card mt-5">
        <div class="card-header">
           <s:text name="tipoCriterio.incrementos" />
        </div>
        <div class="card-body">
          <table id="tablaListadoIncremento" class="tableCriterios table table-striped table-sm">
            <thead>
              <tr>
                <th scope="col" class="hide" />
                <th scope="col" class="col-9" title='<s:text name="tabla.propiedades.comunes.col.0.title"/>' />
                <th scope="col" class="text-center col-1" title='<s:text name="tabla.propiedades.incrementos.col.title"/>' />
                <th scope="col" class="text-center col-1" title='<s:text name="tabla.propiedades.comunes.col.2.title"/>' />
                <th scope="col" class="hide" />
              </tr>
            </thead>
            <tbody>
            </tbody>
            <tfoot>
              <tr>
                <th scope="col" class="hide"/>
                <th scope="col"/>
                <th scope="col" class="text-center"><s:text name="label.total"/></th>
                <th scope="col"/>
                <th scope="col" class="hide"/>
              </tr>
            </tfoot>
          </table>
      <div id="idNoPermiteSumaIncremento" class="alert alert-danger struts2">
        <em class="fas fa-times-circle"></em>
        <s:text name="msg.noPermite.suma.incrementos"/>
      </div>
    </div>
  </div>
    </div>
  </s:if>
  <div id="divContainerTablaTotales" class="container-fluid">
    <div class="card mt-5">
      <div class="card-header">
        <s:text name="tipoCriterio.valoracion.total" />
      </div>
      <div class="card-body">
        <div class="row">
          <div class="col-sm-12">
          <s:form id="formDatosPestania3" cssClass="form-vertical" theme="simple">
            <table id="tablaValoracionTotal" class="table table-bordered table-striped">
              <thead>
                <tr>
                  <th scope="col" class="text-left" title='<s:text name="tabla.valoracion.criterios"/>'><s:text name="tabla.valoracion.criterios" /></th>
                  <th scope="col" class="text-left" title='<s:text name="tabla.valoracion.puntuacion.obtenida"/>'><s:text name="tabla.valoracion.puntuacion.obtenida" /></th>
                  <th scope="col" class="text-left" title='<s:text name="tabla.valoracion.puntuacion.max"/>'><s:text name="tabla.valoracion.puntuacion.max" /></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th scope="row" class="text-secondary" title='<s:text name="tipoCriterio.pertinencia"/>'><s:text name="tipoCriterio.pertinencia" /></th>
                  <td id="totalPuntuacionPertinencia" class="text-right"><s:property value ="valoracionSolicitudDTO.pertinencia.totalPuntuacion"/></td>
                  <td id="puntuacionMaximaPertinencia" class="text-right"><s:property value ="valoracionSolicitudDTO.pertinencia.puntuacionMaxima"/></td>
                </tr>
                <tr>
                  <th scope="row" class="text-secondary" title='<s:text name="tipoCriterio.viabilidad"/>'><s:text name="tipoCriterio.viabilidad" /></th>
                  <td id="totalPuntuacionViabilidad" class="text-right"><s:property value ="valoracionSolicitudDTO.viabilidad.totalPuntuacion"/></td>
                  <td id="puntuacionMaximaViabilidad" class="text-right"><s:property value ="valoracionSolicitudDTO.viabilidad.puntuacionMaxima"/></td>
                </tr>
                <tr>
                  <th scope="row" class="text-secondary" title='<s:text name="tipoCriterio.coherencia"/>'><s:text name="tipoCriterio.coherencia" /></th>
                  <td id="totalPuntuacionCoherencia" class="text-right"><s:property value ="valoracionSolicitudDTO.coherencia.totalPuntuacion"/></td>
                  <td id="puntuacionMaximaCoherencia" class="text-right"><s:property value ="valoracionSolicitudDTO.coherencia.puntuacionMaxima"/></td>
                </tr>
                <s:if test ="'AH'==valoracionSolicitudDTO.codigoFinalidad">
                  <tr>
                    <th scope="row" class="text-secondary" title='<s:text name="tipoCriterio.conectividad"/>'><s:text name="tipoCriterio.conectividad" /></th>
                    <td id="totalPuntuacionConectividad" class="text-right"><s:property value ="valoracionSolicitudDTO.conectividad.totalPuntuacion"/></td>
                    <td id="puntuacionMaximaConectividad" class="text-right"><s:property value ="valoracionSolicitudDTO.conectividad.puntuacionMaxima"/></td>
                  </tr>
                </s:if>
                <s:else>
                  <tr>
                    <th scope="row" class="text-secondary" title='<s:text name="tipoCriterio.sostenibilidad"/>'><s:text name="tipoCriterio.sostenibilidad" /></th>
                    <td id="totalPuntuacionSostenibilidad" class="text-right"><s:property value ="valoracionSolicitudDTO.sostenibilidad.totalPuntuacion"/></td>
                    <td id="puntuacionMaximaSostenibilidad" class="text-right"><s:property value ="valoracionSolicitudDTO.sostenibilidad.puntuacionMaxima"/></td>
                  </tr>
                </s:else>
                <tr>
                  <th scope="row" class="text-secondary" title='<s:text name="tipoCriterio.impacto"/>'><s:text name="tipoCriterio.impacto" /></th>
                  <td id="totalPuntuacionImpacto" class="text-right"><s:property value ="valoracionSolicitudDTO.impacto.totalPuntuacion"/></td>
                  <td id="puntuacionMaximaImpacto" class="text-right"><s:property value ="valoracionSolicitudDTO.impacto.puntuacionMaxima"/></td>
                </tr>
                <s:if test ="valoracionSolicitudDTO.ongd">
                  <tr>
                    <th scope="row" title='<s:text name="tipoCriterio.puntuacion.fase1"/>'><s:text name="tipoCriterio.puntuacion.fase1" /></th>
                    <td id="totalPuntuacionFase1" class="text-right"/>
                    <td />
                  </tr>
                  <s:if test ='%{"C" ==valoracionSolicitudDTO.codigoFinalidad}'>
                    <tr>
                      <th scope="row" class="text-secondary" title='<s:text name="tipoCriterio.convergencia"/>'><s:text name="tipoCriterio.convergencia" /></th>
                      <td id="totalPuntuacionConvergencia" class="text-right"><s:property value ="valoracionSolicitudDTO.convergencia.totalPuntuacion"/></td>
                      <td id="puntuacionMaximaConvergencia" class="text-right"><s:property value ="valoracionSolicitudDTO.convergencia.puntuacionMaxima"/></td>
                    </tr>
                  </s:if>
                  <tr>
                    <th scope="row" class="text-secondary" title='<s:text name="tipoCriterio.capgestion"/>'><s:text name="tipoCriterio.capgestion" /></th>
                    <td id="totalPuntuacionCapGestion" class="text-right"><s:property value ="valoracionSolicitudDTO.capgestion.totalPuntuacion"/></td>
                    <td id="puntuacionMaximaCapGestion" class="text-right"><s:property value ="valoracionSolicitudDTO.capgestion.puntuacionMaxima"/></td>
                  </tr>
                  <tr>
                    <th scope="row" title='<s:text name="tipoCriterio.puntuacion.fase2"/>'><s:text name="tipoCriterio.puntuacion.fase2" /></th>
                    <td id="totalPuntuacionFase2" class="text-right"/>
                    <td />
                  </tr>
                  <tr>
                    <th scope="row" title='<s:text name="tipoCriterio.puntuacion.fase1Fase2"/>'><s:text name="tipoCriterio.puntuacion.fase1Fase2" /></th>
                    <td id="totalPuntuacionFase1Fase2" class="text-right"/>
                    <td />
                  </tr>
                  <tr>
                    <th scope="row" title='<s:text name="tipoCriterio.puntuacion.incremento"/>'><s:text name="tipoCriterio.puntuacion.incremento" /></th>
                    <td id="totalPuntuacionIncremento" class="text-right"/>
                    <td />
                  </tr>
                </s:if>
                <tr>
                  <th class="text-nowrap" scope="row" title='<s:text name="tabla.valoracion.puntuacion.total"/>'><s:text name="tabla.valoracion.puntuacion.total" /></th>
                  <td id="totalPuntuacionSuma" class="text-right"><s:property value ="valoracionSolicitudDTO.valoracionTotal.totalPuntuacion"/></td>
                  <td/>
                </tr>
              </tbody>
            </table>
            <div class="form-group">
              <label for="idObservValoracionTotal"><s:text name="label.otros.comentarios" /></label>
              <s:textarea cssClass="form-control" id="idObservValoracionTotal" rows="5" name="valoracionSolicitudDTO.valoracionTotal.observaciones" />
            </div>
          </s:form>
          </div>
        </div>
      </div>
    </div>
  </div>
</s:form>
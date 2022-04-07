<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:url value="/modulos/valoracion/js/pertinenciaViabilidad.js"/>"></script>
<script type="text/javascript" src="<s:url value="/modulos/valoracion/js/comun.js"/>"></script>
<script type="text/javascript">
  window.tablaPertinencia = "#tablaListadoPertinencia";
  window.tablaViabilidad = "#tablaListadoViabilidad";
  window.urlActionListadoPertinencia = '<s:url action="cargaListadoPertinencia" namespace="/modulos/valoracion" includeParams="none"/>';
  window.urlActionListadoViabilidad = '<s:url action="cargaListadoViabilidad" namespace="/modulos/valoracion" includeParams="none"/>';
  window.urlActionGuardarValoracion = '<s:url action="guardarValoracion" namespace="/modulos/valoracion" includeParams="none"/>';

  initParametrizacionComun([
      '<s:text name="tabla.propiedades.comunes.col.0.title"/>',
      '<s:text name="tabla.propiedades.comunes.col.1.title"/>',
      '<s:text name="tabla.propiedades.comunes.col.2.title"/>',
      '<s:text name="tabla.propiedades.comunes.col.3.title"/>' ], [
      '<s:text name="tabla.propiedades.comunes.col.0.width"/>',
      '<s:text name="tabla.propiedades.comunes.col.1.width"/>',
      '<s:text name="tabla.propiedades.comunes.col.2.width"/>',
      '<s:text name="tabla.propiedades.comunes.col.3.width"/>' ],
      '<s:text name ="mensaje.mensajeSinRegistros"/>');
  $(document).ready(function() {
    initPestanaPertinenciaVis();
  });
</script>
<s:form id="formDatosPestania1" cssClass="form-vertical" theme="simple">
  <div id="divContainerPertinencia" class="container-fluid">
    <div class="card mt-5">
      <div class="card-header">
        <s:text name="tipoCriterio.pertinencia" />
      </div>
      <div class="card-body">
        <div class="row">
          <div class="col-sm-12">
            <table id="tablaListadoPertinencia" class="tableCriterios table table-striped table-sm">
              <thead>
                <tr>
                  <th scope="col" class="hide"/>
                  <th scope="col" class="col-9" title='<s:text name="tabla.propiedades.comunes.col.0.title"/>'/>
                  <th scope="col" class="text-center col-1" title='<s:text name="tabla.propiedades.comunes.col.1.title"/>'/>
                  <th scope="col" class="text-center col-1" title='<s:text name="tabla.propiedades.comunes.col.2.title"/>'/>
                  <th scope="col" class="text-center col-1" title='<s:text name="tabla.propiedades.comunes.col.3.title"/>'/>
                </tr>
              </thead>
              <tbody>
              </tbody>
              <tfoot>
                <tr>
                  <th scope="col" class="hide"/>
                  <th scope="col"/>
                  <th scope="col"><s:text name="label.total"/></th>
                  <th scope="col"/>
                  <th scope="col"/>
                </tr>
              </tfoot>
            </table>
            <div class="form-group mt-5">
              <label for="idObservPertinencia"><s:text name="label.valoracion.cualitativa" /></label>
              <s:textarea cssClass="form-control" id="idObservPertinencia" rows="5" name="valoracionSolicitudDTO.pertinencia.observaciones" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div id="divContainerViabilidad" class="container-fluid">
    <div class="card mt-5">
      <div class="card-header">
        <s:text name="tipoCriterio.viabilidad" />
      </div>
      <div class="card-body">
        <table id="tablaListadoViabilidad" class="tableCriterios table table-striped table-sm">
          <thead>
            <tr>
              <th scope="col" class="hide"/>
              <th scope="col" class="col-9" title='<s:text name="tabla.propiedades.comunes.col.0.title"/>'/>
              <th scope="col" class="text-center col-1" title='<s:text name="tabla.propiedades.comunes.col.1.title"/>'/>
              <th scope="col" class="text-center col-1" title='<s:text name="tabla.propiedades.comunes.col.2.title"/>'/>
              <th scope="col" class="text-center col-1" title='<s:text name="tabla.propiedades.comunes.col.3.title"/>'/>
            </tr>
          </thead>
          <tbody>
          </tbody>
          <tfoot>
            <tr>
              <th scope="col" class="hide"/>
              <th scope="col"/>
              <th scope="col"><s:text name="label.total"/></th>
              <th scope="col"/>
              <th scope="col"/>
            </tr>
          </tfoot>
        </table>
        <div class="form-group mt-5">
          <label for="idObservViabilidad"><s:text name="label.valoracion.cualitativa" /></label>
          <s:textarea cssClass="form-control" id="idObservViabilidad" rows="5" name="valoracionSolicitudDTO.viabilidad.observaciones" />
        </div>
      </div>
    </div>
  </div>
</s:form>
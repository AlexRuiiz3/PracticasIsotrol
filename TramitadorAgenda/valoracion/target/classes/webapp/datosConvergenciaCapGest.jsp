<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:url value="/modulos/valoracion/js/convergenciaCapGest.js"/>"></script>
<script type="text/javascript" src="<s:url value="/modulos/valoracion/js/comun.js"/>"></script>
<script type="text/javascript">
window.tablaConvergencia = "#tablaListadoConvergencia";
window.tablaCapGestion = "#tablaListadoCapGestion";
window.urlActionListadoConvergencia = '<s:url action="cargaListadoConvergencia" namespace="/modulos/valoracion" includeParams="none"/>';
window.urlActionListadoCapGestion = '<s:url action="cargaListadoCapacidadGestion" namespace="/modulos/valoracion" includeParams="none"/>';
    
initParametrizacionComun(
        [ 
          	'<s:text name="tabla.propiedades.comunes.col.0.title"/>', 
            '<s:text name="tabla.propiedades.comunes.col.1.title"/>', 
            '<s:text name="tabla.propiedades.comunes.col.2.title"/>', 
            '<s:text name="tabla.propiedades.comunes.col.3.title"/>'
        ],
        [
            '<s:text name="tabla.propiedades.comunes.col.0.width"/>',
            '<s:text name="tabla.propiedades.comunes.col.1.width"/>', 
            '<s:text name="tabla.propiedades.comunes.col.2.width"/>', 
            '<s:text name="tabla.propiedades.comunes.col.3.width"/>'
        ],
        '<s:text name ="mensaje.mensajeSinRegistros"/>'
    );
initParametrizacionConvergencia(
        [ 
            '<s:text name="tabla.propiedades.comunes.col.0.title"/>', 
            '<s:text name="tabla.propiedades.comunes.col.1.title05"/>', 
            '<s:text name="tabla.propiedades.comunes.col.2.title"/>', 
            '<s:text name="tabla.propiedades.comunes.col.3.title"/>'
        ],
        [
            '<s:text name="tabla.propiedades.comunes.col.0.width"/>',
            '<s:text name="tabla.propiedades.comunes.col.1.width"/>', 
            '<s:text name="tabla.propiedades.comunes.col.2.width"/>', 
            '<s:text name="tabla.propiedades.comunes.col.3.width"/>'
        ]
    );
initParametrizacionCapGestion(
        [
          '<s:text name="tabla.propiedades.comunes.col.0.title"/>',
          '<s:text name="tabla.propiedades.comunes.col.1.title04"/>',
          '<s:text name="tabla.propiedades.comunes.col.2.title"/>',
          '<s:text name="tabla.propiedades.comunes.col.3.title"/>'
        ],
        [
          '<s:text name="tabla.propiedades.comunes.col.0.width"/>',
          '<s:text name="tabla.propiedades.comunes.col.1.width"/>',
          '<s:text name="tabla.propiedades.comunes.col.2.width"/>',
          '<s:text name="tabla.propiedades.comunes.col.3.width"/>'
        ]
);

$(document).ready(function() {
  initPestanaConvergenciaCapGest();
});
</script>
<s:form id="formDatosPestania3" cssClass="form-vertical" theme="simple">
  <s:hidden name="valoracionSolicitudDTO.mostrarPestania3"/>
    <s:hidden id="esCoordinador" name="esCoordinador"/>
    <s:hidden id="esExterno" name="esExterno"/>
  <div id="divContainerDatosPestania3" class="container-fluid">
    <s:if test ='%{"C" ==valoracionSolicitudDTO.codigoFinalidad}'>
      <div class="card mt-5">
        <div class="card-header">
          <s:text name="tipoCriterio.convergencia" />
        </div>
        <div class="card-body">
          <table id="tablaListadoConvergencia" class="tableCriterios table table-striped table-sm">
            <thead>
              <tr>
                <th scope="col" class="hide"/>
                <th scope="col" class="col-9" title='<s:text name="tabla.propiedades.comunes.col.0.title"/>'><s:text name="tabla.propiedades.comunes.col.0.title"/></th>
                <th scope="col" class="text-center col-1" title='<s:text name="tabla.propiedades.comunes.col.1.title05"/>'><s:text name="tabla.propiedades.comunes.col.1.title05"/></th>
                <th scope="col" class="text-center col-1" title='<s:text name="tabla.propiedades.comunes.col.2.title"/>'><s:text name="tabla.propiedades.comunes.col.2.title"/></th>
                <th scope="col" class="text-center col-1" title='<s:text name="tabla.propiedades.comunes.col.3.title"/>'><s:text name="tabla.propiedades.comunes.col.3.title"/></th>
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
          <div class="form-group">
            <label for="idObservConvergencia"><s:text name="label.valoracion.cualitativa" /></label>
            <s:textarea cssClass="form-control" id="idObservConvergencia" rows="5" name="valoracionSolicitudDTO.convergencia.observaciones" />
          </div>
        </div>
      </div>
    </s:if>
    <div class="card mt-5">
      <div class="card-header">
        <s:text name="tipoCriterio.capgestion" />
      </div>
      <div class="card-body">
        <table id="tablaListadoCapGestion" class="tableCriterios table table-striped table-sm">
          <thead>
            <tr>
              <th scope="col" class="hide"/>
              <th scope="col" class="col-9" title='<s:text name="tabla.propiedades.comunes.col.0.title"/>'><s:text name="tabla.propiedades.comunes.col.0.title"/></th>
              <th scope="col" class="text-center col-1" title='<s:text name="tabla.propiedades.comunes.col.1.title"/>'><s:text name="tabla.propiedades.comunes.col.1.title"/></th>
              <th scope="col" class="text-center col-1" title='<s:text name="tabla.propiedades.comunes.col.2.title"/>'><s:text name="tabla.propiedades.comunes.col.2.title"/></th>
              <th scope="col" class="text-center col-1" title='<s:text name="tabla.propiedades.comunes.col.3.title"/>'><s:text name="tabla.propiedades.comunes.col.3.title"/></th>
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
        <div class="form-group">
          <label for="idObservCapGestion"><s:text name="label.valoracion.cualitativa" /></label>
          <s:textarea cssClass="form-control" id="idObservCapGestion" rows="5" name="valoracionSolicitudDTO.capacidadGestion.observaciones" />
        </div>
      </div>
    </div>
  </div>
  <div id="idNoPermiteEdicionPestania3" class="alert alert-danger struts2 mt-5">
    <em class="fas fa-times-circle"></em>
    <s:text name="msg.noPermite.edicion.pestania.3"/>
  </div>
</s:form>
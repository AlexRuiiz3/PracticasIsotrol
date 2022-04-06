<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:url value="/modulos/valoracion/js/coherenciaSostImp.js"/>"></script>
<script type="text/javascript" src="<s:url value="/modulos/valoracion/js/comun.js"/>"></script>
<script type="text/javascript">
  window.tablaCoherencia = "#tablaListadoCoherencia";
  window.tablaConectividad = "#tablaListadoConectividad";
  window.tablaSostenibilidad = "#tablaListadoSostenibilidad";
  window.tablaImpacto = "#tablaListadoImpacto";
  window.urlActionListadoCoherencia = '<s:url action="cargaListadoCoherencia" namespace="/modulos/valoracion" includeParams="none"/>';
  window.urlActionListadoConectividad = '<s:url action="cargaListadoConectividad" namespace="/modulos/valoracion" includeParams="none"/>';
  window.urlActionListadoSostenibilidad = '<s:url action="cargaListadoSostenibilidad" namespace="/modulos/valoracion" includeParams="none"/>';
  window.urlActionListadoImpacto = '<s:url action="cargaListadoImpacto" namespace="/modulos/valoracion" includeParams="none"/>';

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
    initPestanaCoherenciaSostImp();
  });

    
</script>
<s:form id="formDatosPestania2" cssClass="form-vertical" theme="simple">
  <div id="divContainerCoherencia" class="container-fluid">
    <div class="card mt-5">
      <div class="card-header">
        <s:text name="tipoCriterio.coherencia" />
      </div>
      <div class="card-body">
        <table id="tablaListadoCoherencia" class="tableCriterios table table-striped table-sm">
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
        <div class="form-group">
          <label for="idObservCoherencia"><s:text name="label.valoracion.cualitativa" /></label>
          <s:textarea cssClass="form-control" id="idObservCoherencia" rows="5" name="valoracionSolicitudDTO.coherencia.observaciones" />
        </div>
      </div>
    </div>
  </div>
  <s:if test ="'AH'==valoracionSolicitudDTO.codigoFinalidad">
    <div id="divContainerConectividad" class="container-fluid">
      <div class="card mt-5">
        <div class="card-header">
          <s:text name="tipoCriterio.conectividad" />
        </div>
        <div class="card-body">
          <table id="tablaListadoConectividad" class="tableCriterios table table-striped table-sm">
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
          <div class="form-group">
            <label for="idObservConectividad"><s:text name="label.valoracion.cualitativa" /></label>
            <s:textarea cssClass="form-control" id="idObservConectividad" rows="5" name="valoracionSolicitudDTO.conectividad.observaciones" />
          </div>
        </div>
      </div>
    </div>
  </s:if>
  <s:else>
    <div id="divContainerSostenibilidad" class="container-fluid">
      <div class="card mt-5">
        <div class="card-header">
          <s:text name="tipoCriterio.sostenibilidad" />
        </div>
        <div class="card-body">
          <table id="tablaListadoSostenibilidad" class="tableCriterios table table-striped table-sm">
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
          <div class="form-group">
            <label for="idObservSostenibilidad"><s:text name="label.valoracion.cualitativa" /></label>
            <s:textarea cssClass="form-control" id="idObservSostenibilidad" rows="5" name="valoracionSolicitudDTO.sostenibilidad.observaciones" />
          </div>
        </div>
      </div>
    </div>  
  </s:else>
  <div id="divContainerImpacto" class="container-fluid">
    <div class="card mt-5">
      <div class="card-header">
        <s:text name="tipoCriterio.impacto" />
      </div>
      <div class="card-body">
        <table id="tablaListadoImpacto" class="tableCriterios table table-striped table-sm">
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
          <label for="idObservImpacto"><s:text name="label.valoracion.cualitativa" /></label>
          <s:textarea cssClass="form-control" id="idObservImpacto" rows="5" name="valoracionSolicitudDTO.impacto.observaciones" />
        </div>
      </div>
    </div>
  </div>
</s:form>
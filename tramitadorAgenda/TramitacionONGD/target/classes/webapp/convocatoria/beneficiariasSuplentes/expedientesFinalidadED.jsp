<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
  window.finalidadED = "ED";
  window.tablaExpedientesFinalidadED = "#tablaExpedientesFinalidadED";
  
  $(document).ready(function() {
      crearDataTable(window.tablaExpedientesFinalidadED, columnsComunes, mensajeEmptyComun, window.finalidadED);
  });
</script>
<s:form id="formDatosPestania1" cssClass="form-vertical" theme="simple">
  <div id="divContainerFinalidadED" class="container-fluid">
    <div class="card mt-5">
      <div class="card-body">
        <div class="row">
          <div class="col-sm-12">
            <table id="tablaExpedientesFinalidadED" class="tableCriterios table table-striped table-sm">
              <thead>
                <tr>
                  <th scope="col" class="hide"/>
                  <th scope="col" class="centradoVertical" title='<s:text name="tabla.beneSuple.col.0.title"/>'/>
                  <th scope="col" class="centradoVertical" title='<s:text name="tabla.beneSuple.col.1.title"/>'/>
                  <th scope="col" class="centradoVertical" title='<s:text name="tabla.beneSuple.col.2.title"/>'/>
                  <th scope="col" class="centradoVertical" title='<s:text name="tabla.beneSuple.col.3.title"/>'/>
                  <th scope="col" class="centradoVertical" title='<s:text name="tabla.beneSuple.col.4.title"/>'/>
                </tr>
              </thead>
              <tbody>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</s:form>
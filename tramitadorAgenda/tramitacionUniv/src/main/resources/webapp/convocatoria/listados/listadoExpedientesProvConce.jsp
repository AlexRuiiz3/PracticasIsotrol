<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="es">
<head>
<title><s:text name="fieldset.listadoExpedientesProvConce" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Scripts -->
<s:include value="/modulos/tramitacionUniv/templates/head_scripts.jsp" />
<script type="text/javascript" src="<s:url value="/modulos/tramitacionUniv/listadoExpedientes/listados/js/listadoExpedientes.js"/>"></script>

<!-- CSS  -->
<s:include value="/modulos/tramitacionUniv/templates/head_styles.jsp" />

<script type="text/javascript">
  window.listadoExpedientes = "#tablaExpedientesProvConce";

  initParametrizacionListadoExpedientes(
      [ '<s:text name="tabla.listadoExpedientes.col.0.title"/>',
          '<s:text name="tabla.listadoExpedientes.col.1.title"/>',
          '<s:text name="tabla.listadoExpedientes.col.2.title"/>',
          '<s:text name="tabla.listadoExpedientes.col.3.title"/>',
          '<s:text name="tabla.listadoExpedientes.col.4.title"/>',
          '<s:text name="tabla.listadoExpedientes.col.5.title"/>',
          '<s:text name="tabla.listadoExpedientes.col.6.title"/>'
      ],
      [ '<s:text name="tabla.listadoExpedientes.col.0.width"/>',
          '<s:text name="tabla.listadoExpedientes.col.1.width"/>',
          '<s:text name="tabla.listadoExpedientes.col.2.width"/>',
          '<s:text name="tabla.listadoExpedientes.col.3.width"/>',
          '<s:text name="tabla.listadoExpedientes.col.4.width"/>',
          '<s:text name="tabla.listadoExpedientes.col.5.width"/>',
          '<s:text name="tabla.listadoExpedientes.col.6.width"/>'
      ],
      '<s:url action="mostrarTablaListadoExpedientesProvConce" namespace="/agenda/tareas" includeParams="none"/>',
      '<s:text name ="mensaje.mensajeSinHijosConvocatoria"/>');
  $(document).ready(function() {
    initTablaExpedientes();
  });
</script>

</head>
<body>
  <div class="imagenCabecera mb-5"></div>
  <div id="divContainerListadoExpediente" class="container-fluid">
    <div class="justify-content-center row">
      <div id="messagesAndErrors"
        class="align-self-center col-sm-12 d-none"></div>
    </div>
    <s:form id="formTablaConvocatorias" cssClass="form-vertical"
      theme="simple">

      <div class="card mb-5">
        <div class="card-header">
          <h5>
            <s:text name="fieldset.listadoExpedientesProvConce" />
          </h5>
        </div>
        <div class="card-body">
          <table id="tablaExpedientesProvConce" class="table table-bordered table-sm">
            <thead class="thead-dark">
              <tr>
                <th scope="col" class="centradoVertical text-center" title='<s:text name="tabla.listadoExpedientes.col.0.title"/>'/>
                <th scope="col" class="centradoVertical text-center" title='<s:text name="tabla.listadoExpedientes.col.1.title"/>'/>
                <th scope="col" class="centradoVertical text-center" title='<s:text name="tabla.listadoExpedientes.col.2.title"/>'/>
                <th scope="col" class="centradoVertical text-center" title='<s:text name="tabla.listadoExpedientes.col.3.title"/>'/>
                <th scope="col" class="centradoVertical text-center" title='<s:text name="tabla.listadoExpedientes.col.4.title"/>'/>
                <th scope="col" class="centradoVertical text-center" title='<s:text name="tabla.listadoExpedientes.col.5.title"/>'/>
                <th scope="col" class="centradoVertical text-center" title='<s:text name="tabla.listadoExpedientes.col.6.title"/>'/>
              </tr>
            </thead>
          </table>
        </div>
      </div>
    </s:form>
  </div>
  <div class="fondoOverlay d-none" />
</body>
</html>
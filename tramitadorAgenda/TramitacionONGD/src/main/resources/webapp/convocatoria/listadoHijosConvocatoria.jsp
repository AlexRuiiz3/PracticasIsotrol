<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- Scripts -->
<!DOCTYPE html>
<html lang="es">
<head>
<title><s:text name="title.convocatoria.modificacion" /></title>
<meta name="viewport"
  content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Scripts -->
<s:include value="/modulos/tramitacionONGD/templates/head_scripts.jsp" />
<script type="text/javascript"
  src="<s:url value="/modulos/tramitacionONGD/convocatoria/js/listadoSolicitudes.js"/>"></script>
<!-- CSS  -->
<s:include value="/modulos/tramitacionONGD/templates/head_styles.jsp" />
<script type="text/javascript">
    // Ids de los campos del formulario que contienen datepickers para formatear. /
    window.listadoSolicitudes = "#listadoSolicitudes";

    initParametrizacionHijosConvocatoria(
            [ '<s:text name="tabla.hijos.convocatoria.col.0.title"/>',
                    '<s:text name="tabla.hijos.convocatoria.col.1.title"/>',
                    '<s:text name="tabla.hijos.convocatoria.col.2.title"/>',
                    '<s:text name="tabla.hijos.convocatoria.col.3.title"/>',
                    '<s:text name="tabla.hijos.convocatoria.col.4.title"/>',
                    '<s:text name="tabla.hijos.convocatoria.col.5.title"/>',
                    '<s:text name="tabla.hijos.convocatoria.col.6.title"/>',
                    '<s:text name="tabla.hijos.convocatoria.col.7.title"/>',
                    '<s:text name="tabla.hijos.convocatoria.col.8.title"/>'

            ],
            [ '<s:text name="tabla.hijos.convocatoria.col.0.width"/>',
                    '<s:text name="tabla.hijos.convocatoria.col.1.width"/>',
                    '<s:text name="tabla.hijos.convocatoria.col.2.width"/>',
                    '<s:text name="tabla.hijos.convocatoria.col.3.width"/>',
                    '<s:text name="tabla.hijos.convocatoria.col.4.width"/>',
                    '<s:text name="tabla.hijos.convocatoria.col.5.width"/>',
                    '<s:text name="tabla.hijos.convocatoria.col.6.width"/>',
                    '<s:text name="tabla.hijos.convocatoria.col.7.width"/>',
                    '<s:text name="tabla.hijos.convocatoria.col.8.width"/>'

            ],
            '<s:url action="mostrarTablaExpedientesHijosConvocatoria" namespace="/agenda/tareas" includeParams="none"/>',
            '<s:text name ="mensaje.mensajeSinHijosConvocatoria"/>');
    $(document).ready(function() {
        initPestanaHijosConvocatoria();
    });
</script>
<s:head theme="ajax" />
</head>
<body>
  <div class="imagenCabecera mb-5"></div>
  <div id="divContainerModifConvocatoria" class="container-fluid">
    <div class="justify-content-center row">
      <div id="messagesAndErrors" class="align-self-center col-sm-12 d-none"></div>
    </div>
    <s:form id="formTablaConvocatorias" cssClass="form-vertical" theme="simple">
      <!--         Contraparte -->
      <div class="card mb-5">
        <div class="card-header">
          <h5>
            <s:text name="fieldset.listadoHijosConvocatoria" />
          </h5>
        </div>
        <div class="card-body">
          <table id="listadoSolicitudes" class="table table-bordered table-sm">
            <thead class="thead-dark">
              <tr>
                <th
                  title='<s:text name="tabla.hijos.convocatoria.col.0.title"/>'><s:text
                    name="tabla.hijos.convocatoria.col.0.title" /></th>
                <th
                  title='<s:text name="tabla.hijos.convocatoria.col.1.title"/>'>
                  <s:text name="tabla.hijos.convocatoria.col.1.title" />
                </th>
                <th
                  title='<s:text name="tabla.hijos.convocatoria.col.2.title"/>'><s:text
                    name="tabla.hijos.convocatoria.col.2.title" /></th>
                <th
                  title='<s:text name="tabla.hijos.convocatoria.col.3.title"/>'><s:text
                    name="tabla.hijos.convocatoria.col.3.title" /></th>
                <th
                  title='<s:text name="tabla.hijos.convocatoria.col.4.title"/>'><s:text
                    name="tabla.hijos.convocatoria.col.4.title" /></th>
                <th
                  title='<s:text name="tabla.hijos.convocatoria.col.5.title"/>'><s:text
                    name="tabla.hijos.convocatoria.col.5.title" /></th>
                <th
                  title='<s:text name="tabla.hijos.convocatoria.col.6.title"/>'><s:text
                    name="tabla.hijos.convocatoria.col.6.title" /></th>
                <th
                  title='<s:text name="tabla.hijos.convocatoria.col.7.title"/>'><s:text
                    name="tabla.hijos.convocatoria.col.7.title" /></th>
                <th
                  title='<s:text name="tabla.hijos.convocatoria.col.8.title"/>'><s:text
                    name="tabla.hijos.convocatoria.col.8.title" /></th>
              </tr>
            </thead>
            <tbody>
            </tbody>
          </table>
        </div>
      </div>
    </s:form>
  </div>
  <div class="fondoOverlay d-none" />
</body>
</html>
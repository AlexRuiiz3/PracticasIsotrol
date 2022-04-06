<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
    window.tablaExpComuInicio = "#listaExpComuInicio";
    window.urlTablaExpComuInicio = '<s:url action="cargaListadoExpComuInicio" namespace="/modulos/otrasOpciones" includeParams="none"/>';
    window.mensajeEmpty = '<s:text name ="mensaje.mensajeSinDatos"/>';

    window.definicionTablaExpComuInicio=
            [ '<s:text name="tabla.comuInicio.col.0.title"/>',
              '<s:text name="tabla.comuInicio.col.1.title"/>',
              '<s:text name="tabla.comuInicio.col.2.title"/>',
              '<s:text name="tabla.comuInicio.col.3.title"/>',
              '<s:text name="tabla.comuInicio.col.4.title"/>' ],
            [ '<s:text name="tabla.comuInicio.col.0.width"/>',
              '<s:text name="tabla.comuInicio.col.1.width"/>',
              '<s:text name="tabla.comuInicio.col.2.width"/>',
              '<s:text name="tabla.comuInicio.col.3.width"/>',
              '<s:text name="tabla.comuInicio.col.4.width"/>' ];
</script>
<div id="divContainerComunicacionInicio" class="container-fluid">
  <div class="card mt-5">
    <div class="card-header">
      <s:text name="fieldset.comunicacionInicio" />
    </div>
    <div class="card-body">
      <table id="listaExpComuInicio" class="table table-bordered table-sm">
        <thead class="thead-dark">
          <tr>
            <th title='<s:text name="tabla.comuInicio.col.0.title"/>'><s:text name="tabla.comuInicio.col.0.title" /></th>
            <th title='<s:text name="tabla.comuInicio.col.1.title"/>'><s:text name="tabla.comuInicio.col.1.title" /></th>
            <th title='<s:text name="tabla.comuInicio.col.2.title"/>'><s:text name="tabla.comuInicio.col.2.title" /></th>
            <th title='<s:text name="tabla.comuInicio.col.3.title"/>'><s:text name="tabla.comuInicio.col.3.title" /></th>
            <th title='<s:text name="tabla.comuInicio.col.4.title"/>'><s:text name="tabla.comuInicio.col.4.title" /></th>
          </tr>
        </thead>
        <tbody>
        </tbody>
      </table>
    </div>
  </div>
</div>

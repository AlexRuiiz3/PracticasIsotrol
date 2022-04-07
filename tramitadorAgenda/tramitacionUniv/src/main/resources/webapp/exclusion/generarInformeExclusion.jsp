<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<!-- CSS  -->
<s:include value="/modulos/tramitacionUniv/templates/head_styles.jsp" />
<title><s:text name="fieldset.generarInforme.exclusion" /></title>
</head>
<body>
  <div class="imagenCabecera mb-5"></div>
  <div class="container-fluid">
    <div class="card mb-5 mt-5">
      <div class="card-header">
        <h5>
          <s:text name="fieldset.generarInforme.exclusion" />
        </h5>
      </div>
      <div class="card-body"></div>
      <%@include file="../templates/error_messages.jsp"%>
    </div>
  </div>
  <div class="float-right mt-4 mb-4 mr-3">
    <input class="btn btn-danger btn-space" type="button" id="btnCerrar"
      value="<s:text name="CERRAR" />"
      onclick="javascript:opener.recargaPadre([['capa_doc_asoc', '../modulos/docsAsociadosExpediente/listarDocumentos.action'],['capa_tareas_asoc','../modulos/tareasAsociadasExpediente/listarTareasExpediente.action']]);self.close();" />
  </div>
</body>
</html>

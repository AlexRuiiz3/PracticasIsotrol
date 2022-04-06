<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
window.tablaHistorico = "#tablaListadoHistorico";

initParametrizacionHistorico(
        [
            '<s:text name="tabla.historico.col.0.title"/>',  
            '<s:text name="tabla.historico.col.1.title"/>', 
            '<s:text name="tabla.historico.col.2.title"/>'
        ],
        [
            '<s:text name="tabla.historico.col.0.width"/>',
            '<s:text name="tabla.historico.col.1.width"/>', 
            '<s:text name="tabla.historico.col.2.width"/>'
        ],  
            '<s:url action="cargaListadoHistorico" namespace="/modulos/otrasOpciones" includeParams="none"/>',
            '<s:text name ="mensaje.mensajeSinHistorico"/>'
    );

$(document).ready(function() {
	initPestanaHistorico();
});
</script>
	<div id="divContainerHistorico" class="container-fluid">
		<div class="card">
			<div class="card-header">
				<s:text name="fieldset.historico" />
			</div>
			<div class="card-body">
                  <table id="tablaListadoHistorico" class="table table-bordered table-sm">
              			<thead class="thead-dark">
                			<tr>
                 				<th title='<s:text name="tabla.historico.col.0.title"/>'><s:text name="tabla.historico.col.0.title" /></th>
                  				<th title='<s:text name="tabla.historico.col.1.title"/>'><s:text name="tabla.historico.col.1.title" /></th>
                  				<th title='<s:text name="tabla.historico.col.2.title"/>'><s:text name="tabla.historico.col.2.title" /></th>
                			</tr>
              			</thead>
              			<tbody>
              			</tbody>
            	  </table>
		    </div>
		</div>
	</div>

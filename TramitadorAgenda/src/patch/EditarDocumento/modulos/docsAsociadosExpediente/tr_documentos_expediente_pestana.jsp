<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/displaytag.tld" prefix="display"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="es.juntadeandalucia.plataforma.web.UsuarioWeb"%>
<%@ page
	import="es.juntadeandalucia.plataforma.actions.modulos.documentosAsociados.DocumentosAsociadosExpedienteAction"%>
<%@ page
	import="es.juntadeandalucia.plataforma.actions.escritorio.EscritorioPestanaAction"%>

<head>
<script type="text/javascript" src="../modulos/comun/js/bloqueoPantalla.js"></script>
<script language="Javascript" type="text/javascript">	
	 var infoPublicaProc = $("#infoPublicaProc").val();
	 if(infoPublicaProc == 'S'){
		 $(document).ready(function() {
				mostrarEspera = false;
				ocultarDivEspera();
		        $('#documentos').dataTable( {
	  	        	 "oLanguage": {
	  			          "sLengthMenu": 'Mostrar <select>'+
	  			            '<option value="2">2</option>'+
	  			          	'<option selected="selected" value="4">4</option>'+
	  			            '<option value="6">6</option>'+
	  			            '</select> resultados '
	  			        },
	  			        "iDisplayLength": 4,
	  			        "aoColumns": [
	    			                    { "sType": "date-esp" },
	    			  	                null,
	    			  	            	null,
	  	  			  	            null,
	  	  			  	        	null,
	  	  			  	        	null,
	  	  			  	        	null
	  	  	            ]
	  	       		}	
		        		
		        );
		    } );
	 }else {
		 $(document).ready(function() {
				mostrarEspera = false;
				ocultarDivEspera();
		        $('#documentos').dataTable( {
	  	        	 "oLanguage": {
	  			          "sLengthMenu": 'Mostrar <select>'+
	  			            '<option value="2">2</option>'+
	  			          	'<option selected="selected" value="4">4</option>'+
	  			            '<option value="6">6</option>'+
	  			            '</select> resultados '
	  			        },
	  			        "iDisplayLength": 4,
	  			        "aoColumns": [
	    			                    { "sType": "date-esp" },
	    			  	                null,
	    			  	            	null,
	  	  			  	            null,
	  	  			  	        	null,
	  	  			  	        	null
	  	  	            ]
	  	       		}	
		        		
		        );
		    } );
	}
	 

	 // Se ejecuta el script enviando la referencia del documento a modificar, si va a ser de informacion publica 
	 // y le pasamos la variable que viene del escritorio de tramitacion, la cual tiene el valor S o N si el procedimiento tiene alguna fase con info publica.
	 // Mientras se ejecuta el codigo del action, hasta que no termine su ejecucion ponemos un proceso de espera.
	 function cambiaValorInformacionPublica(refDoc, infoPublica){
		 var infoPublicaProc = $("#infoPublicaProc").val();
		 var parametros = "refdoc=" + refDoc + "&infoPublica=" + infoPublica + "&infoPublicaProc="+ infoPublicaProc;
		 mostrarBloqueo();
		 $.ajax({  
				type: "POST",  
				url: "../modulos/docsAsociadosExpediente/cambiaValorInformacionPublica.action",
				data: parametros,
				success: function () {
					$.ajax({  
						type: "GET",  
						url: "../modulos/docsAsociadosExpediente/listarDocumentos.action?infoPublicaProc="+infoPublicaProc,
						success: function (data) {
						$("#contenidoPestana").html(data);
							mostrarEspera = false;
							ocultarDivEspera();
						}
					});
					mostrarEspera = false;
					ocultarDivEspera();
				}
				
			});
	 }
	 
	 function accesoLibreoffice(refDocExp, token, tokenTTL, usuario, direccionLib, direccionWOPI, modoVentana, titulo){
		var parametros = "refdoc=" + refDocExp + "&token=" + token + "&usuario=" + usuario;
		$.ajax({  
			type: "POST",  
			url: "../modulos/docsAsociadosExpediente/accesoLibreoffice.action",
			data: parametros,
			success: function () {
				var date = new Date();
				var time = date.getTime();
				var accessTokenTTL = Number(tokenTTL);
	            document.getElementById("libreofficeURL").value = direccionLib;
	            document.getElementById("WOPISrc").value = direccionWOPI;
	            document.getElementById("access_token").value = token;
	            document.getElementById("access_token_ttl").value = time + accessTokenTTL;
	            if(modoVentana == 'true'){
					var features = ' width=' + window.outerWidth + ', height=' + window.outerHeight + ',location=0, resizable, scrollbars, toolbar=0, menubar=0'; 
					document.getElementById("office_form").target = "myActionWin";
					window.open("", "myActionWin", features);
				}
	            document.getElementById("office_form").submit();
			}
		});
	 }

	 function cargarListadoAvisos(action) {
			
			$.ajax({  
				type: "POST",  
				url: action,
				
				success: function(data){
					document.getElementById('mensajeEspera').style.display = 'none';
					$('#contenido-modal').html(data);
				}  
			});
		}
	 
</script>

</head>


<body>

	<div>
		<form id="office_form" name="office_form" target="office_frame"
			action="../modulos/docsAsociadosExpediente/ventana_edicion.jsp" method="GET">
			<input id="libreofficeURL" name="libreofficeURL" value="" type="hidden"> 
			<input id="WOPISrc" name="WOPISrc" value="" type="hidden"> 
			<input id="access_token" name="access_token" value="" type="hidden"> 
			<input id="access_token_ttl" name="access_token_ttl" value="" type="hidden"> 
		</form>

		<form id="tr_documentos_expediente" method="post" action=""
			onmouseup="javascript:if (document.getElementById('seleccionarPorFase').checked) {
							 replaceLinks('1');
						  } else {
						     replaceLinks('0');
						  }
						  return false;">

			<s:div id="NOcentrado">
				<s:if test="%{fase == 0}">
					<input type="checkbox" name="seleccionarPorFase"
						class="clase_input_checkbox" id="seleccionarPorFase"
						value="Fase_actual"
						style="cursor: pointer; width: 1.01em; margin: 0; padding: 0; top: 2px; position: relative;"
						onclick="if (document.getElementById('seleccionarPorFase').checked) {
							   				ejecutarFiltroFase('1');return false;
							   			 } else {
							   			 	ejecutarFiltroFase('0'); return false;
							   			 }">
			Ver s&oacute;lo documentos de la fase actual
		</s:if>
				<s:if test="%{fase == 1}">
					<input type="checkbox" name="seleccionarPorFase"
						class="clase_input_checkbox" id="seleccionarPorFase"
						value="Fase_actual" checked="checked"
						style="cursor: pointer; width: 1.01em; margin: 0; padding: 0; top: 2px; position: relative;"
						onclick="if (document.getElementById('seleccionarPorFase').checked) {
							   				ejecutarFiltroFase('1');return false;
							   			 } else {
							   			 	ejecutarFiltroFase('0'); return false;
							   			 }">
			Ver s&oacute;lo documentos de la fase actual
		</s:if>
			</s:div>

			<s:if test="%{noTerminado != null}">
				<div class="warning-new"
					style="font-size: 0.75em; text-align: left;">
					<s:text
						name="Es necesario sustituir las variables antes de finalizar el documento." />
				</div>
				<br />
			</s:if>
			<s:if test="listaDocumentos!=null&&listaDocumentos.size()!=0">


				<display:table name="listaDocumentos" requestURI=""
					class="tabla-borde" id="documentos" export="false"
					excludedParams="*">

					<display:column property="fecha" title="Fecha"
						class="contenido_tabla_portlet"
						headerClass="cabecera_tabla_portlet underline" maxLength="18" />

					<display:column property="documento.usuario" title="Usuario"
						class="contenido_tabla_portlet"
						headerClass="cabecera_tabla_portlet" />

					<display:column title="Tipo del documento (Nombre)"
						class="contenido_tabla_portlet"
						headerClass="cabecera_tabla_portlet underline" maxLength="50" style="width:20%">
						${documentos.documento.tipoDocumento.descripcion} (${documentos.documento.nombreFichero})
					</display:column>

					<c:if test="${documentos.estadoDocumento == 'DESCARTADO'}">
						<display:column title="Estado" class="contenido_tabla_portlet"
							headerClass="cabecera_tabla_portlet">
					DESCARTADO <a title="Ver motivo de rechazo"
								href="javascript:motivoPortafirmas(<c:out value='${documentos.documento.refDocumento}'/>);"
								onclick="javascript:ejecutarEventoCierre=false;"> ?</a>
						</display:column>
					</c:if>

					<c:if test="${documentos.estadoDocumento != 'DESCARTADO'}">
						<display:column title="Estado" class="contenido_tabla_portlet"
							headerClass="cabecera_tabla_portlet">
					${documentos.documento.nombreEstado}
					</display:column>
					</c:if>

					<display:column title="Registro" class="contenido_tabla_portlet"
						headerClass="cabecera_tabla_portlet" sortable="false">
						<c:if
							test="${!(documentos.documento.registroDocumento.numeroRegistroEntrada eq null) && !(documentos.documento.registroDocumento.numeroRegistroSalida eq null)}">
						(E)${documentos.documento.registroDocumento.numeroRegistroEntrada}<br>
						(S)${documentos.documento.registroDocumento.numeroRegistroSalida}  
					</c:if>
						<c:if
							test="${documentos.documento.registroDocumento.numeroRegistroEntrada eq null && !(documentos.documento.registroDocumento.numeroRegistroSalida eq null)}">
						(S)${documentos.documento.registroDocumento.numeroRegistroSalida} 
					</c:if>
						<c:if
							test="${!(documentos.documento.registroDocumento.numeroRegistroEntrada eq null) && documentos.documento.registroDocumento.numeroRegistroSalida eq null}">
						(E)${documentos.documento.registroDocumento.numeroRegistroEntrada}
					</c:if>

					</display:column>
					
					<!-- Si la variable definida en el action nos dice que hay al menos una fase con informacion publica en el procedimiento
					mostrará la columna en documentos asociados de informacion publica.
					Por otra parte, por cada documento asociado se mirará el estado del documento donde solo se podra modificar la informacion
					publica en el caso de que el estado sea FIRMADO. 
					Si el documento esta marcado como informacion publica, cada vez que se marque el check se mandará el valor exactamente opuesto,
					para realizar el cambio en base de datos. -->
					<c:if test="${infoPublicaProc == 'S'}">
						<display:column title="Info. Pública" class="contenido_tabla_portlet"
								headerClass="cabecera_tabla_portlet" style="text-align:center">
							<c:if test="${documentos.estadoDocumento == 'FIRMADO'}"> 
								<c:if test="${documentos.documento.informacionPublica eq 'S'}">
										<s:form theme="simple">
									    <input type="checkbox" name="cambiarInfoPublica"
										class="clase_input_checkbox" id="cambiarInfoPublica" value="${documentos.documento.refDocumento}" checked onchange="cambiaValorInformacionPublica(this.value,'N')">
								    </s:form>
								</c:if>
								<c:if test="${documentos.documento.informacionPublica eq 'N'}">
									<s:form theme="simple">
									    <input type="checkbox" name="cambiarInfoPublica"
										class="clase_input_checkbox" id="cambiarInfoPublica" value="${documentos.documento.refDocumento}" onchange="cambiaValorInformacionPublica(this.value,'S')">
								    </s:form> 
								</c:if>
							</c:if>
							<c:if test="${documentos.estadoDocumento != 'FIRMADO'}"> 
									    <input type="checkbox" name="cambiarInfoPublica"
										class="clase_input_checkbox" id="cambiarInfoPublica" value="${documentos.documento.refDocumento}" disabled>
							</c:if>
						</display:column>
					</c:if>
					
					<display:column title="Acciones" class="contenido_tabla_portlet"
						headerClass="cabecera_tabla_portlet" sortable="false">

						<!-- Descargar Documento -->

						<!-- Descargar Documento de tipo Generado PDF -->
						<c:if
							test="${ documentos.documento.modoGen == 'P' 
							&& documentos.documento.tipoDocumento.incGen == 'G' && (documentos.documento.formato == null || documentos.documento.formato == '')
							&& ( documentos.estadoDocumento != 'FIRMADO' || documentos.documento.tipoDocumento.registrable != 'true' || (documentos.documento.registroDocumento.registroDocumento.NUMREGSALIDADOC eq null) )}">
							<a
								href="javascript:
							abrirDocumento('../trewa/DescargaDoc?docExp=${documentos.documento.refDocumento}&nombDocExp=${documentos.documento.tipoDocumento.etiqueta}&multiple=${documentos.documento.tipoDocumento.multiple}&modoDescarga=inline&pieFirma=S');"
								onclick="javascript:ejecutarEventoCierre=false;"> <img
								title="Descargar" alt="Descargar"
								src="../instalaciones/imagenes/escritorio/portlets/descargar.png" /></a>
						</c:if>

						<!-- Descargar Documento de tipo Generado PDF -->
						<c:if
							test="${ documentos.documento.modoGen == 'P' 
							&& documentos.documento.tipoDocumento.incGen == 'G' && (documentos.documento.formato != null && documentos.documento.formato != '') 
							&& ( documentos.estadoDocumento != 'FIRMADO' && documentos.documento.tipoDocumento.registrable != 'true' && (documentos.documento.registroDocumento.registroDocumento.NUMREGSALIDADOC eq null))}">
							<a
								href="javascript:abrirDocumento('../es.juntadeandalucia.plataforma.gwt.RenderizarEscritorio/trewa/DescargaDoc?docExp=${documentos.documento.refDocumento}');"
								onclick="javascript:ejecutarEventoCierre=false;"> <img
								title="Descargar" alt="Descargar"
								src="../instalaciones/imagenes/escritorio/portlets/descargar.png" /></a>
						</c:if>

						<!-- Descargar Documento de tipo Generado PDF -->
						<c:if
							test="${ documentos.documento.modoGen == 'P' 
							&& documentos.documento.tipoDocumento.incGen == 'G' && (documentos.documento.formato != null && documentos.documento.formato != '') 
							&& ( documentos.estadoDocumento == 'FIRMADO' && documentos.documento.tipoDocumento.registrable != 'true' && (documentos.documento.registroDocumento.registroDocumento.NUMREGENTRADADOC eq null))}">
							<a href="javascript:abrirDocumento('${documentos.refGestor}');"
								onclick="javascript:ejecutarEventoCierre=false;"> <img
								title="Descargar" alt="Descargar"
								src="../instalaciones/imagenes/escritorio/portlets/descargar.png" /></a>
						</c:if>

						<!-- Descargar Documento de tipo Generado PDF -->
						<c:if
							test="${ documentos.documento.modoGen == 'P' 
							&& documentos.documento.tipoDocumento.incGen == 'G' && (documentos.documento.formato != null && documentos.documento.formato != '') 
							&& ( documentos.estadoDocumento == 'FIRMADO' && documentos.documento.tipoDocumento.registrable != 'true' && (documentos.documento.registroDocumento.registroDocumento.NUMREGENTRADADOC != null))}">
							<a
								href="javascript:abrirVentanaPopUp('../modulos/docsAsociadosExpediente/verDocumentoFirmadoYRegistradoSiboja.action?refdoc=<c:out value='${documentos.documento.refDocumento}'/>');"
								onclick="javascript:ejecutarEventoCierre=false;"> <img
								title="Descargar" alt="Descargar"
								src="../instalaciones/imagenes/escritorio/portlets/descargar.png" /></a>
						</c:if>


						<!-- Código nuevo -->

						<!-- Descargar -->
						<c:if test="${ documentos.documento.modoGen != 'P' }">
							<a
								href="javascript:abrirVentanaPopUp('../modulos/docsAsociadosExpediente/descargarDocumento.action?refdoc=<c:out value='${documentos.documento.refDocumento}'/>');"
								onclick="javascript:ejecutarEventoCierre=false;"> <img
								title="Descargar" alt="Descargar"
								src="../instalaciones/imagenes/escritorio/portlets/descargar.png" /></a>
						</c:if>

						<!-- Descargar justificante de firma -->
						<c:if
							test="${documentos.estadoDocumento == 'FIRMADO' && documentos.esDescargadoPortaFirmas == 'true' && (documentos.documento.registroDocumento.registroDocumento.NUMREGSALIDADOC eq null) && (documentos.documento.codigoHash !=null && documentos.documento.codigoHash !='')}">
							<a
								href="javascript:abrirVentanaPopUp('../modulos/docsAsociadosExpediente/verDocumentoFirmado.action?refdoc=<c:out value='${documentos.documento.refDocumento}'/>', 'Firma');"
								onclick="javascript:ejecutarEventoCierre=false;"> <img
								src="../instalaciones/imagenes/escritorio/portlets/verfirma.png"
								border="0" alt="Descargar justificante de firma"
								title="Descargar justificante de firma" /></a>
						</c:if>

						<!-- Descargar informe de registro -->
						<c:if
							test="${documentos.estadoDocumento == 'FIRMADO' && documentos.esDescargadoPortaFirmas == 'true' && (!(documentos.documento.registroDocumento.registroDocumento.NUMREGSALIDADOC eq null))}">
							<a
								href="javascript:abrirVentanaPopUp('../modulos/docsAsociadosExpediente/verDocumentoFirmadoYRegistrado.action?refdoc=<c:out value='${documentos.documento.refDocumento}'/>', 'FirmaYRegistro');"
								onclick="javascript:ejecutarEventoCierre=false;"> <img
								src="../instalaciones/imagenes/escritorio/portlets/verregistro.png"
								border="0" alt="Descargar informe de registro"
								title="Descargar informe de registro" /></a>
						</c:if>
						
						<!-- Descargar informe de registro (Documentos aportados por interesado) -->
						<c:if
							test="${documentos.estadoDocumento == 'FIRMADO' && documentos.esFirmadoInteresado == 'true' && (!(documentos.documento.registroDocumento.registroDocumento.NUMREGENTRADADOC eq null))}">
							<a
								href="javascript:abrirVentanaPopUp('../modulos/docsAsociadosExpediente/verDocumentoFirmadoYRegistradoInteresado.action?refdoc=<c:out value='${documentos.documento.refDocumento}'/>', 'FirmaYRegistro');"
								onclick="javascript:ejecutarEventoCierre=false;"> <img
								src="../instalaciones/imagenes/escritorio/portlets/verregistro.png"
								border="0" alt="Descargar informe de registro"
								title="Descargar informe de registro" /></a>
						</c:if>

						<c:if
							test="${documentos.documento.tipoDocumento.incGen == 'I' && documentos.estadoDocumento == 'TERMINADO' && (documentos.documento.registroDocumento.registroDocumento.NUMREGSALIDADOC != null || documentos.documento.registroDocumento.registroDocumento.NUMREGENTRADADOC != null)}">
							<a
								href="javascript:abrirVentanaPopUp('../modulos/docsAsociadosExpediente/verDocumentoFirmadoYRegistradoSiboja.action?refdoc=<c:out value='${documentos.documento.refDocumento}'/>', 'FirmaYRegistroSiboja');"
								onclick="javascript:ejecutarEventoCierre=false;"> <img
								src="../instalaciones/imagenes/escritorio/portlets/verregistro.png"
								border="0" alt="Descargar informe de registro"
								title="Descargar informe de registro" /></a>
						</c:if>

						<!-- Descargar diligencia de compulsa -->
						<c:if
							test="${documentos.documento.tipoDocumento.incGen == 'I' && (documentos.documento.formato != null && documentos.documento.formato != '') && documentos.esFirmadoInteresado == 'false' && documentos.estadoDocumento == 'FIRMADO' && (documentos.documento.registroDocumento.registroDocumento.NUMREGSALIDADOC eq null) && (documentos.documento.codigoHash == null || documentos.documento.codigoHash == '')}">
							<a
								href="javascript:abrirVentanaPopUp('../modulos/docsAsociadosExpediente/verDocumentoIncorporadoYCompulsado.action?refdoc=<c:out value='${documentos.documento.refDocumento}'/>', 'Compulsa');"
								onclick="javascript:ejecutarEventoCierre=false;"> <img
								src="../instalaciones/imagenes/escritorio/portlets/vercompulsa.png"
								border="0" alt="Descargar diligencia de compulsa"
								title="Descargar diligencia de compulsa" /></a>
						</c:if>

						<!-- Código nuevo hasta aquí -->

						<!-- Observaciones -->

						<a
							href="javascript:abrirVentanaPopUp('../modulos/docsAsociadosExpediente/mostrarObservaciones.action?refdoc=<c:out value='${documentos.documento.refDocumento}'/>','Observaciones');"
							onclick="javascript:ejecutarEventoCierre=false;"> <img
							alt="Observaciones" title="Observaciones"
							src="../instalaciones/imagenes/escritorio/portlets/observaciones.png" /></a>

						<s:if
							test="%{ listaDocumentos != null && listaDocumentos.size() > 0}">



							<c:if
								test="${ documentos.documento.claseDoc == 'G' &&
							documentos.documento.faseActual.refFaseActual == documentos.faseActual.refFaseActual &&
							documentos.estadoDocumento != 'FIRMADO' && documentos.estadoDocumento != 'PENDIENTE_FIRMA' &&
						 (documentos.documento.registroDocumento.registroDocumento.NUMREGSALIDADOC eq null) &&
						(documentos.estadoDocumento == 'REALIZACION' ||
						documentos.documento.tipoDocumento.etiqueta == tipoDocNoDef ||
						documentos.extension != 'application/vnd.oasis.opendocument.text')&& (documentos.bloqueado eq 'N')
						
						 }">

								<!--      Eliminar Documento    -->
								<a
									href="javascript:eliminarDocumento('../modulos/otrasOpciones/eliminarDocumento.action',
						'refdoc=<c:out value='${documentos.documento.refDocumento}'/>')"
									onclick="ejecutarEventoCierre=false;if(!confirm('Se borrará el documento asociado al expediente'))return false">
									<img
									src="../instalaciones/imagenes/escritorio/portlets/generar_plantilla_borrar.png"
									border="0" alt="Eliminar" title="Eliminar" />
								</a>

							</c:if>
						</s:if>

						<!-- Edita el documento -->
						<%	
							String url = request.getParameter("url");
							if (url == null || url.equals("")){
								url = new String(request.getRequestURL());
							}
							url = url.substring(0,url.lastIndexOf("modulos"));
							
							String parametrosJVM;
							
							String codificacion = System.getProperty("file.encoding");
							String userCountry = System.getProperty("user.country");
							String userLanguage = System.getProperty("user.language");
							
							parametrosJVM = "-Dfile.encoding=" + codificacion + " -Duser.country=" + userCountry + " -Duser.language=" + userLanguage;
							
							//Detectar versión navegador
							String esIE8 = "no";
							
							String ua = request.getHeader( "User-Agent" );
							String version = new String("");
							
							if(ua != null ){
								if((ua.indexOf("MSIE") != -1)) {
									String tempStr = ua.substring(ua.indexOf("MSIE"),ua.length());
									version = tempStr.substring(4,tempStr.indexOf(";"));
									
									if ( version.equals(" 8.0") || version.equals(" 9.0") || version.equals(" 10.0") ) {
										esIE8 = "si";
									}
								}							
							}							
						%>
						
						<!-- Documento de WebOffice -->
						<c:if
							test="${documentos.documento.modoGen == 'O' 
							&& documentos.documento.tipoDocumento.incGen == 'G' 
							&& documentos.estadoDocumento == 'REALIZACION' && (documentos.bloqueado eq 'N')}">

							<c:set var="esIE8" value="<%=esIE8%>" />
							<c:set var="tokenGen" value="${token}" />
							<c:set var="user" value="${nombreUsuario}" />

							<a id="accLib" href="#"
								onclick="javascript:accesoLibreoffice('${documentos.documento.refDocumento}', '${tokenGen}', '${token_ttl}', '${user}', '${urlLibreOffice}', '${urlWOPI}${documentos.documento.instanciaEnMotorTramitacion.NUMDOC}', '${modoVentana}', 'wofi')">
								<img
								src="../instalaciones/imagenes/escritorio/portlets/modificar.png"
								border="0" alt="Editar" title="Editar" />
							</a>

							<div id="divWebOffice"></div>
						</c:if>

						<!-- Conversion a pdf -->
						<c:if
							test="${documentos.documento.modoGen == 'O' 
							&& documentos.documento.tipoDocumento.incGen == 'G' 
							&& documentos.estadoDocumento == 'TERMINADO' && (documentos.bloqueado eq 'N') }">
							<c:if test="${documentos.extension != 'application/pdf' }">

								<a
									href="javascript:convertirPDF('../modulos/docsAsociadosExpediente/convertirPDF.action','refdoc=<c:out value='${documentos.documento.refDocumento}'/>')"
									onclick="javascript:ejecutarEventoCierre=false;"> <img
									src="../instalaciones/imagenes/escritorio/portlets/ic_pdf.png"
									border="0" alt="Editar" title="Convertir a formato PDF" /></a>

							</c:if>

						</c:if>
						<!-- Documento de tipo Generado Java PDF (Editor por párrafos) -->
						<c:if
							test="${documentos.documento.modoGen == 'P' 
							&& documentos.documento.tipoDocumento.incGen == 'G' 
							&& documentos.estadoDocumento == 'REALIZACION' && (documentos.bloqueado eq 'N')}">

							<a
								href="javascript:editardocumentoPDF('<c:out value='${documentos.documento.refDocumento}'/>')"
								onclick="javascript:ejecutarEventoCierre=false;"> <img
								src="../instalaciones/imagenes/escritorio/portlets/modificar.png"
								border="0" alt="Editar" title="Editar" /></a>

						</c:if>

						<!--  Asociar un interesado al documento. -->
						<c:if
							test="${(documentos.bloqueado eq 'N') && (documentos.estadoDocumento != 'PENDIENTE_FIRMA') && (documentos.estadoDocumento != 'FIRMADO') && (documentos.estadoDocumento != 'DESCARTADO') && (documentos.estadoDocumento != 'VERSIONADO') }">

							<a
								href="javascript:abrirVentanaPopUp('../modulos/docsAsociadosExpediente/nuevoInteresadoDocumento.action?refdoc=<c:out value='${documentos.documento.refDocumento}'/>','Interesados');"
								onclick="javascript:ejecutarEventoCierre=false;"> <img
								src="../instalaciones/imagenes/escritorio/portlets/asociar_interesado.png"
								border="0" alt="Asociar un interesado al documento"
								title="Asociar un interesado al documento"
								style="cursor: pointer" /></a>

						</c:if>


						<!--  Terminar el documento -->
						<c:if
							test="${(documentos.estadoDocumento != 'TERMINADO') && 
						(documentos.estadoDocumento != 'PENDIENTE_FIRMA') &&
						(documentos.estadoDocumento != 'FIRMADO') &&
						(documentos.estadoDocumento != 'VERSIONADO') && 
						(documentos.estadoDocumento != 'DESCARTADO')&& (documentos.bloqueado eq 'N') }">
							<a
								href="javascript:terminarDocumento('../modulos/docsAsociadosExpediente/terminadoc.action','refdoc=<c:out value='${documentos.documento.refDocumento}'/>')"
								onclick="javascript:ejecutarEventoCierre=false;"> <img
								src="../instalaciones/imagenes/escritorio/portlets/terminar_documento.png"
								border="0" alt="Terminar" title="Terminar"
								style="cursor: pointer" /></a>

						</c:if>
						<!-- Reanuda el documento. pte comprobar  && documentos.perteneceFaseActual=='true'-->

						<c:if
							test="${documentos.estadoDocumento == 'TERMINADO' 
						&& (documentos.bloqueado eq 'N')}">
							<c:if
								test="${documentos.documento.faseActual.refFaseActual == documentos.faseActual.refFaseActual}">
								<c:if
									test="${ documentos.extension != 'application/pdf' && documentos.documento.tipoDocumento.incGen != 'I' }">
									<a
										href="javascript:reanudarDocumento('../modulos/docsAsociadosExpediente/reanudardoc.action','refdoc=<c:out value='${documentos.documento.refDocumento}'/>')"
										onclick="javascript:ejecutarEventoCierre=false;"> <img
										src="../instalaciones/imagenes/escritorio/portlets/reanudar_documento.png"
										border="0" alt="Reanudar" title="Reanudar"
										style="cursor: pointer" /></a>
								</c:if>
							</c:if>
						</c:if>

						<!-- Opcion para compulsar el documento -->

						<c:if
							test="${(documentos.entradaSalida == 'ENTRADA') && (documentos.bloqueado eq 'N') 
									&& documentos.documento.tipoDocumento.incGen == 'I'
									&& documentos.estadoDocumento == 'TERMINADO' 
									&& documentos.estadoDocumento != 'FIRMADO'

									&& documentos.documento.firmaDig=='SI'
									&& documentos.documento.tipoDocumento.tipoFirma != '-'}">
							<a
								href="javascript:abrirVentanaPopUp('../modulos/docsAsociadosExpediente/iniciarCompulsa.action?refdoc=<c:out value='${documentos.documento.refDocumento}'/>');"
								onclick="javascript:ejecutarEventoCierre=false;"> <img
								src="../instalaciones/imagenes/escritorio/portlets/compulsa.png"
								border="0" alt="Compulsar el documento"
								title="Compulsar el documento" style="cursor: pointer" /></a>

						</c:if>

						<!-- Firmar directamente sin necesidad de enviar a portafirmas -->
						<!--<c:if
							test="${documentos.documento.tipoDocumento.incGen == 'G'
							&& documentos.estadoDocumento == 'TERMINADO' 
							&& documentos.estadoDocumento != 'FIRMADO'
							&& documentos.numFirmantes > 0
							&& (documentos.bloqueado eq 'N')
							&& documentos.esFirmablePorUsuario == 'true' }">

							<c:choose>
								<c:when test="${documentos.documento.firmaDig=='SI'}">
									<a
										href="javascript:abrirVentanaPopUp('../modulos/docsAsociadosExpediente/iniciarFirmaSinPortaFirmas.action?refdoc=<c:out value='${documentos.documento.refDocumento}'/>');"
										onclick="javascript:ejecutarEventoCierre=false;"> <img
										src="../instalaciones/imagenes/escritorio/portlets/enviopfirma2.png"
										border="0" alt="Firmar el Documento"
										title="Firmar el Documento" style="cursor: pointer" /></a>
								</c:when>
							</c:choose>

						</c:if>-->

						<!-- Poner a pendiente de firma  ,<c:out value='${PKEXP}'/> -->
						<c:if
							test="${documentos.estadoDocumento != 'PENDIENTE_FIRMA'
							&& documentos.estadoDocumento != 'FIRMADO'
							&& documentos.estadoDocumento != 'DESCARTADO'
							&& documentos.estadoDocumento == 'TERMINADO' 
							&& documentos.documento.tipoDocumento.tipoFirma != '-'
							&& documentos.numFirmantes > 0
							&& (documentos.bloqueado eq 'N')
							&& documentos.documento.tipoDocumento.incGen == 'G' }">

							<c:choose>
								<c:when test="${documentos.documento.firmaDig=='SI'}">
									<a
										href="javascript:abrirVentanaPopUp('../modulos/portafirmas/abrirPortafirmas.action?refdoc=<c:out value='${documentos.documento.refDocumento}'/>');"
										onclick="javascript:ejecutarEventoCierre=false;"> <img
										src="../instalaciones/imagenes/escritorio/portlets/enviopfirma2.png"
										border="0" alt="Portafirma" title="Portafirma" /></a>
								</c:when>

								<c:when test="${documentos.documento.firmaDig=='NO'}">
									<a id="envio"
										href="javascript:ponerPendienteFirma('refdoc=<c:out value='${documentos.documento.refDocumento}'/>&refexp=<c:out value='${PKEXP}'/>')"
										onclick="javascript:ejecutarEventoCierre=false;"> <img
										src="../instalaciones/imagenes/escritorio/portlets/enviopfirma2.png"
										border="0" alt="Poner pendiente de firma"
										title="Poner pendiente de firma" /></a>
								</c:when>
							</c:choose>
						</c:if>

						<!-- Poner a pendiente de firma un documento INCORPORADO ,<c:out value='${PKEXP}'/> -->
						<c:if
							test="${documentos.estadoDocumento != 'PENDIENTE_FIRMA'
							&& documentos.estadoDocumento != 'FIRMADO'
							&& documentos.estadoDocumento != 'DESCARTADO'
							&& documentos.estadoDocumento == 'TERMINADO' 
							&& documentos.documento.tipoDocumento.tipoFirma != '-'
							&& documentos.numFirmantes > 0
							&& (documentos.bloqueado eq 'N')
							&& documentos.documento.tipoDocumento.incGen == 'I' 
							&& ( documentos.extension =='application/pdf' || documentos.extension == 'application/zip')}">

							<c:choose>
								<c:when test="${documentos.documento.firmaDig=='SI'}">
									<a
										href="javascript:abrirVentanaPopUp('../modulos/portafirmas/abrirPortafirmas.action?refdoc=<c:out value='${documentos.documento.refDocumento}'/>');"
										onclick="javascript:ejecutarEventoCierre=false;"> <img
										src="../instalaciones/imagenes/escritorio/portlets/enviopfirma2.png"
										border="0" alt="Portafirma" title="Portafirma" /></a>
								</c:when>

								<c:when test="${documentos.documento.firmaDig=='NO'}">
									<a id="envio"
										href="javascript:ponerPendienteFirma('refdoc=<c:out value='${documentos.documento.refDocumento}'/>&refexp=<c:out value='${PKEXP}'/>')"
										onclick="javascript:ejecutarEventoCierre=false;"> <img
										src="../instalaciones/imagenes/escritorio/portlets/enviopfirma2.png"
										border="0" alt="Poner pendiente de firma"
										title="Poner pendiente de firma" /></a>
								</c:when>
							</c:choose>
						</c:if>

						<!-- Ver documento firmado y cajetin  -->
						<!-- 
					<c:if
						test="${documentos.estadoDocumento == 'FIRMADO' && documentos.esDescargadoPortaFirmas == 'true' }">
						<a
							href="javascript:abrirDocumento('${documentos.refGestor}');" onclick="javascript:ejecutarEventoCierre=false;">
						<img src="../instalaciones/imagenes/escritorio/portlets/verfirma.png" border="0"
							alt="Ver documento firmado" title="Ver documento firmado" /></a>
					</c:if>
					
					<c:if
						test="${documentos.estadoDocumento == 'FIRMADO' && documentos.esDescargadoPortaFirmas == 'false' && documentos.documento.tipoDocumento.incGen == 'I'}">
						<a
							href="javascript:if('${documentos.documento.observaciones}' == 'URL'){ abrirDocumento('${documentos.refGestor}');} else{
							abrirVentanaPopUp('../modulos/docsAsociadosExpediente/verDocumentoIncorporadoYCompulsado.action?refdoc=<c:out value='${documentos.documento.refDocumento}'/>');}" onclick="javascript:ejecutarEventoCierre=false;">
						<img src="../instalaciones/imagenes/escritorio/portlets/verfirma.png" border="0"
							alt="Ver documento firmado" title="Ver documento firmado"/></a>
					</c:if>
					 -->

						<!--  Código nuevo extraído de aquí-->


						<c:if
							test="${ permitirNotificar && (documentos.documento.formato != null && documentos.documento.formato != '') &&
							(documentos.documento.tipoDocumento.notificable) &&  (documentos.bloqueado eq 'N') &&
							documentos.estadoDocumento == 'FIRMADO'}">
							<a
								href="javascript:notificarDocumento('refdoc=<c:out value='${documentos.documento.refDocumento}'/>')"
								onclick="javascript:ejecutarEventoCierre=false;"> <img
								src="../instalaciones/imagenes/escritorio/portlets/ico_notifica.png"
								border="0" alt="Notific@" title="Notific@" /></a>

						</c:if>

						<c:if
							test="${ permitirRegistro && (documentos.documento.formato != null && documentos.documento.formato != '') && documentos.estadoDocumento == 'FIRMADO' && documentos.documento.tipoDocumento.registrable == 'true' && (documentos.documento.registroDocumento.registroDocumento.NUMREGSALIDADOC eq null)}">
							<%UsuarioWeb user = (UsuarioWeb)session.getAttribute("usuario_en_sesion"); %>
							<a
								href="javascript:abrirVentanaPopUp('../modulos/registro/abrirRegistro.action?refdoc=<c:out value='${documentos.documento.refDocumento}'/>&refexp=<%=user.getExpediente().getRefExpediente() %>')"
								onclick="javascript:ejecutarEventoCierre=false;"> <img
								src="../instalaciones/imagenes/escritorio/portlets/registroaries2.png"
								border="0" alt="Registro @ries" title="Registro @ries" /></a>
						</c:if>
						
						<!--  Modificar Documento -->
						<c:if
							test="${(documentos.estadoDocumento != 'TERMINADO') && 
						(documentos.documento.tipoDocumento.incGen == 'I') &&
						(documentos.estadoDocumento != 'PENDIENTE_FIRMA') &&
						(documentos.estadoDocumento != 'FIRMADO') &&
						(documentos.estadoDocumento != 'VERSIONADO') && 
						(documentos.estadoDocumento != 'DESCARTADO')&& (documentos.bloqueado eq 'N') }">
							<a
								href="javascript:abrirVentanaPopUp('../modulos/otrasOpciones/modificarDocumentoPDF.action?refdoc=<c:out value='${documentos.documento.refDocumento}'/>')"
								onclick="javascript:ejecutarEventoCierre=false;"> <img
								src="../instalaciones/imagenes/escritorio/portlets/editar.png"
								border="0" alt="Editar documento" title="Editar documento" /></a>

						</c:if>


					</display:column>

				</display:table>

			</s:if>
			<s:else>
				<s:div cssStyle="font-size: 0.75em; color: grey;">
				 Sin resultados. 
				</s:div>
			</s:else>
		</form>
	</div>
	<script>
		ocultarDivEspera();
	</script>
</body>

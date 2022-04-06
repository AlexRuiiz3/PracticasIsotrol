
var divRecargaPestana = "";	

/**
 * Función que calcula el divRecarga de la pestaña
 *          
 */
function calculoDivRecargaPestana() {
	var parametros = "";

	$.ajax({  
		type: "POST",  
		dataType : "json",
		url: "../busquedaJson/calcularNombreDivPrevio.action",
		data: parametros,
		
		success: function(json){  
			divRecargaPestana = json.nombreDivPrevio;
		} 
	});
}


/**
 * Función que carga cada pestaña con su módulo correspondiente al hacer click 
 * sobre la pestaña.
 * 
 * @param url
 *            url del action de inicio 
 * @param nombre
 *            nombre del modulo
 * @param orden
 *            orden de la pestaña seleccionada          
 */
function cargarModuloPortlet(url, nombre, orden) {
		
	var div = "#contenidoPestana";
	var parametros = "";
	divRecargaPestana = "#" + nombre + "_" + orden;
	
	$(div).html('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>');
	
	$.ajax({  
		type: "POST", 
		async: false,
		url: url,
		data: parametros,
		
		success: function(data){  
			$(div).html(data);
		} 
	});
}

/**
 * Función que carga cada pestaña con su módulo correspondiente al hacer click 
 * sobre la pestaña.
 * 
 * @param url
 *            url del action de inicio 
 * @param nombre
 *            nombre del modulo
 * @param orden
 *            orden de la pestaña seleccionada          
 */
function cargarModuloPortletInicio(url, nombre, orden) {
		
	var div = "#contenidoPestana";
	var parametros = "";
	divRecargaPestana = "#" + nombre + "_" + orden;
	
	$(div).html('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>');
	
	$.ajax({  
		type: "POST", 
		url: url,
		data: parametros,
		
		success: function(data){  
			$(div).html(data);
		} 
	});
}
    
/**
 * Función que carga la fase y la fecha de entrada de la fase
 *       
 */
function cargarEstado() {
	
	var parametros = "";
	
	$.ajax({  
		type: "POST", 
		async: false,
		url: "cargarEstadoPestana.action",
		data: parametros,
		
		success: function(data){  
			$('#estadoExp').html(data);
		}  
	});
}


function cargarCsv() {
	
	var parametros = "";
	
	$.ajax({  
		type: "POST", 
		async: false,
		url: "cargarCsvExpediente.action",
		data: parametros,
		
		success: function(data){  
			$('#csvExp').html(data);
		}  
	});
}



/**
 * Función que carga las utilidades
 *       
 */
function cargarFunciones() {
	
	
	if(!existeDesplegableUtilidades){
	
	var parametros = "";
	
	$.ajax({  
		type: "POST",  
		url: "../fase/mostrarFunciones.action",
		data: parametros,
		
		success: function(data){  
			$('#divCapaFunciones').html(data);
		}  
	});
	
	}
}

/**
 * Función que carga la informacion general del expediente
 *       
 */
function cargarInformacionExp() {
	
	$('#informacionExp').html('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>');
	
	var parametros = "";
	
	$.ajax({  
		type: "POST",
		url: "../modulos/portletDatosExpediente/inicio.action",
		data: parametros,
		
		success: function(data){  
			$('#informacionExp').html(data);
		}  
	});
}

/**
 * Función que carga el módulo de transiciones
 *       
 */
function cargarModuloTransicion() {
	
	$('#divModuloTransicion').html('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>');
	
	var parametros = "";
	
	$.ajax({  
		type: "POST",  
		url: "cargarBloqueTransicion.action",
		data: parametros,
		
		success: function(data){  
			$('#divModuloTransicion').html(data);
		}  
	});
}



/**
 * Función que carga los eventos en el módulo de transiciones clásico
 *       
 */
function cargarEventosTramita(checked) {
	
	$.ajax({  
		type: "POST",  
		url: "../modulos/tramitacion/listarTransicionesExpediente.action",
		data: {
			evento: checked
          },
		
		success: function(data){  
			$('#cajaTramitaciones').html(data);
		}  
	});
}



/**
 * Función que carga los eventos en el módulo de ayuda a la tramitación
 *       
 */
function cargarEventosAyudaTramita(checked) {
	
	$.ajax({  
		type: "POST",  
		url: "../modulos/ayudaTramitacion/listarTransicionesExpediente.action",
		data: {
			evento: checked
          },
		
		success: function(data){  
			$('#cajaTramitacionesAyuda').html(data);
		}  
	});
}

/**
 * Función que carga la pestaña seleccionada 
 * 
 * @param indicePestana
 *            indice de la pestaña seleccionada   
 */
function cargarPestanas(indicePestana) {
	
		
	$('#contenidoPestana').html('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>');
	
	$.ajax({  
		type: "POST",  
		url: "cargarPestanas.action",
		data:  {
			indicePestana: indicePestana
        },
		success: function(data){  
			$('#contenidoPestana').html(data);
		}  
	});
}

/**
 * Función que tramita recargando el escritorio de tramitación por pestañas
 * 
 * @param para
 *            identificador de la fase   
 */
function cargarTramitarEscritorioPestana (para){
		
	$.ajax({  
		type: "POST",  
		url: "../modulos/tramitacion/tramita.action",
		beforeSend: function () {
			$.blockUI({ 
				centerX: true, 
				centerY: true, 
				message: '<h1 style="font-size: 1.2em !important;">Tramitando el expediente...</h1>'
			});
		},
		data: {
			REFTRAMITE: para
          },
		
          success: function( data ) {
        	  
        	  if (data != '' && data.indexOf('<div id="tituloPagina" style="color: black;">Avisos</div>')!= -1 ) {
        			 abrirPopUp('../modulos/tramitacion/verMensajes.action');
        		 } else {
        			         	
        			 cargarModuloTransicion();
        			 cargarEstado();
        			 cargarCsv();
        			 cargarFunciones();
        			 cargarPestanas(indicePestana);
        			 		 
        			 if(data != '' && data.indexOf('<legend>Avisos de tramitaci')!= -1){
        				
        				var width = screen.width*70/100;
        				var height = screen.height*70/100;
        				var leftVal = (screen.width - width) / 2;
        				var topVal = (screen.height - height) / 2;
        				tareasWindowAvisos= window.open('../modulos/tramitacion/visualizarAvisos.action','Avisos','left='+leftVal+',top='+topVal+',toolbar=no,status=no,resizable=yes,scrollbars=yes');
        				tareasWindowAvisos.resizeTo(width,height);
        			 }
        			 
        		 }
	      },
          
          complete: function( data ) {
        	  $.unblockUI();
          }
          
		});
	
}

/**
 * Función que cambia de fase actual del expediente
 * 
 * @param para
 *            identificador de la fase   
 */
function seleccionarFaseActualPestana (para){
	
	$.ajax({  
		type: "POST",  
		url: "../fase/cambiarFaseActual.action",
		beforeSend: function () {
			$.blockUI({ 
				centerX: true, 
				centerY: true,
				message: '<h1 style="font-size: 1.2em !important;">Cambiando estado del expediente...</h1>' });
		},
		data: {
			faseSeleccionada: para
          },
		
          success: function( data ) {
        	  	
    			 cargarModuloTransicion();
    			 cargarEstado();
    			 cargarCsv();
    			 cargarFunciones();
    			 cargarPestanas(indicePestana);
        		
	      },
          
          complete: function( data ) {
        	  $.unblockUI();
          }
          
		});
	
}

/**
 * Función que elimina la reserva del expediente
 * 
 */
function eliminarReservaExpediente(numExp){
		
	$.ajax({  
		type: "POST",  
		url: "eliminarReservaExpediente.action",
		data:  {
			refExp : numExp
		},
		async: false, 
		success: function(data){  
		}  
	});
}

/**
 * Función para mantener compatibilidad con los módulos de
 * tipo portlet
 * 
 * @param parametros the parametros
 * @returns {Boolean} true
 */
function replaceLinks(parametros) {
	return true;
}

/**
 * Función para mantener compatibilidad con los módulos de
 * tipo portlet
 * 
 * @param parametros the parametros
 * @returns {Boolean} true
 */
function replaceLinksTareas(parametros) {
	return true;
}

/**
 * Función para mantener compatibilidad con los módulos de
 * tipo portlet
 * 
 * @param parametros the parametros
 * @returns {Boolean} true
 */
function replaceLinksCads(parametros) {
	return true;
}

/**
 * Función para mantener compatibilidad con los módulos de
 * tipo portlet
 * 
 * @param parametros the parametros
 * @returns {Boolean} true
 */
function replaceLinksMensajes(parametros) {
	return true;
}

/**
 * Función para mantener compatibilidad con los módulos de
 * tipo portlet
 * 
 * @param parametros the parametros
 * @returns {Boolean} true
 */
function replaceLinksBandeja(parametros) {
	return true;
}

/**
 * Funcion de prueba para llamar al action de las condiciones de tareas
 */
function evaluarCondicionTarea(id) {
	
	$('#modal-body').html('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>');
	
	openModal('modalInfo');
	$.ajax({  
		type: "POST",  
		url: "evaluarCondicionesTarea.action",
		data:  {
			idTarea : id
		},
		success: function(data){  
			$('#modal-body').html(data);
		}  
	});
}

/**
 * Funcion de prueba para llamar al action de las condiciones de transiciones
 */
function evaluarCondicionTransicion(id) {
	
	$('#modal-body').html('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>');
	
	openModal('modalInfo');
	$.ajax({  
		type: "POST",  
		url: "evaluarCondicionesTransicion.action",
		data:  {
			idTransition : id
		},
		success: function(data){  
			$('#modal-body').html(data);
		}  
	});
}   
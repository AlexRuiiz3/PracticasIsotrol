/**
 * Función que carga en el portlet del módulo pasado por parámetro el contenido
 * que también se pasa como parámetro
 * 
 * @param modulo
 *            modulo
 * @param contenido
 *            contenido HTML
 * @return
 */
function mantenerDimensiones(modulo, contenido) {
	document.getElementById(modulo).innerHTML = contenido;
}


/**
 * Funcion que devuelve el nombre del módulo cargado
 * en la pestaña activa
 * 
 */
function obtenerNombreModuloPestanaActiva() {
	
	var nombreModuloActivo = '';
	var orden = '';
	
	if(indicePestana == '0'){
		calculoDivRecargaPestana();
	}
	
	if ( divRecargaPestana != '' ) {
		var auxNombre = divRecargaPestana.substring(1);
		var arrayNombre = auxNombre.split("_");
		orden = arrayNombre[arrayNombre.length - 1];
		var indiceFin = auxNombre.length - (orden.length + 1); 
		nombreModuloActivo = auxNombre.substring(0, indiceFin);
		
		
	}
	
	return nombreModuloActivo;
}


/**
 * Funcion que establece imagen de espera en los bloques fijos en funcion del booleano
 * recibido
 * 
 * @param recargaTransiciones the recargaTransiciones
 * @param recargaEstado the recargaEstado
 * @param recargaUtilidades the recargaUtilidades
 * @param recargaPestanaActiva the recargaPestanaActiva
 * @param recargaInformacion the recargaInformacion
 */
function establecerImagenEspera( recargaTransiciones, recargaEstado, recargaUtilidades, recargaPestanaActiva, recargaInformacion ) {
	
	if ( recargaTransiciones ) {
		$('#divModuloTransicion').html('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>');
	}
	
	if ( recargaEstado ) {
		$('#estadoExp').html('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>');
	}
	
	if ( recargaUtilidades ) {
		$('#divCapaFunciones').html('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>');
	}
	
	if ( recargaPestanaActiva ) {
		$('#contenidoPestana').html('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>');
	}
	
	if ( recargaInformacion ) {
		$('#informacionExp').html('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>');
	}
}


/**
 * Funcion que realiza la recarga de los bloques fijos
 * 
 * @param recargaTransiciones the recargaTransiciones
 * @param recargaEstado the recargaEstado
 * @param recargaUtilidades the recargaUtilidades
 * @param recargaPestanaActiva the recargaPestanaActiva
 *  * @param recargaInformacion the recargaInformacion
 */
function recargarBloquesFijos( recargaTransiciones, recargaEstado, recargaUtilidades, recargaPestanaActiva, recargaInformacion ) {
	
	if ( recargaTransiciones ) {
		cargarModuloTransicion();
	}
	
	if ( recargaEstado ) {
		cargarEstado();
	}
	
	if ( recargaUtilidades ) {
		cargarFunciones();
	}
	
	if ( recargaPestanaActiva ) {
		cargarPestanas(indicePestana);
	}
	
	if ( recargaInformacion ) {
		cargarInformacionExp();
		cargarCsv();
	}
	
}


/**
 * Función utilizada para ejecutar una acción mediante una petición 
 * al servidor.
 * 
 * IMPORTANTE: NO INTERESA ESPERAR LA RESPUESTA RECIBIDA. SOLO QUEREMOS QUE SE
 * EJECUTE EL ACTION PARA PODER CONTINUAR.
 * 
 * @param array
 *            portlet a recargar
 * @return
 */
function insertarPaginaAS(array, ini) {
	
	if (document.getElementById(array[0][1]) != "undefined") {
		
		var parametros = '';
		if (array[0][2] != null) {
			parametros = array[0][2];
		}
		
		$.ajax({  
			type: "POST",  
			url: array[0][1],
			data: parametros
		});
		
		return true;
	}
}


/**
 * Función que realiza la recarga de UN ÚNICO PORTLET. El array recibido sólo
 * debe tener una posición.
 * 
 * IMPORTANTE: A DIFERENCIA DE INSERTARPAGINAAS AQUÍ NOS PREGUNTAMOS CUANDO LA
 * RESPUESTA DEL SERVIDOR HA FINALIZADO PARA PODER INSERTAR LA RESPUESTA
 * OBTENIDA.
 * 
 * @param array
 *            portlet a recargar
 * @return
 */
function insertarPaginaUnicoPortlet(array) {

	if (document.getElementById(array[0][1]) != "undefined") {
		
		//Obtenemos el nombre del módulo
		var nombreModulo = array[0][0];
		
		//Solo invocamos a la recarga si el módulo pasado
		//por parámetro se corresponde con la pestaña activa
		var nombreModuloActivo = obtenerNombreModuloPestanaActiva();
		
		if ( nombreModulo == nombreModuloActivo ) {
			
			//El módulo a recargar se corresponde con
			//la pestaña activa
			
			//Imagen espera
			$('#contenidoPestana').html('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>');
			
			var parametros = '';
			if (array[0][2] != null) {
				parametros = array[0][2];
			}
			
			$.ajax({  
				type: "POST",  
				url: array[0][1],
				data: parametros,
				
				success: function(data){  
					$('#contenidoPestana').html(data);
					
					if (array[0][3]) {
						eval(array[0][3]);
					}
				} 
			});
		} else if ( nombreModulo == "capa_contenedora_popup") {
			
			var parametros = '';
			if (array[0][2] != null) {
				parametros = array[0][2];
			}
			
			$.ajax({  
				type: "POST",  
				url: array[0][1],
				data: parametros,
				
				success: function(data){
					
					if (document.getElementById('capa_contenedora_popup') != null) {
						$('#capa_contenedora_popup').html(data);
					}
					
					if (array[0][3]) {
						eval(array[0][3]);
					}					
				} 
			});
		}
		
		return true;
	}
}


/**
 * Método que realiza la recarga de todos los portlets que se reciben en el
 * array. El primer action a ejecutar es iniciaTarea. Si tenemos avisos para
 * visualizar se abrira un popup con la lista de avisos configurados.
 * 
 * 
 * IMPORTANTE: EL PORTLET PASADO EN LA PRIMERA POSICION DEBE EJECUTARSE EL
 * PRIMERO YA QUE EL RESTO DE PORTLETS DEPENDEN DEL CONTENIDO DEL PRIMERO.
 * 
 * 
 * @param array
 *            array de portlets a recargar
 * @param ini
 * @return
 */
function insertarPaginaTareaAvisos(array, ini) {
	
	if (document.getElementById(array[0][1]) != "undefined") {
		
		//Imagen espera
		$('#contenidoPestana').html('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>');
		$('#divModuloTransicion').html('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>');
		
		var parametros = '';
		if (array[0][2] != null) {
			parametros = array[0][2];
		}
		
		$.ajax({  
			type: "POST",  
			url: array[0][1],
			data: parametros,
			
			success: function(data){
				
				// Nos preguntamos si tenemos avisos que visualizar
				if (data != '') {
					
					// Tenemos avisos que visualizar
					var width = screen.width * 70 / 100;
					var height = screen.height * 70 / 100;
					var leftVal = (screen.width - width) / 2;
					var topVal = (screen.height - height) / 2;
					tareasWindowAvisos = window
							.open(
									"../modulos/bloquesPermitidos/visualizarAvisosIniciarTareaExp.action",
									'tareaAvisos',
									'left='
											+ leftVal
											+ ',top='
											+ topVal
											+ ',toolbar=no,status=no,resizable=yes,scrollbars=yes');
					tareasWindowAvisos.resizeTo(width, height);
				}
				
				if (array[0][3]) {
					eval(array[0][3]);
				}
				
				//Recargar transiciones
				cargarModuloTransicion();
				
				//Recargar pestana actual
				cargarPestanas(indicePestana);
			} 
		});
		
		return true;
	}
}
		

/**
 * Método que realiza la recarga de todos los portlets que se reciben en el
 * array.
 * 
 * 
 * IMPORTANTE: EL PORTLET PASADO EN LA PRIMERA POSICION DEBE EJECUTARSE EL
 * PRIMERO YA QUE EL RESTO DE PORTLETS DEPENDEN DEL CONTENIDO DEL PRIMERO.
 * 
 * 
 * @param array
 *            array de portlets a recargar
 * @param ini
 * @return
 */
function insertarPagina(array, ini) {
	
	if (document.getElementById(array[0][1]) != "undefined") {
		
		var hayRecargaTransiciones = false;
		var hayRecargaEstado = false;
		var hayRecargaUtilidades = false;
		var hayRecargaPestanaActiva = false;
		var hayRecargaInformacion = false;
		
		var nombreModuloActivo = obtenerNombreModuloPestanaActiva();
		
		if (array.length > 0) {
			
			for (contador = 0; contador < array.length; contador++) {
				
				if ( array[contador][0] == 'capa_tramita' || array[contador][0] == 'ayudaTramitacion' ) {
					hayRecargaTransiciones = true;
					
				} else if ( array[contador][0] == 'estado' ) { 
					hayRecargaEstado = true;
					
				} else if ( array[contador][0] == 'capa_funciones' && !existeDesplegableUtilidades ) { 
					hayRecargaUtilidades = true;
					
				} else if ( array[contador][0] == 'capa_consulta' || array[contador][0] == 'portletDatosExpediente' ) { 
					hayRecargaInformacion = true;	
					
				} else {
					
					if ( nombreModuloActivo == array[contador][0] ) {
						hayRecargaPestanaActiva = true;
					}					
				}
			}
		}
		
		//Ponemos la imagen de espera donde aplique
		establecerImagenEspera( hayRecargaTransiciones, hayRecargaEstado, hayRecargaUtilidades, hayRecargaPestanaActiva , hayRecargaInformacion );		
		
		//Ejecutamos el action de la primera posicion del array.
		//El nombre recibido  en la primera posición podrá referirse
		//a un módulo o no.
		var parametros = '';
		if (array[0][2] != null) {
			parametros = array[0][2];
		}
		
		$.ajax({  
			type: "POST",  
			url: array[0][1],
			data: parametros,
			
			success: function(data){
				
				if ( array[0][0] != 'vaciar' &&
						array[0][0] != 'capa_emergente' &&
						array[0][0] != 'capa_contenedora_popup' &&
						array[0][0] != 'capa_ejecutarTarea' &&
						array[0][0] != 'capa_mensaje_detalle' ) {
					
					$('#contenidoPestana').html(data);
					hayRecargaPestanaActiva = false;
					
				} else if ( array[0][0] == 'capa_contenedora_popup' ) {
					if (document.getElementById('capa_contenedora_popup') != null) {
						$('#capa_contenedora_popup').html(data);
					}
				}											
								
				recargarBloquesFijos( hayRecargaTransiciones, hayRecargaEstado, hayRecargaUtilidades, hayRecargaPestanaActiva, hayRecargaInformacion );
				
				if (array[0][3]) {
					eval(array[0][3]);
				}
			} 
		});
		
		return true;
	}
}


/**
 * Método que realiza la recarga de todos los portlets que se reciben en el
 * array en la eliminacion.
 * 
 * 
 * IMPORTANTE: EL PORTLET PASADO EN LA PRIMERA POSICION DEBE EJECUTARSE EL
 * PRIMERO YA QUE EL RESTO DE PORTLETS DEPENDEN DEL CONTENIDO DEL PRIMERO.
 * 
 * 
 * @param array
 *            array de portlets a recargar
 * @param ini
 * @return
 */
function insertarPaginaEliminacion(array, ini) {
	
	if (document.getElementById(array[0][1]) != "undefined") {
		
		//Imagen espera
		$('#contenidoPestana').html('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>');
		$('#divModuloTransicion').html('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>');
		
		var parametros = '';
		if (array[0][2] != null) {
			parametros = array[0][2];
		}
		
		$.ajax({  
			type: "POST",  
			url: array[0][1],
			data: parametros,
			
			success: function(data){
				
				if (data.lastIndexOf("Se ha producido un error") != -1) {
					window.location = '../tratamientoErrores?id=errorTarea';

				} else {
					$('#contenidoPestana').html(data);					
				}
				
				//Recargar transiciones
				cargarModuloTransicion();
				
				//Recargar pestana actual
				cargarPestanas(indicePestana);
				
				if (array[0][3]) {
					eval(array[0][3]);
				}
			} 
		});
		
		return true;
	}
}


/**
 * Método que realiza la recarga de todos los portlets que se reciben en el
 * array.
 * 
 * 
 * IMPORTANTE: TODOS LOS PORTLETS SE LANZAN SIMULTÁNEAMENTE YA QUE NINGUNO
 * NECESITA DE LA EJECUCIÓN DE LOS OTROS
 * 
 * 
 * @param array
 *            array de portlets a recargar
 * @param ini
 * @return
 */
function insertarPaginaSinDependencia(array, ini) {

	if (document.getElementById(array[0][1]) != "undefined") {
		
		var hayRecargaTransiciones = false;
		var hayRecargaEstado = false;
		var hayRecargaUtilidades = false;
		var hayRecargaPestanaActiva = false;
		var hayRecargaInformacion = false;
		
		var nombreModuloActivo = obtenerNombreModuloPestanaActiva();
		
		if (array.length > 0) {
			
			for (contador = 0; contador < array.length; contador++) {
				
				if ( array[contador][0] == 'capa_tramita' || array[contador][0] == 'ayudaTramitacion' ) {
					hayRecargaTransiciones = true;
					
				} else if ( array[contador][0] == 'estado' ) { 
					hayRecargaEstado = true;
					
				} else if ( array[contador][0] == 'capa_funciones' && !existeDesplegableUtilidades ) { 
					hayRecargaUtilidades = true;
					
				} else if ( array[contador][0] == 'capa_consulta' || array[contador][0] == 'portletDatosExpediente' ) { 
					hayRecargaInformacion = true;	
					
				} else {
					
					if ( nombreModuloActivo == array[contador][0] ) {
						hayRecargaPestanaActiva = true;
					}					
				}
			}
		}
		
		//Ponemos la imagen de espera donde aplique
		establecerImagenEspera( hayRecargaTransiciones, hayRecargaEstado, hayRecargaUtilidades, hayRecargaPestanaActiva, hayRecargaInformacion );		
		
		//Recarga de módulos
		recargarBloquesFijos( hayRecargaTransiciones, hayRecargaEstado, hayRecargaUtilidades, hayRecargaPestanaActiva, hayRecargaInformacion );
		
		return true;
	}
}
	

/**
 * Método que realiza la recarga de una ventana popup.
 * 
 * @param array
 *            array con el action a ejecutar
 * @param ini
 *            indica si se establece la imagen de espera mientras se espera la
 *            respuesta del servidor
 * @return
 */
function insertarPaginaSimple(array, ini) {
	
	if (document.getElementById(array[0][0]) != null) {
		mantenerDimensiones(array[0][0], '<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>');
	}
	
	var parametros = '';
	if (array[0][2] != null) {
		parametros = array[0][2];
	}
	
	$.ajax({  
		type: "POST",  
		url: array[0][1],
		data: parametros,
		
		success: function(data){
			
			if (document.getElementById(array[0][0]) != null) {
				mantenerDimensiones(array[0][0], data);
			}
			
			if (array[0][3]) {
				eval(array[0][3]);
			}
		} 
	});
	
	return true;
}


/**
 * Método que realiza de forma simultánea la recarga de los portlets pasados en
 * el array. Esta función debe invocarse siempre desde una ventana hija de la
 * siguiente forma: opener.recargaPadre()
 * 
 * @param array
 *            portlets a recargar
 * @return
 */
function recargaPadre(array) {
		
	insertarPaginaSinDependencia(array,false);
}
	

/**
 * Método que realiza la recarga de todos los portlets observadores del portlet
 * cuyo nombre se pasa como parámetro.
 * 
 * 
 * IMPORTANTE: TODOS LOS PORTLETS SE LANZAN SIMULTÁNEAMENTE YA QUE NINGUNO
 * NECESITA DE LA EJECUCIÓN DE LOS OTROS
 * 
 * 
 * @param nombre_modulo
 *            the nombre_modulo
 * 
 * @return
 */
function recargarModulosObservadores(nombre_modulo) {
	
	// Si el array es vacío no hacemos nada
	if (nombre_modulo == null || nombre_modulo == '')
		return false;
	
	// Array donde almacenamos la respuesta del servidor. Contendrá
	// todos los IDs de los módulos a recargar.
	var listaModulos = new Array();
	
	//Realizamos petición al servidor para conocer
	//los modulos observadores
	var param = "nombreModuloObservados=" + nombre_modulo;
	
	$.ajax({  
		type: "POST",  
		url: "../funciones/irServicioGWTObservados.action",
		data: param,
		
		success: function(data){
			listaModulos = eval(data);
		} 
	});
	
	
	var nombreModuloActivo = obtenerNombreModuloPestanaActiva();
	var hayRecargaTransiciones = false;
	var hayRecargaEstado = false;
	var hayRecargaUtilidades = false;
	var hayRecargaPestanaActiva = false;
	var hayRecargaInformacion = false;
	
	for (x = 0; x < listaModulos.length; x++) {
		var modulo = listaModulos[x];
		var auxiliar = modulo.split('#');

		array[x] = new Array();
		array[x][0] = auxiliar[0];
		array[x][1] = auxiliar[1];
		
		
		//Controlamos las posibles recargas
		
		//Puede darse el caso que se recarge un módulo
		//que ya ha sido recargado.
		//Controlamos este caso.
		var aux = '';
		if ( auxiliar[0].indexOf("capa_tramita") != -1 || auxiliar[0].indexOf("ayudaTramitacion")  != -1 ) {
			
			aux = $('#divModuloTransicion').html();
			
			if ( aux.indexOf('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>') != -1 ) {
				hayRecargaTransiciones = true;
			}
			
		} else if ( auxiliar[0].indexOf("estado")  != -1 ) { 
			
			aux = $('#estadoExp').html();
			
			if ( aux.indexOf('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>') != -1 ) {
				hayRecargaEstado = true;
			}
			
		} else if ( auxiliar[0].indexOf("capa_funciones")  != -1 && !existeDesplegableUtilidades ) { 
			
			aux = $('#divCapaFunciones').html();
			
			if ( aux.indexOf('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>') != -1 ) {
				hayRecargaUtilidades = true;
			}
			
		} else if ( auxiliar[0].indexOf("capa_consulta")  != -1) { 
			
			aux = $('#informacionExp').html();
			
			if ( aux.indexOf('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>') != -1 ) {
				hayRecargaInformacion = true;
			}
			
		} else {
			
			if ( auxiliar[0].indexOf("nombreModuloActivo")  != -1 ) {
				
				aux = $('#contenidoPestana').html();
				
				if ( aux.indexOf('<p><img src="../instalaciones/imagenes/images/imagenEspera.gif" /></p>') != -1 ) {
					hayRecargaPestanaActiva = true;
				}
			}					
		}
	}
		
	//Ponemos la imagen de espera donde aplique
	establecerImagenEspera( hayRecargaTransiciones, hayRecargaEstado, hayRecargaUtilidades, hayRecargaPestanaActiva, hayRecargaInformacion );
		
	recargarBloquesFijos( hayRecargaTransiciones, hayRecargaEstado, hayRecargaUtilidades, hayRecargaPestanaActiva, hayRecargaInformacion );	
	
	return true;
}


/**
 * Método que realiza de forma simultánea la recarga de los módulos observadores
 * del módulo cuyo nombre se pasa por parámetro.
 * 
 * @param nombre_modulo
 *            the nombre_modulo
 * 
 * @return
 */
function recargaPadreModulosObservados(nombre_modulo) {
	recargarModulosObservadores(nombre_modulo);
}


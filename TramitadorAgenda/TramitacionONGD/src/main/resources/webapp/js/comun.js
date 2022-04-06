window.capaEspera;

/**
 * Realiza peticion AJAX
 * @param type tipo de la peticion HTTP.
 * @param url URL del endpoint que atiende la peticion.
 * @param data Datos a enviar.
 * @param success Callback con funcion de exito.
 * @param error Callback con funcion de error.
 * @returns
 */
function peticionAjax(type, url, data, success, error, async) {
  window.capaEspera = cargarCapaEspera();
  var asyncRequest;
  if (typeof async === "undefined") {
    asyncRequest = true;
  } else {
    asyncRequest = async;
  }

  return $.ajax({
    type: type,
    url: url,
    data: data,
    async: asyncRequest,
    success: success,
    error: error,
    complete: function() {
      window.capaEspera.hide();
    }
  });
}

/**
 * Realiza peticion AJAX POST
 * @param url URL del endpoint que atiende la peticion.
 * @param data Datos a enviar.
 * @param success Callback con funcion de exito.
 * @param error Callback con funcion de error.
 * @returns
 */
function peticionAjaxPOST(url, data, success, error, async) {
  return peticionAjax("POST", url, data, success, error, async);
}

/**
 * Treatment in case of errors in ajax calls to server.
 *
 * @param xhr
 * @param textStatus
 * @param error
 */
function handleAjaxError(jqXHR, textStatus, error) {

  var msg = "";

  if (jqXHR.status === 0) {
    msg = 'No se puede conectar.\n Verica que la conexión es correcta.';
  } else if (jqXHR.status == 404) {
    msg = 'La página solicitada no se encuentra en el servidor [404]';
  } else if (jqXHR.status == 500) {
    msg = 'Error interno en el servidor [500].';
  } else if (textStatus !== 'undefined' && textStatus === 'parsererror') {
    msg = 'Se ha producido un error al tratar los datos de la petición JSON.';
  } else if (textStatus !== 'undefined' && textStatus == 'timeout') {
    msg = 'Tiempo de espera agotado (timeout).';
  } else if (textStatus !== 'undefined' && textStatus == 'abort') {
    msg = 'La petición Ajax ha sido abortada.';
  } else {
    msg = 'Error desconocido.\n' + jqXHR.responseText;
  }

  console.error(msg);
  console.error('textStatus: ' + textStatus + ' \r\n error:' + error);
}

function overlayExito(overlay) {
  overlay.update({
    icon: "/ptwanda-web/modulos/tramitacionONGD/imagenes/check.png",
    text: "Exito"
  });
  window.setTimeout(function() {
    overlay.hide();
  }, 5e3);
  $(".fondoOverlay").addClass("d-none");
  scrollInicio();
}

function overlayError(overlay) {
  overlay.update({
    icon: "/ptwanda-web/modulos/tramitacionONGD/imagenes/cross.png",
    text: "Error"
  });
  window.setTimeout(function() {
    overlay.hide();
  }, 5e3);
  $(".fondoOverlay").addClass("d-none");
  scrollInicio();
}

function scrollInicio() {
  $('html, body').animate({
    scrollTop: 0
  }, 2000);
}

function compruebaLimite(textarea, limite) {
  var str;
  var navegador = navigator.appName;
  if (navegador == "Microsoft Internet Explorer") {
    str = textarea.value;
    if (str.length > parseInt(limite)) {
      textarea.value = str.substring(0, parseInt(limite));
      alert('Este campo no admite mas de ' + parseInt(limite) + ' caracteres');
    }
  } else {
    str = textarea.value.replace(/\n/g, "\r\n");
    if (str.length > parseInt(limite)) {
      textarea.value = str.substring(0, parseInt(limite));
      alert('Este campo no admite mas de ' + parseInt(limite) + ' caracteres');
    }
  }
}

function convertirFormatoUE(valor, decimals) {
  valor += ''; // por si pasan un numero en vez de un string
  valor = parseFloat(valor.replace(/[^0-9\.]/g, '')); // elimino cualquier cosa que no sea numero o punto

  decimals = decimals || 0; // por si la variable no fue fue pasada

  // si no es un numero o es igual a cero retorno el mismo cero
  if (isNaN(valor) || valor === 0)
    return "0";

  // si es mayor o menor que cero retorno el valor formateado como numero
  valor = '' + (valor.toFixed(decimals)).toLocaleString("es-ES");

  var partes_valor = valor.split('.'),
    regexp = /(\d+)(\d{3})/;

  while (regexp.test(partes_valor[0]))
    partes_valor[0] = partes_valor[0].replace(regexp, '$1' + '.' + '$2');

  return partes_valor.join(',');
}

function stringToFloat(valor) {

  var valor = ('' + valor).replaceAll('.', '');
  valor = valor.replaceAll(',', '.');

  return parseFloat(valor);
}
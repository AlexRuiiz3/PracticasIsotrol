/**
* 
*/
var urlGuardarReformula;
var urlIncorporarNotificacionReformula;

/**
 * Función para inicializar datos de la pestaña
 */
function initPantallaNotificacionReformula() {
  configuracionValidacionesNotificacionReformula();
}

/**
* Funcion que asigna los url de los controladores a las variables globales
* 
* @param urls
* @returns
*/
function defineUrlsControladores(urls) {
  urlGuardarReformula = urls[0];
  urlIncorporarNotificacionReformula = urls[1];
}

/**
 * Funcion para realizar la petición ajax para el guardado de la notificación reformulación.
 */
function createOrUpdateReformulaUniv() {
  if ($("#formReformulaUniv").valid()) {
    var data = $('#formReformulaUniv').serialize();
    var success = function(data) {
      $("#messagesAndErrors").html(data);
      $("#messagesAndErrors").removeClass("d-none");
      overlayExito(window.capaEspera);
    }
    var error = function(data) {
      $("#messagesAndErrors").html(data.responseText);
      $("#messagesAndErrors").removeClass("d-none");
      overlayError(window.capaEspera);
    }
    peticionAjaxPOST(urlGuardarReformula, data, success, error);
  } else {
    $([document.documentElement, document.body]).animate({
      scrollTop: $("form").find('.errorLabel').offset().top
    }, 2000);
  }
}

/**
 * Funcion para realizar la petición ajax para la generación e incorporación del documento de la notificación reformulación.
 */
function incorporarNotificacionReformulaUniv() {
  $("#messagesAndErrors").addClass('d-none');
  var data = $("#formReformulaUniv").serialize();
  var success = function(data) {
    $("#messagesAndErrors").html(data);
    $("#messagesAndErrors").removeClass('d-none');
    overlayExito(window.capaEspera);
  }
  var error = function(data) {
    $("#messagesAndErrors").html(data.responseText);
    $("#messagesAndErrors").removeClass('d-none');
    overlayError(window.capaEspera);
  }
  peticionAjaxPOST(urlIncorporarNotificacionReformula, data, success, error);
}
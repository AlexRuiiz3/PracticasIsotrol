$(function() {
  $('input:checkbox').each(function() {
    var value = this.value;
    var editable = value.substring(value.indexOf('-') + 1);
    this.value = value.substring(0, value.indexOf('-'));
    if (editable === 'false') {
      this.onclick = function() { return false; };
      this.style.cursor = 'not-allowed';
      $('label[for="' + this.id + '"]')[0].style.cursor = 'not-allowed';
    }
  }
  )
});

/**
* 
*/
var urlGuardarExclusiones;
/**
* Funcion que asigna los url de los controladores a las variables globales
* 
* @param urls
* @returns
*/
function defineUrlsControladores(urls) {
  var i = 0;
  urlGuardarExclusiones = urls[i];
}

/**
 * Funcion para realizar la petición ajax para el guardado de las exclusiones seleccionadas.
 */
function createOrUpdateExclusion(idFormulario) {
  $("#messagesAndErrors").addClass('d-none');
  if (comprobarTieneExclusiones()) {
    var data = $("#" + idFormulario).serialize();
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
    peticionAjaxPOST(urlGuardarExclusiones, data, success, error);
  }
}

function comprobarTieneExclusiones() {
  var tieneExclusiones = false;
  $('#listadoExclusiones input:checkbox').each(function() {
    tieneExclusiones = $(this).prop("checked") || tieneExclusiones;
  })
  if ($("#noTieneExclusiones").prop("checked")) {
    $("#noTieneExclusiones").val(true);
  } else {
    $("#noTieneExclusiones").val(false);
  }
  if (tieneExclusiones == $("#noTieneExclusiones").prop("checked")) {
    alert("La comprobación de exclusiones no es correcta");
    return false;
  }
  return true
}
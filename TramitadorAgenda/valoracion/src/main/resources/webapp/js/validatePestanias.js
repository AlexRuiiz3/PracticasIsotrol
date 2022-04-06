/**
 * JavaScript para las validaciones de los campos
 */
function configuracionValidaciones() {
  $.validator.setDefaults({
    errorClass: "errorLabel"
  });

  jQuery.validator.addMethod("exactlength", function(value, element, param) {
    return this.optional(element) || tamainoConSaltoLinea(element, param);
  });

  validacionesPrimeraPestania();
  validacionesSegundaPestania();
  validacionesTerceraPestania();
  validacionesCuartaPestania();
}

function validacionesPrimeraPestania() {
  $("#formDatosPestania1").validate({
    rules: {
      'valoracionSolicitudDTO.pertinencia.observaciones': {
        exactlength: 4000
      },
      'valoracionSolicitudDTO.viabilidad.observaciones': {
        exactlength: 4000
      },
    }
  });
}

function validacionesSegundaPestania() {
  $("#formDatosPestania2").validate({
    rules: {
      'valoracionSolicitudDTO.coherencia.observaciones': {
        exactlength: 4000
      },
      'valoracionSolicitudDTO.conectividad.observaciones': {
        exactlength: 4000
      },
      'valoracionSolicitudDTO.sostenibilidad.observaciones': {
        exactlength: 4000
      },
      'valoracionSolicitudDTO.impacto.observaciones': {
        exactlength: 4000
      }
    }
  });
}

function validacionesTerceraPestania() {
  $("#formDatosPestania3").validate({
    rules: {
      'valoracionSolicitudDTO.convergencia.observaciones': {
        exactlength: 4000
      },
      'valoracionSolicitudDTO.capacidadGestion.observaciones': {
        exactlength: 4000
      }
    }
  });
}

function validacionesCuartaPestania() {
  $("#formDatosPestania4").validate({
    rules: {
      'valoracionSolicitudDTO.valoracionTotal.observaciones': {
        exactlength: 4000
      }
    }
  });
}

function tamainoConSaltoLinea(textarea, limite) {
  var str;
  var navegador = navigator.appName;
  if (navegador == "Microsoft Internet Explorer") {
    str = textarea.value;
    if (str.length > parseInt(limite)) {
      return false;
    }
  } else {
    str = textarea.value.replace(/\n/g, "\r\n");
    if (str.length > parseInt(limite)) {
      return false;
    }
  }
  return true;
}

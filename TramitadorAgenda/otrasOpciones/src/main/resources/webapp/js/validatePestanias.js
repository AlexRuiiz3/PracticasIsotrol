/**
 * JavaScript para las validaciones de los campos
 */
function configuracionValidaciones() {
  $.validator.setDefaults({
    errorClass: "errorLabel"
  });

  $.validator.addMethod("nif", function(value, element) {
    return this.optional(element) || validarNIFNIE(value);
  });

  $.validator.addMethod("beforeToday", function(value, element) {
    return this.optional(element)
      || validaFechasAnterior(value);
  });
  $.validator.addMethod("afterToday", function(value, element) {
    return this.optional(element)
      || validaFechasPosterior(value);
  });
  $.validator.addMethod("dateEU", function(value, element) {
    return this.optional(element)
      || /^(0[1-9]|1\d|2\d|3[01])\/(0[1-9]|1[0-2])\/(19|20)\d{2}$/.test(value);
  });

  validacionesDatosGenerales();
  validacionesSubsanaciones();
}

function validacionesDatosGenerales() {
  $("#formDatosGenerales").validate({
    rules: {
      'datosGeneralesDTO.numExpInterno': {
        maxlength: 19
      },
      'datosGeneralesDTO.fechaEntrada': {
        dateEU: true,
        beforeToday: true
      },
      'datosGeneralesDTO.fechaRegistro': {
        dateEU: true,
        beforeToday: true
      },
      'datosGeneralesDTO.lugarRegistro': {
        maxlength: 50
      },
      'datosGeneralesDTO.fechaPagoSubvencion': {
        dateEU: true,
        beforeToday: true
      },
      'datosGeneralesDTO.pais': {
        maxlength: 72
      },
      'datosGeneralesDTO.apellidosSol': {
        maxlength: 50
      },
      'datosGeneralesDTO.nombreSol': {
        maxlength: 50
      },
      'datosGeneralesDTO.nifSol': {
        nif: true,
        maxlength: 9
      },
      'datosGeneralesDTO.observaciones': {
        maxlength: 4000
      },
      'datosGeneralesDTO.motivoDesestimacion': {
        maxlength: 4000
      },
      'datosGeneralesDTO.plazoEjec': {
        maxlength: 8
      },
      'datosGeneralesDTO.tituloProy': {
        maxlength: 100
      }
    },
    errorPlacement: function(error, element) {
      if (element.attr("name") === "datosGeneralesDTO.fechaEntrada" || element.attr("name") === "datosGeneralesDTO.fechaRegistro" || element.attr("name") === "datosGeneralesDTO.fechaPagoSubvencion" || element.attr("name") === "datosGeneralesDTO.fechaInicioPrevista") {
        aniadirLabelErrorFecha(element, error);
      } else {
        element.after(error);
      }
    }
  });
}

function validacionesSubsanaciones() {
  $("#formDatosSubsanacion").validate({
    rules: {
      'fechaEntrega': {
        dateEU: true,
        beforeToday: true
      },
      'fechaRegistro': {
        dateEU: true,
        beforeToday: true
      }
    },
    errorPlacement: function(error, element) {
      if (element.attr("name") === "fechaEntrega" || element.attr("name") === "fechaRegistro") {
        aniadirLabelErrorFecha(element, error);
      } else {
        element.after(error);
      }
    }
  });
}

/**
 * Funcion para validar un NIF/NIE
 * 
 * @param texto
 * @return
 */
function validarNIFNIE(dni) {
  var numero, letr, letra;
  var expresion_regular_dni = /^[XYZ]?\d{5,8}[A-Z]$/;

  dni = dni.toUpperCase();

  if (expresion_regular_dni.test(dni) === true) {
    numero = dni.substr(0, dni.length - 1);
    numero = numero.replace('X', 0);
    numero = numero.replace('Y', 1);
    numero = numero.replace('Z', 2);
    letr = dni.substr(dni.length - 1, 1);
    numero = numero % 23;
    letra = 'TRWAGMYFPDXBNJZSQVHLCKET';
    letra = letra.substring(numero, numero + 1);
    if (letra != letr) {
      return false;
    } else {
      return true;
    }
  } else {
    return false;
  }
}

/**
 * Comprueba si la fecha introducida es anterior o igual a hoy
 * 
 * @param value
 * @returns
 */
function validaFechasAnterior(fecha) {
  var today = new Date();
  var dateParts = fecha.split("/");
  var dateObject = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]);
  return new Date(dateObject) <= today;
}

/**
 * Comprueba si la fecha introducida es posterior o igual a hoy
 * 
 * @param value
 * @returns
 */
function validaFechasPosterior(fecha) {
  var today = new Date();
  var dateParts = fecha.split("/");
  var dateObject = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]);
  return new Date(dateObject) >= today;
}

/**
 * Método para añadir los mensajes de errores de los campos fecha.
 * @param element, elemento que ha provocado el error
 * @param mensaje, mensaje o label de error especifico.
 * @returns
 */
function aniadirLabelErrorFecha(element, mensaje) {
  if (typeof mensaje === 'string') {
    element.parent().parent().children().last().append('<label for="' + element.attr("id") + '" class="errorLabel" style="display:inline">' + mensaje + '</label>');
  } else {
    element.parent().parent().children().last().append(mensaje);
  }
}
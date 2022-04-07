/**
 * JavaScript para las validaciones de los campos
 */
function configuracionValidaciones() {
  $.validator.setDefaults({
    errorClass: "errorLabel"
  });

  $.validator.addMethod("afterToday", function(value, element) {
    return this.optional(element)
      || validaFechas(value);
  });
  $.validator.addMethod("dateEU", function(value, element) {
    return this.optional(element)
      || /^(0[1-9]|1\d|2\d|3[01])\/(0[1-9]|1[0-2])\/(19|20)\d{2}$/.test(value);
  });

  $.validator.addMethod("rangoFechas", function(value, element) {
    return this.optional(element)
      || validaRangoFechas(value);
  });

  $.validator.addMethod("fechaInicioFinMenor", function(value, element) {
    return this.optional(element)
      || validaFechaIniFin();
  });

  validacionesDatosConvocatoria();
}


function validacionesDatosConvocatoria() {
  $("#formDatosConvocatoria").validate({
    rules: {
      'convocatoriaDTO.titulo': {
        maxlength: 250,
        required: true
      },
      'convocatoriaDTO.descripcion': {
        maxlength: 4000
      },
      'convocatoriaDTO.fhInicio': {
        dateEU: true,
        afterToday: true,
        required: true
      },
      'convocatoriaDTO.fhFin': {
        dateEU: true,
        afterToday: true,
        fechaInicioFinMenor: true,
        required: true
      },
      'convocatoriaDTO.fhInicioValoracion': {
        dateEU: true,
        rangoFechas: true
      },
      'convocatoriaDTO.fhPropResolProv': {
        dateEU: true,
        rangoFechas: true
      },
      'convocatoriaDTO.fhLimitDocResolDef': {
        dateEU: true,
        rangoFechas: true
      },
      'convocatoriaDTO.fhResolConc': {
        dateEU: true,
        rangoFechas: true
      },
      'convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecSol': {
        dateEU: true,
        afterToday: true,
        required: true
      },
      'convocatoriaDTO.convocatoriaFechasDTO.fhFinRecSol': {
        dateEU: true,
        afterToday: true,
        required: true
      },
      'convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecSub': {
        dateEU: true,
        afterToday: true,
        required: true
      },
      'convocatoriaDTO.convocatoriaFechasDTO.fhFinRecSub': {
        dateEU: true,
        afterToday: true,
        required: true
      },
      'convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecAlega': {
        dateEU: true,
        afterToday: true,
        required: true
      },
      'convocatoriaDTO.convocatoriaFechasDTO.fhFinRecAlega': {
        dateEU: true,
        afterToday: true,
        required: true
      },
      'convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecSub2': {
        dateEU: true,
        afterToday: true,
        required: true
      },
      'convocatoriaDTO.convocatoriaFechasDTO.fhFinRecSub2': {
        dateEU: true,
        afterToday: true,
        required: true
      },
      'convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecAARPD': {
        dateEU: true,
        afterToday: true,
        required: true
      },
      'convocatoriaDTO.convocatoriaFechasDTO.fhFinRecAARPD': {
        dateEU: true,
        afterToday: true,
        required: true
      },
      'convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecSubDoc': {
        dateEU: true,
        afterToday: true,
        required: true
      },
      'convocatoriaDTO.convocatoriaFechasDTO.fhFinRecSubDoc': {
        dateEU: true,
        afterToday: true,
        required: true
      },
      'convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecComIni': {
        dateEU: true,
        afterToday: true,
        required: true
      },
      'convocatoriaDTO.convocatoriaFechasDTO.fhFinRecComIni': {
        dateEU: true,
        afterToday: true,
        required: true
      }

    },
    errorPlacement: function(error, element) {
      if (element.attr("name") === "convocatoriaDTO.fhInicio" || element.attr("name") === "convocatoriaDTO.fhFin" || element.attr("name") === "convocatoriaDTO.fhInicioValoracion"
        || element.attr("name") === "convocatoriaDTO.fhPropResolProv" || element.attr("name") === "convocatoriaDTO.fhLimitDocResolDef"
        || element.attr("name") === "convocatoriaDTO.fhResolConc" || element.attr("name") === "convocatoriaDTO.titulo" || element.attr("name") === "convocatoriaDTO.descripcion"
        || element.attr("name") === "convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecSol"
        || element.attr("name") === "convocatoriaDTO.convocatoriaFechasDTO.fhFinRecSol"
        || element.attr("name") === "convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecSub"
        || element.attr("name") === "convocatoriaDTO.convocatoriaFechasDTO.fhFinRecSub"
        || element.attr("name") === "convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecAlega"
        || element.attr("name") === "convocatoriaDTO.convocatoriaFechasDTO.fhFinRecAlega"
        || element.attr("name") === "convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecSub2"
        || element.attr("name") === "convocatoriaDTO.convocatoriaFechasDTO.fhFinRecSub2"
        || element.attr("name") === "convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecAARPD"
        || element.attr("name") === "convocatoriaDTO.convocatoriaFechasDTO.fhFinRecAARPD"
        || element.attr("name") === "convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecSubDoc"
        || element.attr("name") === "convocatoriaDTO.convocatoriaFechasDTO.fhFinRecSubDoc"
        || element.attr("name") === "convocatoriaDTO.convocatoriaFechasDTO.fhInicioRecComIni"
        || element.attr("name") === "convocatoriaDTO.convocatoriaFechasDTO.fhFinRecComIni") {
        aniadirLabelErrorFecha(element, error);
      } else {
        element.after(error);
      }
    }
  });
}


/**
 * Comprueba si la fecha prorroga2 es posterior a la prorroga1
 * 
 * @param value
 * @returns
 */
function validaFechas(fecha) {
  var today = new Date();
  var day = today.getDate();
  var month = today.getMonth();
  var year = today.getFullYear();

  var dateParts = fecha.split("/");

  var dateObject = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]);
  var fechaActual = new Date(year, "0" + month, day);
  return new Date(dateObject).getTime() >= new Date(fechaActual).getTime();
}

/**
 * Comprueba si la fecha está comprendida entre la de inicio y la de fin
 * 
 * @param value
 * @returns
 */
function validaRangoFechas(fecha) {
  var datePartsFecha = fecha.split("/");
  var dateFecha = new Date(+datePartsFecha[2], datePartsFecha[1] - 1, +datePartsFecha[0]);
  var fechaInicio = $('input[name="convocatoriaDTO.fhInicio"]').val();
  var fechaFin = $('input[name="convocatoriaDTO.fhFin"]').val();
  var dateParts = fechaInicio.split("/");
  var dateObjectFechaInicio = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]);
  var dateParts2 = fechaFin.split("/");
  var dateObjectFechaFin = new Date(+dateParts2[2], dateParts2[1] - 1, +dateParts2[0]);

  return new Date(dateObjectFechaInicio).getTime() <= new Date(dateFecha).getTime() && new Date(dateObjectFechaFin).getTime() >= new Date(dateFecha).getTime();
}

/**
 * Comprueba que la fecha de inicio no sea posterior a la de fin
 * 
 * @param value
 * @returns
 */
function validaFechaIniFin() {
  var fechaInicio = $('input[name="convocatoriaDTO.fhInicio"]').val();
  var fechaFin = $('input[name="convocatoriaDTO.fhFin"]').val();
  var dateParts = fechaInicio.split("/");
  var dateObjectFechaInicio = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]);
  var dateParts2 = fechaFin.split("/");
  var dateObjectFechaFin = new Date(+dateParts2[2], dateParts2[1] - 1, +dateParts2[0]);

  return new Date(dateObjectFechaFin).getTime() >= new Date(dateObjectFechaInicio).getTime();
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
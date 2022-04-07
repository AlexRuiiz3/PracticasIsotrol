window.capaEspera;
var mensajeEmptyComun;
var columnsDefsComunes;
var listaValoracionesEditadas = [];
var cumpleCondicionPestania3 = false;
var permiteSumaIncrementos = false;
var totalTotalesPuntuacionTipoCriterio = new BigNumber(0);
var totalPuntuacionMaxima = new BigNumber(0);
var totalTotalesPuntuacion = new BigNumber(0);
var totalesPuntuacionFase1 = new BigNumber(0);
var totalesPuntuacionFase2 = new BigNumber(0);
var valorTotal;

function initParametrizacionComun(nombreColumnasTabla, tamanoColumnasTabla, mensajeEmpty) {
  mensajeEmptyComun = mensajeEmpty;
  columnsDefsComunes = [
    {
      data: "idCriterio",
      className: "hide"
    },
    {
      data: "descripcion",
      title: nombreColumnasTabla[0],
      width: tamanoColumnasTabla[0]
    },
    {
      data: "valoracion",
      render: function(data, type, full) {
        //Se debe persistir de alguna manera el valor calculado en "javascript:actualizarPuntuacion" ya que por pantalla se está poniendo con 3 decimales.
        return '<input  type="string" class="form-control" id=valorCriterio_' + full.idCriterio + ' value=' + data + ' style="text-align: right;" onchange="javascript:actualizarPuntuacion(this,3);"/>';
      },
      className: "centradoVertical text-center",
      title: nombreColumnasTabla[1],
      width: tamanoColumnasTabla[1]
    },
    {
      data: "puntuacion",
      render: function(data, type, full) {
        return convertirFormatoUE(data, 3);
      },
      className: "centradoVertical text-right",
      title: nombreColumnasTabla[2],
      width: tamanoColumnasTabla[2]
    },
    {
      data: "valorMaximo",
      render: function(data, type, full) {
        return convertirFormatoUE(data, 3);
      },
      className: "centradoVertical text-right",
      title: nombreColumnasTabla[3],
      width: tamanoColumnasTabla[3]
    }
  ];
}
/**
  * Método para la construccion del datatable de listado de criterios
  */
function crearDataTable(idTable, columnsDefs, mensajeEmpty, url) {
  $(idTable).dataTable({
    type: "POST",
    ajax: {
      url: url,
      data: {
        'valoracionSolicitudDTO.codigoFinalidad': $("input[name='valoracionSolicitudDTO.codigoFinalidad']").val(),
        'valoracionSolicitudDTO.ongd': $("input[name='valoracionSolicitudDTO.ongd']").val(),
        'valoracionSolicitudDTO.idSolicitud': $("input[name='valoracionSolicitudDTO.idSolicitud']").val()
      },
      dataType: 'json',
      dataSrc: function(json) {
        return json;
      },
      complete: function() {
      }
    },
    error: function(xhr, error, code) {
      $("#messagesAndErrors").html(error.responseText);
      $("#messagesAndErrors").removeClass('d-none');
      scrollInicio();
      overlayError(window.capaEspera);
    },
    ordering: false,
    searching: false,
    paging: false,
    autoWidth: false,
    info: false,
    columns: columnsDefs,
    bDestroy: true,
    stateSave: true,
    stateDuration: -1,
    language: {
      emptyTable: mensajeEmpty,
      infoEmpty: "Sin registros que mostrar",
      loadingRecords: "Cargando...",
    },
    footerCallback: function(ow, data, start, end, display) {
      var api = this.api(), data;
      // Remove the formatting to get integer data for summation
      var intVal = function(i) {
        return typeof i === 'string' ?
          stringToBigNumber(i) :
          typeof i === 'number' ?
            i : 0;
      };

      totalPuntuacion = api
        .column(3)
        .data()
        .reduce(function(a, b) {
          return intVal(a) + intVal(b);
        }, 0);

      totalMaxValoracion = api
        .column(4)
        .data()
        .reduce(function(a, b) {
          return intVal(a) + intVal(b);
        }, 0);
      seteaValorRealATipoCriterio(ow.parentNode.parentNode, totalPuntuacion)

      // Actualizamos totales
      $('#totalPuntuacionOculto').val(totalPuntuacion);
      totalPuntuacion = convertirFormatoUE(totalPuntuacion, 3);
      totalMaxValoracion = convertirFormatoUE(totalMaxValoracion, 3);
      if ("tablaListadoIncremento" === ow.parentNode.parentNode.id) {
        if (totalPuntuacion > convertirFormatoUE(maximoPuntuacionIncremento, 3)) {
          totalPuntuacion = maximoPuntuacionIncremento;
        }
        $(api.column(3).footer()).html(totalPuntuacion);
        $(api.column(4).footer()).html(maximoPuntuacionIncremento);
      } else {
        $(api.column(3).footer()).html(totalPuntuacion);
        $(api.column(4).footer()).html(totalMaxValoracion);
      }
      cargarTablaFinal(ow.parentNode.parentNode.id, totalPuntuacion, totalMaxValoracion);
    }
  });
}

function actualizarPuntuacion(campo, valorMax) {
  var valor = stringToBigNumber($(campo).val());
  if (valor.isNaN() || valor.e !== 0) {
    $(campo).addClass("errorLabel");
    alert("El valor introducido no es un entero");
  } else if (valor.gte(0) && valor.lte(valorMax)) {
    $(campo).removeClass("errorLabel");
    var celdaValor = $(campo).parent();
    //IMPORTANTE:  Este valor 'prev' debe coger el valor exacto, y no el que tiene por pantalla 'celdaValor.next().text()' para ello:
    //se debe almacenar en algun lugar accesible el valor calculado tras cambiar el valor del input 'Valoracion', que es unico para cada criterio.
    var prev = stringToBigNumber(celdaValor.next().text());
    var footer = celdaValor.parent().parent().next();

    //totalPuntuacion es lo que se muestra por pantalla, solo con 3 decimales
    var totalPuntuacion = footer.children().children()[3];
    //totalPuntuacionVal es el valor con el que se hacen los calculos, es decir, el valor con todos los decimales
    var totalPuntuacionVal = recogeValoresExactos(celdaValor.parent().parent().parent()[0]);
    var puntuacion = valor.dividedBy(valorMax).multipliedBy(stringToBigNumber(celdaValor.next().next()[0].innerText));

    var diferencia = puntuacion.minus(prev);
    var aux = totalPuntuacionVal.plus(diferencia);
    var nuevoTotalExacto;

    nuevoTotalExacto = aux.isEqualTo(0) ? 0 : aux;
    //Este es el valor mostrado por pantalla, no sirve para hacer los calculos
    totalPuntuacion.innerText = convertirFormatoUE(nuevoTotalExacto, 3);
    //(1) Si se deshabilita esto, en el total de la tabla del criterio, aparecera el numero real sin formatear
    //totalPuntuacion.innerText=convertirFormatoUEAllDecimals(nuevoTotalExacto);

    //Este es el valor con decimales, es el valido para hacer los calculos
    totalPuntuacionVal = convertirFormatoUEAllDecimals(nuevoTotalExacto);
    //En esta funcion se almacenan los valores con decimales (valor exacto) en los input ocultos para hacer el calculo final
    seteaValorRealATipoCriterio(celdaValor.parent().parent().parent()[0], nuevoTotalExacto);

    //En esta funcion unicamente se estan añadiendo los volores formateados a la tabla final,
    cargarTablaFinal(celdaValor.parent().parent().parent()[0].id, totalPuntuacion.innerText, null);

    celdaValor.next()[0].innerText = stringToBigNumber(puntuacion).eq(0) ? 0 : convertirFormatoUE(puntuacion, 3);
    //(2) Si se deshabilita esto, en la puntuacion de cada criterio aparecera el valor total sin formatear
    //celdaValor.next()[0].innerText = stringToBigNumber(puntuacion).toFixed(3) == 0 ? 0 : convertirFormatoUEAllDecimals(puntuacion);
    aniadirAlistaValoracionesEditadas(parseInt(celdaValor.prev().prev().text()), valor, puntuacion);
  } else {
    $(campo).addClass("errorLabel");
    alert("El valor introducido debe estar comprendido entre 0 y " + valorMax + " ambos inclusive");
  }
}

function recogeValoresExactos(tabla) {
  var tipoCriterio = tabla.id;

  if (tipoCriterio == 'tablaListadoPertinencia') {
    return stringToBigNumber($('#totalPuntuacionPertinenciaOculto').val());
  }
  if (tipoCriterio == 'tablaListadoViabilidad') {
    return stringToBigNumber($('#totalPuntuacionViabilidadOculto').val());
  }
  if (tipoCriterio == 'tablaListadoCoherencia') {
    return stringToBigNumber($('#totalPuntuacionCoherenciaOculto').val());
  }
  if (tipoCriterio == 'tablaListadoConectividad') {
    return stringToBigNumber($('#totalPuntuacionConectividadOculto').val());
  }
  if (tipoCriterio == 'tablaListadoSostenibilidad') {
    return stringToBigNumber($('#totalPuntuacionSostenibilidadOculto').val());
  }
  if (tipoCriterio == 'tablaListadoImpacto') {
    return stringToBigNumber($('#totalPuntuacionImpactoOculto').val());
  }

  if (tipoCriterio == 'tablaListadoConvergencia') {
    return stringToBigNumber($('#totalPuntuacionConvergenciaOculto').val());
  }

  if (tipoCriterio == 'tablaListadoImpacto') {
    return stringToBigNumber($('#totalPuntuacionIncrementoOculto').val());
  }
}



function seteaValorRealATipoCriterio(tabla, valorTotal) {
  var idTabla = tabla.id;
  valorTotal = convertirFormatoUEAllDecimals(valorTotal);
  if (idTabla == 'tablaListadoPertinencia') {
    $('#totalPuntuacionPertinenciaOculto').val(valorTotal);
  }
  if (idTabla == 'tablaListadoViabilidad') {
    $('#totalPuntuacionViabilidadOculto').val(valorTotal);
  }
  if (idTabla == 'tablaListadoCoherencia') {
    $('#totalPuntuacionCoherenciaOculto').val(valorTotal);
  }
  if (idTabla == 'tablaListadoConectividad') {
    $('#totalPuntuacionConectividadOculto').val(valorTotal);
  }
  if (idTabla == 'tablaListadoSostenibilidad') {
    $('#totalPuntuacionSostenibilidadOculto').val(valorTotal);
  }
  if (idTabla == 'tablaListadoImpacto') {
    $('#totalPuntuacionImpactoOculto').val(valorTotal);
  }
  if (idTabla == 'tablaListadoConvergencia') {
    $('#totalPuntuacionConvergenciaOculto').val(valorTotal);
  }
  if (idTabla == 'tablaListadoCapGestion') {
    $('#totalPuntuacionCapGestionOculto').val(valorTotal);
  }
}

function aniadirAlistaValoracionesEditadas(id, valor, puntuacion) {
  var encontrado = false;
  $.each(listaValoracionesEditadas, function(i, elemento) {
    if (elemento.idCriterio == id) {
      elemento.valoracion = valor;
      elemento.puntuacion = puntuacion;
      encontrado = true;
    }
  });
  if (!encontrado) {
    listaValoracionesEditadas.push({ idCriterio: id, valoracion: valor, puntuacion: puntuacion });
  }
}

function prepararData(data) {
  data.push({ name: "valoracionSolicitudDTO.codigoFinalidad", value: $("input[name='valoracionSolicitudDTO.codigoFinalidad']").val() });
  data.push({ name: "valoracionSolicitudDTO.idSolicitud", value: $("input[name='valoracionSolicitudDTO.idSolicitud']").val() });
  data.push({ name: "valoracionSolicitudDTO.ongd", value: $("input[name='valoracionSolicitudDTO.ongd']").val() });
  //  Datos de la pestaña de totales
  data.push({ name: "valoracionSolicitudDTO.pertinencia.totalPuntuacion", value: stringToBigNumber($("#totalPuntuacionPertinencia").html()) });
  data.push({ name: "valoracionSolicitudDTO.pertinencia.puntuacionMaxima", value: stringToBigNumber($("#puntuacionMaximaPertinencia").html()) });
  data.push({ name: "valoracionSolicitudDTO.viabilidad.totalPuntuacion", value: stringToBigNumber($("#totalPuntuacionViabilidad").html()) });
  data.push({ name: "valoracionSolicitudDTO.viabilidad.puntuacionMaxima", value: stringToBigNumber($("#puntuacionMaximaViabilidad").html()) });
  data.push({ name: "valoracionSolicitudDTO.coherencia.totalPuntuacion", value: stringToBigNumber($("#totalPuntuacionCoherencia").html()) });
  data.push({ name: "valoracionSolicitudDTO.coherencia.puntuacionMaxima", value: stringToBigNumber($("#puntuacionMaximaCoherencia").html()) });
  if ('AH' === $("input[name='valoracionSolicitudDTO.codigoFinalidad']").val()) {
    data.push({ name: "valoracionSolicitudDTO.conectividad.totalPuntuacion", value: stringToBigNumber($("#totalPuntuacionConectividad").html()) });
    data.push({ name: "valoracionSolicitudDTO.conectividad.puntuacionMaxima", value: stringToBigNumber($("#puntuacionMaximaConectividad").html()) });
  }
  data.push({ name: "valoracionSolicitudDTO.sostenibilidad.totalPuntuacion", value: stringToBigNumber($("#totalPuntuacionSostenibilidad").html()) });
  data.push({ name: "valoracionSolicitudDTO.sostenibilidad.puntuacionMaxima", value: stringToBigNumber($("#puntuacionMaximaSostenibilidad").html()) });
  data.push({ name: "valoracionSolicitudDTO.impacto.totalPuntuacion", value: stringToBigNumber($("#totalPuntuacionImpacto").html()) });
  data.push({ name: "valoracionSolicitudDTO.impacto.puntuacionMaxima", value: stringToBigNumber($("#puntuacionMaximaImpacto").html()) });
  if (esONGD) {
    if ('C' == $("input[name='valoracionSolicitudDTO.codigoFinalidad']").val()) {
      data.push({ name: "valoracionSolicitudDTO.convergencia.totalPuntuacion", value: stringToBigNumber($("#totalPuntuacionConvergencia").html()) });
      data.push({ name: "valoracionSolicitudDTO.convergencia.puntuacionMaxima", value: stringToBigNumber($("#puntuacionMaximaConvergencia").html()) });
      //criterios de convergencia
      listaValoracionesEditadas.push({ idCriterio: parseInt($($($($("#tablaListadoConvergencia").children()[1]).children()[$($("#tablaListadoConvergencia").children()[1]).children().length - 1]).children()[0]).text()), valoracion: 0, puntuacion: stringToBigNumber($($($($("#tablaListadoConvergencia").children()[1]).children()[$($("#tablaListadoConvergencia").children()[1]).children().length - 1]).children()[3]).text()) });
    }
    data.push({ name: "valoracionSolicitudDTO.capacidadGestion.totalPuntuacion", value: stringToBigNumber($("#totalPuntuacionCapGestion").html()) });
    data.push({ name: "valoracionSolicitudDTO.capacidadGestion.puntuacionMaxima", value: stringToBigNumber($("#puntuacionMaximaCapGestion").html()) });
    data.push({ name: "valoracionSolicitudDTO.incrementos.totalPuntuacion", value: stringToBigNumber($("#totalPuntuacionIncremento").html()) });
    //criterios de capacidad de gestión
    listaValoracionesEditadas.push({ idCriterio: parseInt($($($($("#tablaListadoCapGestion").children()[1]).children()[$($("#tablaListadoCapGestion").children()[1]).children().length - 1]).children()[0]).text()), valoracion: 0, puntuacion: stringToBigNumber($($($($("#tablaListadoCapGestion").children()[1]).children()[$($("#tablaListadoCapGestion").children()[1]).children().length - 1]).children()[3]).text()) });
  }
  data.push({ name: "valoracionSolicitudDTO.valoracionTotal.totalPuntuacion", value: stringToBigNumber($("#totalPuntuacionSuma").html()) });
  data.push({ name: "valoracionSolicitudDTO.mostrarPestania3", value: cumpleCondicionPestania3 });
  data.push({ name: "valoracionSolicitudDTO.permiteSumaIncrementos", value: permiteSumaIncrementos });

  $.each(listaValoracionesEditadas, function(i, elemento) {
    data.push({ name: "listaValoracionesEditadas[" + i + "][idCriterio]", value: elemento.idCriterio });
    data.push({ name: "listaValoracionesEditadas[" + i + "][valoracion]", value: elemento.valoracion });
    data.push({ name: "listaValoracionesEditadas[" + i + "][puntuacion]", value: elemento.puntuacion });
  });
}

function comprobarErrroes() {
  if ((esONGD && $("#formDatosPestania1").valid() && $("#formDatosPestania2").valid() && $("#formDatosPestania3").valid() && $("#formDatosPestania4").valid())
    || (!esONGD && $("#formDatosPestania1").valid() && $("#formDatosPestania2").valid() && $("#formDatosPestania4").valid())) {
    return $("input .errorLabel").length === 0;
  } else {
    return false;
  }
}

/**Guardamos las valoraciones realizadas */
function guardarCriteriosSolicitud() {
  recargarPestanaValoracionTotal();
  $("#messagesAndErrors").addClass('d-none');
  if (comprobarErrroes()) {
    var data = $("#formDatosPestania1").serializeArray();
    $.each($("#formDatosPestania2").serializeArray(), function(i, elemento) {
      data.push(elemento);
    });
    $.each($("#formDatosPestania3").serializeArray(), function(i, elemento) {
      data.push(elemento);
    });
    $.each($("#formDatosPestania4").serializeArray(), function(i, elemento) {
      data.push(elemento);
    });
    prepararData(data);
    var success = function(data) {
      $("#messagesAndErrors").html(data);
      $("#messagesAndErrors").removeClass('d-none');
      listaValoracionesEditadas = [];
      scrollInicio();
      overlayExito(window.capaEspera);
    };
    var error = function(data) {
      $("#messagesAndErrors").html(data.responseText);
      $("#messagesAndErrors").removeClass('d-none');
      scrollInicio();
      overlayError(window.capaEspera);
    };
    return peticionAjaxPOST(urlActionGuardarValoracion, data, success, error);
  } else {
    existenErrores();
  }
}
function existenErrores() {
  alert("Corregir los errores antes de guardar");
  $([document.documentElement, document.body]).animate({
    scrollTop: $("form").find('.errorLabel').offset().top
  }, 2000);
}

function comprobarPermisosPestaña3() {
  totalTotalesPuntuacion = new BigNumber(0);
  totalesPuntuacionFase1 = new BigNumber(0);
  totalPuntuacionMaxima = new BigNumber(0);

  valorTotal = stringToBigNumber($('#totalPuntuacionPertinenciaOculto').val());

  if (!valorTotal.isNaN()) {
    totalTotalesPuntuacion = totalTotalesPuntuacion.plus(valorTotal);
    totalesPuntuacionFase1 = totalesPuntuacionFase1.plus(valorTotal);
    totalTotalesPuntuacionTipoCriterio = valorTotal;
  }
  valorTotal = stringToBigNumber($('#puntuacionMaximaPertinencia').html());
  if (!valorTotal.isNaN()) {
    totalPuntuacionMaxima = totalPuntuacionMaxima.plus(valorTotal);
    cumpleCondicionPestania3 = totalTotalesPuntuacionTipoCriterio.gte(valorTotal.dividedBy(2));
  }

  valorTotal = stringToBigNumber($('#totalPuntuacionViabilidadOculto').val());

  if (!valorTotal.isNaN()) {
    totalTotalesPuntuacion = totalTotalesPuntuacion.plus(valorTotal);
    totalesPuntuacionFase1 = totalesPuntuacionFase1.plus(valorTotal);
    totalTotalesPuntuacionTipoCriterio = valorTotal;
  }
  valorTotal = stringToBigNumber($('#puntuacionMaximaViabilidad').html());
  if (!valorTotal.isNaN()) {
    totalPuntuacionMaxima = totalPuntuacionMaxima.plus(valorTotal);
    cumpleCondicionPestania3 = cumpleCondicionPestania3 && totalTotalesPuntuacionTipoCriterio.gte(valorTotal.dividedBy(2));
  }

  valorTotal = stringToBigNumber($('#totalPuntuacionCoherenciaOculto').val());

  if (!valorTotal.isNaN()) {
    totalTotalesPuntuacion = totalTotalesPuntuacion.plus(valorTotal);
    totalesPuntuacionFase1 = totalesPuntuacionFase1.plus(valorTotal);
    totalTotalesPuntuacionTipoCriterio = valorTotal;
  }
  valorTotal = stringToBigNumber($('#puntuacionMaximaCoherencia').html());
  if (!valorTotal.isNaN()) {
    totalPuntuacionMaxima = totalPuntuacionMaxima.plus(valorTotal);
    cumpleCondicionPestania3 = cumpleCondicionPestania3 && totalTotalesPuntuacionTipoCriterio.gte(valorTotal.dividedBy(2));
  }

  valorTotal = stringToBigNumber($('#totalPuntuacionConectividadOculto').val());

  if (!valorTotal.isNaN()) {
    totalTotalesPuntuacion = totalTotalesPuntuacion.plus(valorTotal)
    totalesPuntuacionFase1 = totalesPuntuacionFase1.plus(valorTotal);
    totalTotalesPuntuacionTipoCriterio = valorTotal;
  }
  valorTotal = stringToBigNumber($('#puntuacionMaximaConectividad').html());
  if (!valorTotal.isNaN()) {
    totalPuntuacionMaxima = totalPuntuacionMaxima.plus(valorTotal);
    cumpleCondicionPestania3 = cumpleCondicionPestania3 && totalTotalesPuntuacionTipoCriterio.gte(valorTotal.dividedBy(2));
  }

  valorTotal = stringToBigNumber($('#totalPuntuacionSostenibilidadOculto').val());

  if (!valorTotal.isNaN()) {
    totalTotalesPuntuacion = totalTotalesPuntuacion.plus(valorTotal);
    totalesPuntuacionFase1 = totalesPuntuacionFase1.plus(valorTotal);
    totalTotalesPuntuacionTipoCriterio = valorTotal;
  }
  valorTotal = stringToBigNumber($('#puntuacionMaximaSostenibilidad').html());
  if (!valorTotal.isNaN()) {
    totalPuntuacionMaxima = totalPuntuacionMaxima.plus(valorTotal);
    cumpleCondicionPestania3 = cumpleCondicionPestania3 && totalTotalesPuntuacionTipoCriterio.gte(valorTotal.dividedBy(2));
  }

  valorTotal = stringToBigNumber($('#totalPuntuacionImpactoOculto').val());

  if (!valorTotal.isNaN()) {
    totalTotalesPuntuacion = totalTotalesPuntuacion.plus(valorTotal);
    totalesPuntuacionFase1 = totalesPuntuacionFase1.plus(valorTotal);
    totalTotalesPuntuacionTipoCriterio = valorTotal;
  }
  totalesPuntuacionFase1 = convertirFormatoUEAllDecimals(totalesPuntuacionFase1);
  $('#totalPuntuacionFase1Oculto').val(totalesPuntuacionFase1);

  valorTotal = stringToBigNumber($('#puntuacionMaximaImpacto').html());
  if (!valorTotal.isNaN()) {
    totalPuntuacionMaxima = totalPuntuacionMaxima.plus(valorTotal);
    cumpleCondicionPestania3 = cumpleCondicionPestania3 && totalTotalesPuntuacionTipoCriterio.gte(valorTotal.dividedBy(2));
  }

  if (!esONGD) {
    cumpleCondicionPestania3 = false;
  }
}

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
    icon: "/ptwanda-web/modulos/valoracion/imagenes/check.png",
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
    icon: "/ptwanda-web/modulos/valoracion/imagenes/cross.png",
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

function convertirFormatoUEAllDecimals(valor) {
  valor += ''; // por si pasan un numero en vez de un string
  valor = parseFloat(valor.replace(/[^0-9\.]/g, '')) // elimino cualquier cosa que no sea numero o punto
  // si no es un numero o es igual a cero retorno el mismo cero
  if (isNaN(valor) || valor === 0)
    return "0";
  // si es mayor o menor que cero retorno el valor formateado como numero
  valor = valor.toFixed(14);
  valor += '';
  var partes_valor = valor.split('.'),
    regexp = /(\d+)(\d{3})/;
  while (regexp.test(partes_valor[0]))
    partes_valor[0] = partes_valor[0].replace(regexp, '$1' + '.' + '$2');
  return partes_valor.join(',');
}

function stringToBigNumber(valor) {

  var valor = ('' + valor).replaceAll('.', '');
  valor = valor.replaceAll(',', '.');

  return new BigNumber(valor);
}

function informeValoracion() {
  $("#messagesAndErrors").addClass('d-none');
  var data = {
    'valoracionSolicitudDTO.codigoFinalidad': $("input[name='valoracionSolicitudDTO.codigoFinalidad']").val(),
    'valoracionSolicitudDTO.idSolicitud': $("input[name='valoracionSolicitudDTO.idSolicitud']").val(),
    'valoracionSolicitudDTO.ongd': $("input[name='valoracionSolicitudDTO.ongd']").val()
  };
  var success = function(data) {
    $("#messagesAndErrors").html(data);
    $("#messagesAndErrors").removeClass('d-none');
    scrollInicio();
    overlayExito(window.capaEspera);
    opener.recargaPadre([['capa_doc_asoc', '../modulos/docsAsociadosExpediente/listarDocumentos.action', ''],
    ['ayudaTramitacion', '../modulos/ayudaTramitacion/listarTransicionesExpediente.action', ''],
    ['capa_tramita', '../modulos/tramitacion/listarTransicionesExpediente.action', ''],
    ['capa_doc_perm', '../modulos/bloquesPermitidos/listarBloquesPermitidos.action', '']]);
  };
  var error = function(data) {
    $("#messagesAndErrors").html(data.responseText);
    $("#messagesAndErrors").removeClass('d-none');
    scrollInicio();
    overlayError(window.capaEspera);
  };
  return peticionAjaxPOST(urlActionInformeValoracion, data, success, error);
}

function informeEvaluacion() {
  $("#messagesAndErrors").addClass('d-none');
  var data = {
    'valoracionSolicitudDTO.codigoFinalidad': $("input[name='valoracionSolicitudDTO.codigoFinalidad']").val(),
    'valoracionSolicitudDTO.idSolicitud': $("input[name='valoracionSolicitudDTO.idSolicitud']").val(),
    'valoracionSolicitudDTO.ongd': $("input[name='valoracionSolicitudDTO.ongd']").val()
  };
  var success = function(data) {
    $("#messagesAndErrors").html(data);
    $("#messagesAndErrors").removeClass('d-none');
    scrollInicio();
    overlayExito(window.capaEspera);
    opener.recargaPadre([['capa_doc_asoc', '../modulos/docsAsociadosExpediente/listarDocumentos.action', ''],
    ['ayudaTramitacion', '../modulos/ayudaTramitacion/listarTransicionesExpediente.action', ''],
    ['capa_tramita', '../modulos/tramitacion/listarTransicionesExpediente.action', ''],
    ['capa_doc_perm', '../modulos/bloquesPermitidos/listarBloquesPermitidos.action', '']]);
  };
  var error = function(data) {
    $("#messagesAndErrors").html(data.responseText);
    $("#messagesAndErrors").removeClass('d-none');
    scrollInicio();
    overlayError(window.capaEspera);
  };
  return peticionAjaxPOST(urlActionInformeEvaluacion, data, success, error);
}
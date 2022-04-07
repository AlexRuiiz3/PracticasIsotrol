/**
 * Función para inicializar datos de la pestaña
 */
var mensajeEmptyComun;
var columnsDefsIncremento;
var totalPuntuacionIncrementoSinLimite = 0;

function initPestanaIncrementos() {
  crearDataTable(window.tablaIncrementos, columnsDefsIncremento, mensajeEmptyComun, window.urlActionListadoIncrementos);
  permiteSumaIncrementos = $("input[name='valoracionSolicitudDTO.permiteSumaIncrementos']").val() === "true";
}
 
function initParametrizacionIncrementos(nombreColumnasTabla, tamanoColumnasTabla) {
  columnsDefsIncremento = [
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
        return construyeSelect(full);
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
      className: "hide",
      title: nombreColumnasTabla[3],
      width: tamanoColumnasTabla[3]
    }
  ];
}

function construyeSelect(full) {
  var select = '<select id=valorCriterio_' + full.idCriterio + ' style="text-align: right;" onchange="javascript:actualizarPuntuacionIncremento(this);">';
  if (full.valoracion == 1) {
    select = select + '<option value="Si" selected="selected">Si</option>'
      + '<option value="No" >No</option>'
  } else {
    select = select + '<option value="Si">Si</option>'
      + '<option value="No" selected="selected">No</option>';
  }
  select = select + '</select>';
  return select;
}

function recargarPestanaValoracionTotal() {
  totalPuntuacionMaxima = new BigNumber(0);
  totalesPuntuacionFase2 = new BigNumber(0);

  comprobarPermisosPestaña3();

  if (cumpleCondicionPestania3) {
    if ("C" == $("input[name='valoracionSolicitudDTO.codigoFinalidad']").val()) {
      cargarTablaFinal('tablaListadoConvergencia', $("#tablaListadoConvergencia tfoot").children().children()[3].innerText)
    }
    cargarTablaFinal('tablaListadoCapGestion', $("#tablaListadoCapGestion tfoot").children().children()[3].innerText)
  }

  totalesPuntuacionFase1=stringToBigNumber(totalesPuntuacionFase1);
  $('#totalPuntuacionFase1Oculto').val(convertirFormatoUEAllDecimals(totalesPuntuacionFase1));
  $('#totalPuntuacionFase1').html(convertirFormatoUE(totalesPuntuacionFase1, 3));
  permiteSumaIncrementos = cumpleCondicionPestania3 && totalesPuntuacionFase1.gte(60);

  if (window.esONGD) {
      valorTotal = stringToBigNumber($('#totalPuntuacionConvergenciaOculto').val());
    //valorTotal = stringToBigNumber($('#totalPuntuacionConvergencia').html());
    if (!isNaN(valorTotal)&& valorTotal>0) {
      totalTotalesPuntuacion = totalTotalesPuntuacion.plus(valorTotal);
      totalesPuntuacionFase2 = totalesPuntuacionFase2.plus(valorTotal);
    }
    valorTotal = stringToBigNumber($('#puntuacionMaximaConvergencia').html());
    if (!isNaN(valorTotal)&& valorTotal>0) {
      totalPuntuacionMaxima = totalPuntuacionMaxima.plus(valorTotal);
    }

    valorTotal =  stringToBigNumber($('#totalPuntuacionCapGestionOculto').val());
    if (!isNaN(valorTotal)&& valorTotal>0) {
      totalTotalesPuntuacion = totalTotalesPuntuacion.plus(valorTotal);
      totalesPuntuacionFase2 = totalesPuntuacionFase2.plus(valorTotal);
    }
 	permiteSumaIncrementos = cumpleCondicionPestania3 && totalesPuntuacionFase1.plus(totalesPuntuacionFase2).gte(60);

    valorTotal = stringToBigNumber($('#puntuacionMaximaCapGestion').html());
    if (!isNaN(valorTotal)) {
      totalPuntuacionMaxima = totalPuntuacionMaxima.plus(valorTotal);
    }

    recargarIncrementos();
    recalcularTotalIncrementos();
    var totalIncremento= $('#totalPuntuacionIncrementoOculto').val();
    if (permiteSumaIncrementos) {
      var valorTotalInc =totalIncremento;
      totalTotalesPuntuacion = totalTotalesPuntuacion.plus(stringToBigNumber(valorTotalInc));
      $('#tablaListadoIncremento select').attr('disabled', false);
      $("input[name='valoracionSolicitudDTO.permiteSumaIncrementos']").val(true);
      $("#idNoPermiteSumaIncremento").hide();
    } else {
      $('select').val("No");
      $('select').each(function(index,element) {
        actualizarPuntuacionIncremento($(element));
      });
      $('#tablaListadoIncremento select').attr('disabled', true);
      $("input[name='valoracionSolicitudDTO.permiteSumaIncrementos']").val(false);
      $("#idNoPermiteSumaIncremento").show();
    }

  }
  $('#totalPuntuacionFase2Oculto').val(convertirFormatoUEAllDecimals(totalesPuntuacionFase2));
  $('#totalPuntuacionFase2').html(convertirFormatoUE(totalesPuntuacionFase2, 3));
  $('#totalPuntuacionFase12Oculto').val(convertirFormatoUEAllDecimals(totalesPuntuacionFase1.plus(totalesPuntuacionFase2)));
  $('#totalPuntuacionFase1Fase2').html(convertirFormatoUE(totalesPuntuacionFase1.plus(totalesPuntuacionFase2), 3));
  $('#totalPuntuacionSuma').html(convertirFormatoUE(totalTotalesPuntuacion, 3));

  var cumplen = cumplenTodas();

   if (totalTotalesPuntuacion.isLessThan(55) || (!cumpleCondicionPestania3 && window.esONGD) || !cumplen) {
      $('#totalPuntuacionSuma').addClass("text-danger");
    } else {
      $('#totalPuntuacionSuma').removeClass("text-danger");
    }
  $('#puntuacionMaximaSuma').html(convertirFormatoUE(totalPuntuacionMaxima, 3));
}

function cumplenTodas (){
  var cumplenTodas = true;

  if (stringToBigNumber($('#totalPuntuacionPertinencia').html()).isLessThan(stringToBigNumber($('#puntuacionMaximaPertinencia').html()).dividedBy(2))){
    cumplenTodas = false;
    $('#totalPuntuacionPertinencia').addClass("text-danger");
  }else{
    $('#totalPuntuacionPertinencia').removeClass("text-danger");
  }

  if (stringToBigNumber($('#totalPuntuacionViabilidad').html()).isLessThan(stringToBigNumber($('#puntuacionMaximaViabilidad').html()).dividedBy(2))){
    cumplenTodas = false;
    $('#totalPuntuacionViabilidad').addClass("text-danger");
  }else{
    $('#totalPuntuacionViabilidad').removeClass("text-danger");
  }

  if (stringToBigNumber($('#totalPuntuacionCoherencia').html()).isLessThan(stringToBigNumber($('#puntuacionMaximaCoherencia').html()).dividedBy(2))){
    cumplenTodas = false;
    $('#totalPuntuacionCoherencia').addClass("text-danger");
  }else{
    $('#totalPuntuacionCoherencia').removeClass("text-danger");
  }

  if (stringToBigNumber($('#totalPuntuacionConectividad').html()).isLessThan(stringToBigNumber($('#puntuacionMaximaConectividad').html()).dividedBy(2))){
    cumplenTodas = false;
    $('#totalPuntuacionConectividad').addClass("text-danger");
  }else{
    $('#totalPuntuacionConectividad').removeClass("text-danger");
  }

  if (stringToBigNumber($('#totalPuntuacionSostenibilidad').html()).isLessThan(stringToBigNumber($('#puntuacionMaximaSostenibilidad').html()).dividedBy(2))){
    cumplenTodas = false;
    $('#totalPuntuacionSostenibilidad').addClass("text-danger");
  }else{
    $('#totalPuntuacionSostenibilidad').removeClass("text-danger");
  }

  if (stringToBigNumber($('#totalPuntuacionImpacto').html()).isLessThan(stringToBigNumber($('#puntuacionMaximaImpacto').html()).dividedBy(2))){
    cumplenTodas = false;
    $('#totalPuntuacionImpacto').addClass("text-danger");
  }else{
    $('#totalPuntuacionImpacto').removeClass("text-danger");
  }

  return cumplenTodas;
}

function cargarTablaFinal(tipoCriterio, totalPuntuacion, totalMaxValoracion) {
  if (tipoCriterio == 'tablaListadoPertinencia') {
    $('#totalPuntuacionPertinencia').html(totalPuntuacion);
    $('#totalPuntuacionPertinenciaOculto').html(stringToBigNumber(totalPuntuacion));
    
    if (totalMaxValoracion !== null) {
      $('#puntuacionMaximaPertinencia').html(totalMaxValoracion);
    }
  }
  if (tipoCriterio == 'tablaListadoViabilidad') {
    $('#totalPuntuacionViabilidad').html(totalPuntuacion);
        $('#totalPuntuacionViabilidadOculto').html(stringToBigNumber(totalPuntuacion));
    
    if (totalMaxValoracion !== null) {
      $('#puntuacionMaximaViabilidad').html(totalMaxValoracion);
    }
  }
  if (tipoCriterio == 'tablaListadoCoherencia') {
    $('#totalPuntuacionCoherencia').html(totalPuntuacion);
    $('#totalPuntuacionCoherenciaOculto').html(stringToBigNumber(totalPuntuacion));
    if (totalMaxValoracion !== null) {
      $('#puntuacionMaximaCoherencia').html(totalMaxValoracion);
    }
  }
  if (tipoCriterio == 'tablaListadoConectividad') {
    $('#totalPuntuacionConectividad').html(totalPuntuacion);
     $('#totalPuntuacionCoherenciaOculto').html(stringToBigNumber(totalPuntuacion));
    if (totalMaxValoracion !== null) {
      $('#puntuacionMaximaConectividad').html(totalMaxValoracion);
    }
  }
  if (tipoCriterio == 'tablaListadoSostenibilidad') {
    $('#totalPuntuacionSostenibilidad').html(totalPuntuacion);
    $('#totalPuntuacionSostenibilidadOculto').html(stringToBigNumber(totalPuntuacion));
    if (totalMaxValoracion !== null) {
      $('#puntuacionMaximaSostenibilidad').html(totalMaxValoracion);
    }
  }
  if (tipoCriterio == 'tablaListadoImpacto') {
    $('#totalPuntuacionImpacto').html(totalPuntuacion);
    $('#totalPuntuacionImpactoOculto').html(stringToBigNumber(totalPuntuacion));
    if (totalMaxValoracion !== null) {
      $('#puntuacionMaximaImpacto').html(totalMaxValoracion);
    }
  }

  if (tipoCriterio == 'tablaListadoConvergencia') {
    if (cumpleCondicionPestania3) {
      $('#totalPuntuacionConvergencia').html(totalPuntuacion);
      $('#totalPuntuacionConvergenciaOculto').html(stringToBigNumber(totalPuntuacion));
    } else {
      $('#totalPuntuacionConvergencia').html(0);
    }
    if (totalMaxValoracion !== null) {
      $('#puntuacionMaximaConvergencia').html(totalMaxValoracion);
    }
  }
  if (tipoCriterio == 'tablaListadoCapGestion') {
    if (cumpleCondicionPestania3) {
      $('#totalPuntuacionCapGestion').html(totalPuntuacion);
    } else {
      $('#totalPuntuacionCapGestion').html(0);
    }
    if (totalMaxValoracion !== null) {
      $('#puntuacionMaximaCapGestion').html(totalMaxValoracion);
    }
  }
  if (tipoCriterio == 'tablaListadoIncremento') {
    if (stringToBigNumber(totalPuntuacion) > maximoPuntuacionIncremento) {
      totalPuntuacion = maximoPuntuacionIncremento;
    }
    
   // var nuevoTotal = stringToBigNumber(totalPuntuacion) + stringToBigNumber($('#totalPuntuacionFase1Fase2').html());
    var nuevoTotal = stringToBigNumber(totalPuntuacion).plus(stringToBigNumber($('totalPuntuacionFase12Oculto').html()));
    $('#totalPuntuacionIncremento').html(totalPuntuacion);

    $('#totalPuntuacionSuma').html(convertirFormatoUE(nuevoTotal, 3));
    if (nuevoTotal.isLessThan(55) || !comprobarPermisosPestaña3) {
      $('#totalPuntuacionSuma').addClass("text-danger");
    } else {
      $('#totalPuntuacionSuma').removeClass("text-danger");
    }
  }
}


function actualizarPuntuacionIncremento(campo) {
  let valor;
  //Comprobamos si es de incremento
  if ($(campo).val() === "Si") {
    valor = 1;
  } else {
    valor = 0;
  }
  let celdaValor = $(campo).parent();
  let celdaPuntuacion = celdaValor.next();
  let puntuacionFase1Fase2 = totalesPuntuacionFase1.plus(totalesPuntuacionFase2);
  //  Calculamos el valor a incrementar 
  let porcentaje = stringToBigNumber(celdaValor.next().next().text()).dividedBy(100);
  celdaPuntuacion.text(convertirFormatoUE(puntuacionFase1Fase2.multipliedBy(porcentaje).multipliedBy(valor), 3));

  cargarTablaFinal(celdaValor.parent().parent().parent()[0].id, recalcularTotalIncrementos(), null);
  aniadirAlistaValoracionesEditadas(parseInt(celdaValor.prev().prev().text()), valor, stringToBigNumber(celdaPuntuacion.text()));
}

function recalcularTotalIncrementos() {
  if ($("#tablaListadoIncremento .dataTables_empty").length === 0) {
    let totalIncremento = new BigNumber(0);
    let tamanioTabla = $('#tablaListadoIncremento tr').length - 1;
    //Recorremos tabla para ir sumando las puntuaciones
    $('#tablaListadoIncremento tr').each(function(index, element) {
      if (index > 0 && index < tamanioTabla) {
        totalIncremento = totalIncremento.plus(stringToBigNumber($(element).children()[3].innerText));
      }
    });

    if (totalIncremento.isGreaterThanOrEqualTo(maximoPuntuacionIncremento)) {
      totalIncremento = maximoPuntuacionIncremento;
    }
	$('#totalPuntuacionIncrementoOculto').val(convertirFormatoUEAllDecimals(totalIncremento));
    $("#tablaListadoIncremento tfoot").children().children()[3].innerText = convertirFormatoUE(totalIncremento, 3);
    return convertirFormatoUE(totalIncremento, 3);
  }
}

function recargarIncrementos(){
  let tamanioTabla = $('#tablaListadoIncremento tr').length - 1;
  $('#tablaListadoIncremento tr').each(function(index, element) {
    if (index > 0 && index < tamanioTabla && $(element).find("select")[0].value==='Si') {
      actualizarPuntuacionIncremento($(element).find("select")[0]);
    }
  });
}
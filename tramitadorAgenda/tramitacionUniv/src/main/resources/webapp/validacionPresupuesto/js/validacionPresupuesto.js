var mensajeEmptyValidacionPresupuesto;
window.columnsDefsValidacionPresupuesto;
window.definicionTablaValidacionPresupuesto;
var valorAnterior;
var listaValidacionEditadas = [];

function initParametrizacionValidacionPresupuesto(nombreColumnasTabla, tamanoColumnasTabla, mensajeEmpty) {
  window.definicionTablaValidacionPresupuesto = [
    {
      data: "idGastoContribucion",
      className: "hide"
    }, {
      data: "gasto.descripcionCodigo",
      className: "centradoVertical",
      title: nombreColumnasTabla[0],
      width: tamanoColumnasTabla[0]
    }, {
      data: "valorTitle",
      className: "centradoVertical text-right",
      title: nombreColumnasTabla[1],
      width: tamanoColumnasTabla[1],
    }, {
      data: "valorNoValidadoTitle",
      className: "centradoVertical text-right",
      title: nombreColumnasTabla[2],
      width: tamanoColumnasTabla[2],
      render: function(data, type, full) {
        var input = '<input  type="string" class="form-control" idgasto="' +full.gasto.idGasto+ '" idcontribucion="' + full.idGastoContribucion + '" value=' + data + ' style="text-align: right;" onchange="calcularValidado(this)" onfocus="guardarValorAnterior(this)"';
        if (full.disabled === true) {
          input += ' disabled';
        }
        input += '/>';
        return input;
      }
    }, {
      data: "valorValidadoTitle",
      className: "centradoVertical text-right",
      title: nombreColumnasTabla[3],
      width: tamanoColumnasTabla[3],
    }];

  mensajeEmptyValidacionPresupuesto = mensajeEmpty;

  crearDataTableValidacionPresupuesto($("#listaEntidadesValidacion").val(), window.tablaValidacionPresupuesto, window.urlRecargaTablaContribuciones, window.definicionTablaValidacionPresupuesto, mensajeEmptyValidacionPresupuesto);
}

/**
  * Método para la construccion del datatable de Contraparte en Datos Generales
  */
function crearDataTableValidacionPresupuesto(entidad, idTable, url, definicionTablaValidacionPresupuesto, mensajeEmptyValidacionPresupuesto) {
  var datos = function(d) {
    d.entidadSeleccionada = entidad;
  }
  $(idTable).dataTable({
    type: "POST",
    ajax: {
      data: datos,
      url: url,
      dataType: 'json',
      dataSrc: function(json) {
        return json.listaGastosEntidadSeleccionada;
      },
      complete: function() {
      }
    },
    ordering: false,
    searching: false,
    columns: definicionTablaValidacionPresupuesto,
    autoWidth: false,
    bDestroy: true,
    stateSave: true,
    stateDuration: -1,
    paging: false,
    language: {
      emptyTable: mensajeEmptyValidacionPresupuesto,
      lengthMenu: "Mostrar _MENU_ registros",
      info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
      infoEmpty: "",
      loadingRecords: "Cargando...",
    }
  });
}

function recargaTablaContribuciones() {
  if(confirm('Se perderán los cambios no guardados. ¿Deseas continuar?')){
    $("#messagesAndErrors"). addClass('d-none');
    $('#tablaValidacionPresupuesto').DataTable().destroy();
    crearDataTableValidacionPresupuesto($('#listaEntidadesValidacion').val(), window.tablaValidacionPresupuesto, window.urlRecargaTablaContribuciones, window.definicionTablaValidacionPresupuesto, window.mensajeEmptyValidacionPresupuesto);
  }
}

function calcularValidado(elemento) {
  if (esImporte(elemento.value)) {
    if (elemento.value.indexOf(',') == -1) {
      elemento.value +=',00';
    }
    var totalNoVal = stringToFloat(elemento.value) * 100;
    var total = stringToFloat($(elemento).parent().prev().text()) * 100;
    if (total < totalNoVal) {
      alert("Presupuesto no validado no puede ser mayor al presupuesto");
      elemento.value = valorAnterior;
    } else {
      var tot = Math.round(total - totalNoVal) / 100;
      $(elemento).parent().next().text(convertirFormatoUE(tot, 2));
      
    }
    //idGasto, idGastoContribucion, idEntidad, presNoValidado
    aniadirAlistaValidacionesPresupuesto($(elemento).attr("idgasto"),$(elemento).attr("idcontribucion"), $("#listaEntidadesValidacion").val(), elemento.value );
  } else {
    elemento.value = valorAnterior;
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

function esImporte(numero) {
  var regex = /^(?:\d+|\d{1,3}(?:[\s\.]\d{3})+)(\,[0-9]{2})?$/;
  if (regex.test(numero) === false) {
    alert('Escriba un importe válido (000.000,00)');
    return false;
  }
  return true
}

function guardarValorAnterior(elemento) {
  valorAnterior = elemento.value;
}

function aniadirAlistaValidacionesPresupuesto(idGasto, idGastoContribucion, idEntidad, presNoValidado) {
  var encontrado = false;
  $.each(listaValidacionEditadas, function(i, elemento) {
    if (elemento.idGasto == idGasto) {
      elemento.presNoValidado = presNoValidado;
      encontrado = true;
    }
  });
  if (!encontrado) {
    listaValidacionEditadas.push({ idGasto: idGasto, idGastoContribucion: idGastoContribucion, idEntidad: idEntidad, presNoValidado: presNoValidado });
  }
}

function guardarValidacionPresupuesto(){
  var data = $("#formValidacionPresupuesto").serializeArray();
 $.each(listaValidacionEditadas, function(i, elemento) {
    data.push({ name: "listaGastosEntidadSeleccionada[" + i + "][gasto][idGasto]", value: elemento.idGasto });
    data.push({ name: "listaGastosEntidadSeleccionada[" + i + "][idGastoContribucion]", value: elemento.idGastoContribucion });
    data.push({ name: "listaGastosEntidadSeleccionada[" + i + "][valorNoValidado]", value: elemento.presNoValidado });
  });
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
  return peticionAjaxPOST(urlActionValidacionPresupuesto, data, success, error);
}

function existenErrores() {
  alert("Corregir los errores antes de guardar");
  $([document.documentElement, document.body]).animate({
    scrollTop: $("form").find('.errorLabel').offset().top
  }, 2000);
}
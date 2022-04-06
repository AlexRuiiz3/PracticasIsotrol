var mensajeEmptyComun;
var columnsComunes;
var listaBeneficiariaSuplente = [];

function initParametrizacionComun(nombreColumnasTabla, tamanoColumnasTabla, mensajeEmpty) {
  mensajeEmptyComun = mensajeEmpty;
  columnsComunes = [
    {
      data: "idSolicitud",
      className: "hide"
    },
    {
      data: "codIdentificativo",
      title: nombreColumnasTabla[0],
      width: tamanoColumnasTabla[0],
      className: "centradoVertical"
    },
    {
      data: "numExpTrewa",
      title: nombreColumnasTabla[1],
      width: tamanoColumnasTabla[1],
      className: "centradoVertical"
    },
    {
      data: "entidad",
      title: nombreColumnasTabla[2],
      width: tamanoColumnasTabla[2],
      className: "centradoVertical"
    },
    {
      data: "puntuacion",
      title: nombreColumnasTabla[3],
      width: tamanoColumnasTabla[3],
      className: "text-right centradoVertical"
    },
    {
      data: "beneficiaria",
      render: function(data, type, full) {
        return construyeSelect(full);
      },
      className: "centradoVertical text-center",
      title: nombreColumnasTabla[4],
      width: tamanoColumnasTabla[4]
    }
  ];
}

function construyeSelect(full) {
  var select = '<select id=beneficiariaSolicitud_' + full.idSolicitud + ' onchange="javascript:actualizarListaBeneficiariaSuplente(this);">';
  if (full.beneficiaria == true) {
    select = select + '<option value="">Selecciona una opción</option>'
      + '<option value="true" selected="selected">Beneficiaria</option>'
      + '<option value="false">Suplente</option>';
  } else if (full.beneficiaria == false) {
    select = select + '<option value="">Selecciona una opción</option>'
      + '<option value="true">Beneficiaria</option>'
      + '<option value="false"selected="selected">Suplente</option>';
  } else {
    select = select + '<option value="" selected="selected">Selecciona una opción</option>'
      + '<option value="true">Beneficiaria</option>'
      + '<option value="false">Suplente</option>';
  }
  select = select + '</select>';
  return select;
}

/**
  * Método para la construccion del datatable de listado de criterios
  */
function crearDataTable(idTable, columnsDefs, mensajeEmpty, finalidad) {
  $(idTable).dataTable({
    type: "POST",
    ajax: {
      url: window.urlActionCargaListadoExpedientesPorFinalidad,
      data: {'finalidad':finalidad},
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
    searching: false,
    ordering: true,
    order: [[3, "asc"]],
    paging: false,
    autoWidth: false,
    info: false,
    columnDefs: columnsDefs,
    columns: columnsComunes,
    bDestroy: true,
    stateSave: true,
    stateDuration: -1,
    language: {
      emptyTable: mensajeEmpty,
      infoEmpty: mensajeEmptyComun,
      loadingRecords: "Cargando...",
    }
  });
}

function actualizarListaBeneficiariaSuplente(campo) {
  var valor = null;
  if ($(campo).val() === "true") {
    valor = true;
  } else if ($(campo).val() === "false") {
    valor = false;
  }
    aniadirAlistaBeneficiariaSuplente(parseInt($(campo).parent().prev().prev().prev().prev().prev().text()), valor);
}

function aniadirAlistaBeneficiariaSuplente(id, valor) {
  var encontrado = false;
  $.each(listaBeneficiariaSuplente, function(i, elemento) {
    if (elemento.idSolicitud == id) {
      elemento.beneficiaria = valor;
      encontrado = true;
    }
  });
  if (!encontrado) {
    listaBeneficiariaSuplente.push({ idSolicitud: id, beneficiaria: valor });
  }
}

function prepararData(data) {
  $.each(listaBeneficiariaSuplente, function(i, elemento) {
    data.push({ name: "listaSolicitudConcesionDTO[" + i + "][idSolicitud]", value: elemento.idSolicitud });
    data.push({ name: "listaSolicitudConcesionDTO[" + i + "][beneficiaria]", value: elemento.beneficiaria });
  });
}

/**Guardamos las valoraciones realizadas */
function guardar() {
  $("#messagesAndErrors").addClass('d-none');
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
    listaBeneficiariaSuplente = [];
    scrollInicio();
    overlayExito(window.capaEspera);
  };
  var error = function(data) {
    $("#messagesAndErrors").html(data.responseText);
    $("#messagesAndErrors").removeClass('d-none');
    scrollInicio();
    overlayError(window.capaEspera);
  };
  return peticionAjaxPOST(urlActionGuardarBeneficiariaSuplente, data, success, error);
}

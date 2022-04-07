
var listadoExpedientes;
var urlListadoExpedientes;
var mensajeEmptyListadoExpedientes;
var columnsDefsListadoExpedientes;

function initTablaExpedientes() {
  crearDataTable(window.listadoExpedientes, urlListadoExpedientes, columnsDefsListadoExpedientes, mensajeEmptyListadoExpedientes);

}

function initParametrizacionListadoExpedientes(nombreColumnasTabla, tamanoColumnasTabla, url, mensajeEmpty) {
  urlListadoExpedientes = url;
  mensajeEmptyListadoExpedientes = mensajeEmpty;
  columnsDefsListadoExpedientes = [
    {
      data: "numExpTrewa",
      title: nombreColumnasTabla[0],
      width: tamanoColumnasTabla[0],
      className: "centradoVertical"
    },
    {
      data: "titulo",
      title: nombreColumnasTabla[1],
      width: tamanoColumnasTabla[1],
      className: "centradoVertical"
    },
    {
      data: "localizacion",
      title: nombreColumnasTabla[2],
      width: tamanoColumnasTabla[2],
      className: "centradoVertical"
    },
    {
      data: "entidad",
      title: nombreColumnasTabla[3],
      width: tamanoColumnasTabla[3],
      className: "centradoVertical"
    },
    {
      data: "cif",
      title: nombreColumnasTabla[4],
      width: tamanoColumnasTabla[4],
      className: "centradoVertical"
    },
    {
      data: "puntuacion",
      title: nombreColumnasTabla[5],
      width: tamanoColumnasTabla[5],
      className: "centradoVertical text-right"
    },
    {
      data: "desestimientos",
      title: nombreColumnasTabla[6],
      width: tamanoColumnasTabla[6],
      className: "centradoVertical"
    }
  ];
}

/**
     * Método para la construccion del datatable de Contraparte en Datos Generales
     */
function crearDataTable(idTable, url, columnsDefs, mensajeEmpty) {
  $(idTable).DataTable({
    type: "POST",
    ajax: {
      url: url,
      dataType: 'json',
      dataSrc: function(json) {
        return json;
      },
      complete: function() {
      }
    },
    dom: 'Bfrtip',
    buttons: [{
        extend: 'excelHtml5',
        footer: true,
        title: 'Archivo',
        filename: 'Listado solicitudes beneficiarias, suplentes y desestimadas',
        text: '<button class="btn btn-success">excel <i class="fas fa-file-excel"></i></button>'
    },
    {
        extend: 'csvHtml5',
        footer: true,
        title: 'Archivo',
        filename: 'Listado solicitudes beneficiarias, suplentes y desestimadas',
        text: '<button class="btn btn-success">csv <i class="fas fa-file-csv"></i></button>'
    }],
    ordering: false,
    searching: false,
    paging: false,
    autoWidth: false,
    bDestroy: true,
    stateSave: true,
    stateDuration: -1,
    columns: columnsDefs,
    language: {
      emptyTable: mensajeEmpty,
      lengthMenu: "Mostrar _MENU_ registros",
      info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
      infoEmpty: "",
      loadingRecords: "Cargando...",
      paginate: {
        first: "Primera",
        last: "Última",
        next: "Siguiente",
        previous: "Anterior"
      }
    }
  });
}
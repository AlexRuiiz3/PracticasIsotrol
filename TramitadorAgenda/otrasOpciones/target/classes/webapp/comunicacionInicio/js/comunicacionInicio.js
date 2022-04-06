var definicionTablaExpComuInicio;
var urlTablaExpComuInicio;
var mensajeEmpty;

var columnsDefsExpComuInicio = [
    { data: "numExpediente" },
    { data: "numExpOV" },
    { data: "fechaPagoSubvencion" },
    { data: "fechaFinComuInicio" },
    { data: "diasRestantes" }
];

/**
 * Función para inicializar datos de la pestaña
 */
function initPestanaComunicacionInicio() {
    crearDataTable(window.tablaExpComuInicio, urlTablaExpComuInicio, definicionTablaExpComuInicio, columnsDefsExpComuInicio, mensajeEmpty);
}

function initParametrizacionComunicacionInicio(nombreColumnasTabla, tamanoColumnasTabla, url, mensajeEmpty) {
  urlTablaExpComuInicio = url;
  mensajeEmpty = mensajeEmpty;
  definicionTablaExpComuInicio = [
      {
          title: nombreColumnasTabla[0],
          width: tamanoColumnasTabla[0]
      },
      {
          title: nombreColumnasTabla[1],
          width: tamanoColumnasTabla[1]
      },
      {
          title: nombreColumnasTabla[2],
          width: tamanoColumnasTabla[2]
      },
      {
          title: nombreColumnasTabla[3],
          width: tamanoColumnasTabla[3]
      },
      {
          title: nombreColumnasTabla[4],
          width: tamanoColumnasTabla[4]
      }
  ];
}

/**
     * Método para la construccion del datatable de listado de historico
     */
function crearDataTable(idTable, url, definicionTablaDefectos, columnsDefs, mensajeEmpty) {
    $(idTable).dataTable({
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
        error: function(xhr, error, code) {
          $("#messagesAndErrors").html(error.responseText);
          $("#messagesAndErrors").removeClass('d-none');
          scrollInicio();
          overlayError(window.capaEspera);
        },
        ordering: true,
        searching: true,
        columnDefs: definicionTablaDefectos,
        columns: columnsDefs,
        autoWidth: false,
        bDestroy: true,
        stateSave: true,
        stateDuration: -1,
        language: {
            search: "Buscar",
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
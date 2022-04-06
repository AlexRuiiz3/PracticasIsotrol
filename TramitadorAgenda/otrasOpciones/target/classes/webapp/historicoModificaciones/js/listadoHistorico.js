var definicionTablaHistorico;
var urlTablaHistorico;
var mensajeEmptyHistorico;

var columnsDefsHistorico = [
    { data: "numExpediente" },
    { data: "fechaAlta" },
    { data: "usuarioWeb" }
];

/**
 * FUnción para inicializar datos de la pestaña
 */
function initPestanaHistorico() {
    crearDataTable(window.tablaHistorico, urlTablaHistorico, definicionTablaHistorico, columnsDefsHistorico, mensajeEmptyHistorico);
}

function initParametrizacionHistorico(nombreColumnasTabla, tamanoColumnasTabla, url, mensajeEmpty) {
  urlTablaHistorico = url;
  mensajeEmptyHistorico = mensajeEmpty;
  definicionTablaHistorico = [
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
        ordering: false,
        searching: false,
        columnDefs: definicionTablaDefectos,
        columns: columnsDefs,
        autoWidth: false,
        bDestroy: true,
        stateSave: true,
        stateDuration: -1,
        language: {
            emptyTable: mensajeEmpty,
            lengthMenu: "Mostrar _MENU_ registros",
            info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
            infoEmpty: "Sin registros que mostrar",
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
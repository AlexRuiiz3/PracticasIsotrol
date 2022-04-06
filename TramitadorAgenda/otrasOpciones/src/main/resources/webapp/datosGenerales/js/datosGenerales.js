var urlTablaAgrupacion;
var mensajeEmptyAgrupacion;
var definicionTablaAgrupacion;


var columnsDefsEntidadSolicitante = [
  { data: "entParticipante" },
  { data: "participacion" }
];

/**
 * FUnción para inicializar datos de la pestaña
 */
function initPestanaDatosGenerales() {
  inicializarDatepickers(window.idCamposFechas, "dd/mm/yyyy");
  crearDataTable(window.tablaAgrupacion, urlTablaAgrupacion, definicionTablaAgrupacion, columnsDefsEntidadSolicitante, mensajeEmptyAgrupacion);
  configuracionValidaciones();

}

function initParametrizacionAgrupacion(nombreColumnasTabla, tamanoColumnasTabla, url, mensajeEmpty) {
  urlTablaAgrupacion = url;
  mensajeEmptyAgrupacion = mensajeEmpty;
  definicionTablaAgrupacion = [
    {
      title: nombreColumnasTabla[0],
      width: tamanoColumnasTabla[0]
    },
    {
      title: nombreColumnasTabla[1],
      width: tamanoColumnasTabla[1]
    }
  ];
}


/**
     * Método para la construccion del datatable de Contraparte en Datos Generales
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

function guardarDatosGenerales() {
  if ($("#formDatosGenerales").valid()) {
    var data = $('#formDatosGenerales').serialize();
    var success = function(data) {
      $("#messagesAndErrorsDatosGenerales").html(data);
      $("#messagesAndErrorsDatosGenerales").removeClass("d-none");
      overlayExito(window.capaEspera);
    }
    var error = function(data) {
      $("#messagesAndErrorsDatosGenerales").html(data.responseText);
      $("#messagesAndErrorsDatosGenerales").removeClass("d-none");
      overlayError(window.capaEspera);
    }
    peticionAjaxPOST(urlGuardarDatosGenerales, data, success, error);
  } else {
    $([document.documentElement, document.body]).animate({
      scrollTop: $("form").find('.errorLabel').offset().top
    }, 2000);
  }
}
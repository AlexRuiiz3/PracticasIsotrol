
var listadoSolicitudes;
var urlListadoSolicitudes;
var mensajeEmptyListadoSolicitudes;

var columnsDefsListadoSolicitudes = [
    { data: "numeroIdentificativoExpediente" },
    { data: "titulo" },
    { data: "ongd" },
    { data: "cif" },
    { data: "impSol" },
    { data: "presupuestoTotal" },
    { data: "localizacion" },
	{ data: "plazoEjecucion" },
	{ data: "serviciosSocialesBasicos" }
];


function initPestanaHijosConvocatoria() {
    crearDataTable(window.listadoSolicitudes, urlListadoSolicitudes, listadoSolicitudes, columnsDefsListadoSolicitudes, mensajeEmptyListadoSolicitudes);

}

function initParametrizacionHijosConvocatoria(nombreColumnasTabla, tamanoColumnasTabla, url, mensajeEmpty) {
    urlListadoSolicitudes = url;
    mensajeEmptyListadoSolicitudes = mensajeEmpty;
    definicionTablaContraparte = [
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
        },
        {
            title: nombreColumnasTabla[5],
            width: tamanoColumnasTabla[5]
        },
        {
            title: nombreColumnasTabla[6],
            width: tamanoColumnasTabla[6]
        },
        {
            title: nombreColumnasTabla[7],
            width: tamanoColumnasTabla[7]
        },
        {
            title: nombreColumnasTabla[8],
            width: tamanoColumnasTabla[8]
        }
    ];
}

/**
     * Método para la construccion del datatable de Contraparte en Datos Generales
     */
function crearDataTable(idTable, url, definicionTablaDefectos, columnsDefs, mensajeEmpty) {
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
        filename: 'ListaExpedientes',
        //Aquí es donde generas el botón personalizado
        text: '<button class="btn btn-success">Exportar a excel<i class="fas fa-file-excel"></i></button>'
    }],
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
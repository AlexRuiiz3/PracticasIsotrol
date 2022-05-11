var urlCreaModificaConvocatoria = "agenda/tareas/guardarDatosConvocatoria.action";

function createOrUpdateConvocatoria() {
if ($("#formDatosConvocatoria").valid()) {
    $("#messagesAndErrors").addClass('d-none');
		  var type = "POST";
		  var url = urlCreaModificaConvocatoria;
		  var data = $( "form" ).serialize();
		  var success = function(data) {
			  $("#messagesAndErrors").html(data);
			  $("#messagesAndErrors").removeClass('d-none');
			  overlayExito(window.capaEspera);
		  };
		  var error = function(data){
			  $("#messagesAndErrors").html(data);
			  $("#messagesAndErrors").removeClass('d-none');
			  overlayError(window.capaEspera);
		  };
		return peticionAjax(type,url,data,success,error);
    } else {
        $([document.documentElement, document.body]).animate({
            scrollTop: $("form").find('.errorLabel').offset().top
        }, 2000);
    }
}

/**
 * Funci�n para inicializar fechas
 */
function initPantalla() {
    inicializarDatepickers(window.idCamposFechas, "dd/mm/yyyy");
    configuracionValidaciones();
}

function inicializarDatepickers(idDatepickers, formato) {
    var datePickersId = idDatepickers;
    var valorId;
    var inputFecha;
    var valorFecha;
    $.each(datePickersId, function(indice, valor) {
        valorId = "#" + valor;
        $(valorId).datepicker({
            weekStart: 1,
            todayBtn: true,
            clearBtn: true,
            language: "es",
            format: formato
        });


        inputFecha = valorId + " :input";
        if ($(inputFecha).val() !== null && $(inputFecha).val() !== '') {
            var fecha = $(inputFecha).val();
            var indiceEspacio = fecha.indexOf(' ');
            if (indiceEspacio>0){
                fecha = fecha.substr(0, indiceEspacio);
            }
            $(valorId).datepicker('setDate', fecha);
        }
    });
}
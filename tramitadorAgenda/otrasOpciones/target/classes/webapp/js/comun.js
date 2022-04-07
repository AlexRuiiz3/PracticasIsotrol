window.capaEspera;

$('.nav-tabs a').click(function() {
    $(this).tab('show');
})

function compruebaLimite(textarea, limite) {
    var str;
    var navegador = navigator.appName;
    if (navegador == "Microsoft Internet Explorer") {
        str = textarea.value;
        if (str.length > parseInt(limite)) {
            textarea.value = str.substring(0, parseInt(limite));
            alert('Este campo no admite mas de ' + parseInt(limite) + ' caracteres');
        }
    } else {
        str = textarea.value.replace(/\n/g, "\r\n");
        if (str.length > parseInt(limite)) {
            textarea.value = str.substring(0, parseInt(limite));
            alert('Este campo no admite mas de ' + parseInt(limite) + ' caracteres');
        }
    }
}

function overlayExito(overlay) {
    overlay.update({
        icon: "/ptwanda-web/modulos/otrasOpciones/imagenes/check.png",
        text: "Exito"
    });
    window.setTimeout(function() {
        overlay.hide();
    }, 1000);
    $(".fondoOverlay").addClass("d-none");
    scrollInicio();
}

function overlayError(overlay) {
    overlay.update({
        icon: "/ptwanda-web/modulos/otrasOpciones/imagenes/cross.png",
        text: "Error"
    });
    window.setTimeout(function() {
        overlay.hide();
    }, 1000);
    $(".fondoOverlay").addClass("d-none");
    scrollInicio();
}

function scrollInicio() {
    $('html, body').animate({
        scrollTop: 0
    }, 1000);
}

/**
  * Realiza peticion AJAX tipo POST.
  * 
  * @param url
  * @param data
  * @param success
  * @param error
  * @returns
  */
function peticionAjaxPOST(url, data, success, error, async) {
    return peticionAjax("POST", url, data, success, error, async);
}

/**
 * Realiza peticion AJAX
 * 
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
    }
    else {
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
        }
    });
}

function inicializarDatepickers(idDatepickers, formato) {
    var datePickersId = idDatepickers;
    var valorId;
    var inputFecha;
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
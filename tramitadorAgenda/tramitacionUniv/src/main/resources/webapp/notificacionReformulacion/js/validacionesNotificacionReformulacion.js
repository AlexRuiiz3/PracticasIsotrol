/**
 * JavaScript para las validaciones de los campos
 */
function configuracionValidacionesNotificacionReformula() {
    
    $.validator.setDefaults({
        onfocusout: function (e) {
            this.element(e);
        },
        onkeyup: false, 
        highlight: function (element) {
            jQuery(element).closest('.form-control').addClass('is-invalid');
        },
        unhighlight: function (element) {
            jQuery(element).closest('.form-control').removeClass('is-invalid');
        },
        errorElement: 'div',
        errorClass: 'invalid-feedback',
        errorPlacement: function (error, element) {
            var elm = $(element);
            if (elm.parent('.input-group').length || elm.parent('.input-group-custom').length) {
                error.insertAfter(elm.parent());
            }
            else if (elm.prop('type') === 'checkbox' || elm.prop('type') === 'radio') {
                error.appendTo(elm.closest(':not(input, label, .checkbox, .radio)').first());
            } else {
                error.insertAfter(elm);
            }
        }
    });
    
    $.validator.addMethod("esImporte", function(value, element) {
        return this.optional(element) ||  esImporte(value);
    }, "Escriba un importe válido (000.000,00)");
    
    jQuery.validator.addMethod("rangeImporte", function(value, element, params) {
        return this.optional(element) || rangoImporte(value, params[0], params[1]);
    }, $.validator.format("Escriba un importe entre {0} y {1}"));
    
    validacionesNotificacionReformula();
}


function validacionesNotificacionReformula() {
    $("#formReformulaUniv").validate({
        rules: {
            'datosReformulaDTO.maximoAACID': {
                required: true,
                esImporte: true,
                rangeImporte: ['0', '999.999.999,99']
            },
            'datosReformulaDTO.minimoPresupuestoTotal': {
                required: true,
                esImporte: true,
                rangeImporte: ['0', '999.999.999,99']
            }
        }
    });
}

function esImporte(numero) {
    var regex = /^-?(?:\d+|\d{1,3}(?:[\s\.]\d{3})+)(\,[0-9]{2})?$/;
    return regex.test(numero);
}

function rangoImporte(value, min, max) {
    
    var minimo = stringToFloat(min);
    var maximo = stringToFloat(max);
    var result = false;
    
    if (esImporte(value)){
        var importe = stringToFloat(value);
        result = importe >= minimo && importe <= maximo;
    }

    return result;
}

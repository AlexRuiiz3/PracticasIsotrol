var ar = [];

$('#messages').hide();

function adjuntarDoc() {

    if (ar.length == 0) {
        window.alert("Debe adjuntar al menos un documento.");
    } else {
        window.capaEspera = cargarCapaEspera();
        var myfiles = ar;
        var refDoc = $('#refDoc').val();
        var data = new FormData();
        var pdf = $('#onlyPDF').val();
        var xml = $('#onlyXML').val();
        var error = false;

        $.each(myfiles, function (i, v) {
            var nombre = v.name.split(".")
            if (nombre.length >= 2) {
                var nuevoNombre = "";
                var nombreAntiguo = v.name.split(".");
                for (i = 0; i < nombreAntiguo.length - 1; i++) {
                    nuevoNombre += nombreAntiguo[i] += "_";
                }
                nuevoNombre = nuevoNombre.substring(0, nuevoNombre.length - 1);
                nuevoNombre += ".";
                nuevoNombre += nombreAntiguo[nombreAntiguo.length - 1];
                if (pdf == "true") {
                    var extension = nuevoNombre.split(".");
                    if (extension[1] == "pdf" || extension[1] == "PDF") {
                        data.append('file', v, nuevoNombre);
                    } else {
                        window.alert("Los documentos adjuntos en esta fase deben estar en formato PDF");
                        error = true;
                        return false;
                    }
                } else if (xml == "true") {
                    var extension = nuevoNombre.split(".");
                    if (extension[1] == "xml") {
                        data.append('file', v, nuevoNombre);
                    } else {
                        window.alert("Los documentos adjuntos en esta fase deben estar en formato XML");
                        error = true;
                        return false;
                    }
                } else {
                    data.append('file', v, nuevoNombre);
                }

            } else {
                if (pdf == "true") {
                    var extension = v.name.split(".");
                    if (extension[1] == "pdf") {
                        data.append('file', v);
                    } else {
                        window.alert("Los documentos adjuntos en esta fase deben estar en formato PDF");
                        error = true;
                        return false;
                    }
                } else if (xml == "true") {
                    var extension = v.name.split(".");
                    if (extension[1] == "xml") {
                        data.append('file', v);
                    } else {
                        window.alert("Los documentos adjuntos en esta fase deben estar en formato XML");
                        error = true;
                        return false;
                    }
                } else {
                    data.append('file', v);
                }
            }

        })

        if (!error) {
            data.append('refDoc', refDoc);
            $.apply($.ajax({
                type: "POST",
                url: "../otrasOpciones/subirDocumento.action",
                processData: false,
                contentType: false,
                data: data,
                success: function (data) {
                    overlayExito(window.capaEspera);
                    opener.recargaPadre([['capa_doc_asoc', '../modulos/docsAsociadosExpediente/listarDocumentos.action', ''],
                        ['capa_tramita', '../modulos/tramitacion/listarTransicionesExpediente.action', ''],
                        ['ayudaTramitacion', '../modulos/ayudaTramitacion/listarTransicionesExpediente.action', ''],
                        ['capa_doc_perm', '../modulos/bloquesPermitidos/listarBloquesPermitidos.action', ''],
                        ['jerarquizacionDocumentos', '../modulos/jerarquizacionDocumentos/inicioJerarquizacionDocumentos.action', '']]);
                    opener.recargaPadreModulosObservados('capa_doc_asoc');
                    opener.recargaPadreModulosObservados('jerarquizacionDocumentos');
                    window.close();
                },
                error: function (data) {
                    alert('ERROR: No se han podido adjuntar los archivos seleccionados.');
                    overlayError(window.capaEspera);
                }
            }));

        } else {
            overlayError(window.capaEspera);
        }
    }
}

function eliminaDoc(name) {
    var araux = [];
    for (var i = 0, f; f = ar[i]; i++) {
        var nameaux = f.name;
        nameaux = nameaux.split('.').join('');
        nameaux = nameaux.split(' ').join('');
        if (nameaux != name) {
            araux.push(ar[i])
        }
    }
    ar = araux;
    name = name.replace('.', '');
    name = name.replace(' ', '');
    name = name.replace('(', '');
    name = name.replace(')', '');
    fileid = '#file' + name;
    filexid = '#filex' + name;
    $(fileid).remove();
    if (ar.length == 0) {
        $('#messages').hide();
    }
    $('#fileselect').files = ar;
}

function compruebaDocumentosAdjuntos() {
    if (ar.length == 0) {
        window.alert("Debe adjuntar al menos un documento.");
        return false;
    } else {

        return true;
    }

}
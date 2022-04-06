var listaSubsanacionesSeleccionadas = [];
var listaSubsSeleccionadas = [];


$(function() {
  var type = "POST";
  var url = 'buscarSolicitudesSeleccionadasUniv.action';
  var success = function(data) {
    if (typeof data.catalogosSeleccionados !== 'undefined' && data.catalogosSeleccionados !== null) {
      data.catalogosSeleccionados.forEach(function(element) {
        var valorCompleto = element.catalogo.nombreCompleto;
        if (element.motivo !== null) {
          var textoExtra = '';
          if (element.puedeEditar === false) {
            textoExtra = 'disabled="true" ';
          }
          valorCompleto = valorCompleto + '<br><label class="font-weight-bold">Motivo</label><br><textarea class="form-control rounded-0" rows="2" cols="100" name="texto" ' + textoExtra + 'onkeyup="compruebaLimite(this, 3000)">' + element.motivo + '</textarea>'
        }
        if (element.puedeEditar === true) {
          valorCompleto = valorCompleto + '<br><a href="#" onClick="eliminarSubsanacion(\'' + element.catalogo.nombreCompleto + '\')"> <i class="fas fa-trash"></i> Eliminar Subsanación</a>'
        }

        var selected = [valorCompleto];

        listaSubsSeleccionadas.push(element.catalogo.nombreCompleto);
        listaSubsanacionesSeleccionadas.push(selected);
      })
      seleccionarSubsanacion(true);
    }
    overlayExito(window.capaEspera);
  };
  var error = function(data) {
    $("#messagesAndErrors").html(data);
    $("#messagesAndErrors").removeClass('d-none');
    overlayError(window.capaEspera);
  };
  peticionAjax(type, url, null, success, error);
});

function cargarSelecGrupoSubsanacion() {

  $('#tablaGrupoSubsanaciones').DataTable({
    destroy: true,
    "paging": false,
    "autoWidth": false,
    "ajax": {
      "url": "buscarTipoCatalogoUniv.action",
      "dataType": 'json',
      "dataSrc": function(json) {
        return json.listaTipoCatalogo;
      },
      "error": handleAjaxError
    },
    "columns": [{
      data: "nombre"
    }, {
      data: "id"
    }],
    "columnDefs": [{
      "type": 'natural',
      "targets": [0],
      "render": function(data, type, full) {
        return full.nombre;
      },
      "className": 'text-center'
    }, {
      "targets": [1],
      "width": "5%",
      "render": function(data, type, full) {
        return renderBotonSeleccionar(full);
      },
      "className": '',
      "searchable": false,
      "orderable": false
    }],
    "order": [0, 'asc'],
    language: {
      emptyTable: "No hay registros",
      lengthMenu: "Mostrar _MENU_ registros",
      info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
      infoEmpty: "",
      search: "Buscar:",
      loadingRecords: "Cargando...",
      paginate: {
        first: "Primera",
        last: "Última",
        next: "Siguiente",
        previous: "Anterior"
      }
    }
  });
  $("#modalBuscarGrupo").modal();

}

function selecGrupoSubsanacion(nombre, id) {
  $('#grupoSubsanacionSeleccionado').val(id);
  $('#nombreSubsanacion').html(nombre);
  $('#subsanacionSeleccionada').removeClass('d-none');
  $('#subsanacionNoSeleccionada').addClass('d-none');
}

function renderBotonSeleccionar(full) {
  return '<input type="button" onclick="selecGrupoSubsanacion(\'' + full.nombre + '\', \'' + full.id + '\');" value="Seleccionar" data-dismiss="modal" />';
}

function renderCheckSeleccionar(full) {
  var textoExtra = '';
  if (full.puedeEditar === false) {
    textoExtra = " onClick='return false;' style='cursor:not-allowed;'";
  }
  return "<input type='checkbox' id='" + full.id + "' value='" + full.nombreCompleto + "'" + textoExtra + "/>";
}

function renderTextoColumna(full) {
  var texto = full.descripcion;
  if (full.motivo === true) {
    var textoExtra = '';
    if (full.puedeEditar === false) {
      textoExtra = 'disabled="true" ';
    }
    texto = texto + '<br><label class="font-weight-bold">Motivo</label><br><textarea class="form-control rounded-0" rows="2" cols="100" onkeyup="compruebaLimite(this, 3000)" ' + textoExtra + 'id="text-' + full.id + '"/>';
  }
  return texto;
}

function handleAjaxError() {

}

function cargarSelecSubsanacion() {
  var idCatalogo = $('#grupoSubsanacionSeleccionado').val();
  $('#tablaSubsanaciones').DataTable({
    destroy: true,
    "paging": false,
    "autoWidth": false,
    "ajax": {
      "url": "buscarCatalogoUniv.action",
      "data": {
        "tipoCatalogoSeleccionado": idCatalogo
      },
      "dataType": 'json',
      "dataSrc": function(json) {
        return json.listaCatalogo;
      },
      "error": handleAjaxError
    },
    "columns": [{
      data: "id"
    }, {
      data: "descripcion"
    }],
    "columnDefs": [{
      "targets": [0],
      "width": "5%",
      "render": function(data, type, full) {
        return renderCheckSeleccionar(full);
      },
      "className": 'text-center',
      "searchable": false,
      "orderable": false
    }, {
      "type": 'natural',
      "targets": [1],
      "render": function(data, type, full) {
        return renderTextoColumna(full);
      },
      "className": ''
    }],
    "order": [1, 'asc'],
    language: {
      emptyTable: "No hay registros",
      lengthMenu: "Mostrar _MENU_ registros",
      info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
      infoEmpty: "",
      search: "Buscar:",
      loadingRecords: "Cargando...",
      paginate: {
        first: "Primera",
        last: "Última",
        next: "Siguiente",
        previous: "Anterior"
      }
    }
  });
  $("#modalBuscarSubsanacion").modal();
}

function seleccionarSubsanacion(reload) {

  if (reload === false) {
    $('#tablaSubsanaciones input:checked').each(function() {
      var valorCompleto = this.value;
      var id = this.id;
      if ($('#text-' + id).length) {
        var texto = $('#text-' + id);
        valorCompleto = valorCompleto + '<br><label class="font-weight-bold">Motivo</label><br><textarea class="form-control rounded-0" rows="2" cols="100" name="texto" onkeyup="compruebaLimite(this, 3000)">' + texto.val() + '</textarea>'
      }
      valorCompleto = valorCompleto + '<br><a href="#" onClick="eliminarSubsanacion(\'' + this.value + '\')"> <i class="fas fa-trash"></i> Eliminar Subsanación</a>'
      var selected = [valorCompleto];
      if (!listaSubsSeleccionadas.includes(this.value)) {
        listaSubsSeleccionadas.push(this.value);
        listaSubsanacionesSeleccionadas.push(selected);
      }
    });
  }

  if (listaSubsanacionesSeleccionadas.length === 0) {
    $('#tablaSubsanacionesSeleccionadas').DataTable().clear();
    $('#tablaSubsanacionesSeleccionadas').DataTable().destroy();
    $('#tablaSubsanacionesSeleccionadas').addClass('d-none');
    $('#mensajeInfoSubsanacionSeleccioanda').removeClass('d-none');
  } else {

    $('#tablaSubsanacionesSeleccionadas').DataTable({
      destroy: true,
      "paging": false,
      data: listaSubsanacionesSeleccionadas,
      "columns": [{
        data: "subsanacion"
      }],
      "columnDefs": [{
        "type": 'natural',
        "targets": [0],
        "render": function(data, type, full) {
          return full;
        }
      }],
      "order": [0, 'asc'],
      language: {
        emptyTable: "No hay registros",
        lengthMenu: "Mostrar _MENU_ registros",
        info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
        infoEmpty: "",
        search: "Buscar:",
        loadingRecords: "Cargando...",
        paginate: {
          first: "Primera",
          last: "Última",
          next: "Siguiente",
          previous: "Anterior"
        }
      }
    });

    $('#tablaSubsanacionesSeleccionadas').removeClass('d-none');
    $('#mensajeInfoSubsanacionSeleccioanda').addClass('d-none');

    $('#nombreSubsanacion').html('');
    $('#subsanacionSeleccionada').addClass('d-none');
    $('#subsanacionNoSeleccionada').removeClass('d-none');
  }

}

function eliminarSubsanacion(id) {

  listaSubsanacionesSeleccionadas = jQuery.grep(listaSubsanacionesSeleccionadas, function(elemento) { return elemento[0].indexOf(id) < 0 });
  listaSubsSeleccionadas = jQuery.grep(listaSubsSeleccionadas, function(elemento) { return elemento.indexOf(id) < 0 });

  seleccionarSubsanacion(true);

}

function guardarSubsanacion() {

  var catalogoGuardar = [];
  var tabla = $('#tablaSubsanacionesSeleccionadas').DataTable();
  tabla.rows().every(function() {
    var data = this.data()[0];
    var texto = '';
    var codigoCatalogo = data.substring(0, data.indexOf('. '));

    if (data.indexOf('<textarea') > -1) {
      texto = $(tabla.cell(this.index(), 0).node()).find('textarea').val();
    }

    catalogoGuardar.push({ codigo: codigoCatalogo, motivo: texto });
  });

  var enviar = { listadoSubsanaciones: catalogoGuardar, numeroExpediente: $('#idExp').val(), observaciones: $('#observaciones').val(), motivoDesestimacion: $('#motivoDesestimacion').val() };

  var type = "POST";
  var url = 'guardarSubsanacionesValoradorUniv.action';
  var data = enviar;
  var success = function(data) {
    $("#messagesAndErrors").html(data);
    $("#messagesAndErrors").removeClass('d-none');
    if (listaSubsanacionesSeleccionadas.length === 0) {
      $('#tablaSubsanacionesSeleccionadas').DataTable().destroy();
      $('#tablaSubsanacionesSeleccionadas').addClass('d-none');
      $('#mensajeInfoSubsanacionSeleccioanda').removeClass('d-none');
    }
    scrollInicio();
    overlayExito(window.capaEspera);
  };
  var error = function(data) {
    $("#messagesAndErrors").html(data);
    $("#messagesAndErrors").removeClass('d-none');
    if (listaSubsanacionesSeleccionadas.length === 0) {
      $('#tablaSubsanacionesSeleccionadas').DataTable().destroy();
      $('#tablaSubsanacionesSeleccionadas').addClass('d-none');
      $('#mensajeInfoSubsanacionSeleccioanda').removeClass('d-none');
    }
    scrollInicio();
    overlayError(window.capaEspera);
  };
  return peticionAjax(type, url, data, success, error);
}
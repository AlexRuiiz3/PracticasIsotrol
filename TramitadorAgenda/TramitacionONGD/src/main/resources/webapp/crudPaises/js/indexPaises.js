var idPaisSelecionado;
$(document).ready(function(){
		  $('#tablaPaises').DataTable({
		        ajax: {
		            url: "obtenerPaisesPorAnho.action",
		            dataType: 'json',
		            dataSrc: function(json) {
		                return json;
		            },
		              error: function (request, status, error) {
       					 alert(request.responseText);
    					},
		        },
		        columns: [
			        {data: "codigo"}, 
			        {data: "nombre"}, 
			        {data: "puntuacion"},
			        {render: function (data, type, row) { return '<input type="button" onclick="clickButtonDetalles('+row.idPais+')" class="btn btn-success" value="Detalles">' } },
			        {render: function (data, type, row) { return '<input type="button" onclick="clickButtonEditar('+row.idPais+')" class="btn btn-warning" value="Editar">' } },
			        {render: function (data, type, row) { return '<input type="submit" onclick="clickButtonEliminar('+row.idPais+')" class="btn btn-danger" value="Eliminar">' } }
		        ],
		        ordering: true,
		        searching: true
	        });       
})

function clickButtonDetalles(id){	
	$.ajax({
		url: 'cargarPais.action',
		data: {"idPais": id},
		dataType: 'json',
		dataSrc: function(json) {
		         return json;
		},
		success: function(data){
			$("#lblCodigo").html(data.codigo);
			$("#lblNombre").html(data.nombre);
			$("#lblAnho").html(data.anio);
			$("#lblPuntuacion").html(data.puntuacion);
		}
	});
	$("#modalDetalles").modal('show');
}

function clickButtonCrear(){	
	$("#modalCrear").modal('show');
}

function clickButtonEditar(id){	
		idPaisSelecionado = id;
		$.ajax({
		url: 'cargarPais.action',
		data: {"idPais": id},
		dataType: 'json',
		dataSrc: function(json) {
		         return json;
		},
		success: function(data){
			$("#inputCodigoPaisEditar").val(data.codigo);
			$("#inputNombreEditar").val(data.nombre);
			$("#inputAnhoEditar").val(data.anio);
			$("#inputPuntuacionEditar").val(data.puntuacion);
		},
		error:function(){
			alert("Error preparando modal editar");
		}
	});
	$("#modalEditar").modal('show');
}

function clickButtonEliminar(id){
	if(confirm("¿Desea eliminar el pais seleccionado?")){
       	$.ajax({
		type: 'POST',
		url: 'eliminarPais.action',
		data: {"idPais": id}
		});
    }
    else{
        return false;
    }
}

function enviarFormCrearOEditar(nameAccion,url){
	var informacionForm = $("#form"+nameAccion).serialize();
	$.ajax({
		url: url,
		type: 'POST', //No hace falta
		data: {"idPais": idPaisSelecionado,"datosPais": informacionForm},
		success: function(){
			alert('Operación realizada con exito');
			$("#inputNombre"+nameAccion).val('');
			$("#inputCodigoPais"+nameAccion).val('');
			$("#inputAnho"+nameAccion).val('');
			$("#inputPuntuacion"+nameAccion).val('');
			
			$("#modal"+nameAccion).modal('hide');
		}
	});
}

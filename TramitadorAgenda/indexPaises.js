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
			        {render: function (data, type, row) { return '<input type="submit" onclick="clickButtonDetalles('+row.idPais+')" class="btn btn-outline-secondary" value="Detalles">' } },
			        {render: function (data, type, row) { return '<button onclick="clickButtonDetalles('+row.idPais+')" class="btn btn-outline-secondary">Editar</button>' } },
			        {render: function (data, type, row) { return '<button  class="btn btn-outline-secondary">Eliminar</button>' } }
		        ],
		        ordering: true,
		        searching: true
	        });       
})

function clickButtonDetalles(id){	
	$.ajax({
		url: 'cargarDetallesPais.action',
		data: {"idPais": id}
	});
	$("#modalDetalles").modal('show');
}
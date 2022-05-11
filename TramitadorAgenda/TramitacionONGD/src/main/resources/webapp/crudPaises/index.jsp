<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<!-- CSS  -->
<s:include value="/modulos/tramitacionONGD/templates/head_styles.jsp" />
<!-- Scripts -->
<s:include value="/modulos/tramitacionONGD/templates/head_scripts.jsp" />

<script type="text/javascript" src="<s:url value="/modulos/tramitacionONGD/crudPaises/js/indexPaises.js"/>"></script>
<meta charset="UTF-8">
<s:head theme="ajax" />
</head>
<body>

    <input type="button" onclick="clickButtonCrear()" value="Crear" class="btn btn-primary btn-lg"/>
	<table id="tablaPaises" class="table table-bordered table-sm">
		<thead>
			<tr>
				<th>Codigo</th>
				<th>Nombre</th>
				<th>Puntuacion</th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
		</thead>
	</table>
	
<div id="modalDetalles" class="modal fade">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Detalles</h5>
        <button type="button" class="close" data-dismiss="modal" >X</button>
      </div>
      <div class="modal-body">
      <div class="form-row">
      	<div class="form-group col-md-2">
      		Codigo: <label id="lblCodigo"></label>
      	</div>
      	<div class="form-group col-md-4">
      		 Pais:  <label id="lblNombre"></label>
      	</div>

      </div>
         
      <div class="form-row">
      	<div class="form-group col-md-2">
      		Año: <label id="lblAnho"></label>
      	</div>
      	<div class="form-group col-md-4">
      		Puntuacion: <label id="lblPuntuacion"></label>
      	</div>
      </div>
      </div>
    </div>
  </div>
</div>

<div id="modalCrear" class="modal fade">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Crear</h5>
        <button type="button" class="close" data-dismiss="modal">X</button>
      </div>
      <div class="modal-body">
		<div class="form-group">
		<s:form id="formCrear">
			<div class="form-row">
				<div class="form-group col-md-2">
					<label>Nombre del Pais</label><br/>
					<input id="inputNombreCrear" name="Nombre" type="text" required="required" class="form-control">
				</div>
				<div class="form-group col-md-4">
					<label>Código del Pais</label><br/>
					<input id="inputCodigoPaisCrear" name="CodigoPais" type="number" required="required" class="form-control">
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-2">
					<label>Año</label><br/>
					<input id="inputAnhoCrear" name="Anho" type="number" required="required" class="form-control">
				</div>
				
				<div class="form-group col-md-4">
					<label>Puntuación</label><br/>
					<input id="inputPuntuacionCrear" name="Puntuacion" type="number" step="any" required="required" class="form-control">
				</div>
			</div>
			
			<input type="submit" onclick="enviarFormCrearOEditar('Crear','crearPais.action')" Value="Crear" class="btn btn-success">
		</s:form>	
		</div>
      </div>
    </div>
  </div>
</div>

<div id="modalEditar" class="modal fade">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Editar</h5>
        <button type="button" class="close" data-dismiss="modal">X</button>
      </div>
      <div class="modal-body">
		<div class="form-group">	
		<form id="formEditar">
		
			<div class="form-row">
				<div class="form-group col-md-2">
					<label>Nombre</label><br/>
					<input id="inputNombreEditar" name="Nombre" type="text" required="required" class="form-control">
				</div>
				<div class="form-group col-md-4">
					<label>Código Pais</label><br/>
					<input id="inputCodigoPaisEditar" name="CodigoPais" type="number" required="required" class="form-control">
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-2">
					<label>Año</label><br/>
					<input id="inputAnhoEditar" name="Anho" type="number" required="required" class="form-control">
				</div>
				
				<div class="form-group col-md-4">
					<label>Puntuación</label><br/>
					<input id="inputPuntuacionEditar" name="Puntuacion" type="number" step="any" required="required" class="form-control">
				</div>
			</div>
			<input type="submit" onclick="enviarFormCrearOEditar('Editar','actualizarPais.action')" Value="Editar" class="btn btn-success">
		</form>	
		</div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
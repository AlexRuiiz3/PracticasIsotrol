<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta name="viewport"
  content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><s:text name="title.validacion.presupuesto" /></title>
<!-- Scripts -->
<s:include value="/modulos/tramitacionONGD/templates/head_scripts.jsp" />
<!-- CSS  -->
<s:include value="/modulos/tramitacionONGD/templates/head_styles.jsp" />
<script type="text/javascript" src="<s:url value="/modulos/tramitacionONGD/validacionPresupuesto/js/validacionPresupuesto.js"/>"></script>
<script type="text/javascript">
  $(document) .ready( function() {
    window.tablaValidacionPresupuesto = "#tablaValidacionPresupuesto";
    window.urlRecargaTablaContribuciones = '<s:url action="recargaTablaContribuciones" namespace="/agenda/tareas" includeParams="none"/>';
    window.urlActionValidacionPresupuesto = '<s:url action="guardarValidacionPresupuesto" namespace="/agenda/tareas" includeParams="none"/>';

    initParametrizacionValidacionPresupuesto([
      '<s:text name="tabla.propiedades.validacion.presupuesto.col.0.title"/>',
      '<s:text name="tabla.propiedades.validacion.presupuesto.col.1.title"/>',
      '<s:text name="tabla.propiedades.validacion.presupuesto.col.2.title"/>',
      '<s:text name="tabla.propiedades.validacion.presupuesto.col.3.title"/>' ],
      [
      '<s:text name="tabla.propiedades.validacion.presupuesto.col.0.width"/>',
      '<s:text name="tabla.propiedades.validacion.presupuesto.col.1.width"/>',
      '<s:text name="tabla.propiedades.validacion.presupuesto.col.2.width"/>',
      '<s:text name="tabla.propiedades.validacion.presupuesto.col.3.width"/>' ],
      '<s:text name ="mensaje.mensajeSinRegistros"/>');
  });
</script>
<s:head theme="ajax" />
</head>
<body>
  <div class="imagenCabecera mb-5"></div>
  <div class="container-fluid">
    <div class="justify-content-center row">
      <div id="messagesAndErrors" class="align-self-center col-sm-12 d-none"></div>
    </div>
    <div id="divContainerPertinencia" class="container-fluid">
    <s:hidden id="tipoEntidad" />
      <form id="formValidacionPresupuesto">
        <div class="card mt-5">
          <div class="card-body">
            <div class="form-group row">
                <div class="col-sm-2 align-self-md-center">
                  <s:select label="Entidad" id="listaEntidadesValidacion" 
                    list="validaPresupuestoDTO.listaEntidadesSelect"
                    name="entidadSeleccionada" listKey="idTipoStr"
                    onchange="recargaTablaContribuciones()"
                    value="validaPresupuestoDTO.entidadStr" listValue="descripcion"
                    cssClass="w-100"/>
                  <!-- Div para alojar el label de error -->
                  <div></div>
                </div>
              <div class="col-sm-10">
                <table id="tablaValidacionPresupuesto" class="tablaValidacionPresupuesto table table-borderless table-striped table-sm">
                  <thead>
                    <tr>
                      <th scope="col" class="hide"/>
                      <th scope="col" class="col-9" title='<s:text name="tabla.propiedades.validacion.presupuesto.col.0.title"/>'><s:text name="tabla.propiedades.validacion.presupuesto.col.0.title" /></th>
                      <th scope="col" class="text-center col-1" title='<s:text name="tabla.propiedades.validacion.presupuesto.col.1.title"/>'><s:text name="tabla.propiedades.validacion.presupuesto.col.1.title" /></th>
                      <th scope="col" class="text-center col-1" title='<s:text name="tabla.propiedades.validacion.presupuesto.col.2.title"/>'><s:text name="tabla.propiedades.validacion.presupuesto.col.2.title" /></th>
                      <th scope="col" class="text-center col-1" id="" title='<s:text name="tabla.propiedades.validacion.presupuesto.col.3.title"/>'><s:text name="tabla.propiedades.validacion.presupuesto.col.3.title" /></th>
                    </tr>
                  </thead>
                  <tbody>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </form>
    </div>
    <div class="float-right mt-4 mb-4">
      <input class="btn btn-success btn-space" type="button" id="btnGuardar"
        value="GUARDAR" onclick="guardarValidacionPresupuesto()" /> <input
        class="btn btn-danger btn-space" type="button" id="btnCerrar"
        value="<s:text name="CERRAR" />"
        onclick="if (confirm('Todos los cambios que no hayan guardado se perderán. \n¿Está seguro que desea salir?')){window.close();return false; } else {return false;}"></input>
    </div>
  </div>
</body>
</html>
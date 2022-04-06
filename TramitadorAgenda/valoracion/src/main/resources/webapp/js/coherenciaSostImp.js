/**
 * Función para inicializar datos de la pestaña
 */
function initPestanaCoherenciaSostImp() {
  configuracionValidaciones();
  crearDataTable(window.tablaCoherencia, columnsDefsComunes, mensajeEmptyComun, window.urlActionListadoCoherencia);
  esONGD = esONGD === 'true';
  if (esONGD || esONGD === 'true' || $("input[name='valoracionSolicitudDTO.codigoFinalidad']").val() == "AH") {
    crearDataTable(window.tablaConectividad, columnsDefsComunes, mensajeEmptyComun, window.urlActionListadoConectividad);
  }
  if ($("input[name='valoracionSolicitudDTO.codigoFinalidad']").val() != "AH") {
    crearDataTable(window.tablaSostenibilidad, columnsDefsComunes, mensajeEmptyComun, window.urlActionListadoSostenibilidad);
  }
  crearDataTable(window.tablaImpacto, columnsDefsComunes, mensajeEmptyComun, window.urlActionListadoImpacto);
}
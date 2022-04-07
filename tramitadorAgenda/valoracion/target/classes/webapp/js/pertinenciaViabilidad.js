/**
 * Función para inicializar tablas
 */
function initPestanaPertinenciaVis() {
  crearDataTable(window.tablaPertinencia, columnsDefsComunes, mensajeEmptyComun, window.urlActionListadoPertinencia);
  crearDataTable(window.tablaViabilidad, columnsDefsComunes, mensajeEmptyComun, window.urlActionListadoViabilidad);
}
package es.juntadeandalucia.aacid.comuntramitacion.dto.reformula;

public class DatosReformulaDTO implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 4969783683502439088L;

  private String maximoAACID;
  private String minimoPresupuestoTotal;
  private String tipoSeleccionado;
  private Long idSolicitud;

  public String getMaximoAACID() {
    return maximoAACID;
  }

  public void setMaximoAACID(String maximoAACID) {
    this.maximoAACID = maximoAACID;
  }

  public String getMinimoPresupuestoTotal() {
    return minimoPresupuestoTotal;
  }

  public void setMinimoPresupuestoTotal(String minimoPresupuestoTotal) {
    this.minimoPresupuestoTotal = minimoPresupuestoTotal;
  }

  public String getTipoSeleccionado() {
    return tipoSeleccionado;
  }

  public void setTipoSeleccionado(String tipoSeleccionado) {
    this.tipoSeleccionado = tipoSeleccionado;
  }

  public Long getIdSolicitud() {
    return idSolicitud;
  }

  public void setIdSolicitud(Long idSolicitud) {
    this.idSolicitud = idSolicitud;
  }

}

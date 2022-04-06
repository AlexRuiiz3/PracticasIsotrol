package es.juntadeandalucia.aacid.comuntramitacion.dto;

/**
 * HistoricoDTO class.
 *
 * @author isotrol
 *
 */
public class HistoricoDTO {

  private String numExpediente;
  private String fechaAlta;
  private String usuarioWeb;

  /**
   * @return the numExpediente
   */
  public String getNumExpediente() {
    return numExpediente;
  }

  /**
   * @param numExpediente
   *          the numExpediente to set
   */
  public void setNumExpediente(final String numExpediente) {
    this.numExpediente = numExpediente;
  }

  /**
   * @return the fechaAlta
   */
  public String getFechaAlta() {
    return fechaAlta;
  }

  /**
   * @param fechaAlta
   *          the fechaAlta to set
   */
  public void setFechaAlta(final String fechaAlta) {
    this.fechaAlta = fechaAlta;
  }

  /**
   * @return the usuarioWeb
   */
  public String getUsuarioWeb() {
    return usuarioWeb;
  }

  /**
   * @param usuarioWeb
   *          the usuarioWeb to set
   */
  public void setUsuarioWeb(final String usuarioWeb) {
    this.usuarioWeb = usuarioWeb;
  }

}

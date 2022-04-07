package es.juntadeandalucia.aacid.portletdatosexpediente.dto;

/**
 * DTO para el bloqueo del expediente
 *
 * @author Isotrol
 */
public class BloqueoDTO {

  /** Tipo de bloqueo: expediente o fase (E,F) */
  private String tipoBloqueo;
  /** Codigo usuario . */
  private String codigoUsuario;
  /** usuario . */
  private String usuario;

  /**
   * Obtiene la propiedad codigoUsuario
   *
   * @return el codigoUsuario
   */
  public String getCodigoUsuario() {
    return codigoUsuario;
  }

  /**
   * Obtiene la propiedad usuario
   *
   * @return el usuario
   */
  public String getUsuario() {
    return usuario;
  }

  /**
   * Establece el valor de la propiedad usuario
   *
   * @param usuario
   *          el usuario para establecer
   */
  public void setUsuario(final String usuario) {
    this.usuario = usuario;
  }

  /**
   * Establece el valor de la propiedad codigoUsuario
   *
   * @param codigoUsuario
   *          el codigoUsuario para establecer
   */
  public void setCodigoUsuario(final String codigoUsuario) {
    this.codigoUsuario = codigoUsuario;
  }

  /**
   * Obtiene la propiedad tipoBloqueo
   *
   * @return el tipoBloqueo
   */
  public String getTipoBloqueo() {
    return tipoBloqueo;
  }

  /**
   * Establece el valor de la propiedad tipoBloqueo
   *
   * @param tipoBloqueo
   *          el tipoBloqueo para establecer
   */
  public void setTipoBloqueo(final String tipoBloqueo) {
    this.tipoBloqueo = tipoBloqueo;
  }

}

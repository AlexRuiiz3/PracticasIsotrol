package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.util.Date;

public class DocPoderAdminJADTO {
  private Long id;
  private String nombreDocumento;
  private String consejeriaYOrgano;
  private Date fechaEmisioPresent;
  private String procedEmitido;

  /**
   * Obtiene la propiedad id
   *
   * @return el id
   */
  public Long getId() {
    return id;
  }

  /**
   * Establece el valor de la propiedad id
   *
   * @param id
   *          el id para establecer
   */
  public void setId(final Long id) {
    this.id = id;
  }

  /**
   * Obtiene la propiedad nombreDocumento
   *
   * @return el nombreDocumento
   */
  public String getNombreDocumento() {
    return nombreDocumento;
  }

  /**
   * Establece el valor de la propiedad nombreDocumento
   *
   * @param nombreDocumento
   *          el nombreDocumento para establecer
   */
  public void setNombreDocumento(final String nombreDocumento) {
    this.nombreDocumento = nombreDocumento;
  }

  /**
   * Obtiene la propiedad consejeriaYOrgano
   *
   * @return el consejeriaYOrgano
   */
  public String getConsejeriaYOrgano() {
    return consejeriaYOrgano;
  }

  /**
   * Establece el valor de la propiedad consejeriaYOrgano
   *
   * @param consejeriaYOrgano
   *          el consejeriaYOrgano para establecer
   */
  public void setConsejeriaYOrgano(final String consejeriaYOrgano) {
    this.consejeriaYOrgano = consejeriaYOrgano;
  }

  /**
   * Obtiene la propiedad fechaEmisioPresent
   *
   * @return el fechaEmisioPresent
   */
  public Date getFechaEmisioPresent() {
    return fechaEmisioPresent;
  }

  /**
   * Establece el valor de la propiedad fechaEmisioPresent
   *
   * @param fechaEmisioPresent
   *          el fechaEmisioPresent para establecer
   */
  public void setFechaEmisioPresent(final Date fechaEmisioPresent) {
    this.fechaEmisioPresent = fechaEmisioPresent;
  }

  /**
   * Obtiene la propiedad procedEmitido
   *
   * @return el procedEmitido
   */
  public String getProcedEmitido() {
    return procedEmitido;
  }

  /**
   * Establece el valor de la propiedad procedEmitido
   *
   * @param procedEmitido
   *          el procedEmitido para establecer
   */
  public void setProcedEmitido(final String procedEmitido) {
    this.procedEmitido = procedEmitido;
  }

}

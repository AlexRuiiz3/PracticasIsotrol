package es.juntadeandalucia.aacid.comuntramitacion.dto;

public class TipoEntidadParticipanteDTO {
  private Integer id;
  private String descripcion;
  private String codigo;

  /**
   * Obtiene la propiedad id
   *
   * @return el id
   */
  public Integer getId() {
    return id;
  }

  /**
   * Establece el valor de la propiedad id
   *
   * @param id
   *          el id para establecer
   */
  public void setId(final Integer id) {
    this.id = id;
  }

  /**
   * Obtiene la propiedad descripcion
   *
   * @return el descripcion
   */
  public String getDescripcion() {
    return descripcion;
  }

  /**
   * Establece el valor de la propiedad descripcion
   *
   * @param descripcion
   *          el descripcion para establecer
   */
  public void setDescripcion(final String descripcion) {
    this.descripcion = descripcion;
  }

  /**
   * Obtiene la propiedad codigo
   *
   * @return el codigo
   */
  public String getCodigo() {
    return codigo;
  }

  /**
   * Establece el valor de la propiedad codigo
   *
   * @param codigo
   *          el codigo para establecer
   */
  public void setCodigo(final String codigo) {
    this.codigo = codigo;
  }

}

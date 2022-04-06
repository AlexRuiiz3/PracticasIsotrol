package es.juntadeandalucia.aacid.comuntramitacion.dto;

public class ExclusionDTO implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -6328999395676138009L;
  private Long idExclusion;
  private String descripcion;
  private String idEditar;

  /**
   * Constructor por defecto
   */
  public ExclusionDTO() {
    super();
  }

  /**
   * @param idExclusion
   * @param descripcion
   * @param idEditar
   */
  public ExclusionDTO(Long idExclusion, String descripcion, String idEditar) {
    this.idExclusion = idExclusion;
    this.descripcion = descripcion;
    this.idEditar = idEditar;
  }

  /**
   * Obtiene la propiedad idExclusion
   * 
   * @return el idExclusion
   */
  public Long getIdExclusion() {
    return idExclusion;
  }

  /**
   * Establece el valor de la propiedad idExclusion
   * 
   * @param idExclusion
   *          el idExclusion para establecer
   */
  public void setIdExclusion(Long idExclusion) {
    this.idExclusion = idExclusion;
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
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getIdEditar() {
    return idEditar;
  }

  public void setIdEditar(String idEditar) {
    this.idEditar = idEditar;
  }
}

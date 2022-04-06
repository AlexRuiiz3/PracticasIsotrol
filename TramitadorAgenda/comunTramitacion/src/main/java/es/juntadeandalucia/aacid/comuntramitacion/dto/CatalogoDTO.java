package es.juntadeandalucia.aacid.comuntramitacion.dto;

public class CatalogoDTO {

  private Integer id;
  private String descripcion;
  private boolean motivo;
  private String nombreCompleto;
  private String codigo;
  private boolean puedeEditar;

  public CatalogoDTO(Integer id, String descripcion, boolean motivo, String nombreCompleto) {
    this.id = id;
    this.descripcion = descripcion;
    this.motivo = motivo;
    this.nombreCompleto = nombreCompleto;
  }

  /**
   * @param descripcion
   */
  public CatalogoDTO(String descripcion) {
    super();
    this.descripcion = descripcion;
  }

  public CatalogoDTO() {
    super();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public boolean isMotivo() {
    return motivo;
  }

  public void setMotivo(boolean motivo) {
    this.motivo = motivo;
  }

  public String getNombreCompleto() {
    return nombreCompleto;
  }

  public void setNombreCompleto(String nombreCompleto) {
    this.nombreCompleto = nombreCompleto;
  }

  /**
   * @return the codigo
   */
  public String getCodigo() {
    return codigo;
  }

  /**
   * @param codigo
   *          the codigo to set
   */
  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public boolean isPuedeEditar() {
    return puedeEditar;
  }

  public void setPuedeEditar(boolean puedeEditar) {
    this.puedeEditar = puedeEditar;
  }
}

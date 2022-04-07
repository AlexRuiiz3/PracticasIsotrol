package es.juntadeandalucia.aacid.comuntramitacion.dto;

/**
 * CatalogoSubsanacionDTO class.
 * 
 * @author isotrol.
 *
 */
public class CatalogoSubsanacionDTO {

  /** motivo */
  private String motivo;
  /** catalogo */
  private CatalogoDTO catalogo;

  /** puede editar el catalogo subsanacion */
  private boolean puedeEditar;

  private String codigo;

  /**
   * @param motivo
   * @param descripcion
   */
  public CatalogoSubsanacionDTO(String motivo, String descripcion) {
    super();
    this.motivo = motivo;
    this.catalogo = new CatalogoDTO(descripcion);
  }

  /**
   * @param motivo
   * @param descripcion
   * @param codigo
   */
  public CatalogoSubsanacionDTO(String motivo, String descripcion, String codigo) {
    super();
    this.motivo = motivo;
    this.catalogo = new CatalogoDTO(descripcion);
    this.setCodigo(codigo);
  }

  /**
   * Constructor por defecto
   */
  public CatalogoSubsanacionDTO() {
    super();
  }

  /**
   * @return the motivo
   */
  public String getMotivo() {
    return motivo;
  }

  /**
   * @param motivo
   *          the motivo to set
   */
  public void setMotivo(String motivo) {
    this.motivo = motivo;
  }

  /**
   * @return the catalogo
   */
  public CatalogoDTO getCatalogo() {
    return catalogo;
  }

  /**
   * @param catalogo
   *          the catalogo to set
   */
  public void setCatalogo(CatalogoDTO catalogo) {
    this.catalogo = catalogo;
  }

  public boolean isPuedeEditar() {
    return puedeEditar;
  }

  public void setPuedeEditar(boolean puedeEditar) {
    this.puedeEditar = puedeEditar;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }
}

package es.juntadeandalucia.aacid.comuntramitacion.dto;

public class GastoDTO {

  private Long idGasto;
  private String codigo;
  private String descripcion;
  private Long orden;
  private String descripcionCodigo;

  public GastoDTO(final String codigo, final String descripcion, final Long orden, final Long idGasto) {
    this.codigo = codigo;
    this.descripcion = descripcion;
    this.orden = orden;
    this.idGasto = idGasto;
    setDescripcionCodigo(codigo + " " + descripcion);
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(final String codigo) {
    this.codigo = codigo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(final String descripcion) {
    this.descripcion = descripcion;
  }

  public Long getOrden() {
    return orden;
  }

  public void setOrden(final Long orden) {
    this.orden = orden;
  }

  public Long getIdGasto() {
    return idGasto;
  }

  public void setIdGasto(final Long idGasto) {
    this.idGasto = idGasto;
  }

  public String getDescripcionCodigo() {
    return descripcionCodigo;
  }

  public void setDescripcionCodigo(final String descripcionCodigo) {
    this.descripcionCodigo = descripcionCodigo;
  }
}

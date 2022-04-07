package es.juntadeandalucia.aacid.comuntramitacion.dto.reformula;

import es.juntadeandalucia.aacid.comuntramitacion.objects.Importe;

public class ReformulaDTO {

  private Importe maximo;
  private Importe minimo;
  private String tipo;
  private Long idSolicitud;

  public Importe getMaximo() {
    return maximo;
  }

  public void setMaximo(Importe maximo) {
    this.maximo = maximo;
  }

  public Importe getMinimo() {
    return minimo;
  }

  public void setMinimo(Importe minimo) {
    this.minimo = minimo;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public Long getIdSolicitud() {
    return idSolicitud;
  }

  public void setIdSolicitud(Long idSolicitud) {
    this.idSolicitud = idSolicitud;
  }

}

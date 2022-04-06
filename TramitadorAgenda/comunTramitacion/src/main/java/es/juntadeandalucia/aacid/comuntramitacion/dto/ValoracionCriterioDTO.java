package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.math.BigDecimal;

/**
 * 
 * @author isotrol
 *
 */
public class ValoracionCriterioDTO {

  private Long idCriterio;
  private BigDecimal valoracion;
  private String descripcion;
  private BigDecimal puntuacion;
  private BigDecimal valorMaximo;
  private String puntuacionStr;
  private String valorMaximoStr;

  public ValoracionCriterioDTO() {
    super();
  }

  public ValoracionCriterioDTO(Long idCriterio, BigDecimal valoracion, BigDecimal puntuacion) {
    super();
    this.idCriterio = idCriterio;
    this.valoracion = valoracion;
    this.puntuacion = puntuacion;
  }

  /**
   * @return the idCriterio
   */
  public Long getIdCriterio() {
    return idCriterio;
  }

  /**
   * @param idCriterio
   *          the idCriterio to set
   */
  public void setIdCriterio(Long idCriterio) {
    this.idCriterio = idCriterio;
  }

  /**
   * @return the valoracion
   */
  public BigDecimal getValoracion() {
    return valoracion;
  }

  /**
   * @param valoracion
   *          the valoracion to set
   */
  public void setValoracion(BigDecimal valoracion) {
    this.valoracion = valoracion;
  }

  /**
   * @return the descripcion
   */
  public String getDescripcion() {
    return descripcion;
  }

  /**
   * @param descripcion
   *          the descripcion to set
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  /**
   * @return the puntuacion
   */
  public BigDecimal getPuntuacion() {
    return puntuacion;
  }

  /**
   * @param puntuacion
   *          the puntuacion to set
   */
  public void setPuntuacion(BigDecimal puntuacion) {
    this.puntuacion = puntuacion;
  }

  /**
   * @return the valorMaximo
   */
  public BigDecimal getValorMaximo() {
    return valorMaximo;
  }

  /**
   * @param valorMaximo
   *          the valorMaximo to set
   */
  public void setValorMaximo(BigDecimal valorMaximo) {
    this.valorMaximo = valorMaximo;
  }

public String getPuntuacionStr() {
	return puntuacionStr;
}

public void setPuntuacionStr(String puntuacionStr) {
	this.puntuacionStr = puntuacionStr;
}

public String getValorMaximoStr() {
	return valorMaximoStr;
}

public void setValorMaximoStr(String valorMaximoStr) {
	this.valorMaximoStr = valorMaximoStr;
}

}

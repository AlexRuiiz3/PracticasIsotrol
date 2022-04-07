package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author isotrol
 *
 */
public class ValoracionTipoCriterioDTO {

  private List<ValoracionCriterioDTO> listaValoraciones;
  private BigDecimal totalPuntuacion;
  private BigDecimal puntuacionMaxima;
  private String observaciones;
  private String nombreTipoCriterio;
  private String totalPuntuacionStr;
  private String puntuacionMaximaStr;

  /**
   * @return the listaValoraciones
   */
  public List<ValoracionCriterioDTO> getListaValoraciones() {
    return listaValoraciones;
  }

  /**
   * @param listaValoraciones
   *          the listaValoraciones to set
   */
  public void setListaValoraciones(final List<ValoracionCriterioDTO> listaValoraciones) {
    this.listaValoraciones = listaValoraciones;
  }

  /**
   * @return the totalPuntuacion
   */
  public BigDecimal getTotalPuntuacion() {
    return totalPuntuacion;
  }

  /**
   * @param totalPuntuacion
   *          the totalPuntuacion to set
   */
  public void setTotalPuntuacion(final BigDecimal totalPuntuacion) {
    this.totalPuntuacion = totalPuntuacion;
  }

  /**
   * @return the observaciones
   */
  public String getObservaciones() {
    return observaciones;
  }

  /**
   * @param observaciones
   *          the observaciones to set
   */
  public void setObservaciones(final String observaciones) {
    this.observaciones = observaciones;
  }

  /**
   * Obtiene la propiedad puntuacionMaxima
   *
   * @return el puntuacionMaxima
   */
  public BigDecimal getPuntuacionMaxima() {
    return puntuacionMaxima;
  }

  /**
   * Establece el valor de la propiedad puntuacionMaxima
   *
   * @param puntuacionMaxima
   *          el puntuacionMaxima para establecer
   */
  public void setPuntuacionMaxima(final BigDecimal puntuacionMaxima) {
    this.puntuacionMaxima = puntuacionMaxima;
  }

  /**
   * Obtiene la propiedad nombreTipoCriterio
   *
   * @return el nombreTipoCriterio
   */
  public String getNombreTipoCriterio() {
    return nombreTipoCriterio;
  }

  /**
   * Establece el valor de la propiedad nombreTipoCriterio
   *
   * @param nombreTipoCriterio
   *          el nombreTipoCriterio para establecer
   */
  public void setNombreTipoCriterio(final String nombreTipoCriterio) {
    this.nombreTipoCriterio = nombreTipoCriterio;
  }

public String getTotalPuntuacionStr() {
	return totalPuntuacionStr;
}

public void setTotalPuntuacionStr(String totalPuntuacionStr) {
	this.totalPuntuacionStr = totalPuntuacionStr;
}

public String getPuntuacionMaximaStr() {
	return puntuacionMaximaStr;
}

public void setPuntuacionMaximaStr(String puntuacionMaximaStr) {
	this.puntuacionMaximaStr = puntuacionMaximaStr;
}

}

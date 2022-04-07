package es.juntadeandalucia.aacid.comuntramitacion.dto;

public class TipoExclusionDTO {

  private Integer orden;
  private String anio;
  private Integer tipo;

  /**
   * Obtiene la propiedad orden
   * 
   * @return el orden
   */
  public Integer getOrden() {
    return orden;
  }

  /**
   * Establece el valor de la propiedad orden
   * 
   * @param orden
   *          el orden para establecer
   */
  public void setOrden(Integer orden) {
    this.orden = orden;
  }

  /**
   * Obtiene la propiedad anio
   * 
   * @return el anio
   */
  public String getAnio() {
    return anio;
  }

  /**
   * Establece el valor de la propiedad anio
   * 
   * @param anio
   *          el anio para establecer
   */
  public void setAnio(String anio) {
    this.anio = anio;
  }

  /**
   * Obtiene la propiedad tipo
   * 
   * @return el tipo
   */
  public Integer getTipo() {
    return tipo;
  }

  /**
   * Establece el valor de la propiedad tipo
   * 
   * @param tipo
   *          el tipo para establecer
   */
  public void setTipo(Integer tipo) {
    this.tipo = tipo;
  }
}

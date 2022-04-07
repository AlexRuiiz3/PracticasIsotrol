package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.io.Serializable;


/**
 * Contraparte
 * 
 * @author Isotrol
 */
public class ContraparteDTO implements Serializable {

  /** Serial id */
  private static final long serialVersionUID = 4896822284751542365L;

  private Long id;
  private String contraparte;
  private String pais;
  private String localidad;
  private TipoEntidadContraparteDTO tipoEntidadContraparteDTO;

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
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Obtiene la propiedad contraparte
   * 
   * @return el contraparte
   */
  public String getContraparte() {
    return contraparte;
  }

  /**
   * Establece el valor de la propiedad contraparte
   * 
   * @param contraparte
   *          el contraparte para establecer
   */
  public void setContraparte(String contraparte) {
    this.contraparte = contraparte;
  }

  /**
   * Obtiene la propiedad pais
   * 
   * @return el pais
   */
  public String getPais() {
    return pais;
  }

  /**
   * Establece el valor de la propiedad pais
   * 
   * @param pais
   *          el pais para establecer
   */
  public void setPais(String pais) {
    this.pais = pais;
  }

  /**
   * Obtiene la propiedad localidad
   * 
   * @return el localidad
   */
  public String getLocalidad() {
    return localidad;
  }

  /**
   * Establece el valor de la propiedad localidad
   * 
   * @param localidad
   *          el localidad para establecer
   */
  public void setLocalidad(String localidad) {
    this.localidad = localidad;
  }

  public TipoEntidadContraparteDTO getTipoEntidadContraparteDTO() {
    return tipoEntidadContraparteDTO;
  }

  public void setTipoEntidadContraparteDTO(TipoEntidadContraparteDTO tipoEntidadContraparteDTO) {
    this.tipoEntidadContraparteDTO = tipoEntidadContraparteDTO;
  }
}

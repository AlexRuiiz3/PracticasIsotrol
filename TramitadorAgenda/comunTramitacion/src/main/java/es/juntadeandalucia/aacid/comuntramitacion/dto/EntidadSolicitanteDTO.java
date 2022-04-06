package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.io.Serializable;

/**
 * Entidad Solicitante
 * 
 * @author Isotrol
 */
public class EntidadSolicitanteDTO implements Serializable {

  /** Serial id */
  private static final long serialVersionUID = 4896822284751542365L;

  private Long idEntidad;
  private String nombreEntidad;
  private String cif;
  private String email;
  private String direccion;
  private String codPostal;
  private String localidad;
  private String provincia;

  /**
   * Obtiene la propiedad nombreEntidad
   * 
   * @return el nombreEntidad
   */
  public String getNombreEntidad() {
    return nombreEntidad;
  }

  /**
   * Establece el valor de la propiedad nombreEntidad
   * 
   * @param nombreEntidad
   *          el nombreEntidad para establecer
   */
  public void setNombreEntidad(String nombreEntidad) {
    this.nombreEntidad = nombreEntidad;
  }

  /**
   * Obtiene la propiedad cif
   * 
   * @return el cif
   */
  public String getCif() {
    return cif;
  }

  /**
   * Establece el valor de la propiedad cif
   * 
   * @param cif
   *          el cif para establecer
   */
  public void setCif(String cif) {
    this.cif = cif;
  }

  /**
   * Obtiene la propiedad email
   * 
   * @return el email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Establece el valor de la propiedad email
   * 
   * @param email
   *          el email para establecer
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Obtiene la propiedad direccion
   * 
   * @return el direccion
   */
  public String getDireccion() {
    return direccion;
  }

  /**
   * Establece el valor de la propiedad direccion
   * 
   * @param direccion
   *          el direccion para establecer
   */
  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  /**
   * Obtiene la propiedad codPostal
   * 
   * @return el codPostal
   */
  public String getCodPostal() {
    return codPostal;
  }

  /**
   * Establece el valor de la propiedad codPostal
   * 
   * @param codPostal
   *          el codPostal para establecer
   */
  public void setCodPostal(String codPostal) {
    this.codPostal = codPostal;
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

  /**
   * Obtiene la propiedad provincia
   * 
   * @return el provincia
   */
  public String getProvincia() {
    return provincia;
  }

  /**
   * Establece el valor de la propiedad provincia
   * 
   * @param provincia
   *          el provincia para establecer
   */
  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }

  /**
   * Obtiene la propiedad idEntidad
   *
   * @return el idEntidad
   */
  public Long getIdEntidad() {
    return idEntidad;
  }

  /**
   * Establece el valor de la propiedad idEntidad
   *
   * @param idEntidad
   *          el idEntidad para establecer
   */
  public void setIdEntidad(final Long idEntidad) {
    this.idEntidad = idEntidad;
  }
}

package es.juntadeandalucia.aacid.portletdatosexpediente.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO para los datos a mostrar en el portlet
 *
 * @author Isotrol
 */
public class PortletDatosExpedienteDTO implements Serializable {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 2124892967574341163L;

  /** numero codUsuario. */
  private String codUsuario;
  /** numero idExpediente. */
  private Long idExpediente;
  /** numero expediente. */
  private String numeroExpediente;
  /** fecha registro. */
  private Date fechaRegistro;
  /** fecha computo (es la fecha de registro formateado a String). **/
  private String fechaComputo;
  /** descripcion procedimiento */
  private String desProcedimiento;
  /** descripcion unidad organica */
  private String desUnidadOrganica;
  /** objeto con la informacion del bloqueo **/
  private transient BloqueoDTO bloqueoDTO;
  /** boolean que indica si el expediente esta caducado **/
  private boolean caducado;
  /** boolean que indica si el usuario en session es el mismo que el usuario que bloquea el expediente **/
  private boolean usuSesionEsUsuBloqueo;
  /** Nombre entidad solicitante */
  private String entidad;
  /** Título de proyecto */
  private String titulo;
  /** Num. Expediente AACID */
  private String numAACID;

  /**
   * Constructor PortletDatosExpedienteDto.
   */
  public PortletDatosExpedienteDTO() {

  }

  /**
   * Constructor con parametros
   *
   * @param codUsuario
   * @param numeroExpediente
   * @param fechaRegistro
   * @param desProcedimiento
   * @param desUnidadOrganica
   * @param bloqueoDTO
   * @param caducado
   */

  public PortletDatosExpedienteDTO(final String codUsuario, final String numeroExpediente, final Date fechaRegistro, final String desProcedimiento,
      final String desUnidadOrganica, final BloqueoDTO bloqueoDTO, final boolean caducado) {
    this.codUsuario = codUsuario;
    this.numeroExpediente = numeroExpediente;
    this.fechaRegistro = fechaRegistro;
    this.desProcedimiento = desProcedimiento;
    this.desUnidadOrganica = desUnidadOrganica;
    this.bloqueoDTO = bloqueoDTO;
    this.caducado = caducado;
  }

  /**
   * Obtiene la propiedad idExpediente
   *
   * @return el idExpediente
   */
  public Long getIdExpediente() {
    return idExpediente;
  }

  /**
   * Establece el valor de la propiedad idExpediente
   *
   * @param idExpediente
   *          el idExpediente para establecer
   */
  public void setIdExpediente(final Long idExpediente) {
    this.idExpediente = idExpediente;
  }

  /**
   * Obtiene la propiedad numeroExpediente
   *
   * @return el numeroExpediente
   */
  public String getNumeroExpediente() {
    return numeroExpediente;
  }

  /**
   * Establece el valor de la propiedad numeroExpediente
   *
   * @param numeroExpediente
   *          el numeroExpediente para establecer
   */
  public void setNumeroExpediente(final String numeroExpediente) {
    this.numeroExpediente = numeroExpediente;
  }

  /**
   * Obtiene la propiedad desProcedimiento
   *
   * @return el desProcedimiento
   */
  public String getDesProcedimiento() {
    return desProcedimiento;
  }

  /**
   * Establece el valor de la propiedad desProcedimiento
   *
   * @param desProcedimiento
   *          el desProcedimiento para establecer
   */
  public void setDesProcedimiento(final String desProcedimiento) {
    this.desProcedimiento = desProcedimiento;
  }

  /**
   * Obtiene la propiedad desUnidadOrganica
   *
   * @return el desUnidadOrganica
   */
  public String getDesUnidadOrganica() {
    return desUnidadOrganica;
  }

  /**
   * Establece el valor de la propiedad desUnidadOrganica
   *
   * @param desUnidadOrganica
   *          el desUnidadOrganica para establecer
   */
  public void setDesUnidadOrganica(final String desUnidadOrganica) {
    this.desUnidadOrganica = desUnidadOrganica;
  }

  /**
   * Obtiene la propiedad fechaRegistro
   *
   * @return el fechaRegistro
   */
  public Date getFechaRegistro() {
    return fechaRegistro;
  }

  /**
   * Establece el valor de la propiedad fechaRegistro
   *
   * @param fechaRegistro
   *          el fechaRegistro para establecer
   */
  public void setFechaRegistro(final Date fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
  }

  /**
   * Obtiene la propiedad fechaComputo
   *
   * @return el fechaComputo
   */
  public String getFechaComputo() {
    return fechaComputo;
  }

  /**
   * Establece el valor de la propiedad fechaComputo
   *
   * @param fechaComputo
   *          el fechaComputo para establecer
   */
  public void setFechaComputo(final String fechaComputo) {
    this.fechaComputo = fechaComputo;
  }

  /**
   * Obtiene la propiedad caducado
   *
   * @return el caducado
   */
  public boolean isCaducado() {
    return caducado;
  }

  /**
   * Establece el valor de la propiedad caducado
   *
   * @param caducado
   *          el caducado para establecer
   */
  public void setCaducado(final boolean caducado) {
    this.caducado = caducado;
  }

  /**
   * Obtiene la propiedad codUsuario
   *
   * @return el codUsuario
   */
  public String getCodUsuario() {
    return codUsuario;
  }

  /**
   * Establece el valor de la propiedad codUsuario
   *
   * @param codUsuario
   *          el codUsuario para establecer
   */
  public void setCodUsuario(final String codUsuario) {
    this.codUsuario = codUsuario;
  }

  /**
   * Establece el valor de la propiedad usuSesionEsUsuBloqueo
   *
   * @param usuSesionEsUsuBloqueo
   *          el usuSesionEsUsuBloqueo para establecer
   */
  public void setUsuSesionEsUsuBloqueo(final boolean usuSesionEsUsuBloqueo) {
    this.usuSesionEsUsuBloqueo = usuSesionEsUsuBloqueo;
  }

  /**
   * Obtiene la propiedad usuSesionEsUsuBloqueo
   *
   * @return el usuSesionEsUsuBloqueo
   */
  public boolean isUsuSesionEsUsuBloqueo() {
    return usuSesionEsUsuBloqueo;
  }

  /**
   * Obtiene la propiedad bloqueoDTO
   *
   * @return el bloqueoDTO
   */
  public BloqueoDTO getBloqueoDTO() {
    return bloqueoDTO;
  }

  /**
   * Establece el valor de la propiedad bloqueoDTO
   *
   * @param bloqueoDTO
   *          el bloqueoDTO para establecer
   */
  public void setBloqueoDTO(final BloqueoDTO bloqueoDTO) {
    this.bloqueoDTO = bloqueoDTO;
  }

  /**
   * Obtiene la propiedad entidad
   *
   * @return el entidad
   */
  public String getEntidad() {
    return entidad;
  }

  /**
   * Establece el valor de la propiedad entidad
   *
   * @param entidad
   *          el entidad para establecer
   */
  public void setEntidad(final String entidad) {
    this.entidad = entidad;
  }

  /**
   * Obtiene la propiedad titulo
   *
   * @return el titulo
   */
  public String getTitulo() {
    return titulo;
  }

  /**
   * Establece el valor de la propiedad titulo
   *
   * @param titulo
   *          el titulo para establecer
   */
  public void setTitulo(final String titulo) {
    this.titulo = titulo;
  }

  /**
   * Obtiene la propiedad numAACID
   *
   * @return el numAACID
   */
  public String getNumAACID() {
    return numAACID;
  }

  /**
   * Establece el valor de la propiedad numAACID
   *
   * @param numAACID
   *          el numAACID para establecer
   */
  public void setNumAACID(final String numAACID) {
    this.numAACID = numAACID;
  }

}

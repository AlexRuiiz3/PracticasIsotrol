package es.juntadeandalucia.aacid.comuntramitacion.dto;

public class EntidadParticipanteDTO {

  private Long id;
  private String nombre;
  private String otraFuncion;
  private FuncionesDTO funcion;
  private SolicitudDTO solicitud;
  private TipoEntidadParticipanteDTO tipoEntidadParticipante;

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
  public void setId(final Long id) {
    this.id = id;
  }

  /**
   * Obtiene la propiedad nombre
   *
   * @return el nombre
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Establece el valor de la propiedad nombre
   *
   * @param nombre
   *          el nombre para establecer
   */
  public void setNombre(final String nombre) {
    this.nombre = nombre;
  }

  /**
   * Obtiene la propiedad otraFuncion
   *
   * @return el otraFuncion
   */
  public String getOtraFuncion() {
    return otraFuncion;
  }

  /**
   * Establece el valor de la propiedad otraFuncion
   *
   * @param otraFuncion
   *          el otraFuncion para establecer
   */
  public void setOtraFuncion(final String otraFuncion) {
    this.otraFuncion = otraFuncion;
  }

  /**
   * Obtiene la propiedad funcion
   *
   * @return el funcion
   */
  public FuncionesDTO getFuncion() {
    return funcion;
  }

  /**
   * Establece el valor de la propiedad funcion
   *
   * @param funcion
   *          el funcion para establecer
   */
  public void setFuncion(final FuncionesDTO funcion) {
    this.funcion = funcion;
  }

  /**
   * Obtiene la propiedad solicitud
   *
   * @return el solicitud
   */
  public SolicitudDTO getSolicitud() {
    return solicitud;
  }

  /**
   * Establece el valor de la propiedad solicitud
   *
   * @param solicitud
   *          el solicitud para establecer
   */
  public void setSolicitud(final SolicitudDTO solicitud) {
    this.solicitud = solicitud;
  }

  /**
   * Obtiene la propiedad tipoEntidadParticipante
   *
   * @return el tipoEntidadParticipante
   */
  public TipoEntidadParticipanteDTO getTipoEntidadParticipante() {
    return tipoEntidadParticipante;
  }

  /**
   * Establece el valor de la propiedad tipoEntidadParticipante
   *
   * @param tipoEntidadParticipante
   *          el tipoEntidadParticipante para establecer
   */
  public void setTipoEntidadParticipante(final TipoEntidadParticipanteDTO tipoEntidadParticipante) {
    this.tipoEntidadParticipante = tipoEntidadParticipante;
  }
}

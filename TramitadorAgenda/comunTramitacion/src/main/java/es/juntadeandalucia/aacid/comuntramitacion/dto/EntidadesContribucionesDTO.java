package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.util.ArrayList;
import java.util.List;

public class EntidadesContribucionesDTO {
  private Long id;
  private String descripcion;
  private String descripcionTitle;
  private String tipo;
  private List<GastosContribucionDTO> valoresAI;
  private List<GastosContribucionDTO> valoresAII;
  private ContraparteDTO contraparteDTO;
  private EntidadParticipanteDTO entidadesParticipantes;
  private List<GastosContribucionDTO> listaNuevaEntidad;

  public EntidadesContribucionesDTO() {

  }

  public EntidadesContribucionesDTO(final String descripcion, final String descripcionTitle, final String tipo) {
    this.descripcion = descripcion;
    this.descripcionTitle = descripcionTitle;
    this.tipo = tipo;
    valoresAI = new ArrayList<>();
    valoresAI = new ArrayList<>();
  }

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
   * Obtiene la propiedad descripcion
   *
   * @return el descripcion
   */
  public String getDescripcion() {
    return descripcion;
  }

  /**
   * Establece el valor de la propiedad descripcion
   *
   * @param descripcion
   *          el descripcion para establecer
   */
  public void setDescripcion(final String descripcion) {
    this.descripcion = descripcion;
  }

  /**
   * Obtiene la propiedad descripcionTitle
   *
   * @return el descripcionTitle
   */
  public String getDescripcionTitle() {
    return descripcionTitle;
  }

  /**
   * Establece el valor de la propiedad descripcionTitle
   *
   * @param descripcionTitle
   *          el descripcionTitle para establecer
   */
  public void setDescripcionTitle(final String descripcionTitle) {
    this.descripcionTitle = descripcionTitle;
  }

  /**
   * Obtiene la propiedad tipo
   *
   * @return el tipo
   */
  public String getTipo() {
    return tipo;
  }

  /**
   * Establece el valor de la propiedad tipo
   *
   * @param tipo
   *          el tipo para establecer
   */
  public void setTipo(final String tipo) {
    this.tipo = tipo;
  }

  /**
   * Obtiene la propiedad valoresAI
   *
   * @return el valoresAI
   */
  public List<GastosContribucionDTO> getValoresAI() {
    return valoresAI;
  }

  /**
   * Establece el valor de la propiedad valoresAI
   *
   * @param valoresAI
   *          el valoresAI para establecer
   */
  public void setValoresAI(final List<GastosContribucionDTO> valoresAI) {
    this.valoresAI = valoresAI;
  }

  /**
   * Obtiene la propiedad valoresAII
   *
   * @return el valoresAII
   */
  public List<GastosContribucionDTO> getValoresAII() {
    return valoresAII;
  }

  /**
   * Establece el valor de la propiedad valoresAII
   *
   * @param valoresAII
   *          el valoresAII para establecer
   */
  public void setValoresAII(final List<GastosContribucionDTO> valoresAII) {
    this.valoresAII = valoresAII;
  }

  /**
   * Obtiene la propiedad contraparteDTO
   *
   * @return el contraparteDTO
   */
  public ContraparteDTO getContraparteDTO() {
    return contraparteDTO;
  }

  /**
   * Establece el valor de la propiedad contraparteDTO
   *
   * @param contraparteDTO
   *          el contraparteDTO para establecer
   */
  public void setContraparteDTO(final ContraparteDTO contraparteDTO) {
    this.contraparteDTO = contraparteDTO;
  }

  /**
   * Obtiene la propiedad entidadesParticipantes
   *
   * @return el entidadesParticipantes
   */
  public EntidadParticipanteDTO getEntidadesParticipantes() {
    return entidadesParticipantes;
  }

  /**
   * Establece el valor de la propiedad entidadesParticipantes
   *
   * @param entidadesParticipantes
   *          el entidadesParticipantes para establecer
   */
  public void setEntidadesParticipantes(final EntidadParticipanteDTO entidadesParticipantes) {
    this.entidadesParticipantes = entidadesParticipantes;
  }

  /**
   * Obtiene la propiedad listaNuevaEntidad
   *
   * @return el listaNuevaEntidad
   */
  public List<GastosContribucionDTO> getListaNuevaEntidad() {
    return listaNuevaEntidad;
  }

  /**
   * Establece el valor de la propiedad listaNuevaEntidad
   *
   * @param listaNuevaEntidad
   *          el listaNuevaEntidad para establecer
   */
  public void setListaNuevaEntidad(final List<GastosContribucionDTO> listaNuevaEntidad) {
    this.listaNuevaEntidad = listaNuevaEntidad;
  }

  /**
   * Obtiene la propiedad idTipoStr
   *
   * @return el idTipoStr
   */
  public String getIdTipoStr() {
    return id + "_" + tipo;
  }
}

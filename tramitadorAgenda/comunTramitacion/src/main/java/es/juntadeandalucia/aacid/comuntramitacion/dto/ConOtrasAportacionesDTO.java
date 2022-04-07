package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.math.BigDecimal;

public class ConOtrasAportacionesDTO {

  private Long idConOtra;
  private Long contribuciones;
  private BigDecimal contribucion;
  private BigDecimal contribucionValidada;
  private BigDecimal contribucionNoValidada;
  private BigDecimal otrasEmergencia;
  private EntidadParticipanteDTO entidadParticipanteDTO;
  private ContribucionDTO contribucionDTO;

  public Long getIdConOtra() {
    return idConOtra;
  }

  public void setIdConOtra(final Long idConOtra) {
    this.idConOtra = idConOtra;
  }

  public Long getContribuciones() {
    return contribuciones;
  }

  public void setContribuciones(final Long contribuciones) {
    this.contribuciones = contribuciones;
  }

  public BigDecimal getContribucion() {
    return contribucion;
  }

  public void setContribucion(final BigDecimal contribucion) {
    this.contribucion = contribucion;
  }

  public BigDecimal getContribucionValidada() {
    return contribucionValidada;
  }

  public void setContribucionValidada(final BigDecimal contribucionValidada) {
    this.contribucionValidada = contribucionValidada;
  }

  public BigDecimal getContribucionNoValidada() {
    return contribucionNoValidada;
  }

  public void setContribucionNoValidada(final BigDecimal contribucionNoValidada) {
    this.contribucionNoValidada = contribucionNoValidada;
  }

  public BigDecimal getOtrasEmergencia() {
    return otrasEmergencia;
  }

  public void setOtrasEmergencia(final BigDecimal otrasEmergencia) {
    this.otrasEmergencia = otrasEmergencia;
  }

  public EntidadParticipanteDTO getEntidadesParticipantesDTO() {
    return entidadParticipanteDTO;
  }

  public void setEntidadesParticipantesDTO(final EntidadParticipanteDTO entidadParticipanteDTO) {
    this.entidadParticipanteDTO = entidadParticipanteDTO;
  }

  public ContribucionDTO getContribucionDTO() {
    return contribucionDTO;
  }

  public void setContribucionDTO(final ContribucionDTO contribucionDTO) {
    this.contribucionDTO = contribucionDTO;
  }

}

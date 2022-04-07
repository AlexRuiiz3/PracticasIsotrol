package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import java.math.BigDecimal;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ConOtrasAportacionesDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.ConOtrasAportaciones;

public class ConOtrasAportacionesConverter {

  public ConOtrasAportacionesDTO convertConOtrasAportacionesDaoToDTO(final ConOtrasAportaciones conOtrasAportaciones) {
    final ConOtrasAportacionesDTO conOtrasAportacionesDTO = new ConOtrasAportacionesDTO();
    conOtrasAportacionesDTO.setIdConOtra(conOtrasAportaciones.getIdConOtra());
    conOtrasAportacionesDTO.setContribucion(conOtrasAportaciones.getContribucion() != null ? conOtrasAportaciones.getContribucion() : BigDecimal.ZERO);
    conOtrasAportacionesDTO
        .setContribuciones(conOtrasAportaciones.getContribuciones() != null ? conOtrasAportaciones.getContribuciones().getIdContribucion() : null);
    conOtrasAportacionesDTO.setContribucionNoValidada(
        conOtrasAportaciones.getContribucionNoValidada() != null ? conOtrasAportaciones.getContribucionNoValidada() : BigDecimal.ZERO);
    conOtrasAportacionesDTO
        .setContribucionValidada(conOtrasAportaciones.getContribucionValidada() != null ? conOtrasAportaciones.getContribucionValidada() : BigDecimal.ZERO);
    conOtrasAportacionesDTO.setOtrasEmergencia(conOtrasAportaciones.getOtrasEmergencia() != null ? conOtrasAportaciones.getOtrasEmergencia() : BigDecimal.ZERO);
    conOtrasAportacionesDTO
        .setEntidadesParticipantesDTO(EntidadesParticipantesConverter.convertEntidadesParticipantesToDTO(conOtrasAportaciones.getEntidadesParticipantes()));
    conOtrasAportacionesDTO.setContribucionDTO(ContribucionesConverter.parseContribucionesToContribucionesDTO(conOtrasAportaciones.getContribuciones()));
    return conOtrasAportacionesDTO;
  }
}

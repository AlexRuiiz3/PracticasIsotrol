package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.EntidadParticipanteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.EntidadesParticipantes;

public interface IEntidadesParticipantesService {
  List<EntidadesParticipantes> obtenerEntidadesParticipantesBySolicitud(Long idSolicitud);

  EntidadesParticipantes saveOrUpdateEntidadParticipante(EntidadesParticipantes entidad) throws TramitacionException;

  void saveOrUpdateEntidadesParticipantes(List<EntidadParticipanteDTO> entidadesParticipantesDTO) throws TramitacionException;
}

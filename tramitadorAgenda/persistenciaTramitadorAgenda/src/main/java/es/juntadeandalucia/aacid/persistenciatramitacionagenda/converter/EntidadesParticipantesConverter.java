package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import java.util.ArrayList;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.EntidadParticipanteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.EntidadesParticipantes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Solicitud;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeEntidadesParticipantes;

public class EntidadesParticipantesConverter {

  /**
   *
   */
  private EntidadesParticipantesConverter() {
    // Auto-generated constructor stub
  }

  public static EntidadParticipanteDTO convertPaeEntidadesParticipantesToDTO(final PaeEntidadesParticipantes paeEntidadesParticipantes,
      final Long idSolicitud) {
    final EntidadParticipanteDTO entidadParticipanteDTO = new EntidadParticipanteDTO();
    entidadParticipanteDTO.setFuncion(FuncionesConverter.convertPaeFuncionesToFuncionesDTO(paeEntidadesParticipantes.getPaeFunciones()));
    entidadParticipanteDTO.setTipoEntidadParticipante(
        TipoEntidadParticipanteConverter.convertPaeTipoEntidadPaticipanteToDTO(paeEntidadesParticipantes.getPaeTEntidadParticipante()));
    entidadParticipanteDTO.setNombre(paeEntidadesParticipantes.getEntParticipante());
    entidadParticipanteDTO.setOtraFuncion(paeEntidadesParticipantes.getOtraFuncion());
    entidadParticipanteDTO.setSolicitud(new SolicitudDTO());
    entidadParticipanteDTO.getSolicitud().setIdSolicitud(idSolicitud);
    return entidadParticipanteDTO;

  }

  public static EntidadParticipanteDTO convertEntidadesParticipantesToDTO(final EntidadesParticipantes entidadesParticipantes) {
    final EntidadParticipanteDTO entidadParticipanteDTO = new EntidadParticipanteDTO();
    entidadParticipanteDTO.setFuncion(FuncionesConverter.convertFuncionesToFuncionesDTO(entidadesParticipantes.getFuncion()));
    entidadParticipanteDTO
        .setTipoEntidadParticipante(TipoEntidadParticipanteConverter.convertTipoEntidadPaticipanteToDTO(entidadesParticipantes.getTipoEntidadParticipante()));
    entidadParticipanteDTO.setNombre(entidadesParticipantes.getNombre());
    entidadParticipanteDTO.setOtraFuncion(entidadesParticipantes.getOtraFuncion());
    entidadParticipanteDTO.setId(entidadesParticipantes.getId());
    return entidadParticipanteDTO;
  }

  public static EntidadesParticipantes convertEntidadesParticipantesDTOToDao(final EntidadParticipanteDTO entidadParticipanteDTO) {
    final EntidadesParticipantes entidadesParticipantes = new EntidadesParticipantes();
    if (entidadParticipanteDTO != null) {
      entidadesParticipantes.setFuncion(FuncionesConverter.convertFuncionesDTOToFunciones(entidadParticipanteDTO.getFuncion()));
      entidadesParticipantes.setTipoEntidadParticipante(
          TipoEntidadParticipanteConverter.convertTipoEntidadPaticipanteDTOToDao(entidadParticipanteDTO.getTipoEntidadParticipante()));
      entidadesParticipantes.setNombre(entidadParticipanteDTO.getNombre());
      entidadesParticipantes.setOtraFuncion(entidadParticipanteDTO.getOtraFuncion());
      entidadesParticipantes.setId(entidadParticipanteDTO.getId());
      entidadesParticipantes.setAaciTSolicitudsubongdByIdSolicitud(new Solicitud());
      entidadesParticipantes.getAaciTSolicitudsubongdByIdSolicitud().setIdSolicitud(entidadParticipanteDTO.getSolicitud().getIdSolicitud());
    }
    return entidadesParticipantes;
  }

  public static List<EntidadParticipanteDTO> convertListPaeEntidadesParticipantesToListDTO(final List<PaeEntidadesParticipantes> paeEntidadesParticipantes,
      final Long idSolicitud) {
    final List<EntidadParticipanteDTO> entidadParticipanteDTO = new ArrayList<>();
    paeEntidadesParticipantes.forEach((final PaeEntidadesParticipantes paeEntidadParticipante) -> entidadParticipanteDTO
        .add(convertPaeEntidadesParticipantesToDTO(paeEntidadParticipante, idSolicitud)));
    return entidadParticipanteDTO;
  }

  public static List<EntidadParticipanteDTO> convertListEntidadesParticipantesToListDTO(final List<EntidadesParticipantes> entidadesParticipantes) {
    final List<EntidadParticipanteDTO> entidadParticipanteDTO = new ArrayList<>();
    entidadesParticipantes
        .forEach((final EntidadesParticipantes entidadParticipante) -> entidadParticipanteDTO.add(convertEntidadesParticipantesToDTO(entidadParticipante)));
    return entidadParticipanteDTO;
  }
}

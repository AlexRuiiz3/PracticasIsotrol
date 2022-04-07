package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.TipoEntidadParticipanteDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TipoEntidadParticipante;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeTEntidadParticipante;

public class TipoEntidadParticipanteConverter {

  /**
  *
  */
  private TipoEntidadParticipanteConverter() {
    // Auto-generated constructor stub
  }

  public static TipoEntidadParticipanteDTO convertPaeTipoEntidadPaticipanteToDTO(final PaeTEntidadParticipante paeTipoEntidadParticipante) {
    final TipoEntidadParticipanteDTO tipoEntidadParticipanteDTO = new TipoEntidadParticipanteDTO();
    tipoEntidadParticipanteDTO.setDescripcion(paeTipoEntidadParticipante.getEntidadPartic());
    tipoEntidadParticipanteDTO.setCodigo(ConstantesTramitacion.obtenerMapaTipoEntidadParticipante().get(paeTipoEntidadParticipante.getEntidadPartic()));
    return tipoEntidadParticipanteDTO;
  }

  public static TipoEntidadParticipanteDTO convertTipoEntidadPaticipanteToDTO(final TipoEntidadParticipante tipoEntidadParticipante) {
    final TipoEntidadParticipanteDTO tipoEntidadParticipanteDTO = new TipoEntidadParticipanteDTO();
    tipoEntidadParticipanteDTO.setDescripcion(tipoEntidadParticipante.getTepaTxDescripcion());
    tipoEntidadParticipanteDTO.setCodigo(tipoEntidadParticipante.getTepaTxCodigo());
    return tipoEntidadParticipanteDTO;
  }

  public static TipoEntidadParticipante convertTipoEntidadPaticipanteToDTO(final TipoEntidadParticipanteDTO tipoEntidadParticipanteDTO) {
    final TipoEntidadParticipante tipoEntidadParticipante = new TipoEntidadParticipante();
    tipoEntidadParticipanteDTO.setDescripcion(tipoEntidadParticipanteDTO.getDescripcion());
    tipoEntidadParticipanteDTO.setCodigo(tipoEntidadParticipanteDTO.getCodigo());
    return tipoEntidadParticipante;
  }

  public static TipoEntidadParticipante convertTipoEntidadPaticipanteDTOToDao(final TipoEntidadParticipanteDTO tipoEntidadParticipanteDTO) {
    final TipoEntidadParticipante tipoEntidadParticipante = new TipoEntidadParticipante();
    tipoEntidadParticipante.setTepaTxDescripcion(tipoEntidadParticipanteDTO.getDescripcion());
    tipoEntidadParticipante.setTepaTxCodigo(tipoEntidadParticipanteDTO.getCodigo());
    return tipoEntidadParticipante;
  }
}

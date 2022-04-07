package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import java.util.ArrayList;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ContraparteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.TipoEntidadContraparteDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Contrapartes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TipoEntidadContraparte;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeContrapartes;

public class ContraparteConverter {

  ContraparteConverter() {

  }

  public static List<Contrapartes> convertirListDtoToListDao(final List<ContraparteDTO> contrapartesDTO) {
    final List<Contrapartes> contrapartesDao = new ArrayList<>();
    for (final ContraparteDTO contDTO : contrapartesDTO) {
      contrapartesDao.add(convertirDtoToDao(contDTO));
    }
    return contrapartesDao;
  }

  public static Contrapartes convertirDtoToDao(final ContraparteDTO contraparteDTO) {

    final Contrapartes contrapartes = new Contrapartes();
    contrapartes.setContContraparte(contraparteDTO.getContraparte());
    contrapartes.setContPais(contraparteDTO.getPais());
    contrapartes.setContLocalidad(contraparteDTO.getLocalidad());
    contrapartes.setTipoEntidadContraparte(new TipoEntidadContraparte());
    contrapartes.getTipoEntidadContraparte().settMecNuId(contraparteDTO.getTipoEntidadContraparteDTO().getIdTipoEntidadContraparte());
    contrapartes.getTipoEntidadContraparte().settMecTxCodigo(contraparteDTO.getTipoEntidadContraparteDTO().getCodigoEntidadContraparte());
    contrapartes.getTipoEntidadContraparte().settMecTxDescripcion(contraparteDTO.getTipoEntidadContraparteDTO().getDescripcionEntidadContraparte());

    return contrapartes;

  }

  public static List<ContraparteDTO> convertirListPaeContraparteToListDto(final List<PaeContrapartes> paeContrapartes) {
    final List<ContraparteDTO> contrapartesDTO = new ArrayList<>();
    for (final PaeContrapartes cont : paeContrapartes) {
      contrapartesDTO.add(convertirPaeContrapartesToDTO(cont));
    }
    return contrapartesDTO;
  }

  public static List<ContraparteDTO> convertirListContraparteToListDto(final List<Contrapartes> paeContrapartesDTO) {
    final List<ContraparteDTO> contrapartesDTO = new ArrayList<>();
    for (final Contrapartes contDTO : paeContrapartesDTO) {
      contrapartesDTO.add(convertirContrapartesToDTO(contDTO));
    }
    return contrapartesDTO;
  }

  public static ContraparteDTO convertirContrapartesToDTO(final Contrapartes contrapartes) {

    final ContraparteDTO contrapartesDTO = new ContraparteDTO();
    contrapartesDTO.setContraparte(contrapartes.getContContraparte());
    contrapartesDTO.setPais(contrapartes.getContPais());
    contrapartesDTO.setLocalidad(contrapartes.getContLocalidad());
    contrapartesDTO.setId(contrapartes.getContIdContraparte());
    contrapartesDTO.setTipoEntidadContraparteDTO(new TipoEntidadContraparteDTO());
    contrapartesDTO.getTipoEntidadContraparteDTO().setIdTipoEntidadContraparte(contrapartes.getTipoEntidadContraparte().gettMecNuId());
    contrapartesDTO.getTipoEntidadContraparteDTO().setCodigoEntidadContraparte(contrapartes.getTipoEntidadContraparte().gettMecTxCodigo());
    contrapartesDTO.getTipoEntidadContraparteDTO().setDescripcionEntidadContraparte(contrapartes.getTipoEntidadContraparte().gettMecTxDescripcion());

    return contrapartesDTO;

  }

  public static ContraparteDTO convertirPaeContrapartesToDTO(final PaeContrapartes paeContraparte) {

    final ContraparteDTO contrapartesDTO = new ContraparteDTO();
    contrapartesDTO.setContraparte(paeContraparte.getContraparte());
    contrapartesDTO.setPais(paeContraparte.getPais());
    contrapartesDTO.setLocalidad(paeContraparte.getLocalidad());
    contrapartesDTO.setId(paeContraparte.getIdContraparte());
    contrapartesDTO.setTipoEntidadContraparteDTO(new TipoEntidadContraparteDTO());
    contrapartesDTO.getTipoEntidadContraparteDTO().setIdTipoEntidadContraparte(paeContraparte.getPaeTEntidadContraparteByFkTEntidad().getIdTEntContraparte());
    contrapartesDTO.getTipoEntidadContraparteDTO().setDescripcionEntidadContraparte(paeContraparte.getPaeTEntidadContraparteByFkTEntidad().gettEntContrap());

    return contrapartesDTO;

  }
}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ConContraparteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ContraparteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ContribucionDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.ConContraparte;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Contribuciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeConContrapartes;

public class ConContraparteConverter {

  public static ConContraparteDTO parseConContraparteToConContraparteDTO(final ConContraparte conContraparte) {
    final ConContraparteDTO conContraparteDTO = new ConContraparteDTO();
    conContraparteDTO.setAaciContrapartesByFkEntidad(ContraparteConverter.convertirContrapartesToDTO(conContraparte.getAaciContrapartesByFkEntidad()));
    conContraparteDTO.setAaciContribucionesByFkContribucion(
        ContribucionesConverter.parseContribucionesToContribucionesDTO(conContraparte.getAaciContribucionesByFkContribucion()));
    conContraparteDTO.setContraparteEmergencia(conContraparte.getContraparteEmergencia());
    conContraparteDTO.setContribucion(conContraparte.getContribucion());
    conContraparteDTO.setContribucionNoValidada(conContraparte.getContribucionNoValidada());
    conContraparteDTO.setContribucionValidada(conContraparte.getContribucionValidada());
    conContraparteDTO.setIdConContra(conContraparte.getIdConContra());
    conContraparteDTO.setLocalidad(conContraparte.getLocalidad());
    conContraparteDTO.setPais(conContraparte.getPais());
    return conContraparteDTO;
  }

  public static ConContraparteDTO parsePaeConContraparteToConContraparteDTO(final PaeConContrapartes paeConContraparte, final ContribucionDTO contribucionDTO) {
    final ConContraparteDTO conContraparteDTO = new ConContraparteDTO();
    conContraparteDTO.setAaciContrapartesByFkEntidad(ContraparteConverter.convertirPaeContrapartesToDTO(paeConContraparte.getPaeContrapartesByFkEntidad()));
    if (contribucionDTO == null) {
      conContraparteDTO.setAaciContribucionesByFkContribucion(
          ContribucionesConverter.parsePaeContribucionesToContribucionesDTO(paeConContraparte.getPaeContribucionesByFkContribucion()));
    } else {
      conContraparteDTO.setAaciContribucionesByFkContribucion(contribucionDTO);
    }
    conContraparteDTO.setContraparteEmergencia(paeConContraparte.getContraparteEmergencia());
    conContraparteDTO.setContribucion(paeConContraparte.getContribucion());
    conContraparteDTO.setContribucionNoValidada(paeConContraparte.getContribucionNoValidada());
    conContraparteDTO.setContribucionValidada(paeConContraparte.getContribucionValidada());
    conContraparteDTO.setLocalidad(paeConContraparte.getLocalidad());
    conContraparteDTO.setPais(paeConContraparte.getPais());
    return conContraparteDTO;
  }

  public static ConContraparte parseConContraparteDTOtoConContraparte(final ConContraparteDTO conContraparteDTO, final Contribuciones contribuciones,
      final ContraparteDTO contraparteDTO) {
    final ConContraparte conContraparte = new ConContraparte();
    conContraparte.setAaciContrapartesByFkEntidad(ContraparteConverter.convertirDtoToDao(contraparteDTO));
    conContraparte.setAaciContribucionesByFkContribucion(contribuciones);
    conContraparte.setContraparteEmergencia(conContraparteDTO.getContraparteEmergencia());
    conContraparte.setContribucion(conContraparteDTO.getContribucion());
    conContraparte.setContribucionNoValidada(conContraparteDTO.getContribucionNoValidada());
    conContraparte.setContribucionValidada(conContraparteDTO.getContribucionValidada());
    conContraparte.setIdConContra(conContraparteDTO.getIdConContra());
    conContraparte.setLocalidad(conContraparteDTO.getLocalidad());
    conContraparte.setPais(conContraparteDTO.getPais());
    return conContraparte;
  }

  public static Set<ConContraparte> convertirListConContraparteDTOtoConContraparte(final List<ConContraparteDTO> conContrapartesDTO,
      final Contribuciones contribuciones) {
    final Set<ConContraparte> conContrapartes = new HashSet<>();

    conContrapartesDTO.forEach((final ConContraparteDTO conContraparteDTO) -> conContrapartes
        .add(parseConContraparteDTOtoConContraparte(conContraparteDTO, contribuciones, conContraparteDTO.getAaciContrapartesByFkEntidad())));
    return conContrapartes;
  }

  public static List<ConContraparteDTO> convertirListConContraparteToListDto(final Set<ConContraparte> listaConContraparte) {
    final List<ConContraparteDTO> contrapartesDTO = new ArrayList<>();
    for (final ConContraparte paeConContraparte : listaConContraparte) {
      contrapartesDTO.add(parseConContraparteToConContraparteDTO(paeConContraparte));
    }
    return contrapartesDTO;
  }

  public static List<ConContraparteDTO> convertirListPaeConContraparteToListDto(final List<PaeConContrapartes> paeConContrapartes,
      final ContribucionDTO contribucionDTO) {
    final List<ConContraparteDTO> contrapartesDTO = new ArrayList<>();
    for (final PaeConContrapartes paeConContraparte : paeConContrapartes) {
      contrapartesDTO.add(parsePaeConContraparteToConContraparteDTO(paeConContraparte, contribucionDTO));
    }
    return contrapartesDTO;
  }

}

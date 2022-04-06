package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ContribucionDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Contribuciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeContribuciones;

public class ContribucionesConverter {

  /**
   *
   */
  private ContribucionesConverter() {
    // Auto-generated constructor stub
  }

  public static Contribuciones parseContribucionesDTOToContribuciones(final ContribucionDTO contribucionDTO) {
    final Contribuciones contribuciones = new Contribuciones();
    contribuciones.setIdContribucion(contribucionDTO.getIdContribucion());
    contribuciones.setAacid(contribucionDTO.getAacid());
    contribuciones.setAacidEmergencia(contribucionDTO.getAacidEmergencia());
    contribuciones.setAacidNoValidada(contribucionDTO.getAacidNoValidada());
    contribuciones.setAacidValidada(contribucionDTO.getAacidValidada());
    contribuciones.setContraparte(contribucionDTO.getContraparte());
    contribuciones.setFkProgramacion(contribucionDTO.getFkProgramacion());
    contribuciones.setFkProyectos(contribucionDTO.getFkProyectos());
    contribuciones.setLocalesPrivadas(contribucionDTO.getLocalesPrivadas());
    contribuciones.setLocalesPublicas(contribucionDTO.getLocalesPublicas());
    contribuciones.setPrivadas(contribucionDTO.getPrivadas());
    contribuciones.setPublicas(contribucionDTO.getPublicas());
    contribuciones.setSolicitante(contribucionDTO.getSolicitante());
    contribuciones.setSolicitanteEmergencia(contribucionDTO.getSolicitanteEmergencia());
    contribuciones.setSolicitanteNoValidada(contribucionDTO.getSolicitanteNoValidada());
    contribuciones.setSolicitanteValidada(contribucionDTO.getSolicitanteValidada());
    contribuciones.setSubtotalExterior(contribucionDTO.getSubtotalExterior());
    contribuciones.setSubtotalLocal(contribucionDTO.getSubtotalLocal());
    contribuciones.setTotal(contribucionDTO.getTotal());
    contribuciones.setGasto(GastosConverter.parseGastosDTOToGastos(contribucionDTO.getGastos()));
    contribuciones.setAaciConContrapartesByIdContribucion(
        ConContraparteConverter.convertirListConContraparteDTOtoConContraparte(contribucionDTO.getListaContrapartesDTO(), contribuciones));
    return contribuciones;
  }

  public static List<Contribuciones> parseContribucionesDTOListToContribuciones(final List<ContribucionDTO> contribucionDTO) {
    final List<Contribuciones> contribuciones = new ArrayList<>();
    contribucionDTO.forEach((final ContribucionDTO contribucion) -> contribuciones.add(parseContribucionesDTOToContribuciones(contribucion)));

    return contribuciones;
  }

  public static ContribucionDTO parseContribucionesToContribucionesDTO(final Contribuciones contribuciones) {
    final ContribucionDTO contribucionDTO = new ContribucionDTO();
    contribucionDTO.setIdContribucion(contribuciones.getIdContribucion());
    contribucionDTO.setAacid(contribuciones.getAacid() != null ? contribuciones.getAacid() : BigDecimal.ZERO);
    contribucionDTO.setAacidEmergencia(contribuciones.getAacidEmergencia() != null ? contribuciones.getAacidEmergencia() : BigDecimal.ZERO);
    contribucionDTO.setAacidNoValidada(contribuciones.getAacidNoValidada() != null ? contribuciones.getAacidNoValidada() : BigDecimal.ZERO);
    contribucionDTO.setAacidValidada(contribuciones.getAacidValidada() != null ? contribuciones.getAacidValidada() : BigDecimal.ZERO);
    contribucionDTO.setContraparte(contribuciones.getContraparte());
    contribucionDTO.setFkProgramacion(contribuciones.getFkProgramacion());
    contribucionDTO.setFkProyectos(contribuciones.getFkProyectos());
    contribucionDTO.setLocalesPrivadas(contribuciones.getLocalesPrivadas());
    contribucionDTO.setLocalesPublicas(contribuciones.getLocalesPublicas());
    contribucionDTO.setPrivadas(contribuciones.getPrivadas());
    contribucionDTO.setPublicas(contribuciones.getPublicas());
    contribucionDTO.setSolicitante(contribuciones.getSolicitante() != null ? contribuciones.getSolicitante() : BigDecimal.ZERO);
    contribucionDTO.setSolicitanteEmergencia(contribuciones.getSolicitanteEmergencia() != null ? contribuciones.getSolicitanteEmergencia() : BigDecimal.ZERO);
    contribucionDTO.setSolicitanteNoValidada(contribuciones.getSolicitanteNoValidada() != null ? contribuciones.getSolicitanteNoValidada() : BigDecimal.ZERO);
    contribucionDTO.setSolicitanteValidada(contribuciones.getSolicitanteValidada() != null ? contribuciones.getSolicitanteValidada() : BigDecimal.ZERO);
    contribucionDTO.setSubtotalExterior(contribuciones.getSubtotalExterior() != null ? contribuciones.getSubtotalExterior() : BigDecimal.ZERO);
    contribucionDTO.setSubtotalLocal(contribuciones.getSubtotalLocal() != null ? contribuciones.getSubtotalLocal() : BigDecimal.ZERO);
    contribucionDTO.setTotal(contribuciones.getTotal() != null ? contribuciones.getTotal() : BigDecimal.ZERO);
    contribucionDTO.setGastos(GastosConverter.gastosToGastosDTO(contribuciones.getGasto()));
    contribucionDTO
        .setListaContrapartesDTO(ConContraparteConverter.convertirListConContraparteToListDto(contribuciones.getAaciConContrapartesByIdContribucion()));

    return contribucionDTO;
  }

  public static ContribucionDTO parsePaeContribucionesToContribucionesDTO(final PaeContribuciones paeContribuciones) {
    final ContribucionDTO contribucionDTO = new ContribucionDTO();
    contribucionDTO.setIdContribucion(paeContribuciones.getIdContribucion());
    contribucionDTO.setAacid(paeContribuciones.getAacid());
    contribucionDTO.setAacidEmergencia(paeContribuciones.getAacidEmergencia());
    contribucionDTO.setAacidNoValidada(paeContribuciones.getAacidNoValidada());
    contribucionDTO.setAacidValidada(paeContribuciones.getAacidValidada());
    contribucionDTO.setContraparte(paeContribuciones.getContraparte());
    contribucionDTO.setFkProgramacion(paeContribuciones.getPaeProgramacion() != null ? paeContribuciones.getPaeProgramacion().getIdProgramacion() : null);
    contribucionDTO.setFkProyectos(
        paeContribuciones.getPaeSolicitudes() != null ? (Long.parseLong(String.valueOf(paeContribuciones.getPaeSolicitudes().getIdSolicitud()))) : null);
    contribucionDTO.setLocalesPrivadas(paeContribuciones.getLocalesPrivadas());
    contribucionDTO.setLocalesPublicas(paeContribuciones.getLocalesPublicas());
    contribucionDTO.setPrivadas(paeContribuciones.getPrivadas());
    contribucionDTO.setPublicas(paeContribuciones.getPublicas());
    contribucionDTO.setSolicitante(paeContribuciones.getSolicitante());
    contribucionDTO.setSolicitanteEmergencia(paeContribuciones.getSolicitanteEmergencia());
    contribucionDTO.setSolicitanteNoValidada(paeContribuciones.getSolicitanteNoValidada());
    contribucionDTO.setSolicitanteValidada(paeContribuciones.getSolicitanteValidada());
    contribucionDTO.setSubtotalExterior(paeContribuciones.getSubtotalExterior());
    contribucionDTO.setSubtotalLocal(paeContribuciones.getSubtotalLocal());
    contribucionDTO.setTotal(paeContribuciones.getTotal());
    contribucionDTO.setGastos(GastosConverter.paeGastosToGastosDTO(paeContribuciones.getPaeGastos()));
    contribucionDTO.setListaContrapartesDTO(
        ConContraparteConverter.convertirListPaeConContraparteToListDto(paeContribuciones.getPaeConContrapartesByIdContribucion(), contribucionDTO));
    return contribucionDTO;
  }
}

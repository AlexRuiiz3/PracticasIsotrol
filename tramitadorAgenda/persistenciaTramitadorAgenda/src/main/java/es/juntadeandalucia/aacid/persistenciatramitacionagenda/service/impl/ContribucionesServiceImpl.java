package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ContribucionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.EntidadesContribucionesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.GastosContribucionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.ContraparteConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.ContribucionesConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.EntidadesParticipantesConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IContribucionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.ConContraparte;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.ConOtrasAportaciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Contribuciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Gasto;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeContribuciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeSolicitudes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IContribucionesService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeContribucionesService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeSolicitudService;

public class ContribucionesServiceImpl implements IContribucionesService {

  private IContribucionDao contribucionDao;

  private IPaeSolicitudService paeSolicitudService;

  private IPaeContribucionesService paeContribucionesService;

  @Override
  public List<Contribuciones> obtenerContribucionesBySolicitud(final Long idSolicitud) throws TramitacionException {
    final List<Contribuciones> contribuciones = contribucionDao.obtenerContribucionesBySolicitud(idSolicitud);

    return contribuciones;
  }

  @Override
  public Contribuciones saveOrUpdateContribuciones(final Contribuciones contribucion) throws TramitacionException {
    return contribucionDao.saveOrUpdateContribuciones(contribucion);
  }

  @Override
  public void obtenerContribuciones(final List<ContribucionDTO> listaContribucionesDTO, final SolicitudDatosGeneralesDTO solicitud)
      throws TramitacionException {
    final List<Contribuciones> contribuciones = obtenerContribucionesBySolicitud(solicitud.getIdSolicitud());

    if (CollectionUtils.isNotEmpty(contribuciones)) {
      for (final Contribuciones contribucion : contribuciones) {
        listaContribucionesDTO.add(ContribucionesConverter.parseContribucionesToContribucionesDTO(contribucion));
      }
    } else {
      final PaeSolicitudes paeSolicitudes = paeSolicitudService.obtenerSolicitudExpByCodigo(solicitud.getTxCodidentificativo());
      if (paeSolicitudes != null) {
        final List<PaeContribuciones> paeContribuciones = paeContribucionesService
            .getPaeContribucionesBySolicitud(Long.parseLong(String.valueOf(paeSolicitudes.getIdSolicitud())));
        final Iterator<PaeContribuciones> it = paeContribuciones.iterator();
        while (it.hasNext()) {
          listaContribucionesDTO.add(ContribucionesConverter.parsePaeContribucionesToContribucionesDTO(it.next()));
        }
      }
    }
  }

  @Override
  public void guardarContribuciones(final EntidadesContribucionesDTO entidadContribucionesDTO, final SolicitudDatosGeneralesDTO solicitud)
      throws TramitacionException {
    List<Contribuciones> contribucionesSolicitud;
    if (CollectionUtils.isNotEmpty(solicitud.getContribuciones())) {
      contribucionesSolicitud = ContribucionesConverter.parseContribucionesDTOListToContribuciones(solicitud.getContribuciones());
    } else {
      contribucionesSolicitud = obtenerContribucionesBySolicitud(solicitud.getIdSolicitud());
    }

    if (CollectionUtils.isNotEmpty(entidadContribucionesDTO.getListaNuevaEntidad())) {
      for (final GastosContribucionDTO gastosContribucionDTO : entidadContribucionesDTO.getListaNuevaEntidad()) {
        if (CollectionUtils.isEmpty(contribucionesSolicitud)) {
          creaNuevaContribucion(entidadContribucionesDTO, gastosContribucionDTO, solicitud);
        } else {
          modificarContribucion(entidadContribucionesDTO, gastosContribucionDTO, contribucionesSolicitud);
        }
      }
    }
  }

  private void creaNuevaContribucion(final EntidadesContribucionesDTO entidadContribucionesDTO, final GastosContribucionDTO gastosContribucionDTO,
      final SolicitudDatosGeneralesDTO solicitud) throws TramitacionException {
    final Contribuciones nuevaContribucion = new Contribuciones();
    nuevaContribucion.setAaciConContrapartesByIdContribucion(new HashSet<>());
    nuevaContribucion.setListaOtrasAportaciones(new ArrayList<>());
    nuevaContribucion.setGasto(new Gasto());
    nuevaContribucion.getGasto().setAaciGasto(gastosContribucionDTO.getGasto().getIdGasto());
    nuevaContribucion.setFkProyectos(solicitud.getIdSolicitud());
    switch (entidadContribucionesDTO.getTipo()) {
    case ConstantesTramitacion.SISTEMA_ACCID:
      anyadeValoresAacidToContribucion(entidadContribucionesDTO, gastosContribucionDTO, nuevaContribucion);
      break;
    case ConstantesTramitacion.SOLICITANTE:
      anyadeValoresSolicitanteToContribucion(entidadContribucionesDTO, gastosContribucionDTO, nuevaContribucion);
      break;
    case ConstantesTramitacion.CONTRAPARTE:
      anyadeConContraparteToContribucion(entidadContribucionesDTO, gastosContribucionDTO, nuevaContribucion);
      break;
    default:
      anyadeOtrasAportacionesToContribucion(entidadContribucionesDTO, gastosContribucionDTO, nuevaContribucion);
      break;
    }
    saveOrUpdateContribuciones(nuevaContribucion);
  }

  private void modificarContribucion(final EntidadesContribucionesDTO entidadContribucionesDTO, final GastosContribucionDTO gastosContribucionDTO,
      final List<Contribuciones> contribucionesSolicitud) throws TramitacionException {
    for (final Contribuciones contribucion : contribucionesSolicitud) {
      if (contribucion.getGasto().getAaciGasto().floatValue() == gastosContribucionDTO.getGasto().getIdGasto().floatValue()) {
        anyadeValoresAacidToContribucion(entidadContribucionesDTO, gastosContribucionDTO, contribucion);
        anyadeValoresSolicitanteToContribucion(entidadContribucionesDTO, gastosContribucionDTO, contribucion);
        anyadeConContraparteToContribucion(entidadContribucionesDTO, gastosContribucionDTO, contribucion);
        anyadeOtrasAportacionesToContribucion(entidadContribucionesDTO, gastosContribucionDTO, contribucion);
      }
      saveOrUpdateContribuciones(contribucion);
    }
  }

  private void anyadeValoresAacidToContribucion(final EntidadesContribucionesDTO entidadContribucionDTO, final GastosContribucionDTO gastosContribucionDTO,
      final Contribuciones contribucion) {
    if (entidadContribucionDTO.getTipo().equals(ConstantesTramitacion.SISTEMA_ACCID)) {
      contribucion.setAacid(UtilidadesNumero.safeBigDecimalParsingHelper(gastosContribucionDTO.getValorTitle()));
      if (gastosContribucionDTO.getValorNoValidadoTitle() != null) {
        final BigDecimal total = UtilidadesNumero.safeBigDecimalParsingHelper(gastosContribucionDTO.getValorTitle());
        final BigDecimal noValidado = UtilidadesNumero.safeBigDecimalParsingHelper(gastosContribucionDTO.getValorNoValidadoTitle());
        final BigDecimal validado = total.subtract(noValidado);
        contribucion.setAacidNoValidada(noValidado);
        contribucion.setAacidValidada(validado);
      }
    }
  }

  private void anyadeValoresSolicitanteToContribucion(final EntidadesContribucionesDTO entidadContribucionDTO,
      final GastosContribucionDTO gastosContribucionDTO, final Contribuciones contribucion) {
    if (entidadContribucionDTO.getTipo().equals(ConstantesTramitacion.SOLICITANTE)) {
      contribucion.setSolicitante(UtilidadesNumero.safeBigDecimalParsingHelper(gastosContribucionDTO.getValorTitle()));
      if (gastosContribucionDTO.getValorNoValidadoTitle() != null) {
        final BigDecimal total = UtilidadesNumero.safeBigDecimalParsingHelper(gastosContribucionDTO.getValorTitle());
        final BigDecimal noValidado = UtilidadesNumero.safeBigDecimalParsingHelper(gastosContribucionDTO.getValorNoValidadoTitle());
        final BigDecimal validado = total.subtract(noValidado);
        contribucion.setSolicitanteNoValidada(noValidado);
        contribucion.setSolicitanteValidada(validado);
      }
    }
  }

  private void anyadeConContraparteToContribucion(final EntidadesContribucionesDTO entidadContribucionesDTO, final GastosContribucionDTO gastosContribucionDTO,
      final Contribuciones contribucion) {
    if (entidadContribucionesDTO.getTipo().equals(ConstantesTramitacion.CONTRAPARTE)) {
      boolean enc = false;

      for (final ConContraparte conContraparte : contribucion.getAaciConContrapartesByIdContribucion()) {
        if (entidadContribucionesDTO.getContraparteDTO().getId().longValue() == conContraparte.getAaciContrapartesByFkEntidad().getContIdContraparte()
            .longValue()) {
          conContraparte.setContribucion(UtilidadesNumero.safeBigDecimalParsingHelper(gastosContribucionDTO.getValorTitle()));
          if (gastosContribucionDTO.getValorNoValidadoTitle() != null) {
            final BigDecimal total = UtilidadesNumero.safeBigDecimalParsingHelper(gastosContribucionDTO.getValorTitle());
            final BigDecimal noValidado = UtilidadesNumero.safeBigDecimalParsingHelper(gastosContribucionDTO.getValorNoValidadoTitle());
            final BigDecimal validado = total.subtract(noValidado);
            conContraparte.setContribucionNoValidada(noValidado);
            conContraparte.setContribucionValidada(validado);
          }
          enc = true;
        }
      }
      if (!enc) {
        final ConContraparte nuevaCon = new ConContraparte();
        nuevaCon.setContribucion(UtilidadesNumero.safeBigDecimalParsingHelper(gastosContribucionDTO.getValorTitle()));
        if (gastosContribucionDTO.getValorNoValidadoTitle() != null) {
          final BigDecimal total = UtilidadesNumero.safeBigDecimalParsingHelper(gastosContribucionDTO.getValorTitle());
          final BigDecimal noValidado = UtilidadesNumero.safeBigDecimalParsingHelper(gastosContribucionDTO.getValorNoValidadoTitle());
          final BigDecimal validado = total.subtract(noValidado);
          nuevaCon.setContribucionNoValidada(noValidado);
          nuevaCon.setContribucionValidada(validado);
        }
        nuevaCon.setAaciContribucionesByFkContribucion(contribucion);

        nuevaCon.setAaciContrapartesByFkEntidad(ContraparteConverter.convertirDtoToDao(entidadContribucionesDTO.getContraparteDTO()));
        contribucion.getAaciConContrapartesByIdContribucion().add(nuevaCon);
      }
    }
  }

  public void anyadeOtrasAportacionesToContribucion(final EntidadesContribucionesDTO entidadContribucionesDTO,
      final GastosContribucionDTO gastosContribucionDTO, final Contribuciones contribucion) {
    boolean enc = false;

    for (final ConOtrasAportaciones conOtrasAportaciones : contribucion.getListaOtrasAportaciones()) {
      if (entidadContribucionesDTO.getEntidadesParticipantes().getId().longValue() == conOtrasAportaciones.getEntidadesParticipantes().getId().longValue()) {
        conOtrasAportaciones.setContribucion(UtilidadesNumero.safeBigDecimalParsingHelper(gastosContribucionDTO.getValorTitle()));
        if (gastosContribucionDTO.getValorNoValidadoTitle() != null) {
          final BigDecimal total = UtilidadesNumero.safeBigDecimalParsingHelper(gastosContribucionDTO.getValorTitle());
          final BigDecimal noValidado = UtilidadesNumero.safeBigDecimalParsingHelper(gastosContribucionDTO.getValorNoValidadoTitle());
          final BigDecimal validado = total.subtract(noValidado);
          conOtrasAportaciones.setContribucionNoValidada(noValidado);
          conOtrasAportaciones.setContribucionValidada(validado);
        }
        enc = true;
      }
    }
    if (CollectionUtils.isNotEmpty(contribucion.getListaOtrasAportaciones()) && !enc) {
      final ConOtrasAportaciones nuevaOtra = new ConOtrasAportaciones();
      nuevaOtra.setContribucion(UtilidadesNumero.safeBigDecimalParsingHelper(gastosContribucionDTO.getValorTitle()));
      if (gastosContribucionDTO.getValorNoValidadoTitle() != null) {
        final BigDecimal total = UtilidadesNumero.safeBigDecimalParsingHelper(gastosContribucionDTO.getValorTitle());
        final BigDecimal noValidado = UtilidadesNumero.safeBigDecimalParsingHelper(gastosContribucionDTO.getValorNoValidadoTitle());
        final BigDecimal validado = total.subtract(noValidado);
        nuevaOtra.setContribucionNoValidada(noValidado);
        nuevaOtra.setContribucionValidada(validado);
      }
      nuevaOtra.setContribuciones(contribucion);
      nuevaOtra.setEntidadesParticipantes(
          EntidadesParticipantesConverter.convertEntidadesParticipantesDTOToDao(entidadContribucionesDTO.getEntidadesParticipantes()));
      contribucion.getListaOtrasAportaciones().add(nuevaOtra);
    }
  }

  public void setContribucionDao(final IContribucionDao contribucionDao) {
    this.contribucionDao = contribucionDao;
  }

  public void setPaeSolicitudService(final IPaeSolicitudService paeSolicitudService) {
    this.paeSolicitudService = paeSolicitudService;
  }

  public void setPaeContribucionesService(final IPaeContribucionesService paeContribucionesService) {
    this.paeContribucionesService = paeContribucionesService;
  }

}

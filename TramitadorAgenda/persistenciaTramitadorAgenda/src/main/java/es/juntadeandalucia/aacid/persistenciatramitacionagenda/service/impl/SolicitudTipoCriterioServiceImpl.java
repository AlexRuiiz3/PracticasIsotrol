package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudTipoCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionSolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionTipoCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ISolicitudTipoCriteriosDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.SolicitudTipoCriterio;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudTipoCriterioService;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;

public class SolicitudTipoCriterioServiceImpl implements ISolicitudTipoCriterioService {
  private ISolicitudTipoCriteriosDao solicitudTipoCriteriosDao;

  @Override
  public SolicitudTipoCriterioDTO obtenerSolicitudTipoCriterioByIdSolicitud(final Long idSolicitud, final String nombreTipoCriterio)
      throws TramitacionException {
    return solicitudTipoCriteriosDao.obtenerSolicitudTipoCriterioDTOByIdSolicitud(idSolicitud, nombreTipoCriterio);
  }

  @Override
  public int guardarValoracionTipoCriterio(final SolicitudTipoCriterio solicitudTipoCriterio) throws TramitacionException {
    return solicitudTipoCriteriosDao.guardarValoracionTipoCriterio(solicitudTipoCriterio);
  }

  @Override
  public void realizarComprobaciones(final ValoracionSolicitudDTO valoracionSolicitudDTO, final List<Perfil> perfiles) {
    BigDecimal totalPuntuacion = BigDecimal.ZERO;
    boolean existeTCritNoSuperaMitadMax;

    totalPuntuacion = totalPuntuacion.add(UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getPertinencia().getTotalPuntuacion()));
    existeTCritNoSuperaMitadMax = UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getPertinencia().getTotalPuntuacion())
        .compareTo(UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getPertinencia().getPuntuacionMaxima()).divide(BigDecimal.valueOf(2.0))) < 0;

    totalPuntuacion = totalPuntuacion.add(UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getViabilidad().getTotalPuntuacion()));
    existeTCritNoSuperaMitadMax = existeTCritNoSuperaMitadMax || UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getViabilidad().getTotalPuntuacion())
        .compareTo(UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getViabilidad().getPuntuacionMaxima()).divide(BigDecimal.valueOf(2.0))) < 0;

    totalPuntuacion = totalPuntuacion.add(UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getCoherencia().getTotalPuntuacion()));
    existeTCritNoSuperaMitadMax = existeTCritNoSuperaMitadMax || UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getCoherencia().getTotalPuntuacion())
        .compareTo(UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getCoherencia().getPuntuacionMaxima()).divide(BigDecimal.valueOf(2.0))) < 0;

    totalPuntuacion = totalPuntuacion.add(UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getConectividad().getTotalPuntuacion()));
    existeTCritNoSuperaMitadMax = existeTCritNoSuperaMitadMax
        || UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getConectividad().getTotalPuntuacion())
            .compareTo(UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getConectividad().getPuntuacionMaxima()).divide(BigDecimal.valueOf(2.0))) < 0;

    totalPuntuacion = totalPuntuacion.add(UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getSostenibilidad().getTotalPuntuacion()));
    existeTCritNoSuperaMitadMax = existeTCritNoSuperaMitadMax
        || UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getSostenibilidad().getTotalPuntuacion()).compareTo(
            UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getSostenibilidad().getPuntuacionMaxima()).divide(BigDecimal.valueOf(2.0))) < 0;

    totalPuntuacion = totalPuntuacion.add(UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getImpacto().getTotalPuntuacion()));

    if (valoracionSolicitudDTO.getConvergencia() != null && valoracionSolicitudDTO.getConvergencia().getTotalPuntuacion() != null) {
      totalPuntuacion = totalPuntuacion.add(UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getConvergencia().getTotalPuntuacion()));
    }

    if (valoracionSolicitudDTO.getCapacidadGestion() != null && valoracionSolicitudDTO.getCapacidadGestion().getTotalPuntuacion() != null) {
      totalPuntuacion = totalPuntuacion.add(UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getCapacidadGestion().getTotalPuntuacion()));
    }

    existeTCritNoSuperaMitadMax = existeTCritNoSuperaMitadMax || UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getImpacto().getTotalPuntuacion())
        .compareTo(UtilidadesNumero.obtieneBigDecimal(valoracionSolicitudDTO.getImpacto().getPuntuacionMaxima()).divide(BigDecimal.valueOf(2.0))) < 0;

    valoracionSolicitudDTO.setMostrarPestania3(!existeTCritNoSuperaMitadMax);
    valoracionSolicitudDTO.setPermiteSumaIncrementos(!existeTCritNoSuperaMitadMax && totalPuntuacion.compareTo(BigDecimal.valueOf(60)) >= 0);

  }

  @Override
  public void obtenerDatosTipoCriterio(final Long idSolicitud, final String nombreTipoCritero, final ValoracionTipoCriterioDTO valoracionTipoCriterioDTO)
      throws TramitacionException {
    final SolicitudTipoCriterioDTO solicitudTipoCriterioDTO = solicitudTipoCriteriosDao.obtenerSolicitudTipoCriterioDTOByIdSolicitud(idSolicitud,
        nombreTipoCritero);

    if (solicitudTipoCriterioDTO != null) {
      valoracionTipoCriterioDTO.setObservaciones(solicitudTipoCriterioDTO.getObservaciones());
      valoracionTipoCriterioDTO.setTotalPuntuacion(solicitudTipoCriterioDTO.getPuntuacionTotal());

    } else {
      valoracionTipoCriterioDTO.setObservaciones(StringUtils.EMPTY);
      valoracionTipoCriterioDTO.setTotalPuntuacion(new BigDecimal(0));
      valoracionTipoCriterioDTO.setPuntuacionMaxima(new BigDecimal(0));
    }
  }

  /**
   * Establece el valor de la propiedad solicitudTipoCriteriosDao
   *
   * @param solicitudTipoCriteriosDao
   *          el solicitudTipoCriteriosDao para establecer
   */
  public void setSolicitudTipoCriteriosDao(final ISolicitudTipoCriteriosDao solicitudTipoCriteriosDao) {
    this.solicitudTipoCriteriosDao = solicitudTipoCriteriosDao;
  }
}

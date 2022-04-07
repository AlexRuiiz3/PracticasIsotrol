package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IPaeContribucionesDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.SolicitudConcesion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeConContrapartes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeConOtrasAportaciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeContribuciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeSolicitudes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeContribucionesService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeSolicitudService;

public class PaeContribucionesServiceImpl implements IPaeContribucionesService {

  private IPaeContribucionesDao paeContribucionesDao;

  private IPaeSolicitudService paeSolicitudService;

  @Override
  public List<PaeContribuciones> getPaeContribucionesBySolicitud(final Long idSolicitud) throws TramitacionException {
    return paeContribucionesDao.getPaeContribucionesBySolicitud(idSolicitud);
  }

  @Override
  public void estableceValorEconomicoConcesion(final SolicitudDTO solicitud, final boolean isUniv, final SolicitudConcesion solicitudConcesion,
      final boolean isExcluida) throws TramitacionException {
    String subvencionConceder = null;
    String presupuestoValidado = null;
    String subvencionSolicitada = null;

    if (!isUniv) {
      final PaeSolicitudes paeSolicitudes = paeSolicitudService.obtenerSolicitudExpByCodigo(solicitud.getCodIdentificativo());
      subvencionConceder = UtilidadesNumero.bigDecimalToStringConverter(calcularSubvencion(paeSolicitudes));
      presupuestoValidado = UtilidadesNumero.bigDecimalToStringConverter(calcularValidado(paeSolicitudes));
      if (paeSolicitudes != null && paeSolicitudes.getPlazo() != null) {
        solicitud.setPlazo(paeSolicitudes.getPlazo());
      }
      if (isExcluida) {
        subvencionSolicitada = UtilidadesNumero.bigDecimalToStringConverter(BigDecimal.valueOf(solicitud.getImporte()));
      }
    } else if (solicitudConcesion != null) {
      subvencionConceder = UtilidadesNumero.bigDecimalToStringConverter(
          solicitudConcesion.getSocoNuSubvencionConceder() != null ? solicitudConcesion.getSocoNuSubvencionConceder().setScale(2, RoundingMode.CEILING)
              : BigDecimal.ZERO);
      presupuestoValidado = UtilidadesNumero.bigDecimalToStringConverter(
          solicitudConcesion.getSocoNuPresupuestoValidado() != null ? solicitudConcesion.getSocoNuPresupuestoValidado().setScale(2, RoundingMode.CEILING)
              : BigDecimal.ZERO);
      if (isExcluida) {
        subvencionSolicitada = UtilidadesNumero.bigDecimalToStringConverter(
            solicitudConcesion.getSocoNuSubvencionSolicitada() != null ? solicitudConcesion.getSocoNuSubvencionSolicitada().setScale(2, RoundingMode.CEILING)
                : BigDecimal.ZERO);
      }
    }
    solicitud.setSubvencion(subvencionConceder);
    solicitud.setPresupuesto(presupuestoValidado);
    solicitud.setSubvencionSolicitada(subvencionSolicitada);
  }

  private BigDecimal calcularSubvencion(final PaeSolicitudes sol) {
    BigDecimal totalSub = BigDecimal.ZERO.setScale(3, RoundingMode.CEILING);
    BigDecimal totalSubVal = BigDecimal.ZERO.setScale(3, RoundingMode.CEILING);
    if (sol != null && (sol.getPaeTipoSolicitud() != null && ConstantesTramitacion.PROYECTO.equalsIgnoreCase(sol.getPaeTipoSolicitud().getDenominacion())
        || ConstantesTramitacion.EMERGENCIA.equalsIgnoreCase(sol.getPaeTipoSolicitud().getDenominacion()))) {
      for (final PaeContribuciones cont : sol.getPaeContribucionesByIdSolicitud()) {
        if (cont.getAacidValidada() != null) {
          totalSubVal = totalSubVal.add(UtilidadesNumero.convertStringToBigDecimalIfNotBlank(String.valueOf(cont.getAacidValidada())));
        } else if (cont.getAacid() != null) {
          totalSub = totalSub.add(cont.getAacid().setScale(3, RoundingMode.CEILING));
        }
      }
    }
    return totalSubVal.compareTo(BigDecimal.ZERO.setScale(3, RoundingMode.CEILING)) != 0 ? totalSubVal : totalSub;
  }

  private BigDecimal calcularValidado(final PaeSolicitudes sol) {

    BigDecimal totalAacid = BigDecimal.ZERO.setScale(3, RoundingMode.HALF_DOWN);
    BigDecimal totalAacidVal = BigDecimal.ZERO.setScale(3, RoundingMode.HALF_DOWN);

    BigDecimal totalSolicitante = BigDecimal.ZERO.setScale(3, RoundingMode.HALF_DOWN);
    BigDecimal totalSolicitanteVal = BigDecimal.ZERO.setScale(3, RoundingMode.HALF_DOWN);

    BigDecimal totalContribucion = BigDecimal.ZERO.setScale(3, RoundingMode.HALF_DOWN);
    BigDecimal totalContribucionVal = BigDecimal.ZERO.setScale(3, RoundingMode.HALF_DOWN);

    BigDecimal totalOtras = BigDecimal.ZERO.setScale(3, RoundingMode.HALF_DOWN);
    BigDecimal totalOtrasVal = BigDecimal.ZERO.setScale(3, RoundingMode.HALF_DOWN);

    if (sol != null && (sol.getPaeTipoSolicitud() != null && ConstantesTramitacion.PROYECTO.equalsIgnoreCase(sol.getPaeTipoSolicitud().getDenominacion())
        || ConstantesTramitacion.EMERGENCIA.equalsIgnoreCase(sol.getPaeTipoSolicitud().getDenominacion()))) {
      for (final PaeContribuciones paeContribuciones : sol.getPaeContribucionesByIdSolicitud()) {
        if (paeContribuciones.getAacidValidada() != null) {
          totalAacidVal = totalAacidVal.add(UtilidadesNumero.convertStringToBigDecimalIfNotBlank(String.valueOf(paeContribuciones.getAacidValidada())));
        } else if (paeContribuciones.getAacid() != null) {
          totalAacid = totalAacid.add(paeContribuciones.getAacid().setScale(3, RoundingMode.CEILING));
        }
        if (paeContribuciones.getSolicitanteValidada() != null) {
          totalSolicitanteVal = totalSolicitanteVal
              .add(UtilidadesNumero.convertStringToBigDecimalIfNotBlank(String.valueOf(paeContribuciones.getSolicitanteValidada())));
        } else if (paeContribuciones.getSolicitante() != null) {
          totalSolicitante = totalSolicitante.add(paeContribuciones.getSolicitante().setScale(3, RoundingMode.CEILING));
        }
        for (final PaeConContrapartes paeConContrapartes : paeContribuciones.getPaeConContrapartesByIdContribucion()) {
          if (paeConContrapartes.getContribucionValidada() != null) {
            totalContribucionVal = totalContribucionVal
                .add(UtilidadesNumero.convertStringToBigDecimalIfNotBlank(String.valueOf(paeConContrapartes.getContribucionValidada())));
          } else if (paeConContrapartes.getContribucion() != null) {
            totalContribucion = totalContribucion.add(paeConContrapartes.getContribucion()).setScale(3, RoundingMode.CEILING);
          }
        }
        for (final PaeConOtrasAportaciones otras : paeContribuciones.getListaOtrasAportaciones()) {
          if (otras.getContribucionValidada() != null) {
            totalOtrasVal = totalOtrasVal.add(otras.getContribucionValidada());
          } else if (otras.getContribucion() != null) {
            totalOtras = totalOtras.add(otras.getContribucion()).setScale(3, RoundingMode.CEILING);
          }
        }
      }
    }

    return (totalAacidVal.compareTo(BigDecimal.ZERO.setScale(3, RoundingMode.CEILING)) != 0
        || totalSolicitanteVal.compareTo(BigDecimal.ZERO.setScale(3, RoundingMode.CEILING)) != 0
        || totalContribucionVal.compareTo(BigDecimal.ZERO.setScale(3, RoundingMode.CEILING)) != 0
        || totalOtrasVal.compareTo(BigDecimal.ZERO.setScale(3, RoundingMode.CEILING)) != 0)
            ? totalAacidVal.add(totalSolicitanteVal).add(totalContribucionVal).add(totalOtrasVal)
            : totalAacid.add(totalSolicitante).add(totalContribucion).add(totalOtras);
  }

  @Override
  public BigDecimal obtenerImporteAacid(final PaeSolicitudes sol) throws TramitacionException {
    BigDecimal totalAacidVal = BigDecimal.ZERO;
    BigDecimal totalAacid = BigDecimal.ZERO;

    final List<PaeContribuciones> listaContribuciones = getPaeContribucionesBySolicitud((long) sol.getIdSolicitud());
    for (final PaeContribuciones contrib : listaContribuciones) {
      if (contrib.getAacidValidada() != null) {
        totalAacidVal = totalAacidVal.add(UtilidadesNumero.convertStringToBigDecimalIfNotBlank(String.valueOf(contrib.getAacidValidada())));
      } else if (contrib.getAacid() != null) {
        totalAacid = totalAacid.add(contrib.getAacid()).setScale(3, RoundingMode.CEILING);
      }
    }
    return totalAacidVal.add(totalAacid);
  }

  public IPaeContribucionesDao getPaeContribucionesDao() {
    return paeContribucionesDao;
  }

  public void setPaeContribucionesDao(final IPaeContribucionesDao paeContribucionesDao) {
    this.paeContribucionesDao = paeContribucionesDao;
  }

  public void setPaeSolicitudService(final IPaeSolicitudService paeSolicitudService) {
    this.paeSolicitudService = paeSolicitudService;
  }

}

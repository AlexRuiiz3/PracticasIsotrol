package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesMensajes;
import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudAuxiliarDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudTipoCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionSolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionTipoCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ISolicitudCriteriosDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Solicitud;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.SolicitudTipoCriterio;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TiposCriterios;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ICriteriosService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudCriteriosService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudTipoCriterioService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudesAuxiliarService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ITipoCriteriosService;

public class SolicitudCriteriosServiceImpl implements ISolicitudCriteriosService {

  private ISolicitudCriteriosDao solicitudCriteriosDao;
  private ISolicitudesAuxiliarService solicitudesAuxiliarService;
  private ISolicitudTipoCriterioService solicitudTipoCriterioService;
  private ITipoCriteriosService tipoCriteriosService;
  private ICriteriosService criteriosService;

  /**
   * @param idSolicitud
   * @param valoracionSolicitudDTO
   * @throws TramitacionException
   */
  private void guardarTipoCriterio(final long idSolicitud, final ValoracionTipoCriterioDTO tipoCriterioDTO, final String codFinalidad,
      final String tipoCriterio) throws TramitacionException {
    final SolicitudTipoCriterioDTO soliTipoCriterioDTO = solicitudTipoCriterioService.obtenerSolicitudTipoCriterioByIdSolicitud(idSolicitud, tipoCriterio);
    final Long idTipoCriterio = tipoCriteriosService.obtenerIdTipoCriterioByConvocatoriaFinalidadNombre(codFinalidad, tipoCriterio);
    final SolicitudTipoCriterio soliTipoCriterio = new SolicitudTipoCriterio();

    if (soliTipoCriterioDTO != null && soliTipoCriterioDTO.getId() != null) {
      soliTipoCriterio.setSotcNuId(soliTipoCriterioDTO.getId());
    }

    soliTipoCriterio.setAaciTCriteriosByTcriNuId(new TiposCriterios());
    soliTipoCriterio.getAaciTCriteriosByTcriNuId().setTcriNuId(idTipoCriterio);
    soliTipoCriterio.setAaciTSolicitudsubongdByIdSolicitud(new Solicitud());
    soliTipoCriterio.getAaciTSolicitudsubongdByIdSolicitud().setIdSolicitud(idSolicitud);

    soliTipoCriterio.setSotcTxObservaciones(tipoCriterioDTO.getObservaciones());

    if (ConstantesTramitacion.INCREMENTO.equals(tipoCriterio) && tipoCriterioDTO.getTotalPuntuacion() != null
        && tipoCriterioDTO.getTotalPuntuacion().compareTo(ConstantesTramitacion.TOTAL_PUNTUACION_MAX_INCREMENTO) > 0) {
      soliTipoCriterio.setSotcNuPuntuacionTotal(ConstantesTramitacion.TOTAL_PUNTUACION_MAX_INCREMENTO);
    } else {
      soliTipoCriterio.setSotcNuPuntuacionTotal(tipoCriterioDTO.getTotalPuntuacion());
    }
    solicitudTipoCriterioService.guardarValoracionTipoCriterio(soliTipoCriterio);

  }

  /**
   * @param idSolicitud
   * @param listaValoracionesEditadas
   * @throws TramitacionException
   */
  private void guardarValoracionesCriterio(final long idSolicitud, final List<ValoracionCriterioDTO> listaValoracionesEditadas) throws TramitacionException {
    if (CollectionUtils.isNotEmpty(listaValoracionesEditadas)) {
      SolicitudCriterioDTO solCritDTO;
      for (final ValoracionCriterioDTO valoracion : listaValoracionesEditadas) {
        solCritDTO = solicitudCriteriosDao.obtenerValoracionCriteriosByIdSolIdCrit(idSolicitud, valoracion.getIdCriterio());
        if (solCritDTO != null) {
          solCritDTO.setValor(valoracion.getValoracion());
          solCritDTO.setPuntuacion(valoracion.getPuntuacion());
        } else {
          solCritDTO = new SolicitudCriterioDTO(null, valoracion.getValoracion(), valoracion.getPuntuacion());
          solCritDTO.setIdCriterio(valoracion.getIdCriterio());
          solCritDTO.setIdSolicitud(idSolicitud);
          solCritDTO.setPuntuacion(valoracion.getPuntuacion());
          solCritDTO.setValor(valoracion.getValoracion());
        }
        solicitudCriteriosDao.guardarValoracionCriterio(solCritDTO);
      }
    }
  }

  @Override
  @Transactional
  public void guardarValoracionSolicitud(final List<ValoracionCriterioDTO> listaValoracionesEditadas, final ValoracionSolicitudDTO valoracionSolicitudDTO)
      throws TramitacionException {

    guardarValoracionesCriterio(valoracionSolicitudDTO.getIdSolicitud(), listaValoracionesEditadas);

    guardarTipoCriterio(valoracionSolicitudDTO.getIdSolicitud(), valoracionSolicitudDTO.getPertinencia(), valoracionSolicitudDTO.getCodigoFinalidad(),
        ConstantesTramitacion.PERTINENCIA);
    guardarTipoCriterio(valoracionSolicitudDTO.getIdSolicitud(), valoracionSolicitudDTO.getViabilidad(), valoracionSolicitudDTO.getCodigoFinalidad(),
        ConstantesTramitacion.VIABILIDAD);
    guardarTipoCriterio(valoracionSolicitudDTO.getIdSolicitud(), valoracionSolicitudDTO.getCoherencia(), valoracionSolicitudDTO.getCodigoFinalidad(),
        ConstantesTramitacion.COHERENCIA);
    if (ConstantesTramitacion.COD_ACCION_HUMA.equals(valoracionSolicitudDTO.getCodigoFinalidad())) {
      guardarTipoCriterio(valoracionSolicitudDTO.getIdSolicitud(), valoracionSolicitudDTO.getConectividad(), valoracionSolicitudDTO.getCodigoFinalidad(),
          ConstantesTramitacion.CONECTIVIDAD);
    } else {
      guardarTipoCriterio(valoracionSolicitudDTO.getIdSolicitud(), valoracionSolicitudDTO.getSostenibilidad(), valoracionSolicitudDTO.getCodigoFinalidad(),
          ConstantesTramitacion.SOSTENIBILIDAD);
    }
    guardarTipoCriterio(valoracionSolicitudDTO.getIdSolicitud(), valoracionSolicitudDTO.getImpacto(), valoracionSolicitudDTO.getCodigoFinalidad(),
        ConstantesTramitacion.IMPACTO);

    if (valoracionSolicitudDTO.isMostrarPestania3()) {
      if (ConstantesTramitacion.COD_COOPERACION.equals(valoracionSolicitudDTO.getCodigoFinalidad())) {
        guardarTipoCriterio(valoracionSolicitudDTO.getIdSolicitud(), valoracionSolicitudDTO.getConvergencia(), valoracionSolicitudDTO.getCodigoFinalidad(),
            ConstantesTramitacion.CONVERGENCIA);
      }
      guardarTipoCriterio(valoracionSolicitudDTO.getIdSolicitud(), valoracionSolicitudDTO.getCapacidadGestion(), valoracionSolicitudDTO.getCodigoFinalidad(),
          ConstantesTramitacion.CAP_GESTION);
    } else { // Seteamos la valoración de capacidad de gestión y convergencia si no se cumple la condición de la pestaña 3
      valoracionSolicitudDTO.setCapacidadGestion(criteriosService.obtenerListaCriteriosByFinalidadAndConvocatoria(valoracionSolicitudDTO.getCodigoFinalidad(),
          ConstantesTramitacion.CAP_GESTION, !valoracionSolicitudDTO.isOngd(), valoracionSolicitudDTO.getAnio(), false));
      valoracionSolicitudDTO.getCapacidadGestion().getListaValoraciones().stream().forEach(v -> {
        v.setPuntuacion(BigDecimal.ZERO);
        v.setValoracion(BigDecimal.ZERO);
      });
      guardarValoracionesCriterio(valoracionSolicitudDTO.getIdSolicitud(), valoracionSolicitudDTO.getCapacidadGestion().getListaValoraciones());
      if (ConstantesTramitacion.COD_COOPERACION.equals(valoracionSolicitudDTO.getCodigoFinalidad())) {
        valoracionSolicitudDTO.setConvergencia(criteriosService.obtenerListaCriteriosByFinalidadAndConvocatoria(valoracionSolicitudDTO.getCodigoFinalidad(),
            ConstantesTramitacion.CONVERGENCIA, !valoracionSolicitudDTO.isOngd(), valoracionSolicitudDTO.getAnio(), false));
        valoracionSolicitudDTO.getConvergencia().getListaValoraciones().stream().forEach(v -> {
          v.setPuntuacion(BigDecimal.ZERO);
          v.setValoracion(BigDecimal.ZERO);
        });
        guardarValoracionesCriterio(valoracionSolicitudDTO.getIdSolicitud(), valoracionSolicitudDTO.getConvergencia().getListaValoraciones());
      }
    }

    if (valoracionSolicitudDTO.isPermiteSumaIncrementos()) {
      guardarTipoCriterio(valoracionSolicitudDTO.getIdSolicitud(), valoracionSolicitudDTO.getIncrementos(), valoracionSolicitudDTO.getCodigoFinalidad(),
          ConstantesTramitacion.INCREMENTO);
    }

    solicitudesAuxiliarService.guardarValoracion(valoracionSolicitudDTO);
  }

  @Override
  public void obtenerValoracionCriterios(final Long nExp, final ValoracionTipoCriterioDTO valoraciones) {
    SolicitudCriterioDTO solicitudCriterioDTO = null;
    if (CollectionUtils.isNotEmpty(valoraciones.getListaValoraciones())) {
      for (final ValoracionCriterioDTO valCrit : valoraciones.getListaValoraciones()) {
        solicitudCriterioDTO = solicitudCriteriosDao.obtenerValoracionCriterios(nExp, valCrit.getIdCriterio());
        if (solicitudCriterioDTO != null) {
          valCrit.setPuntuacion(solicitudCriterioDTO.getPuntuacion());
          valCrit.setValoracion(solicitudCriterioDTO.getValor());
        } else {
          valCrit.setPuntuacion(BigDecimal.ZERO);
          valCrit.setValoracion(BigDecimal.ZERO);
        }
      }
    } else {
      valoraciones.setListaValoraciones(new ArrayList<>());
    }
  }

  /**
   * Establece el valor de la propiedad solicitudCriteriosDao
   *
   * @param solicitudCriteriosDao
   *          el solicitudCriteriosDao para establecer
   */
  public void setSolicitudCriteriosDao(final ISolicitudCriteriosDao solicitudCriteriosDao) {
    this.solicitudCriteriosDao = solicitudCriteriosDao;
  }

  /**
   * Establece el valor de la propiedad solicitudesAuxiliarService
   *
   * @param solicitudesAuxiliarService
   *          el solicitudesAuxiliarService para establecer
   */
  public void setSolicitudesAuxiliarService(final ISolicitudesAuxiliarService solicitudesAuxiliarService) {
    this.solicitudesAuxiliarService = solicitudesAuxiliarService;
  }

  /**
   * Establece el valor de la propiedad solicitudTipoCriterioService
   *
   * @param solicitudTipoCriterioService
   *          el solicitudTipoCriterioService para establecer
   */
  public void setSolicitudTipoCriterioService(final ISolicitudTipoCriterioService solicitudTipoCriterioService) {
    this.solicitudTipoCriterioService = solicitudTipoCriterioService;
  }

  /**
   * @param tipoCriterio
   * @param actionErrors
   * @param valoracionSolicitudDTO
   * @return
   */
  private String validarCampoObservaciones(final String observaciones, final String tipoCriterio, final Collection<String> actionErrors) {
    if (StringUtils.isNotEmpty(observaciones) && observaciones.length() > 4000) {
      actionErrors.add(MessageFormat.format(ConstantesMensajes.TIPO_CRITERIO_OBSV_TAM_MAX, tipoCriterio));
    }
    return StringUtils.EMPTY;
  }

  private String validarValoracionCriterios(final List<ValoracionCriterioDTO> listaValoracionesEditadas, final BigDecimal valoracionMax,
      final Collection<String> actionErrors) throws TramitacionException {
    for (final ValoracionCriterioDTO valoracion : listaValoracionesEditadas) {
      final String tipoCriterio = tipoCriteriosService.obtenerNombreTipoCriterioByidCriterio(valoracion.getIdCriterio());
      if (tipoCriterio.equals(ConstantesTramitacion.CONVERGENCIA)) {
        if (valoracion.getValoracion().compareTo(ConstantesTramitacion.VALORACION_MAX_CONV) > 0) {
          actionErrors.add(ConstantesMensajes.CRITERIO_VALOR_MAX);
        }
      } else if (valoracion.getValoracion().compareTo(valoracionMax) > 0) {
        actionErrors.add(ConstantesMensajes.CRITERIO_VALOR_MAX);
      }
    }
    return StringUtils.EMPTY;
  }

  @Override
  public Collection<String> validarValoracionSolicitud(final List<ValoracionCriterioDTO> listaValoracionesEditadas,
      final ValoracionSolicitudDTO valoracionSolicitudDTO, final boolean esONGD) throws TramitacionException {
    final Collection<String> errores = new ArrayList<>();
    validarCampoObservaciones(valoracionSolicitudDTO.getPertinencia().getObservaciones(), ConstantesTramitacion.PERTINENCIA, errores);
    validarCampoObservaciones(valoracionSolicitudDTO.getViabilidad().getObservaciones(), ConstantesTramitacion.VIABILIDAD, errores);
    validarCampoObservaciones(valoracionSolicitudDTO.getCoherencia().getObservaciones(), ConstantesTramitacion.COHERENCIA, errores);
    validarCampoObservaciones(valoracionSolicitudDTO.getSostenibilidad().getObservaciones(), ConstantesTramitacion.SOSTENIBILIDAD, errores);
    validarCampoObservaciones(valoracionSolicitudDTO.getImpacto().getObservaciones(), ConstantesTramitacion.IMPACTO, errores);
    validarCampoObservaciones(valoracionSolicitudDTO.getValoracionTotal().getObservaciones(), ConstantesTramitacion.VALORACION_TOTAL, errores);
    if (esONGD) {
      validarCampoObservaciones(valoracionSolicitudDTO.getConvergencia().getObservaciones(), ConstantesTramitacion.CONVERGENCIA, errores);
      validarCampoObservaciones(valoracionSolicitudDTO.getCapacidadGestion().getObservaciones(), ConstantesTramitacion.CAP_GESTION, errores);
      validarCampoObservaciones(valoracionSolicitudDTO.getIncrementos().getObservaciones(), ConstantesTramitacion.INCREMENTO, errores);
      // Campos Auxiliares
      validarValorMaxCampo(valoracionSolicitudDTO.getNumReintegros(), "Numero de reintegro", 999, errores);
      validarValorMaxCampo(valoracionSolicitudDTO.getCausaReintegros(), "Causa de reintegro", 4, errores);
      validarValorMaxCampo(valoracionSolicitudDTO.getMagnitudReintegros(), "Magnitud de los reintegros", 5, errores);
    }
    if (valoracionSolicitudDTO.getCodigoFinalidad().equalsIgnoreCase("AH")) {
      validarCampoObservaciones(valoracionSolicitudDTO.getConectividad().getObservaciones(), ConstantesTramitacion.CONECTIVIDAD, errores);
    }

    validarValoracionCriterios(listaValoracionesEditadas, ConstantesTramitacion.VALORACION_MAX, errores);

    return errores;
  }

  private void validarValorMaxCampo(final Long campo, final String nombreCampo, final int tamanio, final Collection<String> errores) {
    if (campo != null && campo > tamanio) {
      errores.add(MessageFormat.format(ConstantesMensajes.ERROR_CAMPO_SUPERA_MAX, nombreCampo, tamanio));
    }
  }

  /**
   * Establece el valor de la propiedad tipoCriteriosService
   *
   * @param tipoCriteriosService
   *          el tipoCriteriosService para establecer
   */
  public void setTipoCriteriosService(final ITipoCriteriosService tipoCriteriosService) {
    this.tipoCriteriosService = tipoCriteriosService;
  }

  @Override
  public Map<String, Object> obtenerDatosInformeValoracion(final Long idExpTrewa, final Long idSolicitud, final ValoracionSolicitudDTO valoracionSolicitudDTO,
      final Long anyo, final boolean filtrarSubtipo) throws TramitacionException {
    final Map<String, Object> map = new HashMap<>();
    final List<ValoracionTipoCriterioDTO> listaValoracionesPadre = new ArrayList<>();
    final List<ValoracionTipoCriterioDTO> listaValoracionesPadreFase1 = new ArrayList<>();
    final List<ValoracionTipoCriterioDTO> listaValoracionesPadreFase2 = new ArrayList<>();
    BigDecimal puntuacionTotalFase1 = BigDecimal.ZERO;
    BigDecimal puntuacionTotalFase2 = BigDecimal.ZERO;
    BigDecimal puntuacionTotalF1F2 = BigDecimal.ZERO;
    BigDecimal puntuacionMaximaTotalFase1 = BigDecimal.ZERO;
    BigDecimal puntuacionMaximaTotalFase2 = BigDecimal.ZERO;
    BigDecimal puntuacionMaximaTotalFase1Fase2 = BigDecimal.ZERO;
    try {
      obtenemosListadoValoraciones(idExpTrewa, idSolicitud, ConstantesTramitacion.PERTINENCIA, valoracionSolicitudDTO, anyo, listaValoracionesPadre,
          listaValoracionesPadreFase1, filtrarSubtipo);
      obtenemosListadoValoraciones(idExpTrewa, idSolicitud, ConstantesTramitacion.VIABILIDAD, valoracionSolicitudDTO, anyo, listaValoracionesPadre,
          listaValoracionesPadreFase1, filtrarSubtipo);
      obtenemosListadoValoraciones(idExpTrewa, idSolicitud, ConstantesTramitacion.COHERENCIA, valoracionSolicitudDTO, anyo, listaValoracionesPadre,
          listaValoracionesPadreFase1, filtrarSubtipo);
      if (ConstantesTramitacion.COD_ACCION_HUMA.equals(valoracionSolicitudDTO.getCodigoFinalidad())) {
        obtenemosListadoValoraciones(idExpTrewa, idSolicitud, ConstantesTramitacion.CONECTIVIDAD, valoracionSolicitudDTO, anyo, listaValoracionesPadre,
            listaValoracionesPadreFase1, filtrarSubtipo);
      } else {
        obtenemosListadoValoraciones(idExpTrewa, idSolicitud, ConstantesTramitacion.SOSTENIBILIDAD, valoracionSolicitudDTO, anyo, listaValoracionesPadre,
            listaValoracionesPadreFase1, filtrarSubtipo);
      }
      obtenemosListadoValoraciones(idExpTrewa, idSolicitud, ConstantesTramitacion.IMPACTO, valoracionSolicitudDTO, anyo, listaValoracionesPadre,
          listaValoracionesPadreFase1, filtrarSubtipo);
      puntuacionTotalFase1 = calcularPuntuacionTotal(listaValoracionesPadreFase1);
      puntuacionMaximaTotalFase1 = calcularPuntuacionMaximaTotal(listaValoracionesPadreFase1);
      puntuacionTotalF1F2 = puntuacionTotalFase1;
      puntuacionMaximaTotalFase1Fase2 = puntuacionMaximaTotalFase1;
      if (valoracionSolicitudDTO.isOngd()) {
        if (ConstantesTramitacion.COD_COOPERACION.equals(valoracionSolicitudDTO.getCodigoFinalidad())) {
          obtenemosListadoValoraciones(idExpTrewa, idSolicitud, ConstantesTramitacion.CONVERGENCIA, valoracionSolicitudDTO, anyo, listaValoracionesPadre,
              listaValoracionesPadreFase2, filtrarSubtipo);
        }
        obtenemosListadoValoraciones(idExpTrewa, idSolicitud, ConstantesTramitacion.CAP_GESTION, valoracionSolicitudDTO, anyo, listaValoracionesPadre,
            listaValoracionesPadreFase2, filtrarSubtipo);

        final List<ValoracionTipoCriterioDTO> listaCriteriosFase2 = new ArrayList<>(listaValoracionesPadreFase2);

        final ValoracionTipoCriterioDTO incremento = obtenemosListadoValoraciones(idExpTrewa, idSolicitud, ConstantesTramitacion.INCREMENTO,
            valoracionSolicitudDTO, anyo, listaValoracionesPadre, listaValoracionesPadreFase2, false);

        puntuacionTotalFase2 = calcularPuntuacionTotal(listaCriteriosFase2);
        puntuacionMaximaTotalFase2 = calcularPuntuacionMaximaTotal(listaCriteriosFase2);

        final List<ValoracionTipoCriterioDTO> valoracionTipoCriterioDTOIncremento = new ArrayList<>();
        valoracionTipoCriterioDTOIncremento.add(incremento);
        formatearTodasPuntuacion(valoracionTipoCriterioDTOIncremento);

        map.put("listaIncrementos", incremento.getListaValoraciones());
        map.put("totalIncrementos",
            UtilidadesNumero.bigDecimalToStringConverterPuntuacion(
                    incremento.getTotalPuntuacion().setScale(3, RoundingMode.CEILING)));
        puntuacionTotalF1F2 = puntuacionTotalF1F2.add(puntuacionTotalFase2).add(incremento.getTotalPuntuacion());
        puntuacionMaximaTotalFase1Fase2 = puntuacionMaximaTotalFase1Fase2.add(puntuacionMaximaTotalFase2).add(incremento.getTotalPuntuacion());
      }
      /* Obtenemos observaciones otras de la pestanya totales */
      final SolicitudAuxiliarDTO solicitudAuxiliarDTO = solicitudesAuxiliarService.findByIdSolicitud(idSolicitud);
      if (solicitudAuxiliarDTO != null && solicitudAuxiliarDTO.getId() != null) {
        valoracionSolicitudDTO.getValoracionTotal().setObservaciones(solicitudAuxiliarDTO.getObservaciones());
      }
      formatearTodasPuntuacion(listaValoracionesPadre);
      formatearTodasPuntuacion(listaValoracionesPadreFase1);
      formatearTodasPuntuacion(listaValoracionesPadreFase2);

      map.put("valoracionSuperada", valoracionSuperadaPorTipoCriterio(listaValoracionesPadreFase1));
      map.put("listaCriterios", listaValoracionesPadre);
      map.put("listaCriteriosFase1", listaValoracionesPadreFase1);
      map.put("listaCriteriosFase2", listaValoracionesPadreFase2);
      map.put("totalCalStr", UtilidadesNumero.bigDecimalToStringConverterPuntuacion(
              puntuacionTotalF1F2.setScale(3, RoundingMode.CEILING)));
      final BigDecimal totalCal = puntuacionTotalF1F2.setScale(3, RoundingMode.CEILING);
      map.put("totalCal", totalCal);
      map.put("totalPuntuacionMaxima", puntuacionMaximaTotalFase1Fase2.setScale(3, RoundingMode.CEILING));
      map.put("total", UtilidadesNumero.bigDecimalToStringConverterPuntuacion(puntuacionTotalFase1.add(puntuacionTotalFase2)));
      map.put("totalFase1", UtilidadesNumero.bigDecimalToStringConverterPuntuacion(puntuacionTotalFase1.setScale(3, RoundingMode.CEILING)));
      map.put("totalFase2", UtilidadesNumero.bigDecimalToStringConverterPuntuacion(puntuacionTotalFase2.setScale(3, RoundingMode.CEILING)));
      map.put("Otros", valoracionSolicitudDTO.getValoracionTotal().getObservaciones());

    } catch (final TramitacionException e) {
      throw new TramitacionException("Se ha producido un error al recuperar los criterios de valoracion", e);
    }
    return map;
  }

  public void formatearTodasPuntuacion(final List<ValoracionTipoCriterioDTO> listaValoraciones) {
    for (final ValoracionTipoCriterioDTO val : listaValoraciones) {
      if (val.getPuntuacionMaxima() != null) {
        val.setPuntuacionMaximaStr(UtilidadesNumero.bigDecimalToStringConverterPuntuacion(val.getPuntuacionMaxima().setScale(3, RoundingMode.CEILING)));
      }
      if (val.getTotalPuntuacion() != null) {
        val.setTotalPuntuacionStr(UtilidadesNumero.bigDecimalToStringConverterPuntuacion(val.getTotalPuntuacion().setScale(3, RoundingMode.CEILING)));
      }
      for (final ValoracionCriterioDTO valoracionCriterio : val.getListaValoraciones()) {

        if (val.getNombreTipoCriterio().equalsIgnoreCase(ConstantesTramitacion.CAP_GESTION)) {
          valoracionCriterio.setPuntuacion(val.getTotalPuntuacion());

        }
        if (valoracionCriterio.getPuntuacion() != null) {
          valoracionCriterio.setPuntuacionStr(
              UtilidadesNumero.bigDecimalToStringConverterPuntuacion(valoracionCriterio.getPuntuacion().setScale(3, RoundingMode.CEILING)));
        }
        if (valoracionCriterio.getValorMaximo() != null) {
          valoracionCriterio.setValorMaximoStr(
              UtilidadesNumero.bigDecimalToStringConverterPuntuacion(valoracionCriterio.getValorMaximo().setScale(3, RoundingMode.CEILING)));
        }
      }
    }
  }

  /**
   * @param idExpTrewa
   * @param idSolicitud
   * @param valoracionSolicitudDTO
   * @param anyo
   * @param listaValoracionesPadre
   * @param listaValoracionesPadreFase1
   * @return
   * @throws TramitacionException
   */
  private ValoracionTipoCriterioDTO obtenemosListadoValoraciones(final Long idExpTrewa, final Long idSolicitud, final String tipoCriterio,
      final ValoracionSolicitudDTO valoracionSolicitudDTO, final Long anyo, final List<ValoracionTipoCriterioDTO> listaValoracionesPadre,
      final List<ValoracionTipoCriterioDTO> listaValoracionesPadreFase1, final boolean filtrarSubtipo) throws TramitacionException {
    ValoracionTipoCriterioDTO valoracionTipoCriterioDTO = obtenerDatosValoracionPorTipoCriterio(idSolicitud, valoracionSolicitudDTO, anyo, tipoCriterio,
        filtrarSubtipo);
    if (valoracionTipoCriterioDTO != null) {
      obtenerValoracionCriterios(idExpTrewa, valoracionTipoCriterioDTO);
      if (!StringUtils.equals(tipoCriterio, ConstantesTramitacion.INCREMENTO)) {
        listaValoracionesPadre.add(valoracionTipoCriterioDTO);
        listaValoracionesPadreFase1.add(valoracionTipoCriterioDTO);
      }
    } else {
      valoracionTipoCriterioDTO = new ValoracionTipoCriterioDTO();
    }
    return valoracionTipoCriterioDTO;
  }

  private BigDecimal calcularPuntuacionTotal(final List<ValoracionTipoCriterioDTO> listaValoraciones) {
    return listaValoraciones.stream().reduce(BigDecimal.ZERO, (bd, item) -> bd.add(item.getTotalPuntuacion()), BigDecimal::add);
  }

  private BigDecimal calcularPuntuacionMaximaTotal(final List<ValoracionTipoCriterioDTO> listaValoraciones) {
    return listaValoraciones.stream().reduce(BigDecimal.ZERO, (bd, item) -> bd.add(item.getPuntuacionMaxima()), BigDecimal::add);
  }

  /**
   * @param idSolicitud
   * @param valoracionSolicitudDTO
   * @param anyo
   * @param tipoCriterio
   * @throws TramitacionException
   */
  private ValoracionTipoCriterioDTO obtenerDatosValoracionPorTipoCriterio(final Long idSolicitud, final ValoracionSolicitudDTO valoracionSolicitudDTO,
      final Long anyo, final String tipoCriterio, final boolean filtrarSubtipo) throws TramitacionException {
    ValoracionTipoCriterioDTO valoracionTipoCriterioDTO = new ValoracionTipoCriterioDTO();
    final SolicitudTipoCriterioDTO solicitudTipoCriterio = solicitudTipoCriterioService.obtenerSolicitudTipoCriterioByIdSolicitud(idSolicitud, tipoCriterio);

    if (solicitudTipoCriterio != null) {
      valoracionTipoCriterioDTO = criteriosService.obtenerListaCriteriosByFinalidadAndConvocatoria(valoracionSolicitudDTO.getCodigoFinalidad(), tipoCriterio,
          !valoracionSolicitudDTO.isOngd(), anyo, filtrarSubtipo);
      if (valoracionTipoCriterioDTO != null) {
        valoracionTipoCriterioDTO.setNombreTipoCriterio(tipoCriterio);
        valoracionTipoCriterioDTO.setTotalPuntuacion(solicitudTipoCriterio.getPuntuacionTotal());
        valoracionTipoCriterioDTO.setPuntuacionMaxima(valoracionTipoCriterioDTO.getPuntuacionMaxima());
        valoracionTipoCriterioDTO.setObservaciones(solicitudTipoCriterio.getObservaciones());
      }
    } else if (tipoCriterio.equalsIgnoreCase(ConstantesTramitacion.CONVERGENCIA) || tipoCriterio.equalsIgnoreCase(ConstantesTramitacion.CAP_GESTION)
        || tipoCriterio.equalsIgnoreCase(ConstantesTramitacion.INCREMENTO)) {
      valoracionTipoCriterioDTO.setNombreTipoCriterio(tipoCriterio);
      valoracionTipoCriterioDTO.setTotalPuntuacion(BigDecimal.ZERO);
      valoracionTipoCriterioDTO.setPuntuacionMaxima(BigDecimal.ZERO);
      valoracionTipoCriterioDTO.setObservaciones("");
    } else {
      throw new TramitacionException(ConstantesTramitacion.ERROR_SOLICITUD_NO_VALORADA);
    }

    return valoracionTipoCriterioDTO;
  }

  @Override
  public BigDecimal obtenerPuntuacionTotalSolicitud(final Long idSolicitud) {
    return solicitudCriteriosDao.obtenerPuntuacionTotalSolicitud(idSolicitud);
  }

  private boolean valoracionSuperadaPorTipoCriterio(final List<ValoracionTipoCriterioDTO> listaValoraciones) {
    for (final ValoracionTipoCriterioDTO valoracionTipoCriterioDTO : listaValoraciones) {
      if (valoracionTipoCriterioDTO.getTotalPuntuacion().doubleValue() < valoracionTipoCriterioDTO.getPuntuacionMaxima()
          .divide(new BigDecimal(2), 2, RoundingMode.HALF_DOWN).doubleValue()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Establece el valor de la propiedad criteriosService
   *
   * @param criteriosService
   *          el criteriosService para establecer
   */
  public void setCriteriosService(final ICriteriosService criteriosService) {
    this.criteriosService = criteriosService;
  }
}
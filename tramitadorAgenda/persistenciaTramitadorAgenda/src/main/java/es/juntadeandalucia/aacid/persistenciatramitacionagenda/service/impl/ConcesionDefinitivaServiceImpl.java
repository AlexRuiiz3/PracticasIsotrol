package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ConvocatoriaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudAuxiliarDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionSolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesFecha;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesListas;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IPaisSolicitudDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.SolicitudConcesion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeSolicitudes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IConcesionDefinitivaService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IConvocatoriasService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IExclusionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IFinalidadService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeContribucionesService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeSolicitudService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudConcesionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudCriteriosService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudTipoCriterioService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudesAuxiliarService;
import es.juntadeandalucia.plataforma.comunes.excepciones.BusinessException;
import es.juntadeandalucia.plataforma.resources.ConstantesBean;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;
import trewa.bd.trapi.trapiui.tpo.TrInteresadoExpediente;

public class ConcesionDefinitivaServiceImpl implements IConcesionDefinitivaService {

  private IFinalidadService finalidadService;
  private IConvocatoriasService convocatoriasService;
  private ISolicitudService solicitudService;
  private ITrewaService trewaService;
  private ISolicitudConcesionService solicitudConcesionService;
  private ISolicitudCriteriosService solicitudCriteriosService;
  private IPaeSolicitudService paeSolicitudService;
  private IPaisSolicitudDao paisSolicitudDao;
  private ISolicitudesAuxiliarService solicitudesAuxiliarService;
  private ISolicitudTipoCriterioService solicitudTipoCriterioService;
  private IExclusionService exclusionService;
  private IPaeContribucionesService paeContribucionesService;

  private final Logger log = Logger.getLogger(getClass());

  @Override
  public Map<String, Object> generarListadoConcesionDefinitiva(final UsuarioWeb usuarioSesion, final boolean isUniv)
      throws TramitacionException, NumberFormatException, BusinessException {

    final Map<String, Object> mapaFinal = new HashMap<>();
    int tipoProyecto = 1;
    while (tipoProyecto <= 4) {
      mapaFinal.putAll(estableceListaResolucionesDefinitivas(mapaFinal, usuarioSesion, tipoProyecto, isUniv));
      tipoProyecto++;
    }
    estableceValoresComunes(mapaFinal, isUniv, usuarioSesion);
    return mapaFinal;
  }

  private void estableceValoresComunes(final Map<String, Object> mapaFinal, final boolean isUniv, final UsuarioWeb usuarioSesion) throws TramitacionException {
    final Calendar cal = Calendar.getInstance();
    final int year = cal.get(Calendar.YEAR);
    final String finalidad = obtenerFinalidadByTipoInforme(usuarioSesion, isUniv);
    if (finalidad != null) {
      mapaFinal.put(ConstantesTramitacion.FINALIDAD_PROYECTO, finalidad);
    } else {
      mapaFinal.put(ConstantesTramitacion.FINALIDAD_PROYECTO, ConstantesTramitacion.FINALIDAD_NO_DEFINIDA);
    }
    mapaFinal.put(ConstantesTramitacion.FECHA_PUBLICACION_ACCID, UtilidadesFecha.obtenerFechaModoTexto(new Date()));
    mapaFinal.put(ConstantesTramitacion.ANYO_CONVOCATORIA, String.valueOf(year));
    final ConvocatoriaDTO convocatoria = obtenerFechasConvocatoria(usuarioSesion);
    if (convocatoria != null && convocatoria.getFhLimitDocResolDef() != null) {
      mapaFinal.put(ConstantesTramitacion.FECHA_RESOLUCION, UtilidadesFecha.obtenerFechaModoTexto(convocatoria.getFhLimitDocResolDef()));
    } else {
      mapaFinal.put(ConstantesTramitacion.FECHA_RESOLUCION, ConstantesTramitacion.FECHA_RESOLUCION_NO_DEFINIDA);
    }
    mapaFinal.put(ConstantesTramitacion.FECHA_FIN_PRESENTACIONES, UtilidadesFecha
        .obtenerFechaModoTexto(obtenerFecFinPresentaAlegOrSolicitudes(new Date(), Integer.parseInt(ConstantesTramitacion.PLAZO_FECHA_FIN_PRESENTACIONES))));
  }

  @Override
  public Map<String, Object> generarConcesionDefinitiva(final UsuarioWeb usuarioSesion, final boolean isUniv) throws TramitacionException {
    final Map<String, Object> mapaFinal = new HashMap<>();
    estableceValoresComunes(mapaFinal, isUniv, usuarioSesion);
    return mapaFinal;
  }

  private Map<String, Object> estableceListaResolucionesDefinitivas(final Map<String, Object> mapaFinal, final UsuarioWeb usuarioSesion, final int tipoProyecto,
      final boolean isUniv) throws TramitacionException, NumberFormatException {

    final List<SolicitudDTO> solicitudesTabla = new ArrayList<>();
    final List<SolicitudDTO> solicitudesTablaExclusiones = new ArrayList<>();
    final List<SolicitudDTO> solicitudesTablaPACODE = new ArrayList<>();
    try {

      final ConvocatoriaDTO convocatoriaDTO = convocatoriasService.buscarConvocatoriaDTOPorIdExpediente(usuarioSesion.getExpediente().getRefExpediente());
      final List<String> paisesPacode = cargaListadoPACODE();

      final List<SolicitudDTO> solicitudes = solicitudService.obtenerSolicitudesByTipoProyectoDocumento(convocatoriaDTO.getIdConv(), tipoProyecto, isUniv);
      if (CollectionUtils.isEmpty(solicitudes)) {
        log.warn("La convocatoria " + convocatoriaDTO.getIdConv() + " no tiene solicitudes para el tipo proyecto: " + tipoProyecto);
        return mapaFinal;
      }
      for (final SolicitudDTO solicitud : solicitudes) {
        final TrExpediente expediente = trewaService.obtenerExpedienteTrewa(String.valueOf(solicitud.getNumExpediente()));
        if (expediente == null) {
          throw new TramitacionException("La solicitud " + solicitud.getTitulo() + " no tiene expediente trewa asignado");
        }
        final boolean isExcluida = trewaService.compruebaFaseCorrectaExpediente(expediente, ConstantesTramitacion.FASE_RESOLUCION_DEFINITIVA_DESESTIMACION);
        final boolean isDefinitivaConcesion = trewaService.compruebaFaseCorrectaExpediente(expediente,
            ConstantesTramitacion.FASE_RESOLUCION_DEFINITIVA_CONCESION);
        if (isDefinitivaConcesion || isExcluida) {
          final SolicitudConcesion solicitudConcesion = solicitudConcesionService.obtenerSolicitudConcesionPorSolicitud(solicitud.getIdSolicitud());

          anyadeDatosTrewaSolicitudDTO(solicitud, expediente);
          anyadeDatosSolicitudDTO(solicitud, isUniv, solicitudConcesion, isExcluida);

          estableceListaBeneficiariasOExcluidas(solicitud, solicitudesTabla, solicitudesTablaPACODE, solicitudesTablaExclusiones, paisesPacode, isUniv,
              isExcluida);

          if (isExcluida) {
            obtenerListasCausasDesestimiento(solicitud);
          } else {
            solicitud.setPlazoEjecucion(UtilidadesNumero.bigDecimalToStringConverterNoDecimals(solicitud.getPlazo()));
          }
        } else {
          log.error("El expediente " + expediente.getREFEXP().toString() + " no se encuentra en la fase correcta");
        }
      }
      anyadeSolicitudesATabla(solicitudesTabla, solicitudesTablaPACODE, solicitudesTablaExclusiones, tipoProyecto, mapaFinal);
    } catch (final NumberFormatException ex) {
      log.error("Error al convertir numero en cadena o viceversa. Causa: " + ex.getMessage(), ex);
    } catch (final TramitacionException e) {
      log.error(e.getMessage(), e);
    }
    return mapaFinal;
  }

  private void anyadeSolicitudesATabla(final List<SolicitudDTO> solicitudesTabla, final List<SolicitudDTO> solicitudesTablaPACODE,
      final List<SolicitudDTO> solicitudesTablaExclusiones, final int tipoProyecto, final Map<String, Object> mapaFinal) {
    if (CollectionUtils.isNotEmpty(solicitudesTabla)) {
      UtilidadesListas.ordenamientoBurbujaListaSolicitudesByPuntuacion(solicitudesTabla);
      mapaFinal.put(listaTipoProyecto(tipoProyecto, false, false), solicitudesTabla);
    }
    if (CollectionUtils.isNotEmpty(solicitudesTablaPACODE)) {
      UtilidadesListas.ordenamientoBurbujaListaSolicitudesByPuntuacion(solicitudesTablaPACODE);
      mapaFinal.put(listaTipoProyecto(tipoProyecto, true, false), solicitudesTablaPACODE);
    }
    if (CollectionUtils.isNotEmpty(solicitudesTablaExclusiones)) {
      UtilidadesListas.ordenamientoBurbujaListaSolicitudesByNumExpediente(solicitudesTablaExclusiones);
      mapaFinal.put(listaTipoProyecto(tipoProyecto, false, true), solicitudesTablaExclusiones);
    }
  }

  private String listaTipoProyecto(final int cont, final boolean isPacode, final boolean isExcluida) {
    String listaTipoProyecto = "";
    switch (cont) {
    case 1:
      if (isExcluida) {
        listaTipoProyecto = ConstantesTramitacion.EXP_PROY_COOP_INT_EXCL;
      } else if (!isPacode) {
        listaTipoProyecto = ConstantesTramitacion.EXP_PROY_COOP_INT;
      } else {
        listaTipoProyecto = ConstantesTramitacion.EXP_PROY_COOP_INT_PACODE;
      }
      break;
    case 2:
      if (isExcluida) {
        listaTipoProyecto = ConstantesTramitacion.EXP_PROY_ACC_HUM_EXCL;

      } else if (!isPacode) {
        listaTipoProyecto = ConstantesTramitacion.EXP_PROY_ACC_HUM;
      } else {
        listaTipoProyecto = ConstantesTramitacion.EXP_PROY_ACC_HUM_PACODE;
      }
      break;
    case 3:
      if (isExcluida) {
        listaTipoProyecto = ConstantesTramitacion.EXP_PROY_DESARROLLO_EXCL;

      } else {
        listaTipoProyecto = ConstantesTramitacion.EXP_PROY_DESARROLLO;
      }
      break;
    case 4:
      if (isExcluida) {
        listaTipoProyecto = ConstantesTramitacion.EXP_PROY_FOR_INNOV_EXCL;
      } else {
        listaTipoProyecto = ConstantesTramitacion.EXP_PROY_FOR_INNOV;
      }
      break;
    default:
      break;
    }
    return listaTipoProyecto;
  }

  private void anyadeDatosSolicitudDTO(final SolicitudDTO solicitud, final boolean isUniv, final SolicitudConcesion solicitudConcesion,
      final boolean isExcluida) throws TramitacionException {
    BigDecimal puntuacion = solicitudCriteriosService.obtenerPuntuacionTotalSolicitud(solicitud.getIdSolicitud());
    puntuacion = puntuacion.setScale(3, RoundingMode.HALF_UP);

    if (!isUniv) {
      final PaeSolicitudes paeSolicitudes = paeSolicitudService.obtenerSolicitudExpByCodigo(solicitud.getCodIdentificativo());
      solicitud.setPlazo(paeSolicitudes != null && paeSolicitudes.getPlazo() != null ? paeSolicitudes.getPlazo() : BigDecimal.ZERO);
    }

    final String paises = paisSolicitudDao.obtenerPaisesSeparadosPorComaBySolicitud(solicitud.getIdSolicitud()) != null
        ? paisSolicitudDao.obtenerPaisesSeparadosPorComaBySolicitud(solicitud.getIdSolicitud()).toUpperCase()
        : "";
    solicitud.setPoblacion(paises);

    paeContribucionesService.estableceValorEconomicoConcesion(solicitud, isUniv, solicitudConcesion, isExcluida);

    solicitud.setTitulo(solicitud.getTitulo() != null ? solicitud.getTitulo().toUpperCase() : "");
    solicitud.setPuntuacion(UtilidadesNumero.bigDecimalToStringConverterTwoDecimals(puntuacion));
    solicitud.setPuntuacionNum(puntuacion);

  }

  private void anyadeDatosTrewaSolicitudDTO(final SolicitudDTO solicitud, final TrExpediente expediente) throws TramitacionException {
    final TrInteresadoExpediente inteExp = trewaService.obtenerInteresadoExpediente(expediente.getREFEXP().toString());
    String interesado = "";
    String numIdentInteresado = "";
    if (inteExp == null || inteExp.getINTERESADO() == null) {
      log.warn("No se han encontrado interesados para el expediente: " + expediente.getREFEXP().toString());

    } else {
      String primerApellido = "";
      String segundoApellido = "";
      if (inteExp.getINTERESADO() != null && inteExp.getINTERESADO().getAPELLIDO1() != null && !inteExp.getINTERESADO().getAPELLIDO1().equals("-")
          && !inteExp.getINTERESADO().getAPELLIDO1().isEmpty()) {
        primerApellido = " " + inteExp.getINTERESADO().getAPELLIDO1();
      }
      if (inteExp.getINTERESADO() != null && inteExp.getINTERESADO().getAPELLIDO2() != null && !inteExp.getINTERESADO().getAPELLIDO2().equals("-")
          && !inteExp.getINTERESADO().getAPELLIDO2().isEmpty()) {
        segundoApellido = " " + inteExp.getINTERESADO().getAPELLIDO2();
      }

      interesado = inteExp.getINTERESADO().getNOMBRE() + primerApellido + segundoApellido;
      numIdentInteresado = inteExp.getINTERESADO().getNUMIDENT();
    }
    solicitud.setCif(numIdentInteresado);
    solicitud.setEntidad(interesado);
    final String numExp = expediente.getNUMEXP() + (solicitud.getCodIdentificativo() != null ? (" (" + solicitud.getCodIdentificativo() + ")") : "");
    solicitud.setNumExpTrewa(numExp);
  }

  private void estableceListaBeneficiariasOExcluidas(final SolicitudDTO solicitud, final List<SolicitudDTO> solicitudesTabla,
      final List<SolicitudDTO> solicitudesTablaPACODE, final List<SolicitudDTO> solicitudesTablaExclusiones, final List<String> paisesPacode,
      final boolean isUniv, final boolean isExcluida) {

    if (isExcluida) {
      solicitudesTablaExclusiones.add(solicitud);
    } else if (compruebaPaisPacode(paisesPacode, solicitud.getPoblacion())) {
      if (!isUniv) {

        solicitudesTablaPACODE.add(solicitud);
      } else {
        solicitudesTabla.add(solicitud);
      }
    } else {
      solicitudesTabla.add(solicitud);
    }
  }

  public void setExclusionService(final IExclusionService exclusionService) {
    this.exclusionService = exclusionService;
  }

  /**
   * Obtiene la finalidad del proyecto teniendo en cuenta si es ONGD o Universidad y si es para el informe correspondiente a convocatoria
   * pone el texto en mayusculas.
   *
   * @param usuarioSesion
   * @param isUniv
   * @return
   * @throws TramitacionException
   */
  private String obtenerFinalidadByTipoInforme(final UsuarioWeb usuarioSesion, final boolean isUniv) throws TramitacionException {
    String finalidad = "";
    if (isUniv) {
      finalidad = finalidadService.obtenerFinalidadByNumExpediente(Long.valueOf(usuarioSesion.getExpediente().getRefExpediente()), true);
    } else {
      finalidad = finalidadService.obtenerFinalidadByNumExpediente(Long.valueOf(usuarioSesion.getExpediente().getRefExpediente()), false);
    }
    return finalidad;
  }

  public void setFinalidadService(final IFinalidadService finalidadService) {
    this.finalidadService = finalidadService;
  }

  private ConvocatoriaDTO obtenerFechasConvocatoria(final UsuarioWeb usuarioSesion) throws TramitacionException {
    try {
      return convocatoriasService.buscarConvocatoriaDTOPorIdExpediente(usuarioSesion.getExpediente().getRefExpediente());
    } catch (final TramitacionException e) {
      throw new TramitacionException("Error al obtener los datos para la lista de resoluci\u00F3n definitiva", e);
    }
  }

  public ValoracionSolicitudDTO estableceAtributosValoracionSolicitud(final Long idSolicitud, final ValoracionSolicitudDTO valoracionSolicitudDTO)
      throws TramitacionException {
    valoracionSolicitudDTO.setIdSolicitud(idSolicitud);

    final SolicitudAuxiliarDTO solicitudAuxiliarDTO = solicitudesAuxiliarService.findByIdSolicitud(idSolicitud);
    if (solicitudAuxiliarDTO != null && solicitudAuxiliarDTO.getId() != null) {
      valoracionSolicitudDTO.setNumReintegros(solicitudAuxiliarDTO.getNumReintegros());
      valoracionSolicitudDTO.setCausaReintegros(solicitudAuxiliarDTO.getCausaReintegro());
      valoracionSolicitudDTO.setMagnitudReintegros(solicitudAuxiliarDTO.getMagnitudReintegro());
      valoracionSolicitudDTO.getValoracionTotal().setObservaciones(solicitudAuxiliarDTO.getObservaciones());
    }

    if (valoracionSolicitudDTO.isOngd()) {
      if (ConstantesTramitacion.COD_COOPERACION.equals(valoracionSolicitudDTO.getCodigoFinalidad())) {
        solicitudTipoCriterioService.obtenerDatosTipoCriterio(idSolicitud, ConstantesTramitacion.CONECTIVIDAD, valoracionSolicitudDTO.getConectividad());
      }
      solicitudTipoCriterioService.obtenerDatosTipoCriterio(idSolicitud, ConstantesTramitacion.CONVERGENCIA, valoracionSolicitudDTO.getConvergencia());
      solicitudTipoCriterioService.obtenerDatosTipoCriterio(idSolicitud, ConstantesTramitacion.CAP_GESTION, valoracionSolicitudDTO.getCapacidadGestion());
      solicitudTipoCriterioService.obtenerDatosTipoCriterio(idSolicitud, ConstantesTramitacion.INCREMENTO, valoracionSolicitudDTO.getIncrementos());
    }

    solicitudTipoCriterioService.obtenerDatosTipoCriterio(idSolicitud, ConstantesTramitacion.PERTINENCIA, valoracionSolicitudDTO.getPertinencia());
    solicitudTipoCriterioService.obtenerDatosTipoCriterio(idSolicitud, ConstantesTramitacion.VIABILIDAD, valoracionSolicitudDTO.getViabilidad());
    solicitudTipoCriterioService.obtenerDatosTipoCriterio(idSolicitud, ConstantesTramitacion.COHERENCIA, valoracionSolicitudDTO.getCoherencia());
    solicitudTipoCriterioService.obtenerDatosTipoCriterio(idSolicitud, ConstantesTramitacion.SOSTENIBILIDAD, valoracionSolicitudDTO.getSostenibilidad());
    solicitudTipoCriterioService.obtenerDatosTipoCriterio(idSolicitud, ConstantesTramitacion.IMPACTO, valoracionSolicitudDTO.getImpacto());
    return valoracionSolicitudDTO;
  }

  private List<String> cargaListadoPACODE() {
    final List<String> paisesPacode = new ArrayList<>();
    paisesPacode.add(ConstantesTramitacion.PAIS_SAHARAUI.toUpperCase());
    paisesPacode.add(ConstantesTramitacion.PAIS_PALESTINA.toUpperCase());
    paisesPacode.add(ConstantesTramitacion.PAIS_HAITI.toUpperCase());
    paisesPacode.add(ConstantesTramitacion.PAIS_MAURITANIA.toUpperCase());
    paisesPacode.add(ConstantesTramitacion.PAIS_BURKINA_FASO.toUpperCase());
    paisesPacode.add(ConstantesTramitacion.PAIS_GUINEA_BISSAU.toUpperCase());
    paisesPacode.add(ConstantesTramitacion.PAIS_MALI.toUpperCase());
    paisesPacode.add(ConstantesTramitacion.PAIS_MALI_SIN_ACENTO.toUpperCase());
    paisesPacode.add(ConstantesTramitacion.PAIS_MOZAMBIQUE.toUpperCase());
    paisesPacode.add(ConstantesTramitacion.PAIS_REPUBLICA_DEMOCRATICA_CONGO.toUpperCase());
    paisesPacode.add(ConstantesTramitacion.PAIS_SENEGAL.toUpperCase());
    paisesPacode.add(ConstantesTramitacion.PAIS_TOGO.toUpperCase());
    return paisesPacode;
  }

  private boolean compruebaPaisPacode(final List<String> paisesPacode, final String poblacionExpediente) {
    final String[] poblacionExpedienteArray = poblacionExpediente.split(",");
    for (final String poblacion : poblacionExpedienteArray) {
      if (paisesPacode.contains(poblacion.trim())) {
        return true;
      }
    }
    return false;
  }

  private Date obtenerFecFinPresentaAlegOrSolicitudes(final Date fechaReferencia, final int numTotalDias) {
    final Calendar fechaInicio = Calendar.getInstance();
    fechaInicio.setTime(fechaReferencia);
    int dias = 1;

    while (dias <= numTotalDias) {
      fechaInicio.add(Calendar.DATE, 1);
      if (fechaInicio.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && fechaInicio.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
        dias++;
      }
    }
    return fechaInicio.getTime();
  }

  /**
   * obtenemos causas de desestimacion
   *
   * @param perfil
   * @param anio
   * @param mapaTipoCausasDesest
   */
  private void obtenerListasCausasDesestimiento(final SolicitudDTO solicitud) throws TramitacionException {
    final List<Integer> causasExclusion = exclusionService.obtenerIdsExclusionesSolicitudByTipo(solicitud.getIdSolicitud(), null, null);
    solicitud.setCausasExc(StringUtils.join(causasExclusion, ConstantesBean.STR_COMA));
  }

  public void setConvocatoriasService(final IConvocatoriasService convocatoriasService) {
    this.convocatoriasService = convocatoriasService;
  }

  public void setSolicitudService(final ISolicitudService solicitudService) {
    this.solicitudService = solicitudService;
  }

  public void setTrewaService(final ITrewaService trewaService) {
    this.trewaService = trewaService;
  }

  public void setSolicitudConcesionService(final ISolicitudConcesionService solicitudConcesionService) {
    this.solicitudConcesionService = solicitudConcesionService;
  }

  public void setSolicitudCriteriosService(final ISolicitudCriteriosService solicitudCriteriosService) {
    this.solicitudCriteriosService = solicitudCriteriosService;
  }

  public void setPaeSolicitudService(final IPaeSolicitudService paeSolicitudService) {
    this.paeSolicitudService = paeSolicitudService;
  }

  public void setPaisSolicitudDao(final IPaisSolicitudDao paisSolicitudDao) {
    this.paisSolicitudDao = paisSolicitudDao;
  }

  public void setSolicitudesAuxiliarService(final ISolicitudesAuxiliarService solicitudesAuxiliarService) {
    this.solicitudesAuxiliarService = solicitudesAuxiliarService;
  }

  public void setSolicitudTipoCriterioService(final ISolicitudTipoCriterioService solicitudTipoCriterioService) {
    this.solicitudTipoCriterioService = solicitudTipoCriterioService;
  }

  public void setPaeContribucionesService(final IPaeContribucionesService paeContribucionesService) {
    this.paeContribucionesService = paeContribucionesService;
  }
}

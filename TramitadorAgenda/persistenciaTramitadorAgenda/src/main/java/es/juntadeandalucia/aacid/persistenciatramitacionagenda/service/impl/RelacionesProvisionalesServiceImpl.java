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
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IConvocatoriasService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IFinalidadService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeContribucionesService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeSolicitudService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IRelacionProvisionalService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudConcesionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudCriteriosService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudTipoCriterioService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudesAuxiliarService;
import es.juntadeandalucia.plataforma.comunes.excepciones.BusinessException;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;
import trewa.bd.trapi.trapiui.tpo.TrInteresadoExpediente;

public class RelacionesProvisionalesServiceImpl implements IRelacionProvisionalService {

  private final Logger log = Logger.getLogger(getClass());

  private IFinalidadService finalidadService;
  private IConvocatoriasService convocatoriasService;
  private ISolicitudService solicitudService;
  private IPaeSolicitudService paeSolicitudService;
  private ISolicitudConcesionService solicitudConcesionService;
  private ISolicitudCriteriosService solicitudCriteriosService;

  private ISolicitudesAuxiliarService solicitudesAuxiliarService;
  private ISolicitudTipoCriterioService solicitudTipoCriterioService;
  private IPaisSolicitudDao paisSolicitudDao;
  private IPaeContribucionesService paeContribucionesService;
  /** Interfaz api de trewa */
  private ITrewaService trewaService;

  @Override
  public Map<String, Object> generarRelacionProvisional(final UsuarioWeb usuarioSesion, final boolean isResolProv, final boolean isUniv, final boolean isConv)
      throws NumberFormatException, BusinessException, TramitacionException {
    final Calendar cal = Calendar.getInstance();
    final int year = cal.get(Calendar.YEAR);
    final Map<String, Object> mapaFinal = new HashMap<>();
    int tipoProyecto = 1;
    while (tipoProyecto <= 4) {
      mapaFinal.putAll(estableceListadosConcesionProvisional(mapaFinal, usuarioSesion, tipoProyecto, isUniv));
      tipoProyecto++;
    }
    if (!isConv) {
      final String finalidad = obtenerFinalidadByTipoInforme(usuarioSesion, isUniv);
      if (finalidad != null) {
        mapaFinal.put(ConstantesTramitacion.FINALIDAD_PROYECTO, finalidad);
      } else {
        mapaFinal.put(ConstantesTramitacion.FINALIDAD_PROYECTO, ConstantesTramitacion.FINALIDAD_NO_DEFINIDA);
      }
    }
    mapaFinal.put(ConstantesTramitacion.FECHA_PUBLICACION_ACCID, UtilidadesFecha.obtenerFechaModoTexto(new Date()));
    mapaFinal.put(ConstantesTramitacion.ANYO_CONVOCATORIA, String.valueOf(year));
    if (isResolProv) {
      mapaFinal.put(ConstantesTramitacion.FECHA_FIN_PRESENTACIION_ALEG, UtilidadesFecha
          .obtenerFechaModoTexto(obtenerFecFinPresentaAlegOrSolicitudes(new Date(), Integer.valueOf(ConstantesTramitacion.PLAZO_FECHA_FIN_PRESENT_ALEG))));
    } else {
      final ConvocatoriaDTO convocatoria = obtenerFechasConvocatoria(usuarioSesion);
      if (convocatoria != null && convocatoria.getFhLimitDocResolDef() != null) {
        mapaFinal.put(ConstantesTramitacion.FECHA_RESOLUCION, UtilidadesFecha.obtenerFechaModoTexto(convocatoria.getFhLimitDocResolDef()));
      } else {
        mapaFinal.put(ConstantesTramitacion.FECHA_RESOLUCION, ConstantesTramitacion.FECHA_RESOLUCION_NO_DEFINIDA);
      }
    }
    return mapaFinal;
  }

  private Map<String, Object> estableceListadosConcesionProvisional(final Map<String, Object> mapaFinal, final UsuarioWeb usuarioSesion, final int tipoProyecto,
      final boolean isUniv) throws TramitacionException, NumberFormatException {

    final List<SolicitudDTO> solicitudesBeneficiarias = new ArrayList<>();
    final List<SolicitudDTO> solicitudesBeneficiariasPACODE = new ArrayList<>();
    final List<SolicitudDTO> solicitudesSuplentes = new ArrayList<>();

    try {
      final ConvocatoriaDTO convocatoriaDTO = convocatoriasService.buscarConvocatoriaDTOPorIdExpediente(usuarioSesion.getExpediente().getRefExpediente());
      final List<String> paisesPacode = cargaListadoPACODE();
      final List<SolicitudDTO> solicitudes = solicitudService.obtenerSolicitudesByTipoProyectoDocumento(convocatoriaDTO.getIdConv(), tipoProyecto, isUniv);

      if (solicitudes == null) {
        log.warn("La convocatoria " + convocatoriaDTO.getIdConv() + " no tiene solicitudes para el tipo proyecto: " + tipoProyecto);
        return mapaFinal;
      }

      for (final SolicitudDTO solicitud : solicitudes) {

        final TrExpediente expediente = trewaService.obtenerExpedienteTrewa(String.valueOf(solicitud.getNumExpediente()));
        if (expediente == null) {
          throw new TramitacionException("La solicitud " + solicitud.getTitulo() + " no tiene expediente trewa asignado");
        }

        if (trewaService.compruebaFaseCorrectaExpediente(expediente, ConstantesTramitacion.FASE_RELACION_PROVISIONAL_CONCESION)) {
          final SolicitudConcesion solicitudConcesion = solicitudConcesionService.obtenerSolicitudConcesionPorSolicitud(solicitud.getIdSolicitud());

          anyadeDatosTrewaSolicitudDTO(solicitud, expediente);
          anyadeDatosSolicitudDTO(solicitud, isUniv, solicitudConcesion);
          estableceListaBeneficiariosOSuplentes(solicitud, solicitudConcesion, solicitudesBeneficiarias, solicitudesBeneficiariasPACODE, solicitudesSuplentes,
              paisesPacode, isUniv);

        } else {
          log.error("El expediente " + expediente.getREFEXP().toString() + " no se encuentra en la fase correcta");
        }
      }

      anyadeSolicitudesATabla(solicitudesBeneficiarias, solicitudesBeneficiariasPACODE, solicitudesSuplentes, tipoProyecto, mapaFinal);
    } catch (final NumberFormatException ex) {
      log.error("Error al convertir numero en cadena o viceversa. Causa: " + ex.getMessage(), ex);
    } catch (final TramitacionException e) {
      log.error(e.getMessage(), e);
    }
    return mapaFinal;
  }

  private void anyadeSolicitudesATabla(final List<SolicitudDTO> solicitudesBeneficiarias, final List<SolicitudDTO> solicitudesBeneficiariasPACODE,
      final List<SolicitudDTO> solicitudesSuplentes, final int tipoProyecto, final Map<String, Object> mapaFinal) {
    if (CollectionUtils.isNotEmpty(solicitudesBeneficiarias)) {
      UtilidadesListas.ordenamientoBurbujaListaSolicitudesByPuntuacion(solicitudesBeneficiarias);
      mapaFinal.put(listaTipoProyecto(tipoProyecto, true, false), solicitudesBeneficiarias);
    }
    if (CollectionUtils.isNotEmpty(solicitudesBeneficiariasPACODE)) {
      UtilidadesListas.ordenamientoBurbujaListaSolicitudesByPuntuacion(solicitudesBeneficiariasPACODE);
      mapaFinal.put(listaTipoProyecto(tipoProyecto, true, true), solicitudesBeneficiariasPACODE);
    }
    if (CollectionUtils.isNotEmpty(solicitudesSuplentes)) {
      UtilidadesListas.ordenamientoBurbujaListaSolicitudesByPuntuacion(solicitudesSuplentes);
      mapaFinal.put(listaTipoProyecto(tipoProyecto, false, false), solicitudesSuplentes);
    }
  }

  private void anyadeDatosSolicitudDTO(final SolicitudDTO solicitud, final boolean isUniv, final SolicitudConcesion solicitudConcesion)
      throws TramitacionException {
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

    paeContribucionesService.estableceValorEconomicoConcesion(solicitud, isUniv, solicitudConcesion, false);

    solicitud.setTitulo(solicitud.getTitulo() != null ? solicitud.getTitulo().toUpperCase() : "");
    solicitud.setPuntuacion(UtilidadesNumero.bigDecimalToStringConverter(puntuacion));
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

  private void estableceListaBeneficiariosOSuplentes(final SolicitudDTO solicitud, final SolicitudConcesion solicitudConcesion,
      final List<SolicitudDTO> solicitudesBeneficiarias, final List<SolicitudDTO> solicitudesBeneficiariasPaisesPacode,
      final List<SolicitudDTO> solicitudesSuplentes, final List<String> paisesPacode, final boolean isUniv) {

    if (ConstantesTramitacion.BENEFICIARIA.equals(solicitudConcesion.getSocoTxTipoConcesion())) {
      if (compruebaPaisPacode(paisesPacode, solicitud.getPoblacion())) {
        if (!isUniv) {
          solicitudesBeneficiariasPaisesPacode.add(solicitud);
        } else {
          solicitudesBeneficiarias.add(solicitud);
        }
      } else {
        solicitudesBeneficiarias.add(solicitud);
      }
    }
    if (ConstantesTramitacion.SUPLENTE.equals(solicitudConcesion.getSocoTxTipoConcesion())) {
      solicitudesSuplentes.add(solicitud);
    }
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
      throw new TramitacionException("Error al obtener los datos para la lista de exclusi\u00F3n provisional", e);
    }
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

  @Override
  public Map<String, Object> generarPropuestaBeneficiario(final UsuarioWeb usuarioSesion, final boolean isResolProv, final boolean isUniv, final boolean isConv)
      throws TramitacionException {
    final Calendar cal = Calendar.getInstance();
    final int year = cal.get(Calendar.YEAR);
    final Map<String, Object> datos = new HashMap<>();
    datos.put(ConstantesTramitacion.ANYO_CONVOCATORIA, year);
    return datos;
  }

  private String listaTipoProyecto(final int cont, final boolean beneficiaria, final boolean isPade) {
    String listaTipoProyecto = "";
    switch (cont) {
    case 1:
      if (beneficiaria) {
        listaTipoProyecto = !isPade ? ConstantesTramitacion.EXP_PROY_COOP_INT_BENEF : ConstantesTramitacion.EXP_PROY_COOP_INT_BENEF_SAPA;
      } else {
        listaTipoProyecto = ConstantesTramitacion.EXP_PROY_COOP_INT_SUPLEN;
      }
      break;
    case 2:
      if (beneficiaria) {
        listaTipoProyecto = !isPade ? ConstantesTramitacion.EXP_PROY_ACC_HUM_BENEF : ConstantesTramitacion.EXP_PROY_ACC_HUM_BENEF_SAPA;
      } else {
        listaTipoProyecto = ConstantesTramitacion.EXP_PROY_ACC_HUM_SUPLEN;
      }
      break;
    case 3:
      listaTipoProyecto = beneficiaria ? ConstantesTramitacion.EXP_PROY_DESARROLLO_BENEF : ConstantesTramitacion.EXP_PROY_DESARROLLO_SUPLEN;
      break;
    case 4:
      listaTipoProyecto = beneficiaria ? ConstantesTramitacion.EXP_PROY_FOR_INNOV_BENEF : ConstantesTramitacion.EXP_PROY_FOR_INNOV_SUPLEN;
      break;
    default:
      break;
    }
    return listaTipoProyecto;
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
      if (paisesPacode.contains(poblacion.toUpperCase())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Establece el valor de la propiedad convocatoriasService
   *
   * @param convocatoriasService
   *          el convocatoriasService para establecer
   */
  public void setConvocatoriasService(final IConvocatoriasService convocatoriasService) {
    this.convocatoriasService = convocatoriasService;
  }

  /**
   * Establece el valor de la propiedad solicitudService
   *
   * @param solicitudService
   *          el solicitudService para establecer
   */
  public void setSolicitudService(final ISolicitudService solicitudService) {
    this.solicitudService = solicitudService;
  }

  /**
   * Establece el valor de la propiedad paeSolicitudService
   *
   * @param paeSolicitudService
   *          el paeSolicitudService para establecer
   */
  public void setPaeSolicitudService(final IPaeSolicitudService paeSolicitudService) {
    this.paeSolicitudService = paeSolicitudService;
  }

  /**
   * Establece el valor de la propiedad solicitudConcesionService
   *
   * @param solicitudConcesionService
   *          el solicitudConcesionService para establecer
   */
  public void setSolicitudConcesionService(final ISolicitudConcesionService solicitudConcesionService) {
    this.solicitudConcesionService = solicitudConcesionService;
  }

  /**
   * Establece el valor de la propiedad solicitudCriteriosService
   *
   * @param solicitudCriteriosService
   *          el solicitudCriteriosService para establecer
   */
  public void setSolicitudCriteriosService(final ISolicitudCriteriosService solicitudCriteriosService) {
    this.solicitudCriteriosService = solicitudCriteriosService;
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
   * Establece el valor de la propiedad paisSolicitudDao
   *
   * @param paisSolicitudDao
   *          el paisSolicitudDao para establecer
   */
  public void setPaisSolicitudDao(final IPaisSolicitudDao paisSolicitudDao) {
    this.paisSolicitudDao = paisSolicitudDao;
  }

  /**
   * Establece el valor de la propiedad paeContribucionesService
   *
   * @param paeContribucionesService
   *          el paeContribucionesService para establecer
   */
  public void setPaeContribucionesService(final IPaeContribucionesService paeContribucionesService) {
    this.paeContribucionesService = paeContribucionesService;
  }

  /**
   * Establece el valor de la propiedad trewaService
   *
   * @param trewaService
   *          el trewaService para establecer
   */
  public void setTrewaService(final ITrewaService trewaService) {
    this.trewaService = trewaService;
  }
}
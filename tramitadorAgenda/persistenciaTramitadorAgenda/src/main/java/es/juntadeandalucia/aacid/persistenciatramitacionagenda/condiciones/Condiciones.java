package es.juntadeandalucia.aacid.persistenciatramitacionagenda.condiciones;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ConvocatoriaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IConvocatoriasService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IExclusionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISubsanacionService;
import trewa.bd.tpo.TpoPK;
import trewa.bd.trapi.trapiui.tpo.TrDocumentoExpediente;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;
import trewa.bd.trapi.trapiui.tpo.TrFase;
import trewa.bd.trapi.trapiui.tpo.TrFaseExpediente;
import trewa.bd.trapi.trapiui.tpo.TrTareaExpediente;
import trewa.ext.TrAccesoUI;

public class Condiciones extends TrAccesoUI {

  private static final String ERROR_AL_COMPROBAR_LA_FASE_PADRE = "Error al comprobar la fase padre: ";

  private static final Log LOG = LogFactory.getLog(Condiciones.class);

  private static final String MENSAJE_ERROR_RECUPERAR_DOC_EXPEDIENTE = "Se ha producido un error al recuperar el documento del expediente.";
  private static final String MENSAJE_ERROR_RECUPERAR_DATOS_SOLICITUD_SUBSANACION = "Error al obtener los datos de la solicitud/subsanacion.";
  private static final String MENSAJE_ERROR_RECUPERAR_DATOS_SOLICITUD_EXCLUSION = "Error al obtener los datos de la solicitud/exclusion.";

  /** solicitudService */
  private static ISolicitudService solicitudService;
  /** exclusionService */
  private static IExclusionService exclusionService;
  /** trewaService */
  private static ITrewaService trewaService;
  /** subsanacionService */
  private static ISubsanacionService subsanacionService;
  /** convocatoriasService */
  private static IConvocatoriasService convocatoriasService;
  static {

    final WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
    solicitudService = (ISolicitudService) context.getBean("solicitudService");
    exclusionService = (IExclusionService) context.getBean("exclusionService");
    trewaService = (ITrewaService) context.getBean("trewaService");
    subsanacionService = (ISubsanacionService) context.getBean("subsanacionService");
    convocatoriasService = (IConvocatoriasService) context.getBean("convocatoriasService");
  }

  public Integer comprobarExclusiones(final BigDecimal idExpFase) throws TramitacionException {

    try {
      final TrFaseExpediente faseExpediente = trewaService.obtenerFaseExpediente(idExpFase.toString());
      final SolicitudDTO solicitud = solicitudService.obtenerSolicitudByIdExp(faseExpediente.getREFEXPEDIENTE().toString());
      final TrFase fase = faseExpediente.getFASE();
      final String descFase = fase.getDESCRIPCION();
      final boolean tieneExclusiones;
      final String strExcepcionesExcl = exclusionService.obtenerExcepcionesExclusiones(ConstantesTramitacion.EXCEPCIONES_EXCLUSIONES_TIPO_2,
          ConstantesTramitacion.FASE_FIN_EXCLUSIONES_TIPO_2, faseExpediente);

      tieneExclusiones = exclusionService.existenExclusionesSolicitudByTipo(faseExpediente.getREFEXPEDIENTE().toString(),
          ConstantesTramitacion.obtenerMapaTiposExclusionesByFases().get(descFase), strExcepcionesExcl);

      if (solicitud != null && solicitud.getIdSolicitud() != null && tieneExclusiones) {
        return 1;
      }

    } catch (final TramitacionException e) {
      LOG.error(MENSAJE_ERROR_RECUPERAR_DATOS_SOLICITUD_EXCLUSION, e);
      return 0;
    }
    return 0;
  }

  public Integer comprobarTieneExclusionTipo1NoExclusionTipo2(final BigDecimal idExpFase) {
    try {
      final TrFaseExpediente faseExpediente = trewaService.obtenerFaseExpediente(idExpFase.toString());
      final String strExcepcionesExcl = exclusionService.obtenerExcepcionesExclusiones(ConstantesTramitacion.EXCEPCIONES_EXCLUSIONES_TIPO_2,
          ConstantesTramitacion.FASE_FIN_EXCLUSIONES_TIPO_2, faseExpediente);
      final boolean tieneExclusionesTipo1 = exclusionService.existenExclusionesSolicitudByTipo(faseExpediente.getREFEXPEDIENTE().toString(), 1,
          StringUtils.EMPTY);
      final boolean tieneExclusionesTipo2 = exclusionService.existenExclusionesSolicitudByTipo(faseExpediente.getREFEXPEDIENTE().toString(), 2,
          strExcepcionesExcl);

      if (tieneExclusionesTipo1 && !tieneExclusionesTipo2) {
        return 1;
      }
    } catch (final TramitacionException e) {
      LOG.error(MENSAJE_ERROR_RECUPERAR_DATOS_SOLICITUD_EXCLUSION, e);
      return 0;
    }
    return 0;
  }

  public Integer comprobarNoTieneExclusionTipo1NiTipo2(final BigDecimal idExpFase) {
    try {
      final TrFaseExpediente faseExpediente = trewaService.obtenerFaseExpediente(idExpFase.toString());
      final String strExcepcionesExcl = exclusionService.obtenerExcepcionesExclusiones(ConstantesTramitacion.EXCEPCIONES_EXCLUSIONES_TIPO_2,
          ConstantesTramitacion.FASE_FIN_EXCLUSIONES_TIPO_2, faseExpediente);
      final boolean tieneExclusionesTipo1 = exclusionService.existenExclusionesSolicitudByTipo(faseExpediente.getREFEXPEDIENTE().toString(), 1,
          StringUtils.EMPTY);
      final boolean tieneExclusionesTipo2 = exclusionService.existenExclusionesSolicitudByTipo(faseExpediente.getREFEXPEDIENTE().toString(), 2,
          strExcepcionesExcl);

      if (!tieneExclusionesTipo1 && !tieneExclusionesTipo2) {
        return 1;
      }
    } catch (final TramitacionException e) {
      LOG.error(MENSAJE_ERROR_RECUPERAR_DATOS_SOLICITUD_EXCLUSION, e);
      return 0;
    }
    return 0;
  }

  public Integer comprobarTieneExclusionTipo1OTipo2(final BigDecimal idExpFase) {
    if (comprobarNoTieneExclusionTipo1NiTipo2(idExpFase).equals(0)) {
      return 1;
    }
    return 0;
  }

  public Integer comprobarNoHaPasadoSubsanaciones(final BigDecimal idExp) {
    try {
      final TrFaseExpediente[] fasesExpediente = trewaService.obtenerFasesExpediente(new TpoPK(idExp));
      for (final TrFaseExpediente fase : fasesExpediente) {
        if (fase.getFASE().getDESCRIPCION().equals(ConstantesTramitacion.FASE_REQUERIMIENTO_SUBSANACION)) {
          return 0;
        }
      }
    } catch (final Exception e) {
      LOG.error(MENSAJE_ERROR_RECUPERAR_DATOS_SOLICITUD_SUBSANACION, e);
      return 0;
    }
    return 1;
  }

  public Integer comprobarSubsanaciones(final BigDecimal idExpFase) {
    try {

      final TrFaseExpediente faseExpediente = trewaService.obtenerFaseExpediente(idExpFase.toString());
      final SolicitudDTO solicitud = solicitudService.obtenerSolicitudByIdExp(faseExpediente.getREFEXPEDIENTE().toString());
      if (solicitud != null && solicitud.getIdSolicitud() != null && subsanacionService.existeCatalogoSubsanacion(solicitud.getIdSolicitud())) {
        return 1;
      }
    } catch (final Exception e) {
      LOG.error(MENSAJE_ERROR_RECUPERAR_DATOS_SOLICITUD_SUBSANACION, e);
      return 0;
    }
    return 0;
  }

  /**
   * Condicion que devolvera true si el expediente dato no tiene subsanaciones y exclusiones relacionadas y, ademas, las tareas de exclusion
   * y subsanacion estan finalizadas
   */
  public Integer compruebaExclSubsSolicitud(final BigDecimal idExpFase) throws TramitacionException {
    try {
      if (comprobarNoTieneExclusionesSolicitud(idExpFase) == 1 && comprobarNoTieneSubsanacionesSolicitud(idExpFase) == 1) {
        return 1;
      }
    } catch (final Exception e) {
      LOG.error(MENSAJE_ERROR_RECUPERAR_DATOS_SOLICITUD_SUBSANACION, e);
      return 0;
    }
    return 0;
  }

  /**
   * Condicion que devolvera true si el expediente dado ha finalizado la tarea de subsanacion y no tiene subsanaciones relacionadas
   */
  public Integer comprobarNoTieneSubsanacionesSolicitud(final BigDecimal idExpFase) {

    if (comprobarSubsanaciones(idExpFase) == 1) {
      return 0;
    } else {
      return 1;
    }
  }

  /**
   * Condicion que devolvera true si el expediente dado ha finalizado la tarea de exclusiones y no tiene exclusiones relacionadas
   */
  public Integer comprobarNoTieneExclusionesSolicitud(final BigDecimal idExpFase) throws TramitacionException {
    if (comprobarExclusiones(idExpFase) == 1) {
      return 0;
    } else {
      return 1;
    }
  }

  /*
   * Comprobamos si se ha notificado el documento para permitir pasar al a siguiente fase
   */
  public Integer comprobarNotificacionRequerimientoSubsana(final BigDecimal idExp) {
    String etiquetaDocGenSubVal;
    final String abreviatura = trewaService.getAbreviaturaProcedimiento(idExp.toString());

    if (abreviatura.equals(ConstantesTramitacion.ABREVIATURA_ONGD)) {
      etiquetaDocGenSubVal = ConstantesTramitacion.ETIQUETA_DOC_GEN_SUB_VAL_ONGD;
    } else {
      etiquetaDocGenSubVal = ConstantesTramitacion.ETIQUETA_DOC_GEN_SUB_VAL_UNIV;
    }

    TrDocumentoExpediente[] documentosExp;
    try {
      documentosExp = trewaService.documentosDelExpediente(idExp.longValue(), etiquetaDocGenSubVal);
      if (documentosExp != null) {
        for (final TrDocumentoExpediente element : documentosExp) {
          if (element.getFECHASACUSENOTI() != null) {
            return 1;
          }
        }
      }
    } catch (final TramitacionException e) {
      LOG.error(MENSAJE_ERROR_RECUPERAR_DOC_EXPEDIENTE, e);
      return 0;
    }
    return 0;
  }

  /*
   * Comprobamos si se ha notificado el documento para permitir pasar al a siguiente fase
   */
  public Integer comprobarNotificacionRequerimientoExclu(final BigDecimal idExp) {
    String etiquetaDocGenExcl;
    final String abreviatura = trewaService.getAbreviaturaProcedimiento(idExp.toString());

    if (abreviatura.equals(ConstantesTramitacion.ABREVIATURA_ONGD)) {
      etiquetaDocGenExcl = ConstantesTramitacion.ETIQUETA_DOC_GEN_EXCL_ONGD;
    } else {
      etiquetaDocGenExcl = ConstantesTramitacion.ETIQUETA_DOC_GEN_EXCL_UNIV;
    }
    TrDocumentoExpediente[] documentosExp;
    try {
      documentosExp = trewaService.documentosDelExpediente(idExp.longValue(), etiquetaDocGenExcl);
      if (documentosExp != null) {
        for (final TrDocumentoExpediente element : documentosExp) {
          if (element.getFECHASACUSENOTI() != null) {
            return 1;
          }
        }
      }
    } catch (final TramitacionException e) {
      LOG.error(MENSAJE_ERROR_RECUPERAR_DOC_EXPEDIENTE, e);
      return 0;
    }
    return 0;
  }

  public Integer comprobarNotificacionResolucionExclusionSubsana(final BigDecimal idExp) {
    String etiquetaDocGenSubVal;
    final String abreviatura = trewaService.getAbreviaturaProcedimiento(idExp.toString());

    if (abreviatura.equals(ConstantesTramitacion.ABREVIATURA_ONGD)) {
      etiquetaDocGenSubVal = ConstantesTramitacion.ETIQUETA_DOC_GEN_EXCL_ONGD;
    } else {
      etiquetaDocGenSubVal = ConstantesTramitacion.ETIQUETA_DOC_GEN_EXCL_UNIV;
    }
    TrDocumentoExpediente[] documentosExp;
    try {
      documentosExp = trewaService.documentosDelExpediente(idExp.longValue(), etiquetaDocGenSubVal);
      if (documentosExp != null) {
        for (final TrDocumentoExpediente element : documentosExp) {
          if (element.getFECHASACUSENOTI() != null) {
            return 1;
          }
        }
      }
    } catch (final TramitacionException e) {
      LOG.error(MENSAJE_ERROR_RECUPERAR_DOC_EXPEDIENTE, e);
      return 0;
    }
    return 0;
  }

  /*
   * Comprobamos si se ha notificado el documento para permitir pasar al a siguiente fase (POR TERMINAR)
   */
  public Integer comprobarNotificacionRequerimientoSubsanaAleg(final BigDecimal idExp) {
    String etiquetaDocGenSubVal;
    final String abreviatura = trewaService.getAbreviaturaProcedimiento(idExp.toString());

    if (abreviatura.equals(ConstantesTramitacion.ABREVIATURA_ONGD)) {
      etiquetaDocGenSubVal = ConstantesTramitacion.ETIQUETA_DOC_SUB_SOLI_ALEG_ONGD;
    } else {
      etiquetaDocGenSubVal = ConstantesTramitacion.ETIQUETA_DOC_SUB_SOLI_ALEG_UNIV;
    }

    TrDocumentoExpediente[] documentosExp;
    try {
      documentosExp = trewaService.documentosDelExpediente(idExp.longValue(), etiquetaDocGenSubVal);
      if (documentosExp != null) {
        for (final TrDocumentoExpediente element : documentosExp) {
          if (element.getFECHASACUSENOTI() != null) {
            return 1;
          }
        }
      }
    } catch (final TramitacionException e) {
      LOG.error(MENSAJE_ERROR_RECUPERAR_DOC_EXPEDIENTE, e);
      return 0;
    }
    return 1;
  }

  private boolean tareaEstaFinalizada(final TrTareaExpediente[] tareas) {
    for (final TrTareaExpediente element : tareas) {
      if (ConstantesTramitacion.FINALIZADA.equals(element.getESTADO())) {
        return true;
      }
    }
    return false;
  }

  private String obtenerTareaSegunAbreviatura(final String abreviatura, final String tipoEntidad, final String fase) {
    String nombreTarea = "";
    if (abreviatura.equals(ConstantesTramitacion.ABREVIATURA_ONGD)) {
      if (tipoEntidad.equals(ConstantesTramitacion.EXCLUSION)) {
        nombreTarea = nombreTareaExclusionONGD(fase, nombreTarea);
      } else if (tipoEntidad.equals(ConstantesTramitacion.SUBSANACION)) {
        nombreTarea = descripcionTareaSubsanacionONGD(fase, nombreTarea);
      } else if (tipoEntidad.equals(ConstantesTramitacion.DESESTIMACION)) {
        nombreTarea = descripcionTareaDesestimacionesONGD(fase, nombreTarea);
      }
    } else if (abreviatura.equals(ConstantesTramitacion.ABREVIATURA_UNIV)) {
      if (tipoEntidad.equals(ConstantesTramitacion.EXCLUSION)) {
        nombreTarea = descripcionTareaExclusionUNIV(fase, nombreTarea);
      } else if (tipoEntidad.equals(ConstantesTramitacion.SUBSANACION)) {
        nombreTarea = descripcionTareaSubsanacionUNIV(fase, nombreTarea);
      } else if (tipoEntidad.equals(ConstantesTramitacion.DESESTIMACION)) {
        nombreTarea = descripcionTareaDesestimacionesUNIV(fase, nombreTarea);
      }
    }
    return nombreTarea;
  }

  /**
   * Obtiene la descripción de la tarea de subsanación para Universidades
   *
   * @param fase
   * @param descripcionTarea
   * @return
   */
  private String descripcionTareaSubsanacionUNIV(final String fase, String descripcionTarea) {
    if (fase.equalsIgnoreCase(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS)) {
      descripcionTarea = ConstantesTramitacion.TAREA_GEN_SUB_VAL_UNIV;
    } else if (fase.equalsIgnoreCase(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS_ALEGACION)) {
      descripcionTarea = ConstantesTramitacion.TAREA_GEN_SUB_VAL_UNIV_ALEG;
    }
    return descripcionTarea;
  }

  /**
   * Obtiene la descripción de la tarea de desestimacion para ONGD
   *
   * @param fase
   * @param descripcionTarea
   * @return
   */
  private String descripcionTareaDesestimacionesONGD(final String fase, String descripcionTarea) {
    if (fase.equalsIgnoreCase(ConstantesTramitacion.FASE_VALORACION_SUBSANACION)) {
      descripcionTarea = ConstantesTramitacion.TAREA_CAUSAS_DESEST;
    }
    return descripcionTarea;
  }

  /**
   * Obtiene la descripción de la tarea de desestimacion para UNIV
   *
   * @param fase
   * @param descripcionTarea
   * @return
   */
  private String descripcionTareaDesestimacionesUNIV(final String fase, String descripcionTarea) {
    if (fase.equalsIgnoreCase(ConstantesTramitacion.FASE_VALORACION_SUBSANACION)) {
      descripcionTarea = ConstantesTramitacion.TAREA_CAUSAS_DESEST_UNIV;
    }
    return descripcionTarea;
  }

  /**
   * Obtiene la descripción de la tarea de exclusión para Universidades
   *
   * @param fase
   * @param descripcionTarea
   * @return
   */
  private String descripcionTareaExclusionUNIV(final String fase, String descripcionTarea) {
    if (fase.equalsIgnoreCase(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS)) {
      descripcionTarea = ConstantesTramitacion.TAREA_GEN_EXCL_VAL_UNIV;
    } else if (fase.equalsIgnoreCase(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS_SUBSANACION)) {
      descripcionTarea = ConstantesTramitacion.TAREA_EXCL_POS_SU_UNIV;
    } else if (fase.equalsIgnoreCase(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS_ALEGACION)) {
      descripcionTarea = ConstantesTramitacion.TAREA_EXCL_TRAM_ALEG_UNIV;
    } else if (fase.equalsIgnoreCase(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS_SUBSANACION_VUELTA)) {
      descripcionTarea = ConstantesTramitacion.TAREA_EXCL_POS_SU_UNIV2;
    }
    return descripcionTarea;
  }

  /**
   * Obtiene la descripción de la tarea de subsanación para ONGD
   *
   * @param fase
   * @param descripcionTarea
   * @return
   */
  private String descripcionTareaSubsanacionONGD(final String fase, String descripcionTarea) {
    if (fase.equalsIgnoreCase(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS)) {
      descripcionTarea = ConstantesTramitacion.TAREA_GEN_SUB_VAL_ONGD;
    } else if (fase.equalsIgnoreCase(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS_ALEGACION)) {
      descripcionTarea = ConstantesTramitacion.TAREA_GEN_SUB_VAL_ONGD_ALEG;
    }
    return descripcionTarea;
  }

  /**
   * Obtiene nombre de la tarea de exclusión para ONGD
   *
   * @param fase
   * @param descripcionTarea
   * @return
   */
  private String nombreTareaExclusionONGD(final String fase, String descripcionTarea) {
    if (fase.equalsIgnoreCase(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS)) {
      descripcionTarea = ConstantesTramitacion.TAREA_GEN_EXCL_VAL_ONGD;
    } else if (fase.equalsIgnoreCase(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS_SUBSANACION)) {
      descripcionTarea = ConstantesTramitacion.TAREA_EXCL_POS_SU;
    } else if (fase.equalsIgnoreCase(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS_ALEGACION)) {
      descripcionTarea = ConstantesTramitacion.TAREA_EXCL_TRAM_ALEG;
    } else if (fase.equalsIgnoreCase(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS_SUBSANACION_VUELTA)) {
      descripcionTarea = ConstantesTramitacion.TAREA_EXCL_POS_SU2;
    }
    return descripcionTarea;
  }

  public Integer comprobarValoracionSolicitud(final BigDecimal idExp, final BigDecimal idExpFase) throws TramitacionException {
    final Integer compSub = comprobarSubsanaciones(idExpFase);
    final Integer comExc = comprobarExclusiones(idExpFase);
    if (compSub == 1 && comExc == 1) {
      return 1;
    } else {
      return 0;
    }
  }

  public Integer comprobarFasePadreSubsanacionesVuelta(final BigDecimal idExpFase) {
    try {
      final TrFaseExpediente faseExp = trewaService.obtenerFaseExpediente(idExpFase.toString());
      final TrExpediente exp = trewaService.obtenerExpedienteTrewa(faseExp.getREFEXPEDIENTE().toString());
      final String faseConvocatoria = trewaService.obtenerFaseExpedientePadre(exp.getREFEXP());

      final Map<String, String> mapaRel = new HashMap<>();
      mapaRel.put(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS_ALEGACION, ConstantesTramitacion.FASE_CONV_REQUERIMIENTO_SUBSANACIONES_VUELTA);
      if (mapaRel.get(faseExp.getFASE().getDESCRIPCION()).equalsIgnoreCase(faseConvocatoria)) {
        return 1;
      }
    } catch (final TramitacionException e) {
      LOG.error(ERROR_AL_COMPROBAR_LA_FASE_PADRE, e);
    }
    return 0;

  }

  public Integer comprobarFasePadre(final BigDecimal idExpFase) {
    try {
      final TrFaseExpediente faseExp = trewaService.obtenerFaseExpediente(idExpFase.toString());
      final TrExpediente exp = trewaService.obtenerExpedienteTrewa(faseExp.getREFEXPEDIENTE().toString());
      final String faseConvocatoria = trewaService.obtenerFaseExpedientePadre(exp.getREFEXP());

      final Map<String, String> mapaRel = inicializarMapaRelacionesPadreHijo(exp.getDEFPROC().getABREVIATURA());
      if (mapaRel.get(faseExp.getFASE().getDESCRIPCION()).equalsIgnoreCase(faseConvocatoria)) {
        return 1;
      }
    } catch (final TramitacionException e) {
      LOG.error(ERROR_AL_COMPROBAR_LA_FASE_PADRE, e);
    }
    return 0;
  }

  private Map<String, String> inicializarMapaRelacionesPadreHijo(final String defProc) {
    final Map<String, String> mapaRel = new HashMap<>();

    if (ConstantesTramitacion.ABREVIATURA_ONGD.equals(defProc)) {
      mapaRel.put(ConstantesTramitacion.FASE_ONGD_RECEPCION_SOLICITUD, ConstantesTramitacion.FASE_CONV_COMPROBACION_REQUISITOS);
      mapaRel.put(ConstantesTramitacion.FASE_RECEPCION_SUBSANACION, ConstantesTramitacion.FASE_CONV_COMPROBACION_SUBSANACION_ONGD);

    } else if (ConstantesTramitacion.ABREVIATURA_UNIV.equals(defProc)) {
      mapaRel.put(ConstantesTramitacion.FASE_UNIV_RECEPCION_SOLICITUD, ConstantesTramitacion.FASE_CONV_COMPROBACION_REQUISITOS);
      mapaRel.put(ConstantesTramitacion.FASE_RECEPCION_SUBSANACION, ConstantesTramitacion.FASE_CONV_COMPROBACION_SUBSANACION);

    }
    mapaRel.put(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS, ConstantesTramitacion.FASE_CONV_VALORACION_SOLICITUDES);
    mapaRel.put(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS_SUBSANACION, ConstantesTramitacion.FASE_CONV_COMPROBACION_SUBSANACION);
    mapaRel.put(ConstantesTramitacion.FASE_RESOLUCION_PROVISIONAL, ConstantesTramitacion.FASE_CONV_PRESENTACION_ALEGACIONES);
    mapaRel.put(ConstantesTramitacion.FASE_REQUERIMIENTO_SUBSANACION, ConstantesTramitacion.FASE_CONV_PRESENTACION_SUBSANACION);
    mapaRel.put(ConstantesTramitacion.FASE_RECEPCION_ALEGACION, ConstantesTramitacion.FASE_CONV_COMPROBACION_ALEGACIONES);
    mapaRel.put(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS_ALEGACION, ConstantesTramitacion.FASE_CONV_RESOLUCION_DEFINITVA_EXCLUSION);
    mapaRel.put(ConstantesTramitacion.FASE_REQUERIMIENTO_SUBSANACION_VUELTA, ConstantesTramitacion.FASE_CONV_PRESENTACION_SUBSANACIONES_VUELTA);
    mapaRel.put(ConstantesTramitacion.FASE_RECEPCION_SUBSANACION_VUELTA, ConstantesTramitacion.FASE_CONV_COMPROBACION_SUBSANACIONES_VUELTA);
    mapaRel.put(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS_SUBSANACION_VUELTA, ConstantesTramitacion.FASE_CONV_RESOLUCION_DESESTIMACION_REQUISITOS);
    return mapaRel;

  }

  public Integer comprobarTieneConvocatoria(final BigDecimal idExp) {
    try {
      final ConvocatoriaDTO convocatoriaDTO = convocatoriasService.buscarConvocatoriaDTOPorIdExpediente(idExp.toPlainString());
      if (convocatoriaDTO != null) {
        return (convocatoriaDTO.getFhInicio() != null && convocatoriaDTO.getFhFin() != null) ? 1 : 0;
      }
    } catch (final TramitacionException e) {
      LOG.error(ERROR_AL_COMPROBAR_LA_FASE_PADRE, e);
    }
    return 0;
  }

  public Integer comprobarValoracionSolicitudTrasAlegacion(final BigDecimal idExpFase) {
    Integer vuelta = 0;
    try {
      final TrFaseExpediente faseExpediente = trewaService.obtenerFaseExpediente(idExpFase.toString());

      if (comprobarExclusionNoTipo1NoTipo2(idExpFase)) {
        vuelta = 1;
      }
      if (comprobarNoHaPasadoSubsanaciones(faseExpediente.getREFEXPEDIENTE().getPkVal()).equals(1)) {
        if (compruebaTareaSubsanacionesFinalizada(idExpFase).equals(1) && comprobarSubsanaciones(idExpFase).equals(0)) {
          vuelta = 1;
        } else {
          vuelta = 0;
        }
      }
    } catch (final TramitacionException e) {
      vuelta = 0;
    }
    return vuelta;
  }

  private boolean comprobarExclusionNoTipo1NoTipo2(final BigDecimal idExpFase) {
    try {
      final TrFaseExpediente faseExpediente = trewaService.obtenerFaseExpediente(idExpFase.toString());
      final String strExcepcionesExcl = exclusionService.obtenerExcepcionesExclusiones(ConstantesTramitacion.EXCEPCIONES_EXCLUSIONES_TIPO_2,
          ConstantesTramitacion.FASE_FIN_EXCLUSIONES_TIPO_2, faseExpediente);
      if (!exclusionService.existenExclusionesSolicitudByTipo(faseExpediente.getREFEXPEDIENTE().toString(), 1, StringUtils.EMPTY)
          && !exclusionService.existenExclusionesSolicitudByTipo(faseExpediente.getREFEXPEDIENTE().toString(), 2, strExcepcionesExcl)) {
        return true;
      }

    } catch (final TramitacionException e) {
      LOG.error(MENSAJE_ERROR_RECUPERAR_DATOS_SOLICITUD_EXCLUSION, e);
      return false;
    }
    return false;
  }

  public Integer compruebaTareaExclusionesFinalizada(final BigDecimal idExpFase) {
    try {
      final TrFaseExpediente faseExpediente = trewaService.obtenerFaseExpediente(idExpFase.toString());
      if (obtenerFinalizadaTarea(ConstantesTramitacion.EXCLUSION, faseExpediente)) {
        return 1;
      } else {
        return 0;
      }
    } catch (final Exception e) {
      LOG.error(MENSAJE_ERROR_RECUPERAR_DATOS_SOLICITUD_EXCLUSION, e);
      return 0;
    }
  }

  public Integer compruebaTareaSubsanacionesFinalizada(final BigDecimal idExpFase) {

    try {
      final TrFaseExpediente faseExpediente = trewaService.obtenerFaseExpediente(idExpFase.toString());

      final boolean resultado = obtenerFinalizadaTarea(ConstantesTramitacion.SUBSANACION, faseExpediente);
      if (resultado) {
        return 1;
      } else {
        return 0;
      }
    } catch (final Exception e) {
      LOG.error(MENSAJE_ERROR_RECUPERAR_DATOS_SOLICITUD_EXCLUSION, e);
      return 0;
    }
  }

  public Integer comprobarTieneExclusionTipo3(final BigDecimal idExp) {
    if (tieneExclusionTipo3(idExp)) {
      return 1;
    } else {
      return 0;
    }
  }

  public Integer comprobarNoTieneExclusionTipo3(final BigDecimal idExp) {
    if (!tieneExclusionTipo3(idExp)) {
      return 1;
    } else {
      return 0;
    }
  }

  public Integer comprobarTieneValoracion(final BigDecimal idExp) {
    try {
      final String abreviatura = trewaService.getAbreviaturaProcedimiento(idExp.toString());
      String etiquetaDocGenInfVal = null;
      if (abreviatura.equals(ConstantesTramitacion.ABREVIATURA_ONGD)) {
        etiquetaDocGenInfVal = ConstantesTramitacion.ETIQUETA_INF_VALORACION_ONGD;
      } else {
        etiquetaDocGenInfVal = ConstantesTramitacion.ETIQUETA_INF_VALORACION_UNIV;
      }

      TrDocumentoExpediente[] documentosExp;
      documentosExp = trewaService.documentosDelExpediente(idExp.longValue(), etiquetaDocGenInfVal);
      if (documentosExp != null && documentosExp.length > 0) {
        return 1;
      }
    } catch (final Exception e) {
      LOG.error(MENSAJE_ERROR_RECUPERAR_DOC_EXPEDIENTE, e);
      return 0;
    }

    return 0;
  }

  public Integer compruebaTareaDesestimadasFinalizada(final BigDecimal idExpFase) {

    try {
      final TrFaseExpediente faseExpediente = trewaService.obtenerFaseExpediente(idExpFase.toString());

      final boolean resultado = obtenerFinalizadaTarea(ConstantesTramitacion.DESESTIMACION, faseExpediente);
      if (resultado) {
        return 1;
      } else {
        return 0;
      }
    } catch (final Exception e) {
      LOG.error(MENSAJE_ERROR_RECUPERAR_DATOS_SOLICITUD_EXCLUSION, e);
      return 0;
    }
  }

  private boolean tieneExclusionTipo3(final BigDecimal idExp) {
    try {
      final TrExpediente expediente = trewaService.obtenerExpedienteTrewa(idExp.toString());
      if (exclusionService != null) {
        final boolean tieneExclusionesTipo1 = exclusionService.existenExclusionesSolicitudByTipo(expediente.getREFEXP().toString(), 3, StringUtils.EMPTY);

        if (tieneExclusionesTipo1) {
          return true;
        }
      }
    } catch (final Exception e) {
      LOG.error(MENSAJE_ERROR_RECUPERAR_DATOS_SOLICITUD_EXCLUSION, e);
      return false;
    }
    return false;
  }

  private boolean obtenerFinalizadaTarea(final String tipoEntidad, final TrFaseExpediente faseExpediente) throws TramitacionException {
    final String abreviatura = trewaService.getAbreviaturaProcedimiento(faseExpediente.getREFEXPEDIENTE().toString());
    final TrFase fase = faseExpediente.getFASE();
    final String descFase = fase.getDESCRIPCION();
    final String nombreTarea = obtenerTareaSegunAbreviatura(abreviatura, tipoEntidad, descFase);
    final TrTareaExpediente[] tareas = trewaService.obtenerTareasExpediente(faseExpediente.getREFEXPEDIENTE().toString(), nombreTarea);
    boolean tareaFinalizada = false;

    if (tareas != null) {
      tareaFinalizada = tareaEstaFinalizada(tareas);
    }
    return tareaFinalizada;
  }
}

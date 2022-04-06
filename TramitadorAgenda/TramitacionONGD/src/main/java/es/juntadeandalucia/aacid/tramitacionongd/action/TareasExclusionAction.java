package es.juntadeandalucia.aacid.tramitacionongd.action;

import static es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion.EXCLUSION_TIPO_ADMISION;
import static es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion.EXCLUSION_TIPO_SUBSANACION;
import static java.util.Calendar.YEAR;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import es.juntadeandalucia.aacid.comuntramitacion.action.BaseTareasAction;
import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.DatosExclusionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.DocumentoExpedienteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ListasExclusionesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.IEnvioEmailAutomaticoService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IDocumentoExpedienteService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IExclusionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IHistoricoService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISubsanacionService;
import es.juntadeandalucia.aacid.tramitacionongd.constantes.ConstantesTramitacionONGD;
import es.juntadeandalucia.plataforma.comunes.excepciones.BusinessException;
import es.juntadeandalucia.plataforma.service.reserva.IReservaService;
import es.juntadeandalucia.plataforma.service.tramitacion.IGestionUsuariosService;
import es.juntadeandalucia.plataforma.service.usuarios.IUsuario;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;

/**
 * Action para el control de las tareas de subsanaciones
 *
 * @author Isotrol
 *
 */
public class TareasExclusionAction extends BaseTareasAction {

  private static final String EXITO_ENVIAR_EMAIL = "exito.enviar.email";

  private static final String ERROR_CARGAR = "error.cargar";

  private static final String MSG_EXITO_GUARDAR = "exito.guardar";

  /**
   *
   */
  private static final long serialVersionUID = -7740534787286082237L;

  private DatosExclusionDTO datosExclusionDTO;

  private transient IEnvioEmailAutomaticoService envioEmailAutomaticoService;
  private transient IExclusionService exclusionService;
  private transient ISubsanacionService subsanacionService;

  private transient IHistoricoService historicoService;
  private ListasExclusionesDTO listasExclusionesDTO;
  private final transient Logger log = Logger.getLogger(getClass());
  private transient ISolicitudService solicitudService;
  private transient IReservaService reservaService;
  private transient IGestionUsuariosService gestionUsuariosService;
  private transient IDocumentoExpedienteService documentoExpedienteService;

  public String cargarExclusionNoAdmisionTramitacion() {
    user = getUsuarioSesion();
    final List<Perfil> perfil = user.getListaPerfiles();
    final GregorianCalendar fechaCreacion = new GregorianCalendar();
    fechaCreacion.setTime(user.getExpediente().getFechaCreacion());
    final Long anio = (long) fechaCreacion.get(YEAR);

    final Map<Long, Object> mapaExclusiones = ConstantesTramitacionONGD.obtenerMapaExclusionesONGD();
    @SuppressWarnings("unchecked")
    final Map<String, Long> mapaTipoExclusionAdmision = (Map<String, Long>) mapaExclusiones.get(EXCLUSION_TIPO_ADMISION);
    try {
      obtenerListasExclusiones(perfil, anio, mapaTipoExclusionAdmision);
      obtenerExclusionesSolicitud();
      datosExclusionDTO.setTipoExclusion(EXCLUSION_TIPO_ADMISION);
      datosExclusionDTO
          .setNoTieneExclusiones(exclusionService.noTieneExclusionesTipoExclusion(datosExclusionDTO.getIdSolicitud(), EXCLUSION_TIPO_ADMISION.intValue()));
    } catch (final TramitacionException e) {
      errores = getText(ERROR_CARGAR);
      log.error(errores);
      addActionError(errores);
      return ERROR;
    }
    return SUCCESS;
  }

  public String cargarExclusionPosSubsanacion() {
    user = getUsuarioSesion();
    final List<Perfil> perfil = user.getListaPerfiles();
    final GregorianCalendar fechaCreacion = new GregorianCalendar();
    fechaCreacion.setTime(user.getExpediente().getFechaCreacion());
    final Long anio = (long) fechaCreacion.get(YEAR);
    try {
      if (subsanacionService.existeCatalogoSubsanacion(obtenerSolicitudDTO().getIdSolicitud())) {
        final Map<Long, Object> mapaExclusionesPosSub = ConstantesTramitacionONGD.obtenerMapaExclusionesPosSubONGD();
        @SuppressWarnings("unchecked")
        final Map<String, Long> mapaTipoExclusionSub = (Map<String, Long>) mapaExclusionesPosSub.get(EXCLUSION_TIPO_SUBSANACION);
        obtenerListasExclusionesTrasSubsanacion(perfil, anio, mapaTipoExclusionSub);
        obtenerExclusionesSolicitudTrasSubsanacion();
        datosExclusionDTO.setTipoExclusion(EXCLUSION_TIPO_SUBSANACION);
        datosExclusionDTO
            .setNoTieneExclusiones(exclusionService.noTieneExclusionesTipoExclusion(datosExclusionDTO.getIdSolicitud(), EXCLUSION_TIPO_SUBSANACION.intValue()));
      } else {
        final Map<Long, Object> mapaExclusiones = ConstantesTramitacionONGD.obtenerMapaExclusionesONGD();
        @SuppressWarnings("unchecked")
        final Map<String, Long> mapaTipoExclusionAdmision = (Map<String, Long>) mapaExclusiones.get(EXCLUSION_TIPO_ADMISION);
        obtenerListasExclusiones(perfil, anio, mapaTipoExclusionAdmision);
        obtenerExclusionesSolicitud();
        datosExclusionDTO.setTipoExclusion(EXCLUSION_TIPO_ADMISION);
        datosExclusionDTO
            .setNoTieneExclusiones(exclusionService.noTieneExclusionesTipoExclusion(datosExclusionDTO.getIdSolicitud(), EXCLUSION_TIPO_ADMISION.intValue()));
      }
    } catch (final TramitacionException e) {
      errores = getText(ERROR_CARGAR);
      log.error(errores);
      addActionError(errores);
      return ERROR;
    }
    return SUCCESS;
  }

  public String cargarExclusionCausasDesestimiento() {
    user = getUsuarioSesion();
    final List<Perfil> perfil = user.getListaPerfiles();
    final GregorianCalendar fechaCreacion = new GregorianCalendar();
    fechaCreacion.setTime(user.getExpediente().getFechaCreacion());
    final Long anio = (long) fechaCreacion.get(YEAR);

    final Map<Long, Object> mapaExclusionesCausaDesestimiento = ConstantesTramitacionONGD.obtenerMapaCausasDesestimiento();
    @SuppressWarnings("unchecked")
    final Map<String, Long> mapaTipoExclusionCausaDesestimiento = (Map<String, Long>) mapaExclusionesCausaDesestimiento
        .get(ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_DESESTIMACION);
    try {
      obtenerListasCausasDesestimiento(perfil, anio, mapaTipoExclusionCausaDesestimiento);
      obtenerExclusionesSolicitudCausasDesestimacion();
      datosExclusionDTO.setTipoExclusion(ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_DESESTIMACION);
      datosExclusionDTO.setNoTieneExclusiones(exclusionService.noTieneExclusionesTipoExclusion(datosExclusionDTO.getIdSolicitud(),
          ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_DESESTIMACION.intValue()));
    } catch (final TramitacionException e) {
      errores = getText(ERROR_CARGAR);
      log.error(errores);
      addActionError(errores);
      return ERROR;
    }
    return SUCCESS;
  }

  public String guardarCausasDesestimacion() {
    try {
      if (obtenerSolicitudDTO().getIdSolicitud() != null) {
        datosExclusionDTO.setIdSolicitud(obtenerSolicitudDTO().getIdSolicitud());
        datosExclusionDTO.setTipoExclusion(ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_DESESTIMACION);
        exclusionService.guardarExclusionesSolicitud(datosExclusionDTO);
        historicoService.guardarHistorico(getUsuarioSesion().getExpediente().getNumeroExpediente(), getUsuarioSesion().getNombreUsuario());
      } else {
        addActionError(getText("msg.error.guardar.exclusion.causasDesestimacion.noSolicitud"));
        getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return ERROR;
      }
    } catch (final TramitacionException e) {
      log.error(e.getMessage());
      addActionError(getText("msg.error.guardar.exclusion.causasDesestimacion"));
      getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
      return ERROR;
    }
    addActionMessage(getText(MSG_EXITO_GUARDAR));
    return SUCCESS;
  }

  public String cargarExclusionCausasInadmisionPosPropProvConce() {
    user = getUsuarioSesion();
    final List<Perfil> perfil = user.getListaPerfiles();
    final GregorianCalendar fechaCreacion = new GregorianCalendar();
    fechaCreacion.setTime(user.getExpediente().getFechaCreacion());
    final Long anio = (long) fechaCreacion.get(YEAR);

    final Map<Long, Object> mapaExclusionesCausaInadmision = ConstantesTramitacionONGD.obtenerMapaCausasInadmision();
    @SuppressWarnings("unchecked")
    final Map<String, Long> mapaTipoExclusionCausaInadmision = (Map<String, Long>) mapaExclusionesCausaInadmision
        .get(ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_INADMISION);
    try {
      listasExclusionesDTO = new ListasExclusionesDTO();
      datosExclusionDTO = new DatosExclusionDTO();
      obtenerListasCausasInadmision(perfil, anio, mapaTipoExclusionCausaInadmision);
      obtenerExclusionesSolicitudCausasInadmision();

      // obtener exclusiones tipo 2
      obtenerExclusionesTipo2ExcepcionesExclusion();
      obtenerExclusionesSolicitudTipo2ExcepcionesExclusion(perfil);

      datosExclusionDTO.setTipoExclusion(ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_INADMISION);
      boolean tieneExclusiones = exclusionService.noTieneExclusionesTipoExclusion(datosExclusionDTO.getIdSolicitud(),
          ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_INADMISION.intValue());
      tieneExclusiones = tieneExclusiones
          || exclusionService.noTieneExclusionesTipoExclusion(datosExclusionDTO.getIdSolicitud(), ConstantesTramitacion.EXCLUSION_TIPO_ADMISION.intValue());

      datosExclusionDTO.setNoTieneExclusiones(!tieneExclusiones);

    } catch (final TramitacionException e) {
      errores = getText(ERROR_CARGAR);
      log.error(errores);
      addActionError(errores);
      return ERROR;
    }
    return SUCCESS;
  }

  public String guardarCausasInadmision() {
    try {
      if (obtenerSolicitudDTO().getIdSolicitud() != null) {
        datosExclusionDTO.setIdSolicitud(obtenerSolicitudDTO().getIdSolicitud());
        datosExclusionDTO.setTipoExclusion(ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_INADMISION);
        exclusionService.guardarExclusionesSolicitud(datosExclusionDTO);
        historicoService.guardarHistorico(getUsuarioSesion().getExpediente().getNumeroExpediente(), getUsuarioSesion().getNombreUsuario());
      } else {
        addActionError(getText("msg.error.guardar.exclusion.causasInadmision.noSolicitud"));
        getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return ERROR;
      }
    } catch (final TramitacionException e) {
      log.error(e.getMessage());
      addActionError(getText("msg.error.guardar.exclusion.causasInadmision"));
      getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
      return ERROR;
    }
    addActionMessage(getText(MSG_EXITO_GUARDAR));
    return SUCCESS;
  }

  public String cargarExclusionCausasInadmisionDocumentacionAportar() {
    user = getUsuarioSesion();
    final List<Perfil> perfil = user.getListaPerfiles();
    final GregorianCalendar fechaCreacion = new GregorianCalendar();
    fechaCreacion.setTime(user.getExpediente().getFechaCreacion());
    final Long anio = (long) fechaCreacion.get(YEAR);

    final Map<Long, Object> mapaExclusionesCausaInadmision = ConstantesTramitacionONGD.obtenerMapaCausasInadmision();
    @SuppressWarnings("unchecked")
    final Map<String, Long> mapaTipoExclusionCausaInadmision = (Map<String, Long>) mapaExclusionesCausaInadmision
        .get(ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_INADMISION);
    try {
      obtenerListasCausasInadmision(perfil, anio, mapaTipoExclusionCausaInadmision);
      obtenerExclusionesSolicitudCausasInadmision();
      datosExclusionDTO.setTipoExclusion(ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_INADMISION);
      datosExclusionDTO.setNoTieneExclusiones(exclusionService.noTieneExclusionesTipoExclusion(datosExclusionDTO.getIdSolicitud(),
          ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_INADMISION.intValue()));
    } catch (final TramitacionException e) {
      errores = getText(ERROR_CARGAR);
      log.error(errores);
      addActionError(errores);
      return ERROR;
    }
    return SUCCESS;
  }

  public String guardarCausasInadmisionDocumentacionAportar() {
    try {
      if (obtenerSolicitudDTO().getIdSolicitud() != null) {
        datosExclusionDTO.setIdSolicitud(obtenerSolicitudDTO().getIdSolicitud());
        datosExclusionDTO.setTipoExclusion(ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_INADMISION);
        exclusionService.guardarExclusionesSolicitud(datosExclusionDTO);
        historicoService.guardarHistorico(getUsuarioSesion().getExpediente().getNumeroExpediente(), getUsuarioSesion().getNombreUsuario());
      } else {
        addActionError(getText("msg.error.guardar.exclusion.causasInadmision.noSolicitud"));
        getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return ERROR;
      }
    } catch (final TramitacionException e) {
      log.error(e.getMessage());
      addActionError(getText("msg.error.guardar.exclusion.causasInadmision"));
      getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
      return ERROR;
    }
    addActionMessage(getText(MSG_EXITO_GUARDAR));
    return SUCCESS;
  }

  public String envioEmailDocumentoExclusion() {
    user = getUsuarioSesion();
    try {
      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(user.getExpediente().getRefExpediente());
      envioEmailAutomaticoService.enviarEmail(ConstantesTramitacion.EXCLUSION, solicitudDTO.getEmail(), user.getExpediente().getRefExpediente(),
          ConstantesTramitacion.ETIQUETA_DOC_EXCL_PROV, null);
    } catch (final AddressException e) {
      log.error("La direccion para el envio de correo automatico con el documento de exclusion adjunto es incorrecta. ERROR:" + e.getMessage());
      addActionError(e.getMessage());
      return ERROR;
    } catch (final MessagingException e) {
      log.error("Error en el envio de correo automatico con el documento de exclusion adjunto. Causa: " + e.getMessage(), e);
      addActionError(e.getMessage());
      return ERROR;
    } catch (final Exception ex) {
      log.error("Error en el envio de correo automatico con el documento de exclusion adjunto. Causa: " + ex.getMessage(), ex);
      addActionError(ex.getMessage());
      return ERROR;
    }
    addActionMessage(getText(EXITO_ENVIAR_EMAIL));
    return SUCCESS;
  }

  public String envioEmailDocumentoResolucionExclusionDefinitiva() {
    user = getUsuarioSesion();
    try {
      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(user.getExpediente().getRefExpediente());
      envioEmailAutomaticoService.enviarEmail(ConstantesTramitacion.EXCLUSION, solicitudDTO.getEmail(), user.getExpediente().getRefExpediente(),
          ConstantesTramitacion.ETIQUETA_DOC_EXCL_DEF_ONGD, null);
    } catch (final AddressException e) {
      log.error("La direccion para el envio de correo automatico con el documento de exclusion definitiva adjunto es incorrecta. ERROR:" + e.getMessage(), e);
      addActionError(e.getMessage());
      return ERROR;
    } catch (final MessagingException e) {
      log.error("Error en el envio de correo automatico con el documento de exclusion definitiva adjunto. Causa: " + e.getMessage(), e);
      addActionError(e.getMessage());
      return ERROR;
    } catch (final Exception ex) {
      log.error("Error en el envio de correo automatico con el documento de exclusion definitiva adjunto. Causa: " + ex.getMessage(), ex);
      addActionError(ex.getMessage());
      return ERROR;
    }
    addActionMessage(getText(EXITO_ENVIAR_EMAIL));
    return SUCCESS;
  }

  public String envioEmailDocumentoResolucionDesestimadas() {
    user = getUsuarioSesion();
    try {
      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(user.getExpediente().getRefExpediente());
      envioEmailAutomaticoService.enviarEmail(ConstantesTramitacion.EXCLUSION, solicitudDTO.getEmail(), user.getExpediente().getRefExpediente(),
          ConstantesTramitacion.ETIQUETA_DOC_DES, null);
    } catch (final AddressException e) {
      log.error("La direccion para el envio de correo automatico con el documento de resolucion desestimadas adjunto es incorrecta. ERROR:" + e.getMessage(),
          e);
      addActionError(e.getMessage());
      return ERROR;
    } catch (final MessagingException e) {
      log.error("Error en el envio de correo automatico con el documento de resolucion desestimadas adjunto. Causa: " + e.getMessage(), e);
      addActionError(e.getMessage());
      return ERROR;
    } catch (final Exception ex) {
      log.error("Error en el envio de correo automatico con el documento de resolucion desestimadas adjunto. Causa: " + ex.getMessage(), ex);
      addActionError(ex.getMessage());
      return ERROR;
    }
    addActionMessage(getText(EXITO_ENVIAR_EMAIL));
    return SUCCESS;
  }

  public String generarListaExclusionDefinitiva() {
    user = getUsuarioSesion();

    final ExecutorService service = Executors.newFixedThreadPool(1);
    service.submit(() -> {
      try {
        reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
        final IUsuario usuario = gestionUsuariosService.obtenerUsuario(ConstantesTramitacion.USUARIO_GENERACION_DOCUMENTO);
        reservaService.reservarExpediente(user.getExpediente(), usuario, ConstantesTramitacion.ESQUEMA_TREWA_AACID);

        final Map<String, Object> datos = exclusionService.obtenerDatosDocumentoExclusiones(user, false, true, null, false,
            ConstantesTramitacion.FASE_RESOLUCION_DEFINITIVA_EXCLUSION);

        DocumentoExpedienteDTO doc = tareasService.generarInformes(datos, ConstantesTramitacion.PLANTILLA_EXCLUSION_DEFINITIVA_CONVOCATORIA_ONGD,
            ConstantesTramitacion.NOMBRE_INFORME_SALIDA_EXCLUSION_DEFINITIVA_CONVOCATORIA, ConstantesTramitacion.NOMBRE_DOC_IN_EXCL_DEF_CONV_ONGD,
            ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_EXCLUSION_DEFINITIVA_CONV_ONGD, user);

        documentoExpedienteService.guardarDocumentoExpediente(doc);

      } catch (final Exception e1) {
        addActionError(e1.getMessage());
        log.error(e1.getMessage(), e1);
      } finally {
        try {
          reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
        } catch (final BusinessException e2) {
          addActionError(e2.getMessage());
          log.error(e2.getMessage(), e2);
        }
      }
    });
    addActionMessage(ConstantesTramitacion.MENSAJE_DOCUMENTO_GENERANDO);
    return SUCCESS;
  }

  public String generarListaExclusionProvisional() {
    user = getUsuarioSesion();
    final ExecutorService service = Executors.newFixedThreadPool(1);
    service.submit(() -> {
      try {
        reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
        final IUsuario usuario = gestionUsuariosService.obtenerUsuario(ConstantesTramitacion.USUARIO_GENERACION_DOCUMENTO);
        reservaService.reservarExpediente(user.getExpediente(), usuario, ConstantesTramitacion.ESQUEMA_TREWA_AACID);

        final Map<String, Object> datos = exclusionService.obtenerDatosDocumentoExclusiones(user, false, true, null, false,
            ConstantesTramitacion.FASE_RESOLUCION_PROVISIONAL);

        DocumentoExpedienteDTO doc = tareasService.generarInformes(datos, ConstantesTramitacion.PLANTILLA_EXCLUSION_PROVISIONAL_CONVOCATORIA_ONGD,
            ConstantesTramitacion.NOMBRE_INFORME_SALIDA_EXCLUSION_PROVSIONAL_CONVOCATORIA, ConstantesTramitacion.NOMBRE_DOC_IN_EXCL_PROV_CONV_ONGD,
            ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_EXCLUSION_PROVISIONAL_CONV_ONGD, user);

        documentoExpedienteService.guardarDocumentoExpediente(doc);

      } catch (final Exception e1) {
        addActionError(e1.getMessage());
        log.error(e1.getMessage(), e1);
      } finally {
        try {
          reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
        } catch (final BusinessException e2) {
          addActionError(e2.getMessage());
          log.error(e2.getMessage(), e2);
        }
      }
    });
    addActionMessage(ConstantesTramitacion.MENSAJE_DOCUMENTO_GENERANDO);
    return SUCCESS;
  }

  public String generarResolucionDefinitivaExcluidas() {
    user = getUsuarioSesion();
    final ExecutorService service = Executors.newFixedThreadPool(1);
    service.submit(() -> {
      try {
        reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
        final IUsuario usuario = gestionUsuariosService.obtenerUsuario(ConstantesTramitacion.USUARIO_GENERACION_DOCUMENTO);
        reservaService.reservarExpediente(user.getExpediente(), usuario, ConstantesTramitacion.ESQUEMA_TREWA_AACID);

        final Map<String, Object> datos = exclusionService.obtenerDatosDocumentoResolucionExcluidas(user, false, false, false);

        DocumentoExpedienteDTO doc = tareasService.generarInformes(datos, ConstantesTramitacion.PLANTILLA_EXCLUSION_DEFINITIVA_SOLICITUD_ONGD,
            ConstantesTramitacion.NOMBRE_INFORME_SALIDA_EXCLUSION_DEFINITIVA_SOLICITUD, ConstantesTramitacion.NOMBRE_DOC_IN_EXCL_DEF_ONGD,
            ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_EXCLUSION_DEFINITIVA_SOLI_ONGD, user);

        documentoExpedienteService.guardarDocumentoExpediente(doc);

      } catch (final Exception e1) {
        addActionError(e1.getMessage());
        log.error(e1.getMessage(), e1);
      } finally {
        try {
          reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
        } catch (final BusinessException e2) {
          addActionError(e2.getMessage());
          log.error(e2.getMessage(), e2);
        }
      }
    });
    addActionMessage(ConstantesTramitacion.MENSAJE_DOCUMENTO_GENERANDO);
    return SUCCESS;
  }

  public String generarResolucionProvisionalExcluidas() {
    user = getUsuarioSesion();
    final ExecutorService service = Executors.newFixedThreadPool(1);
    service.submit(() -> {
      try {
        reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
        final IUsuario usuario = gestionUsuariosService.obtenerUsuario(ConstantesTramitacion.USUARIO_GENERACION_DOCUMENTO);
        reservaService.reservarExpediente(user.getExpediente(), usuario, ConstantesTramitacion.ESQUEMA_TREWA_AACID);

        final Map<String, Object> datos = exclusionService.obtenerDatosDocumentoResolucionExcluidas(user, true, false, false);

        DocumentoExpedienteDTO doc = tareasService.generarInformes(datos, ConstantesTramitacion.PLANTILLA_EXCLUSION_PROVISIONAL_SOLICITUD_ONGD,
            ConstantesTramitacion.NOMBRE_INFORME_SALIDA_EXCLUSION_PROVSIONAL_SOLICITUD, ConstantesTramitacion.NOMBRE_DOC_IN_EXCL_PROV_SOLI_ONGD,
            ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_EXCLUSION_PROVISIONAL_SOLI_ONGD, user);

        documentoExpedienteService.guardarDocumentoExpediente(doc);

      } catch (final Exception e1) {
        addActionError(e1.getMessage());
        log.error(e1.getMessage(), e1);
      } finally {
        try {
          reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
        } catch (final BusinessException e2) {
          addActionError(e2.getMessage());
          log.error(e2.getMessage(), e2);
        }
      }
    });
    addActionMessage(ConstantesTramitacion.MENSAJE_DOCUMENTO_GENERANDO);
    return SUCCESS;
  }
  // Nuevos cambios

  public String generarResolucionDesestimada() {
    user = getUsuarioSesion();
    final ExecutorService service = Executors.newFixedThreadPool(1);
    service.submit(() -> {
      try {
        reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
        final IUsuario usuario = gestionUsuariosService.obtenerUsuario(ConstantesTramitacion.USUARIO_GENERACION_DOCUMENTO);
        reservaService.reservarExpediente(user.getExpediente(), usuario, ConstantesTramitacion.ESQUEMA_TREWA_AACID);

        final Map<String, Object> datos = exclusionService.obtenerDatosDocumentoResolucionDestimadas(user, false, false, false);

        DocumentoExpedienteDTO doc = tareasService.generarInformes(datos, ConstantesTramitacion.PLANTILLA_DESESTIMADA_DEFINITIVA_SOLICITUD_ONGD,
            ConstantesTramitacion.NOMBRE_INFORME_SALIDA_DESESTIMADA_DEFINITIVA_SOLICITUD, ConstantesTramitacion.NOMBRE_DOC_IN_DESEST_DEF_ONGD,
            ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_DESEST_DEFINITIVA_SOLI_ONGD, user);

        documentoExpedienteService.guardarDocumentoExpediente(doc);

      } catch (final Exception e1) {
        addActionError(e1.getMessage());
        log.error(e1.getMessage(), e1);
      } finally {
        try {
          reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
        } catch (final BusinessException e2) {
          addActionError(e2.getMessage());
          log.error(e2.getMessage(), e2);
        }
      }
    });
    addActionMessage(ConstantesTramitacion.MENSAJE_DOCUMENTO_GENERANDO);
    return SUCCESS;
  }

  /**
   * Obtiene la propiedad datosExclusionDTO
   *
   * @return el datosExclusionDTO
   */
  public DatosExclusionDTO getDatosExclusionDTO() {
    return datosExclusionDTO;
  }

  public String generarListaDesestimadas() {
    user = getUsuarioSesion();
    final ExecutorService service = Executors.newFixedThreadPool(1);
    service.submit(() -> {
      try {
        reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
        final IUsuario usuario = gestionUsuariosService.obtenerUsuario(ConstantesTramitacion.USUARIO_GENERACION_DOCUMENTO);
        reservaService.reservarExpediente(user.getExpediente(), usuario, ConstantesTramitacion.ESQUEMA_TREWA_AACID);

        final Map<String, Object> datos = exclusionService.obtenerDatosDocumentoExclusionesDesestimadas(user, false, true, null,
            ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_DESESTIMACION, ConstantesTramitacion.FASE_RESOLUCION_DESESTIMACION_PUNTUACION_REQUISITOS);

        DocumentoExpedienteDTO doc = tareasService.generarInformes(datos, ConstantesTramitacion.PLANTILLA_DESESTIMADA_CONVOCATORIA_ONGD,
            ConstantesTramitacion.NOMBRE_INFORME_SALIDA_DESESTIMADA_CONVOCATORIA, ConstantesTramitacion.NOMBRE_DOC_IN_DESEST_CONV_ONGD,
            ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_DESEST_CONV_ONGD, user);

        documentoExpedienteService.guardarDocumentoExpediente(doc);

      } catch (final Exception e1) {
        addActionError(e1.getMessage());
        log.error(e1.getMessage(), e1);
      } finally {
        try {
          reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
        } catch (final BusinessException e2) {
          addActionError(e2.getMessage());
          log.error(e2.getMessage(), e2);
        }
      }
    });
    addActionMessage(ConstantesTramitacion.MENSAJE_DOCUMENTO_GENERANDO);
    return SUCCESS;
  }

  /**
   * Obtiene la propiedad listasExclusionesDTO
   *
   * @return el listasExclusionesDTO
   */
  public ListasExclusionesDTO getListasExclusionesDTO() {
    return listasExclusionesDTO;
  }

  public String guardarExclusionPosSubsanacion() {
    try {
      if (obtenerSolicitudDTO().getIdSolicitud() != null) {
        datosExclusionDTO.setIdSolicitud(obtenerSolicitudDTO().getIdSolicitud());
        if (subsanacionService.existeCatalogoSubsanacion(obtenerSolicitudDTO().getIdSolicitud())) {
          datosExclusionDTO.setTipoExclusion(EXCLUSION_TIPO_SUBSANACION);
        } else {
          datosExclusionDTO.setTipoExclusion(EXCLUSION_TIPO_ADMISION);
        }
        exclusionService.guardarExclusionesSolicitud(datosExclusionDTO);
        historicoService.guardarHistorico(getUsuarioSesion().getExpediente().getNumeroExpediente(), getUsuarioSesion().getNombreUsuario());
      } else {
        addActionError(getText("msg.error.guardar.exclusion.tramitacion.NoSolicitud"));
        getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return ERROR;
      }
    } catch (final TramitacionException e) {
      log.error(e.getMessage());
      addActionError(getText("msg.error.guardar.exclusion.tramitacion"));
      getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
      return ERROR;
    }
    addActionMessage(getText(MSG_EXITO_GUARDAR));
    return SUCCESS;
  }

  public String guardarExclusionTramitacion() {
    try {
      if (obtenerSolicitudDTO().getIdSolicitud() != null) {
        datosExclusionDTO.setIdSolicitud(obtenerSolicitudDTO().getIdSolicitud());
        datosExclusionDTO.setTipoExclusion(EXCLUSION_TIPO_ADMISION);
        exclusionService.guardarExclusionesSolicitud(datosExclusionDTO);
        historicoService.guardarHistorico(getUsuarioSesion().getExpediente().getNumeroExpediente(), getUsuarioSesion().getNombreUsuario());
      } else {
        addActionError(getText("msg.error.guardar.exclusion.tramitacion.NoSolicitud"));
        getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return ERROR;
      }
    } catch (final TramitacionException e) {
      log.error(e.getMessage());
      addActionError(getText("msg.error.guardar.exclusion.tramitacion"));
      getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
      return ERROR;
    }
    addActionMessage(getText(MSG_EXITO_GUARDAR));
    return SUCCESS;
  }

  /**
   *
   */
  @SuppressWarnings("unchecked")
  private void obtenerExclusionesSolicitud() {
    final Map<String, Long> mapaTipoExclusionAdmision = (Map<String, Long>) ConstantesTramitacionONGD.obtenerMapaExclusionesONGD().get(EXCLUSION_TIPO_ADMISION);
    datosExclusionDTO = new DatosExclusionDTO();
    try {
      final SolicitudDTO soliDTO = obtenerSolicitudDTO();
      datosExclusionDTO.setIdSolicitud(soliDTO.getIdSolicitud());
      datosExclusionDTO.setExclusionesComunesSeleccionadas(exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(),
          EXCLUSION_TIPO_ADMISION, mapaTipoExclusionAdmision.get(ConstantesTramitacionONGD.COMUNES), user.getListaPerfiles()));
      datosExclusionDTO.setExclusionesCooperacionSeleccionadas(exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(),
          EXCLUSION_TIPO_ADMISION, mapaTipoExclusionAdmision.get(ConstantesTramitacionONGD.COOPERACION), user.getListaPerfiles()));
      datosExclusionDTO.setExclusionesEducacionSeleccionadas(exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(),
          EXCLUSION_TIPO_ADMISION, mapaTipoExclusionAdmision.get(ConstantesTramitacionONGD.EDUCACION), user.getListaPerfiles()));
      datosExclusionDTO.setExclusionesHumanitariaSeleccionadas(exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(),
          EXCLUSION_TIPO_ADMISION, mapaTipoExclusionAdmision.get(ConstantesTramitacionONGD.HUMANITARIA), user.getListaPerfiles()));
      datosExclusionDTO.setExclusionesFormacionSeleccionadas(exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(),
          EXCLUSION_TIPO_ADMISION, mapaTipoExclusionAdmision.get(ConstantesTramitacionONGD.FORMACION_INVEST_INNOV), user.getListaPerfiles()));
    } catch (final TramitacionException e) {
      log.error(e.getMessage());
    }
  }

  /**
   *
   */
  @SuppressWarnings("unchecked")
  private void obtenerExclusionesSolicitudTrasSubsanacion() {
    final Map<String, Long> mapaTipoExclusionPosSubsanacion = (Map<String, Long>) ConstantesTramitacionONGD.obtenerMapaExclusionesPosSubONGD()
        .get(EXCLUSION_TIPO_SUBSANACION);
    datosExclusionDTO = new DatosExclusionDTO();
    try {
      final SolicitudDTO soliDTO = obtenerSolicitudDTO();
      datosExclusionDTO.setIdSolicitud(soliDTO.getIdSolicitud());
      datosExclusionDTO.setExclusionesInadmisionSeleccionadas(exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(),
          EXCLUSION_TIPO_SUBSANACION, mapaTipoExclusionPosSubsanacion.get(ConstantesTramitacionONGD.INADMISION_DESEST), user.getListaPerfiles()));
      datosExclusionDTO.setExclusionesProyCooperacionSeleccionadas(exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(),
          EXCLUSION_TIPO_SUBSANACION, mapaTipoExclusionPosSubsanacion.get(ConstantesTramitacionONGD.PROY_COOPERACION), user.getListaPerfiles()));
      datosExclusionDTO.setExclusionesProyAccHumanitariaSeleccionadas(exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(),
          EXCLUSION_TIPO_SUBSANACION, mapaTipoExclusionPosSubsanacion.get(ConstantesTramitacionONGD.PROY_ACC_HUM), user.getListaPerfiles()));

    } catch (final TramitacionException e) {
      log.error(e.getMessage());
    }
  }

  /**
   *
   */
  @SuppressWarnings("unchecked")
  private void obtenerExclusionesTipo2ExcepcionesExclusion() {
    final Map<String, Long> mapaTipoExclusionPosSubsanacion = (Map<String, Long>) ConstantesTramitacionONGD.obtenerMapaExclusionesPosSubONGD()
        .get(EXCLUSION_TIPO_SUBSANACION);
    try {
      final SolicitudDTO soliDTO = obtenerSolicitudDTO();
      datosExclusionDTO.setIdSolicitud(soliDTO.getIdSolicitud());
      datosExclusionDTO.setExclusionesExcepcionTipo2Seleccionadas(exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(),
          EXCLUSION_TIPO_SUBSANACION, mapaTipoExclusionPosSubsanacion.get(ConstantesTramitacionONGD.INADMISION_DESEST), user.getListaPerfiles()));
      datosExclusionDTO.getExclusionesExcepcionTipo2Seleccionadas().addAll(exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(),
          EXCLUSION_TIPO_SUBSANACION, mapaTipoExclusionPosSubsanacion.get(ConstantesTramitacionONGD.PROY_COOPERACION), user.getListaPerfiles()));
      datosExclusionDTO.getExclusionesExcepcionTipo2Seleccionadas().addAll(exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(),
          EXCLUSION_TIPO_SUBSANACION, mapaTipoExclusionPosSubsanacion.get(ConstantesTramitacionONGD.PROY_ACC_HUM), user.getListaPerfiles()));

    } catch (final TramitacionException e) {
      log.error(e.getMessage());
    }
  }

  /**
   *
   */
  @SuppressWarnings("unchecked")
  private void obtenerExclusionesSolicitudCausasDesestimacion() {
    final Map<String, Long> mapaTipoCausasDesestimacion = (Map<String, Long>) ConstantesTramitacionONGD.obtenerMapaCausasDesestimiento()
        .get(ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_DESESTIMACION);
    datosExclusionDTO = new DatosExclusionDTO();
    try {
      final SolicitudDTO soliDTO = obtenerSolicitudDTO();
      datosExclusionDTO.setIdSolicitud(soliDTO.getIdSolicitud());
      datosExclusionDTO.setExclusionesComunesSeleccionadas(
          exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(), ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_DESESTIMACION,
              mapaTipoCausasDesestimacion.get(ConstantesTramitacionONGD.CAUSAS_DESESTIMIENTO), user.getListaPerfiles()));
    } catch (final TramitacionException e) {
      log.error(e.getMessage());
    }
  }

  /**
   *
   */
  @SuppressWarnings("unchecked")
  private void obtenerExclusionesSolicitudCausasInadmision() {
    final Map<String, Long> mapaTipoCausasInadmision = (Map<String, Long>) ConstantesTramitacionONGD.obtenerMapaCausasInadmision()
        .get(ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_INADMISION);
    try {
      final SolicitudDTO soliDTO = obtenerSolicitudDTO();
      datosExclusionDTO.setIdSolicitud(soliDTO.getIdSolicitud());
      datosExclusionDTO.setExclusionesComunesSeleccionadas(
          exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(), ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_INADMISION,
              mapaTipoCausasInadmision.get(ConstantesTramitacion.CAUSA_CONCESION_DEFINITIVA), user.getListaPerfiles()));
    } catch (final TramitacionException e) {
      log.error(e.getMessage());
    }
  }

  /**
   * @param perfil
   * @param anio
   * @param mapaTipoExclusionPreSub
   */
  private void obtenerListasExclusiones(final List<Perfil> perfil, final Long anio, final Map<String, Long> mapaTipoExclusionPreSub)
      throws TramitacionException {
    listasExclusionesDTO = new ListasExclusionesDTO();
    listasExclusionesDTO.setListaExclusionesComunes(exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_ONGD,
        EXCLUSION_TIPO_ADMISION, mapaTipoExclusionPreSub.get(ConstantesTramitacionONGD.COMUNES), anio, perfil));
    listasExclusionesDTO.setListaExclusionesCooperacion(exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_ONGD,
        EXCLUSION_TIPO_ADMISION, mapaTipoExclusionPreSub.get(ConstantesTramitacionONGD.COOPERACION), anio, perfil));
    listasExclusionesDTO.setListaExclusionesEducacion(exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_ONGD,
        EXCLUSION_TIPO_ADMISION, mapaTipoExclusionPreSub.get(ConstantesTramitacionONGD.EDUCACION), anio, perfil));
    listasExclusionesDTO.setListaExclusionesHumanitaria(exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_ONGD,
        EXCLUSION_TIPO_ADMISION, mapaTipoExclusionPreSub.get(ConstantesTramitacionONGD.HUMANITARIA), anio, perfil));
    listasExclusionesDTO.setListaExclusionesFormacion(exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_ONGD,
        EXCLUSION_TIPO_ADMISION, mapaTipoExclusionPreSub.get(ConstantesTramitacionONGD.FORMACION_INVEST_INNOV), anio, perfil));
  }

  /**
   * @param perfil
   * @param anio
   * @param mapaTipoExclusionTrasSub
   */
  private void obtenerListasExclusionesTrasSubsanacion(final List<Perfil> perfil, final Long anio, final Map<String, Long> mapaTipoExclusionTrasSub)
      throws TramitacionException {
    listasExclusionesDTO = new ListasExclusionesDTO();
    listasExclusionesDTO.setListaExclusionesInadDesestimiento(exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_ONGD,
        EXCLUSION_TIPO_SUBSANACION, mapaTipoExclusionTrasSub.get(ConstantesTramitacionONGD.INADMISION_DESEST), anio, perfil));
    listasExclusionesDTO.setListaExclusionesProyectosCooperacion(exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_ONGD,
        EXCLUSION_TIPO_SUBSANACION, mapaTipoExclusionTrasSub.get(ConstantesTramitacionONGD.PROY_COOPERACION), anio, perfil));
    listasExclusionesDTO.setListaExclusionesProyectosAccHumanitaria(exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_ONGD,
        EXCLUSION_TIPO_SUBSANACION, mapaTipoExclusionTrasSub.get(ConstantesTramitacionONGD.PROY_ACC_HUM), anio, perfil));
  }

  /**
   * obtenemos causas de desestimacion
   *
   * @param perfil
   * @param anio
   * @param mapaTipoCausasDesest
   */
  private void obtenerListasCausasDesestimiento(final List<Perfil> perfil, final Long anio, final Map<String, Long> mapaTipoCausasDesest)
      throws TramitacionException {
    listasExclusionesDTO = new ListasExclusionesDTO();
    listasExclusionesDTO.setListaExclusionesComunes(exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_ONGD,
        ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_DESESTIMACION, mapaTipoCausasDesest.get(ConstantesTramitacionONGD.CAUSAS_DESESTIMIENTO), anio, perfil));
  }

  /**
   * obtenemos causas de inadmisión
   *
   * @param perfil
   * @param anio
   * @param mapaTipoCausasDesest
   */
  private void obtenerListasCausasInadmision(final List<Perfil> perfil, final Long anio, final Map<String, Long> mapaTipoCausasDesest)
      throws TramitacionException {
    listasExclusionesDTO.setListaExclusionesComunes(exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_ONGD,
        ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_INADMISION, mapaTipoCausasDesest.get(ConstantesTramitacion.CAUSA_CONCESION_DEFINITIVA), anio, perfil));
  }

  /**
   * @param perfil
   * @param anio
   * @param mapaTipoExclusionTrasSub
   */
  private void obtenerExclusionesSolicitudTipo2ExcepcionesExclusion(final List<Perfil> perfil) throws TramitacionException {
    final List<Long> idsExclusiones = new ArrayList<>();
    for (final String contenido : datosExclusionDTO.getExclusionesExcepcionTipo2Seleccionadas()) {
      idsExclusiones.add(Long.valueOf(contenido.substring(0, contenido.indexOf("-"))));
    }
    listasExclusionesDTO.setListaExclusionesExcepcionTipo2(exclusionService.obtenerExclusionesDTOByIds(idsExclusiones, perfil));
  }

  /**
   * @throws TramitacionException
   */
  private SolicitudDTO obtenerSolicitudDTO() throws TramitacionException {
    final String idExp = getUsuarioSesion().getExpediente().getRefExpediente();
    try {
      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(idExp);

      if (solicitudDTO == null) {
        throw new TramitacionException("Se ha producido un error al recuperar la solicitud del expediente con id " + idExp);
      }

      return solicitudDTO;
    } catch (final TramitacionException e) {
      throw new TramitacionException("Se ha producido un error al recuperar la solicitud del expediente con id " + idExp, e);
    }
  }

  /**
   * Establece el valor de la propiedad datosExclusionDTO
   *
   * @param datosExclusionDTO
   *          el datosExclusionDTO para establecer
   */
  public void setDatosExclusionDTO(final DatosExclusionDTO datosExclusionDTO) {
    this.datosExclusionDTO = datosExclusionDTO;
  }

  /**
   * Establece el valor de la propiedad envioEmailAutomaticoService
   *
   * @param envioEmailAutomaticoService
   *          el envioEmailAutomaticoService para establecer
   */
  public void setEnvioEmailAutomaticoService(final IEnvioEmailAutomaticoService envioEmailAutomaticoService) {
    this.envioEmailAutomaticoService = envioEmailAutomaticoService;
  }

  /**
   * Establece el valor de la propiedad exclusionService
   *
   * @param exclusionService
   *          el exclusionService para establecer
   */
  public void setExclusionService(final IExclusionService exclusionService) {
    this.exclusionService = exclusionService;
  }

  public void setHistoricoService(final IHistoricoService historicoService) {
    this.historicoService = historicoService;
  }

  /**
   * Establece el valor de la propiedad listasExclusionesDTO
   *
   * @param listasExclusionesDTO
   *          el listasExclusionesDTO para establecer
   */
  public void setListasExclusionesDTO(final ListasExclusionesDTO listasExclusionesDTO) {
    this.listasExclusionesDTO = listasExclusionesDTO;
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

  public void setSubsanacionService(final ISubsanacionService subsanacionService) {
    this.subsanacionService = subsanacionService;
  }

  public void setReservaService(final IReservaService reservaService) {
    this.reservaService = reservaService;
  }

  public void setGestionUsuariosService(final IGestionUsuariosService gestionUsuariosService) {
    this.gestionUsuariosService = gestionUsuariosService;
  }

  public void setDocumentoExpedienteService(IDocumentoExpedienteService documentoExpedienteService) {
    this.documentoExpedienteService = documentoExpedienteService;
  }

  public String valorarSubsanacion() {
    user = getUsuarioSesion();
    return SUCCESS;

  }

}
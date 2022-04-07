package es.juntadeandalucia.aacid.tramitacionuniv.action;

import static es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion.EXCLUSION_TIPO_ADMISION;
import static es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion.EXCLUSION_TIPO_SUBSANACION;
import static java.util.Calendar.YEAR;

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
import es.juntadeandalucia.aacid.tramitacionuniv.constantes.ConstantesTramitacionUNIV;
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
public class TareasExclusionUnivAction extends BaseTareasAction {

  /**
   *
   */
  private static final long serialVersionUID = -7740534787286082237L;
  private final transient Logger log = Logger.getLogger(getClass());

  private static final String EXITO_ENVIAR_EMAIL = "exito.enviar.email";
  private static final String ERROR_CARGAR = "error.cargar";
  private static final String EXITO_GUARDAR = "exito.guardar";
  private static final String MENSAJE_EXITO_GEN_INFORME_DESES = "Se ha generado correctamente el informe de desestimaci\u00F3n";

  private transient IEnvioEmailAutomaticoService envioEmailAutomaticoService;
  private transient IExclusionService exclusionService;
  private transient IHistoricoService historicoService;
  private transient ISubsanacionService subsanacionService;
  private transient ISolicitudService solicitudService;
  private transient IReservaService reservaService;
  private transient IGestionUsuariosService gestionUsuariosService;
  private transient IDocumentoExpedienteService documentoExpedienteService;
  
  private ListasExclusionesDTO listasExclusionesDTO;
  private DatosExclusionDTO datosExclusionDTO;

  public String cargarExclusionNoAdmisionTramitacion() {
    user = getUsuarioSesion();
    final List<Perfil> perfil = user.getListaPerfiles();
    final GregorianCalendar fechaCreacion = new GregorianCalendar();
    fechaCreacion.setTime(user.getExpediente().getFechaCreacion());
    final Long anio = (long) fechaCreacion.get(YEAR);

    final Map<Long, Object> mapaExclusiones = ConstantesTramitacionUNIV.obtenerMapaExclusionesUNIV();
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
        final Map<Long, Object> mapaExclusionesPosSub = ConstantesTramitacionUNIV.obtenerMapaExclusionesPosSubUNIV();
        @SuppressWarnings("unchecked")
        final Map<String, Long> mapaTipoExclusionSub = (Map<String, Long>) mapaExclusionesPosSub.get(EXCLUSION_TIPO_SUBSANACION);
        obtenerListasExclusionesTrasSubsanacion(perfil, anio, mapaTipoExclusionSub);
        obtenerExclusionesSolicitudTrasSubsanacion();
        datosExclusionDTO.setTipoExclusion(EXCLUSION_TIPO_SUBSANACION);
        datosExclusionDTO
            .setNoTieneExclusiones(exclusionService.noTieneExclusionesTipoExclusion(datosExclusionDTO.getIdSolicitud(), EXCLUSION_TIPO_SUBSANACION.intValue()));
      } else {
        final Map<Long, Object> mapaExclusiones = ConstantesTramitacionUNIV.obtenerMapaExclusionesUNIV();
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

    final Map<Long, Object> mapaExclusionesCausaDesestimiento = ConstantesTramitacionUNIV.obtenerMapaCausasDesestimiento();
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

  /**
   * obtenemos causas de desestimación
   *
   * @param perfil
   * @param anio
   * @param mapaTipoCausasDesest
   */
  private void obtenerListasCausasDesestimiento(final List<Perfil> perfil, final Long anio, final Map<String, Long> mapaTipoCausasDesest)
      throws TramitacionException {
    listasExclusionesDTO = new ListasExclusionesDTO();
    listasExclusionesDTO.setListaExclusionesComunes(exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_UNIV,
        ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_DESESTIMACION, mapaTipoCausasDesest.get(ConstantesTramitacionUNIV.CAUSAS_DESESTIMIENTO), anio, perfil));
  }

  @SuppressWarnings("unchecked")
  private void obtenerExclusionesSolicitudCausasDesestimacion() {
    final Map<String, Long> mapaTipoCausasDesestimacion = (Map<String, Long>) ConstantesTramitacionUNIV.obtenerMapaCausasDesestimiento()
        .get(ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_DESESTIMACION);
    datosExclusionDTO = new DatosExclusionDTO();
    try {
      final SolicitudDTO soliDTO = obtenerSolicitudDTO();
      datosExclusionDTO.setIdSolicitud(soliDTO.getIdSolicitud());
      datosExclusionDTO.setExclusionesComunesSeleccionadas(
          exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(), ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_DESESTIMACION,
              mapaTipoCausasDesestimacion.get(ConstantesTramitacionUNIV.CAUSAS_DESESTIMIENTO), user.getListaPerfiles()));
    } catch (final TramitacionException e) {
      log.error(e.getMessage());
    }
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
    addActionMessage(getText(EXITO_GUARDAR));
    return SUCCESS;
  }

  public String cargarExclusionCausasInadmisionPosPropProvConce() {
    user = getUsuarioSesion();
    final List<Perfil> perfil = user.getListaPerfiles();
    final GregorianCalendar fechaCreacion = new GregorianCalendar();
    fechaCreacion.setTime(user.getExpediente().getFechaCreacion());
    final Long anio = (long) fechaCreacion.get(YEAR);

    final Map<Long, Object> mapaExclusionesCausaInadmision = ConstantesTramitacionUNIV.obtenerMapaCausasInadmision();
    @SuppressWarnings("unchecked")
    final Map<String, Long> mapaTipoExclusionCausaInadmision = (Map<String, Long>) mapaExclusionesCausaInadmision
        .get(ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_INADMISION);
    try {
      obtenerListasCausasInadmision(perfil, anio, mapaTipoExclusionCausaInadmision);
      obtenerExclusionesSolicitudCausasInadmision();
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

  /**
   * obtenemos causas de inadmisión
   *
   * @param perfil
   * @param anio
   * @param mapaTipoCausasDesest
   */
  private void obtenerListasCausasInadmision(final List<Perfil> perfil, final Long anio, final Map<String, Long> mapaTipoCausasDesest)
      throws TramitacionException {
    listasExclusionesDTO = new ListasExclusionesDTO();
    listasExclusionesDTO.setListaExclusionesComunes(exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_UNIV,
        ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_INADMISION, mapaTipoCausasDesest.get(ConstantesTramitacion.CAUSA_CONCESION_DEFINITIVA), anio, perfil));
  }

  @SuppressWarnings("unchecked")
  private void obtenerExclusionesSolicitudCausasInadmision() {
    final Map<String, Long> mapaTipoCausasInadmision = (Map<String, Long>) ConstantesTramitacionUNIV.obtenerMapaCausasInadmision()
        .get(ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_INADMISION);
    datosExclusionDTO = new DatosExclusionDTO();
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
    addActionMessage(getText(EXITO_GUARDAR));
    return SUCCESS;
  }

  public String envioEmailDocumentoExclusionUniv() {
    user = getUsuarioSesion();

    try {
      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(user.getExpediente().getRefExpediente());

      envioEmailAutomaticoService.enviarEmail(ConstantesTramitacion.EXCLUSION, solicitudDTO.getEmail(), user.getExpediente().getRefExpediente(),
          ConstantesTramitacion.ETIQUETA_DOC_EXCL_PROV, null);
    } catch (final AddressException e) {
      log.error("La direccion para el envio de correo automatico con el documento de exclusion adjunto es incorrecta. ERROR:" + e.getMessage(), e);
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

  public String envioEmailDocumentoResolucionDesestimadasUniv() {
    user = getUsuarioSesion();
    try {
      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(user.getExpediente().getRefExpediente());
      envioEmailAutomaticoService.enviarEmail(ConstantesTramitacion.EXCLUSION, solicitudDTO.getEmail(), user.getExpediente().getRefExpediente(),
          ConstantesTramitacion.ETIQUETA_DOC_DES_UNIV, null);
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

  public String envioEmailDocumentoResolucionExclusionDefinitivaUniv() {
    user = getUsuarioSesion();
    try {
      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(user.getExpediente().getRefExpediente());
      envioEmailAutomaticoService.enviarEmail(ConstantesTramitacion.EXCLUSION, solicitudDTO.getEmail(), user.getExpediente().getRefExpediente(),
          ConstantesTramitacion.ETIQUETA_DOC_EXCL_DEF_UNIV, null);
    } catch (final AddressException e) {
      log.error("La direccion para el envio de correo automatico con el documento de exclusiones definitivas adjunto es incorrecta. ERROR:" + e.getMessage());
      addActionError(e.getMessage());
      return ERROR;
    } catch (final MessagingException e) {
      log.error("Error en el envio de correo automatico con el documento de exclusiones definitivas adjunto. Causa: " + e.getMessage(), e);
      addActionError(e.getMessage());
      return ERROR;
    } catch (final Exception ex) {
      log.error("Error en el envio de correo automatico con el documento de exclusiones definitivas adjunto. Causa: " + ex.getMessage(), ex);
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

        final Map<String, Object> datos = exclusionService.obtenerDatosDocumentoExclusiones(user, true, true, null, false,
            ConstantesTramitacion.FASE_RESOLUCION_DEFINITIVA_EXCLUSION);
        DocumentoExpedienteDTO doc = tareasService.generarInformes(datos, ConstantesTramitacion.PLANTILLA_EXCLUSION_DEFINITIVA_CONVOCATORIA_UNIV,
            ConstantesTramitacion.NOMBRE_INFORME_SALIDA_EXCLUSION_DEFINITIVA_CONVOCATORIA, ConstantesTramitacion.NOMBRE_DOC_IN_EXCL_DEF_CONV_UNIV,
            ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_EXCLUSION_DEFINITIVA_CONV_UNIV, user);

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

        final Map<String, Object> datos = exclusionService.obtenerDatosDocumentoExclusiones(user, true, true, null, false,
            ConstantesTramitacion.FASE_RESOLUCION_PROVISIONAL);
        DocumentoExpedienteDTO doc = tareasService.generarInformes(datos, ConstantesTramitacion.PLANTILLA_EXCLUSION_PROVISIONAL_CONVOCATORIA_UNIV,
            ConstantesTramitacion.NOMBRE_INFORME_SALIDA_EXCLUSION_PROVSIONAL_CONVOCATORIA, ConstantesTramitacion.NOMBRE_DOC_IN_EXCL_PROV_CONV_UNIV,
            ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_EXCLUSION_PROVISIONAL_CONV_UNIV, user);

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

        final Map<String, Object> datos = exclusionService.obtenerDatosDocumentoResolucionExcluidas(user, false, true, false);
        DocumentoExpedienteDTO doc = tareasService.generarInformes(datos, ConstantesTramitacion.PLANTILLA_EXCLUSION_DEFINITIVA_SOLICITUD_UNIV,
            ConstantesTramitacion.NOMBRE_INFORME_SALIDA_EXCLUSION_DEFINITIVA_SOLICITUD, ConstantesTramitacion.NOMBRE_DOC_IN_EXCL_DEF_UNIV,
            ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_EXCLUSION_DEFINITIVA_SOLI_UNIV, user);

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

        final Map<String, Object> datos = exclusionService.obtenerDatosDocumentoResolucionExcluidas(user, true, true, false);
        DocumentoExpedienteDTO doc = tareasService.generarInformes(datos, ConstantesTramitacion.PLANTILLA_EXCLUSION_PROVISIONAL_SOLICITUD_UNIV,
            ConstantesTramitacion.NOMBRE_INFORME_SALIDA_EXCLUSION_PROVSIONAL_SOLICITUD, ConstantesTramitacion.NOMBRE_DOC_IN_EXCL_PROV_SOLI_UNIV,
            ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_EXCLUSION_PROVISIONAL_SOLI_UNIV, user);

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

  public String generarResolucionDesestimadaUniv() {
    user = getUsuarioSesion();
    final ExecutorService service = Executors.newFixedThreadPool(1);
    service.submit(() -> {
      try {
        reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
        final IUsuario usuario = gestionUsuariosService.obtenerUsuario(ConstantesTramitacion.USUARIO_GENERACION_DOCUMENTO);
        reservaService.reservarExpediente(user.getExpediente(), usuario, ConstantesTramitacion.ESQUEMA_TREWA_AACID);

        final Map<String, Object> datos = exclusionService.obtenerDatosDocumentoResolucionDestimadas(user, false, true, false);
        DocumentoExpedienteDTO doc = tareasService.generarInformes(datos, ConstantesTramitacion.PLANTILLA_DESESTIMADA_DEFINITIVA_SOLICITUD_UNIV,
            ConstantesTramitacion.NOMBRE_INFORME_SALIDA_DESESTIMADA_DEFINITIVA_SOLICITUD_UNIV, ConstantesTramitacion.NOMBRE_DOC_IN_DESEST_DEF_UNIV,
            ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_DESEST_DEFINITIVA_SOLI_UNIV, user);

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

  public String guardarExclusionPosSubsanacion() {
    user = getUsuarioSesion();
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
    addActionMessage(getText(EXITO_GUARDAR));
    return SUCCESS;
  }

  public String guardarExclusionTramitacion() {
    user = getUsuarioSesion();
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
      addActionError("error.guardar");
      getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
      return ERROR;
    }
    addActionMessage(getText(EXITO_GUARDAR));
    return SUCCESS;
  }

  public String generarListaDesestimadasUniv() {
    user = getUsuarioSesion();

    try {
      final Map<String, Object> datos = exclusionService.obtenerDatosDocumentoExclusionesDesestimadas(user, true, true, null,
          ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_DESESTIMACION, ConstantesTramitacion.FASE_RESOLUCION_DESESTIMACION_PUNTUACION_REQUISITOS);

      DocumentoExpedienteDTO doc = tareasService.generarInformes(datos, ConstantesTramitacion.PLANTILLA_DESESTIMADA_CONVOCATORIA_UNIV,
          ConstantesTramitacion.NOMBRE_INFORME_SALIDA_DESESTIMADA_CONVOCATORIA, ConstantesTramitacion.NOMBRE_DOC_IN_DESEST_CONV_UNIV,
          ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_DESEST_CONV_UNIV, user);

      documentoExpedienteService.guardarDocumentoExpediente(doc);

    } catch (final Exception e) {
      addActionError(e.getMessage());
      log.error(e.getMessage(), e);
      return ERROR;
    }
    addActionMessage(MENSAJE_EXITO_GEN_INFORME_DESES);
    return SUCCESS;
  }

  /**
   *
   */
  @SuppressWarnings("unchecked")
  private void obtenerExclusionesSolicitud() {
    final Map<String, Long> mapaTipoExclusionAdmision = (Map<String, Long>) ConstantesTramitacionUNIV.obtenerMapaExclusionesUNIV().get(EXCLUSION_TIPO_ADMISION);
    datosExclusionDTO = new DatosExclusionDTO();
    try {
      final SolicitudDTO soliDTO = obtenerSolicitudDTO();
      datosExclusionDTO.setIdSolicitud(soliDTO.getIdSolicitud());
      datosExclusionDTO.setExclusionesComunesSeleccionadas(exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(),
          EXCLUSION_TIPO_ADMISION, mapaTipoExclusionAdmision.get(ConstantesTramitacionUNIV.COMUNES), user.getListaPerfiles()));
      datosExclusionDTO.setExclusionesPaisesPriorizadosSeleccionadas(exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(),
          EXCLUSION_TIPO_ADMISION, mapaTipoExclusionAdmision.get(ConstantesTramitacionUNIV.PAISES_PRIORIZADOS), user.getListaPerfiles()));
      datosExclusionDTO.setExclusionesAndaluciaSeleccionadas(exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(),
          EXCLUSION_TIPO_ADMISION, mapaTipoExclusionAdmision.get(ConstantesTramitacionUNIV.ANDALUCIA), user.getListaPerfiles()));
    } catch (final TramitacionException e) {
      log.error(e.getMessage());
    }
  }

  /**
   *
   */
  @SuppressWarnings("unchecked")
  private void obtenerExclusionesSolicitudTrasSubsanacion() {
    final Map<String, Long> mapaTipoExclusionPosSubsanacion = (Map<String, Long>) ConstantesTramitacionUNIV.obtenerMapaExclusionesPosSubUNIV()
        .get(EXCLUSION_TIPO_SUBSANACION);
    datosExclusionDTO = new DatosExclusionDTO();
    try {
      final SolicitudDTO soliDTO = obtenerSolicitudDTO();
      datosExclusionDTO.setIdSolicitud(soliDTO.getIdSolicitud());
      datosExclusionDTO.setExclusionesComunesSeleccionadas(exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(),
          EXCLUSION_TIPO_SUBSANACION, mapaTipoExclusionPosSubsanacion.get(ConstantesTramitacionUNIV.COMUNES_POS_SUB), user.getListaPerfiles()));
      datosExclusionDTO.setExclusionesProyCooperacionSeleccionadas(exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(),
          EXCLUSION_TIPO_SUBSANACION, mapaTipoExclusionPosSubsanacion.get(ConstantesTramitacionUNIV.PROY_COOP_UNIV), user.getListaPerfiles()));
      datosExclusionDTO.setExclusionesProyDesarrolloSeleccionadas(exclusionService.obtenerIdsExclusionesSolicitudByOrdenTipo(soliDTO.getIdSolicitud(),
          EXCLUSION_TIPO_SUBSANACION, mapaTipoExclusionPosSubsanacion.get(ConstantesTramitacionUNIV.PROY_COOP_DES), user.getListaPerfiles()));

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
    listasExclusionesDTO.setListaExclusionesComunes(exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_UNIV,
        EXCLUSION_TIPO_ADMISION, mapaTipoExclusionPreSub.get(ConstantesTramitacionUNIV.COMUNES), anio, perfil));
    listasExclusionesDTO.setListaExclusionesPaisesPriorizados(exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_UNIV,
        EXCLUSION_TIPO_ADMISION, mapaTipoExclusionPreSub.get(ConstantesTramitacionUNIV.PAISES_PRIORIZADOS), anio, perfil));
    listasExclusionesDTO.setListaExclusionesAndalucia(exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_UNIV,
        EXCLUSION_TIPO_ADMISION, mapaTipoExclusionPreSub.get(ConstantesTramitacionUNIV.ANDALUCIA), anio, perfil));
  }

  /**
   * @param perfil
   * @param anio
   * @param mapaTipoExclusionPreSub
   */
  private void obtenerListasExclusionesTrasSubsanacion(final List<Perfil> perfil, final Long anio, final Map<String, Long> mapaTipoExclusionTrasSub)
      throws TramitacionException {
    listasExclusionesDTO = new ListasExclusionesDTO();
    listasExclusionesDTO.setListaExclusionesComunes(exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_UNIV,
        EXCLUSION_TIPO_SUBSANACION, mapaTipoExclusionTrasSub.get(ConstantesTramitacionUNIV.COMUNES_POS_SUB), anio, perfil));
    listasExclusionesDTO.setListaExclusionesProyectosCooperacion(exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_UNIV,
        EXCLUSION_TIPO_SUBSANACION, mapaTipoExclusionTrasSub.get(ConstantesTramitacionUNIV.PROY_COOP_UNIV), anio, perfil));
    listasExclusionesDTO.setListaExclusionesProyectosDesarrolloInnov(exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_UNIV,
        EXCLUSION_TIPO_SUBSANACION, mapaTipoExclusionTrasSub.get(ConstantesTramitacionUNIV.PROY_COOP_DES), anio, perfil));
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

  public String valorarSubsanacion() {
    user = getUsuarioSesion();
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

  /**
   * Obtiene la propiedad listasExclusionesDTO
   *
   * @return el listasExclusionesDTO
   */
  public ListasExclusionesDTO getListasExclusionesDTO() {
    return listasExclusionesDTO;
  }

  public void setDocumentoExpedienteService(IDocumentoExpedienteService documentoExpedienteService) {
    this.documentoExpedienteService = documentoExpedienteService;
  }

}
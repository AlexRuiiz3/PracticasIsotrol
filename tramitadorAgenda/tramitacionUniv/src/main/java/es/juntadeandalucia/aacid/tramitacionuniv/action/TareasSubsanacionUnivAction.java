package es.juntadeandalucia.aacid.tramitacionuniv.action;

import static java.util.Calendar.YEAR;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;

import es.juntadeandalucia.aacid.comuntramitacion.action.BaseTareasAction;
import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoSubsanacionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.DatosSubsanacionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.DocumentoExpedienteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SubsanacionSeleccionadaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.TipoCatalogoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.IEnvioEmailAutomaticoService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ICatalogoService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ICatalogoSubsanacionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IDocumentoExpedienteService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IHistoricoService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISubsanacionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ITipoCatalogoService;
import es.juntadeandalucia.plataforma.comunes.excepciones.BusinessException;
import es.juntadeandalucia.plataforma.service.reserva.IReservaService;
import es.juntadeandalucia.plataforma.service.tramitacion.IGestionUsuariosService;
import es.juntadeandalucia.plataforma.service.usuarios.IUsuario;

/**
 * Action para el control de las tareas de subsanaciones
 *
 * @author Isotrol
 *
 */
public class TareasSubsanacionUnivAction extends BaseTareasAction {

  /**
   *
   */
  private static final long serialVersionUID = -7740534787286082237L;

  /** LOGGER TareasSubsanacionAction.class */
  private final transient Logger log = Logger.getLogger(TareasSubsanacionUnivAction.class);

  /**
   * Datos de subsanación.
   */
  private DatosSubsanacionDTO datos;
  private transient List<TipoCatalogoDTO> listaTipoCatalogo;
  private String tipoCatalogoSeleccionado;
  private transient List<CatalogoDTO> listaCatalogo;
  private transient List<CatalogoSubsanacionDTO> catalogosSeleccionados;

  private transient ITipoCatalogoService tipoCatalogoService;
  private transient ICatalogoService catalogoService;
  private transient ICatalogoSubsanacionService catalogoSubsanacionService;
  private transient ISubsanacionService subsanacionService;
  private transient IHistoricoService historicoService;
  private transient IEnvioEmailAutomaticoService envioEmailAutomaticoService;
  private transient ISolicitudService solicitudService;
  private transient IReservaService reservaService;
  private transient IGestionUsuariosService gestionUsuariosService;
  private transient IDocumentoExpedienteService documentoExpedienteService;

  public String valorarSubsanacion() {
    try {
      user = getUsuarioSesion();

      datos = subsanacionService.getSubsanacionByNumExpediente(Long.parseLong(user.getExpediente().getRefExpediente()), getUsuarioSesion().getListaPerfiles());

      if (datos == null) {
        datos = new DatosSubsanacionDTO();
      }

      datos.setIdExpTrewa(user.getExpediente().getRefExpediente());
      datos.setIdExp(user.getExpediente().getNumeroExpediente());
      datos.setTituloProy(user.getExpediente().getTitulo());
    } catch (final TramitacionException e) {
      errores = getText("error.cargar");
      log.error(errores, e);
      addActionError(errores);
    }

    return SUCCESS;

  }

  public String generarSubsanacionSolicitud() {
    try {
      user = getUsuarioSesion();
      final Map<String, Object> datosVariables = subsanacionService.obtenerDatosDocumento(user);

      DocumentoExpedienteDTO doc = tareasService.generarInformes(datosVariables, ConstantesTramitacion.PLANTILLA_SUBSANACION_SOLICITUD_UNIV,
          ConstantesTramitacion.NOMBRE_INFORME_SALIDA_SUBSANACION_SOLICITUD, ConstantesTramitacion.NOMBRE_DOC_IN_SUB_SOLI_UNIV,
          ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_SUBSANACION_SOLI_UNIV, user);

      documentoExpedienteService.guardarDocumentoExpediente(doc);

    } catch (final Exception e) {
      addActionError(e.getMessage());
      log.error(e.getMessage(), e);
      return ERROR;
    }
    addActionMessage(ConstantesTramitacion.MENSAJE_DOCUMENTO_SUBSANACION_GENERADO);
    return SUCCESS;
  }

  public String buscarSolicitudesSeleccionadas() {
    try {
      final Long idSubsanacion = subsanacionService.existeSubsanacion(Long.parseLong(getUsuarioSesion().getExpediente().getRefExpediente()));
      if (idSubsanacion != null) {
        catalogosSeleccionados = catalogoSubsanacionService.obtenerCatalogoSubsanacionByIdSubsanacion(idSubsanacion.intValue(),
            getUsuarioSesion().getListaPerfiles());
      }
    } catch (final TramitacionException e) {
      log.error("Error al obtener las solicitudes seleccionadas", e);
      return ERROR;
    }
    return SUCCESS;
  }

  public String buscarTipoCatalogo() {
    try {
      final GregorianCalendar fechaCreacion = new GregorianCalendar();
      fechaCreacion.setTime(getUsuarioSesion().getExpediente().getFechaCreacion());
      final Integer anio = fechaCreacion.get(YEAR);

      listaTipoCatalogo = tipoCatalogoService.obtenerCatalogosPorAnio(anio, ConstantesTramitacion.TIPO_CONV_UNIV);

    } catch (final TramitacionException e) {
      log.error("Error al obtener los tipos de catalogo", e);
      return ERROR;
    }

    return SUCCESS;
  }

  public String buscarCatalogo() {

    try {
      log.info(tipoCatalogoSeleccionado);
      listaCatalogo = catalogoService.obtenerCatalogoPorTipo(Integer.parseInt(tipoCatalogoSeleccionado), getUsuarioSesion().getListaPerfiles());

    } catch (final TramitacionException e) {
      log.error("Error al obtener los catálogos", e);
      return ERROR;
    }

    return SUCCESS;
  }

  public String guardarSubsanacionesValorador() {
    try {
      final String motivo = request.getParameter("motivoDesestimacion");
      final String observaciones = request.getParameter("observaciones");
      final List<SubsanacionSeleccionadaDTO> listadoSubsanaciones = new ArrayList<>();
      int i = 0;

      DatosSubsanacionDTO subsanacionGuardar = subsanacionService
          .getSubsanacionByNumExpediente(Long.parseLong(getUsuarioSesion().getExpediente().getRefExpediente()), getUsuarioSesion().getListaPerfiles());
      while (request.getParameterMap().containsKey("listadoSubsanaciones[" + i + "][codigo]")) {

        listadoSubsanaciones.add(new SubsanacionSeleccionadaDTO(request.getParameter("listadoSubsanaciones[" + i + "][codigo]"),
            request.getParameter("listadoSubsanaciones[" + i + "][motivo]")));
        i++;
      }

      final GregorianCalendar fechaCreacion = new GregorianCalendar();
      fechaCreacion.setTime(getUsuarioSesion().getExpediente().getFechaCreacion());
      final Integer anio = fechaCreacion.get(YEAR);

      if (subsanacionGuardar != null) {
        subsanacionService.deleteSubsanacion(subsanacionGuardar.getIdSubsanacion());
      } else {
        subsanacionGuardar = new DatosSubsanacionDTO();
      }
      subsanacionGuardar.setObservacionesDes(motivo);
      subsanacionGuardar.setObservaciones(observaciones);
      subsanacionService.guardarSubsanacion(subsanacionGuardar, getUsuarioSesion().getExpediente().getRefExpediente(), listadoSubsanaciones,
          ConstantesTramitacion.TIPO_CONV_UNIV, anio);

      historicoService.guardarHistorico(getUsuarioSesion().getExpediente().getNumeroExpediente(), getUsuarioSesion().getNombreUsuario());

      addActionMessage(getText("exito.guardar"));
      return SUCCESS;
    } catch (final TramitacionException e) {
      addActionError("error.guardar");
      return ERROR;
    }
  }

  public String generarListadoSubsanaciones() {
    user = getUsuarioSesion();
    final ExecutorService service = Executors.newFixedThreadPool(1);
    service.submit(() -> {
      try {
        reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
        final IUsuario usuario = gestionUsuariosService.obtenerUsuario(ConstantesTramitacion.USUARIO_GENERACION_DOCUMENTO);
        reservaService.reservarExpediente(user.getExpediente(), usuario, ConstantesTramitacion.ESQUEMA_TREWA_AACID);

        final Map<String, Object> datosVariables = subsanacionService.obtenerDatosDocumentoSubsanaciones(user, true);
        DocumentoExpedienteDTO doc = tareasService.generarInformes(datosVariables, ConstantesTramitacion.PLANTILLA_SUBSANACION_CONVOCATORIA_UNIV,
            ConstantesTramitacion.NOMBRE_INFORME_SALIDA_SUBSANACION_CONVOCATORIA, ConstantesTramitacion.NOMBRE_DOC_IN_SUB_CONV_UNIV,
            ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_SUBSANACION_CONV_UNIV, user);

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

  public String generarListadoSubsanacionesAle() {
    user = getUsuarioSesion();
    final ExecutorService service = Executors.newFixedThreadPool(1);
    service.submit(() -> {
      try {
        reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
        final IUsuario usuario = gestionUsuariosService.obtenerUsuario(ConstantesTramitacion.USUARIO_GENERACION_DOCUMENTO);
        reservaService.reservarExpediente(user.getExpediente(), usuario, ConstantesTramitacion.ESQUEMA_TREWA_AACID);
        final Map<String, Object> datosVariables = subsanacionService.obtenerDatosDocumentoSubsanaciones(user, true);

        DocumentoExpedienteDTO doc = tareasService.generarInformes(datosVariables, ConstantesTramitacion.PLANTILLA_SUBSANACION_CONVOCATORIA_VUELTA_UNIV,
            ConstantesTramitacion.NOMBRE_INFORME_SALIDA_SUBSANACION_CONVOCATORIA, ConstantesTramitacion.NOMBRE_DOC_IN_SUB_CONV_UNIV,
            ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_SUBSANACION_CONV_UNIV_ALE, user);

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

  public String envioEmailDocumentoSubsanacionUniv() {
    user = getUsuarioSesion();
    try {
      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(user.getExpediente().getRefExpediente());
      envioEmailAutomaticoService.enviarEmail(ConstantesTramitacion.SUBSANACION, solicitudDTO.getEmail(), user.getExpediente().getRefExpediente(),
          ConstantesTramitacion.ETIQUETA_DOC_SUB_SOLI, null);
    } catch (final AddressException e) {
      log.error("La direccion para el envio de correo automatico con el documento de subsanacion adjunto es incorrecta. ERROR:" + e.getMessage(), e);
      addActionError(e.getMessage());
      return ERROR;
    } catch (final MessagingException e) {
      log.error("Error en el envio de correo automatico con el documento de subsanacion adjunto. Causa: " + e.getMessage(), e);
      addActionError(e.getMessage());
      return ERROR;
    } catch (final Exception ex) {
      log.error("Error en el envio de correo automatico con el documento de subsanacion adjunto. Causa: " + ex.getMessage(), ex);
      addActionError(ex.getMessage());
      return ERROR;
    }
    addActionMessage(getText("exito.enviar.email"));
    return SUCCESS;
  }

  public String cargarListadoSolicitudesSubsanacionesDocumentacionUniv() {
    try {
      user = getUsuarioSesion();
      final ExecutorService service = Executors.newFixedThreadPool(1);

      service.submit(() -> {
        try {
          reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
          final IUsuario usuario = gestionUsuariosService.obtenerUsuario(ConstantesTramitacion.USUARIO_GENERACION_DOCUMENTO);
          reservaService.reservarExpediente(user.getExpediente(), usuario, ConstantesTramitacion.ESQUEMA_TREWA_AACID);

          final Map<String, Object> datosVariables = subsanacionService.obtenerDatosDocumentoListadoSubsanacionesDocumentacion(user, true);
          tareasService.generarInformes(datosVariables, ConstantesTramitacion.PLANTILLA_LISTADO_SUBSANACION_DOCUMENTOS_CONVOCATORIA_UNIV,
              ConstantesTramitacion.NOMBRE_INFORME_SALIDA_SUBSANACION_DOCUMENTACION_CONVOCATORIA_UNIV,
              ConstantesTramitacion.NOMBRE_DOC_LIST_SUBSANACIONES_DOCUMENTACION_UNIV,
              ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_SUBSANACION_DOCUMENTACION_CONV_UNIV, user);
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
    } catch (final Exception e) {
      addActionError(e.getMessage());
      log.error(e.getMessage(), e);
      return ERROR;
    }
  }

  public String cargarDocumentoSubsanacionDocumentacionAportadaUniv() {
    try {
      user = getUsuarioSesion();
      final ExecutorService service = Executors.newFixedThreadPool(1);

      service.submit(() -> {
        try {
          reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
          final IUsuario usuario = gestionUsuariosService.obtenerUsuario(ConstantesTramitacion.USUARIO_GENERACION_DOCUMENTO);
          reservaService.reservarExpediente(user.getExpediente(), usuario, ConstantesTramitacion.ESQUEMA_TREWA_AACID);

          final Map<String, Object> datosVariables = subsanacionService.obtenerDatosDocumento(user);

          tareasService.generarInformes(datosVariables, ConstantesTramitacion.PLANTILLA_SUBSANACION_DOCUMENTACION_APORTADA_SOLICITUD_UNIV,
              ConstantesTramitacion.NOMBRE_INFORME_SALIDA_SUBSANACION_DOCUMENTACION_CONVOCATORIA_UNIV,
              ConstantesTramitacion.NOMBRE_DOC_IN_SUB_DOC_APORT_SOLI_UNIV, ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_SUBSANACION_DOCUMENTACION_UNIV,
              user);

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
    } catch (final Exception e) {
      addActionError(e.getMessage());
      log.error(e.getMessage(), e);
      return ERROR;
    }
  }

  public DatosSubsanacionDTO getDatos() {
    return datos;
  }

  public void setDatos(final DatosSubsanacionDTO datos) {
    this.datos = datos;
  }

  public List<TipoCatalogoDTO> getListaTipoCatalogo() {
    return listaTipoCatalogo;
  }

  public void setListaTipoCatalogo(final List<TipoCatalogoDTO> listaTipoCatalogo) {
    this.listaTipoCatalogo = listaTipoCatalogo;
  }

  public void setTipoCatalogoSeleccionado(final String tipoCatalogoSeleccionado) {
    this.tipoCatalogoSeleccionado = tipoCatalogoSeleccionado;
  }

  public void setTipoCatalogoService(final ITipoCatalogoService tipoCatalogoService) {
    this.tipoCatalogoService = tipoCatalogoService;
  }

  public void setCatalogoService(final ICatalogoService catalogoService) {
    this.catalogoService = catalogoService;
  }

  public List<CatalogoDTO> getListaCatalogo() {
    return listaCatalogo;
  }

  public void setListaCatalogo(final List<CatalogoDTO> listaCatalogo) {
    this.listaCatalogo = listaCatalogo;
  }

  public void setSubsanacionService(final ISubsanacionService subsanacionService) {
    this.subsanacionService = subsanacionService;
  }

  public void setCatalogoSubsanacionService(final ICatalogoSubsanacionService catalogoSubsanacionService) {
    this.catalogoSubsanacionService = catalogoSubsanacionService;
  }

  public List<CatalogoSubsanacionDTO> getCatalogosSeleccionados() {
    return catalogosSeleccionados;
  }

  public void setCatalogosSeleccionados(final List<CatalogoSubsanacionDTO> catalogosSeleccionados) {
    this.catalogosSeleccionados = catalogosSeleccionados;
  }

  public void setHistoricoService(final IHistoricoService historicoService) {
    this.historicoService = historicoService;
  }

  public void setEnvioEmailAutomaticoService(final IEnvioEmailAutomaticoService envioEmailAutomaticoService) {
    this.envioEmailAutomaticoService = envioEmailAutomaticoService;
  }

  public void setSolicitudService(final ISolicitudService solicitudService) {
    this.solicitudService = solicitudService;
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
}
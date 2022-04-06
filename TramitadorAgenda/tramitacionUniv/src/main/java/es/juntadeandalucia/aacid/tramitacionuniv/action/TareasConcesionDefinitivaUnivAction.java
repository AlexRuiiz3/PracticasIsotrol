package es.juntadeandalucia.aacid.tramitacionuniv.action;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;

import es.juntadeandalucia.aacid.comuntramitacion.action.BaseTareasAction;
import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.DocumentoExpedienteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.service.IEnvioEmailAutomaticoService;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITareasService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IConcesionDefinitivaService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IDocumentoExpedienteService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudService;
import es.juntadeandalucia.plataforma.comunes.excepciones.BusinessException;
import es.juntadeandalucia.plataforma.service.reserva.IReservaService;
import es.juntadeandalucia.plataforma.service.tramitacion.IGestionUsuariosService;
import es.juntadeandalucia.plataforma.service.usuarios.IUsuario;

public class TareasConcesionDefinitivaUnivAction extends BaseTareasAction {
  /**
    *
    */
  private static final long serialVersionUID = -7740534787286082237L;
  private final transient Logger log = Logger.getLogger(getClass());

  private transient IConcesionDefinitivaService concesionDefinitivaService;

  private static final String MENSAJE_EXITO_GEN_INFORME_CONCESION_DEFINITIVA = "Se ha generado correctamente el informe de concesi\u00F3n definitiva";
  private transient IEnvioEmailAutomaticoService envioEmailAutomaticoService;
  private transient ISolicitudService solicitudService;
  private transient IReservaService reservaService;
  private transient IGestionUsuariosService gestionUsuariosService;
  private transient IDocumentoExpedienteService documentoExpedienteService;

  public String generarDocumentoListadoResolucionDefinitivaUniv() {
    user = getUsuarioSesion();
    final ExecutorService service = Executors.newFixedThreadPool(1);
    service.submit(() -> {
      try {
        reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
        final IUsuario usuario = gestionUsuariosService.obtenerUsuario(ConstantesTramitacion.USUARIO_GENERACION_DOCUMENTO);
        reservaService.reservarExpediente(user.getExpediente(), usuario, ConstantesTramitacion.ESQUEMA_TREWA_AACID);

        final Map<String, Object> datos = concesionDefinitivaService.generarListadoConcesionDefinitiva(user, true);
        DocumentoExpedienteDTO doc = tareasService.generarInformes(datos, ConstantesTramitacion.PLANTILLA_LISTADO_CONCESION_DEFINITIVA_UNIV,
            ConstantesTramitacion.NOMBRE_INFORME_SALIDA_LISTADO_CONCESION_DEFINITIVA_UNIV, ConstantesTramitacion.NOMBRE_DOC_LIST_RESOLUCION_DEFINITIVA_UNIV,
            ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_LISTADO_CONCESION_DEFINITIVA_UNIV, user);

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

  public String generarDocumentoResolucionDefinitivaUniv() {
    user = getUsuarioSesion();
    try {
      final Map<String, Object> datos = concesionDefinitivaService.generarConcesionDefinitiva(user, true);

      DocumentoExpedienteDTO doc = tareasService.generarInformes(datos, ConstantesTramitacion.PLANTILLA_RELACION_CONCESION_DEFINITIVA_UNIV,
          ConstantesTramitacion.NOMBRE_INFORME_SALIDA_CONCESION_DEFINITIVA_UNIV, ConstantesTramitacion.NOMBRE_DOC_RESOLUCION_DEFINITIVA_UNIV,
          ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_CONCESION_DEFINITIVA_UNIV, user);

      documentoExpedienteService.guardarDocumentoExpediente(doc);

    } catch (final Exception e) {
      addActionError(e.getMessage());
      log.error(e.getMessage(), e);
      return ERROR;
    }
    addActionMessage(MENSAJE_EXITO_GEN_INFORME_CONCESION_DEFINITIVA);
    return SUCCESS;
  }

  public String envioEmailDocumentoConcesionDefinitivaUniv() {
    user = getUsuarioSesion();
    try {
      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(user.getExpediente().getRefExpediente());
      envioEmailAutomaticoService.enviarEmail(ConstantesTramitacion.CAUSA_CONCESION_DEFINITIVA, solicitudDTO.getEmail(),
          user.getExpediente().getRefExpediente(), ConstantesTramitacion.ETIQUETA_DOC_CON_DEF_UNIV, null);

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

  public void setConcesionDefinitivaService(final IConcesionDefinitivaService concesionDefinitivaService) {
    this.concesionDefinitivaService = concesionDefinitivaService;
  }

  public void setEnvioEmailAutomaticoService(final IEnvioEmailAutomaticoService envioEmailAutomaticoService) {
    this.envioEmailAutomaticoService = envioEmailAutomaticoService;
  }

  public void setSolicitudService(final ISolicitudService solicitudService) {
    this.solicitudService = solicitudService;
  }

  @Override
  public void setTareasService(final ITareasService tareasService) {
    this.tareasService = tareasService;
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

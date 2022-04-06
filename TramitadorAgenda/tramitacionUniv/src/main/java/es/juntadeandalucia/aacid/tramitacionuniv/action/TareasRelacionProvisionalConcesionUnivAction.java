package es.juntadeandalucia.aacid.tramitacionuniv.action;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import es.juntadeandalucia.aacid.comuntramitacion.action.BaseTareasAction;
import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.DocumentoExpedienteDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IDocumentoExpedienteService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IRelacionProvisionalService;
import es.juntadeandalucia.plataforma.comunes.excepciones.BusinessException;
import es.juntadeandalucia.plataforma.service.reserva.IReservaService;
import es.juntadeandalucia.plataforma.service.tramitacion.IGestionUsuariosService;
import es.juntadeandalucia.plataforma.service.usuarios.IUsuario;

public class TareasRelacionProvisionalConcesionUnivAction extends BaseTareasAction {

  private static final String MENSAJE_EXITO_GEN_INFORME_REL_PROV = "Se ha generado correctamente el informe de relaci\u00F3n provisional de beneficiarias y suplentes";
  private static final String MENSAJE_EXITO_GEN_INFORME_LIST_REL_PROV = "Se ha generado correctamente el informe de relaci\u00F3n provisional de beneficiarias y suplentes";

  private transient IRelacionProvisionalService relacionProvisionalService;
  private transient IReservaService reservaService;
  private transient IGestionUsuariosService gestionUsuariosService;

  private static final long serialVersionUID = -7740534787286082237L;
  private final transient Logger log = Logger.getLogger(getClass());
  private transient IDocumentoExpedienteService documentoExpedienteService;

  public String generarDocumentoListadoBeneficiariasSuplentesUniv() {
    user = getUsuarioSesion();
    ExecutorService service = Executors.newFixedThreadPool(1);
    service.submit(new Runnable() {
      @Override
      public void run() {
        try {
          reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
          IUsuario usuario = gestionUsuariosService.obtenerUsuario(ConstantesTramitacion.USUARIO_GENERACION_DOCUMENTO);
          reservaService.reservarExpediente(user.getExpediente(), usuario, ConstantesTramitacion.ESQUEMA_TREWA_AACID);
          final Map<String, Object> datos = relacionProvisionalService.generarRelacionProvisional(user, false, true, false);

      DocumentoExpedienteDTO doc = tareasService.generarInformes(datos, ConstantesTramitacion.PLANTILLA_RELACION_PROPUESTA_PROVISIONAL_UNIV,
          ConstantesTramitacion.NOMBRE_INFORME_SALIDA_PROPUESTA_PROVISIONAL_UNIV, ConstantesTramitacion.NOMBRE_DOC_LISTA_PROPUESTA_PROVISIONAL_BEN_SUP_UNIV,
          ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_PROPUESTA_PROVISIONAL_UNIV, user);

      documentoExpedienteService.guardarDocumentoExpediente(doc);
        } catch (final Exception e) {
          addActionError(e.getMessage());
          log.error(e.getMessage(), e);
        } finally {
          try {
            reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
          } catch (BusinessException e) {
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
          }
        }
      }
    });

    addActionMessage(MENSAJE_EXITO_GEN_INFORME_REL_PROV);
    return SUCCESS;
  }

  public String generarDocumentoBeneficiariasSuplentesUniv() {
    user = getUsuarioSesion();
    ExecutorService service = Executors.newFixedThreadPool(1);
    service.submit(new Runnable() {
      @Override
      public void run() {
        try {
          reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
          IUsuario usuario = gestionUsuariosService.obtenerUsuario(ConstantesTramitacion.USUARIO_GENERACION_DOCUMENTO);
          reservaService.reservarExpediente(user.getExpediente(), usuario, ConstantesTramitacion.ESQUEMA_TREWA_AACID);

          final Map<String, Object> datos = relacionProvisionalService.generarPropuestaBeneficiario(user, false, true, true);
          DocumentoExpedienteDTO doc = tareasService.generarInformes(datos, ConstantesTramitacion.PLANTILLA_PROPUESTA_BENEFICIARIO_UNIV,
              ConstantesTramitacion.NOMBRE_INFORME_SALIDA_PROPUESTA_BENEFICIARIO_UNIV, ConstantesTramitacion.NOMBRE_DOC_IN_PROP_BENF_UNIV,
              ConstantesTramitacion.NOMBRE_TAREA_GENERAR_INFORME_PROPUESTA_BENEFICIARIO_UNIV, user);

          documentoExpedienteService.guardarDocumentoExpediente(doc);

        } catch (final Exception e) {
          addActionError(e.getMessage());
          log.error(e.getMessage(), e);
        } finally {
          try {
            reservaService.anularReserva(user.getExpediente(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
          } catch (BusinessException e) {
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
          }
        }
      }
    });
    addActionMessage(ConstantesTramitacion.MENSAJE_DOCUMENTO_GENERANDO);
    return SUCCESS;
  }

  public void setRelacionProvisionalService(final IRelacionProvisionalService relacionProvisionalServiceImpl) {
    this.relacionProvisionalService = relacionProvisionalServiceImpl;
  }

  public void setReservaService(IReservaService reservaService) {
    this.reservaService = reservaService;
  }

  public void setGestionUsuariosService(IGestionUsuariosService gestionUsuariosService) {
    this.gestionUsuariosService = gestionUsuariosService;
  }

  public void setDocumentoExpedienteService(IDocumentoExpedienteService documentoExpedienteService) {
    this.documentoExpedienteService = documentoExpedienteService;
  }
}

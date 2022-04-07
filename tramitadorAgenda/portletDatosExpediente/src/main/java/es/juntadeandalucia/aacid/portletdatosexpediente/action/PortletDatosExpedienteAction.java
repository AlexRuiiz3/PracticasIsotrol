package es.juntadeandalucia.aacid.portletdatosexpediente.action;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.Action;

import es.juntadeandalucia.aacid.comuntramitacion.action.BaseTareasAction;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesFecha;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudService;
import es.juntadeandalucia.aacid.portletdatosexpediente.dto.PortletDatosExpedienteDTO;
import es.juntadeandalucia.aacid.portletdatosexpediente.exception.PortletDatosExpedienteException;
import es.juntadeandalucia.aacid.portletdatosexpediente.services.IPortletDatosExpedienteService;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;

/**
 * Controlador del portlet
 *
 * @author Isotrol
 */

public class PortletDatosExpedienteAction extends BaseTareasAction {
  /**
   *
   */
  private static final long serialVersionUID = 5306660291672280763L;

  /** Objeto con la informacion del expediente */
  PortletDatosExpedienteDTO portletDatosExpedienteDTO;

  /** Servicio de acceso a la información de los datos del expedientes */
  IPortletDatosExpedienteService portletDatosExpedienteService;
  /** Servicio para trabajar con las solicitudes */
  ISolicitudService solicitudService;
  /** Servicio consultas trewa */
  ITrewaService trewaService;

  public String getDatosExpediente() {
    try {
      final UsuarioWeb usuario = getUsuarioSesion();
      portletDatosExpedienteDTO = new PortletDatosExpedienteDTO();
      portletDatosExpedienteDTO.setCodUsuario(usuario.getUsuario().getCodUsuario());
      portletDatosExpedienteDTO.setNumeroExpediente(usuario.getExpediente().getNumeroExpediente());
      portletDatosExpedienteDTO.setIdExpediente(Long.parseLong(usuario.getExpediente().getRefExpediente()));

      final TrExpediente expediente = trewaService.obtenerExpedienteTrewa(portletDatosExpedienteDTO.getIdExpediente().toString());
      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(portletDatosExpedienteDTO.getIdExpediente().toString());

      if (expediente.getFECHAALTA() != null) {
        portletDatosExpedienteDTO.setFechaComputo(UtilidadesFecha.parseDateToString(expediente.getFECHAALTA()));
      } else {
        portletDatosExpedienteDTO.setFechaComputo("-");
      }
      portletDatosExpedienteDTO.setDesUnidadOrganica(expediente.getORGANISMO().getDESCRIPCION());
      portletDatosExpedienteDTO.setDesProcedimiento(expediente.getDEFPROC().getDESCRIPCION());
      if (solicitudDTO != null) {
        portletDatosExpedienteDTO.setTitulo(solicitudDTO.getTitulo());
        portletDatosExpedienteDTO.setEntidad(solicitudDTO.getEntidad());
        portletDatosExpedienteDTO.setNumAACID(solicitudDTO.getCodIdentificativo());
      } else {
        portletDatosExpedienteDTO.setTitulo(expediente.getTITULOEXP());
        portletDatosExpedienteDTO.setEntidad(StringUtils.EMPTY);
        portletDatosExpedienteDTO.setNumAACID(StringUtils.EMPTY);

      }

      portletDatosExpedienteDTO.setCaducado(portletDatosExpedienteService.expedienteCaducado(portletDatosExpedienteDTO));
      portletDatosExpedienteDTO.setBloqueoDTO(portletDatosExpedienteService.getInformacionBloqueo(usuario));
      if (portletDatosExpedienteDTO.getBloqueoDTO().getCodigoUsuario() != null
          && portletDatosExpedienteDTO.getCodUsuario().equals(portletDatosExpedienteDTO.getBloqueoDTO().getCodigoUsuario())) {
        portletDatosExpedienteDTO.setUsuSesionEsUsuBloqueo(true);
      }
      return Action.SUCCESS;

    } catch (final PortletDatosExpedienteException | TramitacionException e) {
      LOG.error(e);
      addActionError("Se ha producido un error al recuperar los datos del expediente");
      return ERROR;
    }

  }

  /**
   * Obtiene la propiedad portletDatosExpedienteDto
   *
   * @return el portletDatosExpedienteDto
   */
  public PortletDatosExpedienteDTO getPortletDatosExpedienteDTO() {
    return portletDatosExpedienteDTO;
  }

  /**
   * Establece el valor de la propiedad portletDatosExpedienteDto
   *
   * @param portletDatosExpedienteDto
   *          el portletDatosExpedienteDto para establecer
   */
  public void setPortletDatosExpedienteDTO(final PortletDatosExpedienteDTO portletDatosExpedienteDto) {
    portletDatosExpedienteDTO = portletDatosExpedienteDto;
  }

  /**
   * Establece el valor de la propiedad portletDatosExpedienteService
   *
   * @param portletDatosExpedienteService
   *          el portletDatosExpedienteService para establecer
   */
  public void setPortletDatosExpedienteService(final IPortletDatosExpedienteService portletDatosExpedienteService) {
    this.portletDatosExpedienteService = portletDatosExpedienteService;
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
   * Establece el valor de la propiedad trewaService
   *
   * @param trewaService
   *          el trewaService para establecer
   */
  public void setTrewaService(final ITrewaService trewaService) {
    this.trewaService = trewaService;
  }

}

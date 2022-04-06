package es.juntadeandalucia.aacid.portletdatosexpediente.services;

import java.io.Serializable;

import es.juntadeandalucia.aacid.portletdatosexpediente.dto.BloqueoDTO;
import es.juntadeandalucia.aacid.portletdatosexpediente.dto.PortletDatosExpedienteDTO;
import es.juntadeandalucia.aacid.portletdatosexpediente.exception.PortletDatosExpedienteException;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;

/**
 * Interfaz del servicio de portlet de datos del expediente.
 *
 * @author Isotrol
 */
public interface IPortletDatosExpedienteService extends Serializable {

  /**
   * Datos del bloqueo del expediente
   *
   * @param usuario
   * @return BloqueoDTO
   * @throws PortletDatosExpedienteException
   */
  BloqueoDTO getInformacionBloqueo(UsuarioWeb usuario) throws PortletDatosExpedienteException;

  /**
   * Obtiene si el expediente esta caducado
   *
   * @param portletDatosExpedienteDto
   * @return boolean si el expediente esta caducado
   */
  boolean expedienteCaducado(PortletDatosExpedienteDTO portletDatosExpedienteDto) throws PortletDatosExpedienteException;

}

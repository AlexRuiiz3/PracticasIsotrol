
package es.juntadeandalucia.aacid.portletdatosexpediente.services.impl;

import org.apache.commons.lang.ArrayUtils;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.aacid.portletdatosexpediente.dto.BloqueoDTO;
import es.juntadeandalucia.aacid.portletdatosexpediente.dto.PortletDatosExpedienteDTO;
import es.juntadeandalucia.aacid.portletdatosexpediente.exception.PortletDatosExpedienteException;
import es.juntadeandalucia.aacid.portletdatosexpediente.services.IPortletDatosExpedienteService;
import es.juntadeandalucia.plataforma.comunes.excepciones.BusinessException;
import es.juntadeandalucia.plataforma.logs.ILogService;
import es.juntadeandalucia.plataforma.service.reserva.IReservaService;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;
import trewa.bd.trapi.trapiui.tpo.TrExpedienteCaducado;
import trewa.bd.trapi.trapiui.tpo.TrUsuario;

/**
 * Servicio para recuperar los datos requeridos por el portlet
 *
 * @author Isotrol
 */
public class PortletDatosExpedienteServiceImpl implements IPortletDatosExpedienteService {

  /** serialVersionUID */

  private static final long serialVersionUID = -5707332616824111115L;

  /** Constante para espacio en blanco **/
  public static final String ESPACIADO = " ";

  /*** IReservaService reservaService para acceso a la tabla de Reservas y obtener informacion relativa a bloqueos de usuarios **/
  private transient IReservaService reservaService;

  private ITrewaService trewaService;

  /** logService **/
  private transient ILogService logService;

  @Override
  public BloqueoDTO getInformacionBloqueo(final UsuarioWeb usuario) throws PortletDatosExpedienteException {

    final BloqueoDTO bloqueoDTO = new BloqueoDTO();
    String tipoBloqueo = "";
    String codUsuario = "";
    String usuarioCompleto = "";
    try {
      codUsuario = reservaService.obtenerUsuarioReserva(usuario.getExpediente(), usuario.getFaseActual(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
      if (codUsuario != null && !codUsuario.isEmpty()) {
        bloqueoDTO.setCodigoUsuario(codUsuario);
        tipoBloqueo = reservaService.obtenerReserva(usuario.getExpediente(), usuario.getFaseActual(), ConstantesTramitacion.ESQUEMA_TREWA_AACID);
        usuarioCompleto = getUsuarioCompleto(codUsuario);
        bloqueoDTO.setUsuario(usuarioCompleto);
        bloqueoDTO.setTipoBloqueo(tipoBloqueo != null ? tipoBloqueo : "");
      }
    } catch (final BusinessException e) {
      logService.crearLog(e.getMessage());
      throw new PortletDatosExpedienteException(e.getMessage(), e);
    }

    return bloqueoDTO;
  }

  @Override
  public boolean expedienteCaducado(final PortletDatosExpedienteDTO portletDatosExpedienteDTO) throws PortletDatosExpedienteException {
    boolean caducado = true;

    final TrExpedienteCaducado[] caducidadesExp = trewaService.obtenerCaducidadExpedientes(portletDatosExpedienteDTO.getIdExpediente().toString());
    if (ArrayUtils.isEmpty(caducidadesExp)) {
      caducado = false;
    }
    return caducado;
  }

  /**
   * Obtiene el nombre del usuario completo.
   *
   * @param codUsuario
   * @return String nombreCompleto
   * @throws PortletDatosExpedienteException
   */
  private String getUsuarioCompleto(final String codUsuario) throws PortletDatosExpedienteException {

    String nombreCompleto = "";
    TrUsuario usuario;
    try {
      usuario = trewaService.obtenerUsuario(codUsuario);
      if (usuario != null) {
        nombreCompleto = usuario.getNOMBRE() + " " + usuario.getAPELLIDO1() + " " + usuario.getAPELLIDO2();
      }
      return nombreCompleto;
    } catch (final TramitacionException e) {
      throw new PortletDatosExpedienteException(e.getMessage(), e);
    }
  }

  /**
   * Establece el valor de la propiedad reservaService
   *
   * @param reservaService
   *          el reservaService para establecer
   */
  public void setReservaService(final IReservaService reservaService) {
    this.reservaService = reservaService;
  }

  /**
   * Establece el valor de la propiedad logService
   *
   * @param logService
   *          el logService para establecer
   */
  public void setLogService(final ILogService logService) {
    this.logService = logService;
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

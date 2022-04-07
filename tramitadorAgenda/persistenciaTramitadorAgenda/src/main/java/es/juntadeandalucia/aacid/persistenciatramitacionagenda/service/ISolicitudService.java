package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.io.Serializable;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.DatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.vea.CampoVeaType;
import es.juntadeandalucia.aacid.comuntramitacion.exception.SubsanacionException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Solicitud;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea.AaciTSolicitudsubongd;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;
import trewa.exception.TrException;

/**
 * ISolicitudService interface.
 *
 * @author isotrol
 *
 */
public interface ISolicitudService extends Serializable {

  /**
   * Obtener solicitud
   *
   * @param idExpTrewa
   *          id expediente de trewa
   * @return SolicitudDao
   * @throws TramitacionException
   */
  SolicitudDTO obtenerSolicitudByIdExp(String idExpTrewa) throws TramitacionException;

  /**
   * Obtener solicitud por su id
   *
   * @param solicitud
   * @return
   */
  Solicitud obtenerSolicitud(Solicitud solicitud) throws TramitacionException;

  /**
   * obtener datos generales de la solicitud del expediente
   *
   * @param idExpTrewa
   * @return
   * @throws TramitacionException
   */
  SolicitudDatosGeneralesDTO datosGeneralesSolicitudByIdExpTrewa(String idExpTrewa) throws TramitacionException;

  /**
   * Almacena en BBDD los otros datos de una solicitud dada
   *
   * @param idExpTrewa
   * @return
   * @throws TramitacionException
   */
  void guardarSolicitudOtrosDatos(DatosGeneralesDTO datosGeneralesDTO, String refExpediente) throws TramitacionException;

  /**
   * Método para el guardado de la soilcitud de subsanación que ha llegado desde VeA
   *
   * @param solicitudSubsanacion
   * @param camposPaises
   * @throws SubsanacionException
   */
  void guardarSolicitudSubsanacion(AaciTSolicitudsubongd solicitudSubsanacion, List<CampoVeaType> camposPaises, String tipoSolicitud)
      throws SubsanacionException;

  /**
   * Metodo para obtener las solicitudes por tipo de proyecto y convocatoria
   *
   * @param solicitudSubsanacion
   * @param camposPaises
   * @throws SubsanacionException
   */
  List<SolicitudDTO> obtenerSolicitudesByTipoProyectoDocumento(final Long idConv, final int tipoProyecto, final boolean isUniv) throws TramitacionException;

  /**
   * Método con el cúal obtendremos los datos de los expedietnes hijos de la convocatoria
   *
   * @param listaExpedientesHijoTrewa
   * @param finalidad
   * @return
   * @throws TramitacionException
   */
  List<SolicitudDTO> datosExpedienteProvConceDesest(List<TrExpediente> listaExpedientesHijoTrewa) throws TramitacionException;

  /**
   * Obtiene el listado de solicitudes hijas de la convocatoria que se encuentran en las fases indicadas.
   *
   * @param idExp
   * @param fases
   * @throws TrException
   * @throws TramitacionException
   */
  List<SolicitudDTO> obtenerListadoSolicitudesHijasByFases(String idExp, String fases) throws TrException, TramitacionException;

  /**
   * Obtenemos listado de expedientes hermanos
   *
   * @param idExpediente
   * @return
   * @throws TrException
   * @throws TramitacionException
   */
  List<SolicitudDatosGeneralesDTO> obtenerSolicitudesHermanas(String idExpediente) throws TramitacionException;

}

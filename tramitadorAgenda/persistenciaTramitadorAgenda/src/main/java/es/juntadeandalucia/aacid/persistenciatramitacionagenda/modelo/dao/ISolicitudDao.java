package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.AlegacionesAceptaReformulaException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.AlegacionesException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.RecepcionComunicacionException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.SubsanacionException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Solicitud;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea.AaciTPaises;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea.AaciTSolicitudsubongd;

/**
 * ISolicitudRepository interface.
 *
 * @author isotrol
 *
 */
public interface ISolicitudDao {

  /**
   * Obtener solicitud
   *
   * @param idExpTrewa
   *          id expediente de trewa
   * @return SolicitudDTO
   * @throws TramitacionException
   */
  SolicitudDTO obtenerSolicitudByIdExp(String idExpTrewa) throws TramitacionException;

  /**
   * obtener datos generales de la solicitud del expediente
   *
   * @param idExpTrewa
   * @return
   * @throws TramitacionException
   */
  SolicitudDatosGeneralesDTO datosGeneralesSolicitudByIdExpTrewa(String idExpTrewa) throws TramitacionException;

  /**
   * Obtener lista de solicitudes por tipo de proyecto y de expediente
   *
   * @param idExp
   * @param tipoProyecto
   * @param tipoExclusion
   * @param isUniv
   * @return
   * @throws TramitacionException
   */
  List<SolicitudDTO> obtenerSolicitudesByTipoProyectoYTipoExclusion(Long idExp, int tipoProyecto, Long tipoExclusion, boolean isUniv)
      throws TramitacionException;

  /**
   * Obtener Solicitud por id
   *
   * @param solicitud
   * @return
   */
  Solicitud find(Solicitud solicitud) throws TramitacionException;

  /**
   * Guardado de los datos de la solicitud de datos generales de la pantalla DGA
   *
   * @param solicitud
   * @return
   * @throws TramitacionException
   */
  int guardarSolicitudDatosGenerales(Solicitud solicitud) throws TramitacionException;

  /**
   * Obtenemos las solicitudes por tipo de proyecto para la generacion del documento.
   *
   * @param numExpediente
   * @param isUniv
   * @return
   * @throws TramitacionException
   */
  List<SolicitudDTO> obtenerSolicitudesByTipoProyectoDocumento(Long idConv, int tipoProyecto, boolean isUniv) throws TramitacionException;

  /**
   * obtenemos los datos de la solicitud que son editables en la subsanación
   *
   * @param id
   *          id del expediente de trewa
   * @return
   * @throws SubsanacionException
   */
  SolicitudDTO obtenerSolicitudSubsanacion(Long id) throws SubsanacionException;

  /**
   * Guardamos los datos de la subsanación de la solicitud
   *
   * @param solicitudSubsanacion
   * @param ongd
   * @return
   * @throws SubsanacionException
   */
  int guardarSolicitudSubsanacion(AaciTSolicitudsubongd solicitudSubsanacion, String tipoSolicitud) throws SubsanacionException;

  /**
   * actualizamos los repetibles de la subsanación de la solicitud
   *
   * @param solicitudEditada
   * @throws SubsanacionException
   */
  void updateCamposRepetibles(AaciTSolicitudsubongd solicitudEditada) throws SubsanacionException;

  AaciTPaises getPaisesById(String idPais);

  /**
   * Guardamos los datos del FORMULARIO DE ALEGACIONES
   *
   * @param formulario
   *          Formulario.
   * @throws AlegacionesException
   *           Excepcion
   */
  void guardarAlegaciones(Solicitud formulario) throws AlegacionesException;

  /**
   * Guardamos los datos del FORMULARIO DE ALEGACIONES/ACEPTACIÓN/REFORMULACIÓN Y PRESENTACIÓN DE DOCUMENTOS para Universidades
   *
   * @param formulario
   *          Formulario.
   * @throws AlegacionesAceptaReformulaException
   *           Excepción
   */
  void guardarAlegacionesAceptaReformulaUNIV(Solicitud formulario) throws AlegacionesAceptaReformulaException;

  /**
   * Guardamos los datos del FORMULARIO DE ALEGACIONES/ACEPTACIÓN/REFORMULACIÓN Y PRESENTACIÓN DE DOCUMENTOS para ONGD
   *
   * @param formulario
   *          Formulario.
   * @throws AlegacionesAceptaReformulaException
   *           Excepción
   */
  void guardarAlegacionesAceptaReformulaONGD(Solicitud formulario) throws AlegacionesAceptaReformulaException;
   /**
   * Guardamos los datos del FORMULARIO DE COMUNICACIÓN DE INICIO para ONGD
   *
   * @param formulario
   *          Formulario.
   * @throws RecepcionComunicacionException
   *           Excepción
   */
   void guardarRecepcionComunicacion(final Solicitud formulario) throws RecepcionComunicacionException;

  /**
   * Método para consultar en bd los datos de los expedientes hijos para el listado de prov de conce y desest.
   *
   * @param listaIdTrewa
   * @return
   * @throws TramitacionException
   */
  List<Object> obtenerDatosExpedientesHijosProvConce(List<String> listaIdTrewa) throws TramitacionException;

}

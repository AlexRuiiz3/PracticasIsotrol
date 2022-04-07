package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.math.BigDecimal;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.ExclusionesSolicitud;

/**
 * Interfaz de ExclusionDAO
 * 
 * @author Isotrol
 *
 */
public interface IExclusionesSolicitudDAO {

  /**
   * obtener exclusiones de la Solicitud
   * 
   * @param idSoli
   * @param numTipo
   * @param orden
   * @return
   */
  List<Long> obtenerIdsExclusionesSolicitud(Long idSoli, Long numTipo, Long orden) throws TramitacionException;


  List<Integer> obtenerTodasIdsExclusionesSolicitud(Long idSoli) throws TramitacionException;
  /**
   * Crea o actualiza una entidad.
   * 
   * @param entidad
   *          para crear en BD
   * @return numero de registros afectados
   */
  int createEntidad(ExclusionesSolicitud entidad) throws TramitacionException;

  /**
   * Obtener exclusiones de la solicitud
   * 
   * @param idSolicitud
   * @param tipoExcl
   * @return
   */
  List<Long> obtenerIdsExclusionesBySoli(Long idSolicitud, Long tipoExcl) throws TramitacionException;

  /**
   * Elimina exclusión de la solicitud
   * 
   * @param idExcl
   * @param idSolicitud
   * @return numero de registros afectados
   * @throws Exception
   */
  int eliminarExclusionSolicitud(Long idExcl, Long idSolicitud) throws TramitacionException;

  /**
   * Comprueba si la solicitud tiene exlusiones pendientes.
   * 
   * @param idSolicitud
   * @return
   * @throws TramitacionException
   */
  BigDecimal existsExlusionesBySolicitud(Long idSolicitud) throws TramitacionException;

  /**
   * Obtiene los identificadores de las exclusiones para una solicitud y tipo dado
   * 
   * @param idSolicitud
   * @param numTipo
   * @return
   * @throws TramitacionException
   */
  List<Integer> obtenerIdsExclusionesSolicitudByTipo(Long idSoli, Integer numTipo) throws TramitacionException;

}

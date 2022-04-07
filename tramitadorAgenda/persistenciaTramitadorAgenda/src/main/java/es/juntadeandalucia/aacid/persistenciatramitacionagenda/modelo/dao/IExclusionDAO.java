package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Exclusion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TExclusionesSolicitud;
import es.juntadeandalucia.plataforma.comunes.excepciones.ArchitectureException;

/**
 * Interfaz de ExclusionDAO
 *
 * @author Isotrol
 *
 */
public interface IExclusionDAO {

  /**
   * Obtiene las exclusiones según el tipo
   *
   * @param tipoConv
   *
   * @param numTipo
   *          código del tipo de la exclusion
   * @param orden
   *          orden del tipo de exclusion
   * @param anio
   *          Año de la solicitud
   * @return
   */
  List<Exclusion> obtenerExclusionesByOrdenTipo(String tipoConv, Long numTipo, Long orden, Long anio);

  /**
   * Obtiene las exclusiones según el tipo
   *
   * @param ids
   * @return
   */
  List<Exclusion> obtenerExclusionesByIds(List<Long> ids);

  /**
   * Obtener exclusion por id
   *
   * @param id
   * @return
   */
  Exclusion findById(Long id) throws ArchitectureException;

  /**
   * Obtener número de exclusiones solicitud por tipo
   *
   * @throws TramitacionException
   *
   */
  List<Integer> obtenerExclusionesSolicitudByTipoExclusion(String numExpediente, Integer tipo) throws TramitacionException;

  /**
   * Comprobamos el check de tiene exclusiones
   *
   * @throws TramitacionException
   *
   */
  Object tieneExclusionesTipoExclusion(Long idSolicitud, Integer tipo) throws TramitacionException;

  /**
   * Guardamos la comprobación de exclusiones
   *
   * @param tieneExclusiones
   * @return
   * @throws TramitacionException
   */
  int guardarComprobacionExclusionPorTipo(TExclusionesSolicitud tieneExclusiones) throws TramitacionException;
}

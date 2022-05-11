package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Pais;

public interface IPaisDao {

  /**
   * Obtenemos Paises
   *
   * @param capacidadGestion
   * @return
   * @throws TramitacionException
   */
  List<Pais> obtenerPaisesSolicitud(Long idsolicitud) throws TramitacionException;

  /**
   * Obtenemos todos los Paises existentes de un año
   * 
   * @param int
   *          anho
   * @return List<Pais>
   */
  List<Pais> obtenerPaisesPorAnho(int anho);

  /**
   * Obtener un pais en especifico a partir por su id
   * 
   * @param Long
   *          id
   * @return Pais
   */
  Pais obtenerPais(Long id);

  /**
   * Eliminar un pais en especifico a partir de su id
   * 
   * @param Long
   *          id
   */
  void eliminarPais(Long id);

  /**
   * Se guarda o actualiza un pais
   * 
   * @param Pais
   *          pais
   */
  void guardarOActualizarPais(Pais pais);
}
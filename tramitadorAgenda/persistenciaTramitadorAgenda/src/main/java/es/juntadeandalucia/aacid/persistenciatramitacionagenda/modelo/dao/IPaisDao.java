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
}
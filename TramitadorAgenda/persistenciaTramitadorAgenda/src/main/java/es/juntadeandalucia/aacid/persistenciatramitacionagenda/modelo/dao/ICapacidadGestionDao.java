package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.CapacidadGestion;

public interface ICapacidadGestionDao {

  /**
   * Obtenemos listado capacidades de gestión a partir de los filtros
   * 
   * @param capacidadGestion
   * @return
   */
  List<CapacidadGestion> obtenerCapacidadesGestion(CapacidadGestion capacidadGestion);
}
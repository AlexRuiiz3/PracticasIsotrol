package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.CapacidadGestion;

public interface ICapacidadGestionService {

  /**
   * Obtenemos listado capacidades de gestión a partir de los filtros
   * 
   * @param capacidadGestion
   * @return
   */
  List<CapacidadGestion> obtenerCapacidadesGestion(CapacidadGestion capacidadGestion);

  /**
   * Obtener valor capacidad de gestión de la entidad
   * 
   * @param numExp
   * @param valoracionCapGest
   * @throws TramitacionException
   */
  void obtenerValorCapacidadGestionEntidad(String numExp, ValoracionCriterioDTO valoracionCapGest, Integer anio) throws TramitacionException;
}

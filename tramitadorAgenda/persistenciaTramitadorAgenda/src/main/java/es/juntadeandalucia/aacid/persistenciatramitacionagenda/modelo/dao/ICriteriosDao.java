package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Criterios;

public interface ICriteriosDao {

  /**
   * Obtener listado de criterios por el tipo de criterio, la finalidad asociada y la convocatoria a la que pertenece
   * 
   * @param finalidad
   * @param tipoCriterio
   * @param isUniv
   * @return
   * @throws TramitacionException
   */
  List<Criterios> obtenerListaCriteriosByFinalidadAndConvocatoria(final String finalidad, final String tipoCriterio, final boolean isUniv, Long anyo,
      boolean comprobarSubFinalidad) throws TramitacionException;
}
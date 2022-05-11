package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Pais;

public interface IPaisService {

  /**
   * Obtener valoracion con la convergencia de paises
   *
   * @param idSolicitud
   * @param valoracionConvergencia
   * @throws TramitacionException
   */
  void obtenerValoracionConvergencia(Long idSolicitud, ValoracionCriterioDTO valoracionConvergencia) throws TramitacionException;

  List<Pais> obtenerPaisesPorAnho(int anho);

  Pais obtenerPais(Long id);

  void eliminarPais(Long id);

  void guardarOActualizarPais(Pais pais);
}

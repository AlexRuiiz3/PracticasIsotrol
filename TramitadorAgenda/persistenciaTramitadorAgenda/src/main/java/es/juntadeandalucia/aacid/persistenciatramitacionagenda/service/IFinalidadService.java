package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Finalidad;

public interface IFinalidadService {

  Finalidad obtenerFinalidadByCodigo(String codigo);

  /**
   * Obtiene la descripción de la finalidad a partir del numero de expediente
   *
   * @param numExpediente
   * @param isUniv
   * @return
   * @throws TramitacionException
   */
  String obtenerFinalidadByNumExpediente(Long numExpediente, boolean isUniv) throws TramitacionException;

  /**
   * Obtiene el codigo de la finalidad a partir del numero de expediente
   *
   * @param numExpediente
   * @param isUniv
   * @return
   * @throws TramitacionException
   */
  String obtenerCodFinalidadByNumExpediente(Long numExpediente, boolean isUniv) throws TramitacionException;

  /**
   * Comprueba que
   *
   * @param numExpediente
   * @param isUniv
   * @return
   * @throws TramitacionException
   */
  boolean filtrarSubFinalidad(String finalidad, String numExpdiente) throws TramitacionException;
}

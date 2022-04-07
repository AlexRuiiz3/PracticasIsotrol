package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeSolicitudes;

public interface IPaeSoliciudDao {

  /**
   * Obtiene la entidad Solicitud del sistema antiguo
   * 
   * @param idExp
   * @return PaeSolicitudes
   * @throws TramitacionException
   */
  PaeSolicitudes getSolicitudExpByCodigo(String codigoIdentificativo, String esquema) throws TramitacionException;

  /**
   * Obten el numero del expediente a partir del codigo
   * 
   * @param idExp
   * @return PaeSolicitudes
   * @throws TramitacionException
   */
  Long getNumExpByCodigo(String codigoIdentificativo, String esquema) throws TramitacionException;

}

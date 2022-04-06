package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Finalidad;

public interface IFinalidadDao {

	public Finalidad obtenerFinalidadByCodigo(String codigo);
	
	
	 /**
	   * Obtenemos la finalidad del expediente
	   * 
	   * @param numExpediente
	   * @param isUniv
	   * @return
	   * @throws TramitacionException
	   */
	public String obtenerFinalidadByNumExpediente(Long numExpediente, boolean isUniv) throws TramitacionException;
	
	/**
	 * Obtenemos el codigo de la finalidad del expediente
	 * 
	 * @param numExpediente
	 * @param isUniv
	 * @return
	 * @throws TramitacionException
	 */
	public String obtenerCodFinalidadByNumExpediente(Long numExpediente, boolean isUniv) throws TramitacionException;
}

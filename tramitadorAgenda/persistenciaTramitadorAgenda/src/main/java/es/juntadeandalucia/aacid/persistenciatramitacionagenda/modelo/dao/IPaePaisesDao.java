package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaePaises;

public interface IPaePaisesDao {

	public PaePaises getPaisById(Long idPais) throws TramitacionException;
}

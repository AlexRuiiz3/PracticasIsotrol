package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Contribuciones;

public interface IContribucionDao {

	public List<Contribuciones> obtenerContribucionesBySolicitud(Long idSolicitud) throws TramitacionException;

	public Contribuciones saveOrUpdateContribuciones(Contribuciones contribucion) throws TramitacionException;
}



package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Contrapartes;

public interface IContrapartesDao {

	public List<Object> obtenerContrapartesBySolicitud(Long idSolicitud) throws TramitacionException;

	public int saveOrUpdateContraparte(Contrapartes contrapartes,Long idSolicitud) throws TramitacionException;
}

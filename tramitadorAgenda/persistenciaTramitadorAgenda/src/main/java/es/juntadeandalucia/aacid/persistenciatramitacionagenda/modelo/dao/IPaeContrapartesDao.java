package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeContrapartes;

public interface IPaeContrapartesDao {

	public List<PaeContrapartes> getPaeContrapartesByPaeSolicitud(Long idSolicitud) throws TramitacionException;
}

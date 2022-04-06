package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.EntidadesParticipantes;

public interface IEntidadParticipanteDao {

	public EntidadesParticipantes saveOrUpdateEntidadParticipante(EntidadesParticipantes entidad) throws TramitacionException;
	
	public List<EntidadesParticipantes> obtenerEntidadesParticipantesBySolicitud(Long idSolicitud);

}

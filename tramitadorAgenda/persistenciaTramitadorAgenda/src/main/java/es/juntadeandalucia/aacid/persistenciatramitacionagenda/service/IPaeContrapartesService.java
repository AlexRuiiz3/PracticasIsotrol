package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeContrapartes;

public interface IPaeContrapartesService {

	public List<PaeContrapartes> getPaeContrapartesByPaeSolicitud(Long idSolicitud) throws TramitacionException;

}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeAgrupaciones;

public interface IPaeAgrupacionesService {

	public List<PaeAgrupaciones> getAgrupacionesBySolicitud(Long idSolicitud) throws TramitacionException;

}

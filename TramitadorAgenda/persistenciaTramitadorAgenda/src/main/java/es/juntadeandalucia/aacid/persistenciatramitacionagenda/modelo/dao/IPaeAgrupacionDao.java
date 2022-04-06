package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeAgrupaciones;

public interface IPaeAgrupacionDao {

public List<PaeAgrupaciones> getPaeAgrupacionesBySolicitud(Long idSolicitud) throws TramitacionException;
}

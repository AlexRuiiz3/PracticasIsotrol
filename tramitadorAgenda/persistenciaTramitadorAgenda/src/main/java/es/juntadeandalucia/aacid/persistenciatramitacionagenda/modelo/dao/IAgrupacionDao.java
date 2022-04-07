package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Agrupaciones;

public interface IAgrupacionDao {

  List<Object> getAgrupacionesBySolicitud(Long idSolicitud) throws TramitacionException;

  int saveOrUpdateAgrupacion(Agrupaciones agrupacion, Long idSolicitud) throws TramitacionException;

  int existeAgrupaciones(Long id);

}

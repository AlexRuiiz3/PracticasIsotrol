package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Agrupaciones;

/**
 * IAgrupacionService interface.
 *
 * @author isotrol.
 *
 */
public interface IAgrupacionService {

  List<Agrupaciones> getAgrupacionesBySolicitud(Long idSolicitud) throws TramitacionException;

  int saveOrUpdateAgrupacion(Agrupaciones agrupacion, Long idSolicitud) throws TramitacionException;

}

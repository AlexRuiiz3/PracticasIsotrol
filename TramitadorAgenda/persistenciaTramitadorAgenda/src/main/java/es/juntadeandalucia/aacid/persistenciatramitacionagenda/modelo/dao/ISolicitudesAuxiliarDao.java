package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudAuxiliarDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.SolicitudesAuxiliar;

public interface ISolicitudesAuxiliarDao {

  SolicitudAuxiliarDTO findByIdSolicitud(Long idSolicitud) throws TramitacionException;

  int saveOrUpdateSolicitudesAuxiliar(SolicitudesAuxiliar solicitudesAuxiliar) throws TramitacionException;
}

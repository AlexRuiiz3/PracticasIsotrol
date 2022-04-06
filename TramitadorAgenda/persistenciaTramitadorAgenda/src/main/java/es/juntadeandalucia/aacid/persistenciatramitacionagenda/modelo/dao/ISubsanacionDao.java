package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Subsanacion;

/**
 * ISubsanacionRepository class.
 * 
 * @author isotrol
 *
 */
public interface ISubsanacionDao {

  Subsanacion saveOrUpdateSubsanacion(Subsanacion subsanacion) throws TramitacionException;

  int updateSubsanacionSolicitudRelation(String idSubsanacion, String idSolicitud) throws TramitacionException;

  Subsanacion getSubsanacionByIdExpediente(Long idExpediente) throws TramitacionException;

  Subsanacion getById(Integer id) throws TramitacionException;

  void deleteSubsanacion(Subsanacion subsanacion) throws TramitacionException;

  void updateSubsanacion(Subsanacion subsanacion) throws TramitacionException;

  void removeSolicitudRelation(Subsanacion subsanacion) throws TramitacionException;

}

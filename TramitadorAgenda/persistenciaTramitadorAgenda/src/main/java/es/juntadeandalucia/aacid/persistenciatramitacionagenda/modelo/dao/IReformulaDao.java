package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Reformula;

public interface IReformulaDao {

  List<Reformula> findAllBySolicitudIdOrderById(Long idSolicitud) throws TramitacionException;

  Reformula save(Reformula reformula) throws TramitacionException;

}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.SolicitudConcesion;

public interface ISolicitudConcesionDao {

  SolicitudConcesion obtenerSolicitudConcesionPorSolicitud(Long idSolicitud) throws TramitacionException;

  List<Object> obtenerListaConcesiones(List<String> listaExpedientes, String finalidad, boolean ongd);

  void guardar(List<SolicitudConcesion> listSolConcesion);
}

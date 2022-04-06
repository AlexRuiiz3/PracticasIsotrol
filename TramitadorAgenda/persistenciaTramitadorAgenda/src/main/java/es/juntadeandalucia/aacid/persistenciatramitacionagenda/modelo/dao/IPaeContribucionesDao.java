package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeContribuciones;

public interface IPaeContribucionesDao {

  
  public List<PaeContribuciones> getPaeContribucionesBySolicitud(Long idSolicitud) throws TramitacionException;

}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TipoEntidadParticipante;

public interface ITipoEntidadParticipanteDao {

  TipoEntidadParticipante obtenerTipoEntidadParticipanteByCodigo(String codigo);
}

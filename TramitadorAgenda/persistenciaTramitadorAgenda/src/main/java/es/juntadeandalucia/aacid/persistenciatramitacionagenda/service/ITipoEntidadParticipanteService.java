package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TipoEntidadParticipante;

public interface ITipoEntidadParticipanteService {

  TipoEntidadParticipante obtenerTipoEntidadParticipanteByCodigo(String codigo);

}

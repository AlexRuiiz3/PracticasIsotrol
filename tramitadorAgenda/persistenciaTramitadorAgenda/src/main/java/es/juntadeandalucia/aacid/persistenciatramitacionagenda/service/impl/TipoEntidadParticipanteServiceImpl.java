package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ITipoEntidadParticipanteDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TipoEntidadParticipante;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ITipoEntidadParticipanteService;

public class TipoEntidadParticipanteServiceImpl implements ITipoEntidadParticipanteService {

  private ITipoEntidadParticipanteDao tipoEntidadParticipanteDao;

  @Override
  public TipoEntidadParticipante obtenerTipoEntidadParticipanteByCodigo(final String codigo) {
    return tipoEntidadParticipanteDao.obtenerTipoEntidadParticipanteByCodigo(codigo);
  }

  /**
   * Establece el valor de la propiedad tipoEntidadParticipanteDao
   *
   * @param tipoEntidadParticipanteDao
   *          el tipoEntidadParticipanteDao para establecer
   */
  public void setTipoEntidadParticipanteDao(final ITipoEntidadParticipanteDao tipoEntidadParticipanteDao) {
    this.tipoEntidadParticipanteDao = tipoEntidadParticipanteDao;
  }

}

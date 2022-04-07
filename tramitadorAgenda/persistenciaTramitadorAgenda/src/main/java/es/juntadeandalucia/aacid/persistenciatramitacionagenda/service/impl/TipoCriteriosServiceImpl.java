package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ITipoCriteriosDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ITipoCriteriosService;

public class TipoCriteriosServiceImpl implements ITipoCriteriosService {

  private ITipoCriteriosDao tipoCriteriosDao;

  @Override
  public Long obtenerIdTipoCriterioByConvocatoriaFinalidadNombre(final String finalidad, final String nombre) throws TramitacionException {
    return tipoCriteriosDao.obtenerIdTipoCriterioByConvocatoriaFinalidadNombre(finalidad, nombre);
  }

  @Override
  public String obtenerNombreTipoCriterioByidCriterio(final Long idCriterio) throws TramitacionException {
    return tipoCriteriosDao.obtenerNombreTipoCriterioByidCriterio(idCriterio);
  }

  public ITipoCriteriosDao getTipoCriteriosDao() {
    return tipoCriteriosDao;
  }

  public void setTipoCriteriosDao(final ITipoCriteriosDao tipoCriteriosDao) {
    this.tipoCriteriosDao = tipoCriteriosDao;
  }

}

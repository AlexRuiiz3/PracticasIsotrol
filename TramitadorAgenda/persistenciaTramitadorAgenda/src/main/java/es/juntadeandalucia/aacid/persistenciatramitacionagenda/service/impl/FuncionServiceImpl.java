package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IFuncionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Funcion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IFuncionService;

public class FuncionServiceImpl implements IFuncionService {

  private IFuncionDao funcionDao;

  @Override
  public Funcion obtenerFuncionByCodigo(final String codigo) {
    return funcionDao.obtenerFuncionByCodigo(codigo);
  }

  /**
   * Establece el valor de la propiedad funcionDao
   *
   * @param funcionDao
   *          el funcionDao para establecer
   */
  public void setFuncionDao(final IFuncionDao funcionDao) {
    this.funcionDao = funcionDao;
  }

}

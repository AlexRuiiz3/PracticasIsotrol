package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.TipoCatalogoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.TipoCatalogoListConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ITipoCatalogoDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TipoCatalogo;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ITipoCatalogoService;

public class TipoCatalogoServiceImpl implements ITipoCatalogoService {

  /** tipoCatalogoDao */
  private ITipoCatalogoDao tipoCatalogoDao;

  @Override
  public List<TipoCatalogoDTO> obtenerCatalogosPorAnio(final Integer anio, final String tipoConvocatoria) throws TramitacionException {
    final List<TipoCatalogo> listaTipos = tipoCatalogoDao.obtenerTiposCatalogoPorAnio(anio, tipoConvocatoria);
    return TipoCatalogoListConverter.convertToListDto(listaTipos);
  }

  public ITipoCatalogoDao getTipoCatalogoDao() {
    return tipoCatalogoDao;
  }

  public void setTipoCatalogoDao(final ITipoCatalogoDao tipoCatalogoDao) {
    this.tipoCatalogoDao = tipoCatalogoDao;
  }
}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.CatalogoListConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ICatalogoDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Catalogo;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ICatalogoService;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;

public class CatalogoServiceImpl implements ICatalogoService {

  /** catalogoDao */
  private ICatalogoDao catalogoDao;

  @Override
  public List<CatalogoDTO> obtenerListaCodigosSubsanacion(Long idSolicitud) throws TramitacionException {
    try {
      return catalogoDao.obtenerListaCodigosSubsanacion(idSolicitud);
    } catch (TramitacionException e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }

  @Override
  public List<CatalogoDTO> obtenerCatalogoPorTipo(Integer tipoCatalogo, List<Perfil> listaperfiles) throws TramitacionException {

    List<Catalogo> listaCat = catalogoDao.obtenerCatalogosPorTipo(tipoCatalogo);

    return CatalogoListConverter.convertToListDto(listaCat, listaperfiles);
  }

  /**
   * @return the catalogoDao
   */
  public ICatalogoDao getCatalogoDao() {
    return catalogoDao;
  }

  /**
   * @param catalogoDao
   *          the catalogoDao to set
   */
  public void setCatalogoDao(ICatalogoDao catalogoDao) {
    this.catalogoDao = catalogoDao;
  }

}

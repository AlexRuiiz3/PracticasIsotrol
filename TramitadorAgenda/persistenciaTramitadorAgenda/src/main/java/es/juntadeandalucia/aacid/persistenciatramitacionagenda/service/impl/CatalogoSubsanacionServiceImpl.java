package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoSubsanacionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.CatalogoSubsanacionConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ICatalogoSubsanacionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.CatalogoSubsanacion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ICatalogoSubsanacionService;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;

public class CatalogoSubsanacionServiceImpl implements ICatalogoSubsanacionService {

  /** catalogoSubsanacionDao */
  private ICatalogoSubsanacionDao catalogoSubsanacionDao;

  /**
   * Obtención del catálogo de subsanación.
   * 
   * @throws TramitacionException
   */
  @Override
  public List<CatalogoSubsanacionDTO> obtenerCatalogoSubsancionByIdSolicitud(String idSolicitud) throws TramitacionException {
    return catalogoSubsanacionDao.obtenerCatalogoSubsancionByIdSolicitud(idSolicitud);
  }

  public List<CatalogoSubsanacionDTO> obtenerCatalogoSubsanacionByIdSubsanacion(Integer idSubsanacion, List<Perfil> listaPerfiles) throws TramitacionException {
    List<CatalogoSubsanacion> listaCatSub = catalogoSubsanacionDao.obtenerCatalogoSubsancionByIdSubsanacion(idSubsanacion);
    return CatalogoSubsanacionConverter.convertListToDTO(listaCatSub, listaPerfiles);
  }

  /**
   * @return the catalogoSubsanacionDao
   */
  public ICatalogoSubsanacionDao getCatalogoSubsanacionDao() {
    return catalogoSubsanacionDao;
  }

  /**
   * @param catalogoSubsanacionDao
   *          the catalogoSubsanacionDao to set
   */
  public void setCatalogoSubsanacionDao(ICatalogoSubsanacionDao catalogoSubsanacionDao) {
    this.catalogoSubsanacionDao = catalogoSubsanacionDao;
  }

}

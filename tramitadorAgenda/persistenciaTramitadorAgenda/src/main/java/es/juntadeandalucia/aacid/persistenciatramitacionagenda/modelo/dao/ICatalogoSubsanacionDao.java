package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoSubsanacionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.CatalogoSubsanacion;

/**
 * ICatalogoSubsanacion interface.
 * 
 * @author isotrol.
 *
 */
public interface ICatalogoSubsanacionDao {

  /**
   * Obtener catálogo de subsanación por la solicitud.
   * 
   * @param idSolicitud
   *          id de la solicitud
   * @return {@link CatalogoSubsanacion} List
   * @throws TramitacionException
   */
  List<CatalogoSubsanacionDTO> obtenerCatalogoSubsancionByIdSolicitud(final String idSolicitud) throws TramitacionException;

  List<CatalogoSubsanacion> obtenerCatalogoSubsancionByIdSubsanacion(Integer idSubsanacion) throws TramitacionException;

  CatalogoSubsanacion guardarCatalogoSubsanacion(CatalogoSubsanacion catalogoSubsanacion) throws TramitacionException;

  void eliminarCatalogoSubsanacion(CatalogoSubsanacion catalogo) throws TramitacionException;

  void eliminarCatalogosSubsanacion(List<CatalogoSubsanacion> catalogos) throws TramitacionException;

  List<CatalogoSubsanacionDTO> obtenerCatalogoSubsancionByIdSolicitudDocumento(String idSolicitud) throws TramitacionException;
}

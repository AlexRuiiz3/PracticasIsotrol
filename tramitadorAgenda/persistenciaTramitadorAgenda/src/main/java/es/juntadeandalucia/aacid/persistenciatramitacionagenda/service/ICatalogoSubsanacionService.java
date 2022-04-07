package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoSubsanacionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;

/**
 * ICatalogoSubsanacionService interface.
 * 
 * @author isotrol.
 *
 */
public interface ICatalogoSubsanacionService {

  /**
   * Obtener catálogo de subsanación por la solicitud.
   * 
   * @param idSolicitud
   *          id de la solicitud
   * @return {@link CatalogoSubsanacionDTO} List
   * @throws TramitacionException
   */
  List<CatalogoSubsanacionDTO> obtenerCatalogoSubsancionByIdSolicitud(final String idSolicitud) throws TramitacionException;

  List<CatalogoSubsanacionDTO> obtenerCatalogoSubsanacionByIdSubsanacion(Integer idSubsanacion, List<Perfil> listaPerfiles) throws TramitacionException;
}

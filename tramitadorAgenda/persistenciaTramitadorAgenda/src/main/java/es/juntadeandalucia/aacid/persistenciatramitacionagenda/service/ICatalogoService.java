package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;

/**
 * ICatalogoService interface.
 * 
 * @author isotrol.
 *
 */
public interface ICatalogoService {

  /**
   * Obtiene el listado de codigos de causas de subasanación por cada solicitud.
   * 
   * @param idSolicitud
   *          id de la solicitu.
   * @return lista de catalogos
   */
  List<CatalogoDTO> obtenerListaCodigosSubsanacion(Long idSolicitud) throws TramitacionException;

  List<CatalogoDTO> obtenerCatalogoPorTipo(Integer tipoCatalogo, List<Perfil> listaperfiles) throws TramitacionException;

}

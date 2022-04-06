package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Catalogo;

public interface ICatalogoDao {

  /**
   * obtiene las causas de la subsanción por el id de la solicitud
   * 
   * @param idSolicitud
   *          id de la solicitud.
   * @throws TramitacionException 
   * @returnn lista de causas de subsanación
   */
  List<CatalogoDTO> obtenerListaCodigosSubsanacion(Long idSolicitud) throws TramitacionException;

  Catalogo getCatalogoByCodTipoCodCatTipoConvAnyo(String codTipo, String codCat, String tipoConv, Integer anyo) throws TramitacionException;

  List<Catalogo> obtenerCatalogosPorTipo (Integer tipCat) throws TramitacionException;

}

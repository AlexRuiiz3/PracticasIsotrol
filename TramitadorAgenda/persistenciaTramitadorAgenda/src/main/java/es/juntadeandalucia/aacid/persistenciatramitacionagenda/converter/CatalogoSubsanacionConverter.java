package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import java.util.ArrayList;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoSubsanacionDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.CatalogoSubsanacion;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;

public class CatalogoSubsanacionConverter {

  /**
   * Constructor Defecto
   */
  private CatalogoSubsanacionConverter() {
    super();
  }

  public static CatalogoSubsanacionDTO convertToDto(CatalogoSubsanacion catalSubsanacion, List<Perfil> listaPerfiles) {
    CatalogoSubsanacionDTO catalogoSubsanacionDTO = new CatalogoSubsanacionDTO();

    catalogoSubsanacionDTO.setCatalogo(CatalogoConverter.convertToDto(catalSubsanacion.getAaciCatalogoByCatNuId(), listaPerfiles));
    catalogoSubsanacionDTO.setMotivo(catalSubsanacion.getMotivo());
    catalogoSubsanacionDTO.setPuedeEditar(catalogoSubsanacionDTO.getCatalogo().isPuedeEditar());

    return catalogoSubsanacionDTO;
  }

  public static List<CatalogoSubsanacionDTO> convertListToDTO(List<CatalogoSubsanacion> listaCatalogosSub, List<Perfil> listaPerfiles) {
    List<CatalogoSubsanacionDTO> listaCatalogos = new ArrayList<>();

    if (listaCatalogosSub != null) {
      for (CatalogoSubsanacion cat : listaCatalogosSub) {
        listaCatalogos.add(convertToDto(cat, listaPerfiles));
      }
    }

    return listaCatalogos;

  }
}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import java.util.ArrayList;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Catalogo;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.CatalogoSubsanacion;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;

public class CatalogoConverter {

  /**
   * Constructor por defecto
   */
  private CatalogoConverter() {
    super();
  }

  /**
   * Conversor de Catalogo
   * 
   * @param catalogo
   *          {@link CatalogoSubsanacion}
   * @return {@link CatalogoDTO} List
   */
  public static CatalogoDTO convertCatalogoSubToDto(Catalogo catalogo) {
    CatalogoDTO catalogoDTO = new CatalogoDTO();
    catalogoDTO.setDescripcion(catalogo.getCatLiDescripcion());
    return catalogoDTO;
  }

  public static CatalogoDTO convertToDto(Catalogo catalogo, List<Perfil> listaPerfiles) {
    CatalogoDTO catalogoDTO = new CatalogoDTO();
    catalogoDTO.setDescripcion(catalogo.getCatLiDescripcion());
    catalogoDTO.setMotivo(catalogo.getCatLoMotivo());
    catalogoDTO.setCodigo(catalogo.getCatCoCodigo());
    catalogoDTO.setId(catalogo.getCatNuId());
    if (catalogo.getAaciTipoCatalogoByTcatNuId() != null) {
      catalogoDTO.setNombreCompleto(
          catalogo.getAaciTipoCatalogoByTcatNuId().getTcatCoCodigo() + " - " + catalogo.getCatCoCodigo() + ". " + catalogo.getCatLiDescripcion());
    }

    boolean puedeEditar = false;
    for (Perfil p : listaPerfiles) {
      if ((ConstantesTramitacion.PERFIL_COORDINADOR.equals(p.getNombre()) && catalogo.getCatLgCoordinador())
          || (ConstantesTramitacion.PERFIL_DGA.equals(p.getNombre()) && catalogo.getCatLgDga())
          || (ConstantesTramitacion.PERFIL_VALORADOR.equals(p.getNombre()) && catalogo.getCatLgValorador())) {
        puedeEditar = true;
        break;
      }
    }
    catalogoDTO.setPuedeEditar(puedeEditar);

    return catalogoDTO;
  }

  public static List<CatalogoDTO> convertListToDTO(List<Catalogo> listaCats, List<Perfil> listaPerfiles) {
    List<CatalogoDTO> listCatalogo = new ArrayList<>();

    for (Catalogo cat : listaCats) {
      listCatalogo.add(convertToDto(cat, listaPerfiles));
    }
    return listCatalogo;
  }
}
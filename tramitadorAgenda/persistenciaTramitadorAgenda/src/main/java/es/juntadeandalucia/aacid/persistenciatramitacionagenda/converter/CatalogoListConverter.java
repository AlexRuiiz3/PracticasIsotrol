package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import java.util.ArrayList;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Catalogo;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;

public class CatalogoListConverter {

  public static CatalogoDTO convertToDTO(Catalogo catalogo, List<Perfil> listaPerfiles) {

    CatalogoDTO catConvertido = new CatalogoDTO();

    catConvertido.setId(catalogo.getCatNuId());

    catConvertido.setDescripcion(catalogo.getCatCoCodigo() + ". " + catalogo.getCatLiDescripcion());
    catConvertido.setMotivo(catalogo.getCatLoMotivo());
    catConvertido.setNombreCompleto(catalogo.getAaciTipoCatalogoByTcatNuId().getTcatCoCodigo() + " - " + catConvertido.getDescripcion());

    boolean puedeEditar = false;
    for (Perfil p : listaPerfiles) {
      if ((ConstantesTramitacion.PERFIL_COORDINADOR.equals(p.getNombre()) && catalogo.getCatLgCoordinador())
          || (ConstantesTramitacion.PERFIL_DGA.equals(p.getNombre()) && catalogo.getCatLgDga())
          || (ConstantesTramitacion.PERFIL_VALORADOR.equals(p.getNombre()) && catalogo.getCatLgValorador())) {
        puedeEditar = true;
        break;
      }
    }
    catConvertido.setPuedeEditar(puedeEditar);

    return catConvertido;

  }

  public static List<CatalogoDTO> convertToListDto(List<Catalogo> listaCats, List<Perfil> listaPerfiles) {
    List<CatalogoDTO> listaCatsConvcertidos = new ArrayList<>();

    for (Catalogo cat : listaCats) {
      listaCatsConvcertidos.add(convertToDTO(cat, listaPerfiles));
    }
    return listaCatsConvcertidos;
  }

  public static List<CatalogoDTO> convertToListCodigosDto(List<Catalogo> listaCodigos) {
    List<CatalogoDTO> listaCodigosConvcertidos = new ArrayList<>();

    for (Catalogo codigo : listaCodigos) {
      listaCodigosConvcertidos.add(convertToCodigosDTO(codigo));
    }
    return listaCodigosConvcertidos;
  }

  private static CatalogoDTO convertToCodigosDTO(Catalogo codigo) {
    CatalogoDTO catConvertido = new CatalogoDTO();
    catConvertido.setCodigo(codigo.getCatCoCodigo());
    return catConvertido;
  }
}

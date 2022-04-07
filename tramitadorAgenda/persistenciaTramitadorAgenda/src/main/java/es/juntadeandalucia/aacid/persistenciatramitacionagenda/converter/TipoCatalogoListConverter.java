package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import java.util.ArrayList;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.TipoCatalogoDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TipoCatalogo;

public class TipoCatalogoListConverter {

  public static TipoCatalogoDTO convertToDTO(final TipoCatalogo tipo) {

    final TipoCatalogoDTO tipoConvertido = new TipoCatalogoDTO();

    tipoConvertido.setId(tipo.getTcatNuId());

    tipoConvertido.setNombre(tipo.getTcatCoCodigo() + ". " + tipo.getTcatLiDescripcion());

    return tipoConvertido;

  }

  public static List<TipoCatalogoDTO> convertToListDto(final List<TipoCatalogo> listaTipos) {
    final List<TipoCatalogoDTO> listaTiposConvcertidos = new ArrayList<>();

    for (final TipoCatalogo tipo : listaTipos) {
      listaTiposConvcertidos.add(convertToDTO(tipo));
    }
    return listaTiposConvcertidos;
  }

}

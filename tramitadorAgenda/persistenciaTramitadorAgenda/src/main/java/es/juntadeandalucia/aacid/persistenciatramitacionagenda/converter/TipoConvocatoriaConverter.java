package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import es.juntadeandalucia.aacid.comuntramitacion.dto.TipoConvocatoriaDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TipoConvocatoria;

public class TipoConvocatoriaConverter {

  /**
   * Conversor tipoConvocatoriaDto
   * 
   * @param tipoConvocatoriaDao
   * @return {@link TipoConvocatoriaDTO}
   */
  public static TipoConvocatoriaDTO convertirTipoConvDaoToDTO(TipoConvocatoria tipoConvocatoriaDao) {
    TipoConvocatoriaDTO tipoConvocatoriaDTO = new TipoConvocatoriaDTO();

    if (null != tipoConvocatoriaDao) {
      tipoConvocatoriaDTO.setId(tipoConvocatoriaDao.getTconvNuId());
      tipoConvocatoriaDTO.setDenominacion(tipoConvocatoriaDao.getTconvLiDenominacion());
    }
    return tipoConvocatoriaDTO;
  }
}

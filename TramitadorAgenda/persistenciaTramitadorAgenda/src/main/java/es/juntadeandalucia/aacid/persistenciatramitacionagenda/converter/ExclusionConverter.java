package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ExclusionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.TipoConvocatoriaDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Exclusion;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;

public final class ExclusionConverter {

  private ExclusionConverter() {
    super();
  }

  /**
   * Conversor Exclusiones
   * 
   * @param exclusion
   * @param perfil
   * 
   * @return {@link TipoConvocatoriaDTO}
   */
  public static ExclusionDTO convertirToDTO(Exclusion exclusion, List<Perfil> perfil) {
    ExclusionDTO exclusionDTO = new ExclusionDTO();

    if (null != exclusion) {
      exclusionDTO.setDescripcion(exclusion.getExclLiDescripcion());
      exclusionDTO.setIdExclusion(exclusion.getExclNuId());

      boolean puedeEditar = false;
      for (Perfil p : perfil) {
        if (exclusion.getExclLiPerfil().contains(p.getNombre())) {
          puedeEditar = true;
          break;
        }
      }
      String idEditar = exclusion.getExclNuId().toString() + "-" + puedeEditar;
      exclusionDTO.setIdEditar(idEditar);
    }
    return exclusionDTO;
  }
}

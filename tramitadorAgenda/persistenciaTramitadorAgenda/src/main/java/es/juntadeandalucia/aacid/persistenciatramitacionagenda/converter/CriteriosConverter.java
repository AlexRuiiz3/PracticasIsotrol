package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionTipoCriterioDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Criterios;

public class CriteriosConverter {

  private CriteriosConverter() {
    super();
  }

  public static ValoracionTipoCriterioDTO convertirToDTO(List<Criterios> criterios) {
    ValoracionCriterioDTO valoracionCriterioDTO = null;
    List<ValoracionCriterioDTO> listaValoraciones = new ArrayList<>();
    ValoracionTipoCriterioDTO valoracionTipoCriterioDTO = new ValoracionTipoCriterioDTO();
    BigDecimal valMax = BigDecimal.ZERO;
    if (null != criterios) {
      for (Criterios crit : criterios) {
        valoracionCriterioDTO = new ValoracionCriterioDTO();
        valoracionCriterioDTO.setDescripcion((crit.getCritTxDescripcion()));
        valoracionCriterioDTO.setIdCriterio(crit.getCritNuId());
        valoracionCriterioDTO.setValorMaximo(crit.getCritNuValMax());
        valMax = valMax.add(crit.getCritNuValMax());
        listaValoraciones.add(valoracionCriterioDTO);
      }
      valoracionTipoCriterioDTO.setListaValoraciones(listaValoraciones);
      valoracionTipoCriterioDTO.setPuntuacionMaxima(valMax);
    }

    return valoracionTipoCriterioDTO;
  }
}

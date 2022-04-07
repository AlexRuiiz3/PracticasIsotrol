package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import es.juntadeandalucia.aacid.comuntramitacion.dto.EntidadesContribucionesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.GastoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.GastosContribucionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValidaPresupuestoDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IEntidadContribucionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IGastoContribucionService;

public class GastoContribucionServiceImpl implements IGastoContribucionService {

  private IEntidadContribucionService entidadContribucionService;

  @Override
  public void anyadeGastoContribucionAEntidad(final ValidaPresupuestoDTO validaPresupuestoDTO) {
    for (final EntidadesContribucionesDTO ent : validaPresupuestoDTO.getListaEntidadesSelect()) {
      almacenaValoresAIyAII(ent, validaPresupuestoDTO.getListaGastosAI(), validaPresupuestoDTO.getListaGastosAII());
    }
  }

  private GastosContribucionDTO creaEntidadGastosContribucion(final GastoDTO gasto) {
    final GastosContribucionDTO gastosContribucion = new GastosContribucionDTO();
    gastosContribucion.setValor(BigDecimal.ZERO);
    gastosContribucion.setValorTitle("0,00");
    gastosContribucion.setGasto(gasto);
    gastosContribucion.setValorNoValidado(BigDecimal.ZERO);
    gastosContribucion.setValorNoValidadoTitle("0,00");
    gastosContribucion.setValorValidado(BigDecimal.ZERO);
    gastosContribucion.setValorValidadoTitle("0,00");
    return gastosContribucion;
  }

  private void almacenaValoresAIyAII(final EntidadesContribucionesDTO ent, final List<GastoDTO> listaGastosAI, final List<GastoDTO> listaGastosAII) {
    if (CollectionUtils.isEmpty(ent.getValoresAI())) {
      ent.setValoresAI(new ArrayList<>());
      for (final GastoDTO gastoDTO : listaGastosAI) {
        final GastosContribucionDTO gastosContribucion = creaEntidadGastosContribucion(gastoDTO);
        ent.getValoresAI().add(gastosContribucion);
      }
    }
    if (CollectionUtils.isEmpty(ent.getValoresAII())) {
      ent.setValoresAII(new ArrayList<>());
      for (final GastoDTO gastoDTO : listaGastosAII) {
        final GastosContribucionDTO gastosContribucion = creaEntidadGastosContribucion(gastoDTO);
        if (gastoDTO.getCodigo().equals("B") && !entidadContribucionService.esEntidadPublica(ent)) {
          gastosContribucion.setDisabled(true);
        }
        ent.getValoresAII().add(gastosContribucion);
      }
    }
  }

  /**
   * Establece el valor de la propiedad entidadContribucionService
   *
   * @param entidadContribucionService
   *          el entidadContribucionService para establecer
   */
  public void setEntidadContribucionService(final IEntidadContribucionService entidadContribucionService) {
    this.entidadContribucionService = entidadContribucionService;
  }

}

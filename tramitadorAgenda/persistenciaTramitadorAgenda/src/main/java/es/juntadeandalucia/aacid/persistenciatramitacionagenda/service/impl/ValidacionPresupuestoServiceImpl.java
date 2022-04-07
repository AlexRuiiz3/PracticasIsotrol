package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.GastoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValidaPresupuestoDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.GastosConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Gasto;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IValidacionPresupuestoService;

public class ValidacionPresupuestoServiceImpl implements IValidacionPresupuestoService {

  @Override
  public void estableceGastosIyII(final List<Gasto> listaGasto, final ValidaPresupuestoDTO validaPresupuestoDTO) {
    GastoDTO gastoDTO;
    for (final Gasto gasto : listaGasto) {
      gastoDTO = GastosConverter.gastosToGastosDTO(gasto);
      validaPresupuestoDTO.getListaGastos().add(gastoDTO);
      if (gasto.getGastCoCodigo().contains(ConstantesTramitacion.GASTOS_AI)) {
        validaPresupuestoDTO.getListaGastosAI().add(gastoDTO);
      } else {
        validaPresupuestoDTO.getListaGastosAII().add(gastoDTO);
      }
    }
  }
}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.EntidadesContribucionesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.GastoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.GastosContribucionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValidaPresupuestoDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IGastoDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Gasto;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IEntidadContribucionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IGastosService;

public class GastosServiceImpl implements IGastosService {

  private IGastoDao gastoDao;
  private IEntidadContribucionService entidadContribucionService;

  @Override
  public void anyadeGastosAEntidad(final ValidaPresupuestoDTO validaPresupuestoDTO) {
    for (final EntidadesContribucionesDTO ent : validaPresupuestoDTO.getListaEntidadesSelect()) {
      ent.setListaNuevaEntidad(new ArrayList<>());
      for (final GastoDTO gasto : validaPresupuestoDTO.getListaGastos()) {
        final GastosContribucionDTO nuevoGasto = new GastosContribucionDTO();
        nuevoGasto.setGasto(gasto);
        nuevoGasto.setValor(BigDecimal.ZERO);
        nuevoGasto.setValorNoValidado(BigDecimal.ZERO);
        nuevoGasto.setValorValidado(BigDecimal.ZERO);
        nuevoGasto.setValorTitle("0,00");
        nuevoGasto.setValorNoValidadoTitle("0,00");
        nuevoGasto.setValorValidadoTitle("0,00");
        if (gasto.getCodigo().equals("B") && !entidadContribucionService.esEntidadPublica(ent)) {
          nuevoGasto.setDisabled(true);
        }
        if (gasto.getCodigo().contains(ConstantesTramitacion.GASTOS_AI)
            && ent.getValoresAI().stream().noneMatch(v -> v.getGasto().getCodigo().equals(gasto.getCodigo()))) {
          ent.getValoresAI().add(nuevoGasto);
        }
        if ((StringUtils.contains(gasto.getCodigo(), ConstantesTramitacion.GASTOS_AII)
            || StringUtils.contains(gasto.getCodigo(), ConstantesTramitacion.GASTOS_B))
            && ent.getValoresAI().stream().noneMatch(v -> v.getGasto().getCodigo().equals(gasto.getCodigo()))) {
          ent.getValoresAII().add(nuevoGasto);
        }
        validaPresupuestoDTO.getListaNuevaEntidadBack().add(nuevoGasto);
      }
    }

  }

  @Override
  public Gasto obtieneGastosById(final Long idGasto) {
    return gastoDao.obtieneGastosById(idGasto);
  }

  @Override
  public List<Gasto> obtieneGastosByFinalidad(final Integer idFinalidad) {
    return gastoDao.obtieneGastosByFinalidad(idFinalidad);
  }

  public void setGastoDao(final IGastoDao gastoDao) {
    this.gastoDao = gastoDao;
  }

  public void setEntidadContribucionService(final IEntidadContribucionService entidadContribucionService) {
    this.entidadContribucionService = entidadContribucionService;
  }
}

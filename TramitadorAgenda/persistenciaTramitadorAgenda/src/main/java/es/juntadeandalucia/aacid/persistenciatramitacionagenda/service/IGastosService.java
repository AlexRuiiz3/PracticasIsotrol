package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ValidaPresupuestoDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Gasto;

public interface IGastosService {

  Gasto obtieneGastosById(Long idGasto);

  List<Gasto> obtieneGastosByFinalidad(Integer idFinalidad);

  void anyadeGastosAEntidad(ValidaPresupuestoDTO validaPresupuestoDTO);
}

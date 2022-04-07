package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ValidaPresupuestoDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Gasto;

public interface IValidacionPresupuestoService {

  void estableceGastosIyII(List<Gasto> gasto, ValidaPresupuestoDTO validaPresupuestoDTO);

}
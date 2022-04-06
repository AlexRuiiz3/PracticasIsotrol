package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Gasto;

public interface IGastoDao {

  Gasto obtieneGastosById(Long idGasto);

  List<Gasto> obtieneGastosByFinalidad(Integer idFinalidad);
}

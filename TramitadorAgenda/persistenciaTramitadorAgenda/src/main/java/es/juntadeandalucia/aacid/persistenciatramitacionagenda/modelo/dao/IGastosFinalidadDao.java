package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

public interface IGastosFinalidadDao {

  List<Long> obtieneGastosFinalidadByFinalidad(Long idFinalidad);
}

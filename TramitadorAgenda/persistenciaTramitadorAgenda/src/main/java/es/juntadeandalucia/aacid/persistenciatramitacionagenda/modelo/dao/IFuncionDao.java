package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Funcion;

public interface IFuncionDao {

  Funcion obtenerFuncionByCodigo(String codigo);
}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Funcion;

public interface IFuncionService {

  Funcion obtenerFuncionByCodigo(String codigo);

}

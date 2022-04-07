package es.juntadeandalucia.aacid.comuntramitacion.service;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;

public interface IParametrosConfiguracionService {

  String obtenerParametroConfiguracion(String plazoComunicacionInicioMeses, String procedimiento) throws TramitacionException;

}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;

public interface ITipoCriteriosService {

  Long obtenerIdTipoCriterioByConvocatoriaFinalidadNombre(String finalidad, String nombre) throws TramitacionException;

  String obtenerNombreTipoCriterioByidCriterio(final Long idCriterio) throws TramitacionException;

}

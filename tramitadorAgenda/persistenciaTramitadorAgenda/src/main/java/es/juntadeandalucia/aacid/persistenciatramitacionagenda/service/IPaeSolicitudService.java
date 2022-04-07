package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeSolicitudes;

public interface IPaeSolicitudService {

  PaeSolicitudes obtenerSolicitudExpByCodigo(String codigoIdentificativo) throws TramitacionException;

  Long getNumExpByCodigoIdentificativo(String codigoIdentificativo) throws TramitacionException;

}

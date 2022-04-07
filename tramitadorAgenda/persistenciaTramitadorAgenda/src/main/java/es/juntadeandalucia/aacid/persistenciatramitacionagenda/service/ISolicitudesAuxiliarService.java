package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudAuxiliarDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionSolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;

public interface ISolicitudesAuxiliarService {

  SolicitudAuxiliarDTO findByIdSolicitud(Long idSolicitud) throws TramitacionException;

  int guardarValoracion(ValoracionSolicitudDTO valoracionSolicitudDTO) throws TramitacionException;

}
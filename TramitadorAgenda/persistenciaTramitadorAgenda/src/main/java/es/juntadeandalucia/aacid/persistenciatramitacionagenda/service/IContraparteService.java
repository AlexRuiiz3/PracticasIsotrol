package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.EntidadesContribucionesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Contrapartes;

public interface IContraparteService {

  List<Contrapartes> obtenerContrapartesBySolicitud(Long idSolicitud) throws TramitacionException;

  int saveOrUpdateContraparte(Contrapartes contrapartes, Long idSolicitud) throws TramitacionException;

  void obtenerContrapartes(SolicitudDatosGeneralesDTO solicitud, List<EntidadesContribucionesDTO> listaEntidades) throws TramitacionException;
}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ContribucionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.EntidadesContribucionesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Contribuciones;

public interface IContribucionesService {

  Contribuciones saveOrUpdateContribuciones(Contribuciones contribucion) throws TramitacionException;

  List<Contribuciones> obtenerContribucionesBySolicitud(Long idSolicitud) throws TramitacionException;

  void obtenerContribuciones(List<ContribucionDTO> listaContribucionesDTO, SolicitudDatosGeneralesDTO solicitud) throws TramitacionException;

  void guardarContribuciones(EntidadesContribucionesDTO entidadContribucion, SolicitudDatosGeneralesDTO solicitud) throws TramitacionException;

}

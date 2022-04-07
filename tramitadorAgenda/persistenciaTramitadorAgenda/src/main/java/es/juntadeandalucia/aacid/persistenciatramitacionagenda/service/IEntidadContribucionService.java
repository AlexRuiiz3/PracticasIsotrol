package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ContribucionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.EntidadesContribucionesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValidaPresupuestoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;

public interface IEntidadContribucionService {

  boolean esEntidadPublica(EntidadesContribucionesDTO cont);

  void estableceContribuciones(SolicitudDatosGeneralesDTO solicitud, EntidadesContribucionesDTO nuevaAacid, EntidadesContribucionesDTO nuevaSol,
      ValidaPresupuestoDTO validaPresupuestoDTO) throws TramitacionException;

  void obtenerEntidadesParticipantes(SolicitudDatosGeneralesDTO solicitud, List<EntidadesContribucionesDTO> listaEntidades) throws TramitacionException;

  void establecerEntidadesOtrasAportaciones(ContribucionDTO contribucionDTO, List<EntidadesContribucionesDTO> listaEntidades);

  void establecerContribucionesEntidadesParticipantes(ContribucionDTO contribucionDTO, SolicitudDatosGeneralesDTO solicitud,
      List<EntidadesContribucionesDTO> listaEntidades) throws TramitacionException;

  void establecerContribucionesContrapartesSolicitud(ContribucionDTO contribucionDTO, SolicitudDatosGeneralesDTO solicitud,
      List<EntidadesContribucionesDTO> listaEntidades) throws TramitacionException;

  void establecerContribucionesConContraparte(ContribucionDTO contribucionDTO, SolicitudDatosGeneralesDTO solicitud,
      List<EntidadesContribucionesDTO> listaEntidades);

}

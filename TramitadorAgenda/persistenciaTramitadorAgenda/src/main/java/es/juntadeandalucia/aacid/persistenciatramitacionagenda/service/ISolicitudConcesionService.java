package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudConcesionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.SolicitudConcesion;
import trewa.exception.TrException;

public interface ISolicitudConcesionService {

  SolicitudConcesion obtenerSolicitudConcesionPorSolicitud(Long idSolicitud) throws TramitacionException;

  List<SolicitudConcesion> obtenerSolicitudConcesionPorSolicitud(List<SolicitudConcesionDTO> listaExpedientesHijosConvocatoriaDTO) throws TramitacionException;

  List<SolicitudConcesionDTO> obtenerBeneficiariasSuplentes(String idExp, String fases, String finalidad, boolean ongd)
      throws TrException, TramitacionException;

  List<SolicitudConcesion> guardarListaSolicitudConcesion(final List<SolicitudConcesionDTO> listaSolicitudConcesionDTO, final Long convocatoria)
      throws TramitacionException;

}

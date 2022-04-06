package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.math.BigDecimal;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.SolicitudConcesion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeContribuciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeSolicitudes;

public interface IPaeContribucionesService {

  public List<PaeContribuciones> getPaeContribucionesBySolicitud(Long idSolicitud) throws TramitacionException;

  public void estableceValorEconomicoConcesion(final SolicitudDTO solicitud, final boolean isUniv, final SolicitudConcesion solicitudConcesion,
      final boolean isExcluida) throws TramitacionException;

  public BigDecimal obtenerImporteAacid(PaeSolicitudes sol) throws TramitacionException;
}

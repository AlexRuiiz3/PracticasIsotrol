package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import org.apache.commons.lang.StringUtils;

import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IFinalidadDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Finalidad;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IFinalidadService;

public class FinalidadServiceImpl implements IFinalidadService {

  private IFinalidadDao finalidadDao;
  private SolicitudServiceImpl solicitudService;

  @Override
  public Finalidad obtenerFinalidadByCodigo(final String codigo) {
    return finalidadDao.obtenerFinalidadByCodigo(codigo);
  }

  @Override
  public String obtenerFinalidadByNumExpediente(final Long numExpediente, final boolean isUniv) throws TramitacionException {
    return finalidadDao.obtenerFinalidadByNumExpediente(numExpediente, isUniv);
  }

  @Override
  public String obtenerCodFinalidadByNumExpediente(final Long numExpediente, final boolean isUniv) throws TramitacionException {
    return finalidadDao.obtenerCodFinalidadByNumExpediente(numExpediente, isUniv);
  }

  @Override
  public boolean filtrarSubFinalidad(final String finalidad, final String numExpdiente) throws TramitacionException {
    boolean filtrarSubFinalidad = false;
    if (StringUtils.isNotBlank(finalidad) && finalidad.equalsIgnoreCase("F")) {
      final SolicitudDatosGeneralesDTO solicitudDatosGeneralesDTO = solicitudService.datosGeneralesSolicitudByIdExpTrewa(numExpdiente);
      if (solicitudDatosGeneralesDTO.getTxCodidentificativo().contains("F") || solicitudDatosGeneralesDTO.getTxCodidentificativo().contains("PF")) {
        filtrarSubFinalidad = true;
      }
    }
    return filtrarSubFinalidad;
  }

  public IFinalidadDao getFinalidadDao() {
    return finalidadDao;
  }

  public void setFinalidadDao(final IFinalidadDao finalidadDao) {
    this.finalidadDao = finalidadDao;
  }

  /**
   * Establece el valor de la propiedad solicitudService
   *
   * @param solicitudService
   *          el solicitudService para establecer
   */
  public void setSolicitudService(final SolicitudServiceImpl solicitudService) {
    this.solicitudService = solicitudService;
  }

}

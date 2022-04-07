package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IPaeAgrupacionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeAgrupaciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeAgrupacionesService;

public class PaeAgrupacionesService implements IPaeAgrupacionesService {
  private final Logger log = Logger.getLogger(PaeAgrupacionesService.class);

  private IPaeAgrupacionDao paeAgrupacionDao;

  @Override
  public List<PaeAgrupaciones> getAgrupacionesBySolicitud(Long idSolicitud) {
    log.debug("Init getAgrupacionesBySolicitud");
    List<PaeAgrupaciones> agrupaciones = new ArrayList<>();
    try {
      agrupaciones = paeAgrupacionDao.getPaeAgrupacionesBySolicitud(idSolicitud);
    } catch (TramitacionException e) {
      log.error(e.getMessage(), e);
    }
    return agrupaciones;
  }

  public IPaeAgrupacionDao getAgrupacionDao() {
    return paeAgrupacionDao;
  }

  public void setAgrupacionDao(IPaeAgrupacionDao paeAgrupacionDao) {
    this.paeAgrupacionDao = paeAgrupacionDao;
  }

}

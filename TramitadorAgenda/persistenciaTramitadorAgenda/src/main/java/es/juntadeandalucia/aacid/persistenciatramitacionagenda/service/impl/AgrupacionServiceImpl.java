package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IAgrupacionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Agrupaciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IAgrupacionService;

public class AgrupacionServiceImpl implements IAgrupacionService {
  private final Logger log = Logger.getLogger(AgrupacionServiceImpl.class);

  private IAgrupacionDao agrupacionDao;

  @Override
  public List<Agrupaciones> getAgrupacionesBySolicitud(final Long idSolicitud) {
    log.debug("Init getAgrupacionesBySolicitud");
    List<Object> objetosAgrupaciones = new ArrayList<>();
    final List<Agrupaciones> agrupaciones = new ArrayList<>();
    try {
      objetosAgrupaciones = agrupacionDao.getAgrupacionesBySolicitud(idSolicitud);
      for (final Object objetoAgrupaciones : objetosAgrupaciones) {
        final String[] arrayAgrupaciones = (String[]) objetoAgrupaciones;
        final Agrupaciones agrupacion = new Agrupaciones();
        agrupacion.setAgrDenominacion(arrayAgrupaciones[0]);
        agrupacion.setAgrInscripcion(arrayAgrupaciones[1]);
        agrupaciones.add(agrupacion);
      }
    } catch (final TramitacionException e) {
      log.error(e.getMessage(), e);
    }
    log.debug("End getAgrupacionesBySolicitud");
    return agrupaciones;
  }

  @Override
  public int saveOrUpdateAgrupacion(final Agrupaciones agrupacion, final Long idSolicitud) throws TramitacionException {
    log.debug("Init saveOrUpdateAgrupacion");
    return agrupacionDao.saveOrUpdateAgrupacion(agrupacion, idSolicitud);
  }

  public IAgrupacionDao getAgrupacionDao() {
    return agrupacionDao;
  }

  public void setAgrupacionDao(final IAgrupacionDao agrupacionDao) {
    this.agrupacionDao = agrupacionDao;
  }
}

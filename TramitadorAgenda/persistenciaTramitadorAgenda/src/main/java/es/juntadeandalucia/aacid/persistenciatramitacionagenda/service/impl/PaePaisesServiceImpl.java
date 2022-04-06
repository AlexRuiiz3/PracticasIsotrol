package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import org.apache.log4j.Logger;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IPaePaisesDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaePaises;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaePaisesService;

public class PaePaisesServiceImpl implements IPaePaisesService{
	private final Logger log = Logger.getLogger(PaePaisesServiceImpl.class);


	private IPaePaisesDao paisesDao;

	public PaePaises getPaisById(Long id){
		log.debug("Init getPaisById");
		try {
			PaePaises paePaises=paisesDao.getPaisById(id);
			log.debug("End getPaisById");
			return paePaises;
		}catch(TramitacionException e) {
			log.error(e.getMessage(),e);
		}
		log.debug("End getPaisById");
		return null;
	}

	public IPaePaisesDao getPaisesDao() {
		return paisesDao;
	}

	public void setPaisesDao(IPaePaisesDao paisesDao) {
		this.paisesDao = paisesDao;
	}

}

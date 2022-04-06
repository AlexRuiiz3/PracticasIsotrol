package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IPaeContrapartesDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeContrapartes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeContrapartesService;

public class PaeContrapartesServiceImpl implements IPaeContrapartesService{

	private final Logger log = Logger.getLogger(PaeContrapartesServiceImpl.class);

	private IPaeContrapartesDao paeContrapartesDao;
	
	@Override
	public List<PaeContrapartes> getPaeContrapartesByPaeSolicitud(Long idSolicitud) throws TramitacionException {
		log.debug("Init getPaeContrapartesByPaeSolicitud");
		List<PaeContrapartes> contrapartes=new ArrayList<>();
		try {
			contrapartes=paeContrapartesDao.getPaeContrapartesByPaeSolicitud(idSolicitud);
		} catch (TramitacionException e) {
			log.error(e.getMessage(), e);
		}
		return contrapartes;
	}

}

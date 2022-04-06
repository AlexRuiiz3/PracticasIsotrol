package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ConvocatoriaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ICriteriosConvocatoriaDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.CriteriosConvocatoria;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Finalidad;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ICriteriosConvocatoriaService;

public class CriteriosConvocatoriaServiceImpl implements ICriteriosConvocatoriaService{

	private ICriteriosConvocatoriaDao criteriosConvocatoriaDao;
	
	@Override
	public List<CriteriosConvocatoria> getListaCriteriosPadrePest1(ConvocatoriaDTO conv, Finalidad finalidad)
			throws TramitacionException {
		return criteriosConvocatoriaDao.getListaCriteriosPadrePest1(conv, finalidad);
	}

	@Override
	public List<CriteriosConvocatoria> getListaCriteriosPadre(ConvocatoriaDTO conv, Finalidad finalidad, long pestana) throws TramitacionException {
		return criteriosConvocatoriaDao.getListaCriteriosPadre(conv, finalidad, pestana);
	}

	public ICriteriosConvocatoriaDao getCriteriosConvocatoriaDao() {
		return criteriosConvocatoriaDao;
	}

	public void setCriteriosConvocatoriaDao(ICriteriosConvocatoriaDao criteriosConvocatoriaDao) {
		this.criteriosConvocatoriaDao = criteriosConvocatoriaDao;
	}

}

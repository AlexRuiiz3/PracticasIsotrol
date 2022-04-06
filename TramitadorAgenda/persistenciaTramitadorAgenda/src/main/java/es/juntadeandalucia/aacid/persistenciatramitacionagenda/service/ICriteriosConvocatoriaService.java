package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ConvocatoriaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.CriteriosConvocatoria;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Finalidad;

public interface ICriteriosConvocatoriaService {

	public List<CriteriosConvocatoria> getListaCriteriosPadrePest1(ConvocatoriaDTO conv,
            Finalidad finalidad)  throws TramitacionException;
	
	public List<CriteriosConvocatoria> getListaCriteriosPadre(ConvocatoriaDTO conv, Finalidad finalidad, long pestana) throws TramitacionException;
}

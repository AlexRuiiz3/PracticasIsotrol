package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ConvocatoriaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ICriteriosConvocatoriaDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.CriteriosConvocatoria;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Finalidad;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class CriteriosConvocatoriaDao  extends CustomHibernateDaoSupport  implements ICriteriosConvocatoriaDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<CriteriosConvocatoria> getListaCriteriosPadrePest1(ConvocatoriaDTO conv,Finalidad finalidad) throws TramitacionException {
		try{
			   return getEntityManager().createQuery("SELECT c FROM CriteriosConvocatoria cc "
				          + "LEFT JOIN c.critNuId tc "
				          + "LEFT JOIN tc.finaNuId f "
				          + "WHERE cc.convocatoria.convNuId= ? "
				          + "AND cc.finalidad.finaNuId = ? "
				          + "AND cc.criterioPadre is null "
				          + "AND cc.criterios.tipoValoracion.tivaNuId=1 "
				          + "ORDER BY cc.criterios.critNuId")
					   .setParameter(0, conv.getIdConv())
					   .setParameter(1, finalidad.getFinaNuId()).getResultList();
			   
		}catch (Exception e) {
			throw new TramitacionException("Se ha producido un error al listar los criterios del padre para la primera pestanya de valoracion. Causa: " + e.getMessage());
		}
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<CriteriosConvocatoria> getListaCriteriosPadre(ConvocatoriaDTO conv,Finalidad finalidad,long pestana) throws TramitacionException {
		try{
			   return getEntityManager().createQuery("SELECT c FROM CriteriosConvocatoria cc "
				          + "LEFT JOIN c.critNuId tc "
				          + "LEFT JOIN tc.finaNuId f "
				          + "WHERE cc.convocatoria.convNuId= ? "
				          + "AND cc.finalidad.finaNuId = ? "
				          + "AND cc.criterioPadre is null "
				          + "AND cc.criterios.tipoValoracion.tivaNuId=? "
				          + "ORDER BY cc.criterios.critNuId")
					   .setParameter(0, conv.getIdConv())
					   .setParameter(1, finalidad.getFinaNuId())
					   .setParameter(2, pestana)
					   .getResultList();
			   
		}catch (Exception e) {
			throw new TramitacionException("Se ha producido un error al listar los criterios del padre para la primera pestanya de valoracion. Causa: " + e.getMessage());
		}
	}
}

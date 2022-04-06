package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;
import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesBoolean;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ISubsanacionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Subsanacion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

/**
 * SubsanacionRepository class.
 * 
 * @author isotrol.
 *
 */
@Repository
public class SubsanacionDao extends CustomHibernateDaoSupport implements ISubsanacionDao {


	@Override
	public Subsanacion saveOrUpdateSubsanacion(Subsanacion subsanacion) throws TramitacionException {

		try{
			return getEntityManager().merge(subsanacion);
		} catch (Exception e){
			throw new TramitacionException("Se ha producido un error al guardar o actualizar la subsanación.", e);
		}


	}

	@Override
	public int updateSubsanacionSolicitudRelation(String idSubsanacion, String idSolicitud) throws TramitacionException {
		getEntityManager().setFlushMode(FlushModeType.AUTO);
		int resultado = 0;
		try {
			resultado = getEntityManager().createNativeQuery("UPDATE AACI_SUBSANACION SET FK_SOLICITUD = ? WHERE SUB_NU_ID = ?")
					.setParameter(1, idSolicitud).setParameter(2, idSubsanacion)
					.executeUpdate();
			getEntityManager().flush();
			getEntityManager().clear();
		} catch (Exception e) {
			throw new TramitacionException("Se ha producido un error al guardar la relación entre la subsanacion ${idSubsanacion} con la solicitud ${idSolicitud}", e);
		}
		return resultado;
	}

	@Override
	public Subsanacion getSubsanacionByIdExpediente (Long idExpediente) throws TramitacionException{
		try {
			return getEntityManager().createQuery(
					"select new Subsanacion(sub.subNuId, sub.subFhSubsanacion, sub.subLiObservaciones, sub.subLiMotivoDesestimacion, "
							+ "sub.subLiLugarRegistro, sub.subLgAlmeria, sub.subLgCadiz, sub.subLgCordoba, "
							+ "sub.subLgGranada, sub.subLgHuelva, sub.subLgJaen, sub.subLgMalaga, sub.subLgSevilla, "
							+ "sub.subLgAfrica, sub.subLgPrevRiesgos, sub.subLgServicios, sub.subLgOtros, "
							+ "sub.noEspecifica, sub.subFhRegistro, sub.subFhEntrada, sub.subFhLimite, sub.subFhEntrega)"
							+ "from Subsanacion sub " + "left join sub.aaciTSolicitudsubongdByFkSolicitud sol "
							+ "where sol.nuNumexpediente = :idExpediente", Subsanacion.class)
					.setParameter("idExpediente", idExpediente).getSingleResult();
		}catch (NoResultException nre){
			//No existe subsanacion.
		}catch (Exception e){
			throw new TramitacionException("Error al obtener la subsanación a través del número expediente de la solicitud", e);
		}
		return null;
	}

	@Override
	public Subsanacion getById (Integer id) throws TramitacionException {
		try {
			return getEntityManager().createQuery(
					"select new Subsanacion(sub.subNuId, sub.subFhSubsanacion, sub.subLiObservaciones, sub.subLiMotivoDesestimacion, "
					+ "sub.subLiLugarRegistro, sub.subLgAlmeria, sub.subLgCadiz, sub.subLgCordoba, "
					+ "sub.subLgGranada, sub.subLgHuelva, sub.subLgJaen, sub.subLgMalaga, sub.subLgSevilla, "
					+ "sub.subLgAfrica, sub.subLgPrevRiesgos, sub.subLgServicios, sub.subLgOtros, "
					+ "sub.noEspecifica, sub.subFhRegistro, sub.subFhEntrada, sub.subFhLimite, sub.subFhEntrega)"
							+ "from Subsanacion sub " + "where sub.subNuId = :idSubsanacion", Subsanacion.class)
					.setParameter("idSubsanacion", id).getSingleResult();
		} catch (NoResultException nre){
			//No existe subsanacion.
		} catch (Exception e){
			throw new TramitacionException("Error al obtener la subsanación por su id", e);
		}
		return null;
	}

	public void deleteSubsanacion(Subsanacion subsanacion)  throws TramitacionException {
		try {
			getEntityManager().setFlushMode(FlushModeType.AUTO);
			int resultado = getEntityManager().createNativeQuery("DELETE from AACI_SUBSANACION WHERE SUB_NU_ID = ?")
					.setParameter(1, subsanacion.getSubNuId())
					.executeUpdate();
			getEntityManager().flush();
			getEntityManager().clear();
			if (resultado != 1){
				throw new TramitacionException("Error al borrar la subsanacion");
			}
		} catch (Exception e){
			throw new TramitacionException("Error al eliminar la subsanación", e);
		}
	}

	public void removeSolicitudRelation (Subsanacion subsanacion) throws TramitacionException {
		try {
			getEntityManager().setFlushMode(FlushModeType.AUTO);
			int resultado = getEntityManager().createNativeQuery("UPDATE AACI_SUBSANACION SET FK_SOLICITUD = null WHERE SUB_NU_ID = ?")
					.setParameter(1, subsanacion.getSubNuId())
					.executeUpdate();
			getEntityManager().flush();
			getEntityManager().clear();
			if (resultado != 1){
				throw new TramitacionException("Error al quitar la relacion de la solicitud con la subsanción");
			}
		} catch (Exception e){
			throw new TramitacionException("Error al quitar la relacion de la solicitud con la subsanción", e);
		}
	}

	@Override
	public void updateSubsanacion (Subsanacion subsanacion) throws TramitacionException {
		try {
			int resultado = getEntityManager().createNativeQuery(
					"UPDATE AACI_SUBSANACION SET SUB_LI_OBSERVACIONES = ?, SUB_LI_MOTIVO_DESESTIMACION = ?, " + "SUB_LG_ALMERIA = ?, SUB_LG_CADIZ = ?, SUB_LG_CORDOBA=?, SUB_LG_GRANADA=?, SUB_LG_HUELVA=?, SUB_LG_JAEN=?, SUB_LG_MALAGA=?, "
							+ "SUB_LG_SEVILLA=?, SUB_LG_AFRICA=?, SUB_LI_LUGAR_REGISTRO = ?, "
							+ "SUB_LG_SERVICIOS=?, SUB_LG_PREV_RIESGOS=?, SUB_LG_OTROS=?, SUB_LG_NO_ESPECIFICA=? WHERE SUB_NU_ID = ?")
					.setParameter(1, subsanacion.getSubLiObservaciones())
					.setParameter(2, subsanacion.getSubLiMotivoDesestimacion()).setParameter(3, UtilidadesBoolean.convertirBooleanNumero(subsanacion.getSubLgAlmeria())).setParameter(4, UtilidadesBoolean.convertirBooleanNumero(subsanacion.getSubLgCadiz()))
					.setParameter(5, UtilidadesBoolean.convertirBooleanNumero(subsanacion.getSubLgCordoba())).setParameter(6, UtilidadesBoolean.convertirBooleanNumero(subsanacion.getSubLgGranada())).setParameter(7, subsanacion.getSubLgHuelva()).setParameter(8, UtilidadesBoolean.convertirBooleanNumero(subsanacion.getSubLgJaen()))
					.setParameter(9, UtilidadesBoolean.convertirBooleanNumero(subsanacion.getSubLgMalaga())).setParameter(10, UtilidadesBoolean.convertirBooleanNumero(subsanacion.getSubLgSevilla())).setParameter(11, UtilidadesBoolean.convertirBooleanNumero(subsanacion.getSubLgAfrica()))
					.setParameter(12, subsanacion.getSubLiLugarRegistro())
					.setParameter(13, subsanacion.getSubLgServicios()).setParameter(14, UtilidadesBoolean.convertirBooleanNumero(subsanacion.getSubLgPrevRiesgos())).setParameter(15, UtilidadesBoolean.convertirBooleanNumero(subsanacion.getSubLgOtros()))
					.setParameter(16, subsanacion.getNoEspecifica()).setParameter(17, subsanacion.getSubNuId()).executeUpdate();
			if (resultado!=1){
				throw new TramitacionException("Error al actualizar la subsanación");
			}
		}catch (Exception e){
			throw new TramitacionException("Error al actualizar la subsanación", e);
		}

	}

}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IPaeAgrupacionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeAgrupaciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class PaeAgrupacionesDao extends CustomHibernateDaoSupport implements IPaeAgrupacionDao{

	public List<PaeAgrupaciones> getPaeAgrupacionesBySolicitud(Long idSolicitud) throws TramitacionException {
		try {
			return getEntityManager().createQuery(
					"select agrup from PaeAgrupaciones agrup " + "where agrup.paeSolicitudesByFkSolicitud= :paeSolicitudesByFkSolicitud", PaeAgrupaciones.class)
					.setParameter("paeSolicitudesByFkSolicitud", idSolicitud).getResultList();
		}catch (NoResultException nre){
			return new ArrayList<>();
		}catch (Exception e) {
			throw new TramitacionException("Error obteniendo las agrupaciones para la solicitud: "
					+ idSolicitud);

		}
	}
}

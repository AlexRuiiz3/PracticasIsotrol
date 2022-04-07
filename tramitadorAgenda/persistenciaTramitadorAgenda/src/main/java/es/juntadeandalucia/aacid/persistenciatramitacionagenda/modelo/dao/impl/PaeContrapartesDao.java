package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IPaeContrapartesDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeContrapartes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class PaeContrapartesDao extends CustomHibernateDaoSupport implements IPaeContrapartesDao{

	@Override
	public List<PaeContrapartes> getPaeContrapartesByPaeSolicitud(Long idSolicitud) throws TramitacionException {
		try {
			return getEntityManagerAntiguo().createQuery(
					"select contrapartes from PaeContrapartes contrapartes where contrapartes.paeSolicitudesByFkSolicitud = :paeSolicitudesByFkSolicitud", PaeContrapartes.class)
					.setParameter("paeSolicitudesByFkSolicitud", idSolicitud).getResultList();

		}catch (NoResultException nre){
			return new  ArrayList<PaeContrapartes>();
		}catch (Exception e) {
			throw new TramitacionException("Error obteniendo las entidades contraaparte de la solicitud: "
					+ idSolicitud);

		}
		
	}

}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;


import javax.persistence.NoResultException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IPaePaisesDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaePaises;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class PaePaisesDaoImpl extends CustomHibernateDaoSupport implements IPaePaisesDao{

	@Override
	public PaePaises getPaisById(Long idPais) throws TramitacionException {

		try {
			return getEntityManagerAntiguo().createQuery(
					"select paises from PaePaises paises where paises.idPais = :idPais", PaePaises.class)
					.setParameter("idPais", idPais).getSingleResult();

		}catch (NoResultException nre){
			return  null;
		}catch (Exception e) {
			throw new TramitacionException("Error obteniendo la entidad pais con id: "
					+ idPais);

		}
	}

}

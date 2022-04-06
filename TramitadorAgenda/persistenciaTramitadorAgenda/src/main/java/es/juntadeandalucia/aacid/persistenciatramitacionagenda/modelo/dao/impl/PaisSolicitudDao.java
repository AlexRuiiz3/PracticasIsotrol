package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IPaisSolicitudDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.PaisesSolicitud;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

@Repository
public class PaisSolicitudDao extends CustomHibernateDaoSupport implements IPaisSolicitudDao {

	@Override
	public String obtenerPaisesSeparadosPorComaBySolicitud (Long idSolicitud){

		List<PaisesSolicitud> listaPaisesSol = getEntityManager().createQuery("select new PaisesSolicitud(p.id, p.aaciTPaisesByIdPais) from PaisesSolicitud p "
						+ "left join p.aaciTPaisesByIdPais "
						+ "where p.aaciTSolicitudsubongdByIdSolicitud.id = :idSolicitud ", PaisesSolicitud.class)
				.setParameter("idSolicitud", idSolicitud).getResultList();

		StringBuilder paises = new StringBuilder();
		if (listaPaisesSol!=null) {
			for (PaisesSolicitud pais : listaPaisesSol) {
				if (!paises.toString().isEmpty()) {
					paises.append(", ");
				}
				paises.append(pais.getAaciTPaisesByIdPais().getNombre());
			}
		}
		return paises.toString();
	}
}

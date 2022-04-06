package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IPaisDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Pais;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

@Repository
public class PaisDao extends CustomHibernateDaoSupport implements IPaisDao {

  @Override
  public List<Pais> obtenerPaisesSolicitud(final Long idSolicitud) throws TramitacionException {
    final List<Pais> paises = new ArrayList<>();
    try {
      @SuppressWarnings("unchecked")
      final List<Object> paisesObj = getEntityManager()
          .createNativeQuery("SELECT atp.* FROM AACI_T_PAISES atp INNER JOIN AACI_T_PAISES_SOLICITUD atps ON atps.ID_PAIS = ATP .ID_PAIS "
              + "INNER JOIN AACI_T_SOLICITUDSUBONGD ats ON ats.ID_SOLICITUD =atps.ID_SOLICITUD WHERE atps.ID_SOLICITUD = ?")
          .setParameter(1, idSolicitud).getResultList();
      Pais pais;
      for (final Object paisObj : paisesObj) {
        pais = new Pais();
        final Object[] obj = (Object[]) paisObj;
        pais.setIdPais(Long.valueOf(obj[0].toString()));
        pais.setNombre(obj[1].toString());
        pais.setCodigo(obj[2].toString());
        pais.setAnio(Integer.valueOf(obj[3].toString()));
        pais.setPuntuacion(UtilidadesNumero.convertStringToBigDecimalIfNotBlank(obj[4].toString()));
        paises.add(pais);
      }
      return paises;
    } catch (

    final NoResultException nre) {
      return new ArrayList<>();
    } catch (final Exception e) {
      throw new TramitacionException("Error obteniendo las contrapartes para la solicitud: " + idSolicitud + ". Causa: " + e.getMessage());
    }
  }
}

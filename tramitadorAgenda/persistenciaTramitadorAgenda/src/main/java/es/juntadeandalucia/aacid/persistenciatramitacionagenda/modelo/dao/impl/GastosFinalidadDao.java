package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.List;

import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IGastosFinalidadDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class GastosFinalidadDao extends CustomHibernateDaoSupport implements IGastosFinalidadDao {

  @SuppressWarnings("unchecked")
  @Override
  public List<Long> obtieneGastosFinalidadByFinalidad(final Long idFinalidad) {
    return getEntityManager().createNativeQuery("SELECT GAST_NU_ID FROM AACI_GASTOS_FINALIDAD WHERE FINA_NU_ID = :finalidad")
        .setParameter("finalidad", idFinalidad).getResultList();
  }

}

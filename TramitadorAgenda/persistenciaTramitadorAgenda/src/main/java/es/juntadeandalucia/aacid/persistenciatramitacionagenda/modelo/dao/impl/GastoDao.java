package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.List;

import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IGastoDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Gasto;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class GastoDao extends CustomHibernateDaoSupport implements IGastoDao {

  @Override
  public Gasto obtieneGastosById(final Long idGasto) {
    return getEntityManager().find(Gasto.class, idGasto);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Gasto> obtieneGastosByFinalidad(final Integer idFinalidad) {
    return getEntityManager().createQuery("SELECT g FROM Gasto g "//
        + " join g.aaciGastosFinalidades gf " //
        + "where gf.aaciFinalidad.finaNuId = :idFinalidad " //
        + "ORDER BY g.gastNuOrden").setParameter("idFinalidad", idFinalidad).getResultList();

  }
}

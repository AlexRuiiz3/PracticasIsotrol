package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import javax.persistence.NoResultException;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IPaeSoliciudDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeSolicitudes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class PaeSolicitudDao extends CustomHibernateDaoSupport implements IPaeSoliciudDao {

  @SuppressWarnings("unchecked")
  @Override
  public PaeSolicitudes getSolicitudExpByCodigo(final String codigoIdentificativo, final String esquema) throws TramitacionException {
    try {
      return (PaeSolicitudes) getEntityManagerAntiguo().createNativeQuery("SELECT sol.* "//
          + "FROM (select * "//
          + "  FROM pae_solicitudes sol "//
          + "  LEFT JOIN " + esquema + ".TR_EXPEDIENTES te ON te.X_EXPE =sol.EXP_TREWA " //
          + "  where te.T_NUM_EXP ='" + codigoIdentificativo//
          + "' ORDER BY ID_SOLICITUD ASC) sol WHERE rownum <= 1", PaeSolicitudes.class).getResultList().stream().findFirst().orElse(null);
    } catch (final NoResultException nre) {
      return null;
    } catch (final Exception e) {
      throw new TramitacionException("Error obteniendo la solicitud con codigo: " + codigoIdentificativo + ". Causa: " + e.getMessage());
    }
  }

  @Override
  public Long getNumExpByCodigo(final String codigoIdentificativo, final String esquema) throws TramitacionException {
    try {
      return getEntityManagerAntiguo().createQuery("select sol.EXP_TREWA FROM PAE_SOLICITUDES sol " //
          + " LEFT JOIN " + esquema + ".TR_EXPEDIENTES expe ON expe.X_EXPE = sol.EXP_TREWA " //
          + " WHERE ID_SOLICITUD = (SELECT ID_SOLICITUD FROM (select ID_SOLICITUD from PAE_SOLICITUDES "//
          + " where EXP_TREWA = EXPE.X_EXPE ORDER BY ID_SOLICITUD ASC) WHERE rownum <= 1 )" //
		  + " AND expe.T_NUM_EXP LIKE '" + codigoIdentificativo + "'", Long.class)//
		  .getResultList().stream().findFirst().orElse(null);
    } catch (final NoResultException nre) {
      return null;
    } catch (final Exception e) {
      throw new TramitacionException("Error obteniendo la solicitud con codigo: " + codigoIdentificativo + ". Causa: " + e.getMessage());
    }
  }
}

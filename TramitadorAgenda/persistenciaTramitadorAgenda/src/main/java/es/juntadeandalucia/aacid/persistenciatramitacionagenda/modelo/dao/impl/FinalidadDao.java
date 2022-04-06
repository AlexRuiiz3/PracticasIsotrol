package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import javax.persistence.NoResultException;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IFinalidadDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Finalidad;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class FinalidadDao extends CustomHibernateDaoSupport implements IFinalidadDao {

  @Override
  public Finalidad obtenerFinalidadByCodigo(final String codigo) {
    return getEntityManager().createQuery("SELECT f FROM Finalidad f WHERE f.finaCoCodigo =: codigo", Finalidad.class).setParameter("codigo", codigo)
        .getSingleResult();
  }

  @Override
  public String obtenerFinalidadByNumExpediente(final Long numExpediente, final boolean isUniv) throws TramitacionException {
    String resultado = "";
    try {
      if (!isUniv) {
        resultado = (String) getEntityManager().createNativeQuery(("SELECT "//
            + "CASE LG_CHECKFINALIDAD1 WHEN 1 " //
            + " THEN 'Cooperaci\u00F3n internacional para el desarrollo.' " //
            + " ELSE "//
            + " (CASE LG_CHECKFINALIDAD2 WHEN 1 " //
            + "   THEN 'Acci\u00F3n Humanitaria, excepto emergencia.' " //
            + "  ELSE "//
            + "   (CASE LG_CHECKFINALIDAD3 WHEN 1 " //
            + "     THEN 'Educaci\u00F3n para el desarrollo.' " //
            + "    ELSE "//
            + "     (CASE LG_CHECKFINALIDAD4 WHEN 1 " //
            + "       THEN 'Formaci\u00F3n, investigaci\u00F3n o innovaci\u00F3n al desarrollo' " //
            + "      END)"//
            + "    END)" //
            + "  END)" //
            + "END " //
            + "FROM AACI_T_SOLICITUDSUBONGD WHERE NU_NUMEXPEDIENTE = :numExpediente"))//
			.setParameter("numExpediente", numExpediente).getSingleResult();
      } else {
        resultado = (String) getEntityManager()
            .createNativeQuery(("SELECT " + "CASE LG_CHECKTIPO1 WHEN 1 "
                + " THEN 'Cooperaci\u00F3n Universitaria para el Desarrollo en pa\u00EDses priorizados.' " + "ELSE " + "   (CASE LG_CHECKTIPO2 WHEN 1 "
                + "     THEN 'Investigaci\u00F3n o Innovaci\u00F3n aplicados a la cooperaci\u00F3n internacional para el desarrollo.' " + "    ELSE "
                + "     (CASE LG_CHECKTIPO3 WHEN 1 " + "       THEN 'Educaci\u00F3n para el Desarrollo' " + "       ELSE "
                + "         (CASE LG_CHECKTIPO4 WHEN 1 " + "           THEN 'Formaci\u00F3n y Estudios sobre el Desarrollo' " + "       END) " + "     END) "
                + "  END) " + "END " + "FROM AACI_T_SOLICITUDSUBONGD WHERE NU_NUMEXPEDIENTE = :numExpediente"))
            .setParameter("numExpediente", numExpediente).getSingleResult();
      }
    } catch (final NoResultException nre) {
      return "No existen resultados";
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
    return resultado;
  }

  @Override
  public String obtenerCodFinalidadByNumExpediente(final Long numExpediente, final boolean isUniv) throws TramitacionException {
    String resultado = "";
    try {
      if (!isUniv) {
        resultado = (String) getEntityManager()
            .createNativeQuery(("SELECT " //
			+ "CASE LG_CHECKFINALIDAD1 WHEN 1 " //
			+ " THEN 'C' " //
			+ " ELSE " //
			+ " (CASE LG_CHECKFINALIDAD2 WHEN 1 " //
			+ " THEN 'AH' "//
            + "  ELSE " //
			+ "   (CASE LG_CHECKFINALIDAD3 WHEN 1 " //
			+ " THEN 'ED' " //
			+ "    ELSE " //
			+ "     (CASE LG_CHECKFINALIDAD4 WHEN 1 " //
			+ "       THEN 'F' "//
			+ "      END)"// 
			+ "    END)" //
			+ "  END)" //
			+ "END " //
			+ "FROM AACI_T_SOLICITUDSUBONGD WHERE NU_NUMEXPEDIENTE = :numExpediente"))//
            .setParameter("numExpediente", numExpediente).getSingleResult();
      } else {
        resultado = (String) getEntityManager().createNativeQuery(
            ("SELECT " //
			+ "CASE LG_CHECKTIPO1 WHEN 1 " //
			+ " THEN 'CU' " //
			+ "ELSE " //
			+ "   (CASE LG_CHECKTIPO2 WHEN 1 " //
			+ "     THEN 'INN' " //
			+ "    ELSE "//
			+ "     (CASE LG_CHECKTIPO3 WHEN 1 " //
			+ "       THEN 'EDD' " //
			+ "       ELSE " //
			+ "         (CASE LG_CHECKTIPO4 WHEN 1 " //
			+ "           THEN 'FE' "//
			+ "       END) " //
			+ "     END) " //
			+ "  END) " //
			+ "END " //
			+ "FROM AACI_T_SOLICITUDSUBONGD WHERE NU_NUMEXPEDIENTE = :numExpediente"))//
			.setParameter("numExpediente", numExpediente).getSingleResult();
      }
    } catch (final NoResultException nre) {
      return "No existen resultados";
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
    return resultado;
  }
}

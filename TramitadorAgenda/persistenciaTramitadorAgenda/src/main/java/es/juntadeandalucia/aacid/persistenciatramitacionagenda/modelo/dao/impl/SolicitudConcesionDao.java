package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ISolicitudConcesionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.SolicitudConcesion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class SolicitudConcesionDao extends CustomHibernateDaoSupport implements ISolicitudConcesionDao {

  @Override
  public SolicitudConcesion obtenerSolicitudConcesionPorSolicitud(final Long idSolicitud) throws TramitacionException {
    try {
      return (SolicitudConcesion) getEntityManager().createQuery("SELECT solCon from SolicitudConcesion solCon "//
          + "where solCon.socoNuSolicitud = :idSolicitud").setParameter("idSolicitud", idSolicitud).getSingleResult();
    } catch (final NoResultException nre) {
      return null;
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Object> obtenerListaConcesiones(final List<String> listaExpedientes, final String finalidad, final boolean ongd) {
    final StringBuilder sqlString = new StringBuilder("SELECT "//
        + "  ats.NU_NUMEXPEDIENTE idTrewa, "//
        + "  sc.SOCO_NU_ID idSolConce, "//
        + "  ats.ID_SOLICITUD idSolicitud, "//
        + "  ats.TX_CODIDENTIFICATIVO codIdent, "//
        + "  sc.SOCO_NU_PRESUPUESTO_VALIDADO presVal, "//
        + "  sc.SOCO_NU_SUBVENCION_CONCEDER subvConce, "//
        + "  sc.SOCO_NU_SUBVENCION_SOLICITADA  subvSoli, "//
        + "  ats.TX_SOLI1NOMBRE entidad, "//
        + "  val.puntuacion puntuacion, "//
        + "  sc.SOCO_TX_TIPO_CONCESION  beneficiario "//
        + "FROM  AACI_T_SOLICITUDSUBONGD ats "//
        + "FULL OUTER JOIN AACI_SOLICITUD_CONCESION sc ON sc.SOCO_NU_SOLICITUD =ats.ID_SOLICITUD "//
        + "FULL OUTER JOIN (SELECT ats.NU_NUMEXPEDIENTE idTrewa, "//
        + "  sum(socr_Nu_puntuacion) puntuacion "//
        + "  FROM AACI_T_SOLICITUDSUBONGD ats "//
        + "  INNER JOIN AACI_SOLICITUD_CRITERIO asc2 "//
        + "        ON ats.ID_SOLICITUD = asc2.ID_SOLICITUD "//
        + "  GROUP BY  ats.NU_NUMEXPEDIENTE) val ON  val.idTrewa=ats.NU_NUMEXPEDIENTE "//
        + "FULL OUTER JOIN AACI_SOLICITUD_CONCESION beneSupl on beneSupl.SOCO_NU_SOLICITUD = ats.ID_SOLICITUD "//
        + "WHERE ats.NU_NUMEXPEDIENTE IN (:listaExpedientes) ");
    if (ongd) {
      if (finalidad != null) {
        switch (finalidad) {
        case ConstantesTramitacion.COD_COOPERACION:
          sqlString.append(" and ats.LG_CHECKFINALIDAD1 = 1 ");
          break;
        case ConstantesTramitacion.COD_ACCION_HUMA:
          sqlString.append(" and ats.LG_CHECKFINALIDAD2 = 1 ");
          break;
        case ConstantesTramitacion.COD_EDUCACION:
          sqlString.append(" and ats.LG_CHECKFINALIDAD3 = 1 ");
          break;
        case ConstantesTramitacion.COD_FORMACION:
          sqlString.append(" and ats.LG_CHECKFINALIDAD4 = 1 ");
          break;
        default:
          break;
        }
      }
    } else if (!ongd && finalidad != null) {
      switch (finalidad) {
      case ConstantesTramitacion.COD_COOP_UNIV:
        sqlString.append(" and ats.LG_CHECKTIPO1 = 1 ");
        break;
      case ConstantesTramitacion.COD_INVESTIGACION:
        sqlString.append(" and ats.LG_CHECKTIPO2 = 1 ");
        break;
      case ConstantesTramitacion.COD_EDUCACION_UNIV:
        sqlString.append(" and ats.LG_CHECKTIPO3 = 1 ");
        break;
      case ConstantesTramitacion.COD_FORMACION_ESTUDIOS:
        sqlString.append(" and ats.LG_CHECKTIPO4 = 1 ");
        break;
      default:
        break;
      }
    }

    return getEntityManager().createNativeQuery(sqlString.toString()).setParameter("listaExpedientes", listaExpedientes).getResultList();
  }

  @Override
  public void guardar(final List<SolicitudConcesion> listSolConcesion) {
    for (final SolicitudConcesion solConc : listSolConcesion) {
      if (solConc.getSocoNuId() != null) {
        getEntityManager().merge(solConc);
      } else {
        getEntityManager().persist(solConc);
      }
    }
  }

}

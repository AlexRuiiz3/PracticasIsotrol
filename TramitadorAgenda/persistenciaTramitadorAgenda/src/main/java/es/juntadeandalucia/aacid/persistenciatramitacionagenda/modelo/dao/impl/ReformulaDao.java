package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IReformulaDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Reformula;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class ReformulaDao extends CustomHibernateDaoSupport implements IReformulaDao {

  @Override
  public List<Reformula> findAllBySolicitudIdOrderById(Long idSolicitud) throws TramitacionException {
    try {
      return getEntityManager().createQuery("SELECT r from Reformula r where r.idSolicitud = :idSolicitud order by r.id", Reformula.class)
          .setParameter("idSolicitud", idSolicitud).getResultList();
    } catch (Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }

  @Override
  @Transactional
  public Reformula save(Reformula reformula) throws TramitacionException {
    try {
      return getEntityManager().merge(reformula);
    } catch (Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }
}

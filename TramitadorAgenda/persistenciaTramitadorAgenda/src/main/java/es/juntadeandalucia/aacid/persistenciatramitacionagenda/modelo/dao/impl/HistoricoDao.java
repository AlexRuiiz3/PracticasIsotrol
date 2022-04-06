package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IHistoricoDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Historico;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;
/**
 * HistoricoDao class.
 * @author isotrol.
 *
 */
public class HistoricoDao extends CustomHibernateDaoSupport implements IHistoricoDao {

  @Override
  @Transactional
  public Historico guardarHistorico(Historico historico) throws TramitacionException {
    try {
      historico = getEntityManager().merge(historico);
    } catch (Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
    return historico;
  }

  @Override
  @Transactional
  public List<Historico> findHistoricosByIdExpediente(String numExp) throws TramitacionException {

    try {
     return getEntityManager().createQuery(
          "SELECT h FROM Historico h WHERE h.hisXExpe =: numExp ORDER BY h.hisFec DESC",
          Historico.class).setParameter("numExp", numExp).getResultList();
    } catch (Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }
}

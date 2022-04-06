package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ITipoCatalogoDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TipoCatalogo;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class TipoCatalogoDao extends CustomHibernateDaoSupport implements ITipoCatalogoDao {

  @SuppressWarnings("unchecked")
  @Override
  public List<TipoCatalogo> obtenerTiposCatalogoPorAnio(final Integer anio, final String tipoConvocatoria) throws TramitacionException {
    try {
      return getEntityManager()
          .createQuery("SELECT tipcat from TipoCatalogo tipcat " + "left join tipcat.aaciTTipoConvByTconvNuId tipconv "
              + "where tipcat.tcatNuAnio = :nuanio and tipconv.tconvLiDenominacion = :tipconv")
          .setParameter("nuanio", anio).setParameter("tipconv", tipoConvocatoria).getResultList();
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }
}

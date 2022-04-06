package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ICatalogoDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Catalogo;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

public class CatalogoDao extends CustomHibernateDaoSupport implements ICatalogoDao {

  @SuppressWarnings("unchecked")
  @Override
  public List<Catalogo> obtenerCatalogosPorTipo(final Integer tipCat) throws TramitacionException {
    try {
      return getEntityManager().createQuery("SELECT cat from Catalogo cat left join cat.aaciTipoCatalogoByTcatNuId tipcat where tipcat.tcatNuId = :tipCat ")
          .setParameter("tipCat", tipCat).getResultList();
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }

  @Override
  public Catalogo getCatalogoByCodTipoCodCatTipoConvAnyo(final String codTipo, final String codCat, final String tipoConv, final Integer anyo)
      throws TramitacionException {
    try {
      return getEntityManager().createQuery("select cat " //
          + "from Catalogo cat " //
          + "LEFT JOIN cat.aaciTipoCatalogoByTcatNuId tipcat "//
          + "LEFT JOIN tipcat.aaciTTipoConvByTconvNuId tipconv "//
          + "where cat.catCoCodigo = :catalogo "//
          + "and tipcat.tcatCoCodigo = :tipoCatalogo "//
          + "and tipconv.tconvLiDenominacion = :tipoConv "//
          + "and tipcat.tcatNuAnio = :anyo", Catalogo.class).setParameter("catalogo", codCat)//
          .setParameter("tipoCatalogo", codTipo)//
          .setParameter("tipoConv", tipoConv)//
          .setParameter("anyo", anyo)//
          .getSingleResult();
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }

  @Override
  public List<CatalogoDTO> obtenerListaCodigosSubsanacion(final Long idSolicitud) throws TramitacionException {
    try {
      return getEntityManager().createQuery("SELECT NEW es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoDTO (cat.catCoCodigo) " //
          + "FROM Catalogo cat "//
          + "JOIN cat.aaciCatalogoSubsanacionsByCatNuId catSub " //
          + "JOIN catSub.aaciSubsanacionBySubNuId sub "//
          + "JOIN sub.aaciTSolicitudsubongdByFkSolicitud sol "//
          + "WHERE sol.idSolicitud =: idSolicitud ORDER BY cat.catNuId", CatalogoDTO.class)//
          .setParameter("idSolicitud", idSolicitud).getResultList();
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }
}

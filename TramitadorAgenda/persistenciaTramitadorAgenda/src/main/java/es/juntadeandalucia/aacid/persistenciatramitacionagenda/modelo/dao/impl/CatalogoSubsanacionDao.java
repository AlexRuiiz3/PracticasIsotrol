package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoSubsanacionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ICatalogoSubsanacionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.CatalogoSubsanacion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

@Repository
public class CatalogoSubsanacionDao extends CustomHibernateDaoSupport implements ICatalogoSubsanacionDao {

  @Transactional
  @Override
  public List<CatalogoSubsanacionDTO> obtenerCatalogoSubsancionByIdSolicitud(String idSolicitud)
      throws TramitacionException {
    try {
      return getEntityManager().createQuery(
          "SELECT NEW es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoSubsanacionDTO (catSub.motivo, cat.catLiDescripcion) "
          + "FROM CatalogoSubsanacion catSub "
          + "JOIN catSub.aaciCatalogoByCatNuId cat "
          + "JOIN catSub.aaciSubsanacionBySubNuId sub "
              + "JOIN sub.aaciTSolicitudsubongdByFkSolicitud sol WHERE sol.idSolicitud =: idSolicitud", CatalogoSubsanacionDTO.class)
          .setParameter("idSolicitud", Long.valueOf(idSolicitud)).getResultList();
    } catch (Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }

  @Transactional
  @Override
  public List<CatalogoSubsanacionDTO> obtenerCatalogoSubsancionByIdSolicitudDocumento(String idSolicitud)
      throws TramitacionException {
    try {
      return getEntityManager().createQuery(
          "SELECT NEW es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoSubsanacionDTO (catSub.motivo, cat.catLiDescripcion,cat.catCoCodigo) "
          + "FROM CatalogoSubsanacion catSub "
          + "JOIN catSub.aaciCatalogoByCatNuId cat "
          + "JOIN catSub.aaciSubsanacionBySubNuId sub "
          + "JOIN sub.aaciTSolicitudsubongdByFkSolicitud sol WHERE sol.idSolicitud =: idSolicitud ORDER BY cat.catNuId", CatalogoSubsanacionDTO.class)
          .setParameter("idSolicitud", Long.valueOf(idSolicitud)).getResultList();
    } catch (Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }

  @Override
  public List<CatalogoSubsanacion> obtenerCatalogoSubsancionByIdSubsanacion(Integer idSubsanacion)
          throws TramitacionException {
    try {
      return getEntityManager().createQuery(
              "SELECT new CatalogoSubsanacion(catSub.csubNuId, catSub.motivo, catSub.aaciCatalogoByCatNuId) "
                      + "FROM CatalogoSubsanacion catSub "
                      + "JOIN catSub.aaciCatalogoByCatNuId cat "
                      + "JOIN catSub.aaciSubsanacionBySubNuId sub "
                      + "WHERE sub.subNuId = :idSubsanacion", CatalogoSubsanacion.class)
              .setParameter("idSubsanacion", idSubsanacion)
              .getResultList();
    } catch (Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }

  @Override
  public CatalogoSubsanacion guardarCatalogoSubsanacion(CatalogoSubsanacion catalogoSubsanacion) throws TramitacionException {
    try {
      return getEntityManager().merge(catalogoSubsanacion);
    } catch (Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }

  public void eliminarCatalogoSubsanacion (CatalogoSubsanacion catalogo) throws TramitacionException {
    try{
      getEntityManager().setFlushMode(FlushModeType.AUTO);
      int resultado = getEntityManager().createNativeQuery("DELETE from AACI_CATALOGO_SUBSANACION WHERE CSUB_NU_ID = ?")
              .setParameter(1, catalogo.getCsubNuId())
              .executeUpdate();
      getEntityManager().clear();
      if (resultado != 1){
        throw new TramitacionException("Error al borrar el catalogo de subsanacion");
      }
    } catch (Exception e){
      throw new TramitacionException("Se ha producido un error al borrar el catálogo subsanación",e);
    }
  }

  @Transactional
  public void eliminarCatalogosSubsanacion (List<CatalogoSubsanacion> catalogos) throws TramitacionException {

    for (CatalogoSubsanacion catalogo : catalogos){
      eliminarCatalogoSubsanacion(catalogo);
    }
  }
}

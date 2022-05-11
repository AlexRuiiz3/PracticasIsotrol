package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IPaisDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Pais;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaisService;

public class PaisServiceImpl implements IPaisService {

  private IPaisDao paisDao;

  @Override
  public void obtenerValoracionConvergencia(final Long idSolicitud, final ValoracionCriterioDTO valoracionConvergencia) throws TramitacionException {
    try {

      final List<Pais> paises = paisDao.obtenerPaisesSolicitud(idSolicitud);
      obtenerPuntuacionPais(valoracionConvergencia, paises);

    } catch (final TramitacionException e) {
      throw new TramitacionException("No se ha podido recuperar los datos de convergencia con los paises.", e);
    }
  }

  /**
   * @param valoracionConvergencia
   * @param solicitudDTO
   * @param pais
   */
  private void obtenerPuntuacionPais(final ValoracionCriterioDTO valoracionConvergencia, final List<Pais> paises) {
    BigDecimal puntuacion = BigDecimal.ZERO;
    if (CollectionUtils.isNotEmpty(paises)) {
      for (final Pais pais : paises) {
        puntuacion = puntuacion.add(pais.getPuntuacion());
      }
      puntuacion = puntuacion.divide(BigDecimal.valueOf(paises.size()), 3, RoundingMode.HALF_DOWN);
    }
    valoracionConvergencia.setPuntuacion(puntuacion);
    valoracionConvergencia.setValoracion(puntuacion);
  }

  /**
   * Establece el valor de la propiedad paisDao
   *
   * @param paisDao
   *          el paisDao para establecer
   */
  public void setPaisDao(final IPaisDao paisDao) {
    this.paisDao = paisDao;
  }

  @Override
  public List<Pais> obtenerPaisesPorAnho(int anho) {
    return paisDao.obtenerPaisesPorAnho(anho);
  }

  @Override
  public Pais obtenerPais(Long id) {
    return paisDao.obtenerPais(id);
  }

  @Override
  public void eliminarPais(Long id) {
    paisDao.eliminarPais(id);
  }

  @Override
  public void guardarOActualizarPais(Pais pais) {
    paisDao.guardarOActualizarPais(pais);
  }

}

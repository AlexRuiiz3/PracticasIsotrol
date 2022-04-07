package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ICapacidadGestionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.CapacidadGestion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Solicitud;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ICapacidadGestionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudService;

public class CapacidadGestionServiceImpl implements ICapacidadGestionService {

  private ISolicitudService solicitudService;
  private ICapacidadGestionDao capacidadGestionDao;

  @Override
  public List<CapacidadGestion> obtenerCapacidadesGestion(final CapacidadGestion capacidadGestion) {
    return capacidadGestionDao.obtenerCapacidadesGestion(capacidadGestion);
  }

  @Override
  public void obtenerValorCapacidadGestionEntidad(final String nExp, final ValoracionCriterioDTO valoracionCapGest, Integer anio) throws TramitacionException {
    try {
      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(nExp);
      CapacidadGestion capacidadGestion = new CapacidadGestion();
      if (solicitudDTO != null) {
        capacidadGestion.setTmcgTxCif(solicitudDTO.getCif());
        capacidadGestion.setTmcgNuAnio(anio.longValue());
        final List<CapacidadGestion> capacidadesGestion = obtenerCapacidadesGestion(capacidadGestion);

        if (CollectionUtils.isNotEmpty(capacidadesGestion) && capacidadesGestion.get(0) != null) {
          capacidadGestion = capacidadesGestion.get(0);
          valoracionCapGest.setValoracion(capacidadGestion.getTmcgNuPuntTotal());
          valoracionCapGest.setPuntuacion(capacidadGestion.getTmcgNuPuntTotal());
        } else {
          valoracionCapGest.setValoracion(ConstantesTramitacion.PUNTUACION_CAP_GEST_ENTIDAD_NO_ENCONTRADA);
          valoracionCapGest.setPuntuacion(ConstantesTramitacion.PUNTUACION_CAP_GEST_ENTIDAD_NO_ENCONTRADA);
        }
      }
    } catch (final TramitacionException e) {
      throw new TramitacionException("No se ha podido recuperar los datos de capacidad de gestión.", e);
    }
  }

  /**
   * Establece el valor de la propiedad capacidadGestionDao
   *
   * @param capacidadGestionDao
   *          el capacidadGestionDao para establecer
   */
  public void setCapacidadGestionDao(final ICapacidadGestionDao capacidadGestionDao) {
    this.capacidadGestionDao = capacidadGestionDao;
  }

  /**
   * Establece el valor de la propiedad solicitudService
   *
   * @param solicitudService
   *          el solicitudService para establecer
   */
  public void setSolicitudService(final ISolicitudService solicitudService) {
    this.solicitudService = solicitudService;
  }

}

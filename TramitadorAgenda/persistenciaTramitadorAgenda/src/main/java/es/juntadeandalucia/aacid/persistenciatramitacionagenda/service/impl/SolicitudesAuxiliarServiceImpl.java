package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudAuxiliarDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionSolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ISolicitudesAuxiliarDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Solicitud;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.SolicitudesAuxiliar;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudesAuxiliarService;

public class SolicitudesAuxiliarServiceImpl extends CustomHibernateDaoSupport implements ISolicitudesAuxiliarService {

  private ISolicitudesAuxiliarDao solicitudesAuxiliarDao;

  @Override
  public SolicitudAuxiliarDTO findByIdSolicitud(final Long idSolicitud) throws TramitacionException {
    return solicitudesAuxiliarDao.findByIdSolicitud(idSolicitud);
  }

  @Override
  public int guardarValoracion(final ValoracionSolicitudDTO valoracionSolicitudDTO) throws TramitacionException {
    final SolicitudAuxiliarDTO solicitudAuxiliarDTO = findByIdSolicitud(valoracionSolicitudDTO.getIdSolicitud());
    final SolicitudesAuxiliar solicitudAux = new SolicitudesAuxiliar();
    final Solicitud solicitud = new Solicitud();
    solicitud.setIdSolicitud(valoracionSolicitudDTO.getIdSolicitud());
    if (solicitudAuxiliarDTO != null && solicitudAuxiliarDTO.getId() != null) {
      solicitudAux.setSoauNuId(solicitudAuxiliarDTO.getId());
    }
    solicitudAux.setAaciTSolicitudsubongdByIdSolicitud(solicitud);

    if (valoracionSolicitudDTO.isOngd() && valoracionSolicitudDTO.isMostrarPestania3()) {
      solicitudAux.setSoauNuCausaReintegro(valoracionSolicitudDTO.getCausaReintegros());
      solicitudAux.setSoauNuNumReintegros(valoracionSolicitudDTO.getNumReintegros());
      solicitudAux.setSoauNuMagnitudReintegro(valoracionSolicitudDTO.getMagnitudReintegros());
    }
    solicitudAux.setSoauTxObservacionesVal(valoracionSolicitudDTO.getValoracionTotal().getObservaciones());
    solicitudAux.setSoauNuPuntuacionTotal(valoracionSolicitudDTO.getValoracionTotal().getTotalPuntuacion());

    return solicitudesAuxiliarDao.saveOrUpdateSolicitudesAuxiliar(solicitudAux);
  }

  public void setSolicitudesAuxiliarDao(final ISolicitudesAuxiliarDao solicitudesAuxiliarDao) {
    this.solicitudesAuxiliarDao = solicitudesAuxiliarDao;
  }

}

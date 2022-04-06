package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IPaeSoliciudDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeSolicitudes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeSolicitudService;
import es.juntadeandalucia.plataforma.service.modulos.IConfig;
import es.juntadeandalucia.plataforma.service.modulos.IConfigService;

/**
 * PaeSolicitudServiceImpl class.
 *
 * @author isotrol.
 *
 */
public class PaeSolicitudServiceImpl implements IPaeSolicitudService {
  private final Logger log = Logger.getLogger(PaeSolicitudServiceImpl.class);

  @Autowired
  private IPaeSoliciudDao paeSolicitudDao;
  @Autowired
  private IConfigService configService;

  @Override
  public PaeSolicitudes obtenerSolicitudExpByCodigo(final String codigoIdentificativo) throws TramitacionException {
    PaeSolicitudes solicitud = null;
    final List<IConfig> configuraciones = configService.obtenerParametrosConfiguracion(ConstantesTramitacion.ESQUEMA_TREWA, null, null, true);
    if (CollectionUtils.isNotEmpty(configuraciones)) {
      solicitud = paeSolicitudDao.getSolicitudExpByCodigo(codigoIdentificativo, configuraciones.get(0).getValor());
      if (solicitud == null) {
        log.info("No se ha encontrado la solicitud asignada al expediente: " + codigoIdentificativo + " de trewa");
        return null;
      }
    }
    return solicitud;
  }

  @Override
  public Long getNumExpByCodigoIdentificativo(final String codigoIdentificativo) throws TramitacionException {
    final List<IConfig> configuraciones = configService.obtenerParametrosConfiguracion(ConstantesTramitacion.ESQUEMA_TREWA, null, null, true);
    if (CollectionUtils.isNotEmpty(configuraciones)) {
      return paeSolicitudDao.getNumExpByCodigo(codigoIdentificativo, configuraciones.get(0).getValor());
    }
    return null;
  }

  public void setPaeSolicitudDao(final IPaeSoliciudDao paeSolicitudDao) {
    this.paeSolicitudDao = paeSolicitudDao;
  }

  public void setConfigService(final IConfigService configService) {
    this.configService = configService;
  }

}

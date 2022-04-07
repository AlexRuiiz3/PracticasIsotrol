package es.juntadeandalucia.aacid.comuntramitacion.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.IParametrosConfiguracionService;
import es.juntadeandalucia.plataforma.comunes.excepciones.ArchitectureException;
import es.juntadeandalucia.plataforma.service.modulos.IConfig;
import es.juntadeandalucia.plataforma.service.modulos.IConfigService;
import es.juntadeandalucia.plataforma.service.sistema.ISistemaService;
import es.juntadeandalucia.plataforma.service.visibilidad.procedimiento.IProcedimientoService;
import es.juntadeandalucia.plataforma.sistema.ISistema;

public class ParametrosConfiguracionServiceImpl implements IParametrosConfiguracionService {

  /** Log log, Atributo para los mensajes de log */
  protected final Log log = LogFactory.getLog(getClass());
  /** Servicio para obtener los parámetros de configuración */
  private IConfigService configService;
  /** Servicio para obtener el procedimiento de ptwanda */
  private IProcedimientoService procedimientoService;
  private ISistemaService sistemasService;

  @Override
  public String obtenerParametroConfiguracion(final String nombreParam, final String procedimiento) throws TramitacionException {
    String valorParam = null;
    try {
      final List<ISistema> sistemas = sistemasService.buscarSistemaPorCodigo("AACID", "TrewaAACID");
      String idProcedimientoPTwanda = null;
      if (StringUtils.isNotBlank(procedimiento)) {
        idProcedimientoPTwanda = procedimientoService.buscarProcedimientoPorIDTramitador(procedimiento, sistemas.get(0)).getId().toString();
      }
      final List<IConfig> parametros = configService.obtenerParametrosConfiguracion(nombreParam, null, idProcedimientoPTwanda, true);
      if (CollectionUtils.isNotEmpty(parametros)) {
        valorParam = parametros.get(0).getValor();
      }
    } catch (final ArchitectureException e) {
      log.error("No se ha encontrado el parámetro " + nombreParam, e);
      throw new TramitacionException("No se ha encontrado el parámetro " + nombreParam, e);
    }
    return valorParam;
  }

  /**
   * Establece el valor de la propiedad configService
   *
   * @param configService
   *          el configService para establecer
   */
  public void setConfigService(final IConfigService configService) {
    this.configService = configService;
  }

  /**
   * Establece el valor de la propiedad procedimientoService
   *
   * @param procedimientoService
   *          el procedimientoService para establecer
   */
  public void setProcedimientoService(final IProcedimientoService procedimientoService) {
    this.procedimientoService = procedimientoService;
  }

  /**
   * Establece el valor de la propiedad sistemasService
   *
   * @param sistemasService
   *          el sistemasService para establecer
   */
  public void setSistemasService(final ISistemaService sistemasService) {
    this.sistemasService = sistemasService;
  }

}

package es.juntadeandalucia.aacid.comuntramitacion.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.service.IFestivosAACIDService;
import es.juntadeandalucia.plataforma.service.festivos.IFestivosService;
import es.juntadeandalucia.plataforma.service.modulos.IFestivosCaducidades;

public class FestivosAACIDServiceImpl implements IFestivosAACIDService {

  protected final Log log = LogFactory.getLog(getClass());

  private IFestivosService festivosService;

  @Override
  public List<Date> obtenerfestivos(final int anio, final String tipoFestivo) {
    final List<Date> festivos = new ArrayList<>();
    final List<IFestivosCaducidades> festivosCaducidad = festivosService.obtenerFestivos();
    final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String fechaString = "";
    Formatter fmt;

    for (final IFestivosCaducidades festivoCaducidad : festivosCaducidad) {
      if (festivoCaducidad.getAnyo() == anio && comprobarTipoFetividad(festivoCaducidad, tipoFestivo)) {
        try {
          fmt = new Formatter();
          fechaString = fmt.format("%02d/%02d/%04d", festivoCaducidad.getDia(), festivoCaducidad.getMes(), festivoCaducidad.getAnyo()).toString();
          festivos.add(formatter.parse(fechaString));
        } catch (final ParseException e) {
          log.error("Se ha producido un error al recuperar el festivo " + fechaString, e);
        }
      }
    }
    return festivos;
  }

  // Obtenemos el tipo del festivo
  private boolean comprobarTipoFetividad(final IFestivosCaducidades festivoCaducidad, final String tipoFestivo) {
    if (StringUtils.isNotBlank(tipoFestivo)) {
      if (StringUtils.equals(tipoFestivo, ConstantesTramitacion.FESTIVO_NACIONAL)) {
        return StringUtils.equals("1", festivoCaducidad.getFestivoNacional());
      } else if (StringUtils.equals(tipoFestivo, ConstantesTramitacion.FESTIVO_AUTONOMICO)) {
        return StringUtils.equals("1", festivoCaducidad.getFestivoNacional()) || StringUtils.equals("1", festivoCaducidad.getFestivoAutonomico());
      } else {
        return true;
      }
    } else {
      return true;
    }
  }

  /**
   * Establece el valor de la propiedad festivosService
   *
   * @param festivosService
   *          el festivosService para establecer
   */
  public void setFestivosService(final IFestivosService festivosService) {
    this.festivosService = festivosService;
  }

}

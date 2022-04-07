package es.juntadeandalucia.aacid.persistenciatramitacionagenda.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.exception.SubsanacionException;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea.AaciTSolicitudsubongd;

/**
 * ProcesamientoEspecificoUtils class.
 *
 * @author isotrol.
 *
 */
public class UtilidadesReflection {

  private UtilidadesReflection() {
  }

  /** LOGGER ProcesamientoEspecifico.class */
  private static final Logger LOGGER = Logger.getLogger(UtilidadesReflection.class);

  public static void setearCamposPorTipo(final Field field, final String campoVea, final AaciTSolicitudsubongd solicitudSubvencion)
      throws SubsanacionException {
    LOGGER.info("Se esta procesando el campo:" + field.getName());
    try {
      if (!StringUtils.isEmpty(campoVea)) {
        switch (field.getType().getName()) {
        case "java.util.Date":
          final Date date1 = new SimpleDateFormat(ConstantesTramitacion.FORMATO_FECHA_UE).parse(campoVea);
          field.set(solicitudSubvencion, date1);
          break;
        case "java.math.BigDecimal":
          field.set(solicitudSubvencion, UtilidadesNumero.safeBigDecimalParsingHelper(campoVea));
          break;
        case "java.lang.Boolean":
          field.set(solicitudSubvencion, Boolean.valueOf(campoVea));
          break;
        case "java.lang.Long":
          final BigDecimal campo = UtilidadesNumero.safeBigDecimalParsingHelper(campoVea);
          if (campo != null) {
            field.set(solicitudSubvencion, campo.longValue());
          }
          break;
        case "java.lang.String":
          field.set(solicitudSubvencion, campoVea);
          break;
        default:
          break;
        }
      }
    } catch (ParseException | IllegalArgumentException | IllegalAccessException e) {
      LOGGER.error("Se ha producido un error en el campo: " + field.getName() + "con valor " + campoVea);
      throw new SubsanacionException("Error al seter el campo ", e);
    }
  }
}

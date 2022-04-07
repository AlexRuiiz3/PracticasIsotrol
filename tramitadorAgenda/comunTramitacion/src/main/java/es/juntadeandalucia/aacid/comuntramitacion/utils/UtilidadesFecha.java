package es.juntadeandalucia.aacid.comuntramitacion.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;

/**
 * ConvocatoriasUtils class.
 *
 * @author isotrol
 *
 */
public final class UtilidadesFecha {
  /** LOGGER **/
  private static final Logger LOG = Logger.getLogger(UtilidadesFecha.class);

  private UtilidadesFecha() {
  }

  /**
   * Formatea un string a formato fecha.
   *
   * @param fecha
   *          fecha de tipo Date.
   * @return Fecha tipo Date.
   */
  public static Date parseStringToDate(final String fecha) {
    return parseStringToDate(fecha, ConstantesTramitacion.FORMATO_FECHA_UE, Locale.getDefault());
  }

  /**
   * Convierte un string en fecha segun un locale y un formato de fecha.
   *
   * @param date
   *          Fecha a convertir.
   * @param dateFormat
   *          Formato de fecha que tiene la fecha inicial.
   * @param locale
   *          Locale a usar.
   * @return La fecha parseada
   */
  private static Date parseStringToDate(final String date, final String dateFormat, final Locale locale) {
    try {
      // Parseo de string a fecha
      final SimpleDateFormat formateadorFecha = new SimpleDateFormat(dateFormat, locale);
      return formateadorFecha.parse(date);
    } catch (ParseException | NullPointerException e) {
      LOG.error("La cadena " + date + " no se ha podido convertir en Date.");
      return null;
    }
  }

  /**
   * Formatea un date a string en formato fecha (dd/MM/yyyy).
   *
   * @param fecha
   *          Fecha de tipo String.
   * @return Fecha tipo String.
   */
  public static String parseDateToString(final Date fecha) {
    return parseDateToString(fecha, ConstantesTramitacion.FORMATO_FECHA_UE, Locale.getDefault(), null);
  }

  /**
   * Formatea un date a string en formato fecha (dd/MM/yyyy).
   *
   * @param fecha
   *          Fecha de tipo String.
   * @return Fecha tipo String.
   */
  public static String parseDateToStringFormatoddMMyy(final Date fecha) {
    return parseDateToString(fecha, "dd-MM-yy", Locale.getDefault(), null);
  }

  /**
   * Convierte una fecha en string, segun un locale y un formato de fecha.
   *
   * @param date
   *          fecha a convertir
   * @param dateFormat
   *          formato de fecha en el que devolver la fecha resultante.
   * @param locale
   *          Locale a usar.
   * @return
   */
  private static String parseDateToString(final Date date, final String dateFormat, final Locale locale, final TimeZone tc) {
    String stringDate = StringUtils.EMPTY;
    // Parseo de fecha a string
    if (date != null) {

      final SimpleDateFormat formatDate = new SimpleDateFormat(dateFormat, locale);

      if (tc != null) {
        formatDate.setTimeZone(TimeZone.getTimeZone("UTC"));
      }

      stringDate = formatDate.format(date);
    }
    return stringDate;
  }

  /**
   * Convierte formato fecha en texto
   *
   * @param fecha
   * @return
   */
  public static String obtenerFechaModoTexto(final Date date) {
    final StringBuilder str = new StringBuilder();
    // Obtienes el mes actual
    final Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    final String mes = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, new Locale("es", "ES"));

    // Obtienes el nombre del mes
    str.append(cal.get(Calendar.DAY_OF_MONTH));
    str.append(" de ");
    str.append(mes.substring(0, 1).toUpperCase() + mes.substring(1));
    str.append(" de ");
    str.append(cal.get(Calendar.YEAR));
    return str.toString();
  }

  /**
   * Obtenemos el año de una fecha
   *
   * @return
   */
  public static Long obtenerAnyoConvocatoriaConAnyoCriterios(final Date fecha) {
    final Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
    cal.setTime(fecha);
    return (long) cal.get(Calendar.YEAR);
  }

  public static Long obtenerAnioActual() {
    final Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
    return new Long(cal.get(Calendar.YEAR));
  }

  public static Date sumarMesesFecha(final Date fecha, final int numMeses) {
    if (fecha != null) {
      final Calendar fCalendar = Calendar.getInstance();
      fCalendar.setTime(fecha);
      fCalendar.add(Calendar.MONTH, numMeses);

      return fCalendar.getTime();
    }
    return null;
  }

  public static int obtenerDiferenciaEnDiasHabiles(final Date fInicio, final Date fFinal, final List<Date> diasFestivos) {
    int diffDays = 0;
    boolean diaHabil = false;
    final Calendar fechaInicial = Calendar.getInstance();
    final Calendar fechaFinal = Calendar.getInstance();

    if (fInicio != null && fFinal != null) {
      fechaInicial.setTime(fInicio);
      fechaFinal.setTime(fFinal);

      // mientras la fecha inicial sea menor o igual que la fecha final se cuentan los dias
      while (fechaInicial.before(fechaFinal) || fechaInicial.equals(fechaFinal)) {
        if (CollectionUtils.isNotEmpty(diasFestivos)) {
          for (final Date date : diasFestivos) {
            final Date fechaNoLaborablecalendar = fechaInicial.getTime();
            // si el dia de la semana de la fecha minima es diferente de sabado o domingo
            if (fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                && !fechaNoLaborablecalendar.equals(date)) {
              // se aumentan los dias de diferencia entre min y max
              diaHabil = true;
            } else {
              diaHabil = false;
              break;
            }
          }
        } else if (fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
          // se aumentan los dias de diferencia entre min y max
          diffDays++;
        }
        if (diaHabil) {
          diffDays++;
        }
        // se suma 1 dia para hacer la validacion del siguiente dia.
        fechaInicial.add(Calendar.DATE, 1);
      }
    }
    return diffDays;
  }

}

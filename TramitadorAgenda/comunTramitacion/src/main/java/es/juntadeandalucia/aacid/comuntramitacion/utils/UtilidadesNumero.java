package es.juntadeandalucia.aacid.comuntramitacion.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.apache.commons.configuration.ConversionException;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class UtilidadesNumero {

  private UtilidadesNumero() {
  }

  /** LOGGER **/
  private static final Logger LOG = Logger.getLogger(UtilidadesNumero.class);

  /**
   * Convierte un String en Long, devolviendo NULL en caso de dar fallo de conversión.
   *
   * @param value
   *          El valor a convertir.
   * @return El valor convertido.
   */
  public static Long parseStringToLong(final String value) {

    if (value == null) {
      return null;
    }

    try {
      return Long.parseLong(value);
    } catch (final NumberFormatException e) {
      LOG.error("La cadena " + value + "no es numerica");
    }

    return null;
  }

  /**
   * Convierte un Long en String
   *
   * @param entero
   *          Número a convertir.
   * @return número convertido
   * @see UtilidadesCadena#convertObjectToString(Object)
   */
  public static String convertLongToString(final Long numero) {
    return ObjectUtils.toString(numero);
  }

  /**
   * Conversor de String a BigDecimal si no está vacío.
   *
   *
   * @param valor
   *          instancia valor
   * @return Un tipo big decimal
   */
  public static BigDecimal convertStringToBigDecimalIfNotBlank(final String valor) throws ConversionException {
    BigDecimal resultado = BigDecimal.ZERO;
    if (StringUtils.isNotBlank(valor)) {
      try {
        resultado = new BigDecimal(valor);
      } catch (final NumberFormatException e) {
        throw new ConversionException(e);
      }
    }
    return resultado;
  }

  /**
   * Convierte un String numérico en BigDecimal, en caso de error devuelve el valor de errorValue.
   *
   * @param bigDecimalString
   *          Valor String a parsear a BigDecimal.
   * @return Número convertido.
   */
  public static BigDecimal safeBigDecimalParsingHelper(final String bigDecimalString) {
    try {
      if (StringUtils.isNotEmpty(bigDecimalString)) {
        final int contadorComa = StringUtils.countMatches(bigDecimalString, ",");
        if (contadorComa == 1) {
          return new BigDecimal(bigDecimalString.replace(".", StringUtils.EMPTY).replace(",", "."));
        }
      }
    } catch (final NumberFormatException e) {
      LOG.error("Se ha producido un error al convertir el valor: " + bigDecimalString + "al formato correcto ");
    }
    return null;
  }

  public static String bigDecimalToStringConverter(final BigDecimal numero) {
    if (numero != null) {
      final DecimalFormatSymbols symbols = new DecimalFormatSymbols();
      symbols.setDecimalSeparator(',');
      symbols.setGroupingSeparator('.');
      final String pattern = "#,##0.00";
      final DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
      return decimalFormat.format(numero.doubleValue());
    } else {
      return StringUtils.EMPTY;
    }
  }

  public static String bigDecimalToStringConverterTwoDecimals(final BigDecimal numero) {
    if (numero != null) {
      return bigDecimalToStringConverterPattern(numero, "#,##0.00");
    } else {
      return "0,00";
    }
  }

  public static String bigDecimalToStringConverterThreeDecimals(final BigDecimal numero) {
    return bigDecimalToStringConverterPattern(numero, "#,##0.000");
  }

  public static String bigDecimalToStringConverterNoDecimals(final BigDecimal numero) {
    return bigDecimalToStringConverterPattern(numero, "#,##0");
  }

  private static String bigDecimalToStringConverterPattern(final BigDecimal numero, final String pattern) {
    if (numero != null) {
      final DecimalFormatSymbols symbols = new DecimalFormatSymbols();
      symbols.setDecimalSeparator(',');
      symbols.setGroupingSeparator('.');
      final DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
      return decimalFormat.format(numero.doubleValue());
    } else {
      return StringUtils.EMPTY;
    }
  }

  public static String bigDecimalToStringConverterPuntuacion(final BigDecimal numero) {
    if (numero != null) {
      final DecimalFormatSymbols symbols = new DecimalFormatSymbols();
      symbols.setDecimalSeparator(',');
      symbols.setGroupingSeparator('.');
      final String pattern = "#,##0.000";
      final DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
      return decimalFormat.format(numero.doubleValue());
    } else {
      return StringUtils.EMPTY;
    }
  }

  /**
   * obtenemos Bigdecimal y si es null devolvemos 0
   *
   * @param bigDecimal
   * @return
   */
  public static BigDecimal obtieneBigDecimal(final BigDecimal bigDecimal) {
    return bigDecimal != null ? bigDecimal : BigDecimal.ZERO;
  }

  /**
   * Comprueba si el valor es un importe o no.
   *
   * @param valor
   *          Posible importe.
   * @return Indica si el valor es un importe o no.
   */
  public static boolean isImporte(final String valor) {
    final String pattern = "^-?(?:\\d+|\\d{1,3}(?:[\\s\\.]\\d{3})+)(\\,[0-9]{2})?$";
    return valor.matches(pattern);
  }

}

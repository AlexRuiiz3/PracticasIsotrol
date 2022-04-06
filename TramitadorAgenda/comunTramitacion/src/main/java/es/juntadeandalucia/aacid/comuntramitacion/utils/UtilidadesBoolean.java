package es.juntadeandalucia.aacid.comuntramitacion.utils;

import org.apache.commons.lang.StringUtils;

public class UtilidadesBoolean {

  /**
   * Constructor por defecto privado
   *
   */
  private UtilidadesBoolean() {
  }

  public static Integer convertirBooleanNumero(final Boolean bool) {
    return (bool != null && bool) ? 1 : 0;
  }

  public static Boolean convertirNumberToBoolean(final int valor) {
    return valor == 1;
  }

  public static Boolean parseStringToBoolean(final String valor) {
    if (StringUtils.isNotEmpty(valor)) {
      return Boolean.parseBoolean(valor);
    } else {
      return null;
    }
  }
}

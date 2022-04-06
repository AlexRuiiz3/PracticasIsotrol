package es.juntadeandalucia.aacid.comuntramitacion.utils;

/**
 *
 * @author Isotrol (SAE)
 *
 */
public class Utilidades {

  private Utilidades() {
  }

  public static String obtenerAbreviaturaConvocatoriaONGD() {
    final String anio = UtilidadesFecha.obtenerAnioActual().toString();
    return "C_ONGD_" + anio.substring(2);
  }

  public static String obtenerAbreviaturaConvocatoriaUNIV() {
    final String anio = UtilidadesFecha.obtenerAnioActual().toString();
    return "C_UNIV_" + anio.substring(2);
  }

  public static String obtenerAbreviaturaProyectoONGD() {
    final String anio = UtilidadesFecha.obtenerAnioActual().toString();
    return "P_ONGD_" + anio.substring(2);
  }

  public static String obtenerAbreviaturaProyectoUNIV() {
    final String anio = UtilidadesFecha.obtenerAnioActual().toString();
    return "P_UNIV_" + anio.substring(2);
  }

}

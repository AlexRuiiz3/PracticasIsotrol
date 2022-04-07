package es.juntadeandalucia.aacid.tramitacionongd.constantes;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.collections.MapUtils;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;

/**
 * ConstantesTramitacionONGD class.
 *
 * @author ISOTROL
 *
 */
public class ConstantesTramitacionONGD {

  /**
   * Default constructor
   */
  private ConstantesTramitacionONGD() {
  }

  public static final ResourceBundle PROPIEDADES = ResourceBundle.getBundle("es.juntadeandalucia.aacid.tramitacionongd.action.TareasSubsanacionAction");
  /**
   * TIPOS EXCLUSIONES
   */
  public static final String COMUNES = "COMUNES";
  public static final String COOPERACION = "COOPERACION";
  public static final String EDUCACION = "EDUCACION";
  public static final String HUMANITARIA = "HUMANITARIA";
  public static final String FORMACION_INVEST_INNOV = "FORMACION_INVEST_INNOV";
  public static final String INADMISION_DESEST = "INADMISION_DESEST";
  public static final String PROY_COOPERACION = "PROY_COOPERACION";
  public static final String PROY_ACC_HUM = "PROY_ACC_HUM";
  public static final String CAUSAS_DESESTIMIENTO = "CAUSAS_DESEST";

  /**
   * Mapas
   */
  // mapa donde aparecera el orden por tipo
  private static final Map<String, Long> MAP_EXCLUSIONES_COMUNES;
  static {
    MAP_EXCLUSIONES_COMUNES = new HashMap<>();
    MAP_EXCLUSIONES_COMUNES.put(COMUNES, 1L);
    MAP_EXCLUSIONES_COMUNES.put(COOPERACION, 2L);
    MAP_EXCLUSIONES_COMUNES.put(HUMANITARIA, 3L);
    MAP_EXCLUSIONES_COMUNES.put(EDUCACION, 4L);
    MAP_EXCLUSIONES_COMUNES.put(FORMACION_INVEST_INNOV, 5L);
  }

  @SuppressWarnings("unchecked")
  public static Map<String, Long> obtenerMapaExclusionesComunes() {
    return MapUtils.unmodifiableMap(MAP_EXCLUSIONES_COMUNES);
  }

  // Mapa de exclusiones por pantalla
  private static Map<Long, Object> mapaExclusionesFase;
  static {
    mapaExclusionesFase = new HashMap<>();
    mapaExclusionesFase.put(ConstantesTramitacion.EXCLUSION_TIPO_ADMISION, obtenerMapaExclusionesComunes());
  }

  /**
   * Obtener map colectivos.
   *
   * @return the map
   */
  @SuppressWarnings("unchecked")
  public static Map<Long, Object> obtenerMapaExclusionesONGD() {
    return MapUtils.unmodifiableMap(mapaExclusionesFase);
  }

  /**
   * Mapas
   */
  // mapa donde aparecera el orden por tipo
  private static final Map<String, Long> MAP_EXCLUSIONES_SUBSANACION;
  static {
    MAP_EXCLUSIONES_SUBSANACION = new HashMap<>();
    MAP_EXCLUSIONES_SUBSANACION.put(INADMISION_DESEST, 1L);
    MAP_EXCLUSIONES_SUBSANACION.put(PROY_COOPERACION, 2L);
    MAP_EXCLUSIONES_SUBSANACION.put(PROY_ACC_HUM, 3L);
  }

  @SuppressWarnings("unchecked")
  public static Map<String, Long> obtenerMapaExclusionesPosSub() {
    return MapUtils.unmodifiableMap(MAP_EXCLUSIONES_SUBSANACION);
  }

  // Mapa de exclusiones por pantalla
  private static Map<Long, Object> mapaExclusionesFaseSub;

  static {
    mapaExclusionesFaseSub = new HashMap<>();
    mapaExclusionesFaseSub.put(ConstantesTramitacion.EXCLUSION_TIPO_SUBSANACION, obtenerMapaExclusionesPosSub());
  }

  /**
   * Obtener map exclusiones pos-subsanación.
   *
   * @return the map
   */
  @SuppressWarnings("unchecked")
  public static Map<Long, Object> obtenerMapaExclusionesPosSubONGD() {
    return MapUtils.unmodifiableMap(mapaExclusionesFaseSub);
  }

  /**
   * Causas de desestimación
   */
  private static final Map<String, Long> MAP_CAUSAS_DESESTIMACION;
  static {
    MAP_CAUSAS_DESESTIMACION = new HashMap<>();
    MAP_CAUSAS_DESESTIMACION.put(CAUSAS_DESESTIMIENTO, 1L);
  }

  @SuppressWarnings("unchecked")
  public static Map<String, Long> obtenerMapaExclusionesCausasDesestimiento() {
    return MapUtils.unmodifiableMap(MAP_CAUSAS_DESESTIMACION);
  }

  private static Map<Long, Object> mapaCausasDesestimacion;
  static {
    mapaCausasDesestimacion = new HashMap<>();
    mapaCausasDesestimacion.put(ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_DESESTIMACION, obtenerMapaExclusionesCausasDesestimiento());
  }

  @SuppressWarnings("unchecked")
  public static Map<Long, Object> obtenerMapaCausasDesestimiento() {
    return MapUtils.unmodifiableMap(mapaCausasDesestimacion);
  }

  // Exclusiones Inadmisión pos Propuesta provisional concesión
  /**
   * Causas de desestimación
   */
  private static final Map<String, Long> MAP_CAUSAS_INADMISION;
  static {
    MAP_CAUSAS_INADMISION = new HashMap<>();
    MAP_CAUSAS_INADMISION.put(ConstantesTramitacion.CAUSA_CONCESION_DEFINITIVA, 1L);
  }

  @SuppressWarnings("unchecked")
  public static Map<String, Long> obtenerMapaExclusionesCausasInadmision() {
    return MapUtils.unmodifiableMap(MAP_CAUSAS_INADMISION);
  }

  private static Map<Long, Object> mapaCausasInadmision;
  static {
    mapaCausasInadmision = new HashMap<>();
    mapaCausasInadmision.put(ConstantesTramitacion.EXCLUSION_TIPO_CAUSAS_INADMISION, obtenerMapaExclusionesCausasInadmision());
  }

  @SuppressWarnings("unchecked")
  public static Map<Long, Object> obtenerMapaCausasInadmision() {
    return MapUtils.unmodifiableMap(mapaCausasInadmision);
  }

  public static final String EXCLUSION_DESISTIMACION = "Desistimiento de la ONGD.";
  public static final String TAREA_WEB_SUBSANACION = PROPIEDADES.getString("tarea.web.subsanacion");
  public static final String FASE_COMPROBACION_SUBSANACION = PROPIEDADES.getString("fase.comprobacionSubsanacion");
  public static final String FASE_COMPROBACION_SUBSANACION_VUELTA = PROPIEDADES.getString("fase.comprobacionSubsanacion.vuelta");

  // Procesamiento especifico Anexo IV
  public static final String ID_TAREA_TREWA_ALEG_TRAS_EXCL = "RECEPCION_ALEGACION_ONGD";

  // Procesamiento específico Anexo V
  public static final String ID_TAREA_TREWA_RECEP_ALEG_ACEPT_REFORM = "RECEPCION_ALEG_ACEPT_REFORM_ONGD";
  // Procesamiento específico Anexo VII
  public static final String ID_TAREA_TREWA_RECEPCION_COMUNICACION_INICIO = "RECEPCION_COMUNICACION_INICIO";

}
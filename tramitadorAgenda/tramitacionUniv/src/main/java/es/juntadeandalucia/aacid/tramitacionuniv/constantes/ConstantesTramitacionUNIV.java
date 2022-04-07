package es.juntadeandalucia.aacid.tramitacionuniv.constantes;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.collections.MapUtils;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;

/**
 * ConstantesTramitacionUNIV class.
 *
 * @author ISOTROL
 *
 */
public class ConstantesTramitacionUNIV {

  public static final ResourceBundle PROPIEDADES = ResourceBundle.getBundle("es.juntadeandalucia.aacid.tramitacionuniv.action.TareasSubsanacionUnivAction");

  /**
   * Default constructor
   */
  private ConstantesTramitacionUNIV() {
  }

  /**
   * TIPOS EXCLUSIONES
   */
  public static final String COMUNES = "COMUNES";
  public static final String PAISES_PRIORIZADOS = "COOPERACION";
  public static final String ANDALUCIA = "EDUCACION";
  public static final String COMUNES_POS_SUB = "COMUNES";
  public static final String PROY_COOP_UNIV = "PROY_COOP_UNIV";
  public static final String PROY_COOP_DES = "PROY_COOP_DES";
  public static final String CAUSAS_DESESTIMIENTO = "CAUSAS_DESEST";

  /**
   * Mapas
   */
  // mapa donde aparecera el orden por tipo
  private static final Map<String, Long> MAP_EXCLUSIONES_COMUNES;
  static {
    MAP_EXCLUSIONES_COMUNES = new HashMap<>();
    MAP_EXCLUSIONES_COMUNES.put(COMUNES, 1L);
    MAP_EXCLUSIONES_COMUNES.put(PAISES_PRIORIZADOS, 2L);
    MAP_EXCLUSIONES_COMUNES.put(ANDALUCIA, 3L);
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
  public static Map<Long, Object> obtenerMapaExclusionesUNIV() {
    return MapUtils.unmodifiableMap(mapaExclusionesFase);
  }

  /**
   * Mapas
   */
  // mapa donde aparecera el orden por tipo
  private static final Map<String, Long> MAP_EXCLUSIONES_SUBSANACION;
  static {
    MAP_EXCLUSIONES_SUBSANACION = new HashMap<>();
    MAP_EXCLUSIONES_SUBSANACION.put(COMUNES_POS_SUB, 1L);
    MAP_EXCLUSIONES_SUBSANACION.put(PROY_COOP_UNIV, 2L);
    MAP_EXCLUSIONES_SUBSANACION.put(PROY_COOP_DES, 3L);
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
   * Obtener map colectivos.
   *
   * @return the map
   */
  @SuppressWarnings("unchecked")
  public static Map<Long, Object> obtenerMapaExclusionesPosSubUNIV() {
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

  public static final String EXCLUSION_DESISTIMACION = "Desistimiento de la UNIVERSIDAD";
  public static final String TAREA_WEB_SUBSANACION = PROPIEDADES.getString("tarea.web.subsanacion");
  public static final String FASE_COMPROBACION_SUBSANACION = PROPIEDADES.getString("fase.comprobacionSubsanacion");
  public static final String FASE_COMPROBACION_SUBSANACION_VUELTA = PROPIEDADES.getString("fase.comprobacionSubsanacion.vuelta");

  // Procesamiento especifico Anexo III
  public static final String ID_TAREA_TREWA_ALEG_TRAS_EXCL = "RECEPCION_ALEGACION_UNIV";

  // Procesamiento específico Anexo IV
  public static final String ID_TAREA_TREWA_RECEP_ALEG_ACEPT_REFORM = "RECEPCION_ALEG_ACEPT_REFORM_UNIV";

  // Procesamiento específico Anexo VI
  public static final String ID_TAREA_TREWA_RECEPCION_COMUNICACION_INICIO = "RECEPCION_COMUNICACION_INICIO_UNIV";
}

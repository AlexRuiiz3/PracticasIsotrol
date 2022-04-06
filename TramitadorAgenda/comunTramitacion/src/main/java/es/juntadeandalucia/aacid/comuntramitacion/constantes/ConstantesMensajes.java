package es.juntadeandalucia.aacid.comuntramitacion.constantes;

import java.util.ResourceBundle;

public class ConstantesMensajes {

  private ConstantesMensajes() {

  }

  public static final ResourceBundle PROPIEDADES = ResourceBundle.getBundle("es.juntadeandalucia.aacid.comuntramitacion.resources.mensajes");

  /** CONVOCATORIA_CREADA */
  public static final String CONVOCATORIA_CREADA = PROPIEDADES.getString("msg.convocatoriaCreada");
  /** CONV_ERROR_CREAR */
  public static final String CONV_ERROR_CREAR = PROPIEDADES.getString("msg.errorCrearConv");
  /** CONV_ERROR_DATOS */
  public static final String CONV_ERROR_DATOS = PROPIEDADES.getString("msg.errorDatosConv");
  /** CONV_ERROR_DATOS */
  public static final String CONV_ERROR_OBTENER_HIJOS = PROPIEDADES.getString("msg.errorObtenerHijos");
  /** FECHAINI_REQ */
  public static final String FECHAINI_REQ = PROPIEDADES.getString("msg.fechaInicioRequerida");
  /** FECHAFIN_REQ */
  public static final String FECHAFIN_REQ = PROPIEDADES.getString("msg.fechaFinRequerida");
  /** FECHA_INI_MAYOR */
  public static final String FECHA_INI_MAYOR = PROPIEDADES.getString("msg.fechaInicioMayor");
  /** FECHAINIVAL_ERROR */
  public static final String FECHAINIVAL_ERROR = PROPIEDADES.getString("msg.errorFechaIniVal");
  /** TITULO_REQ */
  public static final String TITULO_REQ = PROPIEDADES.getString("msg.tituloRequerido");
  /** FECHA_ACTUAL_SUP */
  public static final String FECHA_ACTUAL_SUP = PROPIEDADES.getString("msg.fecActualSup");
  /** FECHA_PROP_RESOL_ERROR */
  public static final String FECHA_PROP_RESOL_ERROR = PROPIEDADES.getString("msg.errorFechaPropResol");
  /** FECHA_LIMITE_ENT_DOC_ERROR */
  public static final String FECHA_LIMITE_ENT_DOC_ERROR = PROPIEDADES.getString("msg.errorFechaLimEntDoc");
  /** FECHA_RESOL_CONCES_ERROR */
  public static final String FECHA_RESOL_CONCES_ERROR = PROPIEDADES.getString("msg.errorFechaResolConcesion");

  public static final String GUARDADO_CRITERIOS_VALORACION = PROPIEDADES.getString("msg.guardado.valoraciones");

  public static final String TIPO_CRITERIO_OBSV_TAM_MAX = PROPIEDADES.getString("msg.tipoCriterio.observaciones.tamMax");

  public static final String CRITERIO_VALOR_MAX = PROPIEDADES.getString("msg.criterio.valor.max");

  public static final String ERROR_CAMPO_SUPERA_MAX = PROPIEDADES.getString("msg.error.campo.supera.max");

  public static final String MSG_ERROR_CAMPO_REQUIRED = PROPIEDADES.getString("msg.error.campo.required");

  public static final String MSG_ERROR_CAMPO_IS_IMPORTE = PROPIEDADES.getString("msg.error.campo.isImporte");

  public static final String MSG_ERROR_CAMPO_RANGE_IMPORTE = PROPIEDADES.getString("msg.error.campo.rangeImporte");

  public static final String REFORMULA_LABEL_MAXIMO_AACID = PROPIEDADES.getString("reformula.label.maximoAACID");

  public static final String REFORMULA_LABEL_MINIMO_PRESUPUESTO_TOTAL = PROPIEDADES.getString("reformula.label.minimoPresupuestoTotal");

}

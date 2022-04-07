package es.juntadeandalucia.aacid.comuntramitacion.constantes;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.collections.MapUtils;

import es.juntadeandalucia.aacid.comuntramitacion.utils.Utilidades;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;

/**
 * ConstantesTramitacion class.
 *
 *
 */
public class ConstantesTramitacion {

  /**
   * Default constructor
   */
  private ConstantesTramitacion() {
  }

  public static final ResourceBundle PROPIEDADES = ResourceBundle.getBundle("es.juntadeandalucia.aacid.comuntramitacion.resources.configuracion");

  public static final String USUARIO_SESION = "usuario_en_sesion";

  /** JNDI_TREWA */
  public static final String JNDI_TREWA = PROPIEDADES.getString("jndi_trewa");

  public static final String ESQUEMA_TREWA_AACID = PROPIEDADES.getString("esquema_trewa");

  public static final String USUARIO_GENERACION_DOCUMENTO = PROPIEDADES.getString("usuario_generacion_documentos");

  /** SISTEMA_ACCID */
  public static final String SISTEMA_ACCID = "AACID";
  /** ONGD */
  public static final String ABREVIATURA_ONGD = Utilidades.obtenerAbreviaturaProyectoONGD();
  public static final String TIPO_CONV_ONGD = "ONGD";
  /** UNIV */
  public static final String ABREVIATURA_UNIV = Utilidades.obtenerAbreviaturaProyectoUNIV();
  public static final String TIPO_CONV_UNIV = "UNIV";
  /** UNIVERSIDAD */
  public static final String UNIVERSIDAD = "Universidades";
  /** AÑO VER VISTOS BUENOS */
  public static final Integer ANIO_VB = Integer.getInteger(PROPIEDADES.getString("vb.anio"));

  /** Perfil coordinador */
  public static final String PERFIL_COORDINADOR = PROPIEDADES.getString("perfil.coordinador");
  /** Perfil DGA */
  public static final String PERFIL_DGA = PROPIEDADES.getString("perfil.dga");
  /** Perfil valorador */
  public static final String PERFIL_VALORADOR = PROPIEDADES.getString("perfil.valorador");

  /** Perfiles visto bueno técnico */
  private static final List<String> PERFILES_VB_TECNICO = Arrays.asList(PROPIEDADES.getString("perfiles.vb.tecnico").split(","));

  /** Listado de expedientes en convocatorias */

  public static final String CODIGOS_FINALIDAD = "F,I,ED";
  public static final String PAIS_POR_DEFECTO = "Espa\u00f1a - Andaluc\u00eda";

  public static final String TIENE_SERVICIOS_SI = "Si";

  public static final String TIENE_SERVICIOS_NO = "No";

  public static final String GRUPO_SERVICIOS_SOCIALES = "L\u00ednea prioritaria 1. Servicios Sociales B\u00e1sicos";

  public static final String TYPE_MIME_ODT = "application/vnd.oasis.opendocument.text";

  public static final String MENSAJE_DOCUMENTO_SUBSANACION_GENERADO = "Se ha generado correctamente el informe de subsanaci\u00F3n";

  public static final String MENSAJE_DOCUMENTO_GENERANDO = "El documento se est\u00e1 generando en segundo plano, una vez generado se incorporar\u00e1 en el expediente.";
  /** FINALIZADA(TAREAS) */
  public static final String FINALIZADA = "F";

  /** TIPO ENTIDAD: EXCLUSION, SUBSANACION */
  public static final String EXCLUSION = "exclusion";
  public static final String SUBSANACION = "subsanacion";
  public static final String VALIDACION_PRESUPUESTO = "validacionPresupuesto";
  public static final String DESESTIMACION = "desestimacion";
  public static final String CAUSA_CONCESION_DEFINITIVA = "concesionDefinitiva";

  public static final List<String> getPerfilesVBTecnico() {
    return PERFILES_VB_TECNICO;
  }

  /**
   * Fechas
   */
  public static final String FORMATO_FECHA_UE = "dd/MM/yyyy";
  public static final String FORMATO_FECHA_EN = "yyyy-MM-dd";

  /**
   * TIPOS EXCLUSIONES
   */
  public static final Long EXCLUSION_TIPO_ADMISION = 1L;
  public static final Long EXCLUSION_TIPO_SUBSANACION = 2L;
  public static final Long EXCLUSION_TIPO_CAUSAS_DESESTIMACION = 3L;
  public static final Long EXCLUSION_TIPO_CAUSAS_INADMISION = 4L;

  private static final List<String> PERFILES_EXCLUSIONES = Arrays.asList(PROPIEDADES.getString("perfiles.exclusiones").split(","));

  public static final List<String> getPerfilesExclusiones() {
    return PERFILES_EXCLUSIONES;
  }

  /**
   * Finalidad
   *
   */
  public static final String COD_COOPERACION = "C";
  public static final String COD_ACCION_HUMA = "AH";
  public static final String COD_EDUCACION = "ED";
  public static final String COD_FORMACION = "F";

  public static final String COD_COOP_UNIV = "CU";
  public static final String COD_EDUCACION_UNIV = "EDD";
  public static final String COD_FORMACION_ESTUDIOS = "FE";
  public static final String COD_INVESTIGACION = "INN";

  /**
   * Razones de interes
   *
   */
  public static final String RAZON_INTERES_SOLICITANTE = PROPIEDADES.getString("razon.interes.solicitante");

  /**
   * Tarea de subsanaciones
   */
  public static final String TAREA_GEN_SUB_VAL_ONGD = "SUBSANACIONES_VALORADOR";
  public static final String TAREA_GEN_SUB_VAL_ONGD_ALEG = "SUBSANACIONES_VALORADOR2";

  public static final String TAREA_GEN_SUB_VAL_UNIV = "SUBSANACIONES_VALORADOR_UNIV";
  public static final String TAREA_GEN_SUB_VAL_UNIV_ALEG = "SUBSANACIONES_VALORADOR2_UNIV";
  /**
   * Tareas de exclusión
   */
  public static final String TAREA_GEN_EXCL_VAL_ONGD = "EXCL_TRAM";
  public static final String TAREA_GEN_EXCL_VAL_UNIV = "EXCL_TRAM_UNIV";
  public static final String TAREA_EXCL_POS_SU = "EXCL_POS_SU";
  public static final String TAREA_EXCL_POS_SU_UNIV = "EXCL_POS_SU_UNIV";
  public static final String TAREA_EXCL_TRAM_ALEG = "EXCL_TRAM_ALEG";
  public static final String TAREA_EXCL_TRAM_ALEG_UNIV = "EXCL_TRAM_ALEG_UNIV";
  public static final String TAREA_EXCL_POS_SU2 = "EXCL_POS_SU2";
  public static final String TAREA_EXCL_POS_SU_UNIV2 = "EXCL_POS_SU_UNIV2";

  /**
   * Tareas de desestimaciones
   */
  public static final String TAREA_CAUSAS_DESEST = "CAUSAS_DESEST";
  public static final String TAREA_CAUSAS_DESEST_UNIV = "CAUSAS_DESEST_UNIV";
  /**
   * Tipos documentos
   */
  public static final String NOMBRE_DOC_IN_SUB_SOLI_UNIV = PROPIEDADES.getString("nombre.docIn.sub.soli.univ");
  public static final String NOMBRE_DOC_IN_SUB_SOLI_ONGD = PROPIEDADES.getString("nombre.docIn.sub.soli.ongd");
  public static final String NOMBRE_DOC_IN_EXCL_PROV_SOLI_UNIV = PROPIEDADES.getString("nombre.docIn.excl.prov.soli.univ");
  public static final String NOMBRE_DOC_IN_EXCL_PROV_SOLI_ONGD = PROPIEDADES.getString("nombre.docIn.excl.prov.soli.ongd");
  public static final String NOMBRE_DOC_IN_EXCL_DEF_UNIV = PROPIEDADES.getString("nombre.docIn.excl.def.soli.univ");
  public static final String NOMBRE_DOC_IN_EXCL_DEF_ONGD = PROPIEDADES.getString("nombre.docIn.excl.def.soli.ongd");
  public static final String NOMBRE_DOC_IN_SUB_CONV_UNIV = PROPIEDADES.getString("nombre.docIn.sub.conv.univ");
  public static final String NOMBRE_DOC_IN_SUB_CONV_ONGD = PROPIEDADES.getString("nombre.docIn.sub.conv.ongd");
  public static final String NOMBRE_DOC_IN_SUB_CONV_UNIV_ALE = PROPIEDADES.getString("nombre.docIn.sub.conv.univ.ale");
  public static final String NOMBRE_DOC_IN_SUB_CONV_ONGD_ALE = PROPIEDADES.getString("nombre.docIn.sub.conv.ongd.ale");
  public static final String NOMBRE_DOC_IN_EXCL_PROV_CONV_UNIV = PROPIEDADES.getString("nombre.docIn.excl.prov.conv.univ");
  public static final String NOMBRE_DOC_IN_EXCL_PROV_CONV_ONGD = PROPIEDADES.getString("nombre.docIn.excl.prov.conv.ongd");
  public static final String NOMBRE_DOC_IN_EXCL_DEF_CONV_UNIV = PROPIEDADES.getString("nombre.docIn.excl.def.conv.univ");
  public static final String NOMBRE_DOC_IN_EXCL_DEF_CONV_ONGD = PROPIEDADES.getString("nombre.docIn.excl.def.conv.ongd");
  public static final String NOMBRE_DOC_IN_PROP_BENF_ONGD = PROPIEDADES.getString("nombre.docIn.prop.benf.ongd");
  public static final String NOMBRE_DOC_IN_PROP_BENF_UNIV = PROPIEDADES.getString("nombre.docIn.prop.benf.univ");

  public static final String NOMBRE_DOC_IN_DESEST_CONV_ONGD = PROPIEDADES.getString("nombre.docIn.desest.conv.ongd");
  public static final String NOMBRE_DOC_IN_DESEST_CONV_UNIV = PROPIEDADES.getString("nombre.docIn.desest.conv.univ");
  public static final String NOMBRE_DOC_IN_DESEST_DEF_ONGD = PROPIEDADES.getString("nombre.docIn.desest.def.soli.ongd");
  public static final String NOMBRE_DOC_IN_DESEST_DEF_UNIV = PROPIEDADES.getString("nombre.docIn.desest.def.soli.univ");

  public static final String NOMBRE_DOC_IN_PRES_VALID_UNIV = PROPIEDADES.getString("nombre.docIn.presupuesto.validado.univ");
  public static final String NOMBRE_DOC_IN_PRES_VALID_ONGD = PROPIEDADES.getString("nombre.docIn.presupuesto.validado.ongd");

  public static final String NOMBRE_DOC_DOC_PROP_PROV_BEN_SUP_ONGD = PROPIEDADES.getString("nombre.docIn.prop.prov.benef.suplen.ongd");
  public static final String NOMBRE_DOC_DOC_PROP_PROV_BEN_SUP_UNIV = PROPIEDADES.getString("nombre.docIn.prop.prov.benef.suplen.univ");
  public static final String NOMBRE_DOC_LISTA_PROPUESTA_PROVISIONAL_BEN_SUP_UNIV = PROPIEDADES.getString("nombre.docIn.listado.prop.prov.benef.suplen.univ");
  public static final String NOMBRE_DOC_LISTA_PROPUESTA_PROVISIONAL_BEN_SUP_ONGD = PROPIEDADES.getString("nombre.docIn.listado.prop.prov.benef.suplen.ongd");

  public static final String NOMBRE_DOC_IN_NOTIF_REFORMULACION_ONGD = PROPIEDADES.getString("nombre.docIn.notificacion.reformulacion.ongd");
  public static final String NOMBRE_DOC_IN_NOTIF_REFORMULACION_UNIV = PROPIEDADES.getString("nombre.docIn.notificacion.reformulacion.univ");

  public static final String NOMBRE_DOC_RESOLUCION_DEFINITIVA_ONGD = PROPIEDADES.getString("nombre.docIn.anexo.concesion.definitiva.ongd");
  public static final String NOMBRE_DOC_RESOLUCION_DEFINITIVA_UNIV = PROPIEDADES.getString("nombre.docIn.anexo.concesion.definitiva.univ");
  public static final String NOMBRE_DOC_LIST_RESOLUCION_DEFINITIVA_ONGD = PROPIEDADES.getString("nombre.docIn.anexo.listado.concesion.definitiva.ongd");
  public static final String NOMBRE_DOC_LIST_RESOLUCION_DEFINITIVA_UNIV = PROPIEDADES.getString("nombre.docIn.anexo.listado.concesion.definitiva.univ");

  public static final String NOMBRE_DOC_LIST_SUBSANACIONES_DOCUMENTACION_ONGD = PROPIEDADES.getString("nombre.docIn.sub.doc.conv.ongd");
  public static final String NOMBRE_DOC_LIST_SUBSANACIONES_DOCUMENTACION_UNIV = PROPIEDADES.getString("nombre.docIn.sub.doc.conv.univ");

  public static final String NOMBRE_DOC_IN_SUB_DOC_APORT_SOLI_UNIV = PROPIEDADES.getString("nombre.docIn.sub.soli.doc.apor.univ");
  public static final String NOMBRE_DOC_IN_SUB_DOC_APORT_SOLI_ONGD = PROPIEDADES.getString("nombre.docIn.sub.soli.doc.apor.ongd");

  public static final String ETIQUETA_DOC_GEN_SUB_VAL_ONGD = "SUBSANACIONES_VALORADOR";
  public static final String ETIQUETA_DOC_SUB_SOLI = "DOC_SUB_SOL";
  public static final String ETIQUETA_DOC_SUB_SOLI_ONGD = "DOC_SUB_SOL";
  public static final String ETIQUETA_DOC_EXCL_PROV = "DOC_EXC_PRO";
  public static final String ETIQUETA_DOC_EXCL_PROV_ONGD = "DOC_EXC_PRO";
  public static final String ETIQUETA_DOC_EXCL_DEF_ONGD = "DOC_EXC_DEF";
  public static final String ETIQUETA_DOC_GEN_EXCL_ONGD = "EXCL_TRAM";
  public static final String ETIQUETA_DOC_SUB_SOLI_ALEG_ONGD = "DOC_SUB_ALE";
  public static final String ETIQUETA_DOC_EXCL_DEF_ALEG_ONGD = "DOC_EXC_DEF_ALEG";
  public static final String ETIQUETA_DOC_GEN_EXCL_UNIV = "EXC_TRA_UNI";
  public static final String ETIQUETA_DOC_SUB_SOLI_UNIV = "DOC_SB_SO_U";
  public static final String ETIQUETA_DOC_EXCL_PROV_UNIV = "DOC_EX_PR_U";
  public static final String ETIQUETA_DOC_EXCL_DEF_UNIV = "DOC_EXC_DEF";
  public static final String ETIQUETA_DOC_GEN_SUB_VAL_UNIV = "SUBSANACIONES_VALORADOR_UNIV";
  public static final String ETIQUETA_DOC_SUB_SOLI_ALEG_UNIV = "D_SUB_ALE_U";
  public static final String ETIQUETA_DOC_EXCL_DEF_ALEG_UNIV = "DOC_EXC_DEF_ALEG_UNIV";
  public static final String ETIQUETA_DOC_PRE_VAL = "DOC_PRE_VAL";
  public static final String ETIQUETA_DOC_DES = "DOC_DES";
  public static final String ETIQUETA_DOC_BEN_SUPL = "DOC_BEN_SUP";

  public static final String ETIQUETA_DOC_DES_UNIV = "D_DES_UNIV";
  public static final String ETIQUETA_DOC_PROP_PROV_BEN_SUP = "D_LIS_BE_SU";

  public static final String ETIQUETA_DOC_CON_DEF_ONGD = "ANX_RES_DEF";
  public static final String ETIQUETA_LIST_DOC_CON_DEF_ONGD = "A_C_RES_DEF";

  public static final String ETIQUETA_DOC_CON_DEF_UNIV = "A_RES_DEF_U";
  public static final String ETIQUETA_LIST_DOC_CON_DEF_UNIV = "A_C_R_DEF_U";
  public static final String ETIQUETA_PRESUPUESTO_REFORMULADO = "DOC_P_REFOR";

  /**
   * TAREAS GENERACIÓN INFORMES
   */
  public static final String NOMBRE_TAREA_GENERAR_INFORME_SUBSANACION_SOLI_UNIV = PROPIEDADES.getString("nombre.tarea.generarInforme.subsanacion.soli.univ");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_SUBSANACION_SOLI_ONGD = PROPIEDADES.getString("nombre.tarea.generarInforme.subsanacion.soli.ongd");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_EXCLUSION_PROVISIONAL_SOLI_UNIV = PROPIEDADES
      .getString("nombre.tarea.generarInforme.exclusion.provisional.soli.univ");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_EXCLUSION_PROVISIONAL_SOLI_ONGD = PROPIEDADES
      .getString("nombre.tarea.generarInforme.exclusion.provisional.soli.ongd");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_EXCLUSION_DEFINITIVA_SOLI_UNIV = PROPIEDADES
      .getString("nombre.tarea.generarInforme.exclusion.definitiva.soli.univ");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_EXCLUSION_DEFINITIVA_SOLI_ONGD = PROPIEDADES
      .getString("nombre.tarea.generarInforme.exclusion.definitiva.soli.ongd");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_SUBSANACION_CONV_UNIV = PROPIEDADES.getString("nombre.tarea.generarInforme.subsanacion.conv.univ");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_SUBSANACION_CONV_ONGD = PROPIEDADES.getString("nombre.tarea.generarInforme.subsanacion.conv.ongd");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_SUBSANACION_CONV_UNIV_ALE = PROPIEDADES
      .getString("nombre.tarea.generarInforme.subsanacion.conv.univ.ale");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_SUBSANACION_CONV_ALE = PROPIEDADES.getString("nombre.tarea.generarInforme.subsanacion.conv.ongd.ale");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_EXCLUSION_PROVISIONAL_CONV_UNIV = PROPIEDADES
      .getString("nombre.tarea.generarInforme.exclusion.provisional.conv.univ");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_EXCLUSION_PROVISIONAL_CONV_ONGD = PROPIEDADES
      .getString("nombre.tarea.generarInforme.exclusion.provisional.conv.ongd");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_EXCLUSION_DEFINITIVA_CONV_UNIV = PROPIEDADES
      .getString("nombre.tarea.generarInforme.exclusion.definitiva.conv.univ");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_EXCLUSION_DEFINITIVA_CONV_ONGD = PROPIEDADES
      .getString("nombre.tarea.generarInforme.exclusion.definitiva.conv.ongd");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_DESEST_CONV_ONGD = PROPIEDADES.getString("nombre.tarea.generarInforme.desest.conv.ongd");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_DESEST_CONV_UNIV = PROPIEDADES.getString("nombre.tarea.generarInforme.desest.conv.univ");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_DESEST_DEFINITIVA_SOLI_ONGD = PROPIEDADES
      .getString("nombre.tarea.generarInforme.desest.definitiva.soli.ongd");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_DESEST_DEFINITIVA_SOLI_UNIV = PROPIEDADES
      .getString("nombre.tarea.generarInforme.desest.definitiva.soli.univ");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_PROPUESTA_PROVISIONAL_ONGD = PROPIEDADES
      .getString("nombre.tarea.generarInforme.propuesta.provisional.ongd");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_PROPUESTA_PROVISIONAL_UNIV = PROPIEDADES
      .getString("nombre.tarea.generarInforme.propuesta.provisional.univ");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_PROPUESTA_BENEFICIARIO_ONGD = PROPIEDADES
      .getString("nombre.tarea.generarInforme.propuesta.beneficiario.ongd");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_PROPUESTA_BENEFICIARIO_UNIV = PROPIEDADES
      .getString("nombre.tarea.generarInforme.propuesta.beneficiario.univ");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_SUBSANACION_DOCUMENTACION_CONV_ONGD = PROPIEDADES
      .getString("nombre.tarea.generarInforme.subsanacion.documentacion.conv.ongd");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_SUBSANACION_DOCUMENTACION_CONV_UNIV = PROPIEDADES
      .getString("nombre.tarea.generarInforme.subsanacion.documentacion.conv.univ");

  public static final String NOMBRE_TAREA_GENERAR_INFORME_SUBSANACION_DOCUMENTACION_UNIV = PROPIEDADES
      .getString("nombre.tarea.generarInforme.subsanacion.documentacion.aportada.soli.univ");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_SUBSANACION_DOCUMENTACION_ONGD = PROPIEDADES
      .getString("nombre.tarea.generarInforme.subsanacion.documentacion.aportada.soli.ongd");

  public static final String NOMBRE_TAREA_GENERAR_INFORME_CONCESION_DEFINITIVA_ONGD = PROPIEDADES
      .getString("nombre.tarea.generarInforme.concesion.definitiva.ongd");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_CONCESION_DEFINITIVA_UNIV = PROPIEDADES
      .getString("nombre.tarea.generarInforme.concesion.definitiva.univ");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_LISTADO_CONCESION_DEFINITIVA_ONGD = PROPIEDADES
      .getString("nombre.tarea.generarInforme.listado.concesion.definitiva.ongd");
  public static final String NOMBRE_TAREA_GENERAR_INFORME_LISTADO_CONCESION_DEFINITIVA_UNIV = PROPIEDADES
      .getString("nombre.tarea.generarInforme.listado.concesion.definitiva.univ");

  /** Salto de línea */
  public static final String SALTO_LINEA = "\n";
  /** Tabulación */
  public static final String TABULADOR = "\t";

  /**
   * GENERACIÓN DE INFORMES
   */
  public static final String FORMATO_ODT = "application/vnd.oasis.opendocument.text";
  public static final String FORMATO_PDF = "application/pdf";
  public static final String FORMATO_XML = "application/xml";
  
  public static final String RUTA_FICHERO_GENERADO = PROPIEDADES.getString("ruta.ficheroGenerado");
  public static final String PLANTILLA_SUBSANACION_SOLICITUD_ONGD = PROPIEDADES.getString("plantilla.subsanacion.solicitud.ongd");
  public static final String PLANTILLA_SUBSANACION_SOLICITUD_UNIV = PROPIEDADES.getString("plantilla.subsanacion.solicitud.univ");
  public static final String PLANTILLA_SUBSANACION_CONVOCATORIA_ONGD = PROPIEDADES.getString("plantilla.subsanacion.convocatoria.ongd");
  public static final String PLANTILLA_SUBSANACION_CONVOCATORIA_UNIV = PROPIEDADES.getString("plantilla.subsanacion.convocatoria.univ");
  public static final String PLANTILLA_SUBSANACION_CONVOCATORIA_VUELTA_ONGD = PROPIEDADES.getString("plantilla.subsanacion.convocatoria.vuelta.ongd");
  public static final String PLANTILLA_SUBSANACION_CONVOCATORIA_VUELTA_UNIV = PROPIEDADES.getString("plantilla.subsanacion.convocatoria.vuelta.univ");

  public static final String PLANTILLA_EXCLUSION_PROVISIONAL_SOLICITUD_ONGD = PROPIEDADES.getString("plantilla.exclusion.provisional.solicitud.ongd");
  public static final String PLANTILLA_EXCLUSION_PROVISIONAL_SOLICITUD_UNIV = PROPIEDADES.getString("plantilla.exclusion.provisional.solicitud.univ");
  public static final String PLANTILLA_EXCLUSION_PROVISIONAL_CONVOCATORIA_ONGD = PROPIEDADES.getString("plantilla.exclusion.provisional.convocatoria.ongd");
  public static final String PLANTILLA_EXCLUSION_PROVISIONAL_CONVOCATORIA_UNIV = PROPIEDADES.getString("plantilla.exclusion.provisional.convocatoria.univ");
  public static final String PLANTILLA_EXCLUSION_DEFINITIVA_SOLICITUD_ONGD = PROPIEDADES.getString("plantilla.exclusion.definitiva.solicitud.ongd");
  public static final String PLANTILLA_EXCLUSION_DEFINITIVA_SOLICITUD_UNIV = PROPIEDADES.getString("plantilla.exclusion.definitiva.solicitud.univ");
  public static final String PLANTILLA_EXCLUSION_DEFINITIVA_CONVOCATORIA_ONGD = PROPIEDADES.getString("plantilla.exclusion.definitiva.convocatoria.ongd");
  public static final String PLANTILLA_EXCLUSION_DEFINITIVA_CONVOCATORIA_UNIV = PROPIEDADES.getString("plantilla.exclusion.definitiva.convocatoria.univ");

  public static final String PLANTILLA_DESESTIMADA_CONVOCATORIA_ONGD = PROPIEDADES.getString("plantilla.desestimada.convocatoria.ongd");
  public static final String PLANTILLA_DESESTIMADA_CONVOCATORIA_UNIV = PROPIEDADES.getString("plantilla.desestimada.convocatoria.univ");
  public static final String PLANTILLA_DESESTIMADA_DEFINITIVA_SOLICITUD_ONGD = PROPIEDADES.getString("plantilla.desestimada.definitiva.solicitud.ongd");
  public static final String PLANTILLA_DESESTIMADA_DEFINITIVA_SOLICITUD_UNIV = PROPIEDADES.getString("plantilla.desestimada.definitiva.solicitud.univ");
  public static final String PLANTILLA_RELACION_PROPUESTA_PROVISIONAL = PROPIEDADES.getString("plantilla.relacion.propuesta.provisional");
  public static final String PLANTILLA_RELACION_PROPUESTA_PROVISIONAL_UNIV = PROPIEDADES.getString("plantilla.relacion.propuesta.provisional.univ");
  public static final String PLANTILLA_PROPUESTA_BENEFICIARIO = PROPIEDADES.getString("plantilla.propuesta.beneficiario");
  public static final String PLANTILLA_PROPUESTA_BENEFICIARIO_UNIV = PROPIEDADES.getString("plantilla.propuesta.beneficiario.univ");
  public static final String PLANTILLA_NOTIFICACION_REFORMULACION_ONGD = PROPIEDADES.getString("plantilla.notificacion.reformulacion.ongd");
  public static final String PLANTILLA_NOTIFICACION_REFORMULACION_UNIV = PROPIEDADES.getString("plantilla.notificacion.reformulacion.univ");
  public static final String NOMBRE_INFORME_SALIDA_SUBSANACION_SOLICITUD = PROPIEDADES.getString("nombreInforme.salida.subsanacion.solicitud");
  public static final String NOMBRE_INFORME_SALIDA_SUBSANACION_CONVOCATORIA = PROPIEDADES.getString("nombreInforme.salida.subsanacion.convocatoria");
  public static final String NOMBRE_INFORME_SALIDA_SUBSANACION_DOCUMENTACION_CONVOCATORIA_ONGD = PROPIEDADES
      .getString("nombreInforme.salida.subsanacion.documentacion.aportada.ongd");

  public static final String NOMBRE_INFORME_SALIDA_SUBSANACION_DOCUMENTACION_CONVOCATORIA_UNIV = PROPIEDADES
      .getString("nombreInforme.salida.subsanacion.documentacion.aportada.univ");

  public static final String PLANTILLA_RELACION_CONCESION_DEFINITIVA_ONGD = PROPIEDADES.getString("plantilla.concesion.definitiva.ongd");
  public static final String PLANTILLA_RELACION_CONCESION_DEFINITIVA_UNIV = PROPIEDADES.getString("plantilla.concesion.definitiva.univ");
  public static final String PLANTILLA_LISTADO_CONCESION_DEFINITIVA_ONGD = PROPIEDADES.getString("plantilla.listado.concesion.definitiva.ongd");
  public static final String PLANTILLA_LISTADO_CONCESION_DEFINITIVA_UNIV = PROPIEDADES.getString("plantilla.listado.concesion.definitiva.univ");
  public static final String PLANTILLA_LISTADO_SUBSANACION_DOCUMENTOS_CONVOCATORIA_ONGD = PROPIEDADES
      .getString("plantilla.listado.subsanacion.documentos.convocatoria.ongd");
  public static final String PLANTILLA_LISTADO_SUBSANACION_DOCUMENTOS_CONVOCATORIA_UNIV = PROPIEDADES
      .getString("plantilla.listado.subsanacion.documentos.convocatoria.univ");
  public static final String PLANTILLA_SUBSANACION_DOCUMENTACION_APORTADA_SOLICITUD_ONGD = PROPIEDADES
      .getString("plantilla.subsanacion.documentacion.aportada.solicitud.ongd");
  public static final String PLANTILLA_SUBSANACION_DOCUMENTACION_APORTADA_SOLICITUD_UNIV = PROPIEDADES
      .getString("plantilla.subsanacion.documentacion.aportada.solicitud.univ");

  public static final String NOMBRE_INFORME_SALIDA_EXCLUSION_PROVSIONAL_SOLICITUD = PROPIEDADES
      .getString("nombreInforme.salida.exclusion.provisional.solicitud");
  public static final String NOMBRE_INFORME_SALIDA_EXCLUSION_PROVSIONAL_CONVOCATORIA = PROPIEDADES
      .getString("nombreInforme.salida.exclusion.provisional.convocatoria");
  public static final String NOMBRE_INFORME_SALIDA_EXCLUSION_DEFINITIVA_SOLICITUD = PROPIEDADES
      .getString("nombreInforme.salida.exclusion.definitiva.solicitud");
  public static final String NOMBRE_INFORME_SALIDA_EXCLUSION_DEFINITIVA_CONVOCATORIA = PROPIEDADES
      .getString("nombreInforme.salida.exclusion.definitiva.convocatoria");

  public static final String NOMBRE_INFORME_SALIDA_PROPUESTA_PROVISIONAL_ONGD = PROPIEDADES.getString("nombreInforme.salida.propuesta.provisional.ongd");
  public static final String NOMBRE_INFORME_SALIDA_PROPUESTA_PROVISIONAL_UNIV = PROPIEDADES.getString("nombreInforme.salida.propuesta.provisional.univ");
  public static final String NOMBRE_INFORME_SALIDA_DESESTIMADA_CONVOCATORIA = PROPIEDADES.getString("nombreInforme.salida.desestimada.convocatoria");
  public static final String NOMBRE_INFORME_SALIDA_DESESTIMADA_CONVOCATORIA_UNIV = PROPIEDADES.getString("nombreInforme.salida.desestimada.convocatoria.univ");
  public static final String NOMBRE_INFORME_SALIDA_DESESTIMADA_DEFINITIVA_SOLICITUD = PROPIEDADES
      .getString("nombreInforme.salida.desestimada.definitiva.solicitud");
  public static final String NOMBRE_INFORME_SALIDA_DESESTIMADA_DEFINITIVA_SOLICITUD_UNIV = PROPIEDADES
      .getString("nombreInforme.salida.desestimada.definitiva.solicitud.univ");
  public static final String NOMBRE_INFORME_SALIDA_NOTIFICACION_REFORMULACION_ONGD = PROPIEDADES
      .getString("nombreInforme.salida.notificacion.reformulacion.ongd");
  public static final String NOMBRE_INFORME_SALIDA_NOTIFICACION_REFORMULACION_UNIV = PROPIEDADES
      .getString("nombreInforme.salida.notificacion.reformulacion.univ");
  public static final String NOMBRE_INFORME_SALIDA_PROPUESTA_BENEFICIARIO_ONGD = PROPIEDADES.getString("nombreInforme.salida.propuesta.beneficiario.ongd");
  public static final String NOMBRE_INFORME_SALIDA_PROPUESTA_BENEFICIARIO_UNIV = PROPIEDADES.getString("nombreInforme.salida.propuesta.beneficiario.univ");
  public static final String NOMBRE_INFORME_SALIDA_CONCESION_DEFINITIVA_ONGD = PROPIEDADES.getString("nombreInforme.salida.concesion.definitiva.ongd");
  public static final String NOMBRE_INFORME_SALIDA_CONCESION_DEFINITIVA_UNIV = PROPIEDADES.getString("nombreInforme.salida.concesion.definitiva.univ");
  public static final String NOMBRE_INFORME_SALIDA_LISTADO_CONCESION_DEFINITIVA_ONGD = PROPIEDADES
      .getString("nombreInforme.salida.listado.concesion.definitiva.ongd");
  public static final String NOMBRE_INFORME_SALIDA_LISTADO_CONCESION_DEFINITIVA_UNIV = PROPIEDADES
      .getString("nombreInforme.salida.listado.concesion.definitiva.univ");

  /** Fases del procedimiento ONGD */
  public static final String FASE_ONGD_RECEPCION_SOLICITUD = PROPIEDADES.getString("ongd.recepcion.solicitud");

  /** Fases del procedimiento UNIV */
  public static final String FASE_UNIV_RECEPCION_SOLICITUD = PROPIEDADES.getString("univ.recepcion.solicitud");

  /** Fases comunes para UNIV y ONGD */
  public static final String FASE_COMPROBACION_REQUISITOS = PROPIEDADES.getString("ongd.comprobacion.requisitos");
  public static final String FASE_RESOLUCION_PROVISIONAL = PROPIEDADES.getString("ongd.resolucion.provisional");
  public static final String FASE_RECEPCION_ALEGACION = PROPIEDADES.getString("ongd.recepcion.alegacion");
  public static final String FASE_COMPROBACION_REQUISITOS_ALEGACION = PROPIEDADES.getString("ongd.comprobacion.requisitos.alegacion");
  public static final String FASE_RESOLUCION_DEFINITIVA_EXCLUSION = PROPIEDADES.getString("ongd.resolucion.definitiva.exclusion");
  public static final String FASE_REQUERIMIENTO_SUBSANACION = PROPIEDADES.getString("ongd.requerimiento.subsanacion");
  public static final String FASE_RECEPCION_SUBSANACION = PROPIEDADES.getString("ongd.recepcion.subsanacion");
  public static final String FASE_COMPROBACION_REQUISITOS_SUBSANACION = PROPIEDADES.getString("ongd.comprobacion.requisitos.subsanacion");
  public static final String FASE_COMPROBACION_REQUISITOS_SUBSANACION_VUELTA = PROPIEDADES.getString("ongd.comprobacion.requisitos.subsanacion.vuelta");
  public static final String FASE_REQUERIMIENTO_SUBSANACION_VUELTA = PROPIEDADES.getString("ongd.requerimiento.subsanacion.vuelta");
  public static final String FASE_RECEPCION_SUBSANACION_VUELTA = PROPIEDADES.getString("ongd.recepcion.subsanacion.vuelta");
  public static final String FASE_RESOLUCION_DESESTIMACION_PUNTUACION_REQUISITOS = PROPIEDADES.getString("ongd.resolucion.desestimacion.puntuacion.requisitos");
  public static final String FASE_VALORACION_SUBSANACION = PROPIEDADES.getString("ongd.valoracion.solicitud");
  public static final String FASE_PREVIA_VALORACION_SUBSANACION = PROPIEDADES.getString("ongd.previa.valoracion.solicitud");
  public static final String FASE_RELACION_PROVISIONAL_CONCESION = PROPIEDADES.getString("ongd.relacion.provisional.concesion");
  public static final String FASE_RESOLUCION_DEFINITIVA_CONCESION = PROPIEDADES.getString("ongd.resolucion.definitiva.concesion");
  public static final String FASE_RESOLUCION_DEFINITIVA_DESESTIMACION = PROPIEDADES.getString("ongd.resolucion.definitiva.desestimacion");
  public static final String FASE_COMPROBACION_ALEGA_ACEPTA_REFOR_DOC = PROPIEDADES.getString("ongd.comprobacion.alega.acepta.refor.doc");
  public static final String FASE_COMPROBACION_DOCUMENTOS_APORTADOS = PROPIEDADES.getString("ongd.comprobacion.documentos.aportados");
  public static final String FASE_REQUERIMIENTO_DOCUMENTOS_APORTADOS = PROPIEDADES.getString("fase.requerimiento.documentos.aportados");
  public static final String FASE_COMUNICACION_INICIO = PROPIEDADES.getString("fase.comunicacionInicio");

  /** Fases de los procedimientos de convocatoria */
  public static final String FASE_CONV_INICIO = PROPIEDADES.getString("conv.inicio");
  public static final String FASE_CONV_PRESENTACION = PROPIEDADES.getString("conv.presentacion");
  public static final String FASE_CONV_COMPROBACION_REQUISITOS = PROPIEDADES.getString("conv.comprobacion.requisitos");
  public static final String FASE_CONV_REQUERIMIENTO_SUBSANACION = PROPIEDADES.getString("conv.requerimiento.subsanacion");
  public static final String FASE_CONV_PRESENTACION_SUBSANACION = PROPIEDADES.getString("conv.presentacion.subsanacion");
  public static final String FASE_CONV_COMPROBACION_SUBSANACION_ONGD = PROPIEDADES.getString("conv.comprobacion.subsanaciones.ongd");
  public static final String FASE_CONV_COMPROBACION_SUBSANACION = PROPIEDADES.getString("conv.comprobacion.subsanaciones");
  public static final String FASE_CONV_RESOLUCION_PROVISIONAL_EXCLUSION = PROPIEDADES.getString("conv.resolucion.provisional.exclusion");
  public static final String FASE_CONV_PRESENTACION_ALEGACIONES = PROPIEDADES.getString("conv.presentacion.alegaciones");
  public static final String FASE_CONV_COMPROBACION_ALEGACIONES = PROPIEDADES.getString("conv.comprobacion.alegaciones");
  public static final String FASE_CONV_RESOLUCION_DEFINITVA_EXCLUSION = PROPIEDADES.getString("conv.resolucion.definitiva.exclusion");
  public static final String FASE_CONV_REQUERIMIENTO_SUBSANACIONES_VUELTA = PROPIEDADES.getString("conv.requerimiento.subsanaciones.vuelta");
  public static final String FASE_CONV_PRESENTACION_SUBSANACIONES_VUELTA = PROPIEDADES.getString("conv.presentacion.subsanaciones.vuelta");
  public static final String FASE_CONV_COMPROBACION_SUBSANACIONES_VUELTA = PROPIEDADES.getString("conv.comprobacion.subsanaciones.vuelta");
  public static final String FASE_CONV_RESOLUCION_DESESTIMACION_REQUISITOS = PROPIEDADES.getString("conv.resolucion.desestimacion.requisitos");
  public static final String FASE_CONV_VALORACION_SOLICITUDES = PROPIEDADES.getString("conv.valoracion.solicitudes");
  public static final String FASE_CONV_PROPUESTA_PROVISIONAL_RESOLUCION_DESISTIMIENTO = PROPIEDADES.getString("conv.resolucion.provisional.desestimacion");
  public static final String FASE_CONV_RECEPCION_PRESENTACION_DOCUMENTOS = PROPIEDADES.getString("conv.recepcion.presentacion.documentos");
  public static final String FASE_CONV_COMPROBACION_PRESENTACION_DOCUMENTACION = PROPIEDADES.getString("conv.comprobacion.presentacion.documentacion");
  public static final String FASE_CONV_REQUERIMIENTO_SUBSANACION_DOCUMENTOS_APORTADOS = PROPIEDADES
      .getString("conv.requerimiento.subsanacion.documentos.aportados");
  public static final String FASE_CONV_PRESENTACION_SUBSANACION_DOCUMENTOS_APORTADOS = PROPIEDADES
      .getString("conv.presentacion.subsanacion.documentos.aportados");
  public static final String FASE_CONV_COMPROBACION_SUBSANACION_DOCUMENTOS_APORTADOS = PROPIEDADES
      .getString("conv.comprobacion.subsanacion.documentos.aportados");
  public static final String FASE_CONV_RESOLUCION_DEFINITIVA_CONCESION_DESESTIMACION = PROPIEDADES
      .getString("conv.resolucion.definitiva.concesion.desestimacion");

  /** Variables correspondientes a los informes generados para ONGD y Universidades */
  public static final String FINALIDAD_PROYECTO = PROPIEDADES.getString("var.finalidad.proyecto");
  public static final String FECHA_PUBLICACION_ACCID = PROPIEDADES.getString("var.fecha.publicacion.aacid");
  public static final String ANYO_CONVOCATORIA = PROPIEDADES.getString("var.anyo.convocatoria");
  public static final String FECHA_FIN_PRESENTACIION_ALEG = PROPIEDADES.getString("var.fechafin.presentacion.alegaciones");
  public static final String FECHA_RESOLUCION = PROPIEDADES.getString("var.fecha.resolucion");
  public static final String FECHA_VALORACION = PROPIEDADES.getString("var.fecha.valoracion");
  public static final String FECHA_FIN_PRESENTACIONES = PROPIEDADES.getString("var.fechafin.presentaciones");
  public static final String FECHA_VALORACION_NO_DEFINIDA = PROPIEDADES.getString("var.fecha.valoracion.nodefinida");
  public static final String FECHA_RESOLUCION_NO_DEFINIDA = PROPIEDADES.getString("var.fecha.resolucion.nodefinida");
  public static final String PLAZO_FECHA_FIN_PRESENTACIONES = PROPIEDADES.getString("plazo.fecha.fin.presentaciones");
  public static final String PLAZO_FECHA_FIN_PRESENT_ALEG = PROPIEDADES.getString("plazo.fecha.fin.presentacion.alegaciones");
  public static final String FINALIDAD_NO_DEFINIDA = PROPIEDADES.getString("var.finalidad.nodefinida");

  /** Listas de expedientes por tipos de proyectos ONGD */
  public static final String EXP_PROY_COOP_INT = PROPIEDADES.getString("exp.proy.coop.int");
  public static final String EXP_PROY_ACC_HUM = PROPIEDADES.getString("exp.proy.acc.hum");
  public static final String EXP_PROY_DESARROLLO = PROPIEDADES.getString("exp.proy.desarrollo");
  public static final String EXP_PROY_FOR_INNOV = PROPIEDADES.getString("exp.proy.for.innov");

  public static final String EXP_PROY_COOP_INT_PACODE = PROPIEDADES.getString("exp.proy.coop.int.pacode");
  public static final String EXP_PROY_ACC_HUM_PACODE = PROPIEDADES.getString("exp.proy.acc.hum.pacode");

  public static final String EXP_PROY_COOP_INT_EXCL = PROPIEDADES.getString("exp.proy.coop.int.excl");
  public static final String EXP_PROY_ACC_HUM_EXCL = PROPIEDADES.getString("exp.proy.acc.hum.excl");
  public static final String EXP_PROY_DESARROLLO_EXCL = PROPIEDADES.getString("exp.proy.desarrollo.excl");
  public static final String EXP_PROY_FOR_INNOV_EXCL = PROPIEDADES.getString("exp.proy.for.innov.excl");

  /** Listas de expedientes por tipos de proyectos ONGD BENEFICIARIAS */
  public static final String EXP_PROY_COOP_INT_BENEF = PROPIEDADES.getString("exp.proy.coop.int.benef");
  public static final String EXP_PROY_COOP_INT_BENEF_SAPA = PROPIEDADES.getString("exp.proy.coop.int.benef.sapa");
  public static final String EXP_PROY_ACC_HUM_BENEF = PROPIEDADES.getString("exp.proy.acc.hum.benef");
  public static final String EXP_PROY_ACC_HUM_BENEF_SAPA = PROPIEDADES.getString("exp.proy.acc.hum.benef.sapa");
  public static final String EXP_PROY_DESARROLLO_BENEF = PROPIEDADES.getString("exp.proy.desarrollo.benef");
  public static final String EXP_PROY_FOR_INNOV_BENEF = PROPIEDADES.getString("exp.proy.for.innov.benef");

  /** Listas de expedientes por tipos de proyectos ONGD SUPLENTES */
  public static final String EXP_PROY_COOP_INT_SUPLEN = PROPIEDADES.getString("exp.proy.coop.int.suplen");
  public static final String EXP_PROY_ACC_HUM_SUPLEN = PROPIEDADES.getString("exp.proy.acc.hum.suplen");
  public static final String EXP_PROY_DESARROLLO_SUPLEN = PROPIEDADES.getString("exp.proy.desarrollo.suplen");
  public static final String EXP_PROY_FOR_INNOV_SUPLEN = PROPIEDADES.getString("exp.proy.for.innov.suplen");

  private static final Map<String, Integer> TIPOS_EXCLUSION;

  public static final String ERROR_OBTENER_SOLICITUD = PROPIEDADES.getString("var.error.obtener.solicitud");

  static {
    TIPOS_EXCLUSION = new HashMap<>();
    TIPOS_EXCLUSION.put(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS, 1);
    TIPOS_EXCLUSION.put(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS_SUBSANACION, 2);
    TIPOS_EXCLUSION.put(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS_SUBSANACION_VUELTA, 2);
    TIPOS_EXCLUSION.put(ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS_ALEGACION, 2);
    TIPOS_EXCLUSION.put(ConstantesTramitacion.FASE_COMPROBACION_ALEGA_ACEPTA_REFOR_DOC, 4);
    TIPOS_EXCLUSION.put(ConstantesTramitacion.FASE_COMPROBACION_DOCUMENTOS_APORTADOS, 4);

  }

  @SuppressWarnings("unchecked")
  public static Map<String, Integer> obtenerMapaTiposExclusionesByFases() {
    return MapUtils.unmodifiableMap(TIPOS_EXCLUSION);
  }

  /** TIPOS CRITERIOS */
  public static final String PERTINENCIA = PROPIEDADES.getString("tipoCriterio.pertinencia");
  public static final String VIABILIDAD = PROPIEDADES.getString("tipoCriterio.viabilidad");
  public static final String COHERENCIA = PROPIEDADES.getString("tipoCriterio.coherencia");
  public static final String SOSTENIBILIDAD = PROPIEDADES.getString("tipoCriterio.sostenibilidad");
  public static final String IMPACTO = PROPIEDADES.getString("tipoCriterio.impacto");
  public static final String CONVERGENCIA = PROPIEDADES.getString("tipoCriterio.convergencia");
  public static final String CONECTIVIDAD = PROPIEDADES.getString("tipoCriterio.conectividad");
  public static final String CAP_GESTION = PROPIEDADES.getString("tipoCriterio.capgestion");
  public static final String INCREMENTO = PROPIEDADES.getString("tipoCriterio.incremento");

  public static final String VALORACION_TOTAL = "VALORACION TOTAL";

  public static final BigDecimal VALORACION_MAX = UtilidadesNumero.convertStringToBigDecimalIfNotBlank(PROPIEDADES.getString("criterio.valoracion.max"));
  public static final BigDecimal VALORACION_MAX_CONV = UtilidadesNumero
      .convertStringToBigDecimalIfNotBlank(PROPIEDADES.getString("criterio.valoracion.max.convergencia"));

  public static final BigDecimal PUNTUACION_CAP_GEST_ENTIDAD_NO_ENCONTRADA = UtilidadesNumero
      .convertStringToBigDecimalIfNotBlank(PROPIEDADES.getString("capacidadGestion.puntuacion.entidad.noEncontrada"));

  /** CONSTANTES PARA EL ENVIO DE EMAILS */
  public static final String EMAIL_FROM = "EMAIL_FROM";
  public static final String EMAIL_FROMNAME = "EMAIL_FROM_NAME";
  public static final String EMAIL_SMTP_USERNAME = "EMAIL_SMTP_USERNAME";
  public static final String EMAIL_SMTP_PASSWORD = "EMAIL_SMTP_PASSWORD";
  public static final String EMAIL_HOST = "EMAIL_HOST";
  public static final String EMAIL_PORT = "EMAIL_PORT";
  public static final String EMAIL_TRANSPORT_PROTOCOL = "EMAIL_TRANSPORT_PROTOCOL";
  public static final String EMAIL_SMTP_AUTH = "EMAIL_SMTP_AUTH";
  public static final String EMAIL_START_TLS_ENABLE = "EMAIL_START_TLS_ENABLE";
  public static final String EMAIL_ENABLE_SSL = "EMAIL_ENABLE_SSL";

  public static final String EMAIL_SUBJECT_SUBS = "EMAIL_SUBJECT_SUBS";
  public static final String EMAIL_SUBJECT_EXCL = "EMAIL_SUBJECT_EXCL";
  public static final String EMAIL_SUBJECT_EXCL_DEF = "EMAIL_SUBJECT_EXCL_DEF";
  public static final String EMAIL_SUBJECT_DOC_VAL = "EMAIL_SUBJECT_DOC_VAL";
  public static final String EMAIL_SUBJECT_PRESUPUESTO_VALIDADO = "EMAIL_SUBJECT_PRESUPUESTO_VALIDADO";
  public static final String EMAIL_SUBJECT_RESOLUCION_DESESTIMADA = "EMAIL_SUBJECT_RESOLUCION_DESESTIMADA";
  public static final String EMAIL_SUBJECT_BENEFICIARIOS_SUPLENTES = "EMAIL_SUBJECT_BENEFICIARIOS_SUPLENTES";
  public static final String EMAIL_SUBJECT_CONCESION_DEFINITIVA = "EMAIL_SUBJECT_CONCESION_DEFINITIVA";

  public static final String EMAIL_BODY_SUBS = "EMAIL_BODY_SUBS";
  public static final String EMAIL_BODY_EXCL = "EMAIL_BODY_EXCL";
  public static final String EMAIL_BODY_EXCL_DEF = "EMAIL_BODY_EXCL_DEF";
  public static final String EMAIL_BODY_DOC_VAL = "EMAIL_BODY_DOC_VAL";
  public static final String EMAIL_BODY_PRESUPUESTO_VALIDADO = "EMAIL_BODY_PRESUPUESTO_VALIDADO";
  public static final String EMAIL_BODY_RESOLUCION_DESESTIMADA = "EMAIL_BODY_RESOLUCION_DESESTIMADA";
  public static final String EMAIL_BODY_BENEFICIARIOS_SUPLENTES = "EMAIL_BODY_BENEFICIARIAS_SUPLENTES";
  public static final String EMAIL_BODY_CONCESION_DEFINITIVA = "EMAIL_BODY_CONS_DEF";

  public static final BigDecimal TOTAL_PUNTUACION_MAX_INCREMENTO = UtilidadesNumero
      .convertStringToBigDecimalIfNotBlank(PROPIEDADES.getString("incremento.puntuacion.max"));

  /** CONSTANTES PARA VALIDACION DE PRESUPUESTO */
  public static final String TIPO_ENTIDAD_PUBLICA = PROPIEDADES.getString("nombre.tipo.entidad.publica");

  /** ENTIDAD SOLICITANTE */
  public static final String SOLICITANTE = "Solicitante";
  public static final String CONTRAPARTE = "Contraparte";

  /** FUNCIONES */
  public static final String FUNCION_COFINANCIADORA_DESCRIPCION = "Cofinanciadora";
  public static final String FUNCION_REPRESENTANTE_DESCRIPCION = "Represante de la poblaci\u00F3n destinataria";
  public static final String FUNCION_OTROS_DESCRIPCION = "Otros";
  public static final String FUNCION_AGRUPACION_DESCRIPCION = "Pertenece a agrupaci\u00F3n";

  public static final String FUNCION_COFINANCIADORA_CODIGO = "COF";
  public static final String FUNCION_REPRESENTANTE_CODIGO = "RPD";
  public static final String FUNCION_OTROS_CODIGO = "OTR";
  public static final String FUNCION_AGRUPACION_CODIGO = "PAG";

  public static final String SOLICITUD_SUBVENCION = "es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea.AaciTSolicitudsubongd";

  /** ERROR_OBTENER_EXP_TREWA */
  public static final String ERROR_OBTENER_EXP_TREWA = "Error al obtener el expediente";

  /** TIPOS GASTOS */
  public static final String GASTOS_AI = "A.I.";
  public static final String GASTOS_AII = "A.II.";
  public static final String GASTOS_B = "B";

  public static final String BENEFICIARIA = "Beneficiaria";
  public static final String SUPLENTE = "Suplente";
  // Informes de pantalla de valoracion
  public static final String ETIQUETA_INF_VALORACION_ONGD = PROPIEDADES.getString("etiqueta.informe.valoracion.ongd");
  public static final String ETIQUETA_INF_VALORACION_UNIV = PROPIEDADES.getString("etiqueta.informe.valoracion.univ");
  public static final String ETIQUETA_INF_EVALUACION_ONGD = PROPIEDADES.getString("etiqueta.informe.evaluacion.ongd");
  public static final String ETIQUETA_INF_EVALUACION_UNIV = PROPIEDADES.getString("etiqueta.informe.evaluacion.univ");
  public static final String TIPO_DOCUMENTO_INF_VAL_ONGD = PROPIEDADES.getString("nombre.tipoDocumento.informe.valoracion.ongd");
  public static final String TIPO_DOCUMENTO_INF_VAL_UNIV = PROPIEDADES.getString("nombre.tipoDocumento.informe.valoracion.univ");
  public static final String TIPO_DOCUMENTO_INF_EVAL_ONGD = PROPIEDADES.getString("nombre.tipoDocumento.informe.evaluacion.ongd");
  public static final String TIPO_DOCUMENTO_INF_EVAL_UNIV = PROPIEDADES.getString("nombre.tipoDocumento.informe.evaluacion.univ");
  public static final String RUTA_PLANTILLA_INFORME_VALORACION_ONGD = PROPIEDADES.getString("ruta.plantilla.informe.valoracion.ongd");
  public static final String RUTA_PLANTILLA_INFORME_VALORACION_UNIV = PROPIEDADES.getString("ruta.plantilla.informe.valoracion.univ");
  /** PAISES PACODE */

  public static final String PAIS_SAHARAUI = "Poblaci\u00F3n Saharaui";
  public static final String PAIS_PALESTINA = "Palestina";
  public static final String PAIS_HAITI = "Hait\u00ed";
  public static final String PAIS_MAURITANIA = "Mauritania";
  public static final String PAIS_BURKINA_FASO = "Burkina Faso";
  public static final String PAIS_GUINEA_BISSAU = "Guinea Bissau";
  public static final String PAIS_MALI = "Mal\u00ED";
  public static final String PAIS_MALI_SIN_ACENTO = "Mali";

  public static final String PAIS_MOZAMBIQUE = "Mozambique";
  public static final String PAIS_REPUBLICA_DEMOCRATICA_CONGO = "Rep\u00FAblica Democr\u00E1tica del Congo";
  public static final String PAIS_SENEGAL = "Senegal";
  public static final String PAIS_TOGO = "Togo";
  public static final String PROYECTO = "Proyecto";
  public static final String EMERGENCIA = "emergencia";

  public static final String ESQUEMA_TREWA = "ESQUEMA_TREWA";
  public static final String ERROR_SOLICITUD_NO_VALORADA = "La solicitud no ha sido valorada";
  public static final String TIPO_ENTIDAD_VALORACION = "VALORACI\u00D3N";

  public static final String EXCEPCIONES_EXCLUSIONES_TIPO_2 = PROPIEDADES.getString("excepciones.exclusiones.tipo.2");
  public static final String FASE_FIN_EXCLUSIONES_TIPO_2 = PROPIEDADES.getString("fase.fin.exclusiones.tipo.2");

  // Festivos
  public static final String FESTIVO_NACIONAL = "Nacional";
  public static final String FESTIVO_AUTONOMICO = "Autonómico";
  public static final String FESTIVO_LOCAL = "Local";

  public static final String PLAZO_COMUNICACION_INICIO_MESES = PROPIEDADES.getString("plazo.comunicacionInicio.meses");
  public static final String PLAZO_AVISO_FIN_COMUNICACION_DIAS = PROPIEDADES.getString("plazo.aviso.fin.comunicacionInicio.dias");
  public static final String EMAIL_AVISO_FIN_COMUNICACION_INICIO = PROPIEDADES.getString("email.aviso.fin.comunicacionInicio");
  public static final String AVISO_FIN_COMUNICACION_INICIO = "emailAvisoFinComunicacionInicio";
  public static final String EMAIL_BODY_AVISO_FIN_COMUNICACION_INICIO = "EMAIL_BODY_AVISO_FIN_COMUNICACION_INICIO";
  public static final String EMAIL_SUBJECT_AVISO_FIN_COMUNICACION_INICIO = "EMAIL_SUBJECT_AVISO_FIN_COMUNICACION_INICIO";

  // Tipos Entidad Participante
  public static final String PUBLICA_ESPANIOLA = "Pública española";
  public static final String PUBLICA_LOCAL = "Pública local";
  public static final String PRIVADA_ESPANIOLA = "Privada española";
  public static final String PRIVADA_LOCAL = "Pública local";
  public static final String BENEFICIARIO = "Beneficiario";

  /**
   * Mapas
   */
  // mapa donde aparecera el orden por tipo
  private static final Map<String, String> CODIGO_TIPO_ENTIDAD_PARTICIPANTE;
  static {
    CODIGO_TIPO_ENTIDAD_PARTICIPANTE = new HashMap<>();
    CODIGO_TIPO_ENTIDAD_PARTICIPANTE.put(PUBLICA_ESPANIOLA, "PE");
    CODIGO_TIPO_ENTIDAD_PARTICIPANTE.put(PUBLICA_LOCAL, "PL");
    CODIGO_TIPO_ENTIDAD_PARTICIPANTE.put(PRIVADA_ESPANIOLA, "PRE");
    CODIGO_TIPO_ENTIDAD_PARTICIPANTE.put(PRIVADA_LOCAL, "PRL");
    CODIGO_TIPO_ENTIDAD_PARTICIPANTE.put(BENEFICIARIO, "BEN");
  }

  @SuppressWarnings("unchecked")
  public static Map<String, String> obtenerMapaTipoEntidadParticipante() {
    return MapUtils.unmodifiableMap(CODIGO_TIPO_ENTIDAD_PARTICIPANTE);
  }

  /** Fases recepción y su código */
  public static final String FASE_RECEPCION_SOLICITUD_CONTAINT = PROPIEDADES.getString("fase.recepcion.soilcitud.contain");
  public static final String FASE_RECEPCION_AARPD = PROPIEDADES.getString("fase.rec.aleAceptaReforPresDoc");
  public static final String FASE_RECEPCION_SUB_DOC = PROPIEDADES.getString("fase.rec.presentacion.documentacion");

  public static final String COD_FASE_REC_SOL = PROPIEDADES.getString("codigo.fase.rec.solicitud");
  public static final String COD_FASE_REC_SUB = PROPIEDADES.getString("codigo.fase.rec.subsanacion.1");
  public static final String COD_FASE_REC_ALEGA = PROPIEDADES.getString("codigo.fase.rec.alegacion");
  public static final String COD_FASE_REC_SUB_2 = PROPIEDADES.getString("codigo.fase.rec.subsanacion.2");
  public static final String COD_FASE_REC_AARPD = PROPIEDADES.getString("codigo.fase.rec.aleAceptaReforPresDoc");
  public static final String COD_FASE_REC_SUB_DOC = PROPIEDADES.getString("codigo.fase.rec.sub.doc");
  public static final String COD_FASE_REC_COM_INI = PROPIEDADES.getString("codigo.fase.rec.comun.ini");

  private static final Map<String, String> CODIGO_FASES;

  static {
    CODIGO_FASES = new HashMap<>();
    CODIGO_FASES.put(COD_FASE_REC_SOL, FASE_RECEPCION_SUBSANACION);
    CODIGO_FASES.put(COD_FASE_REC_SUB, FASE_RECEPCION_SUBSANACION);
    CODIGO_FASES.put(COD_FASE_REC_ALEGA, FASE_RECEPCION_ALEGACION);
    CODIGO_FASES.put(COD_FASE_REC_SUB_2, FASE_RECEPCION_SUBSANACION_VUELTA);
    CODIGO_FASES.put(COD_FASE_REC_AARPD, FASE_RECEPCION_AARPD);
    CODIGO_FASES.put(COD_FASE_REC_SUB_DOC, FASE_RECEPCION_SUB_DOC);
    CODIGO_FASES.put(COD_FASE_REC_COM_INI, FASE_COMUNICACION_INICIO);

  }

  @SuppressWarnings("unchecked")
  public static Map<String, String> obtenerMapaCodigoFase() {
    return MapUtils.unmodifiableMap(CODIGO_FASES);
  }
}

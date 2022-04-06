package es.juntadeandalucia.aacid.comuntramitacion.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.aacid.comuntramitacion.utils.Utilidades;
import es.juntadeandalucia.plataforma.PTWanda.PTWandaServiceImpl;
import es.juntadeandalucia.plataforma.comunes.excepciones.BusinessException;
import es.juntadeandalucia.plataforma.service.expediente.IExpediente;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;
import trewa.bd.sql.ClausulaOrderBy;
import trewa.bd.sql.ClausulaWhere;
import trewa.bd.sql.OperadorOrderBy;
import trewa.bd.sql.OperadorWhere;
import trewa.bd.tpo.TpoDate;
import trewa.bd.tpo.TpoPK;
import trewa.bd.trapi.tpo.TrCaducidadExp;
import trewa.bd.trapi.trapiui.TrAPIUI;
import trewa.bd.trapi.trapiui.TrAPIUIFactory;
import trewa.bd.trapi.trapiui.tpo.TrDatosContacto;
import trewa.bd.trapi.trapiui.tpo.TrDocumentoExpediente;
import trewa.bd.trapi.trapiui.tpo.TrEmpleado;
import trewa.bd.trapi.trapiui.tpo.TrEstadoElaboracionDocEni;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;
import trewa.bd.trapi.trapiui.tpo.TrExpedienteCaducado;
import trewa.bd.trapi.trapiui.tpo.TrFaseActualExpediente;
import trewa.bd.trapi.trapiui.tpo.TrFaseExpediente;
import trewa.bd.trapi.trapiui.tpo.TrInteresadoExpediente;
import trewa.bd.trapi.trapiui.tpo.TrPerfilUsuario;
import trewa.bd.trapi.trapiui.tpo.TrRazonInteres;
import trewa.bd.trapi.trapiui.tpo.TrRelacionExpediente;
import trewa.bd.trapi.trapiui.tpo.TrTareaExpediente;
import trewa.bd.trapi.trapiui.tpo.TrUsuario;
import trewa.bd.trapi.trapiui.tpo.TrUsuarioAsignado;
import trewa.exception.TrException;

public class TrewaServiceImpl extends PTWandaServiceImpl implements ITrewaService {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -5914717125041901298L;

  private static final String SE_HA_OBTENIDO_UN_ERROR_AL_OBTENER_LA_FASE_DEL_EXPEDIENTE = "Se ha obtenido un error al obtener la fase del expediente: ";
  protected final transient Log log = LogFactory.getLog(getClass());

  /** Interfaz api de trewa */
  private ITrewaService trewaService;

  @Override
  public TrDocumentoExpediente[] documentosDelExpediente(final Long idExpediente, final String etiquetaDocumento) throws TramitacionException {
    final ClausulaWhere cW = new ClausulaWhere();
    cW.addExpresion(TrDocumentoExpediente.CAMPO_ETIQUETA, OperadorWhere.OP_IGUAL, etiquetaDocumento);
    TrDocumentoExpediente[] documentosExp = null;
    try {
      documentosExp = getApiUI().obtenerDocumentosExpediente(new TpoPK(idExpediente), false, cW, null);
    } catch (final TrException e) {
      throw new TramitacionException("Ha ocurrido un error al obtener los documentos asociados al expediente de trewa: ", e);
    }
    return documentosExp;
  }

  /**
   * Obtener la abreviatura del procedimiento.
   *
   * @param trapi
   *          api de trewa
   * @param idExpediente
   *          identificador del expediente
   * @return Si existe el expediente devuelve la abreviatura, en otro caso devuelve una cadena vacía
   * @throws CoreHelperException
   *           en caso de error se arr
   */
  @Override
  public String getAbreviaturaProcedimiento(final String idExpediente) {

    if (idExpediente == null) {
      return StringUtils.EMPTY;
    }
    final TrExpediente expediente = obtenerExpedienteTrewa(idExpediente);
    if (expediente == null) {
      return StringUtils.EMPTY;
    }
    return expediente.getDEFPROC().getABREVIATURA();
  }

  /**
   * Obtiene la propiedad trApiUI
   *
   * @return el trApiUI
   * @throws TrException
   */
  public TrAPIUI getApiUI() throws TrException {
    try {
      return TrAPIUIFactory.crearAPIUI(ConstantesTramitacion.JNDI_TREWA, ConstantesTramitacion.SISTEMA_ACCID);
    } catch (final Exception e) {
      throw new TrException("Error al obtener la api de la cache", e);
    }
  }

  @Override
  public Long incorporarDocumentoAlExpediente(final TrAPIUI trAPIUI, final IExpediente iExpediente, final String idTipDoc, final File documento,
      final String nombre, final Date fecha) throws TramitacionException {

    FileInputStream fileInput = null;
    try {
      if (!documento.exists()) {
        throw new TramitacionException("No existe el fichero: " + documento.getName());
      }

      final TpoPK idDocExpte = new TpoPK();
      TpoPK idEstadoElab = new TpoPK();

      final String noPresentado = "N";
      final String correcto = "S";
      final String[] fases = iExpediente.getReferenciaFaseActual().split(",");
      final TrEstadoElaboracionDocEni[] estados = obtenerEstadosElaboracion();

      if (!ArrayUtils.isEmpty(estados)) {
        idEstadoElab = estados[0].getREFESTELAB();
      }
      trAPIUI.incorporarDocumentoNoDefinido(new TpoPK(iExpediente.getRefExpediente()), new TpoPK(idTipDoc), new TpoPK(fases[0]), new TpoDate(fecha.getTime()),
          noPresentado, correcto, null, null, StringUtils.EMPTY, idDocExpte, null, idEstadoElab, null);
      if (idDocExpte.getPkVal() == null) {

        throw new TramitacionException("Hubo un problema al guardar el documento en Trewa: " + documento.getName());
      }

      fileInput = new FileInputStream(documento);

      trAPIUI.adjuntarFicheroDocumento(idDocExpte, fileInput, nombre, ConstantesTramitacion.FORMATO_ODT, documento.length());

      trAPIUI.modificarEstadoDocumento(idDocExpte, "R", null);

      return idDocExpte.getPkVal().longValue();

    } catch (TrException | FileNotFoundException | BusinessException e) {
      throw new TramitacionException("", e);
    } finally {
      IOUtils.closeQuietly(fileInput);
    }

  }

  @Override
  public void modificarDatosExpediente(final TpoPK idExp, final String numExp, final String tituloExp, final String observaciones, final TpoPK idUniOrg,
      final TpoPK idUniOrgEnvia) throws TramitacionException {
    try {
      getApiUI().modificarDatosExpediente(idExp, numExp, tituloExp, observaciones, idUniOrg, idUniOrgEnvia);
    } catch (final TrException e) {
      throw new TramitacionException("Ha ocurrido un error al modificar los datos del expediente en trewa: ", e);
    }
  }

  @Override
  public TrDatosContacto obtenerDatosContactoSolicitante(final String iExp) throws TramitacionException {
    TrDatosContacto datosContactoSolicitante = null;
    try {
      final TrInteresadoExpediente interesado = obtenerInteresadoExpediente(iExp);
      final ClausulaWhere cw = new ClausulaWhere();
      cw.addExpresion(TrDatosContacto.CAMPO_REFDATOCONT, OperadorWhere.OP_IGUAL, interesado.getREFDATOCONT().getPkVal().toString());
      final TrDatosContacto[] listaDatos = getApiUI().obtenerDatosContactoInteresado(interesado.getINTERESADO().getREFINTERESADO(), cw, null);
      datosContactoSolicitante = listaDatos[0];
    } catch (final TrException e) {
      throw new TramitacionException("Se ha producido un error al recuperar los datos de contacto del solicitante", e);
    }
    return datosContactoSolicitante;
  }

  @Override
  public TrEmpleado[] obtenerEmpleados(final TrUsuarioAsignado trUsuarioAsignado) throws TramitacionException {
    try {
      return getApiUI().obtenerEmpleados(trUsuarioAsignado.getUSUARIO().getCODUSUARIO(), null, null, null, null);
    } catch (final TrException e) {
      throw new TramitacionException("Se ha producido un error al recuperar los empleados del usuario asignado al expediente", e);
    }
  }

  /**
   * @throws TrException
   */
  private TrEstadoElaboracionDocEni[] obtenerEstadosElaboracion() throws TrException {
    final ClausulaWhere cwEstado = new ClausulaWhere();
    cwEstado.addExpresion(TrEstadoElaboracionDocEni.CAMPO_CODIGO, OperadorWhere.OP_IGUAL, "EE01");
    return getApiUI().obtenerEstadoElaboracionDocEni((TpoPK) null, cwEstado, (ClausulaOrderBy) null);
  }

  @Override
  public List<TrExpediente> obtenerExpedientesHijos(final TpoPK idExpediente, final String fases) throws TrException {
    final List<TrExpediente> listaExpedientes = new ArrayList<>();
    ClausulaWhere cw = new ClausulaWhere();
    cw.addExpresion(TrRelacionExpediente.CAMPO_TIPORELACION, OperadorWhere.OP_IGUAL, "M");
    final TrRelacionExpediente[] relacionesExpediente = getApiUI().obtenerRelacionesExpediente(idExpediente, cw, null);

    if (relacionesExpediente == null || relacionesExpediente.length == 0) {
      return listaExpedientes;
    }

    TpoPK tpoExpRelacionado;
    cw = new ClausulaWhere();
    cw.addExpresion(TrFaseActualExpediente.CAMPO_ABIERTAEVENTO, OperadorWhere.OP_IGUAL, "N");

    for (final TrRelacionExpediente relExp : relacionesExpediente) {
      tpoExpRelacionado = relExp.getREFEXPEDIENTE();
      // Obtenemos fase del expediente hijo
      final TrFaseActualExpediente[] faseActual = getApiUI().obtenerFaseActualExpediente(tpoExpRelacionado, null, null);
      // Si la fase del expediente hijo se encuentra en la lista de fases o no se filtra por fases se obtiene el expediente relacionado
      if ((StringUtils.isNotBlank(fases) && StringUtils.contains(fases, faseActual[0].getFASE().getDESCRIPCION())) || StringUtils.isBlank(fases)) {
        final TrExpediente[] expedienteRelacionado = getApiUI().obtenerExpedientes(tpoExpRelacionado, null, null);

        if (expedienteRelacionado == null || expedienteRelacionado.length == 0) {
          throw new TrException("Ha ocurrido un error al obtener los datos del expediente trewa: " + tpoExpRelacionado.toString());
        }
        listaExpedientes.add(expedienteRelacionado[0]);
      }
    }
    return listaExpedientes;

  }

  @Override
  public TrExpediente obtenerExpedienteTrewa(final String idExp) {
    final ClausulaWhere where = new ClausulaWhere();
    where.addExpresion(TrExpediente.CAMPO_REFEXP, OperadorWhere.OP_IGUAL, idExp);
    TrExpediente[] expedientes = null;
    final TpoPK tpoPK = new TpoPK();
    tpoPK.setPkVal(new BigDecimal(idExp));
    try {
      expedientes = getApiUI().obtenerExpedientes(tpoPK, null, null);
    } catch (final TrException e) {
      log.error(e);
    }
    if (expedientes != null) {
      return expedientes[0];
    }
    return null;
  }

  @Override
  public TrExpedienteCaducado[] obtenerCaducidadExpedientes(final String idExp) {
    final ClausulaWhere where = new ClausulaWhere();
    if (StringUtils.isNotBlank(idExp)) {
      where.addExpresion(TrCaducidadExp.CAMPO_REFEXPEDIENTE, OperadorWhere.OP_IGUAL, idExp);
    }
    TrExpedienteCaducado[] expedientes = null;
    final TpoPK tpoPK = new TpoPK();
    tpoPK.setPkVal(new BigDecimal(idExp));
    try {
      expedientes = getApiUI().obtenerExpedientesCaducados(null, null, "A", where, null);
    } catch (final TrException e) {
      log.error(e);
    }
    return expedientes;
  }

  @Override
  public TrFaseExpediente obtenerFaseExpediente(final String idExpEnFase) throws TramitacionException {
    try {
      final ClausulaWhere clausulaWhere = new ClausulaWhere();
      clausulaWhere.addExpresion(TrFaseExpediente.CAMPO_REFEXPXFAS, OperadorWhere.OP_IGUAL, idExpEnFase);
      final TrFaseExpediente[] faseExpediente = getApiUI().obtenerFasesExpediente(null, clausulaWhere, null);
      return faseExpediente[0];
    } catch (final TrException e) {
      throw new TramitacionException(SE_HA_OBTENIDO_UN_ERROR_AL_OBTENER_LA_FASE_DEL_EXPEDIENTE, e);
    }
  }

  @Override
  public TrFaseExpediente obtenerFaseExpedienteByIdExp(final String idExp) throws TramitacionException {
    try {
      final ClausulaWhere clausulaWhere = new ClausulaWhere();
      clausulaWhere.addExpresion(TrFaseExpediente.CAMPO_REFEXPEDIENTE, OperadorWhere.OP_IGUAL, idExp);
      final ClausulaOrderBy order = new ClausulaOrderBy();
      order.addExpresion(TrFaseExpediente.CAMPO_REFEXPXFAS, OperadorOrderBy.DESCENDENTE);
      final TrFaseExpediente[] faseExpediente = getApiUI().obtenerFasesExpediente(null, clausulaWhere, order);
      return faseExpediente[0];
    } catch (final TrException e) {
      throw new TramitacionException(SE_HA_OBTENIDO_UN_ERROR_AL_OBTENER_LA_FASE_DEL_EXPEDIENTE, e);
    }
  }

  @Override
  public String obtenerFaseExpedientePadre(final TpoPK idExpediente) throws TramitacionException {
    try {
      final TrRelacionExpediente[] relacionesExpediente = getApiUI().obtenerRelacionesExpediente(idExpediente, null, null);
      String descFase = "";
      if (relacionesExpediente != null) {
        for (final TrRelacionExpediente rel : relacionesExpediente) {
          final TrExpediente[] expedientes = getApiUI().obtenerExpedientes(rel.getREFEXPEDIENTE(), null, null);
          final TrExpediente exp = expedientes[0];
          final String abrevONGD = Utilidades.obtenerAbreviaturaConvocatoriaONGD();
          final String abrevUNIV = Utilidades.obtenerAbreviaturaConvocatoriaUNIV();
          if (exp.getDEFPROC().getABREVIATURA().equals(abrevONGD) || exp.getDEFPROC().getABREVIATURA().equals(abrevUNIV)) {
            final ClausulaWhere cw = new ClausulaWhere();
            cw.addExpresion(TrFaseActualExpediente.CAMPO_ABIERTAEVENTO, OperadorWhere.OP_IGUAL, "N");
            final TrFaseActualExpediente[] faseActual = getApiUI().obtenerFaseActualExpediente(exp.getREFEXP(), cw, null);
            descFase = faseActual[0].getFASE().getDESCRIPCION();
            break;
          }
        }
      }
      return descFase;
    } catch (final TrException e) {
      throw new TramitacionException("Se ha obtenido un error al obtener la fase de la convocatoria asociada al expediente: ", e);
    }
  }

  @Override
  public TrInteresadoExpediente obtenerInteresadoExpediente(final String iExp) throws TramitacionException {
    TrInteresadoExpediente interesadoExp = null;
    try {
      final ClausulaWhere where = new ClausulaWhere();
      where.addExpresion(TrRazonInteres.CAMPO_ABREVIATURA, OperadorWhere.OP_IGUAL, ConstantesTramitacion.RAZON_INTERES_SOLICITANTE);
      final TrRazonInteres[] listaRazones = getApiUI().obtenerRazonesInteres(where, null);
      if (listaRazones != null && listaRazones.length != 0) {
        final TrRazonInteres razon = listaRazones[0];
        final TrInteresadoExpediente[] lista = getApiUI().obtenerInteresadosExpediente(new TpoPK(iExp), null, razon.getREFRAZONINT(), null, null);
        if (lista != null && lista.length != 0) {
          interesadoExp = lista[0];
        }
      }
    } catch (final TrException e) {
      throw new TramitacionException("Se ha producido un error al recuperar los datos de contacto del solicitante", e);
    }
    return interesadoExp;
  }

  @Override
  public TrTareaExpediente[] obtenerTareasExpediente(final String idExp, final String nombreTarea) throws TramitacionException {
    final ClausulaWhere where = new ClausulaWhere();

    where.addExpresion(TrTareaExpediente.CAMPO_NOMBRETAREA, OperadorWhere.OP_IGUAL, nombreTarea);
    final TpoPK tpoPK = new TpoPK();
    tpoPK.setPkVal(new BigDecimal(idExp));

    try {
      return getApiUI().obtenerTareasExpediente(tpoPK, false, where, null);
    } catch (final TrException e) {
      log.error(e);
      throw new TramitacionException("Se ha producido un error al recuperar los tareas asociadas al expediente", e);
    }
  }

  @Override
  public TrUsuarioAsignado[] obtenerUsuarioAsignadoExpediente(final String iExp) throws TramitacionException {
    try {
      return getApiUI().obtenerUsuariosAsignadosExpediente(new TpoPK(iExp), null, null);

    } catch (final TrException e) {
      throw new TramitacionException("Se ha producido un error al recuperar los usuarios asignados al expediente", e);
    }
  }

  @Override
  public InputStream recuperarDocumentoExpediente(final TpoPK refDocExp) throws TramitacionException {
    try {
      return getApiUI().recuperarDocumentoExpediente(refDocExp, null, null);
    } catch (final TrException e) {
      throw new TramitacionException("Ha ocurrido un error al recuperar el binario para el documento de trewa: " + refDocExp, e);
    }
  }

  @Override
  public TrFaseExpediente[] obtenerFasesExpediente(final TpoPK idExp) throws TramitacionException {
    try {
      return getApiUI().obtenerFasesExpediente(idExp, null, null);
    } catch (final TrException e) {
      throw new TramitacionException(SE_HA_OBTENIDO_UN_ERROR_AL_OBTENER_LA_FASE_DEL_EXPEDIENTE, e);
    }
  }

  @Override
  public TrFaseActualExpediente[] obtenerFaseActualExpediente(final TpoPK idExp) throws TramitacionException {
    try {
      return getApiUI().obtenerFaseActualExpediente(idExp, null, null);
    } catch (final TrException e) {
      throw new TramitacionException(SE_HA_OBTENIDO_UN_ERROR_AL_OBTENER_LA_FASE_DEL_EXPEDIENTE, e);
    }
  }

  public TrDocumentoExpediente obtenerDocumentoExpediente(final String idDocumentoExpediente) throws TramitacionException {
    try {
      ClausulaWhere where = new ClausulaWhere();
      where.addExpresion(TrDocumentoExpediente.CAMPO_REFDOCEXP, OperadorWhere.OP_IGUAL, idDocumentoExpediente);
      TrDocumentoExpediente[] documentos = getApiUI().obtenerDocumentosExpediente(null, false, where, null);

      return documentos[0];
    } catch (TrException e) {
      throw new TramitacionException(SE_HA_OBTENIDO_UN_ERROR_AL_OBTENER_LA_FASE_DEL_EXPEDIENTE, e);
    }
  }

  public void eliminarDocumento(final TpoPK idDocumentoExpediente) throws TramitacionException {

    try {
      getApiUI().eliminarDocumento(idDocumentoExpediente);
    } catch (TrException e) {
      throw new TramitacionException(SE_HA_OBTENIDO_UN_ERROR_AL_OBTENER_LA_FASE_DEL_EXPEDIENTE, e);
    }
  }

  @Override
  public List<Perfil> obtenerPerfilesUsuario(final String codigoUsuario) throws BusinessException {
    final ArrayList<Perfil> perfilesUsuario = new ArrayList<>();

    try {
      final TrPerfilUsuario[] trPerfilesUsuario = getApiUI().obtenerPerfilesUsuario(codigoUsuario, (ClausulaWhere) null, (ClausulaOrderBy) null);
      final TrPerfilUsuario[] var3 = trPerfilesUsuario;
      final int var4 = trPerfilesUsuario.length;

      for (int var5 = 0; var5 < var4; ++var5) {
        final TrPerfilUsuario perfilTrewa = var3[var5];
        final Perfil perfilPlataforma = new Perfil();
        perfilPlataforma.setIdPerfil(perfilTrewa.getREFPERFILUSU().getPkVal().toString());
        perfilPlataforma.setNombre(perfilTrewa.getNOMBRE());
        perfilesUsuario.add(perfilPlataforma);
      }

      return perfilesUsuario;
    } catch (final TrException var8) {
      throw new BusinessException(var8.getMessage());
    }
  }

  @Override
  public List<TrExpediente> obtenerExpedientesHermanos(final TpoPK idExpediente, final String fases) {
    List<TrExpediente> listaExpedientes = new ArrayList<>();
    final ClausulaWhere cw = new ClausulaWhere();
    // Para obtener el padre
    cw.addExpresion(TrRelacionExpediente.CAMPO_TIPORELACION, OperadorWhere.OP_IGUAL, "D");
    try {
      final TrRelacionExpediente[] relacionesExpediente = getApiUI().obtenerRelacionesExpediente(idExpediente, null, null);
      if (relacionesExpediente == null || relacionesExpediente.length == 0) {
        return listaExpedientes;
      } else {
        final TpoPK tpoExpRelacionado = relacionesExpediente[0].getREFEXPEDIENTE();
        // Obtenemos fase del expediente relacionado
        final TrExpediente[] expedientePadre = getApiUI().obtenerExpedientes(tpoExpRelacionado, null, null);
        if (expedientePadre == null || expedientePadre.length == 0) {
          throw new TrException("Ha ocurrido un error al obtener los datos del expediente trewa: " + tpoExpRelacionado.toString());
        }
        listaExpedientes = obtenerExpedientesHijos(expedientePadre[0].getREFEXP(), fases);
      }
    } catch (final TrException e) {
      log.error("Se ha producido un error al recuperar los expedientes hermanos del expediente con id " + idExpediente.toString(), e);
    }
    return listaExpedientes;
  }

  @Override
  public TrUsuario obtenerUsuario(final String codUsuario) throws TramitacionException {
    try {
      final ClausulaWhere filtro = new ClausulaWhere();
      filtro.addExpresion(TrUsuario.CAMPO_CODUSUARIO, OperadorWhere.OP_IGUAL, codUsuario);

      final TrUsuario[] usuario = getApiUI().obtenerUsuarios(null, filtro, null);
      if (ArrayUtils.isEmpty(usuario)) {
        return null;
      } else {
        return usuario[0];
      }
    } catch (final TrException e) {
      throw new TramitacionException("Se ha producido un error al recuperar los datos del usuario", e);
    }
  }
  
  public boolean compruebaFaseCorrectaExpediente(final TrExpediente exp, final String fase) throws TramitacionException {
    final TrFaseActualExpediente[] fasesActualExpediente = trewaService.obtenerFaseActualExpediente(exp.getREFEXP());
    if (fasesActualExpediente == null) {
      throw new TramitacionException("Error al obtener la fase actual del expediente: " + exp.getNUMEXP());
    }
    final TrFaseActualExpediente faseActual = fasesActualExpediente[0];
    return faseActual.getFASE().getNOMBRE().equalsIgnoreCase(fase);
  }

  /**
   * Establece el valor de la propiedad trewaService
   *
   * @param trewaService
   *          el trewaService para establecer
   */
  public void setTrewaService(final ITrewaService trewaService) {
    this.trewaService = trewaService;
  }
}
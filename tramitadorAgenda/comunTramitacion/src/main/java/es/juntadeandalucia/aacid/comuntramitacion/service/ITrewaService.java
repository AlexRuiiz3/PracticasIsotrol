package es.juntadeandalucia.aacid.comuntramitacion.service;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.plataforma.comunes.excepciones.BusinessException;
import es.juntadeandalucia.plataforma.service.expediente.IExpediente;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;
import trewa.bd.tpo.TpoPK;
import trewa.bd.trapi.trapiui.TrAPIUI;
import trewa.bd.trapi.trapiui.tpo.TrDatosContacto;
import trewa.bd.trapi.trapiui.tpo.TrDocumentoExpediente;
import trewa.bd.trapi.trapiui.tpo.TrEmpleado;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;
import trewa.bd.trapi.trapiui.tpo.TrExpedienteCaducado;
import trewa.bd.trapi.trapiui.tpo.TrFaseActualExpediente;
import trewa.bd.trapi.trapiui.tpo.TrFaseExpediente;
import trewa.bd.trapi.trapiui.tpo.TrInteresadoExpediente;
import trewa.bd.trapi.trapiui.tpo.TrTareaExpediente;
import trewa.bd.trapi.trapiui.tpo.TrUsuario;
import trewa.bd.trapi.trapiui.tpo.TrUsuarioAsignado;
import trewa.exception.TrException;

public interface ITrewaService extends Serializable {

  /**
   * Obtiene los documentos asociados al expediente
   *
   * @param idExpediente
   * @return
   * @throws TramitacionException
   */
  TrDocumentoExpediente[] documentosDelExpediente(final Long idExpediente, final String tipoDocumento) throws TramitacionException;

  /**
   * Obtener abreviatura del procedimiento
   *
   * @param idExpediente
   *          id del expediente
   * @return abreviatura
   */
  String getAbreviaturaProcedimiento(String idExpediente);

  /**
   * Guarda un documento en el expediente Trewa.
   *
   * @param trAPIUI
   * @param iExpediente
   * @param idTipDoc
   * @param documento
   * @param nombre
   * @param fecha
   * @return
   * @throws TramitacionException
   */
  Long incorporarDocumentoAlExpediente(TrAPIUI trAPIUI, IExpediente iExpediente, String idTipDoc, File documento, String nombre, Date fecha)
      throws TramitacionException;

  void modificarDatosExpediente(TpoPK idExp, String numExp, String tituloExp, String observaciones, TpoPK idUniOrg, TpoPK idUniOrgEnvia)
      throws TramitacionException;

  /**
   * Obtener los datos del interesado
   *
   * @param iExp
   * @return
   * @throws TramitacionException
   */
  TrDatosContacto obtenerDatosContactoSolicitante(String iExp) throws TramitacionException;

  /**
   * Obtenemos empleados
   *
   * @param trUsuarioAsignado
   * @return
   * @throws TramitacionException
   */
  TrEmpleado[] obtenerEmpleados(TrUsuarioAsignado trUsuarioAsignado) throws TramitacionException;

  /**
   * Obtienene los expedientes hijos del expediente 'idExpediente'
   *
   * @param idExp
   * @param fases
   * @return
   * @throws TramitacionException
   */
  List<TrExpediente> obtenerExpedientesHijos(TpoPK idExpediente, String fases) throws TrException;

  /**
   * Obtener id Expediente de trewa
   *
   * @param idExp
   * @param apiUI
   * @return {@link TrExpediente}
   */
  TrExpediente obtenerExpedienteTrewa(final String idExp);

  TrFaseExpediente obtenerFaseExpediente(String idExpEnFase) throws TramitacionException;

  TrFaseExpediente[] obtenerFasesExpediente(final TpoPK idExp) throws TramitacionException;

  String obtenerFaseExpedientePadre(TpoPK idExpediente) throws TramitacionException;

  /**
   * Obtener Interesado expediente
   *
   * @param iExp
   * @return
   * @throws TramitacionException
   */
  TrInteresadoExpediente obtenerInteresadoExpediente(String iExp) throws TramitacionException;

  /**
   * Obtienene las tareas asociadas al expediente
   *
   * @param idExp
   * @return
   * @throws TramitacionException
   */
  TrTareaExpediente[] obtenerTareasExpediente(final String idExp, final String idTarea) throws TramitacionException;

  /**
   * Obtenemos un usuario asignado al expediente
   *
   * @param iExp
   * @return
   * @throws TramitacionException
   */
  TrUsuarioAsignado[] obtenerUsuarioAsignadoExpediente(String iExp) throws TramitacionException;

  InputStream recuperarDocumentoExpediente(TpoPK refDocExp) throws TramitacionException;

  TrFaseActualExpediente[] obtenerFaseActualExpediente(final TpoPK idExp) throws TramitacionException;

  /**
   * Obtenemos listado de perfiles del usuario pasando el código usuario
   *
   * @param codigoUsuario
   * @return
   * @throws BusinessException
   */
  List<Perfil> obtenerPerfilesUsuario(String codigoUsuario) throws BusinessException;

  /**
   * Obtenemos la fase en la que se encuentra el expediente
   *
   * @param idExp
   * @return
   * @throws TramitacionException
   */
  TrFaseExpediente obtenerFaseExpedienteByIdExp(String idExp) throws TramitacionException;

  /**
   * Obtenemos los expedientes hermanos
   *
   * @param idExpediente
   * @param fases
   * @return
   * @throws TrException
   */
  List<TrExpediente> obtenerExpedientesHermanos(TpoPK idExpediente, String fases) throws TrException;
  
  /**
   * Obtenemos expedientes caducados
   *
   * @param idExp
   * @return
   */
  TrExpedienteCaducado[] obtenerCaducidadExpedientes(String idExp);

  /**
   * Obtenemos el usuario con el código usuario enviado por parámetro
   *
   * @param codUsuario
   * @return
   * @throws TramitacionException
   */
  TrUsuario obtenerUsuario(String codUsuario) throws TramitacionException;

  /**
   * Comprobamos que el expediente esté en la fase correcta
   *
   * @param exp
   * @param fase
   * @return
   * @throws TramitacionException
   */
  boolean compruebaFaseCorrectaExpediente(TrExpediente exp, String fase) throws TramitacionException;
  
  TrDocumentoExpediente obtenerDocumentoExpediente(final String idDocumentoExpediente) throws TramitacionException;

  void eliminarDocumento(final TpoPK idDocumentoExpediente) throws TramitacionException;
}

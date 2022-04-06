package es.juntadeandalucia.aacid.comuntramitacion.service;

import java.io.File;
import java.util.Map;

import es.juntadeandalucia.aacid.comuntramitacion.dto.DocumentoExpedienteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;
import trewa.bd.tpo.TpoPK;

public interface ITareasService {

  /**
   * Incorporar documento al expediente
   * 
   * @param documento
   * @param nombreDocumento
   * @param fase
   * @param usuario
   * @param documentoVuelta
   * @throws TramitacionException
   */
  void incorporarDocumentoExpediente(File documento, String nombreDocumento, String fase, UsuarioWeb usuario, DocumentoExpedienteDTO documentoVuelta)
      throws TramitacionException;

  void finalizarTarea(String nombreTarea, TpoPK idExpediente, UsuarioWeb usuario) throws TramitacionException;

  /**
   * metodo para generar informes
   * 
   * @param datosVariables
   * @param rutaPlantilla
   * @param nombreInformeSalida
   * @param nombreTipoDocumento
   * @param nombreTarea
   * @param usuario
   * @throws TramitacionException
   */
  DocumentoExpedienteDTO generarInformes(Map<String, Object> datosVariables, String rutaPlantilla, String nombreInformeSalida, String nombreTipoDocumento,
      String nombreTarea, UsuarioWeb usuario) throws TramitacionException;

}

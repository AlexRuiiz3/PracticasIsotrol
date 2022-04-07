package es.juntadeandalucia.aacid.comuntramitacion.service;

import java.io.File;
import java.util.Map;

import es.juntadeandalucia.aacid.comuntramitacion.dto.DocumentoExpedienteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;

public interface IGeneracionDocumentosService {

  byte[] generarDocumento(byte[] plantilla, Map<String, Object> datos) throws TramitacionException;

  File transformarOdtAPdf(File ficheroODT, String nombrePDF) throws TramitacionException;

  DocumentoExpedienteDTO generarIncorporarInformeValoracion(Map<String, Object> mapaVariables, String etiquetaInfValoracion, String nombreInforme,
      String rutaPlantilla, String nombreTipoDocumento, String nExp, String codOV, UsuarioWeb user) throws TramitacionException;

  DocumentoExpedienteDTO generarIncorporarInforme(Map<String, Object> mapaVariables, String nombreInformeSalida, String rutaPlantilla,
      String nombreTipoDocumento, String nExp, UsuarioWeb user) throws TramitacionException;

  DocumentoExpedienteDTO incorporarDocumentoModificado(byte[] bytesDocumento, final String idDocumentoExpediente, final UsuarioWeb user)
      throws TramitacionException;
}

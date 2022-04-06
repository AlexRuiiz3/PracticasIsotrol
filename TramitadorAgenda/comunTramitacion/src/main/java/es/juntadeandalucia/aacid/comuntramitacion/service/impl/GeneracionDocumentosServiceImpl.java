package es.juntadeandalucia.aacid.comuntramitacion.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.opensymphony.xwork2.inject.Inject;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.DocumentoExpedienteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.IGeneracionDocumentosService;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITareasService;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.plataforma.comunes.excepciones.ArchitectureException;
import es.juntadeandalucia.plataforma.openoffice.ConexionOpenOffice;
import es.juntadeandalucia.plataforma.service.documentacion.IDocumentacionService;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;
import net.sf.jooreports.templates.DocumentTemplate;
import net.sf.jooreports.templates.DocumentTemplateException;
import net.sf.jooreports.templates.DocumentTemplateFactory;
import trewa.bd.trapi.trapiui.tpo.TrDocumentoExpediente;

public class GeneracionDocumentosServiceImpl implements IGeneracionDocumentosService {

  /** Log log, Atributo para los mensajes de log */
  protected final Log log = LogFactory.getLog(getClass());

  /** Servicio de open office. */
  @Inject
  private IDocumentacionService documentacionService;
  private ITareasService tareasService;
  private ITrewaService trewaService;

  @Override
  public byte[] generarDocumento(final byte[] plantilla, final Map<String, Object> datos) throws TramitacionException {

    if (plantilla == null) {
      log.error("Error al recuperar el contenido del documento de la plantilla. ");
      throw new TramitacionException("Error al recuperar el contenido del documento de la plantilla. ");
    }

    // Generar documento
    return transformarDocumento(datos, new ByteArrayInputStream(plantilla));
  }

  private byte[] transformarDocumento(final Object datos, final InputStream templateDoc) throws TramitacionException {

    byte[] resultado = null;
    DocumentTemplate template;
    final DocumentTemplateFactory documentTemplateFactory = new DocumentTemplateFactory();
    try {
      template = documentTemplateFactory.getTemplate(templateDoc);
      final ByteArrayOutputStream output = new ByteArrayOutputStream();
      template.createDocument(datos, output);
      resultado = output.toByteArray();

    } catch (IOException | DocumentTemplateException e) {
      log.error("Error al generar el documento mediante JODReport", e);
      throw new TramitacionException("Error al generar el documento", e);
    }

    return resultado;
  }

  @Override
  public File transformarOdtAPdf(final File ficheroODT, final String nombrePDF) throws TramitacionException {

    final File ficheroPdf = new File(ConstantesTramitacion.RUTA_FICHERO_GENERADO + nombrePDF);

    try {
      final ConexionOpenOffice conexionOO = new ConexionOpenOffice();
      conexionOO.establecerConexionOpenOffice();
      final OfficeDocumentConverter converter = new OfficeDocumentConverter(conexionOO.getOfficeManager());
      converter.convert(ficheroODT, ficheroPdf);
      conexionOO.cerrarConexionOpenOffice();
    } catch (final ArchitectureException e) {
      throw new TramitacionException("error al convertir el documento a PDF", e);
    }

    return ficheroPdf;
  }

  @Override
  public DocumentoExpedienteDTO generarIncorporarInformeValoracion(final Map<String, Object> mapaVariables, final String etiquetaInfValoracion,
      final String nombreInforme, final String rutaPlantilla, final String nombreTipoDocumento, final String codOV, final String nExp, final UsuarioWeb user)
      throws TramitacionException {
    final Resource plantilla = new ClassPathResource(rutaPlantilla);
    byte[] bytesDocumento;
    String nombrePDF;
    final String nombreDocumento;
    File documentoGenerado;
    DocumentoExpedienteDTO documentoVuelta = new DocumentoExpedienteDTO();

    try {
      bytesDocumento = generarDocumento(FileUtils.readFileToByteArray(plantilla.getFile()), mapaVariables);
      documentoVuelta.setFichero(bytesDocumento);
      if (StringUtils.isNotBlank(codOV)) {
        nombreDocumento = nombreInforme + codOV + ".odt";
        nombrePDF = nombreInforme + codOV + ".pdf";
      } else {
        nombreDocumento = nombreInforme + nExp + ".odt";
        nombrePDF = nombreInforme + nExp + ".pdf";
      }

      documentoGenerado = new File(ConstantesTramitacion.RUTA_FICHERO_GENERADO + nombreDocumento);

      FileUtils.writeByteArrayToFile(documentoGenerado, bytesDocumento);

      documentoGenerado = transformarOdtAPdf(documentoGenerado, nombrePDF);

    } catch (final IOException e) {
      throw new TramitacionException("No se ha encontrado la plantilla del informe.", e);
    }

    tareasService.incorporarDocumentoExpediente(documentoGenerado, nombrePDF, nombreTipoDocumento, user, documentoVuelta);

    return documentoVuelta;
  }

  @Override
  public DocumentoExpedienteDTO generarIncorporarInforme(final Map<String, Object> mapaVariables, final String nombreInformeSalida, final String rutaPlantilla,
      final String nombreTipoDocumento, final String numeroExpediente, final UsuarioWeb user) throws TramitacionException {
    DocumentoExpedienteDTO documentoVuelta = new DocumentoExpedienteDTO();
    try {
      final Resource plantilla = new ClassPathResource(rutaPlantilla);
      final byte[] bytesDocumento = generarDocumento(FileUtils.readFileToByteArray(plantilla.getFile()), mapaVariables);
      documentoVuelta.setFichero(bytesDocumento);

      final String nombreDocumento = nombreInformeSalida + numeroExpediente + ".odt";
      final String nombrePDF = nombreInformeSalida + numeroExpediente + ".pdf";
      File documentoGenerado = new File(ConstantesTramitacion.RUTA_FICHERO_GENERADO + nombreDocumento);
      FileUtils.writeByteArrayToFile(documentoGenerado, bytesDocumento);
      documentoGenerado = transformarOdtAPdf(documentoGenerado, nombrePDF);
      tareasService.incorporarDocumentoExpediente(documentoGenerado, nombrePDF, nombreTipoDocumento, user, documentoVuelta);
    } catch (final IOException e) {
      throw new TramitacionException("No se ha encontrado la plantilla del informe.", e);
    }

    return documentoVuelta;
  }

  public DocumentoExpedienteDTO incorporarDocumentoModificado(byte[] bytesDocumento, final String idDocumentoExpediente, final UsuarioWeb user)
      throws TramitacionException {
    try {
      DocumentoExpedienteDTO documentoVuelta = new DocumentoExpedienteDTO();
      TrDocumentoExpediente doc = trewaService.obtenerDocumentoExpediente(idDocumentoExpediente);

      String nombreInformeSalida = doc.getNOMBREFICHERO();
      String[] partes = nombreInformeSalida.split("\\.");
      String nombreTipoDocumento = doc.getTIPODOC().getNOMBRE();

      trewaService.eliminarDocumento(doc.getREFDOCEXP());

      final String nombreDocumento = partes[0] + ".odt";
      final String nombrePDF = partes[0] + ".pdf";
      File documentoGenerado = new File(ConstantesTramitacion.RUTA_FICHERO_GENERADO + nombreDocumento);
      FileUtils.writeByteArrayToFile(documentoGenerado, bytesDocumento);
      documentoGenerado = transformarOdtAPdf(documentoGenerado, nombrePDF);
      tareasService.incorporarDocumentoExpediente(documentoGenerado, nombrePDF, nombreTipoDocumento, user, documentoVuelta);
      return documentoVuelta;
    } catch (IOException e) {
      throw new TramitacionException("Error al convertir a PDF.", e);
    }
  }

  /**
   * Establece el valor de la propiedad tareasService
   *
   * @param tareasService
   *          el tareasService para establecer
   */
  public void setTareasService(final ITareasService tareasService) {
    this.tareasService = tareasService;
  }

  public void setTrewaService(ITrewaService trewaService) {
    this.trewaService = trewaService;
  }
}

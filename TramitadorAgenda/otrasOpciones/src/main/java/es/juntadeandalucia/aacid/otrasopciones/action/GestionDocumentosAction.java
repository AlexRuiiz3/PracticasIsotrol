package es.juntadeandalucia.aacid.otrasopciones.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import es.juntadeandalucia.aacid.comuntramitacion.dto.DocumentoExpedienteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.IGeneracionDocumentosService;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.DocumentoExpediente;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IDocumentoExpedienteService;
import trewa.bd.trapi.trapiui.tpo.TrDocumentoExpediente;

public class GestionDocumentosAction extends AbstractOtrasOpcionesAction {

  private transient IDocumentoExpedienteService documentoExpedienteService;

  private transient IGeneracionDocumentosService generacionDocumentosService;

  private transient ITrewaService trewaService;

  private InputStream ficheroDescarga;

  private String nombreFicheroDescarga;

  private String refDoc;

  private File docSubir;
  private String docSubirFileName;
  private String docSubirContentType;

  public String modificarDocumentoPDF() {

    refDoc = getRequest().getParameter("refdoc");

    return SUCCESS;
  }

  public String descargarDocumento() {

    try {
      DocumentoExpediente documentoExpedienteEnt = documentoExpedienteService.obtenerDocumentoExpediente(Long.valueOf(refDoc));
      if (documentoExpedienteEnt != null) {
        TrDocumentoExpediente docExp = trewaService.obtenerDocumentoExpediente(refDoc);
        nombreFicheroDescarga = docExp.getNOMBREFICHERO().replace(".pdf", ".odt");
        ficheroDescarga = new ByteArrayInputStream(documentoExpedienteEnt.getDoexBFichero());
      } else {
        return ERROR;
      }
    } catch (TramitacionException e) {
      log.error(getText("error.obtenerDocumento"), e);
      addActionError(getText("error.obtenerDocumento"));
    }

    return SUCCESS;
  }

  public String subirDocumento() {
    try {
      MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper) ServletActionContext.getRequest();
      Collection<Part> parts = multiWrapper.getParts();
      List<File> ficheros = new ArrayList<>();
      for (Part p : parts) {
        if (p.getName().equals("file")) {
          String[] nombreFicheroAux = p.getSubmittedFileName().split("\\.");
          String extension = nombreFicheroAux[nombreFicheroAux.length - 1];

          if (extension != null && extension.equals("odt")) {

            File fichero = Files.createTempFile("ficheroTemporal", ".odt").toFile();
            OutputStream outputStream = new FileOutputStream(fichero);

            IOUtils.copy(p.getInputStream(), outputStream);
            ficheros.add(fichero);
            continue;
          }
          return "error";
        }

      }
      if (!ficheros.isEmpty()) {
        byte[] doc = FileUtils.readFileToByteArray(ficheros.get(0));
        DocumentoExpedienteDTO docTrewa = generacionDocumentosService.incorporarDocumentoModificado(doc, refDoc, getUsuarioSesion());
        docTrewa.setFichero(doc);
        documentoExpedienteService.borrarDocumentoExpedienteByidDoc(Long.parseLong(refDoc));
        documentoExpedienteService.guardarDocumentoExpediente(docTrewa);
      }
    } catch (TramitacionException | IOException | ServletException e) {
      log.error(getText("error.subirDocumento"), e);
      addActionError(getText("error.subirDocumento"));
      return "error";
    }
    return SUCCESS;
  }

  public String eliminarDocumento() {
    refDoc = getRequest().getParameter("refdoc");

    documentoExpedienteService.borrarDocumentoExpedienteByidDoc(Long.parseLong(refDoc));

    return SUCCESS;
  }

  public void setDocumentoExpedienteService(IDocumentoExpedienteService documentoExpedienteService) {
    this.documentoExpedienteService = documentoExpedienteService;
  }

  public InputStream getFicheroDescarga() {
    return ficheroDescarga;
  }

  public void setFicheroDescarga(InputStream ficheroDescarga) {
    this.ficheroDescarga = ficheroDescarga;
  }

  public String getRefDoc() {
    return refDoc;
  }

  public void setRefDoc(String refDoc) {
    this.refDoc = refDoc;
  }

  public File getDocSubir() {
    return docSubir;
  }

  public void setDocSubir(File docSubir) {
    this.docSubir = docSubir;
  }

  public String getDocSubirFileName() {
    return docSubirFileName;
  }

  public void setDocSubirFileName(String docSubirFileName) {
    this.docSubirFileName = docSubirFileName;
  }

  public String getDocSubirContentType() {
    return docSubirContentType;
  }

  public void setDocSubirContentType(String docSubirContentType) {
    this.docSubirContentType = docSubirContentType;
  }

  public void setGeneracionDocumentosService(IGeneracionDocumentosService generacionDocumentosService) {
    this.generacionDocumentosService = generacionDocumentosService;
  }

  public void setTrewaService(ITrewaService trewaService) {
    this.trewaService = trewaService;
  }

  public String getNombreFicheroDescarga() {
    return nombreFicheroDescarga;
  }

  public void setNombreFicheroDescarga(String nombreFicheroDescarga) {
    this.nombreFicheroDescarga = nombreFicheroDescarga;
  }
}

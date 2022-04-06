package es.juntadeandalucia.aacid.comuntramitacion.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.DocumentoExpedienteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.IGeneracionDocumentosService;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITareasService;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.plataforma.comunes.excepciones.BusinessException;
import es.juntadeandalucia.plataforma.configuracionTramitacion.ConfiguracionTramitacionServiceImpl;
import es.juntadeandalucia.plataforma.service.documentacion.IDocumentacionService;
import es.juntadeandalucia.plataforma.service.documentacion.IDocumentoPlantilla;
import es.juntadeandalucia.plataforma.service.expediente.IExpediente;
import es.juntadeandalucia.plataforma.service.fase.IFaseActual;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;
import trewa.bd.sql.ClausulaWhere;
import trewa.bd.sql.OperadorWhere;
import trewa.bd.tpo.TpoPK;
import trewa.bd.trapi.trapiui.TrAPIUI;
import trewa.bd.trapi.trapiui.tpo.TrTareaExpediente;
import trewa.exception.TrException;

/**
 * TareasService class.
 *
 * @author isotrol.
 *
 */
public class TareasServiceImpl extends ConfiguracionTramitacionServiceImpl implements ITareasService {

  /** Log log, Atributo para los mensajes de log */
  protected final Log log = LogFactory.getLog(getClass());

  private IDocumentacionService documentacionService;
  private ITrewaService trewaService;
  private IGeneracionDocumentosService generacionDocumentosService;

  @Override
  public void finalizarTarea(final String nombreTarea, final TpoPK idExpediente, final UsuarioWeb usuario) throws TramitacionException {

    final ClausulaWhere cw3 = new ClausulaWhere();
    cw3.addExpresion(TrTareaExpediente.CAMPO_NOMBRETAREA, OperadorWhere.OP_IGUAL, nombreTarea);
    TrTareaExpediente[] tareas;
    try {
      final TrAPIUI apiUsuario = getApiUI();
      apiUsuario.establecerUsuarioSistema(usuario.getUsuario().getCodUsuario());
      tareas = apiUsuario.obtenerTareasExpediente(idExpediente, false, cw3, null);

      if (tareas != null && tareas.length > 0) {
        apiUsuario.finalizarTareaExpediente(tareas[0].getREFTAREAEXP(), tareas[0].getTIPO(), null);
      }
    } catch (final TrException e) {
      throw new TramitacionException("Se ha producido un error al finalizar la tarea", e);
    }
  }

  @Override
  public void incorporarDocumentoExpediente(final File documento, final String nombreDocumento, final String nombreTipoDocumento, final UsuarioWeb usuario,
      DocumentoExpedienteDTO documentoVuelta) throws TramitacionException {
    try {
      final TrAPIUI apiUsuario = this.getApiUI();
      documentoVuelta.setIdExpediente(Long.parseLong(usuario.getExpediente().getRefExpediente()));
      apiUsuario.establecerUsuarioSistema(usuario.getUsuario().getCodUsuario());
      documentoVuelta.setIdDocumento(trewaService.incorporarDocumentoAlExpediente(apiUsuario, usuario.getExpediente(),
          obtenerTipoDocumento(nombreTipoDocumento, usuario), documento, nombreDocumento, new Date()));
    } catch (TramitacionException | TrException e) {
      throw new TramitacionException("Se ha producido un error al incorporar el documento al expediente", e);
    }
  }

  @Override
  public DocumentoExpedienteDTO generarInformes(final Map<String, Object> datosVariables, final String rutaPlantilla, final String nombreInformeSalida,
      final String nombreTipoDocumento, final String nombreTarea, final UsuarioWeb user) throws TramitacionException {
    final Resource plantilla = new ClassPathResource(rutaPlantilla);
    byte[] bytesDocumento;
    String nombrePDF;
    String nombreDocumento;
    File documentoGenerado;
    DocumentoExpedienteDTO documentoVuelta = new DocumentoExpedienteDTO();
    try {
      bytesDocumento = generacionDocumentosService.generarDocumento(FileUtils.readFileToByteArray(plantilla.getFile()), datosVariables);
      documentoVuelta.setFichero(bytesDocumento);
      nombreDocumento = nombreInformeSalida + user.getExpediente().getRefExpediente() + ".odt";
      nombrePDF = nombreInformeSalida + user.getExpediente().getRefExpediente() + ".pdf";
      documentoGenerado = new File(ConstantesTramitacion.RUTA_FICHERO_GENERADO + nombreDocumento);
      FileUtils.writeByteArrayToFile(documentoGenerado, bytesDocumento);
      documentoGenerado = generacionDocumentosService.transformarOdtAPdf(documentoGenerado, nombrePDF);
    } catch (final IOException e) {
      throw new TramitacionException("Se ha producido un error en la generaci\u00F3n del informe.", e);
    }
    incorporarDocumentoExpediente(documentoGenerado, nombrePDF, nombreTipoDocumento, user, documentoVuelta);
    finalizarTarea(nombreTarea, new TpoPK(user.getExpediente().getRefExpediente()), user);

    return documentoVuelta;
  }

  /**
   * Obtener tipo documento.
   *
   * @param nombreTipoDocumento
   *          the fase
   * @param webuser
   *          the webuser
   * @return the string
   * @throws TramitacionException
   */
  private String obtenerTipoDocumento(final String nombreTipoDocumento, final UsuarioWeb webuser) throws TramitacionException {
    // Recuperamos la lista de tipos de documentos permitidos de la
    // fase actual del expediente
    final IExpediente expediente = webuser.getExpediente();
    final IFaseActual faseActual = webuser.getFaseActual();
    try {
      final List<IDocumentoPlantilla> listaDocumentosPermitidos = documentacionService.obtenerDocumentosPermitidos(faseActual, expediente, null);
      if (listaDocumentosPermitidos != null && !listaDocumentosPermitidos.isEmpty()) {

        // Solo nos interesa los documentos de tipo incorporar
        for (final IDocumentoPlantilla docPlantilla : listaDocumentosPermitidos) {
          if (docPlantilla.getTipoDocumento().getIncGen().equals("I")
              && StringUtils.equalsIgnoreCase(docPlantilla.getTipoDocumento().getNombre().trim(), nombreTipoDocumento)) {
            return docPlantilla.getTipoDocumento().getRefTipoDoc();
          }
        }
      }

    } catch (final BusinessException e) {
      throw new TramitacionException("Error al recuperar tipo documento para su incorporaci\u00F3n", e);
    }

    return null;
  }

  /**
   * Establece el valor de la propiedad documentacionService
   *
   * @param documentacionService
   *          el documentacionService para establecer
   */
  public void setDocumentacionService(final IDocumentacionService documentacionService) {
    this.documentacionService = documentacionService;
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

  /**
   * Establece el valor de la propiedad generacionDocumentosService
   *
   * @param generacionDocumentosService
   *          el generacionDocumentosService para establecer
   */
  public void setGeneracionDocumentosService(final IGeneracionDocumentosService generacionDocumentosService) {
    this.generacionDocumentosService = generacionDocumentosService;
  }

}

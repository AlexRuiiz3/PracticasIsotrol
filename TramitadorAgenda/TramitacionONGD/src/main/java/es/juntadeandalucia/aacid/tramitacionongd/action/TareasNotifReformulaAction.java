package es.juntadeandalucia.aacid.tramitacionongd.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import es.juntadeandalucia.aacid.comuntramitacion.action.BaseTareasAction;
import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.DocumentoExpedienteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.reformula.DatosReformulaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.reformula.ReformulaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.IGeneracionDocumentosService;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.functions.ReformulaDTOToDatosReformulaFunction;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IDocumentoExpedienteService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IFinalidadService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IReformulaService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudService;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;
import trewa.bd.trapi.trapiui.tpo.TrInteresadoExpediente;

public class TareasNotifReformulaAction extends BaseTareasAction {
  /**
   *
   */
  private static final long serialVersionUID = 8702335249543689041L;

  /** LOGGER TareasNotifReformulaAction.class */
  private final transient Logger log = Logger.getLogger(TareasNotifReformulaAction.class);

  private transient ISolicitudService solicitudService;

  private transient IReformulaService reformulaService;

  private transient IFinalidadService finalidadService;

  private transient ITrewaService trewaService;

  private transient IGeneracionDocumentosService generacionDocumentosService;

  private DatosReformulaDTO datosReformulaDTO;

  private SortedMap<String, String> tiposReformulaDTO;

  private transient IDocumentoExpedienteService documentoExpedienteService;

  public String inicioNotificacionReformulacion() {

    cargarDesplegables();

    try {
      // se obtiene el usuario de la sesion
      user = getUsuarioSesion();

      // se obtiene el id del expediente correspondiente a la convocatoria.
      final String idExpTrewa = user.getExpediente().getRefExpediente();

      // se obtiene la solicitud a partir del id del expediente.
      final SolicitudDTO solicitud = solicitudService.obtenerSolicitudByIdExp(idExpTrewa);

      // se obtiene la reformulación para dicha solicitud
      final Long idSolicitud = solicitud.getIdSolicitud();
      final Optional<ReformulaDTO> optional = reformulaService.findBySolicitudId(idSolicitud);

      datosReformulaDTO = new DatosReformulaDTO();
      datosReformulaDTO.setIdSolicitud(idSolicitud);

      if (optional.isPresent()) {
        datosReformulaDTO = new ReformulaDTOToDatosReformulaFunction().apply(optional.get());
      } else {
        datosReformulaDTO.setTipoSeleccionado("-1");
      }

      return SUCCESS;

    } catch (final TramitacionException e) {
      final String mensajeError = getText("error.cargar");
      addActionError(mensajeError);
      log.error(mensajeError, e);
      return ERROR;
    }

  }

  public String guardarReformula() {

    try {
      setActionErrors(reformulaService.validated(datosReformulaDTO));

      if (!hasActionErrors()) {
        final ReformulaDTO reformula = reformulaService.save(datosReformulaDTO);
        datosReformulaDTO = new ReformulaDTOToDatosReformulaFunction().apply(reformula);
        addActionMessage(getText("exito.guardar"));
        return SUCCESS;
      } else {
        getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return ERROR;
      }
    } catch (final TramitacionException e) {
      log.error(e.getMessage());
      addActionError(getText("error.guardar.tramitacion"));
      getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
      return ERROR;
    }
  }

  public String incorporarNotificacionReformula() {

    try {
      // se obtiene el usuario de la sesion
      user = getUsuarioSesion();

      // se obtiene el id del expediente correspondiente a la convocatoria.
      final String idExpTrewa = user.getExpediente().getRefExpediente();

      // se obtiene la solicitud a partir del id del expediente.
      final SolicitudDTO solicitud = solicitudService.obtenerSolicitudByIdExp(idExpTrewa);

      // se obtiene la reformulación para dicha solicitud
      final Long idSolicitud = solicitud.getIdSolicitud();
      final Optional<ReformulaDTO> optional = reformulaService.findBySolicitudId(idSolicitud);

      if (optional.isPresent()) {

        final ReformulaDTO reformulaDTO = optional.get();
        final TrExpediente expediente = trewaService.obtenerExpedienteTrewa(idExpTrewa);
        final TrInteresadoExpediente interesado = trewaService.obtenerInteresadoExpediente(idExpTrewa);

        final Map<String, Object> map = new HashMap<>();
        map.put("tipoSol", "proyecto");
        map.put("tipoInt", finalidadService.obtenerFinalidadByNumExpediente(Long.valueOf(idExpTrewa), false));
        map.put("numExp", expediente.getNUMEXP());
        map.put("titulo", expediente.getTITULOEXP().toUpperCase());
        map.put("ongd", interesado.getINTERESADO().getNOMBRE().toUpperCase());
        map.put("maximoAACID", reformulaDTO.getMaximo().toString());
        map.put("minimoTotal", reformulaDTO.getMinimo().toString());

        String solReformulado = "-";
        if ("0".equals(reformulaDTO.getTipo())) {
          solReformulado = "el presupuesto";
        } else if ("1".equals(reformulaDTO.getTipo())) {
          solReformulado = "el presupuesto y la matriz";
        }
        map.put("solReformulado", solReformulado);

        final String rutaPlantilla = ConstantesTramitacion.PLANTILLA_NOTIFICACION_REFORMULACION_ONGD;
        final String nombreTipoDocumento = ConstantesTramitacion.NOMBRE_DOC_IN_NOTIF_REFORMULACION_ONGD;
        final String nombreInformeSalida = ConstantesTramitacion.NOMBRE_INFORME_SALIDA_NOTIFICACION_REFORMULACION_ONGD;
        final String numExpediente = expediente.getNUMEXP();
        DocumentoExpedienteDTO doc = generacionDocumentosService.generarIncorporarInforme(map, nombreInformeSalida, rutaPlantilla, nombreTipoDocumento,
            numExpediente, user);

        documentoExpedienteService.guardarDocumentoExpediente(doc);

        addActionMessage(getText("exito.generar.incorporar.documento"));
        return SUCCESS;
      } else {
        addActionError(getText("error.generar.incorporar.documento.reformula"));
        getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return ERROR;
      }

    } catch (final TramitacionException e) {
      log.error(e.getMessage());
      addActionError(getText("error.generar.incorporar.documento.tramitacion"));
      getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
      return ERROR;
    }
  }

  private void cargarDesplegables() {
    cargarTipos();
  }

  private void cargarTipos() {
    tiposReformulaDTO = new TreeMap<>();
    tiposReformulaDTO.put("-1", "");
    tiposReformulaDTO.put("0", getText("reformula.tipo.opcion0"));
    tiposReformulaDTO.put("1", getText("reformula.tipo.opcion1"));
  }

  public DatosReformulaDTO getDatosReformulaDTO() {
    return datosReformulaDTO;
  }

  public void setDatosReformulaDTO(final DatosReformulaDTO datosReformulaDTO) {
    this.datosReformulaDTO = datosReformulaDTO;
  }

  public final SortedMap<String, String> getTiposReformulaDTO() {
    return tiposReformulaDTO;
  }

  public final void setTiposReformulaDTO(final SortedMap<String, String> tiposReformulaDTO) {
    this.tiposReformulaDTO = tiposReformulaDTO;
  }

  public void setSolicitudService(final ISolicitudService solicitudService) {
    this.solicitudService = solicitudService;
  }

  public void setReformulaService(final IReformulaService reformulaService) {
    this.reformulaService = reformulaService;
  }

  public void setFinalidadService(final IFinalidadService finalidadService) {
    this.finalidadService = finalidadService;
  }

  public void setTrewaService(final ITrewaService trewaService) {
    this.trewaService = trewaService;
  }

  public void setGeneracionDocumentosService(final IGeneracionDocumentosService generacionDocumentosService) {
    this.generacionDocumentosService = generacionDocumentosService;
  }

  public void setDocumentoExpedienteService(IDocumentoExpedienteService documentoExpedienteService) {
    this.documentoExpedienteService = documentoExpedienteService;
  }
}
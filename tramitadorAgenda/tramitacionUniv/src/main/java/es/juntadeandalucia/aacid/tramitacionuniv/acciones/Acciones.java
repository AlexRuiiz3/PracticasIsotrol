package es.juntadeandalucia.aacid.tramitacionuniv.acciones;

import static java.util.Calendar.YEAR;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesProcesamientosEspecificos;
import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.DatosExclusionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ExclusionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.vea.CampoVeaType;
import es.juntadeandalucia.aacid.comuntramitacion.dto.vea.DatosVea;
import es.juntadeandalucia.aacid.comuntramitacion.dto.vea.EntregaVeaType;
import es.juntadeandalucia.aacid.comuntramitacion.exception.AlegacionesAceptaReformulaException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.AlegacionesException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.ParseCamposVeaException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.RecepcionComunicacionException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.SubsanacionException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea.AaciAgrupaciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea.AaciTDocPoderAdminJa;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea.AaciTDocPoderOtrAdmin;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea.AaciTSolicitudsubongd;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IExclusionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.utils.ProcesamientoEspecificoUtils;
import es.juntadeandalucia.aacid.tramitacionuniv.constantes.ConstantesTramitacionUNIV;
import es.juntadeandalucia.aacid.tramitacionuniv.service.IAnexoIIIService;
import es.juntadeandalucia.aacid.tramitacionuniv.service.IAnexoIVService;
import es.juntadeandalucia.aacid.tramitacionuniv.service.IAnexoVIService;
import trewa.bd.tpo.TpoPK;
import trewa.bd.trapi.trapiui.TrAPIUI;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;
import trewa.bd.trapi.trapiui.tpo.TrFaseActualExpediente;
import trewa.exception.TrException;
import trewa.ext.TrAccesoUI;

public class Acciones extends TrAccesoUI {
  private static IExclusionService exclusionService;
  private static ISolicitudService solicitudService;
  private static ITrewaService trewaService;
  private static IAnexoIVService anexoIVService;
  private static IAnexoIIIService anexoIIIService;
  private static IAnexoVIService anexoVIService;

  private static final Log LOG = LogFactory.getLog(Acciones.class);

  static {
    final WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
    exclusionService = (IExclusionService) context.getBean("exclusionService");
    solicitudService = (ISolicitudService) context.getBean("solicitudService");
    trewaService = (ITrewaService) context.getBean("trewaService");
    anexoIVService = (IAnexoIVService) context.getBean("anexoIVService");
    anexoIIIService = (IAnexoIIIService) context.getBean("anexoIIIService");
    anexoVIService = (IAnexoVIService) context.getBean("anexoVIService");
  }

  public final Integer aniadirDesistimientoEntidad(final BigDecimal idExp) {
    final Integer resultado = 0;
    TrExpediente[] expedientes;
    final TrAPIUI apiUI = getApiUI();
    DatosExclusionDTO datosExclusion;
    try {
      expedientes = apiUI.obtenerExpedientes(new TpoPK(idExp), null, null);
      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(idExp.toString());

      TrExpediente expediente;
      final GregorianCalendar fechaCreacion = new GregorianCalendar();
      if (expedientes.length > 0) {
        expediente = expedientes[0];
        fechaCreacion.setTime(expediente.getFECHA_REGISTRO());
        final Long anio = (long) fechaCreacion.get(YEAR);
        final Map<String, Long> mapaExclusiones = ConstantesTramitacionUNIV.obtenerMapaExclusionesComunes();
        final List<ExclusionDTO> exclusiones = exclusionService.obtenerExclusionesDTOByOrdenTipo(ConstantesTramitacion.TIPO_CONV_UNIV,
            ConstantesTramitacion.EXCLUSION_TIPO_ADMISION, mapaExclusiones.get(ConstantesTramitacionUNIV.COMUNES), anio, null);

        final Optional<ExclusionDTO> exclusion = exclusiones.stream().filter(s -> ConstantesTramitacionUNIV.EXCLUSION_DESISTIMACION.equals(s.getDescripcion()))
            .findFirst();

        if (exclusion.isPresent()) {
          datosExclusion = new DatosExclusionDTO();
          datosExclusion.setIdSolicitud(solicitudDTO.getIdSolicitud());
          datosExclusion.setTipoExclusion(ConstantesTramitacion.EXCLUSION_TIPO_ADMISION);

          final List<String> idsExclusiones = new ArrayList<>();
          idsExclusiones.add(exclusion.get().getIdExclusion().toString());
          datosExclusion.setExclusionesComunesSeleccionadas(idsExclusiones);
          exclusionService.guardarExclusionesSolicitud(datosExclusion);
        }
      }
    } catch (TrException | TramitacionException e) {
      LOG.error("Se ha producido un error al asociar la exclusión de desistimiento al expediente con id " + idExp);
    }
    return resultado;
  }

  public final Integer recepcionSubsanacionDesdeVea(final BigDecimal idExp) {
    Integer resultado = 0;
    final TrExpediente expedienteTrewa = trewaService.obtenerExpedienteTrewa(idExp.toString());
    String otrosDatos = expedienteTrewa.getOTROSDATOS();
    final TrAPIUI apiUI = getApiUI();
    final StringBuilder parsed = new StringBuilder();

    try {
      // Comprobamos que el expediente se encuentre en la fase correcta
      final List<TrFaseActualExpediente> fasesActual = obtenerFaseActualExpediente(expedienteTrewa.getREFEXP(), apiUI);
      final TrFaseActualExpediente faseActual = fasesActual.stream()
          .filter(f -> ConstantesTramitacionUNIV.FASE_COMPROBACION_SUBSANACION.equals(f.getFASE().getDESCRIPCION())
              || ConstantesTramitacionUNIV.FASE_COMPROBACION_SUBSANACION_VUELTA.equals(f.getFASE().getDESCRIPCION()))
          .findAny().orElse(null);
      if (faseActual != null) {
        otrosDatos = apiUI.obtenerOtrosDatos(new TpoPK(idExp), "E");

        final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(expedienteTrewa.getREFEXP().toString());
        final DatosVea datosVea = ProcesamientoEspecificoUtils.xmlTojaxbObject(otrosDatos);

        if (datosVea != null && CollectionUtils.isNotEmpty(datosVea.getEntrega())) {
          final List<EntregaVeaType> listaEntregas = datosVea.getEntrega();
          // invertimos la lista de nodos de entregas
          Collections.reverse(listaEntregas);
          // Recuperamos la primera entrega, que gracias al reverse será la última subsanación
          final EntregaVeaType ultimaEntregaSubsanacion = listaEntregas.stream()
              .filter(s -> ConstantesTramitacionUNIV.TAREA_WEB_SUBSANACION.equals(s.getIdTareaTrewa())).findFirst().orElse(null);

          // Una vez tenemos la última entrega de subsanación comenzamos con el procesamientod e los campos.
          procesamientoCamposFormularioVeA(idExp, solicitudDTO, datosVea, ultimaEntregaSubsanacion);
          resultado = 1;
        }
      } else {
        LOG.error("El expediente se encuentra en la fase " + fasesActual + ". El expediente debe estar en la fase "
            + ConstantesTramitacionUNIV.FASE_COMPROBACION_SUBSANACION);
      }
    } catch (XMLStreamException | JAXBException | ParseException e) {
      LOG.error("Se ha producido un error al leer el xml de subsanación para el expediente con id " + idExp.toString(), e);
    } catch (final TramitacionException e) {
      LOG.error("Se ha producido un error al recuperar la solicitud mientras procesabamos la subsanación para el expediente con id " + idExp.toString(), e);
    } catch (final ClassNotFoundException e) {
      LOG.error("Se ha producido un error al intentar obtener la clase de la solicitud");
    } catch (final TrException | IllegalAccessException | NoSuchFieldException e) {
      LOG.error(e.getMessage(), e);
    } catch (final SubsanacionException e) {
      LOG.error("Se ha producido un error al setear el campo " + parsed, e);
    }

    return resultado;
  }

  /**
   * Método que obtiene el último FORMULARIO DE ALEGACIONES/ACEPTACIÓN/REFORMULACIÓN Y PRESENTACIÓN DE DOCUMENTOS y lo guarda en el sistema
   *
   * @param idExp
   *          Identificador del expediente.
   * @return Indica si se ha procesado correctamente o no.
   */
  public final int recepcionAlegacionesAceptaReformulaDesdeVea(final BigDecimal idExp) {

    Integer resultado = 0;

    // Obtener el expediente
    final TrExpediente expedienteTrewa = trewaService.obtenerExpedienteTrewa(idExp.toString());

    final TrAPIUI apiUI = getApiUI();

    try {

      // Obtener el XML
      final String otrosDatos = apiUI.obtenerOtrosDatos(new TpoPK(idExp), "E");

      // Obtener la solicitud
      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(expedienteTrewa.getREFEXP().toString());

      // Transformar los datos
      final DatosVea datosVea = ProcesamientoEspecificoUtils.xmlTojaxbObject(otrosDatos);

      if (datosVea != null && CollectionUtils.isNotEmpty(datosVea.getEntrega())) {

        // Listado de entregas
        final List<EntregaVeaType> entregas = datosVea.getEntrega();

        // invertimos la lista de nodos de entregas
        Collections.reverse(entregas);

        // Recuperamos la primera entrega, que gracias al reverse será el último FORMULARIO DE ALEGACIONES/ACEPTACIÓN/REFORMULACIÓN Y
        // PRESENTACIÓN DE DOCUMENTOS
        final EntregaVeaType ultimoAnexoIV = entregas.stream() // s
            .filter(s -> ConstantesTramitacionUNIV.ID_TAREA_TREWA_RECEP_ALEG_ACEPT_REFORM.equals(s.getIdTareaTrewa())) //
            .findFirst() //
            .orElse(null);

        // Una vez tenemos la última entrega del FORMULARIO DE ALEGACIONES/ACEPTACIÓN/REFORMULACIÓN Y PRESENTACIÓN DE DOCUMENTOS comenzamos
        // con el procesamiento de los campos.
        anexoIVService.procesamientoEspecifico(idExp, solicitudDTO.getIdSolicitud(), ultimoAnexoIV);

        resultado = 1;
      }
    } catch (XMLStreamException | JAXBException | ParseException e) {
      LOG.error(
          "Se ha producido un error al leer el xml de recepción del FORMULARIO DE ALEGACIONES/ACEPTACIÓN/REFORMULACIÓN Y PRESENTACIÓN DE DOCUMENTOS para el expediente con id "
              + idExp.toString(),
          e);
    } catch (final TramitacionException e) {
      LOG.error(
          "Se ha producido un error al recuperar la solicitud mientras procesabamos la recepción del FORMULARIO DE ALEGACIONES/ACEPTACIÓN/REFORMULACIÓN Y PRESENTACIÓN DE DOCUMENTOS para el expediente con id "
              + idExp.toString(),
          e);
    } catch (final ClassNotFoundException e) {
      LOG.error("Se ha producido un error al intentar obtener la clase del Anexo IV");
    } catch (final TrException | IllegalAccessException | NoSuchFieldException e) {
      LOG.error(e.getMessage(), e);
    } catch (final AlegacionesAceptaReformulaException e) {
      LOG.error("Se ha producido un error al guardar los campos", e);
    } catch (final ParseCamposVeaException e) {
      LOG.error("Se ha producido un error al setear algún campo de VeA", e);
    }

    return resultado;
  }

  /**
   * Método que obtiene el último FORMULARIO DE ALEGACIÓN A LA SOLICITUD DE SUBVENCIÓN y lo guarda en el sistema
   *
   * @param idExp
   *          Identificador del expediente.
   * @return Indica si se ha procesado correctamente o no.
   * @throws AlegacionesException
   */
  public final int recepcionAlegacionTrasExclusionDesdeVea(final BigDecimal idExp) throws AlegacionesException {

    Integer resultado = 0;

    // Obtener el expediente
    final TrExpediente expedienteTrewa = trewaService.obtenerExpedienteTrewa(idExp.toString());

    final TrAPIUI apiUI = getApiUI();

    try {

      // Obtener el XML
      final String otrosDatos = apiUI.obtenerOtrosDatos(new TpoPK(idExp), "E");

      // Obtener la solicitud
      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(expedienteTrewa.getREFEXP().toString());

      // Transformar los datos
      final DatosVea datosVea = ProcesamientoEspecificoUtils.xmlTojaxbObject(otrosDatos);

      if (datosVea != null && CollectionUtils.isNotEmpty(datosVea.getEntrega())) {

        // Listado de entregas
        final List<EntregaVeaType> entregas = datosVea.getEntrega();

        // invertimos la lista de nodos de entregas
        Collections.reverse(entregas);

        // Recuperamos la primera entrega, que gracias al reverse será el último FORMULARIO DE ALEGACIÓN A LA SOLICITUD DE SUBVENCIÓN
        final EntregaVeaType ultimoAnexoIII = entregas.stream() //
            .filter(s -> ConstantesTramitacionUNIV.ID_TAREA_TREWA_ALEG_TRAS_EXCL.equals(s.getIdTareaTrewa())) //
            .findFirst() //
            .orElse(null);

        // Una vez tenemos la última entrega del FORMULARIO DE ALEGACIÓN A LA SOLICITUD DE SUBVENCIÓN comenzamos
        // con el procesamiento de los campos.
        anexoIIIService.procesamientoEspecifico(idExp, solicitudDTO.getIdSolicitud(), ultimoAnexoIII);

        resultado = 1;
      }
    } catch (XMLStreamException | JAXBException | ParseException e) {
      LOG.error("Se ha producido un error al leer el xml de recepción del  FORMULARIO DE ALEGACIÓN A LA SOLICITUD DE SUBVENCIÓN para el expediente con id "
          + idExp.toString(), e);
    } catch (final TramitacionException e) {
      LOG.error(
          "Se ha producido un error al recuperar la solicitud mientras procesabamos la recepción del FORMULARIO DE ALEGACIÓN A LA SOLICITUD DE SUBVENCIÓN para el expediente con id "
              + idExp.toString(),
          e);
    } catch (final ClassNotFoundException e) {
      LOG.error("Se ha producido un error al intentar obtener la clase del Anexo IV");
    } catch (final TrException | IllegalAccessException | NoSuchFieldException e) {
      LOG.error(e.getMessage(), e);
    } catch (final AlegacionesException e) {
      LOG.error("Se ha producido un error al guardar los campos", e);
    } catch (final ParseCamposVeaException e) {
      LOG.error("Se ha producido un error al setear algún campo de VeA", e);
    }

    return resultado;
  }

  /**
   * Metodo que obtiene el ultimo FORMULARIO DE COMUNICACIÓN DE INICIO y lo guarda en el sistema
   *
   * @param idExp
   *          Identificador del expediente.
   * @return Indica si se ha procesado correctamente o no.
   * @throws RecepcionComunicacionException
   * @throws AlegacionesAceptaReformulaException
   */
  public final int recepcionComunicacionInicioDesdeVeaUniv(final BigDecimal idExp) throws RecepcionComunicacionException {

    Integer resultado = 0;

    // Obtener el expediente
    final TrExpediente expedienteTrewa = trewaService.obtenerExpedienteTrewa(idExp.toString());

    final TrAPIUI apiUI = getApiUI();

    try {

      // Obtener el XML
      final String otrosDatos = apiUI.obtenerOtrosDatos(new TpoPK(idExp), "E");

      // Obtener la solicitud
      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(expedienteTrewa.getREFEXP().toString());

      // Transformar los datos
      final DatosVea datosVea = ProcesamientoEspecificoUtils.xmlTojaxbObject(otrosDatos);

      if (datosVea != null && CollectionUtils.isNotEmpty(datosVea.getEntrega())) {

        // Listado de entregas
        final List<EntregaVeaType> entregas = datosVea.getEntrega();

        // invertimos la lista de nodos de entregas
        Collections.reverse(entregas);

        // Recuperamos la primera entrega, que gracias al reverse será el último ANEXO VI: FORMULARIO DE COMUNICACIÓN DE INICIO
        final EntregaVeaType ultimoAnexoVI = entregas.stream()
            .filter(s -> ConstantesTramitacionUNIV.ID_TAREA_TREWA_RECEPCION_COMUNICACION_INICIO.equals(s.getIdTareaTrewa())).findFirst().orElse(null);

        // Una vez tenemos la última entrega del ANEXO VI: FORMULARIO DE COMUNICACIÓN DE INICIO comenzamos con el procesamiento de los
        // campos.
        anexoVIService.procesamientoEspecifico(idExp, solicitudDTO.getIdSolicitud(), ultimoAnexoVI);

        resultado = 1;
      }
    } catch (XMLStreamException | JAXBException | ParseException e) {
      LOG.error("Se ha producido un error al leer el xml de recepción del FORMULARIO DE COMUNICACIÓN DE INICIO para el expediente con id " + idExp.toString(),
          e);
    } catch (final TramitacionException e) {
      LOG.error(
          "Se ha producido un error al recuperar la solicitud mientras procesabamos la recepción del FORMULARIO DE COMUNICACIÓN DE INICIO para el expediente con id "
              + idExp.toString(),
          e);
    } catch (final ClassNotFoundException e) {
      LOG.error("Se ha producido un error al intentar obtener la clase del Anexo VII");
    } catch (final TrException | IllegalAccessException | NoSuchFieldException e) {
      LOG.error(e.getMessage(), e);
    } catch (final RecepcionComunicacionException e) {
      LOG.error("Se ha producido un error al guardar los campos", e);
    } catch (final ParseCamposVeaException e) {
      LOG.error("Se ha producido un error al setear algún campo de VeA", e);
    }

    return resultado;
  }

  /**
   * @param idExp
   * @param solicitudDTO
   * @param datosVea
   * @param ultimaEntregaSubsanacion
   * @throws ClassNotFoundException
   * @throws ParseException
   * @throws NoSuchFieldException
   * @throws IllegalAccessException
   * @throws SubsanacionException
   */
  private AaciTSolicitudsubongd procesamientoCamposFormularioVeA(final BigDecimal idExp, final SolicitudDTO solicitudDTO, final DatosVea datosVea,
      final EntregaVeaType ultimaEntregaSubsanacion)
      throws ClassNotFoundException, ParseException, NoSuchFieldException, IllegalAccessException, SubsanacionException {
    AaciTSolicitudsubongd solicitudSubsanacion = null;
    if (ultimaEntregaSubsanacion != null) {
      solicitudSubsanacion = new AaciTSolicitudsubongd();
      final Class<?> clazz = Class.forName(ConstantesTramitacion.SOLICITUD_SUBVENCION);
      final List<CampoVeaType> listaCamposForm = compValSeanCorrectos(datosVea.getEntrega().get(0).getFormularios().getFormulario().get(0).getCampo());
      final List<CampoVeaType> camposPaises = new ArrayList<>();
      final List<CampoVeaType> camposVeaRepetibles = procesarCamposVea(listaCamposForm, solicitudSubsanacion, camposPaises);
      /**
       * Borra del Array camposVea todos aquellos campos que sean repetibles.
       */
      listaCamposForm.removeAll(camposVeaRepetibles);

      setearCamposEntrega(idExp, solicitudSubsanacion, clazz, listaCamposForm, camposVeaRepetibles);
      solicitudSubsanacion.setNumExpediente(idExp.longValue());
      solicitudSubsanacion.setId(solicitudDTO.getIdSolicitud());

      solicitudService.guardarSolicitudSubsanacion(solicitudSubsanacion, camposPaises, ConstantesTramitacion.TIPO_CONV_UNIV);
    } else {
      LOG.error("No se han encontrado formulario de subsanación para el expediente con id " + idExp);
    }
    return solicitudSubsanacion;
  }

  /**
   * @param idExp
   * @param solicitudSubsanacion
   * @param clazz
   * @param listaCamposForm
   * @param camposVeaRepetibles
   * @return
   * @throws NoSuchFieldException
   * @throws IllegalAccessException
   * @throws SubsanacionException
   */
  private StringBuilder setearCamposEntrega(final BigDecimal idExp, final AaciTSolicitudsubongd solicitudSubsanacion, final Class<?> clazz,
      final List<CampoVeaType> listaCamposForm, final List<CampoVeaType> camposVeaRepetibles)
      throws IllegalAccessException, SubsanacionException, NoSuchFieldException {
    StringBuilder parsed = new StringBuilder();
    for (final CampoVeaType campoVea : listaCamposForm) {
      if (!camposVeaRepetibles.contains(campoVea)) {
        final String[] campoDividido = campoVea.getNombre().toLowerCase().split("_");
        parsed = new StringBuilder();
        obtenemosNombreCampo(parsed, campoDividido);
        Field field;
        try {
          field = clazz.getDeclaredField(parsed.toString());
        } catch (NoSuchFieldException | SecurityException e) {
          throw new NoSuchFieldException("El campo " + parsed.toString() + " no se encuentra en la entidad AaciTSolicitudsubongd");
        }

        if (field.getName() == null) {
          LOG.info("Error al acceder a un campo de la solicitud.");
          throw new IllegalAccessException("Error al acceder al campo: el campo es nulo");
        } else if (field.getName().equals(ConstantesProcesamientosEspecificos.NUM_EXPEDIENTE)) {
          field.setAccessible(true);
          field.set(solicitudSubsanacion, idExp.longValue());
        } else {
          field.setAccessible(true);
          final String valorCampoVea = campoVea.getValor();
          ProcesamientoEspecificoUtils.setearCamposPorTipo(field, valorCampoVea, solicitudSubsanacion);
        }
      }
    }
    return parsed;
  }

  /**
   * @param parsed
   * @param campoDividido
   */
  private void obtenemosNombreCampo(final StringBuilder parsed, final String[] campoDividido) {
    for (final String parte : campoDividido) {
      if (parsed.toString().isEmpty()) {
        parsed.append(parte);
      } else {
        parsed.append(parte.substring(0, 1).toUpperCase(Locale.ROOT));
        parsed.append(parte.substring(1));
      }
    }
  }

  /**
   * Obtiene la fase actual del expediente
   *
   * @param tpoPK
   * @param apiUI
   * @return
   * @throws Exception
   */
  private List<TrFaseActualExpediente> obtenerFaseActualExpediente(final TpoPK tpoPK, final TrAPIUI apiUI) throws TrException {
    TrFaseActualExpediente[] fases = null;
    try {
      fases = apiUI.obtenerFaseActualExpediente(tpoPK, null, null);
    } catch (final TrException e) {
      LOG.error(e);
      throw new TrException(ConstantesTramitacion.ERROR_OBTENER_EXP_TREWA, e);
    }
    return Arrays.asList(fases);
  }

  private List<CampoVeaType> compValSeanCorrectos(final List<CampoVeaType> listaCamposForm) {
    for (final CampoVeaType campoVea : listaCamposForm) {
      if (campoVea.getValor().equals("-") || campoVea.getValor().equals("null") || campoVea.getValor().equals("") || campoVea.getValor().equals(" ")) {
        campoVea.setValor(null);
      }
    }
    return listaCamposForm;
  }

  private List<CampoVeaType> procesarCamposVea(final List<CampoVeaType> camposVea, final AaciTSolicitudsubongd solicitud,
      final List<CampoVeaType> paisesSolicitud) throws ParseException {
    final List<CampoVeaType> camposVeaRepetibles = new ArrayList<>();

    final List<CampoVeaType> agrupaciones = new ArrayList<>();
    final List<CampoVeaType> autorizaAdminJA = new ArrayList<>();
    final List<CampoVeaType> autorizaOtraAdmin = new ArrayList<>();

    for (final CampoVeaType campoVea : camposVea) {
      if (campoVea.getNombre().contains("REPET_AGRUPACIONES_")) {
        agrupaciones.add(campoVea);
      } else if (campoVea.getNombre().contains(ConstantesProcesamientosEspecificos.PREFIJO_OTRA_ADMIN)) {
        autorizaOtraAdmin.add(campoVea);
      } else if (campoVea.getNombre().contains(ConstantesProcesamientosEspecificos.PREFIJO_ADMIN_JA)) {
        autorizaAdminJA.add(campoVea);
      } else if (campoVea.getNombre().contains("REP_DATOS_")) {
        paisesSolicitud.add(campoVea);
      }
    }

    if (!paisesSolicitud.isEmpty()) {
      camposVeaRepetibles.addAll(paisesSolicitud);
    }

    if (!agrupaciones.isEmpty()) {
      camposVeaRepetibles.addAll(agrupaciones);
      agregarAgrupaciones(agrupaciones, solicitud);
    }

    if (!autorizaAdminJA.isEmpty()) {
      camposVeaRepetibles.addAll(autorizaAdminJA);
      agregarDocsJA(autorizaAdminJA, solicitud);
    }

    if (!autorizaOtraAdmin.isEmpty()) {
      camposVeaRepetibles.addAll(autorizaOtraAdmin);
      agregarDocsOtraAdmin(autorizaOtraAdmin, solicitud);
    }

    return camposVeaRepetibles;
  }

  private void agregarAgrupaciones(final List<CampoVeaType> agrupaciones, final AaciTSolicitudsubongd solicitud) {
    final List<AaciAgrupaciones> listaAgrupaciones = new ArrayList<>();
    final List<CampoVeaType> agrupacionesLeidas = new ArrayList<>();
    int ultAgrupacion = 0;
    boolean existeValor;
    // Halla el último elemento Agrupacion
    ultAgrupacion = obtenemosPosicionUltimaAgrupacion(agrupaciones, ultAgrupacion);

    // Instancia agrupaciones
    for (int i = ultAgrupacion; i >= 0; i--) {
      final AaciAgrupaciones agrupacion1 = new AaciAgrupaciones();
      existeValor = false;
      existeValor = recorremosAgrupaciones(agrupaciones, agrupacionesLeidas, existeValor, i, agrupacion1);
      if (existeValor) {
        agrupacion1.setAaciTSolicitudsubongdByIdSolicitud(solicitud);
        listaAgrupaciones.add(agrupacion1);
      }
    }
    if (!listaAgrupaciones.isEmpty()) {
      solicitud.setAaciAgrupacionesByIdSolicitud(listaAgrupaciones);
    }
  }

  /**
   * @param agrupaciones
   * @param agrupacionesLeidas
   * @param existeValor
   * @param i
   * @param agrupacion1
   * @return
   */
  private boolean recorremosAgrupaciones(final List<CampoVeaType> agrupaciones, final List<CampoVeaType> agrupacionesLeidas, boolean existeValor, final int i,
      final AaciAgrupaciones agrupacion1) {
    for (final CampoVeaType agrupacion : agrupaciones) {
      if (!agrupacionesLeidas.contains(agrupacion) && agrupacion.getNombre().contains("_" + i + "_")) {
        // Nif de la solicitud
        existeValor = obtenemosDatosRepetibleAgrupacion(agrupacionesLeidas, existeValor, agrupacion1, agrupacion);
      }
    }
    return existeValor;
  }

  /**
   * @param agrupaciones
   * @param ultAgrupacion
   * @return
   */
  private int obtenemosPosicionUltimaAgrupacion(final List<CampoVeaType> agrupaciones, int ultAgrupacion) {
    for (final CampoVeaType campoVea : agrupaciones) {
      if (Integer.parseInt(campoVea.getNombre().substring(19, 20)) > ultAgrupacion) {
        ultAgrupacion = Integer.parseInt(campoVea.getNombre().substring(19, 20));
      }
    }
    return ultAgrupacion;
  }

  /**
   * @param agrupacionesLeidas
   * @param existeValor
   * @param agrupacion1
   * @param agrupacion
   * @return
   */
  private boolean obtenemosDatosRepetibleAgrupacion(final List<CampoVeaType> agrupacionesLeidas, boolean existeValor, final AaciAgrupaciones agrupacion1,
      final CampoVeaType agrupacion) {
    if (agrupacion.getNombre().contains("REPET_AGRUPACIONES_") && agrupacion.getNombre().contains("_AGRUP_IDENTIFICADOR")) {
      if (agrupacion.getValor() != null && !agrupacion.getValor().equals("")) {
        agrupacion1.setAgruLiNif(agrupacion.getValor());
        agrupacionesLeidas.add(agrupacion);
        existeValor = true;
      }
    } else if (agrupacion.getNombre().contains("REPET_AGRUPACIONES_") && agrupacion.getNombre().contains("_AGRUP_RACDA")) {
      if (agrupacion.getValor() != null && !agrupacion.getValor().equals("")) {
        agrupacion1.setAgruCoInscripcion(agrupacion.getValor());
        agrupacionesLeidas.add(agrupacion);
        existeValor = true;
      }
    } else if (agrupacion.getNombre().contains("REPET_AGRUPACIONES_") && agrupacion.getNombre().contains("_AGRUP_NOMBRE")) {
      if (agrupacion.getValor() != null && !agrupacion.getValor().equals("")) {
        agrupacion1.setAgruLiDenominacion(agrupacion.getValor());
        agrupacionesLeidas.add(agrupacion);
        existeValor = true;
      }
    } else if ((agrupacion.getNombre().contains("REPET_AGRUPACIONES_") && agrupacion.getNombre().contains("_AGRUP_SIGLAS"))
        && (agrupacion.getValor() != null && !agrupacion.getValor().equals(""))) {
      agrupacion1.setAgruLiSiglas(agrupacion.getValor());
      agrupacionesLeidas.add(agrupacion);
      existeValor = true;
    }
    return existeValor;
  }

  private void agregarDocsJA(final List<CampoVeaType> autorizaAdminJA, final AaciTSolicitudsubongd solicitud) throws ParseException {
    final List<CampoVeaType> docsJALeidos = new ArrayList<>();
    final List<AaciTDocPoderAdminJa> listaDocsJA = new ArrayList<>();
    int ultAdminJA = 0;
    boolean existeValor;

    // Halla el último elemento DocumentoAdminJA
    for (final CampoVeaType campoVea : autorizaAdminJA) {
      if (Integer.parseInt(campoVea.getNombre().substring(15, 16)) > ultAdminJA) {
        ultAdminJA = Integer.parseInt(campoVea.getNombre().substring(15, 16));
      }
    }

    // Instancia DocumentoAdminJA
    for (int i = ultAdminJA; i >= 0; i--) {
      final AaciTDocPoderAdminJa docAdminJA1 = new AaciTDocPoderAdminJa();
      existeValor = false;
      for (final CampoVeaType docAdminJA : autorizaAdminJA) {
        if (!docsJALeidos.contains(docAdminJA) && docAdminJA.getNombre().contains("_" + i + "_")) {
          existeValor = tratamosDocAdmJA(docsJALeidos, existeValor, docAdminJA1, docAdminJA);
        }
      }
      if (existeValor) {
        docAdminJA1.setAaciTSolicitudsubongdByIdSolicitud(solicitud);
        listaDocsJA.add(docAdminJA1);
      }
    }
    if (!listaDocsJA.isEmpty()) {
      solicitud.setAaciTDocPoderAdminJasByIdSolicitud(listaDocsJA);
    }
  }

  /**
   * @param docsJALeidos
   * @param existeValor
   * @param docAdminJA1
   * @param docAdminJA
   * @return
   * @throws ParseException
   */
  private boolean tratamosDocAdmJA(final List<CampoVeaType> docsJALeidos, boolean existeValor, final AaciTDocPoderAdminJa docAdminJA1,
      final CampoVeaType docAdminJA) throws ParseException {
    if (docAdminJA.getNombre().contains(ConstantesProcesamientosEspecificos.PREFIJO_ADMIN_JA) && docAdminJA.getNombre().contains("_ADM_DOCUMENTO")) {
      if (docAdminJA.getValor() != null && !docAdminJA.getValor().equals("")) {
        docAdminJA1.setTxNomDocumento(docAdminJA.getValor());
        docsJALeidos.add(docAdminJA);
        existeValor = true;
      }
    } else if (docAdminJA.getNombre().contains(ConstantesProcesamientosEspecificos.PREFIJO_ADMIN_JA) && docAdminJA.getNombre().contains("_ADM_CONSEJERIA")) {
      if (docAdminJA.getValor() != null && !docAdminJA.getValor().equals("")) {
        docAdminJA1.setTxConsejeriaYOrgano(docAdminJA.getValor());
        docsJALeidos.add(docAdminJA);
        existeValor = true;
      }
    } else if (docAdminJA.getNombre().contains(ConstantesProcesamientosEspecificos.PREFIJO_ADMIN_JA) && docAdminJA.getNombre().contains("_ADM_PROCEDIMIENTO")) {
      if (docAdminJA.getValor() != null && !docAdminJA.getValor().equals("")) {
        docAdminJA1.setTxProcedEmitio(docAdminJA.getValor());
        docsJALeidos.add(docAdminJA);
        existeValor = true;
      }
    } else if ((docAdminJA.getNombre().contains(ConstantesProcesamientosEspecificos.PREFIJO_ADMIN_JA) && docAdminJA.getNombre().contains("_ADM_FECHA"))
        && (docAdminJA.getValor() != null && !docAdminJA.getValor().equals(""))) {
      docAdminJA1.setFhFechaPresent(new SimpleDateFormat(ConstantesTramitacion.FORMATO_FECHA_UE).parse(docAdminJA.getValor()));
      docsJALeidos.add(docAdminJA);
      existeValor = true;
    }
    return existeValor;
  }

  private void agregarDocsOtraAdmin(final List<CampoVeaType> autorizaOtraAdmin, final AaciTSolicitudsubongd solicitud) throws ParseException {
    final List<CampoVeaType> docsOtraAdminLeidos = new ArrayList<>();
    final List<AaciTDocPoderOtrAdmin> listaDocsOtraAdmin = new ArrayList<>();
    boolean existeValor;
    int ultOtraAdmin = 0;
    // Halla el último elemento DocumentoOtraAdmin
    ultOtraAdmin = obtenemosUltimoDocumentoOtraAdmin(autorizaOtraAdmin, ultOtraAdmin);
    for (int i = ultOtraAdmin; i >= 0; i--) {
      final AaciTDocPoderOtrAdmin docOtraAdmin1 = new AaciTDocPoderOtrAdmin();
      existeValor = false;
      for (final CampoVeaType docOtraAdmin : autorizaOtraAdmin) {
        if (!docsOtraAdminLeidos.contains(docOtraAdmin) && docOtraAdmin.getNombre().contains("_" + i + "_")) {
          existeValor = tratamosDocOtraAdmin(docsOtraAdminLeidos, existeValor, docOtraAdmin1, docOtraAdmin);
        }
      }
      if (existeValor) {
        docOtraAdmin1.setAaciTSolicitudsubongdByIdSolicitud(solicitud);
        listaDocsOtraAdmin.add(docOtraAdmin1);
      }
    }
    if (!listaDocsOtraAdmin.isEmpty()) {
      solicitud.setAaciTDocPoderOtrAdminsByIdSolicitud(listaDocsOtraAdmin);
    }
  }

  /**
   * @param docsOtraAdminLeidos
   * @param existeValor
   * @param docOtraAdmin1
   * @param docOtraAdmin
   * @return
   * @throws ParseException
   */
  private boolean tratamosDocOtraAdmin(final List<CampoVeaType> docsOtraAdminLeidos, boolean existeValor, final AaciTDocPoderOtrAdmin docOtraAdmin1,
      final CampoVeaType docOtraAdmin) throws ParseException {
    if (docOtraAdmin.getNombre().contains(ConstantesProcesamientosEspecificos.PREFIJO_OTRA_ADMIN) && docOtraAdmin.getNombre().contains("_OTRAS_DOCUMENTO")) {
      if (docOtraAdmin.getValor() != null && !docOtraAdmin.getValor().equals("")) {
        docOtraAdmin1.setTxNomDocumento(docOtraAdmin.getValor());
        docsOtraAdminLeidos.add(docOtraAdmin);
        existeValor = true;
      }
    } else if (docOtraAdmin.getNombre().contains(ConstantesProcesamientosEspecificos.PREFIJO_OTRA_ADMIN)
        && docOtraAdmin.getNombre().contains("_OTRAS_ADMINISTRACION")) {
      if (docOtraAdmin.getValor() != null && !docOtraAdmin.getValor().equals("")) {
        docOtraAdmin1.setTxConsejeriaYOrgano(docOtraAdmin.getValor());
        docsOtraAdminLeidos.add(docOtraAdmin);
        existeValor = true;
      }
    } else if (docOtraAdmin.getNombre().contains(ConstantesProcesamientosEspecificos.PREFIJO_OTRA_ADMIN)
        && docOtraAdmin.getNombre().contains("_OTRAS_PROCEDIMIENTO")) {
      if (docOtraAdmin.getValor() != null && !docOtraAdmin.getValor().equals("")) {
        docOtraAdmin1.setTxProcedEmitio(docOtraAdmin.getValor());
        docsOtraAdminLeidos.add(docOtraAdmin);
        existeValor = true;
      }
    } else if ((docOtraAdmin.getNombre().contains(ConstantesProcesamientosEspecificos.PREFIJO_OTRA_ADMIN) && docOtraAdmin.getNombre().contains("_OTRAS_FECHA"))
        && (docOtraAdmin.getValor() != null && !docOtraAdmin.getValor().equals(""))) {

      docOtraAdmin1.setFhFechaPresent(new SimpleDateFormat(ConstantesTramitacion.FORMATO_FECHA_UE).parse(docOtraAdmin.getValor()));
      docsOtraAdminLeidos.add(docOtraAdmin);
      existeValor = true;
    }
    return existeValor;
  }

  /**
   * @param autorizaOtraAdmin
   * @param ultOtraAdmin
   * @return
   */
  private int obtenemosUltimoDocumentoOtraAdmin(final List<CampoVeaType> autorizaOtraAdmin, int ultOtraAdmin) {
    for (final CampoVeaType campoVea : autorizaOtraAdmin) {
      if (Integer.parseInt(campoVea.getNombre().substring(16, 17)) > ultOtraAdmin) {
        ultOtraAdmin = Integer.parseInt(campoVea.getNombre().substring(16, 17));
      }
    }
    return ultOtraAdmin;
  }

  /**
   * Establece el valor de la propiedad exclusionService
   *
   * @param exclusionService
   *          el exclusionService para establecer
   */
  public static void setExclusionService(final IExclusionService exclusionService) {
    Acciones.exclusionService = exclusionService;
  }
}
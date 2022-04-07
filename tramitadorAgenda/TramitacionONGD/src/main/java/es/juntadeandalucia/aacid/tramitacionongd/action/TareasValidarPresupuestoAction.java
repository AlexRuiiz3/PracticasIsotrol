package es.juntadeandalucia.aacid.tramitacionongd.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.juntadeandalucia.aacid.comuntramitacion.action.BaseTareasAction;
import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.EntidadesContribucionesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.GastoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.GastosContribucionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValidaPresupuestoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.IEnvioEmailAutomaticoService;
import es.juntadeandalucia.aacid.comuntramitacion.utils.ComparadorEntidadContribuciones;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Finalidad;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Gasto;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IContraparteService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IContribucionesService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IEntidadContribucionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IFinalidadService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IGastoContribucionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IGastosService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IValidacionPresupuestoService;

public class TareasValidarPresupuestoAction extends BaseTareasAction {
  private static final String EXITO_ENVIAR_EMAIL = "exito.enviar.email";

  private static final String LISTA_VALIDACION_EDITADAS = "listaGastosEntidadSeleccionada";

  /**
   *
   */
  private static final long serialVersionUID = -7740534787286082237L;

  /** LOGGER TareasSubsanacionAction.class */
  private final transient Logger log = Logger.getLogger(TareasValidarPresupuestoAction.class);

  private transient IEnvioEmailAutomaticoService envioEmailAutomaticoService;
  private transient ISolicitudService solicitudService;
  private transient IValidacionPresupuestoService validacionPresupuestoService;
  private transient IGastosService gastosService;
  private transient IFinalidadService finalidadService;
  private transient IContraparteService contrapartesService;
  private transient IEntidadContribucionService entidadContribucionService;
  private transient IGastoContribucionService gastosContribucionService;
  private transient IContribucionesService contribucionesService;

  private transient ValidaPresupuestoDTO validaPresupuestoDTO;
  private transient List<GastosContribucionDTO> listaGastosEntidadSeleccionada;
  private String entidadSeleccionada;

  public String inicioValidacionPresupuesto() {
    user = getUsuarioSesion();
    cargaTablaEntidadesYGastosValidacion();
    getSession().put("validaPresupuestoDTO", validaPresupuestoDTO);
    return SUCCESS;
  }

  public String cargaTablaEntidadesYGastosValidacion() {
    try {
      user = getUsuarioSesion();
      final String refExpediente = user.getExpediente().getRefExpediente();
      final SolicitudDatosGeneralesDTO solicitud = solicitudService.datosGeneralesSolicitudByIdExpTrewa(refExpediente);
      final Finalidad finalidad = finalidadService.obtenerFinalidadByCodigo(solicitud.getFinalidad());
      Long ordenEntidades = 0L;
      validaPresupuestoDTO = new ValidaPresupuestoDTO();

      // Preparamos entidad AACID
      final EntidadesContribucionesDTO nuevaAacid = new EntidadesContribucionesDTO(ConstantesTramitacion.SISTEMA_ACCID, ConstantesTramitacion.SISTEMA_ACCID,
          ConstantesTramitacion.SISTEMA_ACCID);
      nuevaAacid.setValoresAI(new ArrayList<>());
      nuevaAacid.setValoresAII(new ArrayList<>());
      nuevaAacid.setId(ordenEntidades);
      ordenEntidades++;
      validaPresupuestoDTO.getListaEntidadesSelect().add(nuevaAacid);

      // Preparamos entidad Solicitante
      final EntidadesContribucionesDTO nuevaSol = new EntidadesContribucionesDTO(ConstantesTramitacion.SOLICITANTE, ConstantesTramitacion.SOLICITANTE,
          ConstantesTramitacion.SOLICITANTE);
      nuevaSol.setValoresAI(new ArrayList<>());
      nuevaSol.setValoresAII(new ArrayList<>());
      nuevaSol.setId(ordenEntidades);
      ordenEntidades++;
      validaPresupuestoDTO.getListaEntidadesSelect().add(nuevaSol);

      // Inicializamos listas de gastos
      final List<Gasto> gastos = gastosService.obtieneGastosByFinalidad(finalidad.getFinaNuId());
      validacionPresupuestoService.estableceGastosIyII(gastos, validaPresupuestoDTO);
      // Entidades participantes
      entidadContribucionService.obtenerEntidadesParticipantes(solicitud, validaPresupuestoDTO.getListaEntidades());
      // Entidades contrapartes
      contrapartesService.obtenerContrapartes(solicitud, validaPresupuestoDTO.getListaEntidades());
      // Contribuciones
      entidadContribucionService.estableceContribuciones(solicitud, nuevaAacid, nuevaSol, validaPresupuestoDTO);

      // Añadimos todas las entidades contribuyentes a la lista de entidades
      for (final EntidadesContribucionesDTO entidadContribuciones : validaPresupuestoDTO.getListaEntidades()) {
        entidadContribuciones.setId(ordenEntidades);
        ordenEntidades++;
        validaPresupuestoDTO.getListaEntidadesSelect().add(entidadContribuciones);
      }

      // Ordenamos las entidades por el indice
      Collections.sort(validaPresupuestoDTO.getListaEntidadesSelect(), new ComparadorEntidadContribuciones());

      // Preparamos los datos para la creación de nuevos gastos
      gastosService.anyadeGastosAEntidad(validaPresupuestoDTO);
      gastosContribucionService.anyadeGastoContribucionAEntidad(validaPresupuestoDTO);

    } catch (final TramitacionException e) {
      errores = getText("error.cargar");
      log.error(errores, e);
      addActionError(errores);
    }
    return SUCCESS;
  }

  public String recargaTablaContribuciones() {
    validaPresupuestoDTO = (ValidaPresupuestoDTO) getSession().get("validaPresupuestoDTO");
    if (StringUtils.isNotBlank(entidadSeleccionada)) {
      final int posGuion = StringUtils.indexOf(entidadSeleccionada, "_");
      final Long idEntidad = UtilidadesNumero.parseStringToLong(entidadSeleccionada.substring(0, posGuion));
      for (final EntidadesContribucionesDTO entidad : validaPresupuestoDTO.getListaEntidadesSelect()) {
        if (entidad.getId().equals(idEntidad)) {
          validaPresupuestoDTO.setEntidad(entidad);
        }
      }
      if (validaPresupuestoDTO.getEntidad() != null) {
        final List<GastosContribucionDTO> gastos = new ArrayList<>(validaPresupuestoDTO.getEntidad().getValoresAI());
        gastos.addAll(validaPresupuestoDTO.getEntidad().getValoresAII());
        validaPresupuestoDTO.setListaNuevaEntidad(gastos);
        listaGastosEntidadSeleccionada = gastos;
      }
    }
    return SUCCESS;
  }

  public String guardar() {
    final List<String> listaErrores = new ArrayList<>();
    final String refExpediente = getUsuarioSesion().getExpediente().getRefExpediente();
    try {
      if (StringUtils.isNotBlank(entidadSeleccionada)) {
        final int posGuion = entidadSeleccionada.indexOf("_");
        final EntidadesContribucionesDTO entidadContribucionesDTO = new EntidadesContribucionesDTO();
        entidadContribucionesDTO.setId(UtilidadesNumero.parseStringToLong(entidadSeleccionada.substring(0, posGuion)));
        entidadContribucionesDTO.setTipo(entidadSeleccionada.substring(posGuion + 1));
        listaGastosEntidadSeleccionada = new ArrayList<>();
        for (int i = 0; request.getParameterMap().containsKey(LISTA_VALIDACION_EDITADAS + "[" + i + "][idGastoContribucion]"); i++) {
          final GastosContribucionDTO gastoContribucion = new GastosContribucionDTO();
          gastoContribucion
              .setIdGastoContribucion(UtilidadesNumero.parseStringToLong(request.getParameter(LISTA_VALIDACION_EDITADAS + "[" + i + "][idGastoContribucion]")));
          gastoContribucion.setValorNoValidadoTitle(request.getParameter(LISTA_VALIDACION_EDITADAS + "[" + i + "][valorNoValidado]"));
          gastoContribucion.setGasto(new GastoDTO(null, null, null,
              UtilidadesNumero.parseStringToLong(request.getParameter(LISTA_VALIDACION_EDITADAS + "[" + i + "][gasto][idGasto]"))));
          entidadContribucionesDTO.setListaNuevaEntidad(new ArrayList<>());
          entidadContribucionesDTO.getListaNuevaEntidad().add(gastoContribucion);
        }

        final SolicitudDatosGeneralesDTO solicitud = solicitudService.datosGeneralesSolicitudByIdExpTrewa(refExpediente);
        if (solicitud == null) {
          throw new TramitacionException("Ha ocurrido un error al guardar el presupuesto, el expediente no tiene solicitud asignada");
        }
        contribucionesService.guardarContribuciones(entidadContribucionesDTO, solicitud);
      }

    } catch (final NumberFormatException e) {
      log.error("Error en el formato de un numerico. Error: " + e.getMessage(), e);
      listaErrores.add("El número introducido no es correcto");
      return ERROR;
    } catch (final Exception e) {
      errores = "Ha ocurrido un error al guardar el presupuesto validado.";
      log.error(errores, e);
      addActionError(errores);
      listaErrores.add(errores);
      return ERROR;
    }

    if (listaErrores.isEmpty()) {
      addActionMessage("Presupuesto guardado correctamente.");
    }
    recargaTablaContribuciones();
    return SUCCESS;
  }

  public String envioEmailDocumentoPresupuestoValidado() {
    user = getUsuarioSesion();
    try {
      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(user.getExpediente().getRefExpediente());
      envioEmailAutomaticoService.enviarEmail(ConstantesTramitacion.VALIDACION_PRESUPUESTO, solicitudDTO.getEmail(), user.getExpediente().getRefExpediente(),
          ConstantesTramitacion.ETIQUETA_DOC_PRE_VAL, null);
    } catch (final AddressException e) {
      log.error("La direccion para el envio de correo automatico con el documento de presupuesto validado adjunto es incorrecta. ERROR:" + e.getMessage());
      addActionError(e.getMessage());
      return ERROR;
    } catch (final MessagingException e) {
      log.error("Error en el envio de correo automatico con el documento de presupuesto validado adjunto. Causa: " + e.getMessage());
      addActionError(e.getMessage());
      return ERROR;
    } catch (final Exception ex) {
      log.error("Error en el envio de correo automatico con el documento de presupuesto validado adjunto. Causa: " + ex.getMessage());
      addActionError(ex.getMessage());
      return ERROR;
    }
    addActionMessage(getText(EXITO_ENVIAR_EMAIL));
    return SUCCESS;
  }

  public String envioEmailDocumentoBeneficiarosSuplentes() {
    user = getUsuarioSesion();
    try {
      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(user.getExpediente().getRefExpediente());
      envioEmailAutomaticoService.enviarEmail(ConstantesTramitacion.VALIDACION_PRESUPUESTO, solicitudDTO.getEmail(), user.getExpediente().getRefExpediente(),
          ConstantesTramitacion.ETIQUETA_DOC_BEN_SUPL, null);
    } catch (final AddressException e) {
      log.error("La direccion para el envio de correo automatico con el documento de presupuesto validado adjunto es incorrecta. ERROR:" + e.getMessage());
      addActionError(e.getMessage());
      return ERROR;
    } catch (final MessagingException e) {
      log.error("Error en el envio de correo automatico con el documento de presupuesto validado adjunto. Causa: " + e.getMessage());
      addActionError(e.getMessage());
      return ERROR;
    } catch (final Exception ex) {
      log.error("Error en el envio de correo automatico con el documento de presupuesto validado adjunto. Causa: " + ex.getMessage());
      addActionError(ex.getMessage());
      return ERROR;
    }
    addActionMessage(getText(EXITO_ENVIAR_EMAIL));
    return SUCCESS;
  }

  public String envioEmailDocumentoInformeValoracion() {
    user = getUsuarioSesion();
    try {
      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(user.getExpediente().getRefExpediente());
      envioEmailAutomaticoService.enviarEmail(ConstantesTramitacion.VALIDACION_PRESUPUESTO, solicitudDTO.getEmail(), user.getExpediente().getRefExpediente(),
          ConstantesTramitacion.ETIQUETA_INF_VALORACION_ONGD, null);
    } catch (final AddressException e) {
      log.error("La direccion para el envio de correo automatico con el documento de valoracion adjunto es incorrecta. ERROR:" + e.getMessage());
      addActionError(e.getMessage());
      return ERROR;
    } catch (final MessagingException e) {
      log.error("Error en el envio de correo automatico con el informe de valoracion adjunto. Causa: " + e.getMessage());
      addActionError(e.getMessage());
      return ERROR;
    } catch (final Exception ex) {
      log.error("Error en el envio de correo automatico con el informe de valoracion adjunto. Causa: " + ex.getMessage());
      addActionError(ex.getMessage());
      return ERROR;
    }
    addActionMessage(getText(EXITO_ENVIAR_EMAIL));
    return SUCCESS;
  }

  /**
   * Obtiene la propiedad validaPresupuestoDTO
   *
   * @return el validaPresupuestoDTO
   */
  public ValidaPresupuestoDTO getValidaPresupuestoDTO() {
    return validaPresupuestoDTO;
  }

  /**
   * Establece el valor de la propiedad validaPresupuestoDTO
   *
   * @param validaPresupuestoDTO
   *          el validaPresupuestoDTO para establecer
   */
  public void setValidaPresupuestoDTO(final ValidaPresupuestoDTO validaPresupuestoDTO) {
    this.validaPresupuestoDTO = validaPresupuestoDTO;
  }

  /**
   * Establece el valor de la propiedad solicitudService
   *
   * @param solicitudService
   *          el solicitudService para establecer
   */
  public void setSolicitudService(final ISolicitudService solicitudService) {
    this.solicitudService = solicitudService;
  }

  /**
   * Establece el valor de la propiedad validacionPresupuestoService
   *
   * @param validacionPresupuestoService
   *          el validacionPresupuestoService para establecer
   */
  public void setValidacionPresupuestoService(final IValidacionPresupuestoService validacionPresupuestoService) {
    this.validacionPresupuestoService = validacionPresupuestoService;
  }

  /**
   * Establece el valor de la propiedad gastosService
   *
   * @param gastosService
   *          el gastosService para establecer
   */
  public void setGastosService(final IGastosService gastosService) {
    this.gastosService = gastosService;
  }

  /**
   * Establece el valor de la propiedad finalidadService
   *
   * @param finalidadService
   *          el finalidadService para establecer
   */
  public void setFinalidadService(final IFinalidadService finalidadService) {
    this.finalidadService = finalidadService;
  }

  /**
   * Establece el valor de la propiedad contrapartesService
   *
   * @param contrapartesService
   *          el contrapartesService para establecer
   */
  public void setContrapartesService(final IContraparteService contrapartesService) {
    this.contrapartesService = contrapartesService;
  }

  /**
   * Establece el valor de la propiedad entidadContribucionService
   *
   * @param entidadContribucionService
   *          el entidadContribucionService para establecer
   */
  public void setEntidadContribucionService(final IEntidadContribucionService entidadContribucionService) {
    this.entidadContribucionService = entidadContribucionService;
  }

  /**
   * Establece el valor de la propiedad gastosContribucionService
   *
   * @param gastosContribucionService
   *          el gastosContribucionService para establecer
   */
  public void setGastosContribucionService(final IGastoContribucionService gastosContribucionService) {
    this.gastosContribucionService = gastosContribucionService;
  }

  /**
   * Establece el valor de la propiedad contribucionesService
   *
   * @param contribucionesService
   *          el contribucionesService para establecer
   */
  public void setContribucionesService(final IContribucionesService contribucionesService) {
    this.contribucionesService = contribucionesService;
  }

  /**
   * Obtiene la propiedad entidadSeleccionada
   *
   * @return el entidadSeleccionada
   */
  public String getEntidadSeleccionada() {
    return entidadSeleccionada;
  }

  /**
   * Establece el valor de la propiedad entidadSeleccionada
   *
   * @param entidadSeleccionada
   *          el entidadSeleccionada para establecer
   */
  public void setEntidadSeleccionada(final String entidadSeleccionada) {
    this.entidadSeleccionada = entidadSeleccionada;
  }

  /**
   * Establece el valor de la propiedad listaGastosEntidadSeleccionada
   *
   * @param listaGastosEntidadSeleccionada
   *          el listaGastosEntidadSeleccionada para establecer
   */
  public void setListaGastosEntidadSeleccionada(final List<GastosContribucionDTO> listaGastosEntidadSeleccionada) {
    this.listaGastosEntidadSeleccionada = listaGastosEntidadSeleccionada;
  }

  /**
   * Obtiene la propiedad listaGastosEntidadSeleccionada
   *
   * @return el listaGastosEntidadSeleccionada
   */
  public List<GastosContribucionDTO> getListaGastosEntidadSeleccionada() {
    return listaGastosEntidadSeleccionada;
  }

  /**
   * Establece el valor de la propiedad envioEmailAutomaticoService
   *
   * @param envioEmailAutomaticoService
   *          el envioEmailAutomaticoService para establecer
   */
  public void setEnvioEmailAutomaticoService(final IEnvioEmailAutomaticoService envioEmailAutomaticoService) {
    this.envioEmailAutomaticoService = envioEmailAutomaticoService;
  }

}

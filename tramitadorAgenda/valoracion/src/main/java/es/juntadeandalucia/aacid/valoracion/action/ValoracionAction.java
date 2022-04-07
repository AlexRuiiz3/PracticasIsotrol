package es.juntadeandalucia.aacid.valoracion.action;

import static java.util.Calendar.YEAR;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import es.juntadeandalucia.aacid.comuntramitacion.action.BaseTareasAction;
import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.DocumentoExpedienteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudAuxiliarDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionSolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionTipoCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.IGeneracionDocumentosService;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesFecha;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ICapacidadGestionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ICriteriosService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IDocumentoExpedienteService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IFinalidadService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaisService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudCriteriosService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudTipoCriterioService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudesAuxiliarService;
import es.juntadeandalucia.plataforma.comunes.excepciones.BusinessException;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;
import trewa.bd.trapi.trapiui.tpo.TrDocumentoExpediente;

public class ValoracionAction extends BaseTareasAction {

  private final transient Logger log = Logger.getLogger(ValoracionAction.class);

  private static final String ERROR_CARGAR = "error.cargar";

  private static final String LISTA_VALORACIONES_EDITADAS = "listaValoracionesEditadas[";

  private static final long serialVersionUID = -7740534787286082237L;

  private transient ICapacidadGestionService capacidadGestionService;
  private transient IPaisService paisService;
  private transient ISolicitudCriteriosService solicitudCriteriosService;
  private transient ISolicitudesAuxiliarService solicitudesAuxiliarService;
  private transient ISolicitudService solicitudService;
  private transient ISolicitudTipoCriterioService solicitudTipoCriterioService;
  private transient ICriteriosService criteriosService;
  private transient IFinalidadService finalidadService;
  private transient ITrewaService trewaService;
  private transient IGeneracionDocumentosService generacionDocumentosService;
  private transient List<ValoracionCriterioDTO> listaValoracionesEditadas;
  private transient ValoracionSolicitudDTO valoracionSolicitudDTO;
  private boolean esCoordinador;
  private boolean esExterno;
  private transient IDocumentoExpedienteService documentoExpedienteService;

  public String cargaListadoCapacidadGestion() {
    user = getUsuarioSesion();
    nExp = user.getExpediente().getRefExpediente();
    valoracionSolicitudDTO.getCapacidadGestion().setListaValoraciones(new ArrayList<>());
    final GregorianCalendar fechaCreacion = new GregorianCalendar();
    fechaCreacion.setTime(getUsuarioSesion().getExpediente().getFechaCreacion());
    final Integer anio = fechaCreacion.get(YEAR);

    try {
      final ValoracionTipoCriterioDTO valoracionTipoCriterioDTO = criteriosService.obtenerListaCriteriosByFinalidadAndConvocatoria(
          valoracionSolicitudDTO.getCodigoFinalidad(), ConstantesTramitacion.CAP_GESTION, !valoracionSolicitudDTO.isOngd(),
          UtilidadesFecha.obtenerAnyoConvocatoriaConAnyoCriterios(user.getExpediente().getFechaCreacion()), false);

      // Seteamos según valoración existente
      valoracionSolicitudDTO.getCapacidadGestion().setListaValoraciones(valoracionTipoCriterioDTO.getListaValoraciones());
      solicitudCriteriosService.obtenerValoracionCriterios(Long.valueOf(nExp), valoracionSolicitudDTO.getCapacidadGestion());

      if (CollectionUtils.isNotEmpty(valoracionSolicitudDTO.getCapacidadGestion().getListaValoraciones())) {
        final ValoracionCriterioDTO valoracionCapGest = valoracionSolicitudDTO.getCapacidadGestion().getListaValoraciones()
            .get(valoracionSolicitudDTO.getCapacidadGestion().getListaValoraciones().size() - 1);

        // Si no tiene valoración previa recuperamos de la tabla de capacidad de gestión
        if (valoracionCapGest.getValoracion() == null || valoracionCapGest.getValoracion().compareTo(BigDecimal.ZERO) == 0) {
          capacidadGestionService.obtenerValorCapacidadGestionEntidad(nExp, valoracionCapGest, anio);
        }
      }
    } catch (final TramitacionException e) {
      errores = getText(ERROR_CARGAR);
      log.error(errores, e);
      addActionError(errores);
    }
    return SUCCESS;
  }

  public String cargaListadoCoherencia() {
    user = getUsuarioSesion();
    nExp = user.getExpediente().getRefExpediente();
    try {
      final ValoracionTipoCriterioDTO valoracionTipoCriterioDTO = criteriosService.obtenerListaCriteriosByFinalidadAndConvocatoria(
          valoracionSolicitudDTO.getCodigoFinalidad(), ConstantesTramitacion.COHERENCIA, !valoracionSolicitudDTO.isOngd(),
          UtilidadesFecha.obtenerAnyoConvocatoriaConAnyoCriterios(user.getExpediente().getFechaCreacion()),
          finalidadService.filtrarSubFinalidad(valoracionSolicitudDTO.getCodigoFinalidad(), user.getExpediente().getRefExpediente()));
      valoracionSolicitudDTO.getCoherencia().setListaValoraciones(valoracionTipoCriterioDTO.getListaValoraciones());
      solicitudCriteriosService.obtenerValoracionCriterios(Long.valueOf(nExp), valoracionSolicitudDTO.getCoherencia());

    } catch (final TramitacionException e) {
      errores = getText(ERROR_CARGAR);
      log.error(errores, e);
      addActionError(errores);
    }
    return SUCCESS;
  }

  public String cargaListadoConectividad() {
    user = getUsuarioSesion();
    nExp = user.getExpediente().getRefExpediente();
    try {
      final ValoracionTipoCriterioDTO valoracionTipoCriterioDTO = criteriosService.obtenerListaCriteriosByFinalidadAndConvocatoria(
          valoracionSolicitudDTO.getCodigoFinalidad(), ConstantesTramitacion.CONECTIVIDAD, !valoracionSolicitudDTO.isOngd(),
          UtilidadesFecha.obtenerAnyoConvocatoriaConAnyoCriterios(user.getExpediente().getFechaCreacion()),
          finalidadService.filtrarSubFinalidad(valoracionSolicitudDTO.getCodigoFinalidad(), user.getExpediente().getRefExpediente()));
      valoracionSolicitudDTO.getConectividad().setListaValoraciones(valoracionTipoCriterioDTO.getListaValoraciones());
      solicitudCriteriosService.obtenerValoracionCriterios(Long.valueOf(nExp), valoracionSolicitudDTO.getConectividad());

    } catch (final TramitacionException e) {
      errores = getText(ERROR_CARGAR);
      log.error(errores, e);
      addActionError(errores);
    }
    return SUCCESS;
  }

  public String cargaListadoConvergencia() {
    user = getUsuarioSesion();
    nExp = user.getExpediente().getRefExpediente();
    valoracionSolicitudDTO.getConvergencia().setListaValoraciones(new ArrayList<>());
    try {
      final ValoracionTipoCriterioDTO valoracionTipoCriterioDTO = criteriosService.obtenerListaCriteriosByFinalidadAndConvocatoria(
          valoracionSolicitudDTO.getCodigoFinalidad(), ConstantesTramitacion.CONVERGENCIA, !valoracionSolicitudDTO.isOngd(),
          UtilidadesFecha.obtenerAnyoConvocatoriaConAnyoCriterios(user.getExpediente().getFechaCreacion()),
          finalidadService.filtrarSubFinalidad(valoracionSolicitudDTO.getCodigoFinalidad(), user.getExpediente().getRefExpediente()));
      valoracionSolicitudDTO.getConvergencia().setListaValoraciones(valoracionTipoCriterioDTO.getListaValoraciones());
      solicitudCriteriosService.obtenerValoracionCriterios(Long.valueOf(nExp), valoracionSolicitudDTO.getConvergencia());

      valoracionSolicitudDTO.getConvergencia().getListaValoraciones().get(valoracionSolicitudDTO.getConvergencia().getListaValoraciones().size() - 1);
      solicitudCriteriosService.obtenerValoracionCriterios(Long.valueOf(nExp), valoracionSolicitudDTO.getConvergencia());

      final ValoracionCriterioDTO valoracionConverg = valoracionSolicitudDTO.getConvergencia().getListaValoraciones()
          .get(valoracionSolicitudDTO.getConvergencia().getListaValoraciones().size() - 1);

      // Si no tiene valoración previa recuperamos de la tabla de capacidad de gestión
      if (valoracionConverg.getValoracion() == null || valoracionConverg.getValoracion().compareTo(BigDecimal.ZERO) == 0) {
        paisService.obtenerValoracionConvergencia(valoracionSolicitudDTO.getIdSolicitud(), valoracionConverg);
      }

    } catch (final TramitacionException e) {
      errores = getText(ERROR_CARGAR);
      log.error(errores, e);
      addActionError(errores);
    }
    return SUCCESS;
  }

  public String cargaListadoImpacto() {
    user = getUsuarioSesion();
    nExp = user.getExpediente().getRefExpediente();
    try {
      final ValoracionTipoCriterioDTO valoracionTipoCriterioDTO = criteriosService.obtenerListaCriteriosByFinalidadAndConvocatoria(
          valoracionSolicitudDTO.getCodigoFinalidad(), ConstantesTramitacion.IMPACTO, !valoracionSolicitudDTO.isOngd(),
          UtilidadesFecha.obtenerAnyoConvocatoriaConAnyoCriterios(user.getExpediente().getFechaCreacion()),
          finalidadService.filtrarSubFinalidad(valoracionSolicitudDTO.getCodigoFinalidad(), user.getExpediente().getRefExpediente()));
      valoracionSolicitudDTO.getImpacto().setListaValoraciones(valoracionTipoCriterioDTO.getListaValoraciones());
      solicitudCriteriosService.obtenerValoracionCriterios(Long.valueOf(nExp), valoracionSolicitudDTO.getImpacto());

    } catch (final TramitacionException e) {
      errores = getText(ERROR_CARGAR);
      log.error(errores, e);
      addActionError(errores);
    }
    return SUCCESS;
  }

  public String cargaListadoIncrementos() {
    user = getUsuarioSesion();
    nExp = user.getExpediente().getRefExpediente();
    valoracionSolicitudDTO.getIncrementos().setListaValoraciones(new ArrayList<>());
    try {
      final ValoracionTipoCriterioDTO valoracionTipoCriterioDTO = criteriosService.obtenerListaCriteriosByFinalidadAndConvocatoria(
          valoracionSolicitudDTO.getCodigoFinalidad(), ConstantesTramitacion.INCREMENTO, !valoracionSolicitudDTO.isOngd(),
          UtilidadesFecha.obtenerAnyoConvocatoriaConAnyoCriterios(user.getExpediente().getFechaCreacion()), false);
      valoracionSolicitudDTO.getIncrementos().setListaValoraciones(valoracionTipoCriterioDTO.getListaValoraciones());
      solicitudCriteriosService.obtenerValoracionCriterios(Long.valueOf(nExp), valoracionSolicitudDTO.getIncrementos());

    } catch (final TramitacionException e) {
      errores = getText(ERROR_CARGAR);
      log.error(errores, e);
      addActionError(errores);
    }
    return SUCCESS;
  }

  public String cargaListadoPertinencia() {
    user = getUsuarioSesion();
    nExp = user.getExpediente().getRefExpediente();
    try {
      final ValoracionTipoCriterioDTO valoracionTipoCriterioDTO = criteriosService.obtenerListaCriteriosByFinalidadAndConvocatoria(
          valoracionSolicitudDTO.getCodigoFinalidad(), ConstantesTramitacion.PERTINENCIA, !valoracionSolicitudDTO.isOngd(),
          UtilidadesFecha.obtenerAnyoConvocatoriaConAnyoCriterios(user.getExpediente().getFechaCreacion()),
          finalidadService.filtrarSubFinalidad(valoracionSolicitudDTO.getCodigoFinalidad(), user.getExpediente().getRefExpediente()));
      valoracionSolicitudDTO.getPertinencia().setListaValoraciones(valoracionTipoCriterioDTO.getListaValoraciones());
      solicitudCriteriosService.obtenerValoracionCriterios(Long.valueOf(nExp), valoracionSolicitudDTO.getPertinencia());

    } catch (final TramitacionException e) {
      errores = getText(ERROR_CARGAR);
      log.error(errores, e);
      addActionError(errores);
    }
    return SUCCESS;
  }

  public String cargaListadoSostenibilidad() {
    user = getUsuarioSesion();
    nExp = user.getExpediente().getRefExpediente();
    try {
      final ValoracionTipoCriterioDTO valoracionTipoCriterioDTO = criteriosService.obtenerListaCriteriosByFinalidadAndConvocatoria(
          valoracionSolicitudDTO.getCodigoFinalidad(), ConstantesTramitacion.SOSTENIBILIDAD, !valoracionSolicitudDTO.isOngd(),
          UtilidadesFecha.obtenerAnyoConvocatoriaConAnyoCriterios(user.getExpediente().getFechaCreacion()),
          finalidadService.filtrarSubFinalidad(valoracionSolicitudDTO.getCodigoFinalidad(), user.getExpediente().getRefExpediente()));
      valoracionSolicitudDTO.getSostenibilidad().setListaValoraciones(valoracionTipoCriterioDTO.getListaValoraciones());
      solicitudCriteriosService.obtenerValoracionCriterios(Long.valueOf(nExp), valoracionSolicitudDTO.getSostenibilidad());

    } catch (final TramitacionException e) {
      errores = getText(ERROR_CARGAR);
      log.error(errores, e);
      addActionError(errores);
    }
    return SUCCESS;
  }

  public String cargaListadoViabilidad() {
    user = getUsuarioSesion();
    nExp = user.getExpediente().getRefExpediente();
    try {
      final ValoracionTipoCriterioDTO valoracionTipoCriterioDTO = criteriosService.obtenerListaCriteriosByFinalidadAndConvocatoria(
          valoracionSolicitudDTO.getCodigoFinalidad(), ConstantesTramitacion.VIABILIDAD, !valoracionSolicitudDTO.isOngd(),
          UtilidadesFecha.obtenerAnyoConvocatoriaConAnyoCriterios(user.getExpediente().getFechaCreacion()),
          finalidadService.filtrarSubFinalidad(valoracionSolicitudDTO.getCodigoFinalidad(), user.getExpediente().getRefExpediente()));
      valoracionSolicitudDTO.getViabilidad().setListaValoraciones(valoracionTipoCriterioDTO.getListaValoraciones());
      solicitudCriteriosService.obtenerValoracionCriterios(Long.valueOf(nExp), valoracionSolicitudDTO.getViabilidad());

    } catch (final TramitacionException e) {
      errores = getText(ERROR_CARGAR);
      log.error(errores, e);
      addActionError(errores);
    }
    return SUCCESS;
  }

  private void estableceAtributosValoracionSolicitud(final Long idSolicitud) throws TramitacionException {
    valoracionSolicitudDTO.setIdSolicitud(idSolicitud);

    final SolicitudAuxiliarDTO solicitudAuxiliarDTO = solicitudesAuxiliarService.findByIdSolicitud(idSolicitud);
    if (solicitudAuxiliarDTO != null && solicitudAuxiliarDTO.getId() != null) {
      valoracionSolicitudDTO.setNumReintegros(solicitudAuxiliarDTO.getNumReintegros());
      valoracionSolicitudDTO.setCausaReintegros(solicitudAuxiliarDTO.getCausaReintegro());
      valoracionSolicitudDTO.setMagnitudReintegros(solicitudAuxiliarDTO.getMagnitudReintegro());
      valoracionSolicitudDTO.getValoracionTotal().setObservaciones(solicitudAuxiliarDTO.getObservaciones());
    }

    if (valoracionSolicitudDTO.isOngd()) {
      if (ConstantesTramitacion.COD_COOPERACION.equals(valoracionSolicitudDTO.getCodigoFinalidad())) {
        solicitudTipoCriterioService.obtenerDatosTipoCriterio(idSolicitud, ConstantesTramitacion.CONECTIVIDAD, valoracionSolicitudDTO.getConectividad());
      }
      solicitudTipoCriterioService.obtenerDatosTipoCriterio(idSolicitud, ConstantesTramitacion.CONVERGENCIA, valoracionSolicitudDTO.getConvergencia());
      solicitudTipoCriterioService.obtenerDatosTipoCriterio(idSolicitud, ConstantesTramitacion.CAP_GESTION, valoracionSolicitudDTO.getCapacidadGestion());
      solicitudTipoCriterioService.obtenerDatosTipoCriterio(idSolicitud, ConstantesTramitacion.INCREMENTO, valoracionSolicitudDTO.getIncrementos());
    }

    solicitudTipoCriterioService.obtenerDatosTipoCriterio(idSolicitud, ConstantesTramitacion.PERTINENCIA, valoracionSolicitudDTO.getPertinencia());
    solicitudTipoCriterioService.obtenerDatosTipoCriterio(idSolicitud, ConstantesTramitacion.VIABILIDAD, valoracionSolicitudDTO.getViabilidad());
    solicitudTipoCriterioService.obtenerDatosTipoCriterio(idSolicitud, ConstantesTramitacion.COHERENCIA, valoracionSolicitudDTO.getCoherencia());
    solicitudTipoCriterioService.obtenerDatosTipoCriterio(idSolicitud, ConstantesTramitacion.SOSTENIBILIDAD, valoracionSolicitudDTO.getSostenibilidad());
    solicitudTipoCriterioService.obtenerDatosTipoCriterio(idSolicitud, ConstantesTramitacion.IMPACTO, valoracionSolicitudDTO.getImpacto());
  }

  public List<ValoracionCriterioDTO> getListaValoracionesEditadas() {
    return listaValoracionesEditadas;
  }

  /**
   * @return the valoracionSolicitudDTO
   */
  public ValoracionSolicitudDTO getValoracionSolicitudDTO() {
    return valoracionSolicitudDTO;
  }

  public String guardarValoracion() {
    user = getUsuarioSesion();
    try {
      listaValoracionesEditadas = new ArrayList<>();
      for (int i = 0; request.getParameterMap().containsKey(LISTA_VALORACIONES_EDITADAS + i + "][idCriterio]"); i++) {
        listaValoracionesEditadas
            .add(new ValoracionCriterioDTO(UtilidadesNumero.parseStringToLong(request.getParameter(LISTA_VALORACIONES_EDITADAS + i + "][idCriterio]")),
                UtilidadesNumero.convertStringToBigDecimalIfNotBlank(request.getParameter(LISTA_VALORACIONES_EDITADAS + i + "][valoracion]")),
                UtilidadesNumero.convertStringToBigDecimalIfNotBlank(request.getParameter(LISTA_VALORACIONES_EDITADAS + i + "][puntuacion]"))));
      }

      final SolicitudDTO solicitudDTO = solicitudService.obtenerSolicitudByIdExp(user.getExpediente().getRefExpediente());
      if (solicitudDTO == null) {
        throw new TramitacionException(ConstantesTramitacion.ERROR_OBTENER_SOLICITUD);
      }
      valoracionSolicitudDTO.setIdSolicitud(solicitudDTO.getIdSolicitud());
      valoracionSolicitudDTO.setAnio(UtilidadesFecha.obtenerAnyoConvocatoriaConAnyoCriterios(user.getExpediente().getFechaCreacion()));
      valoracionSolicitudDTO.setOngd(ConstantesTramitacion.ABREVIATURA_ONGD.equals(user.getExpediente().getProcedimiento().getAbreviatura()));

      setActionErrors(solicitudCriteriosService.validarValoracionSolicitud(listaValoracionesEditadas, valoracionSolicitudDTO, valoracionSolicitudDTO.isOngd()));

      if (getActionErrors().isEmpty()) {
        if (valoracionSolicitudDTO.isOngd()) {
          solicitudTipoCriterioService.realizarComprobaciones(valoracionSolicitudDTO, user.getListaPerfiles());
        }
        solicitudCriteriosService.guardarValoracionSolicitud(listaValoracionesEditadas, valoracionSolicitudDTO);
      } else {
        getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return ERROR;
      }

    } catch (final TramitacionException | BusinessException e) {
      addActionError(getText("error.guardar"));
      log.error(errores, e);
      addActionError(errores);
      getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
      return ERROR;
    }
    addActionMessage(getText("exito.guardar"));
    return SUCCESS;
  }

  public String inicio() {
    valoracionSolicitudDTO = new ValoracionSolicitudDTO();
    user = getUsuarioSesion();

    if (user != null && user.getExpediente() != null) {
      nExp = user.getExpediente().getRefExpediente();
      try {
        valoracionSolicitudDTO.setOngd(ConstantesTramitacion.ABREVIATURA_ONGD.equals(user.getExpediente().getProcedimiento().getAbreviatura()));
        valoracionSolicitudDTO.setCodigoFinalidad(finalidadService.obtenerCodFinalidadByNumExpediente(Long.valueOf(nExp), !valoracionSolicitudDTO.isOngd()));
        if (StringUtils.isNotBlank(valoracionSolicitudDTO.getCodigoFinalidad())) {
          final SolicitudDatosGeneralesDTO solicitud = solicitudService.datosGeneralesSolicitudByIdExpTrewa(user.getExpediente().getRefExpediente());
          valoracionSolicitudDTO.setIdSolicitud(solicitud.getIdSolicitud());
          estableceAtributosValoracionSolicitud(solicitud.getIdSolicitud());
          if (getActionErrors().isEmpty()) {
            if (valoracionSolicitudDTO.isOngd()) {
              solicitudTipoCriterioService.realizarComprobaciones(valoracionSolicitudDTO, user.getListaPerfiles());
            }
          } else {
            getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            return ERROR;
          }
          // Comprobamos que se encuentre en la fase de valoración para generar los informes.
          valoracionSolicitudDTO.setGeneracionInformes(ConstantesTramitacion.FASE_VALORACION_SUBSANACION.equals(user.getExpediente().getNombreFaseActual()));
        } else {
          getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
          addActionError(getText("msg.error.codFinalidad.noEncontrado"));
          return ERROR;
        }
        for (final Perfil perfil : user.getListaPerfiles()) {
          if (perfil.getNombre().equalsIgnoreCase("coordinador")) {
            esCoordinador = true;
          } else if (perfil.getNombre().equalsIgnoreCase("externo")) {
            esExterno = true;
          }
        }
      } catch (TramitacionException | BusinessException e) {
        log.error(e.getMessage(), e);
        setMensajeError(e.getMessage());
        getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        addActionError(getText(ERROR_CARGAR));
        return ERROR;
      }
    }
    return SUCCESS;

  }

  public String incorporarInfValoracion() {
    user = getUsuarioSesion();
    String etiquetaInfValoracion = null;
    String rutaPlantilla = null;
    String nombreTipoDocumento = null;

    if (user != null && user.getExpediente() != null) {
      nExp = user.getExpediente().getRefExpediente();
      try {
        // Comprobamos si el informe ya ha sido generado.
        if (valoracionSolicitudDTO.isOngd()) {
          etiquetaInfValoracion = ConstantesTramitacion.ETIQUETA_INF_VALORACION_ONGD;
          rutaPlantilla = ConstantesTramitacion.RUTA_PLANTILLA_INFORME_VALORACION_ONGD;
          nombreTipoDocumento = ConstantesTramitacion.TIPO_DOCUMENTO_INF_VAL_ONGD;
        } else {
          etiquetaInfValoracion = ConstantesTramitacion.ETIQUETA_INF_VALORACION_UNIV;
          rutaPlantilla = ConstantesTramitacion.RUTA_PLANTILLA_INFORME_VALORACION_UNIV;
          nombreTipoDocumento = ConstantesTramitacion.TIPO_DOCUMENTO_INF_VAL_UNIV;
        }
        final SolicitudDTO solicitud = solicitudService.obtenerSolicitudByIdExp(nExp);
        if (solicitud != null && solicitud.getIdSolicitud() != null) {
          final boolean filtrarSubtipo = finalidadService.filtrarSubFinalidad(valoracionSolicitudDTO.getCodigoFinalidad(),
              user.getExpediente().getRefExpediente());
          final Map<String, Object> mapaVariables = solicitudCriteriosService.obtenerDatosInformeValoracion(Long.valueOf(nExp), solicitud.getIdSolicitud(),
              valoracionSolicitudDTO, UtilidadesFecha.obtenerAnyoConvocatoriaConAnyoCriterios(user.getExpediente().getFechaCreacion()), filtrarSubtipo);

          mapaVariables.put("numExpediente", (solicitud.getCodIdentificativo() != null ? solicitud.getCodIdentificativo() : "")
              + (user.getExpediente().getNumeroExpediente() != null ? " (" + user.getExpediente().getNumeroExpediente() + ")" : ""));
          mapaVariables.put("numExpedientePie", (solicitud.getCodIdentificativo() != null ? solicitud.getCodIdentificativo() : ""));
          mapaVariables.put("titulo", solicitud.getTitulo());
          mapaVariables.put("entidad", solicitud.getEntidad());
          mapaVariables.put("tipoInforme", ConstantesTramitacion.TIPO_ENTIDAD_VALORACION);

          DocumentoExpedienteDTO doc = generacionDocumentosService.generarIncorporarInformeValoracion(mapaVariables, etiquetaInfValoracion,
              "Informe_valoracion", rutaPlantilla, nombreTipoDocumento, solicitud.getCodIdentificativo(), nExp, user);

          documentoExpedienteService.guardarDocumentoExpediente(doc);

          addActionMessage(getText("msg.exito.generacion.incorporacion.informe.valoracion"));
          return SUCCESS;
        } else {
          getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
          addActionError(getText("msg.error.solicitud.noEncontrada"));
        }
      
      } catch (final NumberFormatException e) {
        log.error(e.getMessage(), e);
        getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        addActionError(getText("msg.error.comprobar.existe.informe.valoracion"));
      } catch (final TramitacionException e) {
        log.error(e.getMessage(), e);
        getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        addActionError(e.getMessage());
      }
    } else {
      getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
      addActionError(getText("msg.error.usuario.no.sesion"));
    }

    return ERROR;
  }

  public String incorporarInfEvaluacion() {
    user = getUsuarioSesion();
    String etiquetaInfValoracion = null;
    String rutaPlantilla = null;
    String nombreTipoDocumento = null;

    if (user != null && user.getExpediente() != null) {
      nExp = user.getExpediente().getRefExpediente();
      try {
        // Comprobamos si el informe ya ha sido generado.
        if (valoracionSolicitudDTO.isOngd()) {
          etiquetaInfValoracion = ConstantesTramitacion.ETIQUETA_INF_EVALUACION_ONGD;
          rutaPlantilla = ConstantesTramitacion.RUTA_PLANTILLA_INFORME_VALORACION_ONGD;
          nombreTipoDocumento = ConstantesTramitacion.TIPO_DOCUMENTO_INF_EVAL_ONGD;
        } else {
          etiquetaInfValoracion = ConstantesTramitacion.ETIQUETA_INF_EVALUACION_UNIV;
          rutaPlantilla = ConstantesTramitacion.RUTA_PLANTILLA_INFORME_VALORACION_UNIV;
          nombreTipoDocumento = ConstantesTramitacion.TIPO_DOCUMENTO_INF_EVAL_UNIV;
        }
        final TrDocumentoExpediente[] documentosExpediente = trewaService.documentosDelExpediente(Long.valueOf(nExp), etiquetaInfValoracion);
        if (documentosExpediente != null && documentosExpediente.length > 0) {
          addActionError(getText("msg.error.existe.informe.evaluacion"));
          getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        } else {
          final SolicitudDTO solicitud = solicitudService.obtenerSolicitudByIdExp(nExp);
          if (solicitud != null && solicitud.getIdSolicitud() != null) {
            final boolean filtrarSubtipo = finalidadService.filtrarSubFinalidad(valoracionSolicitudDTO.getCodigoFinalidad(),
                user.getExpediente().getRefExpediente());
            final Map<String, Object> mapaVariables = solicitudCriteriosService.obtenerDatosInformeValoracion(Long.valueOf(nExp), solicitud.getIdSolicitud(),
                valoracionSolicitudDTO, UtilidadesFecha.obtenerAnyoConvocatoriaConAnyoCriterios(user.getExpediente().getFechaCreacion()), filtrarSubtipo);

            mapaVariables.put("numExpediente",
                solicitud.getCodIdentificativo() != null ? solicitud.getCodIdentificativo() : user.getExpediente().getNumeroExpediente());
            mapaVariables.put("titulo", solicitud.getTitulo());
            mapaVariables.put("entidad", solicitud.getEntidad());
            mapaVariables.put("tipoInforme", "EVALUACION");

            generacionDocumentosService.generarIncorporarInformeValoracion(mapaVariables, etiquetaInfValoracion, "Informe_evaluacion", rutaPlantilla,
                nombreTipoDocumento, null, nExp, user);
            addActionMessage(getText("msg.exito.generacion.incorporacion.informe.evaluacion"));
            return SUCCESS;
          } else {
            getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            addActionError(getText("msg.error.solicitud.noEncontrada"));
          }
        }
      } catch (final TramitacionException | NumberFormatException e) {
        log.error(e.getMessage(), e);
        getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        addActionError(e.getMessage());
      }
    } else {
      getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
      addActionError(getText("msg.error.usuario.no.sesion"));
    }

    return ERROR;
  }

  /**
   * Establece el valor de la propiedad capacidadGestionService
   *
   * @param capacidadGestionService
   *          el capacidadGestionService para establecer
   */
  public void setCapacidadGestionService(final ICapacidadGestionService capacidadGestionService) {
    this.capacidadGestionService = capacidadGestionService;
  }

  /*
   * @param criteriosService the criteriosService to set
   */
  public void setCriteriosService(final ICriteriosService criteriosService) {
    this.criteriosService = criteriosService;
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

  public void setListaValoracionesEditadas(final List<ValoracionCriterioDTO> listaValoracionesEditadas) {
    this.listaValoracionesEditadas = listaValoracionesEditadas;
  }

  /**
   * Establece el valor de la propiedad paisService
   *
   * @param paisService
   *          el paisService para establecer
   */
  public void setPaisService(final IPaisService paisService) {
    this.paisService = paisService;
  }

  /**
   * Establece el valor de la propiedad solicitudCriteriosService
   *
   * @param solicitudCriteriosService
   *          el solicitudCriteriosService para establecer
   */
  public void setSolicitudCriteriosService(final ISolicitudCriteriosService solicitudCriteriosService) {
    this.solicitudCriteriosService = solicitudCriteriosService;
  }

  /**
   * Establece el valor de la propiedad solicitudesAuxiliarService
   *
   * @param solicitudesAuxiliarService
   *          el solicitudesAuxiliarService para establecer
   */
  public void setSolicitudesAuxiliarService(final ISolicitudesAuxiliarService solicitudesAuxiliarService) {
    this.solicitudesAuxiliarService = solicitudesAuxiliarService;
  }

  public void setSolicitudService(final ISolicitudService solicitudService) {
    this.solicitudService = solicitudService;
  }

  public void setSolicitudTipoCriterioService(final ISolicitudTipoCriterioService solicitudTipoCriterioService) {
    this.solicitudTipoCriterioService = solicitudTipoCriterioService;
  }

  /**
   * @param valoracionSolicitudDTO
   *          the valoracionSolicitudDTO to set
   */
  public void setValoracionSolicitudDTO(final ValoracionSolicitudDTO valoracionSolicitudDTO) {
    this.valoracionSolicitudDTO = valoracionSolicitudDTO;
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

  public boolean isEsCoordinador() {
    return esCoordinador;
  }

  public void setEsCoordinador(final boolean esCoordinador) {
    this.esCoordinador = esCoordinador;
  }

  /**
   * Establece el valor de la propiedad esExterno
   *
   * @param esExterno
   *          el esExterno para establecer
   */
  public void setEsExterno(final boolean esExterno) {
    this.esExterno = esExterno;
  }

  /**
   * Obtiene la propiedad esExterno
   *
   * @return el esExterno
   */
  public boolean isEsExterno() {
    return esExterno;
  }

  public void setDocumentoExpedienteService(IDocumentoExpedienteService documentoExpedienteService) {
    this.documentoExpedienteService = documentoExpedienteService;
  }

}

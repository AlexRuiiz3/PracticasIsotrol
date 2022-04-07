package es.juntadeandalucia.aacid.otrasopciones.action;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.AgrupacionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.DatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.EntidadSolicitanteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ExpeComuInicioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.IFestivosAACIDService;
import es.juntadeandalucia.aacid.comuntramitacion.service.IParametrosConfiguracionService;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesFecha;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.otrasopciones.service.IPermisosService;
import es.juntadeandalucia.aacid.otrasopciones.utils.UtilidadesTrewa;
import es.juntadeandalucia.aacid.otrasopciones.utils.constantes.Constantes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.ConContraparte;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.ConOtrasAportaciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Contrapartes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Contribuciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Subsanacion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeConContrapartes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeConOtrasAportaciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeContrapartes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeContribuciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeEntidadesParticipantes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeProgramacion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeSolicitudes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IAgrupacionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IContraparteService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IExclusionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IHistoricoService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeAgrupacionesService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeSolicitudService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISubsanacionService;
import es.juntadeandalucia.plataforma.comunes.excepciones.BusinessException;
import es.juntadeandalucia.plataforma.service.expediente.IExpediente;
import es.juntadeandalucia.plataforma.service.usuarios.IUsuario;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;
import trewa.bd.trapi.trapiui.tpo.TrDatosContacto;
import trewa.bd.trapi.trapiui.tpo.TrEmpleado;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;
import trewa.bd.trapi.trapiui.tpo.TrFaseExpediente;
import trewa.bd.trapi.trapiui.tpo.TrInteresadoExpediente;
import trewa.bd.trapi.trapiui.tpo.TrUsuarioAsignado;

/**
 * Action para el control de la pestaña de Datos Generales
 *
 * @author Isotrol
 */
public class DatosGeneralesAction extends AbstractOtrasOpcionesAction {

  /** Serial id */
  private static final long serialVersionUID = -4362391498621676132L;
  /** Interfaz api de trewa */
  private transient ITrewaService trewaService;
  /** Interfaz de permisos */
  private transient IPermisosService permisosService;
  /** Interfaz de la solicitud */
  private transient ISolicitudService solicitudService;
  /** Interfaz de la subsanacion */
  private transient ISubsanacionService subsanacionService;
  /** Interfaz para el guardado de historico */
  private transient IHistoricoService historicoService;
  /** Interfaz para la obtencion de solicitudes del sistema antiguo */
  private transient IPaeSolicitudService paeSolicitudService;
  /** Interfaz para la obtencion de solicitudes del sistema antiguo */
  private transient IPaeAgrupacionesService paeAgrupacionesService;
  /** Interfaz para la obtencion de agrupaciones */
  private transient IAgrupacionService agrupacionService;
  /** Interfaz para la obtencion de agrupaciones */
  private transient IContraparteService contraparteService;
  /** exclusionService */
  private transient IExclusionService exclusionService;
  private transient IParametrosConfiguracionService parametrosConfiguracionService;
  private transient IFestivosAACIDService festivosAACIDService;

  private DatosGeneralesDTO datosGeneralesDTO;
  private transient List<ExpeComuInicioDTO> listadoExpedientesComuInicio;
  private boolean mostrarTabComuInicio = false;

  /**
   * Obtenemos los datos Generales para pintarlos en la pestaña correspondiente
   *
   * @return
   */
  public String inicio() {
    datosGeneralesDTO = new DatosGeneralesDTO();
    final UsuarioWeb usuario = getUsuarioSesion();
    final String iExp = usuario.getExpediente().getRefExpediente();
    try {
      final TrFaseExpediente faseExpediente = trewaService.obtenerFaseExpedienteByIdExp(getUsuarioSesion().getExpediente().getRefExpediente());
      setMostrarTabComuInicio(StringUtils.equalsIgnoreCase(faseExpediente.getFASE().getDESCRIPCION(), ConstantesTramitacion.FASE_COMUNICACION_INICIO));

      final TrExpediente trExpediente = trewaService.obtenerExpedienteTrewa(iExp);
      final SolicitudDatosGeneralesDTO solicitudDatosGeneralesDTO = solicitudService.datosGeneralesSolicitudByIdExpTrewa(iExp);
      // Obtenemos tambien la solicitud del sistema antiguo a partir del expediente
      if (solicitudDatosGeneralesDTO != null) {
        final PaeSolicitudes paeSolicitud = paeSolicitudService.obtenerSolicitudExpByCodigo(solicitudDatosGeneralesDTO.getTxCodidentificativo());
        /**
         * DATOS GENERALES
         */
        if (solicitudDatosGeneralesDTO != null) {
          datosGeneralesDTO.setIdSolicitud(solicitudDatosGeneralesDTO.getIdSolicitud());
          /** Numero expediente */
          datosGeneralesDTO.setNumExp(trExpediente.getNUMEXP());
          datosGeneralesDTO.setNumExpInterno(UtilidadesNumero.convertLongToString(solicitudDatosGeneralesDTO.getNuNumInterno()));
          /** Persona responsable */
          final TrUsuarioAsignado[] usuariosAsignados = trewaService.obtenerUsuarioAsignadoExpediente(iExp);
          if (!ArrayUtils.isEmpty(usuariosAsignados)) {
            datosGeneralesDTO.setPersRespon(UtilidadesTrewa.obtenerNombreApellidoTrUsuarioAsignado(usuariosAsignados[0]));
            /** Unidad Responsable */
            final TrEmpleado[] empleados = trewaService.obtenerEmpleados(usuariosAsignados[0]);
            if (!ArrayUtils.isEmpty(empleados)) {
              datosGeneralesDTO.setUnidRespon(empleados[0].getORGANISMO().getNOMBRE());
            }
          }
          if (solicitudDatosGeneralesDTO.getFhPresentacion() == null) {
            datosGeneralesDTO.setFechaEntrada(trExpediente.getFECHA_REGISTRO());
          } else {
            datosGeneralesDTO.setFechaEntrada(solicitudDatosGeneralesDTO.getFhPresentacion());
          }
          if (datosGeneralesDTO.getFechaEntrada() == null) {
            datosGeneralesDTO.setFechaEntrada(trExpediente.getFECHAALTA());
          }

          final boolean tieneSubsanaciones = subsanacionService.existeCatalogoSubsanacion(solicitudDatosGeneralesDTO.getIdSolicitud());
          if (tieneSubsanaciones) {
            datosGeneralesDTO.setTieneSub(Constantes.SI);
          } else {
            datosGeneralesDTO.setTieneSub(Constantes.NO);
          }
          datosGeneralesDTO.setFechaPagoSubvencion(solicitudDatosGeneralesDTO.getFhPagoSubvencion());
          datosGeneralesDTO.setFechaRegistro(solicitudDatosGeneralesDTO.getFhRegistro());
          datosGeneralesDTO.setLugarRegistro(solicitudDatosGeneralesDTO.getLugarRegistro());
          datosGeneralesDTO.setFechaPostegracionComunInicio(solicitudDatosGeneralesDTO.getFechaPostegracionComunInicio());

          // VALORACIÓN
          if (StringUtils.equalsIgnoreCase(faseExpediente.getFASE().getDESCRIPCION(), ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS_SUBSANACION)
              || StringUtils.equalsIgnoreCase(faseExpediente.getFASE().getDESCRIPCION(), ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS_SUBSANACION_VUELTA)
              || StringUtils.equalsIgnoreCase(faseExpediente.getFASE().getDESCRIPCION(), ConstantesTramitacion.FASE_COMPROBACION_REQUISITOS_ALEGACION)) {
            datosGeneralesDTO.setPasaAValoracion(pasaAValoracion(faseExpediente));
          }
          // REFORMULACIÓN
          datosGeneralesDTO.setPresupFormu(tienePresupuestoReformulado());

          // VISTOS BUENOS
          obtenerVistosBuenos(usuario, trExpediente, solicitudDatosGeneralesDTO);

          /**
           * PROYECTO
           */
          datosGeneralesDTO.setTituloProy(solicitudDatosGeneralesDTO.getTitulo());
          datosGeneralesDTO.setPais(solicitudDatosGeneralesDTO.getPais());

          if (solicitudDatosGeneralesDTO.getPlazo() != null) {
            datosGeneralesDTO.setPlazoEjec("" + solicitudDatosGeneralesDTO.getPlazo());
          } else if (paeSolicitud != null && paeSolicitud.getPlazo() != null) {
            datosGeneralesDTO.setPlazoEjec("" + paeSolicitud.getPlazo());
          }

          datosGeneralesDTO.setFechaInicio(solicitudDatosGeneralesDTO.getFechaInicio());
          datosGeneralesDTO.setDescripcionProy(solicitudDatosGeneralesDTO.getDescripcion());
          datosGeneralesDTO.setLocalidadEjec(solicitudDatosGeneralesDTO.getMunicipio());

          // Finalidades
          datosGeneralesDTO.setAccionHuman(StringUtils.equals(ConstantesTramitacion.COD_ACCION_HUMA, solicitudDatosGeneralesDTO.getFinalidad()));
          datosGeneralesDTO.setCooperacion(StringUtils.equals(ConstantesTramitacion.COD_COOPERACION, solicitudDatosGeneralesDTO.getFinalidad()));
          datosGeneralesDTO.setEducacion(StringUtils.equals(ConstantesTramitacion.COD_EDUCACION, solicitudDatosGeneralesDTO.getFinalidad()));
          datosGeneralesDTO.setFormacion(StringUtils.equals(ConstantesTramitacion.COD_FORMACION, solicitudDatosGeneralesDTO.getFinalidad()));

          // Provincias, Africa, servicios, riesos, no especifica y otros
          final Subsanacion subsanacion = subsanacionService.obtenerSubsanacionByNumExpediente(Long.valueOf(iExp));
          provinciasOtrosChecks(subsanacion);

          /**
           * Entidad Solicitante
           *
           */
          datosGeneralesDTO.setEntidadSolicitante(new EntidadSolicitanteDTO());
          final TrInteresadoExpediente interesado = trewaService.obtenerInteresadoExpediente(iExp);
          if (interesado != null) {
            datosGeneralesDTO.getEntidadSolicitante().setNombreEntidad(interesado.getINTERESADO().getNOMBRE());
            datosGeneralesDTO.getEntidadSolicitante().setCif(interesado.getINTERESADO().getNUMIDENT());
            final TrDatosContacto datosContacto = trewaService.obtenerDatosContactoSolicitante(iExp);
            datosGeneralesDTO.getEntidadSolicitante().setEmail(datosContacto.getEMAIL());
            datosGeneralesDTO.getEntidadSolicitante().setDireccion(UtilidadesTrewa.construirDireccion(datosContacto));
            datosGeneralesDTO.getEntidadSolicitante().setCodPostal("" + (datosContacto.getCODPOSTAL() != null ? datosContacto.getCODPOSTAL() : ""));
            datosGeneralesDTO.getEntidadSolicitante().setLocalidad(datosContacto.getMUNICIPIO().getNOMBRE());
            datosGeneralesDTO.getEntidadSolicitante().setProvincia(datosContacto.getMUNICIPIO().getPROVINCIA().getNOMBRE());
            if (StringUtils.isNotBlank(solicitudDatosGeneralesDTO.getNifResp())
                && StringUtils.equals(solicitudDatosGeneralesDTO.getNifResp(), interesado.getINTERESADO().getNUMIDENT())) {
              datosGeneralesDTO.setOstentaRepresentacion(true);
            } else {
              datosGeneralesDTO.setOstentaRepresentacion(solicitudDatosGeneralesDTO.isLgRepresentacion());
            }
          } else {
            datosGeneralesDTO.setOstentaRepresentacion(solicitudDatosGeneralesDTO.isLgRepresentacion());
          }
          /**
           * Agrupaciones
           */

          datosGeneralesDTO.setApellidosSol(solicitudDatosGeneralesDTO.getApellidoResp());
          datosGeneralesDTO.setNifSol(solicitudDatosGeneralesDTO.getNifResp());
          datosGeneralesDTO.setNombreSol(solicitudDatosGeneralesDTO.getNombreResp());
          datosGeneralesDTO.setNumOV(solicitudDatosGeneralesDTO.getTxCodidentificativo());
          compruebaPermisosUsuario(usuario);
          /* Funcion para setear el importe total de la solicitud */
          if (paeSolicitud != null && paeSolicitud.getPaePais() != null) {
            establecerContribuciones(paeSolicitud, solicitudDatosGeneralesDTO);
            datosGeneralesDTO.setPais(paeSolicitud.getPaePais().getNombre());
          }
        }
      }
    } catch (final Exception e) {
      final String mensajeError = "Se ha produido un error al recuperar los datos de la pestaña de datos generales";
      addActionError(mensajeError);
      log.error(mensajeError, e);
      return ERROR;
    }
    addActionMessage(getText("exito.guardar"));
    return SUCCESS;
  }

  private String tienePresupuestoReformulado() {
    try {
      if (ArrayUtils.isEmpty(trewaService.documentosDelExpediente(Long.valueOf(getUsuarioSesion().getExpediente().getRefExpediente()),
          ConstantesTramitacion.ETIQUETA_PRESUPUESTO_REFORMULADO))) {
        return Constantes.NO;
      }
    } catch (NumberFormatException | TramitacionException e) {
      LOG.error("No ha sido posible comprobar si existía documento de presupuesto reformulado");
    }
    return Constantes.SI;
  }

  private String pasaAValoracion(final TrFaseExpediente faseExpediente) {
    try {
      final String strExcepcionesExcl = exclusionService.obtenerExcepcionesExclusiones(ConstantesTramitacion.EXCEPCIONES_EXCLUSIONES_TIPO_2,
          ConstantesTramitacion.FASE_FIN_EXCLUSIONES_TIPO_2, faseExpediente);
      final boolean tieneExclusionesTipo1 = exclusionService.existenExclusionesSolicitudByTipo(faseExpediente.getREFEXPEDIENTE().toString(), 1,
          StringUtils.EMPTY);
      final boolean tieneExclusionesTipo2 = exclusionService.existenExclusionesSolicitudByTipo(faseExpediente.getREFEXPEDIENTE().toString(), 2,
          strExcepcionesExcl);

      if (!tieneExclusionesTipo1 && !tieneExclusionesTipo2) {
        return Constantes.SI;
      }
    } catch (final TramitacionException e) {
      LOG.error("Error al obtener los datos de la solicitud/exclusion.", e);
      return "";
    }
    return Constantes.NO;
  }

  private Contribuciones parsePaeContribucionesToContribuciones(final PaeContribuciones paeContribuciones) {
    final Contribuciones contribuciones = new Contribuciones();
    contribuciones.setAacid(paeContribuciones.getAacid());
    contribuciones.setContraparte(paeContribuciones.getContraparte());
    contribuciones.setTotal(paeContribuciones.getTotal());
    contribuciones.setListaOtrasAportaciones(new ArrayList<>());
    contribuciones.setSolicitante(paeContribuciones.getSolicitante());
    contribuciones.setAacidEmergencia(paeContribuciones.getAacidEmergencia());
    contribuciones.setAacidNoValidada(paeContribuciones.getAacidNoValidada());
    if (!paeContribuciones.getListaOtrasAportaciones().isEmpty()) {
      final Iterator<PaeConOtrasAportaciones> it = paeContribuciones.getListaOtrasAportaciones().iterator();
      while (it.hasNext()) {
        contribuciones.getListaOtrasAportaciones().add(parsePaeOtrasAportacionesToOtrasAportaciones(it.next()));
      }
    }
    return contribuciones;
  }

  private ConOtrasAportaciones parsePaeOtrasAportacionesToOtrasAportaciones(final PaeConOtrasAportaciones paeOtrasAportaciones) {
    final ConOtrasAportaciones conOtrasAport = new ConOtrasAportaciones();
    conOtrasAport.setContribucion(paeOtrasAportaciones.getContribucion());
    conOtrasAport.setContribucionNoValidada(paeOtrasAportaciones.getContribucionNoValidada());
    conOtrasAport.setContribucionValidada(paeOtrasAportaciones.getContribucionValidada());
    conOtrasAport.setOtrasEmergencia(paeOtrasAportaciones.getOtrasEmergencia());
    return conOtrasAport;
  }

  /**
   * @param subsanacion
   */
  private void provinciasOtrosChecks(final Subsanacion subsanacion) {
    if (subsanacion != null && subsanacion.getSubNuId() != null) {
      checksProvinciasYOtros(subsanacion);

      datosGeneralesDTO.setFechaEnvSub(subsanacion.getSubFhSubsanacion() == null ? null : subsanacion.getSubFhSubsanacion());
      datosGeneralesDTO.setFechaLimite(subsanacion.getSubFhLimite() != null ? subsanacion.getSubFhLimite() : null);
      datosGeneralesDTO.setIdSubsanacion(subsanacion.getSubNuId());
      datosGeneralesDTO.setObservaciones(subsanacion.getSubLiObservaciones());
      datosGeneralesDTO.setMotivoDesestimacion(subsanacion.getSubLiMotivoDesestimacion());

    } else {
      datosGeneralesDTO.setAlmeria(false);
      datosGeneralesDTO.setCadiz(false);
      datosGeneralesDTO.setCordoba(false);
      datosGeneralesDTO.setHuelva(false);
      datosGeneralesDTO.setGranada(false);
      datosGeneralesDTO.setSevilla(false);
      datosGeneralesDTO.setJaen(false);
      datosGeneralesDTO.setMalaga(false);
      datosGeneralesDTO.setPrevRiesgos(false);
      datosGeneralesDTO.setAfrica(false);
      datosGeneralesDTO.setServSoc(false);
      datosGeneralesDTO.setOtros(false);
      datosGeneralesDTO.setNoEspec(false);
    }
  }

  /**
   * @param subsanacion
   */
  private void checksProvinciasYOtros(final Subsanacion subsanacion) {
    if (datosGeneralesDTO.isFormacion() || datosGeneralesDTO.isEducacion()) {
      checksFormacionYEducacion(subsanacion);
    } else if (datosGeneralesDTO.isAccionHuman()) {
      datosGeneralesDTO.setPrevRiesgos(BooleanUtils.isTrue(subsanacion.getSubLgPrevRiesgos()));
    } else if (datosGeneralesDTO.isCooperacion()) {
      datosGeneralesDTO.setAfrica(BooleanUtils.isTrue(subsanacion.getSubLgAfrica()));
      datosGeneralesDTO.setServSoc(BooleanUtils.isTrue(subsanacion.getSubLgServicios()));
      datosGeneralesDTO.setOtros(BooleanUtils.isTrue(subsanacion.getSubLgOtros()));
      datosGeneralesDTO.setNoEspec(BooleanUtils.isTrue(subsanacion.getNoEspecifica()));
    }
  }

  /**
   * @param subsanacion
   */
  private void checksFormacionYEducacion(final Subsanacion subsanacion) {
    datosGeneralesDTO.setAlmeria(BooleanUtils.isTrue(subsanacion.getSubLgAlmeria()));
    datosGeneralesDTO.setCadiz(BooleanUtils.isTrue(subsanacion.getSubLgCadiz()));
    datosGeneralesDTO.setCordoba(BooleanUtils.isTrue(subsanacion.getSubLgCordoba()));
    datosGeneralesDTO.setGranada(BooleanUtils.isTrue(subsanacion.getSubLgGranada()));
    datosGeneralesDTO.setHuelva(BooleanUtils.isTrue(subsanacion.getSubLgHuelva()));
    datosGeneralesDTO.setJaen(BooleanUtils.isTrue(subsanacion.getSubLgJaen()));
    datosGeneralesDTO.setMalaga(BooleanUtils.isTrue(subsanacion.getSubLgMalaga()));
    datosGeneralesDTO.setSevilla(BooleanUtils.isTrue(subsanacion.getSubLgSevilla()));
  }

  /**
   * @param usuario
   * @param trExpediente
   * @param solicitudDatosGeneralesDTO
   */
  protected void obtenerVistosBuenos(final UsuarioWeb usuario, final TrExpediente trExpediente, final SolicitudDatosGeneralesDTO solicitudDatosGeneralesDTO) {
    // Visto Bueno Técnico
    datosGeneralesDTO.setVbCoord(solicitudDatosGeneralesDTO.isVbCoordinador());
    datosGeneralesDTO.setEditarVBTecnico(permisosService.tienePerfil(usuario.getListaPerfiles(), ConstantesTramitacion.getPerfilesVBTecnico()));
    // Check VBTécnico
    datosGeneralesDTO.setVbTecnico(solicitudDatosGeneralesDTO.isVbTecnico());
    // Check VBCoordinador
    if (!datosGeneralesDTO.isVbTecnico()) {
      datosGeneralesDTO.setEditarVBCoordinador(false);
    } else if (permisosService.tienePerfil(usuario.getListaPerfiles(), ConstantesTramitacion.PERFIL_COORDINADOR)) {
      datosGeneralesDTO.setEditarVBTecnico(true);
      datosGeneralesDTO.setEditarVBCoordinador(true);
    } else {
      datosGeneralesDTO.setEditarVBCoordinador(false);
    }
    // Campo Resumen
    obtenerCampoResumen();
  }

  /**
   *
   */
  private void obtenerCampoResumen() {
    if (datosGeneralesDTO.isVbTecnico()) {
      datosGeneralesDTO.setResumen(getText("label.resumen.tecnico.ok"));

      if (datosGeneralesDTO.isVbCoord()) {
        datosGeneralesDTO.setResumen(getText("label.resumen.coord.ok"));
      } else {
        datosGeneralesDTO.setResumen(getText("label.resumen.coord.nok"));
      }

      if (StringUtils.equals(datosGeneralesDTO.getTieneSub(), Constantes.SI)) {
        datosGeneralesDTO.setResumen(getText("label.resumen.tiene.subsanacion"));
      } else if (datosGeneralesDTO.isTieneExcl()) {
        datosGeneralesDTO.setResumen(getText("label.resumen.tiene.exclusion"));
      } else {
        datosGeneralesDTO.setResumen(getText("label.resumen.no.tiene.subsanacion.exclusion"));
      }
    } else {
      datosGeneralesDTO.setResumen(getText("label.resumen.tecnico.nok"));
    }
  }

  /**
   * Cargamos la lista de agrupaciones
   *
   * @return
   */
  public String obtenerAgrupaciones() {
    datosGeneralesDTO = new DatosGeneralesDTO();
    datosGeneralesDTO.setListaAgrupacion(new ArrayList<>());
    try {
      final UsuarioWeb usuario = getUsuarioSesion();
      final String iExp = usuario.getExpediente().getRefExpediente();
      final SolicitudDatosGeneralesDTO solicitudDatosGeneralesDTO = solicitudService.datosGeneralesSolicitudByIdExpTrewa(iExp);

      if (solicitudDatosGeneralesDTO != null) {
        datosGeneralesDTO.setListaAgrupacion(new ArrayList<>());

        final PaeSolicitudes paeSolicitudes = paeSolicitudService.obtenerSolicitudExpByCodigo(solicitudDatosGeneralesDTO.getTxCodidentificativo());
        // OBTENEMOS EL IMPORTE TOTAL PARA CREAR EL PORCENTAJE DE PARTICIPACION
        if (paeSolicitudes != null) {
          establecerContribuciones(paeSolicitudes, solicitudDatosGeneralesDTO);
          final BigDecimal impEntidadSolicitante = BigDecimal.valueOf(datosGeneralesDTO.getImpoEntidadLider()).setScale(2, RoundingMode.HALF_DOWN);
          final List<PaeEntidadesParticipantes> entidadesParticipantes = obtenerEntidadesAgrupaciones(paeSolicitudes, impEntidadSolicitante);
          final Iterator<PaeEntidadesParticipantes> it1 = entidadesParticipantes.iterator();
          while (it1.hasNext()) {
            final PaeEntidadesParticipantes paeEntidadParticipante = it1.next();
            final AgrupacionDTO agrupacionDTO = new AgrupacionDTO();
            agrupacionDTO.setEntParticipante(paeEntidadParticipante.getEntParticipante());

            agrupacionDTO.setParticipacion(String.valueOf(paeEntidadParticipante.getTotal()) + "%");

            datosGeneralesDTO.getListaAgrupacion().add(agrupacionDTO);
          }

        }
      }
    } catch (final Exception e) {
      log.error(e.getMessage(), e);
    }
    return SUCCESS;
  }

  private List<PaeEntidadesParticipantes> obtenerEntidadesAgrupaciones(final PaeSolicitudes paeSolicitud, final BigDecimal impEntidadSolicitante) {
    final UsuarioWeb usuario = getUsuarioSesion();
    final String iExp = usuario.getExpediente().getRefExpediente();
    final List<PaeEntidadesParticipantes> entidadesParticipantes = new ArrayList<>();

    try {
      // Se obtiene la entidad lider
      estableceEntidadLider(entidadesParticipantes, impEntidadSolicitante, iExp);
      // Se obtienen las entidades participantes
      final Map<PaeEntidadesParticipantes, Double> mapaEntidadesParticipantes = obtieneMapaEntidadesParticipantes(paeSolicitud);
      estableceEntidadesParticipantes(mapaEntidadesParticipantes, entidadesParticipantes, impEntidadSolicitante);

    } catch (final TramitacionException e) {
      log.error(e.getMessage(), e);
    }
    return entidadesParticipantes;
  }

  private void estableceEntidadLider(final List<PaeEntidadesParticipantes> entidadesParticipantes, final BigDecimal impEntidadSolicitante, final String iExp)
      throws TramitacionException {
    final TrInteresadoExpediente interesadoExpediente = trewaService.obtenerInteresadoExpediente(iExp);

    final PaeEntidadesParticipantes entLider = new PaeEntidadesParticipantes();

    // Metemos la entidad lider en el listado de entidades agrupadas
    if (interesadoExpediente != null) {
      entLider
          .setEntParticipante("(Ent Lider) " + interesadoExpediente.getINTERESADO().getNOMBRE() + " " + interesadoExpediente.getINTERESADO().getAPELLIDO1());
    } else {
      entLider.setEntParticipante("Entidad Lider");
    }
    BigDecimal porcEntL = BigDecimal.valueOf(impEntidadSolicitante.doubleValue()).setScale(2, RoundingMode.HALF_DOWN);
    porcEntL = porcEntL.multiply(BigDecimal.valueOf(100.00).setScale(2, RoundingMode.HALF_DOWN)).setScale(2, RoundingMode.HALF_DOWN);
    if (datosGeneralesDTO.getImpoTotal() != null && datosGeneralesDTO.getImpoTotal().doubleValue() != 0.0) {
      porcEntL = porcEntL.divide(BigDecimal.valueOf(datosGeneralesDTO.getImpoTotal()), 2, RoundingMode.HALF_DOWN);
    } else {
      porcEntL = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);
    }
    entLider.setTotal(porcEntL.doubleValue());
    entidadesParticipantes.add(entLider);
  }

  private void estableceEntidadesParticipantes(final Map<PaeEntidadesParticipantes, Double> mapaEntidadesParticipantes,
      final List<PaeEntidadesParticipantes> entidadesParticipantes, final BigDecimal importeEntL) {
    final Iterator iteratorEntidadesParticipantes = mapaEntidadesParticipantes.entrySet().iterator();
    while (iteratorEntidadesParticipantes.hasNext()) {
      final Map.Entry e = (Map.Entry) iteratorEntidadesParticipantes.next();
      final PaeEntidadesParticipantes key = (PaeEntidadesParticipantes) e.getKey();
      final Double valor = (Double) e.getValue();
      BigDecimal porcentaje = null;
      if (valor != null) {
        BigDecimal valor1 = BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_DOWN);
        valor1 = valor1.multiply(BigDecimal.valueOf(100.00).setScale(2, RoundingMode.HALF_DOWN)).setScale(2, RoundingMode.HALF_DOWN);
        if (datosGeneralesDTO.getImpoTotal().doubleValue() != 0.0) {
          porcentaje = valor1.divide(BigDecimal.valueOf(datosGeneralesDTO.getImpoTotal()), 2, RoundingMode.HALF_DOWN);
        } else {
          porcentaje = BigDecimal.valueOf(importeEntL.doubleValue()).setScale(2, RoundingMode.HALF_DOWN);
        }
      } else {
        porcentaje = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);
      }
      key.setTotal(porcentaje.doubleValue());
      entidadesParticipantes.add(key);
    }
  }

  private Map<PaeEntidadesParticipantes, Double> obtieneMapaEntidadesParticipantes(final PaeSolicitudes paeSolicitud) {
    final Map<PaeEntidadesParticipantes, Double> mapaEntidadesParticipantes = new HashMap<>();

    final List<PaeContribuciones> paeContribuciones = paeSolicitud.getPaeContribucionesByIdSolicitud();
    if (paeContribuciones != null) {
      for (final PaeContribuciones contribucion : paeContribuciones) {
        final List<PaeConOtrasAportaciones> listaOtrasAportaciones = contribucion.getListaOtrasAportaciones();
        for (final PaeConOtrasAportaciones otrasAportaciones : listaOtrasAportaciones) {
          anyadeAportacionEntidadMapa(otrasAportaciones, mapaEntidadesParticipantes);
        }
      }
    }
    return mapaEntidadesParticipantes;
  }

  public void anyadeAportacionEntidadMapa(final PaeConOtrasAportaciones otrasAportaciones,
      final Map<PaeEntidadesParticipantes, Double> mapaEntidadesParticipantes) {
    if (Constantes.PERTENECE_AGRUPACION.equals(otrasAportaciones.getPaeEntidadesParticipantes().getPaeFunciones().getFuncion())) {
      if (mapaEntidadesParticipantes.containsKey(otrasAportaciones.getPaeEntidadesParticipantes())) {
        final Double p = mapaEntidadesParticipantes.get(otrasAportaciones.getPaeEntidadesParticipantes());
        BigDecimal p1 = BigDecimal.valueOf(p).setScale(2, RoundingMode.HALF_DOWN);
        final BigDecimal p2 = otrasAportaciones.getContribucion().setScale(2, RoundingMode.HALF_DOWN);
        p1 = p1.add(p2);
        mapaEntidadesParticipantes.put(otrasAportaciones.getPaeEntidadesParticipantes(), p1.doubleValue());
      } else {
        final Double d = otrasAportaciones.getContribucion().doubleValue();
        mapaEntidadesParticipantes.put(otrasAportaciones.getPaeEntidadesParticipantes(), d);
      }
    }
  }

  /**
   * Guardado de la pestanya de datos generales
   *
   */
  public String guardarDatosGenerales() {
    final Collection<ConstraintViolation<Object>> constraintViolations = validDatosGenerales();
    datosGeneralesDTO = (DatosGeneralesDTO) request.getAttribute("datosGeneralesDTO");

    if (datosGeneralesDTO != null) {
      try {
        if (Boolean.FALSE.equals(constraintViolations.isEmpty())) {
          // Si contiene violaciones volvemos a la pantalla
          processConstraintViolations(constraintViolations);
          addActionError(getText("error.guardar"));
          getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
          return ERROR;
        } else {
          solicitudService.guardarSolicitudOtrosDatos(datosGeneralesDTO, getUsuarioSesion().getExpediente().getRefExpediente());
          historicoService.guardarHistorico(getUsuarioSesion().getExpediente().getNumeroExpediente(), getUsuarioSesion().getNombreUsuario());
          addActionMessage(getText("exito.guardar"));
          return SUCCESS;
        }

      } catch (final TramitacionException e) {
        mensajeError = getText("error.guardar");
        log.error(mensajeError, e);
        addActionError(mensajeError);
        getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return ERROR;
      }
    } else {
      mensajeError = "No se ha recibido datos para guardar";
      log.error(mensajeError);
      addActionError(mensajeError);
      getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
      return ERROR;
    }

  }

  @Override
  public void processConstraintViolations(final Collection<ConstraintViolation<Object>> constraintViolations) {
    for (final ConstraintViolation<Object> violation : constraintViolations) {
      StringBuilder errorMessage;
      // Si el campo es un objeto de una lista
      errorMessage = new StringBuilder(getText("label." + violation.getPropertyPath().toString()));

      errorMessage.append(": ");
      errorMessage.append(violation.getMessage());

      LOG.debug(errorMessage.toString());

      addActionError(errorMessage.toString());
    }
  }

  private void compruebaPermisosUsuario(final UsuarioWeb usuario) {
    datosGeneralesDTO.setTienePermisosGuardado(false);
    try {
      final IUsuario iUsuario = usuario.getUsuario();
      final List<Perfil> perfiles = trewaService.obtenerPerfilesUsuario(iUsuario.getCodUsuario());
      for (final Perfil perfil : perfiles) {
        if (perfil.getNombre().equalsIgnoreCase(Constantes.PERFIL_COORDINADOR) || perfil.getNombre().equalsIgnoreCase(Constantes.PERFIL_DGA)) {
          datosGeneralesDTO.setTienePermisosGuardado(true);
        }
      }
    } catch (final BusinessException e) {
      log.error(e.getMessage(), e);
    }
  }

  private void establecerContribuciones(final PaeSolicitudes paeSolicitudes, final SolicitudDatosGeneralesDTO solicitudDatosGeneralesDTO)
      throws TramitacionException {
    final List<Contribuciones> contribuciones = new ArrayList<>();
    estableceContribucionesPorContrapartes(paeSolicitudes, solicitudDatosGeneralesDTO, contribuciones);
    estableceTiposContribucionPorTipoSolicitudYIntervencion(contribuciones, paeSolicitudes);
    establecerImportes(contribuciones);
  }

  private void estableceContribucionesPorContrapartes(final PaeSolicitudes paeSolicitudes, final SolicitudDatosGeneralesDTO solicitudDatosGeneralesDTO,
      final List<Contribuciones> contribuciones) throws TramitacionException {
    if (contraparteService != null) {
      final List<Contrapartes> contrapartes = contraparteService.obtenerContrapartesBySolicitud(solicitudDatosGeneralesDTO.getIdSolicitud());

      if (!CollectionUtils.isEmpty(contrapartes)) {
        final Iterator<Contrapartes> it = contrapartes.iterator();
        while (it.hasNext()) {
          final Contrapartes contraparte = it.next();
          anyadeContribucionesFromContraparte(contraparte, contribuciones);
        }
      } else if (paeSolicitudes != null && paeSolicitudes.getPaeContrapartesByIdSolicitud() != null) {
        final Iterator<PaeContrapartes> it = paeSolicitudes.getPaeContrapartesByIdSolicitud().iterator();
        while (it.hasNext()) {
          final PaeContrapartes paeContraparte = it.next();
          anyadeContribucionesFromContraparte(paeContraparte, contribuciones);
        }
      }
    }
  }

  private void estableceTiposContribucionPorTipoSolicitudYIntervencion(final List<Contribuciones> contribuciones, final PaeSolicitudes paeSolicitudes) {
    if (paeSolicitudes.getPaeTipoSolicitud() != null && Constantes.PROGRAMA.equals(paeSolicitudes.getPaeTipoSolicitud().getDenominacion())) {

      if (paeSolicitudes.getPaeConvocatorias() != null && paeSolicitudes.getPaeConvocatorias().getTIntervencion() == 2011) {

        if (paeSolicitudes.getPaeProgramaciones() != null) {
          recorreProgramacionesSolicitud(contribuciones, paeSolicitudes);
        }
      } else {
        final List<PaeContribuciones> paeContribuciones = paeSolicitudes.getPaeContribucionesByIdSolicitud();
        addContribucionParseadaToList(paeContribuciones, contribuciones);
      }
    } else {
      final List<PaeContribuciones> paeContribuciones = paeSolicitudes.getPaeContribucionesByIdSolicitud();
      addContribucionParseadaToList(paeContribuciones, contribuciones);
    }
  }

  private void recorreProgramacionesSolicitud(final List<Contribuciones> contribuciones, final PaeSolicitudes paeSolicitudes) {
    long maxAnnio = 0;
    PaeProgramacion paeProgramacion = null;
    final Iterator<PaeProgramacion> iterator = paeSolicitudes.getPaeProgramaciones().iterator();
    while (iterator.hasNext()) {
      final PaeProgramacion paeProgramacionIt = iterator.next();
      if (paeProgramacionIt.getAnio() > maxAnnio) {
        maxAnnio = paeProgramacionIt.getAnio();
        paeProgramacion = paeProgramacionIt;
      }
    }
    if (paeProgramacion != null) {
      final List<PaeContribuciones> paeContribuciones = paeProgramacion.getPaeContribuciones();
      addContribucionParseadaToList(paeContribuciones, contribuciones);
    }

  }

  private void addContribucionParseadaToList(final List<PaeContribuciones> paeContribuciones, final List<Contribuciones> contribuciones) {
    if (paeContribuciones != null) {
      for (final PaeContribuciones paeContr : paeContribuciones) {
        contribuciones.add(parsePaeContribucionesToContribuciones(paeContr));
      }
    }
  }

  private void anyadeContribucionesFromContraparte(final Contrapartes contraparte, final List<Contribuciones> contribuciones) {
    final List<ConContraparte> conContrapartes = contraparte.getAaciConContrapartesByContIdContraparte();
    if (!CollectionUtils.isEmpty(conContrapartes)) {
      final Iterator<ConContraparte> it2 = conContrapartes.iterator();
      while (it2.hasNext()) {
        final ConContraparte conContraparte = it2.next();
        contribuciones.add(conContraparte.getAaciContribucionesByFkContribucion());
      }
    }
  }

  private void anyadeContribucionesFromContraparte(final PaeContrapartes paeContraparte, final List<Contribuciones> contribuciones) {
    final List<PaeConContrapartes> paeConContrapartes = paeContraparte.getPaeConContrapartesByIdContraparte();
    if (!CollectionUtils.isEmpty(paeConContrapartes)) {
      final Iterator<PaeConContrapartes> it2 = paeConContrapartes.iterator();
      while (it2.hasNext()) {
        final PaeConContrapartes paeConContraparte = it2.next();
        contribuciones.add(parsePaeContribucionesToContribuciones(paeConContraparte.getPaeContribucionesByFkContribucion()));
      }
    }
  }

  private void establecerImportes(final List<Contribuciones> contribuciones) {
    log.debug("Init obtenerImporteTotal");
    BigDecimal importeSol = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);
    BigDecimal importeEnt = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);
    BigDecimal importeTot = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN);
    final Iterator<Contribuciones> iterator = contribuciones.iterator();
    while (iterator.hasNext()) {
      final Contribuciones contribucion = iterator.next();
      if (contribucion != null) {

        if (contribucion.getAacid() != null) {
          final BigDecimal aacid = contribucion.getAacid().setScale(2, RoundingMode.HALF_DOWN);
          importeTot = importeTot.add(aacid);
          importeSol = importeSol.add(aacid);

        }
        if (contribucion.getSolicitante() != null) {
          final BigDecimal sol = contribucion.getSolicitante().setScale(2, RoundingMode.HALF_DOWN);
          importeEnt = importeEnt.add(sol);

          importeTot = importeTot.add(sol);
        }

        obtieneImporteOtrasAportaciones(importeTot, contribucion);
        obtieneImporteOtrasContrapartes(importeTot, contribucion);

      }
    }

    datosGeneralesDTO.setImpoSoli(importeSol.longValue());
    datosGeneralesDTO.setImpoEntidadLider(importeEnt.longValue());
    datosGeneralesDTO.setImpoTotal(importeTot.longValue());
    log.debug("Init obtenerImporteTotal");

  }

  private void obtieneImporteOtrasAportaciones(BigDecimal importeTot, final Contribuciones contribucion) {
    final List<ConOtrasAportaciones> listaOtrasAportaciones = contribucion.getListaOtrasAportaciones();
    if (listaOtrasAportaciones != null) {
      final Iterator<ConOtrasAportaciones> iterator2 = listaOtrasAportaciones.iterator();
      while (iterator2.hasNext()) {
        final ConOtrasAportaciones otrasAportaciones = iterator2.next();
        final BigDecimal otra = otrasAportaciones.getContribucion().setScale(2, RoundingMode.HALF_DOWN);
        importeTot = importeTot.add(otra);
      }
    }
  }

  private void obtieneImporteOtrasContrapartes(BigDecimal importeTot, final Contribuciones contribucion) {
    final Set<ConContraparte> listaOtrasContrapartes = contribucion.getAaciConContrapartesByIdContribucion();
    if (listaOtrasContrapartes != null) {
      final Iterator<ConContraparte> iterator3 = listaOtrasContrapartes.iterator();
      while (iterator3.hasNext()) {
        final ConContraparte otrasAportaciones = iterator3.next();
        final BigDecimal otra = otrasAportaciones.getContribucion().setScale(2, RoundingMode.HALF_DOWN);
        importeTot = importeTot.add(otra);

      }
    }
  }

  public String cargarTabComunicacionInicio() {
    try {
      listadoExpedientesComuInicio = new ArrayList<>();
      final IExpediente iExpediente = getUsuarioSesion().getExpediente();
      final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      final List<SolicitudDatosGeneralesDTO> listaDatosExpeHermanos = solicitudService.obtenerSolicitudesHermanas(iExpediente.getRefExpediente());

      final String strPlazoComunicacionInicio = parametrosConfiguracionService
          .obtenerParametroConfiguracion(ConstantesTramitacion.PLAZO_COMUNICACION_INICIO_MESES, iExpediente.getProcedimiento().getRefProcedimiento());

      final Calendar fechaExpediente = Calendar.getInstance();
      fechaExpediente.setTime(iExpediente.getFechaCreacion());

      final List<Date> diasFestivos = festivosAACIDService.obtenerfestivos(fechaExpediente.get(Calendar.YEAR), ConstantesTramitacion.FESTIVO_LOCAL);

      ExpeComuInicioDTO expeComuInicioDTO;
      for (final SolicitudDatosGeneralesDTO datos : listaDatosExpeHermanos) {
        expeComuInicioDTO = new ExpeComuInicioDTO();
        expeComuInicioDTO.setNumExpediente(datos.getNumExpediente());
        expeComuInicioDTO.setNumExpOV(datos.getTxCodidentificativo());
        if (datos.getFhPagoSubvencion() != null) {
          expeComuInicioDTO.setFechaPagoSubvencion(sdf.format(datos.getFhPagoSubvencion()));
        }
        if (datos.getFechaPostegracionComunInicio() != null) {
          expeComuInicioDTO.setFechaPostegracionComunInicio(sdf.format(datos.getFechaPostegracionComunInicio()));
        }
        calculoFechaFinComunicacionInicio(sdf, strPlazoComunicacionInicio, diasFestivos, expeComuInicioDTO, datos);

        listadoExpedientesComuInicio.add(expeComuInicioDTO);
      }
    } catch (final TramitacionException | BusinessException e) {
      final String mensajeError = "Se ha producido un error al recuperar las solicitudes hermanas";
      log.error(mensajeError, e);
      addActionError(mensajeError);
      return ERROR;
    }
    return SUCCESS;
  }

  /**
   * @param sdf
   * @param strPlazoComunicacionInicio
   * @param diasFestivos
   * @param expeComuInicioDTO
   * @param datos
   */
  private void calculoFechaFinComunicacionInicio(final SimpleDateFormat sdf, final String strPlazoComunicacionInicio, final List<Date> diasFestivos,
      final ExpeComuInicioDTO expeComuInicioDTO, final SolicitudDatosGeneralesDTO datos) {
    final Calendar calendar = Calendar.getInstance(new Locale("es", "ES"));
    final Date fechaHoy = calendar.getTime();
    Date fechaFinPlazo;
    if (datos.getFechaPostegracionComunInicio() != null) {
      expeComuInicioDTO.setFechaFinComuInicio(sdf.format(datos.getFechaPostegracionComunInicio()));
      expeComuInicioDTO
          .setDiasRestantes(String.valueOf(UtilidadesFecha.obtenerDiferenciaEnDiasHabiles(fechaHoy, datos.getFechaPostegracionComunInicio(), diasFestivos)));
    } else if (datos.getFhPagoSubvencion() != null && StringUtils.isNotBlank(strPlazoComunicacionInicio)) {
      fechaFinPlazo = UtilidadesFecha.sumarMesesFecha(datos.getFhPagoSubvencion(), UtilidadesNumero.parseStringToLong(strPlazoComunicacionInicio).intValue());
      expeComuInicioDTO.setFechaFinComuInicio(sdf.format(fechaFinPlazo));
      expeComuInicioDTO.setDiasRestantes(String.valueOf(UtilidadesFecha.obtenerDiferenciaEnDiasHabiles(fechaHoy, fechaFinPlazo, diasFestivos)));
    }
  }

  /**
   * Realiza las validaciones de los datos generales
   *
   * @return lista de errores
   */
  private Collection<ConstraintViolation<Object>> validDatosGenerales() {
    final List<Class<?>> validations = new ArrayList<>();

    return valid(datosGeneralesDTO, validations);
  }

  /**
   * Obtiene la propiedad datosGeneralesDTO
   *
   * @return el datosGeneralesDTO
   */
  public DatosGeneralesDTO getDatosGeneralesDTO() {
    return datosGeneralesDTO;
  }

  /**
   * Establece el valor de la propiedad datosGeneralesDTO
   *
   * @param datosGeneralesDTO
   *          el datosGeneralesDTO para establecer
   */
  public void setDatosGeneralesDTO(final DatosGeneralesDTO datosGeneralesDTO) {
    this.datosGeneralesDTO = datosGeneralesDTO;
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
   * Establece el valor de la propiedad permisosService
   *
   * @param permisosService
   *          el permisosService para establecer
   */
  public void setPermisosService(final IPermisosService permisosService) {
    this.permisosService = permisosService;
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
   * Establece el valor de la propiedad subsanacionService
   *
   * @param subsanacionService
   *          el subsanacionService para establecer
   */
  public void setSubsanacionService(final ISubsanacionService subsanacionService) {
    this.subsanacionService = subsanacionService;
  }

  /**
   * Establece el valor de la propiedad historicoService
   *
   * @param historicoService
   *          el historicoService para establecer
   */
  public void setHistoricoService(final IHistoricoService historicoService) {
    this.historicoService = historicoService;
  }

  public IPaeSolicitudService getPaeSolicitudService() {
    return paeSolicitudService;
  }

  public void setPaeSolicitudService(final IPaeSolicitudService paeSolicitudService) {
    this.paeSolicitudService = paeSolicitudService;
  }

  public IAgrupacionService getAgrupacionesService() {
    return agrupacionService;
  }

  public void setAgrupacionService(final IAgrupacionService agrupacionService) {
    this.agrupacionService = agrupacionService;
  }

  public IContraparteService getContraparteService() {
    return contraparteService;
  }

  public void setContraparteService(final IContraparteService contraparteService) {
    this.contraparteService = contraparteService;
  }

  public IPaeAgrupacionesService getPaeAgrupacionesService() {
    return paeAgrupacionesService;
  }

  public void setPaeAgrupacionesService(final IPaeAgrupacionesService paeAgrupacionesService) {
    this.paeAgrupacionesService = paeAgrupacionesService;
  }

  /**
   * Obtiene la propiedad mostrarTabComuInicio
   *
   * @return el mostrarTabComuInicio
   */
  public boolean isMostrarTabComuInicio() {
    return mostrarTabComuInicio;
  }

  /**
   * Establece el valor de la propiedad mostrarTabComuInicio
   *
   * @param mostrarTabComuInicio
   *          el mostrarTabComuInicio para establecer
   */
  public void setMostrarTabComuInicio(final boolean mostrarTabComuInicio) {
    this.mostrarTabComuInicio = mostrarTabComuInicio;
  }

  /**
   * Obtiene la propiedad listadoExpedientesComuInicio
   *
   * @return el listadoExpedientesComuInicio
   */
  public List<ExpeComuInicioDTO> getListadoExpedientesComuInicio() {
    return listadoExpedientesComuInicio;
  }

  /**
   * Establece el valor de la propiedad listadoExpedientesComuInicio
   *
   * @param listadoExpedientesComuInicio
   *          el listadoExpedientesComuInicio para establecer
   */
  public void setListadoExpedientesComuInicio(final List<ExpeComuInicioDTO> listadoExpedientesComuInicio) {
    this.listadoExpedientesComuInicio = listadoExpedientesComuInicio;
  }

  /**
   * Establece el valor de la propiedad exclusionService
   *
   * @param exclusionService
   *          el exclusionService para establecer
   */
  public void setExclusionService(final IExclusionService exclusionService) {
    this.exclusionService = exclusionService;
  }

  /**
   * Establece el valor de la propiedad parametrosConfiguracionService
   *
   * @param parametrosConfiguracionService
   *          el parametrosConfiguracionService para establecer
   */
  public void setParametrosConfiguracionService(final IParametrosConfiguracionService parametrosConfiguracionService) {
    this.parametrosConfiguracionService = parametrosConfiguracionService;
  }

  /**
   * Establece el valor de la propiedad festivosAACIDService
   *
   * @param festivosAACIDService
   *          el festivosAACIDService para establecer
   */
  public void setFestivosAACIDService(final IFestivosAACIDService festivosAACIDService) {
    this.festivosAACIDService = festivosAACIDService;
  }

}
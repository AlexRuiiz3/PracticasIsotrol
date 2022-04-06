package es.juntadeandalucia.aacid.tramitacionongd.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import es.juntadeandalucia.aacid.comuntramitacion.action.BaseTareasAction;
import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesMensajes;
import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ConvocatoriaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudConcesionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.TipoConvocatoriaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.aacid.comuntramitacion.service.IValidacionConvocatoriasService;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesBoolean;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Convocatoria;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TipoConvocatoria;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeMedidasPais;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeSolicitudes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IConvocatoriasService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeSolicitudService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudConcesionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudService;
import es.juntadeandalucia.plataforma.comunes.excepciones.BusinessException;
import es.juntadeandalucia.plataforma.service.expediente.IExpediente;
import trewa.bd.tpo.TpoPK;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;
import trewa.bd.trapi.trapiui.tpo.TrInteresadoExpediente;

/**
 * TareasConvocatoriaAction class.
 *
 * @author isotrol.
 *
 */
public class TareasConvocatoriaAction extends BaseTareasAction {

  private static final String LISTA_SOLICITUD_CONCESION_DTO = "listaSolicitudConcesionDTO[";
  /** serialVersionUID */
  private static final long serialVersionUID = 8991422891340786187L;
  /** LOGGER TareasConvocatoriaAction.class */
  private final transient Logger log = Logger.getLogger(TareasConvocatoriaAction.class);
  /** convocatoriasService */
  private transient IConvocatoriasService convocatoriasService;
  /** Interfaz api de trewa */
  private transient ITrewaService trewaService;
  /** validacionConvocatoriaService */
  private transient IValidacionConvocatoriasService validacionConvocatoriaService;
  /** solicitudService */
  private transient ISolicitudService solicitudService;
  /** paeSolicitudService */
  private transient IPaeSolicitudService paeSolicitudService;
  /** Servicio de solicitud concesión */
  private transient ISolicitudConcesionService solicitudConcesionService;
  /** abreviatura */
  private String abreviatura;
  /** ConvocatoriaDTO */
  private transient ConvocatoriaDTO convocatoriaDTO;
  /** listaExpedientesHijosConvocatoriaDTO */
  private transient List<SolicitudDTO> listaExpedientesHijosConvocatoriaDTO;
  private transient List<SolicitudConcesionDTO> listaSolicitudConcesionDTO;
  /** Finaliad de los expedientes hijos */
  private String finalidad;

  /**
   * Modificar convocatoria.
   *
   * @return success or error
   * @throws BusinessException
   * @throws Exception
   */
  public String modificarConvocatoria() throws BusinessException {

    try {
      // se obtiene el usuario de la sesion
      user = getUsuarioSesion();
      // se obtiene el id del expediente correspondiente a la convocatoria.
      final String idExp = user.getExpediente().getRefExpediente();
      // obtenemos la abreviatura correspondiente al procedimiento.
      abreviatura = user.getExpediente().getProcedimiento().getAbreviatura();
      // a partir del id buscamos si existe esa convocatoria en AACID.
      convocatoriaDTO = convocatoriasService.buscarConvocatoriaDTOPorIdExpediente(idExp);

      // si la convocatoria no existe en AACID, hay que buscarla en TREWA.
      if (null == convocatoriaDTO) {
        // se obtiene el expediente de Trewa a partir del numero de expediente de la
        // solicitud.
        final trewa.bd.trapi.trapiui.tpo.TrExpediente trExpediente = trewaService.obtenerExpedienteTrewa(idExp);

        // si el expediente en Trewa no es nulo habra que setear el campo titulo en la
        // convocatoria
        if (null != trExpediente) {
          convocatoriaDTO = new ConvocatoriaDTO();
          convocatoriaDTO.setTitulo(trExpediente.getTITULOEXP());
        }
      }
      return SUCCESS;

    } catch (final Exception e) {
      log.error(ConstantesMensajes.CONV_ERROR_DATOS, e);
      addActionError(e.getMessage());
      return ERROR;
    }
  }

  /**
   * Guardar convocatoria.
   *
   * @return success or error
   * @throws ParseException
   * @throws BusinessException
   * @throws Exception
   *           exception
   */
  public String guardarDatosConvocatoria() throws ParseException, BusinessException {
    String idExp = null;
    TpoPK unidadOrganica = null;
    TpoPK idUniOrgEnvia = null;
    try {
      // se obtiene el usuario de la sesion
      user = getUsuarioSesion();
      // se obtiene el expediente de trewa
      final IExpediente expedienteTrewa = user.getExpediente();

      // se obtiene el id del expediente correspondiente a la convocatoria.
      idExp = expedienteTrewa.getRefExpediente();
      final TpoPK idExpediente = new TpoPK(idExp);

      if (expedienteTrewa.getUnidadOrganica().getRefOrganismo() != null) {
        unidadOrganica = new TpoPK(expedienteTrewa.getUnidadOrganica().getRefOrganismo());
      }
      if (expedienteTrewa.getUnidadOrganicaEnvia().getRefOrganismo() != null) {
        idUniOrgEnvia = new TpoPK(expedienteTrewa.getUnidadOrganicaEnvia().getRefOrganismo());
      }
      // obtenemos la abreviatura correspondiente al procedimiento.
      abreviatura = expedienteTrewa.getProcedimiento().getAbreviatura();

      // Validacion de los datos de la convocatoria.
      setActionErrors(validacionConvocatoriaService.validarDatosConvocatoria(convocatoriaDTO));

      if (!hasActionErrors()) {
        // a partir del id buscamos si existe esa convocatoria en AACID.
        Convocatoria convocatoria = convocatoriasService.buscarConvocatoriaPorIdExpediente(idExp);

        // se comprueba que no exista el expediente de trewa.
        if (convocatoria == null) {
          convocatoria = new Convocatoria();
          // hay que recuperar el tipo de convocatoria segun su abreviatura
          convocatoria.setConvLiAbrevProcedimiento(abreviatura);
          final TipoConvocatoriaDTO tipoConvocatoriaDTO = convocatoriasService.buscarIdTipConvPorAbreviatura(abreviatura);
          if (null != tipoConvocatoriaDTO) {
            convocatoria.setAaciTTipoConvByTconvNuId(new TipoConvocatoria());
            convocatoria.getAaciTTipoConvByTconvNuId().setTconvNuId(tipoConvocatoriaDTO.getId());
          }
          convocatoria.setConvXExpe(Long.valueOf(idExp));
        }

        // se actualiza el titulo del expediente en trewa
        if (convocatoriaDTO.getTitulo() != null) {
          trewaService.modificarDatosExpediente(idExpediente, expedienteTrewa.getNumeroExpediente(), convocatoriaDTO.getTitulo(),
              expedienteTrewa.getObservaciones(), unidadOrganica, idUniOrgEnvia);
        }

        // guardamos o actualizamos la convocatoria
        convocatoriasService.crearOmodificarConvocatoria(convocatoria, convocatoriaDTO);

        addActionMessage(ConstantesMensajes.CONVOCATORIA_CREADA);
        return SUCCESS;

      }

    } catch (final Exception e) {
      addActionError(e.getMessage());
      log.error(ConstantesMensajes.CONV_ERROR_CREAR, e);
      return ERROR;
    }
    return ERROR;
  }

  /**
   * Funcion que redirige a la pantalla del listado de los expedientes hijos de una convocatoria ONGD.
   *
   * @return success or error
   *
   **/
  public String mostrarListaHijosConvocatoria() {
    return SUCCESS;
  }

  /**
   * Funcion que devuelve la lista los expedientes hijos de una convocatoria ONGD para mostrar en la vista.
   *
   * @return success or error
   * @throws ParseException
   * @throws BusinessException
   * @throws Exception
   *           exception
   */
  public String cargarListadoExpedientesHijosConvocatoria() {
    try {
      listaExpedientesHijosConvocatoriaDTO = new ArrayList<>();
      user = getUsuarioSesion();
      final String idExp = user.getExpediente().getRefExpediente();

      final List<TrExpediente> listaExpedientesHijosConvocatoria = trewaService.obtenerExpedientesHijos(new TpoPK(idExp), null);
      if (listaExpedientesHijosConvocatoria == null) {
        log.warn("El expediente " + idExp + " no tiene expedientes hijos");
        return SUCCESS;
      }
      SolicitudDatosGeneralesDTO solicitudDatosGeneralesDTO;
      for (final TrExpediente exp : listaExpedientesHijosConvocatoria) {
        solicitudDatosGeneralesDTO = solicitudService.datosGeneralesSolicitudByIdExpTrewa(exp.getREFEXP().toString());

        if (solicitudDatosGeneralesDTO == null) {
          throw new TramitacionException("El expediente " + idExp + " no tiene una solicitud relacionada");
        }

        if (exp.getREFEXP() == null) {
          throw new TramitacionException(
              "El identificador de uno de los hijos expediente trewa: " + idExp + " es nulo. No se puede realizar el listado de expedientes");
        }
        log.debug("Se va a obtener la solicitud del sistema antiguo asociada al expediente trewa: " + exp.getREFEXP().toString());
        final PaeSolicitudes solicitud = paeSolicitudService.obtenerSolicitudExpByCodigo(solicitudDatosGeneralesDTO.getTxCodidentificativo());
        final SolicitudDTO solicitudDTO = new SolicitudDTO();

        seteaCamposSolicitud(solicitud, solicitudDTO);

        // Obtenemos la solicitud a partir del expediente
        final TrInteresadoExpediente inteExp = trewaService.obtenerInteresadoExpediente(exp.getREFEXP().toString());
        String ongd = "";
        String numIdentInteresado = "";
        if (inteExp == null || inteExp.getINTERESADO() == null) {
          log.warn("No se han encontrado interesados para el expediente: " + exp.getREFEXP().toString());

        } else {
          ongd = inteExp.getINTERESADO().getNOMBRE() + " " + inteExp.getINTERESADO().getAPELLIDO1() + " " + inteExp.getINTERESADO().getAPELLIDO2();
          numIdentInteresado = inteExp.getINTERESADO().getNUMIDENT();

        }

        String plazo = "";
        if (solicitudDatosGeneralesDTO.getPlazo() != null) {
          plazo = String.valueOf(solicitudDatosGeneralesDTO.getPlazo().toPlainString());
        }

        // Se debe modificar el valor 'No' por el que corresponda, cuando consigamos averiguar de donde viene dicho campo

        solicitudDTO.setNumeroIdentificativoExpediente(exp.getNUMEXP());
        solicitudDTO.setTitulo(solicitudDatosGeneralesDTO.getTitulo());
        solicitudDTO.setOngd(ongd);
        solicitudDTO.setCif(numIdentInteresado);
        solicitudDTO.setImpSol(0.0);
        solicitudDTO.setPresupuestoTotal(0.0);
        solicitudDTO.setPlazoEjecucion(plazo);

        listaExpedientesHijosConvocatoriaDTO.add(solicitudDTO);
      }

      return SUCCESS;
    } catch (final TramitacionException e) {
      addActionError(e.getMessage());
      log.error(ConstantesMensajes.CONV_ERROR_OBTENER_HIJOS, e);
      return ERROR;
    } catch (final Exception e) {
      addActionError(e.getMessage());
      log.error(ConstantesMensajes.CONV_ERROR_CREAR, e);
      return ERROR;
    }
  }

  private void seteaCamposSolicitud(final PaeSolicitudes solicitud, final SolicitudDTO solicitudDTO) {
    String pais = "";
    String codigoFinalidad = "";
    String tieneServicios = "";
    if (solicitud != null) {

      pais = "";
      codigoFinalidad = solicitud.getPaeFinalidadByFkFinalidad().getCodigo();
      final String codigosFinalidad = ConstantesTramitacion.CODIGOS_FINALIDAD;
      final String[] codigosFinalidadSplit = codigosFinalidad.split(",");
      if (codigoFinalidad.equals(codigosFinalidadSplit[0]) || codigoFinalidad.equals(codigosFinalidadSplit[1])
          || codigoFinalidad.equals(codigosFinalidadSplit[2])) {
        pais = ConstantesTramitacion.PAIS_POR_DEFECTO;
      } else if (solicitud.getPaePais() != null) {
        pais = solicitud.getPaePais().getNombre();
      }

      tieneServicios = ConstantesTramitacion.TIENE_SERVICIOS_NO;
      if (tieneServiciosSociales(solicitud)) {
        tieneServicios = ConstantesTramitacion.TIENE_SERVICIOS_SI;
      }
      solicitudDTO.setServiciosSocialesBasicos(tieneServicios);
      solicitudDTO.setLocalizacion(pais);
      solicitudDTO.setCodIdentificativo(codigosFinalidad);
    }
  }

  private boolean tieneServiciosSociales(final PaeSolicitudes solicitud) {
    boolean tieneServicioSociales = false;
    if (solicitud.getPaeMedidaPais() != null && !CollectionUtils.isNotEmpty(solicitud.getPaeMedidaPais())) {
      final Iterator<PaeMedidasPais> iterator2 = solicitud.getPaeMedidaPais().iterator();
      while (iterator2.hasNext()) {
        final PaeMedidasPais medidaPais = iterator2.next();
        if (medidaPais.getPaeMedGrupoByFkGrupo() != null && StringUtils.isNotBlank(medidaPais.getPaeMedGrupoByFkGrupo().getNombreGrupo())
            && medidaPais.getPaeMedGrupoByFkGrupo().getNombreGrupo().trim().equalsIgnoreCase(ConstantesTramitacion.GRUPO_SERVICIOS_SOCIALES)) {
          tieneServicioSociales = true;
        }
      }
    }
    return tieneServicioSociales;
  }

  /**
   * Cargará la pantalla del listado de expedientes que se encuentran en relación provisional de concesión.
   *
   * @return success or error
   *
   **/
  public String mostrarListadoExpedientesProvConce() {
    return SUCCESS;
  }

  /**
   * Cargará el listado de expedientes pertenecientes a la fase de relación provisional de concesión y desestimación por puntuación o
   * requisitos .
   *
   * @return
   */
  public String cargarListadoExpedientesProvConceDesest() {
    try {
      listaExpedientesHijosConvocatoriaDTO = new ArrayList<>();
      user = getUsuarioSesion();
      final String idExp = user.getExpediente().getRefExpediente();
      final String fases = ConstantesTramitacion.FASE_RELACION_PROVISIONAL_CONCESION + ", "
          + ConstantesTramitacion.FASE_RESOLUCION_DESESTIMACION_PUNTUACION_REQUISITOS;
      listaExpedientesHijosConvocatoriaDTO = solicitudService.obtenerListadoSolicitudesHijasByFases(idExp, fases);
      return SUCCESS;
    } catch (final Exception e) {
      addActionError(e.getMessage());
      log.error(e.getMessage(), e);
      return ERROR;
    }
  }

  /**
   * Cargará la pantalla del para seleccionar las solicitudes que serán beneficiarias o suplentes.
   *
   * @return success or error
   *
   **/
  public String seleccionarBeneficiariaSuplentes() {
    return SUCCESS;
  }

  /**
   * Cargará el listado de expedientes pertenecientes a la fase de relación provisional de concesión.
   *
   * @return
   */
  public String cargarListadoExpedientesProvConce() {
    try {
      user = getUsuarioSesion();
      final String idExp = user.getExpediente().getRefExpediente();
      final String fases = ConstantesTramitacion.FASE_RELACION_PROVISIONAL_CONCESION;
      listaSolicitudConcesionDTO = solicitudConcesionService.obtenerBeneficiariasSuplentes(idExp, fases, finalidad, true);
      return SUCCESS;
    } catch (final Exception e) {
      addActionError(e.getMessage());
      log.error(e.getMessage(), e);
      return ERROR;
    }
  }

  /**
   * Guardar convocatoria.
   *
   * @return success or error
   * @throws ParseException
   * @throws BusinessException
   * @throws Exception
   *           exception
   */
  public String guardarBeneficiariasSuplentes() {

    try {
      convocatoriaDTO = convocatoriasService.buscarConvocatoriaDTOPorIdExpediente(getUsuarioSesion().getExpediente().getRefExpediente());
      listaSolicitudConcesionDTO = new ArrayList<>();
      for (int i = 0; request.getParameterMap().containsKey(LISTA_SOLICITUD_CONCESION_DTO + i + "][idSolicitud]"); i++) {
        listaSolicitudConcesionDTO
            .add(new SolicitudConcesionDTO(UtilidadesNumero.parseStringToLong(request.getParameter(LISTA_SOLICITUD_CONCESION_DTO + i + "][idSolicitud]")),
                UtilidadesBoolean.parseStringToBoolean(request.getParameter(LISTA_SOLICITUD_CONCESION_DTO + i + "][beneficiaria]"))));
      }
      solicitudConcesionService.guardarListaSolicitudConcesion(listaSolicitudConcesionDTO, convocatoriaDTO.getIdTipConv());
      addActionMessage(getText("guardado.ok.beneficiaria"));

    } catch (final TramitacionException e) {
      addActionError(e.getMessage());
      return ERROR;
    }
    return SUCCESS;
  }

  /**
   * @return the abreviatura
   */
  public String getAbreviatura() {
    return abreviatura;
  }

  /**
   * @param abreviatura
   *          the abreviatura to set
   */
  public void setAbreviatura(final String abreviatura) {
    this.abreviatura = abreviatura;
  }

  /**
   * @param convocatoriasService
   *          the convocatoriasService to set
   */
  public void setConvocatoriasService(final IConvocatoriasService convocatoriasService) {
    this.convocatoriasService = convocatoriasService;
  }

  /**
   * @param trewaService
   *          the trewaService to set
   */
  public void setTrewaService(final ITrewaService trewaService) {
    this.trewaService = trewaService;
  }

  /**
   * @return the convocatoriaDTO
   */
  public ConvocatoriaDTO getConvocatoriaDTO() {
    return convocatoriaDTO;
  }

  /**
   * @param convocatoriaDTO
   *          the convocatoriaDTO to set
   */
  public void setConvocatoriaDTO(final ConvocatoriaDTO convocatoriaDTO) {
    this.convocatoriaDTO = convocatoriaDTO;
  }

  /**
   * @param validacionConvocatoriaService
   *          the validacionConvocatoriaService to set
   */
  public void setValidacionConvocatoriaService(final IValidacionConvocatoriasService validacionConvocatoriaService) {
    this.validacionConvocatoriaService = validacionConvocatoriaService;
  }

  public List<SolicitudDTO> getListaExpedientesHijosConvocatoriaDTO() {
    return listaExpedientesHijosConvocatoriaDTO;
  }

  public void setListaExpedientesHijosConvocatoriaDTO(final List<SolicitudDTO> listaExpedientesHijosConvocatoriaDTO) {
    this.listaExpedientesHijosConvocatoriaDTO = listaExpedientesHijosConvocatoriaDTO;
  }

  public void setSolicitudService(final ISolicitudService solicitudService) {
    this.solicitudService = solicitudService;
  }

  public void setPaeSolicitudService(final IPaeSolicitudService paeSolicitudService) {
    this.paeSolicitudService = paeSolicitudService;
  }

  /**
   * Obtiene la propiedad finalidad
   *
   * @return el finalidad
   */
  public String getFinalidad() {
    return finalidad;
  }

  /**
   * Establece el valor de la propiedad finalidad
   *
   * @param finalidad
   *          el finalidad para establecer
   */
  public void setFinalidad(final String finalidad) {
    this.finalidad = finalidad;
  }

  /**
   * Establece el valor de la propiedad solicitudConcesionService
   *
   * @param solicitudConcesionService
   *          el solicitudConcesionService para establecer
   */
  public void setSolicitudConcesionService(final ISolicitudConcesionService solicitudConcesionService) {
    this.solicitudConcesionService = solicitudConcesionService;
  }

  /**
   * Obtiene la propiedad listaSolicitudConcesionDTO
   *
   * @return el listaSolicitudConcesionDTO
   */
  public List<SolicitudConcesionDTO> getListaSolicitudConcesionDTO() {
    return listaSolicitudConcesionDTO;
  }

  /**
   * Establece el valor de la propiedad listaSolicitudConcesionDTO
   *
   * @param listaSolicitudConcesionDTO
   *          el listaSolicitudConcesionDTO para establecer
   */
  public void setListaSolicitudConcesionDTO(final List<SolicitudConcesionDTO> listaSolicitudConcesionDTO) {
    this.listaSolicitudConcesionDTO = listaSolicitudConcesionDTO;
  }
}
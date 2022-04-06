package es.juntadeandalucia.aacid.otrasopciones.action;

import static java.util.Calendar.YEAR;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

import javax.validation.ConstraintViolation;

import org.apache.http.HttpStatus;

import es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoSubsanacionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.DatosSubsanacionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SubsanacionSeleccionadaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.TipoCatalogoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesFecha;
import es.juntadeandalucia.aacid.otrasopciones.utils.constantes.Constantes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ICatalogoService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ICatalogoSubsanacionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IHistoricoService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISubsanacionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ITipoCatalogoService;
import es.juntadeandalucia.plataforma.comunes.excepciones.BusinessException;
import es.juntadeandalucia.plataforma.service.usuarios.IUsuario;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;

/**
 * Action para el control de las tareas de subsanaciones
 *
 * @author Isotrol
 *
 */
public class DatosSubsanacionAction extends AbstractOtrasOpcionesAction {

  private static final String LISTADO_SUBSANACIONES = "listadoSubsanaciones[";

  /**
   *
   */
  private static final long serialVersionUID = -7740534787286082237L;

  private transient List<CatalogoDTO> listaCatalogo;
  private transient List<CatalogoSubsanacionDTO> catalogosSeleccionados;
  private transient ITipoCatalogoService tipoCatalogoService;
  private transient ICatalogoService catalogoService;
  private transient ICatalogoSubsanacionService catalogoSubsanacionService;
  private transient ISubsanacionService subsanacionService;
  private transient IHistoricoService historicoService;
  /** Interfaz api de trewa */
  private transient ITrewaService trewaService;

  /**
   * Datos de subsanación.
   */
  private DatosSubsanacionDTO datos;

  private transient List<TipoCatalogoDTO> listaTipoCatalogo;

  private String tipoCatalogoSeleccionado;
  private String fechaRegistro;
  private String fechaSubsanacion;
  private String fechaLimite;
  private String fechaEntrega;
  private String lugarRegistro;
  private boolean tienePermisosGuardado;

  public String valorarSubsanacion() {
    try {
      datos = subsanacionService.getSubsanacionByNumExpediente(Long.parseLong(getUsuarioSesion().getExpediente().getRefExpediente()),
          getUsuarioSesion().getListaPerfiles());

      if (datos == null) {
        datos = new DatosSubsanacionDTO();
      }

      datos.setIdExpTrewa(getUsuarioSesion().getExpediente().getRefExpediente());
      datos.setIdExp(getUsuarioSesion().getExpediente().getNumeroExpediente());
      datos.setTituloProy(getUsuarioSesion().getExpediente().getTitulo());

    } catch (final TramitacionException e) {
      log.error(getText("error.cargar"), e);
      addActionError(getText("error.cargar"));
    }

    return SUCCESS;

  }

  public String buscarSolicitudesSeleccionadas() {
    try {
      datos = subsanacionService.getSubsanacionByNumExpediente(Long.parseLong(getUsuarioSesion().getExpediente().getRefExpediente()),
          getUsuarioSesion().getListaPerfiles());
      if (datos != null) {
        fechaEntrega = UtilidadesFecha.parseDateToString(datos.getFechaEntrega());
        fechaLimite = UtilidadesFecha.parseDateToString(datos.getFechaLimite());
        fechaSubsanacion = UtilidadesFecha.parseDateToString(datos.getFechaSubsanacion());
        fechaRegistro = UtilidadesFecha.parseDateToString(datos.getFechaRegistro());
        lugarRegistro = datos.getLugarRegistro();

        if (datos.getIdSubsanacion() != null) {
          catalogosSeleccionados = catalogoSubsanacionService.obtenerCatalogoSubsanacionByIdSubsanacion(datos.getIdSubsanacion().intValue(),
              getUsuarioSesion().getListaPerfiles());
        }
      } else {
        datos = new DatosSubsanacionDTO();
      }
      compruebaPermisosUsuario();
    } catch (final TramitacionException e) {
      log.error("Error al obtener las solicitudes seleccionadas", e);
      return ERROR;
    }

    return SUCCESS;
  }

  public String buscarTipoCatalogo() {
    try {
      final GregorianCalendar fechaCreacion = new GregorianCalendar();
      fechaCreacion.setTime(getUsuarioSesion().getExpediente().getFechaCreacion());
      final Integer anio = fechaCreacion.get(YEAR);

      listaTipoCatalogo = tipoCatalogoService.obtenerCatalogosPorAnio(anio, getUsuarioSesion().getExpediente().getProcedimiento().getAbreviatura());

    } catch (TramitacionException | BusinessException e) {
      log.error("Error al obtener los tipos de catalogo", e);
      return ERROR;
    }

    return SUCCESS;
  }

  public String buscarCatalogo() {

    try {
      log.info(tipoCatalogoSeleccionado);
      listaCatalogo = catalogoService.obtenerCatalogoPorTipo(Integer.parseInt(tipoCatalogoSeleccionado), getUsuarioSesion().getListaPerfiles());

    } catch (final TramitacionException e) {
      log.error("Error al obtener los catálogos", e);
      return ERROR;
    }

    return SUCCESS;
  }

  public String guardarSubsanacionesValorador() {
    try {
      final String fechaEntregaEditada = request.getParameter("fechaEntrega");
      final String fechaRegistroEditada = request.getParameter("fechaRegistro");
      final String lugarRegistroEditada = request.getParameter("lugarRegistro");

      final List<SubsanacionSeleccionadaDTO> listadoSubsanaciones = new ArrayList<>();
      int i = 0;

      DatosSubsanacionDTO subsanacionGuardar = subsanacionService
          .getSubsanacionByNumExpediente(Long.parseLong(getUsuarioSesion().getExpediente().getRefExpediente()), getUsuarioSesion().getListaPerfiles());

      while (request.getParameterMap().containsKey(LISTADO_SUBSANACIONES + i + "][codigo]")) {

        listadoSubsanaciones.add(new SubsanacionSeleccionadaDTO(request.getParameter(LISTADO_SUBSANACIONES + i + "][codigo]"),
            request.getParameter(LISTADO_SUBSANACIONES + i + "][motivo]")));
        i++;
      }

      final GregorianCalendar fechaCreacion = new GregorianCalendar();
      fechaCreacion.setTime(getUsuarioSesion().getExpediente().getFechaCreacion());
      final Integer anio = fechaCreacion.get(YEAR);

      if (subsanacionGuardar == null) {
        subsanacionGuardar = new DatosSubsanacionDTO();
      }
      if (fechaRegistroEditada != null && !fechaRegistroEditada.isEmpty()) {
        subsanacionGuardar.setFechaRegistro(UtilidadesFecha.parseStringToDate(fechaRegistroEditada));
      }
      subsanacionGuardar.setLugarRegistro(lugarRegistroEditada);
      if (fechaEntregaEditada != null && !fechaEntregaEditada.isEmpty()) {
        subsanacionGuardar.setFechaEntrega(UtilidadesFecha.parseStringToDate(fechaEntregaEditada));
      }

      final Collection<ConstraintViolation<Object>> constraintViolations = validDatosSubsanacion(subsanacionGuardar);
      if (Boolean.FALSE.equals(constraintViolations.isEmpty())) {
        // Si contiene violaciones volvemos a la pantalla
        processConstraintViolations(constraintViolations);
        addActionError(getText("error.guardar"));
        getResponse().setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return ERROR;
      } else {
        if (subsanacionGuardar.getIdSubsanacion() != null) {
          subsanacionService.deleteSubsanacion(subsanacionGuardar.getIdSubsanacion());
        }

        subsanacionService.guardarSubsanacion(subsanacionGuardar, getUsuarioSesion().getExpediente().getRefExpediente(), listadoSubsanaciones,
            getUsuarioSesion().getExpediente().getProcedimiento().getAbreviatura(), anio);

        historicoService.guardarHistorico(getUsuarioSesion().getExpediente().getNumeroExpediente(), getUsuarioSesion().getNombreUsuario());

        addActionMessage(getText("exito.guardar"));
        return SUCCESS;
      }

    } catch (TramitacionException | BusinessException e) {
      addActionError("error.guardar");
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

  /**
   * Realiza las validaciones de los datos de subsanación
   *
   * @return lista de errores
   */
  private Collection<ConstraintViolation<Object>> validDatosSubsanacion(final DatosSubsanacionDTO datosSubsanacion) {
    final List<Class<?>> validations = new ArrayList<>();

    return valid(datosSubsanacion, validations);
  }

  private void compruebaPermisosUsuario() {
    log.debug("Init compruebaPermisosUsuario");
    final UsuarioWeb usuario = getUsuarioSesion();
    setTienePermisosGuardado(false);
    try {
      final IUsuario iUsuario = usuario.getUsuario();
      final List<Perfil> perfiles = trewaService.obtenerPerfilesUsuario(iUsuario.getCodUsuario());
      for (final Perfil perfil : perfiles) {
        if (perfil.getNombre().equalsIgnoreCase(Constantes.PERFIL_COORDINADOR) || perfil.getNombre().equalsIgnoreCase(Constantes.PERFIL_DGA)) {
          setTienePermisosGuardado(true);
        }
      }
    } catch (final BusinessException e) {
      e.printStackTrace();
    }
    log.debug("End compruebaPermisosUsuario");
  }

  public DatosSubsanacionDTO getDatos() {
    return datos;
  }

  public void setDatos(final DatosSubsanacionDTO datos) {
    this.datos = datos;
  }

  public List<TipoCatalogoDTO> getListaTipoCatalogo() {
    return listaTipoCatalogo;
  }

  public void setListaTipoCatalogo(final List<TipoCatalogoDTO> listaTipoCatalogo) {
    this.listaTipoCatalogo = listaTipoCatalogo;
  }

  public void setTipoCatalogoSeleccionado(final String tipoCatalogoSeleccionado) {
    this.tipoCatalogoSeleccionado = tipoCatalogoSeleccionado;
  }

  public void setTipoCatalogoService(final ITipoCatalogoService tipoCatalogoService) {
    this.tipoCatalogoService = tipoCatalogoService;
  }

  public void setCatalogoService(final ICatalogoService catalogoService) {
    this.catalogoService = catalogoService;
  }

  public List<CatalogoDTO> getListaCatalogo() {
    return listaCatalogo;
  }

  public void setListaCatalogo(final List<CatalogoDTO> listaCatalogo) {
    this.listaCatalogo = listaCatalogo;
  }

  public void setSubsanacionService(final ISubsanacionService subsanacionService) {
    this.subsanacionService = subsanacionService;
  }

  public void setCatalogoSubsanacionService(final ICatalogoSubsanacionService catalogoSubsanacionService) {
    this.catalogoSubsanacionService = catalogoSubsanacionService;
  }

  public List<CatalogoSubsanacionDTO> getCatalogosSeleccionados() {
    return catalogosSeleccionados;
  }

  public void setCatalogosSeleccionados(final List<CatalogoSubsanacionDTO> catalogosSeleccionados) {
    this.catalogosSeleccionados = catalogosSeleccionados;
  }

  public String getFechaRegistro() {
    return fechaRegistro;
  }

  public void setFechaRegistro(final String fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
  }

  public String getFechaSubsanacion() {
    return fechaSubsanacion;
  }

  public void setFechaSubsanacion(final String fechaSubsanacion) {
    this.fechaSubsanacion = fechaSubsanacion;
  }

  public String getFechaLimite() {
    return fechaLimite;
  }

  public void setFechaLimite(final String fechaLimite) {
    this.fechaLimite = fechaLimite;
  }

  public String getFechaEntrega() {
    return fechaEntrega;
  }

  public void setFechaEntrega(final String fechaEntrega) {
    this.fechaEntrega = fechaEntrega;
  }

  public String getLugarRegistro() {
    return lugarRegistro;
  }

  public void setLugarRegistro(final String lugarRegistro) {
    this.lugarRegistro = lugarRegistro;
  }

  public void setHistoricoService(final IHistoricoService historicoService) {
    this.historicoService = historicoService;
  }

  public boolean isTienePermisosGuardado() {
    return tienePermisosGuardado;
  }

  public void setTienePermisosGuardado(final boolean tienePermisosGuardado) {
    this.tienePermisosGuardado = tienePermisosGuardado;
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
}
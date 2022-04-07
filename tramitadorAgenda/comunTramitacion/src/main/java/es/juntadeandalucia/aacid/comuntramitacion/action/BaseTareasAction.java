package es.juntadeandalucia.aacid.comuntramitacion.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;

import es.juntadeandalucia.aacid.comuntramitacion.service.ITareasService;
import es.juntadeandalucia.plataforma.actions.ConfigurableAction;
import es.juntadeandalucia.plataforma.comunes.excepciones.ArchitectureException;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;

/**
 * Clase base para los action de las tareas
 *
 * @author Isotrol
 *
 */
public abstract class BaseTareasAction extends ConfigurableAction implements ServletRequestAware, ServletResponseAware, SessionAware {

  private static final long serialVersionUID = 8991422891340786187L;

  protected transient ITareasService tareasService;

  protected UsuarioWeb user;

  /** Request de la peticion */
  protected transient HttpServletRequest request;
  /** Mapa de response. */
  protected transient HttpServletResponse response;
  /** Localizador de los bundle de validaciones */
  private transient ResourceBundleLocator resourceBundleLocator;

  protected String nExp;
  protected String idTarea;
  protected transient Map<String, Object> session;
  protected String errores;

  /**
   * Obtiene el usuario de PTw@nda de la sesion
   *
   * @return UsuarioWeb
   */
  public UsuarioWeb getUsuarioSesion() {
    return (UsuarioWeb) session.get("usuario_en_sesion");
  }

  protected void handleException(final Throwable exception) {
    final String fullTrace = StringEscapeUtils.escapeHtml(ExceptionUtils.getFullStackTrace(exception));
    LOG.error("COD_ERROR [" + codigoError + "] :" + fullTrace, exception);
    final Throwable throwable = getLeafException(exception);
    final StringBuilder extraInfo = new StringBuilder();
    if (throwable instanceof ArchitectureException) {
      final ArchitectureException architectureException = (ArchitectureException) throwable;
      extraInfo.append(": ").append(architectureException.getClave());
    }
  }

  private Throwable getLeafException(final Throwable exception) {
    final Throwable throwable = exception.getCause();
    if (exception.equals(throwable)) {
      return throwable;
    } else {
      return throwable == null ? exception : getLeafException(throwable);
    }
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public void setSession(final Map session) {
    this.session = session;
  }

  @Override
  public void setServletRequest(final HttpServletRequest request) {
    this.request = request;

  }

  @Override
  public void setServletResponse(final HttpServletResponse response) {
    this.response = response;

  }

  /**
   * @param tareasService
   *          the tareasService to set
   */
  public void setTareasService(final ITareasService tareasService) {
    this.tareasService = tareasService;
  }

  /**
   * @return the user
   */
  public UsuarioWeb getUser() {
    return user;
  }

  /**
   * @param user
   *          the user to set
   */
  public void setUser(final UsuarioWeb user) {
    this.user = user;
  }

  /**
   * @return the request
   */
  public HttpServletRequest getRequest() {
    return request;
  }

  /**
   * @param request
   *          the request to set
   */
  public void setRequest(final HttpServletRequest request) {
    this.request = request;
  }

  /**
   * @return the response
   */
  public HttpServletResponse getResponse() {
    return response;
  }

  /**
   * @param response
   *          the response to set
   */
  public void setResponse(final HttpServletResponse response) {
    this.response = response;
  }

  /**
   * @return the resourceBundleLocator
   */
  public ResourceBundleLocator getResourceBundleLocator() {
    return resourceBundleLocator;
  }

  /**
   * @param resourceBundleLocator
   *          the resourceBundleLocator to set
   */
  public void setResourceBundleLocator(final ResourceBundleLocator resourceBundleLocator) {
    this.resourceBundleLocator = resourceBundleLocator;
  }

  /**
   * @return the nExp
   */
  public String getnExp() {
    return nExp;
  }

  /**
   * @param nExp
   *          the nExp to set
   */
  public void setnExp(final String nExp) {
    this.nExp = nExp;
  }

  /**
   * @return the idTarea
   */
  public String getIdTarea() {
    return idTarea;
  }

  /**
   * @param idTarea
   *          the idTarea to set
   */
  public void setIdTarea(final String idTarea) {
    this.idTarea = idTarea;
  }

  /**
   * @return the session
   */
  public Map<String, Object> getSession() {
    return session;
  }

  /**
   * @return the errores
   */
  public String getErrores() {
    return errores;
  }

  /**
   * @param errores
   *          the errores to set
   */
  public void setErrores(final String errores) {
    this.errores = errores;
  }

}

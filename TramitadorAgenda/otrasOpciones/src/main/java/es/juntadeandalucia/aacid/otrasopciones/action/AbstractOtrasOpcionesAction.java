package es.juntadeandalucia.aacid.otrasopciones.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;

import es.juntadeandalucia.plataforma.actions.ConfigurableAction;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;

/**
 * Action base del modulo de utilidad de otras opciones (pantalla DGA)
 *
 * @author Isotrol
 */
public abstract class AbstractOtrasOpcionesAction extends ConfigurableAction implements SessionAware, ServletResponseAware {

  /**
   * Serial UID.
   */
  private static final long serialVersionUID = 737925070606645436L;
  protected final transient Logger log = Logger.getLogger(getClass());
  /** Localizador de los bundle de validaciones */
  private transient ResourceBundleLocator resourceBundleLocator;

  protected UsuarioWeb usuario;
  /** Request de la peticion */
  protected transient HttpServletRequest request;
  /** Mapa de response. */
  protected transient HttpServletResponse response;
  protected transient Map<String, Object> session;

  /**
   * Obtiene el usuario de PTw@nda de la sesion
   * 
   * @return UsuarioWeb
   */
  public UsuarioWeb getUsuarioSesion() {
    return (UsuarioWeb) this.session.get("usuario_en_sesion");
  }

  protected Collection<ConstraintViolation<Object>> valid(Object objectToValidate, List<Class<?>> validations) {
    Validator validator = this.obtenerValidador();
    return this.valid(objectToValidate, validator, validations);
  }

  protected Collection<ConstraintViolation<Object>> valid(Object objectToValidate, Validator validator, List<Class<?>> validations) {
    return validations != null && !validations.isEmpty() ? validator.validate(objectToValidate, this.getClassValidations(validations))
        : validator.validate(objectToValidate, new Class[0]);
  }

  protected Class<?>[] getClassValidations(List<Class<?>> validations) {
    return (Class[]) validations.toArray(new Class[validations.size()]);
  }

  /**
   * Obtiene el validador a usar en el controlador
   * 
   * @return Implementacion del validador
   */
  protected Validator obtenerValidador() {

    Validator validador = null;
    if (resourceBundleLocator == null) {
      LOG.info("No se ha informado localizador de bundle de validación, se emplea validador por defecto.");
      validador = Validation.buildDefaultValidatorFactory().getValidator();
    } else {
      validador = Validation.byDefaultProvider().configure()
          .messageInterpolator((MessageInterpolator) new ResourceBundleMessageInterpolator(resourceBundleLocator)).buildValidatorFactory().getValidator();
    }
    return validador;
  }

  @SuppressWarnings("unchecked")
  protected void processConstraintViolations(Collection<ConstraintViolation<Object>> constraintViolations) {
    Iterator i$ = constraintViolations.iterator();

    while (i$.hasNext()) {
      ConstraintViolation<Object> violation = (ConstraintViolation<Object>) i$.next();
      StringBuilder errorMessage = new StringBuilder(StringUtils.capitalize(violation.getPropertyPath().toString()));
      errorMessage.append(": ");
      errorMessage.append(violation.getMessage());
      LOG.debug(errorMessage.toString());
      this.addActionError(errorMessage.toString());
    }

  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public void setSession(Map session) {
    this.session = session;
  }

  @Override
  public void setServletRequest(HttpServletRequest request) {
    this.request = request;

  }

  @Override
  public void setServletResponse(HttpServletResponse response) {
    this.response = response;

  }

  /**
   * @return the user
   */
  public UsuarioWeb getUsuario() {
    return usuario;
  }

  /**
   * @param user
   *          the user to set
   */
  public void setUsuario(UsuarioWeb user) {
    this.usuario = user;
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
  public void setRequest(HttpServletRequest request) {
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
  public void setResponse(HttpServletResponse response) {
    this.response = response;
  }

  /**
   * Establece el valor de la propiedad resourceBundleLocator
   * 
   * @param resourceBundleLocator
   *          el resourceBundleLocator para establecer
   */
  public void setResourceBundleLocator(ResourceBundleLocator resourceBundleLocator) {
    this.resourceBundleLocator = resourceBundleLocator;
  }

}
package es.juntadeandalucia.aacid.otrasopciones.exception;

/**
 * Excepción para el módulo de utilidad
 * 
 * @author Isotrol
 */
public class OtrasOpcionesException extends Exception {

  /**
   * Identificador de serializacion.
   */
  private static final long serialVersionUID = 5841346890390401098L;

  /**
   * Constructor de la Excepcion con el mensaje y la causa
   *
   * @param message
   *          Mensaje
   * @param cause
   *          Causa
   */
  public OtrasOpcionesException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructor de la Excepcion con el mensaje
   *
   * @param message
   *          Mensaje
   */
  public OtrasOpcionesException(String message) {
    super(message);
  }

  /**
   * Constructor de la Excepcion con la causa
   *
   * @param cause
   *          Causa
   */
  public OtrasOpcionesException(Throwable cause) {
    super(cause);
  }
}
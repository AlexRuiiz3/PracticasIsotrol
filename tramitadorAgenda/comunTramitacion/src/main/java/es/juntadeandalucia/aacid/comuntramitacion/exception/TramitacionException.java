package es.juntadeandalucia.aacid.comuntramitacion.exception;

/**
 * TramitacionException class.
 * 
 * @author isotrol
 *
 */
public class TramitacionException extends Exception {

  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  /**
   * Método que controla la excepción.
   * 
   * @param texto
   *          texto clave de la excepción (property)
   */
  public TramitacionException(String texto) {

    super(texto);

  }

  /**
   * Asocia la excepción al contexto del módulo de resoluciones, siempre se encapsula
   * 
   * @param texto
   *          texto de la excepción
   * @param e
   *          excepción a la que esta asociada
   */
  public TramitacionException(String texto, Throwable e) {

    super(texto, e);

  }
}

package es.juntadeandalucia.aacid.comuntramitacion.exception;

/**
 * ParseCamposVeaException class.
 *
 * @author isotrol
 *
 */
public class ParseCamposVeaException extends Exception {

  /** serialVersionUID */
  private static final long serialVersionUID = 1L;

  /**
   * Método que controla la excepción.
   *
   * @param texto
   *          texto clave de la excepción (property)
   */
  public ParseCamposVeaException(final String texto) {

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
  public ParseCamposVeaException(final String texto, final Throwable e) {

    super(texto, e);

  }
}

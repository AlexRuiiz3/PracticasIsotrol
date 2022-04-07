package es.juntadeandalucia.aacid.comuntramitacion.exception;

/**
 * AlegacionesAceptaReformulaException class.
 *
 * @author isotrol
 *
 */
public class AlegacionesAceptaReformulaException extends Exception {

  /** serialVersionUID */
  private static final long serialVersionUID = 858061965350864433L;

  /**
   * Método que controla la excepción.
   *
   * @param texto
   *          texto clave de la excepción (property)
   */
  public AlegacionesAceptaReformulaException(final String texto) {

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
  public AlegacionesAceptaReformulaException(final String texto, final Throwable e) {
    super(texto, e);
  }
}

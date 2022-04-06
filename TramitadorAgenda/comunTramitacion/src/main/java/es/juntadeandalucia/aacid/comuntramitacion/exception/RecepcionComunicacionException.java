package es.juntadeandalucia.aacid.comuntramitacion.exception;

/**
 * RecepcionComunicacionException class.
 *
 * @author isotrol
 *
 */
public class RecepcionComunicacionException extends Exception {

  /** serialVersionUID */
  private static final long serialVersionUID = 858061965350864433L;

  /**
   * Método que controla la excepción.
   *
   * @param texto
   *          texto clave de la excepción (property)
   */
  public RecepcionComunicacionException(final String texto) {

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
  public RecepcionComunicacionException(final String texto, final Throwable e) {
    super(texto, e);
  }
}

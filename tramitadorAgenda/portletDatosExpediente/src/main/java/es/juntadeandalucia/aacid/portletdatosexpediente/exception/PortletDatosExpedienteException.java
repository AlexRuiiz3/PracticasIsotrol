package es.juntadeandalucia.aacid.portletdatosexpediente.exception;

/**
 * @author administracionelectronica.sae PortletDatosExpedienteDtoException.java (2020)
 */
public class PortletDatosExpedienteException extends Exception {

  /** serialVersionUID */
  private static final long serialVersionUID = 1357385604575491477L;

  /**
   * Método que controla la excepción.
   *
   * @param texto
   *          texto clave de la excepción (property)
   */
  public PortletDatosExpedienteException(final String texto) {

    super(texto);

  }

  /**
   * Asocia la excepción al contexto del módulo
   *
   * @param texto
   *          texto de la excepción
   * @param e
   *          excepción a la que esta asociada
   */
  public PortletDatosExpedienteException(final String texto, final Throwable e) {

    super(texto, e);

  }

}

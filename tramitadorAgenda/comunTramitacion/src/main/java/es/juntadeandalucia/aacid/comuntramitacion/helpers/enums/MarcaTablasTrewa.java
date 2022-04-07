/**
 * 
 */
package es.juntadeandalucia.aacid.comuntramitacion.helpers.enums;

public enum MarcaTablasTrewa {

  /** Nombre de la componente de Notifica en Trewa */
  FILA("separadorFila"), /** Nombre para identifica el tipo de notificacion ordinario en Trewa */
  COLUMNA("separadorColumna");

  private final String text;

  /**
   * @param text
   */
  private MarcaTablasTrewa(final String text) {
    this.text = text;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Enum#toString()
   */
  @Override
  public String toString() {
    return text;
  }

}

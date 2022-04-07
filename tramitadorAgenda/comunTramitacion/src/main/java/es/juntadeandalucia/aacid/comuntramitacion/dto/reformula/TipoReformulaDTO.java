package es.juntadeandalucia.aacid.comuntramitacion.dto.reformula;

import java.util.Objects;

public class TipoReformulaDTO implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 5852661788013358702L;

  private String clave;
  private String valor;

  public TipoReformulaDTO(String clave, String valor) {
    super();
    this.clave = clave;
    this.valor = valor;
  }

  public final String getClave() {
    return clave;
  }

  public final void setClave(String clave) {
    this.clave = clave;
  }

  public final String getValor() {
    return valor;
  }

  public final void setValor(String valor) {
    this.valor = valor;
  }

  @Override
  public int hashCode() {
    return Objects.hash(clave);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    TipoReformulaDTO other = (TipoReformulaDTO) obj;
    return Objects.equals(clave, other.clave);
  }

}

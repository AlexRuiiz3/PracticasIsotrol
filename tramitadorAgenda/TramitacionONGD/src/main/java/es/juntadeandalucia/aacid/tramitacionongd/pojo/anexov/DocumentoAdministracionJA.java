package es.juntadeandalucia.aacid.tramitacionongd.pojo.anexov;

import java.util.Date;
import java.util.Objects;

public class DocumentoAdministracionJA {

  private String nombre;
  private String consejeria;
  private Date fecha;
  private String procedimiento;

  /**
   * @return the nombre
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * @param nombre
   *          the nombre to set
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * @return the consejeria
   */
  public String getConsejeria() {
    return consejeria;
  }

  /**
   * @param consejeria
   *          the consejeria to set
   */
  public void setConsejeria(String consejeria) {
    this.consejeria = consejeria;
  }

  /**
   * @return the fecha
   */
  public Date getFecha() {
    return fecha;
  }

  /**
   * @param fecha
   *          the fecha to set
   */
  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  /**
   * @return the procedimiento
   */
  public String getProcedimiento() {
    return procedimiento;
  }

  /**
   * @param procedimiento
   *          the procedimiento to set
   */
  public void setProcedimiento(String procedimiento) {
    this.procedimiento = procedimiento;
  }

  @Override
  public int hashCode() {
    return Objects.hash(consejeria, fecha, nombre, procedimiento);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof DocumentoAdministracionJA)) {
      return false;
    }
    DocumentoAdministracionJA other = (DocumentoAdministracionJA) obj;
    return Objects.equals(consejeria, other.consejeria) && Objects.equals(fecha, other.fecha) && Objects.equals(nombre, other.nombre)
        && Objects.equals(procedimiento, other.procedimiento);
  }

}

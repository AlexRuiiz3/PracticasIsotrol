package es.juntadeandalucia.aacid.tramitacionongd.pojo.anexov;

import java.util.Date;
import java.util.Objects;

public class DocumentoOtraAdministracion {

  private String nombre;
  private String administracion;
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
   * @return the administracion
   */
  public String getAdministracion() {
    return administracion;
  }

  /**
   * @param administracion
   *          the administracion to set
   */
  public void setAdministracion(String administracion) {
    this.administracion = administracion;
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
    return Objects.hash(administracion, fecha, nombre, procedimiento);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof DocumentoOtraAdministracion)) {
      return false;
    }
    DocumentoOtraAdministracion other = (DocumentoOtraAdministracion) obj;
    return Objects.equals(administracion, other.administracion) && Objects.equals(fecha, other.fecha) && Objects.equals(nombre, other.nombre)
        && Objects.equals(procedimiento, other.procedimiento);
  }

}

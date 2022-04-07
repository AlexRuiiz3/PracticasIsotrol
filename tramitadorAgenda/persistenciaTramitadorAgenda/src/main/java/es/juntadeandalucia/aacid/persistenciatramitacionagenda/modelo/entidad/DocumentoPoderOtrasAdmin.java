package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_T_DOC_PODER_OTR_ADMIN", schema = "AACID_OWNER", catalog = "")
public class DocumentoPoderOtrasAdmin implements Serializable {

  private static final long serialVersionUID = 4006804221711443548L;

  @Id
  @Column(name = "ID_DOC_PODER_OTR_ADMIN", nullable = false, precision = 10)
  @SequenceGenerator(name = "seqDocumentoPoderOtrasAdmin", sequenceName = "AACI_SEQ_DOCS_PODER_OTR_ADMIN", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqDocumentoPoderOtrasAdmin")
  private Long id;

  @ManyToOne
  @JoinColumn(referencedColumnName = "ID_SOLICITUD", nullable = false, foreignKey = @ForeignKey(name = "FK_AACI_T_DOC_OTR_ADMIN_SOL"))
  private Solicitud solicitud;

  @Column(name = "TX_NOM_DOCUMENTO", nullable = false, length = 100)
  private String nombre;

  @Column(name = "TX_CONSEJERIA_Y_ORGANO", nullable = false, length = 100)
  private String administracion;

  @Column(name = "FH_FECHA_PRESENT", nullable = false)
  private Date fecha;

  @Column(name = "TX_PROCED_EMITIO", nullable = false, length = 100)
  private String procedimiento;

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the solicitud
   */
  public Solicitud getSolicitud() {
    return solicitud;
  }

  /**
   * @param solicitud
   *          the solicitud to set
   */
  public void setSolicitud(Solicitud solicitud) {
    this.solicitud = solicitud;
  }

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
    return Objects.hash(administracion, fecha, id, nombre, procedimiento, solicitud);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof DocumentoPoderOtrasAdmin)) {
      return false;
    }
    DocumentoPoderOtrasAdmin other = (DocumentoPoderOtrasAdmin) obj;
    return Objects.equals(administracion, other.administracion) && Objects.equals(fecha, other.fecha) && Objects.equals(id, other.id)
        && Objects.equals(nombre, other.nombre) && Objects.equals(procedimiento, other.procedimiento) && Objects.equals(solicitud, other.solicitud);
  }

}

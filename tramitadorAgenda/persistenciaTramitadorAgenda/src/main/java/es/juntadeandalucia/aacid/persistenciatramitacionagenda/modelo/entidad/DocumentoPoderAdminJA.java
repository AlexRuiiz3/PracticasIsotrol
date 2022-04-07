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
@Table(name = "AACI_T_DOC_PODER_ADMIN_JA", schema = "AACID_OWNER", catalog = "")
public class DocumentoPoderAdminJA implements Serializable {

  private static final long serialVersionUID = -8701651359895782277L;

  @Id
  @Column(name = "ID_DOC_PODER_ADMIN_JA", nullable = false, precision = 10)
  @SequenceGenerator(name = "seqDocumentoPoderAdminJA", sequenceName = "AACI_SEQ_DOCS_PODER_JA", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqDocumentoPoderAdminJA")
  private Long id;

  @ManyToOne
  @JoinColumn(referencedColumnName = "ID_SOLICITUD", nullable = false, foreignKey = @ForeignKey(name = "FK_AACI_T_DOC_ADMIN_JA_SOL"))
  private Solicitud solicitud;

  @Column(name = "TX_NOM_DOCUMENTO", nullable = false, length = 100)
  private String nombre;

  @Column(name = "TX_CONSEJERIA_Y_ORGANO", nullable = false, length = 100)
  private String consejeriaOrgano;

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
   * @return the consejeriaOrgano
   */
  public String getConsejeriaOrgano() {
    return consejeriaOrgano;
  }

  /**
   * @param consejeriaOrgano
   *          the consejeriaOrgano to set
   */
  public void setConsejeriaOrgano(String consejeriaOrgano) {
    this.consejeriaOrgano = consejeriaOrgano;
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
    return Objects.hash(consejeriaOrgano, fecha, id, nombre, procedimiento, solicitud);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof DocumentoPoderAdminJA)) {
      return false;
    }
    DocumentoPoderAdminJA other = (DocumentoPoderAdminJA) obj;
    return Objects.equals(consejeriaOrgano, other.consejeriaOrgano) && Objects.equals(fecha, other.fecha) && Objects.equals(id, other.id)
        && Objects.equals(nombre, other.nombre) && Objects.equals(procedimiento, other.procedimiento) && Objects.equals(solicitud, other.solicitud);
  }

}

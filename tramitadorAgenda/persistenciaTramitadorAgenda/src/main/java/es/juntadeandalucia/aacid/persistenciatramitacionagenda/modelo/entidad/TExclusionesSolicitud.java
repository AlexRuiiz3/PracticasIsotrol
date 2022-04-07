package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the AACI_TEXCLUSIONES_SOLICITUD database table.
 *
 */
@Entity
@Table(name = "AACI_TEXCLUSIONES_SOLICITUD", schema = "AACID_OWNER", catalog = "")
@NamedQuery(name = "TExclusionesSolicitud.findAll", query = "SELECT t FROM TExclusionesSolicitud t")
public class TExclusionesSolicitud implements Serializable {
  /**
   *
   */
  private static final long serialVersionUID = -5848663444210202868L;

  @Id
  @SequenceGenerator(name = "AACI_TEXCLUSIONES_SOLICITUD_TESONUID_GENERATOR", sequenceName = "AACI_SEQ_TEXCLU_SOLICITUD", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AACI_TEXCLUSIONES_SOLICITUD_TESONUID_GENERATOR")
  @Column(name = "TESO_NU_ID")
  private Long tesoNuId;

  @Column(name = "TESO_LG_EXCLUSIONES")
  private boolean tesoLgExclusiones;

  @Column(name = "TEXC_NU_TIPO")
  private Long texcNuTipo;

  private Solicitud solicitud;

  public TExclusionesSolicitud() {
    // Constructor por defecto
  }

  /**
   * @param tesoNuId
   * @param tesoLgExclusiones
   * @param texcNuTipo
   */
  public TExclusionesSolicitud(final Long tesoNuId, final boolean tesoLgExclusiones, final Long texcNuTipo) {
    this.tesoNuId = tesoNuId;
    this.tesoLgExclusiones = tesoLgExclusiones;
    this.texcNuTipo = texcNuTipo;
  }

  public Long getTesoNuId() {
    return tesoNuId;
  }

  public void setTesoNuId(final Long tesoNuId) {
    this.tesoNuId = tesoNuId;
  }

  @ManyToOne
  @JoinColumn(name = "ID_SOLICITUD", referencedColumnName = "ID_SOLICITUD")
  public Solicitud getSolicitud() {
    return solicitud;
  }

  public void setSolicitud(final Solicitud solicitud) {
    this.solicitud = solicitud;
  }

  public boolean getTesoLgExclusiones() {
    return tesoLgExclusiones;
  }

  public void setTesoLgExclusiones(final boolean tesoLgExclusiones) {
    this.tesoLgExclusiones = tesoLgExclusiones;
  }

  /**
   * Obtiene la propiedad texcNuTipo
   *
   * @return el texcNuTipo
   */
  public Long getTexcNuTipo() {
    return texcNuTipo;
  }

  /**
   * Establece el valor de la propiedad texcNuTipo
   *
   * @param texcNuTipo
   *          el texcNuTipo para establecer
   */
  public void setTexcNuTipo(final Long texcNuTipo) {
    this.texcNuTipo = texcNuTipo;
  }

}
package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_CONTRAPARTES", schema = "AACID_OWNER", catalog = "")
public class Contrapartes {
  private Long contIdContraparte;
  private String contContraparte;
  private String contOtras;
  private String contLocalidad;
  private String contPais;
  private Solicitud solicitud;
  private TipoEntidadContraparte tipoEntidadContraparte;
  private List<ConContraparte> conContrapartesByContIdContraparte;

  @Id
  @SequenceGenerator(name = "seqContraparte", sequenceName = "AACI_SEQ_CONTRAPARTES", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqContraparte")
  @Column(name = "CONT_NU_ID")
  public Long getContIdContraparte() {
    return contIdContraparte;
  }

  public void setContIdContraparte(final Long contIdContraparte) {
    this.contIdContraparte = contIdContraparte;
  }

  @Basic
  @Column(name = "CONT_LI_CONTRAPARTE")
  public String getContContraparte() {
    return contContraparte;
  }

  public void setContContraparte(final String contContraparte) {
    this.contContraparte = contContraparte;
  }

  @Basic
  @Column(name = "CONT_LI_OTRAS")
  public String getContOtras() {
    return contOtras;
  }

  public void setContOtras(final String contOtras) {
    this.contOtras = contOtras;
  }

  @Basic
  @Column(name = "CONT_LI_LOCALIDAD")
  public String getContLocalidad() {
    return contLocalidad;
  }

  public void setContLocalidad(final String contLocalidad) {
    this.contLocalidad = contLocalidad;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Contrapartes that = (Contrapartes) o;
    return Objects.equals(contIdContraparte, that.contIdContraparte) && Objects.equals(contContraparte, that.contContraparte)
        && Objects.equals(contOtras, that.contOtras) && Objects.equals(contLocalidad, that.contLocalidad);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contIdContraparte, contContraparte, contOtras, contLocalidad);
  }

  @ManyToOne
  @JoinColumn(name = "CONT_FK_SOLICITUD", referencedColumnName = "ID_SOLICITUD")
  public Solicitud getSolicitud() {
    return solicitud;
  }

  public void setSolicitud(final Solicitud solicitud) {
    this.solicitud = solicitud;
  }

  @ManyToOne
  @JoinColumn(name = "TMEC_NU_ID", referencedColumnName = "TMEC_NU_ID")
  public TipoEntidadContraparte getTipoEntidadContraparte() {
    return tipoEntidadContraparte;
  }

  public void setTipoEntidadContraparte(final TipoEntidadContraparte entidad) {
    tipoEntidadContraparte = entidad;
  }

  @OneToMany(mappedBy = "aaciContrapartesByFkEntidad")
  public List<ConContraparte> getAaciConContrapartesByContIdContraparte() {
    return conContrapartesByContIdContraparte;
  }

  public void setAaciConContrapartesByContIdContraparte(final List<ConContraparte> conContrapartesByContIdContraparte) {
    this.conContrapartesByContIdContraparte = conContrapartesByContIdContraparte;
  }

  @Basic
  @Column(name = "CONT_LI_PAIS")
  public String getContPais() {
    return contPais;
  }

  public void setContPais(final String contPais) {
    this.contPais = contPais;
  }
}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PAE_T_ENTIDAD_CONTRAPARTE")
public class PaeTEntidadContraparte {
  private Long idTEntContraparte;
  private String tEntContrap;
  private Collection<PaeContrapartes> paeContrapartesByIdTEntContraparte;

  @Id
  @Column(name = "ID_T_ENT_CONTRAPARTE")
  public Long getIdTEntContraparte() {
    return idTEntContraparte;
  }

  public void setIdTEntContraparte(final Long idTEntContraparte) {
    this.idTEntContraparte = idTEntContraparte;
  }

  @Basic
  @Column(name = "T_ENT_CONTRAP")
  public String gettEntContrap() {
    return tEntContrap;
  }

  public void settEntContrap(final String tEntContrap) {
    this.tEntContrap = tEntContrap;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PaeTEntidadContraparte that = (PaeTEntidadContraparte) o;
    return Objects.equals(idTEntContraparte, that.idTEntContraparte) && Objects.equals(tEntContrap, that.tEntContrap);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idTEntContraparte, tEntContrap);
  }

  @OneToMany(mappedBy = "paeTEntidadContraparteByFkTEntidad")
  public Collection<PaeContrapartes> getPaeContrapartesByIdTEntContraparte() {
    return paeContrapartesByIdTEntContraparte;
  }

  public void setPaeContrapartesByIdTEntContraparte(final Collection<PaeContrapartes> paeContrapartesByIdTEntContraparte) {
    this.paeContrapartesByIdTEntContraparte = paeContrapartesByIdTEntContraparte;
  }
}

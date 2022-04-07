package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PAE_T_ENTIDAD_PARTICIPANTE")
public class PaeTEntidadParticipante implements Serializable {
  private static final long serialVersionUID = 8826923579368332234L;

  private long idTEntPartic;

  private String entidadPartic;

  private String entidadSel;

  private List<PaeEntidadesParticipantes> paeEntidadesParticipanteses;

  public PaeTEntidadParticipante() {
  }

  public PaeTEntidadParticipante(final long idTEntPartic) {
    this.idTEntPartic = idTEntPartic;
  }

  public PaeTEntidadParticipante(final long idTEntPartic, final String entidadPartic, final List<PaeEntidadesParticipantes> paeEntidadesParticipanteses) {
    this.idTEntPartic = idTEntPartic;
    this.entidadPartic = entidadPartic;
    this.paeEntidadesParticipanteses = paeEntidadesParticipanteses;
  }

  @Id
  @Column(name = "ID_T_ENT_PARTIC", nullable = false, precision = 10, scale = 0)
  public long getIdTEntPartic() {
    return idTEntPartic;
  }

  public void setIdTEntPartic(final long idTEntPartic) {
    this.idTEntPartic = idTEntPartic;
  }

  @Column(name = "T_ENTIDAD_PARTIC", length = 50)
  public String getEntidadPartic() {
    return entidadPartic;
  }

  public void setEntidadPartic(final String entidadPartic) {
    this.entidadPartic = entidadPartic;
  }

  // @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "paeTEntidadParticipante")
  public List<PaeEntidadesParticipantes> getPaeEntidadesParticipanteses() {
    return paeEntidadesParticipanteses;
  }

  public void setPaeEntidadesParticipanteses(final List<PaeEntidadesParticipantes> paeEntidadesParticipanteses) {
    this.paeEntidadesParticipanteses = paeEntidadesParticipanteses;
  }

  @Transient
  public String getEntidadSel() {
    return entidadSel;
  }
}

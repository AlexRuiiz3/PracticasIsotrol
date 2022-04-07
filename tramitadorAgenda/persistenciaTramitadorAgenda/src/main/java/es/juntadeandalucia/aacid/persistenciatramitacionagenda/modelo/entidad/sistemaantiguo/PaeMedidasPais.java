package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PAE_MEDIDAS_PAIS")
public class PaeMedidasPais {
	private Integer idMedPais;
	private String descripcion;
	private PaePaises paePaisesByFkPais;
	private PaeMedGrupo paeMedGrupoByFkGrupo;

  @Id
  @Column(name = "ID_MED_PAIS")
  public Integer getIdMedPais() {
    return idMedPais;
  }

  public void setIdMedPais(Integer idMedPais) {
    this.idMedPais = idMedPais;
  }

  @Basic
  @Column(name = "DESCRIPCION")
  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    PaeMedidasPais that = (PaeMedidasPais) o;
    return Objects.equals(idMedPais, that.idMedPais) && Objects.equals(descripcion, that.descripcion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idMedPais, descripcion);
  }

  @ManyToOne
  @JoinColumn(name = "FK_PAIS", referencedColumnName = "ID_PAIS")
  public PaePaises getPaePaisesByFkPais() {
    return paePaisesByFkPais;
  }

  public void setPaePaisesByFkPais(PaePaises paePaisesByFkPais) {
    this.paePaisesByFkPais = paePaisesByFkPais;
  }

  @ManyToOne
  @JoinColumn(name = "FK_GRUPO", referencedColumnName = "ID_GRUPO")
  public PaeMedGrupo getPaeMedGrupoByFkGrupo() {
    return paeMedGrupoByFkGrupo;
  }

  public void setPaeMedGrupoByFkGrupo(PaeMedGrupo paeMedGrupoByFkGrupo) {
    this.paeMedGrupoByFkGrupo = paeMedGrupoByFkGrupo;
  }

}

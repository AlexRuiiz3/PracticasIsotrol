package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity @Table(name = "PAE_MED_GRUPO") public class PaeMedGrupo {
	private Long idGrupo;
	private String nombreGrupo;
	private Long orden;
	private Collection<PaeMedidasPais> paeMedidasPaisByIdGrupo;

	@Id @Column(name = "ID_GRUPO") public Long getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}

	@Basic @Column(name = "NOMBRE_GRUPO") public String getNombreGrupo() {
		return nombreGrupo;
	}

	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	@Basic @Column(name = "ORDEN") public Long getOrden() {
		return orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PaeMedGrupo that = (PaeMedGrupo) o;
		return Objects.equals(idGrupo, that.idGrupo) && Objects.equals(nombreGrupo, that.nombreGrupo) && Objects
				.equals(orden, that.orden);
	}

	@Override public int hashCode() {
		return Objects.hash(idGrupo, nombreGrupo, orden);
	}

	@OneToMany(mappedBy = "paeMedGrupoByFkGrupo") public Collection<PaeMedidasPais> getPaeMedidasPaisByIdGrupo() {
		return paeMedidasPaisByIdGrupo;
	}

	public void setPaeMedidasPaisByIdGrupo(Collection<PaeMedidasPais> paeMedidasPaisByIdGrupo) {
		this.paeMedidasPaisByIdGrupo = paeMedidasPaisByIdGrupo;
	}
}

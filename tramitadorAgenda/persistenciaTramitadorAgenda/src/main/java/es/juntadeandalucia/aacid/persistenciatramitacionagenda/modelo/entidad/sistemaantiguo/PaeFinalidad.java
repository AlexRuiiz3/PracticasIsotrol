package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity @Table(name = "PAE_FINALIDAD") public class PaeFinalidad {
	private Long idFinalidad;
	private String finalidad;
	private String codigo;
	private Collection<PaeSolicitudes> paeSolicitudesByIdFinalidad;

	@Id @Column(name = "ID_FINALIDAD") public Long getIdFinalidad() {
		return idFinalidad;
	}

	public void setIdFinalidad(Long idFinalidad) {
		this.idFinalidad = idFinalidad;
	}

	@Basic @Column(name = "FINALIDAD") public String getFinalidad() {
		return finalidad;
	}

	public void setFinalidad(String finalidad) {
		this.finalidad = finalidad;
	}

	@Basic @Column(name = "CODIGO") public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PaeFinalidad that = (PaeFinalidad) o;
		return Objects.equals(idFinalidad, that.idFinalidad) && Objects.equals(finalidad, that.finalidad) && Objects
				.equals(codigo, that.codigo);
	}

	@Override public int hashCode() {
		return Objects.hash(idFinalidad, finalidad, codigo);
	}

	@OneToMany(mappedBy = "paeFinalidadByFkFinalidad") public Collection<PaeSolicitudes> getPaeSolicitudesByIdFinalidad() {
		return paeSolicitudesByIdFinalidad;
	}

	public void setPaeSolicitudesByIdFinalidad(Collection<PaeSolicitudes> paeSolicitudesByIdFinalidad) {
		this.paeSolicitudesByIdFinalidad = paeSolicitudesByIdFinalidad;
	}
}

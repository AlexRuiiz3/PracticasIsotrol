package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity @Table(name = "PAE_PAISES") public class PaePaises {
	private Long idPais;
	private String codigo;
	private String nombre;
	private Long grupo;
	private Long puntuacion;
	private Collection<PaeMedidasPais> paeMedidasPaisByIdPais;

	@Id @Column(name = "ID_PAIS") public Long getIdPais() {
		return idPais;
	}

	public void setIdPais(Long idPais) {
		this.idPais = idPais;
	}

	@Basic @Column(name = "CODIGO") public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Basic @Column(name = "NOMBRE") public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Basic @Column(name = "GRUPO") public Long getGrupo() {
		return grupo;
	}

	public void setGrupo(Long grupo) {
		this.grupo = grupo;
	}

	@Basic @Column(name = "PUNTUACION") public Long getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Long puntuacion) {
		this.puntuacion = puntuacion;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PaePaises paePaises = (PaePaises) o;
		return Objects.equals(idPais, paePaises.idPais) && Objects.equals(codigo, paePaises.codigo) && Objects
				.equals(nombre, paePaises.nombre) && Objects.equals(grupo, paePaises.grupo) && Objects
				.equals(puntuacion, paePaises.puntuacion);
	}

	@Override public int hashCode() {
		return Objects.hash(idPais, codigo, nombre, grupo, puntuacion);
	}

	@OneToMany(mappedBy = "paePaisesByFkPais") public Collection<PaeMedidasPais> getPaeMedidasPaisByIdPais() {
		return paeMedidasPaisByIdPais;
	}

	public void setPaeMedidasPaisByIdPais(Collection<PaeMedidasPais> paeMedidasPaisByIdPais) {
		this.paeMedidasPaisByIdPais = paeMedidasPaisByIdPais;
	}
}

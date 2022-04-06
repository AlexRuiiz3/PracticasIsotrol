package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "AACI_ENTIDAD_CONTRAPARTE", schema = "AACID_OWNER", catalog = "") public class EntidadContraparte {
	private Integer idEntidad;
	private String nombreEntidad;

	@Id @Column(name = "ENTI_NU_ID") public Integer getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(Integer idEntidad) {
		this.idEntidad = idEntidad;
	}

	@Basic @Column(name = "ENTI_LI_NOMBRE") public String getNombreEntidad() {
		return nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		EntidadContraparte that = (EntidadContraparte) o;
		return Objects.equals(idEntidad, that.idEntidad) && Objects.equals(nombreEntidad, that.nombreEntidad);
	}

	@Override public int hashCode() {
		return Objects.hash(idEntidad, nombreEntidad);
	}
}

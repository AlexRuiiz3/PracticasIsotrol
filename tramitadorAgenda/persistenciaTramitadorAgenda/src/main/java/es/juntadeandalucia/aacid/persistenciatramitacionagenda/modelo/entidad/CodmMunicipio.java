package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "AACI_TM_CODM_MUNICIPIO", schema = "AACID_OWNER", catalog = "") public class CodmMunicipio {
	
	private String idCodMunicipio;
	private String codmCoPro;
	private String codmCoMun;
	private String codmLiNombre;

	@Basic @Column(name = "CODM_CO_PRO") public String getCodmCoPro() {
		return codmCoPro;
	}

	public void setCodmCoPro(String codmCoPro) {
		this.codmCoPro = codmCoPro;
	}

	@Basic @Column(name = "CODM_CO_MUN") public String getCodmCoMun() {
		return codmCoMun;
	}

	public void setCodmCoMun(String codmCoMun) {
		this.codmCoMun = codmCoMun;
	}

	@Basic @Column(name = "CODM_LI_NOMBRE") public String getCodmLiNombre() {
		return codmLiNombre;
	}

	public void setCodmLiNombre(String codmLiNombre) {
		this.codmLiNombre = codmLiNombre;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CodmMunicipio that = (CodmMunicipio) o;
		return Objects.equals(codmCoPro, that.codmCoPro) && Objects.equals(codmCoMun, that.codmCoMun) && Objects
				.equals(codmLiNombre, that.codmLiNombre);
	}

	@Override public int hashCode() {
		return Objects.hash(codmCoPro, codmCoMun, codmLiNombre);
	}

	@Id
	public String getIdCodMunicipio() {
		return idCodMunicipio;
	}

	public void setIdCodMunicipio(String idCodMunicipio) {
		this.idCodMunicipio = idCodMunicipio;
	}
}

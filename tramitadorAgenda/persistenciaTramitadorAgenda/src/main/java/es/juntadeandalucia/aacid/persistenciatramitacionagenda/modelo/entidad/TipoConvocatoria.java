package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_T_TIPO_CONV", schema = "AACID_OWNER", catalog = "")
public class TipoConvocatoria {
	private Long tconvNuId;
	private String tconvLiDenominacion;
	private Collection<TipoCatalogo> aaciTipoCatalogosByTconvNuId;
	private List<TipoExclusion> aaciTipoExclusionsByTconvNuId;

	@GeneratedValue
	@Id
	@Column(name = "TCONV_NU_ID", nullable = false, precision = 0)
	public Long getTconvNuId() {
		return tconvNuId;
	}

	public void setTconvNuId(Long tconvNuId) {
		this.tconvNuId = tconvNuId;
	}

	@Basic
	@Column(name = "TCONV_LI_DENOMINACION", nullable = true, length = 56)
	public String getTconvLiDenominacion() {
		return tconvLiDenominacion;
	}

	public void setTconvLiDenominacion(String tconvLiDenominacion) {
		this.tconvLiDenominacion = tconvLiDenominacion;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TipoConvocatoria that = (TipoConvocatoria) o;
		return Objects.equals(tconvNuId, that.tconvNuId)
				&& Objects.equals(tconvLiDenominacion, that.tconvLiDenominacion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tconvNuId, tconvLiDenominacion);
	}

	@OneToMany(mappedBy = "aaciTTipoConvByTconvNuId")
	public Collection<TipoCatalogo> getAaciTipoCatalogosByTconvNuId() {
		return aaciTipoCatalogosByTconvNuId;
	}

	public void setAaciTipoCatalogosByTconvNuId(Collection<TipoCatalogo> aaciTipoCatalogosByTconvNuId) {
		this.aaciTipoCatalogosByTconvNuId = aaciTipoCatalogosByTconvNuId;
	}

	@OneToMany(mappedBy = "aaciTTipoConvByTconvNuId")
	public Collection<TipoExclusion> getAaciTipoExclusionsByTconvNuId() {
		return aaciTipoExclusionsByTconvNuId;
	}

	public void setAaciTipoExclusionsByTconvNuId(List<TipoExclusion> aaciTipoExclusionsByTconvNuId) {
		this.aaciTipoExclusionsByTconvNuId = aaciTipoExclusionsByTconvNuId;
	}
}

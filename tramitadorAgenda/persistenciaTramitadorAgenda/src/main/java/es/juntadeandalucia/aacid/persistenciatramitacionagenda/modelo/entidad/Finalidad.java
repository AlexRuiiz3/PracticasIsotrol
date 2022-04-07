package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name = "AACI_FINALIDAD", schema = "AACID_OWNER", catalog = "") 
public class Finalidad {
	private Integer finaNuId;
	private String finaTxDescripcion;
	private String finaCoCodigo;
	private TipoConvocatoria aaciTTipoConvByTconNuId;
	private Collection<GastosFinalidad> aaciGastosFinalidades;
	private Collection<TiposCriterios> aaciTCriteriosByFinaNuId;

	@Id @Column(name = "FINA_NU_ID") public Integer getFinaNuId() {
		return finaNuId;
	}

	public void setFinaNuId(Integer finaNuId) {
		this.finaNuId = finaNuId;
	}

	@Basic @Column(name = "FINA_TX_DESCRIPCION") public String getFinaTxDescripcion() {
		return finaTxDescripcion;
	}

	public void setFinaTxDescripcion(String finaTxDescripcion) {
		this.finaTxDescripcion = finaTxDescripcion;
	}

	@Basic @Column(name = "FINA_CO_CODIGO") public String getFinaCoCodigo() {
		return finaCoCodigo;
	}

	public void setFinaCoCodigo(String finaCoCodigo) {
		this.finaCoCodigo = finaCoCodigo;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Finalidad that = (Finalidad) o;
		return Objects.equals(finaNuId, that.finaNuId) && Objects.equals(finaTxDescripcion, that.finaTxDescripcion)
				&& Objects.equals(finaCoCodigo, that.finaCoCodigo);
	}

	@Override public int hashCode() {
		return Objects.hash(finaNuId, finaTxDescripcion, finaCoCodigo);
	}

	@ManyToOne @JoinColumn(name = "TCON_NU_ID", referencedColumnName = "TCONV_NU_ID") public TipoConvocatoria getAaciTTipoConvByTconNuId() {
		return aaciTTipoConvByTconNuId;
	}

	public void setAaciTTipoConvByTconNuId(TipoConvocatoria aaciTTipoConvByTconNuId) {
		this.aaciTTipoConvByTconNuId = aaciTTipoConvByTconNuId;
	}

  @OneToMany(mappedBy = "aaciFinalidad")
  public Collection<GastosFinalidad> getAaciGastosFinalidades() {
		return aaciGastosFinalidades;
	}

	public void setAaciGastosFinalidades(Collection<GastosFinalidad> aaciGastosFinalidades) {
		this.aaciGastosFinalidades = aaciGastosFinalidades;
	}

	@OneToMany(mappedBy = "aaciFinalidad") public Collection<TiposCriterios> getAaciTCriteriosByFinaNuId() {
		return aaciTCriteriosByFinaNuId;
	}

	public void setAaciTCriteriosByFinaNuId(Collection<TiposCriterios> aaciTCriteriosByFinaNuId) {
		this.aaciTCriteriosByFinaNuId = aaciTCriteriosByFinaNuId;
	}
}

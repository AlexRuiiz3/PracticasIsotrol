package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_CATALOGO_SUBSANACION", schema = "AACID_OWNER", catalog = "")
public class CatalogoSubsanacion {
	private Integer csubNuId;
	private String motivo;
	private Catalogo aaciCatalogoByCatNuId;
	private Subsanacion aaciSubsanacionBySubNuId;

	public CatalogoSubsanacion(Integer csubNuId, String motivo, Catalogo aaciCatalogoByCatNuId) {
		this.csubNuId = csubNuId;
		this.motivo = motivo;
		this.aaciCatalogoByCatNuId = aaciCatalogoByCatNuId;
	}

	public CatalogoSubsanacion() {
		super();
	}

	public CatalogoSubsanacion(Integer csubNuId, String motivo, Catalogo aaciCatalogoByCatNuId,
			Subsanacion aaciSubsanacionBySubNuId) {
		this.csubNuId = csubNuId;
		this.motivo = motivo;
		this.aaciCatalogoByCatNuId = aaciCatalogoByCatNuId;
		this.aaciSubsanacionBySubNuId = aaciSubsanacionBySubNuId;
	}

	@Id
	@Column(name = "CSUB_NU_ID", nullable = false, precision = 0)
	@SequenceGenerator(name = "seqCatalogoSubsanacion", sequenceName = "AACI_SEQ_CATALOGO_SUBSANACION", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCatalogoSubsanacion")
	public Integer getCsubNuId() {
		return csubNuId;
	}

	public void setCsubNuId(Integer csubNuId) {
		this.csubNuId = csubNuId;
	}

	@Basic
	@Column(name = "MOTIVO", nullable = true, length = 4000)
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CatalogoSubsanacion that = (CatalogoSubsanacion) o;
		return Objects.equals(csubNuId, that.csubNuId) && Objects.equals(motivo, that.motivo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(csubNuId, motivo);
	}

	@ManyToOne
	@JoinColumn(name = "CAT_NU_ID", referencedColumnName = "CAT_NU_ID")
	public Catalogo getAaciCatalogoByCatNuId() {
		return aaciCatalogoByCatNuId;
	}

	public void setAaciCatalogoByCatNuId(Catalogo aaciCatalogoByCatNuId) {
		this.aaciCatalogoByCatNuId = aaciCatalogoByCatNuId;
	}

	@ManyToOne
	@JoinColumn(name = "SUB_NU_ID", referencedColumnName = "SUB_NU_ID")
	public Subsanacion getAaciSubsanacionBySubNuId() {
		return aaciSubsanacionBySubNuId;
	}

	public void setAaciSubsanacionBySubNuId(Subsanacion aaciSubsanacionBySubNuId) {
		this.aaciSubsanacionBySubNuId = aaciSubsanacionBySubNuId;
	}

}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_TIPO_CATALOGO", schema = "AACID_OWNER", catalog = "")
public class TipoCatalogo {
	private Integer tcatNuId;
	private String tcatCoCodigo;
	private String tcatLiDescripcion;
	private Integer tcatNuAnio;
	private Boolean tcatLoValorador;
	private Boolean tcatLoDocumentacion;
	private Boolean tcatLoComunicacion;
	private List<Catalogo> aaciCatalogosByTcatNuId;
	private TipoConvocatoria aaciTTipoConvByTconvNuId;


	@Id
	@Column(name = "TCAT_NU_ID", nullable = false, precision = 0)
	@SequenceGenerator(name = "seqTipoCatologo", sequenceName = "AACI_SEQ_TIPO_CATALOGO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTipoCatologo")
	public Integer getTcatNuId() {
		return tcatNuId;
	}

	public void setTcatNuId(Integer tcatNuId) {
		this.tcatNuId = tcatNuId;
	}

	@Basic
	@Column(name = "TCAT_CO_CODIGO", nullable = true, length = 10)
	public String getTcatCoCodigo() {
		return tcatCoCodigo;
	}

	public void setTcatCoCodigo(String tcatCoCodigo) {
		this.tcatCoCodigo = tcatCoCodigo;
	}

	@Basic
	@Column(name = "TCAT_LI_DESCRIPCION", nullable = true, length = 1000)
	public String getTcatLiDescripcion() {
		return tcatLiDescripcion;
	}

	public void setTcatLiDescripcion(String tcatLiDescripcion) {
		this.tcatLiDescripcion = tcatLiDescripcion;
	}

	@Basic
	@Column(name = "TCAT_NU_ANIO", nullable = true, precision = 0)
	public Integer getTcatNuAnio() {
		return tcatNuAnio;
	}

	public void setTcatNuAnio(Integer tcatNuAnio) {
		this.tcatNuAnio = tcatNuAnio;
	}

	@Basic
	@Column(name = "TCAT_LO_VALORADOR", nullable = true, precision = 0)
	public Boolean getTcatLoValorador() {
		return tcatLoValorador;
	}

	public void setTcatLoValorador(Boolean tcatLoValorador) {
		this.tcatLoValorador = tcatLoValorador;
	}

	@Basic
	@Column(name = "TCAT_LO_DOCUMENTACION", nullable = true, precision = 0)
	public Boolean getTcatLoDocumentacion() {
		return tcatLoDocumentacion;
	}

	public void setTcatLoDocumentacion(Boolean tcatLoDocumentacion) {
		this.tcatLoDocumentacion = tcatLoDocumentacion;
	}

	@Basic
	@Column(name = "TCAT_LO_COMUNICACION", nullable = true, precision = 0)
	public Boolean getTcatLoComunicacion() {
		return tcatLoComunicacion;
	}

	public void setTcatLoComunicacion(Boolean tcatLoComunicacion) {
		this.tcatLoComunicacion = tcatLoComunicacion;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TipoCatalogo that = (TipoCatalogo) o;
		return Objects.equals(tcatNuId, that.tcatNuId) && Objects.equals(tcatCoCodigo, that.tcatCoCodigo)
				&& Objects.equals(tcatLiDescripcion, that.tcatLiDescripcion)
				&& Objects.equals(tcatNuAnio, that.tcatNuAnio) && Objects.equals(tcatLoValorador, that.tcatLoValorador)
				&& Objects.equals(tcatLoDocumentacion, that.tcatLoDocumentacion)
				&& Objects.equals(tcatLoComunicacion, that.tcatLoComunicacion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tcatNuId, tcatCoCodigo, tcatLiDescripcion, tcatNuAnio, tcatLoValorador, tcatLoDocumentacion,
				tcatLoComunicacion);
	}

	@OneToMany(mappedBy = "aaciTipoCatalogoByTcatNuId")
	public Collection<Catalogo> getAaciCatalogosByTcatNuId() {
		return aaciCatalogosByTcatNuId;
	}

	public void setAaciCatalogosByTcatNuId(List<Catalogo> aaciCatalogosByTcatNuId) {
		this.aaciCatalogosByTcatNuId = aaciCatalogosByTcatNuId;
	}

	@ManyToOne @JoinColumn(name = "TCONV_NU_ID", referencedColumnName = "TCONV_NU_ID", nullable = false)
	public TipoConvocatoria getAaciTTipoConvByTconvNuId() {
		return aaciTTipoConvByTconvNuId;
	}

	public void setAaciTTipoConvByTconvNuId(TipoConvocatoria aaciTTipoConvByTconvNuId) {
		this.aaciTTipoConvByTconvNuId = aaciTTipoConvByTconvNuId;
	}


}

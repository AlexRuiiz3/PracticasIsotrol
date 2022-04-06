package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "AACI_CRITERIOS_CONV", schema = "AACID_OWNER", catalog = "") 
public class CriteriosConvocatoria {

	private Long crcoNuId;
	private Long crcoNuPeso;
	private Convocatoria convocatoria;
	private Criterios criterios;
	private Finalidad finalidad;
	private String crcoTxInstructivo;
	
	
	@Id
	@Column(name = "CRCO_NU_ID", nullable = false, precision = 10)
	@SequenceGenerator(name = "seqCriterioConv", sequenceName = "AACI_SEQ_ENTIDADES_AGRUP", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCriterioConv")
	public Long getCrcoNuId() {
		return crcoNuId;
	}
	public void setCrcoNuId(Long crcoNuId) {
		this.crcoNuId = crcoNuId;
	}
	@Basic
	@Column(name = "CRCO_NU_PESO", nullable = true)
	public Long getCrcoNuPeso() {
		return crcoNuPeso;
	}
	public void setCrcoNuPeso(Long crcoNuPeso) {
		this.crcoNuPeso = crcoNuPeso;
	}
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "CONV_NU_ID")
	public Convocatoria getConvocatoria() {
		return convocatoria;
	}
	public void setConvocatoria(Convocatoria convNuId) {
		this.convocatoria = convNuId;
	}
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "CRIT_NU_ID")
	public Criterios getCriterios() {
		return criterios;
	}
	public void setCriterios(Criterios criterios) {
		this.criterios = criterios;
	}
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne
	@JoinColumn(name = "FINA_NU_ID" )
	public Finalidad getFinalidad() {
		return finalidad;
	}
	public void setFinalidad(Finalidad finaNuId) {
		this.finalidad = finaNuId;
	}
	public String getCrcoTxInstructivo() {
		return crcoTxInstructivo;
	}
	@Basic
	@Column(name = "CRCO_NU_PESO", nullable = true, length = 4000)
	public void setCrcoTxInstructivo(String crcoTxInstructivo) {
		this.crcoTxInstructivo = crcoTxInstructivo;
	}
	
}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity @Table(name = "PAE_CONTRAPARTES") public class PaeContrapartes {
	private Long idContraparte;
	private String contraparte;
	private String otras;
	private String localidad;
	private String pais;
	private PaeSolicitudes paeSolicitudesByFkSolicitud;
	private PaeTEntidadContraparte paeTEntidadContraparteByFkTEntidad;
	private List<PaeConContrapartes> paeConContrapartesByIdContraparte;
//    private int indice;
//    private String entidadSel;
	@Id @Column(name = "ID_CONTRAPARTE") public Long getIdContraparte() {
		return idContraparte;
	}

	public void setIdContraparte(Long idContraparte) {
		this.idContraparte = idContraparte;
	}

	@Basic @Column(name = "CONTRAPARTE") public String getContraparte() {
		return contraparte;
	}

	public void setContraparte(String contraparte) {
		this.contraparte = contraparte;
	}

	@Basic @Column(name = "OTRAS") public String getOtras() {
		return otras;
	}

	public void setOtras(String otras) {
		this.otras = otras;
	}

	@Basic @Column(name = "LOCALIDAD") public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	@Basic @Column(name = "PAIS") public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PaeContrapartes that = (PaeContrapartes) o;
		return Objects.equals(idContraparte, that.idContraparte) && Objects.equals(contraparte, that.contraparte)
				&& Objects.equals(otras, that.otras) && Objects.equals(localidad, that.localidad) && Objects
				.equals(pais, that.pais);
	}

	@Override public int hashCode() {
		return Objects.hash(idContraparte, contraparte, otras, localidad, pais);
	}

	@ManyToOne @JoinColumn(name = "FK_SOLICITUD", referencedColumnName = "ID_SOLICITUD") public PaeSolicitudes getPaeSolicitudesByFkSolicitud() {
		return paeSolicitudesByFkSolicitud;
	}

	public void setPaeSolicitudesByFkSolicitud(PaeSolicitudes paeSolicitudesByFkSolicitud) {
		this.paeSolicitudesByFkSolicitud = paeSolicitudesByFkSolicitud;
	}

	@ManyToOne @JoinColumn(name = "FK_T_ENTIDAD", referencedColumnName = "ID_T_ENT_CONTRAPARTE") public PaeTEntidadContraparte getPaeTEntidadContraparteByFkTEntidad() {
		return paeTEntidadContraparteByFkTEntidad;
	}

	public void setPaeTEntidadContraparteByFkTEntidad(PaeTEntidadContraparte paeTEntidadContraparteByFkTEntidad) {
		this.paeTEntidadContraparteByFkTEntidad = paeTEntidadContraparteByFkTEntidad;
	}
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "paeContrapartesByFkEntidad") public List<PaeConContrapartes> getPaeConContrapartesByIdContraparte() {
		return paeConContrapartesByIdContraparte;
	}

	public void setPaeConContrapartesByIdContraparte(List<PaeConContrapartes> paeConContrapartesByIdContraparte) {
		this.paeConContrapartesByIdContraparte = paeConContrapartesByIdContraparte;
	}
	
}

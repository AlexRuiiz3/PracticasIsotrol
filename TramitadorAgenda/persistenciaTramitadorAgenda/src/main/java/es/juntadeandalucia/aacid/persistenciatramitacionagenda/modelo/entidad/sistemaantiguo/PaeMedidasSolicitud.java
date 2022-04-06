package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name = "PAE_MEDIDAS_SOLICITUD") public class PaeMedidasSolicitud {
	private PaeSolicitudes paeSolicitudesByFkSolicitud;
	private Integer id;
	@ManyToOne @JoinColumn(name = "FK_SOLICITUD", referencedColumnName = "ID_SOLICITUD") public PaeSolicitudes getPaeSolicitudesByFkSolicitud() {
		return paeSolicitudesByFkSolicitud;
	}

	public void setPaeSolicitudesByFkSolicitud(PaeSolicitudes paeSolicitudesByFkSolicitud) {
		this.paeSolicitudesByFkSolicitud = paeSolicitudesByFkSolicitud;
	}
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}

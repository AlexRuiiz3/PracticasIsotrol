package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name = "PAE_MEDIDAS_SOL_MODIFICADO") public class PaeMedidasSolModificado {
	private Integer id;
	private PaeSolicitudes paeSolicitudesByFkSolicitud;
	private PaeMedidasPais paeMedidasPaisByFkMedida;

	@ManyToOne @JoinColumn(name = "FK_SOLICITUD", referencedColumnName = "ID_SOLICITUD") public PaeSolicitudes getPaeSolicitudesByFkSolicitud() {
		return paeSolicitudesByFkSolicitud;
	}

	public void setPaeSolicitudesByFkSolicitud(PaeSolicitudes paeSolicitudesByFkSolicitud) {
		this.paeSolicitudesByFkSolicitud = paeSolicitudesByFkSolicitud;
	}

	@ManyToOne @JoinColumn(name = "FK_MEDIDA", referencedColumnName = "ID_MED_PAIS") public PaeMedidasPais getPaeMedidasPaisByFkMedida() {
		return paeMedidasPaisByFkMedida;
	}

	public void setPaeMedidasPaisByFkMedida(PaeMedidasPais paeMedidasPaisByFkMedida) {
		this.paeMedidasPaisByFkMedida = paeMedidasPaisByFkMedida;
	}
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}

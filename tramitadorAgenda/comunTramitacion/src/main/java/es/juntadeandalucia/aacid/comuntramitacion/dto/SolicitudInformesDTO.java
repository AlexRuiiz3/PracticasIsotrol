package es.juntadeandalucia.aacid.comuntramitacion.dto;

public class SolicitudInformesDTO {

	private String numExpTrewa;
	private String titulo;
	private String poblacion;
	private String entidad;
	private String cif;
	private String importe;
	private String causas;

	public String getNumExpTrewa() {
		return numExpTrewa;
	}

	public void setNumExpTrewa(String numExpTrewa) {
		this.numExpTrewa = numExpTrewa;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getCausas() {
		return causas;
	}

	public void setCausas(String causas) {
		this.causas = causas;
	}
}

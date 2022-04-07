package es.juntadeandalucia.aacid.comuntramitacion.dto;

public class SubsanacionSeleccionadaDTO implements java.io.Serializable {

	private static final long serialVersionUID = 2067648870812864528L;
	private String codigo;
	private String motivo;

	public SubsanacionSeleccionadaDTO() {
		super();
	}

	public SubsanacionSeleccionadaDTO(String codigo, String motivo) {
		this.codigo = codigo;
		this.motivo = motivo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
}

package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import es.juntadeandalucia.aacid.comuntramitacion.validation.annotation.FechaAnteriorActual;

/**
 * DatosSubsanacionDTO class.
 * 
 * @author isotrol
 *
 */
public class DatosSubsanacionDTO implements java.io.Serializable {

	private static final long serialVersionUID = -5740922360086689767L;

	private String idExp;
	private String idExpTrewa;
	private String tituloProy;
	private String idExpInterno;
	private Long idSubsanacion;
	private List<CatalogoSubsanacionDTO> catalogosSeleccionados;
	private String observaciones;
	private String observacionesDes;
	private Date fechaSubsanacion;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@FechaAnteriorActual
	private Date fechaRegistro;
	private Date fechaLimite;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
 	@FechaAnteriorActual
	private Date fechaEntrega;
	private Date fechaEntrada;
	private String lugarRegistro;
	private Boolean almeria;
	private Boolean cadiz;
	private Boolean cordoba;
	private Boolean granada;
	private Boolean huelva;
	private Boolean jaen;
	private Boolean malaga;
	private Boolean sevilla;
	private Boolean africa;
	private Boolean servicios;
	private Boolean prevRiesgos;
	private Boolean otros;
	private Boolean noEspecifica;


	/**
	 * @return the idExp
	 */
	public String getIdExp() {
		return idExp;
	}

	/**
	 * @param idExp the idExp to set
	 */
	public void setIdExp(String idExp) {
		this.idExp = idExp;
	}

	/**
	 * @return the idExpTrewa
	 */
	public String getIdExpTrewa() {
		return idExpTrewa;
	}

	/**
	 * @param idExpTrewa the idExpTrewa to set
	 */
	public void setIdExpTrewa(String idExpTrewa) {
		this.idExpTrewa = idExpTrewa;
	}

	/**
	 * @return the tituloProy
	 */
	public String getTituloProy() {
		return tituloProy;
	}

	/**
	 * @param tituloProy the tituloProy to set
	 */
	public void setTituloProy(String tituloProy) {
		this.tituloProy = tituloProy;
	}

	/**
	 * @return the idExpInterno
	 */
	public String getIdExpInterno() {
		return idExpInterno;
	}

	/**
	 * @param idExpInterno the idExpInterno to set
	 */
	public void setIdExpInterno(String idExpInterno) {
		this.idExpInterno = idExpInterno;
	}

	public List<CatalogoSubsanacionDTO> getCatalogosSeleccionados() {
		return catalogosSeleccionados;
	}

	public void setCatalogosSeleccionados(List<CatalogoSubsanacionDTO> catalogosSeleccionados) {
		this.catalogosSeleccionados = catalogosSeleccionados;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the observacionesDes
	 */
	public String getObservacionesDes() {
		return observacionesDes;
	}

	/**
	 * @param observacionesDes the observacionesDes to set
	 */
	public void setObservacionesDes(String observacionesDes) {
		this.observacionesDes = observacionesDes;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdSubsanacion() {
		return idSubsanacion;
	}

	public void setIdSubsanacion(Long idSubsanacion) {
		this.idSubsanacion = idSubsanacion;
	}

	public Date getFechaSubsanacion() {
		return fechaSubsanacion;
	}

	public void setFechaSubsanacion(Date fechaSubsanacion) {
		this.fechaSubsanacion = fechaSubsanacion;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getLugarRegistro() {
		return lugarRegistro;
	}

	public void setLugarRegistro(String lugarRegistro) {
		this.lugarRegistro = lugarRegistro;
	}

	public Boolean getAlmeria() {
		return almeria;
	}

	public void setAlmeria(Boolean almeria) {
		this.almeria = almeria;
	}

	public Boolean getCadiz() {
		return cadiz;
	}

	public void setCadiz(Boolean cadiz) {
		this.cadiz = cadiz;
	}

	public Boolean getCordoba() {
		return cordoba;
	}

	public void setCordoba(Boolean cordoba) {
		this.cordoba = cordoba;
	}

	public Boolean getGranada() {
		return granada;
	}

	public void setGranada(Boolean granada) {
		this.granada = granada;
	}

	public Boolean getHuelva() {
		return huelva;
	}

	public void setHuelva(Boolean huelva) {
		this.huelva = huelva;
	}

	public Boolean getJaen() {
		return jaen;
	}

	public void setJaen(Boolean jaen) {
		this.jaen = jaen;
	}

	public Boolean getMalaga() {
		return malaga;
	}

	public void setMalaga(Boolean malaga) {
		this.malaga = malaga;
	}

	public Boolean getSevilla() {
		return sevilla;
	}

	public void setSevilla(Boolean sevilla) {
		this.sevilla = sevilla;
	}

	public Boolean getAfrica() {
		return africa;
	}

	public void setAfrica(Boolean africa) {
		this.africa = africa;
	}

	public Boolean getServicios() {
		return servicios;
	}

	public void setServicios(Boolean servicios) {
		this.servicios = servicios;
	}

	public Boolean getPrevRiesgos() {
		return prevRiesgos;
	}

	public void setPrevRiesgos(Boolean prevRiesgos) {
		this.prevRiesgos = prevRiesgos;
	}

	public Boolean getOtros() {
		return otros;
	}

	public void setOtros(Boolean otros) {
		this.otros = otros;
	}

	public Boolean getNoEspecifica() {
		return noEspecifica;
	}

	public void setNoEspecifica(Boolean noEspecifica) {
		this.noEspecifica = noEspecifica;
	}
}

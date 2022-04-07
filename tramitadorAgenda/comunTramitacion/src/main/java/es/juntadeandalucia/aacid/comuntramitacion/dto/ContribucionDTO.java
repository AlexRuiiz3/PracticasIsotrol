package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.math.BigDecimal;
import java.util.List;

public class ContribucionDTO {
  private Long idContribucion;
  private BigDecimal total;
  private BigDecimal contraparte;
  private BigDecimal aacid;
  private BigDecimal publicas;
  private BigDecimal solicitante;
  private BigDecimal privadas;
  private BigDecimal localesPublicas;
  private BigDecimal localesPrivadas;
  private BigDecimal subtotalExterior;
  private BigDecimal subtotalLocal;
  private Long fkProyectos;
  private Long fkProgramacion;
  private BigDecimal aacidValidada;
  private BigDecimal solicitanteValidada;
  private BigDecimal aacidNoValidada;
  private BigDecimal solicitanteNoValidada;
  private BigDecimal aacidEmergencia;
  private BigDecimal solicitanteEmergencia;
  private GastoDTO gastos;
  private List<ConContraparteDTO> listaContrapartesDTO;
  private List<ConOtrasAportacionesDTO> listaOtrasAportaciones;

  public ContribucionDTO() {
    // Constructor por defecto
  }

  public Long getIdContribucion() {
    return idContribucion;
  }

  public void setIdContribucion(final Long idContribucion) {
    this.idContribucion = idContribucion;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(final BigDecimal total) {
    this.total = total;
  }

  public BigDecimal getContraparte() {
    return contraparte;
  }

  public void setContraparte(final BigDecimal contraparte) {
    this.contraparte = contraparte;
  }

  public BigDecimal getAacid() {
    return aacid;
  }

  public void setAacid(final BigDecimal aacid) {
    this.aacid = aacid;
  }

  public BigDecimal getPublicas() {
    return publicas;
  }

  public void setPublicas(final BigDecimal publicas) {
    this.publicas = publicas;
  }

  public BigDecimal getSolicitante() {
    return solicitante;
  }

  public void setSolicitante(final BigDecimal solicitante) {
    this.solicitante = solicitante;
  }

  public BigDecimal getPrivadas() {
    return privadas;
  }

  public void setPrivadas(final BigDecimal privadas) {
    this.privadas = privadas;
  }

  public BigDecimal getLocalesPublicas() {
    return localesPublicas;
  }

  public void setLocalesPublicas(final BigDecimal localesPublicas) {
    this.localesPublicas = localesPublicas;
  }

  public BigDecimal getLocalesPrivadas() {
    return localesPrivadas;
  }

  public void setLocalesPrivadas(final BigDecimal localesPrivadas) {
    this.localesPrivadas = localesPrivadas;
  }

  public BigDecimal getSubtotalExterior() {
    return subtotalExterior;
  }

  public void setSubtotalExterior(final BigDecimal subtotalExterior) {
    this.subtotalExterior = subtotalExterior;
  }

  public BigDecimal getSubtotalLocal() {
    return subtotalLocal;
  }

  public void setSubtotalLocal(final BigDecimal subtotalLocal) {
    this.subtotalLocal = subtotalLocal;
  }

  public Long getFkProyectos() {
    return fkProyectos;
  }

  public void setFkProyectos(final Long fkProyectos) {
    this.fkProyectos = fkProyectos;
  }

  public Long getFkProgramacion() {
    return fkProgramacion;
  }

  public void setFkProgramacion(final Long fkProgramacion) {
    this.fkProgramacion = fkProgramacion;
  }

  public BigDecimal getAacidValidada() {
    return aacidValidada;
  }

  public void setAacidValidada(final BigDecimal aacidValidada) {
    this.aacidValidada = aacidValidada;
  }

  public BigDecimal getSolicitanteValidada() {
    return solicitanteValidada;
  }

  public void setSolicitanteValidada(final BigDecimal solicitanteValidada) {
    this.solicitanteValidada = solicitanteValidada;
  }

  public BigDecimal getAacidNoValidada() {
    return aacidNoValidada;
  }

  public void setAacidNoValidada(final BigDecimal aacidNoValidada) {
    this.aacidNoValidada = aacidNoValidada;
  }

  public BigDecimal getSolicitanteNoValidada() {
    return solicitanteNoValidada;
  }

  public void setSolicitanteNoValidada(final BigDecimal solicitanteNoValidada) {
    this.solicitanteNoValidada = solicitanteNoValidada;
  }

  public BigDecimal getAacidEmergencia() {
    return aacidEmergencia;
  }

  public void setAacidEmergencia(final BigDecimal aacidEmergencia) {
    this.aacidEmergencia = aacidEmergencia;
  }

  public BigDecimal getSolicitanteEmergencia() {
    return solicitanteEmergencia;
  }

  public void setSolicitanteEmergencia(final BigDecimal solicitanteEmergencia) {
    this.solicitanteEmergencia = solicitanteEmergencia;
  }

  public GastoDTO getGastos() {
    return gastos;
  }

  public void setGastos(final GastoDTO gastos) {
    this.gastos = gastos;
  }

  public List<ConContraparteDTO> getListaContrapartesDTO() {
    return listaContrapartesDTO;
  }

  public void setListaContrapartesDTO(final List<ConContraparteDTO> listaContrapartesDTO) {
    this.listaContrapartesDTO = listaContrapartesDTO;
  }

  public List<ConOtrasAportacionesDTO> getListaOtrasAportaciones() {
    return listaOtrasAportaciones;
  }

  public void setListaOtrasAportaciones(final List<ConOtrasAportacionesDTO> listaOtrasAportaciones) {
    this.listaOtrasAportaciones = listaOtrasAportaciones;
  }
}

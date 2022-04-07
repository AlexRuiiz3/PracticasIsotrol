package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "PAE_CONTRIBUCIONES")
public class PaeContribuciones {
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
  private BigDecimal aacidValidada;
  private BigDecimal solicitanteValidada;
  private BigDecimal aacidNoValidada;
  private BigDecimal solicitanteNoValidada;
  private BigDecimal aacidEmergencia;
  private BigDecimal solicitanteEmergencia;
  private PaeSolicitudes paeSolicitudes;
  private List<PaeConContrapartes> paeConContrapartesByIdContribucion;
  private List<PaeConOtrasAportaciones> listaOtrasAportaciones;
  private PaeProgramacion paeProgramacion;
  private PaeGastos paeGastos;

  @Id
  @Column(name = "ID_CONTRIBUCION")
  public Long getIdContribucion() {
    return idContribucion;
  }

  public void setIdContribucion(final Long idContribucion) {
    this.idContribucion = idContribucion;
  }

  @Basic
  @Column(name = "TOTAL")
  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(final BigDecimal total) {
    this.total = total;
  }

  @Basic
  @Column(name = "CONTRAPARTE")
  public BigDecimal getContraparte() {
    return contraparte;
  }

  public void setContraparte(final BigDecimal contraparte) {
    this.contraparte = contraparte;
  }

  @Basic
  @Column(name = "AACID")
  public BigDecimal getAacid() {
    return aacid;
  }

  public void setAacid(final BigDecimal aacid) {
    this.aacid = aacid;
  }

  @Basic
  @Column(name = "PUBLICAS")
  public BigDecimal getPublicas() {
    return publicas;
  }

  public void setPublicas(final BigDecimal publicas) {
    this.publicas = publicas;
  }

  @Basic
  @Column(name = "SOLICITANTE")
  public BigDecimal getSolicitante() {
    return solicitante;
  }

  public void setSolicitante(final BigDecimal solicitante) {
    this.solicitante = solicitante;
  }

  @Basic
  @Column(name = "PRIVADAS")
  public BigDecimal getPrivadas() {
    return privadas;
  }

  public void setPrivadas(final BigDecimal privadas) {
    this.privadas = privadas;
  }

  @Basic
  @Column(name = "LOCALES_PUBLICAS")
  public BigDecimal getLocalesPublicas() {
    return localesPublicas;
  }

  public void setLocalesPublicas(final BigDecimal localesPublicas) {
    this.localesPublicas = localesPublicas;
  }

  @Basic
  @Column(name = "LOCALES_PRIVADAS")
  public BigDecimal getLocalesPrivadas() {
    return localesPrivadas;
  }

  public void setLocalesPrivadas(final BigDecimal localesPrivadas) {
    this.localesPrivadas = localesPrivadas;
  }

  @Basic
  @Column(name = "SUBTOTAL_EXTERIOR")
  public BigDecimal getSubtotalExterior() {
    return subtotalExterior;
  }

  public void setSubtotalExterior(final BigDecimal subtotalExterior) {
    this.subtotalExterior = subtotalExterior;
  }

  @Basic
  @Column(name = "SUBTOTAL_LOCAL")
  public BigDecimal getSubtotalLocal() {
    return subtotalLocal;
  }

  public void setSubtotalLocal(final BigDecimal subtotalLocal) {
    this.subtotalLocal = subtotalLocal;
  }

  @Basic
  @Column(name = "AACID_VALIDADA")
  public BigDecimal getAacidValidada() {
    return aacidValidada;
  }

  public void setAacidValidada(final BigDecimal aacidValidada) {
    this.aacidValidada = aacidValidada;
  }

  @Basic
  @Column(name = "SOLICITANTE_VALIDADA")
  public BigDecimal getSolicitanteValidada() {
    return solicitanteValidada;
  }

  public void setSolicitanteValidada(final BigDecimal solicitanteValidada) {
    this.solicitanteValidada = solicitanteValidada;
  }

  @Basic
  @Column(name = "AACID_NO_VALIDADA")
  public BigDecimal getAacidNoValidada() {
    return aacidNoValidada;
  }

  public void setAacidNoValidada(final BigDecimal aacidNoValidada) {
    this.aacidNoValidada = aacidNoValidada;
  }

  @Basic
  @Column(name = "SOLICITANTE_NO_VALIDADA")
  public BigDecimal getSolicitanteNoValidada() {
    return solicitanteNoValidada;
  }

  public void setSolicitanteNoValidada(final BigDecimal solicitanteNoValidada) {
    this.solicitanteNoValidada = solicitanteNoValidada;
  }

  @Basic
  @Column(name = "AACID_EMERGENCIA")
  public BigDecimal getAacidEmergencia() {
    return aacidEmergencia;
  }

  public void setAacidEmergencia(final BigDecimal aacidEmergencia) {
    this.aacidEmergencia = aacidEmergencia;
  }

  @Basic
  @Column(name = "SOLICITANTE_EMERGENCIA")
  public BigDecimal getSolicitanteEmergencia() {
    return solicitanteEmergencia;
  }

  public void setSolicitanteEmergencia(final BigDecimal solicitanteEmergencia) {
    this.solicitanteEmergencia = solicitanteEmergencia;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PaeContribuciones that = (PaeContribuciones) o;
    return Objects.equals(idContribucion, that.idContribucion) && Objects.equals(total, that.total) && Objects.equals(contraparte, that.contraparte)
        && Objects.equals(aacid, that.aacid) && Objects.equals(publicas, that.publicas) && Objects.equals(solicitante, that.solicitante)
        && Objects.equals(privadas, that.privadas) && Objects.equals(localesPublicas, that.localesPublicas)
        && Objects.equals(localesPrivadas, that.localesPrivadas) && Objects.equals(subtotalExterior, that.subtotalExterior)
        && Objects.equals(subtotalLocal, that.subtotalLocal) && Objects.equals(aacidValidada, that.aacidValidada)
        && Objects.equals(solicitanteValidada, that.solicitanteValidada) && Objects.equals(aacidNoValidada, that.aacidNoValidada)
        && Objects.equals(solicitanteNoValidada, that.solicitanteNoValidada) && Objects.equals(aacidEmergencia, that.aacidEmergencia)
        && Objects.equals(solicitanteEmergencia, that.solicitanteEmergencia);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idContribucion, total, contraparte, aacid, publicas, solicitante, privadas, localesPublicas, localesPrivadas, subtotalExterior,
        subtotalLocal, aacidValidada, solicitanteValidada, aacidNoValidada, solicitanteNoValidada, aacidEmergencia, solicitanteEmergencia);
  }

  @ManyToOne
  @JoinColumn(name = "FK_PROYECTOS", referencedColumnName = "ID_SOLICITUD")
  public PaeSolicitudes getPaeSolicitudes() {
    return paeSolicitudes;
  }

  public void setPaeSolicitudes(final PaeSolicitudes paeSolicitudes) {
    this.paeSolicitudes = paeSolicitudes;
  }

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy = "paeContribucionesByFkContribucion")
  public List<PaeConContrapartes> getPaeConContrapartesByIdContribucion() {
    return paeConContrapartesByIdContribucion;
  }

  public void setPaeConContrapartesByIdContribucion(final List<PaeConContrapartes> paeConContrapartesByIdContribucion) {
    this.paeConContrapartesByIdContribucion = paeConContrapartesByIdContribucion;
  }

  /**
   * Getter de listaOtrasAportaciones
   *
   * @return listaOtrasAportaciones Set<PaeOtrasAportaciones>
   */
  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "paeContribucion")
  public List<PaeConOtrasAportaciones> getListaOtrasAportaciones() {
    return listaOtrasAportaciones;
  }

  /**
   * Setter de listaOtrasAportaciones
   *
   * @param listaOtrasAportaciones
   *          Set<PaeOtrasAportaciones>
   */
  public void setListaOtrasAportaciones(final List<PaeConOtrasAportaciones> listaOtrasAportaciones) {
    this.listaOtrasAportaciones = listaOtrasAportaciones;
  }

  /**
   * Getter de paeProgramación
   *
   * @return paeProgramacion PaeProgramacion
   */
  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne
  @JoinColumn(name = "FK_PROGRAMACION")
  public PaeProgramacion getPaeProgramacion() {
    return paeProgramacion;
  }

  /**
   * Setter de paeProgramacion
   *
   * @param paeProgramacion
   *          PaeProgramacion
   */
  public void setPaeProgramacion(final PaeProgramacion paeProgramacion) {
    this.paeProgramacion = paeProgramacion;
  }

  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne
  @JoinColumn(name = "FK_COSTES")
  public PaeGastos getPaeGastos() {
    return paeGastos;
  }

  public void setPaeGastos(final PaeGastos paeGastos) {
    this.paeGastos = paeGastos;
  }
}

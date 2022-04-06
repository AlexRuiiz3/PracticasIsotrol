package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "AACI_CONTRIBUCIONES", schema = "AACID_OWNER", catalog = "")
public class Contribuciones {
  private Long idContribucion;
  private BigDecimal total;
  private BigDecimal aacid;
  private BigDecimal solicitante;
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
  private Set<ConContraparte> conContrapartesByIdContribucion;
  private List<ConOtrasAportaciones> listaOtrasAportaciones;
  private Gasto gasto;

  // no utilizado
  private BigDecimal privadas;
  // no utilizado
  private BigDecimal localesPublicas;
  // no utilizado
  private BigDecimal localesPrivadas;
  // no utilizado
  private BigDecimal publicas;
  // no utilizado
  private BigDecimal contraparte;

  @Id
  @SequenceGenerator(name = "seqContribucion", sequenceName = "AACI_SEQ_CONTRIBUCIONES", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqContribucion")
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
  @Column(name = "FK_PROYECTOS")
  public Long getFkProyectos() {
    return fkProyectos;
  }

  public void setFkProyectos(final Long fkProyectos) {
    this.fkProyectos = fkProyectos;
  }

  @Basic
  @Column(name = "FK_PROGRAMACION")
  public Long getFkProgramacion() {
    return fkProgramacion;
  }

  public void setFkProgramacion(final Long fkProgramacion) {
    this.fkProgramacion = fkProgramacion;
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
    final Contribuciones that = (Contribuciones) o;
    return Objects.equals(idContribucion, that.idContribucion) && Objects.equals(total, that.total) && Objects.equals(contraparte, that.contraparte)
        && Objects.equals(aacid, that.aacid) && Objects.equals(publicas, that.publicas) && Objects.equals(solicitante, that.solicitante)
        && Objects.equals(privadas, that.privadas) && Objects.equals(localesPublicas, that.localesPublicas)
        && Objects.equals(localesPrivadas, that.localesPrivadas) && Objects.equals(subtotalExterior, that.subtotalExterior)
        && Objects.equals(subtotalLocal, that.subtotalLocal) && Objects.equals(fkProyectos, that.fkProyectos)
        && Objects.equals(fkProgramacion, that.fkProgramacion) && Objects.equals(aacidValidada, that.aacidValidada)
        && Objects.equals(solicitanteValidada, that.solicitanteValidada) && Objects.equals(aacidNoValidada, that.aacidNoValidada)
        && Objects.equals(solicitanteNoValidada, that.solicitanteNoValidada) && Objects.equals(aacidEmergencia, that.aacidEmergencia)
        && Objects.equals(solicitanteEmergencia, that.solicitanteEmergencia);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idContribucion, total, contraparte, aacid, publicas, solicitante, privadas, localesPublicas, localesPrivadas, subtotalExterior,
        subtotalLocal, fkProyectos, fkProgramacion, aacidValidada, solicitanteValidada, aacidNoValidada, solicitanteNoValidada, aacidEmergencia,
        solicitanteEmergencia);
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "aaciContribucionesByFkContribucion")
  public Set<ConContraparte> getAaciConContrapartesByIdContribucion() {
    return conContrapartesByIdContribucion;
  }

  public void setAaciConContrapartesByIdContribucion(final Set<ConContraparte> conContrapartesByIdContribucion) {
    this.conContrapartesByIdContribucion = conContrapartesByIdContribucion;
  }

  /**
   * Getter de listaOtrasAportaciones
   *
   * @return listaOtrasAportaciones Set<PaeOtrasAportaciones>
   */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "contribuciones")
  public List<ConOtrasAportaciones> getListaOtrasAportaciones() {
    return listaOtrasAportaciones;
  }

  /**
   * Setter de listaOtrasAportaciones
   *
   * @param listaOtrasAportaciones
   *          Set<PaeOtrasAportaciones>
   */
  public void setListaOtrasAportaciones(final List<ConOtrasAportaciones> listaOtrasAportaciones) {
    this.listaOtrasAportaciones = listaOtrasAportaciones;
  }

  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne
  @JoinColumn(name = "FK_COSTES", referencedColumnName = "GAST_NU_ID")
  public Gasto getGasto() {
    return gasto;
  }

  public void setGasto(final Gasto gasto) {
    this.gasto = gasto;
  }

}

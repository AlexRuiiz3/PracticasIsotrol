package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "PAE_CON_OTRAS")
public class PaeConOtrasAportaciones {

  private Long idConOtra;
  private PaeContribuciones paeContribucion;
  private BigDecimal contribucion;
  private BigDecimal contribucionValidada;
  private BigDecimal contribucionNoValidada;
  private BigDecimal otrasEmergencia;
  private PaeEntidadesParticipantes paeEntidadesParticipantes;

  @Id
  @Column(name = "ID_CON_OTRA", nullable = false, precision = 10, scale = 0)
  public Long getIdConOtra() {
    return idConOtra;
  }

  /**
   * Setter de idConOtra
   *
   * @param idSolTem
   *          long
   */
  public void setIdConOtra(final long idSolTem) {
    idConOtra = idSolTem;
  }

  /**
   * Getter de paeContribucion
   *
   * @return paeContribucion PaeContribuciones
   */
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinColumn(name = "FK_CONTRIBUCION", referencedColumnName = "ID_CONTRIBUCION")
  @ManyToOne
  public PaeContribuciones getPaeContribucion() {
    return paeContribucion;
  }

  /**
   * Setter de paeContribucion
   *
   * @param paeContribucion
   *          PaeContribuciones
   */
  public void setPaeContribucion(final PaeContribuciones paeContribucion) {
    this.paeContribucion = paeContribucion;
  }

  /**
   * Getter de contribucion
   *
   * @return contribucion Double
   */
  @Column(name = "CONTRIBUCION", precision = 10)
  public BigDecimal getContribucion() {
    return contribucion;
  }

  /**
   * Setter de contribucion
   *
   * @param contribucion
   *          Double
   */
  public void setContribucion(final BigDecimal contribucion) {
    this.contribucion = contribucion;
  }

  @Column(name = "CONTRIBUCION_VALIDADA", precision = 10, scale = 2)
  public BigDecimal getContribucionValidada() {
    return contribucionValidada;
  }

  public void setContribucionValidada(final BigDecimal contribucionValidada) {
    this.contribucionValidada = contribucionValidada;
  }

  @Column(name = "CONTRIBUCION_NO_VALIDADA", precision = 10, scale = 2)
  public BigDecimal getContribucionNoValidada() {
    return contribucionNoValidada;
  }

  public void setContribucionNoValidada(final BigDecimal contribucionNoValidada) {
    this.contribucionNoValidada = contribucionNoValidada;
  }

  @Column(name = "OTRAS_EMERGENCIA", precision = 10, scale = 2)
  public BigDecimal getOtrasEmergencia() {
    return otrasEmergencia;
  }

  public void setOtrasEmergencia(final BigDecimal otrasEmergencia) {
    this.otrasEmergencia = otrasEmergencia;
  }

  /**
   * Getter de paeOtrasEntidades
   *
   * @return paeOtrasEntidades PaeEntidadesParticipantes
   */
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinColumn(name = "FK_ENTIDAD", referencedColumnName = "ID_ENT_PARTICIPANTE")
  @ManyToOne
  public PaeEntidadesParticipantes getPaeEntidadesParticipantes() {
    return paeEntidadesParticipantes;
  }

  public void setPaeEntidadesParticipantes(final PaeEntidadesParticipantes paeEntidadesParticipantes) {
    this.paeEntidadesParticipantes = paeEntidadesParticipantes;
  }
}
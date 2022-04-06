package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "AACI_CON_OTRAS", catalog = "")
public class ConOtrasAportaciones {
  private Long idConOtra;
  private Contribuciones contribuciones;
  private BigDecimal contribucion;
  private BigDecimal contribucionValidada;
  private BigDecimal contribucionNoValidada;
  private BigDecimal otrasEmergencia;
  private EntidadesParticipantes entidadesParticipantes;

  @Id
  @SequenceGenerator(name = "seqOtrasContraparte", sequenceName = "AACI_SEQ_CON_OTRAS", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqOtrasContraparte")
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
  @ManyToOne
  @JoinColumn(name = "FK_CONTRIBUCION")
  public Contribuciones getContribuciones() {
    return contribuciones;
  }

  /**
   * Setter de paeContribucion
   *
   * @param paeContribucion
   *          PaeContribuciones
   */
  public void setContribuciones(final Contribuciones contribuciones) {
    this.contribuciones = contribuciones;
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

  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinColumn(name = "FK_ENTIDAD")
  @ManyToOne
  public EntidadesParticipantes getEntidadesParticipantes() {
    return entidadesParticipantes;
  }

  public void setEntidadesParticipantes(final EntidadesParticipantes entidadesParticipantes) {
    this.entidadesParticipantes = entidadesParticipantes;
  }
}

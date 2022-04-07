package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Basic;
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
@Table(name = "AACI_CON_CONTRAPARTE", schema = "AACID_OWNER", catalog = "")
public class ConContraparte {
  private Long idConContra;
  private BigDecimal contribucion;
  private Long fkProgramacion;
  private String localidad;
  private String pais;
  private BigDecimal contribucionValidada;
  private BigDecimal contribucionNoValidada;
  private BigDecimal contraparteEmergencia;
  private Contribuciones aaciContribucionesByFkContribucion;
  private Contrapartes aaciContrapartesByFkEntidad;

  @Id
  @SequenceGenerator(name = "seqConContra", sequenceName = "AACI_SEQ_CON_CONTRAPARTE", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqConContra")
  @Column(name = "ID_CON_CONTRA")
  public Long getIdConContra() {
    return idConContra;
  }

  public void setIdConContra(final Long idConContra) {
    this.idConContra = idConContra;
  }

  @Basic
  @Column(name = "CONTRIBUCION")
  public BigDecimal getContribucion() {
    return contribucion;
  }

  public void setContribucion(final BigDecimal contribucion) {
    this.contribucion = contribucion;
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
  @Column(name = "LOCALIDAD")
  public String getLocalidad() {
    return localidad;
  }

  public void setLocalidad(final String localidad) {
    this.localidad = localidad;
  }

  @Basic
  @Column(name = "PAIS")
  public String getPais() {
    return pais;
  }

  public void setPais(final String pais) {
    this.pais = pais;
  }

  @Basic
  @Column(name = "CONTRIBUCION_VALIDADA")
  public BigDecimal getContribucionValidada() {
    return contribucionValidada;
  }

  public void setContribucionValidada(final BigDecimal contribucionValidada) {
    this.contribucionValidada = contribucionValidada;
  }

  @Basic
  @Column(name = "CONTRIBUCION_NO_VALIDADA")
  public BigDecimal getContribucionNoValidada() {
    return contribucionNoValidada;
  }

  public void setContribucionNoValidada(final BigDecimal contribucionNoValidada) {
    this.contribucionNoValidada = contribucionNoValidada;
  }

  @Basic
  @Column(name = "CONTRAPARTE_EMERGENCIA")
  public BigDecimal getContraparteEmergencia() {
    return contraparteEmergencia;
  }

  public void setContraparteEmergencia(final BigDecimal contraparteEmergencia) {
    this.contraparteEmergencia = contraparteEmergencia;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final ConContraparte that = (ConContraparte) o;
    return Objects.equals(idConContra, that.idConContra) && Objects.equals(contribucion, that.contribucion)
        && Objects.equals(fkProgramacion, that.fkProgramacion) && Objects.equals(localidad, that.localidad) && Objects.equals(pais, that.pais)
        && Objects.equals(contribucionValidada, that.contribucionValidada) && Objects.equals(contribucionNoValidada, that.contribucionNoValidada)
        && Objects.equals(contraparteEmergencia, that.contraparteEmergencia);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idConContra, contribucion, fkProgramacion, localidad, pais, contribucionValidada, contribucionNoValidada, contraparteEmergencia);
  }

  @ManyToOne
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinColumn(name = "FK_CONTRIBUCION", referencedColumnName = "ID_CONTRIBUCION")
  public Contribuciones getAaciContribucionesByFkContribucion() {
    return aaciContribucionesByFkContribucion;
  }

  public void setAaciContribucionesByFkContribucion(final Contribuciones aaciContribucionesByFkContribucion) {
    this.aaciContribucionesByFkContribucion = aaciContribucionesByFkContribucion;
  }

  @ManyToOne
  @JoinColumn(name = "FK_ENTIDAD", referencedColumnName = "CONT_NU_ID")
  public Contrapartes getAaciContrapartesByFkEntidad() {
    return aaciContrapartesByFkEntidad;
  }

  public void setAaciContrapartesByFkEntidad(final Contrapartes aaciContrapartesByFkEntidad) {
    this.aaciContrapartesByFkEntidad = aaciContrapartesByFkEntidad;
  }
}

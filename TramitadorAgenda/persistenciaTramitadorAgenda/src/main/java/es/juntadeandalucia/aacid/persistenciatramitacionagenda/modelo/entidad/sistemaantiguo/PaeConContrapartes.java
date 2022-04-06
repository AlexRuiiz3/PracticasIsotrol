package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "PAE_CON_CONTRAPARTES")
public class PaeConContrapartes {
  private Long idConContra;
  private BigDecimal contribucion;
  private String localidad;
  private String pais;
  private BigDecimal contribucionValidada;
  private BigDecimal contribucionNoValidada;
  private BigDecimal contraparteEmergencia;
  private PaeContrapartes paeContrapartesByFkEntidad;
  private PaeContribuciones paeContribucionesByFkContribucion;

  @Id
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
    final PaeConContrapartes that = (PaeConContrapartes) o;
    return Objects.equals(idConContra, that.idConContra) && Objects.equals(contribucion, that.contribucion) && Objects.equals(localidad, that.localidad)
        && Objects.equals(pais, that.pais) && Objects.equals(contribucionValidada, that.contribucionValidada)
        && Objects.equals(contribucionNoValidada, that.contribucionNoValidada) && Objects.equals(contraparteEmergencia, that.contraparteEmergencia);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idConContra, contribucion, localidad, pais, contribucionValidada, contribucionNoValidada, contraparteEmergencia);
  }

  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne
  @JoinColumn(name = "FK_ENTIDAD", referencedColumnName = "ID_CONTRAPARTE")
  public PaeContrapartes getPaeContrapartesByFkEntidad() {
    return paeContrapartesByFkEntidad;
  }

  public void setPaeContrapartesByFkEntidad(final PaeContrapartes paeContrapartesByFkEntidad) {
    this.paeContrapartesByFkEntidad = paeContrapartesByFkEntidad;
  }

  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne
  @JoinColumn(name = "FK_CONTRIBUCION", referencedColumnName = "ID_CONTRIBUCION")
  public PaeContribuciones getPaeContribucionesByFkContribucion() {
    return paeContribucionesByFkContribucion;
  }

  public void setPaeContribucionesByFkContribucion(final PaeContribuciones paeContribucionesByFkContribucion) {
    this.paeContribucionesByFkContribucion = paeContribucionesByFkContribucion;
  }
}

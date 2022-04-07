package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entidad de persistencia para la tabla AACI_TM_CAPACIDAD_GESTION de base de datos.
 * 
 */
@Entity
@Table(name = "AACI_TM_CAPACIDAD_GESTION")
@NamedQuery(name = "CapacidadGestion.findAll", query = "SELECT c FROM CapacidadGestion c")
public class CapacidadGestion implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "TMCG_NU_ID")
  private Long tmcgNuId;

  @Column(name = "TMCG_NU_PUNT_TOTAL")
  private BigDecimal tmcgNuPuntTotal;

  @Column(name = "TMCG_TX_CIF")
  private String tmcgTxCif;

  @Column(name = "TMCG_TX_ENT")
  private String tmcgTxEnt;

  @Column(name = "TMCG_NU_ANIO")
  private Long tmcgNuAnio;

  public CapacidadGestion() {
    // Constructor por defecto
  }

  public Long getTmcgNuId() {
    return this.tmcgNuId;
  }

  public void setTmcgNuId(Long tmcgNuId) {
    this.tmcgNuId = tmcgNuId;
  }

  public BigDecimal getTmcgNuPuntTotal() {
    return this.tmcgNuPuntTotal;
  }

  public void setTmcgNuPuntTotal(BigDecimal tmcgNuPuntTotal) {
    this.tmcgNuPuntTotal = tmcgNuPuntTotal;
  }

  public String getTmcgTxCif() {
    return this.tmcgTxCif;
  }

  public void setTmcgTxCif(String tmcgTxCif) {
    this.tmcgTxCif = tmcgTxCif;
  }

  public String getTmcgTxEnt() {
    return this.tmcgTxEnt;
  }

  public void setTmcgTxEnt(String tmcgTxEnt) {
    this.tmcgTxEnt = tmcgTxEnt;
  }

  public Long getTmcgNuAnio() {
    return tmcgNuAnio;
  }

  public void setTmcgNuAnio(Long tmcgNuAnio) {
    this.tmcgNuAnio = tmcgNuAnio;
  }
}
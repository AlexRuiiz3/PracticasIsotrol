package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PAE_GASTOS")
public class PaeGastos {
  
  private long idCoste;
  
  private String coste;
  
  private String codigo;
  
  private List<PaeContribuciones> paeContribucioneses;
  
  private long orden;
  
 public PaeGastos() {}
  
  public PaeGastos(long idCoste) {
    this.idCoste = idCoste;
  }
  
  public PaeGastos(long idCoste, String coste, List<PaeContribuciones> paeContribucioneses) {
    this.idCoste = idCoste;
    this.coste = coste;
    this.paeContribucioneses = paeContribucioneses;
  }
  
  @Id
  @Column(name = "ID_COSTE", nullable = false, precision = 10, scale = 0)
  public long getIdCoste() {
    return this.idCoste;
  }
  
  public void setIdCoste(long idCoste) {
    this.idCoste = idCoste;
  }
  
  @Column(name = "COSTE", length = 100)
  public String getCoste() {
    return this.coste;
  }
  
  public void setCoste(String coste) {
    this.coste = coste;
  }
  
  @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "paeGastos")
  public List<PaeContribuciones> getPaeContribucioneses() {
    return this.paeContribucioneses;
  }
  
  public void setPaeContribucioneses(List<PaeContribuciones> paeContribucioneses) {
    this.paeContribucioneses = paeContribucioneses;
  }
  
  @Column(name = "CODIGO", length = 50)
  public String getCodigo() {
    return this.codigo;
  }
  
  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }
  
  public String toString() {
    return this.codigo;
  }
  
  @Column(name = "ORDEN", nullable = true, precision = 10, scale = 0)
  public long getOrden() {
    return this.orden;
  }
  
  public void setOrden(long orden) {
    this.orden = orden;
  }
}

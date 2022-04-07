package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PAE_TPAE_SOLICITUD")
public class PaeTipoSolicitud {

  private long idTipo;
  private String denominacion;
  private List<PaeSolicitudes> paeSolicitudeses = new ArrayList<>();

  /**
   * Getter de idTipo
   * 
   * @return idTipo long
   */
  @Id
  @Column(name = "FK_TIPO", nullable = false, precision = 10, scale = 0)
  public long getIdTipo() {
    return idTipo;
  }

  /**
   * Setter de idTipo
   * 
   * @param idTipo
   *          long
   */
  public void setIdTipo(long idTipo) {
    this.idTipo = idTipo;
  }

  /**
   * Getter de denominacion
   * 
   * @return denominacion String
   */
  @Column(name = "DENOMINACION", length = 22)
  public String getDenominacion() {
    return denominacion;
  }

  /**
   * Setter de denominacion
   * 
   * @param denominacion
   *          String
   */
  public void setDenominacion(String denominacion) {
    this.denominacion = denominacion;
  }

  /**
   * Getter de paeSolicitudeses
   * 
   * @return paeSolicitudeses Set<PaeSolicitudes>
   */
  @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY,mappedBy = "paeTipoSolicitud")
  public List<PaeSolicitudes> getPaeSolicitudeses() {
    return paeSolicitudeses;
  }

  /**
   * Setter de paeSolicitudeses
   * 
   * @param paeSolicitudeses
   *          Set<PaeSolicitudes>
   */
  public void setPaeSolicitudeses(List<PaeSolicitudes> paeSolicitudeses) {
    this.paeSolicitudeses = paeSolicitudeses;
  }

}

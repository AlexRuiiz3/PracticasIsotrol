package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PAE_CONVOCATORIAS")
public class PaeConvocatorias {

  private long idConvocatoria;
  private Long tIntervencion;
  private Date fecha;
  private List<PaeSolicitudes> paeSolicitudeses = new ArrayList<>(0);

  /**
   * Constructor vacío por defecto
   */
  public PaeConvocatorias() {
  }

  /**
   * Constructor con un parámetro
   *
   * @param idConvocatoria
   *          Clave primaria
   */
  public PaeConvocatorias(final long idConvocatoria) {
    this.idConvocatoria = idConvocatoria;
  }

  /**
   * Constructor con parámetros
   *
   * @param idConvocatoria
   *          Clave primaria
   * @param tIntervencion
   *          tIntervencion
   * @param fecha
   *          fecha
   * @param paeCriteriosConvocatorias
   *          PaeCriteriosConvocatorias
   * @param paeSolicitudeses
   *          PaeSolicitudeses
   */
  public PaeConvocatorias(final long idConvocatoria, final Long tIntervencion, final Date fecha, final List<PaeSolicitudes> paeSolicitudeses) {
    this.idConvocatoria = idConvocatoria;
    this.tIntervencion = tIntervencion;
    this.fecha = fecha;
    this.paeSolicitudeses = paeSolicitudeses;
  }

  /**
   * Getter de idConvocatoria
   *
   * @return idConvocatoria long
   */
  @Id
  @Column(name = "ID_CONVOCATORIA")
  public long getIdConvocatoria() {
    return idConvocatoria;
  }

  /**
   * Setter de idConvocatoria
   *
   * @param idConvocatoria
   *          long
   */
  public void setIdConvocatoria(final long idConvocatoria) {
    this.idConvocatoria = idConvocatoria;
  }

  /**
   * Getter de TIntervencion
   *
   * @return TIntervencion Long
   */
  @Column(name = "T_INTERVENCION", precision = 10, scale = 0)
  public Long getTIntervencion() {
    return tIntervencion;
  }

  /**
   * Setter de TIntervencion
   *
   * @param tIntervencion
   *          Long
   */
  public void setTIntervencion(final Long tIntervencion) {
    this.tIntervencion = tIntervencion;
  }

  /**
   * Getter de fecha
   *
   * @return fecha Date
   */
  @Temporal(TemporalType.DATE)
  @Column(name = "FECHA", length = 7)
  public Date getFecha() {
    return fecha;
  }

  /**
   * Setter de fecha
   *
   * @param fecha
   *          Date
   */
  public void setFecha(final Date fecha) {
    this.fecha = fecha;
  }

  /**
   * Getter de paeSolicitudeses
   *
   * @return paeSolicitudeses Set<PaeSolicitudes>
   */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paeConvocatorias")
  public List<PaeSolicitudes> getPaeSolicitudeses() {
    return paeSolicitudeses;
  }

  /**
   * Setter de paeSolicitudeses
   *
   * @param paeSolicitudeses
   *          Set<PaeSolicitudes>
   */
  public void setPaeSolicitudeses(final List<PaeSolicitudes> paeSolicitudeses) {
    this.paeSolicitudeses = paeSolicitudeses;
  }
}

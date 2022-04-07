package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PAE_PROGRAMACION")
public class PaeProgramacion {

  private long idProgramacion;
  private List<PaeContribuciones> paeContribuciones = new ArrayList<>();
  private PaeSolicitudes paeSolicitudes;
  private Long anio;

  /**
   * Getter de idProgramacion
   *
   * @return idProgramacion long
   */
  @Id
  @Column(name = "ID_PROGRAMACION", nullable = false, precision = 10, scale = 0)
  public long getIdProgramacion() {
    return idProgramacion;
  }

  /**
   * Setter de idProgramacion
   *
   * @param idProgramacion
   *          long
   */
  public void setIdProgramacion(final long idProgramacion) {
    this.idProgramacion = idProgramacion;
  }

  /**
   * Getter de paeSolicitud
   *
   * @return paeSolicitud PaeSolicitud
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FK_SOLICITUD")
  public PaeSolicitudes getPaeSolicitudes() {
    return paeSolicitudes;
  }

  /**
   * Setter de paeSolicitud
   *
   * @param paeSolicitud
   *          PaeSolicitudes
   */
  public void setPaeSolicitudes(final PaeSolicitudes paeSolicitud) {
    paeSolicitudes = paeSolicitud;
  }

  /**
   * Getter de paeContribuciones
   *
   * @return paeContribuciones Set<PAeContribuciones>
   */
  // @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "paeProgramacion")
  public List<PaeContribuciones> getPaeContribuciones() {
    return paeContribuciones;
  }

  /**
   * Setter de paeContribuciones
   *
   * @param paeContribuciones
   *          Set<PaeContribuciones>
   */
  public void setPaeContribuciones(final List<PaeContribuciones> paeContribuciones) {
    this.paeContribuciones = paeContribuciones;
  }

  /**
   * Getter de anio
   *
   * @return anio Long
   */
  @Column(name = "ANIO")
  public Long getAnio() {
    return anio;
  }

  /**
   * Setter de anio
   *
   * @param anio
   *          Long
   */
  public void setAnio(final Long anio) {
    this.anio = anio;
  }
}

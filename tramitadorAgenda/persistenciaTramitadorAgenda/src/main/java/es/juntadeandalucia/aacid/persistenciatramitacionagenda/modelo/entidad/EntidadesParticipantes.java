package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
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
@Table(name = "AACI_ENTIDADES_PARTICIPANTES", schema = "AACID_OWNER", catalog = "")
public class EntidadesParticipantes {
  private Long enpaNuId;
  private String enpaTxNombre;
  private String enpaTxOtraFuncion;
  private Funcion funcion;
  private Solicitud aaciTSolicitudsubongdByIdSolicitud;
  private TipoEntidadParticipante tipoEntidadParticipante;
  private List<ConOtrasAportaciones> conOtrasAportaciones;

  @Id
  @Column(name = "ENPA_NU_ID")
  public Long getId() {
    return enpaNuId;
  }

  public void setId(final Long enpaNuId) {
    this.enpaNuId = enpaNuId;
  }

  @Basic
  @Column(name = "ENPA_TX_NOMBRE")
  public String getNombre() {
    return enpaTxNombre;
  }

  public void setNombre(final String enpaTxNombre) {
    this.enpaTxNombre = enpaTxNombre;
  }

  @Basic
  @Column(name = "ENPA_TX_OTRA_FUNCION")
  public String getOtraFuncion() {
    return enpaTxOtraFuncion;
  }

  public void setOtraFuncion(final String enpaTxOtraFuncion) {
    this.enpaTxOtraFuncion = enpaTxOtraFuncion;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final EntidadesParticipantes that = (EntidadesParticipantes) o;
    return Objects.equals(enpaNuId, that.enpaNuId) && Objects.equals(enpaTxNombre, that.enpaTxNombre)
        && Objects.equals(enpaTxOtraFuncion, that.enpaTxOtraFuncion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(enpaNuId, enpaTxNombre, enpaTxOtraFuncion);
  }

  @ManyToOne
  @JoinColumn(name = "FUNC_NU_ID", referencedColumnName = "FUNC_NU_ID")
  public Funcion getFuncion() {
    return funcion;
  }

  public void setFuncion(final Funcion aaciFuncionesByFuncNuId) {
    funcion = aaciFuncionesByFuncNuId;
  }

  @ManyToOne
  @JoinColumn(name = "ID_SOLICITUD", referencedColumnName = "ID_SOLICITUD")
  public Solicitud getAaciTSolicitudsubongdByIdSolicitud() {
    return aaciTSolicitudsubongdByIdSolicitud;
  }

  public void setAaciTSolicitudsubongdByIdSolicitud(final Solicitud aaciTSolicitudsubongdByIdSolicitud) {
    this.aaciTSolicitudsubongdByIdSolicitud = aaciTSolicitudsubongdByIdSolicitud;
  }

  @ManyToOne
  @JoinColumn(name = "TEPA_NU_ID", referencedColumnName = "TEPA_NU_ID")
  public TipoEntidadParticipante getTipoEntidadParticipante() {
    return tipoEntidadParticipante;
  }

  public void setTipoEntidadParticipante(final TipoEntidadParticipante tipoEntidadParticipante) {
    this.tipoEntidadParticipante = tipoEntidadParticipante;
  }

  /**
   * Getter de paeConOtrasAportaciones
   *
   * @return paeConOtrasAportaciones Set<PaeConOtrasAportaciones>
   */

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "entidadesParticipantes")
  public List<ConOtrasAportaciones> getConOtrasAportaciones() {
    return conOtrasAportaciones;
  }

  public void setConOtrasAportaciones(final List<ConOtrasAportaciones> conOtrasAportaciones) {
    this.conOtrasAportaciones = conOtrasAportaciones;
  }
}

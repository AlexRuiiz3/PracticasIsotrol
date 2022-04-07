package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_T_CONVOCATORIAS", schema = "AACID_OWNER", catalog = "")
public class Convocatoria implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -7038688945900569003L;
  private Long convNuId;
  private Long convXExpe;
  private String convLiAbrevProcedimiento;
  private String convLiTitulo;
  private String convLiDescripcion;
  private Long convNuAnio;
  private Date convFhInicio;
  private Date convFhFin;
  private Date convFhValoracion;
  private Date convFhPropResolProv;
  private Date convFhLimitDocResolDef;
  private Date convFhResolConce;
  private TipoConvocatoria aaciTTipoConvByTconvNuId;
  private List<Solicitud> aaciTSolicitudsubongdsByConvNuId;
  private Set<ConvocatoriaFecha> convocatoriaFechas = new HashSet<>();

  @Id
  @Column(name = "CONV_NU_ID", nullable = false, precision = 0)
  @SequenceGenerator(name = "seqConvocatoria", sequenceName = "AACI_SEQ_CONV", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqConvocatoria")
  public Long getConvNuId() {
    return convNuId;
  }

  public void setConvNuId(final Long convNuId) {
    this.convNuId = convNuId;
  }

  @Basic
  @Column(name = "CONV_X_EXPE", nullable = true, precision = 0)
  public Long getConvXExpe() {
    return convXExpe;
  }

  public void setConvXExpe(final Long convXExpe) {
    this.convXExpe = convXExpe;
  }

  @Basic
  @Column(name = "CONV_LI_ABREV_PROCEDIMIENTO", nullable = true, length = 20)
  public String getConvLiAbrevProcedimiento() {
    return convLiAbrevProcedimiento;
  }

  public void setConvLiAbrevProcedimiento(final String convLiAbrevProcedimiento) {
    this.convLiAbrevProcedimiento = convLiAbrevProcedimiento;
  }

  @Basic
  @Column(name = "CONV_LI_TITULO", nullable = true, length = 1000)
  public String getConvLiTitulo() {
    return convLiTitulo;
  }

  public void setConvLiTitulo(final String convLiTitulo) {
    this.convLiTitulo = convLiTitulo;
  }

  @Basic
  @Column(name = "CONV_LI_DESCRIPCION", nullable = true, length = 4000)
  public String getConvLiDescripcion() {
    return convLiDescripcion;
  }

  public void setConvLiDescripcion(final String convLiDescripcion) {
    this.convLiDescripcion = convLiDescripcion;
  }

  @Basic
  @Column(name = "CONV_NU_ANIO", nullable = true, precision = 0)
  public Long getConvNuAnio() {
    return convNuAnio;
  }

  public void setConvNuAnio(final Long convNuAnio) {
    this.convNuAnio = convNuAnio;
  }

  @Basic
  @Column(name = "CONV_FH_INICIO", nullable = true)
  public Date getConvFhInicio() {
    return convFhInicio;
  }

  public void setConvFhInicio(final Date convFhInicio) {
    this.convFhInicio = convFhInicio;
  }

  @Basic
  @Column(name = "CONV_FH_FIN", nullable = true)
  public Date getConvFhFin() {
    return convFhFin;
  }

  public void setConvFhFin(final Date convFhFin) {
    this.convFhFin = convFhFin;
  }

  @Basic
  @Column(name = "CONV_FH_VALORACION", nullable = true)
  public Date getConvFhValoracion() {
    return convFhValoracion;
  }

  public void setConvFhValoracion(final Date convFhValoracion) {
    this.convFhValoracion = convFhValoracion;
  }

  @Basic
  @Column(name = "CONV_FH_PROP_RESOL_PROV", nullable = true)
  public Date getConvFhPropResolProv() {
    return convFhPropResolProv;
  }

  public void setConvFhPropResolProv(final Date convFhPropResolProv) {
    this.convFhPropResolProv = convFhPropResolProv;
  }

  @Basic
  @Column(name = "CONV_FH_LIMIT_DOC_RESOL_DEF", nullable = true)
  public Date getConvFhLimitDocResolDef() {
    return convFhLimitDocResolDef;
  }

  public void setConvFhLimitDocResolDef(final Date convFhLimitDocResolDef) {
    this.convFhLimitDocResolDef = convFhLimitDocResolDef;
  }

  @Basic
  @Column(name = "CONV_FH_RESOL_CONCE", nullable = true)
  public Date getConvFhResolConce() {
    return convFhResolConce;
  }

  public void setConvFhResolConce(final Date convFhResolConce) {
    this.convFhResolConce = convFhResolConce;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Convocatoria that = (Convocatoria) o;
    return Objects.equals(convNuId, that.convNuId) && Objects.equals(convXExpe, that.convXExpe)
        && Objects.equals(convLiAbrevProcedimiento, that.convLiAbrevProcedimiento) && Objects.equals(convLiTitulo, that.convLiTitulo)
        && Objects.equals(convLiDescripcion, that.convLiDescripcion) && Objects.equals(convNuAnio, that.convNuAnio)
        && Objects.equals(convFhInicio, that.convFhInicio) && Objects.equals(convFhFin, that.convFhFin)
        && Objects.equals(convFhValoracion, that.convFhValoracion) && Objects.equals(convFhPropResolProv, that.convFhPropResolProv)
        && Objects.equals(convFhLimitDocResolDef, that.convFhLimitDocResolDef) && Objects.equals(convFhResolConce, that.convFhResolConce);
  }

  @Override
  public int hashCode() {
    return Objects.hash(convNuId, convXExpe, convLiAbrevProcedimiento, convLiTitulo, convLiDescripcion, convNuAnio, convFhInicio, convFhFin, convFhValoracion,
        convFhPropResolProv, convFhLimitDocResolDef, convFhResolConce);
  }

  @ManyToOne
  @JoinColumn(name = "TCONV_NU_ID", referencedColumnName = "TCONV_NU_ID")
  public TipoConvocatoria getAaciTTipoConvByTconvNuId() {
    return aaciTTipoConvByTconvNuId;
  }

  public void setAaciTTipoConvByTconvNuId(final TipoConvocatoria aaciTTipoConvByTconvNuId) {
    this.aaciTTipoConvByTconvNuId = aaciTTipoConvByTconvNuId;
  }

  @OneToMany(mappedBy = "aaciTConvocatoriasByIdConvocatoria")
  public Collection<Solicitud> getAaciTSolicitudsubongdsByConvNuId() {
    return aaciTSolicitudsubongdsByConvNuId;
  }

  public void setAaciTSolicitudsubongdsByConvNuId(final List<Solicitud> aaciTSolicitudsubongdsByConvNuId) {
    this.aaciTSolicitudsubongdsByConvNuId = aaciTSolicitudsubongdsByConvNuId;
  }

  /**
   * Obtiene la propiedad convocatoriaFechas
   *
   * @return el convocatoriaFechas
   */
  @OneToMany(mappedBy = "convocatoria", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  public Set<ConvocatoriaFecha> getConvocatoriaFechas() {
    return convocatoriaFechas;
  }

  /**
   * Establece el valor de la propiedad convocatoriaFechas
   *
   * @param convocatoriaFechas
   *          el convocatoriaFechas para establecer
   */
  public void setConvocatoriaFechas(final Set<ConvocatoriaFecha> convocatoriaFechas) {
    this.convocatoriaFechas = convocatoriaFechas;
  }

}

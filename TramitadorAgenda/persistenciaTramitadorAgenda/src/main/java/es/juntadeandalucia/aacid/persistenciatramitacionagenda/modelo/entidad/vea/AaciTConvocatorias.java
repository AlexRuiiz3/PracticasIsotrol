package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea;

import java.sql.Time;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_T_CONVOCATORIAS", schema = "AACID_OWNER", catalog = "")
public class AaciTConvocatorias {
  private Long convNuId;
  private Long convXExpe;
  private String convLiAbrevProcedimiento;
  private String convLiTitulo;
  private String convLiDescripcion;
  private Long convNuAnio;
  private Time convFhInicio;
  private Time convFhFin;
  private Time convFhValoracion;
  private Time convFhPropResolProv;
  private Time convFhLimitDocResolDef;
  private Time convFhResolConce;
  private AaciTTipoConv aaciTTipoConvByTconvNuId;
  private List<AaciTSolicitudsubongd> aaciTSolicitudsubongdsByConvNuId;

  @Id
  @Column(name = "CONV_NU_ID")
  public Long getConvNuId() {
    return convNuId;
  }

  public void setConvNuId(final Long convNuId) {
    this.convNuId = convNuId;
  }

  @Basic
  @Column(name = "CONV_X_EXPE")
  public Long getConvXExpe() {
    return convXExpe;
  }

  public void setConvXExpe(final Long convXExpe) {
    this.convXExpe = convXExpe;
  }

  @Basic
  @Column(name = "CONV_LI_ABREV_PROCEDIMIENTO")
  public String getConvLiAbrevProcedimiento() {
    return convLiAbrevProcedimiento;
  }

  public void setConvLiAbrevProcedimiento(final String convLiAbrevProcedimiento) {
    this.convLiAbrevProcedimiento = convLiAbrevProcedimiento;
  }

  @Basic
  @Column(name = "CONV_LI_TITULO")
  public String getConvLiTitulo() {
    return convLiTitulo;
  }

  public void setConvLiTitulo(final String convLiTitulo) {
    this.convLiTitulo = convLiTitulo;
  }

  @Basic
  @Column(name = "CONV_LI_DESCRIPCION")
  public String getConvLiDescripcion() {
    return convLiDescripcion;
  }

  public void setConvLiDescripcion(final String convLiDescripcion) {
    this.convLiDescripcion = convLiDescripcion;
  }

  @Basic
  @Column(name = "CONV_NU_ANIO")
  public Long getConvNuAnio() {
    return convNuAnio;
  }

  public void setConvNuAnio(final Long convNuAnio) {
    this.convNuAnio = convNuAnio;
  }

  @Basic
  @Column(name = "CONV_FH_INICIO")
  public Time getConvFhInicio() {
    return convFhInicio;
  }

  public void setConvFhInicio(final Time convFhInicio) {
    this.convFhInicio = convFhInicio;
  }

  @Basic
  @Column(name = "CONV_FH_FIN")
  public Time getConvFhFin() {
    return convFhFin;
  }

  public void setConvFhFin(final Time convFhFin) {
    this.convFhFin = convFhFin;
  }

  @Basic
  @Column(name = "CONV_FH_VALORACION")
  public Time getConvFhValoracion() {
    return convFhValoracion;
  }

  public void setConvFhValoracion(final Time convFhValoracion) {
    this.convFhValoracion = convFhValoracion;
  }

  @Basic
  @Column(name = "CONV_FH_PROP_RESOL_PROV")
  public Time getConvFhPropResolProv() {
    return convFhPropResolProv;
  }

  public void setConvFhPropResolProv(final Time convFhPropResolProv) {
    this.convFhPropResolProv = convFhPropResolProv;
  }

  @Basic
  @Column(name = "CONV_FH_LIMIT_DOC_RESOL_DEF")
  public Time getConvFhLimitDocResolDef() {
    return convFhLimitDocResolDef;
  }

  public void setConvFhLimitDocResolDef(final Time convFhLimitDocResolDef) {
    this.convFhLimitDocResolDef = convFhLimitDocResolDef;
  }

  @Basic
  @Column(name = "CONV_FH_RESOL_CONCE")
  public Time getConvFhResolConce() {
    return convFhResolConce;
  }

  public void setConvFhResolConce(final Time convFhResolConce) {
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
    final AaciTConvocatorias that = (AaciTConvocatorias) o;
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
  public AaciTTipoConv getAaciTTipoConvByTconvNuId() {
    return aaciTTipoConvByTconvNuId;
  }

  public void setAaciTTipoConvByTconvNuId(final AaciTTipoConv aaciTTipoConvByTconvNuId) {
    this.aaciTTipoConvByTconvNuId = aaciTTipoConvByTconvNuId;
  }

  @OneToMany(mappedBy = "aaciTConvocatoriasByConvNuId")
  public List<AaciTSolicitudsubongd> getAaciTSolicitudsubongdsByConvNuId() {
    return aaciTSolicitudsubongdsByConvNuId;
  }

  public void setAaciTSolicitudsubongdsByConvNuId(final List<AaciTSolicitudsubongd> aaciTSolicitudsubongdsByConvNuId) {
    this.aaciTSolicitudsubongdsByConvNuId = aaciTSolicitudsubongdsByConvNuId;
  }
}

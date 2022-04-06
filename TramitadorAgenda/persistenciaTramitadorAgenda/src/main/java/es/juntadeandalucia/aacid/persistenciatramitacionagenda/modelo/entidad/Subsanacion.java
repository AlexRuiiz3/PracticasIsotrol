package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_SUBSANACION", schema = "AACID_OWNER", catalog = "")
public class Subsanacion {
  private Integer subNuId;
  private Date subFhSubsanacion;
  private String subLiObservaciones;
  private String subLiMotivoDesestimacion;
  private String subLiLugarRegistro;
  private Boolean subLgAlmeria;
  private Boolean subLgCadiz;
  private Boolean subLgCordoba;
  private Boolean subLgGranada;
  private Boolean subLgHuelva;
  private Boolean subLgJaen;
  private Boolean subLgMalaga;
  private Boolean subLgSevilla;
  private Boolean subLgAfrica;
  private Boolean subLgPrevRiesgos;
  private Boolean subLgServicios;
  private Boolean subLgOtros;
  private Boolean noEspecifica;
  private Date subFhRegistro;
  private Date subFhEntrada;
  private Date subFhLimite;
  private Date subFhEntrega;
  private List<CatalogoSubsanacion> aaciCatalogoSubsanacionsBySubNuId;
  private Solicitud aaciTSolicitudsubongdByFkSolicitud;

  public Subsanacion() {
  }

  public Subsanacion(final Integer subNuId, final Date subFhSubsanacion, final String subLiObservaciones, final String subLiMotivoDesestimacion,
      final String subLiLugarRegistro, final Boolean subLgAlmeria, final Boolean subLgCadiz, final Boolean subLgCordoba, final Boolean subLgGranada,
      final Boolean subLgHuelva, final Boolean subLgJaen, final Boolean subLgMalaga, final Boolean subLgSevilla, final Boolean subLgAfrica,
      final Boolean subLgPrevRiesgos, final Boolean subLgServicios, final Boolean subLgOtros, final Boolean noEspecifica, final Date subFhRegistro,
      final Date subFhEntrada, final Date subFhLimite, final Date subFhEntrega) {
    this.subNuId = subNuId;
    this.subFhSubsanacion = subFhSubsanacion;
    this.subLiObservaciones = subLiObservaciones;
    this.subLiMotivoDesestimacion = subLiMotivoDesestimacion;
    this.subLiLugarRegistro = subLiLugarRegistro;
    this.subLgAlmeria = subLgAlmeria;
    this.subLgCadiz = subLgCadiz;
    this.subLgCordoba = subLgCordoba;
    this.subLgGranada = subLgGranada;
    this.subLgHuelva = subLgHuelva;
    this.subLgJaen = subLgJaen;
    this.subLgMalaga = subLgMalaga;
    this.subLgSevilla = subLgSevilla;
    this.subLgAfrica = subLgAfrica;
    this.subLgPrevRiesgos = subLgPrevRiesgos;
    this.subLgServicios = subLgServicios;
    this.subLgOtros = subLgOtros;
    this.noEspecifica = noEspecifica;
    this.subFhRegistro = subFhRegistro;
    this.subFhEntrada = subFhEntrada;
    this.subFhLimite = subFhLimite;
    this.subFhEntrega = subFhEntrega;
  }

  @Id
  @Column(name = "SUB_NU_ID", nullable = false, precision = 0)
  @SequenceGenerator(name = "seqSubsanacion", sequenceName = "AACI_SEQ_SUBSANACION", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqSubsanacion")
  public Integer getSubNuId() {
    return subNuId;
  }

  public void setSubNuId(final Integer subNuId) {
    this.subNuId = subNuId;
  }

  @Basic
  @Column(name = "SUB_FH_SUBSANACION", nullable = true)
  public Date getSubFhSubsanacion() {
    return subFhSubsanacion;
  }

  public void setSubFhSubsanacion(final Date subFhSubsanacion) {
    this.subFhSubsanacion = subFhSubsanacion;
  }

  @Basic
  @Column(name = "SUB_LI_OBSERVACIONES", nullable = true, length = 4000)
  public String getSubLiObservaciones() {
    return subLiObservaciones;
  }

  public void setSubLiObservaciones(final String subLiObservaciones) {
    this.subLiObservaciones = subLiObservaciones;
  }

  @Basic
  @Column(name = "SUB_LI_MOTIVO_DESESTIMACION", nullable = true, length = 4000)
  public String getSubLiMotivoDesestimacion() {
    return subLiMotivoDesestimacion;
  }

  public void setSubLiMotivoDesestimacion(final String subLiMotivoDesestimacion) {
    this.subLiMotivoDesestimacion = subLiMotivoDesestimacion;
  }

  /**
   * Obtiene la propiedad subLiLugarRegistro
   *
   * @return el subLiLugarRegistro
   */
  @Column(name = "SUB_LI_LUGAR_REGISTRO", nullable = true, length = 4000)
  public String getSubLiLugarRegistro() {
    return subLiLugarRegistro;
  }

  /**
   * Establece el valor de la propiedad subLiLugarRegistro
   *
   * @param subLiLugarRegistro
   *          el subLiLugarRegistro para establecer
   */
  public void setSubLiLugarRegistro(final String subLiLugarRegistro) {
    this.subLiLugarRegistro = subLiLugarRegistro;
  }

  @Basic
  @Column(name = "SUB_LG_ALMERIA")
  public Boolean getSubLgAlmeria() {
    return subLgAlmeria;
  }

  public void setSubLgAlmeria(final Boolean subLgAlmeria) {
    this.subLgAlmeria = subLgAlmeria;
  }

  @Basic
  @Column(name = "SUB_LG_CADIZ")
  public Boolean getSubLgCadiz() {
    return subLgCadiz;
  }

  public void setSubLgCadiz(final Boolean subLgCadiz) {
    this.subLgCadiz = subLgCadiz;
  }

  @Basic
  @Column(name = "SUB_LG_CORDOBA")
  public Boolean getSubLgCordoba() {
    return subLgCordoba;
  }

  public void setSubLgCordoba(final Boolean subLgCordoba) {
    this.subLgCordoba = subLgCordoba;
  }

  @Basic
  @Column(name = "SUB_LG_GRANADA")
  public Boolean getSubLgGranada() {
    return subLgGranada;
  }

  public void setSubLgGranada(final Boolean subLgGranada) {
    this.subLgGranada = subLgGranada;
  }

  @Basic
  @Column(name = "SUB_LG_HUELVA")
  public Boolean getSubLgHuelva() {
    return subLgHuelva;
  }

  public void setSubLgHuelva(final Boolean subLgHuelva) {
    this.subLgHuelva = subLgHuelva;
  }

  @Basic
  @Column(name = "SUB_LG_JAEN")
  public Boolean getSubLgJaen() {
    return subLgJaen;
  }

  public void setSubLgJaen(final Boolean subLgJaen) {
    this.subLgJaen = subLgJaen;
  }

  @Basic
  @Column(name = "SUB_LG_MALAGA")
  public Boolean getSubLgMalaga() {
    return subLgMalaga;
  }

  public void setSubLgMalaga(final Boolean subLgMalaga) {
    this.subLgMalaga = subLgMalaga;
  }

  @Basic
  @Column(name = "SUB_LG_SEVILLA")
  public Boolean getSubLgSevilla() {
    return subLgSevilla;
  }

  public void setSubLgSevilla(final Boolean subLgSevilla) {
    this.subLgSevilla = subLgSevilla;
  }

  @Basic
  @Column(name = "SUB_LG_AFRICA")
  public Boolean getSubLgAfrica() {
    return subLgAfrica;
  }

  public void setSubLgAfrica(final Boolean subLgAfrica) {
    this.subLgAfrica = subLgAfrica;
  }

  /**
   * Obtiene la propiedad subLgPrevRiesgos
   *
   * @return el subLgPrevRiesgos
   */
  @Basic
  @Column(name = "SUB_LG_PREV_RIESGOS")
  public Boolean getSubLgPrevRiesgos() {
    return subLgPrevRiesgos;
  }

  /**
   * Establece el valor de la propiedad subLgPrevRiesgos
   *
   * @param subLgPrevRiesgos
   *          el subLgPrevRiesgos para establecer
   */
  public void setSubLgPrevRiesgos(final Boolean subLgPrevRiesgos) {
    this.subLgPrevRiesgos = subLgPrevRiesgos;
  }

  /**
   * Obtiene la propiedad subLgServicios
   *
   * @return el subLgServicios
   */
  @Basic
  @Column(name = "SUB_LG_SERVICIOS")
  public Boolean getSubLgServicios() {
    return subLgServicios;
  }

  /**
   * Establece el valor de la propiedad subLgServicios
   *
   * @param subLgServicios
   *          el subLgServicios para establecer
   */
  public void setSubLgServicios(final Boolean subLgServicios) {
    this.subLgServicios = subLgServicios;
  }

  /**
   * Obtiene la propiedad subLgOtros
   *
   * @return el subLgOtros
   */
  @Basic
  @Column(name = "SUB_LG_OTROS")
  public Boolean getSubLgOtros() {
    return subLgOtros;
  }

  /**
   * Establece el valor de la propiedad subLgOtros
   *
   * @param subLgOtros
   *          el subLgOtros para establecer
   */
  public void setSubLgOtros(final Boolean subLgOtros) {
    this.subLgOtros = subLgOtros;
  }

  /**
   * Obtiene la propiedad noEspecifica
   *
   * @return el noEspecifica
   */
  @Basic
  @Column(name = "SUB_LG_NO_ESPECIFICA")
  public Boolean getNoEspecifica() {
    return noEspecifica;
  }

  /**
   * Establece el valor de la propiedad noEspecifica
   *
   * @param noEspecifica
   *          el noEspecifica para establecer
   */
  public void setNoEspecifica(final Boolean noEspecifica) {
    this.noEspecifica = noEspecifica;
  }

  @Basic
  @Column(name = "SUB_FH_REGISTRO")
  public Date getSubFhRegistro() {
    return subFhRegistro;
  }

  public void setSubFhRegistro(final Date subFhRegistro) {
    this.subFhRegistro = subFhRegistro;
  }

  @Basic
  @Column(name = "SUB_FH_ENTRADA")
  public Date getSubFhEntrada() {
    return subFhEntrada;
  }

  public void setSubFhEntrada(final Date subFhEntrada) {
    this.subFhEntrada = subFhEntrada;
  }

  @Basic
  @Column(name = "SUB_FH_LIMITE")
  public Date getSubFhLimite() {
    return subFhLimite;
  }

  public void setSubFhLimite(final Date subFhLimite) {
    this.subFhLimite = subFhLimite;
  }

  /**
   * Obtiene la propiedad subFhEntrega
   *
   * @return el subFhEntrega
   */
  @Basic
  @Column(name = "SUB_FH_ENTREGA")
  public Date getSubFhEntrega() {
    return subFhEntrega;
  }

  /**
   * Establece el valor de la propiedad subFhEntrega
   *
   * @param subFhEntrega
   *          el subFhEntrega para establecer
   */
  public void setSubFhEntrega(final Date subFhEntrega) {
    this.subFhEntrega = subFhEntrega;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final Subsanacion that = (Subsanacion) o;
    return Objects.equals(subNuId, that.subNuId) && Objects.equals(subFhSubsanacion, that.subFhSubsanacion)
        && Objects.equals(subLiObservaciones, that.subLiObservaciones) && Objects.equals(subLiMotivoDesestimacion, that.subLiMotivoDesestimacion)
        && Objects.equals(subLgAlmeria, that.subLgAlmeria) && Objects.equals(subLgCadiz, that.subLgCadiz) && Objects.equals(subLgCordoba, that.subLgCordoba)
        && Objects.equals(subLgGranada, that.subLgGranada) && Objects.equals(subLgHuelva, that.subLgHuelva) && Objects.equals(subLgJaen, that.subLgJaen)
        && Objects.equals(subLgMalaga, that.subLgMalaga) && Objects.equals(subLgSevilla, that.subLgSevilla) && Objects.equals(subLgAfrica, that.subLgAfrica)
        && Objects.equals(subFhRegistro, that.subFhRegistro) && Objects.equals(subFhEntrada, that.subFhEntrada)
        && Objects.equals(subFhLimite, that.subFhLimite);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subNuId, subFhSubsanacion, subLiObservaciones, subLiMotivoDesestimacion, subLgAlmeria, subLgCadiz, subLgCordoba, subLgGranada,
        subLgHuelva, subLgJaen, subLgMalaga, subLgSevilla, subLgAfrica, subFhRegistro, subFhEntrada, subFhLimite);
  }

  @OneToMany(mappedBy = "aaciSubsanacionBySubNuId")
  public List<CatalogoSubsanacion> getAaciCatalogoSubsanacionsBySubNuId() {
    return aaciCatalogoSubsanacionsBySubNuId;
  }

  public void setAaciCatalogoSubsanacionsBySubNuId(final List<CatalogoSubsanacion> aaciCatalogoSubsanacionsBySubNuId) {
    this.aaciCatalogoSubsanacionsBySubNuId = aaciCatalogoSubsanacionsBySubNuId;
  }

  @ManyToOne
  @JoinColumn(name = "FK_SOLICITUD", referencedColumnName = "ID_SOLICITUD")
  public Solicitud getAaciTSolicitudsubongdByFkSolicitud() {
    return aaciTSolicitudsubongdByFkSolicitud;
  }

  public void setAaciTSolicitudsubongdByFkSolicitud(final Solicitud aaciTSolicitudsubongdByFkSolicitud) {
    this.aaciTSolicitudsubongdByFkSolicitud = aaciTSolicitudsubongdByFkSolicitud;
  }

}

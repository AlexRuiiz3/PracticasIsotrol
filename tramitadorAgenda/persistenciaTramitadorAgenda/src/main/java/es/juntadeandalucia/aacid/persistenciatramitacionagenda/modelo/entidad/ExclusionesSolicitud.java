package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AACI_EXCLUSIONES_SOLICITUD", schema = "AACID_OWNER", catalog = "")
public class ExclusionesSolicitud {
  private Long id;
  private Exclusion aaciExclusionByExclNuId;
  private Solicitud aaciTSolicitudsubongdBySoliNuId;

  /**
   * Obtiene la propiedad id
   * 
   * @return el id
   */
  @Id
  public Long getId() {
    return id;
  }

  /**
   * Establece el valor de la propiedad id
   * 
   * @param id
   *          el id para establecer
   */
  public void setId(Long id) {
    this.id = id;
  }

  @ManyToOne
  @JoinColumn(name = "EXCL_NU_ID", referencedColumnName = "EXCL_NU_ID")
  public Exclusion getAaciExclusionByExclNuId() {
    return aaciExclusionByExclNuId;
  }

  public void setAaciExclusionByExclNuId(Exclusion aaciExclusionByExclNuId) {
    this.aaciExclusionByExclNuId = aaciExclusionByExclNuId;
  }

  @ManyToOne
  @JoinColumn(name = "SOLI_NU_ID", referencedColumnName = "ID_SOLICITUD")
  public Solicitud getAaciTSolicitudsubongdBySoliNuId() {
    return aaciTSolicitudsubongdBySoliNuId;
  }

  public void setAaciTSolicitudsubongdBySoliNuId(Solicitud aaciTSolicitudsubongdBySoliNuId) {
    this.aaciTSolicitudsubongdBySoliNuId = aaciTSolicitudsubongdBySoliNuId;
  }

}

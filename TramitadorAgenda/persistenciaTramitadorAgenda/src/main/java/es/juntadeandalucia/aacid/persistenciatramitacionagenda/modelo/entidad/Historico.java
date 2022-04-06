package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


  @Entity
  @Table(name = "AACI_T_HISTORICO", schema = "AACID_OWNER", catalog = "")
  public class Historico {
    private Long hisNuId;
    private String hisXExpe;
    private Date hisFec;
    private String hisUsu;

    @Id
    @Column(name = "HIS_NU_ID", nullable = true, precision = 0)
    @SequenceGenerator(name = "seqHistorico", sequenceName = "AACI_SEQ_HISTORICO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqHistorico")
    public Long getHisNuId() {
      return hisNuId;
    }

    public void setHisNuId(Long hisNuId) {
      this.hisNuId = hisNuId;
    }

    @Basic
    @Column(name = "HIS_X_EXPE", nullable = true, precision = 0)
    public String getHisXExpe() {
      return hisXExpe;
    }
   
    public void setHisXExpe(String hisXExpe) {
      this.hisXExpe = hisXExpe;
    }

    @Basic
    @Column(name = "HIS_FEC", nullable = true)
    public Date getHisFec() {
      return hisFec;
    }

    public void setHisFec(Date hisFec) {
      this.hisFec = hisFec;
    }
    @Basic
    @Column(name = "HIS_USU", nullable = true, length = 150)
    public String getHisUsu() {
      return hisUsu;
    }
 
    public void setHisUsu(String hisUsu) {
      this.hisUsu = hisUsu;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((hisFec == null) ? 0 : hisFec.hashCode());
      result = prime * result + ((hisNuId == null) ? 0 : hisNuId.hashCode());
      result = prime * result + ((hisUsu == null) ? 0 : hisUsu.hashCode());
      result = prime * result + ((hisXExpe == null) ? 0 : hisXExpe.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Historico other = (Historico) obj;
      if (hisFec == null) {
        if (other.hisFec != null)
          return false;
      } else if (!hisFec.equals(other.hisFec))
        return false;
      if (hisNuId == null) {
        if (other.hisNuId != null)
          return false;
      } else if (!hisNuId.equals(other.hisNuId))
        return false;
      if (hisUsu == null) {
        if (other.hisUsu != null)
          return false;
      } else if (!hisUsu.equals(other.hisUsu))
        return false;
      if (hisXExpe == null) {
        if (other.hisXExpe != null)
          return false;
      } else if (!hisXExpe.equals(other.hisXExpe))
        return false;
      return true;
    }

  }


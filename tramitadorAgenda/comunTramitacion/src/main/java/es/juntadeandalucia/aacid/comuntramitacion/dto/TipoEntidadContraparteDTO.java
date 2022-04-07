package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.io.Serializable;

public class TipoEntidadContraparteDTO implements Serializable{
  
  private static final long serialVersionUID = -6406800198359709893L;
  
  private long idTipoEntidadContraparte;
  private String codigoEntidadContraparte;
  private String descripcionEntidadContraparte;
  
  public long getIdTipoEntidadContraparte() {
    return idTipoEntidadContraparte;
  }
  public void setIdTipoEntidadContraparte(long idTipoEntidadContraparte) {
    this.idTipoEntidadContraparte = idTipoEntidadContraparte;
  }
  public String getCodigoEntidadContraparte() {
    return codigoEntidadContraparte;
  }
  public void setCodigoEntidadContraparte(String codigoEntidadContraparte) {
    this.codigoEntidadContraparte = codigoEntidadContraparte;
  }
  public String getDescripcionEntidadContraparte() {
    return descripcionEntidadContraparte;
  }
  public void setDescripcionEntidadContraparte(String descripcionEntidadContraparte) {
    this.descripcionEntidadContraparte = descripcionEntidadContraparte;
  }
  
  
  
   
}

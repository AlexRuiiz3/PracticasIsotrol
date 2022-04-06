package es.juntadeandalucia.aacid.comuntramitacion.dto;

public class FuncionesDTO {
  private Integer funcNuId;
  private String funcTxDescripcion;
  private String funcTxCodigo;
  
  public Integer getFuncNuId() {
    return funcNuId;
  }
  public void setFuncNuId(Integer funcNuId) {
    this.funcNuId = funcNuId;
  }
  public String getFuncTxDescripcion() {
    return funcTxDescripcion;
  }
  public void setFuncTxDescripcion(String funcTxDescripcion) {
    this.funcTxDescripcion = funcTxDescripcion;
  }
  public String getFuncTxCodigo() {
    return funcTxCodigo;
  }
  public void setFuncTxCodigo(String funcTxCodigo) {
    this.funcTxCodigo = funcTxCodigo;
  }
}

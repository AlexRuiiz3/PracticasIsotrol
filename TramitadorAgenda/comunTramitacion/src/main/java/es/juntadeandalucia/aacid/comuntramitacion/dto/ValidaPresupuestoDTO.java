package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidaPresupuestoDTO implements Serializable {

  private static final long serialVersionUID = 1992737775478394850L;

  private String idExpTrewa;
  private String mensaje;
  private String tipoSolicitud;
  private transient EntidadesContribucionesDTO entidad;
  private transient List<EntidadesContribucionesDTO> listaEntidades;
  private transient List<EntidadesContribucionesDTO> listaEntidadesSelect;
  private transient List<GastoDTO> listaGastosAI;
  private transient List<GastoDTO> listaGastosAII;
  private transient List<GastoDTO> listaGastos;
  private transient List<GastosContribucionDTO> listaNuevaEntidad;
  private transient List<GastosContribucionDTO> listaNuevaEntidadBack;
  private transient List<ContribucionDTO> contribuciones;
  private String entidadStr;

  public ValidaPresupuestoDTO() {
    entidadStr = "";
    listaEntidades = new ArrayList<>();
    listaEntidadesSelect = new ArrayList<>();
    listaGastosAI = new ArrayList<>();
    listaGastosAII = new ArrayList<>();
    listaGastos = new ArrayList<>();
    listaNuevaEntidad = new ArrayList<>();
    listaNuevaEntidadBack = new ArrayList<>();
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(final String mensaje) {
    this.mensaje = mensaje;
  }

  public String getTipoSolicitud() {
    return tipoSolicitud;
  }

  public void setTipoSolicitud(final String tipoSolicitud) {
    this.tipoSolicitud = tipoSolicitud;
  }

  public EntidadesContribucionesDTO getEntidad() {
    return entidad;
  }

  public void setEntidad(final EntidadesContribucionesDTO entidad) {
    this.entidad = entidad;
  }

  public List<EntidadesContribucionesDTO> getListaEntidades() {
    return listaEntidades;
  }

  public void setListaEntidades(final List<EntidadesContribucionesDTO> listaEntidades) {
    this.listaEntidades = listaEntidades;
  }

  public List<EntidadesContribucionesDTO> getListaEntidadesSelect() {
    return listaEntidadesSelect;
  }

  public void setListaEntidadesSelect(final List<EntidadesContribucionesDTO> listaEntidadesSelect) {
    this.listaEntidadesSelect = listaEntidadesSelect;
  }

  public List<GastoDTO> getListaGastosAI() {
    return listaGastosAI;
  }

  public void setListaGastosAI(final List<GastoDTO> listaGastosAnexoI) {
    listaGastosAI = listaGastosAnexoI;
  }

  public List<GastoDTO> getListaGastosAII() {
    return listaGastosAII;
  }

  public void setListaGastosAII(final List<GastoDTO> listaGastosAnexoII) {
    listaGastosAII = listaGastosAnexoII;
  }

  public List<GastoDTO> getListaGastos() {
    return listaGastos;
  }

  public void setListaGastos(final List<GastoDTO> listaGastos) {
    this.listaGastos = listaGastos;
  }

  public List<GastosContribucionDTO> getListaNuevaEntidad() {
    return listaNuevaEntidad;
  }

  public void setListaNuevaEntidad(final List<GastosContribucionDTO> listaNuevaEntidad) {
    this.listaNuevaEntidad = listaNuevaEntidad;
  }

  public List<GastosContribucionDTO> getListaNuevaEntidadBack() {
    return listaNuevaEntidadBack;
  }

  public void setListaNuevaEntidadBack(final List<GastosContribucionDTO> listaNuevaEntidadBack) {
    this.listaNuevaEntidadBack = listaNuevaEntidadBack;
  }

  public String getIdExpTrewa() {
    return idExpTrewa;
  }

  public void setIdExpTrewa(final String idExpTrewa) {
    this.idExpTrewa = idExpTrewa;
  }

  public List<ContribucionDTO> getContribuciones() {
    return contribuciones;
  }

  public void setContribuciones(final List<ContribucionDTO> contribuciones) {
    this.contribuciones = contribuciones;
  }

  public String getEntidadStr() {
    return entidadStr;
  }

  public void setEntidadStr(final String entidadStr) {
    this.entidadStr = entidadStr;
  }

}

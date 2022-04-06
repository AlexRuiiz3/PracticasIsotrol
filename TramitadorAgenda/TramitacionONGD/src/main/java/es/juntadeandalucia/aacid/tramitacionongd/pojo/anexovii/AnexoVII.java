package es.juntadeandalucia.aacid.tramitacionongd.pojo.anexovii;

public class AnexoVII {

  private Long idSolicitud;

  // ----------------------------------------------------------------------------------------------------
  // 1 DATOS DE LA ENTIDAD SOLICITANTE Y DE LA PERSONA REPRESENTANTE
  // ----------------------------------------------------------------------------------------------------

  // SOLICITANTE
  private String soli1Nombre;
  private String soli1Codigo;
  private String soli1Numident;
  private String soli1Siglas;
  private String soli1Tipoident;
  private String soli1Apellido1;
  private String soli1Apellido2;
  private String soli1Sexo;

  private String soli1Tipovia;
  private String soli1Nombrevia;
  private String soli1Numero;
  private String soli1Letra;
  private String soli1Kmvia;
  private String soli1Bloque;
  private String soli1Portal;
  private String soli1Escalera;
  private String soli1Piso;
  private String soli1Puerta;
  private Long soli1Codprov;
  private String soli1Codmunicipio;
  private String soli1Poblacion;
  private Long soli1Cp;
  private Long soli1Telefono;
  private Long soli1Tlfmovil;
  private String soli1Email;

  // REPRESENTANTE
  private String repr1Apellido1;
  private String repr1Apellido2;
  private String repr1Nombre;
  private String repr1Sexo;
  private String repr1Numident;
  private String repr1Titulo;
  private String repr1Tipoident;
  private String repr1Tipovia;
  private String repr1Nombrevia;
  private String repr1Codmunicipio;
  private Long repr1Codprov;
  private String calidadDe;

  // ----------------------------------------------------------------------------------------------------
  // 2 DATOS DEL INICIO
  // ----------------------------------------------------------------------------------------------------
  private String fechaInicio;
  private String fechaFin;
  private Long mesesEjecucion;

  // ----------------------------------------------------------------------------------------------------
  // 3 DECLARACIÓN, LUGAR, FECHA Y FIRMA
  // ----------------------------------------------------------------------------------------------------
  private String firm1Fdo;
  private String firm1Lugar;

  // ----------------------------------------------------------------------------------------------------
  // OTROS CAMPOS DE VeA
  // ----------------------------------------------------------------------------------------------------
  private String codDir3;

  // GETTERS AND SETTERS

  /**
   * @return the idSolicitud
   */
  public Long getIdSolicitud() {
    return idSolicitud;
  }

  /**
   * @param idSolicitud
   *          the idSolicitud to set
   */
  public void setIdSolicitud(final Long idSolicitud) {
    this.idSolicitud = idSolicitud;
  }

  /**
   * @return the soli1Nombre
   */
  public String getSoli1Nombre() {
    return soli1Nombre;
  }

  /**
   * @param soli1Nombre
   *          the soli1Nombre to set
   */
  public void setSoli1Nombre(final String soli1Nombre) {
    this.soli1Nombre = soli1Nombre;
  }

  /**
   * @return the soli1Codigo
   */
  public String getSoli1Codigo() {
    return soli1Codigo;
  }

  /**
   * @param soli1Codigo
   *          the soli1Codigo to set
   */
  public void setSoli1Codigo(final String soli1Codigo) {
    this.soli1Codigo = soli1Codigo;
  }

  /**
   * @return the soli1Numident
   */
  public String getSoli1Numident() {
    return soli1Numident;
  }

  /**
   * @param soli1Numident
   *          the soli1Numident to set
   */
  public void setSoli1Numident(final String soli1Numident) {
    this.soli1Numident = soli1Numident;
  }

  /**
   * @return the soli1Siglas
   */
  public String getSoli1Siglas() {
    return soli1Siglas;
  }

  /**
   * @param soli1Siglas
   *          the soli1Siglas to set
   */
  public void setSoli1Siglas(final String soli1Siglas) {
    this.soli1Siglas = soli1Siglas;
  }

  /**
   * @return the soli1Tipoident
   */
  public String getSoli1Tipoident() {
    return soli1Tipoident;
  }

  /**
   * @param soli1Tipoident
   *          the soli1Tipoident to set
   */
  public void setSoli1Tipoident(final String soli1Tipoident) {
    this.soli1Tipoident = soli1Tipoident;
  }

  /**
   * @return the soli1Apellido1
   */
  public String getSoli1Apellido1() {
    return soli1Apellido1;
  }

  /**
   * @param soli1Apellido1
   *          the soli1Apellido1 to set
   */
  public void setSoli1Apellido1(final String soli1Apellido1) {
    this.soli1Apellido1 = soli1Apellido1;
  }

  /**
   * @return the soli1Apellido2
   */
  public String getSoli1Apellido2() {
    return soli1Apellido2;
  }

  /**
   * @param soli1Apellido2
   *          the soli1Apellido2 to set
   */
  public void setSoli1Apellido2(final String soli1Apellido2) {
    this.soli1Apellido2 = soli1Apellido2;
  }

  /**
   * @return the soli1Sexo
   */
  public String getSoli1Sexo() {
    return soli1Sexo;
  }

  /**
   * @param soli1Sexo
   *          the soli1Sexo to set
   */
  public void setSoli1Sexo(final String soli1Sexo) {
    this.soli1Sexo = soli1Sexo;
  }

  /**
   * @return the soli1Tipovia
   */
  public String getSoli1Tipovia() {
    return soli1Tipovia;
  }

  /**
   * @param soli1Tipovia
   *          the soli1Tipovia to set
   */
  public void setSoli1Tipovia(final String soli1Tipovia) {
    this.soli1Tipovia = soli1Tipovia;
  }

  /**
   * @return the soli1Nombrevia
   */
  public String getSoli1Nombrevia() {
    return soli1Nombrevia;
  }

  /**
   * @param soli1Nombrevia
   *          the soli1Nombrevia to set
   */
  public void setSoli1Nombrevia(final String soli1Nombrevia) {
    this.soli1Nombrevia = soli1Nombrevia;
  }

  /**
   * @return the soli1Numero
   */
  public String getSoli1Numero() {
    return soli1Numero;
  }

  /**
   * @param soli1Numero
   *          the soli1Numero to set
   */
  public void setSoli1Numero(final String soli1Numero) {
    this.soli1Numero = soli1Numero;
  }

  /**
   * @return the soli1Letra
   */
  public String getSoli1Letra() {
    return soli1Letra;
  }

  /**
   * @param soli1Letra
   *          the soli1Letra to set
   */
  public void setSoli1Letra(final String soli1Letra) {
    this.soli1Letra = soli1Letra;
  }

  /**
   * @return the soli1Kmvia
   */
  public String getSoli1Kmvia() {
    return soli1Kmvia;
  }

  /**
   * @param soli1Kmvia
   *          the soli1Kmvia to set
   */
  public void setSoli1Kmvia(final String soli1Kmvia) {
    this.soli1Kmvia = soli1Kmvia;
  }

  /**
   * @return the soli1Bloque
   */
  public String getSoli1Bloque() {
    return soli1Bloque;
  }

  /**
   * @param soli1Bloque
   *          the soli1Bloque to set
   */
  public void setSoli1Bloque(final String soli1Bloque) {
    this.soli1Bloque = soli1Bloque;
  }

  /**
   * @return the soli1Portal
   */
  public String getSoli1Portal() {
    return soli1Portal;
  }

  /**
   * @param soli1Portal
   *          the soli1Portal to set
   */
  public void setSoli1Portal(final String soli1Portal) {
    this.soli1Portal = soli1Portal;
  }

  /**
   * @return the soli1Escalera
   */
  public String getSoli1Escalera() {
    return soli1Escalera;
  }

  /**
   * @param soli1Escalera
   *          the soli1Escalera to set
   */
  public void setSoli1Escalera(final String soli1Escalera) {
    this.soli1Escalera = soli1Escalera;
  }

  /**
   * @return the soli1Piso
   */
  public String getSoli1Piso() {
    return soli1Piso;
  }

  /**
   * @param soli1Piso
   *          the soli1Piso to set
   */
  public void setSoli1Piso(final String soli1Piso) {
    this.soli1Piso = soli1Piso;
  }

  /**
   * @return the soli1Puerta
   */
  public String getSoli1Puerta() {
    return soli1Puerta;
  }

  /**
   * @param soli1Puerta
   *          the soli1Puerta to set
   */
  public void setSoli1Puerta(final String soli1Puerta) {
    this.soli1Puerta = soli1Puerta;
  }

  /**
   * @return the soli1Codprov
   */
  public Long getSoli1Codprov() {
    return soli1Codprov;
  }

  /**
   * @param soli1Codprov
   *          the soli1Codprov to set
   */
  public void setSoli1Codprov(final Long soli1Codprov) {
    this.soli1Codprov = soli1Codprov;
  }

  /**
   * @return the soli1Codmunicipio
   */
  public String getSoli1Codmunicipio() {
    return soli1Codmunicipio;
  }

  /**
   * @param soli1Codmunicipio
   *          the soli1Codmunicipio to set
   */
  public void setSoli1Codmunicipio(final String soli1Codmunicipio) {
    this.soli1Codmunicipio = soli1Codmunicipio;
  }

  /**
   * @return the soli1Poblacion
   */
  public String getSoli1Poblacion() {
    return soli1Poblacion;
  }

  /**
   * @param soli1Poblacion
   *          the soli1Poblacion to set
   */
  public void setSoli1Poblacion(final String soli1Poblacion) {
    this.soli1Poblacion = soli1Poblacion;
  }

  /**
   * @return the soli1Cp
   */
  public Long getSoli1Cp() {
    return soli1Cp;
  }

  /**
   * @param soli1Cp
   *          the soli1Cp to set
   */
  public void setSoli1Cp(final Long soli1Cp) {
    this.soli1Cp = soli1Cp;
  }

  /**
   * @return the soli1Telefono
   */
  public Long getSoli1Telefono() {
    return soli1Telefono;
  }

  /**
   * @param soli1Telefono
   *          the soli1Telefono to set
   */
  public void setSoli1Telefono(final Long soli1Telefono) {
    this.soli1Telefono = soli1Telefono;
  }

  /**
   * @return the soli1Tlfmovil
   */
  public Long getSoli1Tlfmovil() {
    return soli1Tlfmovil;
  }

  /**
   * @param soli1Tlfmovil
   *          the soli1Tlfmovil to set
   */
  public void setSoli1Tlfmovil(final Long soli1Tlfmovil) {
    this.soli1Tlfmovil = soli1Tlfmovil;
  }

  /**
   * @return the soli1Email
   */
  public String getSoli1Email() {
    return soli1Email;
  }

  /**
   * @param soli1Email
   *          the soli1Email to set
   */
  public void setSoli1Email(final String soli1Email) {
    this.soli1Email = soli1Email;
  }

  /**
   * @return the repr1Apellido1
   */
  public String getRepr1Apellido1() {
    return repr1Apellido1;
  }

  /**
   * @param repr1Apellido1
   *          the repr1Apellido1 to set
   */
  public void setRepr1Apellido1(final String repr1Apellido1) {
    this.repr1Apellido1 = repr1Apellido1;
  }

  /**
   * @return the repr1Apellido2
   */
  public String getRepr1Apellido2() {
    return repr1Apellido2;
  }

  /**
   * @param repr1Apellido2
   *          the repr1Apellido2 to set
   */
  public void setRepr1Apellido2(final String repr1Apellido2) {
    this.repr1Apellido2 = repr1Apellido2;
  }

  /**
   * @return the repr1Nombre
   */
  public String getRepr1Nombre() {
    return repr1Nombre;
  }

  /**
   * @param repr1Nombre
   *          the repr1Nombre to set
   */
  public void setRepr1Nombre(final String repr1Nombre) {
    this.repr1Nombre = repr1Nombre;
  }

  /**
   * @return the repr1Sexo
   */
  public String getRepr1Sexo() {
    return repr1Sexo;
  }

  /**
   * @param repr1Sexo
   *          the repr1Sexo to set
   */
  public void setRepr1Sexo(final String repr1Sexo) {
    this.repr1Sexo = repr1Sexo;
  }

  /**
   * @return the repr1Numident
   */
  public String getRepr1Numident() {
    return repr1Numident;
  }

  /**
   * @param repr1Numident
   *          the repr1Numident to set
   */
  public void setRepr1Numident(final String repr1Numident) {
    this.repr1Numident = repr1Numident;
  }

  /**
   * @return the repr1Titulo
   */
  public String getRepr1Titulo() {
    return repr1Titulo;
  }

  /**
   * @param repr1Titulo
   *          the repr1Titulo to set
   */
  public void setRepr1Titulo(final String repr1Titulo) {
    this.repr1Titulo = repr1Titulo;
  }

  /**
   * @return the repr1Tipoident
   */
  public String getRepr1Tipoident() {
    return repr1Tipoident;
  }

  /**
   * @param repr1Tipoident
   *          the repr1Tipoident to set
   */
  public void setRepr1Tipoident(final String repr1Tipoident) {
    this.repr1Tipoident = repr1Tipoident;
  }

  /**
   * @return the firm1Fdo
   */
  public String getFirm1Fdo() {
    return firm1Fdo;
  }

  /**
   * @param firm1Fdo
   *          the firm1Fdo to set
   */
  public void setFirm1Fdo(final String firm1Fdo) {
    this.firm1Fdo = firm1Fdo;
  }

  /**
   * @return the firm1Lugar
   */
  public String getFirm1Lugar() {
    return firm1Lugar;
  }

  /**
   * @param firm1Lugar
   *          the firm1Lugar to set
   */
  public void setFirm1Lugar(final String firm1Lugar) {
    this.firm1Lugar = firm1Lugar;
  }

  /**
   * @return the codDir3
   */
  public String getCodDir3() {
    return codDir3;
  }

  /**
   * @param codDir3
   *          the codDir3 to set
   */
  public void setCodDir3(final String codDir3) {
    this.codDir3 = codDir3;
  }

  /**
   * Obtiene la propiedad repr1Tipovia
   *
   * @return el repr1Tipovia
   */
  public String getRepr1Tipovia() {
    return repr1Tipovia;
  }

  /**
   * Establece el valor de la propiedad repr1Tipovia
   *
   * @param repr1Tipovia
   *          el repr1Tipovia para establecer
   */
  public void setRepr1Tipovia(final String repr1Tipovia) {
    this.repr1Tipovia = repr1Tipovia;
  }

  /**
   * Obtiene la propiedad repr1Nombrevia
   *
   * @return el repr1Nombrevia
   */
  public String getRepr1Nombrevia() {
    return repr1Nombrevia;
  }

  /**
   * Establece el valor de la propiedad repr1Nombrevia
   *
   * @param repr1Nombrevia
   *          el repr1Nombrevia para establecer
   */
  public void setRepr1Nombrevia(final String repr1Nombrevia) {
    this.repr1Nombrevia = repr1Nombrevia;
  }

  /**
   * Obtiene la propiedad repr1Codprov
   *
   * @return el repr1Codprov
   */
  public Long getRepr1Codprov() {
    return repr1Codprov;
  }

  /**
   * Establece el valor de la propiedad repr1Codprov
   *
   * @param repr1Codprov
   *          el repr1Codprov para establecer
   */
  public void setRepr1Codprov(final Long repr1Codprov) {
    this.repr1Codprov = repr1Codprov;
  }

  /**
   * Obtiene la propiedad repr1Codmunicipio
   *
   * @return el repr1Codmunicipio
   */
  public String getRepr1Codmunicipio() {
    return repr1Codmunicipio;
  }

  /**
   * Establece el valor de la propiedad repr1Codmunicipio
   *
   * @param repr1Codmunicipio
   *          el repr1Codmunicipio para establecer
   */
  public void setRepr1Codmunicipio(final String repr1Codmunicipio) {
    this.repr1Codmunicipio = repr1Codmunicipio;
  }

  public String getFechaInicio() {
    return fechaInicio;
  }

  public void setFechaInicio(final String fechaInicio) {
    this.fechaInicio = fechaInicio;
  }

  public String getFechaFin() {
    return fechaFin;
  }

  public void setFechaFin(final String fechaFin) {
    this.fechaFin = fechaFin;
  }

  public Long getMesesEjecucion() {
    return mesesEjecucion;
  }

  public void setMesesEjecucion(final Long mesesEjecucion) {
    this.mesesEjecucion = mesesEjecucion;
  }

  public String getCalidadDe() {
    return calidadDe;
  }

  public void setCalidadDe(final String calidadDe) {
    this.calidadDe = calidadDe;
  }

}

package es.juntadeandalucia.aacid.comuntramitacion.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import es.juntadeandalucia.aacid.comuntramitacion.validation.annotation.FechaPosteriorActual;

/**
 * Objeto para enviar los datos de las fechas de apertura y cierre de las fases de recepción.
 *
 * @author Isotrol (AACID)
 *
 */
public class ConvocatoriaFechaDTO {

  private Long idConvFh;
  // Campos parametrizar fechas de fases de recepción
  // Recepción solicitud
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaPosteriorActual
  private Date fhInicioRecSol;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaPosteriorActual
  private Date fhFinRecSol;
  // Recepción subsanación
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaPosteriorActual
  private Date fhInicioRecSub;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaPosteriorActual
  private Date fhFinRecSub;
  // Recepción alegaciones
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaPosteriorActual
  private Date fhInicioRecAlega;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaPosteriorActual
  private Date fhFinRecAlega;
  // Recepción subsanción (2)
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaPosteriorActual
  private Date fhInicioRecSub2;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaPosteriorActual
  private Date fhFinRecSub2;
  // Recepción Alegación/Aceptación/Reformulación/presentación Documentación
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaPosteriorActual
  private Date fhInicioRecAARPD;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaPosteriorActual
  private Date fhFinRecAARPD;
  // Recepción Subsanación Documentación
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaPosteriorActual
  private Date fhInicioRecSubDoc;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaPosteriorActual
  private Date fhFinRecSubDoc;
  // Recepción Comunicación Inicio
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaPosteriorActual
  private Date fhInicioRecComIni;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @FechaPosteriorActual
  private Date fhFinRecComIni;

  /**
   * Obtiene la propiedad idConvFh
   *
   * @return el idConvFh
   */
  public Long getIdConvFh() {
    return idConvFh;
  }

  /**
   * Establece el valor de la propiedad idConvFh
   *
   * @param idConvFh
   *          el idConvFh para establecer
   */
  public void setIdConvFh(final Long idConvFh) {
    this.idConvFh = idConvFh;
  }

  /**
   * Obtiene la propiedad fhInicioRecSol
   *
   * @return el fhInicioRecSol
   */
  public Date getFhInicioRecSol() {
    return fhInicioRecSol;
  }

  /**
   * Establece el valor de la propiedad fhInicioRecSol
   *
   * @param fhInicioRecSol
   *          el fhInicioRecSol para establecer
   */
  public void setFhInicioRecSol(final Date fhInicioRecSol) {
    this.fhInicioRecSol = fhInicioRecSol;
  }

  /**
   * Obtiene la propiedad fhFinRecSol
   *
   * @return el fhFinRecSol
   */
  public Date getFhFinRecSol() {
    return fhFinRecSol;
  }

  /**
   * Establece el valor de la propiedad fhFinRecSol
   *
   * @param fhFinRecSol
   *          el fhFinRecSol para establecer
   */
  public void setFhFinRecSol(final Date fhFinRecSol) {
    this.fhFinRecSol = fhFinRecSol;
  }

  /**
   * Obtiene la propiedad fhInicioRecSub
   *
   * @return el fhInicioRecSub
   */
  public Date getFhInicioRecSub() {
    return fhInicioRecSub;
  }

  /**
   * Establece el valor de la propiedad fhInicioRecSub
   *
   * @param fhInicioRecSub
   *          el fhInicioRecSub para establecer
   */
  public void setFhInicioRecSub(final Date fhInicioRecSub) {
    this.fhInicioRecSub = fhInicioRecSub;
  }

  /**
   * Obtiene la propiedad fhFinRecSub
   *
   * @return el fhFinRecSub
   */
  public Date getFhFinRecSub() {
    return fhFinRecSub;
  }

  /**
   * Establece el valor de la propiedad fhFinRecSub
   *
   * @param fhFinRecSub
   *          el fhFinRecSub para establecer
   */
  public void setFhFinRecSub(final Date fhFinRecSub) {
    this.fhFinRecSub = fhFinRecSub;
  }

  /**
   * Obtiene la propiedad fhInicioRecAlega
   *
   * @return el fhInicioRecAlega
   */
  public Date getFhInicioRecAlega() {
    return fhInicioRecAlega;
  }

  /**
   * Establece el valor de la propiedad fhInicioRecAlega
   *
   * @param fhInicioRecAlega
   *          el fhInicioRecAlega para establecer
   */
  public void setFhInicioRecAlega(final Date fhInicioRecAlega) {
    this.fhInicioRecAlega = fhInicioRecAlega;
  }

  /**
   * Obtiene la propiedad fhFinRecAlega
   *
   * @return el fhFinRecAlega
   */
  public Date getFhFinRecAlega() {
    return fhFinRecAlega;
  }

  /**
   * Establece el valor de la propiedad fhFinRecAlega
   *
   * @param fhFinRecAlega
   *          el fhFinRecAlega para establecer
   */
  public void setFhFinRecAlega(final Date fhFinRecAlega) {
    this.fhFinRecAlega = fhFinRecAlega;
  }

  /**
   * Obtiene la propiedad fhInicioRecSub2
   *
   * @return el fhInicioRecSub2
   */
  public Date getFhInicioRecSub2() {
    return fhInicioRecSub2;
  }

  /**
   * Establece el valor de la propiedad fhInicioRecSub2
   *
   * @param fhInicioRecSub2
   *          el fhInicioRecSub2 para establecer
   */
  public void setFhInicioRecSub2(final Date fhInicioRecSub2) {
    this.fhInicioRecSub2 = fhInicioRecSub2;
  }

  /**
   * Obtiene la propiedad fhFinRecSub2
   *
   * @return el fhFinRecSub2
   */
  public Date getFhFinRecSub2() {
    return fhFinRecSub2;
  }

  /**
   * Establece el valor de la propiedad fhFinRecSub2
   *
   * @param fhFinRecSub2
   *          el fhFinRecSub2 para establecer
   */
  public void setFhFinRecSub2(final Date fhFinRecSub2) {
    this.fhFinRecSub2 = fhFinRecSub2;
  }

  /**
   * Obtiene la propiedad fhInicioRecAARPD
   *
   * @return el fhInicioRecAARPD
   */
  public Date getFhInicioRecAARPD() {
    return fhInicioRecAARPD;
  }

  /**
   * Establece el valor de la propiedad fhInicioRecAARPD
   *
   * @param fhInicioRecAARPD
   *          el fhInicioRecAARPD para establecer
   */
  public void setFhInicioRecAARPD(final Date fhInicioRecAARPD) {
    this.fhInicioRecAARPD = fhInicioRecAARPD;
  }

  /**
   * Obtiene la propiedad fhFinRecAARPD
   *
   * @return el fhFinRecAARPD
   */
  public Date getFhFinRecAARPD() {
    return fhFinRecAARPD;
  }

  /**
   * Establece el valor de la propiedad fhFinRecAARPD
   *
   * @param fhFinRecAARPD
   *          el fhFinRecAARPD para establecer
   */
  public void setFhFinRecAARPD(final Date fhFinRecAARPD) {
    this.fhFinRecAARPD = fhFinRecAARPD;
  }

  /**
   * Obtiene la propiedad fhInicioRecSubDoc
   *
   * @return el fhInicioRecSubDoc
   */
  public Date getFhInicioRecSubDoc() {
    return fhInicioRecSubDoc;
  }

  /**
   * Establece el valor de la propiedad fhInicioRecSubDoc
   *
   * @param fhInicioRecSubDoc
   *          el fhInicioRecSubDoc para establecer
   */
  public void setFhInicioRecSubDoc(final Date fhInicioRecSubDoc) {
    this.fhInicioRecSubDoc = fhInicioRecSubDoc;
  }

  /**
   * Obtiene la propiedad fhFinRecSubDoc
   *
   * @return el fhFinRecSubDoc
   */
  public Date getFhFinRecSubDoc() {
    return fhFinRecSubDoc;
  }

  /**
   * Establece el valor de la propiedad fhFinRecSubDoc
   *
   * @param fhFinRecSubDoc
   *          el fhFinRecSubDoc para establecer
   */
  public void setFhFinRecSubDoc(final Date fhFinRecSubDoc) {
    this.fhFinRecSubDoc = fhFinRecSubDoc;
  }

  /**
   * Obtiene la propiedad fhInicioRecComIni
   *
   * @return el fhInicioRecComIni
   */
  public Date getFhInicioRecComIni() {
    return fhInicioRecComIni;
  }

  /**
   * Establece el valor de la propiedad fhInicioRecComIni
   *
   * @param fhInicioRecComIni
   *          el fhInicioRecComIni para establecer
   */
  public void setFhInicioRecComIni(final Date fhInicioRecComIni) {
    this.fhInicioRecComIni = fhInicioRecComIni;
  }

  /**
   * Obtiene la propiedad fhFinRecComIni
   *
   * @return el fhFinRecComIni
   */
  public Date getFhFinRecComIni() {
    return fhFinRecComIni;
  }

  /**
   * Establece el valor de la propiedad fhFinRecComIni
   *
   * @param fhFinRecComIni
   *          el fhFinRecComIni para establecer
   */
  public void setFhFinRecComIni(final Date fhFinRecComIni) {
    this.fhFinRecComIni = fhFinRecComIni;
  }
}

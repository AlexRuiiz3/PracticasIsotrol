//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen.
// Generado el: 2018.09.10 a las 04:30:00 PM CEST
//

package es.juntadeandalucia.aacid.comuntramitacion.dto.vea;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para docAdjuntoVeaType complex type.
 *
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 *
 * <pre>
 * &lt;complexType name="docAdjuntoVeaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="cod-doc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="incorporado" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="firmado" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="autorizacion" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="autorizacionSCSP" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="id-doc-trewa" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="csv-doc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="eni-doc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "docAdjuntoVeaType")
public class DocAdjuntoVeaType {

  @XmlAttribute(name = "cod-doc")
  protected String codDoc;
  @XmlAttribute(name = "incorporado")
  protected String incorporado;
  @XmlAttribute(name = "firmado")
  protected String firmado;
  @XmlAttribute(name = "autorizacion")
  protected String autorizacion;
  @XmlAttribute(name = "autorizacionSCSP")
  protected String autorizacionSCSP;
  @XmlAttribute(name = "id-doc-trewa")
  protected String idDocTrewa;
  @XmlAttribute(name = "csv-doc")
  protected String csvDoc;
  @XmlAttribute(name = "eni-doc")
  protected String eniDoc;

  /**
   * Obtiene el valor de la propiedad codDoc.
   *
   * @return possible object is {@link String }
   *
   */
  public String getCodDoc() {
    return codDoc;
  }

  /**
   * Define el valor de la propiedad codDoc.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setCodDoc(final String value) {
    codDoc = value;
  }

  /**
   * Obtiene el valor de la propiedad incorporado.
   *
   * @return possible object is {@link String }
   *
   */
  public String getIncorporado() {
    return incorporado;
  }

  /**
   * Define el valor de la propiedad incorporado.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setIncorporado(final String value) {
    incorporado = value;
  }

  /**
   * Obtiene el valor de la propiedad firmado.
   *
   * @return possible object is {@link String }
   *
   */
  public String getFirmado() {
    return firmado;
  }

  /**
   * Define el valor de la propiedad firmado.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setFirmado(final String value) {
    firmado = value;
  }

  /**
   * Obtiene el valor de la propiedad autorizacion.
   *
   * @return possible object is {@link String }
   *
   */
  public String getAutorizacion() {
    return autorizacion;
  }

  /**
   * Define el valor de la propiedad autorizacion.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setAutorizacion(final String value) {
    autorizacion = value;
  }

  /**
   * Obtiene el valor de la propiedad autorizacionSCSP.
   *
   * @return possible object is {@link String }
   *
   */
  public String getAutorizacionSCSP() {
    return autorizacionSCSP;
  }

  /**
   * Define el valor de la propiedad autorizacionSCSP.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setAutorizacionSCSP(final String value) {
    autorizacionSCSP = value;
  }

  /**
   * Obtiene el valor de la propiedad idDocTrewa.
   *
   * @return possible object is {@link String }
   *
   */
  public String getIdDocTrewa() {
    return idDocTrewa;
  }

  /**
   * Define el valor de la propiedad idDocTrewa.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setIdDocTrewa(final String value) {
    idDocTrewa = value;
  }

  /**
   * Obtiene el valor de la propiedad csvDoc.
   *
   * @return possible object is {@link String }
   *
   */
  public String getCsvDoc() {
    return csvDoc;
  }

  /**
   * Define el valor de la propiedad csvDoc.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setCsvDoc(final String value) {
    csvDoc = value;
  }

  /**
   * Obtiene el valor de la propiedad eniDoc.
   *
   * @return possible object is {@link String }
   *
   */
  public String getEniDoc() {
    return eniDoc;
  }

  /**
   * Define el valor de la propiedad eniDoc.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setEniDoc(final String value) {
    eniDoc = value;
  }

}

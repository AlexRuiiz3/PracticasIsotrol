//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen.
// Generado el: 2018.09.10 a las 04:30:00 PM CEST
//

package es.juntadeandalucia.aacid.comuntramitacion.dto.vea;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para datos-tramitadorVeaType complex type.
 *
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 *
 * <pre>
 * &lt;complexType name="datos-tramitadorVeaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="JNDI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sistema" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="procedimiento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unidad-organica" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipo-expediente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigo-convocatoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datos-tramitadorVeaType", propOrder = { "jndi", "sistema", "procedimiento", "unidadOrganica", "tipoExpediente", "codigoConvocatoria" })
public class DatosTramitadorVeaType {

  @XmlElement(name = "JNDI")
  protected String jndi;
  @XmlElement(required = true)
  protected String sistema;
  @XmlElement(required = true)
  protected String procedimiento;
  @XmlElement(name = "unidad-organica", required = true)
  protected String unidadOrganica;
  @XmlElement(name = "tipo-expediente", required = true)
  protected String tipoExpediente;
  @XmlElement(name = "codigo-convocatoria")
  protected String codigoConvocatoria;

  /**
   * Obtiene el valor de la propiedad jndi.
   *
   * @return possible object is {@link String }
   *
   */
  public String getJNDI() {
    return jndi;
  }

  /**
   * Define el valor de la propiedad jndi.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setJNDI(final String value) {
    jndi = value;
  }

  /**
   * Obtiene el valor de la propiedad sistema.
   *
   * @return possible object is {@link String }
   *
   */
  public String getSistema() {
    return sistema;
  }

  /**
   * Define el valor de la propiedad sistema.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setSistema(final String value) {
    sistema = value;
  }

  /**
   * Obtiene el valor de la propiedad procedimiento.
   *
   * @return possible object is {@link String }
   *
   */
  public String getProcedimiento() {
    return procedimiento;
  }

  /**
   * Define el valor de la propiedad procedimiento.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setProcedimiento(final String value) {
    procedimiento = value;
  }

  /**
   * Obtiene el valor de la propiedad unidadOrganica.
   *
   * @return possible object is {@link String }
   *
   */
  public String getUnidadOrganica() {
    return unidadOrganica;
  }

  /**
   * Define el valor de la propiedad unidadOrganica.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setUnidadOrganica(final String value) {
    unidadOrganica = value;
  }

  /**
   * Obtiene el valor de la propiedad tipoExpediente.
   *
   * @return possible object is {@link String }
   *
   */
  public String getTipoExpediente() {
    return tipoExpediente;
  }

  /**
   * Define el valor de la propiedad tipoExpediente.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setTipoExpediente(final String value) {
    tipoExpediente = value;
  }

  /**
   * @return the codigoConvocatoria
   */
  public String getCodigoConvocatoria() {
    return codigoConvocatoria;
  }

  /**
   * @param codigoConvocatoria
   *          the codigoConvocatoria to set
   */
  public void setCodigoConvocatoria(final String codigoConvocatoria) {
    this.codigoConvocatoria = codigoConvocatoria;
  }

}

//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen.
// Generado el: 2018.09.10 a las 04:30:00 PM CEST
//

package es.juntadeandalucia.aacid.comuntramitacion.dto.vea;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para formularioVeaType complex type.
 *
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 *
 * <pre>
 * &lt;complexType name="formularioVeaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="campo" type="{es:juntadeandalucia:cfv:comov:xsd:peticion-vea:1.0.0}campoVeaType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id-formulario" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="correcto" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="firmado" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="orden-formulario" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="tipo-documento" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlType(name = "formularioVeaType", propOrder = { "campo" })
public class FormularioVeaType {

  @XmlElement(required = true)
  protected List<CampoVeaType> campo;
  @XmlAttribute(name = "id-formulario")
  protected String idFormulario;
  @XmlAttribute(name = "correcto")
  protected String correcto;
  @XmlAttribute(name = "firmado")
  protected String firmado;
  @XmlAttribute(name = "orden-formulario")
  protected String ordenFormulario;
  @XmlAttribute(name = "tipo-documento")
  protected String tipoDocumento;
  @XmlAttribute(name = "id-doc-trewa")
  protected String idDocTrewa;
  @XmlAttribute(name = "csv-doc")
  protected String csvDoc;
  @XmlAttribute(name = "eni-doc")
  protected String eniDoc;

  /**
   * Gets the value of the campo property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list
   * will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the campo property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getCampo().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link CampoVeaType }
   * 
   * 
   */
  public List<CampoVeaType> getCampo() {
    if (campo == null) {
      campo = new ArrayList<>();
    }
    return campo;
  }

  /**
   * Obtiene el valor de la propiedad idFormulario.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdFormulario() {
    return idFormulario;
  }

  /**
   * Define el valor de la propiedad idFormulario.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setIdFormulario(final String value) {
    idFormulario = value;
  }

  /**
   * Obtiene el valor de la propiedad correcto.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCorrecto() {
    return correcto;
  }

  /**
   * Define el valor de la propiedad correcto.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setCorrecto(final String value) {
    correcto = value;
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
   * Obtiene el valor de la propiedad ordenFormulario.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getOrdenFormulario() {
    return ordenFormulario;
  }

  /**
   * Define el valor de la propiedad ordenFormulario.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setOrdenFormulario(final String value) {
    ordenFormulario = value;
  }

  /**
   * Obtiene el valor de la propiedad tipoDocumento.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getTipoDocumento() {
    return tipoDocumento;
  }

  /**
   * Define el valor de la propiedad tipoDocumento.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setTipoDocumento(final String value) {
    tipoDocumento = value;
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

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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para documentosAdjuntosVeaType complex type.
 *
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 *
 * <pre>
 * &lt;complexType name="documentosAdjuntosVeaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="docAdjunto" type="{es:juntadeandalucia:cfv:comov:xsd:peticion-vea:1.0.0}docAdjuntoVeaType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentosAdjuntosVeaType", propOrder = { "docAdjunto" })
public class DocumentosAdjuntosVeaType {

  @XmlElement(required = true)
  protected List<DocAdjuntoVeaType> docAdjunto;

  /**
   * Gets the value of the docAdjunto property.
   *
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list
   * will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the docAdjunto property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   *
   * <pre>
   * getDocAdjunto().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list {@link DocAdjuntoVeaType }
   *
   *
   */
  public List<DocAdjuntoVeaType> getDocAdjunto() {
    if (docAdjunto == null) {
      docAdjunto = new ArrayList<>();
    }
    return docAdjunto;
  }

}

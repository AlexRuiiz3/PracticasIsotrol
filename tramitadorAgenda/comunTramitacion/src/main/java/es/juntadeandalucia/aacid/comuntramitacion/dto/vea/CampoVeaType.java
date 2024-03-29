package es.juntadeandalucia.aacid.comuntramitacion.dto.vea;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para campoVeaType complex type.
 *
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 *
 * <pre>
 * &lt;complexType name="campoVeaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "campoVeaType", propOrder = { "nombre", "valor" })
public class CampoVeaType {

  @XmlElement(required = true)
  protected String nombre;
  @XmlElement(required = true)
  protected String valor;

  /**
   * Obtiene el valor de la propiedad nombre.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Define el valor de la propiedad nombre.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setNombre(final String value) {
    nombre = value;
  }

  /**
   * Obtiene el valor de la propiedad valor.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getValor() {
    return valor;
  }

  /**
   * Define el valor de la propiedad valor.
   * 
   * @param value
   *          allowed object is {@link String }
   * 
   */
  public void setValor(final String value) {
    valor = value;
  }

}

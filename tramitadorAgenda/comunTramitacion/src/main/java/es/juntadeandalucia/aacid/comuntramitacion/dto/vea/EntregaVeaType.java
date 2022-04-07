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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase Java para entregaVeaType complex type.
 *
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 *
 * <pre>
 * &lt;complexType name="entregaVeaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datos-tramitador" type="{es:juntadeandalucia:cfv:comov:xsd:peticion-vea:1.0.0}datos-tramitadorVeaType"/>
 *         &lt;element name="numero-expediente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numero-expEni" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="asiento-aries" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="modoEntrega" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nif-presentador" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="formularios" type="{es:juntadeandalucia:cfv:comov:xsd:peticion-vea:1.0.0}formulariosVeaType"/>
 *         &lt;element name="documentos-adjuntos" type="{es:juntadeandalucia:cfv:comov:xsd:peticion-vea:1.0.0}documentosAdjuntosVeaType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id-entrega" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="transicion" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="id-fase-trewa" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="id_tarea_trewa" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "entregaVeaType", propOrder = { "datosTramitador", "numeroExpediente", "numeroExpEni", "asientoAries", "fecha", "modoEntrega", "nifPresentador",
    "formularios", "documentosAdjuntos" })
public class EntregaVeaType {

  @XmlElement(name = "datos-tramitador", required = true)
  protected DatosTramitadorVeaType datosTramitador;
  @XmlElement(name = "numero-expediente", required = true)
  protected String numeroExpediente;
  @XmlElement(name = "numero-expEni", required = true)
  protected String numeroExpEni;
  @XmlElement(name = "asiento-aries", required = true)
  protected String asientoAries;
  @XmlElement(required = true)
  protected String fecha;
  @XmlElement(required = true)
  protected String modoEntrega;
  @XmlElement(name = "nif-presentador", required = true)
  protected String nifPresentador;
  @XmlElement(required = true)
  protected FormulariosVeaType formularios;
  @XmlElement(name = "documentos-adjuntos", required = true)
  protected DocumentosAdjuntosVeaType documentosAdjuntos;
  @XmlAttribute(name = "id-entrega")
  protected String idEntrega;
  @XmlAttribute(name = "transicion")
  protected String transicion;
  @XmlAttribute(name = "id-fase-trewa")
  protected String idFaseTrewa;
  @XmlAttribute(name = "id-tarea-trewa")
  protected String idTareaTrewa;

  /**
   * Obtiene el valor de la propiedad datosTramitador.
   *
   * @return possible object is {@link DatosTramitadorVeaType }
   *
   */
  public DatosTramitadorVeaType getDatosTramitador() {
    return datosTramitador;
  }

  /**
   * Define el valor de la propiedad datosTramitador.
   *
   * @param value
   *          allowed object is {@link DatosTramitadorVeaType }
   *
   */
  public void setDatosTramitador(final DatosTramitadorVeaType value) {
    datosTramitador = value;
  }

  /**
   * Obtiene el valor de la propiedad numeroExpediente.
   *
   * @return possible object is {@link String }
   *
   */
  public String getNumeroExpediente() {
    return numeroExpediente;
  }

  /**
   * Define el valor de la propiedad numeroExpediente.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setNumeroExpediente(final String value) {
    numeroExpediente = value;
  }

  /**
   * Obtiene el valor de la propiedad numeroExpEni.
   *
   * @return possible object is {@link String }
   *
   */
  public String getNumeroExpEni() {
    return numeroExpEni;
  }

  /**
   * Define el valor de la propiedad numeroExpEni.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setNumeroExpEni(final String value) {
    numeroExpEni = value;
  }

  /**
   * Obtiene el valor de la propiedad asientoAries.
   *
   * @return possible object is {@link String }
   *
   */
  public String getAsientoAries() {
    return asientoAries;
  }

  /**
   * Define el valor de la propiedad asientoAries.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setAsientoAries(final String value) {
    asientoAries = value;
  }

  /**
   * Obtiene el valor de la propiedad fecha.
   *
   * @return possible object is {@link String }
   *
   */
  public String getFecha() {
    return fecha;
  }

  /**
   * Define el valor de la propiedad fecha.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setFecha(final String value) {
    fecha = value;
  }

  /**
   * Obtiene el valor de la propiedad modoEntrega.
   *
   * @return possible object is {@link String }
   *
   */
  public String getModoEntrega() {
    return modoEntrega;
  }

  /**
   * Define el valor de la propiedad modoEntrega.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setModoEntrega(final String value) {
    modoEntrega = value;
  }

  /**
   * Obtiene el valor de la propiedad nifPresentador.
   *
   * @return possible object is {@link String }
   *
   */
  public String getNifPresentador() {
    return nifPresentador;
  }

  /**
   * Define el valor de la propiedad nifPresentador.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setNifPresentador(final String value) {
    nifPresentador = value;
  }

  /**
   * Obtiene el valor de la propiedad formularios.
   *
   * @return possible object is {@link FormulariosVeaType }
   *
   */
  public FormulariosVeaType getFormularios() {
    return formularios;
  }

  /**
   * Define el valor de la propiedad formularios.
   *
   * @param value
   *          allowed object is {@link FormulariosVeaType }
   *
   */
  public void setFormularios(final FormulariosVeaType value) {
    formularios = value;
  }

  /**
   * Obtiene el valor de la propiedad documentosAdjuntos.
   *
   * @return possible object is {@link DocumentosAdjuntosVeaType }
   *
   */
  public DocumentosAdjuntosVeaType getDocumentosAdjuntos() {
    return documentosAdjuntos;
  }

  /**
   * Define el valor de la propiedad documentosAdjuntos.
   *
   * @param value
   *          allowed object is {@link DocumentosAdjuntosVeaType }
   *
   */
  public void setDocumentosAdjuntos(final DocumentosAdjuntosVeaType value) {
    documentosAdjuntos = value;
  }

  /**
   * Obtiene el valor de la propiedad idEntrega.
   *
   * @return possible object is {@link String }
   *
   */
  public String getIdEntrega() {
    return idEntrega;
  }

  /**
   * Define el valor de la propiedad idEntrega.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setIdEntrega(final String value) {
    idEntrega = value;
  }

  /**
   * Obtiene el valor de la propiedad transicion.
   *
   * @return possible object is {@link String }
   *
   */
  public String getTransicion() {
    return transicion;
  }

  /**
   * Define el valor de la propiedad transicion.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setTransicion(final String value) {
    transicion = value;
  }

  /**
   * Obtiene el valor de la propiedad idFaseTrewa.
   *
   * @return possible object is {@link String }
   *
   */
  public String getIdFaseTrewa() {
    return idFaseTrewa;
  }

  /**
   * Define el valor de la propiedad idFaseTrewa.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setIdFaseTrewa(final String value) {
    idFaseTrewa = value;
  }

  /**
   * Obtiene el valor de la propiedad idTareaTrewa.
   *
   * @return possible object is {@link String }
   *
   */
  public String getIdTareaTrewa() {
    return idTareaTrewa;
  }

  /**
   * Define el valor de la propiedad idTareaTrewa.
   *
   * @param value
   *          allowed object is {@link String }
   *
   */
  public void setIdTareaTrewa(final String value) {
    idTareaTrewa = value;
  }

}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.vea.CampoVeaType;
import es.juntadeandalucia.aacid.comuntramitacion.dto.vea.DatosVea;
import es.juntadeandalucia.aacid.comuntramitacion.exception.ParseCamposVeaException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.SubsanacionException;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea.AaciTSolicitudsubongd;

/**
 * ProcesamientoEspecificoUtils class.
 *
 * @author isotrol.
 *
 */
public class ProcesamientoEspecificoUtils {

  /** Constante GUION_BAJO. */
  private static final String GUION_BAJO = "_";

  private ProcesamientoEspecificoUtils() {
  }

  /** LOGGER ProcesamientoEspecifico.class */
  private static final Logger LOGGER = Logger.getLogger(ProcesamientoEspecificoUtils.class);

  /**
   * Convierte el xml a objeto válido de DatosVea
   *
   * @param xmlString
   *          xml
   * @return DatosVea datosVea
   * @throws XMLStreamException
   * @throws JAXBException
   * @throws Exception
   *           exception
   */
  public static DatosVea xmlTojaxbObject(final String xmlString) throws XMLStreamException, JAXBException {
    JAXBContext jaxbContext;
    DatosVea respuesta = null;
    try {
      jaxbContext = JAXBContext.newInstance(DatosVea.class);

      final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

      final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
      final InputStream in = new ByteArrayInputStream(xmlString.getBytes());
      final InputStreamReader reader = new InputStreamReader(in);
      final XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(reader);

      respuesta = (DatosVea) jaxbUnmarshaller.unmarshal(xmlStreamReader);

    } catch (final XMLStreamException e) {
      LOGGER.error(e);
      throw new XMLStreamException(e.getMessage());
    } catch (final JAXBException e) {
      LOGGER.error(e);
      throw new JAXBException(e.getMessage());
    }
    return respuesta;
  }

  public static void setearCamposPorTipo(final Field field, final String campoVea, final AaciTSolicitudsubongd solicitudSubvencion)
      throws SubsanacionException {
    LOGGER.info("Se esta procesando el campo:" + field.getName());
    try {
      if (!StringUtils.isEmpty(campoVea)) {
        switch (field.getType().getName()) {
        case "java.util.Date":
          final Date date1 = new SimpleDateFormat(ConstantesTramitacion.FORMATO_FECHA_UE).parse(campoVea);
          field.set(solicitudSubvencion, date1);
          break;
        case "java.math.BigDecimal":
          field.set(solicitudSubvencion, UtilidadesNumero.convertStringToBigDecimalIfNotBlank(campoVea));
          break;
        case "java.lang.Boolean":
          field.set(solicitudSubvencion, Boolean.valueOf(campoVea));
          break;
        case "java.lang.Long":
          final BigDecimal campo = UtilidadesNumero.convertStringToBigDecimalIfNotBlank(campoVea);
          if (campo != null) {
            field.set(solicitudSubvencion, campo.longValue());
          }
          break;
        case "java.lang.String":
          field.set(solicitudSubvencion, campoVea);
          break;
        }
      }
    } catch (ParseException | IllegalArgumentException | IllegalAccessException e) {
      LOGGER.error("Se ha producido un error en el campo: " + field.getName() + "con valor " + campoVea);
      throw new SubsanacionException("Error al seter el campo ", e);
    }
  }

  public static void setearCamposEntregaVeA(final Object formulario, final Class<?> clazz, final List<CampoVeaType> camposVea)
      throws IllegalAccessException, NoSuchFieldException, ParseCamposVeaException {

    StringBuilder parsed;

    for (final CampoVeaType campoVea : camposVea) {

      final String[] campoDividido = campoVea.getNombre().toLowerCase().split("_");
      parsed = new StringBuilder();

      for (final String parte : campoDividido) {
        if (parsed.toString().isEmpty()) {
          parsed.append(parte);
        } else {
          parsed.append(parte.substring(0, 1).toUpperCase(Locale.ROOT));
          parsed.append(parte.substring(1));
        }
      }

      Field field = null;
      try {
        field = clazz.getDeclaredField(parsed.toString());
      } catch (final NoSuchFieldException e) {
        LOGGER.error("El campo " + parsed.toString() + " no se encuentra en la clase " + clazz.getName());
      } catch (final SecurityException e) {
        throw new IllegalAccessException(e.getMessage());
      }
      if (field != null) {
        if (field.getName() == null) {
          LOGGER.info("Error al acceder a un campo de clase.");
          throw new IllegalAccessException("Error al acceder al campo: " + parsed.toString() + " el campo es nulo");
        } else {
          field.setAccessible(true);
          final String valorCampoVea = campoVea.getValor();
          setearCamposVeAPorTipo(field, valorCampoVea, formulario);
        }
      }
    }
  }

  private static void setearCamposVeAPorTipo(final Field field, final String campoVea, final Object formulario) throws ParseCamposVeaException {
    LOGGER.info("Se esta procesando el campo:" + field.getName());
    try {
      if (!StringUtils.isEmpty(campoVea)) {
        switch (field.getType().getName()) {
        case "java.util.Date":
          final Date date1 = new SimpleDateFormat(ConstantesTramitacion.FORMATO_FECHA_UE).parse(campoVea);
          field.set(formulario, date1);
          break;
        case "java.math.BigDecimal":
          field.set(formulario, UtilidadesNumero.convertStringToBigDecimalIfNotBlank(campoVea));
          break;
        case "java.lang.Boolean":
          field.set(formulario, Boolean.valueOf(campoVea));
          break;
        case "java.lang.Long":
          final BigDecimal campo = UtilidadesNumero.convertStringToBigDecimalIfNotBlank(campoVea);
          if (campo != null) {
            field.set(formulario, campo.longValue());
          }
          break;
        case "java.lang.String":
          field.set(formulario, campoVea);
          break;
        }
      }
    } catch (ParseException | IllegalArgumentException | IllegalAccessException e) {
      LOGGER.error("Se ha producido un error en el campo: " + field.getName() + "con valor " + campoVea);
      throw new ParseCamposVeaException("Error al seter el campo ", e);
    }
  }

  /**
   * Calcula el número de registros insertados en bloque repetible
   *
   * @param variables
   *          variables formulario
   * @param nombreBloque
   *          nombre del bloque repetible
   * @param nombreColumna
   *          nombre de un campo del bloque
   * @return the int
   */
  public static int numeroRegistroLista(final Map<String, String> variables, final String nombreBloque, final String nombreColumna) {
    int tam = 0;
    boolean enc = false;
    for (int i = 0; i < variables.size() && !enc; i++) {
      final StringBuilder elem = new StringBuilder(nombreBloque);
      elem.append(i);
      elem.append(GUION_BAJO);
      elem.append(nombreColumna);
      if (variables.containsKey(elem.toString())) {
        tam++;
      } else {
        enc = true;
      }
    }
    return tam;
  }
}

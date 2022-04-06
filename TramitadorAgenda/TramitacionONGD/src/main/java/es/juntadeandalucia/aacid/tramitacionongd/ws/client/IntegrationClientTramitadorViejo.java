package es.juntadeandalucia.aacid.tramitacionongd.ws.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesFecha;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ICatalogoService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudService;
import es.juntadeandalucia.plataforma.service.modulos.IConfig;
import es.juntadeandalucia.plataforma.service.modulos.IConfigService;
import sun.net.www.protocol.http.HttpURLConnection;

/**
 * IntegrationClientTramitadorViejo class.
 *
 * @author isotrol.
 *
 */
public class IntegrationClientTramitadorViejo {

  public static final ResourceBundle PROPIEDADES = ResourceBundle
      .getBundle("es.juntadeandalucia.aacid.tramitacionongd.ws.client.IntegrationClientTramitadorViejo");
  /** URL */
  private static final String URL_TRA_VIE = PROPIEDADES.getString("url.tramitador");
  /** COD_IDENT */
  private static final String NUM_EXP = PROPIEDADES.getString("url.params.numexp");
  /** FECHA_LIMITE */
  private static final String FECHA_LIMITE = PROPIEDADES.getString("url.params.fechaLimite");
  /** MOTIVOS */
  private static final String MOTIVOS = PROPIEDADES.getString("url.params.motivos");
  /** LOGGER IntegrationClientTramitadorViejo.class */
  private final transient Logger log = Logger.getLogger(IntegrationClientTramitadorViejo.class);
  /** catalogoService */
  private static ICatalogoService catalogoService;
  /** solicitudService */
  private static ISolicitudService solicitudService;

  /** ConfigService */
  private static IConfigService configService;

  static {
    final WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
    solicitudService = (ISolicitudService) context.getBean("solicitudService");
    catalogoService = (ICatalogoService) context.getBean("catalogoService");
    configService = (IConfigService) context.getBean("configService");
  }

  /**
   * Obtener respuesta del WS tramitador viejo.
   *
   * @param idExpTrewa
   *          id expediente de trewa
   * @return respuesta
   * @throws TramitacionException
   *           excepcion.
   * @throws IOException
   *           excepcion.
   */
  public String obtenerRespuestaPeticionAacidViejo(final String idExpTrewa) throws TramitacionException, IOException {

    List<CatalogoDTO> listaMotivos = null;
    final StringBuilder response = new StringBuilder();
    log.info("Se procede a obtener la respuesta del WS de AACID antiguo. IdExpTrewa: " + idExpTrewa);

    // obtenemos el codigo identificativo correspondiente al tramitador viejo.
    final SolicitudDTO solicitud = solicitudService.obtenerSolicitudByIdExp(idExpTrewa);

    String fechaLimite;

    final Calendar fechaActual = new GregorianCalendar();

    fechaActual.add(Calendar.DAY_OF_MONTH, 10);

    fechaLimite = UtilidadesFecha.parseDateToStringFormatoddMMyy(fechaActual.getTime());

    if (null != solicitud && null != solicitud.getIdSolicitud() && null != solicitud.getCodIdentificativo()) {
      // obtenemos los motivos de subsanacion correspondientes a la solicitud.
      listaMotivos = catalogoService.obtenerListaCodigosSubsanacion(solicitud.getIdSolicitud());

      if (!listaMotivos.isEmpty()) {
        final String codigoSubsanacionList = montarListadoMotivos(listaMotivos);
        final StringBuilder urlWs = new StringBuilder();
        urlWs.append(obtenerConfiguracion(URL_TRA_VIE));
        urlWs.append("?");
        urlWs.append(obtenerConfiguracion(NUM_EXP));
        urlWs.append("=");
        urlWs.append(solicitud.getCodIdentificativo());
        urlWs.append("&");
        urlWs.append(obtenerConfiguracion(FECHA_LIMITE));
        urlWs.append("=");
        urlWs.append(fechaLimite);
        urlWs.append("&");
        urlWs.append(obtenerConfiguracion(MOTIVOS));
        urlWs.append("=");
        urlWs.append(codigoSubsanacionList);

        String content;
        final BufferedReader br = obtenerRespuestaWS(urlWs.toString());
        while ((content = br.readLine()) != null) {
          response.append(content);
        }
        br.close();
        log.info("Finalizada la invocacion al tramitador viejo");
      }

    } else {
      log.warn("La solicitud asignada al expediente " + idExpTrewa + " es nula, no se ha podido realizar la comunicación con el WS");
    }
    log.info("Respuesta del servicio: " + response.toString());
    return response.toString();
  }

  private String montarListadoMotivos(final List<CatalogoDTO> listaMotivos) {
    final StringBuilder codigoSubsanacionList = new StringBuilder();
    for (final CatalogoDTO motivo : listaMotivos) {
      if (!codigoSubsanacionList.toString().isEmpty()) {
        codigoSubsanacionList.append(",");
      }
      codigoSubsanacionList.append(motivo.getDescripcion());
    }
    return codigoSubsanacionList.toString();
  }

  private String obtenerConfiguracion(final String nombreConf) {
    final List<IConfig> configuraciones = configService.obtenerParametrosConfiguracion(nombreConf, null, null, true);
    if (CollectionUtils.isNotEmpty(configuraciones)) {
      final IConfig configuracion = configuraciones.get(0);
      if (configuracion != null) {
        return configuracion.getValor();
      }
    }
    return "";
  }

  /**
   * Invocacion al endpoint especifico
   *
   * @param endpoint
   * @throws Exception
   * @throws IOException
   * @throws TramitacionException
   */
  private BufferedReader obtenerRespuestaWS(final String endpoint) throws IOException, TramitacionException {

    final URL url = new URL(endpoint);
    BufferedReader br = null;
    final String protocol = endpoint.substring(0, endpoint.indexOf(":"));
    HttpURLConnection conn;
    HttpsURLConnection conn2;
    try {
      log.info("Invocando al Servicio Web del tramitador viejo");
      log.info("Protocolo: " + protocol);
      if (protocol.equalsIgnoreCase("https")) {
        conn2 = (HttpsURLConnection) url.openConnection();
        conn2.setRequestMethod(HttpMethod.GET.toString());
        log.info("Conn: " + conn2);
        log.info("Conn-ResponseCode: " + conn2.getResponseCode());

        if (String.valueOf(conn2.getResponseCode()).startsWith("5") || String.valueOf(conn2.getResponseCode()).startsWith("4")) {
          log.info("Conn-ErrorStream: " + conn2.getErrorStream());
          final StringBuilder response = new StringBuilder();
          if (conn2.getErrorStream() != null) {
            final InputStreamReader in = new InputStreamReader(conn2.getErrorStream());
            br = new BufferedReader(in);
            String content;
            while ((content = br.readLine()) != null) {
              response.append(content);
            }
            response.append(
                "Se ha producido un error al invocar al tramitador viejo codigo del error: " + conn2.getResponseCode() + ", Mensaje de error: " + response);
          } else {
            response.append("Se ha producido un error sin mensaje al invocar al tramitador viejo. Codigo del error: " + conn2.getResponseCode());
          }
          throw new TramitacionException(response.toString());

        } else {
          if (conn2.getInputStream() == null) {
            throw new TramitacionException("El servicio invocado no contiene respuesta alguna");
          }
          final InputStreamReader in = new InputStreamReader(conn2.getInputStream());
          br = new BufferedReader(in);
        }
      } else {
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(HttpMethod.GET.toString());
        // conn.setRequestProperty(org.apache.http.HttpHeaders.ACCEPT, MediaType.TEXT_HTML.toString());
        log.info("Conn: " + conn);
        log.info("Conn-ResponseCode: " + conn.getResponseCode());

        if (String.valueOf(conn.getResponseCode()).startsWith("5") || String.valueOf(conn.getResponseCode()).startsWith("4")) {
          log.info("Conn-ErrorStream: " + conn.getErrorStream());
          final StringBuilder response = new StringBuilder();
          if (conn.getErrorStream() != null) {
            final InputStreamReader in = new InputStreamReader(conn.getErrorStream());
            br = new BufferedReader(in);
            String content;
            while ((content = br.readLine()) != null) {
              response.append(content);
            }
            response.append(
                "Se ha producido un error al invocar al tramitador viejo codigo del error: " + conn.getResponseCode() + ", Mensaje de error: " + response);
          } else {
            response.append("Se ha producido un error sin mensaje al invocar al tramitador viejo. Codigo del error: " + conn.getResponseCode());
          }
          throw new TramitacionException(response.toString());

        } else {
          if (conn.getInputStream() == null) {
            throw new TramitacionException("El servicio invocado no contiene respuesta alguna");
          }
          final InputStreamReader in = new InputStreamReader(conn.getInputStream());
          br = new BufferedReader(in);
        }
      }
    } catch (final IOException e) {
      throw new TramitacionException("Se ha producido un error al invocar al tramitador viejo: " + endpoint, e);
    }
    return br;
  }

}

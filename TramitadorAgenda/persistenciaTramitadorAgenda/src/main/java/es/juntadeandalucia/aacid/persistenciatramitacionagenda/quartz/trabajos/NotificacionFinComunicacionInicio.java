package es.juntadeandalucia.aacid.persistenciatramitacionagenda.quartz.trabajos;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ConvocatoriaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.IEnvioEmailAutomaticoService;
import es.juntadeandalucia.aacid.comuntramitacion.service.IFestivosAACIDService;
import es.juntadeandalucia.aacid.comuntramitacion.service.IParametrosConfiguracionService;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.aacid.comuntramitacion.utils.Utilidades;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesFecha;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Convocatoria;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IConvocatoriasService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudService;
import es.juntadeandalucia.plataforma.contexto.ContextoPTwanda;
import trewa.bd.tpo.TpoPK;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;
import trewa.exception.TrException;

public class NotificacionFinComunicacionInicio {
  private final Logger log = Logger.getLogger(NotificacionFinComunicacionInicio.class);

  public void ejecutarTrabajo() {
    final ApplicationContext appContext = getApplicationContext();
    if (appContext != null) {
      final Calendar fechaHoyCalendar = Calendar.getInstance(new Locale("es", "ES"));
      final Date fechaHoy = fechaHoyCalendar.getTime();
      // ONGD
      final ConvocatoriaDTO convocatoriaONGD = new ConvocatoriaDTO();
      convocatoriaONGD.setAbreviatura(Utilidades.obtenerAbreviaturaConvocatoriaONGD());
      convocatoriaONGD.setFhInicio(fechaHoy);
      convocatoriaONGD.setFhFin(fechaHoy);
      // UNIV
      final ConvocatoriaDTO convocatoriaUNIV = new ConvocatoriaDTO();
      convocatoriaUNIV.setAbreviatura(Utilidades.obtenerAbreviaturaConvocatoriaUNIV());
      convocatoriaUNIV.setFhInicio(fechaHoy);
      convocatoriaUNIV.setFhFin(fechaHoy);

      log.info("Comprobamos plazo comunicaci\u00F3n inicio para los expedientes ONGD");
      tratarAvisos(fechaHoy, convocatoriaONGD, appContext);
      log.info("Comprobamos plazo comunicaci\u00F3n inicio para los expedientes UNIV");
      tratarAvisos(fechaHoy, convocatoriaUNIV, appContext);
    }
  }

  /**
   *
   * @param listaDatosExpe
   * @param fechaHoy
   * @param convocatoria
   * @param appContext
   */
  private void tratarAvisos(final Date fechaHoy, final ConvocatoriaDTO convocatoria, final ApplicationContext appContext) {
    final IConvocatoriasService convocatoriasService = (IConvocatoriasService) appContext.getBean("convocatoriasService");
    final ITrewaService trewaService = (ITrewaService) appContext.getBean("trewaService");
    final ISolicitudService solicitudService = (ISolicitudService) appContext.getBean("solicitudService");
    final IEnvioEmailAutomaticoService envioEmailAutomaticoService = (IEnvioEmailAutomaticoService) appContext.getBean("envioEmailAutomaticoService");
    final IParametrosConfiguracionService parametrosConfiguracionService = (IParametrosConfiguracionService) appContext
        .getBean("parametrosConfiguracionService");
    final IFestivosAACIDService festivosAACIDService = (IFestivosAACIDService) appContext.getBean("festivosAACIDService");

    try {
      int plazoFinComunicacionInicioMeses = 0;
      int diasAvisoFinComunicacionInicio = 0;
      String emailAviso = "";

      final List<TrExpediente> listaDatosExpe = obtenerSolicitudesHijas(convocatoriasService, trewaService, convocatoria);
      if (CollectionUtils.isNotEmpty(listaDatosExpe)) {
        // Recorrer listado de expedientes y comprobar la fecha limite a ver si está a falta de 7 días de finalización. Los días será
        // un
        // campo paramétrico.
        plazoFinComunicacionInicioMeses = Integer.parseInt(parametrosConfiguracionService.obtenerParametroConfiguracion(
            ConstantesTramitacion.PLAZO_COMUNICACION_INICIO_MESES, listaDatosExpe.get(0).getDEFPROC().getREFDEFPROC().toString()));
        diasAvisoFinComunicacionInicio = Integer.parseInt(parametrosConfiguracionService.obtenerParametroConfiguracion(
            ConstantesTramitacion.PLAZO_AVISO_FIN_COMUNICACION_DIAS, listaDatosExpe.get(0).getDEFPROC().getREFDEFPROC().toString()));
        emailAviso = parametrosConfiguracionService.obtenerParametroConfiguracion(ConstantesTramitacion.EMAIL_AVISO_FIN_COMUNICACION_INICIO,
            listaDatosExpe.get(0).getDEFPROC().getREFDEFPROC().toString());
      }

      // Recorremos los expedientes hijos de la convocatoria
      final LocalDate getLocalDate = fechaHoy.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      final List<Date> diasFestivos = festivosAACIDService.obtenerfestivos(getLocalDate.getYear(), ConstantesTramitacion.FESTIVO_NACIONAL);
      Date fechaFinComuInicio;
      for (final TrExpediente exp : listaDatosExpe) {
        final SolicitudDatosGeneralesDTO solicitud = solicitudService.datosGeneralesSolicitudByIdExpTrewa(exp.getREFEXP().toString());

        if (solicitud.getFechaPostegracionComunInicio() != null) {
          fechaFinComuInicio = solicitud.getFechaPostegracionComunInicio();
        } else {
          fechaFinComuInicio = UtilidadesFecha.sumarMesesFecha(solicitud.getFhPagoSubvencion(), plazoFinComunicacionInicioMeses);
        }

        // Comprobamos que la fecha limite esté dentro de tiempo para crear el aviso ejemplo que falten 7 días
        final int diasRestantesFinComunicacionInicio = UtilidadesFecha.obtenerDiferenciaEnDiasHabiles(fechaHoy, fechaFinComuInicio, diasFestivos);
        if (diasRestantesFinComunicacionInicio <= diasAvisoFinComunicacionInicio) {
          log.info("ENVIO DE CORREO PARA EL EXPEDIENTE " + exp.getNUMEXP());
          envioEmailAutomaticoService.enviarEmail(ConstantesTramitacion.AVISO_FIN_COMUNICACION_INICIO, emailAviso, exp.getREFEXP().toString(), null,
              diasRestantesFinComunicacionInicio);
        }
      }
    } catch (final TramitacionException e) {
      log.error("Se ha producido un error al recuperar los parámetros de configuración", e);
    } catch (final TrException e) {
      log.error("Se ha producido un error al recuperar las solicitudes hijas de la convocatoria", e);
    } catch (final AddressException e) {
      log.error("La direccion para el envio de correo automatico de aviso de fin comunicación es incorrecto. ERROR:" + e.getMessage());
    } catch (final MessagingException e) {
      log.error("Error en el envio de correo automatico de aviso de fin comunicación. Expediente. Causa: " + e.getMessage());
    } catch (final Exception ex) {
      log.error("Error en el envio de correo automatico de aviso de fin comunicación. Expediente. Causa: " + ex.getMessage());
    }
  }

  /**
   * @param convocatoriasService
   * @param trewaService
   * @param listaDatosExpe
   * @param convocatoria
   * @throws TramitacionException
   * @throws TrException
   */
  private final List<TrExpediente> obtenerSolicitudesHijas(final IConvocatoriasService convocatoriasService, final ITrewaService trewaService,
      final ConvocatoriaDTO convocatoria) throws TramitacionException, TrException {
    final List<Convocatoria> listaConvocatorias;
    final List<TrExpediente> listaDatosExpe = new ArrayList<>();
    listaConvocatorias = convocatoriasService.obtenerConvocatoriasByFiltro(convocatoria);
    for (final Convocatoria conv : listaConvocatorias) {
      listaDatosExpe.addAll(trewaService.obtenerExpedientesHijos(new TpoPK(conv.getConvXExpe()), ConstantesTramitacion.FASE_COMUNICACION_INICIO));
    }
    return listaDatosExpe;
  }

  public static ApplicationContext getApplicationContext() {
    return ContextoPTwanda.getContext();
  }

}
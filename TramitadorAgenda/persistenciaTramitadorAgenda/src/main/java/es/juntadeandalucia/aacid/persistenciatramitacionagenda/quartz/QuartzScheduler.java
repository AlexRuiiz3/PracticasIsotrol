package es.juntadeandalucia.aacid.persistenciatramitacionagenda.quartz;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import es.juntadeandalucia.aacid.persistenciatramitacionagenda.quartz.trabajos.NotificacionFinComunicacionInicio;

public class QuartzScheduler extends QuartzJobBean {

  private NotificacionFinComunicacionInicio notificacionFinComunicacionInicio;
  private final Logger log = Logger.getLogger(QuartzScheduler.class);

  @Override
  protected void executeInternal(final JobExecutionContext arg0) throws JobExecutionException {
    log.info("INICIO CRON: COMPROBACION FIN PLAZO DE EXPEDIENTES EN COMUNICACION INICIO.");
    notificacionFinComunicacionInicio.ejecutarTrabajo();
    log.info("FIN CRON: COMPROBACION FIN PLAZO DE EXPEDIENTES EN COMUNICACION INICIO.");

  }

  /**
   * Obtiene la propiedad notificacionFinComunicacionInicio
   *
   * @return el notificacionFinComunicacionInicio
   */
  public NotificacionFinComunicacionInicio getNotificacionFinComunicacionInicio() {
    return notificacionFinComunicacionInicio;
  }

  /**
   * Establece el valor de la propiedad notificacionFinComunicacionInicio
   *
   * @param notificacionFinComunicacionInicio
   *          el notificacionFinComunicacionInicio para establecer
   */
  public void setNotificacionFinComunicacionInicio(final NotificacionFinComunicacionInicio notificacionFinComunicacionInicio) {
    this.notificacionFinComunicacionInicio = notificacionFinComunicacionInicio;
  }

}
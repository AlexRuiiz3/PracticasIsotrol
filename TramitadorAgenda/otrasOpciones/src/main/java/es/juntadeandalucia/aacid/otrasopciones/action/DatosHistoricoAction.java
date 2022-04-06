package es.juntadeandalucia.aacid.otrasopciones.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import es.juntadeandalucia.aacid.comuntramitacion.dto.HistoricoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IHistoricoService;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;

/**
 * Action para operaciones del historico
 * 
 * @author isotrol.
 *
 */
public class DatosHistoricoAction extends AbstractOtrasOpcionesAction {

  /**
   * 
   */
  private static final long serialVersionUID = -7740534787286082237L;

  /** LOGGER TareasSubsanacionAction.class */
  private final transient Logger log = Logger.getLogger(DatosHistoricoAction.class);

  /** historicoService */
  private IHistoricoService historicoService;

  /** Interfaz api de trewa */
  private transient ITrewaService trewaService;

  /** listaHistoricos */
  private List<HistoricoDTO> listaHistoricos = new ArrayList<>();

  public String cargaListadoHistorico() throws ParseException {
    try {

      // se obtiene el usuario de la sesion
      usuario = getUsuarioSesion();
      // se obtiene el id del expediente correspondiente al expediente.
      String idExp = usuario.getExpediente().getRefExpediente();

      TrExpediente expediente = trewaService.obtenerExpedienteTrewa(idExp);
      if (null != expediente.getNUMEXP()) {
        listaHistoricos = historicoService.findHistoricosByIdExpediente(expediente.getNUMEXP());
      }

    } catch (TramitacionException e) {
      log.error("Error al obtener los historicos para el expediente", e);
      return ERROR;
    }

    return SUCCESS;
  }

  /**
   * @param historicoService
   *          the historicoService to set
   */
  public void setHistoricoService(IHistoricoService historicoService) {
    this.historicoService = historicoService;
  }

  /**
   * @return the listaHistoricos
   */
  public List<HistoricoDTO> getListaHistoricos() {
    return listaHistoricos;
  }

  /**
   * @param listaHistoricos
   *          the listaHistoricos to set
   */
  public void setListaHistoricos(List<HistoricoDTO> listaHistoricos) {
    this.listaHistoricos = listaHistoricos;
  }

  /**
   * @param trewaService
   *          the trewaService to set
   */
  public void setTrewaService(ITrewaService trewaService) {
    this.trewaService = trewaService;
  }

}

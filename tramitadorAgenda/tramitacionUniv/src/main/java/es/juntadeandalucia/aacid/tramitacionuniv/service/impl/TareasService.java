package es.juntadeandalucia.aacid.tramitacionuniv.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.juntadeandalucia.aacid.tramitacionuniv.service.ITareasService;
import es.juntadeandalucia.plataforma.PTWanda.PTWandaServiceImpl;
import es.juntadeandalucia.plataforma.service.usuarios.IUsuario;
import trewa.bd.sql.ClausulaWhere;
import trewa.bd.sql.OperadorWhere;
import trewa.bd.tpo.TpoPK;
import trewa.bd.trapi.trapiui.TrAPIUI;
import trewa.bd.trapi.trapiui.tpo.TrTareaExpediente;

/**
 * TareasService class.
 * 
 * @author isotrol.
 *
 */
public class TareasService extends PTWandaServiceImpl implements ITareasService {

  /** Log log, Atributo para los mensajes de log */
  protected final Log log = LogFactory.getLog(getClass());

  /** apiUI */
  private TrAPIUI apiUI;

  public void finalizarTarea(String etiquetaTarea, TpoPK idExpediente, TpoPK idFase, IUsuario usuario) throws Exception {
    ClausulaWhere cw3 = new ClausulaWhere();
    cw3.addExpresion(TrTareaExpediente.CAMPO_NOMBRETAREA, OperadorWhere.OP_IGUAL, etiquetaTarea);
    TrTareaExpediente[] tareas = apiUI.obtenerTareasExpediente(idExpediente, false, cw3, null);
    if (tareas != null && tareas.length > 0) {
      apiUI.finalizarTareaExpediente(tareas[0].getREFTAREA(), tareas[0].getTIPO(), null);
    }
  }

  /**
   * Obtiene la propiedad apiUI
   * 
   * @return el apiUI
   */
  public TrAPIUI getApiUI() {
    return apiUI;
  }

  /**
   * Establece el valor de la propiedad apiUI
   * 
   * @param apiUI
   *          el apiUI para establecer
   */
  public void setApiUI(TrAPIUI apiUI) {
    this.apiUI = apiUI;
  }

}

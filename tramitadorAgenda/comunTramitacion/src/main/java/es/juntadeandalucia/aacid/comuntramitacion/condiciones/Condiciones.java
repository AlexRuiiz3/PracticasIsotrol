package es.juntadeandalucia.aacid.comuntramitacion.condiciones;

import java.math.BigDecimal;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import trewa.bd.trapi.trapiui.tpo.TrTareaExpediente;
import trewa.ext.TrAccesoUI;

public class Condiciones extends TrAccesoUI {

  /** trewaService */
  private static ITrewaService trewaService;
  static {
    WebApplicationContext context;
    context = ContextLoader.getCurrentWebApplicationContext();
    trewaService = (ITrewaService) context.getBean("trewaService");
  }

  public Integer comprobarEstadoFinalizadaTareas(final BigDecimal idExp) throws TramitacionException {
    String descripcionTarea = "";
    try {

      final String abreviatura = trewaService.getAbreviaturaProcedimiento(idExp.toString());

      if (abreviatura.equals(ConstantesTramitacion.ABREVIATURA_ONGD)) {
        descripcionTarea = ConstantesTramitacion.TAREA_GEN_SUB_VAL_ONGD;
      } else {
        descripcionTarea = ConstantesTramitacion.TAREA_GEN_SUB_VAL_UNIV;
      }

      final TrTareaExpediente[] tareas = trewaService.obtenerTareasExpediente(idExp.toString(), descripcionTarea);
      if (tareas != null) {
        for (final TrTareaExpediente element : tareas) {
          if (ConstantesTramitacion.FINALIZADA.equals(element.getESTADO())) {
            return 1;
          }
        }
      }
    } catch (final TramitacionException e) {
      throw new TramitacionException("Error al obtener las tareas del expediente", e);
    }
    return 0;

  }

}

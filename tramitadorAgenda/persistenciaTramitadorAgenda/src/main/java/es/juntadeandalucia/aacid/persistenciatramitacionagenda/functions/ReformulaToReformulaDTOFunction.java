/**
 * 
 */
package es.juntadeandalucia.aacid.persistenciatramitacionagenda.functions;

import java.util.function.Function;

import es.juntadeandalucia.aacid.comuntramitacion.dto.reformula.ReformulaDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Reformula;

/**
 * 
 * Función para transformar un objeto Reformula en ReformulaDTO.
 * 
 */
public class ReformulaToReformulaDTOFunction implements Function<Reformula, ReformulaDTO> {

  @Override
  public ReformulaDTO apply(Reformula entidad) {
    ReformulaDTO dto = new ReformulaDTO();
    dto.setMaximo(entidad.getMaximo());
    dto.setMinimo(entidad.getMinimo());
    dto.setTipo(entidad.getTipo() != null ? entidad.getTipo() : "-1");
    dto.setIdSolicitud(entidad.getIdSolicitud());
    return dto;
  }

}

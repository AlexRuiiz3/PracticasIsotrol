/**
 * 
 */
package es.juntadeandalucia.aacid.persistenciatramitacionagenda.functions;

import java.util.function.Function;

import es.juntadeandalucia.aacid.comuntramitacion.dto.reformula.DatosReformulaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.reformula.ReformulaDTO;

/**
 * 
 * Función para transformar un objeto ReformulaDTO en DatosReformulaDTO.
 * 
 */
public class ReformulaDTOToDatosReformulaFunction implements Function<ReformulaDTO, DatosReformulaDTO> {

  @Override
  public DatosReformulaDTO apply(ReformulaDTO reformulaDTO) {

    DatosReformulaDTO datosReformulaDTO = new DatosReformulaDTO();
    datosReformulaDTO.setIdSolicitud(reformulaDTO.getIdSolicitud());
    datosReformulaDTO.setTipoSeleccionado(reformulaDTO.getTipo());
    datosReformulaDTO.setMaximoAACID(reformulaDTO.getMaximo().toString());
    datosReformulaDTO.setMinimoPresupuestoTotal(reformulaDTO.getMinimo().toString());

    return datosReformulaDTO;
  }

}

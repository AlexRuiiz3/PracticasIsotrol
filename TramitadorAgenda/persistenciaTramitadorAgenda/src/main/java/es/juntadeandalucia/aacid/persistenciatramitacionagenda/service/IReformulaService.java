package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;
import java.util.Optional;

import es.juntadeandalucia.aacid.comuntramitacion.dto.reformula.DatosReformulaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.reformula.ReformulaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;

public interface IReformulaService {

  Optional<ReformulaDTO> findBySolicitudId(Long idSolicitud) throws TramitacionException;

  ReformulaDTO save(DatosReformulaDTO datosReformulaDTO) throws TramitacionException;

  List<String> validated(DatosReformulaDTO datosReformulaDTO);

}

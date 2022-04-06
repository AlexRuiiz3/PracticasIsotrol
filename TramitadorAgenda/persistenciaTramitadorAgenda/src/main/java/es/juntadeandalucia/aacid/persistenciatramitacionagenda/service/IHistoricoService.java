package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.text.ParseException;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.HistoricoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;

public interface IHistoricoService {

  /**
   * Crea un nuevo historico
   * 
   * @param idExp
   * @param usuario
   * @return boolean
   * @throws TramitacionException
   */
  boolean guardarHistorico(final String idExp, final String usuario) throws TramitacionException;

  /**
   * Selecciona todos los históricos por su número de expediente
   * 
   * @param numExp
   *          id expediente
   * @return lista de historicos
   * @throws TramitacionException
   *           exception
   * @throws ParseException 
   */
  List<HistoricoDTO> findHistoricosByIdExpediente(final String numExp) throws TramitacionException, ParseException;
}

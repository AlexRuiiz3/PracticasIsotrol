package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Historico;

public interface IHistoricoDao {

  /**
   * Crea un nuevo historico
   * 
   * @param historico
   * @return Historico
   * @throws TramitacionException
   */
  Historico guardarHistorico(Historico historico) throws TramitacionException;

  /**
   * Selecciona todos los históricos por su número de expediente
   * 
   * @param numExp
   *          numero de expediente
   * @return lista de historicos
   * @throws TramitacionException
   *           exception
   */
  List<Historico> findHistoricosByIdExpediente(final String numExp) throws TramitacionException;
}

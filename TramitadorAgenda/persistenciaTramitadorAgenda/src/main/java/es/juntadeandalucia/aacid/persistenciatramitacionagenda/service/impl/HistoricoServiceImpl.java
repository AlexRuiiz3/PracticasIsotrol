package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.juntadeandalucia.aacid.comuntramitacion.dto.HistoricoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.HistoricoConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IHistoricoDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Historico;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IHistoricoService;

public class HistoricoServiceImpl implements IHistoricoService {

  /** Log log, Atributo para los mensajes de log */
  protected final Log log = LogFactory.getLog(getClass());

  /** interfaz de convocatorias */
  private IHistoricoDao historicoDao;

  @Override
  public List<HistoricoDTO> findHistoricosByIdExpediente(final String numExp) throws TramitacionException, ParseException {
    List<Historico> historicos = null;
    try {
      historicos = historicoDao.findHistoricosByIdExpediente(numExp);
      return HistoricoConverter.convertirListToDto(historicos);

    } catch (final TramitacionException e) {
      throw new TramitacionException("Se ha producido un error al recuperar el listado de historicos ", e);
    }

  }

  @Override
  public boolean guardarHistorico(final String numExp, final String usuario) throws TramitacionException {
    Historico historico = HistoricoConverter.convertirDtoToDao(numExp, usuario);
    try {
      historico = historicoDao.guardarHistorico(historico);
      if (null != historico) {
        return true;
      }
    } catch (final TramitacionException e) {
      throw new TramitacionException("Se ha producido un error al guardar en el historico ", e);
    }
    return false;
  }

  /**
   * @return the historicoDao
   */
  public IHistoricoDao getHistoricoDao() {
    return historicoDao;
  }

  /**
   * @param historicoDao
   *          the historicoDao to set
   */
  public void setHistoricoDao(final IHistoricoDao historicoDao) {
    this.historicoDao = historicoDao;
  }

}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ConvocatoriaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.TipoConvocatoriaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesFecha;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.ConvocatoriaConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.TipoConvocatoriaConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IConvocatoriaDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Convocatoria;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TipoConvocatoria;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IConvocatoriasService;

/**
 * Servicio de convocatorias
 *
 * @author isotrol
 *
 */
public class ConvocatoriasServiceImpl implements IConvocatoriasService {

  /** Log log, Atributo para los mensajes de log */
  protected final Log log = LogFactory.getLog(getClass());

  /** interfaz de convocatorias */
  private IConvocatoriaDao convocatoriaDao;

  /**
   * busqueda de la convocatoria por su id.
   *
   * @throws ParseException
   */
  @Override
  public ConvocatoriaDTO buscarConvocatoriaDTOPorIdExpediente(final String idExp) throws TramitacionException {
    ConvocatoriaDTO convocatoriaDTO = null;
    final Convocatoria convocatoria = convocatoriaDao.obtenerDatosConvocatoriaPorExpediente(idExp);
    if (null != convocatoria) {
      convocatoriaDTO = ConvocatoriaConverter.convertirToDto(convocatoria);
    }
    return convocatoriaDTO;
  }

  /**
   * busqueda de la convocatoria por su id.
   *
   * @throws ParseException
   */
  @Override
  public Convocatoria buscarConvocatoriaPorIdExpediente(final String idExp) throws TramitacionException {
    return convocatoriaDao.obtenerDatosConvocatoriaPorExpediente(idExp);
  }

  /**
   * busqueda del id tipo de convocatoria
   */
  @Override
  public TipoConvocatoriaDTO buscarIdTipConvPorAbreviatura(final String abreviatura) throws TramitacionException {
    String denominacion = "";
    // se comprueba que tipo de denominación tiene asociada la abreviatura
    if (abreviatura.contains(ConstantesTramitacion.ABREVIATURA_ONGD)) {
      denominacion = ConstantesTramitacion.TIPO_CONV_ONGD;
    }
    if (abreviatura.contains(ConstantesTramitacion.ABREVIATURA_UNIV)) {
      denominacion = ConstantesTramitacion.TIPO_CONV_UNIV;
    }
    try {
      // se busca el id del tipo de convocatoria según su denominación.
      final TipoConvocatoria tipoConvocatoriaDao = convocatoriaDao.buscarIdTipConvPorAbreviatura(denominacion);
      if (null != tipoConvocatoriaDao) {
        return TipoConvocatoriaConverter.convertirTipoConvDaoToDTO(tipoConvocatoriaDao);
      }
    } catch (final TramitacionException e) {
      throw new TramitacionException("Se ha producido un error al obtener el tipo de convocatoria ", e);
    }

    return null;
  }

  /**
   * Crea o modifica una convocatoria en AACID_T_CONVOCATORIAS
   *
   * @throws ParseException
   */
  @Override
  public boolean crearOmodificarConvocatoria(Convocatoria convocatoria, final ConvocatoriaDTO convocatoriaDTO) throws TramitacionException, ParseException {
    try {
      convocatoria.setConvNuAnio(UtilidadesFecha.obtenerAnioActual());
      convocatoria = convocatoriaDao.guardarOmodificarConvocatoria(ConvocatoriaConverter.convertirDtoToDao(convocatoria, convocatoriaDTO));
      if (null != convocatoria) {
        return true;
      }
    } catch (final TramitacionException e) {
      throw new TramitacionException("Se ha producido un error al obtener los datos de la convocatoria ", e);
    }
    return false;
  }

  @Override
  public List<Convocatoria> obtenerConvocatoriasByFiltro(final ConvocatoriaDTO filtro) throws TramitacionException {
    return convocatoriaDao.obtenerConvocatoriasByFiltro(filtro);
  }

  /**
   * @return the convocatoriaDao
   */
  public IConvocatoriaDao getConvocatoriaDao() {
    return convocatoriaDao;
  }

  /**
   * @param convocatoriaDao
   *          the convocatoriaDao to set
   */
  public void setConvocatoriaDao(final IConvocatoriaDao convocatoriaDao) {
    this.convocatoriaDao = convocatoriaDao;
  }

}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao;

import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ConvocatoriaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Convocatoria;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TipoConvocatoria;

public interface IConvocatoriaDao {

  /**
   * Obtiene los datos de la convocatoria de la tabla AACI_T_CONVOCATORIAS
   *
   * @param idExp
   *          id del expediente
   * @return convocatoriaDao
   */
  Convocatoria obtenerDatosConvocatoriaPorExpediente(String idExp) throws TramitacionException;

  /**
   * Recupera el id de la tabla AACI_T_TIPO_CONV según su denominación a partir de una abreviatura pasada como parámetro.
   *
   * @param denominacion
   * @return TipoConvocatoriaDao tipo de convocatoria
   */
  TipoConvocatoria buscarIdTipConvPorAbreviatura(String denominacion) throws TramitacionException;

  /**
   * Guarda o modifica una convocatoria en AACI_T_CONVOCATORIAS
   *
   * @param convocatoriaDao
   *          convocatoriaDao
   * @return {@link Convocatoria} convocatoriaDao
   * @throws TramitacionException
   */
  Convocatoria guardarOmodificarConvocatoria(Convocatoria convocatoriaDao) throws TramitacionException;

  /**
   * obtenemos las convocatorias que coincidan con el filtro utilizado
   *
   * @param filtro
   * @return
   */
  List<Convocatoria> obtenerConvocatoriasByFiltro(ConvocatoriaDTO filtro);

}

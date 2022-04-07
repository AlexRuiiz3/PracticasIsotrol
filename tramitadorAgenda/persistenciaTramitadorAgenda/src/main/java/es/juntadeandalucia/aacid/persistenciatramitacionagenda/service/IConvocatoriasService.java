package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.text.ParseException;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ConvocatoriaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.TipoConvocatoriaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Convocatoria;

/**
 * interfaz para las convocatorias
 *
 * @author isotrol
 *
 */
public interface IConvocatoriasService {

  /**
   * Busqueda de la convocatoriaDTO por su id
   *
   * @param idExp
   *          id del expediente
   * @return Convocatoria convocatoria
   * @throws TramitacionException
   * @throws ParseException
   */
  ConvocatoriaDTO buscarConvocatoriaDTOPorIdExpediente(String idExp) throws TramitacionException;

  /**
   * Busqueda de la convocatoria por su id
   *
   * @param idExp
   *          id del expediente
   * @return Convocatoria convocatoria
   * @throws TramitacionException
   * @throws ParseException
   */
  Convocatoria buscarConvocatoriaPorIdExpediente(String idExp) throws TramitacionException;

  /**
   * Recupera el id de la tabla AACI_T_TIPO_CONV según su denominación a partir de una abreviatura pasada como parámetro.
   *
   * @param abreviatura
   * @return id
   * @throws TramitacionException
   */
  TipoConvocatoriaDTO buscarIdTipConvPorAbreviatura(String abreviatura) throws TramitacionException;

  /**
   * Guarda o modifica una convocatoria en AACI_T_CONVOCATORIAS
   *
   * @param ConvocatoriaDTO
   *          convocatoriaDTO
   * @return {@link Convocatoria} convocatoriaDao
   * @throws TramitacionException
   * @throws ParseException
   */
  boolean crearOmodificarConvocatoria(Convocatoria convocatoria, ConvocatoriaDTO convocatoriaDto) throws TramitacionException, ParseException;

  /**
   * obtenemos las convocatorias que coincidan con el filtro utilizado
   *
   * @param filtro
   * @return
   * @throws TramitacionException
   */
  List<Convocatoria> obtenerConvocatoriasByFiltro(ConvocatoriaDTO filtro) throws TramitacionException;

}

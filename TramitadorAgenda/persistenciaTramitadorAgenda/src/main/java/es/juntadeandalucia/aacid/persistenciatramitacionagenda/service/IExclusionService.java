package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;
import java.util.Map;

import es.juntadeandalucia.aacid.comuntramitacion.dto.DatosExclusionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ExclusionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.plataforma.comunes.excepciones.ArchitectureException;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;
import trewa.bd.trapi.trapiui.tpo.TrFaseExpediente;

/**
 * ISubsanacionService interface.
 *
 * @author isotrol.
 *
 */
public interface IExclusionService {

  /**
   * Obtener Exclusiones pertenecientes al grupo pasado por parámetro.
   *
   * @param ongd
   * @param codTipo
   * @param orden
   * @param anio
   * @return List<ExclusionDTO>
   * @throws TramitacionException
   */
  List<ExclusionDTO> obtenerExclusionesDTOByOrdenTipo(String ongd, final Long codTipo, Long orden, Long anio, List<Perfil> perfil) throws TramitacionException;

  /**
   * Obtener Exclusiones pertenecientes por los ids pasados por parámetro
   *
   * @param idsExclusiones
   * @param perfiles
   *
   * @return List<ExclusionDTO>
   * @throws TramitacionException
   */
  List<ExclusionDTO> obtenerExclusionesDTOByIds(List<Long> idsExclusiones, List<Perfil> perfiles) throws TramitacionException;

  /**
   * Obtener las exclusiones seleccionadas para el expediente
   *
   * @param idSoli
   * @param numTipo
   * @param orden
   * @return
   * @throws TramitacionException
   */
  List<String> obtenerIdsExclusionesSolicitudByOrdenTipo(Long idSoli, Long numTipo, Long orden, List<Perfil> listaPerfiles) throws TramitacionException;

  List<Integer> obtenerIdsExclusionesSolicitud(final Long idSoli, final List<Perfil> listaPerfiles) throws TramitacionException;

  /**
   * Guardar exclusiones solicitud
   *
   * @param datosExclusionDTO
   * @throws TramitacionException
   */
  void guardarExclusionesSolicitud(DatosExclusionDTO datosExclusionDTO) throws TramitacionException;

  /**
   * Comprueba si la solicitud tiene exlusiones pendientes
   *
   * @param idSolicitud
   * @return
   * @throws TramitacionException
   */
  boolean existsExlusionesBySolicitud(Long idSolicitud) throws TramitacionException;

  /**
   * Obtiene los datos para generar el documento de exclusión provisional
   *
   * @param usuario
   * @param mapaOrdenes
   * @param isUniv
   * @param isConv
   * @return
   * @throws TramitacionException
   */
  Map<String, Object> obtenerDatosDocumentoExclusiones(UsuarioWeb usuario, boolean isUniv, boolean isConv, List<Perfil> listaPerfiles, boolean isDeses,
      String fase) throws TramitacionException;

  /**
   * Obtiene los datos para generar el documento de exclusión provisional
   *
   * @param usuario
   * @param mapaOrdenes
   * @param isUniv
   * @param isConv
   * @return
   * @throws TramitacionException
   */
  Map<String, Object> obtenerDatosDocumentoExclusionesDesestimadas(final UsuarioWeb usuario, final boolean isUniv, final boolean isConv,
      final List<Perfil> listaPerfiles, final long tipoExclusion, final String fase) throws TramitacionException;

  /**
   *
   * Obtiene los datos para generar el documento de resolucion provisional de solicitudes excluidas.
   *
   * @param usuarioSesion
   * @param isResolProv
   * @param isUniv
   * @param isConv
   * @return
   * @throws TramitacionException
   */
  Map<String, Object> obtenerDatosDocumentoResolucionExcluidas(UsuarioWeb usuarioSesion, boolean isResolProv, boolean isUniv, boolean isConv)
      throws TramitacionException;

  /**
   * Obtiene el número de exclusiones de un expediente a partir del numero del mismo, y del tipo de exclusion dado
   *
   * @param numExpediente
   * @param tipo
   * @param excepciones
   * @return
   * @throws TramitacionException
   */
  boolean existenExclusionesSolicitudByTipo(String numExpediente, Integer tipo, String excepciones) throws TramitacionException;

  /**
   *
   * Obtiene los datos para generar el documento de resolucion provisional de solicitudes excluidas.
   *
   * @param usuarioSesion
   * @param idSoli
   * @param orden
   * @param listaPerfiles
   * @return
   * @throws TramitacionException
   */
  List<Integer> obtenerIdsExclusionesSolicitudByTipo(Long idSoli, List<Perfil> listaPerfiles, Integer tipo) throws TramitacionException;

  Map<String, Object> obtenerDatosDocumentoResolucionDestimadas(UsuarioWeb usuarioSesion, boolean isResolProv, boolean isUniv, boolean isConv)
      throws TramitacionException;

  /**
   * Obtenemos los códigos de las exclusiones que son excepciones separados por comas s
   *
   * @param tipoExclusiones
   * @param faseFinExclusiones
   * @param faseExpediente
   * @return
   * @throws ArchitectureException
   * @throws TramitacionException
   */
  String obtenerExcepcionesExclusiones(String tipoExclusiones, String faseFinExclusiones, TrFaseExpediente faseExpediente) throws TramitacionException;

  /**
   * Obtenemos si el expediente tiene exclusiones del tipo indicado
   *
   * @param idSolicitud
   * @param tipo
   * @return
   * @throws TramitacionException
   */
  boolean noTieneExclusionesTipoExclusion(final Long idSolicitud, final Integer tipo) throws TramitacionException;

}

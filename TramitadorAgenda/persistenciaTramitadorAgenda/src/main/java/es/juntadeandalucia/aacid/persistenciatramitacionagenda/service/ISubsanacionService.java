package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.List;
import java.util.Map;

import es.juntadeandalucia.aacid.comuntramitacion.dto.DatosSubsanacionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SubsanacionSeleccionadaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Subsanacion;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;

/**
 * ISubsanacionService interface.
 * 
 * @author isotrol.
 *
 */
public interface ISubsanacionService {

  void guardarSubsanacion(DatosSubsanacionDTO datosSubsanacion, String idExpediente, List<SubsanacionSeleccionadaDTO> listadoSubsanaciones, String tipoConv,
      Integer anyo) throws TramitacionException;

  DatosSubsanacionDTO getSubsanacionByNumExpediente(Long idExpediente, List<Perfil> listaPerfiles) throws TramitacionException;

  Long existeSubsanacion(Long numExpediente) throws TramitacionException;

  void deleteSubsanacion(Long idSubsanacion) throws TramitacionException;

  Subsanacion obtenerSubsanacionByNumExpediente(Long idExpediente) throws TramitacionException;

  Map<String, Object> obtenerDatosDocumento(UsuarioWeb usuario) throws TramitacionException;

  Map<String, Object> obtenerDatosDocumentoSubsanaciones(UsuarioWeb usuarioSesion, boolean isUniv) throws TramitacionException;

  boolean existeCatalogoSubsanacion(Long idSolicitud) throws TramitacionException;

  Map<String, Object> obtenerDatosDocumentoListadoSubsanacionesDocumentacion(final UsuarioWeb usuario, final boolean isUniv) throws TramitacionException;
}

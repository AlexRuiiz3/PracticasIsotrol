package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import es.juntadeandalucia.aacid.comuntramitacion.dto.DatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Solicitud;

public class SolicitudConverter {

  /**
   * Constructor por defecto
   */
  private SolicitudConverter() {
  }

  /**
   * Conversor de Solicitud
   *
   * @param listadoCatalogoSubsanacion
   *          {@link Solicitud}
   * @return {@link SolicitudDTO} List
   */
  public static SolicitudDTO convertSolicitudToDto(final Solicitud solicitud) {
    final SolicitudDTO solicitudDTO = new SolicitudDTO();
    solicitudDTO.setIdSolicitud(solicitud.getIdSolicitud());
    solicitudDTO.setTitulo(solicitud.getTxDatostitulo());
    solicitudDTO.setEntidad(solicitud.getTxSoli1Nombre());

    return solicitudDTO;
  }

  public static Solicitud convertDatosGeneralesToEntity(final DatosGeneralesDTO datos) {

    final Solicitud sol = new Solicitud();
    sol.setIdSolicitud(datos.getIdSolicitud());
    sol.setNuNumInterno(UtilidadesNumero.parseStringToLong(datos.getNumExpInterno()));
    sol.setTxCodidentificativo(datos.getNumOV());
    sol.setFhPresentacion(datos.getFechaEntrada());
    sol.setFhRegistro(datos.getFechaRegistro());
    sol.setLgRepresentacion(datos.isOstentaRepresentacion());
    sol.setTxLugarRegistro(datos.getLugarRegistro());
    sol.setFhPagoSubvencion(datos.getFechaPagoSubvencion());
    sol.setVbTecnico(datos.isVbTecnico());
    sol.setVbCoordinador(datos.isVbCoord());
    sol.setFhInicio(datos.getFechaInicio());
    sol.setFkPais(datos.getPais());
    sol.setMunicipio(datos.getLocalidadEjec());
    sol.setTxRespApellidos(datos.getApellidosSol());
    sol.setTxRespNombre(datos.getNombreSol());
    sol.setTxRespNif(datos.getNifSol());
    sol.setFhPostegracionComunIni(datos.getFechaPostegracionComunInicio());

    return sol;
  }
}
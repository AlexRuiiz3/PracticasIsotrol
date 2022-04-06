package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudConcesionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;

public class SolicitudConcesionConverter {

  /**
   * Constructor por defecto
   */
  private SolicitudConcesionConverter() {
  }

  public static SolicitudConcesionDTO convertirObjectToSolicitudConcesionDTO(final Object objSolConce, final List<TrExpediente> listaExpedientesTrewa) {
    final SolicitudConcesionDTO solicitudConcesionDTO = new SolicitudConcesionDTO();
    final Object[] arraysolicitudConcesionDTO = (Object[]) objSolConce;
    // IdTrewa
    final String idTrewa = Objects.toString(arraysolicitudConcesionDTO[0], "");
    solicitudConcesionDTO.setIdTrewa(UtilidadesNumero.parseStringToLong(idTrewa));
    // Nímero Expediente
    if (CollectionUtils.isNotEmpty(listaExpedientesTrewa)) {
      solicitudConcesionDTO
          .setNumExpTrewa(listaExpedientesTrewa.stream().filter(e -> StringUtils.equals(e.getREFEXP().toString(), idTrewa)).findFirst().get().getNUMEXP());
    }
    // Id Solicitud Concesion
    solicitudConcesionDTO.setIdSolConce(UtilidadesNumero.parseStringToLong(Objects.toString(arraysolicitudConcesionDTO[1], "")));
    // Id Solicitud
    solicitudConcesionDTO.setIdSolicitud(UtilidadesNumero.parseStringToLong(Objects.toString(arraysolicitudConcesionDTO[2], "")));
    // Código Identificativo
    solicitudConcesionDTO.setCodIdentificativo(Objects.toString(arraysolicitudConcesionDTO[3], ""));
    // Presupuesto Validado
    solicitudConcesionDTO.setPresupuestoValidado(UtilidadesNumero
        .bigDecimalToStringConverter(UtilidadesNumero.convertStringToBigDecimalIfNotBlank(Objects.toString(arraysolicitudConcesionDTO[4], null))));
    // Subvención Concedida
    solicitudConcesionDTO.setSubvencionConceder(UtilidadesNumero
        .bigDecimalToStringConverter(UtilidadesNumero.convertStringToBigDecimalIfNotBlank(Objects.toString(arraysolicitudConcesionDTO[5], null))));
    // Subvención Solicitada
    solicitudConcesionDTO.setSubvencionSolicitada(UtilidadesNumero
        .bigDecimalToStringConverter(UtilidadesNumero.convertStringToBigDecimalIfNotBlank(Objects.toString(arraysolicitudConcesionDTO[6], null))));
    // Entidad
    solicitudConcesionDTO.setEntidad(Objects.toString(arraysolicitudConcesionDTO[7], ""));
    // Puntuación
    solicitudConcesionDTO.setPuntuacion(UtilidadesNumero
        .bigDecimalToStringConverterPuntuacion(UtilidadesNumero.convertStringToBigDecimalIfNotBlank(Objects.toString(arraysolicitudConcesionDTO[8], "0"))));
    // Beneficiaria
    final String beneficiaria = Objects.toString(arraysolicitudConcesionDTO[9], "");
    if (StringUtils.equalsIgnoreCase(ConstantesTramitacion.BENEFICIARIA, beneficiaria)) {
      solicitudConcesionDTO.setBeneficiaria(true);
    } else if (StringUtils.equalsIgnoreCase(ConstantesTramitacion.SUPLENTE, beneficiaria)) {
      solicitudConcesionDTO.setBeneficiaria(false);
    }
    return solicitudConcesionDTO;
  }

  public static List<SolicitudConcesionDTO> convertirObjectToSolicitudConcesionDTO(final List<Object> obtenerListaConcesiones,
      final List<TrExpediente> listaExpedientesTrewa) {
    final List<SolicitudConcesionDTO> listSolConceDTO = new ArrayList<>();

    for (final Object objSolConce : obtenerListaConcesiones) {
      listSolConceDTO.add(convertirObjectToSolicitudConcesionDTO(objSolConce, listaExpedientesTrewa));
    }

    return listSolConceDTO;
  }
}
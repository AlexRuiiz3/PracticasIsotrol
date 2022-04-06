package es.juntadeandalucia.aacid.comuntramitacion.utils;

import java.util.List;

import org.apache.log4j.Logger;

import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudInformesDTO;

public class UtilidadesListas {
  private static final Logger log = Logger.getLogger(UtilidadesListas.class);

  
  public static void ordenamientoBurbujaListaSolicitudesByPuntuacion(final List<SolicitudDTO> solicitudes) {
    SolicitudDTO solicitudDTOAux;
    try {
      for (int i = 0; i < solicitudes.size() - 1; i++) {
        for (int j = 0; j < solicitudes.size() - i - 1; j++) {
          if (solicitudes.get(j + 1).getPuntuacionNum().doubleValue() > solicitudes.get(j).getPuntuacionNum().doubleValue()) {
            solicitudDTOAux = solicitudes.get(j + 1);
            solicitudes.set(j + 1, solicitudes.get(j));
            solicitudes.set(j, solicitudDTOAux);
          }
        }
      }
    } catch (final NumberFormatException ex) {
      log.error("Error al convertir numero en cadena o viceversa. Causa: " + ex.getMessage(), ex);
    }
  }
  
  public static void ordenamientoBurbujaListaSolicitudesByNumExpediente(final List<SolicitudDTO> solicitudes) {
    SolicitudDTO solicitudDTOAux;
    try {
      for (int i = 0; i < solicitudes.size() - 1; i++) {
        for (int j = 0; j < solicitudes.size() - i - 1; j++) {
          if (solicitudes.get(j + 1).getCodIdentificativo().compareTo(solicitudes.get(j).getCodIdentificativo())<0) {
            solicitudDTOAux = solicitudes.get(j + 1);
            solicitudes.set(j + 1, solicitudes.get(j));
            solicitudes.set(j, solicitudDTOAux);
          }
        }
      }
    } catch (final NumberFormatException ex) {
      log.error("Error al convertir numero en cadena o viceversa. Causa: " + ex.getMessage(), ex);
    }
  }
  
  public static void ordenamientoBurbujaListaSolicitudesInformeByNumExpediente(final List<SolicitudInformesDTO> solicitudes) {
    SolicitudInformesDTO solicitudDTOAux;
    try {
      for (int i = 0; i < solicitudes.size() - 1; i++) {
        for (int j = 0; j < solicitudes.size() - i - 1; j++) {
          if (solicitudes.get(j + 1).getNumExpTrewa().compareTo(solicitudes.get(j).getNumExpTrewa())<0) {
            solicitudDTOAux = solicitudes.get(j + 1);
            solicitudes.set(j + 1, solicitudes.get(j));
            solicitudes.set(j, solicitudDTOAux);
          }
        }
      }
    } catch (final NumberFormatException ex) {
      log.error("Error al convertir numero en cadena o viceversa. Causa: " + ex.getMessage(), ex);
    }
  }
}

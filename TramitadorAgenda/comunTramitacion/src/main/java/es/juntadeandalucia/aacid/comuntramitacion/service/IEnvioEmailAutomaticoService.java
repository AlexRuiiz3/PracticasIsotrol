package es.juntadeandalucia.aacid.comuntramitacion.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.mail.MessagingException;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.plataforma.comunes.excepciones.ArchitectureException;

public interface IEnvioEmailAutomaticoService {

  void enviarEmail(String tarea, String emailDestino, String idExpediente, String fase, Integer diasRestantesFinComunicacionIni)
      throws ArchitectureException, MessagingException, TramitacionException, UnsupportedEncodingException;

  Map<String, String> obtenerDatosConfiguracionEnvioEmail() throws ArchitectureException;
}

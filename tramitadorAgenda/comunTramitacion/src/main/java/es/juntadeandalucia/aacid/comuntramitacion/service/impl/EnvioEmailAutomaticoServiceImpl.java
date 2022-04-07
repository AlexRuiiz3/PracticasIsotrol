package es.juntadeandalucia.aacid.comuntramitacion.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.IEnvioEmailAutomaticoService;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.plataforma.comunes.excepciones.ArchitectureException;
import es.juntadeandalucia.plataforma.service.modulos.IConfig;
import es.juntadeandalucia.plataforma.service.modulos.IConfigService;
import trewa.bd.trapi.trapiui.tpo.TrDocumentoExpediente;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;

public class EnvioEmailAutomaticoServiceImpl implements IEnvioEmailAutomaticoService {

  private static final String LINE_SEPARATOR = "line.separator";

  protected final Log log = LogFactory.getLog(getClass());

  private IConfigService configService;
  private ITrewaService trewaService;

  @Override
  public Map<String, String> obtenerDatosConfiguracionEnvioEmail() throws ArchitectureException {

    final Map<String, String> datosConfiguracion = new HashMap<>();
    log.info("Se van a obtener todos los parametros de configuracion");
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_FROM, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_FROMNAME, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_SMTP_USERNAME, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_SMTP_PASSWORD, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_HOST, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_PORT, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_SUBJECT_EXCL, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_SUBJECT_SUBS, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_SUBJECT_PRESUPUESTO_VALIDADO, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_SUBJECT_RESOLUCION_DESESTIMADA, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_BODY_EXCL, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_BODY_SUBS, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_BODY_PRESUPUESTO_VALIDADO, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_BODY_RESOLUCION_DESESTIMADA, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_SMTP_AUTH, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_TRANSPORT_PROTOCOL, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_START_TLS_ENABLE, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_ENABLE_SSL, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_SUBJECT_BENEFICIARIOS_SUPLENTES, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_BODY_BENEFICIARIOS_SUPLENTES, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_BODY_CONCESION_DEFINITIVA, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_SUBJECT_CONCESION_DEFINITIVA, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_BODY_EXCL_DEF, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_SUBJECT_EXCL_DEF, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_BODY_DOC_VAL, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_SUBJECT_DOC_VAL, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_BODY_AVISO_FIN_COMUNICACION_INICIO, datosConfiguracion);
    anyadeValoresMapaEnvioEmail(ConstantesTramitacion.EMAIL_SUBJECT_AVISO_FIN_COMUNICACION_INICIO, datosConfiguracion);

    return datosConfiguracion;
  }

  private void anyadeValoresMapaEnvioEmail(final String propiedad, final Map<String, String> propiedades) {
    final List<IConfig> configuraciones = configService.obtenerParametrosConfiguracion(propiedad, null, null, true);
    if (CollectionUtils.isNotEmpty(configuraciones)) {
      final IConfig propiedadConfig = configuraciones.get(0);
      if (propiedadConfig != null) {
        propiedades.put(propiedad, propiedadConfig.getValor());
      }
    }

  }

  @Override
  public void enviarEmail(final String tarea, final String emailDestino, final String idExpediente, final String etiquetaTipoDocumento,
      final Integer diasRestantesFinComunicacionIni) throws ArchitectureException, MessagingException, TramitacionException, UnsupportedEncodingException {

    final Map<String, String> mapaParametrosEnvio = obtenerDatosConfiguracionEnvioEmail();

    final Properties props = System.getProperties();
    props.put("mail.smtp.host", mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_HOST));
    props.put("mail.transport.protocol", mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_TRANSPORT_PROTOCOL));
    props.put("mail.smtp.port", mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_PORT));
    props.put("mail.smtp.starttls.enable", mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_START_TLS_ENABLE));
    props.put("mail.smtp.auth", mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_SMTP_AUTH));
    props.put("mail.smtp.EnableSSL.enable", mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_ENABLE_SSL));

    log.info("Se va a llevar a cabo la autenticacion");
    final Session session = Session.getInstance(props, new javax.mail.Authenticator() {

      @Override
      protected PasswordAuthentication getPasswordAuthentication() {

        return new PasswordAuthentication(mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_SMTP_USERNAME),
            mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_SMTP_PASSWORD));
      }
    });
    session.setDebug(true);

    final MimeMessage msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress(mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_FROM), mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_FROMNAME)));

    log.info("Se va a obtener el email de la entidad.");
    final String emailTo = emailDestino;

    if (emailTo == null) {
      throw new AddressException("La entidad no tiene asignada direccion email para realizar el envio");
    }

    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));

    log.info("Se procede a adjuntar el documento de subsanaciones en el envio de email para el expediente " + idExpediente);
    String body = null;
    String subject = null;

    switch (tarea) {
    case ConstantesTramitacion.EXCLUSION:
      if (etiquetaTipoDocumento.equalsIgnoreCase(ConstantesTramitacion.ETIQUETA_DOC_EXCL_PROV)) {
        body = String.join(System.getProperty(LINE_SEPARATOR), mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_BODY_EXCL));
        subject = mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_SUBJECT_EXCL);
      } else if (etiquetaTipoDocumento.equalsIgnoreCase(ConstantesTramitacion.ETIQUETA_DOC_DES)
          || etiquetaTipoDocumento.equalsIgnoreCase(ConstantesTramitacion.ETIQUETA_DOC_DES_UNIV)) {
        body = String.join(System.getProperty(LINE_SEPARATOR), mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_BODY_RESOLUCION_DESESTIMADA));
        subject = mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_SUBJECT_RESOLUCION_DESESTIMADA);
      } else if (etiquetaTipoDocumento.equalsIgnoreCase(ConstantesTramitacion.ETIQUETA_DOC_EXCL_DEF_UNIV)
          || etiquetaTipoDocumento.equalsIgnoreCase(ConstantesTramitacion.ETIQUETA_DOC_EXCL_DEF_ONGD)) {
        body = String.join(System.getProperty(LINE_SEPARATOR), mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_BODY_EXCL_DEF));
        subject = mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_SUBJECT_EXCL_DEF);
      }
      break;
    case ConstantesTramitacion.SUBSANACION:
      body = String.join(System.getProperty(LINE_SEPARATOR), mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_BODY_SUBS));
      subject = mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_SUBJECT_SUBS);
      break;
    case ConstantesTramitacion.VALIDACION_PRESUPUESTO:
      if (etiquetaTipoDocumento.equalsIgnoreCase(ConstantesTramitacion.ETIQUETA_DOC_PRE_VAL)) {
        body = String.join(System.getProperty(LINE_SEPARATOR), mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_BODY_PRESUPUESTO_VALIDADO));
        subject = mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_SUBJECT_PRESUPUESTO_VALIDADO);
      } else if (etiquetaTipoDocumento.equalsIgnoreCase(ConstantesTramitacion.ETIQUETA_DOC_BEN_SUPL)) {
        body = String.join(System.getProperty(LINE_SEPARATOR), mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_BODY_BENEFICIARIOS_SUPLENTES));
        subject = mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_SUBJECT_BENEFICIARIOS_SUPLENTES);
      } else if (etiquetaTipoDocumento.equalsIgnoreCase(ConstantesTramitacion.ETIQUETA_INF_VALORACION_UNIV)
          || etiquetaTipoDocumento.equalsIgnoreCase(ConstantesTramitacion.ETIQUETA_INF_VALORACION_ONGD)) {
        body = String.join(System.getProperty(LINE_SEPARATOR), mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_BODY_DOC_VAL));
        subject = mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_SUBJECT_DOC_VAL);
      }
      break;
    case ConstantesTramitacion.CAUSA_CONCESION_DEFINITIVA:
      body = String.join(System.getProperty(LINE_SEPARATOR), mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_BODY_CONCESION_DEFINITIVA));
      subject = mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_SUBJECT_CONCESION_DEFINITIVA);
      break;
    case ConstantesTramitacion.AVISO_FIN_COMUNICACION_INICIO:
      final TrExpediente trExpediente = trewaService.obtenerExpedienteTrewa(idExpediente);
      body = String.join(System.getProperty(LINE_SEPARATOR), MessageFormat.format(
          mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_BODY_AVISO_FIN_COMUNICACION_INICIO), trExpediente.getNUMEXP(), diasRestantesFinComunicacionIni));
      subject = MessageFormat.format(mapaParametrosEnvio.get(ConstantesTramitacion.EMAIL_SUBJECT_AVISO_FIN_COMUNICACION_INICIO), trExpediente.getNUMEXP());
      break;
    default:
      throw new TramitacionException("El tipo de tarea que se ha pasado por parametro no existe.");
    }

    Multipart multipart = new MimeMultipart();
    if (StringUtils.isNotBlank(etiquetaTipoDocumento)) {
      multipart = adjuntarDocumentoEmail(idExpediente, msg, etiquetaTipoDocumento);
    }
    final MimeBodyPart textPart = new MimeBodyPart();
    textPart.setText(body);
    multipart.addBodyPart(textPart);
    msg.setContent(multipart);
    msg.setSubject(subject);
    log.info("Email preparado, se procede a realizar el envio");
    Transport.send(msg, msg.getAllRecipients());
  }

  private Multipart adjuntarDocumentoEmail(final String iExp, final MimeMessage msg, final String etiquetaTipoDocumento)
      throws MessagingException, TramitacionException {
    try {
      final TrDocumentoExpediente[] documentosExpediente = trewaService.documentosDelExpediente(Long.parseLong(iExp), etiquetaTipoDocumento);
      if (documentosExpediente == null || documentosExpediente.length == 0) {
        throw new TramitacionException("Se ha producido un error al adjuntar el documento en el correo automatico.");

      }
      final TrDocumentoExpediente docExp = documentosExpediente[0];
      final InputStream ficheroExpediente = trewaService.recuperarDocumentoExpediente(docExp.getREFDOCEXP());
      final byte[] binarioDocumento = IOUtils.toByteArray(ficheroExpediente);
      docExp.setNOMBREFICHERO(docExp.getNOMBREFICHERO().replace("/", "-"));
      final Multipart multipart = new MimeMultipart();
      if (docExp.getNOMBREFICHERO() != null && binarioDocumento != null) {
        log.info("Se va a crear el fichero temporal que posteriormente adjuntaremos al correo, expediente: " + iExp);
        final File docEmail = File.createTempFile("adjunto-", docExp.getNOMBREFICHERO());
        FileUtils.writeByteArrayToFile(docEmail, binarioDocumento);
        final MimeBodyPart attachmentPart = new MimeBodyPart();
        attachmentPart.attachFile(docEmail);
        multipart.addBodyPart(attachmentPart);
        log.info("Fichero adjuntado con exito para el expediente: " + iExp);
      }
      return multipart;
    } catch (final NumberFormatException e) {
      log.error("El valor " + iExp + " no es un numerico. ERROR: " + e.getMessage(), e);
      throw new TramitacionException("El valor " + iExp + " no es un numerico. ERROR: " + e.getMessage(), e);
    } catch (final IOException e) {
      log.error(e.getMessage(), e);
      throw new TramitacionException("Se ha producido un error al adjuntar el documento de subsanaciones en el correo automatico", e);
    }
  }

  public void setConfigService(final IConfigService configService) {
    this.configService = configService;
  }

  public void setTrewaService(final ITrewaService trewaService) {
    this.trewaService = trewaService;
  }
}

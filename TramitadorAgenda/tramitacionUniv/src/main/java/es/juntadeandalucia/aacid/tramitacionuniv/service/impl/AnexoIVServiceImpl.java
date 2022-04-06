package es.juntadeandalucia.aacid.tramitacionuniv.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesProcesamientosEspecificos;
import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.vea.CampoVeaType;
import es.juntadeandalucia.aacid.comuntramitacion.dto.vea.EntregaVeaType;
import es.juntadeandalucia.aacid.comuntramitacion.exception.AlegacionesAceptaReformulaException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.ParseCamposVeaException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ISolicitudDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.DocumentoPoderAdminJA;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.DocumentoPoderOtrasAdmin;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Solicitud;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.utils.ProcesamientoEspecificoUtils;
import es.juntadeandalucia.aacid.tramitacionuniv.pojo.anexoiv.AnexoIV;
import es.juntadeandalucia.aacid.tramitacionuniv.pojo.anexoiv.DocumentoAdministracionJA;
import es.juntadeandalucia.aacid.tramitacionuniv.pojo.anexoiv.DocumentoOtraAdministracion;
import es.juntadeandalucia.aacid.tramitacionuniv.service.IAnexoIVService;

/**
 * AnexoIVService class.
 *
 * @author isotrol.
 *
 */
public class AnexoIVServiceImpl implements IAnexoIVService {

  /** interfaz de solicitudes */
  private ISolicitudDao solicitudDao;

  private static final Log LOG = LogFactory.getLog(AnexoIVServiceImpl.class);

  @Override
  @Transactional
  public void procesamientoEspecifico(final BigDecimal idExp, final Long idSolicitud, final EntregaVeaType ultimoAnexoIV) throws ClassNotFoundException,
      ParseException, IllegalAccessException, NoSuchFieldException, ParseCamposVeaException, AlegacionesAceptaReformulaException {

    AnexoIV anexo = null;

    if (ultimoAnexoIV != null) {

      anexo = new AnexoIV();
      anexo.setIdSolicitud(idSolicitud);

      final Class<?> clazz = Class.forName("es.juntadeandalucia.aacid.tramitacionuniv.pojo.anexoiv.AnexoIV");

      // Marcamos que el valor es nulo si la cadena es "-", "null", "" o " "
      final List<CampoVeaType> camposVea = ultimoAnexoIV.getFormularios().getFormulario().get(0).getCampo().stream().map(campoVea -> {

        final String valor = campoVea.getValor();

        if ("-".equals(valor) || "null".equals(valor) || StringUtils.isBlank(valor)) {
          campoVea.setValor(null);
        }

        return campoVea;

      }).collect(Collectors.toList());

      final List<CampoVeaType> camposVeaProcesados = procesarCampos(camposVea, anexo);

      // Borra todos aquellos campos que han sidos procesados.
      camposVea.removeAll(camposVeaProcesados);

      ProcesamientoEspecificoUtils.setearCamposEntregaVeA(anexo, clazz, camposVea);

      final Solicitud solicitud = new Solicitud();
      solicitud.setIdSolicitud(anexo.getIdSolicitud());

      // ----------------------------------------------------------------------------------------------------
      // 1 DATOS DE LA ENTIDAD SOLICITANTE Y DE LA PERSONA REPRESENTANTE
      // ----------------------------------------------------------------------------------------------------

      // SOLICITANTE
      solicitud.setTxSoli1Nombre(anexo.getSoli1Nombre());
      solicitud.setTxSoli1Numident(anexo.getSoli1Numident());
      solicitud.setTxSoli1Siglas(anexo.getSoli1Siglas());
      solicitud.setTxSoli1Tipoident(anexo.getSoli1Tipoident());
      solicitud.setTxSoli1Apellido1(anexo.getSoli1Apellido1());
      solicitud.setTxSoli1Apellido2(anexo.getSoli1Apellido2());
      solicitud.setTxSoli1Sexo(anexo.getSoli1Sexo());

      // REPRESENTANTE
      solicitud.setTxRepr1Apellido1(anexo.getRepr1Apellido1());
      solicitud.setTxRepr1Apellido2(anexo.getRepr1Apellido2());
      solicitud.setTxRepr1Nombre(anexo.getRepr1Nombre());
      solicitud.setTxRepr1Sexo(anexo.getRepr1Sexo());
      solicitud.setTxRepr1Numident(anexo.getRepr1Numident());
      solicitud.setTxRepr1Titulo(anexo.getRepr1Titulo());
      solicitud.setTxRepr1Tipoident(anexo.getRepr1Tipoident());

      // ----------------------------------------------------------------------------------------------------
      // 2 NOTIFICACIÓN ELECTRÓNICA OBLIGATORIA
      // ----------------------------------------------------------------------------------------------------
      solicitud.setTxNotifemailautlugar(anexo.getNotificaEmail());
      solicitud.setTxNotiftelefonoautlugar(anexo.getNotificaTlfmovil());

      // ----------------------------------------------------------------------------------------------------
      // 3 DATOS BANCARIOS
      // ----------------------------------------------------------------------------------------------------
      solicitud.setTxBancoIban(anexo.getBancoIban());
      solicitud.setTxBancoCodigo(anexo.getBancoCodigo());
      solicitud.setTxBancoPais(anexo.getBancoPais());
      solicitud.setTxBancoLocalidad(anexo.getBancoLocalidad());
      solicitud.setTxBancoSucursal(anexo.getBancoSucursal());
      solicitud.setTxBancoNumCuenta(anexo.getBancoNumcuenta());

      solicitud.setTxBancoSwiftCodBanco(anexo.getBancoSCodbanco());
      solicitud.setTxBancoSwiftPais(anexo.getBancoSPais());
      solicitud.setTxBancoSwiftLocalidad(anexo.getSwiftLocalidad());
      solicitud.setTxBancoSwiftSucur(anexo.getBancoSSucur());

      solicitud.setTxBancoEntidad(anexo.getBancoEntidad());
      solicitud.setTxBancoDomicilio(anexo.getBancoDomicilio());
      solicitud.setTxBancoCodMunicipio(anexo.getBancoCodmunicipio());
      solicitud.setTxBancoCodProv(anexo.getBancoCodprov());
      solicitud.setTxBancoCP(anexo.getBancoCp());

      // ----------------------------------------------------------------------------------------------------
      // 4 IDENTIFICACIÓN DEL PROYECTO PARA EL QUE SE SOLICITA LA SUBVENCIÓN
      // ----------------------------------------------------------------------------------------------------
      solicitud.setTxDatostitulo(anexo.getDatosTitulo());

      // ----------------------------------------------------------------------------------------------------
      // 5 ALEGACIONES/ACEPTACIÓN/REFORMULACIÓN
      // ----------------------------------------------------------------------------------------------------
      solicitud.setLgConceUno(anexo.getConceUno());
      solicitud.setLgConceDos(anexo.getConceDos());
      solicitud.setLgConceTres(anexo.getConceTres());
      solicitud.setLgConceCuatro(anexo.getConceCuatro());
      solicitud.setLgConceCinco(anexo.getConceCinco());
      solicitud.setLgConceSeis(anexo.getConceSeis());
      solicitud.setTxRefor(anexo.getRefor());
      solicitud.setLgConceSiete(anexo.getConceSiete());
      solicitud.setTxNoAcepto(anexo.getNoAcepto());
      solicitud.setLgConceOcho(anexo.getConceOcho());
      solicitud.setTxOtroA(anexo.getOtroA());

      // ----------------------------------------------------------------------------------------------------
      // 6 DOCUMENTACIÓN
      // ----------------------------------------------------------------------------------------------------

      // PRESENTADOS
      solicitud.setLgDoc1(anexo.getDoc1());
      solicitud.setLgDoc2(anexo.getDoc2());
      solicitud.setLgDoc3(anexo.getDoc3());

      // DOCUMENTOS EN PODER DE LA ADMINISTRACIÓN DE LA JUNTA DE ANDALUCÍA
      final List<DocumentoPoderAdminJA> documentosJA = anexo.getDocumentosJA().stream().map(d -> {
        final DocumentoPoderAdminJA documento = new DocumentoPoderAdminJA();
        documento.setNombre(d.getNombre());
        documento.setConsejeriaOrgano(d.getConsejeria());
        documento.setFecha(d.getFecha());
        documento.setProcedimiento(d.getProcedimiento());
        documento.setSolicitud(solicitud);
        return documento;
      }).collect(Collectors.toList());
      solicitud.getDocumentosJA().addAll(documentosJA);

      // DOCUMENTOS EN PODER DE OTRAS ADMINISTRACIONES
      final List<DocumentoPoderOtrasAdmin> documentosOtrasAdmin = anexo.getDocumentosOtrasAdministraciones().stream().map(d -> {
        final DocumentoPoderOtrasAdmin documento = new DocumentoPoderOtrasAdmin();
        documento.setNombre(d.getNombre());
        documento.setAdministracion(d.getAdministracion());
        documento.setFecha(d.getFecha());
        documento.setProcedimiento(d.getProcedimiento());
        documento.setSolicitud(solicitud);
        return documento;
      }).collect(Collectors.toList());
      solicitud.getDocumentosOtrasAdmin().addAll(documentosOtrasAdmin);

      // ----------------------------------------------------------------------------------------------------
      // 7 DECLARACIÓN, LUGAR, FECHA Y FIRMA
      // ----------------------------------------------------------------------------------------------------
      solicitud.setTxFirm1Fdo(anexo.getFirm1Fdo());
      solicitud.setTxFirm1Lugar(anexo.getFirm1Lugar());

      // ----------------------------------------------------------------------------------------------------
      // DOMICILIO A EFECTOS DE NOTIFICACIÓN
      // ----------------------------------------------------------------------------------------------------
      solicitud.setTxSoli1Tipovia(anexo.getSoli1Tipovia());
      solicitud.setTxSoli1Nombrevia(anexo.getSoli1Nombrevia());
      solicitud.setTxSoli1Numero(anexo.getSoli1Numero());
      solicitud.setTxSol1Letra(anexo.getSoli1Letra());
      solicitud.setTxSoli1Kmvia(anexo.getSoli1Kmvia());
      solicitud.setTxSoli1Bloque(anexo.getSoli1Bloque());
      solicitud.setTxSoli1Portal(anexo.getSoli1Bloque());
      solicitud.setTxSoli1Escalera(anexo.getSoli1Escalera());
      solicitud.setTxSoli1Piso(anexo.getSoli1Piso());
      solicitud.setTxSoli1Puerta(anexo.getSoli1Puerta());
      solicitud.setNuSoli1Codprov(anexo.getSoli1Codprov() != null ? anexo.getSoli1Codprov() : null);
      solicitud.setTxSoli1Codmunicipio(anexo.getSoli1Codmunicipio());
      solicitud.setTxSoli1Poblacion(anexo.getSoli1Poblacion());
      solicitud.setNuSoli1Cp(anexo.getSoli1Cp());
      solicitud.setNuSoli1Telefono(anexo.getSoli1Telefono());
      solicitud.setNuSoli1Tlfmovil(anexo.getSoli1Tlfmovil());
      solicitud.setTxSoli1Email(anexo.getSoli1Email());

      solicitud.setTxRepr1Tipovia(anexo.getRepr1Tipovia());
      solicitud.setTxRepr1Nombrevia(anexo.getRepr1Nombrevia());
      solicitud.setNuRepr1Codprov(anexo.getRepr1Codprov());
      solicitud.setNuRepr1Codmunicipio(anexo.getRepr1Codmunicipio());

      solicitudDao.guardarAlegacionesAceptaReformulaUNIV(solicitud);

    } else {
      LOG.error("No se han encontrado el FORMULARIO DE ALEGACIONES/ACEPTACIÓN/REFORMULACIÓN Y PRESENTACIÓN DE DOCUMENTOS para el expediente con id " + idExp);
    }

  }

  private List<CampoVeaType> procesarCampos(final List<CampoVeaType> camposVea, final AnexoIV solicitud) throws ParseException {

    final List<CampoVeaType> camposVeaProcesados = new ArrayList<>();
    final List<CampoVeaType> autorizaAdminJA = new ArrayList<>();
    final List<CampoVeaType> autorizaOtraAdmin = new ArrayList<>();

    for (final CampoVeaType campoVea : camposVea) {
      if (campoVea.getNombre().startsWith(ConstantesProcesamientosEspecificos.PREFIJO_OTRA_ADMIN)) {
        autorizaOtraAdmin.add(campoVea);
      } else if (campoVea.getNombre().startsWith(ConstantesProcesamientosEspecificos.PREFIJO_ADMIN_JA)) {
        autorizaAdminJA.add(campoVea);
      } else if (campoVea.getNombre().startsWith("ERROR_")) {
        camposVeaProcesados.add(campoVea);
      }
    }

    if (!autorizaAdminJA.isEmpty()) {
      camposVeaProcesados.addAll(autorizaAdminJA);
      solicitud.getDocumentosJA().addAll(obtenerDocumentosEnPoderJuntaAndalucia(autorizaAdminJA));
    }

    if (!autorizaOtraAdmin.isEmpty()) {
      camposVeaProcesados.addAll(autorizaOtraAdmin);
      solicitud.getDocumentosOtrasAdministraciones().addAll(obtenerDocumentosEnPoderOtrasAdministraciones(autorizaOtraAdmin));
    }

    return camposVeaProcesados;
  }

  private List<DocumentoAdministracionJA> obtenerDocumentosEnPoderJuntaAndalucia(final List<CampoVeaType> autorizaAdminJA) throws ParseException {

    final List<DocumentoAdministracionJA> resultado = new ArrayList<>();

    final Map<String, String> variables = new HashMap<>();
    autorizaAdminJA.stream().forEach(campo -> variables.put(campo.getNombre(), campo.getValor()));

    final int numFilas = ProcesamientoEspecificoUtils.numeroRegistroLista(variables, "AUTORIZA_JUNTA", "ADM_DOCUMENTO");

    for (int i = 0; i < numFilas; i++) {

      final String documento = variables.get(ConstantesProcesamientosEspecificos.PREFIJO_ADMIN_JA + i + "_ADM_DOCUMENTO");
      final String consejeria = variables.get(ConstantesProcesamientosEspecificos.PREFIJO_ADMIN_JA + i + "_ADM_CONSEJERIA");
      final String procedimiento = variables.get(ConstantesProcesamientosEspecificos.PREFIJO_ADMIN_JA + i + "_ADM_PROCEDIMIENTO");
      final String fecha = variables.get(ConstantesProcesamientosEspecificos.PREFIJO_ADMIN_JA + i + "_ADM_FECHA");

      final DocumentoAdministracionJA documentoAdministracionJA = new DocumentoAdministracionJA();
      documentoAdministracionJA.setNombre(documento);
      documentoAdministracionJA.setConsejeria(consejeria);
      documentoAdministracionJA.setProcedimiento(procedimiento);
      documentoAdministracionJA.setFecha(new SimpleDateFormat(ConstantesTramitacion.FORMATO_FECHA_UE).parse(fecha));

      resultado.add(documentoAdministracionJA);
    }

    return resultado;
  }

  private List<DocumentoOtraAdministracion> obtenerDocumentosEnPoderOtrasAdministraciones(final List<CampoVeaType> autorizaOtraAdmin) throws ParseException {

    final List<DocumentoOtraAdministracion> resultado = new ArrayList<>();

    final Map<String, String> variables = new HashMap<>();
    autorizaOtraAdmin.stream().forEach(campo -> variables.put(campo.getNombre(), campo.getValor()));

    final int numFilas = ProcesamientoEspecificoUtils.numeroRegistroLista(variables, "AUTORIZA_GESTOR", "OTRAS_DOCUMENTO");

    for (int i = 0; i < numFilas; i++) {

      // Declaración de variables
      final String documento = variables.get(ConstantesProcesamientosEspecificos.PREFIJO_OTRA_ADMIN + i + "_OTRAS_DOCUMENTO");
      final String administracion = variables.get(ConstantesProcesamientosEspecificos.PREFIJO_OTRA_ADMIN + i + "_OTRAS_ADMINISTRACION");
      final String procedimiento = variables.get(ConstantesProcesamientosEspecificos.PREFIJO_OTRA_ADMIN + i + "_OTRAS_PROCEDIMIENTO");
      final String fecha = variables.get(ConstantesProcesamientosEspecificos.PREFIJO_OTRA_ADMIN + i + "_OTRAS_FECHA");

      final DocumentoOtraAdministracion documentoOtraAdministracion = new DocumentoOtraAdministracion();
      documentoOtraAdministracion.setNombre(documento);
      documentoOtraAdministracion.setAdministracion(administracion);
      documentoOtraAdministracion.setProcedimiento(procedimiento);
      documentoOtraAdministracion.setFecha(new SimpleDateFormat(ConstantesTramitacion.FORMATO_FECHA_UE).parse(fecha));

      resultado.add(documentoOtraAdministracion);
    }

    return resultado;
  }

  // GETTERS AND SETTERS

  /**
   * @return the solicitudDao
   */
  public ISolicitudDao getSolicitudDao() {
    return solicitudDao;
  }

  /**
   * @param solicitudDao
   *          the solicitudDao to set
   */
  public void setSolicitudDao(final ISolicitudDao solicitudDao) {
    this.solicitudDao = solicitudDao;
  }

}

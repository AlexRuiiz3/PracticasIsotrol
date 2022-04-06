package es.juntadeandalucia.aacid.tramitacionongd.services.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesProcesamientosEspecificos;
import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.vea.CampoVeaType;
import es.juntadeandalucia.aacid.comuntramitacion.dto.vea.EntregaVeaType;
import es.juntadeandalucia.aacid.comuntramitacion.exception.ParseCamposVeaException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.RecepcionComunicacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ISolicitudDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Solicitud;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.utils.ProcesamientoEspecificoUtils;
import es.juntadeandalucia.aacid.tramitacionongd.pojo.anexovii.AnexoVII;
import es.juntadeandalucia.aacid.tramitacionongd.services.IAnexoVIIService;

public class AnexoVIIServiceImpl implements IAnexoVIIService {

  /** interfaz de solicitudes */
  private ISolicitudDao solicitudDao;

  private static final Log LOG = LogFactory.getLog(AnexoVIIServiceImpl.class);

  @Override
  @Transactional
  public void procesamientoEspecifico(BigDecimal idExp, Long idSolicitud, EntregaVeaType ultimoAnexoVII)
      throws ClassNotFoundException, ParseException, IllegalAccessException, NoSuchFieldException, ParseCamposVeaException, RecepcionComunicacionException {

    AnexoVII anexo = null;

    if (ultimoAnexoVII != null) {

      anexo = new AnexoVII();
      anexo.setIdSolicitud(idSolicitud);

      final Class<?> clazz = Class.forName("es.juntadeandalucia.aacid.tramitacionongd.pojo.anexovii.AnexoVII");

      // Marcamos que el valor es nulo si la cadena es "-", "null", "" o " "
      final List<CampoVeaType> camposVea = ultimoAnexoVII.getFormularios().getFormulario().get(0).getCampo().stream().map(campoVea -> {

        final String valor = campoVea.getValor();

        if ("-".equals(valor) || "null".equals(valor) || StringUtils.isBlank(valor)) {
          campoVea.setValor(null);
        }

        return campoVea;

      }).collect(Collectors.toList());

      final List<CampoVeaType> camposVeaProcesados = procesarCampos(camposVea);

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
      solicitud.setTxSoli1Codigo(anexo.getSoli1Codigo());
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
      solicitud.setTxCalidadde(anexo.getCalidadDe());
      // ----------------------------------------------------------------------------------------------------
      // 2 IDENTIFICACIÓN DEL PROYECTO PARA EL QUE SE SOLICITA LA SUBVENCIÓN
      // ----------------------------------------------------------------------------------------------------
      solicitud.setFhFechaInicio(new SimpleDateFormat(ConstantesTramitacion.FORMATO_FECHA_UE).parse(anexo.getFechaInicio()));
      solicitud.setFhFechaFin(new SimpleDateFormat(ConstantesTramitacion.FORMATO_FECHA_UE).parse(anexo.getFechaFin()));
      solicitud.setPlazo(BigDecimal.valueOf(anexo.getMesesEjecucion()));

      // ----------------------------------------------------------------------------------------------------
      // 3 DECLARACIÓN, LUGAR, FECHA Y FIRMA
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

      solicitudDao.guardarRecepcionComunicacion(solicitud);

    } else {
      LOG.error("No se han encontrado el RECEPCIÓN DE COMUNICACIÓN DE INICIO para el expediente con id " + idExp);
    }
  }

  private List<CampoVeaType> procesarCampos(final List<CampoVeaType> camposVea) {

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

    return camposVeaProcesados;
  }

  public void setSolicitudDao(ISolicitudDao solicitudDao) {
    this.solicitudDao = solicitudDao;
  }

}

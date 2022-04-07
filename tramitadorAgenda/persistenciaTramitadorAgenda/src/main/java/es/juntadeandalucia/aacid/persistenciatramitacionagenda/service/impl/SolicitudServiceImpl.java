package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.DatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.vea.CampoVeaType;
import es.juntadeandalucia.aacid.comuntramitacion.exception.SubsanacionException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.DatosSubsanacionConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.SolicitudConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ISolicitudDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ISubsanacionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Solicitud;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Subsanacion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea.AaciTSolicitudsubongd;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudService;
import trewa.bd.tpo.TpoPK;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;
import trewa.exception.TrException;

/**
 * SolicitudServiceImpl class.
 *
 * @author isotrol.
 *
 */
public class SolicitudServiceImpl implements ISolicitudService {

  /**
   *
   */
  private static final long serialVersionUID = 6852213843551866577L;

  /** Log log, Atributo para los mensajes de log */
  protected final transient Log log = LogFactory.getLog(getClass());

  /** interfaz de trewaService */
  private ITrewaService trewaService;
  /** interfaz de solicitudes */
  private transient ISolicitudDao solicitudDao;
  /** interfaz de subsanacion */
  private transient ISubsanacionDao subsanacionDao;

  /**
   * obtener solicitud
   */
  @Override
  public SolicitudDTO obtenerSolicitudByIdExp(final String idExpTrewa) throws TramitacionException {
    try {
      return solicitudDao.obtenerSolicitudByIdExp(idExpTrewa);
    } catch (final TramitacionException e) {
      throw new TramitacionException("Se ha producido un error al recuperar las solicitudes ", e);
    }
  }

  /**
   * obtener datos generales de la solicitud del expediente
   */
  @Override
  public SolicitudDatosGeneralesDTO datosGeneralesSolicitudByIdExpTrewa(final String idExpTrewa) throws TramitacionException {
    try {
      return solicitudDao.datosGeneralesSolicitudByIdExpTrewa(idExpTrewa);
    } catch (final TramitacionException e) {
      throw new TramitacionException("Se ha producido un error al recuperar la solicitud", e);
    }
  }

  @Override
  public Solicitud obtenerSolicitud(final Solicitud solicitud) throws TramitacionException {
    try {
      return solicitudDao.find(solicitud);
    } catch (final TramitacionException e) {
      throw new TramitacionException("Se ha producido un error al recuperar la solicitud", e);
    }
  }

  @Transactional
  @Override
  public void guardarSolicitudOtrosDatos(final DatosGeneralesDTO datosGeneralesDTO, final String refExpediente) throws TramitacionException {
    final Solicitud solicitud = SolicitudConverter.convertDatosGeneralesToEntity(datosGeneralesDTO);

    final Subsanacion subActual = subsanacionDao.getSubsanacionByIdExpediente(Long.parseLong(refExpediente));
    if (subActual != null) {
      datosGeneralesDTO.setIdSubsanacion(subActual.getSubNuId());
    }

    final Subsanacion subsanacion = DatosSubsanacionConverter.convertDatosGeneralesDTOToEntity(datosGeneralesDTO);

    if (subsanacion.getSubNuId() != null) {
      subsanacionDao.updateSubsanacion(subsanacion);
    }

    solicitudDao.guardarSolicitudDatosGenerales(solicitud);

  }

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

  /**
   * Obtiene la propiedad subsanacionDao
   *
   * @return el subsanacionDao
   */
  public ISubsanacionDao getSubsanacionDao() {
    return subsanacionDao;
  }

  /**
   * Establece el valor de la propiedad subsanacionDao
   *
   * @param subsanacionDao
   *          el subsanacionDao para establecer
   */
  public void setSubsanacionDao(final ISubsanacionDao subsanacionDao) {
    this.subsanacionDao = subsanacionDao;
  }

  @Override
  @Transactional
  public void guardarSolicitudSubsanacion(final AaciTSolicitudsubongd solicitudSubsanacion, final List<CampoVeaType> camposPaises, final String tipoSolicitud)
      throws SubsanacionException {

    solicitudDao.guardarSolicitudSubsanacion(solicitudSubsanacion, tipoSolicitud);
    solicitudDao.updateCamposRepetibles(solicitudSubsanacion);

  }

  @Override
  public List<SolicitudDTO> obtenerSolicitudesByTipoProyectoDocumento(final Long idConv, final int tipoProyecto, final boolean isUniv)
      throws TramitacionException {
    return solicitudDao.obtenerSolicitudesByTipoProyectoDocumento(idConv, tipoProyecto, isUniv);
  }

  @Override
  public List<SolicitudDTO> datosExpedienteProvConceDesest(final List<TrExpediente> listaExpedientesHijoTrewa) throws TramitacionException {
    final List<SolicitudDTO> listadoExpedientes = new ArrayList<>();
    final List<String> listaIdTrewa = new ArrayList<>();
    listaExpedientesHijoTrewa.stream().forEach(a -> {
      listaIdTrewa.add(a.getREFEXP().toString());
    });
    try {
      if (CollectionUtils.isNotEmpty(listaIdTrewa)) {
        final List<Object> listadoObjExpedientes = solicitudDao.obtenerDatosExpedientesHijosProvConce(listaIdTrewa);
        Object[] arrayDatosExpediente;
        SolicitudDTO datosExpediente;
        for (final Object objetoExpediente : listadoObjExpedientes) {
          datosExpediente = new SolicitudDTO();
          arrayDatosExpediente = (Object[]) objetoExpediente;
          final String idTrewa = Objects.toString(arrayDatosExpediente[0], "");
          datosExpediente.setNumExpTrewa(
              listaExpedientesHijoTrewa.stream().filter(e -> StringUtils.equals(e.getREFEXP().toString(), idTrewa)).findFirst().get().getNUMEXP());
          datosExpediente.setCodIdentificativo(Objects.toString(arrayDatosExpediente[1], ""));
          datosExpediente.setTitulo(Objects.toString(arrayDatosExpediente[2], ""));
          datosExpediente.setLocalizacion(Objects.toString(arrayDatosExpediente[3], ""));
          datosExpediente.setEntidad(Objects.toString(arrayDatosExpediente[4], ""));
          datosExpediente.setCif(Objects.toString(arrayDatosExpediente[5], ""));
          datosExpediente.setPuntuacion(UtilidadesNumero
              .bigDecimalToStringConverter(UtilidadesNumero.convertStringToBigDecimalIfNotBlank(Objects.toString(arrayDatosExpediente[6], "0"))));
          datosExpediente.setDesestimientos(Objects.toString(arrayDatosExpediente[7], ""));

          listadoExpedientes.add(datosExpediente);
        }
      }
    } catch (final TramitacionException e) {
      throw new TramitacionException("Se ha producido un error al recuperar los datos de los expedientes perteneciente a la convocatoria.", e);
    }
    return listadoExpedientes;
  }

  @Override
  public List<SolicitudDTO> obtenerListadoSolicitudesHijasByFases(final String idExp, final String fases) throws TrException, TramitacionException {
    List<SolicitudDTO> listaExpedientesHijosConvocatoriaDTO = new ArrayList<>();

    final List<TrExpediente> listaExpedientesHijoTrewa = trewaService.obtenerExpedientesHijos(new TpoPK(idExp), fases);
    if (CollectionUtils.isNotEmpty(listaExpedientesHijoTrewa)) {
      listaExpedientesHijosConvocatoriaDTO = datosExpedienteProvConceDesest(listaExpedientesHijoTrewa);
    }
    return listaExpedientesHijosConvocatoriaDTO;
  }

  @Override
  public List<SolicitudDatosGeneralesDTO> obtenerSolicitudesHermanas(final String idExpediente) throws TramitacionException {
    final List<SolicitudDatosGeneralesDTO> expedientesComunicacionDTO = new ArrayList<>();
    List<TrExpediente> expedientesHermanos;
    try {
      expedientesHermanos = trewaService.obtenerExpedientesHermanos(new TpoPK(idExpediente), ConstantesTramitacion.FASE_COMUNICACION_INICIO);

      SolicitudDatosGeneralesDTO solicitudDatosGeneralesDTO;
      for (final TrExpediente expe : expedientesHermanos) {
        solicitudDatosGeneralesDTO = datosGeneralesSolicitudByIdExpTrewa(expe.getREFEXP().toString());
        solicitudDatosGeneralesDTO.setNumExpediente(expe.getNUMEXP());
        solicitudDatosGeneralesDTO.setIdExpTrewa(expe.getREFEXP().toString());
        expedientesComunicacionDTO.add(solicitudDatosGeneralesDTO);
      }
    } catch (final Exception e) {
      throw new TramitacionException("Error al obtener los expedientes hermanos", e);
    }

    return expedientesComunicacionDTO;
  }

  /**
   * Establece el valor de la propiedad trewaService
   *
   * @param trewaService
   *          el trewaService para establecer
   */
  public void setTrewaService(final ITrewaService trewaService) {
    this.trewaService = trewaService;
  }

}

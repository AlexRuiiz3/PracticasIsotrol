package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.CatalogoSubsanacionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.DatosSubsanacionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudInformesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SubsanacionSeleccionadaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesFecha;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesListas;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.DatosSubsanacionConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ICatalogoDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ICatalogoSubsanacionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IConvocatoriaDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IPaisSolicitudDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ISolicitudDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ISubsanacionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Catalogo;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.CatalogoSubsanacion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Convocatoria;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Subsanacion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeSolicitudes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ICatalogoService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IFinalidadService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeContribucionesService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeSolicitudService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISubsanacionService;
import es.juntadeandalucia.plataforma.comunes.excepciones.BusinessException;
import es.juntadeandalucia.plataforma.resources.ConstantesBean;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;
import trewa.bd.tpo.TpoPK;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;
import trewa.bd.trapi.trapiui.tpo.TrFaseActualExpediente;
import trewa.bd.trapi.trapiui.tpo.TrInteresadoExpediente;

/**
 * SubsanacionService class
 *
 * @author isotrol.
 *
 */
public class SubsanacionServiceImpl implements ISubsanacionService {

  /** subsanacionDao */
  private ISubsanacionDao subsanacionDao;

  private ISolicitudDao solicitudDao;

  private ICatalogoDao catalogoDAO;

  private ICatalogoSubsanacionDao catalogoSubsanacionDao;

  private IConvocatoriaDao convocatoriaDao;

  private ITrewaService trewaService;

  private ICatalogoService catalogoService;

  private IFinalidadService finalidadService;

  private IPaisSolicitudDao paisSolicitudDao;

  private IPaeSolicitudService paeSolicitudService;

  private IPaeContribucionesService paeContribucionesService;

  @Transactional
  @Override
  public void guardarSubsanacion(final DatosSubsanacionDTO datosSubsanacion, final String idExpediente,
      final List<SubsanacionSeleccionadaDTO> listadoSubsanaciones, final String tipoConv, final Integer anyo) throws TramitacionException {

    Subsanacion subsanacion = null;
    if (datosSubsanacion != null && datosSubsanacion.getIdSubsanacion() != null) {
      subsanacion = subsanacionDao.getById(datosSubsanacion.getIdSubsanacion().intValue());
    } else {
      subsanacion = new Subsanacion();
    }
    Subsanacion subGuardar = DatosSubsanacionConverter.convertToEntityGuardar(datosSubsanacion, subsanacion);

    final SolicitudDTO sol = solicitudDao.obtenerSolicitudByIdExp(idExpediente);
    subGuardar = subsanacionDao.saveOrUpdateSubsanacion(subGuardar);

    final List<CatalogoSubsanacion> listaCatalogosGuardados = new ArrayList<>();
    for (final SubsanacionSeleccionadaDTO subSelec : listadoSubsanaciones) {
      final String[] sub = subSelec.getCodigo().split(" - ");
      final Catalogo cat = catalogoDAO.getCatalogoByCodTipoCodCatTipoConvAnyo(sub[0], sub[1], tipoConv, anyo);

      final CatalogoSubsanacion catSub = new CatalogoSubsanacion();

      catSub.setMotivo(subSelec.getMotivo());
      catSub.setAaciSubsanacionBySubNuId(subGuardar);
      catSub.setAaciCatalogoByCatNuId(cat);

      final CatalogoSubsanacion catGuardado = catalogoSubsanacionDao.guardarCatalogoSubsanacion(catSub);
      listaCatalogosGuardados.add(catGuardado);
    }

    subGuardar.setAaciCatalogoSubsanacionsBySubNuId(listaCatalogosGuardados);

    subGuardar = subsanacionDao.saveOrUpdateSubsanacion(subGuardar);

    if (subsanacionDao.updateSubsanacionSolicitudRelation(subGuardar.getSubNuId().toString(), sol.getIdSolicitud().toString()) != 1) {
      throw new TramitacionException("error al relacionar la subsanacion con la solicitud");
    }

  }

  @Override
  public DatosSubsanacionDTO getSubsanacionByNumExpediente(final Long idExpediente, final List<Perfil> listaPerfiles) throws TramitacionException {
    final Subsanacion subsanacion = subsanacionDao.getSubsanacionByIdExpediente(idExpediente);
    if (subsanacion != null) {
      return DatosSubsanacionConverter.convertToDTO(subsanacion, listaPerfiles);
    }
    return null;
  }

  @Override
  public Long existeSubsanacion(final Long numExpediente) throws TramitacionException {
    final DatosSubsanacionDTO datos = getSubsanacionByNumExpediente(numExpediente, null);
    if (datos != null) {
      return datos.getIdSubsanacion();
    }
    return null;
  }

  @Override
  public boolean existeCatalogoSubsanacion(final Long idSolicitud) throws TramitacionException {
    final List<CatalogoSubsanacionDTO> catalogos = catalogoSubsanacionDao.obtenerCatalogoSubsancionByIdSolicitud(String.valueOf(idSolicitud));
    return CollectionUtils.isNotEmpty(catalogos);

  }

  @Transactional
  @Override
  public void deleteSubsanacion(final Long idSubsanacion) throws TramitacionException {
    final Subsanacion subsanacion = subsanacionDao.getById(idSubsanacion.intValue());
    subsanacion.setAaciCatalogoSubsanacionsBySubNuId(catalogoSubsanacionDao.obtenerCatalogoSubsancionByIdSubsanacion(subsanacion.getSubNuId()));
    catalogoSubsanacionDao.eliminarCatalogosSubsanacion(subsanacion.getAaciCatalogoSubsanacionsBySubNuId());
    subsanacionDao.removeSolicitudRelation(subsanacion);
  }

  @Override
  public Subsanacion obtenerSubsanacionByNumExpediente(final Long idExpediente) throws TramitacionException {
    return subsanacionDao.getSubsanacionByIdExpediente(idExpediente);
  }

  @Override
  public Map<String, Object> obtenerDatosDocumento(final UsuarioWeb usuario) throws TramitacionException {
    StringBuilder sResultado;
    final Map<String, Object> datos = new HashMap<>();
    final SolicitudDTO soli = solicitudDao.obtenerSolicitudByIdExp(usuario.getExpediente().getRefExpediente());
    if (soli != null) {
      try {
        String idSolicitud = "";
        if (StringUtils.isNotBlank(soli.getCodIdentificativo())) {
          idSolicitud = soli.getCodIdentificativo()
              + (usuario.getExpediente().getNumeroExpediente() != null ? (" (" + usuario.getExpediente().getNumeroExpediente() + ")") : "");

        } else {
          idSolicitud = usuario.getExpediente().getNumeroExpediente();
        }
        datos.put("idSolicitud", idSolicitud);
        datos.put("tituloProyecto", soli.getTitulo());
        datos.put("tipoSolicitud", usuario.getExpediente().getProcedimiento().getAbreviatura());
        datos.put("interesado", soli.getEntidad());

        final List<CatalogoSubsanacionDTO> listaCatSubsanacion = catalogoSubsanacionDao
            .obtenerCatalogoSubsancionByIdSolicitudDocumento(soli.getIdSolicitud().toString());
        final List<String> listaCausas = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listaCatSubsanacion)) {

          for (final CatalogoSubsanacionDTO catalogoSubsanacionDTO : listaCatSubsanacion) {
            sResultado = new StringBuilder();

            if (CollectionUtils.isNotEmpty(listaCausas)) {
              sResultado.append(ConstantesTramitacion.SALTO_LINEA);
            }

            sResultado.append(catalogoSubsanacionDTO.getCodigo()).append(" ")
                    .append(catalogoSubsanacionDTO.getCatalogo().getDescripcion());
            if (catalogoSubsanacionDTO.getMotivo() != null) {
              sResultado.append(ConstantesTramitacion.SALTO_LINEA);
              sResultado.append("Motivo: ");
              sResultado.append(catalogoSubsanacionDTO.getMotivo());
            }
            listaCausas.add(sResultado.toString());
          }
        }
        datos.put("listaSubsanaciones", listaCausas);
      } catch (final BusinessException be) {
        throw new TramitacionException(
            "Se ha producido un error al recuperar el procedimiento del expediente. Por lo que no se puede continuar con la generacion del documento");
      }
    }
    return datos;

  }

  @Override
  public Map<String, Object> obtenerDatosDocumentoSubsanaciones(final UsuarioWeb usuario, final boolean isUniv) throws TramitacionException {
    int cont = 1;
    final Map<String, Object> mapaFinal = obtenerDatosGeneralesPlantillaSubsanacion(isUniv, usuario);
    try {
      final Convocatoria convocatoria = convocatoriaDao.obtenerDatosConvocatoriaPorExpediente(usuario.getExpediente().getRefExpediente());
      if (convocatoria != null && convocatoria.getConvNuId() != null) {
        final boolean isSegundaVueltaSubsanacion = compruebaFaseConvocatoria(ConstantesTramitacion.FASE_CONV_REQUERIMIENTO_SUBSANACIONES_VUELTA, convocatoria);
        while (cont <= 4) {
          mapaFinal.putAll(comprobarSolicitudesSubsanables(convocatoria, cont, isUniv, isSegundaVueltaSubsanacion));
          cont++;
        }
      }
    } catch (final TramitacionException e) {
      throw new TramitacionException("Error al obtener los datos para la lista de subsanaciones", e);
    }
    return mapaFinal;
  }

  @Override
  public Map<String, Object> obtenerDatosDocumentoListadoSubsanacionesDocumentacion(final UsuarioWeb usuario, final boolean isUniv)
      throws TramitacionException {
    int cont = 1;
    final Map<String, Object> mapaFinal = obtenerDatosGeneralesPlantillaSubsanacion(isUniv, usuario);
    try {
      final Convocatoria convocatoria = convocatoriaDao.obtenerDatosConvocatoriaPorExpediente(usuario.getExpediente().getRefExpediente());
      if (convocatoria != null && convocatoria.getConvNuId() != null) {
        while (cont <= 4) {
          mapaFinal.putAll(comprobarSolicitudesSubsanablesDocumentacion(convocatoria, cont, isUniv));
          cont++;
        }
      }
    } catch (final TramitacionException e) {
      throw new TramitacionException("Error al obtener los datos para la lista de subsanaciones de documentos aportados", e);
    }
    return mapaFinal;
  }

  private Map<String, Object> comprobarSolicitudesSubsanablesDocumentacion(final Convocatoria conv, final int cont, final boolean isUniv)
      throws TramitacionException {
    final Map<String, Object> datos = new HashMap<>();
    List<SolicitudDTO> listaSolicitudes;
    final List<SolicitudInformesDTO> listaSolicitudesTotal = new ArrayList<>();
    List<CatalogoDTO> listaMotivos = null;

    listaSolicitudes = solicitudDao.obtenerSolicitudesByTipoProyectoDocumento(conv.getConvNuId(), cont, isUniv);

    if (listaSolicitudes != null && !listaSolicitudes.isEmpty()) {
      for (final SolicitudDTO solicitud : listaSolicitudes) {
        final SolicitudInformesDTO solInf = new SolicitudInformesDTO();

        boolean solicitudValida;

        solicitudValida = compruebaFaseSolicitud(ConstantesTramitacion.FASE_REQUERIMIENTO_DOCUMENTOS_APORTADOS, solicitud);
        if (solicitudValida) {
          estableceImporteSubsanacion(solInf, isUniv, solicitud);
          anyadeDatosSolicitudInformeDTO(solInf, solicitud, listaMotivos);
          listaSolicitudesTotal.add(solInf);
        }
      }
      UtilidadesListas.ordenamientoBurbujaListaSolicitudesInformeByNumExpediente(listaSolicitudesTotal);
      datos.put(listaTipoProyecto(cont), listaSolicitudesTotal);
    }
    return datos;
  }

  private Map<String, Object> comprobarSolicitudesSubsanables(final Convocatoria conv, final int cont, final boolean isUniv,
      final boolean isSegundaVueltaSubsanacion) throws TramitacionException {
    final Map<String, Object> datos = new HashMap<>();
    List<SolicitudDTO> listaSolicitudes;
    final List<SolicitudInformesDTO> listaSolicitudesTotal = new ArrayList<>();
    List<CatalogoDTO> listaMotivos = null;

    listaSolicitudes = solicitudDao.obtenerSolicitudesByTipoProyectoDocumento(conv.getConvNuId(), cont, isUniv);

    if (listaSolicitudes != null && !listaSolicitudes.isEmpty()) {
      for (final SolicitudDTO solicitud : listaSolicitudes) {
        final SolicitudInformesDTO solInf = new SolicitudInformesDTO();

        boolean solicitudValida = true;
        if (isSegundaVueltaSubsanacion) {
          solicitudValida = compruebaFaseSolicitud(ConstantesTramitacion.FASE_REQUERIMIENTO_SUBSANACION_VUELTA, solicitud);
        } else {
          solicitudValida = compruebaFaseSolicitud(ConstantesTramitacion.FASE_REQUERIMIENTO_SUBSANACION, solicitud);
        }
        listaMotivos = catalogoService.obtenerListaCodigosSubsanacion(solicitud.getIdSolicitud());
        if (solicitudValida && CollectionUtils.isNotEmpty(listaMotivos)) {
          estableceImporteSubsanacion(solInf, isUniv, solicitud);
          anyadeDatosSolicitudInformeDTO(solInf, solicitud, listaMotivos);
          listaSolicitudesTotal.add(solInf);
        }
      }
      UtilidadesListas.ordenamientoBurbujaListaSolicitudesInformeByNumExpediente(listaSolicitudesTotal);
      datos.put(listaTipoProyecto(cont), listaSolicitudesTotal);
    }
    return datos;
  }

  private void anyadeDatosSolicitudInformeDTO(SolicitudInformesDTO solInf, SolicitudDTO solicitud, List<CatalogoDTO> listaMotivos) throws TramitacionException {
    final TrExpediente expediente = trewaService.obtenerExpedienteTrewa(solicitud.getNumExpediente().toString());
    final String numExp = expediente.getNUMEXP() + (solicitud.getCodIdentificativo() != null ? (" (" + solicitud.getCodIdentificativo() + ")") : "");
    solInf.setEntidad(solicitud.getEntidad() != null ? solicitud.getEntidad().toUpperCase() : "");
    final String poblacion = paisSolicitudDao.obtenerPaisesSeparadosPorComaBySolicitud(solicitud.getIdSolicitud()) != null
        ? paisSolicitudDao.obtenerPaisesSeparadosPorComaBySolicitud(solicitud.getIdSolicitud()).toUpperCase()
        : "";
    solInf.setPoblacion(poblacion);
    solInf.setTitulo(solicitud.getTitulo() != null ? solicitud.getTitulo().toUpperCase() : "");
    solInf.setNumExpTrewa(numExp);
    establecerMotivosSubsanacion(listaMotivos, solInf);
    estableceCifSolicitudInformeDTO(solInf, expediente);

  }

  private void establecerMotivosSubsanacion(final List<CatalogoDTO> listaMotivos, SolicitudInformesDTO solicitud) {
    solicitud.setCausas(StringUtils.join(listaMotivos, ConstantesBean.STR_COMA));
  }

  private void estableceImporteSubsanacion(SolicitudInformesDTO solInf, boolean isUniv, SolicitudDTO solicitud) throws TramitacionException {
    BigDecimal valorTotal = new BigDecimal("0.0");
    if (isUniv) {
      valorTotal = new BigDecimal(solicitud.getImporte());
    } else {
      final PaeSolicitudes sol = paeSolicitudService.obtenerSolicitudExpByCodigo(solicitud.getCodIdentificativo());
      if (sol != null && sol.getIdSolicitud() != null) {
        valorTotal = paeContribucionesService.obtenerImporteAacid(sol);
        valorTotal = valorTotal.setScale(2, RoundingMode.CEILING);
      }
    }
    solInf.setImporte(UtilidadesNumero.bigDecimalToStringConverter(valorTotal));
  }

  private boolean compruebaFaseConvocatoria(final String fase, final Convocatoria conv) throws TramitacionException {

    final TrFaseActualExpediente[] fasesActuales = trewaService.obtenerFaseActualExpediente(new TpoPK(conv.getConvXExpe()));
    for (final TrFaseActualExpediente faseActual : fasesActuales) {
      if (faseActual.getFASE() != null && faseActual.getFASE().getNOMBRE().equalsIgnoreCase(fase)) {
        return true;
      }
    }
    return false;
  }

  private boolean compruebaFaseSolicitud(final String fase, final SolicitudDTO solicitudDTO) throws TramitacionException {
    if (solicitudDTO.getNumExpediente() != null) {
      final TrFaseActualExpediente[] fasesActuales = trewaService.obtenerFaseActualExpediente(new TpoPK(solicitudDTO.getNumExpediente()));
      for (final TrFaseActualExpediente faseActual : fasesActuales) {
        if (faseActual.getFASE() != null && faseActual.getFASE().getNOMBRE().equalsIgnoreCase(fase)) {
          return true;
        }
      }
    }
    return false;
  }

  private void estableceCifSolicitudInformeDTO(final SolicitudInformesDTO solicitud, final TrExpediente expediente) throws TramitacionException {
    final TrInteresadoExpediente inteExp = trewaService.obtenerInteresadoExpediente(expediente.getREFEXP().toString());
    String numIdentInteresado = "";
    if (inteExp == null || inteExp.getINTERESADO() == null) {
      throw new TramitacionException("No se han encontrado interesados para el expediente: " + expediente.getREFEXP().toString());
    } else {
      numIdentInteresado = inteExp.getINTERESADO().getNUMIDENT();
    }
    solicitud.setCif(numIdentInteresado);
  }

  private String listaTipoProyecto(final int cont) {
    String listaTipoProyecto = "";
    switch (cont) {
    case 1:
      listaTipoProyecto = ConstantesTramitacion.EXP_PROY_COOP_INT;
      break;
    case 2:
      listaTipoProyecto = ConstantesTramitacion.EXP_PROY_ACC_HUM;
      break;
    case 3:
      listaTipoProyecto = ConstantesTramitacion.EXP_PROY_DESARROLLO;
      break;
    case 4:
      listaTipoProyecto = ConstantesTramitacion.EXP_PROY_FOR_INNOV;
      break;
    default:
      break;
    }
    return listaTipoProyecto;
  }

  private Map<String, Object> obtenerDatosGeneralesPlantillaSubsanacion(final boolean isUniv, final UsuarioWeb usuario) throws TramitacionException {
    final Calendar cal = Calendar.getInstance();
    final int year = cal.get(Calendar.YEAR);
    final Map<String, Object> datos = new HashMap<>();
    if (isUniv) {
      datos.put(ConstantesTramitacion.FINALIDAD_PROYECTO,
          finalidadService.obtenerFinalidadByNumExpediente(Long.valueOf(usuario.getExpediente().getRefExpediente()), true).toUpperCase());
    } else {
      datos.put(ConstantesTramitacion.FINALIDAD_PROYECTO,
          finalidadService.obtenerFinalidadByNumExpediente(Long.valueOf(usuario.getExpediente().getRefExpediente()), false).toUpperCase());
    }
    datos.put(ConstantesTramitacion.FECHA_PUBLICACION_ACCID, UtilidadesFecha.obtenerFechaModoTexto(new Date()));
    datos.put(ConstantesTramitacion.ANYO_CONVOCATORIA, year);
    return datos;
  }

  /**
   * @return the subsanacionDao
   */
  public ISubsanacionDao getSubsanacionDao() {
    return subsanacionDao;
  }

  /**
   * @param subsanacionDao
   *          the subsanacionDao to set
   */
  public void setSubsanacionDao(final ISubsanacionDao subsanacionDao) {
    this.subsanacionDao = subsanacionDao;
  }

  public ISolicitudDao getSolicitudDao() {
    return solicitudDao;
  }

  public void setSolicitudDao(final ISolicitudDao solicitudDao) {
    this.solicitudDao = solicitudDao;
  }

  public ICatalogoDao getCatalogoDAO() {
    return catalogoDAO;
  }

  public void setCatalogoDAO(final ICatalogoDao catalogoDAO) {
    this.catalogoDAO = catalogoDAO;
  }

  public ICatalogoSubsanacionDao getCatalogoSubsanacionDao() {
    return catalogoSubsanacionDao;
  }

  public void setCatalogoSubsanacionDao(final ICatalogoSubsanacionDao catalogoSubsanacionDao) {
    this.catalogoSubsanacionDao = catalogoSubsanacionDao;
  }

  public void setConvocatoriaDao(final IConvocatoriaDao convocatoriaDao) {
    this.convocatoriaDao = convocatoriaDao;
  }

  public void setTrewaService(final ITrewaService trewaService) {
    this.trewaService = trewaService;
  }

  public void setCatalogoService(final ICatalogoService catalogoService) {
    this.catalogoService = catalogoService;
  }

  public void setFinalidadService(final IFinalidadService finalidadService) {
    this.finalidadService = finalidadService;
  }

  public void setPaisSolicitudDao(final IPaisSolicitudDao paisSolicitudDao) {
    this.paisSolicitudDao = paisSolicitudDao;
  }

  public void setPaeSolicitudService(final IPaeSolicitudService paeSolicitudService) {
    this.paeSolicitudService = paeSolicitudService;
  }

  public void setPaeContribucionesService(final IPaeContribucionesService paeContribucionesService) {
    this.paeContribucionesService = paeContribucionesService;
  }
}

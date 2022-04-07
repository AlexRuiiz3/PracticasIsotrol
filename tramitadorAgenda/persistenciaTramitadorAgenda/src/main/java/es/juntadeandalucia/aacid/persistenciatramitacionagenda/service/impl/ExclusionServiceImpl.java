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
import es.juntadeandalucia.aacid.comuntramitacion.dto.DatosExclusionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ExclusionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudInformesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.IParametrosConfiguracionService;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesBoolean;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesFecha;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesListas;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.ExclusionConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IConvocatoriaDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IExclusionDAO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IExclusionesSolicitudDAO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IPaisSolicitudDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ISolicitudDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Convocatoria;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Exclusion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.ExclusionesSolicitud;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Solicitud;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TExclusionesSolicitud;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeSolicitudes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IExclusionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IFinalidadService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeContribucionesService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeSolicitudService;
import es.juntadeandalucia.plataforma.comunes.excepciones.ArchitectureException;
import es.juntadeandalucia.plataforma.resources.ConstantesBean;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;
import trewa.bd.trapi.trapiui.tpo.TrFaseExpediente;
import trewa.bd.trapi.trapiui.tpo.TrInteresadoExpediente;

/**
 * Implementacion de la interfaz IExclusionService
 *
 * @author isotrol.
 *
 */
public class ExclusionServiceImpl implements IExclusionService {

  /** interfaz de solicitudes */
  private IExclusionDAO exclusionDAO;
  private IExclusionesSolicitudDAO exclusionesSolicitudDAO;
  private ISolicitudDao solicitudDao;
  private IConvocatoriaDao convocatoriaDao;
  private ITrewaService trewaService;
  private IFinalidadService finalidadService;
  private IPaisSolicitudDao paisSolicitudDao;
  private IPaeSolicitudService paeSolicitudService;
  private IPaeContribucionesService paeContribucionesService;
  /** Servicio para obtener los parámetros de configuración */
  private IParametrosConfiguracionService parametrosConfiguracionService;

  @Override
  public List<ExclusionDTO> obtenerExclusionesDTOByOrdenTipo(final String tipoConv, final Long numTipo, final Long orden, final Long anio,
      final List<Perfil> perfil) throws TramitacionException {
    final List<ExclusionDTO> exclusionesDTO = new ArrayList<>();
    List<Exclusion> exclusiones;
    try {
      exclusiones = exclusionDAO.obtenerExclusionesByOrdenTipo(tipoConv, numTipo, orden, anio);

      if (CollectionUtils.isNotEmpty(exclusiones)) {
        for (final Exclusion excl : exclusiones) {
          exclusionesDTO.add(ExclusionConverter.convertirToDTO(excl, perfil));
        }
      }
      return exclusionesDTO;
    } catch (final Exception e) {
      throw new TramitacionException("Se ha producido un al obtener las exclusiones disponibles segun el filtro  tipoConv, numTipo, orden, anio: " + tipoConv
          + ", " + numTipo + ", " + orden + ", " + anio, e);
    }
  }

  @Override
  public List<ExclusionDTO> obtenerExclusionesDTOByIds(final List<Long> ids, final List<Perfil> perfiles) throws TramitacionException {
    final List<ExclusionDTO> exclusionesDTO = new ArrayList<>();
    List<Exclusion> exclusiones;
    try {

      if (CollectionUtils.isNotEmpty(ids)) {
        exclusiones = exclusionDAO.obtenerExclusionesByIds(ids);
        for (final Exclusion excl : exclusiones) {
          exclusionesDTO.add(ExclusionConverter.convertirToDTO(excl, perfiles));
        }
      }
      return exclusionesDTO;
    } catch (final Exception e) {
      throw new TramitacionException("Se ha producido un al obtener las exclusiones disponibles", e);
    }
  }

  @Override
  public List<String> obtenerIdsExclusionesSolicitudByOrdenTipo(final Long idSoli, final Long numTipo, final Long orden, final List<Perfil> listaPerfiles)
      throws TramitacionException {
    return procesarExclusionesSolicitud(exclusionesSolicitudDAO.obtenerIdsExclusionesSolicitud(idSoli, numTipo, orden), listaPerfiles);
  }

  @Override
  public List<Integer> obtenerIdsExclusionesSolicitud(final Long idSoli, final List<Perfil> listaPerfiles) throws TramitacionException {
    return exclusionesSolicitudDAO.obtenerTodasIdsExclusionesSolicitud(idSoli);
  }

  @Override
  @Transactional
  public void guardarExclusionesSolicitud(final DatosExclusionDTO datosExclusionDTO) throws TramitacionException {
    final Solicitud solicitud = new Solicitud();
    solicitud.setIdSolicitud(datosExclusionDTO.getIdSolicitud());
    final List<Long> exclusionesSolicitud = exclusionesSolicitudDAO.obtenerIdsExclusionesBySoli(solicitud.getIdSolicitud(),
        datosExclusionDTO.getTipoExclusion());
    final List<String> exclusionesSolicitudDTO = new ArrayList<>(datosExclusionDTO.getExclusionesComunesSeleccionadas());
    exclusionesSolicitudDTO.addAll(datosExclusionDTO.getExclusionesCooperacionSeleccionadas());
    exclusionesSolicitudDTO.addAll(datosExclusionDTO.getExclusionesEducacionSeleccionadas());
    exclusionesSolicitudDTO.addAll(datosExclusionDTO.getExclusionesFormacionSeleccionadas());
    exclusionesSolicitudDTO.addAll(datosExclusionDTO.getExclusionesHumanitariaSeleccionadas());
    exclusionesSolicitudDTO.addAll(datosExclusionDTO.getExclusionesAndaluciaSeleccionadas());
    exclusionesSolicitudDTO.addAll(datosExclusionDTO.getExclusionesPaisesPriorizadosSeleccionadas());
    exclusionesSolicitudDTO.addAll(datosExclusionDTO.getExclusionesInadmisionSeleccionadas());
    exclusionesSolicitudDTO.addAll(datosExclusionDTO.getExclusionesProyCooperacionSeleccionadas());
    exclusionesSolicitudDTO.addAll(datosExclusionDTO.getExclusionesProyAccHumanitariaSeleccionadas());
    exclusionesSolicitudDTO.addAll(datosExclusionDTO.getExclusionesProyDesarrolloSeleccionadas());

    /* Si tine exclusiones de excepciones */
    exclusionesSolicitud.addAll(exclusionesSolicitudDAO.obtenerIdsExclusionesBySoli(solicitud.getIdSolicitud(), 2L));
    exclusionesSolicitudDTO.addAll(datosExclusionDTO.getExclusionesExcepcionTipo2Seleccionadas());

    // Recorremos exclusionesSolicitud para comprobar cuales han sido eliminados No existen en DTO
    final List<Long> auxExclSoli = new ArrayList<>(exclusionesSolicitud);
    for (final Long idExcl : auxExclSoli) {
      comprobarExisteObjetoEnLista(exclusionesSolicitudDTO, idExcl, solicitud.getIdSolicitud(), exclusionesSolicitud);
    }

    // Recorremos DTO para insertar nuevas Exclusiones en la solicitud
    aniadimosExclusionesNuevasPorTipo(exclusionesSolicitudDTO, solicitud, exclusionesSolicitud);
    guardarComprobacionExclusionPorTipo(datosExclusionDTO);
  }

  @Override
  public List<Integer> obtenerIdsExclusionesSolicitudByTipo(final Long idSoli, final List<Perfil> listaPerfiles, final Integer tipo)
      throws TramitacionException {
    return exclusionesSolicitudDAO.obtenerIdsExclusionesSolicitudByTipo(idSoli, tipo);
  }

  private List<String> procesarExclusionesSolicitud(final List<Long> exclusionesSolicitud, final List<Perfil> listaPerfiles) throws TramitacionException {
    final List<String> listaVuelta = new ArrayList<>();
    try {
      for (final Long exc : exclusionesSolicitud) {
        if (listaPerfiles == null) {
          listaVuelta.add(exc.toString());
        } else {
          final Exclusion e = exclusionDAO.findById(exc);

          final ExclusionDTO ed = ExclusionConverter.convertirToDTO(e, listaPerfiles);
          listaVuelta.add(ed.getIdEditar());
        }
      }
    } catch (final ArchitectureException e) {
      throw new TramitacionException("Error al obtener la exclusión: ", e);
    }
    return listaVuelta;
  }

  @Override
  public boolean existsExlusionesBySolicitud(final Long idSolicitud) throws TramitacionException {
    final BigDecimal totalExc = exclusionesSolicitudDAO.existsExlusionesBySolicitud(idSolicitud);
    return !totalExc.equals(new BigDecimal(0));
  }

  /**
   * @param idsExclusiones
   * @param solicitud
   * @param exclusionesSolicitud
   * @throws TramitacionException
   */
  private void aniadimosExclusionesNuevasPorTipo(final List<String> idsExclusiones, final Solicitud solicitud, final List<Long> exclusionesSolicitud)
      throws TramitacionException {
    boolean existe;
    Long idExcl;
    ExclusionesSolicitud newExclSoli;
    Exclusion exclusion;
    final List<Long> auxExclSoli = new ArrayList<>(exclusionesSolicitud);
    for (final String strIdExcl : idsExclusiones) {
      idExcl = Long.valueOf(strIdExcl);
      existe = false;
      for (int i = 0; i < auxExclSoli.size() && !existe; i++) {
        existe = idExcl.compareTo(auxExclSoli.get(i)) == 0;
      }
      if (!existe) {
        newExclSoli = new ExclusionesSolicitud();
        exclusion = new Exclusion();
        exclusion.setExclNuId(idExcl);
        newExclSoli.setAaciTSolicitudsubongdBySoliNuId(solicitud);
        newExclSoli.setAaciExclusionByExclNuId(exclusion);
        exclusionesSolicitud.add(idExcl);
        exclusionesSolicitudDAO.createEntidad(newExclSoli);
      }
    }
  }

  /**
   * Guardamos la comprobación de exclusiones
   *
   * @param datosExclusionDTO
   * @throws TramitacionException
   */
  private void guardarComprobacionExclusionPorTipo(final DatosExclusionDTO datosExclusionDTO) throws TramitacionException {
    final Object objTieneExclusiones = exclusionDAO.tieneExclusionesTipoExclusion(datosExclusionDTO.getIdSolicitud(),
        datosExclusionDTO.getTipoExclusion().intValue());
    final TExclusionesSolicitud tieneExclusiones = new TExclusionesSolicitud();
    if (objTieneExclusiones != null) {
      final Object[] arrayAgrupaciones = (Object[]) objTieneExclusiones;
      tieneExclusiones.setTesoNuId(((BigDecimal) arrayAgrupaciones[0]).longValue());
    }
    tieneExclusiones.setTexcNuTipo(datosExclusionDTO.getTipoExclusion());
    tieneExclusiones.setSolicitud(new Solicitud());
    tieneExclusiones.getSolicitud().setIdSolicitud(datosExclusionDTO.getIdSolicitud());
    tieneExclusiones.setTesoLgExclusiones(datosExclusionDTO.isNoTieneExclusiones());

    exclusionDAO.guardarComprobacionExclusionPorTipo(tieneExclusiones);
  }

  /**
   * @param listaExclusion
   * @param idExcl
   * @return
   * @throws TramitacionException
   */
  private boolean comprobarExisteObjetoEnLista(final List<String> listaExclusion, final Long idExcl, final Long idSolicitud,
      final List<Long> exclusionesSolicitud) throws TramitacionException {
    boolean existe = false;
    for (int i = 0; i < listaExclusion.size() && !existe; i++) {
      // Comprobamos si el id es numerico o no. En caso de no serlo es nueva
      existe = Long.valueOf(listaExclusion.get(0)).compareTo(idExcl) == 0;
    }
    if (!existe) {
      exclusionesSolicitud.remove(idExcl);
      exclusionesSolicitudDAO.eliminarExclusionSolicitud(idExcl, idSolicitud);
    }
    return existe;
  }

  @Override
  public Map<String, Object> obtenerDatosDocumentoExclusiones(final UsuarioWeb usuario, final boolean isUniv, final boolean isConv,
      final List<Perfil> listaPerfiles, final boolean isDeses, final String fase) throws TramitacionException {
    int tipoProyecto = 1;
    final Map<String, Object> mapaFinal = new HashMap<>(obtenerDatosGeneralesDocumento(usuario, isUniv, isConv));
    try {
      final Convocatoria convocatoria = convocatoriaDao.obtenerDatosConvocatoriaPorExpediente(usuario.getExpediente().getRefExpediente());
      if (convocatoria != null && convocatoria.getConvNuId() != null) {
        while (tipoProyecto <= 4) {
          mapaFinal.putAll(comprobarSolicitudesExcluidas(convocatoria.getConvNuId(), tipoProyecto, isUniv, listaPerfiles, isDeses, fase));
          tipoProyecto++;
        }
      }
    } catch (final TramitacionException e) {
      throw new TramitacionException("Error al obtener los datos para la lista de exclusi\u00F3n provisional", e);
    }
    return mapaFinal;
  }

  @Override
  public Map<String, Object> obtenerDatosDocumentoExclusionesDesestimadas(final UsuarioWeb usuario, final boolean isUniv, final boolean isConv,
      final List<Perfil> listaPerfiles, final long tipoExclusion, final String fase) throws TramitacionException {
    int tipoProyecto = 1;
    final Map<String, Object> mapaFinal = new HashMap<>(obtenerDatosGeneralesDocumento(usuario, isUniv, isConv));
    try {
      final Convocatoria convocatoria = convocatoriaDao.obtenerDatosConvocatoriaPorExpediente(usuario.getExpediente().getRefExpediente());
      if (convocatoria != null && convocatoria.getConvNuId() != null) {
        while (tipoProyecto <= 4) {
          mapaFinal.putAll(comprobarSolicitudesExcluidasDesestimadas(convocatoria.getConvNuId(), tipoProyecto, isUniv, listaPerfiles, tipoExclusion, fase));
          tipoProyecto++;
        }
      }
    } catch (final TramitacionException e) {
      throw new TramitacionException("Error al obtener los datos para la lista de exclusiones desestimadas", e);
    }
    return mapaFinal;
  }

  private Map<String, Object> obtenerDatosGeneralesDocumento(final UsuarioWeb usuario, final boolean isUniv, final boolean isConv) throws TramitacionException {
    Map<String, Object> datosGenerales;
    datosGenerales = obtenerDatosDocumentoResolucionExcluidas(usuario, false, isUniv, isConv);
    final Convocatoria conv = obtenerFechasConvocatoria(usuario);
    if (conv != null && conv.getConvFhValoracion() != null) {
      datosGenerales.put(ConstantesTramitacion.FECHA_VALORACION, UtilidadesFecha.obtenerFechaModoTexto(conv.getConvFhValoracion()));
    } else {
      datosGenerales.put(ConstantesTramitacion.FECHA_VALORACION, ConstantesTramitacion.FECHA_VALORACION_NO_DEFINIDA);
    }
    datosGenerales.put(ConstantesTramitacion.FECHA_FIN_PRESENTACIONES, UtilidadesFecha
        .obtenerFechaModoTexto(obtenerFecFinPresentaAlegOrSolicitudes(new Date(), Integer.valueOf(ConstantesTramitacion.PLAZO_FECHA_FIN_PRESENTACIONES))));
    return datosGenerales;
  }

  private Map<String, Object> comprobarSolicitudesExcluidas(final Long convNuId, final int tipoProyecto, final boolean isUniv, final List<Perfil> listaPerfiles,
      final boolean isDeses, final String fase) throws TramitacionException {
    final Map<String, Object> datos = new HashMap<>();
    final List<SolicitudInformesDTO> listaSolicitudesTotal = new ArrayList<>();
    final List<SolicitudDTO> listaSolicitudes = solicitudDao.obtenerSolicitudesByTipoProyectoDocumento(convNuId, tipoProyecto, isUniv);
    if (CollectionUtils.isNotEmpty(listaSolicitudes)) {
      for (final SolicitudDTO solicitud : listaSolicitudes) {
        final TrExpediente expediente = trewaService.obtenerExpedienteTrewa(String.valueOf(solicitud.getNumExpediente()));
        // Comprobamos que la fase sea la correcta
        if (fase == null || trewaService.compruebaFaseCorrectaExpediente(expediente, fase)) {
          final SolicitudInformesDTO solInf = new SolicitudInformesDTO();
          // se obtiene el numero de expediente en trewa

          estableceImporteExclusion(solInf, isUniv, solicitud);

          estableceCifSolicitudInformeDTO(solInf, solicitud, expediente);

          anyadeDatosSolicitudInformeDTO(solicitud, solInf, expediente);

          if (establecerCausasExc(solicitud, isDeses, listaPerfiles)) {
            listaSolicitudesTotal.add(solInf);
          }
        }
      }
      UtilidadesListas.ordenamientoBurbujaListaSolicitudesInformeByNumExpediente(listaSolicitudesTotal);
      datos.put(listaTipoProyecto(tipoProyecto), listaSolicitudesTotal);
    }
    return datos;
  }

  private void anyadeDatosSolicitudInformeDTO(final SolicitudDTO solicitud, final SolicitudInformesDTO solInf, final TrExpediente expediente) {
    final String numExp = expediente.getNUMEXP() + (solicitud.getCodIdentificativo() != null ? (" (" + solicitud.getCodIdentificativo() + ")") : "");

    solInf.setEntidad(solicitud.getEntidad() != null ? solicitud.getEntidad().toUpperCase() : "");
    final String poblacion = paisSolicitudDao.obtenerPaisesSeparadosPorComaBySolicitud(solicitud.getIdSolicitud()) != null
        ? paisSolicitudDao.obtenerPaisesSeparadosPorComaBySolicitud(solicitud.getIdSolicitud()).toUpperCase()
        : "";
    solInf.setPoblacion(poblacion);
    solInf.setTitulo(solicitud.getTitulo() != null ? solicitud.getTitulo().toUpperCase() : "");
    solInf.setNumExpTrewa(numExp);

  }

  private void estableceImporteExclusion(final SolicitudInformesDTO solInf, final boolean isUniv, final SolicitudDTO solicitud) throws TramitacionException {
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

  private void estableceCifSolicitud(final SolicitudDTO solicitud, final TrExpediente expediente) throws TramitacionException {
    final TrInteresadoExpediente inteExp = trewaService.obtenerInteresadoExpediente(expediente.getREFEXP().toString());
    String numIdentInteresado = "";
    if (inteExp == null || inteExp.getINTERESADO() == null) {
      throw new TramitacionException("No se han encontrado interesados para el expediente: " + expediente.getREFEXP().toString());
    } else {
      numIdentInteresado = inteExp.getINTERESADO().getNUMIDENT();
    }
    solicitud.setCif(numIdentInteresado);
  }

  private void estableceCifSolicitudInformeDTO(final SolicitudInformesDTO solInf, final SolicitudDTO solicitud, final TrExpediente expediente)
      throws TramitacionException {
    final TrInteresadoExpediente inteExp = trewaService.obtenerInteresadoExpediente(expediente.getREFEXP().toString());
    String numIdentInteresado = "";
    if (inteExp == null || inteExp.getINTERESADO() == null) {
      throw new TramitacionException("No se han encontrado interesados para el expediente: " + expediente.getREFEXP().toString());
    } else {

      numIdentInteresado = inteExp.getINTERESADO().getNUMIDENT();
    }
    solInf.setCif(numIdentInteresado);

    if (StringUtils.isBlank(solInf.getCif())) {
      solInf.setCif(solicitud.getCif());
    }
  }

  private Map<String, Object> comprobarSolicitudesExcluidasDesestimadas(final Long convNuId, final int tipoProyecto, final boolean isUniv,
      final List<Perfil> listaPerfiles, final long tipoExclusion, final String fase) throws TramitacionException {
    final Map<String, Object> datos = new HashMap<>();
    List<SolicitudDTO> listaSolicitudes;
    final List<SolicitudDTO> listaSolicitudesTotal = new ArrayList<>();
    listaSolicitudes = solicitudDao.obtenerSolicitudesByTipoProyectoYTipoExclusion(convNuId, tipoProyecto, tipoExclusion, isUniv);
    if (CollectionUtils.isNotEmpty(listaSolicitudes)) {
      for (final SolicitudDTO solicitud : listaSolicitudes) {
        // se obtiene el numero de expediente en trewa
        final TrExpediente expediente = trewaService.obtenerExpedienteTrewa(solicitud.getNumExpediente().toString());
        if (fase == null || trewaService.compruebaFaseCorrectaExpediente(expediente, fase)) {
          anyadeDatosSolicitudDTO(solicitud, expediente);

          estableceCifSolicitud(solicitud, expediente);

          if (establecerCausasExc(solicitud, true, listaPerfiles)) {
            listaSolicitudesTotal.add(solicitud);
          }
        }
      }
      UtilidadesListas.ordenamientoBurbujaListaSolicitudesByNumExpediente(listaSolicitudesTotal);
      datos.put(listaTipoProyecto(tipoProyecto), listaSolicitudesTotal);
    }
    return datos;
  }

  private void anyadeDatosSolicitudDTO(final SolicitudDTO solicitud, final TrExpediente expediente) {
    final String numExp = expediente.getNUMEXP() + (solicitud.getCodIdentificativo() != null ? (" (" + solicitud.getCodIdentificativo() + ")") : "");
    solicitud.setNumExpTrewa(numExp);
    final String paises = paisSolicitudDao.obtenerPaisesSeparadosPorComaBySolicitud(solicitud.getIdSolicitud()) != null
        ? paisSolicitudDao.obtenerPaisesSeparadosPorComaBySolicitud(solicitud.getIdSolicitud()).toUpperCase()
        : "";
    solicitud.setPoblacion(paises);
    solicitud.setTitulo(solicitud.getTitulo() != null ? solicitud.getTitulo().toUpperCase() : "");

  }

  private boolean establecerCausasExc(final SolicitudDTO solicitud, final boolean isDeses, final List<Perfil> listaPerfiles) throws TramitacionException {
    List<Integer> idsExclusiones = new ArrayList<>();
    if (isDeses) {
      idsExclusiones = obtenerIdsExclusionesSolicitudByTipo(solicitud.getIdSolicitud(), listaPerfiles, 3);
    } else {
      idsExclusiones.addAll(obtenerIdsExclusionesSolicitudByTipo(solicitud.getIdSolicitud(), listaPerfiles, 1));
      idsExclusiones.addAll(obtenerIdsExclusionesSolicitudByTipo(solicitud.getIdSolicitud(), listaPerfiles, 2));
    }
    if (CollectionUtils.isNotEmpty(idsExclusiones)) {
      solicitud.setCausasExc(StringUtils.join(idsExclusiones, ConstantesBean.STR_COMA));
      return true;
    }
    return false;
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

  @Override
  public Map<String, Object> obtenerDatosDocumentoResolucionExcluidas(final UsuarioWeb usuarioSesion, final boolean isResolProv, final boolean isUniv,
      final boolean isConv) throws TramitacionException {
    final Calendar cal = Calendar.getInstance();
    final int year = cal.get(Calendar.YEAR);
    final Map<String, Object> datos = new HashMap<>();
    if (!isConv) {
      final String finalidad = obtenerFinalidadByTipoInforme(usuarioSesion, isUniv);
      if (finalidad != null) {
        datos.put(ConstantesTramitacion.FINALIDAD_PROYECTO, finalidad);
      } else {
        datos.put(ConstantesTramitacion.FINALIDAD_PROYECTO, ConstantesTramitacion.FINALIDAD_NO_DEFINIDA);
      }
    }
    datos.put(ConstantesTramitacion.FECHA_PUBLICACION_ACCID, UtilidadesFecha.obtenerFechaModoTexto(new Date()));
    datos.put(ConstantesTramitacion.ANYO_CONVOCATORIA, String.valueOf(year));
    if (isResolProv) {
      datos.put(ConstantesTramitacion.FECHA_FIN_PRESENTACIION_ALEG, UtilidadesFecha
          .obtenerFechaModoTexto(obtenerFecFinPresentaAlegOrSolicitudes(new Date(), Integer.valueOf(ConstantesTramitacion.PLAZO_FECHA_FIN_PRESENT_ALEG))));
    } else {
      final Convocatoria convocatoria = obtenerFechasConvocatoria(usuarioSesion);
      if (convocatoria != null && convocatoria.getConvFhLimitDocResolDef() != null) {
        datos.put(ConstantesTramitacion.FECHA_RESOLUCION, UtilidadesFecha.obtenerFechaModoTexto(convocatoria.getConvFhLimitDocResolDef()));
      } else {
        datos.put(ConstantesTramitacion.FECHA_RESOLUCION, ConstantesTramitacion.FECHA_RESOLUCION_NO_DEFINIDA);
      }
    }
    return datos;
  }

  @Override
  public Map<String, Object> obtenerDatosDocumentoResolucionDestimadas(final UsuarioWeb usuarioSesion, final boolean isResolProv, final boolean isUniv,
      final boolean isConv) throws TramitacionException {
    final Calendar cal = Calendar.getInstance();
    final int year = cal.get(Calendar.YEAR);
    final Map<String, Object> datos = new HashMap<>();
    if (!isConv) {
      final String finalidad = obtenerFinalidadByTipoInforme(usuarioSesion, isUniv);
      if (finalidad != null) {
        datos.put(ConstantesTramitacion.FINALIDAD_PROYECTO, finalidad);
      } else {
        datos.put(ConstantesTramitacion.FINALIDAD_PROYECTO, ConstantesTramitacion.FINALIDAD_NO_DEFINIDA);
      }
    }
    datos.put(ConstantesTramitacion.FECHA_PUBLICACION_ACCID, UtilidadesFecha.obtenerFechaModoTexto(new Date()));
    datos.put(ConstantesTramitacion.ANYO_CONVOCATORIA, year);
    if (isResolProv) {
      datos.put(ConstantesTramitacion.FECHA_FIN_PRESENTACIION_ALEG, UtilidadesFecha
          .obtenerFechaModoTexto(obtenerFecFinPresentaAlegOrSolicitudes(new Date(), Integer.valueOf(ConstantesTramitacion.PLAZO_FECHA_FIN_PRESENT_ALEG))));
    } else {
      final Convocatoria convocatoria = obtenerFechasConvocatoria(usuarioSesion);
      if (convocatoria != null && convocatoria.getConvFhLimitDocResolDef() != null) {
        datos.put(ConstantesTramitacion.FECHA_RESOLUCION, UtilidadesFecha.obtenerFechaModoTexto(convocatoria.getConvFhLimitDocResolDef()));
      } else {
        datos.put(ConstantesTramitacion.FECHA_RESOLUCION, ConstantesTramitacion.FECHA_RESOLUCION_NO_DEFINIDA);
      }
    }
    return datos;
  }

  /**
   * Obtiene la finalidad del proyecto teniendo en cuenta si es ONGD o Universidad y si es para el informe correspondiente a convocatoria
   * pone el texto en mayúsculas.
   *
   * @param usuarioSesion
   * @param isUniv
   * @return
   * @throws TramitacionException
   */
  private String obtenerFinalidadByTipoInforme(final UsuarioWeb usuarioSesion, final boolean isUniv) throws TramitacionException {
    String finalidad = "";
    if (isUniv) {
      finalidad = finalidadService.obtenerFinalidadByNumExpediente(Long.valueOf(usuarioSesion.getExpediente().getRefExpediente()), true);
    } else {
      finalidad = finalidadService.obtenerFinalidadByNumExpediente(Long.valueOf(usuarioSesion.getExpediente().getRefExpediente()), false);
    }
    return finalidad;
  }

  private Convocatoria obtenerFechasConvocatoria(final UsuarioWeb usuarioSesion) throws TramitacionException {
    try {
      return convocatoriaDao.obtenerDatosConvocatoriaPorExpediente(usuarioSesion.getExpediente().getRefExpediente());
    } catch (final TramitacionException e) {
      throw new TramitacionException("Error al obtener los datos para la lista de exclusi\u00F3n provisional", e);
    }
  }

  private Date obtenerFecFinPresentaAlegOrSolicitudes(final Date fechaReferencia, final int numTotalDias) {
    final Calendar fechaInicio = Calendar.getInstance();
    fechaInicio.setTime(fechaReferencia);
    int dias = 1;

    while (dias <= numTotalDias) {
      fechaInicio.add(Calendar.DATE, 1);
      if (fechaInicio.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && fechaInicio.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
        dias++;
      }
    }
    return fechaInicio.getTime();
  }

  @Override
  public boolean existenExclusionesSolicitudByTipo(final String numExpediente, final Integer tipo, final String excepciones) throws TramitacionException {
    final List<Integer> ordenExclusiones = exclusionDAO.obtenerExclusionesSolicitudByTipoExclusion(numExpediente, null);
    boolean sonExcepciones = true;

    if (ordenExclusiones != null) {
      for (int i = 0; ordenExclusiones.size() > i && sonExcepciones; i++) {
        sonExcepciones = StringUtils.contains(excepciones, ordenExclusiones.get(i).toString());
      }
    } else {
      return false;
    }

    return !sonExcepciones && !ordenExclusiones.isEmpty();
  }

  @Override
  public String obtenerExcepcionesExclusiones(final String tipoExclusiones, final String faseFinExclusiones, final TrFaseExpediente faseExpediente)
      throws TramitacionException {
    String strExcepcionesExcl = null;

    final String faseSinExcepcionesTipo2 = parametrosConfiguracionService.obtenerParametroConfiguracion(faseFinExclusiones,
        faseExpediente.getREFDEFPROC().toString());

    // Comprobamos que la fase del expediente no sea de las que no tienen excepciones
    if (!StringUtils.equalsIgnoreCase(faseSinExcepcionesTipo2, faseExpediente.getFASE().getDESCRIPCION())) {
      strExcepcionesExcl = parametrosConfiguracionService.obtenerParametroConfiguracion(faseFinExclusiones, faseExpediente.getREFDEFPROC().toString());
    }
    return strExcepcionesExcl;
  }

  @Override
  public boolean noTieneExclusionesTipoExclusion(final Long idSolicitud, final Integer tipo) throws TramitacionException {
    final Object objetoTieneExclusiones = exclusionDAO.tieneExclusionesTipoExclusion(idSolicitud, tipo);
    if (objetoTieneExclusiones != null) {
      final Object[] arrayAgrupaciones = (Object[]) objetoTieneExclusiones;
      final TExclusionesSolicitud tieneExclusiones = new TExclusionesSolicitud();
      tieneExclusiones.setTesoLgExclusiones(UtilidadesBoolean.convertirNumberToBoolean(((BigDecimal) arrayAgrupaciones[3]).intValue()));

      return tieneExclusiones.getTesoLgExclusiones();

    } else {
      return false;
    }
  }

  /**
   * Establece el valor de la propiedad exclusionDAO
   *
   * @param exclusionDAO
   *          el exclusionDAO para establecer
   */
  public void setExclusionDAO(final IExclusionDAO exclusionDAO) {
    this.exclusionDAO = exclusionDAO;
  }

  /**
   * Establece el valor de la propiedad exclusionesSolicitudDAO
   *
   * @param exclusionesSolicitudDAO
   *          el exclusionesSolicitudDAO para establecer
   */
  public void setExclusionesSolicitudDAO(final IExclusionesSolicitudDAO exclusionesSolicitudDAO) {
    this.exclusionesSolicitudDAO = exclusionesSolicitudDAO;
  }

  /**
   * Establece el valor de la propiedad solicitudDao
   *
   * @param solicitudDao
   *          el solicitudDao para establecer
   */
  public void setSolicitudDao(final ISolicitudDao solicitudDao) {
    this.solicitudDao = solicitudDao;
  }

  /**
   * Establece el valor de la propiedad convocatoriaDao
   *
   * @param convocatoriaDao
   *          el convocatoriaDao para establecer
   */
  public void setConvocatoriaDao(final IConvocatoriaDao convocatoriaDao) {
    this.convocatoriaDao = convocatoriaDao;
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

  /**
   * Establece el valor de la propiedad finalidadService
   *
   * @param finalidadService
   *          el finalidadService para establecer
   */
  public void setFinalidadService(final IFinalidadService finalidadService) {
    this.finalidadService = finalidadService;
  }

  /**
   * Establece el valor de la propiedad paisSolicitudDao
   *
   * @param paisSolicitudDao
   *          el paisSolicitudDao para establecer
   */
  public void setPaisSolicitudDao(final IPaisSolicitudDao paisSolicitudDao) {
    this.paisSolicitudDao = paisSolicitudDao;
  }

  /**
   * Establece el valor de la propiedad paeSolicitudService
   *
   * @param paeSolicitudService
   *          el paeSolicitudService para establecer
   */
  public void setPaeSolicitudService(final IPaeSolicitudService paeSolicitudService) {
    this.paeSolicitudService = paeSolicitudService;
  }

  /**
   * Establece el valor de la propiedad paeContribucionesService
   *
   * @param paeContribucionesService
   *          el paeContribucionesService para establecer
   */
  public void setPaeContribucionesService(final IPaeContribucionesService paeContribucionesService) {
    this.paeContribucionesService = paeContribucionesService;
  }

  /**
   * Establece el valor de la propiedad parametrosConfiguracionService
   *
   * @param parametrosConfiguracionService
   *          el parametrosConfiguracionService para establecer
   */
  public void setParametrosConfiguracionService(final IParametrosConfiguracionService parametrosConfiguracionService) {
    this.parametrosConfiguracionService = parametrosConfiguracionService;
  }
}
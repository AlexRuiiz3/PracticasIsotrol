package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudConcesionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.service.ITrewaService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.SolicitudConcesionConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ISolicitudConcesionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.SolicitudConcesion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.TipoConvocatoria;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ISolicitudConcesionService;
import trewa.bd.tpo.TpoPK;
import trewa.bd.trapi.trapiui.tpo.TrExpediente;
import trewa.exception.TrException;

public class SolicitudConcesionServiceImpl implements ISolicitudConcesionService {

  private ISolicitudConcesionDao solicitudConcesionDao;
  /** interfaz de trewaService */
  private ITrewaService trewaService;

  @Override
  public SolicitudConcesion obtenerSolicitudConcesionPorSolicitud(final Long idSolicitud) throws TramitacionException {
    return solicitudConcesionDao.obtenerSolicitudConcesionPorSolicitud(idSolicitud);
  }

  @Override
  public List<SolicitudConcesion> obtenerSolicitudConcesionPorSolicitud(final List<SolicitudConcesionDTO> listaExpedientesHijosConvocatoriaDTO)
      throws TramitacionException {
    final List<SolicitudConcesion> lstConcesiones = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(listaExpedientesHijosConvocatoriaDTO)) {
      try {
        for (final SolicitudConcesionDTO solConceDTO : listaExpedientesHijosConvocatoriaDTO) {
          final SolicitudConcesion solConce = obtenerSolicitudConcesionPorSolicitud(solConceDTO.getIdSolicitud());
          if (solConce != null) {
            lstConcesiones.add(solConce);
          }
        }
      } catch (final TramitacionException e) {
        throw new TramitacionException("Se ha producido un error al recuperar los datos de la concesión de la solicitud", e);
      }
    }
    return lstConcesiones;
  }

  public void setSolicitudConcesionDao(final ISolicitudConcesionDao solicitudConcesionDao) {
    this.solicitudConcesionDao = solicitudConcesionDao;
  }

  @Override
  public List<SolicitudConcesionDTO> obtenerBeneficiariasSuplentes(final String idExp, final String fases, final String finalidad, final boolean ongd)
      throws TrException, TramitacionException {
    List<SolicitudConcesionDTO> listaConcesionesDTO = null;
    final List<TrExpediente> listaExpedientesHijoTrewa = trewaService.obtenerExpedientesHijos(new TpoPK(idExp), fases);
    if (CollectionUtils.isNotEmpty(listaExpedientesHijoTrewa)) {
      listaConcesionesDTO = obtenerBeneficiariasSuplentesPorFinalidad(listaExpedientesHijoTrewa, finalidad, ongd);
    }
    return listaConcesionesDTO;
  }

  private List<SolicitudConcesionDTO> obtenerBeneficiariasSuplentesPorFinalidad(final List<TrExpediente> listaExpedientes, final String finalidad,
      final boolean ongd) {
    List<SolicitudConcesionDTO> listadoConcesionesDTO = new ArrayList<>();
    final List<String> listaIdTrewa = new ArrayList<>();
    listaExpedientes.stream().forEach(a -> {
      listaIdTrewa.add(a.getREFEXP().toString());
    });

    if (CollectionUtils.isNotEmpty(listaIdTrewa)) {
      listadoConcesionesDTO = SolicitudConcesionConverter
          .convertirObjectToSolicitudConcesionDTO(solicitudConcesionDao.obtenerListaConcesiones(listaIdTrewa, finalidad, ongd), listaExpedientes);
    }

    return listadoConcesionesDTO;

  }

  @Override
  @Transactional
  public List<SolicitudConcesion> guardarListaSolicitudConcesion(final List<SolicitudConcesionDTO> listaSolicitudConcesionDTO, final Long convocatoria)
      throws TramitacionException {
    final List<SolicitudConcesion> listSolConcesion = obtenerSolicitudConcesionPorSolicitud(listaSolicitudConcesionDTO);

    boolean encontrado = false;
    SolicitudConcesion solConceGuardar = new SolicitudConcesion();
    for (final SolicitudConcesionDTO solConceDTO : listaSolicitudConcesionDTO) {
      encontrado = false;
      for (int i = 0; listSolConcesion.size() > i && !encontrado; i++) {
        solConceGuardar = listSolConcesion.get(i);
        // La concesión ya existe en BD y la actualizamos
        if (solConceDTO.getIdSolicitud().equals(solConceGuardar.getSocoNuSolicitud())) {
          solConceGuardar.setSocoTxTipoConcesion(setearBeneficiaria(solConceDTO));
          encontrado = true;
        }
      }
      // La concesión no existe en BD y la creamos
      if (!encontrado) {
        solConceGuardar.setSocoNuSolicitud(solConceDTO.getIdSolicitud());
        solConceGuardar.setSocoTxTipoConvocatoria(new TipoConvocatoria());
        solConceGuardar.getSocoTxTipoConvocatoria().setTconvNuId(convocatoria);
        solConceGuardar.setSocoTxTipoConcesion(setearBeneficiaria(solConceDTO));
        listSolConcesion.add(solConceGuardar);
      }
    }

    solicitudConcesionDao.guardar(listSolConcesion);

    return listSolConcesion;
  }

  /**
   * @param solConceGuardar
   * @param solConceDTO
   */
  private String setearBeneficiaria(final SolicitudConcesionDTO solConceDTO) {
    String beneficiaria = null;
    if (solConceDTO.getBeneficiaria() != null) {
      if (Boolean.TRUE.equals(solConceDTO.getBeneficiaria())) {
        beneficiaria = ConstantesTramitacion.BENEFICIARIA;
      } else {
        beneficiaria = ConstantesTramitacion.SUPLENTE;
      }
    }
    return beneficiaria;
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

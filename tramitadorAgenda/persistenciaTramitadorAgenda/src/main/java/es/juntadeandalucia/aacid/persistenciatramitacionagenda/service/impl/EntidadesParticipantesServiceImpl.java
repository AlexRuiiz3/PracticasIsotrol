package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.juntadeandalucia.aacid.comuntramitacion.dto.EntidadParticipanteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.EntidadesParticipantesConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl.EntidadesParticipantesDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.EntidadesParticipantes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IEntidadesParticipantesService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IFuncionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ITipoEntidadParticipanteService;

public class EntidadesParticipantesServiceImpl implements IEntidadesParticipantesService {

  private IFuncionService funcionService;
  private ITipoEntidadParticipanteService tipoEntidadParticipanteService;
  private EntidadesParticipantesDao entidadesParticipantesDao;

  @Override
  public EntidadesParticipantes saveOrUpdateEntidadParticipante(final EntidadesParticipantes entidad) throws TramitacionException {
    return entidadesParticipantesDao.saveOrUpdateEntidadParticipante(entidad);
  }

  @Override
  @Transactional
  public void saveOrUpdateEntidadesParticipantes(final List<EntidadParticipanteDTO> entidadesParticipantesDTO) throws TramitacionException {
    EntidadesParticipantes entidad;
    EntidadesParticipantes entidadGuardada;
    for (final EntidadParticipanteDTO entidadDTO : entidadesParticipantesDTO) {
      entidad = EntidadesParticipantesConverter.convertEntidadesParticipantesDTOToDao(entidadDTO);
      entidad.setFuncion(funcionService.obtenerFuncionByCodigo(entidadDTO.getFuncion().getFuncTxCodigo()));
      entidad.setTipoEntidadParticipante(
          tipoEntidadParticipanteService.obtenerTipoEntidadParticipanteByCodigo(entidad.getTipoEntidadParticipante().getTepaTxCodigo()));
      entidadGuardada = entidadesParticipantesDao.saveOrUpdateEntidadParticipante(entidad);
      entidadDTO.setId(entidadGuardada.getId());
    }

  }

  @Override
  public List<EntidadesParticipantes> obtenerEntidadesParticipantesBySolicitud(final Long idSolicitud) {
    return entidadesParticipantesDao.obtenerEntidadesParticipantesBySolicitud(idSolicitud);
  }

  public void setEntidadesParticipantesDao(final EntidadesParticipantesDao entidadesParticipantesDao) {
    this.entidadesParticipantesDao = entidadesParticipantesDao;
  }

  /**
   * Establece el valor de la propiedad funcionService
   *
   * @param funcionService
   *          el funcionService para establecer
   */
  public void setFuncionService(final IFuncionService funcionService) {
    this.funcionService = funcionService;
  }

  /**
   * Establece el valor de la propiedad tipoEntidadParticipanteService
   *
   * @param tipoEntidadParticipanteService
   *          el tipoEntidadParticipanteService para establecer
   */
  public void setTipoEntidadParticipanteService(final ITipoEntidadParticipanteService tipoEntidadParticipanteService) {
    this.tipoEntidadParticipanteService = tipoEntidadParticipanteService;
  }

}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ValoracionTipoCriterioDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.CriteriosConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ICriteriosDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Criterios;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.ICriteriosService;

public class CriteriosServiceImpl implements ICriteriosService {

  private ICriteriosDao criteriosDao;

  @Override
  public ValoracionTipoCriterioDTO obtenerListaCriteriosByFinalidadAndConvocatoria(final String finalidad, final String tipoCriterio, final boolean isUniv,
      final Long anyo, final boolean comprobarSubFinalidad) throws TramitacionException {
    ValoracionTipoCriterioDTO valoracionTipoCriterioDTO = new ValoracionTipoCriterioDTO();
    valoracionTipoCriterioDTO.setListaValoraciones(new ArrayList<>());
    List<Criterios> criterios;
    try {
      criterios = criteriosDao.obtenerListaCriteriosByFinalidadAndConvocatoria(finalidad, tipoCriterio, isUniv, anyo, comprobarSubFinalidad);
      if (CollectionUtils.isNotEmpty(criterios)) {
        valoracionTipoCriterioDTO = CriteriosConverter.convertirToDTO(criterios);
      }
    } catch (final TramitacionException e) {
      throw new TramitacionException(
          "Se ha producido un error al obtener los criterios disponibles segun el filtro  finalidad, tipoCriterio: " + finalidad + ", " + tipoCriterio, e);
    }
    return valoracionTipoCriterioDTO;
  }

  /**
   * @param criteriosDao
   *          the criteriosDao to set
   */
  public void setCriteriosDao(final ICriteriosDao criteriosDao) {
    this.criteriosDao = criteriosDao;
  }

}

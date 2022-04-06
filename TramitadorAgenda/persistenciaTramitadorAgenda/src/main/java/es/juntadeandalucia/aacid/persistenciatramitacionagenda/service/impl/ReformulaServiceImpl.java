package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesMensajes;
import es.juntadeandalucia.aacid.comuntramitacion.dto.reformula.DatosReformulaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.reformula.ReformulaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.objects.Importe;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.functions.ReformulaToReformulaDTOFunction;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IReformulaDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Reformula;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IReformulaService;

public class ReformulaServiceImpl implements IReformulaService {

  private IReformulaDao reformulaDao;

  @Override
  public Optional<ReformulaDTO> findBySolicitudId(Long idSolicitud) throws TramitacionException {
    return reformulaDao.findAllBySolicitudIdOrderById(idSolicitud).stream().findFirst().map(new ReformulaToReformulaDTOFunction());
  }

  @Transactional
  @Override
  public ReformulaDTO save(DatosReformulaDTO datosReformulaDTO) throws TramitacionException {

    Reformula toUpdate;

    Long idSolicitud = datosReformulaDTO.getIdSolicitud();

    Optional<Reformula> optional = reformulaDao.findAllBySolicitudIdOrderById(idSolicitud).stream().findFirst();

    if (optional.isPresent()) {
      toUpdate = optional.get();
    } else {
      toUpdate = new Reformula();
      toUpdate.setIdSolicitud(idSolicitud);
    }

    toUpdate.setTipo("-1".equals(datosReformulaDTO.getTipoSeleccionado()) ? null : datosReformulaDTO.getTipoSeleccionado());
    toUpdate.setMaximo(new Importe(datosReformulaDTO.getMaximoAACID()));
    toUpdate.setMinimo(new Importe(datosReformulaDTO.getMinimoPresupuestoTotal()));

    return new ReformulaToReformulaDTOFunction().apply(reformulaDao.save(toUpdate));
  }

  @Override
  public List<String> validated(DatosReformulaDTO datosReformulaDTO) {

    List<String> errores = new ArrayList<>();

    Importe minimo = new Importe("0");
    Importe maximo = new Importe("999999999.99");

    // máximo AACID
    final String maximoAACID = datosReformulaDTO.getMaximoAACID();
    final String labelMaximoAACID = ConstantesMensajes.REFORMULA_LABEL_MAXIMO_AACID;
    validatedImporte(errores, minimo, maximo, maximoAACID, labelMaximoAACID);

    // minimo presupuesto total
    final String minimoPresupuestoTotal = datosReformulaDTO.getMinimoPresupuestoTotal();
    final String labelMinimoPresupuestoTotal = ConstantesMensajes.REFORMULA_LABEL_MINIMO_PRESUPUESTO_TOTAL;
    validatedImporte(errores, minimo, maximo, minimoPresupuestoTotal, labelMinimoPresupuestoTotal);

    return errores;
  }

  private void validatedImporte(List<String> errores, Importe minimo, Importe maximo, final String importe, String label) {
    if (StringUtils.isBlank(importe)) {
      errores.add(MessageFormat.format(ConstantesMensajes.MSG_ERROR_CAMPO_REQUIRED, label));
    } else {

      if (!UtilidadesNumero.isImporte(importe)) {
        errores.add(MessageFormat.format(ConstantesMensajes.MSG_ERROR_CAMPO_IS_IMPORTE, label));
      } else {

        Importe bdMaximoAACID = new Importe(importe);

        if (bdMaximoAACID.compareTo(minimo) < 0 || bdMaximoAACID.compareTo(maximo) > 0) {
          errores.add(MessageFormat.format(ConstantesMensajes.MSG_ERROR_CAMPO_RANGE_IMPORTE, label, minimo.toString(), maximo.toString()));
        }
      }
    }
  }

  public IReformulaDao getReformulaDao() {
    return reformulaDao;
  }

  public void setReformulaDao(final IReformulaDao reformulaDao) {
    this.reformulaDao = reformulaDao;
  }

}
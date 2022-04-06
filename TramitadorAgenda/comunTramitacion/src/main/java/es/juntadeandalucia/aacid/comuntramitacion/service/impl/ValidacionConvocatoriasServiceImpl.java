package es.juntadeandalucia.aacid.comuntramitacion.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesMensajes;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ConvocatoriaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.service.IValidacionConvocatoriasService;

public class ValidacionConvocatoriasServiceImpl implements IValidacionConvocatoriasService {

  @Override
  public List<String> validarDatosConvocatoria(ConvocatoriaDTO convocatoriaDTO) throws ParseException {
    Date fecIni = null;
    Date fecFin = null;
    List<String> listaErrores = new ArrayList<>();

    fecFin = convocatoriaDTO.getFhFin();

    if (convocatoriaDTO.getTitulo() == null || StringUtils.isEmpty(convocatoriaDTO.getTitulo())) {
      listaErrores.add(ConstantesMensajes.TITULO_REQ);
    }
    if (convocatoriaDTO.getFhInicio() == null) {
      listaErrores.add(ConstantesMensajes.FECHAINI_REQ);
    } else {
      fecIni = convocatoriaDTO.getFhInicio();
    }
    if (convocatoriaDTO.getFhFin() == null) {
      listaErrores.add(ConstantesMensajes.FECHAFIN_REQ);
    } else {
      validarFechasConvocatoria(convocatoriaDTO, listaErrores, fecIni, fecFin);
    }
    return listaErrores;
  }

  private void validarFechasConvocatoria(ConvocatoriaDTO convocatoriaDTO, List<String> listaErrores, Date fecIni, Date fecFin) {
    Date fecActual = null;
    fecActual = new Date();
    if (fecActual.after(fecFin)) {
      listaErrores.add(ConstantesMensajes.FECHA_ACTUAL_SUP);
    }
    if (fecIni != null) {
      if (fecIni.after(fecFin)) {
        listaErrores.add(ConstantesMensajes.FECHA_INI_MAYOR);
      }
      validaRangoDeFechas(convocatoriaDTO.getFhInicioValoracion(), listaErrores, fecIni, fecFin, ConstantesMensajes.FECHAINIVAL_ERROR);
      validaRangoDeFechas(convocatoriaDTO.getFhPropResolProv(), listaErrores, fecIni, fecFin, ConstantesMensajes.FECHA_PROP_RESOL_ERROR);
      validaRangoDeFechas(convocatoriaDTO.getFhLimitDocResolDef(), listaErrores, fecIni, fecFin, ConstantesMensajes.FECHA_LIMITE_ENT_DOC_ERROR);
      validaRangoDeFechas(convocatoriaDTO.getFhResolConc(), listaErrores, fecIni, fecFin, ConstantesMensajes.FECHA_RESOL_CONCES_ERROR);
    }
  }

  private void validaRangoDeFechas(Date fecha, List<String> listaErrores, Date fecIni, Date fecFin, String mensajeError) {
    if (fecha != null && (!fecIni.before(fecha) && !fecIni.equals(fecha) || !fecFin.after(fecha) && !fecFin.equals(fecha))) {
      listaErrores.add(mensajeError);
    }
  }
}
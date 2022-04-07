package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ConvocatoriaDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ConvocatoriaFechaDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Convocatoria;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.ConvocatoriaFecha;

public class ConvocatoriaConverter {

  private ConvocatoriaConverter() {
  }

  /**
   * Conversora DTO
   *
   * @param convocatoria
   *          convocatoriaDao
   * @return ConvocatoriaDTO convocatoriaDto
   * @throws ParseException
   */
  public static ConvocatoriaDTO convertirToDto(final Convocatoria convocatoria) {
    final ConvocatoriaDTO convocatoriaDTO = new ConvocatoriaDTO();
    if (null != convocatoria) {
      convocatoriaDTO.setDescripcion(convocatoria.getConvLiDescripcion());
      convocatoriaDTO.setFhFin(convocatoria.getConvFhFin());
      convocatoriaDTO.setFhInicio(convocatoria.getConvFhInicio());
      convocatoriaDTO.setFhInicioValoracion(convocatoria.getConvFhValoracion());
      convocatoriaDTO.setFhLimitDocResolDef(convocatoria.getConvFhLimitDocResolDef());
      convocatoriaDTO.setFhPropResolProv(convocatoria.getConvFhPropResolProv());
      convocatoriaDTO.setFhResolConc(convocatoria.getConvFhResolConce());
      convocatoriaDTO.setTitulo(convocatoria.getConvLiTitulo());
      convocatoriaDTO.setIdConv(convocatoria.getConvNuId());
      convocatoriaDTO.setIdExpTrewa(convocatoria.getConvXExpe());
      if (convocatoria.getAaciTTipoConvByTconvNuId() != null && convocatoria.getAaciTTipoConvByTconvNuId().getTconvNuId() != null) {
        convocatoriaDTO.setIdTipConv(convocatoria.getAaciTTipoConvByTconvNuId().getTconvNuId());
      }
      if (convocatoria.getConvocatoriaFechas() != null) {
        convocatoriaDTO.setConvocatoriaFechasDTO(new ConvocatoriaFechaDTO());
        for (final ConvocatoriaFecha convFh : convocatoria.getConvocatoriaFechas()) {
          switch (convFh.getConvfCoFase()) {
          case "REC_SOL":
            convocatoriaDTO.getConvocatoriaFechasDTO().setFhInicioRecSol(convFh.getConvfFhApertura());
            convocatoriaDTO.getConvocatoriaFechasDTO().setFhFinRecSol(convFh.getConvfFhCierre());
            break;
          case "REC_SUB":
            convocatoriaDTO.getConvocatoriaFechasDTO().setFhInicioRecSub(convFh.getConvfFhApertura());
            convocatoriaDTO.getConvocatoriaFechasDTO().setFhFinRecSub(convFh.getConvfFhCierre());
            break;
          case "REC_ALEGA":
            convocatoriaDTO.getConvocatoriaFechasDTO().setFhInicioRecAlega(convFh.getConvfFhApertura());
            convocatoriaDTO.getConvocatoriaFechasDTO().setFhFinRecAlega(convFh.getConvfFhCierre());
            break;
          case "REC_SUB_2":
            convocatoriaDTO.getConvocatoriaFechasDTO().setFhInicioRecSub2(convFh.getConvfFhApertura());
            convocatoriaDTO.getConvocatoriaFechasDTO().setFhFinRecSub2(convFh.getConvfFhCierre());
            break;
          case "REC_AARPD":
            convocatoriaDTO.getConvocatoriaFechasDTO().setFhInicioRecAARPD(convFh.getConvfFhApertura());
            convocatoriaDTO.getConvocatoriaFechasDTO().setFhFinRecAARPD(convFh.getConvfFhCierre());
            break;
          case "REC_SUB_DOC":
            convocatoriaDTO.getConvocatoriaFechasDTO().setFhInicioRecSubDoc(convFh.getConvfFhApertura());
            convocatoriaDTO.getConvocatoriaFechasDTO().setFhFinRecSubDoc(convFh.getConvfFhCierre());
            break;
          case "REC_COM_INI":
            convocatoriaDTO.getConvocatoriaFechasDTO().setFhInicioRecComIni(convFh.getConvfFhApertura());
            convocatoriaDTO.getConvocatoriaFechasDTO().setFhFinRecComIni(convFh.getConvfFhCierre());
            break;
          default:
            break;
          }
        }
      }
    }
    return convocatoriaDTO;
  }

  /**
   * Conversor de DTO
   *
   * @param convocatoria2
   *
   * @param ConvocatoriaDTO
   *          convocatoriaDto
   * @return convocatoriaDao convocatoriaDao
   * @throws ParseException
   */
  public static Convocatoria convertirDtoToDao(final Convocatoria convocatoria, final ConvocatoriaDTO convocatoriaDTO) {
    if (null != convocatoriaDTO) {
      convocatoria.setConvLiDescripcion(convocatoriaDTO.getDescripcion());
      convocatoria.setConvFhFin(convocatoriaDTO.getFhFin());
      convocatoria.setConvFhInicio(convocatoriaDTO.getFhInicio());
      convocatoria.setConvFhValoracion(convocatoriaDTO.getFhInicioValoracion());
      convocatoria.setConvFhLimitDocResolDef(convocatoriaDTO.getFhLimitDocResolDef());
      convocatoria.setConvFhPropResolProv(convocatoriaDTO.getFhPropResolProv());
      convocatoria.setConvFhResolConce(convocatoriaDTO.getFhResolConc());
      convocatoria.setConvLiAbrevProcedimiento(convocatoriaDTO.getAbreviatura());
      convocatoria.setConvLiTitulo(convocatoriaDTO.getTitulo());
      ConvocatoriaFecha convocatoriaFecha;

      if (CollectionUtils.isEmpty(convocatoria.getConvocatoriaFechas())) {
        convocatoria.setConvocatoriaFechas(new HashSet<>());
        for (final Entry<String, String> codigoFase : ConstantesTramitacion.obtenerMapaCodigoFase().entrySet()) {
          convocatoriaFecha = new ConvocatoriaFecha();
          convocatoriaFecha.setConvocatoria(convocatoria);
          convocatoriaFecha.setConvfCoFase(codigoFase.getKey());
          convocatoriaFecha.setConvfLiFase(codigoFase.getValue());
          if (codigoFase.getKey().equals(ConstantesTramitacion.COD_FASE_REC_SOL)) {
            convocatoriaFecha.setConvfFhApertura(convocatoriaDTO.getConvocatoriaFechasDTO().getFhInicioRecSol());
            convocatoriaFecha.setConvfFhCierre(convocatoriaDTO.getConvocatoriaFechasDTO().getFhFinRecSol());
          } else if (codigoFase.getKey().equals(ConstantesTramitacion.COD_FASE_REC_SUB)) {
            convocatoriaFecha.setConvfFhApertura(convocatoriaDTO.getConvocatoriaFechasDTO().getFhInicioRecSub());
            convocatoriaFecha.setConvfFhCierre(convocatoriaDTO.getConvocatoriaFechasDTO().getFhFinRecSub());
          } else if (codigoFase.getKey().equals(ConstantesTramitacion.COD_FASE_REC_ALEGA)) {
            convocatoriaFecha.setConvfFhApertura(convocatoriaDTO.getConvocatoriaFechasDTO().getFhInicioRecAlega());
            convocatoriaFecha.setConvfFhCierre(convocatoriaDTO.getConvocatoriaFechasDTO().getFhFinRecAlega());
          } else if (codigoFase.getKey().equals(ConstantesTramitacion.COD_FASE_REC_SUB_2)) {
            convocatoriaFecha.setConvfFhApertura(convocatoriaDTO.getConvocatoriaFechasDTO().getFhInicioRecSub2());
            convocatoriaFecha.setConvfFhCierre(convocatoriaDTO.getConvocatoriaFechasDTO().getFhFinRecSub2());
          } else if (codigoFase.getKey().equals(ConstantesTramitacion.COD_FASE_REC_AARPD)) {
            convocatoriaFecha.setConvfFhApertura(convocatoriaDTO.getConvocatoriaFechasDTO().getFhInicioRecAARPD());
            convocatoriaFecha.setConvfFhCierre(convocatoriaDTO.getConvocatoriaFechasDTO().getFhFinRecAARPD());
          } else if (codigoFase.getKey().equals(ConstantesTramitacion.COD_FASE_REC_SUB_DOC)) {
            convocatoriaFecha.setConvfFhApertura(convocatoriaDTO.getConvocatoriaFechasDTO().getFhInicioRecSubDoc());
            convocatoriaFecha.setConvfFhCierre(convocatoriaDTO.getConvocatoriaFechasDTO().getFhFinRecSubDoc());
          } else if (codigoFase.getKey().equals(ConstantesTramitacion.COD_FASE_REC_COM_INI)) {
            convocatoriaFecha.setConvfFhApertura(convocatoriaDTO.getConvocatoriaFechasDTO().getFhInicioRecComIni());
            convocatoriaFecha.setConvfFhCierre(convocatoriaDTO.getConvocatoriaFechasDTO().getFhFinRecComIni());
          }
          convocatoria.getConvocatoriaFechas().add(convocatoriaFecha);
        }
      } else {
        for (final ConvocatoriaFecha convFh : convocatoria.getConvocatoriaFechas()) {
          switch (convFh.getConvfCoFase()) {
          case "REC_SOL":
            convFh.setConvfFhApertura(convocatoriaDTO.getConvocatoriaFechasDTO().getFhInicioRecSol());
            convFh.setConvfFhCierre(convocatoriaDTO.getConvocatoriaFechasDTO().getFhFinRecSol());
            break;
          case "REC_SUB":
            convFh.setConvfFhApertura(convocatoriaDTO.getConvocatoriaFechasDTO().getFhInicioRecSub());
            convFh.setConvfFhCierre(convocatoriaDTO.getConvocatoriaFechasDTO().getFhFinRecSub());
            break;
          case "REC_ALEGA":
            convFh.setConvfFhApertura(convocatoriaDTO.getConvocatoriaFechasDTO().getFhInicioRecAlega());
            convFh.setConvfFhCierre(convocatoriaDTO.getConvocatoriaFechasDTO().getFhFinRecAlega());
            break;
          case "REC_SUB_2":
            convFh.setConvfFhApertura(convocatoriaDTO.getConvocatoriaFechasDTO().getFhInicioRecSub2());
            convFh.setConvfFhCierre(convocatoriaDTO.getConvocatoriaFechasDTO().getFhFinRecSub2());
            break;
          case "REC_AARPD":
            convFh.setConvfFhApertura(convocatoriaDTO.getConvocatoriaFechasDTO().getFhInicioRecAARPD());
            convFh.setConvfFhCierre(convocatoriaDTO.getConvocatoriaFechasDTO().getFhFinRecAARPD());
            break;
          case "REC_SUB_DOC":
            convFh.setConvfFhApertura(convocatoriaDTO.getConvocatoriaFechasDTO().getFhInicioRecSubDoc());
            convFh.setConvfFhCierre(convocatoriaDTO.getConvocatoriaFechasDTO().getFhFinRecSubDoc());
            break;
          case "REC_COM_INI":
            convFh.setConvfFhApertura(convocatoriaDTO.getConvocatoriaFechasDTO().getFhInicioRecComIni());
            convFh.setConvfFhCierre(convocatoriaDTO.getConvocatoriaFechasDTO().getFhFinRecComIni());
            break;
          default:
            break;
          }
        }
      }
    }
    return convocatoria;
  }
}

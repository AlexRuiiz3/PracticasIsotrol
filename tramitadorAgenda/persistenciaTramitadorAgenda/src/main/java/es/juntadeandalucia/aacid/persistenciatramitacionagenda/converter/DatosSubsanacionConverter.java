package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import java.util.ArrayList;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.DatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.DatosSubsanacionDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Subsanacion;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;

public class DatosSubsanacionConverter {

  public static DatosSubsanacionDTO convertToDTO(Subsanacion sub, List<Perfil> listaPerfiles) {

    DatosSubsanacionDTO subsanacion = new DatosSubsanacionDTO();

    subsanacion.setIdSubsanacion(sub.getSubNuId().longValue());
    subsanacion.setObservaciones(sub.getSubLiObservaciones());
    subsanacion.setObservacionesDes(sub.getSubLiMotivoDesestimacion());
    subsanacion.setFechaSubsanacion(sub.getSubFhSubsanacion());
    subsanacion.setFechaEntrega(sub.getSubFhEntrega());
    subsanacion.setFechaLimite(sub.getSubFhLimite());
    subsanacion.setFechaRegistro(sub.getSubFhRegistro());
    subsanacion.setFechaEntrada(sub.getSubFhEntrada());
    subsanacion.setLugarRegistro(sub.getSubLiLugarRegistro());

    subsanacion.setCatalogosSeleccionados(CatalogoSubsanacionConverter.convertListToDTO(sub.getAaciCatalogoSubsanacionsBySubNuId(), listaPerfiles));

    subsanacion.setAlmeria(sub.getSubLgAlmeria());
    subsanacion.setCadiz(sub.getSubLgCadiz());
    subsanacion.setCordoba(sub.getSubLgCordoba());
    subsanacion.setGranada(sub.getSubLgGranada());
    subsanacion.setHuelva(sub.getSubLgHuelva());
    subsanacion.setJaen(sub.getSubLgJaen());
    subsanacion.setMalaga(sub.getSubLgMalaga());
    subsanacion.setSevilla(sub.getSubLgSevilla());
    subsanacion.setAfrica(sub.getSubLgAfrica());
    subsanacion.setServicios(sub.getSubLgServicios());
    subsanacion.setPrevRiesgos(sub.getSubLgPrevRiesgos());
    subsanacion.setOtros(sub.getSubLgOtros());
    subsanacion.setNoEspecifica(sub.getNoEspecifica());

    return subsanacion;

  }

  public static List<DatosSubsanacionDTO> convertToListDto(List<Subsanacion> listaSubs, List<Perfil> listaPerfiles) {
    List<DatosSubsanacionDTO> listaSubsanaciones = new ArrayList<>();

    for (Subsanacion sub : listaSubs) {
      listaSubsanaciones.add(convertToDTO(sub, listaPerfiles));
    }
    return listaSubsanaciones;
  }

  public static Subsanacion convertToEntityGuardar(DatosSubsanacionDTO datos, Subsanacion sub) {
    sub.setSubLiObservaciones(datos.getObservaciones());
    sub.setSubLiMotivoDesestimacion(datos.getObservacionesDes());
    sub.setSubFhSubsanacion(datos.getFechaSubsanacion());
    sub.setSubFhEntrega(datos.getFechaEntrega());
    sub.setSubFhLimite(datos.getFechaLimite());
    sub.setSubFhRegistro(datos.getFechaRegistro());
    sub.setSubFhEntrada(datos.getFechaEntrada());
    sub.setSubLiLugarRegistro(datos.getLugarRegistro());
    sub.setSubLgAlmeria(datos.getAlmeria());
    sub.setSubLgCadiz(datos.getCadiz());
    sub.setSubLgCordoba(datos.getCordoba());
    sub.setSubLgGranada(datos.getGranada());
    sub.setSubLgHuelva(datos.getHuelva());
    sub.setSubLgJaen(datos.getJaen());
    sub.setSubLgMalaga(datos.getMalaga());
    sub.setSubLgSevilla(datos.getSevilla());
    sub.setSubLgAfrica(datos.getAfrica());
    sub.setSubLgServicios(datos.getServicios());
    sub.setSubLgPrevRiesgos(datos.getPrevRiesgos());
    sub.setSubLgOtros(datos.getOtros());
    sub.setNoEspecifica(datos.getNoEspecifica());

    return sub;
  }

  public static Subsanacion convertDatosGeneralesDTOToEntity(DatosGeneralesDTO datos) {
    Subsanacion sub = new Subsanacion();

    sub.setSubNuId(datos.getIdSubsanacion());

    sub.setSubLiObservaciones(datos.getObservaciones());
    sub.setSubLiMotivoDesestimacion(datos.getMotivoDesestimacion());
    sub.setSubLgPrevRiesgos(datos.isPrevRiesgos());
    sub.setSubLgAfrica(datos.isAfrica());
    sub.setSubLgServicios(datos.isServSoc());
    sub.setSubLgOtros(datos.isOtros());
    sub.setNoEspecifica(datos.isNoEspec());
    sub.setSubLgAlmeria(datos.getAlmeria());
    sub.setSubLgCadiz(datos.getCadiz());
    sub.setSubLgHuelva(datos.getHuelva());
    sub.setSubLgSevilla(datos.getSevilla());
    sub.setSubLgCordoba(datos.getCordoba());
    sub.setSubLgGranada(datos.getGranada());
    sub.setSubLgJaen(datos.getJaen());
    sub.setSubLgMalaga(datos.getMalaga());

    return sub;
  }
}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import java.util.ArrayList;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.GastoDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Gasto;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeGastos;

public class GastosConverter {

  /**
   *
   */
  private GastosConverter() {
    // Auto-generated constructor stub
  }

  public static GastoDTO gastosToGastosDTO(final Gasto gastos) {
    return new GastoDTO(gastos.getGastCoCodigo(), gastos.getGastTxDescripcion(), gastos.getGastNuOrden(), gastos.getAaciGasto());
  }

  public static Gasto parseGastosDTOToGastos(final GastoDTO gastosDTO) {
    final Gasto gastos = new Gasto();
    gastos.setAaciGasto(gastosDTO.getIdGasto());
    gastos.setGastCoCodigo(gastosDTO.getCodigo());
    gastos.setAaciGasto(gastosDTO.getIdGasto());
    gastos.setGastTxDescripcion(gastosDTO.getDescripcion());
    return gastos;
  }

  public static List<Gasto> parseListGastosDTOToGastos(final List<GastoDTO> gastosDTO) {
    final List<Gasto> gastos = new ArrayList<>();
    gastosDTO.forEach((final GastoDTO gastoDTO) -> gastos.add(parseGastosDTOToGastos(gastoDTO)));
    return gastos;
  }

  public static GastoDTO paeGastosToGastosDTO(final PaeGastos paeGastos) {
    return new GastoDTO(paeGastos.getCodigo(), paeGastos.getCoste(), paeGastos.getOrden(), paeGastos.getIdCoste());
  }

  public static List<GastoDTO> parseListGastosToListGastosDTO(final List<Gasto> gastos) {
    final List<GastoDTO> gastosDTO = new ArrayList<>();
    gastos.forEach((final Gasto gasto) -> gastosDTO.add(gastosToGastosDTO(gasto)));
    return gastosDTO;
  }

  public static List<GastoDTO> parseListPaeGastosToListGastosDTO(final List<PaeGastos> paeGastos) {
    final List<GastoDTO> gastosDTO = new ArrayList<>();
    paeGastos.forEach((final PaeGastos paeGasto) -> gastosDTO.add(paeGastosToGastosDTO(paeGasto)));
    return gastosDTO;
  }
}

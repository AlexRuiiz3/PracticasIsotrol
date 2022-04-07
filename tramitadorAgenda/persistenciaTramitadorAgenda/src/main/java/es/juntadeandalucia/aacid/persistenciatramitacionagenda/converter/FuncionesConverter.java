package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.FuncionesDTO;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Funcion;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeFunciones;

public class FuncionesConverter {


  public static FuncionesDTO convertPaeFuncionesToFuncionesDTO(PaeFunciones paeFunciones) {
    FuncionesDTO funcionesDTO = new FuncionesDTO();
    funcionesDTO.setFuncTxDescripcion(paeFunciones.getFuncion());

    switch(paeFunciones.getFuncion()) {
    case ConstantesTramitacion.FUNCION_COFINANCIADORA_DESCRIPCION:
      funcionesDTO.setFuncTxCodigo(ConstantesTramitacion.FUNCION_COFINANCIADORA_CODIGO);
      break;
    case ConstantesTramitacion.FUNCION_AGRUPACION_DESCRIPCION:
      funcionesDTO.setFuncTxCodigo(ConstantesTramitacion.FUNCION_AGRUPACION_CODIGO);
      break;
    case ConstantesTramitacion.FUNCION_REPRESENTANTE_DESCRIPCION:
      funcionesDTO.setFuncTxCodigo(ConstantesTramitacion.FUNCION_REPRESENTANTE_CODIGO);
      break;
    case ConstantesTramitacion.FUNCION_OTROS_DESCRIPCION:
      funcionesDTO.setFuncTxCodigo(ConstantesTramitacion.FUNCION_OTROS_CODIGO);
      break;
    default:
      funcionesDTO.setFuncTxCodigo("");
    }
    return funcionesDTO;
  }

  public static FuncionesDTO convertFuncionesToFuncionesDTO(Funcion funcion) {
    FuncionesDTO funcionesDTO = new FuncionesDTO();
    funcionesDTO.setFuncTxDescripcion(funcion.getFuncTxDescripcion());
    funcionesDTO.setFuncTxCodigo(funcion.getFuncTxCodigo());
    return funcionesDTO;
  }

  public static Funcion convertFuncionesDTOToFunciones(FuncionesDTO funcionesDTO) {
    Funcion funcion = new Funcion();
    funcion.setFuncTxDescripcion(funcionesDTO.getFuncTxDescripcion());
    funcion.setFuncTxCodigo(funcionesDTO.getFuncTxCodigo());
    return funcion;
  }
}

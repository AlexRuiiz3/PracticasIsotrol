package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.Map;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.plataforma.comunes.excepciones.BusinessException;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;

public interface IRelacionProvisionalService {

  Map<String, Object> generarPropuestaBeneficiario(UsuarioWeb usuarioSesion, boolean isResolProv, boolean isUniv, boolean isConv) throws TramitacionException;

  Map<String, Object> generarRelacionProvisional(UsuarioWeb usuarioSesion, boolean isResolProv, boolean isUniv, boolean isConv)
      throws NumberFormatException, BusinessException, TramitacionException;
}

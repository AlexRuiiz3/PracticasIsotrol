package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service;

import java.util.Map;

import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.plataforma.comunes.excepciones.BusinessException;
import es.juntadeandalucia.plataforma.web.UsuarioWeb;

public interface IConcesionDefinitivaService {

	public Map<String, Object> generarListadoConcesionDefinitiva(final UsuarioWeb usuarioSesion,boolean isUniv) throws TramitacionException, NumberFormatException, BusinessException;
	
	public Map<String, Object> generarConcesionDefinitiva(final UsuarioWeb usuarioSesion,boolean isUniv) throws TramitacionException;
}

package es.juntadeandalucia.aacid.otrasopciones.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;

import es.juntadeandalucia.aacid.otrasopciones.service.IPermisosService;
import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;

/**
 * Implementación del servicio de permisos
 *
 * @author Isotrol
 */
public class PermisosServiceImpl implements IPermisosService {

  @Override
  public boolean tienePerfil(final List<Perfil> listaPerfiles, final List<String> perfilesATener) {
    Optional<Perfil> perfil = Optional.empty();
    if (CollectionUtils.isNotEmpty(listaPerfiles)) {
      perfil = listaPerfiles.stream().filter(p -> perfilesATener.contains(p.getNombre())).findFirst();
    }
    return perfil.isPresent();
  }

  @Override
  public boolean tienePerfil(final List<Perfil> listaPerfiles, final String strPerfil) {
    Optional<Perfil> perfil = Optional.empty();
    if (CollectionUtils.isNotEmpty(listaPerfiles)) {
      perfil = listaPerfiles.stream().filter(p -> strPerfil.contains(p.getNombre())).findFirst();
    }
    return perfil.isPresent();
  }

}

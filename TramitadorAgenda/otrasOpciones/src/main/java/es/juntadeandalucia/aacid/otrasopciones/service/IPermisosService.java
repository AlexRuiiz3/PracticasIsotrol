/**
 *
 */
package es.juntadeandalucia.aacid.otrasopciones.service;

import java.util.List;

import es.juntadeandalucia.plataforma.visibilidad.perfil.Perfil;

/**
 * Servicio para comprobar los permisos
 *
 * @author Isotrol
 *
 */
public interface IPermisosService {

  /**
   * Método para comprobar que tiene el perfil necesario
   *
   * @param listaPerfiles
   * @param perfilesATener
   * @return
   */
  boolean tienePerfil(List<Perfil> listaPerfiles, List<String> perfilesATener);

  /**
   * Método para comprobar que tiene el perfil necesario
   *
   * @param listaPerfiles
   * @param perfilATener
   * @return
   */
  boolean tienePerfil(List<Perfil> listaPerfiles, String perfilATener);
}

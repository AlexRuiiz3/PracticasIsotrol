package es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.AbstractEntidad;

public class CustomHibernateDaoSupport {

  /**
   * Atributo EntityManager.
   */
  @PersistenceContext(unitName = "persistenciaTramitadorAgenda")
  private EntityManager entityManager;

  @PersistenceContext(unitName = "persistenciaTramitadorAgendaAntiguo")
  private EntityManager entityManagerAntiguo;
  /**
   * @return the entityManager
   */
  public final EntityManager getEntityManager() {
    return entityManager;
  }

  /**
   * @return the entityManagerAntiguo
   */
  public final EntityManager getEntityManagerAntiguo() {
    return entityManagerAntiguo;
  }
  
  /**
   * Método que obtiene una entidad.
   * 
   * @param clazz
   *          clase de la entidad
   * @param id
   *          de la entidad
   * @return AbstractEntidad tipo de entidad que devuelve
   */
  public final AbstractEntidad getEntidadById(final Class<? extends AbstractEntidad> clazz, final Long id) {

    return getEntityManager().find(clazz, id);
  }
  
  /**
   * Método que obtiene una entidad.
   * 
   * @param clazz
   *          clase de la entidad
   * @param id
   *          de la entidad
   * @return AbstractEntidad tipo de entidad que devuelve
   */
  public final AbstractEntidad getEntidadAntiguaById(final Class<? extends AbstractEntidad> clazz, final Long id) {

    return getEntityManagerAntiguo().find(clazz, id);
  }

  /**
   * @param entidad
   *          que se va a eliminar
   */
  public final void deleteEntidad(final AbstractEntidad entidad) {

    getEntityManager().remove(entidad);

  }
  
  /**
   * @param entidad
   *          que se va a eliminar
   */
  public final void deleteEntidadAntigua(final AbstractEntidad entidad) {

    getEntityManagerAntiguo().remove(entidad);

  }

  /**
   * Crea o actualiza una entidad.
   * 
   * @param entidad
   *          para crear en BD
   * @return AbstractEntidad
   */
  public final AbstractEntidad createOrUpdateEntidad(final AbstractEntidad entidad) {
    return getEntityManager().merge(entidad);

  }

  
  /**
   * Crea o actualiza una entidad.
   * 
   * @param entidad
   *          para crear en BD
   * @return AbstractEntidad
   */
  public final AbstractEntidad createOrUpdateEntidadAntigua(final AbstractEntidad entidad) {
    return getEntityManagerAntiguo().merge(entidad);

  }
}

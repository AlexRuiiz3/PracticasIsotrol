package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad;

import java.io.Serializable;

import javax.persistence.Version;

public abstract class AbstractEntidad implements Serializable {

  /**
   * serialVersion.
   */
  private static final long serialVersionUID = 1770804374233830363L;

  /** Version for JPA. */
  @Version
  private Integer version;

  /**
   * @return the version
   */
  public final Integer getVersion() {
    return version;
  }

  /**
   * @param versionN
   *          the version to set
   */
  public final void setVersion(final Integer versionN) {
    this.version = versionN;
  }

}

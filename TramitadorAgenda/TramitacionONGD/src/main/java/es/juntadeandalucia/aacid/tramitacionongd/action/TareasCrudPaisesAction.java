package es.juntadeandalucia.aacid.tramitacionongd.action;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Pais;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaisService;
import es.juntadeandalucia.plataforma.actions.BaseAction;

public class TareasCrudPaisesAction extends BaseAction {

  private static final long serialVersionUID = -4530874376154678835L;

  private final transient Logger log = Logger.getLogger(TareasConvocatoriaAction.class);

  private transient IPaisService paisService;

  private transient List<Pais> listadoPaises;

  private transient Pais paisSeleccionado;

  private String idPais;// Parametro que llega de la web siempre como string

  private String datosPais; // Datos de un pais que son recogidos desde un form

  public String getIndexPaises() {
    return SUCCESS;
  }

  public String obtenerPaisesPorAnho() {
    String result = SUCCESS;
    try {
      log.info("Obteniendo paises por año");
      listadoPaises = paisService.obtenerPaisesPorAnho(2022);
    } catch (Exception e) {
      log.error(" Error obteniendo los paises por año. Inf error: " + e.getMessage());
      result = ERROR;
    }
    return result;
  }

  public String cargarPais() {
    String result = SUCCESS;
    try {
      log.info("Obteniendo el pais " + idPais);
      paisSeleccionado = paisService.obtenerPais(Long.parseLong(idPais));
    } catch (Exception e) {
      log.error("Error en la obtencion del pais " + idPais);
      result = ERROR;
    }
    return result;
  }

  public String crearPais() {
    String result = SUCCESS;
    try {
      log.error("Instanciando un pais con los siguiente datos de la vista: " + datosPais);
      Pais pais = instanciarPais();
      pais.setIdPais((long) 0);
      log.error("Guardando o actualizando el pais(Datos del pais: " + datosPais + ")");
      paisService.guardarOActualizarPais(pais);
    } catch (Exception e) {
      log.error("Error guardando o actualizando el pais(Datos del pais: " + datosPais + ")");
      result = ERROR;
    }
    return result;
  }

  public String actualizarPais() {
    String result = SUCCESS;
    try {
      log.error("Instanciando un pais con los siguiente datos de la vista: " + datosPais);
      Pais pais = instanciarPais();
      pais.setIdPais(Long.parseLong(idPais));
      log.error("Guardando o actualizando el pais(Datos del pais: " + datosPais + ")");
      paisService.guardarOActualizarPais(pais);
    } catch (Exception e) {
      log.error("Error guardando o actualizando el pais(Datos del pais: " + datosPais + ")");
      result = ERROR;
    }
    return result;
  }

  public String eliminarPais() {
    String result = SUCCESS;
    try {
      log.error("Eliminando el pais con id: " + idPais);
      paisService.eliminarPais(Long.parseLong(idPais));
    } catch (Exception e) {
      log.error("Error en la eliminacion del pais " + idPais);
      result = ERROR;
    }
    return result;
  }

  public List<Pais> getListadoPaises() {
    return listadoPaises;
  }

  public void setListadoPaises(List<Pais> listadoPaises) {
    this.listadoPaises = listadoPaises;
  }

  public void setPaisService(IPaisService paisService) {
    this.paisService = paisService;
  }

  public String getIdPais() {
    return idPais;
  }

  public void setIdPais(String idPais) {
    this.idPais = idPais;
  }

  public Pais getPaisSeleccionado() {
    return paisSeleccionado;
  }

  public void setPaisSeleccionado(Pais paisSeleccionado) {
    this.paisSeleccionado = paisSeleccionado;
  }

  public String getDatosPais() {
    return datosPais;
  }

  public void setDatosPais(String datosPais) {
    this.datosPais = datosPais;
  }

  private Pais instanciarPais() {
    Pais pais = new Pais();
    try {
      String[] informacion = datosPais.split("=");
      pais.setNombre(informacion[1].split("&")[0]);
      pais.setCodigo(informacion[2].split("&")[0]);
      pais.setAnio(Integer.parseInt(informacion[3].split("&")[0]));
      pais.setPuntuacion(new BigDecimal(informacion[4]));
    } catch (Exception e) {
      log.error("Error instanciando un pais a partir de los siguientes datos: " + datosPais);
    }
    return pais;
  }

}

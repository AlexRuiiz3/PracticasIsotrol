package com.isotrol.action;

import com.opensymphony.xwork2.ActionSupport;

public class CocheAction extends ActionSupport {

  private static final long serialVersionUID = -6384094151675764347L;

  private String nombreCoche;

  public String verCoches() {
    int aleatorio = (int) (Math.random() * 6);
    nombreCoche = "Seat";
    return aleatorio != 1 ? SUCCESS : ERROR;
  }

  public String guardarCoche() {
    int aleatorio = (int) (Math.random() * 2);
    nombreCoche = "Ford";
    return aleatorio == 1 ? SUCCESS : ERROR;
  }

  public String getNombreCoche() {
    return nombreCoche;
  }

  public void setNombreCoche(String nombreCoche) {
    this.nombreCoche = nombreCoche;
  }

}

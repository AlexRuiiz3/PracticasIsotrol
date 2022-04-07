package es.juntadeandalucia.aacid.comuntramitacion.service;

import java.util.Date;
import java.util.List;

public interface IFestivosAACIDService {

  List<Date> obtenerfestivos(int anio, String tipoFestivo);

}

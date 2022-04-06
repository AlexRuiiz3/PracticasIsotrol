package es.juntadeandalucia.aacid.tramitacionuniv.utils;

import java.util.Comparator;

import es.juntadeandalucia.aacid.comuntramitacion.dto.EntidadesContribucionesDTO;

public class ComparadorEntidadContribuciones implements Comparator<Object>{
  
  public int compare(Object o1, Object o2) {
    if(o1 instanceof EntidadesContribucionesDTO && o2 instanceof EntidadesContribucionesDTO){
      EntidadesContribucionesDTO e1 = (EntidadesContribucionesDTO) o1;
      EntidadesContribucionesDTO e2 = (EntidadesContribucionesDTO) o2;
      if(e1.getDescripcion().equals("AACID")){
        return -1;
      }
      if(e2.getDescripcion().equals("AACID")){
        return 1;
      }
      if(e1.getDescripcion().equals("Solicitante")) {
        return -1;
      }
      if(e2.getDescripcion().equals("Solicitante")) {
        return 1;
      }
      if(e1.getDescripcion().equals("Beneficiaria")) {
        return -1;
      }
      if(e2.getDescripcion().equals("Beneficiaria")) {
        return 1;
      }
      return e1.getDescripcion().compareToIgnoreCase(e2.getDescripcion());
      
      
    }
    return 0;
  }
}

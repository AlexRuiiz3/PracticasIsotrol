package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.HistoricoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesFecha;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Historico;

/**
 * historico converter.
 * 
 * @author isotrol.
 *
 */
public class HistoricoConverter {

  /**
   * Default constructor.
   */
  private HistoricoConverter() {

  }

  public static Historico convertirDtoToDao(String numExp, String usuario) {
    Historico historico = new Historico();
    historico.setHisXExpe(numExp);
    historico.setHisUsu(usuario);
    historico.setHisFec(new Date());
    return historico;
  }

  public static List<HistoricoDTO> convertirListToDto(List<Historico> historicos) {
    List<HistoricoDTO> listaHistoricos = new ArrayList<>();

    for (Historico his : historicos) {
      listaHistoricos.add(convertToDto(his));
    }
    return listaHistoricos;
  }

  private static HistoricoDTO convertToDto(Historico historico) {
    HistoricoDTO historicoDTO = new HistoricoDTO();
    if (null != historico) {
      historicoDTO.setFechaAlta(UtilidadesFecha.parseDateToString(historico.getHisFec()));
      historicoDTO.setNumExpediente(historico.getHisXExpe());
      historicoDTO.setUsuarioWeb(historico.getHisUsu());
    }
    return historicoDTO;
  }

}

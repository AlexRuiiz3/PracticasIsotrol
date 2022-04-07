package es.juntadeandalucia.aacid.comuntramitacion.service;

import java.text.ParseException;
import java.util.List;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ConvocatoriaDTO;

public interface IValidacionConvocatoriasService {

	 List<String> validarDatosConvocatoria(ConvocatoriaDTO convocatoriaDTO) throws ParseException;
}

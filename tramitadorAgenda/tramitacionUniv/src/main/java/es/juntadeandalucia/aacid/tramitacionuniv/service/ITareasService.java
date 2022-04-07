package es.juntadeandalucia.aacid.tramitacionuniv.service;
import es.juntadeandalucia.plataforma.service.usuarios.IUsuario;
import trewa.bd.tpo.TpoPK;

public interface ITareasService {
	void finalizarTarea(String etiquetaTarea, TpoPK idExpediente, TpoPK idFase, IUsuario usuario) throws Exception;


}

package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import es.juntadeandalucia.aacid.comuntramitacion.dto.ContraparteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.EntidadesContribucionesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.ContraparteConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IContrapartesDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Contrapartes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeSolicitudes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IContraparteService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeSolicitudService;

public class ContraparteServiceImpl implements IContraparteService {
  private final Logger log = Logger.getLogger(ContraparteServiceImpl.class);

  private IContrapartesDao contrapartesDao;

  private IPaeSolicitudService paeSolicitudService;

  @Override
  public List<Contrapartes> obtenerContrapartesBySolicitud(final Long idSolicitud) {
    log.debug("Init getContrapartesBySolicitud");
    Object[] contrapartesObject = null;
    final List<Contrapartes> contrapartes = new ArrayList<>();
    try {
      contrapartesObject = contrapartesDao.obtenerContrapartesBySolicitud(idSolicitud).toArray();
      for (final Object objetoContraparte : contrapartesObject) {
        final Object[] datosContraparteObject = (Object[]) objetoContraparte;
        final String[] stringArray = Arrays.copyOf(datosContraparteObject, datosContraparteObject.length, String[].class);
        final Contrapartes contraparte = new Contrapartes();
        contraparte.setContContraparte(stringArray[0]);
        contraparte.setContLocalidad(stringArray[1]);
        contraparte.setContPais(stringArray[2]);
        contrapartes.add(contraparte);
      }
    } catch (final TramitacionException e) {
      log.error(e.getMessage(), e);
    }
    return contrapartes;

  }

  @Override
  public int saveOrUpdateContraparte(final Contrapartes contrapartes, final Long idSolicitud) throws TramitacionException {
    log.debug("Init saveOrUpdateContraparte");
    return contrapartesDao.saveOrUpdateContraparte(contrapartes, idSolicitud);
  }

  @Override
  public void obtenerContrapartes(final SolicitudDatosGeneralesDTO solicitud, final List<EntidadesContribucionesDTO> listaEntidades)
      throws TramitacionException {
    // PRIMERO, COMPROBAMOS QUE LA SOLICITUD ACTUAL TENGA CONTRAPARTES EN LA NUEVA BBDD
    final List<Contrapartes> contrapartes = obtenerContrapartesBySolicitud(solicitud.getIdSolicitud());
    List<ContraparteDTO> listaContrapartesDTO = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(contrapartes)) {
      listaContrapartesDTO = ContraparteConverter.convertirListContraparteToListDto(contrapartes);
      // SEGUNDO, SI NO TIENE, DEBEMOS OBTENERLAS DE LA SOLICITUD DEL SISTEMA ANTIGUO
    } else {
      final PaeSolicitudes paeSolicitud = paeSolicitudService.obtenerSolicitudExpByCodigo(solicitud.getTxCodidentificativo());
      if (paeSolicitud != null && CollectionUtils.isNotEmpty(paeSolicitud.getPaeContrapartesByIdSolicitud())) {
        listaContrapartesDTO = ContraparteConverter.convertirListPaeContraparteToListDto(paeSolicitud.getPaeContrapartesByIdSolicitud());
      }
    }
    EntidadesContribucionesDTO nuevaEntidad;
    for (final ContraparteDTO contraparteDTO : listaContrapartesDTO) {
      nuevaEntidad = new EntidadesContribucionesDTO(contraparteDTO.getContraparte(), contraparteDTO.getContraparte(), "Contraparte");
      nuevaEntidad.setContraparteDTO(contraparteDTO);
      nuevaEntidad.setValoresAI(new ArrayList<>());
      nuevaEntidad.setValoresAII(new ArrayList<>());
      listaEntidades.add(nuevaEntidad);
    }
  }

  public void setPaeSolicitudService(final IPaeSolicitudService paeSolicitudService) {
    this.paeSolicitudService = paeSolicitudService;
  }

  public void setContrapartesDao(final IContrapartesDao contrapartesDao) {
    this.contrapartesDao = contrapartesDao;
  }
}

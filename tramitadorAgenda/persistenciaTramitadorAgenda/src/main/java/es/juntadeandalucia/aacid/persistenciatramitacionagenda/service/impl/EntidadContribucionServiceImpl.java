package es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ConContraparteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ConOtrasAportacionesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ContraparteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ContribucionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.EntidadParticipanteDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.EntidadesContribucionesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.GastosContribucionDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.ValidaPresupuestoDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesNumero;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.ContraparteConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter.EntidadesParticipantesConverter;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.EntidadesParticipantes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.sistemaantiguo.PaeSolicitudes;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IContribucionesService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IEntidadContribucionService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IEntidadesParticipantesService;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.service.IPaeSolicitudService;

public class EntidadContribucionServiceImpl implements IEntidadContribucionService {

  private IEntidadesParticipantesService entidadesParticipantesService;

  private IPaeSolicitudService paeSolicitudService;

  private IContribucionesService contribucionesService;

  @Override
  public void obtenerEntidadesParticipantes(final SolicitudDatosGeneralesDTO solicitud, final List<EntidadesContribucionesDTO> listaEntidades)
      throws TramitacionException {
    // PRIMERO, COMPROBAMOS QUE LA SOLICITUD ACTUAL TENGA ENTIDADES PARTICIPANTES EN LA NUEVA BBDD
    final List<EntidadesParticipantes> entidadesParticipantes = entidadesParticipantesService
        .obtenerEntidadesParticipantesBySolicitud(solicitud.getIdSolicitud());
    List<EntidadParticipanteDTO> entidadParticipanteDTO = null;
    if (CollectionUtils.isNotEmpty(entidadesParticipantes)) {
      entidadParticipanteDTO = EntidadesParticipantesConverter.convertListEntidadesParticipantesToListDTO(entidadesParticipantes);
    } else {
      // SEGUNDO, SI NO TIENE, DEBEMOS OBTENERLAS DE LA SOLICITUD DEL SISTEMA ANTIGUO
      final PaeSolicitudes paeSolicitud = paeSolicitudService.obtenerSolicitudExpByCodigo(solicitud.getTxCodidentificativo());
      if (paeSolicitud != null && CollectionUtils.isNotEmpty(paeSolicitud.getPaeEntidadesParticipanteses())) {
        entidadParticipanteDTO = EntidadesParticipantesConverter.convertListPaeEntidadesParticipantesToListDTO(paeSolicitud.getPaeEntidadesParticipanteses(),
            solicitud.getIdSolicitud());
        entidadesParticipantesService.saveOrUpdateEntidadesParticipantes(entidadParticipanteDTO);
      }
    }

    EntidadesContribucionesDTO nuevaEntidad;
    if (CollectionUtils.isNotEmpty(entidadParticipanteDTO)) {
      for (final EntidadParticipanteDTO entidadParticipante : entidadParticipanteDTO) {
        if (entidadParticipante.getFuncion() != null
            && (entidadParticipante.getFuncion().getFuncTxCodigo().equals(ConstantesTramitacion.FUNCION_COFINANCIADORA_CODIGO)
                || entidadParticipante.getFuncion().getFuncTxCodigo().equals(ConstantesTramitacion.FUNCION_AGRUPACION_CODIGO))) {
          nuevaEntidad = new EntidadesContribucionesDTO(entidadParticipante.getNombre(), entidadParticipante.getNombre(),
              entidadParticipante.getTipoEntidadParticipante().getDescripcion());
          nuevaEntidad.setEntidadesParticipantes(entidadParticipante);
          nuevaEntidad.setValoresAI(new ArrayList<>());
          nuevaEntidad.setValoresAII(new ArrayList<>());
          listaEntidades.add(nuevaEntidad);
        }
      }
    }
  }

  @Override
  public boolean esEntidadPublica(final EntidadesContribucionesDTO cont) {
    // En primer lugar, hacemos la comprobacion con
    if (cont.getEntidadesParticipantes() != null) {
      return esEntidadPublica(cont.getEntidadesParticipantes());
    } else if (cont.getContraparteDTO() != null) {
      return esEntidadPublica(cont.getContraparteDTO());
    } else if (cont.getDescripcion().equals(ConstantesTramitacion.SISTEMA_ACCID)) {
      return true;
    }
    return false;
  }

  private boolean esEntidadPublica(final ContraparteDTO contrapartesDTO) {
    return contrapartesDTO.getTipoEntidadContraparteDTO().getDescripcionEntidadContraparte().equals(ConstantesTramitacion.TIPO_ENTIDAD_PUBLICA);
  }

  private boolean esEntidadPublica(final EntidadParticipanteDTO entidadParticipanteDTO) {
    return (StringUtils.equals(entidadParticipanteDTO.getTipoEntidadParticipante().getCodigo(), ConstantesTramitacion.FUNCION_COFINANCIADORA_CODIGO)
        || StringUtils.equals(entidadParticipanteDTO.getTipoEntidadParticipante().getCodigo(), ConstantesTramitacion.FUNCION_AGRUPACION_CODIGO));
  }

  @Override
  public void estableceContribuciones(final SolicitudDatosGeneralesDTO solicitud, final EntidadesContribucionesDTO nuevaAacid,
      final EntidadesContribucionesDTO nuevaSol, final ValidaPresupuestoDTO validaPresupuestoDTO) throws TramitacionException {
    final List<ContribucionDTO> listaContribucionesDTO = new ArrayList<>();
    contribucionesService.obtenerContribuciones(listaContribucionesDTO, solicitud);

    validaPresupuestoDTO.setContribuciones(listaContribucionesDTO);
    for (final ContribucionDTO contribucionDTO : listaContribucionesDTO) {
      establecerContribucionesAACID(nuevaAacid, contribucionDTO);
      establecerContribucionesSolicitante(nuevaSol, contribucionDTO);

      if (CollectionUtils.isNotEmpty(contribucionDTO.getListaOtrasAportaciones())) {
        establecerEntidadesOtrasAportaciones(contribucionDTO, validaPresupuestoDTO.getListaEntidades());
      } else {
        establecerContribucionesEntidadesParticipantes(contribucionDTO, solicitud, validaPresupuestoDTO.getListaEntidades());
      }
      if (CollectionUtils.isNotEmpty(contribucionDTO.getListaContrapartesDTO())) {
        establecerContribucionesConContraparte(contribucionDTO, solicitud, validaPresupuestoDTO.getListaEntidades());
      } else {
        establecerContribucionesContrapartesSolicitud(contribucionDTO, solicitud, validaPresupuestoDTO.getListaEntidades());
      }
    }
  }

  private void establecerContribucionesSolicitante(final EntidadesContribucionesDTO nuevaSol, final ContribucionDTO contribucionDTO) {
    final GastosContribucionDTO nuevoGastoSol = new GastosContribucionDTO();
    nuevoGastoSol.setGasto(contribucionDTO.getGastos());
    nuevoGastoSol.setIdGastoContribucion(contribucionDTO.getIdContribucion());
    if (contribucionDTO.getSolicitante() != null) {
      nuevoGastoSol.setValor(contribucionDTO.getSolicitante());
      nuevoGastoSol.setValorTitle(UtilidadesNumero.bigDecimalToStringConverterTwoDecimals(contribucionDTO.getSolicitante()));
      nuevoGastoSol.setValorNoValidadoTitle(UtilidadesNumero.bigDecimalToStringConverterTwoDecimals(contribucionDTO.getSolicitanteNoValidada()));
      nuevoGastoSol.setValorNoValidado(contribucionDTO.getSolicitanteNoValidada());
      nuevoGastoSol.setValorValidado(contribucionDTO.getSolicitanteValidada());
      nuevoGastoSol.setValorValidadoTitle(UtilidadesNumero.bigDecimalToStringConverterTwoDecimals(contribucionDTO.getSolicitanteValidada()));
    } else {
      nuevoGastoSol.setValor(BigDecimal.ZERO);
      nuevoGastoSol.setValorNoValidado(BigDecimal.ZERO);
      nuevoGastoSol.setValorValidado(BigDecimal.ZERO);
      nuevoGastoSol.setValorTitle("0,00");
      nuevoGastoSol.setValorValidadoTitle("0,00");
      nuevoGastoSol.setValorNoValidadoTitle("0,00");
    }
    if (contribucionDTO.getGastos().getCodigo().contains(ConstantesTramitacion.GASTOS_AI)) {
      nuevaSol.getValoresAI().add(nuevoGastoSol);
    } else {
      nuevoGastoSol.setDisabled(contribucionDTO.getGastos().getCodigo().equals(ConstantesTramitacion.GASTOS_B));
      nuevaSol.getValoresAII().add(nuevoGastoSol);
    }
  }

  private void establecerContribucionesAACID(final EntidadesContribucionesDTO nuevaSol, final ContribucionDTO contribucionDTO) {
    final GastosContribucionDTO nuevoGastoSol = new GastosContribucionDTO();
    nuevoGastoSol.setGasto(contribucionDTO.getGastos());
    nuevoGastoSol.setIdGastoContribucion(contribucionDTO.getIdContribucion());
    if (contribucionDTO.getAacid() != null) {
      nuevoGastoSol.setValor(contribucionDTO.getAacid());
      nuevoGastoSol.setValorTitle(UtilidadesNumero.bigDecimalToStringConverterTwoDecimals(contribucionDTO.getAacid()));
      nuevoGastoSol.setValorNoValidadoTitle(UtilidadesNumero.bigDecimalToStringConverterTwoDecimals(contribucionDTO.getAacidNoValidada()));
      nuevoGastoSol.setValorNoValidado(contribucionDTO.getAacidNoValidada());
      nuevoGastoSol.setValorValidado(contribucionDTO.getAacidValidada());
      nuevoGastoSol.setValorValidadoTitle(UtilidadesNumero.bigDecimalToStringConverterTwoDecimals(contribucionDTO.getAacidValidada()));
    } else {
      nuevoGastoSol.setValor(BigDecimal.ZERO);
      nuevoGastoSol.setValorNoValidado(BigDecimal.ZERO);
      nuevoGastoSol.setValorValidado(BigDecimal.ZERO);
      nuevoGastoSol.setValorTitle("0,00");
      nuevoGastoSol.setValorValidadoTitle("0,00");
      nuevoGastoSol.setValorNoValidadoTitle("0,00");
    }

    if (contribucionDTO.getGastos().getCodigo().contains(ConstantesTramitacion.GASTOS_AI)) {
      nuevaSol.getValoresAI().add(nuevoGastoSol);
    } else {
      nuevaSol.getValoresAII().add(nuevoGastoSol);
    }
  }

  @Override
  public void establecerEntidadesOtrasAportaciones(final ContribucionDTO contribucionDTO, final List<EntidadesContribucionesDTO> listaEntidades) {
    for (final ConOtrasAportacionesDTO conOtrasAportacionesDTO : contribucionDTO.getListaOtrasAportaciones()) {
      for (final EntidadesContribucionesDTO entidadesContribucionesDTO : listaEntidades) {
        if (entidadesContribucionesDTO.getEntidadesParticipantes() != null && entidadesContribucionesDTO.getEntidadesParticipantes().getFuncion() != null
            && (entidadesContribucionesDTO.getEntidadesParticipantes().getFuncion().getFuncTxCodigo()
                .equalsIgnoreCase(ConstantesTramitacion.FUNCION_COFINANCIADORA_CODIGO)
                || entidadesContribucionesDTO.getEntidadesParticipantes().getFuncion().getFuncTxCodigo()
                    .equals(ConstantesTramitacion.FUNCION_AGRUPACION_CODIGO))
            && entidadesContribucionesDTO.getEntidadesParticipantes().getId().equals(conOtrasAportacionesDTO.getEntidadesParticipantesDTO().getId())) {

          final GastosContribucionDTO nuevoGasto = new GastosContribucionDTO();
          nuevoGasto.setGasto(contribucionDTO.getGastos());
          aniadeValoresContribucionConOtrasAportaciones(contribucionDTO, conOtrasAportacionesDTO, nuevoGasto, entidadesContribucionesDTO);
        }
      }
    }
  }

  private void aniadeValoresContribucionConOtrasAportaciones(final ContribucionDTO contribucionDTO, final ConOtrasAportacionesDTO conOtrasAportacionesDTO,
      final GastosContribucionDTO nuevoGasto, final EntidadesContribucionesDTO entidadesContribucionesDTO) {
    nuevoGasto.setValor(conOtrasAportacionesDTO.getContribucion());
    nuevoGasto.setValorTitle(UtilidadesNumero.bigDecimalToStringConverterTwoDecimals(conOtrasAportacionesDTO.getContribucion()));
    nuevoGasto.setValorNoValidado(conOtrasAportacionesDTO.getContribucionNoValidada());
    nuevoGasto.setValorValidado(conOtrasAportacionesDTO.getContribucionNoValidada());
    nuevoGasto.setValorValidado(conOtrasAportacionesDTO.getContribucionValidada());
    if (conOtrasAportacionesDTO.getContribucionDTO().getGastos().getCodigo().contains(ConstantesTramitacion.GASTOS_AI)) {
      entidadesContribucionesDTO.getValoresAI().add(nuevoGasto);
    } else {
      if (contribucionDTO.getGastos().getCodigo().equals(ConstantesTramitacion.GASTOS_B) && !esEntidadPublica(entidadesContribucionesDTO)) {
        nuevoGasto.setDisabled(true);
      }
      entidadesContribucionesDTO.getValoresAII().add(nuevoGasto);
    }
  }

  @Override
  public void establecerContribucionesEntidadesParticipantes(final ContribucionDTO contribucionDTO, final SolicitudDatosGeneralesDTO solicitud,
      final List<EntidadesContribucionesDTO> listaEntidades) throws TramitacionException {
    final List<EntidadParticipanteDTO> listaEntidadesParticipantesDTO = obtenerEntidadesParticipantes(solicitud);
    for (final EntidadParticipanteDTO entidadParticipanteDTO : listaEntidadesParticipantesDTO) {
      for (final EntidadesContribucionesDTO con : listaEntidades) {
        // Existe entidades participantes y su función es cofinanciadora o participante a agrupación y se encuentra entre las asociadas a
        // la solicitud
        if (con.getEntidadesParticipantes() != null && con.getEntidadesParticipantes().getFuncion() != null
            && (con.getEntidadesParticipantes().getFuncion().getFuncTxCodigo().equals(ConstantesTramitacion.FUNCION_COFINANCIADORA_CODIGO)
                || con.getEntidadesParticipantes().getFuncion().getFuncTxCodigo().equals(ConstantesTramitacion.FUNCION_AGRUPACION_CODIGO))
            && con.getEntidadesParticipantes().getId().equals(entidadParticipanteDTO.getId())) {
          final GastosContribucionDTO nuevoGasto = new GastosContribucionDTO();
          nuevoGasto.setGasto(contribucionDTO.getGastos());
          nuevoGasto.setIdGastoContribucion(contribucionDTO.getIdContribucion());
          nuevoGasto.setValorNoValidadoTitle("0,00");
          nuevoGasto.setValorTitle("0,00");
          nuevoGasto.setValorValidadoTitle("0,00");
          if (contribucionDTO.getGastos().getCodigo().contains(ConstantesTramitacion.GASTOS_AI)) {
            con.getValoresAI().add(nuevoGasto);
          } else {
            if (contribucionDTO.getGastos().getCodigo().equals(ConstantesTramitacion.GASTOS_B) && !esEntidadPublica(con)) {
              nuevoGasto.setDisabled(true);
            }
            con.getValoresAII().add(nuevoGasto);
          }
        }
      }
    }
  }

  /**
   * @param solicitud
   * @return
   * @throws TramitacionException
   */
  private List<EntidadParticipanteDTO> obtenerEntidadesParticipantes(final SolicitudDatosGeneralesDTO solicitud) throws TramitacionException {
    final List<EntidadesParticipantes> entidadesParticipantes = entidadesParticipantesService
        .obtenerEntidadesParticipantesBySolicitud(solicitud.getIdSolicitud());
    List<EntidadParticipanteDTO> listaEntidadesParticipantesDTO = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(entidadesParticipantes)) {
      listaEntidadesParticipantesDTO = EntidadesParticipantesConverter.convertListEntidadesParticipantesToListDTO(entidadesParticipantes);
    } else {
      final PaeSolicitudes paeSolicitud = paeSolicitudService.obtenerSolicitudExpByCodigo(solicitud.getTxCodidentificativo());
      if (paeSolicitud != null) {
        listaEntidadesParticipantesDTO = EntidadesParticipantesConverter
            .convertListPaeEntidadesParticipantesToListDTO(paeSolicitud.getPaeEntidadesParticipanteses(), solicitud.getIdSolicitud());
      }
    }
    return listaEntidadesParticipantesDTO;
  }

  @Override
  public void establecerContribucionesConContraparte(final ContribucionDTO contribucionDTO, final SolicitudDatosGeneralesDTO solicitud,
      final List<EntidadesContribucionesDTO> listaEntidades) {
    for (final ConContraparteDTO conContraparteDTO : contribucionDTO.getListaContrapartesDTO()) {
      for (final EntidadesContribucionesDTO entidadesContribucionesDTO : listaEntidades) {
        if (entidadesContribucionesDTO.getContraparteDTO() != null
            && entidadesContribucionesDTO.getContraparteDTO().getId().longValue() == conContraparteDTO.getAaciContrapartesByFkEntidad().getId()) {
          final GastosContribucionDTO nuevoGasto = new GastosContribucionDTO();
          nuevoGasto.setGasto(contribucionDTO.getGastos());
          aniadeValoresContribucionesConContraparte(contribucionDTO, conContraparteDTO, nuevoGasto, entidadesContribucionesDTO);
        }
      }
    }
  }

  private void aniadeValoresContribucionesConContraparte(final ContribucionDTO contribucionDTO, final ConContraparteDTO conContraparteDTO,
      final GastosContribucionDTO nuevoGasto, final EntidadesContribucionesDTO entidadesContribucionesDTO) {
    nuevoGasto.setValor(conContraparteDTO.getContribucion());
    nuevoGasto.setValorTitle(UtilidadesNumero.bigDecimalToStringConverterTwoDecimals(conContraparteDTO.getContribucion()));
    nuevoGasto.setValorNoValidado(conContraparteDTO.getContribucionNoValidada());
    nuevoGasto.setValorNoValidadoTitle(UtilidadesNumero.bigDecimalToStringConverterTwoDecimals(conContraparteDTO.getContribucionNoValidada()));
    nuevoGasto.setValorValidado(conContraparteDTO.getContribucionValidada());
    if (conContraparteDTO.getAaciContribucionesByFkContribucion().getGastos().getCodigo().contains(ConstantesTramitacion.GASTOS_AI)) {
      entidadesContribucionesDTO.getValoresAI().add(nuevoGasto);
    } else {
      if (contribucionDTO.getGastos().getCodigo().equals(ConstantesTramitacion.GASTOS_B) && !esEntidadPublica(entidadesContribucionesDTO)) {
        nuevoGasto.setDisabled(true);
      }
      entidadesContribucionesDTO.getValoresAII().add(nuevoGasto);
    }
  }

  @Override
  public void establecerContribucionesContrapartesSolicitud(final ContribucionDTO contribucionDTO, final SolicitudDatosGeneralesDTO solicitud,
      final List<EntidadesContribucionesDTO> listaEntidades) throws TramitacionException {
    List<ContraparteDTO> contraparteDTO = null;
    if (CollectionUtils.isNotEmpty(solicitud.getContrapartes())) {
      contraparteDTO = solicitud.getContrapartes();
    } else {
      final PaeSolicitudes paeSolicitud = paeSolicitudService.obtenerSolicitudExpByCodigo(solicitud.getTxCodidentificativo());
      contraparteDTO = paeSolicitud != null ? ContraparteConverter.convertirListPaeContraparteToListDto(paeSolicitud.getPaeContrapartesByIdSolicitud())
          : new ArrayList<>();
    }
    for (final ContraparteDTO contraparte : contraparteDTO) {
      for (final EntidadesContribucionesDTO entidadContribuciones : listaEntidades) {
        if (entidadContribuciones.getContraparteDTO() != null
            && entidadContribuciones.getContraparteDTO().getId().longValue() == contraparte.getId().longValue()) {
          final GastosContribucionDTO nuevoGasto = new GastosContribucionDTO();
          nuevoGasto.setGasto(contribucionDTO.getGastos());
          if (contribucionDTO.getGastos().getCodigo().contains(ConstantesTramitacion.GASTOS_AI)) {
            entidadContribuciones.getValoresAI().add(nuevoGasto);
          } else {
            if (contribucionDTO.getGastos().getCodigo().equals(ConstantesTramitacion.GASTOS_B) && esEntidadPublica(entidadContribuciones)) {
              nuevoGasto.setDisabled(true);
            }
            entidadContribuciones.getValoresAII().add(nuevoGasto);
          }
        }
      }
    }
  }

  public void setEntidadesParticipantesService(final IEntidadesParticipantesService entidadesParticipantesService) {
    this.entidadesParticipantesService = entidadesParticipantesService;
  }

  public void setPaeSolicitudService(final IPaeSolicitudService paeSolicitudService) {
    this.paeSolicitudService = paeSolicitudService;
  }

  public void setContribucionesService(final IContribucionesService contribucionesService) {
    this.contribucionesService = contribucionesService;
  }

}

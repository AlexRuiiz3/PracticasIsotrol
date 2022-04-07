package es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import es.juntadeandalucia.aacid.comuntramitacion.constantes.ConstantesTramitacion;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO;
import es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDatosGeneralesDTO;
import es.juntadeandalucia.aacid.comuntramitacion.exception.AlegacionesAceptaReformulaException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.AlegacionesException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.RecepcionComunicacionException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.SubsanacionException;
import es.juntadeandalucia.aacid.comuntramitacion.exception.TramitacionException;
import es.juntadeandalucia.aacid.comuntramitacion.utils.UtilidadesBoolean;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.IAgrupacionDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.dao.ISolicitudDao;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.Solicitud;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea.AaciAgrupaciones;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea.AaciTDocPoderAdminJa;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea.AaciTDocPoderOtrAdmin;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea.AaciTPaises;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.modelo.entidad.vea.AaciTSolicitudsubongd;
import es.juntadeandalucia.aacid.persistenciatramitacionagenda.persistence.CustomHibernateDaoSupport;

/**
 * SolicitudDao class.
 *
 * @author isotrol
 *
 */
@Repository
public class SolicitudDao extends CustomHibernateDaoSupport implements ISolicitudDao {

  private static final String ID_EXP_TREWA = "idExpTrewa";
  private static final String ENTREGADA_POR_VEAJA = " entregada por VEAJA";
  private static final String INSERT_DOCS_PODER_JA = "INSERT INTO AACI_T_DOC_PODER_ADMIN_JA (ID_DOC_PODER_ADMIN_JA, ID_SOLICITUD, TX_CONSEJERIA_Y_ORGANO,"
      + " FH_FECHA_PRESENT, TX_PROCED_EMITIO, TX_NOM_DOCUMENTO) VALUES (AACI_SEQ_DOCS_PODER_JA.nextval,?,?,?,?,?)";
  private static final String INSERT_DOCS_PODER_OTR_ADM = "INSERT INTO AACI_T_DOC_PODER_OTR_ADMIN ( ID_DOC_PODER_OTR_ADMIN, ID_SOLICITUD, "
      + "TX_CONSEJERIA_Y_ORGANO, FH_FECHA_PRESENT, TX_PROCED_EMITIO, TX_NOM_DOCUMENTO) VALUES (AACI_SEQ_DOCS_PODER_OTR_ADMIN.nextval,?,?,?,?,?)";
  private IAgrupacionDao agrupacionDao;

  @Override
  public SolicitudDatosGeneralesDTO datosGeneralesSolicitudByIdExpTrewa(final String idExpTrewa) throws TramitacionException {
    try {
      return getEntityManager().createQuery("SELECT NEW es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDatosGeneralesDTO (sol.idSolicitud, "//
          + "CASE WHEN sol.vbTecnico = 1 " //
          + "  THEN true " //
          + "ELSE false END, "//
          + "CASE WHEN sol.vbCoordinador = 1 "//
          + "  THEN true "//
          + "ELSE false END, "//
          + "sol.txDatostitulo, sol.descripcion ,sol.municipio, sol.fhInicio, "//
          + "CASE WHEN sol.lgCheckfinalidad1 = '1' "//
          + "  THEN 'C' "//
          + "WHEN sol.lgCheckfinalidad2 = '1' "//
          + "  THEN 'AH' "//
          + "WHEN sol.lgCheckfinalidad3 = '1' "//
          + "  THEN 'ED' "//
          + "WHEN sol.lgCheckfinalidad4 = '1' "//
          + "  THEN 'F' "//
          + "ELSE '' "//
          + "END AS FINALIDAD, "//
          + "sol.plazo, sol.fhPagoSubvencion, sol.fhPresentacion, sol.txRespApellidos, sol.txRespNombre, "//
          + "sol.txRespNif, sol.nuNumInterno, sol.fhRegistro, "//
          + "CASE WHEN sol.lgRepresentacion = 1 "//
          + "  THEN true "//
          + "ELSE false END, "//
          + "sol.txLugarRegistro, sol.fkPais,sol.txCodidentificativo,sol.txSoli1Email, " //
          + "sol.fhPostegracionComunIni) "//
          + "FROM Solicitud sol WHERE sol.nuNumexpediente =:idExpTrewa", //
          SolicitudDatosGeneralesDTO.class).setParameter(ID_EXP_TREWA, Long.valueOf(idExpTrewa)).getSingleResult();
    } catch (final NoResultException nre) {
      return null;
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }

  @Override
  public Solicitud find(Solicitud solicitud) throws TramitacionException {
    if (solicitud != null) {
      try {
        final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Solicitud> cq = cb.createQuery(Solicitud.class);
        final Root<Solicitud> root = cq.from(Solicitud.class);
        cq.select(root);
        final Predicate conjuncion = cb.conjunction();
        if (solicitud.getIdSolicitud() != null) {
          conjuncion.getExpressions().add(cb.equal(root.get("idSolicitud"), solicitud.getIdSolicitud()));
        }
        if (solicitud.getNuNumexpediente() != null) {
          conjuncion.getExpressions().add(cb.equal(root.get("nuNumexpediente"), solicitud.getNuNumexpediente()));
        }
        cq.where(conjuncion);

        solicitud = getEntityManager().createQuery(cq).getResultList().stream().findFirst().orElse(null);
      } catch (final Exception e) {
        throw new TramitacionException(e.getMessage(), e);
      }
    }
    return solicitud;
  }

  @Override
  public int guardarSolicitudDatosGenerales(final Solicitud solicitud) throws TramitacionException {
    int resultado = 0;
    try {
      resultado = getEntityManager().createNativeQuery("UPDATE AACI_T_SOLICITUDSUBONGD SET " //
          + "NU_NUMINTERNO = ?, " //
          + "FH_PRESENTACION = ?, " //
          + "FH_REGISTRO = ?, " //
          + "LG_REPRESENTACION = ?, " //
          + "FH_INICIO = ?, " //
          + "FH_PAGO_SUBVENCION = ?, " //
          + "VB_TECNICO = ?, " //
          + "VB_COORDINADOR = ?, " //
          + "MUNICIPIO = ?, " //
          + "TX_RESP_APELLIDOS = ?, " //
          + "TX_RESP_NOMBRE = ?, " //
          + "TX_RESP_NIF = ?, " //
          + "FK_PAIS = ?, " //
          + "TX_CODIDENTIFICATIVO = ?, " //
          + "FH_POSTEGRACION_COMUN_INI = ? " //
          + "WHERE ID_SOLICITUD = ?")//
          .setParameter(1, getParameterQuery(solicitud.getNuNumInterno()))//
          .setParameter(2, solicitud.getFhPresentacion() != null ? solicitud.getFhPresentacion() : "")
          .setParameter(3, solicitud.getFhRegistro() != null ? solicitud.getFhRegistro() : "")//
          .setParameter(4, getParameterQuery(solicitud.isLgRepresentacion()))//
          .setParameter(5, solicitud.getFhInicio() != null ? solicitud.getFhInicio() : "")
          .setParameter(6, solicitud.getFhPagoSubvencion() != null ? solicitud.getFhPagoSubvencion() : "")
          .setParameter(7, getParameterQuery(solicitud.isVbTecnico()))//
          .setParameter(8, getParameterQuery(solicitud.isVbCoordinador()))//
          .setParameter(9, getParameterQuery(solicitud.getMunicipio()))//
          .setParameter(10, getParameterQuery(solicitud.getTxRespApellidos()))//
          .setParameter(11, getParameterQuery(solicitud.getTxRespNombre()))//
          .setParameter(12, getParameterQuery(solicitud.getTxRespNif()))//
          .setParameter(13, getParameterQuery(solicitud.getFkPais()))//
          .setParameter(14, getParameterQuery(solicitud.getTxCodidentificativo()))//
          .setParameter(15, solicitud.getFhPostegracionComunIni() != null ? solicitud.getFhPostegracionComunIni() : "")//
          .setParameter(16, getParameterQuery(solicitud.getIdSolicitud())) //
          .executeUpdate();
      return resultado;
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }

  }

  /**
   * obtener solicitud por el id de expediente Trewa.
   *
   * @throws TramitacionException
   */
  @Override
  public SolicitudDTO obtenerSolicitudByIdExp(final String idExpTrewa) throws TramitacionException {
    try {
      return getEntityManager().createQuery("SELECT NEW es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO "
          + "(sol.idSolicitud, sol.txSoli1Nombre, sol.txDatostitulo, sol.txCodidentificativo, sol.txSoli1Numident, sol.fkPais,sol.txSoli1Email, sol.txRepdatos0Ejecucion, sol.txRepdatos1Ejecucion, sol.txRepdatos2Ejecucion) FROM Solicitud sol WHERE sol.nuNumexpediente =:idExpTrewa",
          SolicitudDTO.class).setParameter(ID_EXP_TREWA, Long.valueOf(idExpTrewa)).getSingleResult();
    } catch (final NoResultException nre) {
      return null;
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }

  @Override
  public List<SolicitudDTO> obtenerSolicitudesByTipoProyectoDocumento(final Long idConv, final int tipoProyecto, final boolean isUniv)
      throws TramitacionException {
    List<SolicitudDTO> listaSolicitudes = null;
    String finalidad = "";
    if (isUniv) {
      finalidad = "sol.lgChecktipo" + tipoProyecto;
    } else {
      finalidad = "sol.lgCheckfinalidad" + tipoProyecto;
    }
    try {
      listaSolicitudes = getEntityManager().createQuery(
          "SELECT NEW es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO (sol.idSolicitud, sol.nuNumexpediente, sol.txDatostitulo, sol.txSoli1Poblacion, "
              + "sol.txSoli1Nombre, sol.txRepr1Numident, sol.nuFirm1Importe, sol.txCodidentificativo, sol.plazo) "
              + "FROM Solicitud sol JOIN sol.aaciTConvocatoriasByIdConvocatoria conv WHERE conv.convNuId =:idConv AND " + finalidad + " = true",
          SolicitudDTO.class).setParameter("idConv", idConv).getResultList();
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
    return listaSolicitudes;
  }

  @Override
  public List<SolicitudDTO> obtenerSolicitudesByTipoProyectoYTipoExclusion(final Long idConv, final int tipoProyecto, final Long tipoExclusion,
      final boolean isUniv) throws TramitacionException {
    List<SolicitudDTO> listaSolicitudes = null;
    String finalidad = "";
    if (isUniv) {
      finalidad = "sol.lgChecktipo" + tipoProyecto;
    } else {
      finalidad = "sol.lgCheckfinalidad" + tipoProyecto;
    }
    try {
      listaSolicitudes = getEntityManager().createQuery(
          "SELECT DISTINCT NEW es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO (sol.idSolicitud, sol.nuNumexpediente, sol.txDatostitulo, sol.txSoli1Poblacion, "
              + "sol.txSoli1Nombre, sol.txRepr1Numident, sol.nuFirm1Importe,sol.txCodidentificativo) " + "FROM Solicitud sol "
              + "JOIN sol.aaciTConvocatoriasByIdConvocatoria conv " + "join sol.aaciExclusionesSolicitudsByIdSolicitud es "
              + "join es.aaciExclusionByExclNuId e " + "join e.aaciTipoExclusionByTexcNuId te " + "WHERE conv.convNuId =:idConv AND " + finalidad + " = true "
              + "and te.texcNuTipo =:tipoExclusion",
          SolicitudDTO.class).setParameter("idConv", idConv).setParameter("tipoExclusion", tipoExclusion.intValue()).getResultList();
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
    return listaSolicitudes;
  }

  @Override
  public SolicitudDTO obtenerSolicitudSubsanacion(final Long idSolicitud) throws SubsanacionException {
    SolicitudDTO solicitud = null;
    try {
      final String strQuery = "SELECT NEW es.juntadeandalucia.aacid.comuntramitacion.dto.SolicitudDTO " + "(sol.id, " + "sol.soli1Siglas, "
          + "sol.codigoIdentificativo, " + "sol.soli1Tipovia, " + "sol.soli1Nombrevia, " + "sol.soli1Numero, " + "sol.soli1Letra, " + "sol.soli1Kmvia, "
          + "sol.soli1Bloque, " + "sol.soli1Portal, " + "sol.soli1Escalera, " + "sol.soli1Piso, " + "sol.soli1Puerta, " + "sol.soli1Codprov, "
          + "sol.soli1Codmunicipio, " + "sol.soli1Poblacion, " + "sol.soli1Cp, " + "sol.soli1Telefono, " + "sol.soli1Tlfmovil, " + "sol.soli1Email, "
          + "sol.repr1Titulo, " + "sol.repr1Sexo, " + "sol.soli1AltaNotifica, " + "sol.datosTitulo, " + "sol.respReqSubsanacion, " + "sol.checkDoc1, "
          + "sol.checkDoc2, " + "sol.checkDoc3, " + "sol.checkDoc4, " + "sol.checkDoc5, " + "sol.checkDoc6, " + "sol.checkDoc7, " + "sol.checkDoc8, "
          + "sol.checkDoc9, " + "sol.checkDoc10, " + "sol.checkDoc11, " + "sol.checkDoc12, " + "sol.checkDoc13, " + "sol.checkDoc14, " + "sol.checkDoc15, "
          + "sol.checkDoc16, " + "sol.checkDoc17, " + "sol.checkDoc18, " + "sol.checkDoc19, " + "sol.checkDoc20, " + "sol.checkDoc21, " + "sol.checkDoc22, "
          + "sol.checkDoc23, " + "sol.checkDoc24, " + "sol.checkDoc25, " + "sol.checkDoc26, " + "sol.checkDoc27, " + "sol.checkDoc28, " + "sol.checkDoc29, "
          + "sol.checkDoc30, " + "sol.checkDoc31, " + "sol.checkDoc32, " + "sol.autorizaJunta0AdmDocumento, " + "sol.autorizaJunta0AdmConsejeria, "
          + "sol.autorizaJunta0AdmFecha, " + "sol.autorizaJunta0AdmProcedimiento, " + "sol.autorizaJunta1AdmDocumento, " + "sol.autorizaJunta1AdmConsejeria, "
          + "sol.autorizaJunta1AdmFecha, " + "sol.autorizaJunta1AdmProcedimiento, " + "sol.autorizaGestor0OtrasDocumento, "
          + "sol.autorizaGestor0OtrasAdministracion, " + "sol.autorizaGestor0OtrasFecha, " + "sol.autorizaGestor0OtrasProcedimiento, "
          + "sol.autorizaGestor1OtrasDocumento, " + "sol.autorizaGestor1OtrasAdministracion, " + "sol.autorizaGestor1OtrasFecha, "
          + "sol.autorizaGestor1OtrasProcedimiento, " + "sol.firm1Lugar, " + "sol.firm1Fdo " + "FROM Solicitud sol WHERE sol.idSolicitud =:idSolicitud";
      solicitud = getEntityManager().createQuery(strQuery, SolicitudDTO.class).setParameter("idSolicitud", idSolicitud).getResultList().stream().findFirst()
          .orElse(null);
    } catch (final Exception e) {
      throw new SubsanacionException("Se ha producido un error al recuperar los datos de la solicitud para el expediente trewa con ID: " + idSolicitud, e);
    }
    return solicitud;
  }

  @Override
  public int guardarSolicitudSubsanacion(final AaciTSolicitudsubongd solicitudSubsanacion, final String tipoSolicitud) throws SubsanacionException {
    int resultado = 0;
    try {
      final StringBuilder sqlString = new StringBuilder(
          "UPDATE AACI_T_SOLICITUDSUBONGD SET TX_SOLI1SIGLAS =:siglas , TX_SOLI1CODIGO =:codigoRAGDA , TX_SOLI1TIPOVIA =:solTipoVia , TX_SOLI1NOMBREVIA =:solNombreVia , TX_SOLI1NUMERO =:solNumeroVia , TX_SOL1LETRA =:solLetraVia , TX_SOLI1KMVIA =:solKMVia , TX_SOLI1BLOQUE =:solBloque , TX_SOLI1PORTAL =:solPortal , TX_SOLI1ESCALERA =:solEscalera , TX_SOLI1PISO =:solPiso , TX_SOLI1PUERTA =:solPuerta , ")
              .append(
                  "NU_SOLI1CODPROV =:solCodProv , TX_SOLI1CODMUNICIPIO =:solCodMun , TX_SOLI1POBLACION =:solPoblacion , NU_SOLI1CP =:solCP , NU_SOLI1TELEFONO =:solTelf , NU_SOLI1TLFMOVIL =:solTelfMovil , TX_SOLI1EMAIL =:solEmail , TX_REPR1TITULO =:reprTitulo , TX_REPR1SEXO =:reprSexo , NU_REPR1TLFMOVIL =:repr1Tlfmovil, NU_REPR1TELEFONO =:repr1Telefono, TX_REPR1EMAIL =:repr1Email, ")
              .append(
                  "LG_SOLI1ALTANOTIFICA =:solAltaNotif , TX_DATOSTITULO =:solTitulo , TX_RESP_REQ_SUBSANACION =:respReqSubs , LG_OCHECKDOC1 =:oCheckDoc1 , ")
              .append(
                  "LG_OCHECKDOC2 =:oCheckDoc2 , LG_OCHECKDOC3 =:oCheckDoc3 , LG_OCHECKDOC4 =:oCheckDoc4 , LG_OCHECKDOC5 =:oCheckDoc5 , LG_OCHECKDOC6 =:oCheckDoc6 , LG_OCHECKDOC7 =:oCheckDoc7 , LG_OCHECKDOC8 =:oCheckDoc8 , LG_OCHECKDOC9 =:oCheckDoc9 , LG_OCHECKDOC10 =:oCheckDoc10 , LG_OCHECKDOC11 =:oCheckDoc11 , LG_OCHECKDOC12 =:oCheckDoc12 , LG_OCHECKDOC13 =:oCheckDoc13 , LG_OCHECKDOC14 =:oCheckDoc14 , ")
              .append(
                  "LG_OCHECKDOC15 =:oCheckDoc15 , LG_OCHECKDOC16 =:oCheckDoc16 , LG_OCHECKDOC17 =:oCheckDoc17 , LG_OCHECKDOC18 =:oCheckDoc18 , LG_OCHECKDOC19 =:oCheckDoc19 , LG_OCHECKDOC20 =:oCheckDoc20 , LG_OCHECKDOC21 =:oCheckDoc21 , LG_OCHECKDOC22 =:oCheckDoc22 , LG_OCHECKDOC23 =:oCheckDoc23 , LG_OCHECKDOC24 =:oCheckDoc24 , LG_OCHECKDOC25 =:oCheckDoc25 , LG_OCHECKDOC26 =:oCheckDoc26 , LG_OCHECKDOC27 =:oCheckDoc27 , ")
              .append(
                  "LG_OCHECKDOC28 =:oCheckDoc28 , LG_OCHECKDOC29 =:oCheckDoc29 , LG_OCHECKDOC30 =:oCheckDoc30 , LG_OCHECKDOC31 =:oCheckDoc31 , LG_OCHECKDOC32 =:oCheckDoc32 , LG_TCHECKDOC1 =:tCheckDoc1 , LG_TCHECKDOC2 =:tCheckDoc2 , LG_TCHECKDOC3 =:tCheckDoc3 , LG_TCHECKDOC4 =:tCheckDoc4 , LG_TCHECKDOC5 =:tCheckDoc5 , LG_TCHECKDOC6 =:tCheckDoc6 , LG_TCHECKDOC7 =:tCheckDoc7 , LG_TCHECKDOC8 =:tCheckDoc8 , ")
              .append(
                  "LG_TCHECKDOC9 =:tCheckDoc9 , LG_TCHECKDOC10 =:tCheckDoc10 , LG_TCHECKDOC11 =:tCheckDoc11 , LG_TCHECKDOC12 =:tCheckDoc12 , LG_TCHECKDOC13 =:tCheckDoc13 , LG_TCHECKDOC14 =:tCheckDoc14 , LG_TCHECKDOC15 =:tCheckDoc15 , LG_TCHECKDOC16 =:tCheckDoc16 , LG_TCHECKDOC17 =:tCheckDoc17 , LG_TCHECKDOC18 =:tCheckDoc18 , LG_TCHECKDOC19 =:tCheckDoc19 , LG_TCHECKDOC20 =:tCheckDoc20 , LG_TCHECKDOC21 =:tCheckDoc21 , ")
              .append(
                  "LG_TCHECKDOC22 =:tCheckDoc22 , LG_TCHECKDOC23 =:tCheckDoc23 , LG_TCHECKDOC24 =:tCheckDoc24 , LG_TCHECKDOC25 =:tCheckDoc25 , LG_TCHECKDOC26 =:tCheckDoc26 , LG_TCHECKDOC27 =:tCheckDoc27 , LG_TCHECKDOC28 =:tCheckDoc28 , LG_TCHECKDOC29 =:tCheckDoc29 , LG_TCHECKDOC30 =:tCheckDoc30 , LG_TCHECKDOC31 =:tCheckDoc31 , LG_TCHECKDOC32 =:tCheckDoc32 , TX_FIRM1LUGAR =:lugarFirma , TX_FIRM1FDO =:firmadoFirma ");

      if (tipoSolicitud.equals(ConstantesTramitacion.TIPO_CONV_ONGD)) {
        sqlString.append(
            ", LG_CHECKFINALIDAD1 =:cooperacion, LG_CHECKFINALIDAD2 =:accionHumanitaria, LG_CHECKFINALIDAD3 =:educacionDesarrollo, LG_CHECKFINALIDAD4 =:formacion , ")
            .append("LG_CHECKTIPO1 =:cu, LG_CHECKTIPO2 =:inn, LG_CHECKTIPO3 =:edd, LG_CHECKTIPO4 =:fe ");
      }
      sqlString.append(" WHERE ID_SOLICITUD =:idSolicitud");

      final Query query = getEntityManager().createNativeQuery(sqlString.toString()).setParameter("idSolicitud", solicitudSubsanacion.getId())
          .setParameter("siglas", solicitudSubsanacion.getSoli1Siglas() != null ? solicitudSubsanacion.getSoli1Siglas() : "")
          .setParameter("codigoRAGDA", solicitudSubsanacion.getSoli1Codigo() != null ? solicitudSubsanacion.getSoli1Codigo() : "")
          .setParameter("solTipoVia", solicitudSubsanacion.getSoli1Tipovia())
          .setParameter("solNombreVia", solicitudSubsanacion.getSoli1Nombrevia() != null ? solicitudSubsanacion.getSoli1Nombrevia() : "")
          .setParameter("solNumeroVia", solicitudSubsanacion.getSoli1Numero() != null ? solicitudSubsanacion.getSoli1Numero() : "")
          .setParameter("solLetraVia", solicitudSubsanacion.getSoli1Letra() != null ? solicitudSubsanacion.getSoli1Letra() : "")
          .setParameter("solKMVia", solicitudSubsanacion.getSoli1Kmvia() != null ? solicitudSubsanacion.getSoli1Kmvia() : "")
          .setParameter("solBloque", solicitudSubsanacion.getSoli1Bloque() != null ? solicitudSubsanacion.getSoli1Bloque() : "")
          .setParameter("solPortal", solicitudSubsanacion.getSoli1Portal() != null ? solicitudSubsanacion.getSoli1Portal() : "")
          .setParameter("solEscalera", solicitudSubsanacion.getSoli1Escalera() != null ? solicitudSubsanacion.getSoli1Escalera() : "")
          .setParameter("solPiso", solicitudSubsanacion.getSoli1Piso() != null ? solicitudSubsanacion.getSoli1Piso() : "")
          .setParameter("solPuerta", solicitudSubsanacion.getSoli1Puerta() != null ? solicitudSubsanacion.getSoli1Puerta() : "")
          .setParameter("solCodProv", solicitudSubsanacion.getSoli1Codprov() != null ? solicitudSubsanacion.getSoli1Codprov() : 0)
          .setParameter("solCodMun", solicitudSubsanacion.getSoli1Codmunicipio() != null ? solicitudSubsanacion.getSoli1Codmunicipio() : "")
          .setParameter("solPoblacion", solicitudSubsanacion.getSoli1Poblacion() != null ? solicitudSubsanacion.getSoli1Poblacion() : "")
          .setParameter("solCP", solicitudSubsanacion.getSoli1Cp() != null ? solicitudSubsanacion.getSoli1Cp() : 0)
          .setParameter("solTelf", solicitudSubsanacion.getSoli1Telefono() != null ? solicitudSubsanacion.getSoli1Telefono() : 0)
          .setParameter("solTelfMovil", solicitudSubsanacion.getSoli1Tlfmovil() != null ? solicitudSubsanacion.getSoli1Tlfmovil() : 0)
          .setParameter("solEmail", solicitudSubsanacion.getSoli1Email() != null ? solicitudSubsanacion.getSoli1Email() : "")
          .setParameter("reprTitulo", solicitudSubsanacion.getRepr1Titulo() != null ? solicitudSubsanacion.getRepr1Titulo() : "")
          .setParameter("reprSexo", solicitudSubsanacion.getRepr1Sexo() != null ? solicitudSubsanacion.getRepr1Sexo() : "")
          .setParameter("repr1Tlfmovil", solicitudSubsanacion.getRepr1Tlfmovil() != null ? solicitudSubsanacion.getRepr1Tlfmovil() : 0)
          .setParameter("repr1Telefono", solicitudSubsanacion.getRepr1Telefono() != null ? solicitudSubsanacion.getRepr1Telefono() : 0)
          .setParameter("repr1Email", solicitudSubsanacion.getRepr1Email() != null ? solicitudSubsanacion.getRepr1Email() : "")
          .setParameter("solAltaNotif",
              solicitudSubsanacion.getSoli1AltaNotifica() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getSoli1AltaNotifica()) : 0)
          .setParameter("solTitulo", solicitudSubsanacion.getDatosTitulo() != null ? solicitudSubsanacion.getDatosTitulo() : "")
          .setParameter("respReqSubs", solicitudSubsanacion.getTextoSubsanacion() != null ? solicitudSubsanacion.getTextoSubsanacion() : "")
          .setParameter("oCheckDoc1",
              solicitudSubsanacion.getoCheckDoc1() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc1()) : 0)
          .setParameter("oCheckDoc2",
              solicitudSubsanacion.getoCheckDoc2() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc2()) : 0)
          .setParameter("oCheckDoc3",
              solicitudSubsanacion.getoCheckDoc3() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc3()) : 0)
          .setParameter("oCheckDoc4",
              solicitudSubsanacion.getoCheckDoc4() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc4()) : 0)
          .setParameter("oCheckDoc5",
              solicitudSubsanacion.getoCheckDoc5() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc5()) : 0)
          .setParameter("oCheckDoc6",
              solicitudSubsanacion.getoCheckDoc6() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc6()) : 0)
          .setParameter("oCheckDoc7",
              solicitudSubsanacion.getoCheckDoc7() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc7()) : 0)
          .setParameter("oCheckDoc8",
              solicitudSubsanacion.getoCheckDoc8() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc8()) : 0)
          .setParameter("oCheckDoc9",
              solicitudSubsanacion.getoCheckDoc9() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc9()) : 0)
          .setParameter("oCheckDoc10",
              solicitudSubsanacion.getoCheckDoc10() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc10()) : 0)
          .setParameter("oCheckDoc11",
              solicitudSubsanacion.getoCheckDoc11() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc11()) : 0)
          .setParameter("oCheckDoc12",
              solicitudSubsanacion.getoCheckDoc12() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc12()) : 0)
          .setParameter("oCheckDoc13",
              solicitudSubsanacion.getoCheckDoc13() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc13()) : 0)
          .setParameter("oCheckDoc14",
              solicitudSubsanacion.getoCheckDoc14() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc14()) : 0)
          .setParameter("oCheckDoc15",
              solicitudSubsanacion.getoCheckDoc15() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc15()) : 0)
          .setParameter("oCheckDoc16",
              solicitudSubsanacion.getoCheckDoc16() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc16()) : 0)
          .setParameter("oCheckDoc17",
              solicitudSubsanacion.getoCheckDoc17() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc17()) : 0)
          .setParameter("oCheckDoc18",
              solicitudSubsanacion.getoCheckDoc18() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc18()) : 0)
          .setParameter("oCheckDoc19",
              solicitudSubsanacion.getoCheckDoc19() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc19()) : 0)
          .setParameter("oCheckDoc20",
              solicitudSubsanacion.getoCheckDoc20() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc20()) : 0)
          .setParameter("oCheckDoc21",
              solicitudSubsanacion.getoCheckDoc21() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc21()) : 0)
          .setParameter("oCheckDoc22",
              solicitudSubsanacion.getoCheckDoc22() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc22()) : 0)
          .setParameter("oCheckDoc23",
              solicitudSubsanacion.getoCheckDoc23() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc23()) : 0)
          .setParameter("oCheckDoc24",
              solicitudSubsanacion.getoCheckDoc24() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc24()) : 0)
          .setParameter("oCheckDoc25",
              solicitudSubsanacion.getoCheckDoc25() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc25()) : 0)
          .setParameter("oCheckDoc26",
              solicitudSubsanacion.getoCheckDoc26() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc26()) : 0)
          .setParameter("oCheckDoc27",
              solicitudSubsanacion.getoCheckDoc27() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc27()) : 0)
          .setParameter("oCheckDoc28",
              solicitudSubsanacion.getoCheckDoc28() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc28()) : 0)
          .setParameter("oCheckDoc29",
              solicitudSubsanacion.getoCheckDoc29() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc29()) : 0)
          .setParameter("oCheckDoc30",
              solicitudSubsanacion.getoCheckDoc30() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc30()) : 0)
          .setParameter("oCheckDoc31",
              solicitudSubsanacion.getoCheckDoc31() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc31()) : 0)
          .setParameter("oCheckDoc32",
              solicitudSubsanacion.getoCheckDoc32() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.getoCheckDoc32()) : 0)
          .setParameter("tCheckDoc1",
              solicitudSubsanacion.gettCheckDoc1() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc1()) : 0)
          .setParameter("tCheckDoc2",
              solicitudSubsanacion.gettCheckDoc2() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc2()) : 0)
          .setParameter("tCheckDoc3",
              solicitudSubsanacion.gettCheckDoc3() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc3()) : 0)
          .setParameter("tCheckDoc4",
              solicitudSubsanacion.gettCheckDoc4() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc4()) : 0)
          .setParameter("tCheckDoc5",
              solicitudSubsanacion.gettCheckDoc5() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc5()) : 0)
          .setParameter("tCheckDoc6",
              solicitudSubsanacion.gettCheckDoc6() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc6()) : 0)
          .setParameter("tCheckDoc7",
              solicitudSubsanacion.gettCheckDoc7() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc7()) : 0)
          .setParameter("tCheckDoc8",
              solicitudSubsanacion.gettCheckDoc8() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc8()) : 0)
          .setParameter("tCheckDoc9",
              solicitudSubsanacion.gettCheckDoc9() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc9()) : 0)
          .setParameter("tCheckDoc10",
              solicitudSubsanacion.gettCheckDoc10() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc10()) : 0)
          .setParameter("tCheckDoc11",
              solicitudSubsanacion.gettCheckDoc11() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc11()) : 0)
          .setParameter("tCheckDoc12",
              solicitudSubsanacion.gettCheckDoc12() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc12()) : 0)
          .setParameter("tCheckDoc13",
              solicitudSubsanacion.gettCheckDoc13() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc13()) : 0)
          .setParameter("tCheckDoc14",
              solicitudSubsanacion.gettCheckDoc14() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc14()) : 0)
          .setParameter("tCheckDoc15",
              solicitudSubsanacion.gettCheckDoc15() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc15()) : 0)
          .setParameter("tCheckDoc16",
              solicitudSubsanacion.gettCheckDoc16() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc16()) : 0)
          .setParameter("tCheckDoc17",
              solicitudSubsanacion.gettCheckDoc17() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc17()) : 0)
          .setParameter("tCheckDoc18",
              solicitudSubsanacion.gettCheckDoc18() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc18()) : 0)
          .setParameter("tCheckDoc19",
              solicitudSubsanacion.gettCheckDoc19() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc19()) : 0)
          .setParameter("tCheckDoc20",
              solicitudSubsanacion.gettCheckDoc20() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc20()) : 0)
          .setParameter("tCheckDoc21",
              solicitudSubsanacion.gettCheckDoc21() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc21()) : 0)
          .setParameter("tCheckDoc22",
              solicitudSubsanacion.gettCheckDoc22() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc22()) : 0)
          .setParameter("tCheckDoc23",
              solicitudSubsanacion.gettCheckDoc23() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc23()) : 0)
          .setParameter("tCheckDoc24",
              solicitudSubsanacion.gettCheckDoc24() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc24()) : 0)
          .setParameter("tCheckDoc25",
              solicitudSubsanacion.gettCheckDoc25() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc25()) : 0)
          .setParameter("tCheckDoc26",
              solicitudSubsanacion.gettCheckDoc26() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc26()) : 0)
          .setParameter("tCheckDoc27",
              solicitudSubsanacion.gettCheckDoc27() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc27()) : 0)
          .setParameter("tCheckDoc28",
              solicitudSubsanacion.gettCheckDoc28() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc28()) : 0)
          .setParameter("tCheckDoc29",
              solicitudSubsanacion.gettCheckDoc29() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc29()) : 0)
          .setParameter("tCheckDoc30",
              solicitudSubsanacion.gettCheckDoc30() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc30()) : 0)
          .setParameter("tCheckDoc31",
              solicitudSubsanacion.gettCheckDoc31() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc31()) : 0)
          .setParameter("tCheckDoc32",
              solicitudSubsanacion.gettCheckDoc32() != null ? UtilidadesBoolean.convertirBooleanNumero(solicitudSubsanacion.gettCheckDoc32()) : 0)
          .setParameter("lugarFirma", solicitudSubsanacion.getFirm1Lugar() != null ? solicitudSubsanacion.getFirm1Lugar() : "")
          .setParameter("firmadoFirma", solicitudSubsanacion.getFirm1Fdo() != null ? solicitudSubsanacion.getFirm1Fdo() : "");
      if (tipoSolicitud.equals(ConstantesTramitacion.TIPO_CONV_ONGD)) {
        query.setParameter("cooperacion", solicitudSubsanacion.getCheckFinalidad1() != null ? solicitudSubsanacion.getCheckFinalidad1() : 0)
            .setParameter("accionHumanitaria", solicitudSubsanacion.getCheckFinalidad2() != null ? solicitudSubsanacion.getCheckFinalidad2() : 0)
            .setParameter("educacionDesarrollo", solicitudSubsanacion.getCheckFinalidad3() != null ? solicitudSubsanacion.getCheckFinalidad3() : 0)
            .setParameter("formacion", solicitudSubsanacion.getCheckFinalidad4() != null ? solicitudSubsanacion.getCheckFinalidad4() : 0)
            .setParameter("cu", solicitudSubsanacion.getCheckTipo1() != null ? solicitudSubsanacion.getCheckTipo1() : 0)
            .setParameter("inn", solicitudSubsanacion.getCheckTipo2() != null ? solicitudSubsanacion.getCheckTipo2() : 0)
            .setParameter("edd", solicitudSubsanacion.getCheckTipo3() != null ? solicitudSubsanacion.getCheckTipo3() : 0)
            .setParameter("fe", solicitudSubsanacion.getCheckTipo4() != null ? solicitudSubsanacion.getCheckTipo4() : 0);
      }
      resultado = query.executeUpdate();
      return resultado;
    } catch (final Exception e) {
      throw new SubsanacionException(
          "Se ha producido un error al guardar la subsanación de la solicitud con id " + solicitudSubsanacion.getId() + ENTREGADA_POR_VEAJA, e);
    }
  }

  @Override
  public void updateCamposRepetibles(final AaciTSolicitudsubongd solicitudEditada) throws SubsanacionException {
    if (CollectionUtils.isNotEmpty(solicitudEditada.getAaciTDocPoderAdminJasByIdSolicitud())) {
      final int existeDocAdmJA = ((BigDecimal) getEntityManager()
          .createNativeQuery("select count(*) FROM AACI_T_DOC_PODER_ADMIN_JA WHERE ID_SOLICITUD =:idSolicitud")
          .setParameter("idSolicitud", solicitudEditada.getId()).getSingleResult()).intValue();
      if (existeDocAdmJA > 0) {
        getEntityManager().createNativeQuery("DELETE FROM AACI_T_DOC_PODER_ADMIN_JA WHERE ID_SOLICITUD =:idSolicitud")
            .setParameter("idSolicitud", solicitudEditada.getId()).executeUpdate();
      }
      for (final AaciTDocPoderAdminJa docJA : solicitudEditada.getAaciTDocPoderAdminJasByIdSolicitud()) {
        getEntityManager().createNativeQuery(INSERT_DOCS_PODER_JA).setParameter(1, docJA.getAaciTSolicitudsubongdByIdSolicitud().getId())
            .setParameter(2, docJA.getTxConsejeriaYOrgano()).setParameter(3, docJA.getFhFechaPresent()).setParameter(4, docJA.getTxProcedEmitio())
            .setParameter(5, docJA.getTxNomDocumento()).executeUpdate();
      }
    }

    if (CollectionUtils.isNotEmpty(solicitudEditada.getAaciTDocPoderOtrAdminsByIdSolicitud())) {
      final int existeDocOtraAdm = ((BigDecimal) getEntityManager()
          .createNativeQuery("select count(*) FROM AACI_T_DOC_PODER_OTR_ADMIN WHERE ID_SOLICITUD =:idSolicitud")
          .setParameter("idSolicitud", solicitudEditada.getId()).getSingleResult()).intValue();
      if (existeDocOtraAdm > 0) {
        getEntityManager().createNativeQuery("DELETE FROM AACI_T_DOC_PODER_OTR_ADMIN WHERE ID_SOLICITUD =:idSolicitud")
            .setParameter("idSolicitud", solicitudEditada.getId()).executeUpdate();
      }
      for (final AaciTDocPoderOtrAdmin docOtraAdmin : solicitudEditada.getAaciTDocPoderOtrAdminsByIdSolicitud()) {
        getEntityManager().createNativeQuery(INSERT_DOCS_PODER_OTR_ADM).setParameter(1, docOtraAdmin.getAaciTSolicitudsubongdByIdSolicitud().getId())
            .setParameter(2, docOtraAdmin.getTxConsejeriaYOrgano()).setParameter(3, docOtraAdmin.getFhFechaPresent())
            .setParameter(4, docOtraAdmin.getTxProcedEmitio()).setParameter(5, docOtraAdmin.getTxNomDocumento()).executeUpdate();
      }
    }
    if (CollectionUtils.isNotEmpty(solicitudEditada.getAaciAgrupacionesByIdSolicitud())) {
      final int agrupaciones = agrupacionDao.existeAgrupaciones(solicitudEditada.getId());
      if (agrupaciones > 0) {
        getEntityManager().createNativeQuery("DELETE FROM AACI_AGRUPACIONES WHERE ID_SOLICITUD =:idSolicitud")
            .setParameter("idSolicitud", solicitudEditada.getId()).executeUpdate();
      }
      for (final AaciAgrupaciones agrupacion : solicitudEditada.getAaciAgrupacionesByIdSolicitud()) {
        getEntityManager()
            .createNativeQuery("INSERT INTO AACI_AGRUPACIONES ( AGRU_NU_ID, ID_SOLICITUD, AGRU_LI_DENOMINACION, AGRU_LI_SIGLAS,"
                + " AGRU_CO_INSCRIPCION, AGRU_LI_NIF, AGRU_LI_ONGD_LIDER) VALUES (AACI_SEQ_AGRUPACIONES.nextval,?,?,?,?,?,?)")
            .setParameter(1, agrupacion.getAaciTSolicitudsubongdByIdSolicitud().getId()).setParameter(2, agrupacion.getAgruLiDenominacion())
            .setParameter(3, agrupacion.getAgruLiSiglas()).setParameter(4, agrupacion.getAgruCoInscripcion()).setParameter(5, agrupacion.getAgruLiNif())
            .setParameter(6, solicitudEditada.getSoli1Nombre()).executeUpdate();
      }
    }
  }

  @Override
  public AaciTPaises getPaisesById(final String idPais) {
    return getEntityManager().createQuery("from AaciTPaises p where p.txCodigo =:idPais", AaciTPaises.class).setParameter("idPais", idPais).getSingleResult();
  }

  @Override
  public void guardarAlegaciones(final Solicitud formulario) throws AlegacionesException {
    try {
      final StringBuilder sqlSolicitud = new StringBuilder("UPDATE AACI_T_SOLICITUDSUBONGD SET") //

          // ----------------------------------------------------------------------------------------------------
          // 1 DATOS DE LA ENTIDAD SOLICITANTE Y DE LA PERSONA REPRESENTANTE
          // ----------------------------------------------------------------------------------------------------

          // SOLICITANTE
          .append(" TX_SOLI1NOMBRE = :soli1Nombre") // DENOMINACIÓN DE LA ENTIDAD*
          .append(", TX_SOLI1NUMIDENT = :soli1Numident") // NIF*
          .append(", TX_SOLI1SIGLAS = :soli1Siglas") // SIGLAS*

          // REPRESENTANTE
          .append(", TX_REPR1APELLIDO1 = :repr1Apellido1") // APELLIDO 1 DE LA PERSONA REPRESENTANTE/RAZÓN SOCIAL/DENOMINACIÓN
          .append(", TX_REPR1APELLIDO2 = :repr1Apellido2") // APELLIDO 2 DE LA PERSONA REPRESENTANTE/RAZÓN SOCIAL/DENOMINACIÓN
          .append(", TX_REPR1NOMBRE = :repr1Nombre") // NOMBRE 1 DE LA PERSONA REPRESENTANTE/RAZÓN SOCIAL/DENOMINACIÓN
          .append(", TX_REPR1SEXO = :repr1Sexo") // SEXO
          .append(", TX_REPR1NUMIDENT = :repr1Numident") // DNI/NIE/NIF
          .append(", TX_REPR1TITULO = :repr1Titulo") // QUE ACTÚA EN CALIDAD DE
          .append(", TX_REPR1_TLFMOVIL = :repr1TlfMovil").append(", TX_REPR1_TELEFONO = :repr1Telefono").append(", TX_REPR1_EMAIL= :repr1Email")
          // ----------------------------------------------------------------------------------------------------
          // 2 NOTIFICACIÓN ELECTRÓNICA OBLIGATORIA
          // ----------------------------------------------------------------------------------------------------
          .append(", LG_ALTANOFIFICA = :altaNotifica") // Alta en notifica

          // ----------------------------------------------------------------------------------------------------
          // 3 IDENTIFICACIÓN DEL PROYECTO PARA EL QUE SE SOLICITA LA SUBVENCIÓN
          // ----------------------------------------------------------------------------------------------------
          .append(", TX_DATOSTITULO = :datosTitulo") // TÍTULO DEL PROYECTO

          // ----------------------------------------------------------------------------------------------------
          // 4 ALEGACIONES
          // ----------------------------------------------------------------------------------------------------
          .append(", LG_ALEGO = :alego") // TÍTULO DEL PROYECTO
          .append(", TX_ALEGO = :textoAlego") // TÍTULO DEL PROYECTO
          .append(", LG_OTRO_ALEGO = :alegoOtra") // TÍTULO DEL PROYECTO
          .append(", TX_OTRO_ALEGO = :textoOtra") // TÍTULO DEL PROYECTO

          // ----------------------------------------------------------------------------------------------------
          // 6 DOCUMENTACIÓN
          // ----------------------------------------------------------------------------------------------------

          // ----------------------------------------------------------------------------------------------------
          // 7 DECLARACIÓN, LUGAR, FECHA Y FIRMA
          // ----------------------------------------------------------------------------------------------------
          .append(", TX_FIRM1FDO = :firm1Fdo") // FIRMANTE
          .append(", TX_FIRM1LUGAR = :firm1Lugar") // LUGAR DE LA FIRMA
          .append(", TX_COD_DIR3 = :txCodDir3") // LUGAR DE LA FIRMA

          // ----------------------------------------------------------------------------------------------------
          // DOMICILIO A EFECTOS DE NOTIFICACIÓN
          // ----------------------------------------------------------------------------------------------------
          .append(", TX_SOLI1TIPOVIA = :soli1Tipovia") // TIPO DE VÍA*
          .append(", TX_SOLI1NOMBREVIA = :soli1Nombrevia") // NOMBRE DE LA VÍA*
          .append(", TX_SOLI1NUMERO = :soli1Numero") // NÚMERO
          .append(", TX_SOL1LETRA = :soli1Letra") // LETRA
          .append(", TX_SOLI1KMVIA = :soli1Kmvia") // KM EN LA VÍA
          .append(", TX_SOLI1BLOQUE = :soli1Bloque") // BLOQUE
          .append(", TX_SOLI1PORTAL = :soli1Portal") // PORTAL
          .append(", TX_SOLI1ESCALERA = :soli1Escalera") // ESCALERA
          .append(", TX_SOLI1PISO = :soli1Piso") // PISO
          .append(", TX_SOLI1PUERTA = :soli1Puerta") // PUERTA
          .append(", TX_SOLI1CODMUNICIPIO = :soli1Codmunicipio") // MUNICIPIO
          .append(", TX_SOLI1POBLACION = :soli1Poblacion") // ENTIDAD DE POBLACIÓN

          .append(", NU_SOLI1CP = :soli1Cp") // CÓDIGO POSTAL
          .append(", NU_SOLI1TELEFONO = :soli1Telefono") // NÚMERO TELÉFONO
          .append(", NU_SOLI1TLFMOVIL = :soli1Tlfmovil") // NÚMERO MÓVIL
          .append(", NU_SOLI1CODPROV = :soli1Codprov") // PROVINCIA

          .append(", TX_SOLI1EMAIL = :soli1Email") // CORREO ELECTRÓNICO

          .append(" WHERE ID_SOLICITUD = :idSolicitud");

      getEntityManager().createNativeQuery(sqlSolicitud.toString()) //
          .setParameter("idSolicitud", getParameterQuery(formulario.getIdSolicitud())) //

          // ----------------------------------------------------------------------------------------------------
          // 1 DATOS DE LA ENTIDAD SOLICITANTE Y DE LA PERSONA REPRESENTANTE
          // ----------------------------------------------------------------------------------------------------

          // SOLICITANTE
          .setParameter("soli1Nombre", getParameterQuery(formulario.getTxSoli1Nombre())) //
          .setParameter("soli1Numident", getParameterQuery(formulario.getTxSoli1Numident())) //
          .setParameter("soli1Siglas", getParameterQuery(formulario.getTxSoli1Siglas())) //

          // REPRESENTANTE
          .setParameter("repr1Apellido1", getParameterQuery(formulario.getTxRepr1Apellido1())) //
          .setParameter("repr1Apellido2", getParameterQuery(formulario.getTxRepr1Apellido2())) //
          .setParameter("repr1Nombre", getParameterQuery(formulario.getTxRepr1Nombre())) //
          .setParameter("repr1Sexo", getParameterQuery(formulario.getTxRepr1Sexo())) //
          .setParameter("repr1Numident", getParameterQuery(formulario.getTxRepr1Numident())) //
          .setParameter("repr1Titulo", getParameterQuery(formulario.getTxRepr1Titulo())) //
          .setParameter("repr1TlfMovil", getParameterQuery(formulario.getTxRepr1TlfMovil())) //
          .setParameter("repr1Telefono", getParameterQuery(formulario.getTxRepr1Telefono())) //
          .setParameter("repr1Email", getParameterQuery(formulario.getTxRepr1Email())) //

          // ----------------------------------------------------------------------------------------------------
          // 2 NOTIFICACIÓN ELECTRÓNICA OBLIGATORIA
          // ----------------------------------------------------------------------------------------------------
          .setParameter("altaNotifica", getParameterQuery(formulario.getLgAltanofifica()))

          // ----------------------------------------------------------------------------------------------------
          // 3 IDENTIFICACIÓN DEL PROYECTO PARA EL QUE SE SOLICITA LA SUBVENCIÓN
          // ----------------------------------------------------------------------------------------------------
          .setParameter("datosTitulo", getParameterQuery(formulario.getTxDatostitulo())) //

          // ----------------------------------------------------------------------------------------------------
          // 4 ALEGACIONES
          // ----------------------------------------------------------------------------------------------------
          .setParameter("alego", getParameterQuery(formulario.getLgAlego())) //
          .setParameter("textoAlego", getParameterQuery(formulario.getTxAlego())) //
          .setParameter("alegoOtra", getParameterQuery(formulario.getLgOtroAlego())) //
          .setParameter("textoOtra", getParameterQuery(formulario.getTxOtroAlego())) //

          // ----------------------------------------------------------------------------------------------------
          // 5 DECLARACIÓN, LUGAR, FECHA Y FIRMA
          // ----------------------------------------------------------------------------------------------------
          .setParameter("firm1Fdo", getParameterQuery(formulario.getTxFirm1Fdo())) //
          .setParameter("firm1Lugar", getParameterQuery(formulario.getTxFirm1Lugar())) //
          .setParameter("txCodDir3", getParameterQuery(formulario.getTxCodDir3())) //

          // ----------------------------------------------------------------------------------------------------
          // DOMICILIO A EFECTOS DE NOTIFICACIÓN
          // ----------------------------------------------------------------------------------------------------
          .setParameter("soli1Tipovia", getParameterQuery(formulario.getTxSoli1Tipovia())) //
          .setParameter("soli1Nombrevia", getParameterQuery(formulario.getTxSoli1Nombrevia())) //
          .setParameter("soli1Numero", getParameterQuery(formulario.getTxSoli1Numero())) //
          .setParameter("soli1Letra", getParameterQuery(formulario.getTxSol1Letra())) //
          .setParameter("soli1Kmvia", getParameterQuery(formulario.getTxSoli1Kmvia())) //
          .setParameter("soli1Bloque", getParameterQuery(formulario.getTxSoli1Bloque())) //
          .setParameter("soli1Portal", getParameterQuery(formulario.getTxSoli1Portal())) //
          .setParameter("soli1Escalera", getParameterQuery(formulario.getTxSoli1Escalera())) //
          .setParameter("soli1Piso", getParameterQuery(formulario.getTxSoli1Piso())) //
          .setParameter("soli1Puerta", getParameterQuery(formulario.getTxSoli1Puerta())) //
          .setParameter("soli1Codmunicipio", getParameterQuery(formulario.getTxSoli1Codmunicipio())) //
          .setParameter("soli1Poblacion", getParameterQuery(formulario.getTxSoli1Poblacion())) //

          .setParameter("soli1Cp", getParameterQuery(formulario.getNuSoli1Cp())) //
          .setParameter("soli1Telefono", getParameterQuery(formulario.getNuSoli1Telefono())) //
          .setParameter("soli1Tlfmovil", getParameterQuery(formulario.getNuSoli1Tlfmovil())) //
          .setParameter("soli1Codprov", getParameterQuery(formulario.getNuSoli1Codprov())) //

          .setParameter("soli1Email", getParameterQuery(formulario.getTxSoli1Email())) //

          .executeUpdate();

      formulario.getDocumentosJA().stream().forEach(docJA -> //
      getEntityManager()
          .createNativeQuery("INSERT INTO AACI_T_DOC_PODER_ADMIN_JA (ID_DOC_PODER_ADMIN_JA, ID_SOLICITUD, TX_CONSEJERIA_Y_ORGANO,"
              + " FH_FECHA_PRESENT, TX_PROCED_EMITIO, TX_NOM_DOCUMENTO) VALUES (AACI_SEQ_DOCS_PODER_JA.nextval,?,?,?,?,?)")
          .setParameter(1, getParameterQuery(docJA.getSolicitud().getIdSolicitud())) //
          .setParameter(2, getParameterQuery(docJA.getConsejeriaOrgano())) //
          .setParameter(3, docJA.getFecha() != null ? docJA.getFecha() : null, TemporalType.DATE) //
          .setParameter(4, getParameterQuery(docJA.getProcedimiento())) //
          .setParameter(5, getParameterQuery(docJA.getNombre())) //
          .executeUpdate());

      formulario.getDocumentosOtrasAdmin().stream().forEach(docOtraAdmin -> //
      getEntityManager()
          .createNativeQuery("INSERT INTO AACI_T_DOC_PODER_OTR_ADMIN ( ID_DOC_PODER_OTR_ADMIN, ID_SOLICITUD, TX_CONSEJERIA_Y_ORGANO,"
              + " FH_FECHA_PRESENT, TX_PROCED_EMITIO, TX_NOM_DOCUMENTO) VALUES (AACI_SEQ_DOCS_PODER_OTR_ADMIN.nextval,?,?,?,?,?)")
          .setParameter(1, getParameterQuery(docOtraAdmin.getSolicitud().getIdSolicitud())) //
          .setParameter(2, getParameterQuery(docOtraAdmin.getAdministracion())) //
          .setParameter(3, docOtraAdmin.getFecha() != null ? docOtraAdmin.getFecha() : null, TemporalType.DATE) //
          .setParameter(4, getParameterQuery(docOtraAdmin.getProcedimiento())) //
          .setParameter(5, getParameterQuery(docOtraAdmin.getNombre())) //
          .executeUpdate());

    } catch (final Exception e) {
      throw new AlegacionesException("Se ha producido un error al guardar el FORMULARIO DE ALEGACIONES de la solicitud de ONGD con id "
          + formulario.getIdSolicitud() + " entregada por VEAJA", e);
    }
  }

  @Override
  public void guardarAlegacionesAceptaReformulaUNIV(final Solicitud formulario) throws AlegacionesAceptaReformulaException {

    try {
      final StringBuilder sqlSolicitud = new StringBuilder("UPDATE AACI_T_SOLICITUDSUBONGD SET") //

          // ----------------------------------------------------------------------------------------------------
          // 1 DATOS DE LA ENTIDAD SOLICITANTE Y DE LA PERSONA REPRESENTANTE
          // ----------------------------------------------------------------------------------------------------

          // SOLICITANTE
          .append(" TX_SOLI1NOMBRE = :soli1Nombre") // DENOMINACIÓN DE LA ENTIDAD
          .append(", TX_SOLI1NUMIDENT = :soli1Numident") // NIF
          .append(", TX_SOLI1SIGLAS = :soli1Siglas") // SIGLAS
          .append(", TX_SOLI1TIPOIDENT = :soli1Tipoident") // TIPO DE IDENTIDAD DE LA ENTIDAD
          .append(", TX_SOLI1APELLIDO1 = :soli1Apellido1") // APELLIDO 1 DE LA ENTIDAD
          .append(", TX_SOLI1APELLIDO2 = :soli1Apellido2") // APELLIDO 2 DE LA ENTIDAD
          .append(", TX_SOLI1SEXO = :soli1Sexo") // SEXO

          // REPRESENTANTE
          .append(", TX_REPR1APELLIDO1 = :repr1Apellido1") // APELLIDO 1 DE LA PERSONA REPRESENTANTE/RAZÓN SOCIAL/DENOMINACIÓN
          .append(", TX_REPR1APELLIDO2 = :repr1Apellido2") // APELLIDO 2 DE LA PERSONA REPRESENTANTE/RAZÓN SOCIAL/DENOMINACIÓN
          .append(", TX_REPR1NOMBRE = :repr1Nombre") // NOMBRE 1 DE LA PERSONA REPRESENTANTE/RAZÓN SOCIAL/DENOMINACIÓN
          .append(", TX_REPR1SEXO = :repr1Sexo") // SEXO
          .append(", TX_REPR1NUMIDENT = :repr1Numident") // DNI/NIE/NIF
          .append(", TX_REPR1TITULO = :repr1Titulo") // QUE ACTÚA EN CALIDAD DE
          .append(", TX_REPR1TIPOIDENT = :repr1Tipoident") // TIPO DE IDENTIDAD DE LA PERSONA REPRESENTANTE/RAZÓN SOCIAL/DENOMINACIÓN

          // ----------------------------------------------------------------------------------------------------
          // 2 NOTIFICACIÓN ELECTRÓNICA OBLIGATORIA
          // ----------------------------------------------------------------------------------------------------
          .append(", TX_NOTIFEMAILAUTLUGAR = :notifEmailAutLugar") // Correo electrónico
          .append(", TX_NOTIFTELEFONOAUTLUGAR = :notifTelefonoAutLugar") // Nº teléfono móvil

          // ----------------------------------------------------------------------------------------------------
          // 3 DATOS BANCARIOS
          // ----------------------------------------------------------------------------------------------------
          .append(", TX_BANCO_IBAN = :bancoIban") // IBAN
          .append(", TX_BANCO_CODIGO = :bancoCodigo") // IBAN
          .append(", TX_BANCO_PAIS = :bancoPais") // IBAN
          .append(", TX_BANCO_LOCALIDAD = :bancoLocalidad") // IBAN
          .append(", TX_BANCO_SUCURSAL= :bancoSucursal") // IBAN
          .append(", TX_BANCO_NUMCUENTA = :bancoNumcuenta") // IBAN

          .append(", TX_BANCO_S_CODBANCO = :bancoSCodbanco") // SWIFT Código Banco
          .append(", TX_BANCO_S_PAIS = :bancoSPais") // SWIFT País
          .append(", TX_SWIFT_LOCALIDAD = :swiftLocalidad") // SWIFT Localidad
          .append(", TX_BANCO_S_SUCUR = :bancoSSucur") // SWIFT Sucursal

          .append(", TX_BANCO_ENTIDAD = :bancoEntidad") // Entidad
          .append(", TX_BANCO_DOMICILIO = :bancoDomicilio") // Domicilio
          .append(", TX_BANCO_CODMUNICIPIO = :bancoCodmunicipio") // Localidad
          .append(", TX_BANCO_CODPROV = :bancoCodprov") // Provincia
          .append(", TX_BANCO_CP = :bancoCp") // Código Postal

          // ----------------------------------------------------------------------------------------------------
          // 4 IDENTIFICACIÓN DEL PROYECTO PARA EL QUE SE SOLICITA LA SUBVENCIÓN
          // ----------------------------------------------------------------------------------------------------
          .append(", TX_DATOSTITULO = :datosTitulo") // TÍTULO DEL PROYECTO

          // ----------------------------------------------------------------------------------------------------
          // 5 ALEGACIONES/ACEPTACIÓN/REFORMULACIÓN
          // ----------------------------------------------------------------------------------------------------
          .append(", LG_CONCE_UNO = :conceUno") // CONCEDIDA COMO ENTIDAD BENEFICIARIA PROVISIONAL por el importe o pretensión solicitado
          .append(", LG_CONCE_DOS = :conceDos") // CONCEDIDA COMO ENTIDAD BENEFICIARIA PROVISIONAL por un importe o pretensión inferior al
                                                // solicitado
          .append(", LG_CONCE_TRES = :conceTres") // CONCEDIDA COMO ENTIDAD BENEFICIARIA SUPLENTE

          .append(", LG_CONCE_CUATRO = :conceCuatro") // ACEPTO la subvención propuesta.
          .append(", LG_CONCE_CINCO = :conceCinco") // DESISTO de la solicitud.
          .append(", LG_CONCE_SEIS = :conceSeis") // REFORMULO
          .append(", TX_REFOR = :refor") //

          .append(", LG_CONCE_SIETE = :conceSiete") // NO ACEPTO la subvención propuesta y ALEGO lo siguiente
          .append(", TX_NO_ACEPTO = :noAcepto") //
          .append(", LG_CONCE_OCHO = :conceOcho") // Otra/s (especificar)
          .append(", TX_OTRO_A = :otroA") //

          // ----------------------------------------------------------------------------------------------------
          // 6 DOCUMENTACIÓN
          // ----------------------------------------------------------------------------------------------------
          .append(", LG_DOC1 = :doc1") // Presupuesto validado firmado
          .append(", LG_DOC2 = :doc2") // Para el caso de reformulación del Presupuesto: Presupuesto reformulado
          .append(", LG_DOC3 = :doc3") // Para el caso de reformulación de la Matriz: Matriz reformulada

          // ----------------------------------------------------------------------------------------------------
          // 7 DECLARACIÓN, LUGAR, FECHA Y FIRMA
          // ----------------------------------------------------------------------------------------------------
          .append(", TX_FIRM1FDO = :firm1Fdo") // FIRMANTE
          .append(", TX_FIRM1LUGAR = :firm1Lugar") // LUGAR DE LA FIRMA

          // ----------------------------------------------------------------------------------------------------
          // DOMICILIO A EFECTOS DE NOTIFICACIÓN
          // ----------------------------------------------------------------------------------------------------
          .append(", TX_SOLI1TIPOVIA = :soli1Tipovia") // TIPO DE VÍA
          .append(", TX_SOLI1NOMBREVIA = :soli1Nombrevia") // NOMBRE DE LA VÍA
          .append(", TX_SOLI1NUMERO = :soli1Numero") // NÚMERO
          .append(", TX_SOL1LETRA = :soli1Letra") // LETRA
          .append(", TX_SOLI1KMVIA = :soli1Kmvia") // KM EN LA VÍA
          .append(", TX_SOLI1BLOQUE = :soli1Bloque") // BLOQUE
          .append(", TX_SOLI1PORTAL = :soli1Portal") // PORTAL
          .append(", TX_SOLI1ESCALERA = :soli1Escalera") // ESCALERA
          .append(", TX_SOLI1PISO = :soli1Piso") // PISO
          .append(", TX_SOLI1PUERTA = :soli1Puerta") // PUERTA
          .append(", NU_SOLI1CODPROV = :soli1Codprov") // PROVINCIA
          .append(", TX_SOLI1CODMUNICIPIO = :soli1Codmunicipio") // MUNICIPIO
          .append(", TX_SOLI1POBLACION = :soli1Poblacion") // ENTIDAD DE POBLACIÓN
          .append(", NU_SOLI1CP = :soli1Cp") // CÓDIGO POSTAL
          .append(", NU_SOLI1TELEFONO = :soli1Telefono") // NÚMERO TELÉFONO
          .append(", NU_SOLI1TLFMOVIL = :soli1Tlfmovil") // NÚMERO MÓVIL
          .append(", TX_SOLI1EMAIL = :soli1Email") // CORREO ELECTRÓNICO

          .append(", TX_REPR1TIPOVIA = :repr1Tipovia") // TIPO DE VÍA
          .append(", TX_REPR1NOMBREVIA = :repr1Nombrevia") // NOMBRE DE LA VÍA
          .append(", NU_REPR1CODPROV = :repr1Codprov") // PROVINCIA
          .append(", NU_REPR1CODMUNICIPIO = :repr1Codmunicipio") // MUNICIPIO

          .append(" WHERE ID_SOLICITUD = :idSolicitud");

      getEntityManager().createNativeQuery(sqlSolicitud.toString()) //
          .setParameter("idSolicitud", getParameterQuery(formulario.getIdSolicitud())) //

          // ----------------------------------------------------------------------------------------------------
          // 1 DATOS DE LA ENTIDAD SOLICITANTE Y DE LA PERSONA REPRESENTANTE
          // ----------------------------------------------------------------------------------------------------

          // SOLICITANTE
          .setParameter("soli1Nombre", getParameterQuery(formulario.getTxSoli1Nombre())) //
          .setParameter("soli1Numident", getParameterQuery(formulario.getTxSoli1Numident())) //
          .setParameter("soli1Siglas", getParameterQuery(formulario.getTxSoli1Siglas())) //
          .setParameter("soli1Tipoident", getParameterQuery(formulario.getTxSoli1Tipoident())) //
          .setParameter("soli1Apellido1", getParameterQuery(formulario.getTxSoli1Apellido1())) //
          .setParameter("soli1Apellido2", getParameterQuery(formulario.getTxSoli1Apellido2())) //
          .setParameter("soli1Sexo", getParameterQuery(formulario.getTxSoli1Sexo())) //

          // REPRESENTANTE
          .setParameter("repr1Apellido1", getParameterQuery(formulario.getTxRepr1Apellido1())) //
          .setParameter("repr1Apellido2", getParameterQuery(formulario.getTxRepr1Apellido2())) //
          .setParameter("repr1Nombre", getParameterQuery(formulario.getTxRepr1Nombre())) //
          .setParameter("repr1Sexo", getParameterQuery(formulario.getTxRepr1Sexo())) //
          .setParameter("repr1Numident", getParameterQuery(formulario.getTxRepr1Numident())) //
          .setParameter("repr1Titulo", getParameterQuery(formulario.getTxRepr1Titulo())) //
          .setParameter("repr1Tipoident", getParameterQuery(formulario.getTxRepr1Tipoident())) //

          // ----------------------------------------------------------------------------------------------------
          // 2 NOTIFICACIÓN ELECTRÓNICA OBLIGATORIA
          // ----------------------------------------------------------------------------------------------------
          .setParameter("notifEmailAutLugar", getParameterQuery(formulario.getTxNotifemailautlugar())) //
          .setParameter("notifTelefonoAutLugar", getParameterQuery(formulario.getTxNotiftelefonoautlugar())) //

          // ----------------------------------------------------------------------------------------------------
          // 3 DATOS BANCARIOS
          // ----------------------------------------------------------------------------------------------------
          .setParameter("bancoIban", getParameterQuery(formulario.getTxBancoIban())) //
          .setParameter("bancoCodigo", getParameterQuery(formulario.getTxBancoCodigo())) //
          .setParameter("bancoPais", getParameterQuery(formulario.getTxBancoPais())) //
          .setParameter("bancoLocalidad", getParameterQuery(formulario.getTxBancoLocalidad())) //
          .setParameter("bancoSucursal", getParameterQuery(formulario.getTxBancoSucursal())) //
          .setParameter("bancoNumcuenta", getParameterQuery(formulario.getTxBancoNumCuenta())) //

          .setParameter("bancoSCodbanco", getParameterQuery(formulario.getTxBancoSwiftCodBanco())) //
          .setParameter("bancoSPais", getParameterQuery(formulario.getTxBancoSwiftPais())) //
          .setParameter("swiftLocalidad", getParameterQuery(formulario.getTxBancoSwiftLocalidad())) //
          .setParameter("bancoSSucur", getParameterQuery(formulario.getTxBancoSwiftSucur())) //

          .setParameter("bancoEntidad", getParameterQuery(formulario.getTxBancoEntidad())) //
          .setParameter("bancoDomicilio", getParameterQuery(formulario.getTxBancoDomicilio())) //
          .setParameter("bancoCodmunicipio", getParameterQuery(formulario.getTxBancoCodMunicipio())) //
          .setParameter("bancoCodprov", getParameterQuery(formulario.getTxBancoCodProv())) //
          .setParameter("bancoCp", getParameterQuery(formulario.getTxBancoCP())) //

          // ----------------------------------------------------------------------------------------------------
          // 4 IDENTIFICACIÓN DEL PROYECTO PARA EL QUE SE SOLICITA LA SUBVENCIÓN
          // ----------------------------------------------------------------------------------------------------
          .setParameter("datosTitulo", getParameterQuery(formulario.getTxDatostitulo())) //

          // ----------------------------------------------------------------------------------------------------
          // 5 ALEGACIONES/ACEPTACIÓN/REFORMULACIÓN
          // ----------------------------------------------------------------------------------------------------
          .setParameter("conceUno", getParameterQuery(formulario.getLgConceUno())) //
          .setParameter("conceDos", getParameterQuery(formulario.getLgConceDos())) //
          .setParameter("conceTres", getParameterQuery(formulario.getLgConceTres())) //

          .setParameter("conceCuatro", getParameterQuery(formulario.getLgConceCuatro())) //
          .setParameter("conceCinco", getParameterQuery(formulario.getLgConceCinco())) //
          .setParameter("conceSeis", getParameterQuery(formulario.getLgConceSeis())) //
          .setParameter("refor", getParameterQuery(formulario.getTxRefor())) //

          .setParameter("conceSiete", getParameterQuery(formulario.getLgConceSiete())) //
          .setParameter("noAcepto", getParameterQuery(formulario.getTxNoAcepto())) //
          .setParameter("conceOcho", getParameterQuery(formulario.getLgConceOcho())) //
          .setParameter("otroA", getParameterQuery(formulario.getTxOtroA())) //

          // ----------------------------------------------------------------------------------------------------
          // 6 DOCUMENTACIÓN
          // ----------------------------------------------------------------------------------------------------
          .setParameter("doc1", getParameterQuery(formulario.getLgDoc1())) //
          .setParameter("doc2", getParameterQuery(formulario.getLgDoc2())) //
          .setParameter("doc3", getParameterQuery(formulario.getLgDoc3())) //

          // ----------------------------------------------------------------------------------------------------
          // 7 DECLARACIÓN, LUGAR, FECHA Y FIRMA
          // ----------------------------------------------------------------------------------------------------
          .setParameter("firm1Fdo", getParameterQuery(formulario.getTxFirm1Fdo())) //
          .setParameter("firm1Lugar", getParameterQuery(formulario.getTxFirm1Lugar())) //

          // ----------------------------------------------------------------------------------------------------
          // DOMICILIO A EFECTOS DE NOTIFICACIÓN
          // ----------------------------------------------------------------------------------------------------
          .setParameter("soli1Tipovia", getParameterQuery(formulario.getTxSoli1Tipovia())) //
          .setParameter("soli1Nombrevia", getParameterQuery(formulario.getTxSoli1Nombrevia())) //
          .setParameter("soli1Numero", getParameterQuery(formulario.getTxSoli1Numero())) //
          .setParameter("soli1Letra", getParameterQuery(formulario.getTxSol1Letra())) //
          .setParameter("soli1Kmvia", getParameterQuery(formulario.getTxSoli1Kmvia())) //
          .setParameter("soli1Bloque", getParameterQuery(formulario.getTxSoli1Bloque())) //
          .setParameter("soli1Portal", getParameterQuery(formulario.getTxSoli1Portal())) //
          .setParameter("soli1Escalera", getParameterQuery(formulario.getTxSoli1Escalera())) //
          .setParameter("soli1Piso", getParameterQuery(formulario.getTxSoli1Piso())) //
          .setParameter("soli1Puerta", getParameterQuery(formulario.getTxSoli1Puerta())) //
          .setParameter("soli1Codprov", getParameterQuery(formulario.getNuSoli1Codprov())) //
          .setParameter("soli1Codmunicipio", getParameterQuery(formulario.getTxSoli1Codmunicipio())) //
          .setParameter("soli1Poblacion", getParameterQuery(formulario.getTxSoli1Poblacion())) //
          .setParameter("soli1Cp", getParameterQuery(formulario.getNuSoli1Cp())) //
          .setParameter("soli1Telefono", getParameterQuery(formulario.getNuSoli1Telefono())) //
          .setParameter("soli1Tlfmovil", getParameterQuery(formulario.getNuSoli1Tlfmovil())) //
          .setParameter("soli1Email", getParameterQuery(formulario.getTxSoli1Email())) //

          .setParameter("repr1Tipovia", getParameterQuery(formulario.getTxRepr1Tipovia())) //
          .setParameter("repr1Nombrevia", getParameterQuery(formulario.getTxRepr1Nombrevia())) //
          .setParameter("repr1Codprov", getParameterQuery(formulario.getNuRepr1Codprov())) //
          .setParameter("repr1Codmunicipio", getParameterQuery(formulario.getNuRepr1Codmunicipio())) //

          .executeUpdate();

      formulario.getDocumentosJA().stream().forEach(docJA -> //
      getEntityManager().createNativeQuery(INSERT_DOCS_PODER_JA).setParameter(1, getParameterQuery(docJA.getSolicitud().getIdSolicitud())) //
          .setParameter(2, getParameterQuery(docJA.getConsejeriaOrgano())) //
          .setParameter(3, docJA.getFecha() != null ? docJA.getFecha() : null, TemporalType.DATE) //
          .setParameter(4, getParameterQuery(docJA.getProcedimiento())) //
          .setParameter(5, getParameterQuery(docJA.getNombre())) //
          .executeUpdate());

      formulario.getDocumentosOtrasAdmin().stream().forEach(docOtraAdmin -> //
      getEntityManager().createNativeQuery(INSERT_DOCS_PODER_OTR_ADM).setParameter(1, getParameterQuery(docOtraAdmin.getSolicitud().getIdSolicitud())) //
          .setParameter(2, getParameterQuery(docOtraAdmin.getAdministracion())) //
          .setParameter(3, docOtraAdmin.getFecha() != null ? docOtraAdmin.getFecha() : null, TemporalType.DATE) //
          .setParameter(4, getParameterQuery(docOtraAdmin.getProcedimiento())) //
          .setParameter(5, getParameterQuery(docOtraAdmin.getNombre())) //
          .executeUpdate());

    } catch (final Exception e) {
      throw new AlegacionesAceptaReformulaException(
          "Se ha producido un error al guardar el FORMULARIO DE ALEGACIONES/ACEPTACIÓN/REFORMULACIÓN Y PRESENTACIÓN DE DOCUMENTOS de la solicitud de Universidades con id "
              + formulario.getIdSolicitud() + ENTREGADA_POR_VEAJA,
          e);
    }
  }

  @Override
  public void guardarAlegacionesAceptaReformulaONGD(final Solicitud formulario) throws AlegacionesAceptaReformulaException {

    try {
      final StringBuilder sqlSolicitud = new StringBuilder("UPDATE AACI_T_SOLICITUDSUBONGD SET") //

          // ----------------------------------------------------------------------------------------------------
          // 1 DATOS DE LA ENTIDAD SOLICITANTE Y DE LA PERSONA REPRESENTANTE
          // ----------------------------------------------------------------------------------------------------

          // SOLICITANTE
          .append(" TX_SOLI1NOMBRE = :soli1Nombre") // DENOMINACIÓN DE LA ENTIDAD
          .append(", TX_SOLI1CODIGO = :soli1Codigo") // Nº DE INSCRIPCIÓN EN EL RACDA
          .append(", TX_SOLI1NUMIDENT = :soli1Numident") // NIF
          .append(", TX_SOLI1SIGLAS = :soli1Siglas") // SIGLAS
          .append(", TX_SOLI1TIPOIDENT = :soli1Tipoident") // TIPO DE IDENTIDAD DE LA ENTIDAD
          .append(", TX_SOLI1APELLIDO1 = :soli1Apellido1") // APELLIDO 1 DE LA ENTIDAD
          .append(", TX_SOLI1APELLIDO2 = :soli1Apellido2") // APELLIDO 2 DE LA ENTIDAD
          .append(", TX_SOLI1SEXO = :soli1Sexo") // SEXO

          // REPRESENTANTE
          .append(", TX_REPR1APELLIDO1 = :repr1Apellido1") // APELLIDO 1 DE LA PERSONA REPRESENTANTE/RAZÓN SOCIAL/DENOMINACIÓN
          .append(", TX_REPR1APELLIDO2 = :repr1Apellido2") // APELLIDO 2 DE LA PERSONA REPRESENTANTE/RAZÓN SOCIAL/DENOMINACIÓN
          .append(", TX_REPR1NOMBRE = :repr1Nombre") // NOMBRE 1 DE LA PERSONA REPRESENTANTE/RAZÓN SOCIAL/DENOMINACIÓN
          .append(", TX_REPR1SEXO = :repr1Sexo") // SEXO
          .append(", TX_REPR1NUMIDENT = :repr1Numident") // DNI/NIE/NIF
          .append(", TX_REPR1TITULO = :repr1Titulo") // QUE ACTÚA EN CALIDAD DE
          .append(", TX_REPR1TIPOIDENT = :repr1Tipoident") // TIPO DE IDENTIDAD DE LA PERSONA REPRESENTANTE/RAZÓN SOCIAL/DENOMINACIÓN

          // ----------------------------------------------------------------------------------------------------
          // 2 NOTIFICACIÓN ELECTRÓNICA OBLIGATORIA
          // ----------------------------------------------------------------------------------------------------
          .append(", TX_NOTIFEMAILAUTLUGAR = :notifEmailAutLugar") // Correo electrónico
          .append(", TX_NOTIFTELEFONOAUTLUGAR = :notifTelefonoAutLugar") // Nº teléfono móvil
          .append(", LG_ALTANOFIFICA = :altaNotifica") // Manifiesto que dispongo de una dirección electrónica habilitada en el Sistema de
                                                       // Notificaciones Notific@.

          // ----------------------------------------------------------------------------------------------------
          // 3 DATOS BANCARIOS
          // ----------------------------------------------------------------------------------------------------
          .append(", TX_BANCO_IBAN = :bancoIban") // IBAN
          .append(", TX_BANCO_CODIGO = :bancoCodigo") // IBAN
          .append(", TX_BANCO_PAIS = :bancoPais") // IBAN
          .append(", TX_BANCO_LOCALIDAD = :bancoLocalidad") // IBAN
          .append(", TX_BANCO_SUCURSAL= :bancoSucursal") // IBAN
          .append(", TX_BANCO_NUMCUENTA = :bancoNumcuenta") // IBAN

          .append(", TX_BANCO_S_CODBANCO = :bancoSCodbanco") // SWIFT Código Banco
          .append(", TX_BANCO_S_PAIS = :bancoSPais") // SWIFT País
          .append(", TX_SWIFT_LOCALIDAD = :swiftLocalidad") // SWIFT Localidad
          .append(", TX_BANCO_S_SUCUR = :bancoSSucur") // SWIFT Sucursal

          .append(", TX_BANCO_ENTIDAD = :bancoEntidad") // Entidad
          .append(", TX_BANCO_DOMICILIO = :bancoDomicilio") // Domicilio
          .append(", TX_BANCO_CODMUNICIPIO = :bancoCodmunicipio") // Localidad
          .append(", TX_BANCO_CODPROV = :bancoCodprov") // Provincia
          .append(", TX_BANCO_CP = :bancoCp") // Código Postal

          // ----------------------------------------------------------------------------------------------------
          // 4 IDENTIFICACIÓN DEL PROYECTO PARA EL QUE SE SOLICITA LA SUBVENCIÓN
          // ----------------------------------------------------------------------------------------------------
          .append(", TX_DATOSTITULO = :datosTitulo") // TÍTULO DEL PROYECTO

          // ----------------------------------------------------------------------------------------------------
          // 5 ALEGACIONES/ACEPTACIÓN/REFORMULACIÓN
          // ----------------------------------------------------------------------------------------------------
          .append(", LG_CONCE_UNO = :conceUno") // CONCEDIDA COMO ENTIDAD BENEFICIARIA PROVISIONAL por el importe o pretensión solicitado
          .append(", LG_CONCE_DOS = :conceDos") // CONCEDIDA COMO ENTIDAD BENEFICIARIA PROVISIONAL por un importe o pretensión inferior al
                                                // solicitado
          .append(", LG_CONCE_TRES = :conceTres") // CONCEDIDA COMO ENTIDAD BENEFICIARIA SUPLENTE

          .append(", LG_CONCE_CUATRO = :conceCuatro") // ACEPTO la subvención propuesta.
          .append(", LG_CONCE_CINCO = :conceCinco") // DESISTO de la solicitud.
          .append(", LG_CONCE_SEIS = :conceSeis") // REFORMULO

          .append(", LG_CONCE_SIETE = :conceSiete") // NO ACEPTO la subvención propuesta y ALEGO lo siguiente
          .append(", LG_CONCE_OCHO = :conceOcho") // Otra/s (especificar)
          .append(", TX_REFOR = :refor") //
          .append(", TX_NO_ACEPTO = :noAcepto") //
          .append(", TX_OTRO_A = :otroA") //

          // ----------------------------------------------------------------------------------------------------
          // 6 DOCUMENTACIÓN
          // ----------------------------------------------------------------------------------------------------
          .append(", LG_DOC1 = :doc1") // Presupuesto validado firmado
          .append(", LG_DOC2 = :doc2") // Para el caso de reformulación del Presupuesto: Presupuesto reformulado
          .append(", LG_DOC3 = :doc3") // Para el caso de Agrupaciones: poderes de la persona representante de la agrupación
          .append(", LG_DOC4 = :doc4") // Declaración responsable sobre el cumplimiento del artículo 13.5 de la Ley Orgánica 1/1996, de
                                       // Protección Jurídica del Menor

          // ----------------------------------------------------------------------------------------------------
          // 7 DECLARACIÓN, LUGAR, FECHA Y FIRMA
          // ----------------------------------------------------------------------------------------------------
          .append(", TX_FIRM1FDO = :firm1Fdo") // FIRMANTE
          .append(", TX_FIRM1LUGAR = :firm1Lugar") // LUGAR DE LA FIRMA

          // ----------------------------------------------------------------------------------------------------
          // DOMICILIO A EFECTOS DE NOTIFICACIÓN
          // ----------------------------------------------------------------------------------------------------
          .append(", TX_SOLI1TIPOVIA = :soli1Tipovia") // TIPO DE VÍA
          .append(", TX_SOLI1NOMBREVIA = :soli1Nombrevia") // NOMBRE DE LA VÍA
          .append(", TX_SOLI1NUMERO = :soli1Numero") // NÚMERO
          .append(", TX_SOL1LETRA = :soli1Letra") // LETRA
          .append(", TX_SOLI1KMVIA = :soli1Kmvia") // KM EN LA VÍA
          .append(", TX_SOLI1BLOQUE = :soli1Bloque") // BLOQUE
          .append(", TX_SOLI1PORTAL = :soli1Portal") // PORTAL
          .append(", TX_SOLI1ESCALERA = :soli1Escalera") // ESCALERA
          .append(", TX_SOLI1PISO = :soli1Piso") // PISO
          .append(", TX_SOLI1PUERTA = :soli1Puerta") // PUERTA
          .append(", NU_SOLI1CODPROV = :soli1Codprov") // PROVINCIA
          .append(", TX_SOLI1CODMUNICIPIO = :soli1Codmunicipio") // MUNICIPIO
          .append(", TX_SOLI1POBLACION = :soli1Poblacion") // ENTIDAD DE POBLACIÓN
          .append(", NU_SOLI1CP = :soli1Cp") // CÓDIGO POSTAL
          .append(", NU_SOLI1TELEFONO = :soli1Telefono") // NÚMERO TELÉFONO
          .append(", NU_SOLI1TLFMOVIL = :soli1Tlfmovil") // NÚMERO MÓVIL
          .append(", TX_SOLI1EMAIL = :soli1Email") // CORREO ELECTRÓNICO

          .append(" WHERE ID_SOLICITUD = :idSolicitud");

      getEntityManager().createNativeQuery(sqlSolicitud.toString()) //
          .setParameter("idSolicitud", getParameterQuery(formulario.getIdSolicitud())) //

          // ----------------------------------------------------------------------------------------------------
          // 1 DATOS DE LA ENTIDAD SOLICITANTE Y DE LA PERSONA REPRESENTANTE
          // ----------------------------------------------------------------------------------------------------

          // SOLICITANTE
          .setParameter("soli1Nombre", getParameterQuery(formulario.getTxSoli1Nombre())) //
          .setParameter("soli1Codigo", getParameterQuery(formulario.getTxSoli1Codigo())) //
          .setParameter("soli1Numident", getParameterQuery(formulario.getTxSoli1Numident())) //
          .setParameter("soli1Siglas", getParameterQuery(formulario.getTxSoli1Siglas())) //
          .setParameter("soli1Tipoident", getParameterQuery(formulario.getTxSoli1Tipoident())) //
          .setParameter("soli1Apellido1", getParameterQuery(formulario.getTxSoli1Apellido1())) //
          .setParameter("soli1Apellido2", getParameterQuery(formulario.getTxSoli1Apellido2())) //
          .setParameter("soli1Sexo", getParameterQuery(formulario.getTxSoli1Sexo())) //

          // REPRESENTANTE
          .setParameter("repr1Apellido1", getParameterQuery(formulario.getTxRepr1Apellido1())) //
          .setParameter("repr1Apellido2", getParameterQuery(formulario.getTxRepr1Apellido2())) //
          .setParameter("repr1Nombre", getParameterQuery(formulario.getTxRepr1Nombre())) //
          .setParameter("repr1Sexo", getParameterQuery(formulario.getTxRepr1Sexo())) //
          .setParameter("repr1Numident", getParameterQuery(formulario.getTxRepr1Numident())) //
          .setParameter("repr1Titulo", getParameterQuery(formulario.getTxRepr1Titulo())) //
          .setParameter("repr1Tipoident", getParameterQuery(formulario.getTxRepr1Tipoident())) //

          // ----------------------------------------------------------------------------------------------------
          // 2 NOTIFICACIÓN ELECTRÓNICA OBLIGATORIA
          // ----------------------------------------------------------------------------------------------------
          .setParameter("notifEmailAutLugar", getParameterQuery(formulario.getTxNotifemailautlugar())) //
          .setParameter("notifTelefonoAutLugar", getParameterQuery(formulario.getTxNotiftelefonoautlugar())) //
          .setParameter("altaNotifica", getParameterQuery(formulario.getLgAltanofifica())) //

          // ----------------------------------------------------------------------------------------------------
          // 3 DATOS BANCARIOS
          // ----------------------------------------------------------------------------------------------------
          .setParameter("bancoIban", getParameterQuery(formulario.getTxBancoIban())) //
          .setParameter("bancoCodigo", getParameterQuery(formulario.getTxBancoCodigo())) //
          .setParameter("bancoPais", getParameterQuery(formulario.getTxBancoPais())) //
          .setParameter("bancoLocalidad", getParameterQuery(formulario.getTxBancoLocalidad())) //
          .setParameter("bancoSucursal", getParameterQuery(formulario.getTxBancoSucursal())) //
          .setParameter("bancoNumcuenta", getParameterQuery(formulario.getTxBancoNumCuenta())) //

          .setParameter("bancoSCodbanco", getParameterQuery(formulario.getTxBancoSwiftCodBanco())) //
          .setParameter("bancoSPais", getParameterQuery(formulario.getTxBancoSwiftPais())) //
          .setParameter("swiftLocalidad", getParameterQuery(formulario.getTxBancoSwiftLocalidad())) //
          .setParameter("bancoSSucur", getParameterQuery(formulario.getTxBancoSwiftSucur())) //

          .setParameter("bancoEntidad", getParameterQuery(formulario.getTxBancoEntidad())) //
          .setParameter("bancoDomicilio", getParameterQuery(formulario.getTxBancoDomicilio())) //
          .setParameter("bancoCodmunicipio", getParameterQuery(formulario.getTxBancoCodMunicipio())) //
          .setParameter("bancoCodprov", getParameterQuery(formulario.getTxBancoCodProv())) //
          .setParameter("bancoCp", getParameterQuery(formulario.getTxBancoCP())) //

          // ----------------------------------------------------------------------------------------------------
          // 4 IDENTIFICACIÓN DEL PROYECTO PARA EL QUE SE SOLICITA LA SUBVENCIÓN
          // ----------------------------------------------------------------------------------------------------
          .setParameter("datosTitulo", getParameterQuery(formulario.getTxDatostitulo())) //

          // ----------------------------------------------------------------------------------------------------
          // 5 ALEGACIONES/ACEPTACIÓN/REFORMULACIÓN
          // ----------------------------------------------------------------------------------------------------
          .setParameter("conceUno", getParameterQuery(formulario.getLgConceUno())) //
          .setParameter("conceDos", getParameterQuery(formulario.getLgConceDos())) //
          .setParameter("conceTres", getParameterQuery(formulario.getLgConceTres())) //

          .setParameter("conceCuatro", getParameterQuery(formulario.getLgConceCuatro())) //
          .setParameter("conceCinco", getParameterQuery(formulario.getLgConceCinco())) //
          .setParameter("conceSeis", getParameterQuery(formulario.getLgConceSeis())) //
          .setParameter("refor", getParameterQuery(formulario.getTxRefor())) //

          .setParameter("conceSiete", getParameterQuery(formulario.getLgConceSiete())) //
          .setParameter("noAcepto", getParameterQuery(formulario.getTxNoAcepto())) //
          .setParameter("conceOcho", getParameterQuery(formulario.getLgConceOcho())) //
          .setParameter("otroA", getParameterQuery(formulario.getTxOtroA())) //

          // ----------------------------------------------------------------------------------------------------
          // 6 DOCUMENTACIÓN
          // ----------------------------------------------------------------------------------------------------
          .setParameter("doc1", getParameterQuery(formulario.getLgDoc1())) //
          .setParameter("doc2", getParameterQuery(formulario.getLgDoc2())) //
          .setParameter("doc3", getParameterQuery(formulario.getLgDoc3())) //
          .setParameter("doc4", getParameterQuery(formulario.getLgDoc4())) //

          // ----------------------------------------------------------------------------------------------------
          // 7 DECLARACIÓN, LUGAR, FECHA Y FIRMA
          // ----------------------------------------------------------------------------------------------------
          .setParameter("firm1Fdo", getParameterQuery(formulario.getTxFirm1Fdo())) //
          .setParameter("firm1Lugar", getParameterQuery(formulario.getTxFirm1Lugar())) //

          // ----------------------------------------------------------------------------------------------------
          // DOMICILIO A EFECTOS DE NOTIFICACIÓN
          // ----------------------------------------------------------------------------------------------------
          .setParameter("soli1Tipovia", getParameterQuery(formulario.getTxSoli1Tipovia())) //
          .setParameter("soli1Nombrevia", getParameterQuery(formulario.getTxSoli1Nombrevia())) //
          .setParameter("soli1Numero", getParameterQuery(formulario.getTxSoli1Numero())) //
          .setParameter("soli1Letra", getParameterQuery(formulario.getTxSol1Letra())) //
          .setParameter("soli1Kmvia", getParameterQuery(formulario.getTxSoli1Kmvia())) //
          .setParameter("soli1Bloque", getParameterQuery(formulario.getTxSoli1Bloque())) //
          .setParameter("soli1Portal", getParameterQuery(formulario.getTxSoli1Portal())) //
          .setParameter("soli1Escalera", getParameterQuery(formulario.getTxSoli1Escalera())) //
          .setParameter("soli1Piso", getParameterQuery(formulario.getTxSoli1Piso())) //
          .setParameter("soli1Puerta", getParameterQuery(formulario.getTxSoli1Puerta())) //
          .setParameter("soli1Codprov", getParameterQuery(formulario.getNuSoli1Codprov())) //
          .setParameter("soli1Codmunicipio", getParameterQuery(formulario.getTxSoli1Codmunicipio())) //
          .setParameter("soli1Poblacion", getParameterQuery(formulario.getTxSoli1Poblacion())) //
          .setParameter("soli1Cp", getParameterQuery(formulario.getNuSoli1Cp())) //
          .setParameter("soli1Telefono", getParameterQuery(formulario.getNuSoli1Telefono())) //
          .setParameter("soli1Tlfmovil", getParameterQuery(formulario.getNuSoli1Tlfmovil())) //
          .setParameter("soli1Email", getParameterQuery(formulario.getTxSoli1Email())) //

          .executeUpdate();

      formulario.getDocumentosJA().stream().forEach(docJA -> //
      getEntityManager().createNativeQuery(INSERT_DOCS_PODER_JA).setParameter(1, getParameterQuery(docJA.getSolicitud().getIdSolicitud())) //
          .setParameter(2, getParameterQuery(docJA.getConsejeriaOrgano())) //
          .setParameter(3, docJA.getFecha() != null ? docJA.getFecha() : null, TemporalType.DATE) //
          .setParameter(4, getParameterQuery(docJA.getProcedimiento())) //
          .setParameter(5, getParameterQuery(docJA.getNombre())) //
          .executeUpdate());

      formulario.getDocumentosOtrasAdmin().stream().forEach(docOtraAdmin -> //
      getEntityManager().createNativeQuery(INSERT_DOCS_PODER_OTR_ADM).setParameter(1, getParameterQuery(docOtraAdmin.getSolicitud().getIdSolicitud())) //
          .setParameter(2, getParameterQuery(docOtraAdmin.getAdministracion())) //
          .setParameter(3, docOtraAdmin.getFecha() != null ? docOtraAdmin.getFecha() : null, TemporalType.DATE) //
          .setParameter(4, getParameterQuery(docOtraAdmin.getProcedimiento())) //
          .setParameter(5, getParameterQuery(docOtraAdmin.getNombre())) //
          .executeUpdate());

    } catch (final Exception e) {
      throw new AlegacionesAceptaReformulaException(
          "Se ha producido un error al guardar el FORMULARIO DE ALEGACIONES/ACEPTACIÓN/REFORMULACIÓN Y PRESENTACIÓN DE DOCUMENTOS de la solicitud de ONGD con id "
              + formulario.getIdSolicitud() + ENTREGADA_POR_VEAJA,
          e);
    }
  }

  @Override
  public void guardarRecepcionComunicacion(final Solicitud formulario) throws RecepcionComunicacionException {

    try {
      final StringBuilder sqlSolicitud = new StringBuilder("UPDATE AACI_T_SOLICITUDSUBONGD SET") //

          // ----------------------------------------------------------------------------------------------------
          // 1 DATOS DE LA ENTIDAD SOLICITANTE Y DE LA PERSONA REPRESENTANTE
          // ----------------------------------------------------------------------------------------------------

          // SOLICITANTE
          .append(" TX_SOLI1NOMBRE = :soli1Nombre") // DENOMINACIÓN DE LA ENTIDAD
          .append(", TX_SOLI1CODIGO = :soli1Codigo") // Nº DE INSCRIPCIÓN EN EL RACDA
          .append(", TX_SOLI1NUMIDENT = :soli1Numident") // NIF
          .append(", TX_SOLI1SIGLAS = :soli1Siglas") // SIGLAS
          .append(", TX_SOLI1TIPOIDENT = :soli1Tipoident") // TIPO DE IDENTIDAD DE LA ENTIDAD
          .append(", TX_SOLI1APELLIDO1 = :soli1Apellido1") // APELLIDO 1 DE LA ENTIDAD
          .append(", TX_SOLI1APELLIDO2 = :soli1Apellido2") // APELLIDO 2 DE LA ENTIDAD
          .append(", TX_SOLI1SEXO = :soli1Sexo") // SEXO

          // REPRESENTANTE
          .append(", TX_REPR1APELLIDO1 = :repr1Apellido1") // APELLIDO 1 DE LA PERSONA REPRESENTANTE/RAZÓN SOCIAL/DENOMINACIÓN
          .append(", TX_REPR1APELLIDO2 = :repr1Apellido2") // APELLIDO 2 DE LA PERSONA REPRESENTANTE/RAZÓN SOCIAL/DENOMINACIÓN
          .append(", TX_REPR1NOMBRE = :repr1Nombre") // NOMBRE 1 DE LA PERSONA REPRESENTANTE/RAZÓN SOCIAL/DENOMINACIÓN
          .append(", TX_REPR1SEXO = :repr1Sexo") // SEXO
          .append(", TX_REPR1NUMIDENT = :repr1Numident") // DNI/NIE/NIF
          .append(", TX_REPR1TITULO = :repr1Titulo") // QUE ACTÚA EN CALIDAD DE
          .append(", TX_REPR1TIPOIDENT = :repr1Tipoident") // TIPO DE IDENTIDAD DE LA PERSONA REPRESENTANTE/RAZÓN SOCIAL/DENOMINACIÓN
          .append(", TX_CALIDADDE= :calidadDe") // REPRESENTACION EN CALIDAD DE
          // ----------------------------------------------------------------------------------------------------
          // 2 DATOS DEL INICIO
          // ----------------------------------------------------------------------------------------------------
          .append(", PLAZO = :mesesEjecucion") // Plazo de ejecucion en meses
          .append(", FH_INICIOCOM = :fechaInicioCom") // Plazo de ejecucion en meses
          .append(", FH_FINCOM = :fechaFinCom") // Plazo de ejecucion en meses

          // ----------------------------------------------------------------------------------------------------
          // 3 IDENTIFICACION DEL PROYECTO PARA EL QUE SE SOLICITA LA SUBVENCION
          // ----------------------------------------------------------------------------------------------------
          .append(", TX_DATOSTITULO = :datosTitulo") // TÍTULO DEL PROYECTO

          // ----------------------------------------------------------------------------------------------------
          // 4 DECLARACION, LUGAR, FECHA Y FIRMA
          // ----------------------------------------------------------------------------------------------------
          .append(", TX_FIRM1FDO = :firm1Fdo") // FIRMANTE
          .append(", TX_FIRM1LUGAR = :firm1Lugar") // LUGAR DE LA FIRMA

          // ----------------------------------------------------------------------------------------------------
          // DOMICILIO A EFECTOS DE NOTIFICACIÓN
          // ----------------------------------------------------------------------------------------------------
          .append(", TX_SOLI1TIPOVIA = :soli1Tipovia") // TIPO DE VÍA
          .append(", TX_SOLI1NOMBREVIA = :soli1Nombrevia") // NOMBRE DE LA VÍA
          .append(", TX_SOLI1NUMERO = :soli1Numero") // NUÚMERO
          .append(", TX_SOL1LETRA = :soli1Letra") // LETRA
          .append(", TX_SOLI1KMVIA = :soli1Kmvia") // KM EN LA VÍA
          .append(", TX_SOLI1BLOQUE = :soli1Bloque") // BLOQUE
          .append(", TX_SOLI1PORTAL = :soli1Portal") // PORTAL
          .append(", TX_SOLI1ESCALERA = :soli1Escalera") // ESCALERA
          .append(", TX_SOLI1PISO = :soli1Piso") // PISO
          .append(", TX_SOLI1PUERTA = :soli1Puerta") // PUERTA
          .append(", NU_SOLI1CODPROV = :soli1Codprov") // PROVINCIA
          .append(", TX_SOLI1CODMUNICIPIO = :soli1Codmunicipio") // MUNICIPIO
          .append(", TX_SOLI1POBLACION = :soli1Poblacion") // ENTIDAD DE POBLACIÓN
          .append(", NU_SOLI1CP = :soli1Cp") // CODIGO POSTAL
          .append(", NU_SOLI1TELEFONO = :soli1Telefono") // NUMERO TELÉFONO
          .append(", NU_SOLI1TLFMOVIL = :soli1Tlfmovil") // NÚMERO MÓVIL
          .append(", TX_SOLI1EMAIL = :soli1Email") // CORREO ELECTRÓNICO

          .append(" WHERE ID_SOLICITUD = :idSolicitud");

      getEntityManager().createNativeQuery(sqlSolicitud.toString()) //
          .setParameter("idSolicitud", getParameterQuery(formulario.getIdSolicitud())) //

          // ----------------------------------------------------------------------------------------------------
          // 1 DATOS DE LA ENTIDAD SOLICITANTE Y DE LA PERSONA REPRESENTANTE
          // ----------------------------------------------------------------------------------------------------

          // SOLICITANTE
          .setParameter("soli1Nombre", getParameterQuery(formulario.getTxSoli1Nombre())) //
          .setParameter("soli1Codigo", getParameterQuery(formulario.getTxSoli1Codigo())) //
          .setParameter("soli1Numident", getParameterQuery(formulario.getTxSoli1Numident())) //
          .setParameter("soli1Siglas", getParameterQuery(formulario.getTxSoli1Siglas())) //
          .setParameter("soli1Tipoident", getParameterQuery(formulario.getTxSoli1Tipoident())) //
          .setParameter("soli1Apellido1", getParameterQuery(formulario.getTxSoli1Apellido1())) //
          .setParameter("soli1Apellido2", getParameterQuery(formulario.getTxSoli1Apellido2())) //
          .setParameter("soli1Sexo", getParameterQuery(formulario.getTxSoli1Sexo())) //

          // REPRESENTANTE
          .setParameter("repr1Apellido1", getParameterQuery(formulario.getTxRepr1Apellido1())) //
          .setParameter("repr1Apellido2", getParameterQuery(formulario.getTxRepr1Apellido2())) //
          .setParameter("repr1Nombre", getParameterQuery(formulario.getTxRepr1Nombre())) //
          .setParameter("repr1Sexo", getParameterQuery(formulario.getTxRepr1Sexo())) //
          .setParameter("repr1Numident", getParameterQuery(formulario.getTxRepr1Numident())) //
          .setParameter("repr1Titulo", getParameterQuery(formulario.getTxRepr1Titulo())) //
          .setParameter("repr1Tipoident", getParameterQuery(formulario.getTxRepr1Tipoident())) //
          .setParameter("calidadDe", getParameterQuery(formulario.getTxCalidadde())) //
          // ----------------------------------------------------------------------------------------------------
          // 2 DATOS DEL INICIO
          // ----------------------------------------------------------------------------------------------------
          .setParameter("mesesEjecucion", getParameterQuery(formulario.getPlazo().longValue())) //
          .setParameter("fechaInicioCom", formulario.getFhFechaInicio()) //
          .setParameter("fechaFinCom", formulario.getFhFechaFin()) //

          // ----------------------------------------------------------------------------------------------------
          // 4 IDENTIFICACIOÓN DEL PROYECTO PARA EL QUE SE SOLICITA LA SUBVENCIÓN
          // ----------------------------------------------------------------------------------------------------
          .setParameter("datosTitulo", getParameterQuery(formulario.getTxDatostitulo())) //

          // ----------------------------------------------------------------------------------------------------
          // 7 DECLARACION, LUGAR, FECHA Y FIRMA
          // ----------------------------------------------------------------------------------------------------
          .setParameter("firm1Fdo", getParameterQuery(formulario.getTxFirm1Fdo())) //
          .setParameter("firm1Lugar", getParameterQuery(formulario.getTxFirm1Lugar())) //

          // ----------------------------------------------------------------------------------------------------
          // DOMICILIO A EFECTOS DE NOTIFICACION
          // ----------------------------------------------------------------------------------------------------
          .setParameter("soli1Tipovia", getParameterQuery(formulario.getTxSoli1Tipovia())) //
          .setParameter("soli1Nombrevia", getParameterQuery(formulario.getTxSoli1Nombrevia())) //
          .setParameter("soli1Numero", getParameterQuery(formulario.getTxSoli1Numero())) //
          .setParameter("soli1Letra", getParameterQuery(formulario.getTxSol1Letra())) //
          .setParameter("soli1Kmvia", getParameterQuery(formulario.getTxSoli1Kmvia())) //
          .setParameter("soli1Bloque", getParameterQuery(formulario.getTxSoli1Bloque())) //
          .setParameter("soli1Portal", getParameterQuery(formulario.getTxSoli1Portal())) //
          .setParameter("soli1Escalera", getParameterQuery(formulario.getTxSoli1Escalera())) //
          .setParameter("soli1Piso", getParameterQuery(formulario.getTxSoli1Piso())) //
          .setParameter("soli1Puerta", getParameterQuery(formulario.getTxSoli1Puerta())) //
          .setParameter("soli1Codprov", getParameterQuery(formulario.getNuSoli1Codprov())) //
          .setParameter("soli1Codmunicipio", getParameterQuery(formulario.getTxSoli1Codmunicipio())) //
          .setParameter("soli1Poblacion", getParameterQuery(formulario.getTxSoli1Poblacion())) //
          .setParameter("soli1Cp", getParameterQuery(formulario.getNuSoli1Cp())) //
          .setParameter("soli1Telefono", getParameterQuery(formulario.getNuSoli1Telefono())) //
          .setParameter("soli1Tlfmovil", getParameterQuery(formulario.getNuSoli1Tlfmovil())) //
          .setParameter("soli1Email", getParameterQuery(formulario.getTxSoli1Email())) //

          .executeUpdate();

    } catch (final Exception e) {
      throw new RecepcionComunicacionException(
          "Se ha producido un error al guardar el FORMULARIO DE COMUNICACION DE INICIO Y PRESENTACION DE DOCUMENTOS de la solicitud de ONGD con id "
              + formulario.getIdSolicitud() + ENTREGADA_POR_VEAJA,
          e);
    }
  }

  private Integer getParameterQuery(final Boolean parametro) {
    return parametro != null ? UtilidadesBoolean.convertirBooleanNumero(parametro) : 0;
  }

  private TypedParameterValue getParameterQuery(final String parametro) {
    return new TypedParameterValue(StandardBasicTypes.STRING, parametro);
  }

  private TypedParameterValue getParameterQuery(final Long parametro) {
    return new TypedParameterValue(StandardBasicTypes.LONG, parametro);
  }

  /**
   * Establece el valor de la propiedad agrupacionDao
   *
   * @param agrupacionDao
   *          el agrupacionDao para establecer
   */
  public void setAgrupacionDao(final IAgrupacionDao agrupacionDao) {
    this.agrupacionDao = agrupacionDao;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Object> obtenerDatosExpedientesHijosProvConce(final List<String> listaIdTrewa) throws TramitacionException {
    try {
      final StringBuilder sqlString = new StringBuilder("SELECT " //
          + "   ats.NU_NUMEXPEDIENTE idTrewa, "// idTerwa
          + "   ats.TX_CODIDENTIFICATIVO ,"// Nº Expediente
          + "   ats.TX_DATOSTITULO ," // Titulo de la intervención
          + "   p.lugarIntervencion ," // Lugar de intervención
          + "   ats.TX_SOLI1NOMBRE Entidad, " // Entidad
          + "   ats.TX_SOLI1NUMIDENT CIF, " // CIF entidad
          + "   val.puntuacion, " // Puntuación
          + "   desest.exclusiones " // Desestimación
          + "FROM  AACI_T_SOLICITUDSUBONGD ats " // Tabla de solicitudes
          + "FULL OUTER JOIN AACI_SOLICITUDES_AUXILIAR asa ON asa.ID_SOLICITUD = ats.ID_SOLICITUD " // Tabla para las puntuaciones
          + "FULL OUTER JOIN (SELECT ats.NU_NUMEXPEDIENTE AS idTrewa, SUBSTR(rtrim (xmlagg (xmlelement(e,atp.TX_NOMBRE||', ')).extract ('//text()'), ' '), 1, LENGTH(rtrim (xmlagg (xmlelement(e,atp.TX_NOMBRE||', ')).extract ('//text()'), ' ')) - 1 ) "
          + "     AS lugarIntervencion " //
          + "   FROM AACI_T_SOLICITUDSUBONGD ats " //
          + "   FULL OUTER JOIN AACI_T_PAISES_SOLICITUD atps ON atps.ID_SOLICITUD = ats.ID_SOLICITUD "
          + "   FULL OUTER JOIN AACI_T_PAISES atp ON atp.ID_PAIS = atps.ID_PAIS " //
          + "   GROUP BY NU_NUMEXPEDIENTE) p ON p.idTrewa=ats.NU_NUMEXPEDIENTE "
          + "FULL OUTER JOIN (SELECT ats.NU_NUMEXPEDIENTE AS idTrewa ,SUBSTR(rtrim (xmlagg (xmlelement(e,ae.excl_nu_orden||', ')).extract ('//text()'), ' '), 1, LENGTH(rtrim (xmlagg (xmlelement(e,ae.excl_nu_orden||', ')).extract ('//text()'), ' ')) - 1 ) AS exclusiones "
          + "   FROM AACI_T_SOLICITUDSUBONGD ats " //
          + "   INNER JOIN AACI_EXCLUSIONES_SOLICITUD aes ON ats.ID_SOLICITUD = aes.SOLI_NU_ID " //
          + "   INNER JOIN AACI_EXCLUSION ae ON ae.EXCL_NU_ID =aes.EXCL_NU_ID " //
          + "   INNER JOIN AACI_TIPO_EXCLUSION ate ON ate.TEXC_NU_ID = ae.TEXC_NU_ID " //
          + "   WHERE ate.TEXC_NU_TIPO = 3 " //
          + "   GROUP BY NU_NUMEXPEDIENTE) desest ON desest.idTrewa = ats.NU_NUMEXPEDIENTE "//
          + "FULL OUTER JOIN (SELECT ats.NU_NUMEXPEDIENTE idTrewa, "//
          + "  sum(socr_Nu_puntuacion) puntuacion "//
          + "  FROM AACI_T_SOLICITUDSUBONGD ats "//
          + "  INNER JOIN AACI_SOLICITUD_CRITERIO asc2 "//
          + "          ON ats.ID_SOLICITUD = asc2.ID_SOLICITUD "//
          + "  GROUP BY  ats.NU_NUMEXPEDIENTE) val ON  val.idTrewa=ats.NU_NUMEXPEDIENTE "//
          + "FULL OUTER JOIN AACI_SOLICITUD_CONCESION beneSupl on beneSupl.SOCO_NU_SOLICITUD = ats.ID_SOLICITUD "//
          + "WHERE ats.NU_NUMEXPEDIENTE IN (:idExpTrewa) ");

      return getEntityManager().createNativeQuery(sqlString.toString()).setParameter(ID_EXP_TREWA, listaIdTrewa).getResultList();
    } catch (final NoResultException nre) {
      return new ArrayList<>();
    } catch (final Exception e) {
      throw new TramitacionException(e.getMessage(), e);
    }
  }

}
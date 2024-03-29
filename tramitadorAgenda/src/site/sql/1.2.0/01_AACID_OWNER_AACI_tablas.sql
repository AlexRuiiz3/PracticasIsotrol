SPOOL 01_AACID_OWNER_AACI_tablas.log
    
DEF USUARIO=AACID_OWNER;
DEF TABLESPACE_DATOS=TS_D_AACID_OWNER;
    
 CREATE TABLE "&USUARIO".AACI_T_CRITERIOS (
    TCRI_NU_ID  NUMBER(10,0),
    TCON_NU_ID  NUMBER(10,0),
    FINA_NU_ID  NUMBER(10,0),
    TCRI_TX_NOMBRE VARCHAR2(200 CHAR)
    )
    TABLESPACE "&TABLESPACE_DATOS";
    
		COMMENT ON COLUMN "&USUARIO".AACI_T_CRITERIOS.TCRI_NU_ID  IS 'Identificador tipo de criterio';
		COMMENT ON COLUMN "&USUARIO".AACI_T_CRITERIOS.TCON_NU_ID  IS 'FK tipo de convocatoria';
		COMMENT ON COLUMN "&USUARIO".AACI_T_CRITERIOS.FINA_NU_ID  IS 'FK finalidad del proyecto';
	  COMMENT ON COLUMN "&USUARIO".AACI_T_CRITERIOS.TCRI_TX_NOMBRE  IS 'Nombre del tipo criterio';

 CREATE TABLE "&USUARIO".AACI_CRITERIOS (
    CRIT_NU_ID  NUMBER(10,0),
    TCRI_NU_ID  NUMBER(10,0),
    CRIT_TX_DESCRIPCION  VARCHAR2(4000 CHAR),
    CRIT_NU_ANIO NUMBER(4,0),
    CRIT_NU_VAL_MAX  NUMBER(4,2),
    CRIT_SUB_FINALIDAD VARCHAR2(3)
    )
    TABLESPACE "&TABLESPACE_DATOS";
    
		COMMENT ON COLUMN "&USUARIO".AACI_CRITERIOS.CRIT_NU_ID IS 'Identificador del criterio';
		COMMENT ON COLUMN "&USUARIO".AACI_CRITERIOS.TCRI_NU_ID  IS 'FK del tipo de criterio';
		COMMENT ON COLUMN "&USUARIO".AACI_CRITERIOS.CRIT_TX_DESCRIPCION IS 'Descripci�n del criterio';
    COMMENT ON COLUMN "&USUARIO".AACI_CRITERIOS.CRIT_NU_ANIO IS 'A�o al que pertenece el criterio';
    COMMENT ON COLUMN "&USUARIO".AACI_CRITERIOS.CRIT_NU_VAL_MAX IS 'Valoraci�n m�xima del criterio';
    COMMENT ON COLUMN "&USUARIO".AACI_CRITERIOS.CRIT_SUB_FINALIDAD IS 'Indicaci�n del subcrierio';
    
    
 CREATE TABLE "&USUARIO".AACI_SOLICITUD_CRITERIO (
    SOCR_NU_ID  NUMBER(10,0),
    ID_SOLICITUD  NUMBER(10,0),
    CRIT_NU_ID NUMBER(10,0),
    SOCR_NU_VALOR NUMBER(2,0),
    SOCR_NU_PUNTUACION NUMBER(10,3)
    )
    TABLESPACE "&TABLESPACE_DATOS";
    
		COMMENT ON COLUMN "&USUARIO".AACI_SOLICITUD_CRITERIO.SOCR_NU_ID IS 'Identificador criterio de la solicitud';
		COMMENT ON COLUMN "&USUARIO".AACI_SOLICITUD_CRITERIO.ID_SOLICITUD  IS 'FK de la solicitud';
    COMMENT ON COLUMN "&USUARIO".AACI_SOLICITUD_CRITERIO.CRIT_NU_ID  IS 'FK del criterio';
    COMMENT ON COLUMN "&USUARIO".AACI_SOLICITUD_CRITERIO.SOCR_NU_VALOR  IS 'Valor del criterio de la solicitud';
    COMMENT ON COLUMN "&USUARIO".AACI_SOLICITUD_CRITERIO.SOCR_NU_PUNTUACION  IS 'Valor del criterio de la solicitud';
    
 CREATE TABLE "&USUARIO".AACI_SOLICITUDES_AUXILIAR (
    SOAU_NU_ID  NUMBER(10,0),
    ID_SOLICITUD  NUMBER(10,0),
    SOAU_TX_OBSERVACIONES_VAL VARCHAR2(4000 CHAR),
    SOAU_NU_NUM_REINTEGROS NUMBER(3,0),
    SOAU_NU_CAUSA_REINTEGRO NUMBER(1,0),
    SOAU_NU_MAGNITUD_REINTEGRO NUMBER(1,0)
    )
    TABLESPACE "&TABLESPACE_DATOS";
    
		COMMENT ON COLUMN "&USUARIO".AACI_SOLICITUDES_AUXILIAR.SOAU_NU_ID IS 'Identificador solicitudes auxiliar';
		COMMENT ON COLUMN "&USUARIO".AACI_SOLICITUDES_AUXILIAR.ID_SOLICITUD  IS 'FK de la solicitud';
    COMMENT ON COLUMN "&USUARIO".AACI_SOLICITUDES_AUXILIAR.SOAU_TX_OBSERVACIONES_VAL  IS 'Observaciones valorador para solicitudes auxiliar';
    COMMENT ON COLUMN "&USUARIO".AACI_SOLICITUDES_AUXILIAR.SOAU_NU_NUM_REINTEGROS  IS 'N�mero de reintegros solicitudes auxiliar';
    COMMENT ON COLUMN "&USUARIO".AACI_SOLICITUDES_AUXILIAR.SOAU_NU_CAUSA_REINTEGRO  IS 'N�mero de causas de reintegro solicitudes auxiliar';
    COMMENT ON COLUMN "&USUARIO".AACI_SOLICITUDES_AUXILIAR.SOAU_NU_MAGNITUD_REINTEGRO  IS 'N�mero de magnitud de reintegro solicitudes auxiliar';
    
 CREATE TABLE "&USUARIO".AACI_SOLICITUD_TCRITERIO (
    SOTC_NU_ID  NUMBER(10,0),
    ID_SOLICITUD  NUMBER(10,0),
    TCRI_NU_ID  NUMBER(10,0),
    SOTC_TX_OBSERVACIONES VARCHAR2(4000 CHAR),
    SOTC_NU_PUNTUACION_TOTAL NUMBER(10,3)
    )
    TABLESPACE "&TABLESPACE_DATOS";
    
    COMMENT ON COLUMN "&USUARIO".AACI_SOLICITUD_TCRITERIO.SOTC_NU_ID IS 'Identificador de la solicitud tipo criterio';
    COMMENT ON COLUMN "&USUARIO".AACI_SOLICITUD_TCRITERIO.ID_SOLICITUD  IS 'FK de la solicitud';
    COMMENT ON COLUMN "&USUARIO".AACI_SOLICITUD_TCRITERIO.TCRI_NU_ID  IS 'FK del tipo de criterio';
    COMMENT ON COLUMN "&USUARIO".AACI_SOLICITUD_TCRITERIO.SOTC_TX_OBSERVACIONES  IS 'Observaciones de los tipos de criterio de la solicitud';
    COMMENT ON COLUMN "&USUARIO".AACI_SOLICITUD_TCRITERIO.SOTC_NU_PUNTUACION_TOTAL  IS 'Valor total de los tipos de criterio de la solicitud';
    
    
 CREATE TABLE "&USUARIO".AACI_FINALIDAD (
    FINA_NU_ID  NUMBER(10,0),
    FINA_TX_DESCRIPCION  VARCHAR2(150 CHAR),
    FINA_CO_CODIGO VARCHAR2(50 CHAR),
    TCON_NU_ID NUMBER(10,0)
    )
    TABLESPACE "&TABLESPACE_DATOS";
    
    COMMENT ON COLUMN "&USUARIO".AACI_FINALIDAD.FINA_NU_ID IS 'Identificador de la finalidad';
    COMMENT ON COLUMN "&USUARIO".AACI_FINALIDAD.FINA_TX_DESCRIPCION  IS 'Descripci�n de la finalidad';
    COMMENT ON COLUMN "&USUARIO".AACI_FINALIDAD.FINA_CO_CODIGO  IS 'C�digo de la finalidad';
    COMMENT ON COLUMN "&USUARIO".AACI_FINALIDAD.TCON_NU_ID  IS 'FK tipo de convocatoria';
	
  CREATE TABLE "&USUARIO".AACI_GASTOS_FINALIDAD (
    GAFI_NU_ID  NUMBER(10,0),
    FINA_NU_ID  NUMBER(10,0)
    )
    TABLESPACE "&TABLESPACE_DATOS";
    
    COMMENT ON COLUMN "&USUARIO".AACI_GASTOS_FINALIDAD.GAFI_NU_ID IS 'Identificador de gastos finalidad';
    COMMENT ON COLUMN "&USUARIO".AACI_GASTOS_FINALIDAD.FINA_NU_ID  IS 'FK de la finalidad';
    
 CREATE TABLE "&USUARIO".AACI_GASTOS (
    GAST_NU_ID  NUMBER(10,0),
    GAST_TX_DESCRIPCION  VARCHAR2(150 CHAR),
    GAST_CO_CODIGO VARCHAR2(50 CHAR),
    GAST_NU_ORDEN NUMBER(10,0)
    )
    TABLESPACE "&TABLESPACE_DATOS";
    
    COMMENT ON COLUMN "&USUARIO".AACI_GASTOS.GAST_NU_ID IS 'Identificador del gasto';
    COMMENT ON COLUMN "&USUARIO".AACI_GASTOS.GAST_TX_DESCRIPCION  IS 'Descripci�n del gasto';
    COMMENT ON COLUMN "&USUARIO".AACI_GASTOS.GAST_CO_CODIGO  IS 'C�digo del gasto';
    COMMENT ON COLUMN "&USUARIO".AACI_GASTOS.GAST_NU_ORDEN  IS 'N�mero de orden del gasto';
    
 CREATE TABLE "&USUARIO".AACI_FUNCIONES (
    FUNC_NU_ID  NUMBER(10,0),
    FUNC_TX_DESCRIPCION VARCHAR2(250 CHAR)
    )
    TABLESPACE "&TABLESPACE_DATOS";
    
    COMMENT ON COLUMN "&USUARIO".AACI_FUNCIONES.FUNC_NU_ID IS 'Identificador de la funci�n';
    COMMENT ON COLUMN "&USUARIO".AACI_FUNCIONES.FUNC_TX_DESCRIPCION  IS 'Descripci�n de la funci�n';
    
 CREATE TABLE "&USUARIO".AACI_T_ENTIDAD_PARTICIPANTE (
    TEPA_NU_ID  NUMBER(10,0),
    TEPA_TX_DESCRIPCION VARCHAR2(250 CHAR)
    )
    TABLESPACE "&TABLESPACE_DATOS";
    
    COMMENT ON COLUMN "&USUARIO".AACI_T_ENTIDAD_PARTICIPANTE.TEPA_NU_ID IS 'Identificador del tipo de entidad participante';
    COMMENT ON COLUMN "&USUARIO".AACI_T_ENTIDAD_PARTICIPANTE.TEPA_TX_DESCRIPCION  IS 'Descripci�n del tipo de entidad participante';
    
 CREATE TABLE "&USUARIO".AACI_ENTIDADES_PARTICIPANTES (
    ENPA_NU_ID  NUMBER(10,0),
    ENPA_TX_NOMBRE  VARCHAR2(50 CHAR),
    ENPA_TX_OTRA_FUNCION VARCHAR2(50 CHAR),
    FUNC_NU_ID NUMBER (10,0),
    ID_SOLICITUD NUMBER (10,0),
    TEPA_NU_ID NUMBER(10,0)
    )
    TABLESPACE "&TABLESPACE_DATOS";
      
    COMMENT ON COLUMN "&USUARIO".AACI_ENTIDADES_PARTICIPANTES.ENPA_NU_ID IS 'Identificador de la entidad participante';
    COMMENT ON COLUMN "&USUARIO".AACI_ENTIDADES_PARTICIPANTES.ENPA_TX_NOMBRE  IS 'Nombre de la entidad participante';
    COMMENT ON COLUMN "&USUARIO".AACI_ENTIDADES_PARTICIPANTES.ENPA_TX_OTRA_FUNCION  IS 'Otra funci�n de la entidad participante';
    COMMENT ON COLUMN "&USUARIO".AACI_ENTIDADES_PARTICIPANTES.FUNC_NU_ID  IS 'FK de Funciones';
    COMMENT ON COLUMN "&USUARIO".AACI_ENTIDADES_PARTICIPANTES.ID_SOLICITUD  IS 'FK de Solicitudes';
    COMMENT ON COLUMN "&USUARIO".AACI_ENTIDADES_PARTICIPANTES.TEPA_NU_ID  IS 'FK de Tipo de entidad participante';
    
 CREATE TABLE "&USUARIO".AACI_TM_CAPACIDAD_GESTION (
    TMCG_NU_ID  NUMBER(10,0),
    TMCG_TX_CIF  VARCHAR2(200 CHAR),
    TMCG_TX_ENT  VARCHAR2(1000 CHAR),
    TMCG_NU_PUNT_TOTAL NUMBER(10,3)
    )
    TABLESPACE "&TABLESPACE_DATOS";
    
		COMMENT ON COLUMN "&USUARIO".AACI_TM_CAPACIDAD_GESTION.TMCG_NU_ID  IS 'Clave primaria de la tabla';
		COMMENT ON COLUMN "&USUARIO".AACI_TM_CAPACIDAD_GESTION.TMCG_TX_CIF  IS 'Cif perteneciente a la capacidad de gestion';
		COMMENT ON COLUMN "&USUARIO".AACI_TM_CAPACIDAD_GESTION.TMCG_TX_ENT  IS 'Entidad perteneciente a la capacidad de gestion';
    COMMENT ON COLUMN "&USUARIO".AACI_TM_CAPACIDAD_GESTION.TMCG_NU_PUNT_TOTAL  IS 'Puntuacion total de la capacidad de gestion';
    
  ALTER TABLE AACID_OWNER.AACI_EXCLUSIONES_SOLICITUD ADD ID NUMBER (19);

	
  CREATE TABLE "&USUARIO".AACI_T_PAISES (
	ID_PAIS NUMBER(19,0),
	TX_CODIGO VARCHAR2(100),
	TX_NOMBRE VARCHAR2(500),
	NU_ANIO NUMBER(5,0),
	NU_PUNTUACION NUMBER(3,2))
	TABLESPACE "&TABLESPACE_DATOS";
	
	COMMENT ON COLUMN "&USUARIO".AACI_T_PAISES.ID_PAIS IS 'Identificador deL pais';
	COMMENT ON COLUMN "&USUARIO".AACI_T_PAISES.TX_CODIGO IS 'Codigo del pais';
	COMMENT ON COLUMN "&USUARIO".AACI_T_PAISES.TX_NOMBRE IS 'Nombre del pais';
	COMMENT ON COLUMN "&USUARIO".AACI_T_PAISES.NU_ANIO IS 'Anio del registro';
	COMMENT ON COLUMN "&USUARIO".AACI_T_PAISES.NU_PUNTUACION IS 'Numero de puntuacion del pais';
	
  CREATE TABLE "&USUARIO".AACI_T_PAISES_SOLICITUD (
	ID_SOLICITUD NUMBER(10,0),
	ID_PAIS NUMBER(10,0))
	TABLESPACE "&TABLESPACE_DATOS";
	
	COMMENT ON COLUMN "&USUARIO".AACI_T_PAISES_SOLICITUD.ID_SOLICITUD IS 'Identificador de la solicitud asociada';
	COMMENT ON COLUMN "&USUARIO".AACI_T_PAISES_SOLICITUD.ID_PAIS IS 'Identificador del pais asociado';

SPOOL OFF
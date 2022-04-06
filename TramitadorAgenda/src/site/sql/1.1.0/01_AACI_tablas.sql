SPOOL 01_AACI_tablas.log
	
DEF USUARIO=AACID_OWNER;
DEF TABLESPACE_DATOS=TS_D_AACID_OWNER;
    
 CREATE TABLE "&USUARIO".AACI_AGRUPACIONES (
    AGRU_NU_ID NUMBER(19,0), 
    ID_SOLICITUD NUMBER (19,0),
	AGRU_LI_DENOMINACION VARCHAR2(500),
	AGRU_LI_SIGLAS VARCHAR2(15),
	AGRU_CO_INSCRIPCION VARCHAR2(50),
	AGRU_LI_NIF VARCHAR2(10), 
	AGRU_LI_ONGD_LIDER VARCHAR2(500))
	TABLESPACE "&TABLESPACE_DATOS";

	COMMENT ON COLUMN "&USUARIO".AACI_AGRUPACIONES.AGRU_NU_ID IS 'Identificador de la agrupacion';
	COMMENT ON COLUMN "&USUARIO".AACI_AGRUPACIONES.ID_SOLICITUD IS 'Clave ajena de la solicitud';
	COMMENT ON COLUMN "&USUARIO".AACI_AGRUPACIONES.AGRU_LI_DENOMINACION IS 'Denominacion de la agrupacion';
	COMMENT ON COLUMN "&USUARIO".AACI_AGRUPACIONES.AGRU_LI_SIGLAS IS 'Siglas de la agrupacion';
	COMMENT ON COLUMN "&USUARIO".AACI_AGRUPACIONES.AGRU_CO_INSCRIPCION IS 'Inscripcion de la agrupacion';
	COMMENT ON COLUMN "&USUARIO".AACI_AGRUPACIONES.AGRU_LI_NIF IS 'NIF de la agrupacion';
	COMMENT ON COLUMN "&USUARIO".AACI_AGRUPACIONES.AGRU_LI_ONGD_LIDER IS 'ONG_LIDER';
	
	CREATE TABLE "&USUARIO".AACI_ENTIDAD_CONTRAPARTE (
    ENTI_NU_ID NUMBER(10,0),
    ENTI_LI_NOMBRE VARCHAR2(4000))
    TABLESPACE "&TABLESPACE_DATOS";
    
    COMMENT ON COLUMN "&USUARIO".AACI_ENTIDAD_CONTRAPARTE.ENTI_NU_ID IS 'Identificador de la entidad';
    COMMENT ON COLUMN "&USUARIO".AACI_ENTIDAD_CONTRAPARTE.ENTI_LI_NOMBRE IS 'Nombre de la entidad';

    CREATE TABLE "&USUARIO".AACI_CONTRAPARTES (
    CONT_NU_ID NUMBER(19,0),
    CONT_LI_CONTRAPARTE VARCHAR2(50),
    CONT_LI_OTRAS VARCHAR2(250),
    CONT_FK_SOLICITUD NUMBER(19,0),
    ENTI_NU_ID NUMBER(19,0),
	CONT_LI_LOCALIDAD VARCHAR2(1000),
	CONT_LI_PAIS VARCHAR2(1000)
    )
    TABLESPACE "&TABLESPACE_DATOS";
    
	COMMENT ON COLUMN "&USUARIO".AACI_CONTRAPARTES.CONT_NU_ID             IS 'Identificador de la contraparte';
	COMMENT ON COLUMN "&USUARIO".AACI_CONTRAPARTES.CONT_LI_CONTRAPARTE         IS 'Nombre de la contraparte';
	COMMENT ON COLUMN "&USUARIO".AACI_CONTRAPARTES.CONT_LI_OTRAS            IS 'Descripcion de la contraparte';
	COMMENT ON COLUMN "&USUARIO".AACI_CONTRAPARTES.CONT_FK_SOLICITUD           IS 'Clave ajena de la solicitud';
	COMMENT ON COLUMN "&USUARIO".AACI_CONTRAPARTES.ENTI_NU_ID 				IS 'Clave ajena de la entidad';
	COMMENT ON COLUMN "&USUARIO".AACI_CONTRAPARTES.CONT_LI_LOCALIDAD   IS 'Localidad de la contraparte';
	COMMENT ON COLUMN "&USUARIO".AACI_CONTRAPARTES.CONT_LI_PAIS   IS 'Pa�s de la contraparte';
	
	CREATE TABLE "&USUARIO".AACI_CONTRIBUCIONES (
    ID_CONTRIBUCION NUMBER(10,0),
    TOTAL FLOAT,
    CONTRAPARTE FLOAT,
    AACID FLOAT,
    PUBLICAS FLOAT,
    SOLICITANTE FLOAT,
    PRIVADAS FLOAT,
    LOCALES_PUBLICAS FLOAT,
    LOCALES_PRIVADAS FLOAT,
    SUBTOTAL_EXTERIOR FLOAT,
    SUBTOTAL_LOCAL FLOAT,
    FK_COSTES NUMBER(19,0),
    FK_PROYECTOS NUMBER(19,0),
    FK_PROGRAMACION NUMBER(19,0),
    AACID_VALIDADA NUMBER(10,2),
    SOLICITANTE_VALIDADA NUMBER(10,2),
    AACID_NO_VALIDADA NUMBER(10,2),
    SOLICITANTE_NO_VALIDADA NUMBER(10,2),
    AACID_EMERGENCIA NUMBER(10,2),
    SOLICITANTE_EMERGENCIA NUMBER(10,2))
    TABLESPACE "&TABLESPACE_DATOS";
    
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.ID_CONTRIBUCION IS 'Identificador de la contribucion';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.TOTAL IS 'Total de la contribucion';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.CONTRAPARTE IS 'Contraparte de la contribucion';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.AACID IS 'aacid de la contribucion';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.PUBLICAS IS 'Contribuciones de caracter publico';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.SOLICITANTE IS 'solicitante de la contribucion';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.PRIVADAS IS 'Contribucion de caracter privado';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.LOCALES_PUBLICAS IS 'Contribucion de caracter local publico';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.LOCALES_PRIVADAS IS 'Contribuciones de caracter local privado';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.SUBTOTAL_EXTERIOR IS 'Subtotal exterior de la contribucion';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.SUBTOTAL_LOCAL IS 'Subtotal local de la contribucion';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.FK_COSTES IS 'Clave ajena de la tabla costes';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.FK_PROYECTOS IS 'Clave ajena de la tabla proyectos';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.FK_PROGRAMACION IS 'Clave ajena de la tabla programacion';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.AACID_VALIDADA IS 'aacid validada de la contribucion';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.SOLICITANTE_VALIDADA IS 'Solicitante validada de la contribucion';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.AACID_NO_VALIDADA IS 'aacid no validada de la contribucion';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.SOLICITANTE_NO_VALIDADA IS 'solicitante no validada de la contribucion';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.AACID_EMERGENCIA IS 'aacid de emergencia de la contribucion';
    COMMENT ON COLUMN "&USUARIO".AACI_CONTRIBUCIONES.SOLICITANTE_EMERGENCIA IS 'solicitante de emergencia de la contribucion';



CREATE TABLE "&USUARIO".AACI_CON_CONTRAPARTE (
    ID_CON_CONTRA NUMBER(19,0), 
    CONTRIBUCION FLOAT,
	FK_CONTRIBUCION NUMBER(19,0),
	FK_ENTIDAD NUMBER(19,0),
	FK_PROGRAMACION NUMBER(10,0),
	LOCALIDAD VARCHAR2(1000), 
	PAIS VARCHAR2(1000),
	CONTRIBUCION_VALIDADA NUMBER(10,2),
	CONTRIBUCION_NO_VALIDADA NUMBER(10,2),
	CONTRAPARTE_EMERGENCIA NUMBER(10,2))
	TABLESPACE "&TABLESPACE_DATOS";

	COMMENT ON COLUMN "&USUARIO".AACI_CON_CONTRAPARTE.ID_CON_CONTRA IS 'Identificador de la entidad';
	COMMENT ON COLUMN "&USUARIO".AACI_CON_CONTRAPARTE.CONTRIBUCION IS 'Contribucion de la contraparte';
	COMMENT ON COLUMN "&USUARIO".AACI_CON_CONTRAPARTE.FK_CONTRIBUCION IS 'Clave ajena de la tabla CONTRIBUCION';
	COMMENT ON COLUMN "&USUARIO".AACI_CON_CONTRAPARTE.FK_ENTIDAD IS 'Entidad relacionada a la contraparte';
	COMMENT ON COLUMN "&USUARIO".AACI_CON_CONTRAPARTE.FK_PROGRAMACION IS 'Clave ajena de la tabla PROGRAMACION';
	COMMENT ON COLUMN "&USUARIO".AACI_CON_CONTRAPARTE.LOCALIDAD IS 'Localidad de la contraparte';
	COMMENT ON COLUMN "&USUARIO".AACI_CON_CONTRAPARTE.PAIS IS 'Pais de la contraparte';
	COMMENT ON COLUMN "&USUARIO".AACI_CON_CONTRAPARTE.CONTRIBUCION_VALIDADA IS 'Contribucion ya validada de la contraparte';
	COMMENT ON COLUMN "&USUARIO".AACI_CON_CONTRAPARTE.CONTRIBUCION_NO_VALIDADA IS 'Contribucion no validada de la contraparte';
	COMMENT ON COLUMN "&USUARIO".AACI_CON_CONTRAPARTE.CONTRAPARTE_EMERGENCIA IS 'Contraparte de emergencia';



    ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD (LG_CHECKTIPO3 NUMBER(1,0), LG_CHECKTIPO4 NUMBER(1,0));

    COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.LG_CHECKTIPO3 IS 'LG_CHECKTIPO3';
    COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.LG_CHECKTIPO4 IS 'LG_CHECKTIPO4';

    
    CREATE TABLE "&USUARIO".AACI_CON_OTRAS (
    ID_CON_OTRA NUMBER(19,0), 
    CONTRIBUCION FLOAT,
	FK_CONTRIBUCION NUMBER(19,0),
	FK_ENTIDAD NUMBER(19,0),
	FK_PROGRAMACION NUMBER(10,0),
	CONTRIBUCION_VALIDADA NUMBER(10,2),
	CONTRIBUCION_NO_VALIDADA NUMBER(10,2),
	OTRAS_EMERGENCIA NUMBER(10,2))
	TABLESPACE "&TABLESPACE_DATOS";

	COMMENT ON COLUMN "&USUARIO".AACI_CON_OTRAS.ID_CON_OTRA IS 'Identificador de la entidad';
	COMMENT ON COLUMN "&USUARIO".AACI_CON_OTRAS.CONTRIBUCION IS 'Contribucion de la contraparte';
	COMMENT ON COLUMN "&USUARIO".AACI_CON_OTRAS.FK_CONTRIBUCION IS 'Clave ajena de la tabla CONTRIBUCION';
	COMMENT ON COLUMN "&USUARIO".AACI_CON_OTRAS.FK_ENTIDAD IS 'Entidad relacionada a la contraparte';
	COMMENT ON COLUMN "&USUARIO".AACI_CON_OTRAS.FK_PROGRAMACION IS 'Clave ajena de la tabla PROGRAMACION';
	COMMENT ON COLUMN "&USUARIO".AACI_CON_OTRAS.CONTRIBUCION_VALIDADA IS 'Contribucion ya validada de la contraparte';
	COMMENT ON COLUMN "&USUARIO".AACI_CON_OTRAS.CONTRIBUCION_NO_VALIDADA IS 'Contribucion no validada de la contraparte';
	COMMENT ON COLUMN "&USUARIO".AACI_CON_OTRAS.OTRAS_EMERGENCIA IS 'Contraparte de emergencia';
	
SPOOL OFF
SPOOL 01_AACID_OWNER_AACI_tablas.log
    
DEF USUARIO=AACID_OWNER;
DEF TABLESPACE_DATOS=TS_D_AACID_OWNER;
DEF TABLESPACE_FICHEROS=TS_B_AACID_OWNER;

 CREATE TABLE "&usuario".AACI_T_DOCUMENTO_EXPEDIENTE (
    DOEX_NU_ID NUMBER(19,0),
    DOEX_X_EXPE NUMBER (19) NOT NULL, 
    DOEX_X_DOC NUMBER (19) NOT NULL, 
    DOEX_B_FICHERO BLOB
    ) LOB (DOEX_B_FICHERO) STORE AS SECUREFILE (TABLESPACE "&TABLESPACE_FICHEROS") 
    TABLESPACE "&TABLESPACE_DATOS";

	COMMENT ON COLUMN "&usuario".AACI_T_DOCUMENTO_EXPEDIENTE.DOEX_NU_ID IS 'Identificador del documento expediente';
	COMMENT ON COLUMN "&usuario".AACI_T_DOCUMENTO_EXPEDIENTE.DOEX_X_EXPE  IS 'identificador del expediente en trewa';
	COMMENT ON COLUMN "&usuario".AACI_T_DOCUMENTO_EXPEDIENTE.DOEX_X_DOC IS 'Identificador dol documento del expediente en trewa';
	COMMENT ON COLUMN "&usuario".AACI_T_DOCUMENTO_EXPEDIENTE.DOEX_B_FICHERO IS 'Fichero odt generado';
	
	--Añadimos columnas para el PE de alegaciones
alter table "&USUARIO".AACI_T_SOLICITUDSUBONGD
  add LG_ALEGO NUMBER(1,0);
comment on column "&USUARIO".AACI_T_SOLICITUDSUBONGD.LG_ALEGO is 'Campo que indica si se indicara motivo de alegacion';

alter table "&USUARIO".AACI_T_SOLICITUDSUBONGD
  add TX_ALEGO VARCHAR2(4000);
comment on column "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_ALEGO is 'Campo que indica el motivo de alegacion';

alter table "&USUARIO".AACI_T_SOLICITUDSUBONGD
  add LG_OTRO_ALEGO NUMBER(1,0);
comment on column "&USUARIO".AACI_T_SOLICITUDSUBONGD.LG_OTRO_ALEGO is 'Campo que indica si se indicara otro motivo de alegacion';

alter table "&USUARIO".AACI_T_SOLICITUDSUBONGD
  add TX_OTRO_ALEGO VARCHAR2(4000);
comment on column "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_OTRO_ALEGO is 'Campo que indica otro motivo de alegacion';

alter table "&USUARIO".AACI_T_SOLICITUDSUBONGD
  add TX_COD_DIR3 VARCHAR2(30);
comment on column "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_COD_DIR3 is 'Campo que indica el codigo directorio comun de unidades organicas y oficinas';

alter table "&USUARIO".AACI_T_SOLICITUDSUBONGD
  add TX_REPR1_EMAIL VARCHAR2(100);
comment on column "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_REPR1_EMAIL is 'Campo que indica email del representante';

alter table "&USUARIO".AACI_T_SOLICITUDSUBONGD
  add TX_REPR1_TELEFONO VARCHAR2(15);
comment on column "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_REPR1_TELEFONO is 'Campo que indica el telefono fijo del representante';

alter table "&USUARIO".AACI_T_SOLICITUDSUBONGD
  add TX_REPR1_TLFMOVIL VARCHAR2(15);
comment on column "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_REPR1_TLFMOVIL is 'Campo que indica el telefono movil del representante';

alter table "&usuario".AACI_TM_CAPACIDAD_GESTION
  add TMCG_NU_ANIO NUMBER(4);
comment on column "&usuario".AACI_TM_CAPACIDAD_GESTION.TMCG_NU_ANIO is 'Año de la capacidad de gestión';

alter table "&usuario".AACI_GASTOS_FINALIDAD add GAST_NU_ID NUMBER(4);
comment on column "&usuario".AACI_GASTOS_FINALIDAD.GAST_NU_ID is 'Id de la tabla de gastos';
	

SPOOL OFF
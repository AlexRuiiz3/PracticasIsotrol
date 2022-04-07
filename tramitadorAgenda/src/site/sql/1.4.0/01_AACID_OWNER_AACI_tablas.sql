SPOOL 01_AACID_OWNER_AACI_tablas.log
    
DEF USUARIO=AACID_OWNER;

--Procesamiento Espec�fico Alegaciones/Aceptaci�n/Reformulaci�n--

ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "TX_BANCO_IBAN" VARCHAR2(4 CHAR);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "TX_BANCO_CODIGO" VARCHAR2(4 CHAR);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "TX_BANCO_PAIS" VARCHAR2(4 CHAR);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "TX_BANCO_LOCALIDAD" VARCHAR2(4 CHAR);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "TX_BANCO_SUCURSAL" VARCHAR2(4 CHAR);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "TX_BANCO_NUMCUENTA" VARCHAR2(4 CHAR);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "TX_BANCO_S_CODBANCO" VARCHAR2(4 CHAR);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "TX_BANCO_S_PAIS" VARCHAR2(2 CHAR);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "TX_SWIFT_LOCALIDAD" VARCHAR2(2 CHAR);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "TX_BANCO_S_SUCUR" VARCHAR2(3 CHAR);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "TX_BANCO_ENTIDAD" VARCHAR2(255 CHAR);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "TX_BANCO_DOMICILIO" VARCHAR2(255 CHAR);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "TX_BANCO_CODPROV" VARCHAR2(2 CHAR);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "TX_BANCO_CODMUNICIPIO" VARCHAR2(20 CHAR);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "TX_BANCO_CP" VARCHAR2(5 CHAR);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "LG_CONCE_UNO" NUMBER(1,0);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "LG_CONCE_DOS" NUMBER(1,0);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "LG_CONCE_TRES" NUMBER(1,0);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "LG_CONCE_CUATRO" NUMBER(1,0);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "LG_CONCE_CINCO" NUMBER(1,0);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "LG_CONCE_SEIS" NUMBER(1,0);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "TX_REFOR" VARCHAR2(4000 CHAR);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "LG_CONCE_SIETE" NUMBER(1,0);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "TX_NO_ACEPTO" VARCHAR2(4000 CHAR);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "LG_CONCE_OCHO" NUMBER(1,0);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "TX_OTRO_A" VARCHAR2(4000 CHAR);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "LG_DOC1" NUMBER(1,0);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "LG_DOC2" NUMBER(1,0);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "LG_DOC3" NUMBER(1,0);
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD "LG_DOC4" NUMBER(1,0);

COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_BANCO_IBAN IS 'TX_BANCO_IBAN';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_BANCO_CODIGO IS 'TX_BANCO_CODIGO';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_BANCO_PAIS IS 'TX_BANCO_PAIS';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_BANCO_LOCALIDAD IS 'TX_BANCO_LOCALIDAD';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_BANCO_SUCURSAL IS 'TX_BANCO_SUCURSAL';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_BANCO_NUMCUENTA IS 'TX_BANCO_NUMCUENTA';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_BANCO_S_CODBANCO IS 'TX_BANCO_S_CODBANCO';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_BANCO_S_PAIS IS 'TX_BANCO_S_PAIS';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_SWIFT_LOCALIDAD IS 'TX_SWIFT_LOCALIDAD';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_BANCO_S_SUCUR IS 'TX_BANCO_S_SUCUR';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_BANCO_ENTIDAD IS 'TX_BANCO_ENTIDAD';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_BANCO_DOMICILIO IS 'TX_BANCO_DOMICILIO';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_BANCO_CODPROV IS 'TX_BANCO_CODPROV';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_BANCO_CODMUNICIPIO IS 'TX_BANCO_CODMUNICIPIO';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_BANCO_CP IS 'TX_BANCO_CP';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.LG_CONCE_UNO IS 'LG_CONCE_UNO';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.LG_CONCE_DOS IS 'LG_CONCE_DOS';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.LG_CONCE_TRES IS 'LG_CONCE_TRES';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.LG_CONCE_CUATRO IS 'LG_CONCE_CUATRO';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.LG_CONCE_CINCO IS 'LG_CONCE_CINCO';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.LG_CONCE_SEIS IS 'LG_CONCE_SEIS';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_REFOR IS 'TX_REFOR';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.LG_CONCE_SIETE IS 'LG_CONCE_SIETE';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_NO_ACEPTO IS 'TX_NO_ACEPTO';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.LG_CONCE_OCHO IS 'LG_CONCE_OCHO';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.TX_OTRO_A IS 'TX_OTRO_A';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.LG_DOC1 IS 'LG_DOC1';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.LG_DOC2 IS 'LG_DOC2';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.LG_DOC3 IS 'LG_DOC3';
COMMENT ON COLUMN "&USUARIO".AACI_T_SOLICITUDSUBONGD.LG_DOC4 IS 'LG_DOC4';


SPOOL OFF
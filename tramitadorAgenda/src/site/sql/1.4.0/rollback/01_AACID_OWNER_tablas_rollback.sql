SPOOL 01_AACID_OWNER_tablas_rollback.log

DEF USUARIO=AACID_OWNER;

--Procesamiento Espec�fico Alegaciones/Aceptaci�n/Reformulaci�n--

ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "TX_BANCO_IBAN";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "TX_BANCO_CODIGO";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "TX_BANCO_PAIS";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "TX_BANCO_LOCALIDAD";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "TX_BANCO_SUCURSAL";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "TX_BANCO_NUMCUENTA";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "TX_BANCO_S_CODBANCO";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "TX_BANCO_S_PAIS";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "TX_SWIFT_LOCALIDAD";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "TX_BANCO_S_SUCUR";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "TX_BANCO_ENTIDAD";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "TX_BANCO_DOMICILIO";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "TX_BANCO_CODPROV";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "TX_BANCO_CODMUNICIPIO";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "TX_BANCO_CP";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "LG_CONCE_UNO";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "LG_CONCE_DOS";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "LG_CONCE_TRES";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "LG_CONCE_CUATRO";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "LG_CONCE_CINCO";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "LG_CONCE_SEIS";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "TX_REFOR";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "LG_CONCE_SIETE";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "TX_NO_ACEPTO";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "LG_CONCE_OCHO";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "TX_OTRO_A";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "LG_DOC1";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "LG_DOC2";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "LG_DOC3";
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD DROP COLUMN "LG_DOC4";

SPOOL OFF
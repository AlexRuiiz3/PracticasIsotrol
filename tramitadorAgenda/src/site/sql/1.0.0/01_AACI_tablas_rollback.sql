SPOOL 01_AACI_tablas_rollback.log

DEF USUARIO=AACID_OWNER;

DROP TABLE "&USUARIO".AACI_EXCLUSIONES_SOLICITUD CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_EXCLUSION CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_TIPO_EXCLUSION CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_CATALOGO_SUBSANACION CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_CATALOGO CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_TIPO_CATALOGO CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_SUBSANACION CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_T_CONVOCATORIAS CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_T_TIPO_CONV CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_T_HISTORICO CASCADE CONSTRAINTS;

SPOOL OFF
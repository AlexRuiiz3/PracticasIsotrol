SPOOL 02_AACI_secuencias_rollback.log

DEF USUARIO=AACID_OWNER;

DROP SEQUENCE "&USUARIO".AACI_SEQ_SOLIONGD;
DROP SEQUENCE "&USUARIO".AACI_SEQ_CONV;
DROP SEQUENCE "&USUARIO".AACI_SEQ_TIPO_CONV;
DROP SEQUENCE "&USUARIO".AACI_SEQ_TIPO_CATALOGO;
DROP SEQUENCE "&USUARIO".AACI_SEQ_CATALOGO;
DROP SEQUENCE "&USUARIO".AACI_SEQ_SUBSANACION;
DROP SEQUENCE "&USUARIO".AACI_SEQ_CATALOGO_SUBSANACION;
DROP SEQUENCE "&USUARIO".AACI_SEQ_TIPO_EXCLUSION;
DROP SEQUENCE "&USUARIO".AACI_SEQ_EXCLUSION;
DROP SEQUENCE "&USUARIO".AACI_SEQ_HISTORICO;

SPOOL OFF
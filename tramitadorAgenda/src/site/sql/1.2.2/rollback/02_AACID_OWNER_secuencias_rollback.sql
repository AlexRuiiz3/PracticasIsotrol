SPOOL 02_AACI_secuencias_rollback.log

DEF USUARIO= AACID_OWNER;

DROP SEQUENCE "&USUARIO".AACI_SEQ_PAISES_SOLICITUD;
DROP SEQUENCE "&USUARIO".AACI_SEQ_SOL_OTRAS_SUB;
DROP SEQUENCE "&USUARIO".AACI_SEQ_CON_OTRAS_SUB;
DROP SEQUENCE "&USUARIO".AACI_SEQ_DOCS_PODER_JA;
DROP SEQUENCE "&USUARIO".AACI_SEQ_DOCS_PODER_OTR_ADMIN;
DROP SEQUENCE "&USUARIO".AACI_SEQ_OTROS_DOCUMENTOS;
DROP SEQUENCE "&USUARIO".AACI_SEQ_UNIVERSIDADES;

SPOOL OFF
SPOOL 02_AACI_secuencias.log

DEF USUARIO= AACID_OWNER;

CREATE SEQUENCE "&USUARIO".AACI_SEQ_PAISES_SOLICITUD INCREMENT BY 1 START WITH 1 NOCYCLE NOCACHE NOORDER;
CREATE SEQUENCE "&USUARIO".AACI_SEQ_SOL_OTRAS_SUB INCREMENT BY 1 START WITH 1 NOCYCLE NOCACHE NOORDER;
CREATE SEQUENCE "&USUARIO".AACI_SEQ_CON_OTRAS_SUB INCREMENT BY 1 START WITH 1 NOCYCLE NOCACHE NOORDER;
CREATE SEQUENCE "&USUARIO".AACI_SEQ_DOCS_PODER_JA INCREMENT BY 1 START WITH 1 NOCYCLE NOCACHE NOORDER;
CREATE SEQUENCE "&USUARIO".AACI_SEQ_DOCS_PODER_OTR_ADMIN INCREMENT BY 1 START WITH 1 NOCYCLE NOCACHE NOORDER;
CREATE SEQUENCE "&USUARIO".AACI_SEQ_OTROS_DOCUMENTOS INCREMENT BY 1 START WITH 1 NOCYCLE NOCACHE NOORDER;
CREATE SEQUENCE "&USUARIO".AACI_SEQ_UNIVERSIDADES INCREMENT BY 1 START WITH 1 NOCYCLE NOCACHE NOORDER;

SPOOL OFF
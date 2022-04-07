SPOOL 06_ACCI_REVOKEs.log

DEF USUARIO_OWNER=AACID_OWNER;
DEF USUARIO_USER=AACID_USER;

REVOKE SELECT ON "&USUARIO_OWNER".AACI_SEQ_SOLIONGD FROM "&USUARIO_USER";
REVOKE DELETE, INSERT, UPDATE, SELECT ON "&USUARIO_OWNER".AACI_T_SOLICITUDSUBONGD FROM "&USUARIO_USER";
REVOKE SELECT ON "&USUARIO_OWNER".AACI_SEQ_CONV FROM "&USUARIO_USER";
REVOKE DELETE, INSERT, UPDATE, SELECT ON "&USUARIO_OWNER".AACI_T_CONVOCATORIAS FROM "&USUARIO_USER";
REVOKE SELECT ON "&USUARIO_OWNER".AACI_SEQ_TIPO_CONV FROM "&USUARIO_USER";
REVOKE DELETE, INSERT, UPDATE, SELECT ON "&USUARIO_OWNER".AACI_T_TIPO_CONV FROM "&USUARIO_USER";
REVOKE SELECT ON "&USUARIO_OWNER".AACI_SEQ_TIPO_CATALOGO FROM "&USUARIO_USER";
REVOKE DELETE, INSERT, UPDATE, SELECT ON "&USUARIO_OWNER".AACI_TIPO_CATALOGO FROM "&USUARIO_USER";
REVOKE SELECT ON "&USUARIO_OWNER".AACI_SEQ_CATALOGO FROM "&USUARIO_USER";
REVOKE DELETE, INSERT, UPDATE, SELECT ON "&USUARIO_OWNER".AACI_CATALOGO FROM "&USUARIO_USER";
REVOKE SELECT ON "&USUARIO_OWNER".AACI_SEQ_SUBSANACION FROM "&USUARIO_USER";
REVOKE DELETE, INSERT, UPDATE, SELECT ON "&USUARIO_OWNER".AACI_SUBSANACION FROM "&USUARIO_USER";
REVOKE SELECT ON "&USUARIO_OWNER".AACI_SEQ_CATALOGO_SUBSANACION FROM "&USUARIO_USER";
REVOKE DELETE, INSERT, UPDATE, SELECT ON "&USUARIO_OWNER".AACI_CATALOGO_SUBSANACION FROM "&USUARIO_USER";
REVOKE SELECT ON "&USUARIO_OWNER".AACI_SEQ_TIPO_EXCLUSION FROM "&USUARIO_USER";
REVOKE DELETE, INSERT, UPDATE, SELECT ON "&USUARIO_OWNER".AACI_TIPO_EXCLUSION FROM "&USUARIO_USER";
REVOKE SELECT ON "&USUARIO_OWNER".AACI_SEQ_EXCLUSION FROM "&USUARIO_USER";
REVOKE DELETE, INSERT, UPDATE, SELECT ON "&USUARIO_OWNER".AACI_EXCLUSION FROM "&USUARIO_USER";
REVOKE DELETE, INSERT, UPDATE, SELECT ON "&USUARIO_OWNER".AACI_EXCLUSIONES_SOLICITUD FROM "&USUARIO_USER";
REVOKE SELECT ON "&USUARIO_OWNER".AACI_SEQ_HISTORICO FROM "&USUARIO_USER";
REVOKE DELETE, INSERT, UPDATE, SELECT ON "&USUARIO_OWNER".AACI_T_HISTORICO FROM "&USUARIO_USER";

SPOOL OFF
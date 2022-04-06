SPOOL 06_AACID_OWNER_AACI_grants_rollback.log

DEF USUARIO_OWNER=AACID_OWNER;
DEF USUARIO_USER=AACID_USER;

REVOKE SELECT ON "&USUARIO_OWNER".AACI_SEQ_PAISES_SOLICITUD FROM "&USUARIO_USER";
REVOKE SELECT ON "&USUARIO_OWNER".AACI_SEQ_SOL_OTRAS_SUB FROM "&USUARIO_USER";
REVOKE DELETE, INSERT, UPDATE, SELECT ON "&USUARIO_OWNER".AACI_T_SOL_OTRAS_SUBVENCIONES FROM "&USUARIO_USER";
REVOKE SELECT ON "&USUARIO_OWNER".AACI_SEQ_CON_OTRAS_SUB FROM "&USUARIO_USER";
REVOKE DELETE, INSERT, UPDATE, SELECT ON "&USUARIO_OWNER".AACI_T_CON_OTRAS_SUBVENCIONES FROM "&USUARIO_USER";
REVOKE SELECT ON "&USUARIO_OWNER".AACI_SEQ_DOCS_PODER_JA FROM "&USUARIO_USER";
REVOKE DELETE, INSERT, UPDATE, SELECT ON "&USUARIO_OWNER".AACI_T_DOC_PODER_ADMIN_JA FROM "&USUARIO_USER";
REVOKE SELECT ON "&USUARIO_OWNER".AACI_SEQ_DOCS_PODER_OTR_ADMIN FROM "&USUARIO_USER";
REVOKE DELETE, INSERT, UPDATE, SELECT ON "&USUARIO_OWNER".AACI_T_DOC_PODER_OTR_ADMIN FROM "&USUARIO_USER";
REVOKE SELECT ON "&USUARIO_OWNER".AACI_SEQ_OTROS_DOCUMENTOS FROM "&USUARIO_USER";
REVOKE DELETE, INSERT, UPDATE, SELECT ON "&USUARIO_OWNER".AACI_T_OTR_DOC FROM "&USUARIO_USER";
REVOKE SELECT ON "&USUARIO_OWNER".AACI_SEQ_UNIVERSIDADES FROM "&USUARIO_USER";
REVOKE DELETE, INSERT, UPDATE, SELECT ON "&USUARIO_OWNER".AACI_T_UNIVERSIDADES FROM "&USUARIO_USER";

SPOOL OFF
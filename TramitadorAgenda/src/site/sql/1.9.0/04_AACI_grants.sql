SPOOL 04_ACCI_grants.log

DEF USUARIO_OWNER=AACID_OWNER;
DEF USUARIO_USER=AACID_USER;

GRANT SELECT ON "&USUARIO_OWNER".AACI_SEQ_TEXCLU_SOLICITUD TO "&USUARIO_USER";
GRANT DELETE, INSERT, UPDATE, SELECT ON "&USUARIO_OWNER".AACI_TEXCLUSIONES_SOLICITUD TO "&USUARIO_USER";

SPOOL OFF
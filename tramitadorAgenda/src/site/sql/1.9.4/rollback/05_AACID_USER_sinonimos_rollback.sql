SPOOL 05_AACID_USER_sinonimos_rollback.log

DEF USUARIO_OWNER=AACID_OWNER;
DEF USUARIO_USER=AACID_USER;

DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_SEQ_DOCUMENTO_EXPEDIENTE;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_T_DOCUMENTO_EXPEDIENTE;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_SEQ_GASTOS;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_SEQ_GASTOS_FINALIDAD;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_SEQ_FUNCIONES;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_SEQ_T_ENT_PARTICIPANTE;

SPOOL OFF
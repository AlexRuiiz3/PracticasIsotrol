SPOOL 04_ACCI_sinonimos_rollback.log

DEF USUARIO_OWNER=AACID_OWNER;
DEF USUARIO_USER=AACID_USER;

DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_SEQ_SOLIONGD;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_T_SOLICITUDSUBONGD;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_SEQ_CONV;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_T_CONVOCATORIAS;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_SEQ_TIPO_CONV;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_T_TIPO_CONV;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_SEQ_TIPO_CATALOGO;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_TIPO_CATALOGO;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_SEQ_CATALOGO;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_CATALOGO;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_SEQ_SUBSANACION;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_SUBSANACION;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_SEQ_CATALOGO_SUBSANACION;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_CATALOGO_SUBSANACION;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_SEQ_TIPO_EXCLUSION;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_TIPO_EXCLUSION;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_SEQ_EXCLUSION;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_EXCLUSION;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_EXCLUSIONES_SOLICITUD;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_SEQ_HISTORICO;
DROP PUBLIC SYNONYM "&USUARIO_USER".AACI_T_HISTORICO;



SPOOL OFF
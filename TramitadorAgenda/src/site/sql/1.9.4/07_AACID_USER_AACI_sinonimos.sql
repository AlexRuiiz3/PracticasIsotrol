SPOOL 07_AACID_USER_AACI_sinonimos.log

DEF USUARIO_OWNER=AACID_OWNER;
DEF USUARIO_USER=AACID_USER;


CREATE OR REPLACE SYNONYM "&USUARIO_USER".AACI_SEQ_DOCUMENTO_EXPEDIENTE FOR "&USUARIO_OWNER".AACI_SEQ_DOCUMENTO_EXPEDIENTE;
CREATE OR REPLACE SYNONYM "&USUARIO_USER".AACI_T_DOCUMENTO_EXPEDIENTE FOR "&USUARIO_OWNER".AACI_T_DOCUMENTO_EXPEDIENTE;

CREATE OR REPLACE SYNONYM "&USUARIO_USER".AACI_SEQ_GASTOS FOR "&USUARIO_OWNER".AACI_SEQ_DOCUMENTO_EXPEDIENTE;
CREATE OR REPLACE SYNONYM "&USUARIO_USER".AACI_SEQ_GASTOS_FINALIDAD FOR "&USUARIO_OWNER".AACI_SEQ_DOCUMENTO_EXPEDIENTE;
CREATE OR REPLACE SYNONYM "&USUARIO_USER".AACI_SEQ_FUNCIONES FOR "&USUARIO_OWNER".AACI_SEQ_DOCUMENTO_EXPEDIENTE;
CREATE OR REPLACE SYNONYM "&USUARIO_USER".AACI_SEQ_T_ENT_PARTICIPANTE FOR "&USUARIO_OWNER".AACI_SEQ_DOCUMENTO_EXPEDIENTE;

SPOOL OFF
SPOOL 05_AACID_OWNER_AACI_constraints.log

DEF USUARIO=AACID_OWNER;
DEF TABLESPACE_INDICES=TS_I_AACID_OWNER;

ALTER TABLE "&USUARIO".AACI_T_DOCUMENTO_EXPEDIENTE ADD CONSTRAINT AACI_DOCUMENTO_EXPEDIENTE_PK PRIMARY KEY ( DOEX_NU_ID ) USING INDEX TABLESPACE "&TABLESPACE_INDICES";

ALTER TABLE "&USUARIO".AACI_SOLICITUD_CONCESION ADD CONSTRAINT AACI_SOLICITUD_CONCESION_PK PRIMARY KEY (SOCO_NU_ID) USING INDEX TABLESPACE "&TABLESPACE_INDICES";
ALTER TABLE "&USUARIO".AACI_CONTRIBUCIONES ADD CONSTRAINT FK_AACI_GASTOS FOREIGN KEY (FK_COSTES) REFERENCES "&USUARIO".AACI_GASTOS(GAST_NU_ID);
ALTER TABLE "&USUARIO".AACI_GASTOS_FINALIDAD ADD CONSTRAINT FK_AACI_GASTOS_FINALIDAD FOREIGN KEY (GAST_NU_ID) REFERENCES "&USUARIO".AACI_GASTOS(GAST_NU_ID);

SPOOL OFF
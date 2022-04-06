SPOOL 05_AACID_OWNER_AACI_constraints.log

DEF USUARIO=AACID_OWNER;
DEF TABLESPACE_INDICES=TS_I_AACID_OWNER;

ALTER TABLE "&USUARIO".AACI_T_CRITERIOS ADD CONSTRAINT AACI_IDTIPOCRITERIO_PK PRIMARY KEY ( TCRI_NU_ID ) USING INDEX TABLESPACE "&TABLESPACE_INDICES";
ALTER TABLE "&USUARIO".AACI_CRITERIOS ADD CONSTRAINT AACI_IDCRITERIO_PK PRIMARY KEY ( CRIT_NU_ID ) USING INDEX TABLESPACE "&TABLESPACE_INDICES";
ALTER TABLE "&USUARIO".AACI_SOLICITUD_CRITERIO ADD CONSTRAINT AACI_IDCRITERIOSOL_PK PRIMARY KEY (SOCR_NU_ID) USING INDEX TABLESPACE "&TABLESPACE_INDICES";
ALTER TABLE "&USUARIO".AACI_SOLICITUDES_AUXILIAR ADD CONSTRAINT AACI_IDSOLAUXILIAR_PK PRIMARY KEY (SOAU_NU_ID) USING INDEX TABLESPACE "&TABLESPACE_INDICES";
ALTER TABLE "&USUARIO".AACI_FINALIDAD ADD CONSTRAINT AACI_IDFINALIDAD_PK PRIMARY KEY (FINA_NU_ID) USING INDEX TABLESPACE "&TABLESPACE_INDICES";
ALTER TABLE "&USUARIO".AACI_GASTOS_FINALIDAD ADD CONSTRAINT AACI_IDGASTOSFINALIDAD_PK PRIMARY KEY (GAFI_NU_ID) USING INDEX TABLESPACE "&TABLESPACE_INDICES";
ALTER TABLE "&USUARIO".AACI_GASTOS ADD CONSTRAINT AACI_IDGASTOS_PK PRIMARY KEY (GAST_NU_ID) USING INDEX TABLESPACE "&TABLESPACE_INDICES";
ALTER TABLE "&USUARIO".AACI_FUNCIONES ADD CONSTRAINT AACI_IDFUNCIONES_PK PRIMARY KEY ( FUNC_NU_ID ) USING INDEX TABLESPACE "&TABLESPACE_INDICES";
ALTER TABLE "&USUARIO".AACI_T_ENTIDAD_PARTICIPANTE ADD CONSTRAINT AACI_IDTIPENTIDADPAR_PK PRIMARY KEY (TEPA_NU_ID) USING INDEX TABLESPACE "&TABLESPACE_INDICES";
ALTER TABLE "&USUARIO".AACI_ENTIDADES_PARTICIPANTES ADD CONSTRAINT AACI_IDENTIDADPAR_PK PRIMARY KEY ( ENPA_NU_ID ) USING INDEX TABLESPACE "&TABLESPACE_INDICES";
ALTER TABLE "&USUARIO".AACI_SOLICITUD_TCRITERIO ADD CONSTRAINT AACI_IDSOLITCRITERIO_PK PRIMARY KEY ( SOTC_NU_ID ) USING INDEX TABLESPACE "&TABLESPACE_INDICES";
ALTER TABLE "&USUARIO".AACI_TM_CAPACIDAD_GESTION ADD CONSTRAINT AACI_IDCAPACIDADGESTION_PK PRIMARY KEY ( TMCG_NU_ID ) USING INDEX TABLESPACE "&TABLESPACE_INDICES";

ALTER TABLE "&USUARIO".AACI_T_CRITERIOS ADD CONSTRAINT FK_AACI_T_CRITERIO_TIPO_CONV FOREIGN KEY ( TCON_NU_ID ) REFERENCES "&USUARIO".AACI_T_TIPO_CONV ( TCONV_NU_ID ) ;
ALTER TABLE "&USUARIO".AACI_T_CRITERIOS ADD CONSTRAINT FK_AACI_T_CRITERIO_FINALIDAD FOREIGN KEY ( FINA_NU_ID ) REFERENCES "&USUARIO".AACI_FINALIDAD ( FINA_NU_ID ) ;
ALTER TABLE "&USUARIO".AACI_CRITERIOS ADD CONSTRAINT FK_AACI_CRITERIO_TIPO_CRITERIO FOREIGN KEY ( TCRI_NU_ID ) REFERENCES "&USUARIO".AACI_T_CRITERIOS ( TCRI_NU_ID ) ;
ALTER TABLE "&USUARIO".AACI_SOLICITUD_CRITERIO ADD CONSTRAINT FK_AACI_SOL_CRITERIO_SOLICITUD FOREIGN KEY ( ID_SOLICITUD ) REFERENCES "&USUARIO".AACI_T_SOLICITUDSUBONGD ( ID_SOLICITUD ) ;
ALTER TABLE "&USUARIO".AACI_SOLICITUD_CRITERIO ADD CONSTRAINT FK_AACI_SOL_CRITERIO_CRITERIOS FOREIGN KEY ( CRIT_NU_ID ) REFERENCES "&USUARIO".AACI_CRITERIOS ( CRIT_NU_ID ) ;
ALTER TABLE "&USUARIO".AACI_SOLICITUDES_AUXILIAR ADD CONSTRAINT FK_AACI_SOL_AUX_SOLICITUD FOREIGN KEY ( ID_SOLICITUD ) REFERENCES "&USUARIO".AACI_T_SOLICITUDSUBONGD ( ID_SOLICITUD ) ;
ALTER TABLE "&USUARIO".AACI_FINALIDAD ADD CONSTRAINT FK_AACI_FINALIDAD_TIPO_CONV FOREIGN KEY ( TCON_NU_ID ) REFERENCES "&USUARIO".AACI_T_TIPO_CONV ( TCONV_NU_ID ) ;
ALTER TABLE "&USUARIO".AACI_GASTOS_FINALIDAD ADD CONSTRAINT FK_AACI_GASTOS_FIN_FINALIDAD FOREIGN KEY ( FINA_NU_ID ) REFERENCES "&USUARIO".AACI_FINALIDAD ( FINA_NU_ID ) ;
ALTER TABLE "&USUARIO".AACI_ENTIDADES_PARTICIPANTES ADD CONSTRAINT FK_AACI_ENT_PART_FUNCIONES FOREIGN KEY ( FUNC_NU_ID ) REFERENCES "&USUARIO".AACI_FUNCIONES ( FUNC_NU_ID ) ;
ALTER TABLE "&USUARIO".AACI_ENTIDADES_PARTICIPANTES ADD CONSTRAINT FK_AACI_ENT_PART_SOLICITUD FOREIGN KEY ( ID_SOLICITUD ) REFERENCES "&USUARIO".AACI_T_SOLICITUDSUBONGD ( ID_SOLICITUD ) ;
ALTER TABLE "&USUARIO".AACI_ENTIDADES_PARTICIPANTES ADD CONSTRAINT FK_AACI_ENT_PART_TIPENTPART FOREIGN KEY ( TEPA_NU_ID ) REFERENCES "&USUARIO".AACI_T_ENTIDAD_PARTICIPANTE ( TEPA_NU_ID ) ;
ALTER TABLE "&USUARIO".AACI_SOLICITUD_TCRITERIO ADD CONSTRAINT FK_AACI_SOLI_TCRITERIO_SOLI FOREIGN KEY ( ID_SOLICITUD ) REFERENCES "&USUARIO".AACI_T_SOLICITUDSUBONGD ( ID_SOLICITUD ) ;
ALTER TABLE "&USUARIO".AACI_SOLICITUD_TCRITERIO ADD CONSTRAINT FK_AACI_SOLI_TCRITERIO_TICR FOREIGN KEY ( TCRI_NU_ID ) REFERENCES "&USUARIO".AACI_T_CRITERIOS ( TCRI_NU_ID ) ;
ALTER TABLE "&USUARIO".AACI_T_PAISES ADD CONSTRAINT AACI_T_PAISES_PK PRIMARY KEY (ID_PAIS) USING INDEX TABLESPACE "&TABLESPACE_INDICES";

SPOOL OFF
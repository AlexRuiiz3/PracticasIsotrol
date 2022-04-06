SPOOL 01_AACI_tablas.log
  
  DEF USUARIO=AACID_OWNER;
  DEF TABLESPACE_DATOS=TS_D_AACID_OWNER;

  CREATE TABLE "&USUARIO".AACI_TEXCLUSIONES_SOLICITUD (
    TESO_NU_ID NUMBER (19,0) NOT null,
    TEXC_NU_TIPO NUMBER (10,0) NOT null,
    ID_SOLICITUD NUMBER (19,0) NOT null,
    TESO_LG_EXCLUSIONES NUMBER (1,0) default 0 NOT null
  )
  TABLESPACE "&TABLESPACE_DATOS" LOGGING;


  COMMENT ON TABLE AACID_OWNER.AACI_TEXCLUSIONES_SOLICITUD IS 'TABLA QUE INDICAR� SI LA SOLICITUD TIENE ALGUNA EXCLUSI�N DE UN TIPO';
  COMMENT ON COLUMN AACID_OWNER.AACI_TEXCLUSIONES_SOLICITUD.TESO_NU_ID IS 'ID DE LA RELACI�N ENTRE TIPOS EXCLUSIONES Y SOLICITUDES';
  COMMENT ON COLUMN AACID_OWNER.AACI_TEXCLUSIONES_SOLICITUD.TESO_LG_EXCLUSIONES IS 'TIENE EXCLUSIONES PARA ESE TIPO EXCLUSIONES LA SOLICITUD';
  COMMENT ON COLUMN AACID_OWNER.AACI_TEXCLUSIONES_SOLICITUD.TEXC_NU_TIPO IS 'ID TIPO EXCLUSI�N';
  COMMENT ON COLUMN AACID_OWNER.AACI_TEXCLUSIONES_SOLICITUD.ID_SOLICITUD IS 'ID DE LA SOLICITUD';
  
    
SPOOL OFF
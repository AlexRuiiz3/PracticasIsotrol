SPOOL 02_AACID_OWNER_AACI_insert_concesiones.log

DEF USUARIO=AACID_OWNER;

insert into "&USUARIO".AACI_SOLICITUD_CONCESION (SOCO_NU_ID, SOCO_NU_SOLICITUD, SOCO_TX_TIPO_CONCESION, SOCO_NU_PRESUPUESTO_VALIDADO, SOCO_NU_SUBVENCION_SOLICITADA, SOCO_NU_SUBVENCION_CONCEDER, SOCO_TCONV_NU_ID) values ("&USUARIO".AACI_SEQ_SOLICITUD_CONCESION.NEXTVAL,991,'Beneficiaria',118800,80000,80000, (select TCONV_NU_ID from "&USUARIO". AACI_T_TIPO_CONV where TCONV_LI_DENOMINACION= 'UNIV'));
insert into "&USUARIO".AACI_SOLICITUD_CONCESION (SOCO_NU_ID, SOCO_NU_SOLICITUD, SOCO_TX_TIPO_CONCESION, SOCO_NU_PRESUPUESTO_VALIDADO, SOCO_NU_SUBVENCION_SOLICITADA, SOCO_NU_SUBVENCION_CONCEDER, SOCO_TCONV_NU_ID) values ("&USUARIO".AACI_SEQ_SOLICITUD_CONCESION.NEXTVAL,992,'Suplente',42900,42900,40000, (select TCONV_NU_ID from "&USUARIO". AACI_T_TIPO_CONV where TCONV_LI_DENOMINACION= 'UNIV'));
insert into "&USUARIO".AACI_SOLICITUD_CONCESION (SOCO_NU_ID, SOCO_NU_SOLICITUD, SOCO_TX_TIPO_CONCESION, SOCO_NU_PRESUPUESTO_VALIDADO, SOCO_NU_SUBVENCION_SOLICITADA, SOCO_NU_SUBVENCION_CONCEDER, SOCO_TCONV_NU_ID) values ("&USUARIO".AACI_SEQ_SOLICITUD_CONCESION.NEXTVAL,993,'Beneficiaria',55278.37,32803.37,32803.37, (select TCONV_NU_ID from "&USUARIO". AACI_T_TIPO_CONV where TCONV_LI_DENOMINACION= 'UNIV'));
insert into "&USUARIO".AACI_SOLICITUD_CONCESION (SOCO_NU_ID, SOCO_NU_SOLICITUD, SOCO_TX_TIPO_CONCESION, SOCO_NU_PRESUPUESTO_VALIDADO, SOCO_NU_SUBVENCION_SOLICITADA, SOCO_NU_SUBVENCION_CONCEDER, SOCO_TCONV_NU_ID) values ("&USUARIO".AACI_SEQ_SOLICITUD_CONCESION.NEXTVAL,994,'Suplente',46000,46000,40000, (select TCONV_NU_ID from "&USUARIO". AACI_T_TIPO_CONV where TCONV_LI_DENOMINACION= 'UNIV'));
insert into "&USUARIO".AACI_SOLICITUD_CONCESION (SOCO_NU_ID, SOCO_NU_SOLICITUD, SOCO_TX_TIPO_CONCESION, SOCO_NU_PRESUPUESTO_VALIDADO, SOCO_NU_SUBVENCION_SOLICITADA, SOCO_NU_SUBVENCION_CONCEDER, SOCO_TCONV_NU_ID) values ("&USUARIO".AACI_SEQ_SOLICITUD_CONCESION.NEXTVAL,1000,'Beneficiaria',131392,109892,109892, (select TCONV_NU_ID from "&USUARIO". AACI_T_TIPO_CONV where TCONV_LI_DENOMINACION= 'UNIV'));
commit;

SPOOL OFF
SPOOL 03_AACI_deletes.log

DEF USUARIO=AACID_OWNER;


delete from "&USUARIO".AACI_CATALOGO_SUBSANACION;
delete from "&USUARIO".AACI_EXCLUSIONES_SOLICITUD;
delete from "&USUARIO".AACI_CATALOGO;
delete from "&USUARIO".AACI_EXCLUSION;
delete from "&USUARIO".AACI_TIPO_EXCLUSION;
delete from "&USUARIO".AACI_TIPO_CATALOGO;
delete from "&USUARIO".AACI_T_TIPO_CONV;
commit;

SPOOL OFF
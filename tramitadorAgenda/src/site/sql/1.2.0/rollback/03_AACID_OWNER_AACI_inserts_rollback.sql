SPOOL 03_AACID_OWNER_AACI_inserts_rollback.log

DEF USUARIO=AACID_OWNER;

delete from "&USUARIO".AACI_FINALIDAD;
delete from "&USUARIO".AACI_T_CRITERIOS;
delete from "&USUARIO".AACI_TM_CAPACIDAD_GESTION;
delete from "&USUARIO".AACI_T_PAISES;

commit;

SPOOL OFF
SPOOL 17_AACID_OWNER_AACI_update_correccion_expediente.log

DEF USUARIO=AACID_OWNER;

update AACI_T_SOLICITUDSUBONGD sol set sol.TX_DATOSTITULO = 'Perfeccionamiento de capacidades científico-técnicas de la UNJBG' where sol.TX_CODIDENTIFICATIVO = '2021UC004';
commit;

SPOOL OFF
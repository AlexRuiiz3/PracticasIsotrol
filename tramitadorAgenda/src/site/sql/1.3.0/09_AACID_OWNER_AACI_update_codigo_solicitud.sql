SPOOL 09_AACID_OWNER_AACI_update_codigo_solicitud.log

DEF USUARIO=AACID_OWNER;

UPDATE "&USUARIO".AACI_T_SOLICITUDSUBONGD ats SET ats.TX_CODIDENTIFICATIVO = '0ED000/2021' WHERE ats.ID_SOLICITUD=45;--SHYGB200000202129
UPDATE "&USUARIO".AACI_T_SOLICITUDSUBONGD ats SET ats.TX_CODIDENTIFICATIVO = '0CC000/2021' WHERE ats.ID_SOLICITUD=39;--SHYGB200000202186

commit;

SPOOL OFF
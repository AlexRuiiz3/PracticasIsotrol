SPOOL 04_AACID_OWNER_AACI_tablas.log
    
DEF USUARIO=AACID_OWNER;

alter table "&USUARIO".AACI_SOLICITUDES_AUXILIAR
	add SOAU_NU_PUNTUACION_TOTAL NUMBER(13,3);

comment on column "&USUARIO".AACI_SOLICITUDES_AUXILIAR.SOAU_NU_PUNTUACION_TOTAL is 'Puntuaci�n total de la solicitud';

ALTER TABLE "&USUARIO".AACI_CRITERIOS
ADD CRIT_NU_ORDEN  NUMBER(10,0);

COMMIT;
SPOOL OFF
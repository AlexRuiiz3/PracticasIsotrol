SPOOL 01_AACID_OWNER_alter_table.log
    
DEF USUARIO=AACID_OWNER;

--A�adimos columnas para el PE de comunicacion de inicio
alter table "&USUARIO".AACI_T_SOLICITUDSUBONGD add FH_INICIOCOM TIMESTAMP;
comment on column "&USUARIO".AACI_T_SOLICITUDSUBONGD.FH_INICIOCOM is 'Campo que indica la fecha de inicio de la comunicaci�n';

alter table "&USUARIO".AACI_T_SOLICITUDSUBONGD add FH_FINCOM TIMESTAMP;
comment on column "&USUARIO".AACI_T_SOLICITUDSUBONGD.FH_FINCOM is 'Campo que indica la fecha de fin de la comunicaci�n';

SPOOL OFF

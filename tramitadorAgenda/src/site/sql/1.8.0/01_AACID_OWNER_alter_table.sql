SPOOL 01_AACID_OWNER_alter_table.log
    
DEF USUARIO=AACID_OWNER;

--A�adimos columnas para el PE de comunicacion de inicio
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD FH_POSTEGRACION_COMUN_INI DATE;
comment on column "&USUARIO".AACI_T_SOLICITUDSUBONGD.FH_POSTEGRACION_COMUN_INI is 'Campo que indica la postegraci�n de la comunicaci�n de inicio';

SPOOL OFF

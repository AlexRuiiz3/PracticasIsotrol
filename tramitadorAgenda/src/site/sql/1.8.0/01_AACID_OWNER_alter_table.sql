SPOOL 01_AACID_OWNER_alter_table.log
    
DEF USUARIO=AACID_OWNER;

--Añadimos columnas para el PE de comunicacion de inicio
ALTER TABLE "&USUARIO".AACI_T_SOLICITUDSUBONGD ADD FH_POSTEGRACION_COMUN_INI DATE;
comment on column "&USUARIO".AACI_T_SOLICITUDSUBONGD.FH_POSTEGRACION_COMUN_INI is 'Campo que indica la postegración de la comunicación de inicio';

SPOOL OFF

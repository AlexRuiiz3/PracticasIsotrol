SPOOL 01_AACI_tablas.log
	
	DEF USUARIO=AACID_OWNER;

	alter table "&USUARIO".AACI_CATALOGO add CAT_LG_VALORADOR NUMBER(1);
	alter table "&USUARIO".AACI_CATALOGO add CAT_LG_DGA number(1);
	alter table "&USUARIO".AACI_CATALOGO add CAT_LG_COORDINADOR NUMBER(1);
	
	comment on column "&USUARIO".AACI_CATALOGO.CAT_LG_VALORADOR is 'Indica si lo ve el valorador';
	comment on column "&USUARIO".AACI_CATALOGO.CAT_LG_DGA is 'Indica si lo ve el DGA';
	comment on column "&USUARIO".AACI_CATALOGO.CAT_LG_COORDINADOR is 'Indica si lo ve el coordinador';
	
SPOOL OFF
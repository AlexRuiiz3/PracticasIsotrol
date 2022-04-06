SPOOL 01_AACID_OWNER_AACI_tablas_rollback.log

DEF USUARIO=AACID_OWNER;

DROP TABLE "&USUARIO".AACI_T_CRITERIOS CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_CRITERIOS CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_SOLICITUD_CRITERIO CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_SOLICITUDES_AUXILIAR CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_FINALIDAD CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_GASTOS_FINALIDAD CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_GASTOS CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_FUNCIONES CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_T_ENTIDAD_PARTICIPANTE CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_ENTIDADES_PARTICIPANTES CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_TM_CAPACIDAD_GESTION CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_SOLICITUD_TCRITERIO CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_T_PAISES CASCADE CONSTRAINTS;
DROP TABLE "&USUARIO".AACI_T_PAISES_SOLICITUD CASCADE CONSTRAINTS;

ALTER TABLE "&USUARIO".AACI_EXCLUSIONES_SOLICITUD DROP COLUMN ID;

SPOOL OFF
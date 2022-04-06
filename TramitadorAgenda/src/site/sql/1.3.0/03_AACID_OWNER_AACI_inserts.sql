SPOOL 03_AACID_OWNER_AACI_inserts.log

DEF USUARIO=AACID_OWNER;

--CAPACIDAD DE GESTI覰
INSERT INTO "&USUARIO".AACI_CRITERIOS (CRIT_NU_ID,TCRI_NU_ID,CRIT_TX_DESCRIPCION,CRIT_NU_ANIO,CRIT_NU_VAL_MAX,CRIT_SUB_FINALIDAD) VALUES ("&USUARIO".AACI_SEQ_CRITERIOS.NEXTVAL,(SELECT TCRI_NU_ID FROM "&USUARIO".aaci_t_criterios atc WHERE atc.tcri_tx_nombre = 'Capacidad de gesti贸n' AND atc.fina_nu_id = (SELECT fina_nu_id FROM "&USUARIO".aaci_finalidad af WHERE af.FINA_CO_CODIGO = 'F') AND atc.tcon_nu_id = (SELECT attc.TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV attc WHERE attc.TCONV_LI_DENOMINACION = 'ONGD')),'Se valorar谩 la trayectoria de gesti贸n de la entidad solicitante en la justificaci贸n de subvenciones concedidas por la AACID en los cuatro a帽os anteriores a la Convocatoria en funci贸n del n煤mero, tipolog铆a y volumen de reintegros solicitados',2021,4,'F');

SPOOL OFF
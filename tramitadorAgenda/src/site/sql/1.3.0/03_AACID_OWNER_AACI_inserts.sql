SPOOL 03_AACID_OWNER_AACI_inserts.log

DEF USUARIO=AACID_OWNER;

--CAPACIDAD DE GESTI�N
INSERT INTO "&USUARIO".AACI_CRITERIOS (CRIT_NU_ID,TCRI_NU_ID,CRIT_TX_DESCRIPCION,CRIT_NU_ANIO,CRIT_NU_VAL_MAX,CRIT_SUB_FINALIDAD) VALUES ("&USUARIO".AACI_SEQ_CRITERIOS.NEXTVAL,(SELECT TCRI_NU_ID FROM "&USUARIO".aaci_t_criterios atc WHERE atc.tcri_tx_nombre = 'Capacidad de gestión' AND atc.fina_nu_id = (SELECT fina_nu_id FROM "&USUARIO".aaci_finalidad af WHERE af.FINA_CO_CODIGO = 'F') AND atc.tcon_nu_id = (SELECT attc.TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV attc WHERE attc.TCONV_LI_DENOMINACION = 'ONGD')),'Se valorará la trayectoria de gestión de la entidad solicitante en la justificación de subvenciones concedidas por la AACID en los cuatro años anteriores a la Convocatoria en función del número, tipología y volumen de reintegros solicitados',2021,4,'F');

SPOOL OFF
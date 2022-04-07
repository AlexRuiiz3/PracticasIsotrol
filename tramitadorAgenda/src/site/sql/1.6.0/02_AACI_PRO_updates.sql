SPOOL 02_AACI_PRO_Updates.log

DEF USUARIO=AACID_OWNER;

UPDATE "&USUARIO".AACI_CRITERIOS SET TCRI_NU_ID =(SELECT TCRI_NU_ID FROM "&USUARIO".AACI_T_CRITERIOS atc WHERE TCON_NU_ID =(SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_CONVOCATORIAS atc2 WHERE atc2.CONV_LI_ABREV_PROCEDIMIENTO='CONV_ONGD' AND rownum <= 1) 
AND atc.FINA_NU_ID =(SELECT FINA_NU_ID FROM "&USUARIO".AACI_FINALIDAD af WHERE af.FINA_CO_CODIGO='F' AND af.TCON_NU_ID=1) AND atc.TCRI_TX_NOMBRE =	'Capacidad de gestión')
, CRIT_TX_DESCRIPCION ='Se valorará la trayectoria de gestión de la entidad solicitante en la justificación de subvenciones concedidas por la AACID en los cuatro años anteriores a la Convocatoria en función del número, tipología y volumen de reintegros solicitados' 
, CRIT_NU_ORDEN =1
WHERE CRIT_NU_ID =455;
COMMIT;

SPOOL OFF
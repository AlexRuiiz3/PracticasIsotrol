SPOOL 10_AACID_OWNER_AACI_update_codigo_solicitud.log
DEF USUARIO=AACID_OWNER;

UPDATE "&USUARIO".AACI_SOLICITUD_CONCESION soc SET soc.SOCO_TCONV_NU_ID = (SELECT tconv.TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV tconv WHERE tconv.TCONV_LI_DENOMINACION='UNIV') WHERE soc.SOCO_TCONV_NU_ID=3

commit;
SPOOL OFF
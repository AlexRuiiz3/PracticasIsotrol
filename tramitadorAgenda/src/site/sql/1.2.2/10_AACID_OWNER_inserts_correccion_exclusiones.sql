SPOOL 10_AACI_inserts_correccion_exclusiones.log

DEF USUARIO=AACID_OWNER;


UPDATE "&USUARIO".AACI_EXCLUSION set EXCL_NU_ORDEN = 23 where EXCL_NU_ORDEN = 95;
UPDATE "&USUARIO".AACI_EXCLUSION set EXCL_NU_ORDEN = 24 where EXCL_NU_ORDEN = 96;
UPDATE "&USUARIO".AACI_EXCLUSION set EXCL_NU_ORDEN = 25 where EXCL_NU_ORDEN = 97;
UPDATE "&USUARIO".AACI_EXCLUSION set EXCL_NU_ORDEN = 26 where EXCL_NU_ORDEN = 98;
UPDATE "&USUARIO".AACI_EXCLUSION set EXCL_NU_ORDEN = 27 where EXCL_NU_ORDEN = 99;
UPDATE "&USUARIO".AACI_EXCLUSION set EXCL_NU_ORDEN = 28 where EXCL_NU_ORDEN = 100;
UPDATE "&USUARIO".AACI_EXCLUSION set EXCL_NU_ORDEN = 29 where EXCL_NU_ORDEN = 101;
UPDATE "&USUARIO".AACI_EXCLUSION set EXCL_NU_ORDEN = 30 where EXCL_NU_ORDEN = 102;
UPDATE "&USUARIO".AACI_EXCLUSION set EXCL_NU_ORDEN = 31 where EXCL_NU_ORDEN = 103;
UPDATE "&USUARIO".AACI_EXCLUSION set EXCL_NU_ORDEN = 32 where EXCL_NU_ORDEN = 104;
UPDATE "&USUARIO".AACI_EXCLUSION set EXCL_NU_ORDEN = 33 where EXCL_NU_ORDEN = 105;
UPDATE "&USUARIO".AACI_EXCLUSION set EXCL_NU_ORDEN = 34 where EXCL_NU_ORDEN = 106;
UPDATE "&USUARIO".AACI_EXCLUSION set EXCL_NU_ORDEN = 35 where EXCL_NU_ORDEN = 107;
UPDATE "&USUARIO".AACI_EXCLUSION set EXCL_NU_ORDEN = 36 where EXCL_NU_ORDEN = 108;
UPDATE "&USUARIO".AACI_EXCLUSION set EXCL_NU_ORDEN = 37 where EXCL_NU_ORDEN = 109;
commit;

SPOOL OFF
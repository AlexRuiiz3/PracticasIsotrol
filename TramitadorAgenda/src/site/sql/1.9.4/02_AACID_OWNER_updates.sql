SPOOL 02_AACID_OWNER_updates.log

DEF USUARIO=AACID_OWNER;

UPDATE "&usuario".AACI_T_PAISES t SET t.NU_PUNTUACION = 0.71 WHERE t.TX_CO_PAIS = '009' AND t.NU_ANIO = 2022;
UPDATE "&usuario".AACI_T_PAISES t SET t.NU_PUNTUACION = 0.88 WHERE t.TX_CO_PAIS = '005' AND t.NU_ANIO = 2022;
UPDATE "&usuario".AACI_T_PAISES t SET t.NU_PUNTUACION = 0.88 WHERE t.TX_CO_PAIS = '052' AND t.NU_ANIO = 2022;
UPDATE "&usuario".AACI_T_PAISES t SET t.NU_PUNTUACION = 0.85 WHERE t.TX_CO_PAIS = '046' AND t.NU_ANIO = 2022;
UPDATE "&usuario".AACI_T_PAISES t SET t.NU_PUNTUACION = 0.44 WHERE t.TX_CO_PAIS = '033' AND t.NU_ANIO = 2022;
UPDATE "&usuario".AACI_T_PAISES t SET t.NU_PUNTUACION = 0.97 WHERE t.TX_CO_PAIS = '051' AND t.NU_ANIO = 2022;
UPDATE "&usuario".AACI_T_PAISES t SET t.NU_PUNTUACION = 0.76 WHERE t.TX_CO_PAIS = '004' AND t.NU_ANIO = 2022;
UPDATE "&usuario".AACI_T_PAISES t SET t.NU_PUNTUACION = 0.74 WHERE t.TX_CO_PAIS = '038' AND t.NU_ANIO = 2022;
UPDATE "&usuario".AACI_T_PAISES t SET t.NU_PUNTUACION = 0.88 WHERE t.TX_CO_PAIS = '013' AND t.NU_ANIO = 2022;
UPDATE "&usuario".AACI_T_PAISES t SET t.NU_PUNTUACION = 0.97 WHERE t.TX_CO_PAIS = '023' AND t.NU_ANIO = 2022;
UPDATE "&usuario".AACI_T_PAISES t SET t.NU_PUNTUACION = 0.68 WHERE t.TX_CO_PAIS = '030' AND t.NU_ANIO = 2022;
UPDATE "&usuario".AACI_T_PAISES t SET t.NU_PUNTUACION = 0.82 WHERE t.TX_CO_PAIS = '024' AND t.NU_ANIO = 2022;
UPDATE "&usuario".AACI_T_PAISES t SET t.NU_PUNTUACION = 0.74 WHERE t.TX_CO_PAIS = '034' AND t.NU_ANIO = 2022;
UPDATE "&usuario".AACI_T_PAISES t SET t.NU_PUNTUACION = 0.74 WHERE t.TX_CO_PAIS = '020' AND t.NU_ANIO = 2022;
UPDATE "&usuario".AACI_T_PAISES t SET t.NU_PUNTUACION = 0.74 WHERE t.TX_CO_PAIS = '032' AND t.NU_ANIO = 2022;
UPDATE "&usuario".AACI_T_PAISES t SET t.NU_PUNTUACION = 0.38 WHERE t.TX_CO_PAIS = '031' AND t.NU_ANIO = 2022;
UPDATE "&usuario".AACI_T_PAISES t SET t.NU_PUNTUACION = 0.50 WHERE t.TX_CO_PAIS = '042' AND t.NU_ANIO = 2022;
UPDATE "&usuario".AACI_T_PAISES t SET t.NU_PUNTUACION = 0.82 WHERE t.TX_CO_PAIS = '039' AND t.NU_ANIO = 2022;

UPDATE "&usuario".AACI_TM_CAPACIDAD_GESTION cg SET cg.TMCG_NU_ANIO = 2021;

COMMIT;

SPOOL OFF
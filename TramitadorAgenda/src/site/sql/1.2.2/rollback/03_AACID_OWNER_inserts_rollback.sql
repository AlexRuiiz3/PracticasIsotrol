SPOOL 02_AACID_OWNER_tablas_rollback.log
    
DEF USUARIO=AACID_OWNER;

DELETE FROM "&USUARIO".AACI_EXCLUSION WHERE EXCL_NU_ORDEN = 38 AND EXCL_NU_ANIO = 2021 AND TEXC_NU_ID =(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 1 AND TEXC_NU_TIPO = 3 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV'));
DELETE FROM "&USUARIO".AACI_EXCLUSION WHERE EXCL_NU_ORDEN = 39 AND EXCL_NU_ANIO = 2021 AND TEXC_NU_ID =(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 1 AND TEXC_NU_TIPO = 3 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV'));
DELETE FROM "&USUARIO".AACI_EXCLUSION WHERE EXCL_NU_ORDEN = 40 AND EXCL_NU_ANIO = 2021 AND TEXC_NU_ID =(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 1 AND TEXC_NU_TIPO = 3 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV'));
DELETE FROM "&USUARIO".AACI_EXCLUSION WHERE EXCL_NU_ORDEN = 41 AND EXCL_NU_ANIO = 2021 AND TEXC_NU_ID =(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 1 AND TEXC_NU_TIPO = 3 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV'));
DELETE FROM "&USUARIO".AACI_EXCLUSION WHERE EXCL_NU_ORDEN = 42 AND EXCL_NU_ANIO = 2021 AND TEXC_NU_ID =(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 1 AND TEXC_NU_TIPO = 3 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV'));
DELETE FROM "&USUARIO".AACI_EXCLUSION WHERE EXCL_NU_ORDEN = 43 AND EXCL_NU_ANIO = 2021 AND TEXC_NU_ID =(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 1 AND TEXC_NU_TIPO = 3 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV'));
DELETE FROM "&USUARIO".AACI_EXCLUSION WHERE EXCL_NU_ORDEN = 44 AND EXCL_NU_ANIO = 2021 AND TEXC_NU_ID =(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 1 AND TEXC_NU_TIPO = 3 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV'));

DELETE FROM "&USUARIO".AACI_T_PAISES WHERE TX_CODIGO='27' AND NU_ANIO = 2021;

DELETE FROM "&USUARIO".AACI_T_UNIVERSIDADES;

COMMIT;

SPOOL OFF
SPOOL 03_AACI_inserts.log

DEF USUARIO=AACID_OWNER;

Insert into "&USUARIO".AACI_EXCLUSION values ("&USUARIO".AACI_SEQ_EXCLUSION.NEXTVAL,(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 1 AND TEXC_NU_TIPO = 2 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV')),'No haber presentado la subsanaci�n requerida (art. 22), siempre que lo solicitado se refiera a alg�n requisito exigido por la Orden reguladora.','DGA',95,2021);
Insert into "&USUARIO".AACI_EXCLUSION values ("&USUARIO".AACI_SEQ_EXCLUSION.NEXTVAL,(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 1 AND TEXC_NU_TIPO = 2 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV')),'Subsanaci�n fuera de plazo (art. 22), siempre que lo presentado fuera de plazo se refiera a alg�n requisito exigido por la Orden reguladora.','DGA',96,2021);
Insert into "&USUARIO".AACI_EXCLUSION values ("&USUARIO".AACI_SEQ_EXCLUSION.NEXTVAL,(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 1 AND TEXC_NU_TIPO = 2 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV')),'Subsanaci�n incompleta (art. 22), siempre que lo no presentado se refiera a alg�n requisito exigido por la Orden reguladora.','DGA',97,2021);
Insert into "&USUARIO".AACI_EXCLUSION values ("&USUARIO".AACI_SEQ_EXCLUSION.NEXTVAL,(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 1 AND TEXC_NU_TIPO = 2 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV')),'Desistimiento de la ONGD.','DGA',98,2021);
Insert into "&USUARIO".AACI_EXCLUSION values ("&USUARIO".AACI_SEQ_EXCLUSION.NEXTVAL,(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 1 AND TEXC_NU_TIPO = 2 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV')),'No aportar la documentaci�n que acredite la concesi�n de financiaci�n de las administraciones o instituciones p�blicas o privadas cofinanciadoras de la intervenci�n distintas a las provenientes del pa�s o pa�ses donde se vaya a desarrollar la intervenci�n (art. 21).','VALORADOR,COORDINADOR',99,2021);
Insert into "&USUARIO".AACI_EXCLUSION values ("&USUARIO".AACI_SEQ_EXCLUSION.NEXTVAL,(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 1 AND TEXC_NU_TIPO = 2 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV')),'No aportar documentaci�n acreditativa de la propiedad del terreno y su disponibilidad para que se realice la rehabilitaci�n de inmuebles que contempla el proyecto (art. 21)','VALORADOR,COORDINADOR',100,2021);
Insert into "&USUARIO".AACI_EXCLUSION values ("&USUARIO".AACI_SEQ_EXCLUSION.NEXTVAL,(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 1 AND TEXC_NU_TIPO = 2 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV')),'No aportar certificado de disponibilidad de la autoridad en la zona, cuando la propietaria del terreno o inmueble fuera una administraci�n p�blica o una entidad comunal (art. 21).','VALORADOR,COORDINADOR',101,2021);
Insert into "&USUARIO".AACI_EXCLUSION values ("&USUARIO".AACI_SEQ_EXCLUSION.NEXTVAL,(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 2 AND TEXC_NU_TIPO = 2 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV')),'No tener como contraparte a una o varias Universidades o centros de ense�anza superior, de car�cter p�blico, del pa�s en el que se desarrolla el proyecto (art.3.1.b))','VALORADOR,COORDINADOR',102,2021);
Insert into "&USUARIO".AACI_EXCLUSION values ("&USUARIO".AACI_SEQ_EXCLUSION.NEXTVAL,(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 2 AND TEXC_NU_TIPO = 2 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV')),'No tener documento Marco de Colaboraci�n suscrito con la entidad o entidades contrapartes del proyecto (art. 3.1.b))','VALORADOR,COORDINADOR',103,2021);
Insert into "&USUARIO".AACI_EXCLUSION values ("&USUARIO".AACI_SEQ_EXCLUSION.NEXTVAL,(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 3 AND TEXC_NU_TIPO = 2 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV')),'No acreditar tener como contraparte a entidad o entidades, sin �nimo de lucro, p�blicas o privadas,  legalmente constituidas y/o inscritas como entidades nacionales en los correspondientes Registros oficiales del pa�s en el que se desarrolla el proyecto (art. 3.1.c) y art. 21)','VALORADOR,COORDINADOR',104,2021);
Insert into "&USUARIO".AACI_EXCLUSION values ("&USUARIO".AACI_SEQ_EXCLUSION.NEXTVAL,(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 3 AND TEXC_NU_TIPO = 2 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV')),'No presentar Convenio con el agente andaluz de cooperaci�n, debidamente inscrito en el RACDA, con experiencia en la zona, que act�e como entidad colaboradora, donde se manifieste haber participado en la identificaci�n y formulaci�n del proyecto, as� como sus responsabilidades espec�ficas en la aplicaci�n de la investigaci�n o innovaci�n (art. 3.1.c) y art. 21)','VALORADOR,COORDINADOR',105,2021);
Insert into "&USUARIO".AACI_EXCLUSION values ("&USUARIO".AACI_SEQ_EXCLUSION.NEXTVAL,(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 3 AND TEXC_NU_TIPO = 2 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV')),'No aportar documentaci�n acreditativa del agente andaluz de cooperaci�n con experiencia en la zona y en el sector de intervenci�n, esto es, identificaci�n del agente que va a participar, as� como declaraci�n jurada del representante del agente andaluz de las actividades desarrolladas en la zona (art. 3.1.c) y art. 21)','VALORADOR,COORDINADOR',106,2021);
Insert into "&USUARIO".AACI_EXCLUSION values ("&USUARIO".AACI_SEQ_EXCLUSION.NEXTVAL,(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 3 AND TEXC_NU_TIPO = 2 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV')),'No aportar documentaci�n acreditativa de que la persona coordinadora del proyecto est� en posesi�n del t�tulo de doctor (art. 3.1.c) y art. 21)','VALORADOR,COORDINADOR',107,2021);
Insert into "&USUARIO".AACI_EXCLUSION values ("&USUARIO".AACI_SEQ_EXCLUSION.NEXTVAL,(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 3 AND TEXC_NU_TIPO = 2 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV')),'No aportar documentaci�n del grupo de investigaci�n al que pertenece la persona coordinadora del proyecto (art. 3.1.c) y art. 21) ','VALORADOR,COORDINADOR',108,2021);
Insert into "&USUARIO".AACI_EXCLUSION values ("&USUARIO".AACI_SEQ_EXCLUSION.NEXTVAL,(SELECT TEXC_NU_ID FROM "&USUARIO".AACI_TIPO_EXCLUSION WHERE TEXC_NU_ORDEN= 3 AND TEXC_NU_TIPO = 2 and TCONV_NU_ID = (SELECT TCONV_NU_ID FROM "&USUARIO".AACI_T_TIPO_CONV WHERE TCONV_LI_DENOMINACION = 'UNIV')),'No aportar manifestaci�n por escrito de la contraparte o contrapartes de haber participado en la identificaci�n y formulaci�n del proyecto, as� como de sus responsabilidades espec�ficas en su (art. 21','VALORADOR,COORDINADOR',109,2021);
commit;

SPOOL OFF
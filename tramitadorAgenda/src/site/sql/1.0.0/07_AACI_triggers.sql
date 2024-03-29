SPOOL 07_AACI_trigger.log

DEF USUARIO=AACID_OWNER;

CREATE OR REPLACE TRIGGER "&USUARIO".IGIR_TR_SUBONGD
    BEFORE INSERT ON "&USUARIO".AACI_T_SOLICITUDSUBONGD
FOR EACH ROW
  WHEN (new.ID_SOLICITUD IS NULL)
BEGIN
  :new.ID_SOLICITUD := "&USUARIO".AACI_SEQ_SOLIONGD.NEXTVAL;
END;
/

SPOOL OFF
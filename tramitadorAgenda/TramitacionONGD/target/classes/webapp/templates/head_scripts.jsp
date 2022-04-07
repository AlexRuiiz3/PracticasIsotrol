<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- Forzar compatibilidad a EDGE -->
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<script type="text/javascript">
    // Cambia el nombre de las funciones del plugin JQueryUI para corregir un conflicto con las funciones de bootstrap que usan el mismo nombre.
    //$.widget.bridge('uitooltip', $.ui.tooltip);
    //$.widget.bridge('uibutton', $.ui.button);
</script>


<!-- BOOTSTRAP JS + PLUGINS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js"></script> 
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js"></script>

<script src="https://cdn.datatables.net/plug-ins/1.10.24/sorting/natural.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.js" integrity="sha256-ncetQ5WcFxZU3YIwggfwOwmewLVX4SHLBtDYnrsxooY=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/locales/bootstrap-datepicker.es.min.js" integrity="sha256-K5G+7qV0tjuHL0LlhCU0TqQKR+7QwT8MfEUe2UgpmRY=" crossorigin="anonymous"></script>


<script type="text/javascript" src="<s:url value="/modulos/tramitacionONGD/js/comun.js"/>" ></script>
<script type="text/javascript" src="<s:url value="/modulos/tramitacionONGD/convocatoria/js/validacionesModificarConvocatorias.js"/>" ></script>
<script type="text/javascript" src="<s:url value='/modulos/tramitacionONGD/js/jquery/jquery.validate.js' />"></script>
<script type="text/javascript" src="<s:url value='/modulos/tramitacionONGD/js/jquery/messages_es.js' />"></script>

<!-- Específico -->


<!-- IOS OVERLAY JS -->
<script type="text/javascript" src="<s:url value="/modulos/tramitacionONGD/js/ios-overlay/iosOverlay.js" includeParams="none"/>"></script>
<script type="text/javascript" src="<s:url value="/modulos/tramitacionONGD/js/ios-overlay/spin.min.js" includeParams="none"/>"></script>

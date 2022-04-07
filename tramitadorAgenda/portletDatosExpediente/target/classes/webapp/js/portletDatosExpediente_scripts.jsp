<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">

  var idDivMensajeEspera = "#divMensajeEspera";
  var idDivMensajeEsperaFondo = "#divMensajeEsperaFondo";

  /*
  * Eventos al cargar la página
  */

  $(function() {
    habilitarCapaEspera();
    prepareTablaPortletDatosExpediente();
  });

  function prepareTablaPortletDatosExpediente() {
    limpiarCampos();
    var obtenerDatoExpedienteRequest = $
    .ajax({
      type: 'POST',
      url: '<s:url action="getDatosExpediente.action" namespace="/modulos/portletDatosExpediente" includeParams="none"/>',
      dataType: 'json'
    });

    obtenerDatoExpedienteRequest.fail(function(data) {
      $('#bloqueDatoExpediente').html(data.responseText);
      $('#bloqueMensajes').addClass('mt-3');
      deshabilitarCapaEspera();
    });

    obtenerDatoExpedienteRequest.done(function(data, textStatus, jqXHR) {

      // DATOS EXPEDIENTE
      $("#entidad").html(data.entidad);
      $("#titulo").html(data.titulo);
      $("#numAACID").html(data.numAACID);
      $("#fechaComputo").html(data.fechaComputo);
      $("#numeroExpediente").html(data.numeroExpediente);
      $("#desUnidadOrganica").html(data.desUnidadOrganica);
  
      if (data.bloqueoDTO.usuario != null && data.bloqueoDTO.usuario != '') {
        $('#bloqueUsuarioBloqueo').show();

        //DATOS USUARIO DEL EXPEDIENTE
        $("#nombreUsuarioBloqueo").html(data.bloqueoDTO.usuario);

        if (data.usuSesionEsUsuBloqueo) {
          $('#bloqueUsuarioBloqueo').addClass('label-default');
        } else {
          $('#bloqueUsuarioBloqueo').addClass('label-danger');
        }

        if (data.bloqueoDTO.tipoBloqueo == 'E') {
          $("#tipoBloqueoE").show();
          $("#tipoBloqueoF").hide();
        } else {
          $("#tipoBloqueoF").show();
          $("#tipoBloqueoE").hide();
        }
      }

      if (data.caducado) {
        $('#infoExpedienteCaducado').removeClass('hidden');
      }
      
      deshabilitarCapaEspera();
    });
  }
  
  function limpiarCampos(){
      $("#entidad").html("");
      $("#titulo").html("");
      $("#numAACID").html("");
      $("#fechaComputo").html("");
      $("#numeroExpediente").html("");
      $("#desUnidadOrganica").html("");
      $('#bloqueUsuarioBloqueo').hide();
      $("#tipoBloqueoE").hide();
      $("#tipoBloqueoF").hide();
      $('#infoExpedienteCaducado').addClass('hidden');
  }

  /**
   * Activa la capa de espera.
   */
  function habilitarCapaEspera() {
    centraDiv(idDivMensajeEspera);
    $(idDivMensajeEspera).show();
    $(idDivMensajeEsperaFondo).show();
  }

  /**
   * Desactiva la capa de espera.
   */
  function deshabilitarCapaEspera() {
    $(idDivMensajeEspera).hide();
    $($('#bloqueDatoExpediente').height()).hide();
  }

  /**
   * Centra el contenedor en el centro de la pagina
   *
   * @param idDiv Identificador del contenedor
   * @returns
   */
  function centraDiv(idDiv) {
    var posicionHeight = (((($('#bloqueDatoExpediente').height()) - ($('#bloqueDatoExpediente').height() - ($(idDiv).outerHeight()))) / 2) * 100) / ($('#bloqueDatoExpediente').height());
    var posicionWidth = (((($('#bloqueDatoExpediente').width()) - ($('#bloqueDatoExpediente').width() - ($(idDiv).outerWidth()))) / 2) * 100) / ($('#bloqueDatoExpediente').width());
    $(idDiv).css('top', 50 - posicionHeight + "%");
    $(idDiv).css('left', 50 - posicionWidth + "%");
  }
  
</script>
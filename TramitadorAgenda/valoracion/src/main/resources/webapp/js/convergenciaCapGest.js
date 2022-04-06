var columnsDefsCapGest;
var columnsDefsConvergencia;

function initPestanaConvergenciaCapGest() {
  crearDataTable(window.tablaConvergencia, columnsDefsConvergencia, mensajeEmptyComun, window.urlActionListadoConvergencia);
  crearDataTable(window.tablaCapGestion, columnsDefsCapGest, mensajeEmptyComun, window.urlActionListadoCapGestion);
  cumpleCondicionPestania3 = false;
}

function initParametrizacionConvergencia(nombreColumnasTabla, tamanoColumnasTabla) {
  columnsDefsConvergencia = [
    {
      data: "idCriterio",
      className: "hide"
    },
    {
      data: "descripcion",
      title: nombreColumnasTabla[0],
      width: tamanoColumnasTabla[0]
    },
    {
      data: "valoracion",
      render: function(data, type, full) {
      let inputStr ='<input type="string" class="form-control" id=valorCriterio_' + full.idCriterio + ' value=' + data + ' style="text-align: right;" onchange="javascript:actualizarPuntuacion(this,5);"';
      let esCoordinador = $('#esCoordinador').val();
      let esExterno = $('#esExterno').val();
      if(esCoordinador !== "true" && esExterno !== "true"){
        inputStr += 'readonly ';
      }
      inputStr += '/>';
      
      return inputStr;
      },
      className: "centradoVertical text-center",
      title: nombreColumnasTabla[1],
      width: tamanoColumnasTabla[1]
    },
    {
      data: "puntuacion",
      render: function(data, type, full) {
        return convertirFormatoUE(data, 3);
      },
      className: "centradoVertical text-right",
      title: nombreColumnasTabla[2],
      width: tamanoColumnasTabla[2]
    },
    {
      data: "valorMaximo",
      render: function(data, type, full) {
        return convertirFormatoUE(data, 3);
      },
      className: "centradoVertical text-right",
      title: nombreColumnasTabla[3],
      width: tamanoColumnasTabla[3]
    }
  ];
}

function initParametrizacionCapGestion(nombreColumnasTabla, tamanoColumnasTabla) {
  columnsDefsCapGest = [
    {
      data: "idCriterio",
      className: "hide"
    },
    {
      data: "descripcion",
      title: nombreColumnasTabla[0],
      width: tamanoColumnasTabla[0]
    },
    {
      data: "valoracion",
      render: function(data, type, full) {
        return '<input type="string" class="form-control" id=valorCriterio_' + full.idCriterio + ' value=' + data + ' style="text-align: right;" onchange="javascript:actualizarPuntuacion(this,4);"/>';
      },
      className: "centradoVertical text-center",
      title: nombreColumnasTabla[1],
      width: tamanoColumnasTabla[1]
    },
    {
      data: "puntuacion",
      render: function(data, type, full) {
        return convertirFormatoUE(data, 3);
      },
      className: "centradoVertical text-right",
      title: nombreColumnasTabla[2],
      width: tamanoColumnasTabla[2]
    },
    {
      data: "valorMaximo",
      render: function(data, type, full) {
        return convertirFormatoUE(data, 3);
      },
      className: "centradoVertical text-right",
      title: nombreColumnasTabla[3],
      width: tamanoColumnasTabla[3]
    }
  ];
}

function recargarPestania3() {
  $(window.tablaConvergencia + ':last tr:last-child() td:nth-child(3)').children().addClass("text-hide");
  $(window.tablaConvergencia + ':last tr:last-child() td:nth-child(3)').children().attr("disabled", true)
  $(window.tablaCapGestion + ':last tr:last-child() td:nth-child(3)').children().addClass("text-hide");
  $(window.tablaCapGestion + ':last tr:last-child() td:nth-child(3)').children().attr("disabled", true)
  comprobarPermisosPestaña3();
  if (cumpleCondicionPestania3) {
    $("input[name='valoracionSolicitudDTO.mostrarPestania3']").val(true);
    $("#idNoPermiteEdicionPestania3").hide();
    $("#divContainerDatosPestania3").show();
    $("#idObservConvergencia").prop('disabled', false);
  } else {
    $("input[name='valoracionSolicitudDTO.mostrarPestania3']").val(false);
    $("#idNoPermiteEdicionPestania3").show()
    $("#divContainerDatosPestania3").hide();
    $("#idObservConvergencia").prop('disabled', false);
  }

}
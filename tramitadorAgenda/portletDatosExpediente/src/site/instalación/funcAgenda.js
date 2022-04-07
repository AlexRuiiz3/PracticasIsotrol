var pkexp;

//tabla de imagenes para las tareas  [getTAREA,nombre_del_fichero_imagen]
var img_tareas=new Array();
var _info = navigator.userAgent; 
var _ie = (_info.indexOf("MSIE") > 0 && _info.indexOf("Win") > 0 && _info.indexOf("Windows 3.1") < 0);


  //      *********** Funciones JS relaciones entre capas ***********
     
     //modulos/tramitacion
 function cargarTramitar (para){

     paraLote="&listaLote=";
     if (document.getElementById("listaCheckTramita")){
        var listaCheckTramita=document.getElementById("listaCheckTramita");
        listaCheckTramita = listaCheckTramita.getElementsByTagName("input");
        for (var i =0; i<listaCheckTramita.length; i++){
            if(listaCheckTramita[i].type=="checkbox"){
                if (listaCheckTramita[i].checked){
	                    if (paraLote!="&listaLote=")
	                        paraLote=paraLote+",";
	                    paraLote=paraLote+listaCheckTramita[i].value;
                }
            }
        }
     }

     var paraEstado="";
     paraEstado=paraEstado+"&fechaTra=";
     if (document.getElementById("fechaTra"))
        paraEstado=paraEstado+document.getElementById("fechaTra").value;
     paraEstado=paraEstado+"&fechaLim=";
     if (document.getElementById("fechaLim"))
        paraEstado=paraEstado+document.getElementById("fechaLim").value;
     paraEstado=paraEstado+"&observaciones=";   
     if (document.getElementById("observacionesTramita"))   
	     paraEstado=paraEstado+document.getElementById("observacionesTramita").value;
	 paraEstado=paraEstado+"&reservar=";

     if (document.getElementById("check"))
	 {
        var check=document.getElementById("check").getElementsByTagName("input");
        for (var i=0;i<check.length;i++)
            if (check[i].checked==true)
                paraEstado=paraEstado+check[i].value;
     }
    
     var response = insertarPaginaTramita([
        ["capa_tramita","../modulos/tramitacion/tramita.action",para+paraLote+paraEstado]]);
        
	 if (response != '' && response.indexOf('<div id="tituloPagina" style="color: black;">Avisos</div>')!= -1 ) {
		 abrirPopUp('../modulos/tramitacion/verMensajes.action');
	 } else {
		 
		 insertarPaginaSinDependencia([
        ["estado", "../fase/listarEstadosExpediente.action","PKEXP="+this.pkexp],
        ["capa_tramita", "../modulos/tramitacion/listarTransicionesExpediente.action","PKEXP="+this.pkexp],
        ["ayudaTramitacion", "../modulos/ayudaTramitacion/listarTransicionesExpediente.action","PKEXP="+this.pkexp],
        ["capa_doc_asoc", "../modulos/docsAsociadosExpediente/listarDocumentos.action","PKEXP="+this.pkexp, "replaceLinks('')"],
        ["jerarquizacionDocumentos","../modulos/jerarquizacionDocumentos/inicioJerarquizacionDocumentos.action","PKEXP="+this.pkexp],
        ["capa_tareas_asoc", "../modulos/tareasAsociadasExpediente/listarTareasExpediente.action","PKEXP="+this.pkexp],
        ["capa_doc_perm", "../modulos/bloquesPermitidos/listarBloquesPermitidos.action","PKEXP="+this.pkexp,"document.getElementById('capa_auxiliar').style.display='none'"],
        ["capa_funciones", "../fase/mostrarFunciones.action","PKEXP="+this.pkexp]],false);
		 		 
		 if(response != '' && response.indexOf('<legend>Avisos de tramitaci')!= -1){
			
			var width = screen.width*70/100;
			var height = screen.height*70/100;
			var leftVal = (screen.width - width) / 2;
			var topVal = (screen.height - height) / 2;
			tareasWindowAvisos= window.open('../modulos/tramitacion/visualizarAvisos.action','Avisos','left='+leftVal+',top='+topVal+',toolbar=no,status=no,resizable=yes,scrollbars=yes');
			tareasWindowAvisos.resizeTo(width,height);
		 }
		 
		 recargarModulosObservadores('capa_tramita');
	 }
	 
     if (document.getElementById("capa_tramita_lote").style.display=="inline")
        contraeTramitaLote();
     if (document.getElementById("capa_tramita_estado").style.display=="inline")
        contraeTramitaEstado();
 }
     
 function deshacerTramitacion(faseActual)
 {
     insertarPaginaAS([
         ["vaciar","../modulo/tramitacion/deshacerTramitacion.action","faseAct="+faseActual]],true);
 }
     
 function deshacer() {
	 insertarPaginaSinDependencia([
        ["estado", "../fase/listarEstadosExpediente.action","PKEXP="+this.pkexp],
        ["capa_tramita", "../modulos/tramitacion/listarTransicionesExpediente.action","PKEXP="+this.pkexp],
        ["ayudaTramitacion", "../modulos/ayudaTramitacion/listarTransicionesExpediente.action","PKEXP="+this.pkexp],
        ["capa_doc_asoc", "../modulos/docsAsociadosExpediente/listarDocumentos.action","PKEXP="+this.pkexp, "replaceLinks('')"],
        ["jerarquizacionDocumentos","../modulos/jerarquizacionDocumentos/inicioJerarquizacionDocumentos.action","PKEXP="+this.pkexp],
        ["capa_tareas_asoc", "../modulos/tareasAsociadasExpediente/listarTareasExpediente.action","PKEXP="+this.pkexp],
        ["capa_doc_perm", "../modulos/bloquesPermitidos/listarBloquesPermitidos.action","PKEXP="+this.pkexp,"document.getElementById('capa_auxiliar').style.display='none'"],
        ["capa_funciones", "../fase/mostrarFunciones.action","PKEXP="+this.pkexp]],false);
	 recargarModulosObservadores('capa_tramita');
}
     
    
    
    
     function ejecutarTarea (para)
     {
	     insertarPagina([
        ["vaciar","../agenda/ejecutarTarea.do",para],
        ["capa_tramita", "../modulos/tramitacion/listarTransicionesExpediente.action","PKEXP="+this.pkexp],
        ["ayudaTramitacion", "../modulos/ayudaTramitacion/listarTransicionesExpediente.action","PKEXP="+this.pkexp],
        ["capa_doc_perm", "../modulos/bloquesPermitidos/listarBloquesPermitidos.action","PKEXP="+this.pkexp,"document.getElementById('capa_auxiliar').style.display='none'"],
        ["portletDatosExpediente", "../modulos/portletDatosExpediente/inicio.action","PKEXP="+this.pkexp]],true)
     }
     
     
     function seleccionarFaseActual(para){
        insertarPaginaAS([["estado", "../fase/cambiarFaseActual.action", "faseSeleccionada="+para]]);

		//recargamos la pagina entera para que vuelva a controlar la visibilidad por fases.
        insertarPaginaSinDependencia([
	        ["estado", "../fase/listarEstadosExpediente.action","PKEXP="+this.pkexp],
	        ["capa_tramita", "../modulos/tramitacion/listarTransicionesExpediente.action","PKEXP="+this.pkexp],
	        ["ayudaTramitacion", "../modulos/ayudaTramitacion/listarTransicionesExpediente.action","PKEXP="+this.pkexp],
	        ["capa_doc_asoc", "../modulos/docsAsociadosExpediente/listarDocumentos.action","PKEXP="+this.pkexp, "replaceLinks('')"],
	        ["jerarquizacionDocumentos","../modulos/jerarquizacionDocumentos/inicioJerarquizacionDocumentos.action","PKEXP="+this.pkexp],
	        ["capa_tareas_asoc", "../modulos/tareasAsociadasExpediente/listarTareasExpediente.action","PKEXP="+this.pkexp],
	        ["capa_doc_perm", "../modulos/bloquesPermitidos/listarBloquesPermitidos.action","PKEXP="+this.pkexp,"document.getElementById('capa_auxiliar').style.display='none'"]],false);
     }     
    
	
	function ejecutarFiltroFaseTarea (checked) {
		insertarPaginaUnicoPortlet([
	        ["capa_tareas_asoc", "../modulos/tareasAsociadasExpediente/listarTareasExpediente.action","fase="+checked,"replaceLinks('" + checked + "')"]],true)        
	}
    
     function usuariosAsignados(para){
     document.getElementById("capa_informacion").style.width= "35%";
     if (para == null)
        insertarPagina([["capa_informacion", "../agenda/listarUsuarios.do","PKEXP="+this.pkexp,"iniciarUsuarios()"]],true)
      else
        insertarPagina([["capa_informacion", "../agenda/listarUsuarios.do",para,"iniciarUsuarios()"]],true)
     }     
     
     
     
    //               *********** Funciones JS varias ***********
        
    //funcion para seleccionar todos los expedientes de la tramitacion en lote
    function allLote(){
     var listaCheckTramita=document.getElementById("listaCheckTramita");
     listaCheckTramita = listaCheckTramita.getElementsByTagName("input");

     if (document.getElementById("selectLote").checked)
        var marcar="si";
      else
        var marcar="no";

     for (var i =0; i<listaCheckTramita.length; i++)
        if(listaCheckTramita[i].type=="checkbox"){
            if (marcar=="si")
                listaCheckTramita[i].checked=true;
             else if (marcar=="no")
                listaCheckTramita[i].checked=false;
        }
    }
        
    
    //Funciones para expandir o contraer el estado de las transiciones
    function marcarTramita(refTransicion){
    var id="caja"+refTransicion;
    var imagen=document.getElementById(id).style.background;
    if (document.getElementById(id).className=="noPulsadoTramita")
        {
        
        var tables = document.getElementById("contenedorBotonesTransiciones").getElementsByTagName("table");
        for (var i=0; i<tables.length;i++)
            tables[i].className="noPulsadoTramita";
        document.getElementById(id).className='pulsadoTramita';
        marcarTramitaDesplazaEstado();
        document.getElementById("capa_tramita_estado").style.display="inline";
        insertarPagina([["capa_tramita_estado", "../modulos/tramitacion/mostrarEstadoTramita.action","REFTRANSICION="+refTransicion]],true);
        }
     else
        {
        desmarcarTramitaDesplazaEstado();
        document.getElementById(id).className='noPulsadoTramita';
        //document.getElementById("capa_tramita_estado").style.display="none";
        document.getElementById("capa_tramita_estado").innerHTML="";
        }
    }
    
    
    function  contraeTramitaEstado (){
        document.getElementById("capa_tramita_estado").style.display="none";
        document.getElementById("capa_tramita_estado").innerHTML="";
    }
    
    
    //Funciones de avisos para la ventana de estado de las tramitaciones
    function abrirAvisosTramita(capa) { 	
        document.getElementById("capa_fondo").style.display="inline";
        document.getElementById("capaAvisosTramita").style.display="inline";
        var contenido="";
        contenido=contenido+"<div style='width:100%' class='cabeceraAvisos'><b>"+capa.substring(5)+"</b>";

        contenido=contenido+"</div>";
        contenido=contenido+"<div id='contenidoAvisosTramita'>";
        
        var inputs = document.getElementById(capa).getElementsByTagName("input");
        for (var i=0;i<inputs.length;i++)
            {
            contenido=contenido+"<table width='98%'><tr><td width='10%'><img src='../../../instalaciones/imagenes/administracion/alerta.png' alt='Alerta' ></td>";
            contenido=contenido+"<td><a>"+inputs[i].value+"</a></td></tr></table>";
            if (i!=inputs.length-1)
            contenido=contenido+"<hr />";
            }
        
        contenido=contenido+"</div>";
        document.getElementById("capaAvisosTramita").innerHTML=contenido;
    }
    
    function cerrarAvisosTramita(){
    document.getElementById("capaAvisosTramita").style.display="none";
    document.getElementById("capa_fondo").style.display="none";
    document.getElementById("capaAvisosTramita").innerHTML="";
    }
    
    
    
    //Funciones para expandir o contraer un mensaje recibido por el usuario
    function marcarMensaje(refMensaje){
	    var id="caja"+refMensaje;
	    var imagen=document.getElementById(id).style.background;
	    if (document.getElementById(id).className=="noPulsadoMensaje")
	    {
	        var tables = document.getElementById("contenedorMensajes").getElementsByTagName("table");
	        for (var i=0; i<tables.length;i++)
	        {
	            tables[i].className="noPulsadoMensaje";
	        }
	        document.getElementById(id).className='pulsadoMensaje';
	        marcarMensajeDesplazaDetalle();
	        document.getElementById("capa_mensaje_detalle").style.display="inline";
	        insertarPagina([["capa_mensaje_detalle", "../agenda/verMensaje.do","REFMENSAJE="+refMensaje]],true);
	    }
	    else
	    {
	        desmarcarMensajeDesplazaDetalle();
	        document.getElementById(id).className='noPulsadoMensaje';
	        document.getElementById("capa_mensaje_detalle").innerHTML="";
	    }
    }
    
     
     
    //funcion generica para tomar elementos a partir de su clase
    function getElementsByClassName(oElm, strTagName, strClassName){
        var arrElements = (strTagName == "*" && oElm.all)? oElm.all : oElm.getElementsByTagName(strTagName);
        var arrReturnElements = new Array();
        strClassName = strClassName.replace(/\-/g, "\\-");
        var oRegExp = new RegExp("(^|\\s)" + strClassName + "(\\s|$)");
        var oElement;
        for(var i=0; i<arrElements.length; i++){
            oElement = arrElements[i];
            if(oRegExp.test(oElement.className)){
                arrReturnElements.push(oElement);
            }
        }
        return (arrReturnElements);
    } 
 
    


        
        //Cambiar enlaces de paginacion sobre la tabla de usuarios asignados
        function iniciarUsuarios () {
            //obtenemos los elementos de paginacion para cambiarlos
            var piesdepaginas = getElementsByClassName(document, "table", "tabla_usuarios");
            if (piesdepaginas[0] != null )
            {
                piesdepaginas = piesdepaginas[0].getElementsByTagName("a");
                for (var i = 0; i < piesdepaginas.length; i++) {
                    temp=piesdepaginas[i].href.split('?');
                    piesdepaginas[i].href = "javascript:usuariosAsignados('"+temp[1]+"');";
                }
            }
        } 
        
        
        
        //funcion para comprobar el campo file de los documentos permitidos
        function incorporarDocumento()
        {
            if (document.getElementById("campo_texto_file").value == null || document.getElementById("campo_texto_file").value == "")
                alert ("Elija un archivo a incorporar");
             else
                document.getElementById("incorpora").submit();
        }
        
        
        // funcion para abrir documentos
        function abrirDocumento(url)
        {
            win = window.open(url,'Documento','resizable=yes,width=700,height=450,top=150,left=150,status=false,toolbar=false'); 
        }
        
        
        //funcion para recargar la pagina de documentos asociados e inicia los enlaces
        function recargaPaginaTarea (capa,pagina) {
     ;
         var parametro = "";
          
         if (pagina.indexOf("?") != -1) 
           {
               var s = pagina.split('?');
               pagina = s[0];
               parametro = s[1];
           }
        insertarPagina([
        [capa,pagina,parametro]], true); 
        }
/*        
      
 */       
    
        
        //funcion para eliminar la tarea
        function ejecutaAccionSobreTarea (direccion,parametros)
        {	  
	        insertarPagina([
	        ['capa_tareas_asoc',direccion,parametros,""],
	        ["capa_doc_perm", "../modulos/bloquesPermitidos/listarBloquesPermitidos.action","PKEXP="+this.pkexp,"document.getElementById('capa_auxiliar').style.display='none'"],
	        ["ayudaTramitacion", "../modulos/ayudaTramitacion/listarTransicionesExpediente.action","PKEXP="+this.pkexp],
	        ["capa_tramita", "../modulos/tramitacion/listarTransicionesExpediente.action","PKEXP="+this.pkexp]],true);
	        recargarModulosObservadores('capa_tareas_asoc');
        }
        
        function out_over_documentos(accion,documento,imagen)
        {
	        var cont=0;
            if(imagen!=''){
    	             if (accion == "over")
		                document.getElementById(documento).src="../instalaciones/imagenes/agenda/"+imagen+"2.gif"
		              else if (accion == "out")
		                document.getElementById(documento).src="../instalaciones/imagenes/agenda/"+imagen+".gif"
            
            }else{
		             if (accion == "over")
		                document.getElementById(documento).src="../../../instalaciones/imagenes/informe2.gif"
		              else if (accion == "out")
		                document.getElementById(documento).src="../../../instalaciones/imagenes/informe.gif"
            }
        }
        
                

        function cargaPaginaAdjuntarDiv (direccion)
        {
            direccion=direccion+'&PKEXP='+this.pkexp;

			ventanaTarea(direccion+'&PKEXP='+this.pkexp);
        }
        
        function cargaPaginaTareaConNombreFichero (direccion, expediente)
        {
        	var tarea = direccion.substring(12);
            direccion= '../agenda/tareas/' + direccion+'.do?PKEXP='+expediente+'&salida=si';

			ventanaTarea(direccion);						
	
			//tareasWindow = window.open(direccion,'tarea','left='+leftVal+',top='+topVal+',toolbar=no,status=no,resizable=yes,scrollbars=yes');
            //tareasWindow.resizeTo(width,height);    
        }
        
        //funciones para ejecutar las "funciones" del escritorio
        function funcion_modela ()
        {
            var nueva = window.open('../agenda/funciones/appletModela.jsp?'+'&url='+location.href,'appletModela','menubar=no,resizable=yes,left=0,top=0,titlebar=yes,status=yes;');//,'applet Modela',"resizable=no,scrollbars=yes");
            nueva.resizeTo(screen.availWidth,screen.availHeight);
        }
        
        //funciones para ejecutar las "funciones" del escritorio
        function funcion_escaner (parametros)
        {
            var nueva = window.open('../modulos/comun/funciones/appletEscaner.jsp?'+parametros+'&url='+location.href,'appletEscaner','menubar=no,resizable=yes,left=0,top=0,titlebar=yes,status=yes;');
            nueva.resizeTo(screen.availWidth,screen.availHeight);
        }
        
        function abreVentanaEvol(parametros)
        {
            document.getElementById("capa_fondo").style.display="inline";
            document.getElementById("capa_contenedora_popup").style.display="inline";
            insertarPagina([["capa_contenedora_popup","../agenda/listarEvolucion.do"]],true)
        }
        
        
        
       
        
        
        function abreVentanaRelacionesExp(parametros)
        {
            document.getElementById("capa_fondo").style.display="inline";
            document.getElementById("capa_emergente").style.display="inline";
            insertarPagina([["capa_emergente","../agenda/funciones/trrelacionesexp.jsp?",parametros]],true)
        }
        
        function ventanaTarea(pagina)
        {
      		  
            document.getElementById("capa_fondo").style.display="inline";
            document.getElementById("capa_contenedora_popup").style.display="inline";
	        insertarPagina([["capa_contenedora_popup",pagina,""]],true);            
        }
        
        function ventanaTareaDim(pagina, width, height, leftVal, topVal)
        {
            document.getElementById("capa_fondo").style.display="inline";
            document.getElementById("capa_contenedora_popup").style.display="inline";
            document.getElementById("capa_contenedora_popup").style.width = width;
            document.getElementById("capa_contenedora_popup").style.height = height;            
            document.getElementById("capa_contenedora_popup").style.left = leftVal;            
            document.getElementById("capa_contenedora_popup").style.top = topVal;                                    
	        insertarPagina([["capa_contenedora_popup",pagina,""]],true);            
        }
                
        function abrirPopUp(pagina)
        {
            document.getElementById("capa_contenedora_popup").style.display="inline";
            document.getElementById("capa_fondo").style.display="inline";
	        insertarPagina([["capa_contenedora_popup",pagina,""]],true);            
        }        
        
        //funciones de calendario
        function calendarTramita(cal){
            if (cal=="actual")
                Calendar.setup({inputField:"fechaTra",
                                    button:"calendarioActual"});  
            else
                Calendar.setup({inputField:"fechaLim",
                                    button:"calendarioLimite"});  
          }
        
        
        //pruebas
        function  tramita_remoto()
        {
        window.open("listarExpedientesRemotos.do");
        }
        

	 function sustituirInput () {
	
		var params = '';	
		for (i = 0; i < window.parent.document.all.tags("input").length ; ++i) {
			var obj = window.parent.document.all.tags("input")[i];
			if (obj != null) {
				id = obj.id;
				if (id != null && id != '' && id.substring(0,3) == "txt") {
					nombreParam = id.substring(3);
					nombreParam = nombreParam.charAt(0).toLowerCase() + nombreParam.substring(1);
					params += '&' + nombreParam + '=' + obj.value;
				}
			}		
	    }
	    
		for (i = 0; i < window.parent.document.all.tags("select").length ; ++i) {
			var obj = window.parent.document.all.tags("select")[i];
			if (obj != null) {
				id = obj.id;
				if (id != null && id != '' && id.substring(0,3) == "sel") {
					nombreParam = id.substring(3);
					nombreParam = nombreParam.charAt(0).toLowerCase() + nombreParam.substring(1);
					params += '&' + nombreParam + '=' + obj.value;
				}
			}		
	    }

		for (i = 0; i < window.parent.document.all.tags("textarea").length ; ++i) {
			var obj = window.parent.document.all.tags("textarea")[i];
			if (obj != null) {
				id = obj.id;
				if (id != null && id != '' && id.substring(0,3) == "txt") {
					nombreParam = id.substring(3);
					nombreParam = nombreParam.charAt(0).toLowerCase() + nombreParam.substring(1);
					params += '&' + nombreParam + '=' + obj.value;
				}
			}		
	    }
	    
	    return params;
	 }
	 

        
	 
     function ejecutarTarea (idExp, idTarea)
     {
     	 params = sustituirInput();     	 
         insertarPagina([
            ["capa_ejecutarTarea","./ejecutarTarea" + idTarea + ".do","PKEXP="+idExp+params,""]],true);     	
     }     

     function ejecutarTareaDatosExp (idExp, idTarea)
     {
     	 params = sustituirInput();     	 
         insertarPagina([
            ["capa_ejecutarTarea","../agenda/tareas/ejecutarTarea" + idTarea + ".do","PKEXP="+idExp+params,""],
            ["portletDatosExpediente", "../modulos/portletDatosExpediente/inicio.action","PKEXP="+idExp]],true);
     } 
     
     function ejecutarTareaSubirDoc (idExp, idTarea)
     {  
     	 params = sustituirInput();

         insertarPagina([
            ["capa_ejecutarTarea","../agenda/tareas/ejecutarTarea" + idTarea + ".do","PKEXP="+idExp+params,""],
			["capa_doc_asoc", "../modulos/docsAsociadosExpediente/listarDocumentos.action","PKEXP="+idExp, "replaceLinks('')"],
			["jerarquizacionDocumentos","../modulos/jerarquizacionDocumentos/inicioJerarquizacionDocumentos.action","PKEXP="+this.pkexp]],
            true);
         recargarModulosObservadores('jerarquizacionDocumentos');
     }     
     
     
       	function abreVentanaExpedientesRelacionados(parametros)
        {
          abrirPopUpModal("../funciones/expedientesRelacionados.action",parametros);
        }     
     
        
        
       	function abreVentanaCambioProc(parametros)
        {
          abrirPopUpModal("../cambioProcedimiento/cambioProcedimiento.action",parametros);
        }
        
        
       function notificarDocumento(parametros)
       
        {
        var width = screen.width*70/100;
		var height = screen.height*70/100;
		var leftVal = (screen.width - width) / 2;
		var topVal = (screen.height - height) / 2;
		tareasWindow = window.open("../modulos/notifica/notificarDocumentoInteresado.action?"+parametros, 'tarea','left='+leftVal+',top='+topVal+',toolbar=no,status=no,resizable=yes,scrollbars=yes');
		tareasWindow.resizeTo(width,height);    
      
          
        }
       
       function cargarApplet(nombreApplet, codeApplet, archiveApplet, codebase, urlXML, urlComponentes, parametrosJVM) {
    	   var applet = '<APPLET id='+ nombreApplet +' archive='+ archiveApplet +' CODE='+ codeApplet +' codebase='+ codebase +' name='+ nombreApplet +' width="1" height="1" ALIGN="bottom" MAYSCRIPT>';
    	   applet = applet + buildParamTag('urlXML', urlXML);
    	   applet = applet + buildParamTag('urlComponentes', urlComponentes);
    	   applet = applet + buildParamTag('java_arguments', parametrosJVM);
    	   applet = applet + '</APPLET>';

    	   document.getElementById("divWebOffice").innerHTML = applet;

       }
       
       function buildParamTag(name, value) {
           return '<PARAM NAME="' + name + '" VALUE="' + value + '">';
       }
       
       //Script para mostrar div con la botonera asociada.
       //Funcion que muestra el div en la posicion del mouse
       function showdivBotoneraTramitacion(event, modulo) {
       	//determina un margen de pixels del div al raton
       	margin=5;

       	//La variable IE determina si estamos utilizando IE
       	var IE = document.all?true:false;

       	var tempX = 0;
       	var tempY = 0;

       	//document.body.clientHeight = devuelve la altura del body
       	if(IE) { //para IE
       		//event.y|event.clientY = devuelve la posicion en relacion a la parte superior visible del navegador
       		//event.screenY = devuelve la posicion del cursor en relaciona la parte superior de la pantalla
       		//event.offsetY = devuelve la posicion del mouse en relacion a la posicion superior de la caja donde se ha pulsado
       		tempX = event.x
       		tempY = event.y
       		if(window.pageYOffset) {
       			tempY=(tempY+window.pageYOffset);
       			tempX=(tempX+window.pageXOffset);
       		} else {
       			tempY=(tempY+Math.max(document.body.scrollTop,document.documentElement.scrollTop));
       			tempX=(tempX+Math.max(document.body.scrollLeft,document.documentElement.scrollLeft));
       		}
       	} else { //para netscape
       		//window.pageYOffset = devuelve el tama�o en pixels de la parte superior no visible (scroll) de la pagina
       		document.captureEvents(Event.MOUSEMOVE);
       		tempX = event.pageX;
       		tempY = event.pageY;
       	}

       	if (tempX < 0) {tempX = 0;}
       	if (tempY < 0) {tempY = 0;}

       	//modificamos el valor del id posicion para indicar la posicion del mouse
       	//document.getElementById('posicion').innerHTML="PosX = "+tempX+" | PosY = "+tempY;

       	//window.alert(event.pageYOffset+" - "+document.body.pageYOffset+" - "+screen.pageYOffset+" - "+this.pageYOffset+" - "+window.pageYOffset);

       	document.getElementById(modulo).style.top = (tempY+margin)+"px";
       	document.getElementById(modulo).style.left = (tempX+margin)+"px";
       	document.getElementById(modulo).style.display='block';
       	return;
       }
       
       /**
        * Funci�n que realiza la operaci�n pasada por par�metro.
        * Uno de los par�metros es el id del div donde queremos insertar
        * la respuesta devuelta por el servidor.
        * 
        * IMPORTANTE: eSTA FUNCI�N SOLO REALIZA UNA PETICI�N AL
        * SERVIDOR. POR ELLO ESPERAMOS A QUE LA RESPUESTA DEL MISMO
        * SEA QUE HA FINALIZADO.
        * 
        */
       function insertarPaginaAyudaTramitacion (array, idModulo) {
       	
       	   function Ajax() 
       	   {
       	       var xmlhttp = false;
       	       try {
       	           xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
       	           } catch (e) {
       	           try {
       	               xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
       	               } catch (E) {
       	               xmlhttp = false;
       	               }
       	           }
       	       if (!xmlhttp && typeof XMLHttpRequest != 'undefined') 
       	       {
       	           xmlhttp = new XMLHttpRequest();
       	       }
       	      
       	      if (xmlhttp == null)
       	        alert("El objeto utilizado en Ajax (XMLHttpRequest) ha generado un error.");
       	      return xmlhttp;
       	   }	
          
           var ajax = new Ajax();  
       	   
           if (document.getElementById(array[0][1]) != "undefined") {
                     	     	   
           	  var contenido = "";
           	  ajax.open("POST", array[0][1], true);
           	  ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");		       
           	  ajax.onreadystatechange = function () {
             		  if (ajax.readyState == 4) {
        	        	   
             			  contenido = 	ajax.responseText;
             			  
             			  if (document.getElementById(idModulo) != null) {	           	   
             				document.getElementById(idModulo).innerHTML = contenido;           			           
             			  }              	       
        	              
             			  if (array[0][3]){
             				  eval(array[0][3]);
             			  } 
             			  return true;
             		  } else
             			  return false;
             	  }
             	  if (array[0][2] != "undefined") {
             		  if (array[0][2] == null)
             			  ajax.send(""); 
             		  else
             			  ajax.send(array[0][2]); 
             	  }
       	   		}         
       	}
       
       /**
        * Funcion que establece la imagen de espera en 
        * el div oculto de la botonera
        * @param modulo
        * @param contenido
        * @return
        */
       function establecerEsperaTramitacion (modulo) {
       	document.getElementById(modulo).innerHTML = "<div style='width:30px;height:30px;' class='contenido_tabla_portlet'><img src='../instalaciones/imagenes/espera.gif' style='float: left;margin-top:4px;'/></div>";		           	 
       }
       
       //Funcion para cerrar el div oculto asociado
       //a cada documento
       function cerrarDivOcultoTramitacion(modulo) {
         var divCerrar = modulo.parentNode;
         var idListaCondiciones = divCerrar.parentNode;
         
         document.getElementById(idListaCondiciones.id).innerHTML = '';     
         document.getElementById(idListaCondiciones.id).style.visibility = 'hidden';
         
         return false;
       }
       
       function ejecutarFiltroEventoAyuda (checked) {
       	insertarPaginaUnicoPortlet([
             	["ayudaTramitacion", "../modulos/ayudaTramitacion/listarTransicionesExpediente.action","PKEXP="+this.pkexp+"&evento="+checked]],true);	       
       }


	       
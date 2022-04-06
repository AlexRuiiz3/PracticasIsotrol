function cargaPaginaTareaDiv (direccion)
{ 
	var tarea = direccion.substring(12);	
	direccion='../agenda/tareas/'+direccion+'.action?PKEXP='+this.pkexp+'&salida=si&idTarea='+tarea;   
	ventanaTarea(direccion);
}
		
//funcion para ejecutar las tareas en una ventana nueva
function tareas(direccion,parametro,parametro2,parametro3)
{  
	insertarPaginaTareaAvisos([
    ["vaciar","../modulos/bloquesPermitidos/iniciaTarea.action",'idTarea='+parametro+'&idTareaFase='+parametro2+'&idTipoTarea='+parametro3,"cargaPaginaTarea('tareaEntrada"+ direccion + "')"],
    ["capa_tramita", "../modulos/tramitacion/listarTransicionesExpediente.action","PKEXP="+this.pkexp],
    ["ayudaTramitacion", "../modulos/ayudaTramitacion/listarTransicionesExpediente.action","PKEXP="+this.pkexp],
    ["capa_tareas_asoc", "../modulos/tareasAsociadasExpediente/listarTareasExpediente.action","PKEXP="+this.pkexp, "replaceLinksTareas('')"],
	["capa_doc_perm", "../modulos/bloquesPermitidos/listarBloquesPermitidos.action","PKEXP="+this.pkexp,"document.getElementById('capa_auxiliar').style.display='none'"]],true);
	recargarModulosObservadores('capa_tareas_asoc');
} 


//funcion para ejecutar las tareas en un el div del popup
function tareasDiv(direccion,parametro)
{  
    insertarPagina([
    ["vaciar","../modulos/bloquesPermitidos/iniciaTarea.action",'idTarea='+parametro,"cargaPaginaTareaDiv('tareaEntrada"+ direccion + "')"],
    ["capa_tramita", "../modulos/tramitacion/listarTransicionesExpediente.action","PKEXP="+this.pkexp],
    ["ayudaTramitacion", "../modulos/ayudaTramitacion/listarTransicionesExpediente.action","PKEXP="+this.pkexp],
    ["capa_tareas_asoc", "../modulos/tareasAsociadasExpediente/listarTareasExpediente.action","PKEXP="+this.pkexp, "replaceLinksTareas('')"],
    ["capa_doc_perm", "../modulos/bloquesPermitidos/listarBloquesPermitidos.action","PKEXP="+this.pkexp,"document.getElementById('capa_auxiliar').style.display='none'"]],true);
    recargarModulosObservadores('capa_tareas_asoc');
} 

function ejecutaAccionSobreTarea (direccion,parametros)
{	
	insertarPagina([
	['capa_tareas_asoc',direccion,parametros, "replaceLinksTareas('')"],
	["capa_doc_perm", "../modulos/bloquesPermitidos/listarBloquesPermitidos.action",""],
	["ayudaTramitacion", "../modulos/ayudaTramitacion/listarTransicionesExpediente.action",""],
	["capa_tramita", "../modulos/tramitacion/listarTransicionesExpediente.action",""]],true);
	recargarModulosObservadores('capa_tareas_asoc');
}

function ejecutaEliminacionSobreTarea (direccion,parametros)
{	      	
	insertarPaginaEliminacion([
	['capa_tareas_asoc',direccion,parametros, "replaceLinksTareas('')"],
	["capa_doc_perm", "../modulos/bloquesPermitidos/listarBloquesPermitidos.action",""],
	["ayudaTramitacion", "../modulos/ayudaTramitacion/listarTransicionesExpediente.action",""],
	["capa_tramita", "../modulos/tramitacion/listarTransicionesExpediente.action",""]],true);
	recargarModulosObservadores('capa_tareas_asoc');
}

        
function out_over_tareas(accion,tarea,imagen)
{	
	var cont=0;
	if(imagen!=''){
		if (accion == "over")
			document.getElementById(tarea).src="../instalaciones/imagenes/agenda/"+imagen+"2.gif"
		                
			else if (accion == "out")
				document.getElementById(tarea).src="../instalaciones/imagenes/agenda/"+imagen+".gif"
            
	}else{
		if (accion == "over")
			document.getElementById(tarea).src="../instalaciones/imagenes/tarea2.gif"
				else if (accion == "out")
					document.getElementById(tarea).src="../instalaciones/imagenes/tarea.gif"
	}
}
 
         
function cargaPaginaTareaDiv (direccion)
{ 
	var tarea = direccion.substring(12);        	    	
	direccion='../modulos/tareas_subv/'+direccion+'.action?PKEXP='+this.pkexp+'&salida=si&idTarea='+tarea;
	ventanaTarea(direccion);
}
       
    
function ventanaTarea(pagina)
{	
	document.getElementById("capa_fondo").style.display="inline";
	document.getElementById("capa_contenedora_popup").style.display="inline";
	insertarPaginaUnicoPortlet([["capa_contenedora_popup",pagina,""]],true);            
}
        
                
function ventanaTareaSinDiv(pagina)
{	 
	cargaPaginaTareaSinDiv (pagina);      
}

              
function ejecutarTarea (idExp, idTarea)
{
	params = sustituirInput();     	 
	insertarPagina([["capa_ejecutarTarea","./ejecutarTarea" + idTarea + ".do","PKEXP="+idExp+params,""]],true);     	
} 
   
function ejecutarTareaDatosExp (idExp, idTarea)
{
	params = sustituirInput();     
     	 
	insertarPagina([["capa_ejecutarTarea","../agenda/tareas/ejecutarTarea" + idTarea + ".do","PKEXP="+idExp+params,""],
			["portletDatosExpediente", "../modulos/portletDatosExpediente/inicio.action","PKEXP="+idExp]],true);
}
     
function ejecutarTareaSubirDoc (idExp, idTarea)
{ 
	params = sustituirInput();

	insertarPagina([
	                ["capa_ejecutarTarea","../agenda/tareas/ejecutarTarea" + idTarea + ".do","PKEXP="+idExp+params,""],
	                ["capa_doc_asoc", "../modulos/docsAsociadosExpediente/listarDocumentos.action","PKEXP="+idExp,"replaceLinks('')"],
	                ["jerarquizacionDocumentos","../modulos/jerarquizacionDocumentos/inicioJerarquizacionDocumentos.action","PKEXP="+this.pkexp]],
	                true);
	recargarModulosObservadores('capa_doc_asoc');
	recargarModulosObservadores('jerarquizacionDocumentos');
} 
     
function cargaPaginaTarea (direccion)
{
	
	var tarea = direccion.substring(12);
     	
	var indice = -1;
	indice = tarea.indexOf(':FORMULA');
	if (indice == '-1')
	{

		var indice2 = -1; 
		indice2 = tarea.indexOf(':PROC');
		if (indice2 == '-1')
		{
			//aqui establecer direccion al action que te dice si una tarea es generada o no.
			direccion='../agenda/tareas/'+direccion+'.action?PKEXP='+this.pkexp+'&salida=si&idTarea='+tarea;
	        var width = screen.width*70/100;
	        var height = screen.height*70/100;
			var leftVal = (screen.width - width) / 2;
			var topVal = (screen.height - height) / 2;
			tareasWindow = window.open(direccion,'tarea','left='+leftVal+',top='+topVal+',toolbar=no,status=no,resizable=yes,scrollbars=yes');
	        tareasWindow.resizeTo(width,height);    
			
		} else {
			cargaPaginaTareaProcesa (tarea);
		}
	}
	else 
	{
		cargaPaginaTareaFormula (tarea);
	}	
    
}

function cargaPaginaTareaFormula (tarea)
{
	
	//Obtenemos el action de inicio
	var array = new Array();
	array = tarea.split(':');
	var actionInicio = array[3];
	
	//aqui establecer direccion al action que te dice si una tarea es generada o no.
	direccion='../formula/' + actionInicio + '.action?nombreTarea='+tarea;
	var width = screen.width*70/100;
	var height = screen.height*70/100;
	var leftVal = (screen.width - width) / 2;
	var topVal = (screen.height - height) / 2;
	tareasWindow = window.open(direccion,'tarea','left='+leftVal+',top='+topVal+',toolbar=no,status=no,resizable=yes,scrollbars=yes');
	tareasWindow.resizeTo(width,height);            	
}

function cargaPaginaTareaProcesa (tarea)
{
	//Obtenemos el action de inicio
	var array = new Array();
	array = tarea.split(':');
	var namespace = array[2];
	
	//aqui establecer direccion al action que te dice si una tarea es generada o no.
	direccion='..'+namespace+'/inicioProcesa.action?nombreTarea='+tarea;
	var width = screen.width*70/100;
	var height = screen.height*70/100;
	var leftVal = (screen.width - width) / 2;
	var topVal = (screen.height - height) / 2;
	tareasWindow = window.open(direccion,'tarea','left='+leftVal+',top='+topVal+',toolbar=no,status=no,resizable=yes,scrollbars=yes');
	tareasWindow.resizeTo(width,height);            	
}
    
function cargaPaginaTareaSinDiv (direccion)
{
	var tarea = direccion.substring(12);
	//aqui establecer direccion al action que te dice si una tarea es generada o no.
     
	var width = screen.width*70/100;
	var height = screen.height*70/100;
	var leftVal = (screen.width - width) / 2;
	var topVal = (screen.height - height) / 2;
	tareasWindow = window.open(direccion,'tarea','left='+leftVal+',top='+topVal+',toolbar=no,status=no,resizable=yes,scrollbars=yes');
	tareasWindow.resizeTo(width,height);    
}
    
    
//funcion para ejecutar las tareas que se generan a partir de un XML
function cargarTareaGeneradaXSD(referenciaTarea)
{  
	cargaPaginaTareaXML(referenciaTarea);
} 
        
//funcion para ejecutar las tareas que se generan a partir de un XML
function cargarTareaGeneradaXSDNueva(referenciaTarea)
{  
	insertarPagina([
	                ["vaciar","../modulos/bloquesPermitidos/iniciaTarea.action",'idTarea='+referenciaTarea,"cargaPaginaTareaXML("+ referenciaTarea + ")"],
	                ["capa_tramita", "../modulos/tramitacion/listarTransicionesExpediente.action","PKEXP="+this.pkexp],
	                ["ayudaTramitacion", "../modulos/ayudaTramitacion/listarTransicionesExpediente.action","PKEXP="+this.pkexp],
	                ["capa_tareas_asoc", "../modulos/tareasAsociadasExpediente/listarTareasExpediente.action","PKEXP="+this.pkexp, "replaceLinksTareas('')"],
	                ["capa_doc_perm", "../modulos/bloquesPermitidos/listarBloquesPermitidos.action","PKEXP="+this.pkexp,"document.getElementById('capa_auxiliar').style.display='none'"]],true);
	recargarModulosObservadores('capa_tareas_asoc');
}
     

     
function cargaPaginaTareaXML (referenciaTarea)
{
	direccion='../modulos/generacionTareas/abrirFormularioGenerado.action?&salida=si&referenciaTarea='+referenciaTarea;
	var width = screen.width*70/100;
	var height = screen.height*70/100;
	var leftVal = (screen.width - width) / 2;
	var topVal = (screen.height - height) / 2;
	tareasWindow = window.open(direccion,'tarea','left='+leftVal+',top='+topVal+',toolbar=no,status=no,resizable=yes,scrollbars=yes');
	tareasWindow.resizeTo(width,height);    
}

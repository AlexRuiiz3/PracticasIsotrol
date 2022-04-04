/**
 * 
 */
 
$("#inputBtnShowAlert").on("click", function(){
		var texto = $("#inputTxtEjemplo").val();
		alert(texto);
	});
	
$("#inputBtnOcultar").on("click", function(){	
		var inputTxt = $("#inputTxtEjemplo");
		var estaVisible = inputTxt.is(":visible");
		
		if(!estaVisible){
			inputTxt.show();
		}else{
			inputTxt.hide();
		}
});	
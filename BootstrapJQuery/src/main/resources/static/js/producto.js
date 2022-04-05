 $(document).ready(function(){
	$("#formularioPrueba").validate({
		
		rules:{
			inputAlgo:{
							required: true,
							minlength: 5,
							maxlength: 15
			},
			inputNumero:{
							required: true,
							number: true
			},
			inputRangoNumero:{
						range: [1,3]
			}
		},
		messages:{
			inputAlgo:{
							required: "Tienes que escribir algo",
							minlength: "Longitud minima de 5",
							maxlength: "Longitud Maxima de 15"
			},
			inputNumero:{
							required: "Tienes que ingresar un numero",
							number: "El valor tiene que ser un numero"
			},
			inputRangoNumero:{
						range: "El valor tiene que estar entre 1 y 3"
			}
		}
	});
});
 
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
(function(factory) {
  if (typeof define === "function" && define.amd) {
    define(["jquery", "../jquery.validate"], factory);
  } else {
    factory(jQuery);
  }
}(function($) {

  /*
   * Translated default messages for the jQuery validation plugin.
   * Locale: ES (Spanish; Español)
   */
  $.extend($.validator.messages, {
    required: "Este campo es obligatorio",
    remote: "Rellene este campo",
    email: "Escriba una dirección de correo válida",
    url: "Escriba una URL válida",
    date: "Escriba una fecha válida",
    dateISO: "Escriba una fecha (ISO) válida",
    dateEU: "Escriba una fecha válida (dd/mm/aaaa)",
    number: "Escriba un número válido",
    digits: "Escriba sólo dígitos",
    creditcard: "Escriba un número de tarjeta válido",
    equalTo: "Escriba el mismo valor de nuevo",
    extension: "Escriba un valor con una extensión aceptada",
    maxlength: $.validator.format("No escriba más de {0} caracteres"),
    minlength: $.validator.format("No escriba menos de {0} caracteres"),
    rangelength: $.validator.format("Escriba un valor entre {0} y {1} caracteres"),
    range: $.validator.format("Escriba un valor entre {0} y {1}"),
    max: $.validator.format("Escriba un valor menor o igual a {0}"),
    min: $.validator.format("Escriba un valor mayor o igual a {0}"),
    nif: "Escriba un NIF válido",
    cifES: "Escriba un CIF válido",
    telMovFax: "Formato incorrecto. Necesarios 9 dígitos",
    beforeToday: "La fecha debe ser menor o igual a hoy",
    afterToday: "La fecha debe ser mayor o igual a hoy",
    rangoFechas: "La fecha introducida debe estar comprendida entre la fecha de inicio y fin",
    fechaInicioFinMenor: "La fecha de fin debe de ser mayor que la fecha de inicio"
  });

}));
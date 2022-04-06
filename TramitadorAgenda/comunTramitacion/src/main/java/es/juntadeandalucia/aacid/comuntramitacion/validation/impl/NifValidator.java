package es.juntadeandalucia.aacid.comuntramitacion.validation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import es.juntadeandalucia.aacid.comuntramitacion.validation.annotation.Nif;

/**
 * Implementación de la constraint de validación del NIF
 * 
 * @author Isotrol
 */
public class NifValidator implements ConstraintValidator<Nif, String> {

  private Pattern mask = Pattern.compile("[0-9]{8,8}[A-Z|a-z]");
 

  @Override
  public void initialize(Nif constraintAnnotation) {
    // Método de inicialización
  }

  @Override
  public boolean isValid(String nif, ConstraintValidatorContext context) {
    if (StringUtils.isNotBlank(nif)) {
      final Matcher matcher = mask.matcher(nif);

      if (!matcher.matches()) {
        return false;
      }

      final String dni = nif.substring(0, 8);
      final String control = nif.substring(8, 9);
      final String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
      final int position = Integer.parseInt(dni) % 23;

      final String controlCalculated = letters.substring(position, position + 1);

      return control.equalsIgnoreCase(controlCalculated);
    }
    return true;
  }
}
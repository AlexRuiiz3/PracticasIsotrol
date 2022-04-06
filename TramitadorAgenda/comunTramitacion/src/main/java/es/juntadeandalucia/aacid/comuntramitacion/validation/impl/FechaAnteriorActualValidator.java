package es.juntadeandalucia.aacid.comuntramitacion.validation.impl;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import es.juntadeandalucia.aacid.comuntramitacion.validation.annotation.FechaAnteriorActual;

/**
 * Implementación de la constraint de validación de la fecha anterior
 *
 * @author Isotrol
 */
public class FechaAnteriorActualValidator implements ConstraintValidator<FechaAnteriorActual, Date> {

  private String message;

  @Override
  public void initialize(final FechaAnteriorActual constraintAnnotation) {
    message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(final Date fecha, final ConstraintValidatorContext context) {
    boolean isValid = true;

    final Date fechaActual = new Date();
    if (fecha != null && fecha.compareTo(fechaActual) > 0) {
      context.buildConstraintViolationWithTemplate(message);

      isValid = false;
    }

    return isValid;
  }

}
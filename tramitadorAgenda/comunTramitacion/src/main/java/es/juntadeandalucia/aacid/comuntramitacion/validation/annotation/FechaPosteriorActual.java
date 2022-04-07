package es.juntadeandalucia.aacid.comuntramitacion.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import es.juntadeandalucia.aacid.comuntramitacion.validation.impl.FechaPosteriorActualValidator;

/**
 * Constraint de validación de fecha posterior
 *
 * @author Isotrol
 */
@Constraint(validatedBy = { FechaPosteriorActualValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FechaPosteriorActual {

  /**
   * Devuelve mensaje error de la razon de interes
   *
   * @return
   */
  String message() default "La fecha introducida debe ser igual o posterior a hoy";

  /**
   * Grupos por defecto vacio
   *
   * @return
   */
  Class<?>[] groups() default {};

  /**
   * Payload defecto
   *
   * @return
   */
  Class<? extends Payload>[] payload() default {};

}
package es.juntadeandalucia.aacid.comuntramitacion.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import es.juntadeandalucia.aacid.comuntramitacion.validation.impl.NifValidator;

/**
 * Constraint de validación del NIF
 * 
 * @author Isotrol
 */
@Constraint(validatedBy = { NifValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Nif {

  /**
   * Devuelve mensaje error del nif
   * 
   * @return
   */
  String message() default "El valor introducido no es un NIF v\u00E1lido";

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
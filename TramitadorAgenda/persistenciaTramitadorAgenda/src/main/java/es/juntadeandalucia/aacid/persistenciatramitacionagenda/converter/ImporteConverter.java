package es.juntadeandalucia.aacid.persistenciatramitacionagenda.converter;

import java.math.BigDecimal;

import javax.persistence.AttributeConverter;

import es.juntadeandalucia.aacid.comuntramitacion.objects.Importe;

public class ImporteConverter implements AttributeConverter<Importe, BigDecimal> {

  @Override
  public BigDecimal convertToDatabaseColumn(Importe attribute) {
    return attribute != null ? attribute.getBigDecimal() : null;
  }

  @Override
  public Importe convertToEntityAttribute(BigDecimal dbData) {
    return new Importe(dbData);
  }

}

package es.juntadeandalucia.aacid.comuntramitacion.objects;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public class Importe extends Number implements Comparable<Importe> {

  private static final long serialVersionUID = -2066916710197293344L;

  private BigDecimal numero;

  public Importe(String importe) {
    if (StringUtils.isNotBlank(importe)) {
      numero = new BigDecimal(importe.replace(".", StringUtils.EMPTY).replace(",", "."));
    } else {
      numero = new BigDecimal(importe);
    }
  }

  public Importe(BigDecimal importe) {
    numero = importe;
  }

  public BigDecimal getBigDecimal() {
    return numero;
  }

  public String toString() {
    if (numero != null) {
      DecimalFormatSymbols symbols = new DecimalFormatSymbols();
      symbols.setDecimalSeparator(',');
      symbols.setGroupingSeparator('.');
      String pattern = "#,##0.00";
      DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
      return decimalFormat.format(numero.doubleValue());
    } else {
      return StringUtils.EMPTY;
    }
  }

  @Override
  public int compareTo(Importe o) {

    if (numero == null) {
      if (o == null) {
        return 0;
      } else {
        if (o.numero == null) {
          return 0;
        } else {
          return -1;
        }
      }
    } else {
      if (o == null) {
        return 1;
      } else {
        if (o.numero == null) {
          return 1;
        }
      }
    }

    return numero.compareTo(o.numero);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numero);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null)
      return false;
    if (getClass() != o.getClass())
      return false;
    Importe other = (Importe) o;
    return Objects.equals(numero, other.numero);
  }

  @Override
  public int intValue() {
    return numero.intValue();
  }

  @Override
  public long longValue() {
    return numero.longValue();
  }

  @Override
  public float floatValue() {
    return numero.floatValue();
  }

  @Override
  public double doubleValue() {
    return numero.doubleValue();
  }

}

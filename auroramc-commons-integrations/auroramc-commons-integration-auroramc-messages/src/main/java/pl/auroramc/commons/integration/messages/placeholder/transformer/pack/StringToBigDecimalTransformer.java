package pl.auroramc.commons.integration.messages.placeholder.transformer.pack;

import static pl.auroramc.commons.format.decimal.DecimalFormatter.getFormattedDecimal;

import java.math.BigDecimal;
import pl.auroramc.messages.placeholder.transformer.pack.ObjectTransformer;

class StringToBigDecimalTransformer implements ObjectTransformer<BigDecimal, String> {

  StringToBigDecimalTransformer() {}

  @Override
  public String transform(final BigDecimal value) {
    return getFormattedDecimal(value);
  }

  @Override
  public Class<?> type() {
    return BigDecimal.class;
  }
}

package pl.auroramc.commons.message.placeholder.transformer.pack;

import static pl.auroramc.commons.format.temporal.TemporalFormatter.getFormattedTemporalShortly;

import java.time.LocalDate;
import pl.auroramc.messages.placeholder.transformer.pack.ObjectTransformer;

class StringToLocalDateTransformer implements ObjectTransformer<LocalDate, String> {

  StringToLocalDateTransformer() {}

  @Override
  public String transform(final LocalDate value) {
    return getFormattedTemporalShortly(value);
  }

  @Override
  public Class<?> getType() {
    return LocalDate.class;
  }
}

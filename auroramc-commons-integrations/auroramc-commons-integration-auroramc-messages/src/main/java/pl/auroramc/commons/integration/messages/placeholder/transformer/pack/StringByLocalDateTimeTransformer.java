package pl.auroramc.commons.integration.messages.placeholder.transformer.pack;

import static pl.auroramc.commons.format.temporal.TemporalFormatter.getFormattedTemporal;

import java.time.LocalDateTime;
import pl.auroramc.messages.placeholder.transformer.pack.ObjectTransformer;

class StringByLocalDateTimeTransformer implements ObjectTransformer<LocalDateTime, String> {

  StringByLocalDateTimeTransformer() {}

  @Override
  public String transform(final LocalDateTime value) {
    return getFormattedTemporal(value);
  }

  @Override
  public Class<?> type() {
    return LocalDateTime.class;
  }
}

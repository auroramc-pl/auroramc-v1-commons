package pl.auroramc.commons.message.placeholder.transformer.pack;

import static pl.auroramc.commons.format.temporal.TemporalFormatter.getFormattedTemporal;

import java.time.LocalDateTime;
import pl.auroramc.messages.placeholder.transformer.pack.ObjectTransformer;

class StringToLocalDateTimeTransformer implements ObjectTransformer<LocalDateTime, String> {

  StringToLocalDateTimeTransformer() {}

  @Override
  public String transform(final LocalDateTime value) {
    return getFormattedTemporal(value);
  }

  @Override
  public Class<?> getType() {
    return LocalDateTime.class;
  }
}
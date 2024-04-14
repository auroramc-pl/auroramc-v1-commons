package pl.auroramc.commons.integration.messages.placeholder.transformer.pack;

import static pl.auroramc.commons.format.temporal.TemporalFormatter.getFormattedTemporal;

import java.time.Instant;
import pl.auroramc.messages.placeholder.transformer.pack.ObjectTransformer;

class StringByInstantTransformer implements ObjectTransformer<Instant, String> {

  StringByInstantTransformer() {}

  @Override
  public String transform(final Instant value) {
    return getFormattedTemporal(value);
  }

  @Override
  public Class<?> type() {
    return Instant.class;
  }
}

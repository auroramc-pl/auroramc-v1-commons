package pl.auroramc.commons.message.placeholder.transformer.pack;

import static pl.auroramc.commons.format.temporal.TemporalFormatter.getFormattedTemporal;

import java.time.Instant;
import pl.auroramc.messages.placeholder.transformer.pack.ObjectTransformer;

class StringToInstantTransformer implements ObjectTransformer<Instant, String> {

  StringToInstantTransformer() {}

  @Override
  public String transform(final Instant value) {
    return getFormattedTemporal(value);
  }

  @Override
  public Class<?> getType() {
    return Instant.class;
  }
}

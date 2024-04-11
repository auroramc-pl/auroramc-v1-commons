package pl.auroramc.commons.integration.messages.placeholder.transformer.pack;

import java.time.Duration;
import pl.auroramc.commons.format.duration.DurationFormatter;
import pl.auroramc.messages.placeholder.transformer.pack.ObjectTransformer;

class StringToDurationTransformer implements ObjectTransformer<Duration, String> {

  private final DurationFormatter durationFormatter;

  StringToDurationTransformer(final DurationFormatter durationFormatter) {
    this.durationFormatter = durationFormatter;
  }

  @Override
  public String transform(final Duration value) {
    return durationFormatter.getFormattedDuration(value);
  }

  @Override
  public Class<?> type() {
    return Duration.class;
  }
}

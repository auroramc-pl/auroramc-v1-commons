package pl.auroramc.commons.format.temporal;

import static java.time.ZoneOffset.UTC;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

public final class TemporalUtils {

  private static final LocalTime END_OF_DAY = LocalTime.of(23, 59, 59, 999_999_999);

  private TemporalUtils() {}

  public static Instant getMaximumTimeOfDay(final Instant instant) {
    return LocalDate.ofInstant(instant, UTC)
        .atTime(END_OF_DAY)
        .toInstant(UTC);
  }

  public static Instant getMinimumTimeOfDay(final Instant instant) {
    return LocalDate.ofInstant(instant, UTC)
        .atStartOfDay()
        .toInstant(UTC.getRules().getOffset(instant));
  }
}

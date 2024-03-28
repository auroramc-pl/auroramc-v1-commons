package pl.auroramc.commons.format.temporal;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

public final class TemporalUtils {

  private static final ZoneId SYSTEM_ZONE_ID = ZoneId.systemDefault();
  private static final LocalTime END_OF_DAY = LocalTime.of(23, 59, 59, 999_999_999);

  private TemporalUtils() {}

  public static Instant getMaximumTimeOfDay(final Instant period) {
    return LocalDate.ofInstant(period, SYSTEM_ZONE_ID)
        .atTime(END_OF_DAY)
        .toInstant(SYSTEM_ZONE_ID.getRules().getOffset(period));
  }

  public static Instant getMinimumTimeOfDay(final Instant period) {
    return LocalDate.ofInstant(period, SYSTEM_ZONE_ID)
        .atStartOfDay()
        .toInstant(SYSTEM_ZONE_ID.getRules().getOffset(period));
  }
}

package pl.auroramc.commons.format.temporal;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class TemporalFormatter {

  private static final ZoneId SYSTEM_ZONE_ID = ZoneId.systemDefault();
  private static final DateTimeFormatter LONG_DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
  private static final DateTimeFormatter SHORT_DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("dd/MM/yyyy");

  private TemporalFormatter() {}

  public static String getFormattedTemporal(final java.time.temporal.TemporalAccessor period) {
    return LONG_DATE_TIME_FORMATTER.format(period);
  }

  public static String getFormattedTemporal(final Instant period) {
    return getFormattedTemporal(ZonedDateTime.ofInstant(period, SYSTEM_ZONE_ID));
  }

  public static String getFormattedTemporalShortly(final java.time.temporal.TemporalAccessor period) {
    return SHORT_DATE_TIME_FORMATTER.format(period);
  }

  public static String getFormattedTemporalShortly(final Instant period) {
    return getFormattedTemporalShortly(ZonedDateTime.ofInstant(period, SYSTEM_ZONE_ID));
  }
}

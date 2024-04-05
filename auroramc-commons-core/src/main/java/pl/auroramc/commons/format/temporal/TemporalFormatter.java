package pl.auroramc.commons.format.temporal;

import static java.time.ZoneOffset.UTC;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public final class TemporalFormatter {

  private static final DateTimeFormatter LONG_DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(UTC);
  private static final DateTimeFormatter SHORT_DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone(UTC);

  private TemporalFormatter() {}

  public static String getFormattedTemporal(final TemporalAccessor temporal) {
    return LONG_DATE_TIME_FORMATTER.format(temporal);
  }

  public static String getFormattedTemporalShortly(final TemporalAccessor temporal) {
    return SHORT_DATE_TIME_FORMATTER.format(temporal);
  }

  public static String getFormattedTemporal(final Instant instant) {
    return getFormattedTemporal(ZonedDateTime.ofInstant(instant, UTC));
  }

  public static String getFormattedTemporalShortly(final Instant instant) {
    return getFormattedTemporalShortly(ZonedDateTime.ofInstant(instant, UTC));
  }

  public static Instant parseInstant(final String input) {
    return parseInstant(input, LONG_DATE_TIME_FORMATTER);
  }

  public static Instant parseInstantShortly(final String input) {
    return parseInstant(input, SHORT_DATE_TIME_FORMATTER);
  }
  private static Instant parseInstant(final String input, final DateTimeFormatter formatter) {
    return LocalDateTime.parse(input, formatter).atZone(UTC).toInstant();
  }
}

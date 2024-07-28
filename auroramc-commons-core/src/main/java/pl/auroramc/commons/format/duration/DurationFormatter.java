package pl.auroramc.commons.format.duration;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MILLIS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.SECONDS;
import static java.time.temporal.ChronoUnit.WEEKS;
import static java.time.temporal.ChronoUnit.YEARS;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import pl.auroramc.commons.i18n.plural.Pluralizer;
import pl.auroramc.commons.i18n.plural.variety.VarietiesByCases;

public final class DurationFormatter {

  private static final ChronoUnit[] UNITS =
      new ChronoUnit[] {YEARS, MONTHS, WEEKS, DAYS, HOURS, MINUTES, SECONDS, MILLIS};
  private static final long[] UNIT_DURATIONS =
      new long[] {
        1_000L * 60 * 60 * 24 * 365,
        1_000L * 60 * 60 * 24 * 30,
        1_000L * 60 * 60 * 24 * 7,
        1_000L * 60 * 60 * 24,
        1_000L * 60 * 60,
        1_000L * 60,
        1_000L
      };
  private static final long SMALLEST_UNIT_DURATION = UNIT_DURATIONS[UNIT_DURATIONS.length - 1];

  private DurationFormatter() {}

  public static String getFormattedDuration(
      final DurationFormatterVocabulary settings,
      final Pluralizer pluralizer,
      final Duration duration) {
    final long millis = duration.toMillis();
    if (SMALLEST_UNIT_DURATION > millis) {
      final VarietiesByCases unitForm = settings.getUnitForm(MILLIS);
      return "%d %s".formatted(millis, pluralizer.pluralize(unitForm, millis));
    }

    final StringBuilder chain = new StringBuilder();

    String lastMatch = null;
    long remainingMillis = millis;
    for (int index = 0; index < UNIT_DURATIONS.length; index++) {
      if (remainingMillis < SMALLEST_UNIT_DURATION) {
        break;
      }

      final long divider = UNIT_DURATIONS[index];
      if (divider > remainingMillis) {
        continue;
      }

      final long matches = remainingMillis / divider;
      if (matches > 0) {
        final VarietiesByCases unitForm = settings.getUnitForm(UNITS[index]);
        final String currentUnit =
            "%d %s".formatted(matches, pluralizer.pluralize(unitForm, matches));

        if (lastMatch != null) {
          chain.append(lastMatch).append(settings.aggregatingPhrase());
        }
        lastMatch = currentUnit;

        remainingMillis -= matches * divider;
      }
    }

    if (lastMatch != null) {
      if (chain.isEmpty()) {
        chain.append(lastMatch);
        return chain.toString();
      }

      chain
          .delete(chain.length() - settings.aggregatingPhrase().length(), chain.length())
          .append(settings.aggregatingPhraseEnclosing())
          .append(lastMatch);
    }

    return chain.toString();
  }
}

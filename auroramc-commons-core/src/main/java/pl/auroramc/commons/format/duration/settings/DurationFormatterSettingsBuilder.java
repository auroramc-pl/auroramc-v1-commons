package pl.auroramc.commons.format.duration.settings;

import java.time.temporal.ChronoUnit;
import java.util.EnumMap;
import java.util.Map;
import pl.auroramc.commons.i18n.plural.variety.VarietiesByCases;

public class DurationFormatterSettingsBuilder {

  private final Map<ChronoUnit, VarietiesByCases> unitForms;
  private String aggregatingPhrase = ", ";
  private String aggregatingPhraseEnclosing = aggregatingPhrase;

  DurationFormatterSettingsBuilder(final Map<ChronoUnit, VarietiesByCases> unitForms) {
    this.unitForms = unitForms;
  }

  DurationFormatterSettingsBuilder() {
    this(new EnumMap<>(ChronoUnit.class));
  }

  public DurationFormatterSettingsBuilder withAggregatingPhrase(final String aggregatingPhrase) {
    this.aggregatingPhrase = aggregatingPhrase;
    return this;
  }

  public DurationFormatterSettingsBuilder withAggregatingPhraseEnclosing(
      final String aggregatingPhraseEnclosing) {
    this.aggregatingPhraseEnclosing = aggregatingPhraseEnclosing;
    return this;
  }

  public DurationFormatterSettingsBuilder withUnitForm(
      final ChronoUnit unit, final VarietiesByCases unitForm) {
    unitForms.put(unit, unitForm);
    return this;
  }

  public DurationFormatterSettings build() {
    return new DurationFormatterSettings(aggregatingPhrase, aggregatingPhraseEnclosing, unitForms);
  }
}

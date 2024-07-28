package pl.auroramc.commons.format.duration;

import java.time.temporal.ChronoUnit;
import java.util.EnumMap;
import java.util.Map;
import pl.auroramc.commons.i18n.plural.variety.VarietiesByCases;

public class DurationFormatterVocabularyBuilder {

  private final Map<ChronoUnit, VarietiesByCases> unitForms;
  private String aggregatingPhrase = ", ";
  private String aggregatingPhraseEnclosing = aggregatingPhrase;

  DurationFormatterVocabularyBuilder(final Map<ChronoUnit, VarietiesByCases> unitForms) {
    this.unitForms = unitForms;
  }

  public static DurationFormatterVocabularyBuilder newBuilder() {
    return new DurationFormatterVocabularyBuilder(new EnumMap<>(ChronoUnit.class));
  }

  public DurationFormatterVocabularyBuilder withAggregatingPhrase(final String aggregatingPhrase) {
    this.aggregatingPhrase = aggregatingPhrase;
    return this;
  }

  public DurationFormatterVocabularyBuilder withAggregatingPhraseEnclosing(
      final String aggregatingPhraseEnclosing) {
    this.aggregatingPhraseEnclosing = aggregatingPhraseEnclosing;
    return this;
  }

  public DurationFormatterVocabularyBuilder withUnitForm(
      final ChronoUnit unit, final VarietiesByCases unitForm) {
    unitForms.put(unit, unitForm);
    return this;
  }

  public DurationFormatterVocabularyBuilder withUnitForms(
      final Map<ChronoUnit, VarietiesByCases> unitForms) {
    this.unitForms.putAll(unitForms);
    return this;
  }

  public DurationFormatterVocabulary build() {
    return new DurationFormatterVocabulary(aggregatingPhrase, aggregatingPhraseEnclosing, unitForms);
  }
}

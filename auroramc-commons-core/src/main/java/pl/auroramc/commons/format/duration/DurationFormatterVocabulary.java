package pl.auroramc.commons.format.duration;

import java.time.temporal.ChronoUnit;
import java.util.Map;
import pl.auroramc.commons.i18n.plural.variety.VarietiesByCases;

public record DurationFormatterVocabulary(
    String aggregatingPhrase,
    String aggregatingPhraseEnclosing,
    Map<ChronoUnit, VarietiesByCases> unitForms) {

  public VarietiesByCases getUnitForm(final ChronoUnit unit) {
    return unitForms.get(unit);
  }

  public DurationFormatterVocabularyBuilder toBuilder() {
    return new DurationFormatterVocabularyBuilder(unitForms)
        .withAggregatingPhrase(aggregatingPhrase)
        .withAggregatingPhraseEnclosing(aggregatingPhraseEnclosing);
  }
}

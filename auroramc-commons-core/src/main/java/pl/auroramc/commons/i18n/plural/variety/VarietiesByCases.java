package pl.auroramc.commons.i18n.plural.variety;

import java.util.Map;
import pl.auroramc.commons.i18n.plural.PluralizationCase;

public record VarietiesByCases(Map<PluralizationCase, String> pluralForms) {

  public String getPluralForm(final PluralizationCase pluralizationCase) {
    return pluralForms.get(pluralizationCase);
  }
}

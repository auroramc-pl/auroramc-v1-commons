package pl.auroramc.commons.i18n.plural.variety;

import java.util.Map;
import pl.auroramc.commons.i18n.plural.PluralizationCase;

public class VarietiesByCases {

  private final Map<PluralizationCase, String> pluralForms;

  VarietiesByCases(final Map<PluralizationCase, String> pluralForms) {
    this.pluralForms = pluralForms;
  }

  public static VarietiesByCasesBuilder newBuilder() {
    return new VarietiesByCasesBuilder();
  }

  public String getPluralForm(final PluralizationCase pluralizationCase) {
    return pluralForms.get(pluralizationCase);
  }
}

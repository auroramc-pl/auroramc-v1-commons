package pl.auroramc.commons.i18n.plural.variety;

import java.util.EnumMap;
import java.util.Map;
import pl.auroramc.commons.i18n.plural.PluralizationCase;

public class VarietiesByCasesBuilder {

  private final Map<PluralizationCase, String> pluralForms;

  VarietiesByCasesBuilder() {
    this.pluralForms = new EnumMap<>(PluralizationCase.class);
  }

  public VarietiesByCasesBuilder withPluralForm(
      final PluralizationCase pluralizationCase, final String pluralForm) {
    pluralForms.put(pluralizationCase, pluralForm);
    return this;
  }

  public VarietiesByCasesBuilder withPluralForm(final String pluralForm) {
    withPluralForm(PluralizationCase.SINGULAR, pluralForm);
    withPluralForm(PluralizationCase.PLURAL_GENITIVE, pluralForm);
    withPluralForm(PluralizationCase.PLURAL_NOMINATIVE, pluralForm);
    return this;
  }

  public VarietiesByCases build() {
    return new VarietiesByCases(pluralForms);
  }
}

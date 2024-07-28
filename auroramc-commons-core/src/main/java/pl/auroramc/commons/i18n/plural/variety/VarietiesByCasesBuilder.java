package pl.auroramc.commons.i18n.plural.variety;

import static pl.auroramc.commons.i18n.plural.PluralizationCase.PLURAL_GENITIVE;
import static pl.auroramc.commons.i18n.plural.PluralizationCase.PLURAL_NOMINATIVE;
import static pl.auroramc.commons.i18n.plural.PluralizationCase.SINGULAR;

import java.util.EnumMap;
import java.util.Map;
import pl.auroramc.commons.i18n.plural.PluralizationCase;
import pl.auroramc.commons.tuplet.Pair;
import pl.auroramc.commons.tuplet.Triple;

public class VarietiesByCasesBuilder {

  private final Map<PluralizationCase, String> pluralForms;

  private VarietiesByCasesBuilder() {
    this.pluralForms = new EnumMap<>(PluralizationCase.class);
  }

  public static VarietiesByCasesBuilder newBuilder() {
    return new VarietiesByCasesBuilder();
  }

  public VarietiesByCasesBuilder withPluralForm(
      final PluralizationCase pluralizationCase, final String pluralForm) {
    pluralForms.put(pluralizationCase, pluralForm);
    return this;
  }

  public VarietiesByCasesBuilder withPluralForm(final Pair<String, String> pluralForm) {
    return withPluralForm(SINGULAR, pluralForm.a())
        .withPluralForm(PLURAL_GENITIVE, pluralForm.b())
        .withPluralForm(PLURAL_NOMINATIVE, pluralForm.b());
  }

  public VarietiesByCasesBuilder withPluralForm(final Triple<String, String, String> pluralForms) {
    return withPluralForm(SINGULAR, pluralForms.a())
        .withPluralForm(PLURAL_GENITIVE, pluralForms.c())
        .withPluralForm(PLURAL_NOMINATIVE, pluralForms.b());
  }

  public VarietiesByCasesBuilder withPluralForm(final String pluralForm) {
    return withPluralForm(SINGULAR, pluralForm)
        .withPluralForm(PLURAL_GENITIVE, pluralForm)
        .withPluralForm(PLURAL_NOMINATIVE, pluralForm);
  }

  public VarietiesByCases build() {
    return new VarietiesByCases(pluralForms);
  }
}

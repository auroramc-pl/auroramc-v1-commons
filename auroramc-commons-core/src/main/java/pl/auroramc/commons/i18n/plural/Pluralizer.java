package pl.auroramc.commons.i18n.plural;

import pl.auroramc.commons.i18n.plural.variety.VarietiesByCases;

@FunctionalInterface
public interface Pluralizer {

  String pluralize(final VarietiesByCases context, final long count);
}

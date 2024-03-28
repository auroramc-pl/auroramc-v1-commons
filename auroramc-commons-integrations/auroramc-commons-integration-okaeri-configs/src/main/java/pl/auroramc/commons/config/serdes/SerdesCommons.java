package pl.auroramc.commons.config.serdes;

import eu.okaeri.configs.serdes.SerdesRegistry;
import org.jetbrains.annotations.NotNull;

public class SerdesCommons extends eu.okaeri.configs.serdes.commons.SerdesCommons {

  @Override
  public void register(final @NotNull SerdesRegistry registry) {
    super.register(registry);
    registry.register(new StringToDecimalFormatTransformer());
    registry.register(new StringToLocaleTransformer());
    registry.register(new StringToZoneIdTransformer());
  }
}

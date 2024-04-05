package pl.auroramc.commons.integration.litecommands.cooldown;

import dev.rollczi.litecommands.meta.MetaKey;

final class CooldownMeta {

  static final MetaKey<CooldownContext> CONTEXT_KEY =
      MetaKey.of("cooldown-context", CooldownContext.class);

  private CooldownMeta() {}
}

package pl.auroramc.messages.message.compiler;

import org.bukkit.entity.Player;
import pl.auroramc.messages.placeholder.resolver.PlaceholderResolver;

class BukkitMessageCompilerImpl extends MessageCompilerImpl<Player>
    implements BukkitMessageCompiler {

  BukkitMessageCompilerImpl(final PlaceholderResolver<Player> placeholderResolver) {
    super(placeholderResolver);
  }
}

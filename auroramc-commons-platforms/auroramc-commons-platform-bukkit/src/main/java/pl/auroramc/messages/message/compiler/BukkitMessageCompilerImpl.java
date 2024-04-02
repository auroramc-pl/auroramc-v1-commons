package pl.auroramc.messages.message.compiler;

import org.bukkit.command.CommandSender;
import pl.auroramc.messages.placeholder.resolver.PlaceholderResolver;

class BukkitMessageCompilerImpl extends MessageCompilerImpl<CommandSender>
    implements BukkitMessageCompiler {

  BukkitMessageCompilerImpl(final PlaceholderResolver<CommandSender> placeholderResolver) {
    super(placeholderResolver);
  }
}

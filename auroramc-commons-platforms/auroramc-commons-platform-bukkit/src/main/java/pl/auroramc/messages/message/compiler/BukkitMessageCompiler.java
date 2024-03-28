package pl.auroramc.messages.message.compiler;

import static pl.auroramc.messages.placeholder.evaluator.PlaceholderEvaluator.getReflectivePlaceholderEvaluator;
import static pl.auroramc.messages.placeholder.resolver.BukkitPlaceholderResolver.getBukkitPlaceholderResolver;
import static pl.auroramc.messages.placeholder.scanner.PlaceholderScanner.getPlaceholderScanner;
import static pl.auroramc.messages.placeholder.transformer.registry.ObjectTransformerRegistry.getObjectTransformerRegistry;

import org.bukkit.entity.Player;
import pl.auroramc.messages.placeholder.resolver.PlaceholderResolver;
import pl.auroramc.messages.placeholder.transformer.pack.ObjectTransformerPack;

public class BukkitMessageCompiler extends MessageCompilerImpl<Player> {

  BukkitMessageCompiler(final PlaceholderResolver<Player> placeholderResolver) {
    super(placeholderResolver);
  }

  public static MessageCompiler<Player> getBukkitMessageCompiler(
      final ObjectTransformerPack... transformerPacks) {
    return MessageCompiler.getMessageCompiler(
        getBukkitPlaceholderResolver(
            getObjectTransformerRegistry(transformerPacks),
            getPlaceholderScanner(),
            getReflectivePlaceholderEvaluator()));
  }
}

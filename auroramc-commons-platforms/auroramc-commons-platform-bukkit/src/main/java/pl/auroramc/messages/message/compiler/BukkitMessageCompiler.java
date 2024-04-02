package pl.auroramc.messages.message.compiler;

import static pl.auroramc.messages.placeholder.evaluator.PlaceholderEvaluator.getReflectivePlaceholderEvaluator;
import static pl.auroramc.messages.placeholder.resolver.BukkitPlaceholderResolver.getBukkitPlaceholderResolver;
import static pl.auroramc.messages.placeholder.scanner.PlaceholderScanner.getPlaceholderScanner;
import static pl.auroramc.messages.placeholder.transformer.registry.ObjectTransformerRegistry.getObjectTransformerRegistry;

import org.bukkit.command.CommandSender;
import pl.auroramc.commons.bukkit.message.placeholder.transformer.pack.BukkitObjectTransformerPack;
import pl.auroramc.commons.message.placeholder.transformer.pack.CommonsObjectTransformerPack;
import pl.auroramc.messages.placeholder.resolver.PlaceholderResolver;
import pl.auroramc.messages.placeholder.transformer.pack.ObjectTransformerPack;
import pl.auroramc.messages.placeholder.transformer.pack.standard.StandardObjectTransformerPack;

public interface BukkitMessageCompiler extends MessageCompiler<CommandSender> {

  static BukkitMessageCompiler getBukkitMessageCompiler(
      final ObjectTransformerPack... transformerPacks) {
    final BukkitMessageCompiler messageCompiler =
        getBukkitMessageCompiler(
            getBukkitPlaceholderResolver(
                getObjectTransformerRegistry(transformerPacks),
                getPlaceholderScanner(),
                getReflectivePlaceholderEvaluator()));
    messageCompiler.register(new CommonsObjectTransformerPack());
    messageCompiler.register(new StandardObjectTransformerPack());
    messageCompiler.register(new BukkitObjectTransformerPack(messageCompiler));
    return messageCompiler;
  }

  static BukkitMessageCompiler getBukkitMessageCompiler(
      final PlaceholderResolver<CommandSender> placeholderResolver) {
    return new BukkitMessageCompilerImpl(placeholderResolver);
  }
}

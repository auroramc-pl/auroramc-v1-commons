package pl.auroramc.messages.placeholder.resolver;

import static me.clip.placeholderapi.PlaceholderAPI.setPlaceholders;

import org.bukkit.entity.Player;
import pl.auroramc.messages.placeholder.context.PlaceholderContext;
import pl.auroramc.messages.placeholder.evaluator.PlaceholderEvaluator;
import pl.auroramc.messages.placeholder.scanner.PlaceholderScanner;
import pl.auroramc.messages.placeholder.transformer.registry.ObjectTransformerRegistry;

public class BukkitPlaceholderResolver extends PlaceholderResolverImpl<Player> {

  private BukkitPlaceholderResolver(
      final ObjectTransformerRegistry transformerRegistry,
      final PlaceholderScanner placeholderScanner,
      final PlaceholderEvaluator placeholderEvaluator) {
    super(transformerRegistry, placeholderScanner, placeholderEvaluator);
  }

  public static BukkitPlaceholderResolver getBukkitPlaceholderResolver(
      final ObjectTransformerRegistry transformerRegistry,
      final PlaceholderScanner placeholderScanner,
      final PlaceholderEvaluator placeholderEvaluator) {
    return new BukkitPlaceholderResolver(
        transformerRegistry, placeholderScanner, placeholderEvaluator);
  }

  @Override
  public String apply(
      final Player viewer, final String template, final PlaceholderContext context) {
    if (viewer == null) {
      return super.apply(null, template, context);
    }

    return setPlaceholders(viewer, super.apply(viewer, template, context));
  }
}

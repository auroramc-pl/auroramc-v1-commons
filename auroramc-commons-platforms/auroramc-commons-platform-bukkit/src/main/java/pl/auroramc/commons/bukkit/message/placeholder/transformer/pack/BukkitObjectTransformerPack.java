package pl.auroramc.commons.bukkit.message.placeholder.transformer.pack;

import pl.auroramc.commons.bukkit.format.item.ItemStackFormatter;
import pl.auroramc.messages.message.compiler.MessageCompiler;
import pl.auroramc.messages.placeholder.transformer.pack.ObjectTransformerPack;
import pl.auroramc.messages.placeholder.transformer.registry.ObjectTransformerRegistry;

public class BukkitObjectTransformerPack implements ObjectTransformerPack {

  private final ItemStackFormatter itemStackFormatter;

  public BukkitObjectTransformerPack(final ItemStackFormatter itemStackFormatter) {
    this.itemStackFormatter = itemStackFormatter;
  }

  public BukkitObjectTransformerPack(final MessageCompiler<?> messageCompiler) {
    this(new ItemStackFormatter(messageCompiler));
  }

  @Override
  public void register(final ObjectTransformerRegistry transformerRegistry) {
    transformerRegistry.register(new ComponentToItemStackTransformer(itemStackFormatter));
  }
}

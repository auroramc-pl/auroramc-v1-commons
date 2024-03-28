package pl.auroramc.commons.bukkit.message.placeholder.transformer.pack;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import pl.auroramc.commons.bukkit.format.item.ItemStackFormatter;
import pl.auroramc.messages.placeholder.transformer.pack.ObjectTransformer;

class ComponentToItemStackTransformer implements ObjectTransformer<ItemStack, Component> {

  private final ItemStackFormatter itemStackFormatter;

  ComponentToItemStackTransformer(final ItemStackFormatter itemStackFormatter) {
    this.itemStackFormatter = itemStackFormatter;
  }

  @Override
  public Component transform(final ItemStack itemStack) {
    return itemStackFormatter.getFormattedItemStack(itemStack);
  }

  @Override
  public Class<?> getType() {
    return ItemStack.class;
  }
}

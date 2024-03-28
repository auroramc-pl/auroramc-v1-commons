package pl.auroramc.commons.bukkit.format.item;

import static net.kyori.adventure.text.Component.translatable;
import static org.bukkit.inventory.ItemStack.deserializeBytes;

import java.util.Optional;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.auroramc.messages.message.MutableMessage;
import pl.auroramc.messages.message.compiler.MessageCompiler;

public final class ItemStackFormatter {

  private final MessageCompiler<?> messageCompiler;

  public ItemStackFormatter(final MessageCompiler<?> messageCompiler) {
    this.messageCompiler = messageCompiler;
  }

  public Component getFormattedItemStack(final ItemStack itemStack) {
    return messageCompiler
        .compile(
            null,
            MutableMessage.of("<dark_gray>x{item.@amount} <white>{name}")
                .placeholder("item", itemStack)
                .placeholder("name", getDisplayName(itemStack)))
        .getComponent()
        .hoverEvent(itemStack.asHoverEvent());
  }

  public Component getFormattedItemStack(final byte[] serializedItemStack) {
    return getFormattedItemStack(deserializeBytes(serializedItemStack));
  }

  private Component getDisplayName(final ItemStack itemStack) {
    return Optional.ofNullable(itemStack.getItemMeta())
        .map(ItemMeta::displayName)
        .orElse(translatable(itemStack));
  }
}

package pl.auroramc.commons.bukkit.item;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class ItemStackUtils {

  private ItemStackUtils() {}

  public static ItemStack getItemStackWithQuantity(final ItemStack itemStack, final int quantity) {
    final ItemStack copyOfItemStack = itemStack.clone();
    copyOfItemStack.setAmount(quantity);
    return copyOfItemStack;
  }

  public static void giveOrDropItemStack(final Player player, final ItemStack itemStack) {
    player
        .getInventory()
        .addItem(itemStack)
        .forEach(
            (index, remainingItemStack) ->
                player.getWorld().dropItemNaturally(player.getLocation(), remainingItemStack));
  }

  public static void increaseQuantityOfHeldItem(final Player player) {
    final ItemStack itemStack = player.getInventory().getItemInMainHand();
    if (itemStack.getAmount() == itemStack.getMaxStackSize()) {
      giveOrDropItemStack(player, ItemStackBuilder.newBuilder(itemStack.clone()).count(1).build());
    } else {
      itemStack.setAmount(itemStack.getAmount() + 1);
    }
  }

  public static void decreaseQuantityOfHeldItem(final Player player) {
    final ItemStack itemStack = player.getInventory().getItemInMainHand();
    if (itemStack.getAmount() == 1) {
      player.getInventory().setItemInMainHand(null);
    } else {
      itemStack.setAmount(itemStack.getAmount() - 1);
    }
  }
}

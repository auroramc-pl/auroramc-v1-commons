package pl.auroramc.commons.bukkit.page.navigation;

import org.bukkit.inventory.ItemStack;

public record Navigator(NavigationDirection direction, ItemStack icon) {

  static final int INITIAL_PAGE_INDEX = 0;
  static final int INDICATOR_OFFSET = 1;
}

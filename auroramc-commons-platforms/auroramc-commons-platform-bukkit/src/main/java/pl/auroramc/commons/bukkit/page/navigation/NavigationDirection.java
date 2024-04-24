package pl.auroramc.commons.bukkit.page.navigation;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static pl.auroramc.commons.bukkit.page.navigation.Navigator.INDICATOR_OFFSET;
import static pl.auroramc.commons.bukkit.page.navigation.Navigator.INITIAL_PAGE_INDEX;

import java.util.function.BinaryOperator;

public enum NavigationDirection {
  FORWARD((pageCount, pageIndex) -> min(pageIndex + INDICATOR_OFFSET, pageCount - 1)),
  BACKWARD((pageCount, pageIndex) -> max(pageIndex - INDICATOR_OFFSET, INITIAL_PAGE_INDEX));

  private final BinaryOperator<Integer> pageIndexModifier;

  NavigationDirection(final BinaryOperator<Integer> pageIndexModifier) {
    this.pageIndexModifier = pageIndexModifier;
  }

  public int navigate(int pageCount, int pageIndex) {
    return pageIndexModifier.apply(pageCount, pageIndex);
  }
}

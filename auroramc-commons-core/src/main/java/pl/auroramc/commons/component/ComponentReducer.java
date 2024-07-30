package pl.auroramc.commons.component;

import static net.kyori.adventure.text.Component.empty;

import java.util.function.BinaryOperator;
import net.kyori.adventure.text.Component;

public class ComponentReducer implements BinaryOperator<Component> {

  @Override
  public Component apply(final Component aggregator, final Component child) {
    if (isEmpty(aggregator)) {
      return child;
    }
    return aggregator.appendNewline().append(child);
  }

  private boolean isEmpty(final Component component) {
    return component == empty();
  }
}

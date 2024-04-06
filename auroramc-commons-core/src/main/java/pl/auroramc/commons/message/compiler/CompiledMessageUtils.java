package pl.auroramc.commons.message.compiler;

import net.kyori.adventure.text.Component;
import pl.auroramc.messages.message.compiler.CompiledMessage;

public final class CompiledMessageUtils {

  private CompiledMessageUtils() {}

  public static Component resolveComponent(final CompiledMessage message) {
    return message.getComponent();
  }

  public static Component[] resolveComponent(final CompiledMessage... messages) {
    final Component[] components = new Component[messages.length];
    for (int index = 0; index < messages.length; index++) {
      components[index] = messages[index].getComponent();
    }
    return components;
  }
}

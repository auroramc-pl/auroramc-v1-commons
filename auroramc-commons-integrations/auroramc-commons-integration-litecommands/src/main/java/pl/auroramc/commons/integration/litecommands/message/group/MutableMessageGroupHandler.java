package pl.auroramc.commons.integration.litecommands.message.group;

import static pl.auroramc.messages.message.display.MessageDisplay.CHAT;

import dev.rollczi.litecommands.handler.result.ResultHandler;
import dev.rollczi.litecommands.handler.result.ResultHandlerChain;
import dev.rollczi.litecommands.invocation.Invocation;
import java.util.Map.Entry;
import java.util.Set;
import net.kyori.adventure.audience.Audience;
import pl.auroramc.messages.message.MutableMessage;
import pl.auroramc.messages.message.compiler.CompiledMessage;
import pl.auroramc.messages.message.compiler.MessageCompiler;
import pl.auroramc.messages.message.group.MutableMessageGroup;

public class MutableMessageGroupHandler<T extends Audience>
    implements ResultHandler<T, MutableMessageGroup> {

  private final MessageCompiler<T> messageCompiler;

  public MutableMessageGroupHandler(final MessageCompiler<T> messageCompiler) {
    this.messageCompiler = messageCompiler;
  }

  @Override
  public void handle(
      final Invocation<T> invocation,
      final MutableMessageGroup group,
      final ResultHandlerChain<T> chain) {
    for (final Entry<MutableMessage, Set<Audience>> messageByReceivers :
        group.messagesByReceivers().entrySet()) {
      final CompiledMessage message = messageCompiler.compile(messageByReceivers.getKey());
      for (final Audience receiver : messageByReceivers.getValue()) {
        message.render(receiver, CHAT);
      }
    }
  }
}

package pl.auroramc.commons.integration.litecommands.cooldown;

import static dev.rollczi.litecommands.flow.Flow.continueFlow;
import static dev.rollczi.litecommands.flow.Flow.terminateFlow;
import static java.time.Duration.between;
import static java.time.Instant.now;
import static pl.auroramc.commons.integration.litecommands.cooldown.CooldownMeta.CONTEXT_KEY;

import dev.rollczi.litecommands.argument.suggester.input.SuggestionInput;
import dev.rollczi.litecommands.flow.Flow;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.meta.MetaHolder;
import dev.rollczi.litecommands.validator.Validator;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pl.auroramc.messages.message.MutableMessage;

public class CooldownValidator<T> implements Validator<T> {

  private final MutableMessage commandOnCooldown;
  private final Map<CooldownCompositeKey, Instant> expirationTimeByCooldownKey;

  public CooldownValidator(final MutableMessage commandOnCooldown) {
    this.commandOnCooldown = commandOnCooldown;
    this.expirationTimeByCooldownKey = new HashMap<>();
  }

  @Override
  public Flow validate(final Invocation<T> invocation, final MetaHolder metaHolder) {
    if (invocation.arguments() instanceof SuggestionInput<?>) {
      return continueFlow();
    }

    final List<CooldownContext> aggregatedContexts =
        metaHolder.metaCollector().collect(CONTEXT_KEY);
    if (aggregatedContexts.isEmpty()) {
      return continueFlow();
    }

    return validateCooldown(invocation, aggregatedContexts.getFirst());
  }

  private Flow validateCooldown(final Invocation<T> invocation, final CooldownContext context) {
    final CooldownCompositeKey cooldownKey =
        new CooldownCompositeKey(invocation.platformSender().getName(), context.key());
    final Instant now = now();
    final Instant expirationTime = expirationTimeByCooldownKey.get(cooldownKey);
    if (expirationTime != null && expirationTime.isAfter(now)) {
      return terminateFlow(commandOnCooldown.placeholder("period", between(now, expirationTime)));
    }

    expirationTimeByCooldownKey.put(cooldownKey, now.plus(context.period()));
    return continueFlow();
  }

  private record CooldownCompositeKey(String name, String command) {}
}

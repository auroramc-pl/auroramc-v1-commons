package pl.auroramc.commons.integration.litecommands.cooldown;

import static pl.auroramc.commons.integration.litecommands.cooldown.CooldownMeta.CONTEXT_KEY;

import dev.rollczi.litecommands.annotations.AnnotationInvoker;
import dev.rollczi.litecommands.annotations.AnnotationProcessor;
import java.time.Duration;

public class CooldownAnnotationResolver<T> implements AnnotationProcessor<T> {

  @Override
  public AnnotationInvoker<T> process(final AnnotationInvoker<T> invoker) {
    return invoker.on(
        Cooldown.class,
        (annotation, metaHolder) ->
            metaHolder
                .meta()
                .put(
                    CONTEXT_KEY,
                    new CooldownContext(
                        annotation.key(), Duration.of(annotation.period(), annotation.unit()))));
  }
}

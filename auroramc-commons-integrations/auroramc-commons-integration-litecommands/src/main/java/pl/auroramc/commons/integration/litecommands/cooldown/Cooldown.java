package pl.auroramc.commons.integration.litecommands.cooldown;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.time.temporal.ChronoUnit.SECONDS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.temporal.ChronoUnit;

@Target({TYPE, METHOD})
@Retention(RUNTIME)
public @interface Cooldown {

  String key();

  long period();

  ChronoUnit unit() default SECONDS;
}

package pl.auroramc.commons.memoize;

import static java.time.Instant.now;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;

public class Memoized<T> {

  private final Duration timeToLive;
  private final Supplier<T> valueInitializer;
  private T value;
  private Instant updatedAt;
  private Instant updatesAt;

  private Memoized(final Duration timeToLive, final Supplier<T> valueInitializer) {
    this.timeToLive = timeToLive;
    this.valueInitializer = valueInitializer;
  }

  public static <T> Memoized<T> memoize(
      final Duration timeToLive, final Supplier<T> valueInitializer) {
    return new Memoized<>(timeToLive, valueInitializer);
  }

  public T get() {
    if (value == null || isExpired()) {
      update();
    }

    return value;
  }

  public Duration getTimeToLive() {
    return timeToLive;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public Instant getUpdatesAt() {
    return updatesAt;
  }

  private void update() {
    value = valueInitializer.get();
    updatedAt = now();
    updatesAt = updatedAt.plus(timeToLive);
  }

  private boolean isExpired() {
    return updatesAt.isBefore(now());
  }
}

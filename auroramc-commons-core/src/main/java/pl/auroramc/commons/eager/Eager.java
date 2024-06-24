package pl.auroramc.commons.eager;

import java.util.function.Supplier;

public class Eager<T> {

  private final T value;

  private Eager(final T value) {
    this.value = value;
  }

  public static <T> Eager<T> eager(final Supplier<T> valueInitializer) {
    return new Eager<>(valueInitializer.get());
  }

  public T get() {
    return value;
  }
}

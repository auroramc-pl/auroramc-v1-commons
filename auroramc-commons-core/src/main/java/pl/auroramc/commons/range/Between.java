package pl.auroramc.commons.range;

public record Between<T extends Comparable<T>>(T maximum, T minimum) {

  public static <T extends Comparable<T>> Between<T> single(final T value) {
    return new Between<>(value, value);
  }

  public static <T extends Comparable<T>> Between<T> ranged(final T minimum, final T maximum) {
    return new Between<>(maximum, minimum);
  }

  public boolean single() {
    return maximum.equals(minimum);
  }

  public boolean ranged() {
    return !single();
  }

  public boolean matches(final T query) {
    return query.compareTo(maximum) <= 0 && query.compareTo(minimum) >= 0;
  }
}

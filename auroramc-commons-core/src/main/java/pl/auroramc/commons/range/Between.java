package pl.auroramc.commons.range;

public record Between<T extends Comparable<T>>(T maximum, T minimum) {

  boolean matches(final T query) {
    return query.compareTo(maximum) <= 0 && query.compareTo(minimum) >= 0;
  }
}

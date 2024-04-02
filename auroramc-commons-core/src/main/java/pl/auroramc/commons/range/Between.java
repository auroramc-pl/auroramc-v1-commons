package pl.auroramc.commons.range;

public record Between<T extends Comparable<T>>(T min, T max) {

  boolean matches(final T query) {
    return query.compareTo(min) >= 0 && query.compareTo(max) <= 0;
  }
}

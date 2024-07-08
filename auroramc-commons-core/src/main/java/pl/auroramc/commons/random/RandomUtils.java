package pl.auroramc.commons.random;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;

public final class RandomUtils {

  private static final int INTEGER_EPSILON = 1;
  private static final double DOUBLE_EPSILON = 1E-10;
  private static final double MINIMUM_BORDER = 0D;
  private static final double MAXIMUM_BORDER = 100D;

  private RandomUtils() {}

  public static <T> T pickElementOf(final T[] source) {
    return source[ThreadLocalRandom.current().nextInt(source.length)];
  }

  public static <T> T pickElementOf(final Collection<T> source, final IntFunction<T[]> aggregator) {
    return pickElementOf(source.toArray(aggregator));
  }

  public static int pickNumberBetween(final int minimum, final int maximum) {
    return ThreadLocalRandom.current().nextInt(minimum, maximum + INTEGER_EPSILON);
  }

  public static double pickNumberBetween(final double minimum, final double maximum) {
    return ThreadLocalRandom.current().nextDouble(minimum, maximum + DOUBLE_EPSILON);
  }

  public static boolean randomEvent(final double chance) {
    return pickNumberBetween(MINIMUM_BORDER, MAXIMUM_BORDER) > chance;
  }
}

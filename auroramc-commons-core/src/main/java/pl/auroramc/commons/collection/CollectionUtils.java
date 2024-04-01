package pl.auroramc.commons.collection;

import static java.lang.Math.ceil;
import static java.lang.Math.min;
import static java.util.stream.IntStream.iterate;
import static java.util.stream.Stream.concat;

import java.util.Collection;
import java.util.List;
import java.util.function.IntFunction;

public final class CollectionUtils {

  private CollectionUtils() {}

  public static <T> T[] merge(
      final Collection<T> a, final Collection<T> b, final IntFunction<T[]> arrayResolver) {
    return concat(a.stream(), b.stream()).toArray(arrayResolver);
  }

  public static <T> List<List<T>> partition(final List<T> items, final int partitionSize) {
    return iterate(0, index -> index + partitionSize)
        .limit((long) ceil((double) items.size() / partitionSize))
        .mapToObj(index -> items.subList(index, min(index + partitionSize, items.size())))
        .toList();
  }
}

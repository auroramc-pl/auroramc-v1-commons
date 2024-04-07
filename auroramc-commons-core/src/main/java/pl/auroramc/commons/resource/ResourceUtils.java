package pl.auroramc.commons.resource;

import static java.util.Spliterator.ORDERED;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.Collectors.toUnmodifiableSet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.StreamSupport;

public final class ResourceUtils {

  private ResourceUtils() {}

  public static List<File> unpackResources(
      final File file, final File dataFile, final String path) {
    final List<File> resources = new ArrayList<>();
    try (final JarFile jarFile = new JarFile(file)) {
      final Set<JarEntry> entries = getUnmodifiableSetOf(jarFile.entries().asIterator());
      for (final JarEntry entry : entries) {
        final String entryName = entry.getName();
        if (!entryName.startsWith(path + '/') || entryName.endsWith("/")) {
          continue;
        }

        final File resourceFile = new File(dataFile, entryName);
        if (resourceFile.exists()) {
          resources.add(resourceFile);
          continue;
        }

        if (!dataFile.exists() && !dataFile.mkdirs()) {
          throw new IllegalStateException(
              "Could not create data directory for resource file: %s"
                  .formatted(dataFile.getPath()));
        }

        final File parentFile = resourceFile.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
          throw new IllegalStateException(
              "Could not create parent directory for resource file: %s"
                  .formatted(resourceFile.getPath()));
        }

        if (!resourceFile.createNewFile()) {
          throw new IllegalStateException(
              "Could not create resource file: %s".formatted(resourceFile.getPath()));
        }

        try (final FileOutputStream outputStream = new FileOutputStream(resourceFile)) {
          jarFile.getInputStream(entry).transferTo(outputStream);
        }

        resources.add(resourceFile);
      }

      return resources;
    } catch (final IOException exception) {
      throw new IllegalStateException(
          "Could not search or create resources, because of unexpected exception.", exception);
    }
  }

  private static <T> Set<T> getUnmodifiableSetOf(final Iterator<T> iterator) {
    return StreamSupport.stream(spliteratorUnknownSize(iterator, ORDERED), false)
        .collect(toUnmodifiableSet());
  }
}

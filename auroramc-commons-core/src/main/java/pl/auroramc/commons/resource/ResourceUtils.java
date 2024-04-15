package pl.auroramc.commons.resource;

import static java.util.Spliterator.ORDERED;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.Collectors.toUnmodifiableSet;
import static java.util.stream.StreamSupport.stream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class ResourceUtils {

  private ResourceUtils() {}

  public static List<File> unpackResources(
      final File file,
      final File dataFile,
      final String path,
      final String prefix,
      final String suffix) {
    final List<File> resources = new ArrayList<>();
    try (final JarFile jarFile = new JarFile(file)) {
      final Set<JarEntry> entries = getUnmodifiableSet(jarFile.entries().asIterator());
      for (final JarEntry entry : entries) {
        final String entryName = entry.getName();
        if (!isExpectedPath(entryName, path)) {
          continue;
        }

        final String fileName = entryName.substring(path.length() + 1);
        if (!isExpectedFile(fileName, prefix, suffix)) {
          continue;
        }

        final File resourceFile = new File(dataFile, entryName);
        if (!resourceFile.exists()) {
          createNecessaryFilesAndDirectories(dataFile, resourceFile);
          try (final FileOutputStream outputStream = new FileOutputStream(resourceFile)) {
            jarFile.getInputStream(entry).transferTo(outputStream);
          }
        }

        resources.add(resourceFile);
      }

      return resources;
    } catch (final IOException exception) {
      throw new ResourceUnpackingException(
          "Could not search or create resources, because of unexpected exception.", exception);
    }
  }

  private static boolean isExpectedPath(final String entryName, final String path) {
    return entryName.startsWith(path + '/') && !entryName.endsWith("/");
  }

  private static boolean isExpectedFile(
      final String fileName, final String prefix, final String suffix) {
    return fileName.startsWith(prefix) && fileName.endsWith(suffix);
  }

  private static void createNecessaryFilesAndDirectories(
      final File dataFile, final File resourceFile) throws IOException {
    if (!dataFile.exists() && !dataFile.mkdirs()) {
      throw new ResourceUnpackingException(
          "Could not create data directory for resource file: %s".formatted(dataFile.getPath()));
    }

    final File parentFile = resourceFile.getParentFile();
    if (!parentFile.exists() && !parentFile.mkdirs()) {
      throw new ResourceUnpackingException(
          "Could not create parent directory for resource file: %s"
              .formatted(resourceFile.getPath()));
    }

    if (!resourceFile.createNewFile()) {
      throw new ResourceUnpackingException(
          "Could not create resource file: %s".formatted(resourceFile.getPath()));
    }
  }

  private static <T> Set<T> getUnmodifiableSet(final Iterator<T> iterator) {
    return stream(spliteratorUnknownSize(iterator, ORDERED), false).collect(toUnmodifiableSet());
  }
}

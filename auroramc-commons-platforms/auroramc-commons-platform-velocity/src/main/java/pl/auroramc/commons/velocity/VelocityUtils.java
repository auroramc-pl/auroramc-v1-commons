package pl.auroramc.commons.velocity;

import com.velocitypowered.api.proxy.ProxyServer;
import java.io.File;

public final class VelocityUtils {

  private VelocityUtils() {}

  public static void registerListeners(
      final Object plugin, final ProxyServer server, final Object... bunchOfListeners) {
    for (final Object listener : bunchOfListeners) {
      server.getEventManager().register(plugin, listener);
    }
  }

  public static File getJarFile(final Object plugin) {
    try {
      return new File(
          plugin.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
    } catch (final Exception exception) {
      throw new VelocityJarFileRetrievalException(
          "Could not retrieve jar file, because of unexpected exception.", exception);
    }
  }
}

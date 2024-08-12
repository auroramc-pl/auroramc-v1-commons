package pl.auroramc.commons.bukkit;

import static java.util.Optional.ofNullable;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;

public final class BukkitUtils {

  private BukkitUtils() {}

  public static <T> T resolveService(final Server server, final Class<T> serviceType) {
    return ofNullable(server.getServicesManager().getRegistration(serviceType))
        .map(RegisteredServiceProvider::getProvider)
        .orElseThrow(
            () ->
                new BukkitServiceRetrievalException(
                    "Could not resolve %s service through bukkit's services."
                        .formatted(serviceType.getSimpleName())));
  }

  public static void registerServices(final Plugin plugin, final Set<?> services) {
    services.forEach(service -> registerService(plugin, service));
  }

  public static void registerFacades(final Plugin plugin, final Set<?> facades) {
    facades.forEach(facade -> registerFacade(plugin, facade));
  }

  public static <T> void registerService(
      final Plugin plugin, final T service, final Class<? super T> serviceType) {
    plugin
        .getServer()
        .getServicesManager()
        .register(serviceType, service, plugin, ServicePriority.Normal);
  }

  public static <T> void registerService(final Plugin plugin, final T service) {
    // noinspection unchecked
    registerService(plugin, service, (Class<T>) service.getClass());
  }

  public static <T> void registerFacade(final Plugin plugin, final T facade) {
    registerService(plugin, facade, getFacadeType(facade));
  }

  public static void registerListeners(final Plugin plugin, final Listener... bunchOfListeners) {
    for (final Listener listener : bunchOfListeners) {
      plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }
  }

  @SuppressWarnings("unchecked")
  private static <T> Class<T> getFacadeType(final T service) {
    final List<Class<?>> interfaces = getInterfacesOf(service.getClass());
    return (Class<T>)
        (interfaces.isEmpty() ? service.getClass() : interfaces.getFirst());
  }

  private static List<Class<?>> getInterfacesOf(Class<?> clazz) {
    final List<Class<?>> interfaces = new LinkedList<>();
    while (clazz != null) {
      for (final Class<?> value : clazz.getInterfaces()) {
        interfaces.add(value);
        interfaces.addAll(getInterfacesOf(value));
      }
      clazz = clazz.getSuperclass();
    }
    return interfaces;
  }
}

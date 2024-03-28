package pl.auroramc.commons.bukkit.scheduler;

import org.bukkit.plugin.Plugin;
import pl.auroramc.commons.scheduler.Scheduler;

public final class BukkitSchedulerFactory {

  private BukkitSchedulerFactory() {}

  public static Scheduler getBukkitScheduler(final Plugin plugin) {
    return new BukkitSchedulerImpl(plugin, plugin.getServer(), plugin.getServer().getScheduler());
  }
}

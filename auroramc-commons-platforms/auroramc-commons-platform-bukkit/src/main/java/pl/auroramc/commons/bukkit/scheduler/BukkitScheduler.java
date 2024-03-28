package pl.auroramc.commons.bukkit.scheduler;

import org.bukkit.plugin.Plugin;

public class BukkitScheduler {

  private static final long ONE_TICK_DELAY = 1L;
  private final Plugin plugin;

  public BukkitScheduler(final Plugin plugin) {
    this.plugin = plugin;
  }

  public void postToMainThread(final Runnable task) {
    if (plugin.getServer().isPrimaryThread()) {
      task.run();
    } else {
      plugin.getServer().getScheduler().runTask(plugin, task);
    }
  }

  public void postToMainThreadAndNextTick(final Runnable task) {
    if (plugin.getServer().isPrimaryThread()) {
      task.run();
    } else {
      plugin.getServer().getScheduler().runTaskLater(plugin, task, ONE_TICK_DELAY);
    }
  }
}

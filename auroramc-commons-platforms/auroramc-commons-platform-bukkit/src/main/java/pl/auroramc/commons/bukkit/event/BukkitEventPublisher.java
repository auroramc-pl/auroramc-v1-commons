package pl.auroramc.commons.bukkit.event;

import static org.bukkit.Bukkit.getPluginManager;

import org.bukkit.event.Event;
import pl.auroramc.commons.bukkit.scheduler.BukkitScheduler;

public class BukkitEventPublisher {

  private final BukkitScheduler scheduler;

  public BukkitEventPublisher(final BukkitScheduler scheduler) {
    this.scheduler = scheduler;
  }

  public void publish(final Event event) {
    scheduler.postToMainThread(() -> getPluginManager().callEvent(event));
  }
}

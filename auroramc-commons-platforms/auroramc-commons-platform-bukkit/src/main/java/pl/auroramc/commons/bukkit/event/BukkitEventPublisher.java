package pl.auroramc.commons.bukkit.event;

import static org.bukkit.Bukkit.getPluginManager;
import static pl.auroramc.commons.scheduler.SchedulerPoll.SYNC;

import org.bukkit.event.Event;
import pl.auroramc.commons.scheduler.Scheduler;

public class BukkitEventPublisher {

  private final Scheduler scheduler;

  public BukkitEventPublisher(final Scheduler scheduler) {
    this.scheduler = scheduler;
  }

  public void publish(final Event event) {
    scheduler.run(SYNC, () -> getPluginManager().callEvent(event));
  }
}

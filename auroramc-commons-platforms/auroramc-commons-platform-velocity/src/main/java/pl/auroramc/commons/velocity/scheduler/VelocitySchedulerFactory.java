package pl.auroramc.commons.velocity.scheduler;

import pl.auroramc.commons.scheduler.Scheduler;

public final class VelocitySchedulerFactory {

  private VelocitySchedulerFactory() {}

  public static Scheduler getVelocityScheduler(
      final Object plugin, final com.velocitypowered.api.scheduler.Scheduler scheduler) {
    return new VelocitySchedulerImpl(plugin, scheduler);
  }
}

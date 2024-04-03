package pl.auroramc.commons.scheduler.caffeine;

import static pl.auroramc.commons.scheduler.SchedulerPoll.ASYNC;

import java.util.concurrent.Executor;
import org.jetbrains.annotations.NotNull;
import pl.auroramc.commons.scheduler.Scheduler;

public class CaffeineExecutor implements Executor {

  private final Scheduler scheduler;

  public CaffeineExecutor(final Scheduler scheduler) {
    this.scheduler = scheduler;
  }

  @Override
  public void execute(final @NotNull Runnable task) {
    scheduler.run(ASYNC, task);
  }
}

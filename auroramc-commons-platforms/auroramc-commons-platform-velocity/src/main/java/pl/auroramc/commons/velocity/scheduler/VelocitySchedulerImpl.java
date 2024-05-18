package pl.auroramc.commons.velocity.scheduler;

import static java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor;
import static pl.auroramc.commons.scheduler.SchedulerPoll.ASYNC;
import static pl.auroramc.commons.scheduler.SchedulerPoll.SYNC;

import com.pivovarit.function.ThrowingSupplier;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import pl.auroramc.commons.scheduler.Scheduler;
import pl.auroramc.commons.scheduler.SchedulerPoll;

class VelocitySchedulerImpl implements Scheduler {

  private static final ExecutorService VIRTUAL_THREAD_PER_TASK = newVirtualThreadPerTaskExecutor();
  private final Object plugin;
  private final com.velocitypowered.api.scheduler.Scheduler scheduler;

  VelocitySchedulerImpl(
      final Object plugin, final com.velocitypowered.api.scheduler.Scheduler scheduler) {
    this.plugin = plugin;
    this.scheduler = scheduler;
  }

  @Override
  public <T> CompletableFuture<T> supplyLater(
      final SchedulerPoll pool,
      final Duration delay,
      final ThrowingSupplier<T, Exception> supplier) {
    return switch (pool) {
      case SYNC ->
          throw new UnsupportedOperationException(
              "Sync tasks are not supported in Velocity, as main thread there does not exists.");
      case ASYNC -> supplyAsync(supplier, delay);
    };
  }

  private <T> CompletableFuture<T> supplyAsync(
      final ThrowingSupplier<T, Exception> supplier, final Duration duration) {
    final CompletableFuture<T> future = new CompletableFuture<>();
    if (duration.isZero()) {
      VIRTUAL_THREAD_PER_TASK.submit(() -> tryRun(future, supplier));
    } else {
      scheduler.buildTask(plugin, () -> tryRun(future, supplier)).delay(duration).schedule();
    }
    return future;
  }

  private <T> CompletableFuture<T> tryRun(
      final CompletableFuture<T> future, final ThrowingSupplier<T, Exception> supplier) {
    try {
      future.complete(supplier.get());
    } catch (final Exception exception) {
      future.completeExceptionally(exception);
    }
    return future;
  }

  @Override
  public void schedule(final SchedulerPoll poll, final Runnable task, final Duration duration) {
    if (poll == SYNC) {
      throw new UnsupportedOperationException(
          "Sync tasks are not supported in Velocity, as main thread there does not exists.");
    }

    if (poll == ASYNC) {
      scheduleAsync(task, duration);
    }
  }

  private void scheduleAsync(final Runnable task, final Duration duration) {
    scheduler.buildTask(plugin, task).delay(duration).schedule();
  }
}

package pl.auroramc.commons.scheduler;

import static java.time.Duration.ZERO;

import com.pivovarit.function.ThrowingSupplier;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public interface Scheduler {

  default CompletableFuture<Void> run(final SchedulerPoll pool, final Runnable task) {
    return supply(
        pool,
        () -> {
          task.run();
          return null;
        });
  }

  default CompletableFuture<Void> runLater(
      final SchedulerPoll pool, final Duration delay, final Runnable task) {
    return supplyLater(
        pool,
        delay,
        () -> {
          task.run();
          return null;
        });
  }

  default <T> CompletableFuture<T> supply(
      final SchedulerPoll pool, final ThrowingSupplier<T, Exception> supplier) {
    return supplyLater(pool, ZERO, supplier);
  }

  <T> CompletableFuture<T> supplyLater(
      final SchedulerPoll pool,
      final Duration delay,
      final ThrowingSupplier<T, Exception> supplier);

  void schedule(
      final SchedulerPoll poll, final Runnable task, final Duration period);
}

package pl.auroramc.commons.scheduler;

import static java.time.Duration.ZERO;
import static pl.auroramc.commons.scheduler.SchedulerUtils.NIL;

import com.pivovarit.function.ThrowingSupplier;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import pl.auroramc.commons.CompletableFutureUtils;

public interface Scheduler {

  default CompletableFuture<Void> run(final SchedulerPoll pool, final Runnable task) {
    return supply(
            pool,
            () -> {
              task.run();
              return NIL;
            })
        .exceptionally(CompletableFutureUtils::delegateCaughtException);
  }

  default CompletableFuture<Void> runLater(
      final SchedulerPoll pool, final Duration delay, final Runnable task) {
    return supplyLater(
            pool,
            delay,
            () -> {
              task.run();
              return NIL;
            })
        .exceptionally(CompletableFutureUtils::delegateCaughtException);
  }

  default <T> CompletableFuture<T> supply(
      final SchedulerPoll pool, final ThrowingSupplier<T, Exception> supplier) {
    return supplyLater(pool, ZERO, supplier)
        .exceptionally(CompletableFutureUtils::delegateCaughtException);
  }

  <T> CompletableFuture<T> supplyLater(
      final SchedulerPoll pool,
      final Duration delay,
      final ThrowingSupplier<T, Exception> supplier);

  void schedule(final SchedulerPoll poll, final Runnable task, final Duration duration);
}

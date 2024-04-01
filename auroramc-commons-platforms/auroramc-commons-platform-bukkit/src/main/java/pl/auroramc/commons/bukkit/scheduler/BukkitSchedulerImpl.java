package pl.auroramc.commons.bukkit.scheduler;

import static pl.auroramc.commons.scheduler.SchedulerPoll.ASYNC;
import static pl.auroramc.commons.scheduler.SchedulerPoll.SYNC;

import com.pivovarit.function.ThrowingSupplier;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import pl.auroramc.commons.bukkit.time.MinecraftTimeEquivalent;
import pl.auroramc.commons.scheduler.Scheduler;
import pl.auroramc.commons.scheduler.SchedulerPoll;

class BukkitSchedulerImpl implements Scheduler {

  private final Plugin plugin;
  private final Server server;
  private final BukkitScheduler bukkitScheduler;

  BukkitSchedulerImpl(
      final Plugin plugin, final Server server, final BukkitScheduler bukkitScheduler) {
    this.plugin = plugin;
    this.server = server;
    this.bukkitScheduler = bukkitScheduler;
  }

  @Override
  public <T> CompletableFuture<T> supplyLater(
      final SchedulerPoll pool,
      final Duration delay,
      final ThrowingSupplier<T, Exception> supplier) {
    return switch (pool) {
      case SYNC -> supplySync(supplier, delay);
      case ASYNC -> supplyAsync(supplier, delay);
    };
  }

  private <T> CompletableFuture<T> supplySync(
      final ThrowingSupplier<T, Exception> supplier, final Duration delay) {
    final CompletableFuture<T> future = new CompletableFuture<>();
    if (server.isPrimaryThread()) {
      return tryRun(future, supplier);
    }

    if (delay.isZero()) {
      bukkitScheduler.runTask(plugin, () -> tryRun(future, supplier));
    } else {
      bukkitScheduler.runTaskLater(
          plugin, () -> tryRun(future, supplier), MinecraftTimeEquivalent.of(delay));
    }

    return future;
  }

  private <T> CompletableFuture<T> supplyAsync(
      final ThrowingSupplier<T, Exception> supplier, final Duration delay) {
    final CompletableFuture<T> future = new CompletableFuture<>();
    if (delay.isZero()) {
      bukkitScheduler.runTaskAsynchronously(plugin, () -> tryRun(future, supplier));
    } else {
      bukkitScheduler.runTaskLaterAsynchronously(
          plugin, () -> tryRun(future, supplier), MinecraftTimeEquivalent.of(delay));
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
  public void schedule(final SchedulerPoll poll, final Runnable task, final Duration period) {
    if (poll == SYNC) {
      scheduleSync(task, period);
    }

    if (poll == ASYNC) {
      scheduleAsync(task, period);
    }
  }

  private void scheduleSync(final Runnable task, final Duration period) {
    final long delay = MinecraftTimeEquivalent.of(period);
    bukkitScheduler.runTaskTimer(plugin, task, delay, delay);
  }

  private void scheduleAsync(final Runnable task, final Duration period) {
    final long delay = MinecraftTimeEquivalent.of(period);
    bukkitScheduler.runTaskTimerAsynchronously(plugin, task, delay, delay);
  }
}

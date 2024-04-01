package pl.auroramc.commons.bukkit.time;

import java.time.Duration;

public class MinecraftTimeEquivalent {

  private static final long TICKS_PER_SECOND = 20;
  private static final long MILLISECONDS_PER_SECOND = 1000;
  private static final long MILLISECONDS_PER_TICK = MILLISECONDS_PER_SECOND / TICKS_PER_SECOND;

  public static final long TICK = 1;
  public static final long SECOND = 20;
  public static final long MINUTE = SECOND * 60;
  public static final long HOUR = MINUTE * 60;

  private MinecraftTimeEquivalent() {}

  public static long of(final Duration period) {
    return TICK * (period.toMillis() / MILLISECONDS_PER_TICK);
  }
}

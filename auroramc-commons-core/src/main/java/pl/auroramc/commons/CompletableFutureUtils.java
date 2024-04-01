package pl.auroramc.commons;

import static java.util.logging.Level.SEVERE;

import java.util.logging.Logger;

public final class CompletableFutureUtils {

  private static final String EXCEPTION_CAUGHT_MESSAGE =
      "Caught an exception in future execution: %s";
  private static final Logger LOGGER = Logger.getGlobal();

  private CompletableFutureUtils() {}

  public static <T> T delegateCaughtException(final Throwable cause) {
    LOGGER.log(SEVERE, EXCEPTION_CAUGHT_MESSAGE.formatted(cause.getMessage()), cause);
    return null;
  }
}
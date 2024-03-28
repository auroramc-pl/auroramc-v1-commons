package pl.auroramc.commons.bukkit.integration;

import org.bukkit.Server;

public interface ExternalIntegration {

  boolean isSupportedEnvironment(final Server server);

  void configure();
}

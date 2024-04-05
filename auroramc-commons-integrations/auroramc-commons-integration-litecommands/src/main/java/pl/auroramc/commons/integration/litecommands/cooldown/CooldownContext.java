package pl.auroramc.commons.integration.litecommands.cooldown;

import java.time.Duration;

record CooldownContext(String key, Duration period) {}

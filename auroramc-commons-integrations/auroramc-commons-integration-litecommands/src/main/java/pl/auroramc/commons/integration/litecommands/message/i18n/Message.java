package pl.auroramc.commons.integration.litecommands.message.i18n;

import java.util.HashMap;
import java.util.Map;

public record Message(String key, Map<String, Object> placeholders) {

  private Message(String key) {
    this(key, new HashMap<>());
  }

  public static Message of(final String key) {
    return new Message(key);
  }

  public Message placeholder(final String path, final Object value) {
    placeholders.put(path, value);
    return this;
  }
}

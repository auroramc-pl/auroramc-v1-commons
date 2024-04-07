package pl.auroramc.messages.i18n;

import java.io.File;
import java.util.Locale;
import org.bukkit.command.CommandSender;
import pl.auroramc.messages.i18n.locale.BukkitLocaleProvider;
import pl.auroramc.messages.i18n.locale.LocaleProvider;
import pl.auroramc.messages.message.MutableMessage;

public interface BukkitMessageFacade extends MessageFacade<MutableMessage, CommandSender> {

  static BukkitMessageFacade getBukkitMessageFacade(final Locale fallbackLocale) {
    return getBukkitMessageFacade(fallbackLocale, new BukkitLocaleProvider(fallbackLocale));
  }

  static BukkitMessageFacade getBukkitMessageFacade(
      final Locale fallbackLocale, final LocaleProvider<CommandSender> localeProvider) {
    return new BukkitMessageFacadeImpl(fallbackLocale, localeProvider);
  }

  BukkitMessageFacade registerResources(
      final Class<? extends MessageSource> messageSourceType,
      final File jarFile,
      final File dataPath,
      final String path,
      final String prefix,
      final String suffix);
}

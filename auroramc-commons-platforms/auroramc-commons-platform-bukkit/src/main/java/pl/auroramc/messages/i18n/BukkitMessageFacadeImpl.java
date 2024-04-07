package pl.auroramc.messages.i18n;

import static pl.auroramc.commons.resource.ResourceUtils.unpackResources;
import static pl.auroramc.messages.i18n.MessageSourceUtils.getLocaleByFile;
import static pl.auroramc.messages.i18n.MessageSourceUtils.getMessageSource;

import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import java.io.File;
import java.util.Locale;
import org.bukkit.command.CommandSender;
import pl.auroramc.messages.i18n.locale.LocaleProvider;

class BukkitMessageFacadeImpl extends MutableMessageService<CommandSender> implements BukkitMessageFacade {

  BukkitMessageFacadeImpl(
      final Locale fallbackLocale, final LocaleProvider<CommandSender> localeProvider) {
    super(YamlBukkitConfigurer::new, fallbackLocale, localeProvider);
  }

  @Override
  public BukkitMessageFacade registerResources(
      final Class<? extends MessageSource> messageSourceType,
      final File jarFile,
      final File dataPath,
      final String path,
      final String prefix,
      final String suffix) {
    unpackResources(jarFile, dataPath, path)
        .forEach(resourceFile -> registerResource(messageSourceType, prefix, suffix, resourceFile));
    return this;
  }

  private void registerResource(
      final Class<? extends MessageSource> messageSourceType,
      final String prefix,
      final String suffix,
      final File resourceFile) {
    registerMessageSource(
        getLocaleByFile(prefix, suffix, resourceFile),
        getMessageSource(messageSourceType, new YamlBukkitConfigurer(), resourceFile));
  }
}

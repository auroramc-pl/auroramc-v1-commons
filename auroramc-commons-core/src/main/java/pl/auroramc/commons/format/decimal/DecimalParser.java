package pl.auroramc.commons.format.decimal;

import static java.lang.Character.isDigit;

import java.math.BigDecimal;

public class DecimalParser {

  private static final char PARTS_DELIMITER = '.';
  private static final BigDecimal[] SCALE_FACTORS_BY_SUFFIXES = {
    BigDecimal.valueOf(1_000L),
    BigDecimal.valueOf(1_000_000L),
    BigDecimal.valueOf(1_000_000_000L),
    BigDecimal.valueOf(1_000_000_000_000L),
    BigDecimal.valueOf(1_000_000_000_000_000L),
    BigDecimal.valueOf(1_000_000_000_000_000_000L)
  };
  private static final String SUFFIX_CHARACTERS = "kmgtpe";

  private DecimalParser() {}

  public static BigDecimal getParsedDecimal(final String input) {
    final StringBuilder integralPart = new StringBuilder();
    final StringBuilder fractionalPart = new StringBuilder();

    boolean isIntegral = true;
    for (int index = 0; index < input.length(); index++) {
      final char character = input.charAt(index);
      if (character == PARTS_DELIMITER) {
        isIntegral = false;
        continue;
      }

      if (isDigit(character)) {
        if (isIntegral) {
          integralPart.append(character);
        } else {
          fractionalPart.append(character);
        }
        continue;
      }

      final int scaleFactorIndex = SUFFIX_CHARACTERS.indexOf(character);
      if (scaleFactorIndex != -1) {
        return getParsedDecimal(integralPart, fractionalPart, scaleFactorIndex);
      }

      break;
    }

    try {
      return new BigDecimal(input);
    } catch (final NumberFormatException exception) {
      return null;
    }
  }

  private static BigDecimal getParsedDecimal(
      final StringBuilder integralPart,
      final StringBuilder fractionalPart,
      final int scaleFactorIndex) {
    return new BigDecimal(integralPart.toString())
        .add(new BigDecimal("0." + fractionalPart.toString()))
        .multiply(SCALE_FACTORS_BY_SUFFIXES[scaleFactorIndex]);
  }
}

package pl.auroramc.commons.sql.repository;

import static java.util.Optional.ofNullable;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.function.Function;

public final class SqlRepositoryUtils {

  private SqlRepositoryUtils() {}

  public static Instant getInstant(final ResultSet resultSet, final String columnName)
      throws SQLException {
    return mapValueOrNull(resultSet.getTimestamp(columnName), Timestamp::toInstant);
  }

  public static void setInstant(
      final PreparedStatement statement, final int parameterIndex, final Instant value)
      throws SQLException {
    statement.setTimestamp(parameterIndex, mapValueOrNull(value, Timestamp::from));
  }

  public static LocalDate getLocalDate(final ResultSet resultSet, final String columnName)
      throws SQLException {
    return mapValueOrNull(resultSet.getDate(columnName), Date::toLocalDate);
  }

  public static void setLocalDate(
      final PreparedStatement statement, final int parameterIndex, final LocalDate value)
      throws SQLException {
    statement.setDate(parameterIndex, mapValueOrNull(value, Date::valueOf));
  }

  private static <T, R> R mapValueOrNull(final T value, final Function<T, R> mapper) {
    return ofNullable(value).map(mapper).orElse(null);
  }
}

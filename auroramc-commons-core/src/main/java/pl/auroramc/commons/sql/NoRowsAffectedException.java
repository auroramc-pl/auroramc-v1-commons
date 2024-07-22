package pl.auroramc.commons.sql;

import java.sql.SQLException;

public class NoRowsAffectedException extends SQLException {

  public NoRowsAffectedException(final String reason) {
    super(reason);
  }
}

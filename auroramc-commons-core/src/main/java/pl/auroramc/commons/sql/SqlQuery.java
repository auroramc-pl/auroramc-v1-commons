package pl.auroramc.commons.sql;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.intellij.lang.annotations.Language;

@Retention(RUNTIME)
@Target(FIELD)
@Language("MariaDB")
public @interface SqlQuery {}
